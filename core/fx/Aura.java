package core.fx;

import assets.Effect;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/** Hào quang nhấp nháy (khi biến hình, khi vật phẩm nhiệm vụ xuất hiện). Chỉ một cái. */
public final class Aura {
    public static final int DURATION = 30;

    public static Image[] sprites;
    public static int x;
    public static int y;
    /** Đếm frame từ 1, 0 = không hiển thị. */
    public static int timer;

    private Aura() {
    }

    public static void init() {
        if (sprites == null) {
            sprites = new Image[2];

            for (int i = 0; i < 2; ++i) {
                try {
                    sprites[i] = Effect.loadAura(i);
                } catch (IOException ex) {
                }
            }
        }
    }

    public static void show(int px, int py) {
        timer = 1;
        x = px;
        y = py;
    }

    public static void update() {
        if (timer > 0 && ++timer > DURATION) {
            timer = 0;
        }
    }

    /** Nhấp nháy: 2 khung hình rồi 1 frame ẩn. */
    public static void draw(Graphics g) {
        int frame;
        if (timer != 0 && (frame = timer % 3) < 2) {
            g.drawImage(sprites[frame], x, y, 17);
        }
    }
}
