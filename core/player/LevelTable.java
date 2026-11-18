package core.player;

/**
 * Bảng dữ liệu tăng trưởng theo cấp độ của nhân vật.
 *
 * Index theo cấp hiện tại:
 *  - EXP_TO_NEXT_LEVEL[level - 1]   : exp cần (vượt quá) để lên cấp tiếp theo
 *  - ATTACK_GAIN[newLevel - 2]      : sức đánh cộng thêm khi đạt newLevel
 *  - MAX_HP_GAIN[newLevel - 2]      : HP tối đa cộng thêm khi đạt newLevel
 *  - MAX_MP_GAIN[newLevel - 2]      : MP tối đa cộng thêm khi đạt newLevel
 */
public final class LevelTable {
    public static final int MIN_LEVEL = 1;

    public static final int[] EXP_TO_NEXT_LEVEL = new int[]{
            50, 150, 240, 400, 600, 800, 1000, 1500, 2000, 3000,
            5000, 7000, 9000, 12000, 15000, 19000, 26000, 32000, 40000, 50000,
            60000, 70000, 80000, 90000, 100000, 100000, 100000, 100000, 100000, 100000,
            100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000,
            100000, 100000, 100000, 100000};

    public static final int[] ATTACK_GAIN = new int[]{
            3, 3, 3, 4, 4, 4, 5, 5, 5, 6,
            6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
            6, 6, 6, 6, 6, 7, 7, 7, 7, 10,
            10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
            10, 10, 10, 10, 10};

    public static final int[] MAX_HP_GAIN = new int[]{
            20, 20, 20, 20, 30, 30, 30, 40, 40, 50,
            60, 70, 80, 80, 90, 90, 100, 120, 130, 150,
            160, 170, 180, 190, 200, 210, 220, 250, 300, 300,
            300, 300, 300, 300, 300, 300, 300, 300, 300, 300,
            300, 300};

    /** MP tối đa cộng thêm khi lên cấp, bằng 1/10 MAX_HP_GAIN. */
    public static final int[] MAX_MP_GAIN = new int[]{
            2, 2, 2, 2, 3, 3, 3, 4, 4, 5,
            6, 7, 8, 8, 9, 9, 10, 12, 13, 15,
            16, 17, 18, 19, 20, 21, 22, 25, 30, 30,
            30, 30, 30, 30, 30, 30, 30, 30, 30, 30,
            30, 30};

    /** Sát thương phi tiêu cộng thêm mỗi lần lên cấp. */
    public static final int DART_DAMAGE_GAIN = 2;

    /**
     * Cấp tối đa mà tất cả các bảng còn dữ liệu.
     * Bản gốc không chặn nên sẽ ArrayIndexOutOfBounds khi vượt cấp 43.
     */
    public static final int MAX_LEVEL = min(
            EXP_TO_NEXT_LEVEL.length,
            min(ATTACK_GAIN.length, min(MAX_HP_GAIN.length, MAX_MP_GAIN.length)) + 1);

    private LevelTable() {
    }

    public static int expToNextLevel(int level) {
        return EXP_TO_NEXT_LEVEL[level - 1];
    }

    public static int attackGain(int newLevel) {
        return ATTACK_GAIN[newLevel - 2];
    }

    public static int maxHpGain(int newLevel) {
        return MAX_HP_GAIN[newLevel - 2];
    }

    public static int maxMpGain(int newLevel) {
        return MAX_MP_GAIN[newLevel - 2];
    }

    private static int min(int a, int b) {
        return a < b ? a : b;
    }
}
