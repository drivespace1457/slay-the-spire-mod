package hopemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

public class HpEnergy extends BaseCard {
    public static final String ID = makeID(HpEnergy.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    public int Energy = 2;
    public int STPower = 1;

    public HpEnergy() {
        super(ID, info);
    }
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.Energy = this.Energy + 1;
            this.STPower = this.STPower + 2;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
            }
        }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction((AbstractCreature)p, (AbstractCreature)p, 1));
        addToBot(new GainEnergyAction(this.Energy));
        addToBot(new ApplyPowerAction(p, p, new STPower(p, STPower)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new HpEnergy();
    }
}