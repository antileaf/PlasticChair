package antileaf.plastic.relics;

import antileaf.plastic.PlasticChairMod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.ScoreBonusStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.ShopRoom;

public class PlasticChair extends CustomRelic {
	public static final String SIMPLE_NAME = PlasticChair.class.getSimpleName();

	public static final String ID = PlasticChairMod.makeID(SIMPLE_NAME);
	private static final String IMG = "PlasticChair/img/relics/" + SIMPLE_NAME + ".png";
	private static final String IMG_OTL = "PlasticChair/img/relics/outline/" + SIMPLE_NAME + ".png";
	private static final String IMG_LARGE = "PlasticChair/img/relics/large/" + SIMPLE_NAME + ".png";
	
	public static ScoreBonusStrings bonusStrings = CardCrawlGame.languagePack.getScoreString(
			PlasticChairMod.makeID(SIMPLE_NAME));

	public PlasticChair() {
		super(
				ID,
				ImageMaster.loadImage(IMG),
				ImageMaster.loadImage(IMG_OTL),
				RelicTier.COMMON,
				LandingSound.SOLID
		);
		
		this.largeImg = ImageMaster.loadImage(IMG_LARGE);
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	public void atBattleStart() {
		this.flash();
		this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
				new StrengthPower(AbstractDungeon.player, 1), 1));
		this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
	}
	
	@Override
	public boolean canSpawn() {
		return !(AbstractDungeon.getCurrRoom() instanceof ShopRoom);
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new PlasticChair();
	}
}
