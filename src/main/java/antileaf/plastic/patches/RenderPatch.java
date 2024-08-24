package antileaf.plastic.patches;

import antileaf.plastic.relics.PlasticChair;
import antileaf.plastic.utils.ImageHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;

@SuppressWarnings("unused")
public class RenderPatch {
	@SpirePatch(clz = AbstractPlayer.class, method = "render", paramtypez = {SpriteBatch.class})
	public static class PlayerRenderPatch {
		@SpirePostfixPatch
		public static void Postfix(AbstractPlayer _inst, SpriteBatch sb) {
			Color originalColor = sb.getColor();
			sb.setColor(Color.WHITE.cpy());
			
			if (_inst.hasRelic(PlasticChair.ID))
				sb.draw(
						ImageHelper.CHAIR,
						_inst.drawX - 105.0F + (_inst.flipHorizontal ? 1.0F : -1.0F) * 60.0F * Settings.scale,
						_inst.drawY,
						105.0F,
						0.0F,
						210.0F,
						230.0F,
						Settings.scale,
						Settings.scale,
						0.0F,
						0,
						0,
						210,
						230,
						!_inst.flipHorizontal,
						false);
		}
	}
}
