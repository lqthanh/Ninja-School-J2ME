package core.npc;

import assets.Character;
import core.quest.Skills;
import core.text.DialogueText;
import javax.microedition.lcdui.Image;

/**
 * Bạn học đi lại trên map trường, sân tập, làng và trong đoạn mở đầu/tốt nghiệp. Bạn học
 * tự đánh quái ở gần ({@link #targetMonster}); dùng chung tư thế và ảnh thân/chân với
 * nhân vật, chỉ khác ảnh đầu ({@link #skin}).
 *
 * Trận đấu Toniku (map 96) dùng ô 0 của các mảng này làm Toniku (xem {@link Toniku}); khi đó
 * {@link #count} bằng 0 nên vòng cập nhật bạn học bỏ qua ô này. Cập nhật: GameCanvas.updateClassmates().
 */
public final class Classmates {
    // =====================================================================================
    // #region Trạng thái ({@link #state}); các trạng thái đánh nhau chỉ Toniku dùng

    /** Toniku: đứng chờ cho tới khi bị nhân vật tấn công. */
    public static final int STATE_TONIKU_WAITING = 0;
    public static final int STATE_IDLE = 1;
    /** Đi tới {@link #target}. */
    public static final int STATE_WALK = 2;
    /** Toniku: nhảy né lên. */
    public static final int STATE_JUMP = 3;
    /** Toniku: rơi xuống. */
    public static final int STATE_FALL = 4;
    /** Chém liên hoàn ({@link #target} là bước của chuỗi đòn). */
    public static final int STATE_ATTACK = 5;
    /** Toniku: rơi khỏi đáy map. */
    public static final int STATE_FELL_OFF_MAP = 6;
    /** Toniku: bị đánh bật lùi. */
    public static final int STATE_KNOCKBACK = 7;
    /** Toniku: ném phi tiêu. */
    public static final int STATE_THROW_DART = 8;
    /** Toniku: lao tới rồi chém. */
    public static final int STATE_DASH_ATTACK = 24;

    // #endregion
    // =====================================================================================

    public static int count;
    /** Vị trí chân (pixel). */
    public static int[] x;
    public static int[] y;
    public static int[] vx;
    public static int[] vy;
    public static int[] state;
    /** Bộ đếm khung hình. */
    public static int[] animTimer;
    /** Điểm đến khi đi, bộ đếm chờ khi đứng, hoặc bước của chuỗi đòn khi chém. */
    public static int[] target;
    /** Tư thế (chỉ số trong bảng tư thế của nhân vật). */
    public static int[] pose;
    /** Ảnh đầu (chỉ số trong {@link #heads}). */
    public static int[] skin;
    /** Hiệu ứng chém đang vẽ, -1 nếu không có. */
    public static int[] slashEffect;
    /** Hướng nhìn: 1 phải, -1 trái. */
    public static int[] facing;
    /** Quãng đi lang thang (pixel). */
    public static int[] wanderRange;
    /** Quái đang đánh (chỉ số trong Monsters). */
    public static int[] targetMonster;
    /** Giới hạn đi lại (pixel). */
    public static int[] minX;
    public static int[] maxX;
    /** Lời thoại khi nói chuyện. */
    public static String[][] lines;
    /** Ảnh đầu theo skin và khung hình. */
    public static Image[][] heads;

    /** Đứng yên tại chỗ (đoạn mở đầu và lễ tốt nghiệp); được lưu trong save. */
    public static boolean stayPut;
    /** Cảnh diễn: mọi bạn học đi về {@link #gatherX}. */
    public static boolean gathering;
    public static int gatherX;

    private Classmates() {
    }

