package core.map;

import assets.Item;
import javax.microedition.lcdui.Image;

/**
 * Thùng gỗ đẩy được (map 1, 5, 11, 97). Nhân vật đẩy hoặc chém thùng để trượt đi,
 * đứng lên thùng để nhảy cao hơn ({@link core.player.Player#crate}). Thùng rơi khỏi mép
 * thì rơi xuống; rơi xuống nước thì nổi bập bềnh và trôi.
 * Cập nhật nằm trong b.run().
 */
public final class Crates {
    /** Đứng yên. */
    public static final int STATE_RESTING = 1;
    /** Đang trượt theo {@link #vx} (giảm dần về 0). */
    public static final int STATE_SLIDING = 2;
    /** Đang rơi. */
    public static final int STATE_FALLING = 4;
    /** Nổi trên mặt nước, nhấp nhô và trôi theo {@link #driftVx}. */
    public static final int STATE_FLOATING = 11;

    public static int count;
    /** Tâm đáy thùng (pixel). */
    public static int[] x;
    public static int[] y;
    public static int[] state;
    public static int[] vx;
    /** Vận tốc rơi; khi nổi thì là độ lệch nhấp nhô (-1..1). */
    public static int[] vy;
    /** Hướng nhấp nhô khi nổi. */
    public static int[] bobDirection;
    /** Vận tốc trôi ngang khi nổi. */
    public static int[] driftVx;
    public static Image sprite;

    private Crates() {
    }

    /** Đặt thùng cho map. */
    public static final void setup(int mapId) {
        count = 0;
        if (mapId == 1) {
            count = 2;
            alloc();
            place(0, 21, 9);
            place(1, 13, 8);
        }

        if (mapId == 11) {
            count = 2;
            alloc();
            place(0, 33, 7);
            place(1, 44, 10);
        }

        if (mapId == 5) {
            count = 2;
            alloc();
            place(0, 5, 46);
            place(1, 15, 46);
        }

        if (mapId == 97) {
            count = 4;
            alloc();
            place(0, 3, 6);
            place(1, 7, 4);
            place(2, 12, 3);
            place(3, 22, 6);
        }

    }

    private static void alloc() {
        try {
            sprite = Item.ensureCrate(sprite);
        } catch (Exception e) {
        }

        x = new int[count];
        y = new int[count];
        state = new int[count];
        vx = new int[count];
        vy = new int[count];
        bobDirection = new int[count];
        driftVx = new int[count];
    }

    private static void place(int i, int tileX, int tileY) {
        x[i] = tileX * GameMap.TILE_SIZE + 12;
        y[i] = tileY * GameMap.TILE_SIZE + 24;
        state[i] = STATE_RESTING;
    }

}
