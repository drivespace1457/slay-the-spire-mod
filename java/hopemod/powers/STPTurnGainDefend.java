package hopemod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static hopemod.BasicMod.makeID;

public class STPTurnGainDefend extends BasePower {
    public static final String POWER_ID = makeID("STP_TGD");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public STPTurnGainDefend(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount <= 0) {
            this.addToTop(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            int STPint = 0;
            if (AbstractDungeon.player.hasPower(STPower.POWER_ID)) {
                STPint = (AbstractDungeon.player.getPower(STPower.POWER_ID)).amount;
            }
            if (STPint > 0) {
                this.flash();
                int AllBlock = STPint * this.amount;
                addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, AllBlock));
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

}
