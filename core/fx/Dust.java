package core.fx;

import assets.Effect;
import core.app.Settings;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Bụi dưới chân khi chạy, tiếp đất (tắt khi chất lượng hình ảnh thấp). Có 2 ô theo hướng:
 * ô 0 bay sang trái, ô 1 bay sang phải.
 */
public final class Dust {
    public static final int FREE = -1;
    public static final int FRAME_COUNT = 5;
    public static final int SLOT_LEFT = 0;
    public static final int SLOT_RIGHT = 1;

    /** Ảnh bụi theo ô (hướng) và khung hình. */
    public static Image[][] sprites;
    public static int[] x;
    public static int[] y;
    /** Khung hình hiện tại, {@link #FREE} nếu ô trống. */
    public static int[] frame;

    private Dust() {
    }

    public static void init() {
        if (!Settings.lowGraphics) {
            if (sprites == null) {
                sprites = new Image[2][FRAME_COUNT];

                for (int slot = 0; slot < 2; ++slot) {
                    for (int f = 0; f < FRAME_COUNT; ++f) {
                        try {
                            sprites[slot][f] = Effect.loadDust(slot, f);
                        } catch (IOException ex) {
                        }
                    }
                }
            }

            x = new int[2];
            y = new int[2];
            (frame = new int[2])[0] = frame[1] = FREE;
        }
    }

    /**
     * Thêm bụi tại (px, py). {@code facing} = 1 (quay phải) thì bụi bay sang trái, ngược
     * lại bay sang phải. Trả về false nếu ô đang bận hoặc đang tắt hiệu ứng.
     */
    public static boolean spawn(int facing, int px, int py) {
        if (Settings.lowGraphics) {
            return false;
        }

        int slot = facing == 1 ? SLOT_LEFT : SLOT_RIGHT;
        if (frame[slot] != FREE) {
            return false;
        }

        frame[slot] = 0;
        x[slot] = px;
        y[slot] = py;
        return true;
    }

    public static void update() {
        if (Settings.lowGraphics) {
            return;
        }

        for (int i = 0; i < 2; ++i) {
            if (frame[i] != FREE) {
                ++frame[i];
                if (frame[i] >= FRAME_COUNT) {
                    frame[i] = FREE;
                }

                if (i == SLOT_LEFT) {
                    --x[i];
                } else {
                    ++x[i];
                }

                --y[i];
            }
        }
    }

    public static void draw(Graphics g) {
        if (Settings.lowGraphics) {
            return;
        }

        for (int i = 0; i < 2; ++i) {
            if (frame[i] != FREE) {
                g.drawImage(sprites[i][frame[i]], x[i], y[i], 3);
            }
        }
    }
}
