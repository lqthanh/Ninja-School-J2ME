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
 * Bảng quái theo map nằm ở {@link MonsterSpawns}; AI nằm ở b.ak().
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
    /** Không hồi sinh nữa (bị hạ trong đoạn mở đầu, b.U). */
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
        int var2;
        for(var2 = 0; var2 < count && type[var2] != FREE; ++var2) {
        }

        if (var2 != count) {
            for(int var3 = var2; var3 < var2 + amount; ++var3) {
                group[var3] = spawnGroup;
                type[var3] = TYPE_DEMON_BAT;
                attackRangeX[var3] = 120;
                attackRangeY[var3] = 100;
                height[var3] = 20;
                width[var3] = 30;
                direction[var3] = 0;
                state[var3] = STATE_RESPAWN_WAIT;
                timer[var3] = var3 * 7 + var3;
                hp[var3] = maxHp[var3] = MonsterStats.maxHp[type[var3]];
                damage[var3] = MonsterStats.damage[type[var3]];
                money[var3] = MonsterStats.money[type[var3]];
                attackCooldown[var3] = 100;
                vx[var3] = 2;
                vy[var3] = 3;
            }

        }
    }

    /** Thêm {@code amount} con dơi vàng vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnGoldenBats(int spawnGroup, int amount) {
        int var2;
        for(var2 = 0; var2 < count && type[var2] != FREE; ++var2) {
        }

        if (var2 != count) {
            for(int var3 = var2; var3 < var2 + amount; ++var3) {
                group[var3] = spawnGroup;
                type[var3] = TYPE_GOLDEN_BAT;
                attackRangeX[var3] = 120;
                attackRangeY[var3] = 50;
                height[var3] = 20;
                width[var3] = 20;
                direction[var3] = -1;
                state[var3] = STATE_RESPAWN_WAIT;
                timer[var3] = var3 << 1;
                hp[var3] = maxHp[var3] = MonsterStats.maxHp[type[var3]];
                damage[var3] = MonsterStats.damage[type[var3]];
                money[var3] = MonsterStats.money[type[var3]];
                attackCooldown[var3] = 100;
                vx[var3] = -3;
                vy[var3] = 3;
            }

        }
    }

    /** Thêm {@code amount} con ninja gió vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnWindNinjas(int spawnGroup, int amount) {
        int var2;
        for(var2 = 0; var2 < count && type[var2] != FREE; ++var2) {
        }

        if (var2 != count) {
            for(int var3 = var2; var3 < var2 + amount; ++var3) {
                group[var3] = spawnGroup;
                type[var3] = TYPE_WIND_NINJA;
                attackRangeX[var3] = 36;
                attackRangeY[var3] = 20;
                height[var3] = 34;
                width[var3] = 32;
                direction[var3] = 1;
                state[var3] = STATE_RESPAWN_WAIT;
                timer[var3] = var3 * 6;
                hp[var3] = maxHp[var3] = MonsterStats.maxHp[type[var3]];
                damage[var3] = MonsterStats.damage[type[var3]];
                money[var3] = MonsterStats.money[type[var3]];
                attackCooldown[var3] = 100;
            }

        }
    }

    /** Thêm {@code amount} con cá cọp vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. Bản gốc bỏ qua {@code spawnGroup}, luôn dùng nhóm 1. */
    public static void spawnTigerFish(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int var2 = start; var2 < start + amount; ++var2) {
                group[var2] = 1;
                type[var2] = TYPE_TIGER_FISH;
                attackRangeX[var2] = 40;
                attackRangeY[var2] = 30;
                height[var2] = 12;
                width[var2] = 16;
                int[] var10000 = y;
                var10000[var2] += 5;
                direction[var2] = 1;
                state[var2] = STATE_RESPAWN_WAIT;
                timer[var2] = var2 * 5;
                hp[var2] = maxHp[var2] = MonsterStats.maxHp[type[var2]];
                damage[var2] = MonsterStats.damage[type[var2]];
                money[var2] = MonsterStats.money[type[var2]];
                attackCooldown[var2] = 60;
            }

        }
    }

    /** Thêm {@code amount} con cá cọp đỏ vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. Bản gốc bỏ qua {@code spawnGroup}, luôn dùng nhóm 2. */
    public static void spawnRedTigerFish(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int var2 = start; var2 < start + amount; ++var2) {
                group[var2] = 2;
                type[var2] = TYPE_RED_TIGER_FISH;
                attackRangeX[var2] = 80;
                attackRangeY[var2] = 100;
                height[var2] = 12;
                width[var2] = 16;
                direction[var2] = 1;
                state[var2] = STATE_RESPAWN_WAIT;
                timer[var2] = var2 * 5;
                hp[var2] = maxHp[var2] = MonsterStats.maxHp[type[var2]];
                damage[var2] = MonsterStats.damage[type[var2]];
                money[var2] = MonsterStats.money[type[var2]];
                attackCooldown[var2] = 40;
            }

        }
    }

    /** Thêm {@code amount} con ong bò vẽ vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnBees(int spawnGroup, int amount) {
        int var2;
        for(var2 = 0; var2 < count && type[var2] != FREE; ++var2) {
        }

        if (var2 != count) {
            for(int var3 = var2; var3 < var2 + amount; ++var3) {
                group[var3] = spawnGroup;
                type[var3] = TYPE_BEE;
                attackRangeX[var3] = 60;
                attackRangeY[var3] = 60;
                height[var3] = 16;
                width[var3] = 16;
                direction[var3] = -1;
                state[var3] = STATE_RESPAWN_WAIT;
                timer[var3] = var3 << 3;
                hp[var3] = maxHp[var3] = MonsterStats.maxHp[type[var3]];
                damage[var3] = MonsterStats.damage[type[var3]];
                money[var3] = MonsterStats.money[type[var3]];
                attackCooldown[var3] = 100;
                vx[var3] = 2;
                vy[var3] = 3;
            }

        }
    }

    /** Thêm {@code amount} con ong đỏ vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnRedBees(int spawnGroup, int amount) {
        int var2;
        for(var2 = 0; var2 < count && type[var2] != FREE; ++var2) {
        }

        if (var2 != count) {
            for(int var3 = var2; var3 < var2 + amount; ++var3) {
                group[var3] = spawnGroup;
                type[var3] = TYPE_RED_BEE;
                attackRangeX[var3] = 60;
                attackRangeY[var3] = 60;
                height[var3] = 16;
                width[var3] = 16;
                direction[var3] = -1;
                state[var3] = STATE_RESPAWN_WAIT;
                timer[var3] = var3 + 60;
                hp[var3] = maxHp[var3] = MonsterStats.maxHp[type[var3]];
                damage[var3] = MonsterStats.damage[type[var3]];
                money[var3] = MonsterStats.money[type[var3]];
                attackCooldown[var3] = 100;
                vx[var3] = 2;
                vy[var3] = 3;
            }

        }
    }

    /** Thêm {@code amount} con bù nhìn rơm vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnScarecrows(int spawnGroup, int amount) {
        int var2;
        for(var2 = 0; var2 < count && type[var2] != FREE; ++var2) {
        }

        if (var2 != count) {
            for(int var3 = var2; var3 < var2 + amount; ++var3) {
                group[var3] = spawnGroup;
                type[var3] = TYPE_SCARECROW;
                attackRangeX[var3] = 10;
                attackRangeY[var3] = 10;
                height[var3] = 30;
                width[var3] = 25;
                direction[var3] = 1;
                state[var3] = STATE_RESPAWN_WAIT;
                timer[var3] = var3 << 2;
                hp[var3] = maxHp[var3] = MonsterStats.maxHp[type[var3]];
                damage[var3] = MonsterStats.damage[type[var3]];
                money[var3] = MonsterStats.money[type[var3]];
                attackCooldown[var3] = 0;
            }

        }
    }

    /** Thêm {@code amount} con cóc xanh vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. Bản gốc bỏ qua {@code spawnGroup}, luôn dùng nhóm 2. */
    public static void spawnGreenToads(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int var2 = start; var2 < start + amount; ++var2) {
                group[var2] = 2;
                type[var2] = TYPE_GREEN_TOAD;
                attackRangeX[var2] = 20;
                attackRangeY[var2] = 10;
                height[var2] = 18;
                width[var2] = 18;
                direction[var2] = 1;
                state[var2] = STATE_RESPAWN_WAIT;
                timer[var2] = var2 << 3;
                hp[var2] = maxHp[var2] = MonsterStats.maxHp[type[var2]];
                damage[var2] = MonsterStats.damage[type[var2]];
                money[var2] = MonsterStats.money[type[var2]];
                attackCooldown[var2] = 0;
            }

        }
    }

    /** Thêm {@code amount} con ma một mắt vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnOneEyedGhosts(int spawnGroup, int amount) {
        int var2;
        for(var2 = 0; var2 < count && type[var2] != FREE; ++var2) {
        }

        if (var2 != count) {
            for(int var3 = var2; var3 < var2 + amount; ++var3) {
                group[var3] = spawnGroup;
                type[var3] = TYPE_ONE_EYED_GHOST;
                attackRangeX[var3] = 25;
                attackRangeY[var3] = 10;
                height[var3] = 27;
                width[var3] = 15;
                direction[var3] = 1;
                state[var3] = STATE_RESPAWN_WAIT;
                timer[var3] = var3 * 7;
                hp[var3] = maxHp[var3] = MonsterStats.maxHp[type[var3]];
                damage[var3] = MonsterStats.damage[type[var3]];
                money[var3] = MonsterStats.money[type[var3]];
                attackCooldown[var3] = 0;
            }

        }
    }

    /** Thêm {@code amount} con thổ dân vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnNatives(int spawnGroup, int amount) {
        int var2;
        for(var2 = 0; var2 < count && type[var2] != FREE; ++var2) {
        }

        if (var2 != count) {
            for(int var3 = var2; var3 < var2 + amount; ++var3) {
                group[var3] = spawnGroup;
                type[var3] = TYPE_NATIVE;
                attackRangeX[var3] = 36;
                attackRangeY[var3] = 10;
                height[var3] = 27;
                width[var3] = 15;
                direction[var3] = 1;
                state[var3] = STATE_RESPAWN_WAIT;
                timer[var3] = var3 << 1;
                hp[var3] = maxHp[var3] = MonsterStats.maxHp[type[var3]];
                damage[var3] = MonsterStats.damage[type[var3]];
                money[var3] = MonsterStats.money[type[var3]];
                attackCooldown[var3] = 0;
            }

        }
    }

    /** Thêm {@code amount} con ốc sên vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnSnails(int spawnGroup, int amount) {
        int var2;
        for(var2 = 0; var2 < count && type[var2] != FREE; ++var2) {
        }

        if (var2 != count) {
            for(int var3 = var2; var3 < var2 + amount; ++var3) {
                group[var3] = spawnGroup;
                type[var3] = TYPE_SNAIL;
                attackRangeX[var3] = 7;
                attackRangeY[var3] = 10;
                height[var3] = 16;
                width[var3] = 15;
                direction[var3] = 1;
                state[var3] = STATE_RESPAWN_WAIT;
                timer[var3] = var3 << 3;
                hp[var3] = maxHp[var3] = MonsterStats.maxHp[type[var3]];
                damage[var3] = MonsterStats.damage[type[var3]];
                money[var3] = MonsterStats.money[type[var3]];
                attackCooldown[var3] = 100;
            }

        }
    }

    /** Thêm {@code amount} con quỷ hoa vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnFlowerDemons(int spawnGroup, int amount) {
        int var2;
        for(var2 = 0; var2 < count && type[var2] != FREE; ++var2) {
        }

        if (var2 != count) {
            for(int var3 = var2; var3 < var2 + amount; ++var3) {
                group[var3] = spawnGroup;
                type[var3] = TYPE_FLOWER_DEMON;
                attackRangeX[var3] = 120;
                attackRangeY[var3] = 10;
                height[var3] = 24;
                width[var3] = 20;
                direction[var3] = 1;
                state[var3] = STATE_RESPAWN_WAIT;
                timer[var3] = 60;
                hp[var3] = maxHp[var3] = MonsterStats.maxHp[type[var3]];
                damage[var3] = MonsterStats.damage[type[var3]];
                money[var3] = MonsterStats.money[type[var3]];
                attackCooldown[var3] = 100;
            }

        }
    }

    /** Thêm {@code amount} con quỷ tử hoa vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. Bản gốc bỏ qua {@code spawnGroup}, luôn dùng nhóm 0. */
    public static void spawnPurpleFlowerDemons(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int var2 = start; var2 < start + amount; ++var2) {
                group[var2] = 0;
                type[var2] = TYPE_PURPLE_FLOWER_DEMON;
                attackRangeX[var2] = 60;
                attackRangeY[var2] = 30;
                height[var2] = 24;
                width[var2] = 20;
                direction[var2] = 1;
                state[var2] = STATE_RESPAWN_WAIT;
                timer[var2] = 62;
                hp[var2] = maxHp[var2] = MonsterStats.maxHp[type[var2]];
                damage[var2] = MonsterStats.damage[type[var2]];
                money[var2] = MonsterStats.money[type[var2]];
                attackCooldown[var2] = 70;
            }

        }
    }

    /** Thêm {@code amount} con bò cạp vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. */
    public static void spawnScorpions(int spawnGroup, int amount) {
        int var2;
        for(var2 = 0; var2 < count && type[var2] != FREE; ++var2) {
        }

        if (var2 != count) {
            for(int var3 = var2; var3 < var2 + amount; ++var3) {
                group[var3] = spawnGroup;
                type[var3] = TYPE_SCORPION;
                attackRangeX[var3] = 60;
                attackRangeY[var3] = 10;
                height[var3] = 22;
                width[var3] = 22;
                direction[var3] = 1;
                state[var3] = STATE_RESPAWN_WAIT;
                timer[var3] = 50 + (var3 << 1);
                hp[var3] = maxHp[var3] = MonsterStats.maxHp[type[var3]];
                damage[var3] = MonsterStats.damage[type[var3]];
                money[var3] = MonsterStats.money[type[var3]];
                attackCooldown[var3] = 500;
            }

        }
    }

    /** Thêm {@code amount} con rết sa mạc vào các ô trống liên tiếp, thuộc nhóm {@code spawnGroup}. Bản gốc bỏ qua {@code spawnGroup}, luôn dùng nhóm 0. */
    public static void spawnDesertCentipedes(int spawnGroup, int amount) {
        int start;
        for(start = 0; start < count && type[start] != FREE; ++start) {
        }

        if (start != count) {
            for(int var2 = start; var2 < start + amount; ++var2) {
                group[var2] = 0;
                type[var2] = TYPE_DESERT_CENTIPEDE;
                attackRangeX[var2] = 24;
                attackRangeY[var2] = 10;
                height[var2] = 16;
                width[var2] = 22;
                direction[var2] = 1;
                state[var2] = STATE_RESPAWN_WAIT;
                timer[var2] = 70;
                hp[var2] = maxHp[var2] = MonsterStats.maxHp[type[var2]];
                damage[var2] = MonsterStats.damage[type[var2]];
                money[var2] = MonsterStats.money[type[var2]];
                attackCooldown[var2] = 500;
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
        int var0;
        for(var0 = 0; var0 < groupSpriteSets.length; ++var0) {
            spawnUsed[var0] = new boolean[spawnX[var0].length];
        }

        for(var0 = 0; var0 < spawnX.length; ++var0) {
            for(int var1 = 0; var1 < spawnX[var0].length; ++var1) {
                spawnX[var0][var1] = spawnX[var0][var1] * GameMap.TILE_SIZE + 12;
                spawnY[var0][var1] *= GameMap.TILE_SIZE;
                if (var0 == 8) {
                    int[] var10000 = spawnY[var0];
                    var10000[var1] -= 12;
                }
            }
        }

    }}
