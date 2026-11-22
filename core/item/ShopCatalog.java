package core.item;

/**
 * Bảng hàng của cửa hàng. Hàng 0 là 6 loại bình thuốc (cùng thứ tự với
 * {@link Inventory#potionCounts}), hàng 1 là 4 cấp kiếm (chỉ số = cấp kiếm).
 */
public final class ShopCatalog {
    public static final int ROW_POTIONS = 0;
    public static final int ROW_SWORDS = 1;

    public static String[][] itemNames = new String[][]{{"Bình HP nhỏ",
            "Bình HP vừa",
            "Bình HP to",
            "Bình MP nhỏ",
            "Bình MP vừa",
            "Bình MP to"},
        {"Kiếm tre",
            "Thủy linh kiếm",
            "Hỏa giác kiếm",
            "Long giao kiếm"}};

    /** Giá ở cửa hàng tiền thật (SMS); chỉ còn để hiển thị. */
    public static String[][] smsPrices = new String[][]{{"2000vnđ / 50 bình",
            "3000vnđ / 50 bình",
            "3000vnđ / 30 bình",
            "2000vnđ / 50 bình",
            "2000vnđ / 30 bình",
            "3000vnđ / 50 bình"},
        {"2000vnđ",
            "3000vnđ",
            "3000vnđ",
            "5000vnđ"}};

    /** Giá bằng tiền trong game. */
    public static int[][] prices = new int[][]{{200,
            600,
            1500,
            200,
            600,
            1200},
        {2000,
            12000,
            45000,
            182000}};

    /** Cấp độ nhân vật tối thiểu để mua từng cấp kiếm. */
    public static int[] swordLevelRequirements = new int[]{0, 10, 15, 20};

    private ShopCatalog() {
    }
}
