package core.ui;

/**
 * Loại hộp thoại thông báo (b.showMessage). Loại quyết định nội dung mặc định,
 * nhãn 2 phím mềm và màn hình quay về khi đóng hộp thoại.
 */
public final class MessageType {
    /** Bản đồ: chưa từng đi bộ đến nơi này. OK quay về bản đồ. */
    public static final int TRAVEL_NOT_VISITED = 1;
    /** Bản đồ: hỏi trả tiền để chuyển đến nơi đã chọn. */
    public static final int TRAVEL_CONFIRM = 2;
    /** Bản đồ: đang ở đúng nơi đã chọn. OK quay về bản đồ. */
    public static final int TRAVEL_ALREADY_HERE = 3;
    /** Không đủ tiền chuyển map. OK quay về bản đồ. */
    public static final int NOT_ENOUGH_MONEY = 4;
    /** Thông báo trong lúc chơi (mặc định: bị trọng thương, mất EXP). OK quay về game. */
    public static final int GAME_NOTICE = 5;
    /** Lên cấp. OK quay về game. */
    public static final int LEVEL_UP = 6;
    /** Thông báo của cửa hàng (phím mềm phải). OK quay về cửa hàng. */
    public static final int SHOP_NOTICE = 7;

    private MessageType() {
    }
}
