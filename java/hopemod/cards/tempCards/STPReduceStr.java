package hopemod.cards.tempCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import hopemod.cards.BaseCard;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

public class STPReduceStr extends BaseCard {
    public static final String ID = makeID(STPReduceStr.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.CURSE,
            CardType.CURSE,
            CardRarity.CURSE,
            CardTarget.NONE,
            -2
    );

    public STPReduceStr() {
        super(ID, info);
        this.isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerOnEndOfPlayerTurn(){
        addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new StrengthPower(AbstractDungeon.player,-1),-1));
        addToTop(new com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPReduceStr();
    }
}