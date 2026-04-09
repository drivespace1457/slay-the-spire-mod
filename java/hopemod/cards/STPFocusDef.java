package hopemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.STPFocusAtk;
import hopemod.util.CardStats;

public class STPFocusDef extends BaseCard {
    public static final String ID = makeID(STPFocusDef.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            2
    );

    public STPFocusDef() {
        super(ID, info);
        setBlock(15,3);
        setMagic(2,2);
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
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPFocusDef();
    }
}