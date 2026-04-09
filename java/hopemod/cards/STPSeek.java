package hopemod.cards;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

public class STPSeek extends BaseCard {
    public static final String ID = makeID(STPSeek.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );

    public STPSeek() {
        super(ID, info);
        setMagic(1,1);
        setCustomVar("SeekNum",2,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int STPint = 0;
        if (p.hasPower(STPower.POWER_ID)) {
            STPint = (p.getPower(STPower.POWER_ID)).amount;
        }
        if (STPint > 0) {
            if (STPint >= 5) {
                addToBot(new BetterDrawPileToHandAction(this.magicNumber));
                addToBot(new ReducePowerAction(p, p, STPower.POWER_ID, 2));
            }
            if (STPint < 5) {
                addToBot(new BetterDiscardPileToHandAction(this.magicNumber));
                addToBot(new ReducePowerAction(p, p, STPower.POWER_ID, 1));
            }
        }
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        }

        int STPint = 0;
        if (p.hasPower(STPower.POWER_ID)) {
            STPint = p.getPower(STPower.POWER_ID).amount;
        }

        if (STPint < 1) {
            this.cantUseMessage = "非凡之力不足";
            return false;
        }

        return true;
    }
    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPSeek();
    }
}