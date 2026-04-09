package hopemod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static hopemod.BasicMod.makeID;

public class STPower extends BasePower {
    public static final String POWER_ID = makeID("STP");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public int gainedThisTurn = 0;
    public int gainedThisTurnDefUsed = 0;
    public static int gainedThisTurnOne = 0;

    public STPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        if (amount > 0) {
            this.gainedThisTurn = amount;
            this.gainedThisTurnDefUsed = amount;
            updateAllCards();
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void atEndOfTurn(boolean isPlayer) {
        flash();
        if (isPlayer) {
            int ODCint = 0;
            if (AbstractDungeon.player.hasPower(STPOneDS.POWER_ID)) {
                ODCint = (AbstractDungeon.player.getPower(STPOneDS.POWER_ID)).amount;
            }
            if (this.amount < 8) {
                addToBot(new ReducePowerAction(this.owner, this.owner, this, 3));
            }
            if (this.amount >= 8) {
                addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 1),1));
                if (ODCint > 0) {
                    // 這裡獲取的敏捷量 = 一條龍服務的層數
                    addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, ODCint),ODCint));
                }
                addToBot(new ReducePowerAction(this.owner, this.owner, this, 8));
            }
        }
        gainedThisTurnDefUsed = 0;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);

        if (stackAmount > 0) {
            this.gainedThisTurn += stackAmount;
            gainedThisTurnOne++;
            gainedThisTurnDefUsed++;
            updateAllCards();
        }
    }

    private void updateAllCards() {
        AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : AbstractDungeon.player.hand.group) { c.applyPowers(); }
                for (AbstractCard c : AbstractDungeon.player.drawPile.group) { c.applyPowers(); }
                for (AbstractCard c : AbstractDungeon.player.discardPile.group) { c.applyPowers(); }
                for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) { c.applyPowers(); }
                this.isDone = true;
            }
        });
    }
}
