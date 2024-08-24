package antileaf.plastic.utils;

import basemod.BaseMod;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class BGMHelper {
	public static String BGM = "PlasticChairMod:PlasticChairBGM";
	public static String BGM_PATH = "PlasticChairMod/audio/bury_the_light.mp3";
	public static long id = 0L;
	
	public static void init() {
		BaseMod.addAudio(BGM, "PlasticChairMod/audio/bury_the_light.mp3");
	}
	
	public static boolean isPlaying() {
		return id != 0L;
	}
	
	public static void startBGM() {
		id = CardCrawlGame.sound.playAndLoop(BGM);
	}
	
	public static void stopBGM() {
		CardCrawlGame.sound.stop(BGM, id);
		id = 0L;
	}
	
	public static void adjustVolume(float volume) {
		CardCrawlGame.sound.adjustVolume(BGM, id, volume);
	}
}
