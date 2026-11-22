package core.story;

/**
 * Kịch bản cutscene. Mỗi cutscene là một dãy lệnh; lệnh thứ i có mã {@link #commands},
 * tham số {@link #args} và được chạy khi bộ đếm frame của cutscene đạt
 * {@link #triggerFrames} (bộ đếm không reset giữa các lệnh, nên các giá trị tăng dần).
 * Trình thông dịch là {@code b.updateCutscene()}.
 */
public final class Cutscene {
    // =====================================================================================
    // #region Mã lệnh

    /** Giả lập nhấn phím; tham số là chỉ số phím (Input.KEY_*). */
    public static final int CMD_KEY_DOWN = 1;
    /** Giả lập nhả phím. */
    public static final int CMD_KEY_UP = 2;
    /** Kết thúc cutscene. */
    public static final int CMD_END = 3;
    /** Hiện lời thoại {@code DialogueText.cutsceneLines[arg]} (chờ nhân vật đứng yên). */
    public static final int CMD_SAY = 4;
    /** Đặt mũi tên chỉ đường ở x = arg trên map hiện tại. */
    public static final int CMD_SHOW_GUIDE_MARKER = 5;
    public static final int CMD_HIDE_GUIDE_MARKER = 6;
    /** Camera đi theo nhân vật. */
    public static final int CMD_CAMERA_FOLLOW_PLAYER = 7;
    /** Camera thôi đi theo nhân vật. */
    public static final int CMD_CAMERA_FREE = 8;
    /** Đặt camera ngay tại {@link #cameraPoints}[arg]. */
    public static final int CMD_CAMERA_JUMP = 9;
    /** Lia camera tới {@link #cameraPoints}[arg]. */
    public static final int CMD_CAMERA_PAN = 10;
    /** Lia camera tới boss rừng trúc. */
    public static final int CMD_CAMERA_PAN_TO_BAMBOO_BOSS = 11;
    /** Cuộn camera xuống 2px/frame tới y của {@link #cameraPoints}[arg]. */
    public static final int CMD_CAMERA_SCROLL_DOWN = 12;
    /** Không làm gì (chờ). */
    public static final int CMD_WAIT = 13;
    /** Cuộn camera sang trái 2px/frame tới x của {@link #cameraPoints}[arg]. */
    public static final int CMD_CAMERA_SCROLL_LEFT = 14;
    /** Hiện chú thích {@code DialogueText.cutsceneCaptions[arg]} trên dải đen. */
    public static final int CMD_CAPTION = 15;
    /** Bắt đầu cảnh diễn của bạn học (b.dy = arg); giống {@link #CMD_CLASSMATE_SCENE_2}. */
    public static final int CMD_CLASSMATE_SCENE = 16;
    /** Chuyển sang map arg, xuất hiện ở lối ra bên phải. */
    public static final int CMD_CHANGE_MAP = 17;
    /** Đặt mũi tên chỉ NPC tiếp theo (b.setNextNpcMarker(arg)). */
    public static final int CMD_NEXT_NPC_MARKER = 18;
    /** Cô gái đi theo nhân vật. */
    public static final int CMD_GIRL_FOLLOW = 19;
    /** Cô gái đứng lại tại vị trí cố định. */
    public static final int CMD_GIRL_STAY = 20;
    /** Hoàn thành nhiệm vụ hộ tống em gái Ayumi, nhận bằng lái mô tô. */
    public static final int CMD_REWARD_MOTORBIKE_LICENSE = 21;
    /** Thủy quái đã bị hạ: mở lối qua sông. */
    public static final int CMD_RIVER_MONSTER_DEFEATED = 22;
    /** Lia camera tới bạn học số arg. */
    public static final int CMD_CAMERA_PAN_TO_CLASSMATE = 23;
    public static final int CMD_CLASSMATE_SCENE_2 = 24;
    /** Bạn học số 0 dừng diễn và đứng yên. */
    public static final int CMD_STOP_FIRST_CLASSMATE = 25;
    /** Toniku đã bị hạ: mở lối đi. */
    public static final int CMD_TONIKU_DEFEATED = 26;
    /** Lia camera tới boss cuối (đền Orochi). */
    public static final int CMD_CAMERA_PAN_TO_FINAL_BOSS = 27;
    /** Boss cuối bắt đầu đánh. */
    public static final int CMD_START_FINAL_BOSS = 28;
    /** Kết thúc game: giải phóng tài nguyên và chuyển sang màn hình kết. */
    public static final int CMD_END_GAME = 29;
    /** Hoàn thành buổi đi chơi với cô gái. */
    public static final int CMD_GIRL_OUTING_DONE = 30;
    /** Lia camera tới cô gái. */
    public static final int CMD_CAMERA_PAN_TO_GIRL = 31;
    /** Cô gái chạy đi. */
    public static final int CMD_GIRL_RUN = 32;
    /** Bắt đầu hộ tống Sakura. */
    public static final int CMD_START_SAKURA_ESCORT = 33;

