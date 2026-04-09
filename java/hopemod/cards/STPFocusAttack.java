package hopemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.STPFocusAtk;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

public class STPFocusAttack extends BaseCard {
    public static final String ID = makeID(STPFocusAttack.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 3;

    public STPFocusAttack() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(3,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
        addToBot(new ApplyPowerAction(m,p,new STPFocusAtk(m,this.magicNumber),this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPFocusAttack();
    }
}