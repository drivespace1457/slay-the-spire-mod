package hopemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

public class STPBuffer extends BaseCard {
    public static final String ID = makeID(STPBuffer.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );
    public static final int Buffer = 1;
    public static final int EX_Buffer = 1;

    public STPBuffer() {
        super(ID, info);
        setMagic(Buffer,EX_Buffer);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int STPint = 0;
        if (p.hasPower(STPower.POWER_ID)) {
            STPint = (p.getPower(STPower.POWER_ID)).amount;
        }
        if (STPint >= 13) {
            addToBot(new ApplyPowerAction(p, p, new BufferPower(p, this.magicNumber), this.magicNumber));
            addToBot(new ReducePowerAction(p,p,STPower.POWER_ID,13));
        }
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        }

        int STPint = 0;
        if (p.hasPower(STPower.POWER_ID)) {
            STPint = p.getPower(STPower.POWER_ID).amount;
        }

        if (STPint < 13) {
            this.cantUseMessage = "非凡之力不足"; // 顯示紅字提示
            return false;
        }

        return true;
    }
    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPBuffer();
    }
}