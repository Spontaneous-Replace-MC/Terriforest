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

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.eerie.EerieRindBehavior;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.eerie.EerieRindShape;

import static net.minecraft.block.Blocks.*;

/**
 * <h2 style="color:FFC800">岩浆阴森木壳方块</h2>
 * 含有岩浆的阴森木壳
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class LavaEerieRindBlock extends AbstractCauldronBlock {
	public static final MapCodec<LavaEerieRindBlock> CODEC = createCodec(LavaEerieRindBlock::new);
	
	public LavaEerieRindBlock(Settings settings) {
		super(settings, EerieRindBehavior.INSTANCE.lavaBehavior);
	}
	
	/**
	 * <p>如果上方方块变为含非岩浆壳则传递熄灭</p>
	 * <p>如果下方方块变为空气则传递岩浆到下方</p>
	 * <p>如果下方方块变为空壳则传递岩浆到下方</p>
	 */
	@Override
	@SuppressWarnings("deprecation")
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction == Direction.UP && (neighborState.isOf(Blocks.WATER_EERIE_RIND) || neighborState.isOf(Blocks.POWDER_SNOW_EERIE_RIND))) {
			world.setBlockState(neighborPos, Blocks.EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			world.playSound(null, neighborPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
		}
		if (direction != Direction.DOWN) return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
		if (neighborState.isAir()) {
			world.setBlockState(pos, Blocks.EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			world.setBlockState(neighborPos, LAVA.getDefaultState(), Block.NOTIFY_ALL);
		}
		if (neighborState.isOf(Blocks.EERIE_RIND)) {
			world.setBlockState(pos, Blocks.EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			world.setBlockState(neighborPos, state, Block.NOTIFY_ALL);
		}
		if (neighborState.isOf(CAULDRON)) {
			world.setBlockState(pos, Blocks.EERIE_RIND.getDefaultState(), Block.NOTIFY_ALL);
			world.setBlockState(neighborPos, LAVA_CAULDRON.getDefaultState(), Block.NOTIFY_ALL);
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
	
	@Override
	public boolean isFull(BlockState state) {
		return true;
	}
	
	@Override
	protected double getFluidHeight(BlockState state) {
		return 0.9375;
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return EerieRindBlock.MAX_LEVEL;
	}
	
	@Override
	public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
		return EerieRindShape.RAYCAST_SHAPE;
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return EerieRindShape.OUTLINE_SHAPE;
	}
	
	@Override
	protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
		return CODEC;
	}
}
