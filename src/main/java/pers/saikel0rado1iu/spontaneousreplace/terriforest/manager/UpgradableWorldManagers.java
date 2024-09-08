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

package pers.saikel0rado1iu.spontaneousreplace.terriforest.manager;

import net.minecraft.world.dimension.DimensionOptions;
import pers.saikel0rado1iu.silk.api.landform.UpgradableWorldData;
import pers.saikel0rado1iu.silk.api.landform.UpgradableWorldManagerRegistry;
import pers.saikel0rado1iu.spontaneousreplace.Settings;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.chunk.ClassicChunkGenerator;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.chunk.SnapshotChunkGenerator;

/**
 * <h2 style="color:FFC800">可升级世界管理器集</h2>
 * 毛骨森然的可升级世界管理器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface UpgradableWorldManagers extends UpgradableWorldManagerRegistry {
	UpgradableWorldData<ClassicChunkGenerator> CLASSIC_CHUNK_GENERATOR_DATA = UpgradableWorldData.builder(SpontaneousReplace.INSTANCE, Settings.SETTINGS, DimensionOptions.OVERWORLD, ClassicChunkGenerator::getInstance).build();
	UpgradableWorldData<SnapshotChunkGenerator> SNAPSHOT_CHUNK_GENERATOR_DATA = UpgradableWorldData.builder(SpontaneousReplace.INSTANCE, Settings.SETTINGS, DimensionOptions.OVERWORLD, SnapshotChunkGenerator::getInstance).build();
}
