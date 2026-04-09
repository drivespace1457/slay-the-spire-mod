package hopemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

import static hopemod.BasicMod.makeID;

public class STPFocusTwenty extends BasePower {
    public static final String POWER_ID = makeID("STP_FT20");
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public STPFocusTwenty(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    // 1. 之前的減少 20% 傷害邏輯保持不變
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage * 0.9F;
        }
        return damage;
    }

    // 2. 新增：每回合結束給玩家金幣
    @Override
    public void atEndOfRound() {
        if (this.amount > 0) {
            this.flash(); // 能力閃爍特效
            float startX = this.owner.hb.cX;
            float startY = this.owner.hb.cY;

            int goldAmount = this.amount * 3;

            for (int i = 0; i < goldAmount; i++) {
                AbstractDungeon.effectList.add(new GainPennyEffect(startX,startY));
            }
            addToBot(new GainGoldAction(goldAmount));
            com.megacrit.cardcrawl.core.CardCrawlGame.sound.play("GOLD_GAIN");
        }
    }

}
