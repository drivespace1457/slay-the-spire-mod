package hopemod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.cards.tempCards.STPP;
import hopemod.character.MyCharacter;
import hopemod.util.CardStats;

public class STPTemp extends BaseCard {
    public static final String ID = makeID(STPTemp.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 2;
    private static final int STPPINT = 1;
    private static final int EX_STPPINT = 1;


    public STPTemp() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK); //Sets the card's damage and how much it changes when upgraded.
        setMagic(STPPINT,EX_STPPINT);
        this.cardsToPreview = new STPP();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new MakeTempCardInHandAction(new STPP(), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPTemp();
    }
}