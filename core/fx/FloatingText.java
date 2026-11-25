package core.fx;

import assets.FontGraph;
import javax.microedition.lcdui.Graphics;

/** Chữ nổi lên rồi biến mất: sát thương "-20", tiền "+100", hồi máu... (tối đa 5 cùng lúc). */
public final class FloatingText {
    public static final int CAPACITY = 5;
    public static final int FREE = -1;
    /** Số frame tối đa (tính theo quãng đường đã bay) trước khi biến mất. */
    public static final int LIFETIME = 30;

    private static String[] text;
    /** Màu chữ, truyền cho FontGraph.drawSmall. */
    private static int[] color;
    private static int[] x;
    private static int[] y;
    private static int[] vx;
    private static int[] vy;
    /** Tuổi (cộng |vy| mỗi frame), {@link #FREE} nếu ô trống. */
    private static int[] age;

    private FloatingText() {
    }

    public static void init() {
        x = new int[CAPACITY];
        y = new int[CAPACITY];
        vx = new int[CAPACITY];
        vy = new int[CAPACITY];
        age = new int[CAPACITY];
        color = new int[CAPACITY];
        text = new String[CAPACITY];

        for (int i = 0; i < CAPACITY; ++i) {
            age[i] = FREE;
        }
    }

    /**
     * Thêm một dòng chữ; bỏ qua nếu đã đầy.
     *
     * @param unused tham số không dùng (giữ theo bản gốc)
     */
    public static void spawn(String message, int textColor, int px, int py, int unused, int speedY) {
        int slot = FREE;

        for (int i = 0; i < CAPACITY; ++i) {
            if (age[i] == FREE) {
                slot = i;
                break;
            }
        }

        if (slot != FREE) {
            text[slot] = message;
            x[slot] = px;
            y[slot] = py;
            vx[slot] = 0;
            vy[slot] = speedY;
            age[slot] = 0;
            color[slot] = textColor;
        }
    }

    public static void update() {
        for (int i = 0; i < CAPACITY; ++i) {
            if (age[i] != FREE) {
                age[i] += vy[i] > 0 ? vy[i] : -vy[i];
                if (age[i] > LIFETIME) {
                    age[i] = FREE;
                }

                x[i] += vx[i];
                y[i] += vy[i];
            }
        }
    }

    public static void draw(Graphics g) {
        for (int i = 0; i < CAPACITY; ++i) {
            if (age[i] != FREE) {
                FontGraph.drawSmall(g, text[i], color[i], x[i], y[i], 0);
            }
        }
    }
}
