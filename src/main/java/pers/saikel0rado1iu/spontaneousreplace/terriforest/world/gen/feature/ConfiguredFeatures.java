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

package pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.feature;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import pers.saikel0rado1iu.silk.api.generate.world.ConfiguredFeatureEntry;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.Blocks;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.foliage.EerieFoliagePlacer;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.foliage.TreacherousFoliagePlacer;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.treedecorator.EerieTreeDecorator;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.trunk.TreacherousTrunkPlacer;

import java.util.List;

/**
 * <h2 style="color:FFC800">已配置的地物集</h2>
 * 毛骨森然的所有已配置的地物
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface ConfiguredFeatures extends ConfiguredFeatureEntry {
	ConfiguredFeatures INSTANCE = new ConfiguredFeatures() {
	};
	
	RegistryKey<ConfiguredFeature<?, ?>> EERIE_TREE = ConfiguredFeatureEntry.of(SpontaneousReplace.INSTANCE, "eerie_tree");
	RegistryKey<ConfiguredFeature<?, ?>> TREACHEROUS_TREE = ConfiguredFeatureEntry.of(SpontaneousReplace.INSTANCE, "treacherous_tree");
	
	private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
		registerable.register(key, new ConfiguredFeature<>(feature, config));
	}
	
	@Override
	default RegistryBuilder.BootstrapFunction<ConfiguredFeature<?, ?>> bootstrap() {
		return registerable -> {
			register(registerable, EERIE_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
					BlockStateProvider.of(Blocks.EERIE_RIND), new StraightTrunkPlacer(6, 1, 2),
					BlockStateProvider.of(Blocks.EERIE_BOUGH), new EerieFoliagePlacer(ConstantIntProvider.create(3), UniformIntProvider.create(3, 4), 6),
					new TwoLayersFeatureSize(1, 0, 1))
					.decorators(List.of(new EerieTreeDecorator()))
					.dirtProvider(BlockStateProvider.of(Blocks.EERIE_REGOLITH))
					.ignoreVines().build());
			register(registerable, TREACHEROUS_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
					BlockStateProvider.of(Blocks.TREACHEROUS_SAC), new TreacherousTrunkPlacer(5, 2, 0),
					BlockStateProvider.of(Blocks.TREACHEROUS_VINES_PLANT), new TreacherousFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
					new TwoLayersFeatureSize(1, 0, 1))
					.dirtProvider(BlockStateProvider.of(Blocks.TREACHEROUS_SLUDGE))
					.ignoreVines().build());
		};
	}
}
