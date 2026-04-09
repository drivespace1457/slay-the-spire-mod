package hopemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.GainSTPower;
import hopemod.util.CardStats;

public class STPCountStrike extends BaseCard {
    public static final String ID = makeID(STPCountStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );
    public static final int EX_INT = 1;

    public STPCountStrike() {
        super(ID, info);
        setMagic(EX_INT);
    }
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
            }
        }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new ApplyPowerAction(p, p, new hopemod.powers.STPCountStrike(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPCountStrike();
    }
}