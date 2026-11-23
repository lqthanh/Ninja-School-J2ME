package core.monster;

import assets.Mob;
import javax.microedition.lcdui.Image;

/**
 * Ninja địch trên map hiện tại (chỉ có ở map 9, 10, 11, 16, 17, 18). Mỗi ninja là một chỉ số i
 * trong các mảng song song bên dưới. Ninja đi tuần trong đoạn [{@link #minX}, {@link #maxX}],
 * thấy nhân vật thì lao tới và tấn công: loại 0/4 ném phi tiêu, 1/5 ném đạn lửa, 2/3/6 chém gần.
 * Ảnh gồm chân ({@link #legSprites}) và thân ({@link #bodySprites}) vẽ riêng; khi bị hạ, thân văng
 * ra khỏi chân. AI nằm ở b.updateNinjas().
 */
public final class Ninjas {
    // =====================================================================================
    // #region Trạng thái ({@link #state})

    /** Bị hạ: chân và thân văng ra rồi rơi khỏi màn hình. */
    public static final int STATE_KNOCKED_OUT = 1;
    /** Đi bộ tới {@link #targetX}. */
    public static final int STATE_WALK = 2;
    /** Đứng tại chỗ; sau 50 frame chọn điểm đến mới. */
    public static final int STATE_IDLE = 3;
    /** Tấn công (ném hoặc chém ở frame thứ 3). */
    public static final int STATE_ATTACK = 4;
    /** Đã chết, chờ khoảng 550 frame rồi hồi sinh ở điểm xuất hiện. */
    public static final int STATE_DEAD = 5;
    /** Bị trúng đòn; sau 5 frame lùi ra xa nhân vật. */
    public static final int STATE_HURT = 6;
    /** Chạy nhanh tới {@link #targetX}. */
    public static final int STATE_DASH = 7;

    // #endregion
    // =====================================================================================

    /** Số ninja trên map. */
    public static int count;
    /** Loại ninja (chỉ số trong NinjaStats). */
    public static int[] type;
    public static int[] state;
    /** Bộ đếm frame của trạng thái hiện tại. */
    public static int[] timer;
    public static int[] hp;
    /** Còn bao nhiêu frame hiện thanh máu (đặt 100 khi bị trúng đòn). */
    public static int[] healthBarTimer;
    /** Điểm xuất hiện (pixel). */
    public static int[] spawnX;
    public static int[] spawnY;
    /** Vị trí chân (pixel). */
    public static int[] x;
    public static int[] y;
    /** Vị trí ảnh thân (tách khỏi chân khi bị hạ). */
    public static int[] bodyX;
    public static int[] bodyY;
    /** Vận tốc của chân. */
    public static int[] vx;
    public static int[] vy;
    /** Vận tốc của thân khi bị hạ. */
    public static int[] bodyVx;
    public static int[] bodyVy;
    /** Độ nhún của thân (0 hoặc 1) khi đi/đứng. */
    public static int[] bob;
    /** Điểm đến khi đi bộ hoặc lao tới. */
    public static int[] targetX;
    /** Hướng nhìn: 1 phải, -1 trái. */
    public static int[] direction;
    /** Khung hình chân (0..3) và thân (0..1). */
    public static int[] legsFrame;
    public static int[] bodyFrame;
    /** Giới hạn đi tuần (pixel). */
    public static int[] minX;
    public static int[] maxX;

    /** Loại ninja có mặt trên map, để chỉ nạp ảnh cần dùng. */
    public static boolean[] typesUsed;
    /** Ảnh chân (chung cho mọi loại) và ảnh thân theo loại và khung hình. */
    public static Image[] legSprites;
    public static Image[][] bodySprites;

    private Ninjas() {
    }

    public static void releaseSprites() {
        legSprites = null;
        bodySprites = null;
        System.gc();
    }

