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
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.*;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionTypes;
import net.minecraft.world.gen.WorldPreset;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import pers.saikel0rado1iu.silk.api.event.modplus.ModifyChunkGeneratorCustomEvents;
import pers.saikel0rado1iu.silk.api.event.modplus.ModifyChunkGeneratorInstanceEvents;
import pers.saikel0rado1iu.silk.api.event.modplus.ModifyChunkGeneratorUpgradableEvents;
import pers.saikel0rado1iu.silk.api.generate.world.WorldPresetEntry;
import pers.saikel0rado1iu.silk.api.landform.ChunkStorageData;
import pers.saikel0rado1iu.silk.api.landform.gen.chunk.ModifiedChunkGenerator;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.biome.source.BiomeSourceParamLists;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * <h2 style="color:FFC800">经典区块生成器</h2>
 * 自然更替中的具有原版风格的特殊区块生成器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class ClassicChunkGenerator extends ModifiedChunkGenerator {
	public static final String VERSION_DELIMITER = ":";
	public static final String DEFAULT_VERSION = "00";
	private static final String VERSION = DEFAULT_VERSION;
	
	protected ClassicChunkGenerator(BiomeSource biomeSource, List<FixedBiomeSource> additionalBiomeSources, RegistryEntry<ChunkGeneratorSettings> settings, String version) {
		super(biomeSource, additionalBiomeSources, settings, version);
	}
	
	public static ClassicChunkGenerator getInstance(DynamicRegistryManager registryManager) {
		RegistryEntry<MultiNoiseBiomeSourceParameterList> parameters = registryManager.get(RegistryKeys.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST)
				.getEntry(MultiNoiseBiomeSourceParameterLists.OVERWORLD).orElseThrow();
		RegistryEntry<ChunkGeneratorSettings> settings = registryManager.get(RegistryKeys.CHUNK_GENERATOR_SETTINGS).getEntry(ChunkGeneratorSettings.OVERWORLD).orElseThrow();
		return ModifyChunkGeneratorInstanceEvents.MODIFY_DATA_GEN_INSTANCE.invoker().getInstance(new ClassicChunkGenerator(MultiNoiseBiomeSource.create(parameters), ImmutableList.of(), settings, VERSION), registryManager);
	}
	
	private static ClassicChunkGenerator getInstance(Registerable<WorldPreset> registerable, WorldPresetEntry.Registrar registrar) {
		RegistryEntry<MultiNoiseBiomeSourceParameterList> parameters = registrar.multiNoisePresetLookup.getOrThrow(BiomeSourceParamLists.CLASSIC);
		RegistryEntry<ChunkGeneratorSettings> settings = registrar.chunkGeneratorSettingsLookup.getOrThrow(ChunkGeneratorSetting.CLASSIC);
		return ModifyChunkGeneratorInstanceEvents.MODIFY_REGISTER_INSTANCE.invoker().getInstance(new ClassicChunkGenerator(MultiNoiseBiomeSource.create(parameters), ImmutableList.of(), settings, VERSION), registerable, registrar);
	}
	
	public static void register(RegistryKey<WorldPreset> worldPreset, Registerable<WorldPreset> registerable) {
		WorldPresetEntry.Registrar registrar = new WorldPresetEntry.Registrar(registerable);
		registrar.register(worldPreset, new DimensionOptions(registerable.getRegistryLookup(RegistryKeys.DIMENSION_TYPE).getOrThrow(DimensionTypes.OVERWORLD), getInstance(registerable, registrar)));
	}
	
	@Override
	public Optional<Pair<BlockPos, RegistryEntry<Biome>>> locateBiome(BlockPos origin, int radius, int horizontalBlockCheckInterval, int verticalBlockCheckInterval, Predicate<RegistryEntry<Biome>> predicate, MultiNoiseUtil.MultiNoiseSampler noiseSampler, ServerWorld world) {
		Optional<Pair<BlockPos, RegistryEntry<Biome>>> pair = super.locateBiome(origin, radius, horizontalBlockCheckInterval, verticalBlockCheckInterval, predicate, noiseSampler, world);
		Map.Entry<ActionResult, Optional<Pair<BlockPos, RegistryEntry<Biome>>>> result = ModifyChunkGeneratorCustomEvents.MODIFY_LOCATE_BIOME.invoker().locateBiome(this, pair, origin, radius, horizontalBlockCheckInterval, verticalBlockCheckInterval, predicate, noiseSampler, world);
		if (ActionResult.FAIL == result.getKey()) return pair;
		return result.getValue();
	}
	
	@Override
	public String version() {
		String version = super.version();
		Map.Entry<ActionResult, String> result = ModifyChunkGeneratorUpgradableEvents.MODIFY_VERSION.invoker().version(this, version);
		if (ActionResult.FAIL == result.getKey()) return version;
		return result.getValue();
	}
	
	/**
	 * 用于判断生成器版本号的方法
	 *
	 * @param savedVersion 已保存在存档中的版本号
	 * @return -1 为小于；0 为等于；1 为大于
	 */
	@Override
	public int compare(String savedVersion) {
		String[] savedVersions = savedVersion.split(VERSION_DELIMITER);
		String[] versions = version().split(VERSION_DELIMITER);
		int compare = 0;
		if (savedVersions.length < versions.length) {
			compare = -1;
		} else if (savedVersions.length > versions.length) {
			compare = 1;
		} else {
			boolean isGreater = false;
			boolean isLess = false;
			for (int count = 0; count < versions.length; count++) {
				if (Integer.parseInt(savedVersions[count], 16) > Integer.parseInt(versions[count], 16)) isGreater = true;
				else if (Integer.parseInt(savedVersions[count], 16) < Integer.parseInt(versions[count], 16)) isLess = true;
			}
			if (isGreater) compare = 1;
			else if (isLess) compare = -1;
		}
		Map.Entry<ActionResult, Integer> result = ModifyChunkGeneratorUpgradableEvents.MODIFY_COMPARE.invoker().compare(this, compare, savedVersion);
		if (ActionResult.FAIL == result.getKey()) return compare;
		return result.getValue();
	}
	
	/**
	 * 通过块坐标解析 {@link ChunkStorageData} 的数据进行判断是否可刷新坐标中的区块
	 *
	 * @param pos     需判断块坐标
	 * @param chunk   区块数据
	 * @param version 区块当前的生成器版本
	 * @return 是否需要刷新区块
	 */
	@Override
	public boolean needRefresh(BlockPos pos, ChunkStorageData chunk, String version) {
		boolean needRefresh = false;
		Map.Entry<ActionResult, Boolean> result = ModifyChunkGeneratorUpgradableEvents.MODIFY_NEED_REFRESH.invoker().needRefresh(this, needRefresh, pos, chunk, version);
		if (ActionResult.FAIL == result.getKey()) return needRefresh;
		return result.getValue();
	}
	
	/**
	 * 通过块坐标解析 {@link ChunkStorageData} 的数据进行判断是否可升级坐标中的区块
	 *
	 * @param pos     需判断块坐标
	 * @param chunk   区块数据
	 * @param version 区块当前的生成器版本
	 * @return 是否需要升级区块
	 */
	@Override
	public boolean needUpgrade(BlockPos pos, ChunkStorageData chunk, String version) {
		boolean needUpgrade = false;
		Map.Entry<ActionResult, Boolean> result = ModifyChunkGeneratorUpgradableEvents.MODIFY_NEED_UPGRADE.invoker().needUpgrade(this, needUpgrade, pos, chunk, version);
		if (ActionResult.FAIL == result.getKey()) return needUpgrade;
		return result.getValue();
	}
	
	/**
	 * 通过块坐标解析 {@link ChunkStorageData} 的数据进行判断是否可降级坐标中的区块
	 *
	 * @param pos     需判断块坐标
	 * @param chunk   区块数据
	 * @param version 区块当前的生成器版本
	 * @return 是需要降级区块
	 */
	@Override
	public boolean needDowngrade(BlockPos pos, ChunkStorageData chunk, String version) {
		boolean needDowngrade = false;
		Map.Entry<ActionResult, Boolean> result = ModifyChunkGeneratorUpgradableEvents.MODIFY_NEED_DOWNGRADE.invoker().needDowngrade(this, needDowngrade, pos, chunk, version);
		if (ActionResult.FAIL == result.getKey()) return needDowngrade;
		return result.getValue();
	}
	
	/**
	 * 通过在世界中的块坐标判断生物群系，用于设置以特殊方法而非原版方法生成生物群系
	 *
	 * @param pos 块坐标
	 * @return 在坐标上应生成的生物群系源
	 */
	@Override
	public BiomeSource getBiomeSource(BlockPos pos) {
		BiomeSource source = biomeSource;
		Map.Entry<ActionResult, BiomeSource> result = ModifyChunkGeneratorCustomEvents.MODIFY_GET_BIOME_SOURCE.invoker().getBiomeSource(this, source, pos);
		if (ActionResult.FAIL == result.getKey()) return source;
		return result.getValue();
	}
	
	/**
	 * 修改地形噪声的一种方法，用于设置以特殊方法而非原版方法生成地形噪声
	 *
	 * @param pos                   块坐标
	 * @param originBlock           原始块
	 * @param estimateSurfaceHeight 预期表面高度
	 * @return 应设置的噪声块
	 */
	@Override
	public Optional<BlockState> getTerrainNoise(BlockPos pos, Optional<BlockState> originBlock, int estimateSurfaceHeight) {
		Map.Entry<ActionResult, Optional<BlockState>> result = ModifyChunkGeneratorCustomEvents.MODIFY_GET_TERRAIN_NOISE.invoker().getTerrainNoise(this, originBlock, pos, originBlock, estimateSurfaceHeight);
		if (ActionResult.FAIL == result.getKey()) return originBlock;
		return result.getValue();
	}
	
	@Override
	public MapCodec<? extends ChunkGenerator> getCodec() {
		MapCodec<? extends ChunkGenerator> codec = ChunkGeneratorCodecs.CLASSIC;
		Map.Entry<ActionResult, MapCodec<? extends ChunkGenerator>> result = ModifyChunkGeneratorUpgradableEvents.MODIFY_GET_CODEC.invoker().getCodec(this, codec);
		if (ActionResult.FAIL == result.getKey()) return codec;
		return result.getValue();
	}
}
