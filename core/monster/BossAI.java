package core.monster;

import core.app.GameState;
import core.combat.Combat;
import core.fx.Dust;
import core.map.GameMap;
import core.map.TileFlags;
import core.player.Darts;
import core.player.Player;
import core.player.PlayerAction;
import core.render.Camera;
import core.render.Viewport;
import core.util.MathUtil;

/** AI của boss lợn rừng, thủy quái và boss cuối. */
public final class BossAI {
    private BossAI() {
    }

    /** Boss lợn rừng lao tới khi nhân vật ở gần cùng độ cao. */
    private static void boarBossChargeIfPlayerNear() {
        if (!Player.hidden && Player.action != PlayerAction.BURROWED && MathUtil.abs(Player.y - BoarBoss.y) < 30 && MathUtil.abs(Player.x - BoarBoss.x) < 200) {
            BoarBoss.state = BoarBoss.STATE_CHARGE;
            BoarBoss.direction = BoarBoss.x < Player.x ? 1 : -1;
            BoarBoss.targetX = Player.x + (BoarBoss.direction << 5);
            BoarBoss.vx = BoarBoss.direction * 6;
            if (BoarBoss.hp < BoarBoss.maxHp >> 1) {
                BoarBoss.vx = BoarBoss.direction << 3;
            }
        }

    }

