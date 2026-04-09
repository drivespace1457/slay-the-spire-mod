package hopemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

public class DefendSTP extends BaseCard {
    public static final String ID = makeID(DefendSTP.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 5;
    private static final int EX_BLOCK = 5;
    private static final int EXX_BLOCK = 5;

    public DefendSTP() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(EX_BLOCK, EXX_BLOCK);//Sets the card's damage and how much it changes when upgraded.
        setCustomVar("STP",3,2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int gained = 0;

        if (p.hasPower(STPower.POWER_ID)) {
            STPower power = (STPower) p.getPower(STPower.POWER_ID);
            gained = power.gainedThisTurn;
        }
        if (gained <= 4) {
            addToBot(new GainBlockAction(p,p,block));
            addToBot(new ApplyPowerAction(p,p,new STPower(p,customVar("STP"))));
        }
        else {
            addToBot(new GainBlockAction(p,p,this.magicNumber));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DefendSTP();
    }
}