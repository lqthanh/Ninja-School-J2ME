package core.npc;

import core.quest.Story;

/**
 * Trận đấu với Toniku ở map 96. Toniku dùng ô 0 của các mảng trong {@link Classmates}
 * (vị trí, trạng thái, tư thế...); các field ở đây là máu và sát thương riêng của trận đấu.
 * AI: {@link ClassmateAI#updateTonikuFight()}.
 */
public final class Toniku {
    /** Đang có trận đấu Toniku trên map. */
    public static boolean active;
    /** Hiện thanh máu (sau khi trận đấu bắt đầu). */
    public static boolean healthBarVisible;
    /** Cảnh diễn yêu cầu Toniku dừng đánh và quay về trạng thái chờ. */
    public static boolean pacified;
    /** Sát thương mỗi nhát chém. */
    public static int damage;
    public static int hp;
    public static int maxHp;

    private Toniku() {
    }

    public static void setup(int mapId) {
        active = false;
        if (mapId == 96 && !Story.tonikuDefeated) {
            active = true;
            Classmates.count = 1;
            Classmates.alloc();
            Classmates.place(0, 2, 5, 11, 200, 4, 22, (String[])null);
            Classmates.loadHeads(3);
            maxHp = 15000;
            hp = 15000;
            damage = 120;
            Classmates.count = 0;
            Classmates.state[0] = Classmates.STATE_TONIKU_WAITING;
            Classmates.facing[0] = 1;
        }

    }
}
