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

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import pers.saikel0rado1iu.silk.api.generate.world.PlacedFeatureEntry;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;

import java.util.List;

import static net.minecraft.world.gen.feature.PlacedFeatures.wouldSurvive;

/**
 * <h2 style="color:FFC800">已放置的地物集</h2>
 * 毛骨森然的所有已放置的地物
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface PlacedFeatures extends PlacedFeatureEntry {
	PlacedFeatures INSTANCE = new PlacedFeatures() {
	};
	
	RegistryKey<PlacedFeature> EERIE_TREE = PlacedFeatureEntry.of(SpontaneousReplace.INSTANCE, "eerie_tree");
	RegistryKey<PlacedFeature> TREACHEROUS_TREE = PlacedFeatureEntry.of(SpontaneousReplace.INSTANCE, "treacherous_tree");
	
	private static void register(Registerable<PlacedFeature> featureRegisterable, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
		featureRegisterable.register(key, new PlacedFeature(feature, List.of(modifiers)));
	}
	
	@Override
	default RegistryBuilder.BootstrapFunction<PlacedFeature> bootstrap() {
		return registerable -> {
			register(registerable, EERIE_TREE, registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
					.getOrThrow(ConfiguredFeatures.EERIE_TREE), wouldSurvive(Blocks.OAK_SAPLING));
			register(registerable, TREACHEROUS_TREE, registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
					.getOrThrow(ConfiguredFeatures.TREACHEROUS_TREE), wouldSurvive(Blocks.DARK_OAK_SAPLING));
		};
	}
}
