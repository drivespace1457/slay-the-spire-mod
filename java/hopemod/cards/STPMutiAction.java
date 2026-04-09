package hopemod.cards;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.cards.tempCards.STPP;
import hopemod.character.MyCharacter;
import hopemod.powers.STPFocusAtk;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

public class STPMutiAction extends BaseCard {
    public static final String ID = makeID(STPMutiAction.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );

    public STPMutiAction() {
        super(ID, info);
        setBlock(4,4);
        setMagic(2,1);
        setCustomVar("MutiSTPP",1);
        setCustomVar("MutiEnergy",1,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int STPint = 0;
        if (p.hasPower(STPower.POWER_ID)) {
            STPint = (p.getPower(STPower.POWER_ID)).amount;
        }
        if (STPint >= 1){
            addToBot(new GainBlockAction(p, p, block));
        }
        if (STPint >= 2){
            AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);

            if (randomMonster != null) {
                this.addToBot(new ApplyPowerAction(randomMonster, p, new STPFocusAtk(randomMonster, this.magicNumber), this.magicNumber));
            }
        }
        if (STPint >= 3){
            addToBot(new DrawCardAction(1));
            addToBot(new MakeTempCardInDrawPileAction(new hopemod.cards.tempCards.STPReduceStr(),1,true,true));

        }
        if (STPint >= 4) {
            addToBot(new MakeTempCardInHandAction(new STPP(), customVar("MutiSTPP")));
            addToBot(new GainEnergyAction(customVar("MutiEnergy")));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPMutiAction();
    }
}