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
import pers.saikel0rado1iu.silk.api.codex.OptionTexts;
import pers.saikel0rado1iu.silk.api.generate.data.LinkedLanguageProvider;
import pers.saikel0rado1iu.silk.api.landform.gen.chunk.ChunkGeneratorUpgradable;
import pers.saikel0rado1iu.silk.api.pattern.widget.WidgetTexts;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.Settings;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.Terriforest;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.block.Blocks;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.entity.damage.DamageTypes;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.entity.effect.StatusEffects;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.sound.SoundEvents;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.WorldPresets;

/**
 * <h2 style="color:FFC800">语言生成器</h2>
 * 毛骨森然的全球化语言生成器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
interface LanguageGenerator {
	final class EnUs extends LinkedLanguageProvider {
		EnUs(FabricDataOutput dataOutput) {
			super(dataOutput, "en_us");
		}
		
		@Override
		public void generateTranslations(TranslationBuilder translationBuilder) {
			translationBuilder.add(comment("note"), "Origin Language is Simplified Chinese(zh_cn)");
			translationBuilder.add(i18nName(Terriforest.INSTANCE), "§3'Terriforest' DLC");
			translationBuilder.add(i18nSummary(Terriforest.INSTANCE), "A SR expansion that adds various exotic trees and bizarre biomes.");
			translationBuilder.add(i18nDesc(Terriforest.INSTANCE), """
					§r  What happens when you have some bizarre and wonderful natural landscapes in Minecraft? That's the experience this expansion pack brings you!
					§r  This expansion pack adds multiple bizarre trees and biomes, each tree having its unique effects and mechanisms. Go explore! Use these trees to craft brand new equipment!""");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "homepage"), "Homepage");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "support"), "Support");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "community"), "Discord");
			translationBuilder.add(WidgetTexts.titleKey(SpontaneousReplace.INSTANCE, "changelog"), "Changelog");
			translationBuilder.add(WidgetTexts.titleKey(SpontaneousReplace.INSTANCE, "target"), "1.0.0 > Target");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "target"), "    Continue planning and implementing the feature, biomes, items, and tools.");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "tab.synopsis"), "Synopsis");
			translationBuilder.add(WidgetTexts.titleKey(SpontaneousReplace.INSTANCE, "tab.synopsis.intro"), "§f§lMod introduction:");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "tab.synopsis.intro"), """
					§r  What happens when you have some bizarre and wonderful natural landscapes in Minecraft? That's the experience this expansion pack brings you!
					§r  This expansion pack adds multiple bizarre trees and biomes, each tree having its unique effects and mechanisms. Go explore! Use these trees to craft brand new equipment!""");
			translationBuilder.add(WidgetTexts.titleKey(SpontaneousReplace.INSTANCE, "tab.synopsis.vision"), "§f§lMod Vision:");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "tab.synopsis.vision"), """
					§r  I hope to make a gameplay mod that is based on the core of the vanilla game and does not destroy the vanilla gameplay. It is very difficult to develop on this basis. Whether an item is added, how to design data so as not to destroy the balance of the game, These are all points that developers need to consider.
					§r  If you think the mod is doing a good job, you are welcome to sponsor my project, or translate this mod, thank your very much!""");
			translationBuilder.add(OptionTexts.rootKey(Settings.SETTINGS), "Setting 'Terriforest' DLC...");
			translationBuilder.add(worldPreset(WorldPresets.CLASSIC), "Spontaneous-Replace");
			translationBuilder.add(worldPreset(WorldPresets.SNAPSHOT), "SR Snapshot");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "generator." + ChunkGeneratorUpgradable.NON_VERSION), "Too Old");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "generator.classic"), "Spontaneous-Replace");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "generator.snapshot"), "SR Snapshot");
			translationBuilder.add(Blocks.EERIE_REGOLITH, "Eerie Regolith");
			translationBuilder.add(Blocks.TREACHEROUS_SLUDGE, "Treacherous Sludge");
			translationBuilder.add(Blocks.EERIE_RIND, "Eerie Rind");
			translationBuilder.add(Blocks.TREACHEROUS_SAC, "Treacherous Sac");
			translationBuilder.add(Blocks.EERIE_BOUGH, "Eerie Bough");
			translationBuilder.add(Blocks.TREACHEROUS_VINES, "Treacherous Vines");
			translationBuilder.add(StatusEffects.ACIDIZE, "Acidize");
			translationBuilder.add(soundSub(SoundEvents.TREACHEROUS_SAC_BREAK), "Treacherous sac break");
			translationBuilder.add(deathMessage(DamageTypes.ACIDIZE, ""), "%1$s dissolved");
			translationBuilder.add(deathMessage(DamageTypes.ACIDIZE, "player"), "%1$s dissolved while fighting %2$s");
		}
	}
	
	final class ZhCn extends LinkedLanguageProvider {
		ZhCn(FabricDataOutput dataOutput) {
			super(dataOutput, "zh_cn");
		}
		
		@Override
		public void generateTranslations(TranslationBuilder translationBuilder) {
			translationBuilder.add(comment("note"), "原生语言");
			translationBuilder.add(i18nName(Terriforest.INSTANCE), "§3「毛骨森然」拓展包");
			translationBuilder.add(i18nSummary(Terriforest.INSTANCE), "添加了多种奇异树木与怪异的生物群系的「自然更替」拓展");
			translationBuilder.add(i18nDesc(Terriforest.INSTANCE), """
					§r　　当·Minecraft·中拥有一些怪异的奇妙自然景观时会发生什么呢？这就是本拓展包带给你的体验！
					§r　　本拓展包添加了多个怪异的树木与生物群系，每个树木都有自己独特的效果与机制，去探索吧！利用这些树木制作全新的装备吧！""");
			translationBuilder.add(WidgetTexts.textKey(Terriforest.INSTANCE, "homepage"), "模组官网");
			translationBuilder.add(WidgetTexts.textKey(Terriforest.INSTANCE, "support"), "支持我们");
			translationBuilder.add(WidgetTexts.textKey(Terriforest.INSTANCE, "community"), "官方社群");
			translationBuilder.add(WidgetTexts.titleKey(Terriforest.INSTANCE, "changelog"), "更新日志");
			translationBuilder.add(WidgetTexts.titleKey(Terriforest.INSTANCE, "target"), "1.0.0 > 目标");
			translationBuilder.add(WidgetTexts.textKey(Terriforest.INSTANCE, "target"), "　　继续计划实现但未实现的地物、生物群系、物品、道具。");
			translationBuilder.add(WidgetTexts.textKey(Terriforest.INSTANCE, "tab.synopsis"), "简介");
			translationBuilder.add(WidgetTexts.titleKey(Terriforest.INSTANCE, "tab.synopsis.intro"), "§f§l模组简介：");
			translationBuilder.add(WidgetTexts.textKey(Terriforest.INSTANCE, "tab.synopsis.intro"), """
					§r　　当·Minecraft·中拥有一些怪异的奇妙自然景观时会发生什么呢？这就是本拓展包带给你的体验！
					§r　　本拓展包添加了多个怪异的树木与生物群系，每个树木都有自己独特的效果与机制，去探索吧！利用这些树木制作全新的装备吧！""");
			translationBuilder.add(WidgetTexts.titleKey(Terriforest.INSTANCE, "tab.synopsis.vision"), "§f§l模组愿景：");
			translationBuilder.add(WidgetTexts.textKey(Terriforest.INSTANCE, "tab.synopsis.vision"), """
					§r　　我希望做一个基于原版游戏内核，不破坏原版游戏玩法的玩法类模组。在这基础上进行开发十分困难，一件物品是否加入，怎样设计数据才不会破坏游戏平衡性，这些都是开发者需要考虑的点。
					§r　　如果你觉得模组做的不错，欢迎对我的项目进行赞助，或者对此模组进行翻译，十分感谢你们!""");
			translationBuilder.add(OptionTexts.rootKey(Settings.SETTINGS), "「毛骨森然」拓展包设置…");
			translationBuilder.add(worldPreset(WorldPresets.CLASSIC), "自然更替");
			translationBuilder.add(worldPreset(WorldPresets.SNAPSHOT), "自然更替快照");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "generator." + ChunkGeneratorUpgradable.NON_VERSION), "过旧");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "generator.classic"), "自然更替");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "generator.snapshot"), "自然更替快照");
			translationBuilder.add(Blocks.EERIE_REGOLITH, "阴森浮土");
			translationBuilder.add(Blocks.TREACHEROUS_SLUDGE, "诡谲污泥");
			translationBuilder.add(Blocks.EERIE_RIND, "阴森木壳");
			translationBuilder.add(Blocks.TREACHEROUS_SAC, "诡谲囊");
			translationBuilder.add(Blocks.EERIE_BOUGH, "阴森木枝");
			translationBuilder.add(Blocks.TREACHEROUS_VINES, "诡谲藤");
			translationBuilder.add(StatusEffects.ACIDIZE, "酸化");
			translationBuilder.add(soundSub(SoundEvents.TREACHEROUS_SAC_BREAK), "诡谲囊：破裂");
			translationBuilder.add(deathMessage(DamageTypes.ACIDIZE, ""), "%1$s溶解了");
			translationBuilder.add(deathMessage(DamageTypes.ACIDIZE, "player"), "%1$s在与%2$s战斗时溶解了");
		}
	}
	
	final class ZhHk extends LinkedLanguageProvider {
		ZhHk(FabricDataOutput dataOutput) {
			super(dataOutput, "zh_hk");
		}
		
		@Override
		public void generateTranslations(TranslationBuilder translationBuilder) {
			translationBuilder.add(comment("note"), "原生語言為簡體中文(zh_cn)");
			translationBuilder.add(i18nName(Terriforest.INSTANCE), "§3「毛骨森然」拓展包");
			translationBuilder.add(i18nSummary(Terriforest.INSTANCE), "添加咗多種奇異樹木與怪異嘅生物羣落嘅「自然更替」擴展");
			translationBuilder.add(i18nDesc(Terriforest.INSTANCE), """
					§r　　當·Minecraft·中擁有一些怪異嘅奇妙自然景觀時會發生咩呢？呢個就係本擴展包帶畀你嘅體驗！
					§r　　本擴展包添加咗多個怪異嘅樹木與生物羣落，每個樹木都有自己獨特嘅效果與機制，去探索吧！利用呢啲樹木製作全新嘅裝備吧！""");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "homepage"), "模組官網");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "support"), "支持我們");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "community"), "官方社羣");
			translationBuilder.add(WidgetTexts.titleKey(SpontaneousReplace.INSTANCE, "changelog"), "更新日誌");
			translationBuilder.add(WidgetTexts.titleKey(SpontaneousReplace.INSTANCE, "target"), "1.0.0 > 目標");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "target"), "　　繼續計劃實現但未實現嘅地物、生物羣落、物品、道具。");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "tab.synopsis"), "簡介");
			translationBuilder.add(WidgetTexts.titleKey(SpontaneousReplace.INSTANCE, "tab.synopsis.intro"), "§f§l模組簡介：");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "tab.synopsis.intro"), """
					§r　　當·Minecraft·中擁有一些怪異嘅奇妙自然景觀時會發生咩呢？呢個就係本擴展包帶畀你嘅體驗！
					§r　　本擴展包添加咗多個怪異嘅樹木與生物羣落，每個樹木都有自己獨特嘅效果與機制，去探索吧！利用呢啲樹木製作全新嘅裝備吧！""");
			translationBuilder.add(WidgetTexts.titleKey(SpontaneousReplace.INSTANCE, "tab.synopsis.vision"), "§f§l模組願景：");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "tab.synopsis.vision"), """
					§r　　我希望做一個基於原版遊戲內核，不破壞原版遊戲玩法嘅玩法類模組。在這基礎上進行開發十分困難，一件物品是否加入，怎樣設計數據才不會破壞遊戲平衡性，這些都是開發者需要考慮嘅點。
					§r　　如果你覺得模組做嘅不錯，歡迎對我嘅項目進行贊助，或者對此模組進行翻譯，十分感謝你們!""");
			translationBuilder.add(OptionTexts.rootKey(Settings.SETTINGS), "「毛骨森然」拓展包設定⋯⋯");
			translationBuilder.add(worldPreset(WorldPresets.CLASSIC), "自然更替");
			translationBuilder.add(worldPreset(WorldPresets.SNAPSHOT), "自然更替快照");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "generator." + ChunkGeneratorUpgradable.NON_VERSION), "過舊");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "generator.classic"), "自然更替");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "generator.snapshot"), "自然更替快照");
			translationBuilder.add(Blocks.EERIE_REGOLITH, "陰森浮土");
			translationBuilder.add(Blocks.TREACHEROUS_SLUDGE, "詭譎污泥");
			translationBuilder.add(Blocks.EERIE_RIND, "陰森木殼");
			translationBuilder.add(Blocks.TREACHEROUS_SAC, "詭譎囊");
			translationBuilder.add(Blocks.EERIE_BOUGH, "陰森木枝");
			translationBuilder.add(Blocks.TREACHEROUS_VINES, "詭譎藤");
			translationBuilder.add(StatusEffects.ACIDIZE, "酸化");
			translationBuilder.add(soundSub(SoundEvents.TREACHEROUS_SAC_BREAK), "詭譎囊破裂");
			translationBuilder.add(deathMessage(DamageTypes.ACIDIZE, ""), "%1$s 溶解咗");
			translationBuilder.add(deathMessage(DamageTypes.ACIDIZE, "player"), "%1$s 同 %2$s 戰鬥時溶解咗");
		}
	}
	
	final class ZhTw extends LinkedLanguageProvider {
		ZhTw(FabricDataOutput dataOutput) {
			super(dataOutput, "zh_tw");
		}
		
		@Override
		public void generateTranslations(TranslationBuilder translationBuilder) {
			translationBuilder.add(comment("note"), "原生語言為簡體中文(zh_cn)");
			translationBuilder.add(i18nName(Terriforest.INSTANCE), "§3「毛骨森然」擴充套件");
			translationBuilder.add(i18nSummary(Terriforest.INSTANCE), "新增了多種奇異樹木與怪異的生態域的「自然更替」擴充套件");
			translationBuilder.add(i18nDesc(Terriforest.INSTANCE), """
					§r　　當·Minecraft·中擁有一些怪異的奇妙自然景觀時會發生什麼呢？這就是本擴充套件帶給你的體驗！
					§r　　本擴充套件添加了多個怪異的樹木與生態域，每個樹木都有自己獨特的效果與機制，去探索吧！利用這些樹木製作全新的裝備吧！""");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "homepage"), "模組官網");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "support"), "支援我們");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "community"), "官方社群");
			translationBuilder.add(WidgetTexts.titleKey(SpontaneousReplace.INSTANCE, "changelog"), "更新日誌");
			translationBuilder.add(WidgetTexts.titleKey(SpontaneousReplace.INSTANCE, "target"), "1.0.0 > 目標");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "target"), "　　繼續計劃實現但未實現的地物、生態域、物品、道具。");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "tab.synopsis"), "簡介");
			translationBuilder.add(WidgetTexts.titleKey(SpontaneousReplace.INSTANCE, "tab.synopsis.intro"), "§f§l模組簡介：");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "tab.synopsis.intro"), """
					§r　　當·Minecraft·中擁有一些怪異的奇妙自然景觀時會發生什麼呢？這就是本擴充套件帶給你的體驗！
					§r　　本擴充套件添加了多個怪異的樹木與生態域，每個樹木都有自己獨特的效果與機制，去探索吧！利用這些樹木製作全新的裝備吧！""");
			translationBuilder.add(WidgetTexts.titleKey(SpontaneousReplace.INSTANCE, "tab.synopsis.vision"), "§f§l模組願景：");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "tab.synopsis.vision"), """
					§r　　我希望做一個基於原版遊戲核心，不破壞原版遊戲玩法的玩法類模組。在這基礎上進行開發十分困難，一件物品是否加入，怎樣設計資料才不會破壞遊戲平衡性，這些都是開發者需要考慮的點。
					§r　　如果你覺得模組做的不錯，歡迎對我的專案進行贊助，或者對此模組進行翻譯，十分感謝你們!""");
			translationBuilder.add(OptionTexts.rootKey(Settings.SETTINGS), "「毛骨森然」擴充套件設定...");
			translationBuilder.add(worldPreset(WorldPresets.CLASSIC), "自然更替");
			translationBuilder.add(worldPreset(WorldPresets.SNAPSHOT), "自然更替快照");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "generator." + ChunkGeneratorUpgradable.NON_VERSION), "過舊");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "generator.classic"), "自然更替");
			translationBuilder.add(WidgetTexts.textKey(SpontaneousReplace.INSTANCE, "generator.snapshot"), "自然更替快照");
			translationBuilder.add(Blocks.EERIE_REGOLITH, "陰森浮土");
			translationBuilder.add(Blocks.TREACHEROUS_SLUDGE, "詭譎污泥");
			translationBuilder.add(Blocks.EERIE_RIND, "陰森木殼");
			translationBuilder.add(Blocks.TREACHEROUS_SAC, "詭譎囊");
			translationBuilder.add(Blocks.EERIE_BOUGH, "陰森木枝");
			translationBuilder.add(Blocks.TREACHEROUS_VINES, "詭譎藤");
			translationBuilder.add(StatusEffects.ACIDIZE, "酸化");
			translationBuilder.add(soundSub(SoundEvents.TREACHEROUS_SAC_BREAK), "詭譎囊破裂");
			translationBuilder.add(deathMessage(DamageTypes.ACIDIZE, ""), "%1$s 溶解了");
			translationBuilder.add(deathMessage(DamageTypes.ACIDIZE, "player"), "%1$s 在與 %1$s 戰鬥時溶解了");
		}
	}
}

