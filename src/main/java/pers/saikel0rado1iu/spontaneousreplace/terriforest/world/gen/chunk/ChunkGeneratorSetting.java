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

package pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.chunk;

import net.minecraft.block.Blocks;
import net.minecraft.registry.*;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctions;
import net.minecraft.world.gen.noise.NoiseRouter;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import pers.saikel0rado1iu.silk.api.generate.world.ChunkGeneratorSettingEntry;
import pers.saikel0rado1iu.silk.api.generate.world.chunk.ChunkGenerationShapeConfig;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.biome.source.util.ClassicBiomeParameters;

/**
 * <h2 style="color:FFC800">区块生成器设置集</h2>
 * 毛骨森然的所有区块生成器设置
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface ChunkGeneratorSetting extends ChunkGeneratorSettingEntry {
	ChunkGeneratorSetting INSTANCE = new ChunkGeneratorSetting() {
	};
	
	RegistryKey<ChunkGeneratorSettings> CLASSIC = ChunkGeneratorSettingEntry.of(SpontaneousReplace.INSTANCE, "classic");
	RegistryKey<ChunkGeneratorSettings> SNAPSHOT = ChunkGeneratorSettingEntry.of(SpontaneousReplace.INSTANCE, "snapshot");
	
	@SuppressWarnings("SameParameterValue")
	private static ChunkGeneratorSettings createSurfaceSettings(Registerable<?> registerable, boolean amplified, boolean largeBiomes) {
		return new ChunkGeneratorSettings(ChunkGenerationShapeConfig.SURFACE, Blocks.STONE.getDefaultState(), Blocks.WATER.getDefaultState(),
				DF.createSurfaceNoiseRouter(registerable.getRegistryLookup(RegistryKeys.DENSITY_FUNCTION), registerable.getRegistryLookup(RegistryKeys.NOISE_PARAMETERS), largeBiomes, amplified),
				VanillaSurfaceRules.createOverworldSurfaceRule(), new ClassicBiomeParameters().getSpawnSuitabilityNoises(), 63, false, true, true, false);
	}
	
	@Override
	default RegistryBuilder.BootstrapFunction<ChunkGeneratorSettings> bootstrap() {
		return registerable -> {
			registerable.register(CLASSIC, ChunkGeneratorSetting.createSurfaceSettings(registerable, false, false));
			registerable.register(SNAPSHOT, ChunkGeneratorSetting.createSurfaceSettings(registerable, false, false));
		};
	}
	
	class DF extends DensityFunctions {
		public static NoiseRouter createSurfaceNoiseRouter(RegistryEntryLookup<DensityFunction> densityFunctionLookup, RegistryEntryLookup<DoublePerlinNoiseSampler.NoiseParameters> noiseParametersLookup, boolean largeBiomes, boolean amplified) {
			return DensityFunctions.createSurfaceNoiseRouter(densityFunctionLookup, noiseParametersLookup, largeBiomes, amplified);
		}
	}
}
