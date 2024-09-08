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

package pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.biome.source;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.MultiNoiseBiomeSourceParameterList;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import pers.saikel0rado1iu.silk.api.generate.world.biome.source.MultiNoiseBiomeSourceFunction;
import pers.saikel0rado1iu.silk.api.spinningjenny.world.biome.source.BiomeSourceParamListPresetRegistry;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.biome.source.util.ClassicBiomeParameters;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.biome.source.util.SnapshotBiomeParameters;

import java.util.function.Function;

/**
 * <h2 style="color:FFC800">生物群系源参数列表预设集</h2>
 * 毛骨森然的所有生物群系源参数列表预设
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface BiomeSourceParamListPresets extends BiomeSourceParamListPresetRegistry {
	MultiNoiseBiomeSourceParameterList.Preset CLASSIC = BiomeSourceParamListPresetRegistry.registrar(new MultiNoiseBiomeSourceParameterList.Preset(SpontaneousReplace.INSTANCE.ofId("classic"), new MultiNoiseBiomeSourceFunction() {
		@Override
		public <T> MultiNoiseUtil.Entries<T> apply(Function<RegistryKey<Biome>, T> biomeEntryGetter) {
			ImmutableList.Builder<Pair<MultiNoiseUtil.NoiseHypercube, T>> builder = ImmutableList.builder();
			new ClassicBiomeParameters().writeOverworldBiomeParameters(pair -> builder.add(pair.mapSecond(biomeEntryGetter)));
			return new MultiNoiseUtil.Entries<>(builder.build());
		}
	})).register(SpontaneousReplace.INSTANCE, "classic");
	MultiNoiseBiomeSourceParameterList.Preset SNAPSHOT = BiomeSourceParamListPresetRegistry.registrar(new MultiNoiseBiomeSourceParameterList.Preset(SpontaneousReplace.INSTANCE.ofId("snapshot"), new MultiNoiseBiomeSourceFunction() {
		@Override
		public <T> MultiNoiseUtil.Entries<T> apply(Function<RegistryKey<Biome>, T> biomeEntryGetter) {
			ImmutableList.Builder<Pair<MultiNoiseUtil.NoiseHypercube, T>> builder = ImmutableList.builder();
			new SnapshotBiomeParameters().writeOverworldBiomeParameters(pair -> builder.add(pair.mapSecond(biomeEntryGetter)));
			return new MultiNoiseUtil.Entries<>(builder.build());
		}
	})).register(SpontaneousReplace.INSTANCE, "snapshot");
}
