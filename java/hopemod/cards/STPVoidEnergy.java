package hopemod.cards;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

public class STPVoidEnergy extends BaseCard {
    public static final String ID = makeID(STPVoidEnergy.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );

    public STPVoidEnergy() {
        super(ID, info);
        setMagic(1);
    }
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(this.magicNumber));
        addToBot(new ApplyPowerAction(p,p,new STPower(p,this.magicNumber)));
        addToBot(new MakeTempCardInDrawPileAction(new VoidCard(), 1,true,true));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPVoidEnergy();
    }
}