    /** AI boss cuối: đi lại, chém, ném phi tiêu, bắn cầu lửa, lao tới. */
    public static void updateFinalBoss() {
        if (FinalBoss.active) {
            int phase = GameState.tick % 10;
            int dx = MathUtil.abs(Player.x - FinalBoss.x);
            int dy = MathUtil.abs(Player.y - FinalBoss.y);
            int frame;
            switch (FinalBoss.state) {
                case FinalBoss.STATE_WAITING:
                    if (phase < 7) {
                        FinalBoss.poseX = FinalBoss.idlePoseX[0];
                        FinalBoss.poseY = FinalBoss.idlePoseY[0];
                    } else {
                        FinalBoss.poseX = FinalBoss.idlePoseX[1];
                        FinalBoss.poseY = FinalBoss.idlePoseY[1];
                    }
                    break;
                case FinalBoss.STATE_IDLE:
                    if (phase < 7) {
                        FinalBoss.poseX = FinalBoss.idlePoseX[0];
                        FinalBoss.poseY = FinalBoss.idlePoseY[0];
                    } else {
                        FinalBoss.poseX = FinalBoss.idlePoseX[1];
                        FinalBoss.poseY = FinalBoss.idlePoseY[1];
                    }

                    if (dx > 90 && dx < 100 && phase < 2) {
                        FinalBoss.direction = FinalBoss.x < Player.x ? 1 : -1;
                        FinalBoss.state = FinalBoss.STATE_SHOOT;
                        FinalBoss.timer = 0;
                    } else if ((dx > 120 && dx < 150 || Player.y < FinalBoss.y && dx > 40) && phase < 2) {
                        FinalBoss.direction = FinalBoss.x < Player.x ? 1 : -1;
                        FinalBoss.timer = 0;
                        FinalBoss.state = FinalBoss.STATE_THROW_DARTS;
                    } else if (dx < 40 && phase == 1) {
                        FinalBoss.direction = FinalBoss.x < Player.x ? 1 : -1;
                        FinalBoss.timer = 0;
                        FinalBoss.state = FinalBoss.STATE_MELEE;
                    } else if (dx > 40 && dx < 100 && phase < 3) {
                        FinalBoss.direction = FinalBoss.x < Player.x ? 1 : -1;
                        FinalBoss.timer = 0;
                        if ((FinalBoss.targetX = Player.x) > FinalBoss.maxX) {
                            FinalBoss.targetX = FinalBoss.maxX;
                        } else if (FinalBoss.targetX < FinalBoss.minX) {
                            FinalBoss.targetX = FinalBoss.minX;
                        }

                        FinalBoss.state = FinalBoss.STATE_DASH;
                    } else if (dx > 80 && phase == 2) {
                        FinalBoss.timer = 0;
                        FinalBoss.state = FinalBoss.STATE_WALK;
                        if ((FinalBoss.targetX = Player.x) > FinalBoss.maxX) {
                            FinalBoss.targetX = FinalBoss.maxX;
                        } else if (FinalBoss.targetX < FinalBoss.minX) {
                            FinalBoss.targetX = FinalBoss.minX;
                        }
                    }
                    break;
                case FinalBoss.STATE_WALK:
                    if ((frame = ++FinalBoss.timer % 6 >> 1) == 3) {
                        Camera.shakeStep = 0;
                    }

                    FinalBoss.poseX = FinalBoss.walkPoseX[frame];
                    FinalBoss.poseY = FinalBoss.walkPoseY[frame];
                    if ((FinalBoss.x += FinalBoss.direction << 2) > FinalBoss.targetX && FinalBoss.direction > 0 || FinalBoss.x < FinalBoss.targetX && FinalBoss.direction < 0 || FinalBoss.x > FinalBoss.maxX || FinalBoss.x < FinalBoss.minX || FinalBoss.timer > 100) {
                        FinalBoss.timer = 0;
                        FinalBoss.state = FinalBoss.STATE_IDLE;
                    }

                    if (dx > 50 && dx < 100 && phase == 1) {
                        FinalBoss.direction = FinalBoss.x < Player.x ? 1 : -1;
                        FinalBoss.state = FinalBoss.STATE_SHOOT;
                        FinalBoss.timer = 0;
                    }
                    break;
                case FinalBoss.STATE_MELEE:
                    if (++FinalBoss.timer < 2) {
                        FinalBoss.poseX = FinalBoss.idlePoseX[0];
                        FinalBoss.poseY = FinalBoss.idlePoseY[0];
                    } else if (FinalBoss.timer < 2) {
                        FinalBoss.poseX = FinalBoss.attackPoseX[0];
                        FinalBoss.poseY = FinalBoss.attackPoseY[0];
                    } else if (FinalBoss.timer < 12) {
                        FinalBoss.poseX = FinalBoss.attackPoseX[1];
                        FinalBoss.poseY = FinalBoss.attackPoseY[1];
                    } else if (FinalBoss.timer < 14) {
                        FinalBoss.poseX = FinalBoss.attackPoseX[0];
                        FinalBoss.poseY = FinalBoss.attackPoseY[0];
                    } else if (FinalBoss.timer == 14) {
                        FinalBoss.timer = 0;
                        FinalBoss.state = FinalBoss.STATE_IDLE;
                    }

                    if (FinalBoss.timer == 3 && dx < 40 && dy < 10) {
                        Combat.damagePlayer(FinalBoss.damage, FinalBoss.direction);
                    }
                    break;
                case FinalBoss.STATE_DYING:
                    ++FinalBoss.timer;

                    for(int part = 0; part < FinalBoss.partBaseY.length; ++part) {
                        FinalBoss.partBaseY[part] += MathUtil.abs(part - 3) + 2;
                        FinalBoss.partBaseX[part] += part - 3 << 1;
                    }

                    if (FinalBoss.timer >= 50) {
                        FinalBoss.state = FinalBoss.STATE_DEFEATED;
                    }
                    break;
                case FinalBoss.STATE_THROW_DARTS:
                    if (++FinalBoss.timer < 2) {
                        FinalBoss.poseX = FinalBoss.idlePoseX[0];
                        FinalBoss.poseY = FinalBoss.idlePoseY[0];
                    } else if (FinalBoss.timer < 2) {
                        FinalBoss.poseX = FinalBoss.attackPoseX[0];
                        FinalBoss.poseY = FinalBoss.attackPoseY[0];
                    } else if (FinalBoss.timer < 12) {
                        FinalBoss.poseX = FinalBoss.attackPoseX[1];
                        FinalBoss.poseY = FinalBoss.attackPoseY[1];
                    } else if (FinalBoss.timer < 14) {
                        FinalBoss.poseX = FinalBoss.attackPoseX[0];
                        FinalBoss.poseY = FinalBoss.attackPoseY[0];
                    } else if (FinalBoss.timer == 14) {
                        FinalBoss.timer = 0;
                        FinalBoss.state = FinalBoss.STATE_IDLE;
                    }

                    if (FinalBoss.timer % 5 == 0) {
                        Darts.spawn(FinalBoss.x + (FinalBoss.direction << 3), FinalBoss.y - 20, FinalBoss.direction << 3, -phase + 2, false);
                    }
                    break;
                case FinalBoss.STATE_SHOOT:
                    frame = ++FinalBoss.timer % 4 >> 1;
                    FinalBoss.poseX = FinalBoss.attackPoseX[frame];
                    FinalBoss.poseY = FinalBoss.attackPoseY[frame];
                    if (FinalBoss.timer > 20 && FinalBoss.timer % 10 == 0) {
                        Projectiles.spawn(FinalBoss.x + (FinalBoss.direction << 3), FinalBoss.y - 24, FinalBoss.direction << 3, FinalBoss.damage, Projectiles.TYPE_FIREBALL);
                    }

                    if (FinalBoss.timer == 100) {
                        FinalBoss.direction = FinalBoss.x < Player.x ? 1 : -1;
                        FinalBoss.timer = 0;
                        if ((FinalBoss.targetX = Player.x) > FinalBoss.maxX) {
                            FinalBoss.targetX = FinalBoss.maxX;
                        } else if (FinalBoss.targetX < FinalBoss.minX) {
                            FinalBoss.targetX = FinalBoss.minX;
                        }

                        FinalBoss.state = FinalBoss.STATE_DASH;
                    }
                    break;
                case FinalBoss.STATE_DASH:
                    frame = ++FinalBoss.timer % 6 >> 1;
                    FinalBoss.poseX = FinalBoss.walkPoseX[frame];
                    FinalBoss.poseY = FinalBoss.walkPoseY[frame];
                    if ((FinalBoss.x += FinalBoss.direction << 3) > FinalBoss.targetX && FinalBoss.direction > 0 || FinalBoss.x < FinalBoss.targetX && FinalBoss.direction < 0 || FinalBoss.x > FinalBoss.maxX || FinalBoss.x < FinalBoss.minX || FinalBoss.timer > 100) {
                        FinalBoss.timer = 0;
                        FinalBoss.state = FinalBoss.STATE_MELEE;
                    }
            }

            for(int part = 0; part < 6; ++part) {
                if (FinalBoss.direction == 1) {
                    FinalBoss.partX[part] = FinalBoss.x + FinalBoss.partBaseX[part] + FinalBoss.poseX[part];
                } else {
                    FinalBoss.partX[part] = FinalBoss.x - FinalBoss.partBaseX[part] - FinalBoss.poseX[part];
                }

                FinalBoss.partY[part] = FinalBoss.y + FinalBoss.partBaseY[part] + FinalBoss.poseY[part];
            }

        }
    }

