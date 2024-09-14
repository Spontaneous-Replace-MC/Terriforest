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

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.source.*;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionTypes;
import net.minecraft.world.gen.WorldPreset;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import pers.saikel0rado1iu.silk.api.event.modplus.ModifyChunkGeneratorInstanceEvents;
import pers.saikel0rado1iu.silk.api.generate.world.WorldPresetEntry;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.biome.source.BiomeSourceParamLists;

import java.util.List;

/**
 * <h2 style="color:FFC800">快照区块生成器</h2>
 * 自然更替中的经典区块生成器的快照版本
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class SnapshotChunkGenerator extends ClassicChunkGenerator {
	private static final String VERSION = DEFAULT_VERSION;
	
	public SnapshotChunkGenerator(BiomeSource biomeSource, List<FixedBiomeSource> additionalBiomeSources, RegistryEntry<ChunkGeneratorSettings> settings, String version) {
		super(biomeSource, additionalBiomeSources, settings, version);
	}
	
	public static SnapshotChunkGenerator getInstance(DynamicRegistryManager registryManager) {
		RegistryEntry<MultiNoiseBiomeSourceParameterList> parameters = registryManager.get(RegistryKeys.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST)
				.getEntry(MultiNoiseBiomeSourceParameterLists.OVERWORLD).orElseThrow();
		RegistryEntry<ChunkGeneratorSettings> settings = registryManager.get(RegistryKeys.CHUNK_GENERATOR_SETTINGS).getEntry(ChunkGeneratorSettings.OVERWORLD).orElseThrow();
		return ModifyChunkGeneratorInstanceEvents.MODIFY_DATA_GEN_INSTANCE.invoker().getInstance(new SnapshotChunkGenerator(MultiNoiseBiomeSource.create(parameters), ImmutableList.of(), settings, VERSION), registryManager);
	}
	
	private static SnapshotChunkGenerator getInstance(Registerable<WorldPreset> registerable, WorldPresetEntry.Registrar registrar) {
		RegistryEntry<MultiNoiseBiomeSourceParameterList> parameters = registrar.multiNoisePresetLookup.getOrThrow(BiomeSourceParamLists.SNAPSHOT);
		RegistryEntry<ChunkGeneratorSettings> settings = registrar.chunkGeneratorSettingsLookup.getOrThrow(ChunkGeneratorSetting.SNAPSHOT);
		return ModifyChunkGeneratorInstanceEvents.MODIFY_REGISTER_INSTANCE.invoker().getInstance(new SnapshotChunkGenerator(MultiNoiseBiomeSource.create(parameters), ImmutableList.of(), settings, VERSION), registerable, registrar);
	}
	
	public static void register(RegistryKey<WorldPreset> worldPreset, Registerable<WorldPreset> registerable) {
		WorldPresetEntry.Registrar registrar = new WorldPresetEntry.Registrar(registerable);
		registrar.register(worldPreset, new DimensionOptions(registerable.getRegistryLookup(RegistryKeys.DIMENSION_TYPE).getOrThrow(DimensionTypes.OVERWORLD), getInstance(registerable, registrar)));
	}
	
	@Override
	public Codec<? extends ChunkGenerator> getCodec() {
		return ChunkGeneratorCodecs.SNAPSHOT;
	}
}
