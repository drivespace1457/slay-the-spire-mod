package hopemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.util.CardStats;

public class STPPercent extends BaseCard {
    public static final String ID = makeID(STPPercent.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE, // 百分比傷害通常比較強，建議設為 RARE
            CardTarget.ENEMY,
            2 // 考慮到平衡，消耗可以設高一點
    );

    public STPPercent() {
        super(ID, info);
        // 這裡設定 baseDamage 為 0，因為我們會動態修改它
        setDamage(0);
        // 使用 magicNumber 來存百分比數值，方便升級或顯示
        setMagic(30, 10); // 基礎 30%，升級後再加 10% (變成 40%)
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 執行時再次計算，確保數值精確
        if (m != null) {
            this.calculateCardDamage(m);
        }
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public void applyPowers() {
        // 在沒有指向特定敵人時（例如在手牌裡），可以預覽對「當前血量最高敵人」的傷害，或者乾脆顯示為 0
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        if (mo != null) {
            // 核心邏輯：計算敵人當前生命值的百分比
            // (float)this.magicNumber / 100.0F 得到 0.3
            float percent = (float)this.magicNumber / 100.0F;
            this.baseDamage = (int)((float)mo.currentHealth * percent);
        }

        super.calculateCardDamage(mo);

        // 讓數字變色，提醒玩家這是動態計算的
        this.isDamageModified = true;
    }

    @Override
    public AbstractCard makeCopy() {
        return new STPPercent();
    }
}