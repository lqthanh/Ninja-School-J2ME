package core.npc;

import core.app.GameState;
import core.combat.Combat;
import core.fx.Blood;
import core.fx.Dust;
import core.fx.FloatingText;
import core.map.GameMap;
import core.map.TileFlags;
import core.monster.Monsters;
import core.player.Darts;
import core.player.Player;
import core.player.PlayerAction;
import core.player.PlayerStats;
import core.ui.Dialogue;
import core.ui.Hud;
import core.util.MathUtil;

/** AI của bạn học (đi lại, đánh quái) và trận đấu với Toniku. */
public final class ClassmateAI {
    private ClassmateAI() {
    }

    /** Nhát chém của Toniku trúng nhân vật ở gần. */
    private static void tonikuSlashPlayer(int unused) {
        if (Classmates.y[0] == Player.y && MathUtil.abs(Classmates.x[0] + (Classmates.facing[0] << 5) - Player.x) < 25) {
            Combat.damagePlayer(Toniku.damage, Classmates.facing[0]);
        }

    }

    /** Nhát chém của bạn học thứ {@code i} trúng quái đang nhắm. */
    private static void hitMonster(int i) {
        int damage = PlayerStats.attack;
        int monster = Classmates.targetMonster[i];
        int direction = -Classmates.facing[i];
        boolean killed = false;
        if (Monsters.state[monster] == Monsters.STATE_ACTIVE || Monsters.state[monster] == Monsters.STATE_RESTING || Monsters.state[monster] == Monsters.STATE_ATTACKING) {
            Monsters.hp[monster] -= damage;
            if (Monsters.hp[monster] <= 0) {
                Monsters.hp[monster] = 0;
                Monsters.timer[monster] = -5;
                Monsters.aux1[monster] = -(direction << 3);
                Monsters.aux2[monster] = 0;
                Monsters.healthBarTimer[monster] = 0;
                Monsters.state[monster] = Monsters.STATE_KNOCKED_OUT;
                killed = true;
            } else {
                Monsters.timer[monster] = 0;
                Monsters.direction[monster] = direction;
                Monsters.state[monster] = Monsters.STATE_HURT;
                Monsters.frame[monster] = 3;
            }
        }

        if (!killed) {
            Blood.spawn(Monsters.x[monster], Monsters.y[monster] - (Monsters.height[monster] >> 1), Monsters.direction[monster]);
            FloatingText.spawn("-" + damage, 0, Monsters.x[monster], Monsters.y[monster] - Monsters.height[monster], 0, -2);
        }
    }

    /** Tìm quái ở gần để bạn học thứ {@code i} đánh. */
    private static void findMonster(int i) {
        for(int m = 0; m < Monsters.count; ++m) {
            if (Monsters.state[m] != Monsters.STATE_RESPAWN_WAIT && Monsters.y[m] == Classmates.y[i] && MathUtil.abs(Classmates.x[i] + (Classmates.facing[i] << 3) - Monsters.x[m]) < 20) {
                if (Monsters.x[m] < Classmates.x[i]) {
                    Classmates.facing[i] = -1;
                } else {
                    Classmates.facing[i] = 1;
                }

                Classmates.state[i] = Classmates.STATE_ATTACK;
                Classmates.animTimer[i] = Classmates.target[i] = 0;
                Classmates.targetMonster[i] = m;
            }
        }

    }

