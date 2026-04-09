package hopemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;
import hopemod.util.CardStats;
import hopemod.util.Sounds;


public class STPStackAttack extends BaseCard {
    public static final String ID = makeID(STPStackAttack.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            2
    );

    public STPStackAttack() {
        super(ID, info);
        setDamage(0,0);
        upgradeBaseCost(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(Sounds.STPStackAttack));
        addToBot(new DamageAllEnemiesAction(p, damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public void applyPowers(){
        int STPint = 0;
        if (AbstractDungeon.player.hasPower(STPower.POWER_ID)) {
            STPint = (AbstractDungeon.player.getPower(STPower.POWER_ID)).amount;
        }

        this.baseDamage = STPint;
        super.applyPowers();

        if (this.damage < 0){
            this.damage = 0;
        }
        this.isDamageModified = this.damage > 0;
        this.initializeDescription();

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPStackAttack();
    }
}