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
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import pers.saikel0rado1iu.silk.api.generate.data.client.ExtendedBlockStateModelGenerator;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.Blocks;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.data.client.Models;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.item.Items;

import java.util.Optional;

import static net.minecraft.block.Blocks.*;

/**
 * <h2 style="color:FFC800">模型生成器</h2>
 * 毛骨森然的模型生成器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
final class ModelGenerator extends FabricModelProvider {
	ModelGenerator(FabricDataOutput output) {
		super(output);
	}
	
	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		ExtendedBlockStateModelGenerator generator = new ExtendedBlockStateModelGenerator(blockStateModelGenerator);
		generator.registerSingleton(Blocks.EERIE_REGOLITH, TexturedModel.CUBE_BOTTOM_TOP);
		generator.registerSimpleCubeAll(Blocks.TREACHEROUS_SLUDGE);
		generator.registerFullCauldron(TextureMap.getSubId(Blocks.EERIE_RIND, "_content"), Blocks.EERIE_RIND, Blocks.EERIE_RIND, Models.TEMPLATE_EERIE_RIND_FULL);
		new Model(Optional.of(ModelIds.getBlockModelId(Blocks.EERIE_RIND)), Optional.empty()).upload(ModelIds.getItemModelId(Items.EERIE_RIND), new TextureMap(), generator.modelCollector);
		generator.registerFullCauldron(TextureMap.getSubId(LAVA, "_still"), Blocks.EERIE_RIND, Blocks.LAVA_EERIE_RIND, Models.TEMPLATE_EERIE_RIND_FULL);
		generator.registerLeveledCauldron(TextureMap.getSubId(WATER, "_still"), Blocks.EERIE_RIND, Blocks.WATER_EERIE_RIND, Models.TEMPLATE_EERIE_RIND_LEVEL1, Models.TEMPLATE_EERIE_RIND_LEVEL2, Models.TEMPLATE_EERIE_RIND_FULL);
		generator.registerLeveledCauldron(TextureMap.getId(POWDER_SNOW), Blocks.EERIE_RIND, Blocks.POWDER_SNOW_EERIE_RIND, Models.TEMPLATE_EERIE_RIND_LEVEL1, Models.TEMPLATE_EERIE_RIND_LEVEL2, Models.TEMPLATE_EERIE_RIND_FULL);
		generator.registerCubeColumn(Blocks.ICE_EERIE_RIND, TextureMap.getSubId(Blocks.EERIE_RIND, "_side"), TextureMap.getId(Blocks.ICE_EERIE_RIND));
		generator.excludeFromSimpleItemModelGeneration(Blocks.ICE_EERIE_RIND);
		generator.registerSimpleCubeAll(Blocks.TREACHEROUS_SAC);
		generator.registerVines(Blocks.TREACHEROUS_VINES, Blocks.TREACHEROUS_VINES_PLANT, BlockStateModelGenerator.TintType.NOT_TINTED);
		generator.registerConnectingBlock(Blocks.EERIE_BOUGH);
	}
	
	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
	}
}
