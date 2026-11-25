package core.monster;

import core.app.GameState;
import core.combat.Combat;
import core.map.GameMap;
import core.player.Player;
import core.render.Camera;
import core.render.Viewport;
import core.util.MathUtil;

/** AI của ninja địch (Ninjas). */
public final class NinjaAI {
    private NinjaAI() {
    }

    /** Ninja cận chiến tấn công khi nhân vật ở ngay trước mặt. */
    private static void meleeIfPlayerClose(int i) {
        if (MathUtil.abs(Player.y - Ninjas.y[i]) <= 24 && MathUtil.abs(Ninjas.x[i] + (Ninjas.direction[i] << 3) - Player.x) < 25 && (Ninjas.type[i] == Ninjas.TYPE_RED_SWORDSMAN || Ninjas.type[i] == Ninjas.TYPE_SWORDSMAN || Ninjas.type[i] == Ninjas.TYPE_RED_SPEARMAN)) {
            Ninjas.targetX[i] = Ninjas.timer[i] = 0;
            Ninjas.state[i] = Ninjas.STATE_ATTACK;
        }

    }

    /** Thấy nhân vật trong tầm: ninja tầm xa quay lại bắn, ninja cận chiến lao tới. */
    private static void reactToPlayer(int i) {
        if (!Player.hidden) {
            if (MathUtil.abs(Ninjas.x[i] - Player.x) < 100 && MathUtil.abs(Ninjas.y[i] - Player.y) < 20) {
                switch (Ninjas.type[i]) {
                    case Ninjas.TYPE_ARCHER:
                    case Ninjas.TYPE_KUNAI_THROWER:
                    case Ninjas.TYPE_RED_ARCHER:
                    case Ninjas.TYPE_RED_KUNAI_THROWER:
                        Ninjas.direction[i] = Ninjas.x[i] < Player.x ? 1 : -1;
                        Ninjas.targetX[i] = Ninjas.timer[i] = 0;
                        Ninjas.state[i] = Ninjas.STATE_ATTACK;
                        break;
                    case Ninjas.TYPE_SWORDSMAN:
                    case Ninjas.TYPE_RED_SPEARMAN:
                    case Ninjas.TYPE_RED_SWORDSMAN:
                        Ninjas.targetX[i] = Player.x;
                        if (Ninjas.x[i] < Player.x) {
                            Ninjas.targetX[i] -= 10;
                        } else {
                            Ninjas.targetX[i] += 10;
                        }

                        Ninjas.direction[i] = Ninjas.targetX[i] < Player.x ? 1 : -1;
                        Ninjas.state[i] = Ninjas.STATE_DASH;
                        Ninjas.vx[i] = Ninjas.direction[i] << 3;
                        if (Ninjas.targetX[i] < Ninjas.minX[i]) {
                            Ninjas.targetX[i] = Ninjas.minX[i];
                        }

                        if (Ninjas.targetX[i] > Ninjas.maxX[i]) {
                            Ninjas.targetX[i] = Ninjas.maxX[i];
                            return;
                        }
                }
            }

        }
    }

