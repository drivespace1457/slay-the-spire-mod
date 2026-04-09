package hopemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

public class STPAttack_debuff extends BaseCard {
    public static final String ID = makeID(STPAttack_debuff.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 3;
    private static final int POISON = 3;
    private static final int EX_POISON = 3;
    public STPAttack_debuff() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setMagic(POISON,EX_POISON);
        setCustomVar("STPAttack_debuff_Vul",2,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int STPint = 0;
        if (p.hasPower(STPower.POWER_ID)) {
            STPint = (p.getPower(STPower.POWER_ID)).amount;
        }
        if (STPint >= 1) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
            addToBot(new ApplyPowerAction(m,p,new PoisonPower(m,p,this.magicNumber),this.magicNumber));
            addToBot(new ApplyPowerAction(m,p,new VulnerablePower(p,customVar("STPAttack_debuff_Vul"),true),customVar("STPAttack_debuff_Vul")));
            addToBot(new ReducePowerAction(p,p,STPower.POWER_ID,1));
        }
        else {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPAttack_debuff();
    }
}