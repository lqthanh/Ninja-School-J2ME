package core.quest;

/** Nhiệm vụ đang nhận và tiến độ (được lưu trong save). */
public final class QuestState {
    /** Id nhiệm vụ đang nhận ({@link Quest}), {@link Quest#NONE} nếu không có. */
    public static int current;
    /** Tiến độ: số quái đã giết / số đòn trúng. */
    public static int progress;
    /**
     * Nhiệm vụ tìm bí kíp: giết quái loại {@code dropMonster - DROP_MONSTER_BASE} thì bí kíp
     * có {@code dropChance}/10 khả năng rơi ra. 0 = không có.
     */
    public static int dropMonster;
    public static int dropChance;
    /** Đã nhặt được bí kíp của nhiệm vụ hiện tại. */
    public static boolean itemFound;

    /** dropMonster = DROP_MONSTER_BASE + loại quái. */
    public static final int DROP_MONSTER_BASE = 100;

    private QuestState() {
    }
}
