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

package pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.Blocks;

import java.util.Random;

/**
 * <h2 style="color:FFC800">阴森木树木装饰器</h2>
 * 阴森木的树木装饰器，用于生成阴森木的树根
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class EerieTreeDecorator extends TreeDecorator {
	public static final Codec<EerieTreeDecorator> CODEC = Codec.unit(EerieTreeDecorator::new);
	
	public EerieTreeDecorator() {
	}
	
	@Override
	public void generate(Generator generator) {
		BlockPos pos = generator.getLogPositions().get(0).up();
		if (new Random().nextBoolean()) {
			for (Direction side : Direction.Type.HORIZONTAL) {
				BlockPos targetPos = pos.offset(side, 1);
				if (generator.getWorld().testBlockState(targetPos.down(), (state -> state.isSideSolidFullSquare((BlockView) generator.getWorld(), targetPos.down(), Direction.UP)))) {
					Blocks.EERIE_BOUGH.placeBlock((BlockView) generator.getWorld(), targetPos, generator::replace);
				}
			}
		} else {
			for (Direction side : Direction.Type.HORIZONTAL) {
				BlockPos targetPos = pos.offset(side, 1);
				BlockPos finalTargetPos1 = targetPos;
				if (generator.getWorld().testBlockState(targetPos.down(), (state -> state.isSideSolidFullSquare((BlockView) generator.getWorld(), finalTargetPos1.down(), Direction.UP)))) {
					Blocks.EERIE_BOUGH.placeBlock((BlockView) generator.getWorld(), targetPos.up(), generator::replace);
				}
				targetPos = targetPos.offset(side, 1);
				BlockPos finalTargetPos = targetPos;
				if (generator.getWorld().testBlockState(targetPos.down(), (state -> state.isSideSolidFullSquare((BlockView) generator.getWorld(), finalTargetPos1.down(), Direction.UP)))) {
					Blocks.EERIE_BOUGH.placeBlock((BlockView) generator.getWorld(), finalTargetPos1, generator::replace);
				}
				if (generator.getWorld().testBlockState(targetPos.down(), (state -> state.isSideSolidFullSquare((BlockView) generator.getWorld(), finalTargetPos.down(), Direction.UP)))) {
					Blocks.EERIE_BOUGH.placeBlock((BlockView) generator.getWorld(), targetPos, generator::replace);
				}
			}
		}
	}
	
	@Override
	protected TreeDecoratorType<?> getType() {
		return TreeDecoratorTypes.EERIE_TREE_TREE_DECORATOR;
	}
}