    // #endregion
    // =====================================================================================
    // #region Người nói của mỗi câu thoại (theo chỉ số trong DialogueText.cutsceneLines)

    public static final int SPEAKER_PLAYER = 0;
    public static final int SPEAKER_NPC = 1;
    public static final int SPEAKER_CLASSMATE = 2;
    /** 3 và 4 đều là cô gái. */
    public static final int SPEAKER_GIRL = 3;
    public static final int SPEAKER_GIRL_2 = 4;

    // #endregion
    // =====================================================================================

    public static final int[][] commands = new int[][]{{15, 1, 1, 2, 2, 1, 2, 4, 5, 3}, {6, 15, 1, 2, 1, 2, 1, 2, 1, 2, 4, 5, 3}, {6, 1, 2, 8, 11, 4, 7, 1, 2, 1, 2, 3}, {8, 9, 15, 12, 14, 10, 4, 16, 5, 1, 2, 3}, {7, 17}, {4, 1, 2, 17}, {8, 9, 15, 12, 14, 10, 4, 18, 4, 7, 3}, {1, 2, 4, 5, 3}, {6, 4, 19, 3}, {4, 20, 21, 3}, {4, 3}, {4, 22, 3}, {23, 4, 7, 4, 3}, {23, 25, 4, 7, 24, 26, 3}, {27, 4, 7, 4, 28, 3}, {4, 31, 32, 7, 4, 31, 4, 7, 4, 8, 17, 3}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 29, 3}, {4, 30, 3}, {15, 31, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 33, 17, 3}};
    public static final int[][] args = new int[][]{{1, -4, -1, -1, -4, -4, -4, 0, 20000, 0}, {0, 2, -4, -4, -5, -5, -5, -5, -5, -5, 1, 20000, 0}, {0, -5, -5, 0, 0, 2, 0, 55, 55, -4, -4, 0}, {0, 1, 0, 2, 3, 4, 3, 432, 20000, -4, -4, 0}, {0, 4}, {4, 55, 55, 0}, {0, 5, 3, 6, 7, 8, 5, 2, 6, 0, 0}, {-4, -4, 7, 20000, 0}, {0, 8, 0, 0}, {9, 0, 0, 0}, {10, 0}, {11, 0, 0}, {0, 12, 0, 13, 0}, {0, 0, 14, 0, 600, 0, 0}, {0, 15, 0, 16, 0, 0}, {17, 0, 0, 0, 55, 0, 56, 0, 57, 0, 94, 0}, {18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 0, 0}, {54, 0, 0}, {4, 0, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 0, 0, 0}};
    public static final int[][] triggerFrames = new int[][]{{5, 26, 30, 33, 41, 48, 53, 57, 58, 60}, {5, 6, 8, 9, 18, 20, 30, 32, 39, 40, 60, 63, 67}, {5, 6, 7, 13, 14, 19, 21, 34, 35, 60, 79, 80}, {1, 2, 3, 20, 50, 80, 85, 90, 92, 93, 95, 0}, {5, 6}, {10, 12, 13, 50}, {1, 2, 3, 4, 5, 6, 17, 18, 19, 20, 21}, {5, 10, 20, 30, 32}, {5, 10, 15, 20}, {10, 15, 20, 25}, {20, 25}, {10, 12, 14}, {15, 30, 35, 45, 55}, {10, 12, 30, 40, 45, 50, 75}, {15, 30, 35, 45, 53, 55}, {10, 12, 13, 40, 50, 60, 70, 80, 90, 95, 100, 105}, {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47}, {10, 12, 14}, {10, 11, 60, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74}};
    /** Điểm camera hướng tới (x, y theo pixel map) cho các lệnh CMD_CAMERA_*. */
    public static final int[][] cameraPoints = new int[][]{{1344, 288}, {816, 48}, {816, 192}, {744, 192}, {144, 264}, {1440, 96}, {1368, 288}, {1128, 288}, {840, 312}};
    /** Loại người nói (SPEAKER_*) của mỗi câu thoại cutscene. */
    public static final int[] lineSpeakerKind = new int[]{0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 2, 0, 2, 3, 0, 0, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 4, 0, 4, 0, 1, 2, 1, 2, 0, 1, 2, 1, 2, 1, 2};
    /** Chỉ số NPC / bạn học nói câu thoại (khi người nói là NPC hoặc bạn học). */
    public static final int[] lineSpeakerIndex = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 1, 0, 4, 0, 3, 0, 6, 0, 2, 0, 5, 0, 4, 0, 1, 0, 3, 0, 0, 0, 5, 0, 4, 0, 3, 0, 2, 0, 2, 0, 1, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 2, 1, 3, 2, 2};

    private Cutscene() {
    }
}
