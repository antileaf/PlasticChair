package antileaf.plastic.patches;

import antileaf.plastic.PlasticChairMod;
import antileaf.plastic.relics.PlasticChair;
import antileaf.plastic.utils.BGMHelper;
import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

@SuppressWarnings("unused")
public class BGMPatch {
	@SpirePatch(clz = MainMusic.class, method = "getSong", paramtypez = {String.class})
	public static class GetSongPatch {
		@SpirePrefixPatch
		public static SpireReturn<Music> Prefix(String key) {
			if (key.equals(BGMHelper.BGM))
				return SpireReturn.Return(MainMusic.newMusic(BGMHelper.BGM_PATH));
			return SpireReturn.Continue();
		}
	}
	
	@SpirePatch(clz = AbstractRoom.class, method = "playBGM", paramtypez = {String.class})
	public static class PlayBGMPatch {
		@SpirePrefixPatch
		public static void Prefix(AbstractRoom room, @ByRef String[] key) {
			if (AbstractDungeon.isPlayerInDungeon() && AbstractDungeon.player != null &&
					AbstractDungeon.player.hasRelic(PlasticChair.ID)) {
				key[0] = BGMHelper.BGM;
				PlasticChairMod.logger.info("Changed BGM: {}", key[0]);
			}
		}
	}
}
