package core.player;

/** Hành động hiện tại của nhân vật ({@link Player#action}); {@link PlayerController#update()} cập nhật theo hành động. */
public final class PlayerAction {
    /** Đứng yên (hiện gợi ý nói chuyện khi đứng gần NPC, bạn học, cô gái). */
    public static final int STAND = 1;
    public static final int RUN = 2;
    /** Đang bay lên khi nhảy. */
    public static final int JUMP = 3;
    /** Đang rơi. */
    public static final int FALL = 4;
    /** Đang đánh (kiếm hoặc phi tiêu, xem {@link Player#weapon}). */
    public static final int ATTACK = 5;
    /** Chết: văng ra rồi hồi sinh ở {@link Player#checkpointX}, {@link Player#checkpointY}. */
    public static final int DEAD = 6;
    /** Bị trúng đòn, bật lùi. */
    public static final int HURT = 7;
    /** Ngồi (giữ phím xuống); đứng trên đất đào được thì độn thổ. */
    public static final int CROUCH = 8;
    public static final int UNUSED_9 = 9;
    public static final int UNUSED_10 = 10;
    /** Chạy trên mặt nước (kỹ năng, tốn MP). */
    public static final int RUN_ON_WATER = 11;
    /** Chìm xuống nước. */
    public static final int SINK = 12;
    /** Bắt đầu độn thổ. */
    public static final int BURROW_START = 13;
    /** Đang ở dưới đất (tốn MP; hết MP thì trồi lên). */
    public static final int BURROWED = 14;
    /** Trồi lên khỏi mặt đất. */
    public static final int BURROW_EXIT = 15;
    /** Đi dưới đáy nước (kỹ năng lặn). */
    public static final int UNDERWATER_WALK = 16;
    public static final int UNDERWATER_STAND = 17;
    /** Ngồi trên mô tô đứng yên. */
    public static final int MOTORBIKE_STAND = 18;
    public static final int MOTORBIKE_RIDE = 19;
    public static final int MOTORBIKE_FALL = 20;
    public static final int MOTORBIKE_JUMP = 21;
    public static final int UNUSED_22 = 22;
    /** Đang biến hình. */
    public static final int TRANSFORM = 23;

    private PlayerAction() {
    }
}
