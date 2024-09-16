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

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.ConnectingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import pers.saikel0rado1iu.silk.api.magiccube.PhysicsConnectingBlock;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.entity.EerieBoughBlockEntity;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.registry.tag.BlockTags;

/**
 * <h2 style="color:FFC800">阴森木枝方块</h2>
 * 一种可连接方块
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class EerieBoughBlock extends PhysicsConnectingBlock {
	public static final MapCodec<EerieBoughBlock> CODEC = createCodec(EerieBoughBlock::new);
	
	protected EerieBoughBlock(Settings settings) {
		super(0.25F, settings);
	}
	
	/**
	 * 是否可放置
	 *
	 * @param state 检测方块状态
	 * @return 如果为 {@code true} 则此连接块可以放置在这些完整方块上
	 */
	@Override
	public boolean isPlaceable(BlockState state) {
		return true;
	}
	
	/**
	 * 是否可连接
	 *
	 * @param state 检测方块状态
	 * @return 如果为 {@code true} 此方块会将这些方块识别为可连接的一部分
	 */
	@Override
	public boolean isConnectable(BlockState state) {
		return state.isIn(BlockTags.EERIE_RINDS) || state.isOf(this);
	}
	
	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new EerieBoughBlockEntity(pos, state);
	}
	
	@Override
	protected MapCodec<? extends ConnectingBlock> getCodec() {
		return CODEC;
	}
}
