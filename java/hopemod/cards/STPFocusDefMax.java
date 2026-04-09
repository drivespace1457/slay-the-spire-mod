package hopemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Shame;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import hopemod.character.MyCharacter;
import hopemod.powers.STPFocusAtk;
import hopemod.util.CardStats;

public class STPFocusDefMax extends BaseCard {
    public static final String ID = makeID(STPFocusDefMax.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            3
    );

    public STPFocusDefMax() {
        super(ID, info);
        setBlock(30,10);
        setMagic(1,1);
        upgradeBaseCost(1);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if (!mo.isDead && !mo.isDying) {
                addToBot(new ApplyPowerAction(mo, p, new STPFocusAtk(mo, this.magicNumber), this.magicNumber));
            }
        }
        addToBot(new MakeTempCardInHandAction(new Shame(), 1));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPFocusDefMax();
    }
}