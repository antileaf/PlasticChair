package antileaf.plastic.patches;

import antileaf.plastic.relics.PlasticChair;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.GameOverScreen;
import javassist.CannotCompileException;
import javassist.CtBehavior;

@SuppressWarnings("unused")
public class BonusScorePatch {
	public static boolean PLASTIC_CHAIR = false;
	
	@SpirePatch(clz = GameOverScreen.class, method = "resetScoreChecks")
	public static class GameOverScreenResetPatch {
		@SpirePostfixPatch
		public static void Postfix(GameOverScreen _inst) {
			PLASTIC_CHAIR = false;
		}
	}
	
	@SpirePatch(clz = GameOverScreen.class, method = "checkScoreBonus", paramtypez = {boolean.class})
	public static class GameOverScreenCheckBonusPatch {
		private static class Locator extends SpireInsertLocator {
			@Override
			public int[] Locate(CtBehavior ctBehavior) throws PatchingException, CannotCompileException {
				int[] tmp = LineFinder.findAllInOrder(ctBehavior,
						new Matcher.MethodCallMatcher(AbstractPlayer.class, "hasRelic"));
				return new int[]{tmp[0]};
			}
		}
		
		@SpireInsertPatch(locator = Locator.class, localvars = {"points"})
		public static void Insert(GameOverScreen _inst, boolean isVictory, @ByRef int[] points) {
			if (AbstractDungeon.player.hasRelic(PlasticChair.ID)) {
				PLASTIC_CHAIR = true;
				points[0] += 1;
			}
			else
				PLASTIC_CHAIR = false;
		}
	}
	
//	@SpirePatch(clz = VictoryScreen.class, method = "createGameOverStats")
//	public static class VictoryScreenPatch {
//		private static class Locator extends SpireInsertLocator {
//			@Override
//			public int[] Locate(CtBehavior ctBehavior) throws PatchingException, CannotCompileException {
//				int[] tmp = LineFinder.findAllInOrder(ctBehavior,
//						new Matcher.FieldAccessMatcher(GameOverScreen.class, "IS_POOPY"));
//				return new int[]{tmp[0]};
//			}
//		}
//
//		@SpireInsertPatch(locator = Locator.class)
//		public static void Insert(VictoryScreen _inst, ArrayList<GameOverStat> ___stats) {
//			if (PLASTIC_CHAIR)
//				___stats.add(new GameOverStat(PlasticChair.bonusStrings.NAME,
//						PlasticChair.bonusStrings.DESCRIPTIONS[0], Integer.toString(1)));
//		}
//	}
	
//	@SpirePatch(clz = DeathScreen.class, method = "createGameOverStats")
//	public static class DeathScreenPatch {
//		private static class Locator extends SpireInsertLocator {
//			@Override
//			public int[] Locate(CtBehavior ctBehavior) throws PatchingException, CannotCompileException {
//				int[] tmp = LineFinder.findAllInOrder(ctBehavior,
//						new Matcher.FieldAccessMatcher(GameOverScreen.class, "IS_POOPY"));
//				return new int[]{tmp[0]};
//			}
//		}
//
//		@SpireInsertPatch(locator = Locator.class)
//		public static void Insert(DeathScreen _inst, ArrayList<GameOverStat> ___stats) {
//			if (PLASTIC_CHAIR)
//				___stats.add(new GameOverStat(PlasticChair.bonusStrings.NAME,
//						PlasticChair.bonusStrings.DESCRIPTIONS[0], Integer.toString(1)));
//		}
//	}
}
