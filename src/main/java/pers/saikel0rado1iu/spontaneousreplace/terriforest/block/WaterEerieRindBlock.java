/*
 * MIT License
 *
 * Copyright (c) 2023 GameGeek-Saikel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package pers.saikel0rado1iu.spontaneousreplace.terriforest.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.eerie.EerieRindBehavior;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.eerie.EerieRindShape;

import static net.minecraft.block.Blocks.*;

/**
 * <h2 style="color:FFC800">水阴森木壳方块</h2>
 * 含有水的阴森木壳方块
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class WaterEerieRindBlock extends LeveledEerieRindBlock {
	public WaterEerieRindBlock(Settings settings) {
		super(Biome.Precipitation.RAIN, EerieRindBehavior.INSTANCE.waterBehavior, settings);
	}
	
	/**
	 * 阴森木极寒，在阴森木壳中的实体会冻伤
	 */
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		super.onEntityCollision(state, world, pos, entity);
		if (!(entity instanceof LivingEntity) || entity.getBlockStateAtPos().isOf(this)) entity.slowMovement(state, new Vec3d(0.9, 1.5, 0.9));
		entity.setInPowderSnow(true);
		entity.setFrozenTicks(entity.getFrozenTicks() + 2);
		if (world.isClient) return;
		entity.setOnFire(false);
		if (entity.isFrozen()) entity.damage(entity.getDamageSources().freeze(), 1);
	}
	
	/**
	 * 会随机变成冻成冰
	 */
	@Override
	@SuppressWarnings("deprecation")
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		super.randomTick(state, world, pos, random);
		world.setBlockState(pos, Blocks.ICE_EERIE_RIND.getDefaultState());
		for (Direction direction : DIRECTIONS) {
			if (world.getBlockState(pos.offset(direction)).isOf(WATER) && random.nextBoolean())
				world.setBlockState(pos.offset(direction), ICE.getDefaultState());
		}
	}
	
	/**
	 * <p>如果上方方块变为含岩浆壳则替换水</p>
	 * <p>如果上方方块变为含细雪壳则变为水</p>
	 * <p>如果下方方块变为空气则传递水到下方</p>
	 * <p>如果下方方块变为空壳则传递含水块到下方</p>
	 */
	@Override
	@SuppressWarnings("deprecation")
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction == Direction.UP) {
			if (neighborState.isOf(Blocks.LAVA_EERIE_RIND)) {
				world.setBlockState(pos, Blocks.LAVA_EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
				world.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
				world.setBlockState(neighborPos, Blocks.EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			} else if (neighborState.isOf(Blocks.WATER_EERIE_RIND) || neighborState.isOf(Blocks.POWDER_SNOW_EERIE_RIND)) {
				int upLevel = Math.min(Block.NOTIFY_ALL, state.get(level()) + neighborState.get(level()));
				int posLevel = state.get(level()) + neighborState.get(level()) - MAX_LEVEL;
				world.setBlockState(pos, state.with(level(), upLevel), Block.NOTIFY_ALL);
				if (posLevel > 0)
					world.setBlockState(neighborPos, state.with(level(), posLevel), Block.NOTIFY_ALL);
				else world.setBlockState(neighborPos, Blocks.EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			}
		}
		if (direction != Direction.DOWN)
			return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
		if (neighborState.isAir()) {
			world.setBlockState(pos, Blocks.EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			if (isFull(state))
				world.setBlockState(neighborPos, WATER.getDefaultState(), Block.NOTIFY_ALL);
		}
		if (neighborState.isOf(Blocks.EERIE_RIND)) {
			world.setBlockState(pos, Blocks.EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			world.setBlockState(neighborPos, state, Block.NOTIFY_ALL);
		}
		if (neighborState.isOf(CAULDRON)) {
			world.setBlockState(pos, Blocks.EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			world.setBlockState(neighborPos, WATER_CAULDRON.getDefaultState().with(level(), state.get(level())), Block.NOTIFY_ALL);
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
	
	@Override
	public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
		return EerieRindShape.RAYCAST_SHAPE;
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return EerieRindShape.OUTLINE_SHAPE;
	}
}
