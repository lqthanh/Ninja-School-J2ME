package core.quest;

/** Kỹ năng nhân vật đã học (phần thưởng nhiệm vụ, được lưu trong save). */
public final class Skills {
    /** Khinh công (cô Ayumi, nhiệm vụ 0): nhảy cao hơn. */
    public static boolean lightness;
    /** Khinh công: bấm lên lần nữa trên không để nhảy lần 2 (tốn MP). */
    public static boolean doubleJump;
    /** Khinh công cấp 2 (cô Ayumi, nhiệm vụ 11): nhảy lần 3 trên không. */
    public static boolean tripleJump;
    /** Ném phi tiêu (thầy Hiroyaki, nhiệm vụ 1). */
    public static boolean darts;
    /** Phi tiêu cấp 2 (thầy Hiroyaki, nhiệm vụ 12): sát thương phi tiêu x4. */
    public static boolean dartsLevel2;
    /** Độn thổ (thầy Honda, nhiệm vụ 2): giữ phím xuống trên ô đất đào được, tốn MP. */
    public static boolean burrow;
    /** Bằng lái xe máy (thầy Takanashi, nhiệm vụ 3, trao trong cutscene): bấm lên cạnh xe máy để lái. */
    public static boolean motorbikeLicense;
    /** Sức đánh x2 (thầy Hiroyaki, nhiệm vụ 4). */
    public static boolean doubleStrength;
    /** Lặn nước / thở dưới nước (thầy Honda, nhiệm vụ 5): chìm xuống đáy thì đi bộ được, không chết đuối (trừ ô nước sâu). */
    public static boolean dive;
    /** Chạy trên mặt nước (thầy Honda, nhiệm vụ 5): rơi xuống nước thì chạy trên mặt nước, tốn MP. */
    public static boolean runOnWater;
    /** Đứng nước (thầy Honda, nhiệm vụ 14): dừng lại trên mặt nước không bị chìm. */
    public static boolean standOnWater;
    /** Biến hình (thầy Hiroyaki, nhiệm vụ 6): phím 7. */
    public static boolean transform;
    /** Chạy nhanh (cô Ayumi, nhiệm vụ 13). */
    public static boolean sprint;
    /** Ẩn thân (thầy Honda, nhiệm vụ 15): phím 9; cần để vào khu vực map 95. */
    public static boolean hide;

    private Skills() {
    }
}
