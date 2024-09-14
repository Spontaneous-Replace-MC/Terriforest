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

package pers.saikel0rado1iu.spontaneousreplace.terriforest;

import com.google.common.collect.ImmutableSet;
import pers.saikel0rado1iu.silk.api.modpass.ModData;
import pers.saikel0rado1iu.silk.api.modpass.ModMain;
import pers.saikel0rado1iu.silk.api.modpass.ModPass;
import pers.saikel0rado1iu.silk.api.modpass.registry.MainRegistrationProvider;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.Blocks;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.entity.BlockEntityTypes;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.entity.EntityTypes;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.entity.effect.StatusEffects;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.item.Items;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.manager.UpdateManagers;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.sound.SoundEvents;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.chunk.ChunkGeneratorCodecs;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.foliage.FoliagePlacerTypes;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.treedecorator.TreeDecoratorTypes;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.trunk.TrunkPlacerTypes;

import java.util.Set;

/**
 * <h2 style="color:FFC800">主类</h2>
 * 毛骨森然的主类
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public final class Main implements ModMain {
	/**
	 * 模组主函数
	 *
	 * @param mod 提供的模组通
	 */
	@Override
	public void main(ModPass mod) {
	}
	
	/**
	 * 注册表方法，提供注册表以供注册
	 *
	 * @return 注册表的类型集合
	 */
	@Override
	public Set<Class<? extends MainRegistrationProvider<?>>> registry() {
		return ImmutableSet.of(
				Items.class,
				Blocks.class,
				BlockEntityTypes.class,
				EntityTypes.class,
				SoundEvents.class,
				StatusEffects.class,
				FoliagePlacerTypes.class,
				TreeDecoratorTypes.class,
				TrunkPlacerTypes.class,
				ChunkGeneratorCodecs.class,
				UpdateManagers.class);
	}
	
	/**
	 * 用于提供模组数据以基于模组数据实现功能
	 *
	 * @return 模组数据
	 */
	@Override
	public ModData modData() {
		return Terriforest.INSTANCE;
	}
	
	@Override
	public ModPass registrationNamespace() {
		return SpontaneousReplace.INSTANCE;
	}
}
