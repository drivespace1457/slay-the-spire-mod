package hopemod.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.util.CardStats;

public class DrewTwoDisOne extends BaseCard {
    public static final String ID = makeID(DrewTwoDisOne.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    private static final int DIS_INT = 1;

    public DrewTwoDisOne() {
        super(ID, info);
        setMagic(DIS_INT);
        setCustomVar("DREW",2);
        this.exhaust = true;
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }

    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, customVar("DREW")));
        addToBot(new DiscardAction(p, p, this.magicNumber, false));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DrewTwoDisOne();
    }
}