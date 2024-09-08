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

package pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.foliage;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.Blocks;

import java.util.List;
import java.util.function.BiFunction;

/**
 * <h2 style="color:FFC800">阴森树叶放置器</h2>
 * 阴森木地物的树叶放置器，用于生成阴森木凌乱的枝条
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class EerieFoliagePlacer extends BlobFoliagePlacer {
	public static final Codec<EerieFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
			fillFoliagePlacerFields(instance)
					.and(Codec.intRange(0, 16).fieldOf("height").forGetter(placer -> placer.height))
					.apply(instance, EerieFoliagePlacer::new));
	
	public EerieFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
		super(radius, offset, height);
	}
	
	private static List<BlockPos> generateEerieBoughList(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig ignoredConfig, List<BlockPos> posList) {
		final float upProbability = 4;
		final float sizeProbability = 3;
		final float downProbability = 1.5F;
		ImmutableList<BlockPos> blockPosList = ImmutableList.copyOf(posList);
		List<BlockPos> genPosList = Lists.newArrayList();
		BiFunction<BlockState, BlockPos, Boolean> testBlockState = (state, pos) -> !state.isOf(Blocks.EERIE_RIND) && !genPosList.contains(pos) && Blocks.EERIE_BOUGH.canPlaceAt(state, (WorldView) world, pos);
		for (BlockPos pos : blockPosList) {
			for (Direction direction : Direction.values()) {
				BlockPos offsetPos = switch (direction) {
					case UP -> random.nextInt(10) < upProbability ? pos.offset(direction) : pos;
					case DOWN -> random.nextInt(10) < downProbability ? pos.offset(direction) : pos;
					case NORTH, EAST, WEST, SOUTH -> random.nextInt(10) < sizeProbability ? pos.offset(direction) : pos;
				};
				if (!world.testBlockState(offsetPos, state -> testBlockState.apply(state, offsetPos))) continue;
				Blocks.EERIE_BOUGH.placeBlock((BlockView) world, offsetPos, placer::placeBlock);
				genPosList.add(offsetPos);
			}
		}
		return genPosList;
	}
	
	@Override
	protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
		List<BlockPos> posList = Lists.newArrayListWithExpectedSize(4);
		BlockPos.Mutable mutable = new BlockPos.Mutable().set(treeNode.getCenter(), 0, offset - foliageHeight, 0);
		for (Direction direction : Direction.Type.HORIZONTAL) {
			if (random.nextInt(6) == 0) continue;
			if (random.nextInt(4) == 0) posList.add(mutable.offset(direction).up());
			else if (random.nextInt(4) == 0) posList.add(mutable.offset(direction).down());
			else posList.add(mutable.offset(direction));
		}
		posList.forEach(pos -> Blocks.EERIE_BOUGH.placeBlock((BlockView) world, pos, placer::placeBlock));
		for (int count = 0; count < radius * 2; count++) posList = generateEerieBoughList(world, placer, random, config, posList);
	}
	
	@Override
	protected FoliagePlacerType<?> getType() {
		return FoliagePlacerTypes.EERIE_TREE_FOLIAGE_PLACER;
	}
}