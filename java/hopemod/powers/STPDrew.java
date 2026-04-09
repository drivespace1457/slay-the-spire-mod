package hopemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static hopemod.BasicMod.makeID;

public class STPDrew extends BasePower {
    public static final String POWER_ID = makeID("STPD");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public STPDrew(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }


    public void atStartOfTurnPostDraw() {

        int STPint = 0;
        if (this.owner.hasPower(STPower.POWER_ID)){
            STPint = (this.owner.getPower(STPower.POWER_ID)).amount;
        }
        if (STPint >= 4) {
            addToBot(new ReducePowerAction(this.owner,this.owner,STPower.POWER_ID,2));
            addToBot(new DrawCardAction(2));
        }
        if (STPint < 4) {
            addToBot(new ReducePowerAction(this.owner,this.owner,STPower.POWER_ID,2));
            addToBot(new DrawCardAction(1));
        }
    }

}
