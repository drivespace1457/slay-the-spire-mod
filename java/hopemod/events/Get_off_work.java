package hopemod.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import static hopemod.BasicMod.makeID;

public class Get_off_work extends AbstractImageEvent {
    public static final String ID = makeID("Get_off_work");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);

    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private int screenNum = 0;

    public Get_off_work() {
        // 初始對話框顯示 DESCRIPTIONS[0] ("你好。")
        super(NAME, DESCRIPTIONS[0], "hopemod/images/events/Get_off_work.png");

        // 設定初始選項
        this.imageEventText.setDialogOption(OPTIONS[0]); // [回復]
        this.imageEventText.setDialogOption(OPTIONS[1]); // [加入]
        }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0: // 選項 1：補血
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]); // "你感覺好多了。"
                        int healAmt = AbstractDungeon.player.maxHealth / 2;
                        AbstractDungeon.player.heal(healAmt);
                        break;
                    case 1: // 選項 2：給卡
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]); // "你獲得了一份禮物。"
                        // 隨機給一張卡片
                        AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        break;
                }
                // 清除舊按鈕，換成「離開」
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[2]); // "離開"
                this.screenNum = 1;
                break;
            case 1:
                openMap(); // 回到地圖
                break;
        }
    }
}
