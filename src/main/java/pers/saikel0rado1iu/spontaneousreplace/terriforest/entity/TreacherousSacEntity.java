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

package pers.saikel0rado1iu.spontaneousreplace.terriforest.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import pers.saikel0rado1iu.silk.api.base.common.util.TickUtil;
import pers.saikel0rado1iu.silk.api.spore.TntLikeEntity;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.Blocks;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.treacherous.TreacherousData;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.entity.effect.StatusEffects;

import java.util.Optional;

/**
 * <h2 style="color:FFC800">诡谲囊实体</h2>
 * 诡谲囊的实体
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class TreacherousSacEntity extends TntLikeEntity {
	private static final int DEFAULT_FUSE = TickUtil.getTick(1);
	public final AnimationState explodeAnimationState = new AnimationState();
	
	public TreacherousSacEntity(EntityType<?> type, World world) {
		this(type, world, Optional.empty());
	}
	
	public TreacherousSacEntity(World world, Optional<LivingEntity> causingEntity) {
		super(EntityTypes.TREACHEROUS_SAC, world, causingEntity);
	}
	
	public TreacherousSacEntity(EntityType<?> type, World world, Optional<LivingEntity> causingEntity) {
		super(type, world, causingEntity);
	}
	
	/**
	 * 爆炸能量为 {@link TreacherousData#EXPLODE_POWER}
	 */
	@Override
	protected void explode() {
		getWorld().createExplosion(this, getX(), getBodyY(0.0625), getZ(), TreacherousData.EXPLODE_POWER, World.ExplosionSourceType.BLOCK);
	}
	
	/**
	 * 爆炸会产生一大片酸性气体云
	 */
	@Override
	public void tick() {
		int fuse = getFuse() - 1;
		setFuse(fuse);
		if (fuse <= 0) {
			discard();
			if (getWorld().isClient) return;
			explode();
			AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(getWorld(), getX(), getBodyY(0.0625), getZ());
			areaEffectCloudEntity.addEffect(new StatusEffectInstance(StatusEffects.ACIDIZE, TickUtil.getTick(15), 1));
			areaEffectCloudEntity.setOwner(getOwner());
			areaEffectCloudEntity.setRadius(3.0F);
			areaEffectCloudEntity.setRadiusOnUse(-0.5F);
			areaEffectCloudEntity.setWaitTime(0);
			areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float) areaEffectCloudEntity.getDuration());
			getWorld().spawnEntity(areaEffectCloudEntity);
		} else {
			if (getWorld().isClient) explodeAnimationState.startIfNotRunning(age);
			updateWaterState();
		}
	}
	
	/**
	 * 不处理移动
	 */
	@Override
	public void move(MovementType movementType, Vec3d movement) {
	}
	
	/**
	 * 对爆炸免疫
	 */
	@Override
	public boolean isImmuneToExplosion(Explosion explosion) {
		return true;
	}
	
	/**
	 * 引信刻数
	 *
	 * @return 引燃 TNT 所需的刻数
	 */
	@Override
	public int fuseTick() {
		return DEFAULT_FUSE;
	}
	
	/**
	 * TNT 方块
	 *
	 * @return 此实体所依赖的 TNT 块
	 */
	@Override
	public Block tntBlock() {
		return Blocks.TREACHEROUS_SAC;
	}
}
