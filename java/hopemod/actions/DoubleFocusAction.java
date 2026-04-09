package hopemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import hopemod.powers.STPFocusAtk;

public class DoubleFocusAction extends AbstractGameAction {
    public DoubleFocusAction(AbstractCreature target, AbstractCreature source) {
        this.source = source;
        this.actionType = ActionType.BLOCK;
    }

    @Override
    public void update() {
        int totalStp = 0;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped() && m.hasPower(STPFocusAtk.POWER_ID)) {
                int amount = m.getPower(STPFocusAtk.POWER_ID).amount;
                totalStp += amount;
            }
        }
        if (totalStp > 0) {
            // 注意：如果你要的是「翻倍後」的層數作為格擋，這裡要用 (totalStp * 2)
            this.addToTop(new GainBlockAction(AbstractDungeon.player, this.source, totalStp * 2));
        }
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped() && m.hasPower(STPFocusAtk.POWER_ID)) {
                int amount = m.getPower(STPFocusAtk.POWER_ID).amount;
                this.addToTop(new ApplyPowerAction(m, this.source, new STPFocusAtk(m, amount), amount));
            }
        }


        this.isDone = true;
    }
}