package hopemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.defect.ForTheEyesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;
import hopemod.util.CardStats;
import org.omg.CORBA.portable.CustomValue;

public class STPWeakAttack extends BaseCard {
    public static final String ID = makeID(STPWeakAttack.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    public  int STPint = 0;


    public STPWeakAttack() {
        super(ID, info);
        setDamage(6,2);
        setMagic(4,-1);
        setCustomVar("WEAK",1,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));

        if (p.hasPower(STPower.POWER_ID)) {
            STPint = (p.getPower(STPower.POWER_ID)).amount;
        }

        if (STPint >= this.magicNumber){
            addToBot(new ForTheEyesAction(customVar("WEAK") + 1, m));
            addToBot(new ReducePowerAction(p, p, STPower.POWER_ID, this.magicNumber));
        }
        else {
            addToBot(new ForTheEyesAction(customVar("WEAK"), m));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPWeakAttack();
    }
}