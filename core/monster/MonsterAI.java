package core.monster;

import core.app.GameState;
import core.combat.Combat;
import core.fx.WaterSplash;
import core.map.GameMap;
import core.map.TileFlags;
import core.npc.Girl;
import core.player.Player;
import core.player.PlayerAction;
import core.quest.Story;
import core.util.MathUtil;

/** AI và vật lý của quái thường (Monsters). */
public final class MonsterAI {
    private MonsterAI() {
    }

    /** Quái thứ {@code i} chạm nhân vật thì gây sát thương. */
    private static void tryAttackPlayer(int i) {
        if (Monsters.attackCooldown[i] > 30) {
            int left = Monsters.direction[i] > 0 ? Monsters.x[i] : Monsters.x[i] - Monsters.attackRangeX[i];
            int right = Monsters.direction[i] >= 0 ? Monsters.x[i] + Monsters.attackRangeX[i] : Monsters.x[i];
            int top = Monsters.y[i] - Monsters.attackRangeY[i];
            int bottom = Monsters.y[i] + Monsters.attackRangeY[i];
            if (Player.action == PlayerAction.DEAD ? false : (Player.hidden ? false : (Player.action == PlayerAction.BURROWED ? false : (Player.x < left ? false : (Player.x > right ? false : (Player.y < top ? false : Player.y <= bottom)))))) {
                Monsters.attackCooldown[i] = 0;
                Monsters.state[i] = Monsters.STATE_ATTACKING;
                Monsters.timer[i] = 0;
            }
        }

    }

    /** Quái thứ {@code i} chạm cô bé thì gây sát thương. */
    private static void tryAttackGirl(int i) {
        if (Monsters.attackCooldown[i] > 30) {
            int left = Monsters.direction[i] > 0 ? Monsters.x[i] : Monsters.x[i] - Monsters.attackRangeX[i];
            int right = Monsters.direction[i] >= 0 ? Monsters.x[i] + Monsters.attackRangeX[i] : Monsters.x[i];
            int top = Monsters.y[i] - Monsters.attackRangeY[i];
            int bottom = Monsters.y[i] + Monsters.attackRangeY[i];
            if (Girl.action == PlayerAction.BURROWED ? false : (Girl.x < left ? false : (Girl.x > right ? false : (Girl.y < top ? false : Girl.y <= bottom)))) {
                Monsters.attackCooldown[i] = 0;
                Monsters.state[i] = Monsters.STATE_ATTACKING;
                Monsters.timer[i] = 0;
                Monsters.attackingGirl[i] = true;
            }
        }

    }

