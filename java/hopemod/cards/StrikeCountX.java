package hopemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

public class StrikeCountX extends BaseCard {
    public static final String ID = makeID(StrikeCountX.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            3
    );

    public StrikeCountX() {
        super(ID, info);
        setDamage(0);
        setMagic(2,1);
        //this.selfRetain = true;//保留 測試用
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new DamageRandomEnemyAction(new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }

    @Override
    public void applyPowers() {
        this.baseDamage = STPower.gainedThisTurnOne;
        super.applyPowers();
        this.isDamageModified = this.damage != this.baseDamage;
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        this.baseDamage = STPower.gainedThisTurnOne;
        super.calculateCardDamage(mo);
        this.isDamageModified = this.damage != this.baseDamage;
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new StrikeCountX();
    }
}