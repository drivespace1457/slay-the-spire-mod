package hopemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.util.CardStats;

public class STPDoubleGain extends BaseCard {
    public static final String ID = makeID(STPDoubleGain.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            3
    );

    public STPDoubleGain() {
        super(ID, info);
    }
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
            }
        }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new ApplyPowerAction(p, p, new hopemod.powers.STPDoubleGain(p, 1),this.magicNumber));
    }
    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPDoubleGain();
    }
}