    /** Cập nhật mọi quái trên map theo trạng thái và loại quái. */
    public static void update() {
        for(int i = 0; i < Monsters.count; ++i) {
            if (Monsters.attackCooldown[i] <= 30) {
                Monsters.attackCooldown[i]++;
            }

            if (Monsters.healthBarTimer[i] > 0) {
                Monsters.healthBarTimer[i]--;
            }

            switch (Monsters.state[i]) {
                case Monsters.STATE_KNOCKED_OUT:
                    Monsters.timer[i]++;
                    Monsters.y[i] += Monsters.timer[i];
                    if (GameState.tick % 2 == 0) {
                        if (Monsters.aux1[i] > 1) {
                            Monsters.aux1[i]--;
                        } else if (Monsters.aux1[i] < -1) {
                            Monsters.aux1[i]++;
                        }
                    }

                    Monsters.x[i] += Monsters.aux1[i];
                    Monsters.frame[i] = 2;
                    if (Monsters.y[i] < GameMap.pixelHeight && Monsters.x[i] >= 0 && Monsters.x[i] < GameMap.pixelWidth) {
                        if ((Monsters.aux2[i] == 0 || Monsters.aux2[i] < 3 && Monsters.timer[i] > 7) && (GameMap.flagsAtPixel(Monsters.x[i], Monsters.y[i]) & TileFlags.SOLID) == TileFlags.SOLID) {
                            Monsters.timer[i] = Monsters.timer[i] > 4 ? -4 : -Monsters.timer[i];
                            Monsters.aux2[i]++;
                        }

                        if (GameMap.hasFlag(Monsters.x[i], Monsters.y[i], TileFlags.WATER) && Monsters.y[i] % GameMap.TILE_SIZE > 8) {
                            WaterSplash.spawn(Monsters.x[i], GameMap.alignToTileY(Monsters.y[i]) + 8);
                        }
                        break;
                    }

                    Monsters.timer[i] = -300;
                    Monsters.state[i] = Monsters.STATE_RESPAWN_WAIT;
                    if (Story.prologueNoRespawn) {
                        Monsters.state[i] = Monsters.STATE_GONE;
                    }

                    Monsters.spawnUsed[Monsters.group[i]][Monsters.spawnPoint[i]] = false;
                    break;
                case Monsters.STATE_ACTIVE:
                    switch (Monsters.type[i]) {
                        case Monsters.TYPE_ONE_EYED_GHOST:
                            if (GameState.tick % 4 == 0) {
                                Monsters.frame[i]++;
                                if (Monsters.frame[i] > 1) {
                                    Monsters.frame[i] = 0;
                                }
                            }

                            Monsters.x[i] += Monsters.direction[i];
                            if (Monsters.x[i] > Monsters.rangeB[i]) {
                                Monsters.direction[i] = -1;
                            } else if (Monsters.x[i] < Monsters.rangeA[i]) {
                                Monsters.direction[i] = 1;
                            }

                            tryAttackPlayer(i);
                            if (GameState.tick % 9 == 0) {
                                tryAttackGirl(i);
                            }
                            continue;
                        case Monsters.TYPE_SNAIL:
                            patrolSlow(i);
                            continue;
                        case Monsters.TYPE_DEMON_BAT:
                            Monsters.x[i] += Monsters.vx[i];
                            Monsters.y[i] += Monsters.vy[i];
                            if (Monsters.x[i] - Monsters.rangeA[i] > 40) {
                                Monsters.vx[i] = -(GameState.tick % 3 + 1);
                            }

                            if (Monsters.x[i] - Monsters.rangeA[i] < -40) {
                                Monsters.vx[i] = GameState.tick % 3 + 1;
                            }

                            if (Monsters.y[i] - Monsters.rangeB[i] > 40) {
                                Monsters.vy[i] = -(GameState.tick % 3 + 1);
                            }

                            if (Monsters.y[i] - Monsters.rangeB[i] < -40) {
                                Monsters.vy[i] = GameState.tick % 3 + 1;
                            }

                            if (GameState.tick % 15 == 0) {
                                Monsters.vx[i] = GameState.tick % 4 - 1;
                                Monsters.vy[i] = (GameState.tick >> 1) % 4 - 1;
                            }

                            if (GameState.tick % 2 == 0) {
                                Monsters.frame[i]++;
                                if (Monsters.frame[i] > 1) {
                                    Monsters.frame[i] = 0;
                                }

                                if (Monsters.frame[i] == 0) {
                                    Monsters.y[i] -= 3;
                                } else {
                                    Monsters.y[i] += 2;
                                }
                            }

                            tryAttackPlayer(i);
                            continue;
                        case Monsters.TYPE_ONE_LEGGED_DEMON:
                            Monsters.x[i] += Monsters.timer[i];
                            if (Monsters.timer[i] > 1) {
                                Monsters.timer[i]--;
                            } else if (Monsters.timer[i] < -1) {
                                Monsters.timer[i]++;
                            }

                            Monsters.y[i] += Monsters.aux1[i];
                            Monsters.aux1[i]++;
                            if (Monsters.aux1[i] > 4) {
                                Monsters.aux1[i] = -4;
                                Monsters.timer[i] = Monsters.direction[i] * 3;
                            }

                            if (MathUtil.abs(Monsters.aux1[i]) == 3) {
                                if (Monsters.x[i] > Monsters.rangeB[i]) {
                                    Monsters.direction[i] = -1;
                                } else if (Monsters.x[i] < Monsters.rangeA[i]) {
                                    Monsters.direction[i] = 1;
                                }

                                Monsters.frame[i] = 0;
                            } else {
                                Monsters.frame[i] = 1;
                            }

                            tryAttackPlayer(i);
                            if (GameState.tick % 9 == 0) {
                                tryAttackGirl(i);
                            }
                            continue;
                        case Monsters.TYPE_GREEN_TOAD:
                            Monsters.x[i] += Monsters.aux2[i];
                            if (Monsters.aux2[i] > 1) {
                                Monsters.aux2[i]--;
                            } else if (Monsters.aux2[i] < -1) {
                                Monsters.aux2[i]++;
                            }

                            Monsters.y[i] += Monsters.aux1[i];
                            Monsters.aux1[i]++;
                            if (Monsters.aux1[i] > 3) {
                                Monsters.aux1[i] = -3;
                                Monsters.aux2[i] = Monsters.direction[i] << 2;
                                Monsters.timer[i] = 0;
                                Monsters.y[i] = Monsters.spawnY[Monsters.group[i]][Monsters.spawnPoint[i]];
                                Monsters.state[i] = Monsters.STATE_RESTING;
                            }

                            if (Monsters.aux1[i] == -2) {
                                if (Monsters.x[i] > Monsters.rangeB[i]) {
                                    Monsters.direction[i] = -1;
                                } else if (Monsters.x[i] < Monsters.rangeA[i]) {
                                    Monsters.direction[i] = 1;
                                }

                                Monsters.aux2[i] = Monsters.direction[i] * 6;
                                Monsters.frame[i] = 0;
                            } else {
                                Monsters.frame[i] = 1;
                            }

                            tryAttackPlayer(i);
                            if (GameState.tick % 9 == 0) {
                                tryAttackGirl(i);
                            }
                            continue;
                        case Monsters.TYPE_SPIDER:
                            if (GameState.tick % 4 == 0) {
                                Monsters.frame[i]++;
                                if (Monsters.frame[i] > 1) {
                                    Monsters.frame[i] = 0;
                                }

                                Monsters.y[i] += Monsters.timer[i];
                            }

                            if (Monsters.y[i] > Monsters.rangeB[i]) {
                                Monsters.timer[i] = -1;
                            } else if (Monsters.y[i] < Monsters.rangeA[i]) {
                                Monsters.timer[i] = 1;
                            }

                            tryAttackPlayer(i);
                            continue;
                        case Monsters.TYPE_BEE:
                            hover(i);
                        case Monsters.TYPE_SCARECROW:
                        default:
                            continue;
                        case Monsters.TYPE_TIGER_FISH:
                            Monsters.frame[i]++;
                            if (Monsters.frame[i] > 1) {
                                Monsters.frame[i] = 0;
                            }

                            Monsters.x[i] += GameState.tick % 5 * Monsters.direction[i];
                            if (Monsters.x[i] > Monsters.rangeB[i]) {
                                Monsters.direction[i] = -1;
                            } else if (Monsters.x[i] < Monsters.rangeA[i]) {
                                Monsters.direction[i] = 1;
                            }

                            tryAttackPlayer(i);
                            continue;
                        case Monsters.TYPE_WIND_NINJA:
                            patrolFast(i);
                            continue;
                        case Monsters.TYPE_RED_TIGER_FISH:
                            Monsters.frame[i]++;
                            if (Monsters.frame[i] > 1) {
                                Monsters.frame[i] = 0;
                            }

                            Monsters.x[i] += 5 * Monsters.direction[i];
                            if (Monsters.x[i] > Monsters.rangeB[i]) {
                                Monsters.direction[i] = -1;
                            } else if (Monsters.x[i] < Monsters.rangeA[i]) {
                                Monsters.direction[i] = 1;
                            }

                            tryAttackPlayer(i);
                            continue;
                        case Monsters.TYPE_ONE_EYED_GHOST_2:
                            if (GameState.tick % 4 == 0) {
                                Monsters.frame[i]++;
                                Monsters.timer[i] = 0;
                                if (Monsters.frame[i] > 1) {
                                    Monsters.frame[i] = 0;
                                    Monsters.timer[i] = 1;
                                }
                            }

                            Monsters.x[i] += Monsters.timer[i] * Monsters.direction[i];
                            if (Monsters.x[i] > Monsters.rangeB[i]) {
                                Monsters.direction[i] = -1;
                            } else if (Monsters.x[i] < Monsters.rangeA[i]) {
                                Monsters.direction[i] = 1;
                            }

                            tryAttackPlayer(i);
                            if (GameState.tick % 9 == 0) {
                                tryAttackGirl(i);
                            }
                            continue;
                        case Monsters.TYPE_DESERT_CENTIPEDE:
                            patrolFast(i);
                            continue;
                        case Monsters.TYPE_GOLDEN_BAT:
                            Monsters.x[i] += Monsters.vx[i];
                            if (Monsters.x[i] - Monsters.rangeA[i] > 100) {
                                Monsters.direction[i] = -1;
                                Monsters.vx[i] = Monsters.direction[i] * (GameState.tick % 4 + 1);
                                Monsters.x[i] += Monsters.direction[i] << 2;
                            } else if (Monsters.x[i] - Monsters.rangeA[i] < -100) {
                                Monsters.direction[i] = 1;
                                Monsters.vx[i] = Monsters.direction[i] * (GameState.tick % 4 + 1);
                                Monsters.x[i] += Monsters.direction[i] << 2;
                            }

                            if (Monsters.y[i] - Monsters.rangeB[i] > 20) {
                                Monsters.vy[i] = -(GameState.tick % 4 + 1);
                            } else if (Monsters.y[i] - Monsters.rangeB[i] < -20) {
                                Monsters.vy[i] = GameState.tick % 4 + 1;
                            } else {
                                Monsters.vy[i] = 0;
                            }

                            Monsters.y[i] += Monsters.vy[i];
                            if (GameState.tick % 2 == 0) {
                                Monsters.frame[i]++;
                                if (Monsters.frame[i] > 1) {
                                    Monsters.frame[i] = 0;
                                    Monsters.y[i] -= 12;
                                } else {
                                    Monsters.y[i] += 12;
                                }
                            }

                            tryAttackPlayer(i);
                            continue;
                        case Monsters.TYPE_RAT:
                            Monsters.frame[i]++;
                            if (Monsters.frame[i] > 1) {
                                Monsters.frame[i] = 0;
                            }

                            if (GameState.tick % 2 == 0) {
                                Monsters.x[i] += Monsters.direction[i] << 2;
                            } else {
                                Monsters.x[i] += Monsters.direction[i] << 1;
                            }

                            if (Monsters.x[i] > Monsters.rangeB[i]) {
                                Monsters.direction[i] = -1;
                            } else if (Monsters.x[i] < Monsters.rangeA[i]) {
                                Monsters.direction[i] = 1;
                            }

                            tryAttackPlayer(i);
                            if (GameState.tick % 5 == 0) {
                                tryAttackGirl(i);
                            }
                            continue;
                        case Monsters.TYPE_NATIVE:
                            Monsters.x[i] += Monsters.timer[i];
                            if (Monsters.timer[i] > 1) {
                                Monsters.timer[i]--;
                            } else if (Monsters.timer[i] < -1) {
                                Monsters.timer[i]++;
                            }

                            Monsters.y[i] += Monsters.aux1[i];
                            Monsters.aux1[i]++;
                            if (Monsters.aux1[i] > 4) {
                                Monsters.aux1[i] = -4;
                                Monsters.timer[i] = Monsters.direction[i] * 3;
                            }

                            if (MathUtil.abs(Monsters.aux1[i]) == 3) {
                                if (Monsters.x[i] > Monsters.rangeB[i]) {
                                    Monsters.direction[i] = -1;
                                } else if (Monsters.x[i] < Monsters.rangeA[i]) {
                                    Monsters.direction[i] = 1;
                                }

                                Monsters.frame[i] = 0;
                            } else {
                                Monsters.frame[i] = 1;
                            }

                            tryAttackPlayer(i);
                            if (GameState.tick % 9 == 0) {
                                tryAttackGirl(i);
                            }
                            continue;
                        case Monsters.TYPE_RED_BEE:
                            hover(i);
                            continue;
                        case Monsters.TYPE_SCORPION:
                            patrolSlow(i);
                            continue;
                        case Monsters.TYPE_FLOWER_DEMON:
                        case Monsters.TYPE_PURPLE_FLOWER_DEMON:
                            if (GameState.tick % 4 == 0) {
                                Monsters.frame[i]++;
                                if (Monsters.frame[i] > 1) {
                                    Monsters.frame[i] = 0;
                                }
                            }

                            if (Monsters.x[i] > Player.x) {
                                Monsters.direction[i] = -1;
                            } else {
                                Monsters.direction[i] = 1;
                            }

                            tryAttackPlayer(i);
                            if (GameState.tick % 9 == 0) {
                                tryAttackGirl(i);
                            }
                            continue;
                    }
                case Monsters.STATE_RESTING:
                    Monsters.timer[i]++;
                    if (Monsters.timer[i] >= 35) {
                        Monsters.timer[i] = 0;
                        Monsters.state[i] = Monsters.STATE_ACTIVE;
                    }

                    Monsters.frame[i] = 0;
                    tryAttackPlayer(i);
                    break;
                case Monsters.STATE_ATTACKING:
                    if (Player.action == PlayerAction.BURROWED || Player.hidden) {
                        Monsters.frame[i] = 0;
                        Monsters.state[i] = Monsters.STATE_ACTIVE;
                        Monsters.timer[i] = 0;
                        Monsters.aux1[i] = 0;
                    }

                    switch (Monsters.type[i]) {
                        case Monsters.TYPE_ONE_EYED_GHOST:
                            Monsters.timer[i]++;
                            if (Monsters.timer[i] == 2) {
                                if (Monsters.attackingGirl[i]) {
                                    Monsters.attackingGirl[i] = false;
                                    Combat.damageGirl(Monsters.damage[i], Monsters.direction[i]);
                                } else {
                                    Combat.damagePlayer(Monsters.damage[i], Monsters.direction[i]);
                                }
                            }

                            if ((Monsters.timer[i] >> 2) % 2 == 0) {
                                Monsters.x[i] += Monsters.direction[i];
                                Monsters.frame[i] = 3;
                            } else {
                                Monsters.frame[i] = 0;
                                Monsters.x[i] -= Monsters.direction[i];
                            }

                            if (Monsters.timer[i] == 12) {
                                Monsters.state[i] = Monsters.STATE_RESTING;
                                Monsters.timer[i] = 0;
                            }
                            continue;
                        case Monsters.TYPE_SNAIL:
                            Combat.damagePlayer(Monsters.damage[i], -Player.facing);
                            Monsters.state[i] = Monsters.STATE_RESTING;
                            Monsters.timer[i] = 0;
                            Monsters.frame[i] = 2;
                            continue;
                        case Monsters.TYPE_DEMON_BAT:
                            if (Monsters.x[i] < Player.x) {
                                Monsters.vx[i] = 3 + GameState.tick % 4;
                            } else if (Monsters.x[i] > Player.x) {
                                Monsters.vx[i] = -(3 + GameState.tick % 4);
                            }

                            if (Monsters.y[i] < Player.y) {
                                Monsters.vy[i] = GameState.tick % 3;
                            } else if (Monsters.y[i] > Player.y) {
                                Monsters.vy[i] = -GameState.tick % 3;
                            }

                            Monsters.x[i] += Monsters.vx[i];
                            Monsters.y[i] += Monsters.vy[i];
                            if (GameState.tick % 2 == 0) {
                                Monsters.frame[i]++;
                                if (Monsters.frame[i] > 1) {
                                    Monsters.frame[i] = 0;
                                }
                            }

                            if (MathUtil.abs(Monsters.x[i] - Player.x) < 5 && MathUtil.abs(Monsters.y[i] - Player.y) < 5) {
                                Monsters.attackCooldown[i] = 0;
                                Combat.damagePlayer(Monsters.damage[i], -Player.facing);
                                Monsters.timer[i] = 0;
                                Monsters.state[i] = Monsters.STATE_ACTIVE;
                            }

                            if (MathUtil.abs(Monsters.x[i] - Player.x) > 160 || MathUtil.abs(Monsters.y[i] - Player.y) > 160) {
                                Monsters.state[i] = Monsters.STATE_ACTIVE;
                                Monsters.timer[i] = 0;
                                Monsters.vx[i] = Monsters.vy[i] = 0;
                                Monsters.attackCooldown[i] = 0;
                            }
                            continue;
                        case Monsters.TYPE_ONE_LEGGED_DEMON:
                            Monsters.timer[i]++;
                            if (Monsters.timer[i] == 3) {
                                if (Monsters.attackingGirl[i]) {
                                    Monsters.attackingGirl[i] = false;
                                    Combat.damageGirl(Monsters.damage[i], Monsters.direction[i]);
                                } else {
                                    Combat.damagePlayer(Monsters.damage[i], Monsters.direction[i]);
                                }
                            }

                            if ((Monsters.timer[i] >> 2) % 2 == 0) {
                                Monsters.x[i] += Monsters.direction[i];
                                Monsters.frame[i] = 0;
                            } else {
                                Monsters.frame[i] = 3;
                                Monsters.x[i] -= Monsters.direction[i];
                            }

                            if (Monsters.timer[i] == 12) {
                                Monsters.y[i] = Monsters.spawnY[Monsters.group[i]][Monsters.spawnPoint[i]];
                                Monsters.state[i] = Monsters.STATE_RESTING;
                                Monsters.aux1[i] = -4;
                                Monsters.timer[i] = 0;
                            }
                            continue;
                        case Monsters.TYPE_GREEN_TOAD:
                            Monsters.timer[i]++;
                            if (Monsters.timer[i] == 3) {
                                if (Monsters.attackingGirl[i]) {
                                    Monsters.attackingGirl[i] = false;
                                    Combat.damageGirl(Monsters.damage[i], Monsters.direction[i]);
                                } else {
                                    Combat.damagePlayer(Monsters.damage[i], Monsters.direction[i]);
                                }
                            }

                            if ((Monsters.timer[i] >> 2) % 2 == 0) {
                                Monsters.x[i] += Monsters.direction[i];
                                Monsters.frame[i] = 0;
                            } else {
                                Monsters.frame[i] = 1;
                                Monsters.x[i] -= Monsters.direction[i];
                            }

                            if (Monsters.timer[i] == 12) {
                                Monsters.y[i] = Monsters.spawnY[Monsters.group[i]][Monsters.spawnPoint[i]];
                                Monsters.state[i] = Monsters.STATE_RESTING;
                                Monsters.aux1[i] = -4;
                                Monsters.timer[i] = 0;
                            }
                            continue;
                        case Monsters.TYPE_SPIDER:
                            Combat.damagePlayer(Monsters.damage[i], -Player.facing);
                            Monsters.state[i] = Monsters.STATE_ACTIVE;
                            Monsters.timer[i] = 1;
                            continue;
                        case Monsters.TYPE_BEE:
                            chasePlayer(i);
                        case Monsters.TYPE_SCARECROW:
                        default:
                            continue;
                        case Monsters.TYPE_TIGER_FISH:
                            Monsters.timer[i]++;
                            if (Monsters.timer[i] <= 10) {
                                if (Monsters.y[i] > Player.y) {
                                    Monsters.y[i] -= 4;
                                }

                                if (Monsters.x[i] < Player.x) {
                                    Monsters.x[i] += 2;
                                } else if (Monsters.x[i] > Player.x) {
                                    Monsters.x[i] -= 2;
                                }

                                if (MathUtil.abs(Monsters.x[i] - Player.x) < 16 && MathUtil.abs(Monsters.y[i] - (Player.y - 16)) < 20) {
                                    Combat.damagePlayer(Monsters.damage[i], -Player.facing);
                                    Monsters.timer[i] = 10;
                                }
                            }

                            if (Monsters.timer[i] == 10) {
                                Monsters.aux1[i] = 1;
                            }

                            if (Monsters.timer[i] > 10) {
                                Monsters.y[i] += Monsters.aux1[i];
                                Monsters.aux1[i]++;
                                if (Monsters.y[i] > Monsters.spawnY[Monsters.group[i]][Monsters.spawnPoint[i]]) {
                                    Monsters.y[i] = Monsters.spawnY[Monsters.group[i]][Monsters.spawnPoint[i]];
                                    Monsters.aux1[i] = 0;
                                    Monsters.timer[i] = 0;
                                    Monsters.state[i] = Monsters.STATE_ACTIVE;
                                }
                            }

                            Monsters.frame[i] = Monsters.timer[i] % 2 == 0 ? 0 : 1;
                            continue;
                        case Monsters.TYPE_WIND_NINJA:
                            Monsters.timer[i]++;
                            if (Monsters.timer[i] < 6) {
                                Monsters.x[i] += Monsters.direction[i] << 2;
                            } else if (Monsters.timer[i] == 6) {
                                if (MathUtil.abs(Monsters.x[i] + (Monsters.direction[i] << 3) - Player.x) < 13 + Player.halfWidth) {
                                    Combat.damagePlayer(Monsters.damage[i], Monsters.direction[i]);
                                }
                            } else if (Monsters.timer[i] < 12) {
                                Monsters.x[i] -= Monsters.direction[i] << 1;
                            } else if (Monsters.timer[i] == 12) {
                                Monsters.y[i] = Monsters.spawnY[Monsters.group[i]][Monsters.spawnPoint[i]];
                                Monsters.state[i] = Monsters.STATE_RESTING;
                                Monsters.aux1[i] = -4;
                                Monsters.timer[i] = 0;
                            }

                            Monsters.frame[i] = 1;
                            if (Monsters.timer[i] > 3 && Monsters.timer[i] < 9 && Monsters.timer[i] % 2 == 0) {
                                Monsters.frame[i] = 3;
                            }
                            continue;
                        case Monsters.TYPE_RED_TIGER_FISH:
                            Monsters.timer[i]++;
                            Monsters.frame[i] = 3;
                            if (Monsters.timer[i] <= 15) {
                                Monsters.y[i] -= 7;
                                if (Monsters.x[i] < Player.x) {
                                    Monsters.x[i]++;
                                } else if (Monsters.x[i] > Player.x) {
                                    Monsters.x[i]--;
                                }

                                if (MathUtil.abs(Monsters.x[i] - Player.x) < 16 && MathUtil.abs(Monsters.y[i] - (Player.y - 16)) < 20) {
                                    Combat.damagePlayer(Monsters.damage[i], -Player.facing);
                                    Monsters.timer[i] = 15;
                                }
                            }

                            if (Monsters.timer[i] == 15) {
                                Monsters.aux1[i] = 1;
                            }

                            if (Monsters.timer[i] > 15) {
                                Monsters.frame[i] = 2;
                                Monsters.y[i] += Monsters.aux1[i];
                                Monsters.aux1[i]++;
                                if (MathUtil.abs(Monsters.x[i] - Player.x) < 16 && MathUtil.abs(Monsters.y[i] - (Player.y - 16)) < 20) {
                                    Combat.damagePlayer(Monsters.damage[i], -Player.facing);
                                }

                                if (Monsters.y[i] > Monsters.spawnY[Monsters.group[i]][Monsters.spawnPoint[i]]) {
                                    Monsters.y[i] = Monsters.spawnY[Monsters.group[i]][Monsters.spawnPoint[i]];
                                    Monsters.aux1[i] = 0;
                                    Monsters.timer[i] = 0;
                                    Monsters.state[i] = Monsters.STATE_ACTIVE;
                                }
                            }
                            continue;
                        case Monsters.TYPE_ONE_EYED_GHOST_2:
                            Monsters.timer[i]++;
                            if (Monsters.timer[i] == 4 && Monsters.y[i] == Player.y) {
                                Combat.damagePlayer(Monsters.damage[i], Monsters.direction[i]);
                            }

                            if (Monsters.timer[i] < 8) {
                                Monsters.x[i] += Monsters.direction[i] << 1;
                                Monsters.frame[i] = 3;
                            } else {
                                Monsters.frame[i] = 0;
                                Monsters.x[i] -= Monsters.direction[i] << 1;
                            }

                            if (Monsters.timer[i] == 12) {
                                Monsters.state[i] = Monsters.STATE_RESTING;
                                Monsters.timer[i] = 0;
                                Monsters.attackCooldown[i] = 25;
                            }
                            continue;
                        case Monsters.TYPE_DESERT_CENTIPEDE:
                            hitPlayer(i);
                            continue;
                        case Monsters.TYPE_GOLDEN_BAT:
                            if (Monsters.x[i] < Player.x) {
                                Monsters.vx[i] = 4 + GameState.tick % 6;
                            } else if (Monsters.x[i] > Player.x) {
                                Monsters.vx[i] = -(4 + GameState.tick % 6);
                            }

                            if (Monsters.y[i] < Player.y) {
                                Monsters.vy[i] = 2 + GameState.tick % 4;
                            } else if (Monsters.y[i] > Player.y) {
                                Monsters.vy[i] = -(2 + GameState.tick % 4);
                            }

                            Monsters.x[i] += Monsters.vx[i];
                            Monsters.y[i] += Monsters.vy[i];
                            if (GameState.tick % 2 == 0) {
                                Monsters.frame[i]++;
                                if (Monsters.frame[i] > 1) {
                                    Monsters.frame[i] = 0;
                                }
                            }

                            if (MathUtil.abs(Monsters.x[i] - Player.x) < 5 && MathUtil.abs(Monsters.y[i] - Player.y) < 5) {
                                Combat.damagePlayer(Monsters.damage[i], -Player.facing);
                                Monsters.state[i] = Monsters.STATE_ACTIVE;
                                Monsters.timer[i] = 0;
                                Monsters.direction[i] = Monsters.x[i] > Monsters.rangeA[i] ? -1 : 1;
                                Monsters.vx[i] = Monsters.direction[i] * (GameState.tick % 4 + 1);
                                Monsters.attackCooldown[i] = 10;
                            }

                            if (MathUtil.abs(Monsters.x[i] - Player.x) > 130 || MathUtil.abs(Monsters.y[i] - Player.y) > 130) {
                                Monsters.state[i] = Monsters.STATE_ACTIVE;
                                Monsters.timer[i] = 0;
                                Monsters.vx[i] = Monsters.vy[i] = 0;
                                Monsters.direction[i] = Monsters.x[i] > Monsters.rangeA[i] ? -1 : 1;
                                Monsters.vx[i] = Monsters.direction[i] * (GameState.tick % 4 + 1);
                                Monsters.attackCooldown[i] = 0;
                            }
                            continue;
                        case Monsters.TYPE_RAT:
                            hitPlayer(i);
                            continue;
                        case Monsters.TYPE_NATIVE:
                            Monsters.timer[i]++;
                            if (Monsters.timer[i] == 3) {
                                if (Monsters.attackingGirl[i]) {
                                    Monsters.attackingGirl[i] = false;
                                    Combat.damageGirl(Monsters.damage[i], Monsters.direction[i]);
                                } else {
                                    Combat.damagePlayer(Monsters.damage[i], Monsters.direction[i]);
                                }
                            }

                            if ((Monsters.timer[i] >> 2) % 2 == 0) {
                                Monsters.x[i] += Monsters.direction[i];
                                Monsters.frame[i] = 0;
                            } else {
                                Monsters.frame[i] = 3;
                                Monsters.x[i] -= Monsters.direction[i];
                            }

                            if (Monsters.timer[i] == 12) {
                                Monsters.y[i] = Monsters.spawnY[Monsters.group[i]][Monsters.spawnPoint[i]];
                                Monsters.state[i] = Monsters.STATE_RESTING;
                                Monsters.aux1[i] = -4;
                                Monsters.timer[i] = 0;
                            }
                            continue;
                        case Monsters.TYPE_RED_BEE:
                            chasePlayer(i);
                            continue;
                        case Monsters.TYPE_SCORPION:
                        case Monsters.TYPE_FLOWER_DEMON:
                            Monsters.timer[i]++;
                            Monsters.frame[i] = 3;
                            if (Monsters.timer[i] == 3) {
                                Projectiles.spawn(Monsters.x[i] + (Monsters.direction[i] << 3), Monsters.y[i] - 12, Monsters.direction[i] << 3, Monsters.damage[i], Projectiles.TYPE_ENERGY_BALL);
                            } else if (Monsters.timer[i] == 6) {
                                Monsters.state[i] = Monsters.STATE_RESTING;
                                Monsters.timer[i] = 0;
                            }
                            continue;
                        case Monsters.TYPE_PURPLE_FLOWER_DEMON:
                            Monsters.timer[i]++;
                            Monsters.frame[i] = 3;
                            if (Monsters.timer[i] == 3) {
                                Monsters.aux1[i]++;
                                if (Monsters.aux1[i] == 3) {
                                    Monsters.aux1[i] = 0;
                                }

                                if (Monsters.aux1[i] == 2) {
                                    ArcProjectiles.spawn(Monsters.x[i], Monsters.y[i] - 22, Monsters.direction[i], -10, Monsters.damage[i]);
                                } else if (Monsters.aux1[i] == 0) {
                                    ArcProjectiles.spawn(Monsters.x[i], Monsters.y[i] - 22, Monsters.direction[i] << 1, -9, Monsters.damage[i]);
                                } else {
                                    ArcProjectiles.spawn(Monsters.x[i], Monsters.y[i] - 22, 0, -11, Monsters.damage[i]);
                                }

                                WaterSplash.spawn(Monsters.x[i], Monsters.y[i] - 20);
                            } else if (Monsters.timer[i] == 6) {
                                Monsters.state[i] = Monsters.STATE_RESTING;
                                Monsters.timer[i] = 0;
                            }
                            continue;
                    }
                case Monsters.STATE_RESPAWN_WAIT:
                    Monsters.timer[i]++;
                    if (Monsters.timer[i] >= Monsters.respawnDelay) {
                        Monsters.timer[i] = 0;
                        int spawnIndex = GameState.tick % Monsters.spawnX[Monsters.group[i]].length;
                        if (Monsters.spawnUsed[Monsters.group[i]][spawnIndex]) {
                            Monsters.timer[i] = Monsters.respawnDelay;
                        } else {
                            Monsters.x[i] = Monsters.spawnX[Monsters.group[i]][spawnIndex];
                            Monsters.y[i] = Monsters.spawnY[Monsters.group[i]][spawnIndex];
                            Monsters.spawnUsed[Monsters.group[i]][spawnIndex] = true;
                            Monsters.spawnPoint[i] = spawnIndex;
                            Monsters.state[i] = Monsters.STATE_ACTIVE;
                            Monsters.hp[i] = Monsters.maxHp[i];
                            switch (Monsters.type[i]) {
                                case Monsters.TYPE_ONE_EYED_GHOST:
                                    Monsters.rangeA[i] = Monsters.x[i] - 20;
                                    Monsters.rangeB[i] = Monsters.x[i] + 20;
                                    break;
                                case Monsters.TYPE_SNAIL:
                                    Monsters.rangeA[i] = Monsters.x[i] - 10;
                                    Monsters.rangeB[i] = Monsters.x[i] + 10;
                                    break;
                                case Monsters.TYPE_DEMON_BAT:
                                    Monsters.rangeA[i] = Monsters.x[i] + 12;
                                    Monsters.rangeB[i] = Monsters.y[i] + 12;
                                    break;
                                case Monsters.TYPE_ONE_LEGGED_DEMON:
                                    Monsters.rangeA[i] = Monsters.x[i] - 20;
                                    Monsters.rangeB[i] = Monsters.x[i] + 20;
                                    Monsters.aux1[i] = -4;
                                case Monsters.TYPE_GREEN_TOAD:
                                    Monsters.rangeA[i] = Monsters.x[i] - 20;
                                    Monsters.rangeB[i] = Monsters.x[i] + 20;
                                    Monsters.aux1[i] = -3;
                                    break;
                                case Monsters.TYPE_SPIDER:
                                    Monsters.rangeA[i] = Monsters.y[i] - 10;
                                    Monsters.rangeB[i] = Monsters.y[i] + 10;
                                    Monsters.timer[i] = 1;
                                    break;
                                case Monsters.TYPE_BEE:
                                    Monsters.rangeA[i] = Monsters.x[i];
                                    Monsters.rangeB[i] = Monsters.y[i];
                                    break;
                                case Monsters.TYPE_SCARECROW:
                                    Monsters.state[i] = Monsters.STATE_RESTING;
                                    break;
                                case Monsters.TYPE_TIGER_FISH:
                                case Monsters.TYPE_WIND_NINJA:
                                case Monsters.TYPE_ONE_EYED_GHOST_2:
                                case Monsters.TYPE_DESERT_CENTIPEDE:
                                    Monsters.rangeA[i] = Monsters.x[i] - 20;
                                    Monsters.rangeB[i] = Monsters.x[i] + 20;
                                    break;
                                case Monsters.TYPE_RED_TIGER_FISH:
                                    Monsters.rangeA[i] = Monsters.x[i] - 30;
                                    Monsters.rangeB[i] = Monsters.x[i] + 30;
                                    break;
                                case Monsters.TYPE_GOLDEN_BAT:
                                    Monsters.rangeA[i] = Monsters.x[i] + 12;
                                    Monsters.rangeB[i] = Monsters.y[i] + 12;
                                    break;
                                case Monsters.TYPE_RAT:
                                    Monsters.rangeA[i] = Monsters.x[i] - 40;
                                    Monsters.rangeB[i] = Monsters.x[i] + 40;
                                    break;
                                case Monsters.TYPE_NATIVE:
                                    Monsters.rangeA[i] = Monsters.x[i] - 20;
                                    Monsters.rangeB[i] = Monsters.x[i] + 20;
                                    Monsters.aux1[i] = -4;
                                    break;
                                case Monsters.TYPE_RED_BEE:
                                    Monsters.rangeA[i] = Monsters.x[i];
                                    Monsters.rangeB[i] = Monsters.y[i];
                                    break;
                                case Monsters.TYPE_SCORPION:
                                    Monsters.rangeA[i] = Monsters.x[i] - 30;
                                    Monsters.rangeB[i] = Monsters.x[i] + 30;
                            }
                        }
                    }
                    break;
                case Monsters.STATE_HURT:
                    Monsters.healthBarTimer[i] = 100;
                    Monsters.frame[i] = 2;
                    switch (Monsters.type[i]) {
                        case Monsters.TYPE_ONE_EYED_GHOST:
                            if (Monsters.attackCooldown[i] < 30) {
                                Monsters.x[i] -= Monsters.direction[i] << 1;
                            } else {
                                Monsters.x[i] -= Monsters.direction[i];
                            }

                            Monsters.timer[i]++;
                            if (Monsters.timer[i] == 1) {
                                Monsters.y[i] -= 2;
                            } else if (Monsters.timer[i] == 5) {
                                Monsters.y[i] += 2;
                                Monsters.state[i] = Monsters.STATE_ACTIVE;
                            }
                            break;
                        case Monsters.TYPE_SNAIL:
                            recoil5(i);
                            break;
                        case Monsters.TYPE_DEMON_BAT:
                            Monsters.timer[i]++;
                            Monsters.x[i] -= Monsters.vx[i] >> 3;
                            Monsters.y[i] -= Monsters.vy[i] >> 3;
                            if (Monsters.vx[i] == 0) {
                                Monsters.vx[i] = 2;
                            }

                            if (Monsters.vy[i] == 0) {
                                Monsters.vy[i] = 2;
                            }

                            if (Monsters.timer[i] == 5) {
                                Monsters.state[i] = Monsters.STATE_ACTIVE;
                                Monsters.timer[i] = 0;
                                Monsters.vx[i] = Monsters.vy[i] = 0;
                                Monsters.attackCooldown[i] = 10;
                            }
                            break;
                        case Monsters.TYPE_ONE_LEGGED_DEMON:
                            Monsters.x[i] -= Monsters.direction[i];
                            Monsters.frame[i] = 2;
                            Monsters.timer[i]++;
                            if (Monsters.timer[i] == 7) {
                                Monsters.y[i] = Monsters.spawnY[Monsters.group[i]][Monsters.spawnPoint[i]];
                                Monsters.state[i] = Monsters.STATE_ACTIVE;
                                Monsters.aux1[i] = -4;
                                Monsters.timer[i] = Monsters.direction[i] * 3;
                            }
                            break;
                        case Monsters.TYPE_GREEN_TOAD:
                            Monsters.x[i] -= Monsters.direction[i];
                            Monsters.frame[i] = 2;
                            Monsters.timer[i]++;
                            if (Monsters.timer[i] == 7) {
                                Monsters.y[i] = Monsters.spawnY[Monsters.group[i]][Monsters.spawnPoint[i]];
                                Monsters.state[i] = Monsters.STATE_ACTIVE;
                                Monsters.aux1[i] = -4;
                                Monsters.timer[i] = Monsters.direction[i] * 3;
                            }
                            break;
                        case Monsters.TYPE_SPIDER:
                            Monsters.timer[i]++;
                            Monsters.y[i]--;
                            if (Monsters.timer[i] == 3) {
                                Monsters.state[i] = Monsters.STATE_ACTIVE;
                                Monsters.timer[i] = 1;
                            }

                            Monsters.frame[i] = 2;
                            break;
                        case Monsters.TYPE_BEE:
                            flyBack(i);
                            break;
                        case Monsters.TYPE_SCARECROW:
                            Monsters.timer[i]++;
                            Monsters.direction[i] = -Player.facing;
                            if (Monsters.timer[i] % 4 > 1) {
                                Monsters.frame[i] = 0;
                            } else {
                                Monsters.frame[i] = 2;
                            }

                            if (Monsters.timer[i] == 8) {
                                Monsters.state[i] = Monsters.STATE_RESTING;
                                Monsters.direction[i] = 0;
                                Monsters.vx[i] = Monsters.vy[i] = 0;
                                Monsters.attackCooldown[i] = 0;
                            }
                            break;
                        case Monsters.TYPE_TIGER_FISH:
                            Monsters.state[i] = Monsters.STATE_ATTACKING;
                            break;
                        case Monsters.TYPE_WIND_NINJA:
                            Monsters.x[i] -= Monsters.direction[i];
                            Monsters.frame[i] = 2;
                            Monsters.timer[i]++;
                            if (Monsters.timer[i] == 6) {
                                Monsters.y[i] = Monsters.spawnY[Monsters.group[i]][Monsters.spawnPoint[i]];
                                Monsters.state[i] = Monsters.STATE_ATTACKING;
                                Monsters.timer[i] = 4;
                                Monsters.x[i] += Monsters.direction[i] << 3;
                            }
                            break;
                        case Monsters.TYPE_RED_TIGER_FISH:
                            Monsters.state[i] = Monsters.STATE_ATTACKING;
                            break;
                        case Monsters.TYPE_ONE_EYED_GHOST_2:
                            Monsters.x[i] -= Monsters.direction[i];
                            Monsters.frame[i] = 2;
                            Monsters.timer[i]++;
                            if (Monsters.timer[i] == 6) {
                                Monsters.y[i] = Monsters.spawnY[Monsters.group[i]][Monsters.spawnPoint[i]];
                                Monsters.state[i] = Monsters.STATE_ATTACKING;
                                Monsters.timer[i] = 0;
                                Monsters.x[i] += Monsters.direction[i] << 3;
                            }
                            break;
                        case Monsters.TYPE_DESERT_CENTIPEDE:
                            recoil5(i);
                            break;
                        case Monsters.TYPE_GOLDEN_BAT:
                            Monsters.timer[i]++;
                            Monsters.x[i] -= Monsters.vx[i] >> 3;
                            Monsters.y[i] -= Monsters.vy[i] >> 3;
                            if (Monsters.vx[i] == 0) {
                                Monsters.vx[i] = 2;
                            }

                            if (Monsters.vy[i] == 0) {
                                Monsters.vy[i] = 2;
                            }

                            if (Monsters.timer[i] == 5) {
                                Monsters.state[i] = Monsters.STATE_ACTIVE;
                                Monsters.timer[i] = 0;
                                Monsters.direction[i] = Monsters.x[i] > Monsters.rangeA[i] ? -1 : 1;
                                Monsters.vx[i] = Monsters.direction[i] << 3;
                                Monsters.attackCooldown[i] = 0;
                            }
                            break;
                        case Monsters.TYPE_RAT:
                            recoil3(i);
                            break;
                        case Monsters.TYPE_NATIVE:
                            Monsters.x[i] -= Monsters.direction[i];
                            Monsters.frame[i] = 2;
                            Monsters.timer[i]++;
                            if (Monsters.timer[i] == 3) {
                                Monsters.y[i] = Monsters.spawnY[Monsters.group[i]][Monsters.spawnPoint[i]];
                                Monsters.state[i] = Monsters.STATE_ACTIVE;
                                Monsters.aux1[i] = -4;
                                Monsters.timer[i] = Monsters.direction[i] * 3;
                            }
                            break;
                        case Monsters.TYPE_RED_BEE:
                            flyBack(i);
                            break;
                        case Monsters.TYPE_SCORPION:
                            recoil3(i);
                            break;
                        case Monsters.TYPE_FLOWER_DEMON:
                            Monsters.timer[i]++;
                            Monsters.x[i] -= Monsters.direction[i];
                            if (Monsters.timer[i] == 4) {
                                Monsters.x[i] += Monsters.direction[i] << 2;
                                Monsters.state[i] = Monsters.STATE_ACTIVE;
                            }
                            break;
                        case Monsters.TYPE_PURPLE_FLOWER_DEMON:
                            Monsters.timer[i]++;
                            Monsters.x[i] -= Monsters.direction[i];
                            if (Monsters.timer[i] == 4) {
                                Monsters.x[i] += Monsters.direction[i] << 2;
                                Monsters.timer[i] = 0;
                                Monsters.state[i] = Monsters.STATE_ATTACKING;
                            }
                    }
                case Monsters.STATE_UNUSED_7:
                case Monsters.STATE_GONE:
                default:
                    break;
                case Monsters.STATE_LAUNCHED:
                    Monsters.y[i] += Monsters.timer[i];
                    Monsters.timer[i]++;
                    Monsters.frame[i] = 2;
                    if (Monsters.y[i] > Monsters.spawnY[Monsters.group[i]][Monsters.spawnPoint[i]]) {
                        if (Monsters.type[i] != Monsters.TYPE_DEMON_BAT && Monsters.type[i] != Monsters.TYPE_BEE && Monsters.type[i] != Monsters.TYPE_RED_BEE && Monsters.type[i] != Monsters.TYPE_GOLDEN_BAT) {
                            Monsters.y[i] = Monsters.spawnY[Monsters.group[i]][Monsters.spawnPoint[i]];
                        }

                        Monsters.state[i] = Monsters.STATE_RESTING;
                    }
            }
        }

    }