    /** Cập nhật bạn học: đứng / đi lang thang, tụ tập khi có lệnh (Classmates.gathering), đánh quái ở gần. */
    public static void update() {
        for(int i = 0; i < Classmates.count; ++i) {
            switch (Classmates.state[i]) {
                case Classmates.STATE_IDLE:
                    Classmates.animTimer[i]++;
                    if (Classmates.animTimer[i] > 30) {
                        Classmates.animTimer[i] = 0;
                    }

                    if (Classmates.animTimer[i] % (i + 18) < i + 6) {
                        Classmates.pose[i] = 0;
                    } else {
                        Classmates.pose[i] = 1;
                    }

                    if (Classmates.gathering) {
                        Classmates.target[i] = Classmates.gatherX;
                        Classmates.facing[i] = Classmates.gatherX > Classmates.x[i] ? 1 : -1;
                        Classmates.vx[i] = Classmates.facing[i] << 2;
                        Classmates.vx[i] = Classmates.facing[i] > 0 ? Classmates.vx[i] + i % 2 : Classmates.vx[i] - i % 2;
                        Classmates.state[i] = Classmates.STATE_WALK;
                    } else {
                        if (Classmates.stayPut) {
                            break;
                        }

                        Classmates.target[i]++;
                        if (Classmates.target[i] > (Classmates.wanderRange[i] + Classmates.x[i]) % 20 + (Classmates.wanderRange[i] >> 1)) {
                            if (Dialogue.visible && Player.nearClassmate == i) {
                                break;
                            }

                            Classmates.target[i] = 0;
                            int offset;
                            if ((offset = (GameState.tick + Classmates.wanderRange[i]) % 120 - 60) > 0) {
                                offset += Classmates.wanderRange[i];
                            } else {
                                offset -= Classmates.wanderRange[i];
                            }

                            Classmates.target[i] = Classmates.x[i] + offset;
                            Classmates.facing[i] = offset > 0 ? 1 : -1;
                            Classmates.vx[i] = Classmates.facing[i] << 2;
                            Classmates.state[i] = Classmates.STATE_WALK;
                            if (Player.nearClassmate == i) {
                                Player.nearClassmate = -1;
                                Hud.rightSoftKeyLabel = " ";
                                Hud.dirty = true;
                            }
                        }

                        if (GameState.tick % 10 == 1) {
                            findMonster(i);
                        }
                    }
                    break;
                case Classmates.STATE_WALK:
                    Classmates.animTimer[i]++;
                    if (Classmates.animTimer[i] >= 10) {
                        Classmates.animTimer[i] = 0;
                    }

                    Classmates.pose[i] = (Classmates.animTimer[i] >> 1) + 2;
                    Classmates.x[i] += Classmates.vx[i];
                    if (Classmates.x[i] <= Classmates.maxX[i] && Classmates.x[i] >= Classmates.minX[i] && MathUtil.abs(Classmates.x[i] - Classmates.target[i]) >= 8) {
                        if (GameState.tick % 10 == 0) {
                            findMonster(i);
                        }
                    } else {
                        Classmates.animTimer[i] = Classmates.target[i] = 0;
                        Classmates.x[i] -= Classmates.vx[i];
                        Classmates.vx[i] = 0;
                        Classmates.state[i] = Classmates.STATE_IDLE;
                    }
                case Classmates.STATE_JUMP:
                case Classmates.STATE_FALL:
                default:
                    break;
                case Classmates.STATE_ATTACK:
                    Classmates.animTimer[i]++;
                    switch (Classmates.target[i]) {
                        case Player.COMBO_FIRST_SLASH:
                            switch (Classmates.animTimer[i]) {
                                case 1:
                                case 2:
                                case 3:
                                    Classmates.slashEffect[i] = -1;
                                    Classmates.pose[i] = 13;
                                    continue;
                                case 4:
                                    Classmates.slashEffect[i] = 0;
                                    Classmates.pose[i] = 14;
                                    continue;
                                case 5:
                                    Classmates.slashEffect[i] = 1;
                                    Classmates.pose[i] = 15;
                                    continue;
                                case 6:
                                    Classmates.slashEffect[i] = 1;
                                    Classmates.pose[i] = 15;
                                    hitMonster(i);
                                    continue;
                                case 7:
                                    Classmates.slashEffect[i] = 2;
                                    Classmates.pose[i] = 16;
                                    continue;
                                case 8:
                                    Classmates.slashEffect[i] = 2;
                                    Classmates.pose[i] = 16;
                                    Classmates.target[i] = Player.COMBO_SECOND_SLASH;
                                    Classmates.animTimer[i] = 1;
                                default:
                                    continue;
                            }
                        case Player.COMBO_FIRST_SLASH_END:
                            switch (Classmates.animTimer[i]) {
                                case 1:
                                    Classmates.slashEffect[i] = 1;
                                    Classmates.pose[i] = 15;
                                    continue;
                                case 2:
                                    Classmates.slashEffect[i] = 1;
                                    Classmates.pose[i] = 15;
                                    Classmates.target[i] = Player.COMBO_RECOVER;
                                default:
                                    continue;
                            }
                        case Player.COMBO_SECOND_SLASH:
                            switch (Classmates.animTimer[i]) {
                                case 1:
                                case 2:
                                case 3:
                                    Classmates.slashEffect[i] = 3;
                                    Classmates.pose[i] = 17;
                                    continue;
                                case 4:
                                    Classmates.slashEffect[i] = 4;
                                    Classmates.pose[i] = 18;
                                    continue;
                                case 5:
                                    Classmates.slashEffect[i] = 5;
                                    Classmates.pose[i] = 19;
                                    continue;
                                case 6:
                                    Classmates.slashEffect[i] = 6;
                                    Classmates.pose[i] = 20;
                                    hitMonster(i);
                                    continue;
                                case 7:
                                    Classmates.slashEffect[i] = 6;
                                    Classmates.pose[i] = 20;
                                    Classmates.target[i] = Player.COMBO_THIRD_SLASH;
                                    Classmates.animTimer[i] = 1;
                                default:
                                    continue;
                            }
                        case Player.COMBO_THIRD_SLASH:
                            switch (Classmates.animTimer[i]) {
                                case 1:
                                    Classmates.slashEffect[i] = 1;
                                    Classmates.pose[i] = 15;
                                    break;
                                case 2:
                                    Classmates.slashEffect[i] = 1;
                                    Classmates.pose[i] = 15;
                                    break;
                                case 3:
                                    Classmates.slashEffect[i] = 2;
                                    Classmates.pose[i] = 16;
                                    break;
                                case 4:
                                    Classmates.slashEffect[i] = 2;
                                    Classmates.pose[i] = 16;
                                    Classmates.target[i] = Player.COMBO_FIRST_SLASH_END;
                                    Classmates.animTimer[i] = 1;
                            }
                        default:
                            break;
                        case Player.COMBO_RECOVER:
                            switch (Classmates.animTimer[i]) {
                                case 1:
                                    Classmates.slashEffect[i] = 2;
                                    Classmates.pose[i] = 16;
                                    break;
                                case 2:
                                    Classmates.slashEffect[i] = 0;
                                    Classmates.pose[i] = 14;
                                    break;
                                case 3:
                                case 4:
                                    Classmates.pose[i] = 13;
                                    Classmates.slashEffect[i] = -1;
                                    break;
                                case 5:
                                    Classmates.pose[i] = 13;
                                    Classmates.slashEffect[i] = -1;
                                    Classmates.state[i] = Classmates.STATE_IDLE;
                                    Classmates.target[i] = 120;
                                    Classmates.animTimer[i] = 0;
                            }
                    }
            }
        }

    }

