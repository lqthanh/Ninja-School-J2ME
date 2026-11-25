package core.fx;

import assets.Effect;
import core.app.Settings;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/** Bọt nước khi nhân vật, quái rơi xuống nước (2 ô; tắt khi chất lượng hình ảnh thấp). */
public final class WaterSplash {
    public static final int CAPACITY = 2;
    public static final int FREE = -1;
    public static final int FRAME_COUNT = 3;

    public static Image[] sprites;
    private static int[] x;
    private static int[] y;
    /** Bộ đếm khung hình (tăng mỗi 2 frame), {@link #FREE} nếu ô trống. */
    private static int[] timer;
    private static int[] frame;

    private WaterSplash() {
    }

    public static void init() {
        if (!Settings.lowGraphics) {
            sprites = new Image[FRAME_COUNT];

            for (int i = 0; i < FRAME_COUNT; ++i) {
                try {
                    sprites[i] = Effect.loadWaterSplash(i);
                } catch (IOException ex) {
                }
            }

            x = new int[CAPACITY];
            y = new int[CAPACITY];
            timer = new int[CAPACITY];
            frame = new int[CAPACITY];
            timer[0] = timer[1] = FREE;
        }
    }

    /** Thêm bọt nước tại (px, py); trả về false nếu đầy hoặc đang tắt hiệu ứng. */
    public static boolean spawn(int px, int py) {
        if (Settings.lowGraphics) {
            return false;
        }

        int slot = timer[0] == FREE ? 0 : 1;
        if (timer[slot] != FREE) {
            return false;
        }

        timer[slot] = 0;
        x[slot] = px;
        y[slot] = py;
        return true;
    }

    public static void update(int tick) {
        if (Settings.lowGraphics) {
            return;
        }

        for (int i = 0; i < CAPACITY; ++i) {
            if (timer[i] != FREE) {
                --y[i];
                if (tick % 2 == 0) {
                    ++timer[i];
                    if (timer[i] > FRAME_COUNT - 1) {
                        timer[i] = FREE;
                    } else {
                        frame[i] = timer[i];
                    }
                }
            }
        }
    }

    public static void draw(Graphics g) {
        if (Settings.lowGraphics) {
            return;
        }

        for (int i = 0; i < CAPACITY; ++i) {
            if (timer[i] != FREE) {
                g.drawImage(sprites[frame[i]], x[i], y[i], 3);
            }
        }
    }
}
