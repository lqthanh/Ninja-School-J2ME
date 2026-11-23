package core.player;

import assets.Effect;
import core.quest.Skills;
import javax.microedition.lcdui.Image;

/**
 * Phi tiêu nhân vật ném ra (tối đa {@link #CAPACITY} cái cùng lúc). Phi tiêu bay thẳng,
 * cắm vào tường ({@link #STATE_STUCK}) khoảng 8 frame rồi rơi xuống. Phi tiêu mạnh
 * ({@link #powered}) gây gấp đôi sát thương và xuyên qua ninja địch.
 * Cập nhật và va chạm nằm trong b.run().
 */
public final class Darts {
    public static final int CAPACITY = 10;

    public static final int STATE_FREE = -1;
    public static final int STATE_FLYING = 1;
    /** Cắm vào tường; tăng dần mỗi frame tới 10 thì rơi xuống (trở lại {@link #STATE_FLYING}). */
    public static final int STATE_STUCK = 2;

    public static int count;
    public static int[] x;
    public static int[] y;
    public static int[] state;
    /** Khung hình (0..1). */
    public static int[] frame;
    public static int[] vx;
    public static int[] vy;
    public static boolean[] powered;
    public static Image[] sprites;

    private Darts() {
    }

    public static final void init() {
        int i;
        if (sprites == null) {
            sprites = Effect.createDartFrames();
            Effect.loadDarts(sprites, Skills.dartsLevel2 ? Effect.DART_LEVEL_2 : Effect.DART_LEVEL_1);
        }

        count = 10;
        x = new int[10];
        y = new int[count];
        state = new int[count];
        frame = new int[count];
        vx = new int[count];
        powered = new boolean[count];
        vy = new int[count];

        for(i = 0; i < count; ++i) {
            state[i] = STATE_FREE;
        }

    }

    /** Ném một phi tiêu nếu còn ô trống. */
    public static void spawn(int startX, int startY, int speedX, int speedY, boolean isPowered) {
        int slot = -1;

        for(int i = 0; i < count; ++i) {
            if (state[i] == STATE_FREE) {
                slot = i;
                break;
            }
        }

        if (slot != -1) {
            state[slot] = STATE_FLYING;
            x[slot] = startX;
            y[slot] = startY;
            vx[slot] = speedX;
            vy[slot] = speedY;
            powered[slot] = isPowered;
        }
    }
}
