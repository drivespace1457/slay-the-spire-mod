package hopemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

public class STPUltiAttack extends BaseCard {
    public static final String ID = makeID(STPUltiAttack.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0
    );

    public STPUltiAttack() {
        super(ID, info);
        setDamage(0,0);
        setMagic(0,2);
        this.baseExhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new SFXAction("ORB_LIGHTNING_EVOKE",0.1F));

        if (Settings.FAST_MODE){
            addToBot(new VFXAction(new LightningEffect(m.drawX, m.drawY)));
        }
        else {
            addToBot(new VFXAction(new LightningEffect(m.drawX, m.drawY),0.1F));
        }

        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        }

    }
    @Override
    public void applyPowers() {
        int STPint = 0;
        if (AbstractDungeon.player.hasPower(STPower.POWER_ID)) {
            STPint = (AbstractDungeon.player.getPower(STPower.POWER_ID)).amount;
        }
        this.baseMagicNumber = STPint / 2 ; //攻擊次數
        this.magicNumber = this.baseMagicNumber;

        this.baseDamage = 3; //單次攻擊傷害

        super.applyPowers();
        this.isMagicNumberModified = this.magicNumber > 0;
        if (this.damage < 0){
            this.damage = 0;
        }

        this.isDamageModified = this.damage > 0;
        this.initializeDescription();
    }
    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPUltiAttack();
    }
}