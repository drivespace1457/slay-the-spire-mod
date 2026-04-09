package hopemod.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.STPFocusAtk;
import hopemod.powers.STPower;
import hopemod.util.CardStats;


public class FocusBomb extends BaseCard {
    public static final String ID = makeID(FocusBomb.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2
    );

    public FocusBomb() {
        super(ID, info);
        this.baseDamage = 0;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void applyPowers(){
        this.baseDamage = 0;
        super.applyPowers();

        this.isDamageModified = false;
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int amount = 0;

        if (mo != null && mo.hasPower(STPFocusAtk.POWER_ID)) {
            amount = mo.getPower(STPFocusAtk.POWER_ID).amount;
        }


        this.baseDamage = amount * 4;
        this.damage = this.baseDamage;

        this.isDamageModified = this.damage > 0;
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FocusBomb();
    }
}