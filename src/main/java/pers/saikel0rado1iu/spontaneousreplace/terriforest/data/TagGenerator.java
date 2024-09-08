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

package pers.saikel0rado1iu.spontaneousreplace.terriforest.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.data.DataOutput;
import net.minecraft.data.server.tag.TagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.WorldPresetTags;
import net.minecraft.world.gen.WorldPreset;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.Blocks;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.registry.tag.BlockTags;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.WorldPresets;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.registry.tag.BlockTags.*;
import static pers.saikel0rado1iu.silk.api.spinningjenny.tag.BlockTags.SAPLING_GROW_BLOCK;

/**
 * <h2 style="color:FFC800">标签生成器</h2>
 * 毛骨森然的标签生成器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
interface TagGenerator {
	final class Block extends FabricTagProvider.BlockTagProvider {
		Block(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
			super(output, completableFuture);
		}
		
		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			getOrCreateTagBuilder(BlockTags.EERIE_RINDS).add(Blocks.EERIE_RIND, Blocks.ICE_EERIE_RIND, Blocks.LAVA_EERIE_RIND, Blocks.WATER_EERIE_RIND, Blocks.POWDER_SNOW_EERIE_RIND);
			getOrCreateTagBuilder(SAPLING_GROW_BLOCK).add(Blocks.EERIE_REGOLITH, Blocks.TREACHEROUS_SLUDGE);
			getOrCreateTagBuilder(CAULDRONS).add(Blocks.EERIE_RIND, Blocks.LAVA_EERIE_RIND, Blocks.POWDER_SNOW_EERIE_RIND, Blocks.WATER_EERIE_RIND);
			getOrCreateTagBuilder(PICKAXE_MINEABLE).add(Blocks.EERIE_RIND, Blocks.ICE_EERIE_RIND, Blocks.LAVA_EERIE_RIND, Blocks.WATER_EERIE_RIND, Blocks.POWDER_SNOW_EERIE_RIND, Blocks.EERIE_BOUGH);
			getOrCreateTagBuilder(NEEDS_IRON_TOOL).add(Blocks.EERIE_RIND, Blocks.ICE_EERIE_RIND, Blocks.LAVA_EERIE_RIND, Blocks.WATER_EERIE_RIND, Blocks.POWDER_SNOW_EERIE_RIND, Blocks.EERIE_BOUGH);
			getOrCreateTagBuilder(SHOVEL_MINEABLE).add(Blocks.EERIE_REGOLITH, Blocks.TREACHEROUS_SLUDGE);
			getOrCreateTagBuilder(AXE_MINEABLE).add(Blocks.TREACHEROUS_SAC, Blocks.TREACHEROUS_VINES_PLANT);
			getOrCreateTagBuilder(CLIMBABLE).add(Blocks.TREACHEROUS_VINES);
			getOrCreateTagBuilder(FALL_DAMAGE_RESETTING).add(Blocks.TREACHEROUS_VINES);
			getOrCreateTagBuilder(SWORD_EFFICIENT).add(Blocks.TREACHEROUS_VINES_PLANT);
			getOrCreateTagBuilder(ENDERMAN_HOLDABLE).add(Blocks.EERIE_REGOLITH, Blocks.TREACHEROUS_SLUDGE);
			getOrCreateTagBuilder(OVERWORLD_CARVER_REPLACEABLES).add(Blocks.EERIE_REGOLITH, Blocks.TREACHEROUS_SLUDGE);
			getOrCreateTagBuilder(NETHER_CARVER_REPLACEABLES).add(Blocks.EERIE_REGOLITH, Blocks.TREACHEROUS_SLUDGE);
			getOrCreateTagBuilder(SCULK_REPLACEABLE).add(Blocks.EERIE_REGOLITH, Blocks.TREACHEROUS_SLUDGE);
		}
	}
	
	final class WP extends TagProvider<WorldPreset> {
		WP(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
			super(output, RegistryKeys.WORLD_PRESET, registryLookupFuture);
		}
		
		@Override
		protected void configure(RegistryWrapper.WrapperLookup lookup) {
			getOrCreateTagBuilder(WorldPresetTags.NORMAL).addOptional(WorldPresets.CLASSIC.getValue());
			getOrCreateTagBuilder(WorldPresetTags.EXTENDED).addOptional(WorldPresets.SNAPSHOT.getValue());
		}
	}
	
}