    /** Đặt ninja cho map; map không có ninja thì {@link #count} bằng 0. */
    public static void setup(int mapId) {
        count = 0;
        if (mapId == 9) {
            count = 6;
            typesUsed = new boolean[]{false, false, true, false, false, false, false};
            alloc();
            place(0, 10, 12, 9, 12, 2);
            place(1, 7, 8, 6, 8, 2);
            place(2, 30, 8, 27, 33, 2);
            place(3, 29, 12, 27, 33, 2);
            place(4, 45, 5, 42, 50, 2);
            place(5, 64, 9, 62, 66, 2);
        }

        if (mapId == 10) {
            count = 12;
            typesUsed = new boolean[]{true, false, true, false, false, false, false};
            alloc();
            place(0, 5, 8, 4, 6, 2);
            place(1, 6, 12, 5, 9, 0);
            place(2, 16, 12, 9, 21, 2);
            place(3, 28, 15, 26, 29, 0);
            place(4, 25, 15, 22, 27, 2);
            place(5, 16, 25, 14, 17, 0);
            place(6, 4, 29, 2, 6, 0);
            place(7, 8, 38, 2, 28, 2);
            place(8, 14, 38, 2, 28, 2);
            place(9, 20, 38, 2, 28, 2);
            place(10, 31, 30, 28, 37, 2);
            place(11, 33, 30, 28, 37, 0);
        }

        if (mapId == 11) {
            count = 8;
            typesUsed = new boolean[]{true, true, true, false, false, false, false};
            alloc();
            place(0, 6, 11, 3, 7, 1);
            place(1, 19, 12, 18, 20, 0);
            place(2, 33, 8, 28, 36, 2);
            place(3, 53, 11, 53, 54, 1);
            place(5, 54, 11, 53, 54, 0);
            place(4, 68, 12, 62, 79, 2);
            place(6, 71, 12, 70, 72, 0);
            place(7, 72, 8, 70, 73, 1);
        }

        if (mapId == 16) {
            count = 4;
            typesUsed = new boolean[]{false, false, false, true, false, false, false};
            alloc();
            place(0, 5, 11, 3, 10, 3);
            place(1, 26, 12, 24, 29, 3);
            place(2, 47, 10, 30, 55, 3);
            place(3, 60, 11, 56, 64, 3);
        }

        if (mapId == 17) {
            count = 8;
            typesUsed = new boolean[]{false, false, false, true, true, false, false};
            alloc();
            place(0, 11, 8, 9, 13, 3);
            place(1, 20, 6, 18, 21, 4);
            place(2, 20, 11, 18, 21, 3);
            place(3, 26, 3, 24, 29, 3);
            place(4, 26, 8, 24, 29, 4);
            place(5, 26, 13, 24, 29, 4);
            place(6, 33, 6, 32, 35, 3);
            place(7, 33, 10, 32, 35, 4);
        }

        if (mapId == 18) {
            count = 8;
            typesUsed = new boolean[]{false, false, false, false, true, true, true};
            alloc();
            place(0, 2, 8, 1, 4, 5);
            place(1, 2, 12, 1, 5, 6);
            place(2, 18, 12, 12, 23, 6);
            place(3, 18, 8, 15, 21, 5);
            place(4, 42, 9, 38, 45, 4);
            place(5, 42, 13, 34, 67, 6);
            place(6, 62, 13, 34, 67, 6);
            place(7, 60, 9, 57, 64, 4);
        }

    }

    private static void alloc() {
        if (legSprites == null) {
            try {
                legSprites = Mob.loadNinjaLegs();
                bodySprites = Mob.loadNinjaBodies(typesUsed);
            } catch (Exception e) {
            }
        }

        spawnX = new int[count];
        spawnY = new int[count];
        x = new int[count];
        y = new int[count];
        bodyX = new int[count];
        bodyY = new int[count];
        type = new int[count];
        state = new int[count];
        timer = new int[count];
        targetX = new int[count];
        direction = new int[count];
        legsFrame = new int[count];
        bodyFrame = new int[count];
        bob = new int[count];
        minX = new int[count];
        maxX = new int[count];
        vx = new int[count];
        vy = new int[count];
        bodyVx = new int[count];
        bodyVy = new int[count];
        hp = new int[count];
        healthBarTimer = new int[count];
    }

    /** Đặt ninja i đứng ở ô (tileX, tileY), đi tuần trong cột [minTileX, maxTileX]. */
    private static void place(int i, int tileX, int tileY, int minTileX, int maxTileX, int ninjaType) {
        spawnX[i] = tileX * 24 + 12;
        spawnY[i] = tileY * 24;
        x[i] = spawnX[i];
        y[i] = spawnY[i];
        bodyX[i] = x[i];
        bodyY[i] = y[i] - 9;
        type[i] = ninjaType;
        state[i] = STATE_IDLE;
        timer[i] = i << 1;
        targetX[i] = 0;
        direction[i] = 1;
        legsFrame[i] = 0;
        bodyFrame[i] = 0;
        bob[i] = 0;
        minX[i] = minTileX * 24 + 12;
        maxX[i] = maxTileX * 24 + 12;
        hp[i] = NinjaStats.maxHp[type[i]];
    }
}
