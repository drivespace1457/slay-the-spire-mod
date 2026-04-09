package hopemod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

public class STPStackDef extends BaseCard {
    public static final String ID = makeID(STPStackDef.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );



    public STPStackDef() {
        super(ID, info);
        setBlock(0);
        setMagic(0);
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    public void upgrade() {
            upgradeMagicNumber(3);
            this.timesUpgraded++;
            this.upgraded = true;
            this.name = cardStrings.NAME + "+" + this.timesUpgraded;
            initializeDescription();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }
    @Override
    public void applyPowers() {
        int STPint = 0;
        if (AbstractDungeon.player.hasPower(STPower.POWER_ID)) {
            STPint = (AbstractDungeon.player.getPower(STPower.POWER_ID)).amount;
        }

        this.baseBlock = STPint + this.magicNumber;

        super.applyPowers();
        this.isBlockModified = this.block != this.baseBlock;
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPStackDef();
    }
}