    /** Cập nhật mọi ninja trên map theo trạng thái. */
    public static void update() {
        for(int i = 0; i < Ninjas.count; ++i) {
            if (Ninjas.healthBarTimer[i] > 0) {
                Ninjas.healthBarTimer[i]--;
            }

            switch (Ninjas.state[i]) {
                case Ninjas.STATE_KNOCKED_OUT:
                    Ninjas.bodyFrame[i] = 1;
                    Ninjas.timer[i]++;
                    Ninjas.x[i] += Ninjas.vx[i];
                    Ninjas.y[i] += Ninjas.vy[i];
                    Ninjas.bodyX[i] += Ninjas.bodyVx[i];
                    Ninjas.bodyY[i] += Ninjas.bodyVy[i];
                    if (Ninjas.timer[i] > 14) {
                        if (Ninjas.vx[i] > 1) {
                            Ninjas.vx[i]--;
                        } else if (Ninjas.vx[i] < -1) {
                            Ninjas.vx[i]++;
                        }

                        Ninjas.vy[i]++;
                    }

                    if (Ninjas.bodyVx[i] > 1) {
                        Ninjas.bodyVx[i]--;
                    } else if (Ninjas.bodyVx[i] < -1) {
                        Ninjas.bodyVx[i]++;
                    }

                    Ninjas.bodyVy[i]++;
                    if (Ninjas.y[i] <= Camera.y + Viewport.viewHeight + 32 && Ninjas.y[i] < GameMap.pixelHeight) {
                        continue;
                    }

                    Ninjas.timer[i] = 0;
                    Ninjas.targetX[i] = 0;
                    Ninjas.state[i] = Ninjas.STATE_DEAD;
                    break;
                case Ninjas.STATE_WALK:
                    if (GameState.tick % 12 > 7) {
                        Ninjas.bob[i] = 1;
                        Ninjas.legsFrame[i] = 1;
                        if (GameState.tick % 12 < 10) {
                            if (MathUtil.abs(Ninjas.x[i] - Ninjas.targetX[i]) < 4) {
                                Ninjas.timer[i] = Ninjas.targetX[i] = 0;
                                Ninjas.state[i] = Ninjas.STATE_IDLE;
                                break;
                            }

                            Ninjas.x[i] += Ninjas.vx[i];
                        }

                        reactToPlayer(i);
                    } else {
                        Ninjas.legsFrame[i] = 0;
                        Ninjas.bob[i] = 0;
                    }
                    break;
                case Ninjas.STATE_IDLE:
                    if ((GameState.tick + i) % 10 > 5) {
                        Ninjas.bob[i] = 1;
                    } else {
                        Ninjas.bob[i] = 0;
                    }

                    Ninjas.timer[i]++;
                    Ninjas.legsFrame[i] = 0;
                    Ninjas.bodyFrame[i] = 0;
                    if (Ninjas.timer[i] > 50) {
                        int offset;
                        if ((offset = (GameState.tick - i) % (i + 80) - 40) > 0) {
                            offset += 20;
                        } else {
                            offset -= 20;
                        }

                        Ninjas.targetX[i] = Ninjas.x[i] + offset;
                        Ninjas.direction[i] = offset > 0 ? 1 : -1;
                        if (GameState.tick % 4 > 1) {
                            Ninjas.state[i] = Ninjas.STATE_WALK;
                            Ninjas.vx[i] = Ninjas.direction[i] << 1;
                        } else {
                            Ninjas.state[i] = Ninjas.STATE_DASH;
                            Ninjas.vx[i] = Ninjas.direction[i] << 2;
                        }

                        if (Ninjas.targetX[i] < Ninjas.minX[i]) {
                            Ninjas.targetX[i] = Ninjas.minX[i];
                        }

                        if (Ninjas.targetX[i] > Ninjas.maxX[i]) {
                            Ninjas.targetX[i] = Ninjas.maxX[i];
                        }
                    }

                    if (GameState.tick % 30 == 28) {
                        reactToPlayer(i);
                        meleeIfPlayerClose(i);
                    }
                    break;
                case Ninjas.STATE_ATTACK:
                    Ninjas.timer[i]++;
                    if (Ninjas.timer[i] < 3) {
                        Ninjas.bob[i] = 0;
                        Ninjas.legsFrame[i] = 0;
                        Ninjas.bodyFrame[i] = 0;
                    } else if (Ninjas.timer[i] < 14) {
                        Ninjas.bob[i] = 1;
                        Ninjas.legsFrame[i] = 1;
                        Ninjas.bodyFrame[i] = 1;
                    } else {
                        Ninjas.state[i] = Ninjas.STATE_IDLE;
                        Ninjas.timer[i] = 1;
                    }

                    if (Ninjas.timer[i] == 3) {
                        switch (Ninjas.type[i]) {
                            case Ninjas.TYPE_ARCHER:
                            case Ninjas.TYPE_RED_ARCHER:
                                Projectiles.spawn(Ninjas.x[i] + (Ninjas.direction[i] << 3), Ninjas.y[i] - 15, Ninjas.direction[i] * 12, NinjaStats.damage[Ninjas.type[i]], Projectiles.TYPE_ARROW);
                                break;
                            case Ninjas.TYPE_KUNAI_THROWER:
                            case Ninjas.TYPE_RED_KUNAI_THROWER:
                                Projectiles.spawn(Ninjas.x[i] + (Ninjas.direction[i] << 3), Ninjas.y[i] - 15, Ninjas.direction[i] << 3, NinjaStats.damage[Ninjas.type[i]], Projectiles.TYPE_KUNAI);
                                break;
                            case Ninjas.TYPE_SWORDSMAN:
                            case Ninjas.TYPE_RED_SPEARMAN:
                            case Ninjas.TYPE_RED_SWORDSMAN:
                                if (MathUtil.abs(Player.y - Ninjas.y[i]) < 5 && MathUtil.abs(Player.x - Ninjas.x[i]) < 30) {
                                    Combat.damagePlayer(NinjaStats.damage[Ninjas.type[i]], Ninjas.direction[i]);
                                }
                        }
                    }
                    break;
                case Ninjas.STATE_DEAD:
                    Ninjas.timer[i]++;
                    if (Ninjas.timer[i] > 550 + i * 5) {
                        Ninjas.x[i] = Ninjas.spawnX[i];
                        Ninjas.y[i] = Ninjas.spawnY[i];
                        Ninjas.timer[i] = 0;
                        Ninjas.state[i] = Ninjas.STATE_IDLE;
                        Ninjas.hp[i] = NinjaStats.maxHp[Ninjas.type[i]];
                    }
                    break;
                case Ninjas.STATE_HURT:
                    if ((GameState.tick + i) % 10 > 5) {
                        Ninjas.bob[i] = 1;
                    } else {
                        Ninjas.bob[i] = 0;
                    }

                    Ninjas.legsFrame[i] = 0;
                    Ninjas.bodyFrame[i] = 0;
                    Ninjas.timer[i]++;
                    if (Ninjas.timer[i] >= 5) {
                        Ninjas.targetX[i] = Player.x;
                        if (Ninjas.x[i] < Player.x) {
                            Ninjas.targetX[i] -= 80;
                        } else {
                            Ninjas.targetX[i] += 80;
                        }

                        Ninjas.timer[i] = 0;
                        Ninjas.direction[i] = Ninjas.targetX[i] < Player.x ? -1 : 1;
                        Ninjas.state[i] = Ninjas.STATE_DASH;
                        Ninjas.vx[i] = Ninjas.direction[i] << 3;
                        if (Ninjas.targetX[i] < Ninjas.minX[i]) {
                            Ninjas.targetX[i] = Ninjas.minX[i];
                        }

                        if (Ninjas.targetX[i] > Ninjas.maxX[i]) {
                            Ninjas.targetX[i] = Ninjas.maxX[i];
                        }
                    }
                    break;
                case Ninjas.STATE_DASH:
                    if (MathUtil.abs(Ninjas.x[i] - Ninjas.targetX[i]) < 12) {
                        Ninjas.timer[i] = Ninjas.targetX[i] = 0;
                        Ninjas.state[i] = Ninjas.STATE_IDLE;
                        meleeIfPlayerClose(i);
                    } else {
                        Ninjas.x[i] += Ninjas.vx[i];
                        if (GameState.tick % 2 == 1) {
                            Ninjas.bob[i] = 1;
                            Ninjas.legsFrame[i] = 3;
                        } else {
                            Ninjas.legsFrame[i] = 2;
                            Ninjas.bob[i] = 0;
                        }
                    }
            }

            Ninjas.bodyX[i] = Ninjas.x[i];
            Ninjas.bodyY[i] = Ninjas.y[i] - 9 + Ninjas.bob[i] + NinjaStats.bodyOffsetY[Ninjas.type[i]][Ninjas.bodyFrame[i]];
        }

    }
}
