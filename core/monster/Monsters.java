package core.monster;

import core.map.GameMap;
import javax.microedition.lcdui.Image;

/**
 * Quái trên map hiện tại. Mỗi con là một chỉ số i trong các mảng song song bên dưới;
 * {@code type[i] == FREE} nghĩa là ô trống.
 *
 * Quái thuộc một nhóm ({@link #group}); mỗi nhóm có bộ ảnh riêng ({@link #groupSpriteSets})
 * và danh sách điểm xuất hiện ({@link #spawnX}, {@link #spawnY}). Khi bị hạ, quái chờ
 * {@link #respawnDelay} frame rồi hồi sinh ở một điểm trống ngẫu nhiên của nhóm.
 * Bảng quái theo map nằm ở {@link MonsterSpawns}; AI nằm ở b.updateMonsters().
 */
public final class Monsters {
    public static final int FREE = -1;

    // =====================================================================================
    // #region Loại quái (chỉ số trong MonsterStats và GameText.monsterNames)

    public static final int TYPE_ONE_EYED_GHOST = 0;
    public static final int TYPE_SNAIL = 1;
    public static final int TYPE_DEMON_BAT = 2;
    public static final int TYPE_ONE_LEGGED_DEMON = 3;
    public static final int TYPE_GREEN_TOAD = 4;
    public static final int TYPE_SPIDER = 5;
    public static final int TYPE_BEE = 6;
    public static final int TYPE_SCARECROW = 7;
    public static final int TYPE_TIGER_FISH = 8;
    public static final int TYPE_WIND_NINJA = 9;
    public static final int TYPE_RED_TIGER_FISH = 10;
    public static final int TYPE_ONE_EYED_GHOST_2 = 11;
    public static final int TYPE_DESERT_CENTIPEDE = 12;
    public static final int TYPE_GOLDEN_BAT = 13;
    public static final int TYPE_RAT = 14;
    public static final int TYPE_NATIVE = 15;
    public static final int TYPE_RED_BEE = 16;
    public static final int TYPE_SCORPION = 17;
    public static final int TYPE_FLOWER_DEMON = 18;
    public static final int TYPE_PURPLE_FLOWER_DEMON = 19;

    // #endregion
    // =====================================================================================
    // #region Trạng thái AI ({@link #state})

    /** Bị hạ: văng lên rồi rơi khỏi map, sau đó chuyển sang {@link #STATE_RESPAWN_WAIT}. */
    public static final int STATE_KNOCKED_OUT = 1;
    /** Đang hoạt động theo AI của loại quái. */
    public static final int STATE_ACTIVE = 2;
    /** Nghỉ 35 frame rồi hoạt động lại. */
    public static final int STATE_RESTING = 3;
    /** Đang tấn công (nhân vật hoặc cô gái nếu {@link #attackingGirl}). */
    public static final int STATE_ATTACKING = 4;
    /** Chờ {@link #respawnDelay} frame rồi xuất hiện ở điểm trống của nhóm. */
    public static final int STATE_RESPAWN_WAIT = 5;
    /** Bị trúng đòn, lùi lại. */
    public static final int STATE_HURT = 6;
    /** Không dùng. */
    public static final int STATE_UNUSED_7 = 7;
    /** Không hồi sinh nữa (bị hạ trong đoạn mở đầu, b.prologueNoRespawn). */
    public static final int STATE_GONE = 8;
    /** Bị hất tung lên (đòn chém hất), rơi về điểm xuất hiện. */
    public static final int STATE_LAUNCHED = 9;

    // #endregion
    // =====================================================================================

