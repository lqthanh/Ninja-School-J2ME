package core.monster;

/** Chỉ số và độ lệch ảnh của 7 loại ninja địch. */
public final class NinjaStats {
    public static final int TYPE_COUNT = 7;

    public static final int[] maxHp = new int[]{800, 1000, 1200, 3000, 3500, 4000, 6000};
    /** Sát thương gây cho nhân vật; bình thuốc rơi ra hồi 4 lần giá trị này. */
    public static final int[] damage = new int[]{20, 30, 40, 100, 80, 120, 200};
    /** Tiền rơi ra (cộng thêm 0..9). */
    public static final int[] money = new int[]{150, 180, 180, 300, 350, 380, 500};
    /** Điểm kinh nghiệm khi hạ. */
    public static final int[] exp = new int[]{150, 140, 120, 240, 280, 290, 300};

    /** Độ lệch x của ảnh thân so với chân, theo loại và khung hình. */
    public static final int[][] bodyOffsetX = new int[][]{{-5, -2}, {-2, -4}, {0, -5}, {-6, -6}, {-5, -2}, {-2, -4}, {0, -5}};
    /** Độ lệch y của ảnh thân, theo loại và khung hình. */
    public static final int[][] bodyOffsetY = new int[][]{{5, 8}, {3, 3}, new int[2], {4, 4}, {5, 8}, {3, 3}, new int[2]};
    /** Độ lệch x của ảnh chân, theo loại. */
    public static final int[] legsOffsetX = new int[]{1, -2, 1, 1, 1, -2, 1};

    private NinjaStats() {
    }
}