    /** Lao theo nhân vật; chạm thì gây sát thương (cho cô bé nếu đang nhắm cô bé), cách quá 160 pixel thì thôi đuổi. */
    private static void chasePlayer(int i) {
        if (Monsters.x[i] < Player.x) {
            Monsters.vx[i] = GameState.tick % 3 + 3;
            Monsters.direction[i] = 1;
        } else if (Monsters.x[i] > Player.x) {
            Monsters.vx[i] = -GameState.tick % 3 - 3;
            Monsters.direction[i] = -1;
        }

        if (Monsters.y[i] < Player.y) {
            Monsters.vy[i] = GameState.tick % 3 + 3;
        } else if (Monsters.y[i] > Player.y) {
            Monsters.vy[i] = -GameState.tick % 3 - 3;
        }

        Monsters.x[i] += Monsters.vx[i];
        Monsters.y[i] += Monsters.vy[i];
        Monsters.frame[i]++;
        if (Monsters.frame[i] > 1) {
            Monsters.frame[i] = 0;
        }

        if (MathUtil.abs(Monsters.x[i] - Player.x) < 10 && MathUtil.abs(Monsters.y[i] - Player.y) < 10) {
            Monsters.attackCooldown[i] = 0;
            if (Monsters.attackingGirl[i]) {
                Monsters.attackingGirl[i] = false;
                Combat.damageGirl(Monsters.damage[i], Monsters.direction[i]);
            } else {
                Combat.damagePlayer(Monsters.damage[i], Monsters.direction[i]);
            }

            Monsters.timer[i] = 0;
            Monsters.state[i] = Monsters.STATE_ACTIVE;
        }

        if (MathUtil.abs(Monsters.x[i] - Player.x) > 160 || MathUtil.abs(Monsters.y[i] - Player.y) > 160) {
            Monsters.state[i] = Monsters.STATE_ACTIVE;
            Monsters.timer[i] = 0;
            Monsters.vx[i] = Monsters.vy[i] = 0;
            Monsters.attackCooldown[i] = 0;
        }

    }

