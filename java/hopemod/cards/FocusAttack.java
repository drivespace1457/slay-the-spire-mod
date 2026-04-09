package hopemod.cards;

/*
import basemod.helpers.TooltipInfo;
import java.util.ArrayList;
import java.util.List;
*/

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.util.CardStats;


public class FocusAttack extends BaseCard {
    public static final String ID = makeID(FocusAttack.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2
    );

    public FocusAttack() {
        super(ID, info);
        setDamage(0);
        setMagic(2,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.calculateCardDamage(m);
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public void applyPowers() {
        this.baseDamage = 0;
        super.applyPowers();
        this.isDamageModified = false;
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int amount = 0;

        if (mo != null && mo.hasPower(hopemod.powers.STPFocusAtk.POWER_ID)) {
            amount = mo.getPower(hopemod.powers.STPFocusAtk.POWER_ID).amount;
        }

        this.baseDamage = amount * this.magicNumber;

        super.calculateCardDamage(mo);

        this.isDamageModified = (this.damage > 0);
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FocusAttack();
    }
}