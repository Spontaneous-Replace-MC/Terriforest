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

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import pers.saikel0rado1iu.silk.api.spinningjenny.BlockRegistry;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.treacherous.TreacherousData;

import static net.minecraft.block.Blocks.*;

/**
 * <h2 style="color:FFC800">方块集</h2>
 * 毛骨森然的所有方块
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface Blocks extends BlockRegistry {
	EerieRegolithBlock EERIE_REGOLITH = BlockRegistry.registrar(() -> new EerieRegolithBlock(FabricBlockSettings.copyOf(PODZOL).mapColor(MapColor.BLACK))).register("eerie_regolith");
	TreacherousSludgeBlock TREACHEROUS_SLUDGE = BlockRegistry.registrar(() -> new TreacherousSludgeBlock(FabricBlockSettings.copyOf(MUD).mapColor(MapColor.TERRACOTTA_ORANGE))).register("treacherous_sludge");
	EerieRindBlock EERIE_RIND = BlockRegistry.registrar(() -> new EerieRindBlock(FabricBlockSettings.copyOf(CAULDRON).nonOpaque().sounds(BlockSoundGroup.COPPER).mapColor(MapColor.BLACK))).register("eerie_rind");
	IceEerieRindBlock ICE_EERIE_RIND = BlockRegistry.registrar(() -> new IceEerieRindBlock(FabricBlockSettings.copyOf(EERIE_RIND).slipperiness(ICE.getSlipperiness()).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.LIGHT_BLUE))).register("ice_eerie_rind");
	LavaEerieRindBlock LAVA_EERIE_RIND = BlockRegistry.registrar(() -> new LavaEerieRindBlock(FabricBlockSettings.copyOf(LAVA_CAULDRON).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.BRIGHT_RED))).register("lava_eerie_rind");
	WaterEerieRindBlock WATER_EERIE_RIND = BlockRegistry.registrar(() -> new WaterEerieRindBlock(FabricBlockSettings.copyOf(WATER_CAULDRON).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.WATER_BLUE))).register("water_eerie_rind");
	PowderSnowEerieRindBlock POWDER_SNOW_EERIE_RIND = BlockRegistry.registrar(() -> new PowderSnowEerieRindBlock(FabricBlockSettings.copyOf(POWDER_SNOW_CAULDRON).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.WHITE))).register("powder_snow_eerie_rind");
	TreacherousSacBlock TREACHEROUS_SAC = BlockRegistry.registrar(() -> new TreacherousSacBlock(FabricBlockSettings.copyOf(DARK_OAK_LOG).strength(DARK_OAK_LOG.getHardness() / 2, 0).luminance(TreacherousData.LUMINANCE).mapColor(MapColor.BROWN))).register("treacherous_sac");
	EerieBoughBlock EERIE_BOUGH = BlockRegistry.registrar(() -> new EerieBoughBlock(FabricBlockSettings.copyOf(DIAMOND_BLOCK).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.LIGHT_GRAY))).register("eerie_bough");
	TreacherousVinesBlock TREACHEROUS_VINES = BlockRegistry.registrar(() -> new TreacherousVinesBlock(FabricBlockSettings.copyOf(WEEPING_VINES).luminance(TreacherousData.LUMINANCE).mapColor(MapColor.ORANGE))).register("treacherous_vines");
	TreacherousVinesPlantBlock TREACHEROUS_VINES_PLANT = BlockRegistry.registrar(() -> new TreacherousVinesPlantBlock(FabricBlockSettings.copyOf(WEEPING_VINES_PLANT).luminance(TreacherousData.LUMINANCE).mapColor(MapColor.ORANGE))).register("treacherous_vines_plant");
}