    /** Số ô (sức chứa) của các mảng. */
    public static int count;
    /** Số frame chờ trước khi hồi sinh. */
    public static int respawnDelay;
    /** Loại quái (TYPE_*), {@link #FREE} nếu ô trống. */
    public static int[] type;
    /** Nhóm điểm xuất hiện / bộ ảnh. */
    public static int[] group;
    /** Vị trí chân (pixel). */
    public static int[] x;
    public static int[] y;
    /** Kích thước hộp va chạm. */
    public static int[] height;
    public static int[] width;
    /** Còn hiện thanh máu (khác 0) sau khi bị đánh. */
    public static int[] healthBarTimer;
    /** Trạng thái AI (STATE_*). */
    public static int[] state;
    /**
     * Phạm vi hoạt động, ý nghĩa tùy loại: thường là x nhỏ nhất / lớn nhất khi đi tuần;
     * với quái bay là toạ độ x / y của điểm lượn quanh.
     */
    public static int[] rangeA;
    public static int[] rangeB;
    public static int[] vx;
    public static int[] vy;
    /** Bộ đếm dùng chung cho mọi trạng thái. */
    public static int[] timer;
    /** Biến phụ của AI, ý nghĩa tùy loại và trạng thái (vận tốc nảy, đếm số lần nảy...). */
    public static int[] aux1;
    public static int[] aux2;
    /** Khung hình. */
    public static int[] frame;
    /** Hướng nhìn: 1 phải, -1 trái. */
    public static int[] direction;
    public static int[] hp;
    public static int[] maxHp;
    public static int[] damage;
    /** Đếm từ lần tấn công trước; tấn công được khi vượt quá 30. */
    public static int[] attackCooldown;
    /** Tầm tấn công theo x (về phía trước) và theo y (lên/xuống). */
    public static int[] attackRangeX;
    public static int[] attackRangeY;
    /** Điểm xuất hiện đang chiếm trong nhóm. */
    public static int[] spawnPoint;
    /** Tiền rơi ra (nhân 8). */
    public static int[] money;
    /** Lần tấn công này nhắm vào cô gái đi theo nhân vật thay vì nhân vật. */
    public static boolean[] attackingGirl;
    /** Điểm xuất hiện theo nhóm (khai báo theo ô tile, {@link #finishGroups()} đổi sang pixel). */
    public static int[][] spawnX;
    public static int[][] spawnY;
    /** Điểm xuất hiện đang có quái đứng. */
    public static boolean[][] spawnUsed;
    /** Bộ ảnh (Mob.loadMonster) của mỗi nhóm. */
    public static int[] groupSpriteSets;
    /** Ảnh theo nhóm, rồi theo khung hình. */
    public static Image[][][] sprites;

    private Monsters() {
    }

