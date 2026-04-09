package hopemod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

public class STPStackDefEveryTurn extends BaseCard {
    public static final String ID = makeID(STPStackDefEveryTurn.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );

    public STPStackDefEveryTurn() {
        super(ID, info);
        this.baseBlock = 0;
        setMagic(1,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }
    @Override
    public void applyPowers() {
        STPower g = (STPower) AbstractDungeon.player.getPower(STPower.POWER_ID);
        if (g != null) {
            this.baseBlock = g.gainedThisTurnDefUsed * this.magicNumber;
        } else {
            this.baseBlock = 0;
        }
        super.applyPowers();

        if (this.block < 0) {
            this.block = 0;
        }

        this.isBlockModified = this.block > 0;
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPStackDefEveryTurn();
    }
}