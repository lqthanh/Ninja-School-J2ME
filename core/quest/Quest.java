package core.quest;

/**
 * Danh sách nhiệm vụ (id 0..17) và mục tiêu đếm số lượng của từng nhiệm vụ.
 *
 * Nhiệm vụ do các thầy cô ở trường (map 0) giao theo chuỗi, thưởng là kỹ năng ({@link Skills})
 * hoặc mốc cốt truyện ({@link Story}). Điều kiện giao/nhận thưởng nằm trong code hội thoại NPC
 * (b.run, "Giao tiếp"), hành động khi đóng hội thoại nằm ở b.onDialogueFinished ({@link QuestAction}).
 *
 * Chỉ nhiệm vụ "giết N con quái" dùng {@link #TARGET_MONSTER}/{@link #TARGET_COUNT}; các nhiệm vụ
 * khác hoàn thành qua bí kíp ({@link QuestState#itemFound}), cờ sự kiện hoặc cutscene.
 */
public final class Quest {
    /** Không có nhiệm vụ. */
    public static final int NONE = -1;

    /** Ayumi: giết 10 ốc sên. Thưởng: khinh công. */
    public static final int KILL_SNAILS = 0;
    /** Hiroyaki: đánh ngã 2 bù nhìn rơm. Thưởng: ném phi tiêu. */
    public static final int KNOCK_DOWN_SCARECROWS = 1;
    /** Honda: tìm bí kíp (Quỷ một chân rơi ra). Thưởng: độn thổ. */
    public static final int FIND_BURROW_SCROLL = 2;
    /** Takanashi: đón em gái cô Ayumi ở Con đường hoa đào (map 3) về trường. Thưởng (cutscene): bằng lái xe máy. */
    public static final int ESCORT_AYUMI_SISTER = 3;
    /** Hiroyaki: giết 20 ong bò vẽ. Thưởng: sức đánh x2. */
    public static final int KILL_BEES = 4;
    /** Honda: giết heo rừng thành tinh, lấy bí kíp lặn nước. Thưởng: lặn nước + chạy trên nước. */
    public static final int KILL_WILD_BOAR = 5;
    /** Hiroyaki (cấp 8): vượt sông đến nghĩa địa (map 8) lấy bí kíp. Thưởng: biến hình. */
    public static final int CROSS_RIVER = 6;
    /** Ayumi (cấp 10): giết 8 dơi vàng. Thưởng: 5000$. */
    public static final int KILL_GOLDEN_BATS = 7;
    /** Takanashi: tìm đánh Toniku. */
    public static final int FIGHT_TONIKU = 8;
    /** Takanashi: giết 10 thổ dân. Thưởng: đi gặp cô bé. */
    public static final int KILL_NATIVES = 9;
    /** Cô bé: dẫn cô bé đi chơi (map 9). */
    public static final int GIRL_OUTING = 10;
    /** Ayumi (cấp 12): 20 đòn trúng trên không. Thưởng: khinh công cấp 2. */
    public static final int AIR_HITS = 11;
    /** Hiroyaki: giết 15 quỷ hoa. Thưởng: phi tiêu cấp 2. */
    public static final int KILL_FLOWER_DEMONS = 12;
    /** Ayumi (cấp 15): giết 18 bò cạp. Thưởng: chạy nhanh. */
    public static final int KILL_SCORPIONS = 13;
    /** Honda (cấp 16): giết 4 cá cọp đỏ. Thưởng: đứng trên nước. */
    public static final int KILL_RED_TIGER_FISH = 14;
    /** Honda: tìm bí kíp ẩn thân (map 15). Thưởng: ẩn thân. */
    public static final int FIND_HIDE_SCROLL = 15;
    /** Hiệu trưởng: cứu Sakura, dẫn tới đoạn kết. */
    public static final int RESCUE_SAKURA = 16;
    /** Không được dùng. */
    public static final int UNUSED_17 = 17;

    /** Giá trị trong {@link #TARGET_MONSTER}: nhiệm vụ không đếm quái. */
    public static final int NO_MONSTER = 100;

    /** Loại quái cần giết (chỉ số vào bảng tên quái GameText.monsterNames), hoặc {@link #NO_MONSTER}. */
    public static final int[] TARGET_MONSTER = new int[]{1, 7, 100, 100, 6, 100, 100, 13, 100, 15, 100, 100, 18, 17, 10, 100, 100, 100};

    /**
     * Số lượng cần đạt: số quái phải giết, riêng {@link #AIR_HITS} là số đòn trúng trên không.
     * 0 = nhiệm vụ không đếm số lượng.
     */
    public static final int[] TARGET_COUNT = new int[]{10, 2, 0, 0, 20, 0, 0, 8, 0, 10, 0, 20, 15, 18, 4, 0, 0, 0};

    private Quest() {
    }
}
