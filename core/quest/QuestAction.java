package core.quest;

/**
 * Hành động chạy khi đọc hết một đoạn hội thoại (Dialogue.finishAction, xử lý ở
 * GameCanvas.onDialogueFinished): giao nhiệm vụ (START_*), trao thưởng (REWARD_*) ...
 * Dialogue.NO_ACTION (-1), {@link #TALK} và {@link #QUEST_BUSY} không làm gì.
 */
public final class QuestAction {
    /** Nói chuyện thường (cô bé, bạn học). */
    public static final int TALK = 0;
    /** "Hãy hoàn thành nhiệm vụ được giao rồi quay lại gặp ta!" */
    public static final int QUEST_BUSY = 1;
    public static final int START_KILL_SNAILS = 3;
    public static final int REWARD_LIGHTNESS = 4;
    public static final int START_KNOCK_DOWN_SCARECROWS = 5;
    public static final int REWARD_DARTS = 6;
    /** Không được dùng (không có xử lý). */
    public static final int UNUSED_7 = 7;
    /** Không được dùng: xóa thông báo nhanh. */
    public static final int UNUSED_CLEAR_NOTICE = 8;
    public static final int START_FIND_BURROW_SCROLL = 9;
    public static final int REWARD_BURROW = 10;
    public static final int START_ESCORT_AYUMI_SISTER = 11;
    public static final int REWARD_DOUBLE_STRENGTH = 12;
    public static final int START_KILL_BEES = 13;
    public static final int START_KILL_WILD_BOAR = 14;
    /** Lặn nước + chạy trên nước (thông báo chỉ ghi "đã học chạy nước"). */
    public static final int REWARD_WATER_SKILLS = 15;
    public static final int START_CROSS_RIVER = 16;
    public static final int REWARD_TRANSFORM = 17;
    public static final int START_KILL_GOLDEN_BATS = 18;
    public static final int REWARD_MONEY = 19;
    public static final int START_FIND_HIDE_SCROLL = 20;
    public static final int REWARD_HIDE = 21;
    public static final int START_FIGHT_TONIKU = 22;
    public static final int COMPLETE_FIGHT_TONIKU = 23;
    public static final int START_KILL_NATIVES = 24;
    public static final int REWARD_MEET_GIRL = 25;
    public static final int START_GIRL_OUTING = 26;
    public static final int REWARD_TRIPLE_JUMP = 27;
    public static final int START_AIR_HITS = 28;
    public static final int REWARD_DARTS_LEVEL_2 = 29;
    public static final int START_KILL_FLOWER_DEMONS = 30;
    public static final int REWARD_SPRINT = 31;
    public static final int START_KILL_SCORPIONS = 32;
    public static final int REWARD_STAND_ON_WATER = 33;
    public static final int START_KILL_RED_TIGER_FISH = 34;
    public static final int COMPLETE_RESCUE_SAKURA = 35;
    public static final int START_RESCUE_SAKURA = 36;

    private QuestAction() {
    }
}