    /** Thêm {@code amount} con dơi quỷ vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnDemonBats(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int i = start; i < start + amount; ++i) {
                group[i] = spawnGroup;
                type[i] = TYPE_DEMON_BAT;
                attackRangeX[i] = 120;
                attackRangeY[i] = 100;
                height[i] = 20;
                width[i] = 30;
                direction[i] = 0;
                state[i] = STATE_RESPAWN_WAIT;
                timer[i] = i * 7 + i;
                hp[i] = maxHp[i] = MonsterStats.maxHp[type[i]];
                damage[i] = MonsterStats.damage[type[i]];
                money[i] = MonsterStats.money[type[i]];
                attackCooldown[i] = 100;
                vx[i] = 2;
                vy[i] = 3;
            }

        }
    }

    /** Thêm {@code amount} con dơi vàng vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnGoldenBats(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int i = start; i < start + amount; ++i) {
                group[i] = spawnGroup;
                type[i] = TYPE_GOLDEN_BAT;
                attackRangeX[i] = 120;
                attackRangeY[i] = 50;
                height[i] = 20;
                width[i] = 20;
                direction[i] = -1;
                state[i] = STATE_RESPAWN_WAIT;
                timer[i] = i << 1;
                hp[i] = maxHp[i] = MonsterStats.maxHp[type[i]];
                damage[i] = MonsterStats.damage[type[i]];
                money[i] = MonsterStats.money[type[i]];
                attackCooldown[i] = 100;
                vx[i] = -3;
                vy[i] = 3;
            }

        }
    }

    /** Thêm {@code amount} con ninja gió vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnWindNinjas(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int i = start; i < start + amount; ++i) {
                group[i] = spawnGroup;
                type[i] = TYPE_WIND_NINJA;
                attackRangeX[i] = 36;
                attackRangeY[i] = 20;
                height[i] = 34;
                width[i] = 32;
                direction[i] = 1;
                state[i] = STATE_RESPAWN_WAIT;
                timer[i] = i * 6;
                hp[i] = maxHp[i] = MonsterStats.maxHp[type[i]];
                damage[i] = MonsterStats.damage[type[i]];
                money[i] = MonsterStats.money[type[i]];
                attackCooldown[i] = 100;
            }

        }
    }

    /** Thêm {@code amount} con cá cọp vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. Bản gốc bỏ qua {@code spawnGroup}, luôn dùng nhóm 1. */
    public static void spawnTigerFish(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int i = start; i < start + amount; ++i) {
                group[i] = 1;
                type[i] = TYPE_TIGER_FISH;
                attackRangeX[i] = 40;
                attackRangeY[i] = 30;
                height[i] = 12;
                width[i] = 16;
                y[i] += 5;
                direction[i] = 1;
                state[i] = STATE_RESPAWN_WAIT;
                timer[i] = i * 5;
                hp[i] = maxHp[i] = MonsterStats.maxHp[type[i]];
                damage[i] = MonsterStats.damage[type[i]];
                money[i] = MonsterStats.money[type[i]];
                attackCooldown[i] = 60;
            }

        }
    }

    /** Thêm {@code amount} con cá cọp đỏ vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. Bản gốc bỏ qua {@code spawnGroup}, luôn dùng nhóm 2. */
    public static void spawnRedTigerFish(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int i = start; i < start + amount; ++i) {
                group[i] = 2;
                type[i] = TYPE_RED_TIGER_FISH;
                attackRangeX[i] = 80;
                attackRangeY[i] = 100;
                height[i] = 12;
                width[i] = 16;
                direction[i] = 1;
                state[i] = STATE_RESPAWN_WAIT;
                timer[i] = i * 5;
                hp[i] = maxHp[i] = MonsterStats.maxHp[type[i]];
                damage[i] = MonsterStats.damage[type[i]];
                money[i] = MonsterStats.money[type[i]];
                attackCooldown[i] = 40;
            }

        }
    }

    /** Thêm {@code amount} con ong bò vẽ vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnBees(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int i = start; i < start + amount; ++i) {
                group[i] = spawnGroup;
                type[i] = TYPE_BEE;
                attackRangeX[i] = 60;
                attackRangeY[i] = 60;
                height[i] = 16;
                width[i] = 16;
                direction[i] = -1;
                state[i] = STATE_RESPAWN_WAIT;
                timer[i] = i << 3;
                hp[i] = maxHp[i] = MonsterStats.maxHp[type[i]];
                damage[i] = MonsterStats.damage[type[i]];
                money[i] = MonsterStats.money[type[i]];
                attackCooldown[i] = 100;
                vx[i] = 2;
                vy[i] = 3;
            }

        }
    }

    /** Thêm {@code amount} con ong đỏ vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnRedBees(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int i = start; i < start + amount; ++i) {
                group[i] = spawnGroup;
                type[i] = TYPE_RED_BEE;
                attackRangeX[i] = 60;
                attackRangeY[i] = 60;
                height[i] = 16;
                width[i] = 16;
                direction[i] = -1;
                state[i] = STATE_RESPAWN_WAIT;
                timer[i] = i + 60;
                hp[i] = maxHp[i] = MonsterStats.maxHp[type[i]];
                damage[i] = MonsterStats.damage[type[i]];
                money[i] = MonsterStats.money[type[i]];
                attackCooldown[i] = 100;
                vx[i] = 2;
                vy[i] = 3;
            }

        }
    }

    /** Thêm {@code amount} con bù nhìn rơm vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnScarecrows(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int i = start; i < start + amount; ++i) {
                group[i] = spawnGroup;
                type[i] = TYPE_SCARECROW;
                attackRangeX[i] = 10;
                attackRangeY[i] = 10;
                height[i] = 30;
                width[i] = 25;
                direction[i] = 1;
                state[i] = STATE_RESPAWN_WAIT;
                timer[i] = i << 2;
                hp[i] = maxHp[i] = MonsterStats.maxHp[type[i]];
                damage[i] = MonsterStats.damage[type[i]];
                money[i] = MonsterStats.money[type[i]];
                attackCooldown[i] = 0;
            }

        }
    }

    /** Thêm {@code amount} con cóc xanh vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. Bản gốc bỏ qua {@code spawnGroup}, luôn dùng nhóm 2. */
    public static void spawnGreenToads(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int i = start; i < start + amount; ++i) {
                group[i] = 2;
                type[i] = TYPE_GREEN_TOAD;
                attackRangeX[i] = 20;
                attackRangeY[i] = 10;
                height[i] = 18;
                width[i] = 18;
                direction[i] = 1;
                state[i] = STATE_RESPAWN_WAIT;
                timer[i] = i << 3;
                hp[i] = maxHp[i] = MonsterStats.maxHp[type[i]];
                damage[i] = MonsterStats.damage[type[i]];
                money[i] = MonsterStats.money[type[i]];
                attackCooldown[i] = 0;
            }

        }
    }

    /** Thêm {@code amount} con ma một mắt vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnOneEyedGhosts(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int i = start; i < start + amount; ++i) {
                group[i] = spawnGroup;
                type[i] = TYPE_ONE_EYED_GHOST;
                attackRangeX[i] = 25;
                attackRangeY[i] = 10;
                height[i] = 27;
                width[i] = 15;
                direction[i] = 1;
                state[i] = STATE_RESPAWN_WAIT;
                timer[i] = i * 7;
                hp[i] = maxHp[i] = MonsterStats.maxHp[type[i]];
                damage[i] = MonsterStats.damage[type[i]];
                money[i] = MonsterStats.money[type[i]];
                attackCooldown[i] = 0;
            }

        }
    }

    /** Thêm {@code amount} con thổ dân vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnNatives(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int i = start; i < start + amount; ++i) {
                group[i] = spawnGroup;
                type[i] = TYPE_NATIVE;
                attackRangeX[i] = 36;
                attackRangeY[i] = 10;
                height[i] = 27;
                width[i] = 15;
                direction[i] = 1;
                state[i] = STATE_RESPAWN_WAIT;
                timer[i] = i << 1;
                hp[i] = maxHp[i] = MonsterStats.maxHp[type[i]];
                damage[i] = MonsterStats.damage[type[i]];
                money[i] = MonsterStats.money[type[i]];
                attackCooldown[i] = 0;
            }

        }
    }

    /** Thêm {@code amount} con ốc sên vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnSnails(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int i = start; i < start + amount; ++i) {
                group[i] = spawnGroup;
                type[i] = TYPE_SNAIL;
                attackRangeX[i] = 7;
                attackRangeY[i] = 10;
                height[i] = 16;
                width[i] = 15;
                direction[i] = 1;
                state[i] = STATE_RESPAWN_WAIT;
                timer[i] = i << 3;
                hp[i] = maxHp[i] = MonsterStats.maxHp[type[i]];
                damage[i] = MonsterStats.damage[type[i]];
                money[i] = MonsterStats.money[type[i]];
                attackCooldown[i] = 100;
            }

        }
    }

    /** Thêm {@code amount} con quỷ hoa vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnFlowerDemons(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int i = start; i < start + amount; ++i) {
                group[i] = spawnGroup;
                type[i] = TYPE_FLOWER_DEMON;
                attackRangeX[i] = 120;
                attackRangeY[i] = 10;
                height[i] = 24;
                width[i] = 20;
                direction[i] = 1;
                state[i] = STATE_RESPAWN_WAIT;
                timer[i] = 60;
                hp[i] = maxHp[i] = MonsterStats.maxHp[type[i]];
                damage[i] = MonsterStats.damage[type[i]];
                money[i] = MonsterStats.money[type[i]];
                attackCooldown[i] = 100;
            }

        }
    }

    /** Thêm {@code amount} con quỷ tử hoa vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. Bản gốc bỏ qua {@code spawnGroup}, luôn dùng nhóm 0. */
    public static void spawnPurpleFlowerDemons(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int i = start; i < start + amount; ++i) {
                group[i] = 0;
                type[i] = TYPE_PURPLE_FLOWER_DEMON;
                attackRangeX[i] = 60;
                attackRangeY[i] = 30;
                height[i] = 24;
                width[i] = 20;
                direction[i] = 1;
                state[i] = STATE_RESPAWN_WAIT;
                timer[i] = 62;
                hp[i] = maxHp[i] = MonsterStats.maxHp[type[i]];
                damage[i] = MonsterStats.damage[type[i]];
                money[i] = MonsterStats.money[type[i]];
                attackCooldown[i] = 70;
            }

        }
    }

    /** Thêm {@code amount} con bò cạp vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnScorpions(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int i = start; i < start + amount; ++i) {
                group[i] = spawnGroup;
                type[i] = TYPE_SCORPION;
                attackRangeX[i] = 60;
                attackRangeY[i] = 10;
                height[i] = 22;
                width[i] = 22;
                direction[i] = 1;
                state[i] = STATE_RESPAWN_WAIT;
                timer[i] = 50 + (i << 1);
                hp[i] = maxHp[i] = MonsterStats.maxHp[type[i]];
                damage[i] = MonsterStats.damage[type[i]];
                money[i] = MonsterStats.money[type[i]];
                attackCooldown[i] = 500;
            }

        }
    }

    /** Thêm {@code amount} con rết sa mạc vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. Bản gốc bỏ qua {@code spawnGroup}, luôn dùng nhóm 0. */
    public static void spawnDesertCentipedes(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int i = start; i < start + amount; ++i) {
                group[i] = 0;
                type[i] = TYPE_DESERT_CENTIPEDE;
                attackRangeX[i] = 24;
                attackRangeY[i] = 10;
                height[i] = 16;
                width[i] = 22;
                direction[i] = 1;
                state[i] = STATE_RESPAWN_WAIT;
                timer[i] = 70;
                hp[i] = maxHp[i] = MonsterStats.maxHp[type[i]];
                damage[i] = MonsterStats.damage[type[i]];
                money[i] = MonsterStats.money[type[i]];
                attackCooldown[i] = 500;
            }

        }
    }

    /** Cấp phát mảng cho {@code capacity} con quái, tất cả ở trạng thái trống. */
    public static void init(int capacity) {
        count = capacity;
        respawnDelay = 70;
        type = new int[count];
        group = new int[count];
        x = new int[count];
        y = new int[count];
        state = new int[count];
        rangeA = new int[count];
        rangeB = new int[count];
        vx = new int[count];
        vy = new int[count];
        timer = new int[count];
        aux1 = new int[count];
        aux2 = new int[count];
        frame = new int[count];
        direction = new int[count];
        hp = new int[count];
        attackingGirl = new boolean[count];
        maxHp = new int[count];
        damage = new int[count];
        attackCooldown = new int[count];
        height = new int[count];
        width = new int[count];
        width = new int[count];
        healthBarTimer = new int[count];
        attackRangeX = new int[count];
        attackRangeY = new int[count];
        spawnPoint = new int[count];
        money = new int[count];

        for(int i = 0; i < count; ++i) {
            type[i] = FREE;
        }

    }

    /** Cấp phát danh sách điểm xuất hiện cho mỗi nhóm trong {@link #groupSpriteSets}. */
    public static void allocGroups() {
        spawnX = new int[groupSpriteSets.length][];
        spawnY = new int[groupSpriteSets.length][];
        spawnUsed = new boolean[groupSpriteSets.length][];
    }

    /** Đổi điểm xuất hiện từ ô tile sang pixel (giữa ô theo x, đáy ô theo y; nhóm 8 cao hơn 12px). */
    public static void finishGroups() {
        int g;
        for(g = 0; g < groupSpriteSets.length; ++g) {
            spawnUsed[g] = new boolean[spawnX[g].length];
        }

        for(g = 0; g < spawnX.length; ++g) {
            for(int k = 0; k < spawnX[g].length; ++k) {
                spawnX[g][k] = spawnX[g][k] * GameMap.TILE_SIZE + 12;
                spawnY[g][k] *= GameMap.TILE_SIZE;
                if (g == 8) {
                    spawnY[g][k] -= 12;
                }
            }
        }

    }
}
