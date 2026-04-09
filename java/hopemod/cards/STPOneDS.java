package hopemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.GainSTPower;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

public class STPOneDS extends BaseCard {
    public static final String ID = makeID(STPOneDS.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    private static final int OneDS_INT = 1;


    public STPOneDS() {
        super(ID, info);
        setMagic(OneDS_INT);
        this.isEthereal = true;
    }
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
            }
        }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new ApplyPowerAction(p, p, new hopemod.powers.STPOneDS(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPOneDS();
    }
}