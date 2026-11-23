package core.monster;

import assets.Mob;
import javax.microedition.lcdui.Image;

/**
 * Quái của từng map: bộ ảnh mỗi nhóm, điểm xuất hiện (theo ô tile) và số lượng từng loại.
 * Gọi khi nạp map (Midlet.run).
 */
public final class MonsterSpawns {
    private MonsterSpawns() {
    }

    public static void setup(int mapId) {
        Monsters.count = 0;
        int start;
        switch (mapId) {
            case 0:
                Monsters.groupSpriteSets = new int[]{7};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{68, 71, 74, 64};
                Monsters.spawnY[0] = new int[]{15, 15, 15, 17};
                Monsters.finishGroups();
                Monsters.init(2);
                Monsters.spawnScarecrows(0, 2);
                break;
            case 1:
                Monsters.groupSpriteSets = new int[]{1, 7, 0};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{44, 40, 42, 39, 41, 39, 34};
                Monsters.spawnY[0] = new int[]{10, 11, 9, 9, 7, 5, 9};
                Monsters.spawnX[1] = new int[]{48, 51};
                Monsters.spawnY[1] = new int[]{9, 9};
                Monsters.spawnX[2] = new int[]{8, 3, 24, 25, 26};
                Monsters.spawnY[2] = new int[]{9, 9, 10, 8, 6};
                Monsters.finishGroups();
                Monsters.init(7);
                Monsters.spawnSnails(0, 3);
                Monsters.spawnScarecrows(1, 1);
                Monsters.spawnOneEyedGhosts(2, 3);
                break;
            case 2:
                Monsters.groupSpriteSets = new int[]{1, 6};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{32, 41, 44, 48};
                Monsters.spawnY[0] = new int[]{13, 13, 11, 11};
                Monsters.spawnX[1] = new int[]{41, 47, 57, 36};
                Monsters.spawnY[1] = new int[]{9, 7, 7, 8};
                Monsters.finishGroups();
                Monsters.init(4);
                Monsters.spawnSnails(0, 2);
                Monsters.spawnBees(1, 2);
                break;
            case 3:
                Monsters.groupSpriteSets = new int[]{1, 6, 0};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{6, 10, 17, 26, 42, 83, 96};
                Monsters.spawnY[0] = new int[]{9, 10, 10, 10, 10, 10, 11};
                Monsters.spawnX[1] = new int[]{10, 19, 24, 32, 41, 49, 59, 68, 80, 87, 110};
                Monsters.spawnY[1] = new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 7};
                Monsters.spawnX[2] = new int[]{17, 24, 28, 35, 42, 49, 59, 69, 78, 86, 97, 107, 113};
                Monsters.spawnY[2] = new int[]{10, 10, 8, 8, 10, 9, 9, 9, 10, 10, 8, 8, 9};
                Monsters.finishGroups();
                Monsters.init(20);
                Monsters.spawnSnails(0, 4);
                Monsters.spawnBees(1, 8);
                Monsters.spawnOneEyedGhosts(2, 8);
                break;
            case 4:
                Monsters.groupSpriteSets = new int[]{6, 0, 4, 9, 1};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{29, 26, 37, 43, 58, 67, 11, 74, 57};
                Monsters.spawnY[0] = new int[]{11, 10, 11, 10, 8, 3, 8, 7, 7};
                Monsters.spawnX[1] = new int[]{21, 39, 37, 45, 25, 62, 58};
                Monsters.spawnY[1] = new int[]{12, 12, 12, 8, 12, 9, 5};
                Monsters.spawnX[2] = new int[]{29, 33, 68, 42, 57};
                Monsters.spawnY[2] = new int[]{13, 12, 7, 12, 9};
                Monsters.spawnX[3] = new int[]{55, 59, 53};
                Monsters.spawnY[3] = new int[]{5, 5, 5};
                Monsters.spawnX[4] = new int[]{19, 11};
                Monsters.spawnY[4] = new int[]{12, 11};
                Monsters.finishGroups();
                Monsters.init(20);
                Monsters.spawnBees(0, 6);
                Monsters.spawnOneEyedGhosts(1, 6);
                Monsters.spawnGreenToads(2, 4);
                Monsters.spawnWindNinjas(3, 2);
                Monsters.spawnSnails(4, 2);
                break;
            case 5:
                Monsters.groupSpriteSets = new int[]{0, 3, 4};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{7, 14, 7, 14, 7, 6, 15, 9};
                Monsters.spawnY[0] = new int[]{9, 13, 18, 28, 29, 35, 42, 70};
                Monsters.spawnX[1] = new int[]{6, 4, 14, 4, 16, 7, 15, 11};
                Monsters.spawnY[1] = new int[]{14, 26, 33, 44, 47, 62, 65, 78};
                Monsters.spawnX[2] = new int[]{14, 14, 7, 12, 8, 14, 8};
                Monsters.spawnY[2] = new int[]{23, 39, 56, 62, 66, 70, 78};
                Monsters.finishGroups();
                Monsters.init(20);
                Monsters.spawnOneEyedGhosts(0, 7);

                for(start = 0; start < Monsters.count && Monsters.type[start] != Monsters.FREE; ++start) {
                }

                if (start != Monsters.count) {
                    for(int i = start; i < start + 7; ++i) {
                        Monsters.group[i] = 1;
                        Monsters.type[i] = Monsters.TYPE_ONE_LEGGED_DEMON;
                        Monsters.attackRangeX[i] = 30;
                        Monsters.attackRangeY[i] = 10;
                        Monsters.height[i] = 27;
                        Monsters.width[i] = 15;
                        Monsters.direction[i] = 1;
                        Monsters.state[i] = Monsters.STATE_RESPAWN_WAIT;
                        Monsters.timer[i] = i << 3;
                        Monsters.hp[i] = Monsters.maxHp[i] = MonsterStats.maxHp[Monsters.type[i]];
                        Monsters.damage[i] = MonsterStats.damage[Monsters.type[i]];
                        Monsters.money[i] = MonsterStats.money[Monsters.type[i]];
                        Monsters.attackCooldown[i] = 0;
                    }
                }

                Monsters.spawnGreenToads(2, 6);
                break;
            case 6:
                Monsters.groupSpriteSets = new int[]{9, 2, 5};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{42, 47, 72, 82, 85, 93, 104, 130, 139, 151, 165, 182, 189, 200, 207, 214, 225, 225, 237};
                Monsters.spawnY[0] = new int[]{10, 8, 10, 8, 6, 6, 6, 9, 9, 7, 4, 7, 9, 10, 10, 8, 4, 10, 8};
                Monsters.spawnX[1] = new int[]{39, 40, 41, 72, 73, 74, 132, 106, 107, 130, 131, 142, 149, 157, 168, 182, 183, 194, 214, 215, 216, 236, 39, 40, 56, 57};
                Monsters.spawnY[1] = new int[]{5, 5, 6, 6, 4, 3, 2, 3, 4, 5, 5, 5, 2, 1, 3, 4, 5, 6, 6, 5, 4, 5, 6, 6, 6, 6};
                Monsters.spawnX[2] = new int[]{45, 64, 83, 101, 119, 147, 173, 220};
                Monsters.spawnY[2] = new int[]{6, 8, 4, 3, 6, 7, 5, 4};
                Monsters.finishGroups();
                Monsters.init(26);
                Monsters.spawnWindNinjas(0, 5);
                Monsters.spawnDemonBats(1, 18);

                for(start = 0; start < Monsters.count && Monsters.type[start] != Monsters.FREE; ++start) {
                }

                if (start != Monsters.count) {
                    for(int i = start; i < start + 3; ++i) {
                        Monsters.group[i] = 2;
                        Monsters.type[i] = Monsters.TYPE_SPIDER;
                        Monsters.attackRangeX[i] = 7;
                        Monsters.attackRangeY[i] = 10;
                        Monsters.height[i] = 16;
                        Monsters.width[i] = 15;
                        Monsters.direction[i] = 0;
                        Monsters.state[i] = Monsters.STATE_RESPAWN_WAIT;
                        Monsters.timer[i] = i << 3;
                        Monsters.hp[i] = Monsters.maxHp[i] = MonsterStats.maxHp[Monsters.type[i]];
                        Monsters.damage[i] = MonsterStats.damage[Monsters.type[i]];
                        Monsters.money[i] = MonsterStats.money[Monsters.type[i]];
                        Monsters.attackCooldown[i] = 100;
                    }
                }
                break;
            case 7:
                Monsters.groupSpriteSets = new int[]{9, 8, 10};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{38, 39, 47, 52, 56, 61, 66, 73, 71};
                Monsters.spawnY[0] = new int[]{5, 5, 3, 8, 4, 4, 4, 4, 8};
                Monsters.spawnX[1] = new int[]{16, 23, 26, 29, 32, 35, 38, 41, 44, 47, 56, 59, 61, 64, 66};
                Monsters.spawnY[1] = new int[]{11, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
                Monsters.spawnX[2] = new int[]{36, 42, 45, 487, 56, 60, 64};
                Monsters.spawnY[2] = new int[]{11, 11, 11, 11, 11, 11, 11};
                Monsters.finishGroups();
                Monsters.init(23);
                Monsters.spawnWindNinjas(0, 7);
                Monsters.spawnTigerFish(1, 10);
                Monsters.spawnRedTigerFish(2, 6);
                break;
            case 8:
                Monsters.groupSpriteSets = new int[]{11, 8, 10, 2};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{12, 29, 6, 12, 24, 18, 37, 50, 65, 72, 47};
                Monsters.spawnY[0] = new int[]{10, 8, 12, 12, 11, 12, 10, 8, 8, 7, 7};
                Monsters.spawnX[1] = new int[]{29, 45, 48, 70, 73};
                Monsters.spawnY[1] = new int[]{12, 12, 12, 12, 12};
                Monsters.spawnX[2] = new int[]{46, 47, 71, 72};
                Monsters.spawnY[2] = new int[]{13, 13, 13, 13};
                Monsters.spawnX[3] = new int[]{25, 7, 69, 69, 50, 51};
                Monsters.spawnY[3] = new int[]{25, 8, 6, 7, 5, 5};
                Monsters.finishGroups();
                Monsters.init(22);

                for(start = 0; start < Monsters.count && Monsters.type[start] != Monsters.FREE; ++start) {
                }

                if (start != Monsters.count) {
                    for(int i = start; i < start + 10; ++i) {
                        Monsters.group[i] = 0;
                        Monsters.type[i] = Monsters.TYPE_ONE_EYED_GHOST_2;
                        Monsters.attackRangeX[i] = 32;
                        Monsters.attackRangeY[i] = 10;
                        Monsters.height[i] = 27;
                        Monsters.width[i] = 15;
                        Monsters.direction[i] = 1;
                        Monsters.state[i] = Monsters.STATE_RESPAWN_WAIT;
                        Monsters.timer[i] = Monsters.respawnDelay - i;
                        Monsters.hp[i] = Monsters.maxHp[i] = MonsterStats.maxHp[Monsters.type[i]];
                        Monsters.damage[i] = MonsterStats.damage[Monsters.type[i]];
                        Monsters.money[i] = MonsterStats.money[Monsters.type[i]];
                        Monsters.attackCooldown[i] = 0;
                    }
                }

                Monsters.spawnTigerFish(1, 4);
                Monsters.spawnRedTigerFish(2, 3);
                Monsters.spawnDemonBats(3, 5);
                break;
            case 9:
                Monsters.groupSpriteSets = new int[]{13};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{10, 37, 23, 65};
                Monsters.spawnY[0] = new int[]{7, 6, 6, 4};
                Monsters.finishGroups();
                Monsters.init(3);
                Monsters.spawnGoldenBats(0, 3);
                break;
            case 10:
                Monsters.groupSpriteSets = new int[]{13};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{31, 12, 25, 12};
                Monsters.spawnY[0] = new int[]{19, 23, 23, 6};
                Monsters.finishGroups();
                Monsters.init(3);
                Monsters.spawnGoldenBats(0, 3);
                break;
            case 11:
                Monsters.groupSpriteSets = new int[]{14, 8};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{15, 16, 17, 31, 32, 33, 68, 69, 72};
                Monsters.spawnY[0] = new int[]{12, 12, 12, 12, 8, 8, 8, 8, 8};
                Monsters.spawnX[1] = new int[]{47, 48, 50, 49};
                Monsters.spawnY[1] = new int[]{12, 12, 12, 12};
                Monsters.finishGroups();
                Monsters.init(10);

                for(start = 0; start < Monsters.count && Monsters.type[start] != Monsters.FREE; ++start) {
                }

                if (start != Monsters.count) {
                    for(int i = start; i < start + 7; ++i) {
                        Monsters.group[i] = 0;
                        Monsters.type[i] = Monsters.TYPE_RAT;
                        Monsters.attackRangeX[i] = 7;
                        Monsters.attackRangeY[i] = 10;
                        Monsters.height[i] = 16;
                        Monsters.width[i] = 15;
                        Monsters.direction[i] = 1;
                        Monsters.state[i] = Monsters.STATE_RESPAWN_WAIT;
                        Monsters.timer[i] = i << 3;
                        Monsters.hp[i] = Monsters.maxHp[i] = MonsterStats.maxHp[Monsters.type[i]];
                        Monsters.damage[i] = MonsterStats.damage[Monsters.type[i]];
                        Monsters.money[i] = MonsterStats.money[Monsters.type[i]];
                        Monsters.attackCooldown[i] = 500;
                    }
                }

                Monsters.spawnTigerFish(1, 3);
                break;
            case 12:
                Monsters.groupSpriteSets = new int[]{15, 16, 10};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{11, 18, 20, 9, 9, 17, 18, 22, 31};
                Monsters.spawnY[0] = new int[]{7, 5, 10, 14, 17, 15, 20, 19, 21};
                Monsters.spawnX[1] = new int[]{5, 10, 5, 10, 14, 21, 16, 37};
                Monsters.spawnY[1] = new int[]{6, 12, 15, 19, 16, 17, 9, 17};
                Monsters.spawnX[2] = new int[]{25, 26, 27, 34};
                Monsters.spawnY[2] = new int[]{23, 23, 23, 23};
                Monsters.finishGroups();
                Monsters.init(19);
                Monsters.spawnNatives(0, 7);
                Monsters.spawnRedBees(1, 8);
                Monsters.spawnRedTigerFish(2, 4);
                break;
            case 13:
                Monsters.groupSpriteSets = new int[]{18, 15, 13};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{6, 12, 30, 34, 34, 43, 54, 53, 70, 76};
                Monsters.spawnY[0] = new int[]{9, 11, 11, 10, 5, 5, 5, 11, 11, 11};
                Monsters.spawnX[1] = new int[]{11, 16, 18, 29, 50, 38, 48, 58, 62, 74};
                Monsters.spawnY[1] = new int[]{11, 9, 7, 5, 11, 5, 5, 5, 5, 11};
                Monsters.spawnX[2] = new int[]{22, 41, 42, 61};
                Monsters.spawnY[2] = new int[]{9, 8, 2, 8};
                Monsters.finishGroups();
                Monsters.init(18);
                Monsters.spawnFlowerDemons(0, 8);
                Monsters.spawnNatives(1, 6);
                Monsters.spawnGoldenBats(2, 4);
                break;
            case 14:
                Monsters.groupSpriteSets = new int[]{12, 17, 16};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{7, 12, 16, 25, 28, 45, 50};
                Monsters.spawnY[0] = new int[]{4, 7, 11, 14, 8, 14, 9};
                Monsters.spawnX[1] = new int[]{14, 18, 29, 28, 29, 53, 47, 51};
                Monsters.spawnY[1] = new int[]{7, 11, 12, 8, 4, 14, 9, 4};
                Monsters.spawnX[2] = new int[]{4, 41, 26, 26, 34, 41, 49, 56};
                Monsters.spawnY[2] = new int[]{4, 9, 11, 2, 2, 5, 7, 2};
                Monsters.finishGroups();
                Monsters.init(19);
                Monsters.spawnDesertCentipedes(0, 6);
                Monsters.spawnScorpions(1, 7);
                Monsters.spawnRedBees(2, 6);
                break;
            case 15:
                Monsters.groupSpriteSets = new int[]{19, 18, 17};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{22, 7, 19, 32};
                Monsters.spawnY[0] = new int[]{28, 25, 28, 14};
                Monsters.spawnX[1] = new int[]{3, 13, 31, 35, 24};
                Monsters.spawnY[1] = new int[]{6, 25, 25, 21, 27};
                Monsters.spawnX[2] = new int[]{35, 29, 24, 17, 13, 2};
                Monsters.spawnY[2] = new int[]{8, 11, 9, 7, 7, 17};
                Monsters.finishGroups();
                Monsters.init(15);
                Monsters.spawnPurpleFlowerDemons(0, 4);
                Monsters.spawnFlowerDemons(1, 5);
                Monsters.spawnScorpions(2, 6);
                break;
            case 16:
                Monsters.groupSpriteSets = new int[]{19, 13};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{15, 33, 53};
                Monsters.spawnY[0] = new int[]{12, 10, 10};
                Monsters.spawnX[1] = new int[]{20, 39, 59};
                Monsters.spawnY[1] = new int[]{10, 8, 8};
                Monsters.finishGroups();
                Monsters.init(6);
                Monsters.spawnPurpleFlowerDemons(0, 3);
                Monsters.spawnGoldenBats(1, 3);
                break;
            case 17:
                Monsters.groupSpriteSets = new int[]{12};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{8, 14, 39, 45};
                Monsters.spawnY[0] = new int[]{12, 12, 9, 16};
                Monsters.finishGroups();
                Monsters.init(4);
                Monsters.spawnDesertCentipedes(0, 4);
                break;
            case 95:
                Monsters.groupSpriteSets = new int[]{13};
                Monsters.allocGroups();
                Monsters.spawnX[0] = new int[]{7, 10, 14, 18};
                Monsters.spawnY[0] = new int[]{8, 8, 8, 8};
                Monsters.finishGroups();
                Monsters.init(4);
                Monsters.spawnGoldenBats(0, 4);
        }

        if (Monsters.count > 0) {
            Monsters.sprites = new Image[Monsters.groupSpriteSets.length][][];

            for(int i = 0; i < Monsters.groupSpriteSets.length; ++i) {
                Monsters.sprites[i] = Mob.loadMonster(Monsters.groupSpriteSets[i]);
            }
        }

    }
}