    /** Bay lùi ngược hướng vừa lao tới trong 5 frame. */
    private static void flyBack(int i) {
        Monsters.timer[i]++;
        Monsters.x[i] -= Monsters.vx[i];
        Monsters.y[i] -= Monsters.vy[i];
        if (Monsters.vx[i] == 0) {
            Monsters.vx[i] = 2;
        }

        if (Monsters.vy[i] == 0) {
            Monsters.vy[i] = 2;
        }

        if (Monsters.timer[i] == 5) {
            Monsters.state[i] = Monsters.STATE_ACTIVE;
            Monsters.timer[i] = 0;
            Monsters.vx[i] = Monsters.vy[i] = 0;
            Monsters.attackCooldown[i] = 0;
        }

    }

    /** Bay lượn ngẫu nhiên quanh điểm gốc (rangeA, rangeB), lệch tối đa 40 pixel; chạm thì gây sát thương. */
    private static void hover(int i) {
        Monsters.x[i] += Monsters.vx[i];
        Monsters.y[i] += Monsters.vy[i];
        if (Monsters.x[i] - Monsters.rangeA[i] > 40) {
            Monsters.vx[i] = -(GameState.tick % 2 + 1);
        }

        if (Monsters.x[i] - Monsters.rangeA[i] < -40) {
            Monsters.vx[i] = GameState.tick % 2 + 1;
        }

        if (Monsters.y[i] - Monsters.rangeB[i] > 40) {
            Monsters.vy[i] = -(GameState.tick % 2 + 1);
        }

        if (Monsters.y[i] - Monsters.rangeB[i] < -40) {
            Monsters.vy[i] = GameState.tick % 2 + 1;
        }

        if (GameState.tick % 15 == 0) {
            Monsters.vx[i] = GameState.tick % 3 - 1;
            Monsters.vy[i] = (GameState.tick >> 1) % 3 - 1;
        }

        Monsters.frame[i]++;
        if (Monsters.frame[i] > 1) {
            Monsters.frame[i] = 0;
        }

        tryAttackPlayer(i);
        if (GameState.tick % 5 == 0) {
            tryAttackGirl(i);
        }

    }

