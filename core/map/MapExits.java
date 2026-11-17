package core.map;

/**
 * Cửa ra của map hiện tại: đi ra mép map thì chuyển sang map khác.
 * Mỗi cửa gồm map đích (-1 = không có cửa) và vị trí xuất hiện (pixel) ở map đích.
 * Giá trị được gán bởi {@link MapConfig#load(int)}; field nào map không gán thì giữ giá trị cũ.
 */
public final class MapExits {
    /** Không có cửa ra ở hướng này. */
    public static final int NONE = -1;

    /** Đi ra mép trái. */
    public static int leftMap;
    public static int leftSpawnX;
    public static int leftSpawnY;

    /** Đi ra mép phải. */
    public static int rightMap;
    public static int rightSpawnX;
    public static int rightSpawnY;

    /** Đi ra mép phải khi đang ở thấp hơn {@link #rightLowerMinY} (thay cho cửa phải). */
    public static int rightLowerMap;
    public static int rightLowerSpawnX;
    public static int rightLowerSpawnY;
    public static int rightLowerMinY;

    /** Rơi xuống mép dưới. */
    public static int bottomMap;
    public static int bottomSpawnX;
    public static int bottomSpawnY;

    private MapExits() {
    }
}
