package antileaf.plastic.utils;

import antileaf.plastic.PlasticChairMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

import java.io.IOException;
import java.util.Properties;

public class ConfigHelper {
	public static String SHOULD_CHANGE_BGM = "should_change_bgm";
	
	public static SpireConfig conf = null;
	
	public static void load() {
		try {
			Properties defaults = new Properties();
			defaults.setProperty(SHOULD_CHANGE_BGM, "true");
			
			conf = new SpireConfig(PlasticChairMod.SIMPLE_NAME, "config", defaults);
		} catch (IOException e) {
			PlasticChairMod.logger.error("Failed to load config.");
		}
	}
	
	public static void save() {
		try {
			conf.save();
		} catch (IOException e) {
			PlasticChairMod.logger.error("Failed to save config.");
		}
	}
	
	public static boolean shouldChangeBGM() {
		return conf.getBool(SHOULD_CHANGE_BGM);
	}
	
	public static void setShouldChangeBGM(boolean shouldChangeBGM) {
		conf.setBool(SHOULD_CHANGE_BGM, shouldChangeBGM);
	}
	
	public static ModPanel createConfigPanel() {
		ModPanel panel = new ModPanel();
		
		String text;
		switch (Settings.language) {
			case ZHS:
				text = "更改背景音乐";
				break;
			case ZHT:
				text = "更改背景音樂";
				break;
			case JPN:
				text = "BGMを変更する";
				break;
			case KOR:
				text = "BGM 변경";
				break;
			case FRA:
				text = "Changer la BGM";
				break;
			case DEU:
				text = "BGM ändern";
				break;
			case RUS:
				text = "Изменить BGM";
				break;
			case PTB:
				text = "Mudar BGM";
				break;
			case ITA:
				text = "Cambia BGM";
				break;
			case ENG:
			default:
				text = "Change BGM";
				break;
		}
		
		panel.addUIElement(new ModLabeledToggleButton(
				text,
				350.0F,
				700.0F,
				Settings.CREAM_COLOR,
				FontHelper.charDescFont,
				shouldChangeBGM(),
				panel,
				(modLabel) -> {},
				(button) -> {
					setShouldChangeBGM(button.enabled);
					save();
				}
		));
		
		return panel;
	}
}