    /** AI của Toniku (bạn học số 0) khi đấu với nhân vật. */
    public static void updateTonikuFight() {
        int phase = GameState.tick % 50;
        int distance = MathUtil.abs(Classmates.x[0] - Player.x);
        switch (Classmates.state[0]) {
            case Classmates.STATE_TONIKU_WAITING:
                Classmates.animTimer[0]++;
                if (Classmates.animTimer[0] > 30) {
                    Classmates.animTimer[0] = 0;
                }

                if (Classmates.animTimer[0] % 18 < 6) {
                    Classmates.pose[0] = 0;
                } else {
                    Classmates.pose[0] = 1;
                }

                if (Classmates.gathering) {
                    Toniku.healthBarVisible = false;
                    Classmates.target[0] = Classmates.gatherX;
                    Classmates.facing[0] = Classmates.gatherX > Classmates.x[0] ? 1 : -1;
                    Classmates.vx[0] = Classmates.facing[0] << 2;
                    Classmates.vx[0] = Classmates.facing[0] > 0 ? Classmates.vx[0] : Classmates.vx[0];
                    Classmates.state[0] = Classmates.STATE_WALK;
                    return;
                }

                if (distance >= 40 || Player.action != PlayerAction.ATTACK) {
                    return;
                }

                Toniku.healthBarVisible = true;
                Classmates.state[0] = Classmates.STATE_JUMP;
                Classmates.facing[0] = -Player.facing;
                if (phase > 35) {
                    Classmates.vx[0] = Player.facing * 5;
                    Classmates.vy[0] = -9;
                } else {
                    Classmates.vx[0] = -Player.facing * 6;
                    Classmates.vy[0] = -9;
                }

                if (Classmates.x[0] < Classmates.minX[0] + 40) {
                    Classmates.vx[0] = 10;
                    Classmates.facing[0] = -1;
                } else if (Classmates.x[0] > Classmates.maxX[0] - 40) {
                    Classmates.vx[0] = -10;
                    Classmates.facing[0] = 1;
                }
                break;
            case Classmates.STATE_IDLE:
                Classmates.animTimer[0]++;
                if (Classmates.animTimer[0] > 30) {
                    Classmates.animTimer[0] = 0;
                }

                if (Classmates.animTimer[0] % 18 < 6) {
                    Classmates.pose[0] = 0;
                } else {
                    Classmates.pose[0] = 1;
                }

                if (Toniku.pacified) {
                    Classmates.state[0] = Classmates.STATE_TONIKU_WAITING;
                    Classmates.animTimer[0] = Classmates.target[0] = 0;
                }

                if (distance < 40 && Player.action == PlayerAction.ATTACK && Player.facing != Classmates.facing[0]) {
                    Classmates.state[0] = Classmates.STATE_JUMP;
                    Classmates.facing[0] = -Player.facing;
                    if (phase > 35) {
                        Classmates.vx[0] = Player.facing * 5;
                        Classmates.vy[0] = -9;
                    } else {
                        Classmates.vx[0] = -Player.facing * 6;
                        Classmates.vy[0] = -9;
                    }

                    if (Classmates.x[0] < Classmates.minX[0] + 40) {
                        Classmates.vx[0] = 10;
                        Classmates.facing[0] = -1;
                    } else if (Classmates.x[0] > Classmates.maxX[0] - 40) {
                        Classmates.vx[0] = -10;
                        Classmates.facing[0] = 1;
                    }

                    Classmates.animTimer[0] = Classmates.target[0] = 0;
                } else if (distance < 40 && phase < 10) {
                    Classmates.facing[0] = Player.x > Classmates.x[0] ? 1 : -1;
                    Classmates.state[0] = Classmates.STATE_ATTACK;
                    Classmates.animTimer[0] = Classmates.target[0] = Classmates.targetMonster[0] = 0;
                } else if (distance < 100 && distance > 40 && phase < 1) {
                    Classmates.facing[0] = Player.x > Classmates.x[0] ? 1 : -1;
                    Classmates.state[0] = Classmates.STATE_DASH_ATTACK;
                    Classmates.animTimer[0] = Classmates.target[0] = Classmates.targetMonster[0] = 0;
                    Classmates.vx[0] = (MathUtil.abs(Classmates.x[0] - Player.x) >> 3) * Classmates.facing[0];
                } else if (distance > 100 && distance < 160 && phase < 30) {
                    Classmates.facing[0] = Player.x > Classmates.x[0] ? 1 : -1;
                    Classmates.state[0] = Classmates.STATE_THROW_DART;
                    Classmates.animTimer[0] = Classmates.target[0] = Classmates.targetMonster[0] = 0;
                } else if (distance >= 20 && phase != 42) {
                    if (distance > 160) {
                        Classmates.facing[0] = Player.x > Classmates.x[0] ? 1 : -1;
                        Classmates.target[0] = Classmates.x[0] + (Classmates.facing[0] << 7);
                        if (Classmates.target[0] < Classmates.minX[0]) {
                            Classmates.target[0] = Classmates.minX[0];
                        } else if (Classmates.target[0] > Classmates.maxX[0]) {
                            Classmates.target[0] = Classmates.maxX[0];
                        }

                        Classmates.animTimer[0] = 0;
                        Classmates.state[0] = Classmates.STATE_WALK;
                        Classmates.vx[0] = Classmates.facing[0] << 2;
                    }
                } else {
                    Classmates.facing[0] = -Player.facing;
                    Classmates.target[0] = Classmates.x[0] + (Classmates.facing[0] << 6);
                    if (Classmates.target[0] < Classmates.minX[0] || Classmates.target[0] > Classmates.maxX[0]) {
                        Classmates.facing[0] = -Classmates.facing[0];
                        Classmates.target[0] = Classmates.x[0] + (Classmates.facing[0] << 6);
                    }

                    Classmates.animTimer[0] = 0;
                    Classmates.state[0] = Classmates.STATE_WALK;
                    Classmates.vx[0] = Classmates.facing[0] << 3;
                }
                return;
            case Classmates.STATE_WALK:
                Classmates.animTimer[0]++;
                if (Classmates.animTimer[0] >= 10) {
                    Classmates.animTimer[0] = 0;
                }

                Classmates.pose[0] = (Classmates.animTimer[0] >> 1) + 2;
                Classmates.x[0] += Classmates.vx[0];
                if (!Classmates.gathering && (Classmates.facing[0] > 0 && Classmates.x[0] > Classmates.target[0] || Classmates.facing[0] < 0 && Classmates.x[0] < Classmates.target[0])) {
                    Classmates.facing[0] = Player.x > Classmates.x[0] ? 1 : -1;
                    Classmates.target[0] = Classmates.animTimer[0] = 0;
                    Classmates.state[0] = Classmates.STATE_IDLE;
                }
                return;
            case Classmates.STATE_JUMP:
            case Classmates.STATE_KNOCKBACK:
                Classmates.x[0] += Classmates.vx[0];
                if (Classmates.x[0] < Classmates.minX[0]) {
                    Classmates.x[0] = Classmates.minX[0];
                }

                if (Classmates.x[0] > Classmates.maxX[0]) {
                    Classmates.x[0] = Classmates.maxX[0];
                }

                Classmates.y[0] += Classmates.vy[0];
                Classmates.pose[0] = 23;
                if (Classmates.vx[0] > 0) {
                    Classmates.vx[0]--;
                }

                Classmates.vy[0]++;
                if (Classmates.vy[0] == 0) {
                    Classmates.state[0] = Classmates.STATE_FALL;
                    Classmates.animTimer[0] = 0;
                    Classmates.vy[0] = 1;
                }
                return;
            case Classmates.STATE_FALL:
                Classmates.x[0] += Classmates.vx[0];
                if (Classmates.x[0] < Classmates.minX[0]) {
                    Classmates.x[0] = Classmates.minX[0];
                }

                if (Classmates.x[0] > Classmates.maxX[0]) {
                    Classmates.x[0] = Classmates.maxX[0];
                }

                Classmates.y[0] += Classmates.vy[0];
                Classmates.pose[0] = 7;
                if (Classmates.vy[0] < 18) {
                    Classmates.vy[0]++;
                }

                if (Classmates.vx[0] > 0) {
                    Classmates.vx[0]--;
                }

                Classmates.pose[0] = 7;
                if (Classmates.y[0] >= GameMap.pixelHeight) {
                    Classmates.state[0] = Classmates.STATE_FELL_OFF_MAP;
                } else if ((GameMap.flagsAtPixel(Classmates.x[0], Classmates.y[0]) & TileFlags.SOLID) == TileFlags.SOLID) {
                    Classmates.state[0] = Classmates.STATE_IDLE;
                    Classmates.animTimer[0] = Classmates.target[0] = 0;
                    Classmates.vx[0] = Classmates.vy[0] = 0;
                    Classmates.y[0] = GameMap.alignToTileX(Classmates.y[0]);
                    Dust.spawn(-1, Classmates.x[0] - -8, Classmates.y[0]);
                    Dust.spawn(1, Classmates.x[0] - 8, Classmates.y[0]);
                    if (MathUtil.abs(Classmates.x[0] - Player.x) < 40 && phase < 25) {
                        Classmates.facing[0] = Player.x > Classmates.x[0] ? 1 : -1;
                        Classmates.state[0] = Classmates.STATE_ATTACK;
                        Classmates.animTimer[0] = Classmates.target[0] = Classmates.targetMonster[0] = 0;
                    }

                    if (distance < 40) {
                        Classmates.facing[0] = Player.facing;
                        Classmates.target[0] = Classmates.x[0] + Classmates.facing[0] * 110;
                        if (Classmates.target[0] < Classmates.minX[0] || Classmates.target[0] > Classmates.maxX[0]) {
                            Classmates.facing[0] = -Classmates.facing[0];
                            Classmates.target[0] = Classmates.x[0] + (Classmates.facing[0] << 6);
                        }

                        Classmates.animTimer[0] = 0;
                        Classmates.state[0] = Classmates.STATE_WALK;
                        Classmates.vx[0] = Classmates.facing[0] * 10;
                    }
                }
                return;
            case Classmates.STATE_ATTACK:
                Classmates.animTimer[0]++;
                switch (Classmates.target[0]) {
                    case Player.COMBO_FIRST_SLASH:
                        switch (Classmates.animTimer[0]) {
                            case 1:
                            case 2:
                            case 3:
                                Classmates.slashEffect[0] = -1;
                                Classmates.pose[0] = 13;
                                return;
                            case 4:
                                Classmates.slashEffect[0] = 0;
                                Classmates.pose[0] = 14;
                                return;
                            case 5:
                                Classmates.slashEffect[0] = 1;
                                Classmates.pose[0] = 15;
                                return;
                            case 6:
                                Classmates.slashEffect[0] = 1;
                                Classmates.pose[0] = 15;
                                tonikuSlashPlayer(0);
                                return;
                            case 7:
                                Classmates.slashEffect[0] = 2;
                                Classmates.pose[0] = 16;
                                return;
                            case 8:
                                Classmates.slashEffect[0] = 2;
                                Classmates.pose[0] = 16;
                                Classmates.target[0] = Player.COMBO_SECOND_SLASH;
                                Classmates.animTimer[0] = 1;
                            default:
                                return;
                        }
                    case Player.COMBO_FIRST_SLASH_END:
                        switch (Classmates.animTimer[0]) {
                            case 1:
                                Classmates.slashEffect[0] = 1;
                                Classmates.pose[0] = 15;
                                return;
                            case 2:
                                Classmates.slashEffect[0] = 1;
                                Classmates.pose[0] = 15;
                                Classmates.target[0] = Player.COMBO_RECOVER;
                            default:
                                return;
                        }
                    case Player.COMBO_SECOND_SLASH:
                        switch (Classmates.animTimer[0]) {
                            case 1:
                            case 2:
                            case 3:
                                Classmates.slashEffect[0] = 3;
                                Classmates.pose[0] = 17;
                                return;
                            case 4:
                                Classmates.slashEffect[0] = 4;
                                Classmates.pose[0] = 18;
                                return;
                            case 5:
                                Classmates.slashEffect[0] = 5;
                                Classmates.pose[0] = 19;
                                return;
                            case 6:
                                Classmates.slashEffect[0] = 6;
                                Classmates.pose[0] = 20;
                                tonikuSlashPlayer(0);
                                return;
                            case 7:
                                Classmates.slashEffect[0] = 6;
                                Classmates.pose[0] = 20;
                                Classmates.target[0] = Player.COMBO_THIRD_SLASH;
                                Classmates.animTimer[0] = 1;
                            default:
                                return;
                        }
                    case Player.COMBO_THIRD_SLASH:
                        switch (Classmates.animTimer[0]) {
                            case 1:
                                Classmates.slashEffect[0] = 1;
                                Classmates.pose[0] = 15;
                                return;
                            case 2:
                                Classmates.slashEffect[0] = 1;
                                Classmates.pose[0] = 15;
                                return;
                            case 3:
                                Classmates.slashEffect[0] = 2;
                                Classmates.pose[0] = 16;
                                return;
                            case 4:
                                Classmates.slashEffect[0] = 2;
                                Classmates.pose[0] = 16;
                                Classmates.target[0] = Player.COMBO_FIRST_SLASH_END;
                                Classmates.animTimer[0] = 1;
                        }
                    default:
                        return;
                    case Player.COMBO_RECOVER:
                        switch (Classmates.animTimer[0]) {
                            case 1:
                                Classmates.slashEffect[0] = 2;
                                Classmates.pose[0] = 16;
                                return;
                            case 2:
                                Classmates.slashEffect[0] = 0;
                                Classmates.pose[0] = 14;
                                return;
                            case 3:
                            case 4:
                                Classmates.pose[0] = 13;
                                Classmates.slashEffect[0] = -1;
                                return;
                            case 5:
                                Classmates.pose[0] = 13;
                                Classmates.slashEffect[0] = -1;
                                Classmates.state[0] = Classmates.STATE_IDLE;
                                Classmates.target[0] = 120;
                                Classmates.animTimer[0] = 0;
                            default:
                                return;
                        }
                }
            case Classmates.STATE_THROW_DART:
                Classmates.animTimer[0]++;
                switch (Classmates.animTimer[0]) {
                    case 1:
                    case 2:
                    case 3:
                        Classmates.pose[0] = 13;
                        return;
                    case 4:
                        Classmates.pose[0] = 14;
                        return;
                    case 5:
                        Classmates.pose[0] = 15;
                        return;
                    case 6:
                        Classmates.pose[0] = 15;
                        Darts.spawn(Classmates.x[0] + (Classmates.facing[0] << 3), Classmates.y[0] - 12, Classmates.facing[0] << 3, 0, false);
                        return;
                    case 7:
                        Classmates.pose[0] = 16;
                        return;
                    case 8:
                        Classmates.pose[0] = 16;
                        Classmates.target[0] = 2;
                        return;
                    case 9:
                        Classmates.animTimer[0] = 0;
                        Classmates.state[0] = Classmates.STATE_IDLE;
                    default:
                        return;
                }
            case Classmates.STATE_DASH_ATTACK:
                Classmates.target[0]++;
                if (Classmates.target[0] < 7) {
                    Classmates.animTimer[0]++;
                    if (Classmates.animTimer[0] >= 10) {
                        Classmates.animTimer[0] = 0;
                    }

                    Classmates.pose[0] = (Classmates.animTimer[0] >> 1) + 2;
                    Classmates.x[0] += Classmates.vx[0];
                    if (MathUtil.abs(Classmates.x[0] - Player.x) < 30) {
                        if (Player.action == PlayerAction.ATTACK && Player.facing != Classmates.facing[0]) {
                            Classmates.state[0] = Classmates.STATE_JUMP;
                            Classmates.facing[0] = -Player.facing;
                            Classmates.vx[0] = Player.facing << 1;
                            if (Classmates.x[0] < Classmates.minX[0] + 30) {
                                Classmates.vx[0] = 6;
                                Classmates.facing[0] = -1;
                            } else if (Classmates.x[0] > Classmates.maxX[0] - 30) {
                                Classmates.vx[0] = -6;
                                Classmates.facing[0] = 1;
                            }

                            Classmates.vy[0] = -9;
                            break;
                        } else {
                            Classmates.target[0] = 6;
                        }
                    }
                } else if (Classmates.target[0] != 7) {
                    if (Classmates.target[0] >= 15) {
                        Classmates.slashEffect[0] = -1;
                        Classmates.state[0] = Classmates.STATE_IDLE;
                        break;
                    } else {
                        Classmates.animTimer[0]++;
                        switch (Classmates.animTimer[0]) {
                            case 1:
                            case 2:
                            case 3:
                                Classmates.slashEffect[0] = -1;
                                Classmates.pose[0] = 13;
                                return;
                            case 4:
                                Classmates.slashEffect[0] = 0;
                                Classmates.pose[0] = 14;
                                return;
                            case 5:
                                Classmates.slashEffect[0] = 1;
                                Classmates.pose[0] = 15;
                                return;
                            case 6:
                                Classmates.slashEffect[0] = 1;
                                Classmates.pose[0] = 15;
                                tonikuSlashPlayer(0);
                                return;
                            case 7:
                                Classmates.slashEffect[0] = 2;
                                Classmates.pose[0] = 16;
                                return;
                            case 8:
                                Classmates.slashEffect[0] = 2;
                                Classmates.pose[0] = 16;
                                Classmates.target[0] = 2;
                                Classmates.animTimer[0] = 1;
                        }
                    }
                } else {
                    Classmates.animTimer[0] = 0;
                }
            default:
                return;
        }

        Classmates.animTimer[0] = Classmates.target[0] = 0;
    }
}
