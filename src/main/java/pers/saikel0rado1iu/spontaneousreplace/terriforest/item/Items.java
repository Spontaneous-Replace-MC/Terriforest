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

package pers.saikel0rado1iu.spontaneousreplace.terriforest.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import pers.saikel0rado1iu.silk.api.spinningjenny.ItemRegistry;
import pers.saikel0rado1iu.spontaneousreplace.item.ItemGroups;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.Blocks;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.eerie.EerieRindBehavior;

/**
 * <h2 style="color:FFC800">物品集</h2>
 * 毛骨森然的所有物品
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface Items extends ItemRegistry {
	BlockItem EERIE_REGOLITH = ItemRegistry.registrar(() -> new BlockItem(Blocks.EERIE_REGOLITH, new FabricItemSettings())).group(ItemGroups.NATURAL).register("eerie_regolith");
	BlockItem TREACHEROUS_SLUDGE = ItemRegistry.registrar(() -> new BlockItem(Blocks.TREACHEROUS_SLUDGE, new FabricItemSettings())).group(ItemGroups.NATURAL).register("treacherous_sludge");
	BlockItem EERIE_RIND = ItemRegistry.registrar(() -> new BlockItem(Blocks.EERIE_RIND, new FabricItemSettings().fireproof())).other(blockItem -> {
		EerieRindBehavior.INSTANCE.registerBehavior();
		Item.BLOCK_ITEMS.put(Blocks.ICE_EERIE_RIND, blockItem);
		Item.BLOCK_ITEMS.put(Blocks.LAVA_EERIE_RIND, blockItem);
		Item.BLOCK_ITEMS.put(Blocks.WATER_EERIE_RIND, blockItem);
		Item.BLOCK_ITEMS.put(Blocks.POWDER_SNOW_EERIE_RIND, blockItem);
	}).group(ItemGroups.NATURAL).register("eerie_rind");
	BlockItem TREACHEROUS_SAC = ItemRegistry.registrar(() -> new BlockItem(Blocks.TREACHEROUS_SAC, new FabricItemSettings())).group(ItemGroups.NATURAL).register("treacherous_sac");
	BlockItem EERIE_BOUGH = ItemRegistry.registrar(() -> new BlockItem(Blocks.EERIE_BOUGH, new FabricItemSettings().fireproof())).group(ItemGroups.NATURAL).register("eerie_bough");
	BlockItem TREACHEROUS_VINES = ItemRegistry.registrar(() -> new BlockItem(Blocks.TREACHEROUS_VINES, new FabricItemSettings())).group(ItemGroups.NATURAL).register("treacherous_vines");
}
