package core.app;

/**
 * Các màn hình / trạng thái chính của game ({@link #current}).
 * Mỗi frame, GameCanvas.run() cập nhật và GameCanvas.paint() vẽ theo trạng thái hiện tại.
 */
public final class GameState {
    /** Đang chơi: cập nhật nhân vật, quái, map và vẽ HUD. */
    public static final int PLAYING = 1;
    /** Màn hình "Loading... x%" khi Midlet.run() nạp tài nguyên ở thread riêng. */
    public static final int LOADING = 2;
    /** Màn hình chính: Chơi mới / Chơi tiếp / Chỉ dẫn / Game khác / Thoát. */
    public static final int MAIN_MENU = 3;
    /** Logo nhà phát hành lúc khởi động. */
    public static final int LOGO = 4;
    /** Menu trong game (phím mềm trái): Sử dụng item / Xem bản đồ / Lưu và thoát / Nhạc. */
    public static final int GAME_MENU = 5;
    /** Bản đồ thế giới, chọn nơi để chuyển đến. */
    public static final int WORLD_MAP = 6;
    /** Hộp thoại thông báo. */
    public static final int MESSAGE = 7;
    /** Túi đồ: chọn item để sử dụng. */
    public static final int INVENTORY = 8;
    /** Cửa hàng. */
    public static final int SHOP = 9;
    /** Hỏi bật âm thanh và chất lượng hình ảnh lúc khởi động. */
    public static final int SETUP_PROMPT = 10;
    /** Màn chơi hướng dẫn ("Chỉ dẫn" ở màn hình chính, map 101), nhân vật tự chạy theo kịch bản. */
    public static final int TUTORIAL = 11;
    /** Xử lý giống TUTORIAL nhưng không có chỗ nào chuyển sang trạng thái này. */
    public static final int UNUSED_12 = 12;

    /** Trạng thái hiện tại, một trong các hằng số ở trên. */
    public static int current;
    /** Trạng thái chuyển tới khi màn hình tải xong. */
    public static int next;
    public static int tick;

    private GameState() {
    }
}
