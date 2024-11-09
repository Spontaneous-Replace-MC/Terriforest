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

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import pers.saikel0rado1iu.silk.api.spinningjenny.world.gen.chunk.ChunkGeneratorCodecRegistry;

import java.util.List;

import static pers.saikel0rado1iu.silk.api.landform.gen.chunk.ChunkGeneratorUpgradable.NON_VERSION;
import static pers.saikel0rado1iu.silk.api.landform.gen.chunk.ChunkGeneratorUpgradable.VERSION_KEY;

/**
 * <h2 style="color:FFC800">区块生成器解编码器集</h2>
 * 毛骨森然的所有区块生成器解编码器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface ChunkGeneratorCodecs extends ChunkGeneratorCodecRegistry {
	MapCodec<ClassicChunkGenerator> CLASSIC = ChunkGeneratorCodecRegistry.registrar(ClassicChunkGenerator.class, () ->
					RecordCodecBuilder.mapCodec(instance -> instance.group(
									BiomeSource.CODEC.fieldOf("biome_source").forGetter(ClassicChunkGenerator::getBiomeSource),
									FixedBiomeSource.CODEC.codec().listOf().optionalFieldOf("fixed_biome_sources", List.of()).forGetter(ClassicChunkGenerator::additionalBiomeSources),
									ChunkGeneratorSettings.REGISTRY_CODEC.fieldOf("settings").forGetter(ClassicChunkGenerator::getSettings),
									Codec.STRING.optionalFieldOf(VERSION_KEY, NON_VERSION).forGetter(ClassicChunkGenerator::version))
							.apply(instance, instance.stable(ClassicChunkGenerator::new))))
			.register("classic");
	MapCodec<SnapshotChunkGenerator> SNAPSHOT = ChunkGeneratorCodecRegistry.registrar(SnapshotChunkGenerator.class, () ->
					RecordCodecBuilder.mapCodec(instance -> instance.group(
									BiomeSource.CODEC.fieldOf("biome_source").forGetter(SnapshotChunkGenerator::getBiomeSource),
									FixedBiomeSource.CODEC.codec().listOf().optionalFieldOf("fixed_biome_sources", List.of()).forGetter(SnapshotChunkGenerator::additionalBiomeSources),
									ChunkGeneratorSettings.REGISTRY_CODEC.fieldOf("settings").forGetter(SnapshotChunkGenerator::getSettings),
									Codec.STRING.optionalFieldOf(VERSION_KEY, NON_VERSION).forGetter(SnapshotChunkGenerator::version))
							.apply(instance, instance.stable(SnapshotChunkGenerator::new))))
			.register("snapshot");
}
