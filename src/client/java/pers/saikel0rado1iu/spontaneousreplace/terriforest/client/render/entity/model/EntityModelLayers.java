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

package pers.saikel0rado1iu.spontaneousreplace.terriforest.client.render.entity.model;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import pers.saikel0rado1iu.silk.api.client.spinningjenny.EntityModelLayerRegistry;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;

import static net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.registerModelLayer;

/**
 * <h2 style="color:FFC800">实体模型图层集</h2>
 * 毛骨森然的所有实体模型图层
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public abstract class EntityModelLayers implements EntityModelLayerRegistry {
	public static final EntityModelLayer TREACHEROUS_SAC_LAYER = new EntityModelLayer(SpontaneousReplace.INSTANCE.ofId("treacherous_sac"), "main");
	
	static {
		EntityModelLayerRegistry.registrar(() -> registerModelLayer(TREACHEROUS_SAC_LAYER, TreacherousSacModel::getTexturedModelData)).register(TREACHEROUS_SAC_LAYER);
	}
}
