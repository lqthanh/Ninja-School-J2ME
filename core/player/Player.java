package core.player;

/**
 * Trạng thái của nhân vật chính: vị trí, vận tốc, hành động, hộp va chạm...
 * Chỉ số (HP, MP, cấp...) nằm ở {@link PlayerStats}. Cập nhật ở {@link PlayerController#update()}.
 */
public final class Player {
    // =====================================================================================
    // #region Bước của chuỗi đòn kiếm ({@link #comboStep})

    /** Nhát chém thứ nhất; nhấn đánh tiếp thì sang {@link #COMBO_SECOND_SLASH}, không thì {@link #COMBO_FIRST_SLASH_END}. */
    public static final int COMBO_FIRST_SLASH = 0;
    /** Giữ thế sau nhát thứ nhất 2 frame rồi sang {@link #COMBO_RECOVER}. */
    public static final int COMBO_FIRST_SLASH_END = 1;
    /**
     * Nhát chém thứ hai. Nhấn đánh tiếp (tối đa 2 lần nối): đang đứng đất và đòn trước hất trúng quái
     * thì sang {@link #COMBO_LAUNCH_SLASH}, còn lại sang {@link #COMBO_THIRD_SLASH}.
     */
    public static final int COMBO_SECOND_SLASH = 2;
    /** Nhát chém thứ ba (vung ngược); nhấn đánh tiếp thì quay lại {@link #COMBO_SECOND_SLASH}. */
    public static final int COMBO_THIRD_SLASH = 3;
    /** Chém hất quái lên không; trúng thì nhân vật nhảy theo, trượt thì sang {@link #COMBO_RECOVER}. */
    public static final int COMBO_LAUNCH_SLASH = 9;
    /** Thu kiếm, xong thì về đứng hoặc rơi. */
    public static final int COMBO_RECOVER = 10;

    // #endregion
    // =====================================================================================

    /** Vị trí chân (pixel trên map). */
    public static int x;
    public static int y;
    public static int vx;
    /** Vận tốc dọc (âm = bay lên). */
    public static int vy;
    /** Hướng nhìn: 1 phải, -1 trái. */
    public static int facing;

    /** Hành động hiện tại ({@link PlayerAction}). */
    public static int action;
    /** Tư thế (khung hình) đang vẽ. */
    public static int pose;
    /** Bộ đếm của hành động hiện tại. */
    public static int actionTimer;
    /** Bộ đếm phụ của hành động hiện tại. */
    public static int actionTimer2;

    /** Vũ khí đang dùng: 0 kiếm, 1 phi tiêu. */
    public static int weapon;
    /** Hiệu ứng chém đang vẽ (chỉ số trong HeroSprites.slashEffects), -1 nếu không có. */
    public static int slashEffect;
    /** Bước của chuỗi đòn đánh (COMBO_*). */
    public static int comboStep;
    /** Số đòn đã nối trong chuỗi. */
    public static int comboCount;
    /** Đã nhấn đánh tiếp trong lúc đang đánh. */
    public static boolean comboQueued;
    /** Đòn chém hất vừa trúng quái. */
    public static boolean launchHit;

    /** Kích thước hộp va chạm và các nửa / phần tư của nó. */
    public static int width;
    public static int height;
    public static int halfWidth;
    public static int halfHeight;
    public static int quarterWidth;
    public static int quarterHeight;

    /** Điểm hồi sinh khi chết (vị trí vào map). */
    public static int checkpointX;
    public static int checkpointY;

    /** Tốc độ chạy cộng thêm khi có kỹ năng chạy nhanh. */
    public static int sprintBonus;
    /** Tốc độ di chuyển trên mặt đất / trên không. */
    public static int moveSpeed;
    /** Tốc độ khi chạy trên mặt nước. */
    public static int runOnWaterSpeed;
    /** Tốc độ khi đi dưới đáy nước. */
    public static int underwaterSpeed;
    /** Tốc độ khi đi mô tô. */
    public static int motorbikeSpeed;

    /** Đếm từ -20 lên 0 sau khi bị trúng đòn; bản gốc không dùng giá trị này ở đâu. */
    public static int hurtRecovery;

    /** Đang ẩn thân (kỹ năng, tốn MP). */
    public static boolean hidden;
    /** Đang biến hình (tốn MP). */
    public static boolean transformed;

    /** NPC / bạn học đang đứng gần để nói chuyện, -1 nếu không có. */
    public static int nearNpc;
    public static int nearClassmate;
    /** Đang đứng gần cô gái để nói chuyện. */
    public static boolean nearGirl;

    /** Mô tô đang cưỡi, -1 nếu không cưỡi. */
    public static int motorbike = -1;
    /** Thùng gỗ (đẩy được) đang đứng trên, -1 nếu không có. */
    public static int crate = -1;

    private Player() {
    }
}
