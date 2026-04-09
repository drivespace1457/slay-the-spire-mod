package hopemod.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hopemod.character.MyCharacter;
import hopemod.powers.STPower;
import hopemod.util.CardStats;

import java.util.ArrayList;
import java.util.List;

public class STPEnergy extends BaseCard {
    public static final String ID = makeID(STPEnergy.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    private static final int STPOWER = 5;
    private static final int EX_STPOWER = 0;

    public STPEnergy() {
        super(ID, info);
        setMagic(STPOWER,EX_STPOWER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new ApplyPowerAction(p, p, new STPower(p, this.magicNumber)));
    }
    /* 自訂文字 但是在UI.json裡面不能用空格 導致文字顏色不能換 改用keyword比較好 雖然卡面描述上是黃色 但是裡面字的顏色可以改
    public List<TooltipInfo> getCustomTooltips() {
        UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("hopemod:STPower");
        String[] TEXT = uiStrings.TEXT;
        List<TooltipInfo> STPEnergy = new ArrayList<>();
        STPEnergy.add(new TooltipInfo(TEXT[0], TEXT[1]));
        return STPEnergy.isEmpty() ? null : STPEnergy;
    }
    */
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new STPEnergy();
    }
}