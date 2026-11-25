package core.fx;

import assets.Effect;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/** Máu bắn ra khi trúng đòn (2 ô). */
public final class Blood {
    public static final int CAPACITY = 2;
    public static final int FREE = -1;
    public static final int FRAME_COUNT = 3;
    /** Số frame hiển thị. */
    public static final int LIFETIME = 6;

    public static Image[] sprites;
    private static int[] x;
    private static int[] y;
    /** Tuổi theo frame, {@link #FREE} nếu ô trống. */
    private static int[] timer;
    private static int[] frame;
    /** Hướng bắn: 1 = sang phải, còn lại = sang trái (ảnh lật ngang). */
    private static int[] direction;

    private Blood() {
    }

    public static void init() {
        if (sprites == null) {
            sprites = new Image[FRAME_COUNT];

            for (int i = 0; i < FRAME_COUNT; ++i) {
                try {
                    sprites[i] = Effect.loadBlood(i);
                } catch (IOException ex) {
                }
            }
        }

        x = new int[CAPACITY];
        y = new int[CAPACITY];
        timer = new int[CAPACITY];
        frame = new int[CAPACITY];
        direction = new int[CAPACITY];
        timer[0] = timer[1] = FREE;
    }

    /** Thêm máu tại (px, py) bay theo {@code dir}; trả về false nếu đầy. */
    public static boolean spawn(int px, int py, int dir) {
        int slot = timer[0] == FREE ? 0 : 1;
        if (timer[slot] != FREE) {
            return false;
        }

        timer[slot] = 0;
        direction[slot] = dir;
        x[slot] = px;
        y[slot] = py;
        return true;
    }

    public static void update() {
        for (int i = 0; i < CAPACITY; ++i) {
            if (timer[i] != FREE) {
                ++timer[i];
                x[i] += direction[i] << 2;
                --y[i];
                if (timer[i] >= LIFETIME) {
                    timer[i] = FREE;
                } else {
                    frame[i] = (timer[i] >> 1) % FRAME_COUNT;
                }
            }
        }
    }

    public static void draw(Graphics g) {
        for (int i = 0; i < CAPACITY; ++i) {
            if (timer[i] != FREE) {
                Image image = sprites[frame[i]];
                if (direction[i] == 1) {
                    g.drawImage(image, x[i], y[i], 3);
                } else {
                    g.drawRegion(image, 0, 0, image.getWidth(), image.getHeight(), 2, x[i], y[i], 3);
                }
            }
        }
    }
}