    /** AI thủy quái: bơi qua lại, phun đạn vòng cung, nhô lên bắn, lặn rồi trồi lên cạnh nhân vật; bị hạ thì chìm dần. */
    public static void updateRiverMonster() {
        if (RiverMonster.active) {
            int phase = GameState.tick % 10;
            if (RiverMonster.healthBarTimer > 0) {
                --RiverMonster.healthBarTimer;
            }

            switch (RiverMonster.state) {
                case RiverMonster.STATE_SINKING:
                    ++RiverMonster.timer;
                    RiverMonster.y += 2;
                    ++RiverMonster.part2OffsetY;
                    ++RiverMonster.part3OffsetY;
                    ++RiverMonster.part1OffsetY;
                    if (RiverMonster.timer > 50) {
                        RiverMonster.state = RiverMonster.STATE_SUNK;
                    }
                    break;
                case RiverMonster.STATE_SWIM:
                    if (RiverMonster.hp < RiverMonster.maxHp >> 1) {
                        RiverMonster.x += RiverMonster.direction << 2;
                    } else {
                        RiverMonster.x += RiverMonster.direction << 1;
                    }

                    if (RiverMonster.x < RiverMonster.minX) {
                        RiverMonster.direction = 1;
                    }

                    if (RiverMonster.x > RiverMonster.maxX) {
                        RiverMonster.direction = -1;
                    }

                    if (RiverMonster.timer > 0) {
                        --RiverMonster.timer;
                    }

                    if (RiverMonster.timer == 0 && MathUtil.abs(Player.x - (RiverMonster.x - 40)) < 30) {
                        RiverMonster.timer = 0;
                        RiverMonster.state = RiverMonster.STATE_ATTACK;
                        RiverMonster.direction = RiverMonster.x < Player.x ? 1 : -1;
                    }

                    if (GameState.tick % 122 == 0) {
                        ArcProjectiles.spawn(RiverMonster.x, RiverMonster.y - 45, -1, -12, RiverMonster.damage);
                        ArcProjectiles.spawn(RiverMonster.x, RiverMonster.y - 50, 0, -15, RiverMonster.damage);
                        ArcProjectiles.spawn(RiverMonster.x, RiverMonster.y - 45, 1, -12, RiverMonster.damage);
                    } else if (GameState.tick % 200 == 0) {
                        RiverMonster.state = RiverMonster.STATE_DIVE;
                        RiverMonster.timer = 0;
                    }
                case RiverMonster.STATE_SUNK:
                default:
                    break;
                case RiverMonster.STATE_ATTACK:
                    if (RiverMonster.timer == 110) {
                        RiverMonster.state = RiverMonster.STATE_SWIM;
                        RiverMonster.timer = 30;
                    } else if (RiverMonster.timer < 35) {
                        --RiverMonster.y;
                    } else if (RiverMonster.timer >= 75) {
                        ++RiverMonster.y;
                        if (phase == 5) {
                            ArcProjectiles.spawn(RiverMonster.x, RiverMonster.y - 45, -2, -10, RiverMonster.damage);
                        } else if (phase == 7 && RiverMonster.hp < RiverMonster.maxHp >> 1) {
                            ArcProjectiles.spawn(RiverMonster.x, RiverMonster.y - 50, 0, -11, RiverMonster.damage);
                        } else if (phase == 9) {
                            ArcProjectiles.spawn(RiverMonster.x, RiverMonster.y - 45, 2, -10, RiverMonster.damage);
                        }
                    }

                    if (RiverMonster.timer > 20 && RiverMonster.timer < 50 && phase == 0) {
                        Projectiles.spawn(RiverMonster.x + RiverMonster.direction * 20, RiverMonster.y - 29, RiverMonster.direction << 3, RiverMonster.damage, Projectiles.TYPE_ENERGY_BALL);
                    }

                    if (RiverMonster.timer > 45 && RiverMonster.timer < 50) {
                        RiverMonster.part1OffsetX += RiverMonster.direction;
                    } else if (RiverMonster.timer > 50 && RiverMonster.timer < 55) {
                        RiverMonster.part1OffsetX -= RiverMonster.direction;
                    }

                    ++RiverMonster.timer;
                    break;
                case RiverMonster.STATE_DIVE:
                    if (++RiverMonster.timer == 50) {
                        RiverMonster.state = RiverMonster.STATE_SWIM;
                        RiverMonster.timer = 1;
                    } else if (RiverMonster.timer == 30) {
                        if ((RiverMonster.x = Player.x + (Player.facing << 6)) > RiverMonster.maxX) {
                            RiverMonster.x = RiverMonster.maxX - 30;
                        }

                        if (RiverMonster.x < RiverMonster.minX) {
                            RiverMonster.x = RiverMonster.minX + 30;
                        }

                        RiverMonster.direction = RiverMonster.x < Player.x ? 1 : -1;
                    } else if (RiverMonster.timer < 10) {
                        RiverMonster.y += 10;
                    } else if (RiverMonster.timer > 40) {
                        RiverMonster.y -= 10;
                    }
            }

            if (phase < 4) {
                --RiverMonster.y;
            } else if (phase < 8) {
                ++RiverMonster.y;
            }

            if (phase > 4) {
                --RiverMonster.part2OffsetY;
                ++RiverMonster.part3OffsetY;
            } else {
                ++RiverMonster.part2OffsetY;
                --RiverMonster.part3OffsetY;
            }

            if (RiverMonster.direction == -1) {
                RiverMonster.part2X = RiverMonster.x + RiverMonster.part2OffsetX;
                RiverMonster.part2Y = RiverMonster.y + RiverMonster.part2OffsetY;
                RiverMonster.part3X = RiverMonster.x + RiverMonster.part3OffsetX;
                RiverMonster.part3Y = RiverMonster.y + RiverMonster.part3OffsetY;
                RiverMonster.part1X = RiverMonster.x + RiverMonster.part1OffsetX;
                RiverMonster.part1Y = RiverMonster.y + RiverMonster.part1OffsetY;
            } else {
                RiverMonster.part2X = RiverMonster.x - RiverMonster.part2OffsetX;
                RiverMonster.part2Y = RiverMonster.y + RiverMonster.part2OffsetY;
                RiverMonster.part3X = RiverMonster.x - RiverMonster.part3OffsetX;
                RiverMonster.part3Y = RiverMonster.y + RiverMonster.part3OffsetY;
                RiverMonster.part1X = RiverMonster.x - RiverMonster.part1OffsetX;
                RiverMonster.part1Y = RiverMonster.y + RiverMonster.part1OffsetY;
            }
        }
    }

