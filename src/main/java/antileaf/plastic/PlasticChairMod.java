package antileaf.plastic;

import antileaf.plastic.relics.PlasticChair;
import antileaf.plastic.utils.ConfigHelper;
import antileaf.plastic.utils.ImageHelper;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.ScoreBonusStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@SuppressWarnings("Duplicates")
@SpireInitializer
public class PlasticChairMod implements
		EditRelicsSubscriber,
		EditStringsSubscriber,
		PostInitializeSubscriber,
		RenderSubscriber,
		AddAudioSubscriber {
	public static final String SIMPLE_NAME = PlasticChairMod.class.getSimpleName();
	public static final Logger logger = LogManager.getLogger(PlasticChairMod.class.getName());
	
	public static String makeID(String name) {
		return SIMPLE_NAME + ":" + name;
	}
	
	public PlasticChairMod() {
		BaseMod.subscribe(this);
	}
	
	@SuppressWarnings("unused")
	// 必须有这个函数才能初始化
	public static void initialize() {
		new PlasticChairMod();
		ConfigHelper.load();
	}
	
	@Override
	public void receiveEditRelics() {
		BaseMod.addRelic(new PlasticChair(), RelicType.SHARED);
	}
	
	@Override
	public void receiveEditStrings() {
		logger.info("start editing strings");
		
		String lang = Settings.language.name().toLowerCase();
		if (!Gdx.files.internal("localization/" + lang).exists())
			lang = "eng";
		
		BaseMod.loadCustomStringsFile(RelicStrings.class, "localization/" + lang + "/relics.json");
		BaseMod.loadCustomStringsFile(ScoreBonusStrings.class, "localization/" + lang + "/score.json");
	}
	
	@Override
	public void receivePostInitialize() {
		BaseMod.registerModBadge(
				ImageMaster.loadImage("PlasticChair/img/modBadge.png"),
				"Plastic Chair",
				"antileaf, wolf",
				"Adds a plastic chair relic.",
				ConfigHelper.createConfigPanel()
		);
		
		ImageHelper.load();
	}
	
	@Override
	public void receiveRender(SpriteBatch sb) {
//		DollManager.get().render(sb);
	}
	
	@Override
	public void receiveAddAudio() {
		logger.info("Adding audio");
	}
}
