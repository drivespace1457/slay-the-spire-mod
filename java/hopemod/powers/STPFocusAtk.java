package hopemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static hopemod.BasicMod.makeID;

public class STPFocusAtk extends BasePower {
    public static final String POWER_ID = makeID("STP_FT");
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public STPFocusAtk(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }


    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner != null && info.owner != this.owner) {
            this.flash();
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new STPower(AbstractDungeon.player, 1), 1));
        }
        return damageAmount;
    }

    @Override
    public void atEndOfRound() {
        if(this.amount >= 20){
            addToTop(new ApplyPowerAction(this.owner, this.owner, new STPFocusTwenty(this.owner, 1), 1));
            addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,STPFocusAtk.POWER_ID));
        }
        else {
            addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

}
