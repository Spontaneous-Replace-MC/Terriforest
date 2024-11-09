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
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.VineLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import pers.saikel0rado1iu.silk.api.base.common.util.ParticleUtil;
import pers.saikel0rado1iu.silk.api.base.common.util.TickUtil;
import pers.saikel0rado1iu.silk.api.spore.EntityUtil;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.treacherous.TreacherousData;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.entity.effect.StatusEffects;

/**
 * <h2 style="color:FFC800">诡谲藤</h2>
 * 一种特殊的悬挂类藤蔓，接触后有概率获得 {@link StatusEffects#ACIDIZE} 效果
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class TreacherousVinesBlock extends AbstractPlantStemBlock {
	public static final VoxelShape SHAPE = Block.createCuboidShape(4, 9, 4, 12, 16, 12);
	public static final MapCodec<TreacherousVinesBlock> CODEC = createCodec(TreacherousVinesBlock::new);
	
	public TreacherousVinesBlock(Settings settings) {
		super(settings, Direction.DOWN, SHAPE, false, 0.1);
	}
	
	/**
	 * 随机酸气粒子效果
	 */
	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		if (random.nextInt(5) != 0) return;
		java.util.Random randomValue = new java.util.Random();
		ParticleUtil.addEffectParticle(world, StatusEffects.ACIDIZE.value(),
				pos.getX() + EntityUtil.POS_SHIFTING + randomValue.nextDouble(-0.5, 0.5),
				pos.getY() + EntityUtil.POS_SHIFTING + randomValue.nextDouble(-0.5, 0.5),
				pos.getZ() + EntityUtil.POS_SHIFTING + randomValue.nextDouble(-0.5, 0.5));
	}
	
	/**
	 * 玩家接触会酸化
	 */
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		super.onEntityCollision(state, world, pos, entity);
		if (world.isClient) return;
		if (!(entity instanceof LivingEntity living) || new java.util.Random().nextInt((int) Math.pow(TreacherousData.STABILITY.applyAsInt(state), 3)) != 0) {
			return;
		}
		living.addStatusEffect(new StatusEffectInstance(StatusEffects.ACIDIZE, TickUtil.getTick(3)));
	}
	
	@Override
	protected Block getPlant() {
		return Blocks.TREACHEROUS_VINES_PLANT;
	}
	
	@Override
	protected int getGrowthLength(Random random) {
		return VineLogic.getGrowthLength(random);
	}
	
	@Override
	protected boolean chooseStemState(BlockState state) {
		return VineLogic.isValidForWeepingStem(state);
	}
	
	@Override
	protected MapCodec<? extends AbstractPlantStemBlock> getCodec() {
		return CODEC;
	}
}