    /** AI boss lợn rừng: đứng nghỉ, đi tới điểm đến, lao vào nhân vật (dừng lại ở mép đất); bị hạ thì văng lên rồi rơi. */
    public static void updateBoarBoss() {
        if (BoarBoss.active) {
            if (BoarBoss.hurtFlash > 0) {
                --BoarBoss.hurtFlash;
            }

            switch (BoarBoss.state) {
                case BoarBoss.STATE_IDLE:
                    if (BoarBoss.timer > 0) {
                        --BoarBoss.timer;
                    } else {
                        int offset;
                        if ((offset = GameState.tick % 80 - 40) > 0) {
                            offset += 20;
                        } else {
                            offset -= 20;
                        }

                        BoarBoss.targetX = BoarBoss.x + offset;
                        BoarBoss.vx = (BoarBoss.direction = offset > 0 ? 1 : -1) << 2;
                        if (BoarBoss.hp < BoarBoss.maxHp >> 1) {
                            BoarBoss.vx = BoarBoss.direction << 3;
                        }

                        BoarBoss.state = BoarBoss.STATE_WALK;
                        if (GameState.tick % 5 == 0) {
                            boarBossChargeIfPlayerNear();
                        }
                    }

                    BoarBoss.frame = 0;
                    if (GameState.tick % 10 == 6) {
                        Dust.spawn(-BoarBoss.direction, BoarBoss.x + BoarBoss.direction * 28, BoarBoss.y - 5);
                    }
                    break;
                case BoarBoss.STATE_WALK:
                    if (GameState.tick % 8 == 0) {
                        boarBossChargeIfPlayerNear();
                    }

                    if (!GameMap.hasFlag(BoarBoss.x + (BoarBoss.direction << 4), BoarBoss.y + 1, TileFlags.SOLID)) {
                        BoarBoss.state = BoarBoss.STATE_IDLE;
                        BoarBoss.timer = 0;
                    } else {
                        if (MathUtil.abs(BoarBoss.x - BoarBoss.targetX) < 8) {
                            BoarBoss.state = BoarBoss.STATE_IDLE;
                            BoarBoss.timer = 20;
                        }

                        if (GameState.tick % 2 == 0) {
                            BoarBoss.x += BoarBoss.vx;
                            BoarBoss.frame = 0;
                        } else {
                            BoarBoss.frame = 1;
                        }
                    }
                default:
                    break;
                case BoarBoss.STATE_CHARGE:
                    if (!GameMap.hasFlag(BoarBoss.x + (BoarBoss.direction << 4), BoarBoss.y + 1, TileFlags.SOLID)) {
                        BoarBoss.state = BoarBoss.STATE_IDLE;
                        BoarBoss.timer = 0;
                    } else {
                        if (MathUtil.abs(BoarBoss.x - BoarBoss.targetX) < 4) {
                            BoarBoss.state = BoarBoss.STATE_IDLE;
                            BoarBoss.timer = 35;
                        }

                        if (MathUtil.abs(Player.y - BoarBoss.y) < 18 && MathUtil.abs(BoarBoss.x + (BoarBoss.direction << 4) - Player.x) < 10) {
                            Combat.damagePlayer(BoarBoss.damage, BoarBoss.direction);
                            BoarBoss.state = BoarBoss.STATE_IDLE;
                            BoarBoss.timer = 45;
                            if (BoarBoss.hp < BoarBoss.maxHp >> 1) {
                                BoarBoss.timer = 18;
                            }
                        }

                        BoarBoss.x += BoarBoss.vx;
                        BoarBoss.frame = GameState.tick % 3;
                    }
                    break;
                case BoarBoss.STATE_KNOCKED_OUT:
                    BoarBoss.y += BoarBoss.timer;
                    ++BoarBoss.timer;
                    BoarBoss.x += BoarBoss.vx;
                    if (BoarBoss.vx > 1) {
                        --BoarBoss.vx;
                    }

                    if (BoarBoss.vx < -1) {
                        ++BoarBoss.vx;
                    }

                    if (BoarBoss.y > Camera.y + Viewport.viewHeight + BoarBoss.height || BoarBoss.y >= GameMap.pixelHeight + BoarBoss.height) {
                        BoarBoss.timer = 0;
                        BoarBoss.state = BoarBoss.STATE_GONE;
                    }
                    break;
                case BoarBoss.STATE_HURT:
                    BoarBoss.x -= BoarBoss.direction;
                    BoarBoss.frame = 2;
                    if (++BoarBoss.timer == 4) {
                        BoarBoss.state = BoarBoss.STATE_IDLE;
                        if ((BoarBoss.timer = BoarBoss.targetX) > 0 && BoarBoss.hp < BoarBoss.maxHp >> 1) {
                            BoarBoss.timer = 5;
                        }
                    }
            }
        }
    }
}