    /** Đặt bạn học cho map; map không có bạn học thì {@link #count} bằng 0. */
    public static void setup(int mapId) {
        count = 0;
        gathering = false;
        if (mapId == 0) {
            count = 7;
            alloc();
            place(0, 0, 14, 17, 130, 11, 22, DialogueText.schoolClassmate0);
            place(1, 1, 24, 17, 160, 15, 30, DialogueText.schoolClassmate1);
            if (Skills.transform) {
                place(2, 6, 17, 12, 200, 7, 29, DialogueText.schoolClassmate2Assassin);
            } else {
                place(2, 2, 17, 12, 200, 7, 29, DialogueText.schoolClassmate2);
            }

            place(3, 3, 48, 17, 200, 44, 63, DialogueText.schoolClassmate3);
            place(4, 4, 46, 12, 200, 40, 54, DialogueText.schoolClassmate4);
            place(5, 5, 38, 17, 200, 34, 49, DialogueText.schoolClassmate5);
            place(6, 6, 58, 12, 200, 46, 60, DialogueText.schoolClassmate6);
            loadHeads(7);
        }

        if (mapId == 1) {
            count = 3;
            alloc();
            place(0, 5, 35, 9, 40, 33, 36, DialogueText.trainingClassmate0);
            if (Skills.transform) {
                place(1, 6, 25, 10, 40, 22, 28, DialogueText.trainingClassmate1Assassin);
            } else {
                place(1, 2, 25, 10, 40, 22, 28, DialogueText.trainingClassmate1);
            }

            place(2, 1, 6, 9, 40, 2, 10, DialogueText.trainingClassmate2);
            loadHeads(7);
        }

        if (mapId == 2) {
            count = 7;
            alloc();
            place(0, 3, 16, 12, 60, 14, 21, DialogueText.villageClassmate0);
            place(1, 4, 8, 12, 50, 2, 15, DialogueText.villageClassmate1);
            if (Skills.transform) {
                place(2, 6, 29, 13, 200, 27, 40, DialogueText.villageClassmate2Assassin);
            } else {
                place(2, 2, 29, 13, 200, 27, 40, DialogueText.villageClassmate2);
            }

            place(3, 5, 46, 13, 200, 41, 49, DialogueText.villageClassmate3);
            place(4, 1, 46, 11, 200, 43, 53, DialogueText.villageClassmate4);
            place(5, 0, 54, 11, 200, 50, 58, DialogueText.villageClassmate5);
            place(6, 6, 34, 13, 200, 28, 48, DialogueText.villageClassmate6);
            loadHeads(7);
        }

        String[] sharedLines;
        if (mapId == 99 || mapId == 101) {
            count = 7;
            alloc();
            sharedLines = DialogueText.tutorialClassmates;
            place(0, 0, 8, 13, 0, 0, 1000, sharedLines);
            place(1, 1, 5, 13, 0, 0, 1000, sharedLines);
            place(2, 6, 6, 13, 0, 0, 1000, sharedLines);
            place(3, 2, 7, 13, 0, 0, 1000, sharedLines);
            place(4, 4, 6, 13, 0, 0, 1000, sharedLines);
            place(5, 5, 5, 13, 0, 0, 1000, sharedLines);
            place(6, 3, 8, 13, 0, 0, 1000, sharedLines);
            loadHeads(7);
        }

        if (mapId == 94) {
            count = 7;
            alloc();
            sharedLines = DialogueText.graduationClassmates;
            place(0, 0, 5, 12, 0, 0, 1000, sharedLines);
            place(1, 1, 5, 12, 0, 0, 1000, sharedLines);
            place(2, 6, 6, 12, 0, 0, 1000, sharedLines);
            place(3, 7, 3, 12, 0, 0, 1000, sharedLines);
            place(4, 4, 6, 12, 0, 0, 1000, sharedLines);
            place(5, 5, 5, 12, 0, 0, 1000, sharedLines);
            place(6, 3, 8, 12, 0, 0, 1000, sharedLines);
            loadHeads(8);
        }

    }

    static void alloc() {
        x = new int[count];
        y = new int[count];
        vx = new int[count];
        vy = new int[count];
        state = new int[count];
        animTimer = new int[count];
        facing = new int[count];
        target = new int[count];
        pose = new int[count];
        skin = new int[count];
        slashEffect = new int[count];
        wanderRange = new int[count];
        targetMonster = new int[count];
        minX = new int[count];
        maxX = new int[count];
        lines = new String[count][];
    }

    static void loadHeads(int skinCount) {
        if (heads == null) {
            heads = new Image[skinCount][2];

            for(int s = 0; s < skinCount; ++s) {
                for(int f = 0; f < 2; ++f) {
                    try {
                        heads[s][f] = Character.loadClassmateHead(s, f);
                    } catch (Exception e) {
                    }
                }
            }
        }

    }

    /**
     * Đặt bạn học i (dùng ảnh đầu skinId) ở ô (tileX, tileY), đi lang thang trong cột
     * [minTileX, maxTileX] với quãng đường khoảng range pixel; npcLines là lời thoại.
     */
    static void place(int i, int skinId, int tileX, int tileY, int range, int minTileX, int maxTileX, String[] npcLines) {
        x[i] = tileX * 24 + (i << 2);
        y[i] = tileY * 24;
        vx[i] = 0;
        vy[i] = 0;
        state[i] = STATE_IDLE;
        facing[i] = -1;
        animTimer[i] = 0;
        target[i] = (i + 1) * 20 + i;
        pose[i] = 0;
        skin[i] = skinId;
        slashEffect[i] = -1;
        targetMonster[i] = -1;
        minX[i] = minTileX * 24 + 12;
        maxX[i] = maxTileX * 24 + 12;
        wanderRange[i] = range;
        lines[i] = npcLines;
    }
}
