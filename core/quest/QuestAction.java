package core.quest;

import assets.Effect;
import core.item.Inventory;
import core.npc.Girl;
import core.player.Darts;
import core.player.PlayerAction;
import core.player.PlayerStats;
import core.ui.Dialogue;
import core.ui.GuideMarker;
import core.ui.Hud;
import core.ui.Notice;

/**
 * Hành động chạy khi đọc hết một đoạn hội thoại (Dialogue.finishAction, xử lý ở
 * {@link #onDialogueFinished}): giao nhiệm vụ (START_*), trao thưởng (REWARD_*) ...
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

    public static void onDialogueFinished() {
        switch (Dialogue.finishAction) {
            case START_KILL_SNAILS:
                QuestState.current = Quest.KILL_SNAILS;
                QuestState.progress = 0;
                Notice.show("NV: Giết 10 ốc sên");
                break;
            case REWARD_LIGHTNESS:
                Skills.lightness = true;
                Skills.doubleJump = true;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                Notice.show("đã học phép khinh công");
                break;
            case START_KNOCK_DOWN_SCARECROWS:
                QuestState.current = Quest.KNOCK_DOWN_SCARECROWS;
                QuestState.progress = 0;
                Notice.show("NV: đánh ngã 2 bù nhìn");
                break;
            case REWARD_DARTS:
                Skills.darts = true;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                Notice.show("đã học ném phi tiêu");
            case UNUSED_7:
            default:
                break;
            case UNUSED_CLEAR_NOTICE:
                Notice.show((String)null);
                break;
            case START_FIND_BURROW_SCROLL:
                QuestState.current = Quest.FIND_BURROW_SCROLL;
                QuestState.dropMonster = QuestState.DROP_MONSTER_BASE + 3;
                QuestState.dropChance = 2;
                QuestState.itemFound = false;
                Notice.show("NV: Tìm bí kíp");
                break;
            case REWARD_BURROW:
                Skills.burrow = true;
                QuestState.current = Quest.NONE;
                QuestState.dropMonster = 0;
                QuestState.dropChance = 0;
                QuestState.itemFound = false;
                Notice.show("đã học thuật độn thổ");
                break;
            case START_ESCORT_AYUMI_SISTER:
                QuestState.current = Quest.ESCORT_AYUMI_SISTER;
                Girl.mapId = 3;
                Girl.x = 2808;
                Girl.y = 216;
                Girl.facing = -1;
                Girl.action = PlayerAction.STAND;
                Girl.vx = 5;
                break;
            case REWARD_DOUBLE_STRENGTH:
                Skills.doubleStrength = true;
                PlayerStats.attack <<= 1;
                QuestState.current = Quest.NONE;
                QuestState.dropMonster = 0;
                QuestState.dropChance = 0;
                QuestState.itemFound = false;
                Notice.show("sức công nhân đôi!");
                break;
            case START_KILL_BEES:
                QuestState.current = Quest.KILL_BEES;
                QuestState.progress = 0;
                Notice.show("NV: giết 20 con ong");
                break;
            case START_KILL_WILD_BOAR:
                QuestState.current = Quest.KILL_WILD_BOAR;
                QuestState.itemFound = false;
                QuestState.progress = 0;
                QuestState.dropChance = 10;
                Notice.show("NV: giết heo rừng");
                break;
            case REWARD_WATER_SKILLS:
                QuestState.current = Quest.NONE;
                Skills.dive = true;
                Skills.runOnWater = true;
                // Thưởng gồm cả lặn nước (Skills.dive) nhưng thông báo chỉ ghi chạy nước (giữ như bản gốc).
                Notice.show("đã học chạy nước.");
                break;
            case START_CROSS_RIVER:
                QuestState.current = Quest.CROSS_RIVER;
                QuestState.itemFound = false;
                QuestState.dropChance = 10;
                Notice.show("NV: vượt sông");
                break;
            case REWARD_TRANSFORM:
                QuestState.current = Quest.NONE;
                Skills.transform = true;
                Notice.show("đã học biến hình.");
                break;
            case START_KILL_GOLDEN_BATS:
                QuestState.current = Quest.KILL_GOLDEN_BATS;
                QuestState.progress = 0;
                Notice.show("NV: giết 8 dơi vàng");
                break;
            case REWARD_MONEY:
                Story.goldenBatQuestDone = true;
                Inventory.money += 5000;
                Hud.dirty = true;
                QuestState.current = Quest.NONE;
                QuestState.dropMonster = 0;
                QuestState.dropChance = 0;
                QuestState.itemFound = false;
                Notice.show("Nhận được tiền 5000$");
                break;
            case START_FIND_HIDE_SCROLL:
                QuestState.current = Quest.FIND_HIDE_SCROLL;
                QuestState.progress = 0;
                QuestState.itemFound = false;
                Notice.show("NV: tìm bí kíp");
                break;
            case REWARD_HIDE:
                Skills.hide = true;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                QuestState.itemFound = false;
                Notice.show("đã có thể ẩn thân");
                break;
            case START_FIGHT_TONIKU:
                QuestState.current = Quest.FIGHT_TONIKU;
                Notice.show("NV: tìm đánh Toniku");
                break;
            case COMPLETE_FIGHT_TONIKU:
                QuestState.current = Quest.NONE;
                Story.tonikuQuestDone = true;
                Notice.show("Hoàn thành nhiệm vụ.");
                break;
            case START_KILL_NATIVES:
                QuestState.current = Quest.KILL_NATIVES;
                QuestState.progress = 0;
                Notice.show("NV: giết 10 thổ dân");
                break;
            case REWARD_MEET_GIRL:
                Story.girlWaiting = true;
                QuestState.current = Quest.NONE;
                QuestState.dropMonster = 0;
                QuestState.dropChance = 0;
                QuestState.itemFound = false;
                Notice.show("đến gặp cô bé!");
                break;
            case START_GIRL_OUTING:
                QuestState.current = Quest.GIRL_OUTING;
                QuestState.progress = 0;
                Girl.following = true;
                Notice.show("NV: dẫn cô bé đi chơi");
                break;
            case REWARD_TRIPLE_JUMP:
                QuestState.current = Quest.NONE;
                Skills.tripleJump = true;
                QuestState.progress = 0;
                Notice.show("Học khinh công cấp 2");
                break;
            case START_AIR_HITS:
                QuestState.current = Quest.AIR_HITS;
                QuestState.progress = 0;
                Notice.show("NV: 20 hit trên không");
                break;
            case REWARD_DARTS_LEVEL_2:
                Skills.dartsLevel2 = true;
                PlayerStats.dartDamage <<= 2;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                Notice.show("đã học phi tiêu cấp 2");
                Effect.loadDarts(Darts.sprites, Effect.DART_LEVEL_2);
                break;
            case START_KILL_FLOWER_DEMONS:
                QuestState.current = Quest.KILL_FLOWER_DEMONS;
                QuestState.progress = 0;
                // Mục tiêu thực tế là "Quỷ hoa" (Quest.TARGET_MONSTER = 18), thông báo ghi "quỷ hoa đỏ" (giữ như bản gốc).
                Notice.show("NV: đánh 15 quỷ hoa đỏ");
                break;
            case REWARD_SPRINT:
                Skills.sprint = true;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                Notice.show("đã có thể chạy nhanh");
                break;
            case START_KILL_SCORPIONS:
                QuestState.current = Quest.KILL_SCORPIONS;
                QuestState.progress = 0;
                Notice.show("NV: Giết 18 bò cạp");
                break;
            case REWARD_STAND_ON_WATER:
                Skills.standOnWater = true;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                Notice.show("đã có thể đứng nước");
                break;
            case START_KILL_RED_TIGER_FISH:
                QuestState.current = Quest.KILL_RED_TIGER_FISH;
                QuestState.progress = 0;
                Notice.show("NV: Giết 4 cá đỏ");
                break;
            case COMPLETE_RESCUE_SAKURA:
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                QuestState.itemFound = false;
                break;
            case START_RESCUE_SAKURA:
                QuestState.current = Quest.RESCUE_SAKURA;
                QuestState.progress = 0;
                QuestState.itemFound = false;
                Notice.show("NV: cứu Sakura");
        }

        if (GuideMarker.pendingMap >= 0) {
            GuideMarker.set(GuideMarker.pendingMap, GuideMarker.pendingX, GuideMarker.pendingY, GuideMarker.pendingNpc);
            GuideMarker.pendingMap = -1;
        }

    }
}
