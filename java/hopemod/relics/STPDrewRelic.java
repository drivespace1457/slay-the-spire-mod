package hopemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;

import static hopemod.BasicMod.makeID;

public class STPDrewRelic extends BaseRelic {
    private static final String NAME = "STPDrewRelic"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.
    //For convenience of changing it later and clearly knowing what the number means instead of just having a 10 sitting around in the code.

    public STPDrewRelic() {
        super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
        this.counter = 0;
    }

    public void atBattleStart() {
        this.counter = 0;
    }
    public void onCardDraw(AbstractCard card){
        ++this.counter;
        if (this.counter == 2){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, new STPower((AbstractCreature)AbstractDungeon.player, 1), 1));
            this.counter = 0;
        }
    }
    @Override
    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0]);
    }
}