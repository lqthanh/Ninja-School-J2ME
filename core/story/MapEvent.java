package core.story;

/**
 * Sự kiện của map: dãy bước chờ điều kiện rồi phát cutscene. Mỗi map có sự kiện dùng
 * chỉ số b.mapEventId; trình chạy là {@code b.updateMapEvent()}.
 */
public final class MapEvent {
    /** Chờ nhân vật đi qua x = {@link #paramX}. */
    public static final int WAIT_PLAYER_PAST_X = 1;
    /** Phát ngay. */
    public static final int IMMEDIATE = 2;
    /** Hết sự kiện. */
    public static final int END = 3;
    /** Chờ nhân vật ra khỏi mép phải màn hình. */
    public static final int WAIT_PLAYER_OFFSCREEN_RIGHT = 4;
    /** Chờ boss rừng trúc bị hạ. */
    public static final int WAIT_BAMBOO_BOSS_DEFEATED = 5;
    /**
     * Chờ nhân vật đứng trong khoảng (paramX - 96, paramX) và ở đúng y = {@link #paramY}.
     *
     * <p>LỖI GỐC (giữ nguyên, chưa sửa): thiếu break nên rơi xuống kiểm tra luôn điều kiện
     * {@link #WAIT_BAMBOO_BOSS_DEFEATED} (b.gS == 22). Chỉ sự kiện 4 dùng điều kiện này: về
     * map 0 cùng em gái cô Ayumi, tới chỗ thầy (x 1152..1248, y 408) thì phát cutscene 9
     * (khen, cô bé đứng lại, nhận bằng lái mô tô). Vì b.gS không được reset khi rời map
     * boss và không được lưu vào save:
     * <ul>
     * <li>Đã hạ boss rừng trúc trong lần chạy này (ví dụ chơi liền từ "Chơi mới"): cutscene 9
     * phát ngay frame đầu khi vào map 0, không cần tới chỗ thầy.</li>
     * <li>Chưa hạ boss nào trong lần chạy này (vào bằng "Chơi tiếp"): chạy đúng thiết kế.</li>
     * <li>Hiếm: gS == 22 và nhân vật đang ở đúng chỗ thầy thì cutscene 9 bị thay bằng
     * cutscene 0, bước sự kiện vượt quá độ dài mảng (có thể ArrayIndexOutOfBounds).</li>
     * </ul>
     * Sửa: thêm break (hoặc return) sau nhánh này trong b.updateMapEvent().</p>
     */
    public static final int WAIT_PLAYER_AT = 6;
    /** Chờ thủy quái chìm xuống (bị hạ). */
    public static final int WAIT_RIVER_MONSTER_SUNK = 7;
    /** Chờ Toniku còn dưới 10 HP. */
    public static final int WAIT_TONIKU_DEFEATED = 8;
    /** Chờ boss cuối bị hạ (cứu được Sakura). */
    public static final int WAIT_FINAL_BOSS_DEFEATED = 9;

    public static final int[][] conditions = new int[][]{{2, 1, 1, 5, 3}, {2, 4, 3}, {2, 3}, {2, 1, 3}, {6, 3}, {2, 7, 3}, {2, 8, 3}, {2, 9, 3}, {2, 3}, {1, 3}, {2, 3}};
    /** Cutscene phát ở mỗi bước. */
    public static final int[][] cutscenes = new int[][]{{0, 1, 2, 5, 0}, {3, 4, 0}, {6, 0}, {7, 8, 0}, {9, 0}, {10, 11, 0}, {12, 13, 0}, {14, 15, 0}, {16, 0}, {17, 0}, {18, 0}};
    public static final int[][] paramX = new int[][]{{0, 360, 1128, 0, 0}, new int[3], new int[2], {0, 2784, 0}, {1248, 0}, new int[3], new int[3], new int[3], new int[2], new int[2], new int[2]};
    public static final int[][] paramY = new int[][]{new int[5], new int[3], new int[2], new int[3], {408, 0}, new int[3], new int[3], new int[3], new int[2], new int[2], new int[2]};

    private MapEvent() {
    }
}
