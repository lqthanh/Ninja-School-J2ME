package core.item;

/**
 * Tài sản của người chơi: tiền, số bình thuốc và cấp kiếm đang dùng.
 *
 * Bình thuốc được đánh chỉ số 0..5: 0-2 là bình HP (nhỏ, vừa, to), 3-5 là bình MP.
 */
public final class Inventory {
    public static final int POTION_HP_SMALL = 0;
    public static final int POTION_HP_MEDIUM = 1;
    public static final int POTION_HP_LARGE = 2;
    public static final int POTION_MP_SMALL = 3;
    public static final int POTION_MP_MEDIUM = 4;
    public static final int POTION_MP_LARGE = 5;
    public static final int POTION_COUNT = 6;

    public static int money;

    /** Cấp kiếm đang dùng (0 = kiếm tre ... 3 = long giao kiếm). */
    public static int swordLevel;

    /** Số bình mỗi loại đang có (giá trị khi bắt đầu game mới). */
    public static int[] potionCounts = new int[]{5, 2, 1, 5, 2, 1};

    public static String[] potionNames = new String[]{"Bình HP nhỏ",
            "Bình HP vừa",
            "Bình HP to",
            "Bình MP nhỏ",
            "Bình MP vừa",
            "Bình MP to"};

    /** Lượng HP/MP mỗi loại bình hồi lại. */
    public static int[] potionRestoreAmounts = new int[]{100, 400, 1000, 100, 200, 300};

    private Inventory() {
    }
}
