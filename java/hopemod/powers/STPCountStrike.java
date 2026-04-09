package hopemod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import static hopemod.BasicMod.makeID;

public class STPCountStrike extends BasePower {
    public static final String POWER_ID = makeID("STP_CS");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public STPCountStrike(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }


    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (source == this.owner && power.ID.equals(STPower.POWER_ID)) {
            int STPamount = power.amount;
            for (int i = 0; i < STPamount; i++) {
                flash();
                this.addToBot(new SFXAction("ATTACK_HEAVY"));
                if (Settings.FAST_MODE) {
                    this.addToBot(new VFXAction(new CleaveEffect()));
                } else {
                    this.addToBot(new VFXAction(this.owner, new CleaveEffect(), 0.2F));
                }
                addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE,true));
            }
        }
    }
}
