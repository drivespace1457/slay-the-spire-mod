package hopemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class StpAction extends AbstractGameAction {
    private final int increaseHpAmount;
    public DamageInfo info;


    public StpAction(AbstractMonster target, DamageInfo info, int maxHPAmount) {
        this.target = target;
        this.info = info;
        this.increaseHpAmount = maxHPAmount;
    }

    @Override
    public void update() {
        this.target.damage(this.info);
        if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead
                && !this.target.hasPower("Minion")) { //斬殺
            AbstractDungeon.player.increaseMaxHp(this.increaseHpAmount, false);
        }
        this.isDone = true;
    }

}