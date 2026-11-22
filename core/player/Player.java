package core.player;

/**
 * Trạng thái của nhân vật chính: vị trí, vận tốc, hành động, hộp va chạm...
 * Chỉ số (HP, MP, cấp...) nằm ở {@link PlayerStats}. Cập nhật ở b.R().
 */
public final class Player {
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
    /** Hiệu ứng chém đang vẽ (chỉ số trong b.slashEffects), -1 nếu không có. */
    public static int slashEffect;
    /** Bước của chuỗi đòn đánh. */
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
