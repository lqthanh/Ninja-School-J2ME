package core.quest;

/** Các mốc cốt truyện / sự kiện đã qua (được lưu trong save). */
public final class Story {
    /** Đang chơi đoạn mở đầu (hồi tưởng "6 tháng trước", map 99 rồi map 4); về map 0 thì kết thúc. */
    public static boolean prologueActive;
    /** Đã hạ thủy quái hạ nguồn sông (map 97), mở đường đi tiếp. */
    public static boolean riverMonsterDefeated;
    /** Đã hạ Toniku (map 96, nhiệm vụ 8). */
    public static boolean tonikuDefeated;
    /** Đã tìm thấy Sakura (map 95, nhiệm vụ 16). */
    public static boolean sakuraFound;
    /** Xong nhiệm vụ giết dơi vàng (nhiệm vụ 7). */
    public static boolean goldenBatQuestDone;
    /** Xong nhiệm vụ Toniku (nhiệm vụ 8). */
    public static boolean tonikuQuestDone;
    /** Cô bé đang chờ người chơi đến rủ đi chơi (sau nhiệm vụ 9). */
    public static boolean girlWaiting;
    /** Đã dẫn cô bé đi chơi xong (nhiệm vụ 10). */
    public static boolean girlOutingDone;
    /** Đang dẫn Sakura về trường (đoạn kết). */
    public static boolean sakuraEscorting;
    /** Đã đưa Sakura về trường: hoàn thành game. */
    public static boolean gameCompleted;
    public static boolean prologueNoRespawn;

    private Story() {
    }
}
