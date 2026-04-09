package hopemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;

import static hopemod.BasicMod.makeID;

public class STPRelic extends BaseRelic {
    private static final String NAME = "STPRelic"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.RARE; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.
    //For convenience of changing it later and clearly knowing what the number means instead of just having a 10 sitting around in the code.

    public STPRelic() {
        super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction){
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, new STPower((AbstractCreature)AbstractDungeon.player, 1), 1));
    }

    @Override
    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0]);
    }
}