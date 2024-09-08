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

package pers.saikel0rado1iu.spontaneousreplace.terriforest.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import pers.saikel0rado1iu.silk.api.magiccube.entity.PhysicsConnectingBlockEntity;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.EerieBoughBlock;

/**
 * <h2 style="color:FFC800">阴森木枝方块实体</h2>
 * {@link EerieBoughBlock} 的方块实体
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class EerieBoughBlockEntity extends PhysicsConnectingBlockEntity {
	/**
	 * @param pos   方块位置
	 * @param state 方块状态
	 */
	public EerieBoughBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityTypes.EERIE_BOUGH, pos, state);
	}
}