    /** Đi tuần qua lại trong [rangeA, rangeB], 2 pixel mỗi 4 frame; chạm nhân vật thì gây sát thương. */
    private static void patrolFast(int i) {
        if (GameState.tick % 4 == 1) {
            Monsters.frame[i]++;
            if (Monsters.frame[i] > 1) {
                Monsters.frame[i] = 0;
            }

            Monsters.x[i] += Monsters.direction[i] << 1;
            if (Monsters.x[i] > Monsters.rangeB[i]) {
                Monsters.direction[i] = -1;
            } else if (Monsters.x[i] < Monsters.rangeA[i]) {
                Monsters.direction[i] = 1;
            }
        }

        tryAttackPlayer(i);
    }

    /** Gây sát thương cho nhân vật theo hướng của quái. */
    private static void hitPlayer(int i) {
        Combat.damagePlayer(Monsters.damage[i], -Player.facing);
        Monsters.state[i] = Monsters.STATE_ACTIVE;
        Monsters.timer[i] = 0;
        Monsters.frame[i] = 2;
    }

    /** Lùi lại 5 frame rồi trở về trạng thái hoạt động. */
    private static void recoil5(int i) {
        Monsters.timer[i]++;
        Monsters.x[i] -= Monsters.direction[i];
        if (Monsters.timer[i] == 5) {
            Monsters.state[i] = Monsters.STATE_ACTIVE;
        }

    }

    /** Lùi lại 3 frame rồi trở về trạng thái hoạt động. */
    private static void recoil3(int i) {
        Monsters.timer[i]++;
        Monsters.x[i] -= Monsters.direction[i];
        if (Monsters.timer[i] == 3) {
            Monsters.state[i] = Monsters.STATE_ACTIVE;
        }

    }

    /** Đi tuần qua lại trong [rangeA, rangeB], 1 pixel mỗi 4 frame; chạm nhân vật / cô bé thì gây sát thương. */
    private static void patrolSlow(int i) {
        if (GameState.tick % 4 == 0) {
            Monsters.frame[i]++;
            if (Monsters.frame[i] > 1) {
                Monsters.frame[i] = 0;
            }

            Monsters.x[i] += Monsters.direction[i];
        }

        if (Monsters.x[i] > Monsters.rangeB[i]) {
            Monsters.direction[i] = -1;
        } else if (Monsters.x[i] < Monsters.rangeA[i]) {
            Monsters.direction[i] = 1;
        }

        tryAttackPlayer(i);
        if (GameState.tick % 5 == 0) {
            tryAttackGirl(i);
        }

    }
}
