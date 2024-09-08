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
import net.minecraft.block.SaplingBlock;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import pers.saikel0rado1iu.silk.api.magiccube.infect.InfectSapling;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.feature.ConfiguredFeatures;

/**
 * <h2 style="color:FFC800">阴森浮土方块</h2>
 * 一种特殊的感染方块，会将树苗感染成阴森木
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class EerieRegolithBlock extends Block implements InfectSapling {
	public static final MapCodec<EerieRegolithBlock> CODEC = createCodec(EerieRegolithBlock::new);
	
	public EerieRegolithBlock(Settings settings) {
		super(settings);
	}
	
	/**
	 * 树苗的生成方法
	 *
	 * @param world   服务区存档世界
	 * @param pos     方块位置
	 * @param state   方块状态
	 * @param random  随机数
	 * @param sapling 树苗方块
	 */
	@Override
	public void generate(ServerWorld world, BlockPos pos, BlockState state, Random random, SaplingBlock sapling) {
		world.setBlockState(pos, Blocks.AIR.getDefaultState());
		ConfiguredFeature<?, ?> registryEntry = world.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE).getEntry(ConfiguredFeatures.EERIE_TREE).orElseThrow().value();
		if (!registryEntry.generate(world, world.getChunkManager().getChunkGenerator(), random, pos)) world.setBlockState(pos, state);
	}
	
	@Override
	protected MapCodec<? extends Block> getCodec() {
		return CODEC;
	}
}
