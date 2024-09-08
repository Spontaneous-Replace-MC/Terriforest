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
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import pers.saikel0rado1iu.silk.api.spore.EntityUtil;

/**
 * <h2 style="color:FFC800">冰阴森木壳方块</h2>
 * 含有冰的阴森木壳方块
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class IceEerieRindBlock extends Block {
	public static final MapCodec<IceEerieRindBlock> CODEC = createCodec(IceEerieRindBlock::new);
	
	public IceEerieRindBlock(Settings settings) {
		super(settings);
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
	protected MapCodec<? extends Block> getCodec() {
		return CODEC;
	}
}
