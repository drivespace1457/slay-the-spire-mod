package hopemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.actions.DoubleFocusAction;
import hopemod.character.MyCharacter;
import hopemod.util.CardStats;

public class STPDoubleFocusBlock extends BaseCard {
    public static final String ID = makeID(STPDoubleFocusBlock.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            2
    );

    public STPDoubleFocusBlock() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DoubleFocusAction(m,p));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPDoubleFocusBlock();
    }
}