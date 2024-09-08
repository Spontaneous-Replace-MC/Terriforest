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

package pers.saikel0rado1iu.spontaneousreplace.terriforest.block.eerie;

import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.tag.TagKey;
import pers.saikel0rado1iu.silk.api.magiccube.cauldron.CauldronLikeBehavior;
import pers.saikel0rado1iu.silk.api.magiccube.cauldron.LeveledCauldronLikeBlock;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.Blocks;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.registry.tag.BlockTags;

/**
 * <h2 style="color:FFC800">阴森木壳行为</h2>
 * 用于实现阴森木壳的坩埚行为
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class EerieRindBehavior extends CauldronLikeBehavior {
	public static final EerieRindBehavior INSTANCE = new EerieRindBehavior();
	
	/**
	 * 水平面坩埚类方块
	 *
	 * @return 水平面坩埚类方块
	 */
	@Override
	public LeveledCauldronLikeBlock leveledCauldronLikeBlock() {
		return Blocks.WATER_EERIE_RIND;
	}
	
	/**
	 * 空坩埚类方块
	 *
	 * @return 空坩埚类方块
	 */
	@Override
	public AbstractCauldronBlock emptyCauldronLikeBlock() {
		return Blocks.EERIE_RIND;
	}
	
	/**
	 * 含水坩埚类方块
	 *
	 * @return 含水坩埚类方块
	 */
	@Override
	public AbstractCauldronBlock waterCauldronLikeBlock() {
		return Blocks.WATER_EERIE_RIND;
	}
	
	/**
	 * 含熔岩坩埚类方块
	 *
	 * @return 含熔岩坩埚类方块
	 */
	@Override
	public AbstractCauldronBlock lavaCauldronLikeBlock() {
		return Blocks.LAVA_EERIE_RIND;
	}
	
	/**
	 * 含细雪坩埚类方块
	 *
	 * @return 含细雪坩埚类方块
	 */
	@Override
	public AbstractCauldronBlock powderSnowCauldronLikeBlock() {
		return Blocks.POWDER_SNOW_EERIE_RIND;
	}
	
	/**
	 * 同种坩埚方块标签，在标签内的方块被认为是同一种坩埚类方块的不同变体
	 *
	 * @return 方块标签
	 */
	@Override
	public TagKey<Block> sameCauldronBlockTag() {
		return BlockTags.EERIE_RINDS;
	}
}
