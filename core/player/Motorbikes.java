package core.player;

import assets.Effect;
import core.map.GameMap;
import javax.microedition.lcdui.Image;

/**
 * Xe mô tô trên map hiện tại. Mỗi map có tối đa một xe đỗ; nếu nhân vật đang cưỡi xe
 * ({@link Player#motorbike} khác -1) thì xe đó được mang sang map mới. Khi đang cưỡi,
 * vị trí xe bám theo nhân vật; khi xuống xe giữa không trung, xe rơi ({@link #falling}).
 */
public final class Motorbikes {
    public static int count;
    public static int[] x;
    public static int[] y;
    /** Hướng xe: 1 phải, -1 trái. */
    public static int[] facing;
    /** Khung hình (0..2). */
    public static int[] frame;
    /** 1 nếu xe đang rơi. */
    public static int[] falling;
    public static int[] vy;
    public static Image[] sprites;

    private Motorbikes() {
    }

    /** Đặt xe cho map. */
    public static final void setup(int mapId) {
        count = 0;
        int riding = Player.motorbike == -1 ? 0 : 1;
        if ((mapId == 0 || mapId == 1 || mapId == 7 || mapId == 5 || mapId == 18 || mapId >= 94) && riding == 1) {
            count = 1;
            alloc();
            place(0, Player.x, Player.y);
            Player.motorbike = 0;
            facing[Player.motorbike] = Player.facing;
        }

        if (mapId == 2) {
            setupWithRidden(riding, 18, 12);
        }

        if (mapId == 3) {
            setupWithRidden(riding, 2, 9);
        }

        if (mapId == 4) {
            setupWithRidden(riding, 3, 6);
        }

        if (mapId == 6) {
            setupWithRidden(riding, 12, 9);
        }

        if (mapId == 8) {
            setupWithRidden(riding, 3, 12);
        }

        if (mapId == 9) {
            setupWithRidden(riding, 2, 11);
        }

        if (mapId == 10) {
            setupWithRidden(riding, 3, 12);
        }

        if (mapId == 11) {
            setupWithRidden(riding, 5, 6);
        }

        if (mapId == 12) {
            setupWithRidden(riding, 4, 8);
        }

        if (mapId == 13) {
            setupWithRidden(riding, 16, 11);
        }

        if (mapId == 14) {
            setupWithRidden(riding, 18, 7);
        }

        if (mapId == 15) {
            setupWithRidden(riding, 1, 11);
        }

        if (mapId == 16) {
            setupWithRidden(riding, 7, 11);
        }

        if (mapId == 17) {
            setupWithRidden(riding, 14, 12);
        }

    }

    /** Đặt một xe đỗ ở ô (tileX, tileY); nếu đang cưỡi xe thì thêm xe đang cưỡi làm xe thứ 2. */
    private static void setupWithRidden(int riding, int tileX, int tileY) {
        count = riding + 1;
        alloc();
        place(0, tileX, tileY);
        if (riding == 1) {
            place(1, Player.x, Player.y);
            Player.motorbike = 1;
            facing[Player.motorbike] = Player.facing;
        }

    }

    private static void alloc() {
        try {
            sprites = Effect.ensureMotorbike(sprites);
        } catch (Exception e) {
        }

        x = new int[count];
        y = new int[count];
        facing = new int[count];
        frame = new int[count];
        falling = new int[count];
        vy = new int[count];
    }

    /**
     * Đặt xe i ở ô (tileX, tileY). Khi mang xe đang cưỡi sang map mới, hàm được gọi với tọa độ
     * pixel của nhân vật (lỗi gốc, vô hại vì vị trí xe bám theo nhân vật mỗi frame).
     */
    private static void place(int i, int tileX, int tileY) {
        x[i] = tileX * GameMap.TILE_SIZE + 12;
        y[i] = tileY * GameMap.TILE_SIZE;
        frame[i] = 0;
        facing[i] = 1;
    }
}
