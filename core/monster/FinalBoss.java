package core.monster;

import assets.Character;
import assets.Mob;
import core.quest.Story;
import javax.microedition.lcdui.Image;

/**
 * Boss cuối ở map 95 (giải cứu Sakura). Thân gồm 6 phần vẽ riêng: phần i nằm ở
 * ({@link #x} + {@link #partBaseX}[i] + {@link #poseX}[i], {@link #y} + {@link #partBaseY}[i] + {@link #poseY}[i]),
 * lật theo {@link #direction}. Bảng tư thế idle/attack/walk cho độ lệch của từng phần.
 * Khi bị hạ, các phần văng ra xa nhau. AI: {@link BossAI#updateFinalBoss()}.
 */
public final class FinalBoss {
    /** Chờ cảnh diễn bắt đầu trận đấu. */
    public static final int STATE_WAITING = 0;
    /** Đứng, chọn đòn theo khoảng cách tới nhân vật. */
    public static final int STATE_IDLE = 1;
    /** Đi tới {@link #targetX}. */
    public static final int STATE_WALK = 2;
    /** Chém gần. */
    public static final int STATE_MELEE = 5;
    /** Bị hạ: các phần văng ra trong 50 frame. */
    public static final int STATE_DYING = 6;
    /** Ném phi tiêu. */
    public static final int STATE_THROW_DARTS = 8;
    /** Đã bị hạ. */
    public static final int STATE_DEFEATED = 22;
    /** Bắn đạn liên tục. */
    public static final int STATE_SHOOT = 23;
    /** Lao nhanh tới {@link #targetX} rồi chém. */
    public static final int STATE_DASH = 24;

    public static final int PART_COUNT = 6;

    public static boolean active;
    public static int damage;
    /** Kích thước dùng để vẽ thanh máu. */
    public static int width;
    public static int height;
    public static int hp;
    public static int maxHp;
    /** Còn bao nhiêu frame hiện thanh máu. */
    public static int healthBarTimer;
    public static int x;
    public static int y;
    public static int state;
    /** Hướng: 1 phải, -1 trái. */
    public static int direction;
    /** Giới hạn di chuyển (pixel). */
    public static int minX;
    public static int maxX;
    public static int timer;
    public static int targetX;
    /** Bảng tư thế [khung][phần]: đứng (2 khung), đánh (2 khung), đi (3 khung). */
    public static int[][] idlePoseX;
    public static int[][] idlePoseY;
    public static int[][] attackPoseX;
    public static int[][] attackPoseY;
    public static int[][] walkPoseX;
    public static int[][] walkPoseY;
    /** Vị trí vẽ của từng phần, tính lại mỗi frame. */
    public static int[] partX;
    public static int[] partY;
    /** Vị trí gốc của từng phần so với thân (thay đổi khi bị hạ). */
    public static int[] partBaseX;
    public static int[] partBaseY;
    /** Khung tư thế hiện tại (một dòng của các bảng tư thế). */
    public static int[] poseX;
    public static int[] poseY;
    /** Ảnh 6 phần; phần 5 là đầu Toniku. */
    public static Image[] sprites;

    private FinalBoss() {
    }

    public static void setup(int mapId) {
        active = false;
        if (mapId == 95 && !Story.sakuraFound) {
            active = true;
            sprites = new Image[6];

            try {
                sprites[0] = Mob.loadBossPart(2, 0);
                sprites[1] = Mob.loadBossPart(2, 1);
                sprites[2] = Mob.loadBossPart(2, 2);
                sprites[3] = sprites[1];
                sprites[4] = Mob.loadBossPart(2, 3);
                sprites[5] = Character.loadClassmateHead(2, 0);
            } catch (Exception e) {
            }

            x = 192;
            y = 264;
            minX = 120;
            maxX = 504;
            state = STATE_WAITING;
            direction = -1;
            maxHp = 90000;
            hp = 90000;
            partX = new int[6];
            partY = new int[6];
            poseX = new int[6];
            poseY = new int[6];
            partBaseX = new int[]{20, 10, 0, -10, -16, 2};
            partBaseY = new int[]{-16, 0, -12, 0, -18, -42};
            idlePoseX = new int[][]{new int[6], {0, 0, 1, 0, 1, 1}};
            idlePoseY = new int[][]{new int[6], {1, 0, 1, 0, 2, 1}};
            attackPoseX = new int[][]{{6, -1, 1, 1, 5, 1}, {14, -1, 2, 1, 12, 2}};
            attackPoseY = new int[][]{{1, 0, 0, 0, 1, 0}, {2, 0, 1, 0, 2, 1}};
            walkPoseX = new int[][]{{-3, 3, 0, -3, 3, 0}, new int[6], {3, -3, 0, 3, -3, 0}};
            walkPoseY = new int[][]{{-1, -2, 1, 0, 1, 1}, new int[6], {1, 0, 1, -2, -1, 1}};
        }
    }
}
