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

import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;
import pers.saikel0rado1iu.silk.api.magiccube.cauldron.LeveledCauldronLikeBlock;
import pers.saikel0rado1iu.silk.api.spore.EntityUtil;

/**
 * <h2 style="color:FFC800">水平阴森木壳方块</h2>
 * 可调整液面的阴森木壳
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class LeveledEerieRindBlock extends LeveledCauldronLikeBlock {
	public LeveledEerieRindBlock(Biome.Precipitation precipitation, CauldronBehavior.CauldronBehaviorMap behaviorMap, Settings settings) {
		super(precipitation, behaviorMap, settings);
	}
	
	/**
	 * 液位属性
	 *
	 * @return 整数属性
	 */
	@Override
	public IntProperty level() {
		return Properties.LEVEL_3;
	}
	
	/**
	 * 最大液位
	 *
	 * @return 一个正整数
	 */
	@Override
	public int maxLevel() {
		return 3;
	}
	
	/**
	 * 递减液位方法
	 *
	 * @param world 存档世界
	 * @param state 方块状态
	 * @param pos   方块位置
	 */
	@Override
	public void decrementFluidLevel(World world, BlockState state, BlockPos pos) {
		int i = state.get(LEVEL) - 1;
		BlockState blockState = i == 0 ? Blocks.EERIE_RIND.getDefaultState() : state.with(LEVEL, i);
		world.setBlockState(pos, blockState);
		world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
	}
	
	/**
	 * 有随机刻
	 */
	@Override
	public boolean hasRandomTicks(BlockState state) {
		return true;
	}
	
	/**
	 * 随机灵魂粒子效果
	 */
	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		if (random.nextInt(3) == 0) {
			java.util.Random randomValue = new java.util.Random();
			world.addParticle(ParticleTypes.SCULK_SOUL,
					pos.getX() + EntityUtil.POS_SHIFTING + randomValue.nextDouble(-1, 1),
					pos.getY() + EntityUtil.POS_SHIFTING + randomValue.nextDouble(-1, 1),
					pos.getZ() + EntityUtil.POS_SHIFTING + randomValue.nextDouble(-1, 1),
					randomValue.nextDouble(-0.07, 0.07),
					randomValue.nextDouble(0, 0.1),
					randomValue.nextDouble(-0.07, 0.07));
		}
	}
	
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (!world.isClient && entity.isOnFire() && this.isEntityTouchingFluid(state, pos, entity)) {
			if (entity.canModifyAt(world, pos)) onFireCollision(state, world, pos);
			entity.extinguish();
		}
	}
	
	protected void onFireCollision(BlockState state, World world, BlockPos pos) {
		world.setBlockState(pos, state);
	}
}
