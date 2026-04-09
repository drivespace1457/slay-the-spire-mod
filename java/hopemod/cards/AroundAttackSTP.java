package hopemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

public class AroundAttackSTP extends BaseCard {
    public static final String ID = makeID(AroundAttackSTP.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            1
    );

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;
    public AroundAttackSTP() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        this.isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int STPint = 0;
        if (p.hasPower(STPower.POWER_ID)) {
            STPint = (p.getPower(STPower.POWER_ID)).amount;
        }
        if (STPint >= 3) {
            addToBot(new DamageAllEnemiesAction(p,damage,this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            addToBot(new GainEnergyAction(1));
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

        if (STPint < 3) {
            this.cantUseMessage = "非凡之力不足"; // 顯示紅字提示
            return false;
        }

        return true;
    }
    @Override
    public AbstractCard makeCopy() { //Optional
        return new AroundAttackSTP();
    }
}