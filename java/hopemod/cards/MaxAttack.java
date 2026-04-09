package hopemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.actions.StpAction;
import hopemod.character.MyCharacter;
import hopemod.util.CardStats;

public class MaxAttack extends BaseCard {
    public static final String ID = makeID(MaxAttack.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;
    private static final int MAX_HP = 1;
    private static final int MAXX_HP = 1;

    public MaxAttack() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setMagic(MAX_HP,MAXX_HP);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new StpAction(m,new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MaxAttack();
    }
}