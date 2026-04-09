package hopemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

public class AllAttackSTP extends BaseCard {
    public static final String ID = makeID(AllAttackSTP.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );

    private static final int DAMAGE = 15;
    private static final int UPG_DAMAGE = 5;
    public AllAttackSTP() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int STPint = 0;
        if (p.hasPower(STPower.POWER_ID)) {
            STPint = (p.getPower(STPower.POWER_ID)).amount;
        }
        if (STPint >= 6) {
            addToBot(new DamageAllEnemiesAction(p,damage,this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            addToBot(new DrawCardAction(1));
        }
        else {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new AllAttackSTP();
    }
}