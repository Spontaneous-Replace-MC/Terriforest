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
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import pers.saikel0rado1iu.silk.api.magiccube.TntLikeBlock;
import pers.saikel0rado1iu.silk.api.spore.EntityUtil;
import pers.saikel0rado1iu.silk.api.spore.TntLikeEntity;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.treacherous.TreacherousData;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.entity.TreacherousSacEntity;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.entity.effect.StatusEffects;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.sound.SoundEvents;

import java.util.Optional;

/**
 * <h2 style="color:FFC800">诡谲囊</h2>
 * 一种特殊的植株方块，在受到强烈击打时会爆炸
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class TreacherousSacBlock extends TntLikeBlock {
	public static final MapCodec<TreacherousSacBlock> CODEC = TreacherousSacBlock.createCodec(TreacherousSacBlock::new);
	
	public TreacherousSacBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	protected void primeTnt(World world, BlockPos pos, Optional<LivingEntity> igniter) {
		if (world.isClient) return;
		TntLikeEntity tnt = createEntity(world, pos.getX() + EntityUtil.POS_SHIFTING, pos.getY(), pos.getZ() + EntityUtil.POS_SHIFTING, igniter);
		world.spawnEntity(tnt);
		world.playSound(null, tnt.getX(), tnt.getY(), tnt.getZ(), SoundEvents.TREACHEROUS_SAC_BREAK, SoundCategory.BLOCKS, 1, 1);
		world.emitGameEvent(igniter.orElse(null), GameEvent.PRIME_FUSE, pos);
	}
	
	/**
	 * 随机酸气粒子效果
	 */
	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		if (random.nextInt(3) != 0) return;
		ParticleUtil.spawnParticle(world, pos, random, StatusEffects.ACIDIZE.value().createParticle(new StatusEffectInstance(StatusEffects.ACIDIZE)));
	}
	
	/**
	 * 如果在方块上跳跃则有概率会爆炸
	 */
	@Override
	public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		if (!world.isClient && world.random.nextFloat() < fallDistance - 0.5F
				&& entity instanceof LivingEntity
				&& entity.getWidth() * entity.getWidth() * entity.getHeight() > 0.512F
				&& new java.util.Random().nextInt(TreacherousData.STABILITY.applyAsInt(state)) == 0) {
			world.removeBlock(pos, false);
			primeTnt(world, pos, Optional.of((LivingEntity) entity));
		}
		super.onLandedUpon(world, state, pos, entity, fallDistance);
	}
	
	/**
	 * 如果被斧右键使用则会爆炸
	 */
	@Override
	protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		Item item = stack.getItem();
		if (!(item instanceof AxeItem)) return onUseWithItemSuper(stack, state, world, pos, player, hand, hit);
		if (!world.isClient) {
			stack.damage(1, player, LivingEntity.getSlotForHand(hand));
			world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL_AND_REDRAW);
			primeTnt(world, pos, Optional.of(player));
		}
		player.incrementStat(Stats.USED.getOrCreateStat(item));
		return ItemActionResult.success(world.isClient);
	}
	
	/**
	 * 如果被弹射物击中
	 */
	@Override
	public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
		if (world.isClient) return;
		BlockPos blockPos = hit.getBlockPos();
		Entity entity = projectile.getOwner();
		if (!(projectile instanceof PersistentProjectileEntity) || !projectile.canModifyAt(world, blockPos)) return;
		world.removeBlock(blockPos, false);
		primeTnt(world, blockPos, entity instanceof LivingEntity ? Optional.of((LivingEntity) entity) : Optional.empty());
	}
	
	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
	}
	
	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
	}
	
	@Override
	protected TntLikeEntity createEntity(World world, double x, double y, double z, Optional<LivingEntity> igniter) {
		TntLikeEntity tnt = new TreacherousSacEntity(world, igniter);
		tnt.refreshPositionAndAngles(x, y, z, 0, 0);
		return tnt;
	}
	
	@Override
	protected MapCodec<? extends TntLikeBlock> getCodec() {
		return CODEC;
	}
}
