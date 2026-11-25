package core.quest;

import assets.Item;
import core.player.Player;
import core.ui.Notice;
import core.util.MathUtil;
import javax.microedition.lcdui.Image;

/** Vật phẩm nhiệm vụ (quyển bí kíp) rơi ra trên map để nhặt. */
public final class QuestItem {
    /** Ảnh quyển bí kíp. */
    public static Image icon;

    /** Vị trí vật phẩm (pixel trên map). */
    public static int x;
    public static int y;

    /** 0 không có, 1 đang rơi, 2 nằm trên đất chờ nhặt. */
    public static int state;

    private QuestItem() {
    }

    /** Nạp ảnh quyển bí kíp (một lần). */
    public static void loadIcon() {
        try {
            icon = Item.loadScroll();
        } catch (Exception e) {
        }
    }

    /** Cho vật phẩm rơi ra tại (x, y). */
    public static void place(int x, int y) {
        QuestItem.x = x;
        QuestItem.y = y;
        state = 1;
    }

    /** Vật phẩm nhiệm vụ: rơi xuống đất, nhân vật chạm vào thì nhặt. */
    public static void update() {
        if (state != 0) {
            if (state == 1) {
                if (MathUtil.abs(Player.y - (Player.height >> 1) - (y - 7)) < 10 && MathUtil.abs(Player.x - x) < 10) {
                    state = 2;
                    QuestState.itemFound = true;
                    Notice.show("đã lấy được bí kíp");
                }
            } else {
                ++state;
                y -= 3;
                if (state > 40) {
                    state = 0;
                }
            }
        }
    }
}
