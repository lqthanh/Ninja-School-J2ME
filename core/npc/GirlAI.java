package core.npc;

import core.app.GameState;
import core.combat.Combat;
import core.fx.Blood;
import core.fx.Dust;
import core.fx.FloatingText;
import core.fx.WaterSplash;
import core.map.Crates;
import core.map.GameMap;
import core.map.TileFlags;
import core.monster.BoarBoss;
import core.monster.Monsters;
import core.monster.Ninjas;
import core.player.Darts;
import core.player.Player;
import core.player.PlayerAction;
import core.player.PlayerStats;
import core.render.Camera;
import core.render.Viewport;
import core.util.MathUtil;

/** AI của cô bé: đi theo nhân vật, đánh quái. */
public final class GirlAI {
    private GirlAI() {
    }

    /** Nhát chém của cô bé trúng quái, ninja hoặc boss. */
    private static void attackHit() {
        if (Girl.weapon == 1) {
            int vx = Girl.facing << 3;
            int aimY = Player.y - Girl.y >> 3;
            Darts.spawn(Girl.x + (Girl.facing << 3), Girl.y - 12, vx, aimY + (vx - 8), true);
        } else {
            int damage = PlayerStats.attack << 1;
            int left = Girl.facing > 0 ? Girl.x - 20 : Girl.x - 40;
            int right = Girl.facing > 0 ? Girl.x + 40 : Girl.x + 20;
            int top = Girl.y - Girl.height - 15;
            int bottom = Girl.y + 5;

            // Quái đầu tiên trong vùng chém (giống findLaunchTarget nhưng tính từ cô gái).
            int target = -1;
            for(int m = 0; m < Monsters.count; ++m) {
                if (Monsters.state[m] != Monsters.STATE_RESPAWN_WAIT && Monsters.x[m] >= left && Monsters.x[m] <= right && Monsters.y[m] >= top && Monsters.y[m] <= bottom) {
                    target = m;
                    break;
                }
            }

            if (target != -1) {
                Combat.damageMonster(target, damage, -Girl.facing);
                Blood.spawn(Monsters.x[target], Monsters.y[target] - (Monsters.height[target] >> 1), Girl.facing);
                FloatingText.spawn("-" + damage, 0, Monsters.x[target], Monsters.y[target] - Monsters.height[target], 0, -2);
            }

            if (BoarBoss.active && MathUtil.abs(Girl.y - BoarBoss.y) < 30 && MathUtil.abs(Girl.x + (Girl.facing << 4) - BoarBoss.x) < 40 && !Combat.damageBoarBoss(damage, -Girl.facing)) {
                Blood.spawn(Girl.x + (BoarBoss.x - Girl.x >> 1), Girl.y - 12, Girl.facing);
                FloatingText.spawn("-" + damage, 0, BoarBoss.x, BoarBoss.y - BoarBoss.height, 0, -2);
            }

            for(int i = 0; i < Ninjas.count; ++i) {
                if (Girl.y == Ninjas.y[i] && MathUtil.abs(Girl.x + (Girl.facing << 4) - Ninjas.x[i]) < 24) {
                    Combat.damageNinja(i, damage, -Girl.facing);
                    Blood.spawn(Ninjas.x[i], Ninjas.y[i] - 15, Girl.facing);
                    FloatingText.spawn("-" + damage, 0, Ninjas.x[i], Ninjas.y[i] - 28, 0, -2);
                    return;
                }
            }
        }
    }

    /** Tìm quái ở gần để cô bé đánh. */
    private static void findTarget() {
        int i;
        for(i = 0; i < Monsters.count; ++i) {
            if (Monsters.state[i] != Monsters.STATE_RESPAWN_WAIT && MathUtil.abs(Monsters.y[i] - Girl.y) < 20 && MathUtil.abs(Monsters.x[i] - Girl.x) < 40) {
                Girl.facing = Monsters.x[i] > Girl.x ? 1 : -1;
                Girl.action = PlayerAction.ATTACK;
                Girl.weapon = 0;
                Girl.actionTimer = 0;
                Girl.actionTimer2 = 0;
                return;
            }
        }

        for(i = 0; i < Ninjas.count; ++i) {
            if (Ninjas.state[i] != Ninjas.STATE_DEAD && Ninjas.y[i] == Girl.y && MathUtil.abs(Ninjas.x[i] - Girl.x) < 40) {
                Girl.facing = Ninjas.x[i] > Girl.x ? 1 : -1;
                Girl.action = PlayerAction.ATTACK;
                Girl.weapon = 0;
                Girl.actionTimer = 0;
                Girl.actionTimer2 = 0;
                return;
            }
        }

        if (MathUtil.abs(Player.x - Girl.x) > 25 && Player.y != Girl.y && Player.action == PlayerAction.ATTACK) {
            Girl.facing = Player.x > Girl.x ? 1 : -1;
            Girl.action = PlayerAction.ATTACK;
            Girl.weapon = 1;
            Girl.actionTimer = 0;
            Girl.actionTimer2 = 0;
        }
    }

    /** Cô bé đi / nhảy theo nhân vật. */
    private static void followPlayer() {
        if (Girl.following) {
            if (Player.y <= Girl.y - 24 && GameMap.hasFlag(Girl.x, Girl.y - 1, TileFlags.SOLID)) {
                Girl.action = PlayerAction.JUMP;
                Girl.vx = 0;
                Girl.vy = -7;
                return;
            }

            if (Player.y <= Girl.y - 24 && GameMap.hasFlag(Girl.x, Girl.y - 25, TileFlags.SOLID)) {
                Girl.action = PlayerAction.JUMP;
                Girl.vx = 0;
                Girl.vy = -10;
                return;
            }

            int tx;
            int ty;
            if (Girl.x >= Camera.x - 24 && Girl.x <= Camera.x + Viewport.viewWidth + 24) {
                if (Girl.y < Camera.y - 30 || Girl.y > Camera.y + Viewport.viewHeight + 30) {
                    Girl.facing = Player.x + (Player.facing << 5) > Girl.x ? 1 : -1;
                    tx = Player.x;
                    ty = Player.y > Girl.y ? Camera.y - 20 : Camera.y + Viewport.viewHeight;
                    Girl.x = tx;
                    Girl.y = ty;
                    Girl.action = PlayerAction.JUMP;
                    Girl.vx = Girl.facing << 2;
                    Girl.vy = -4;
                    return;
                }

                if (MathUtil.abs(Player.x + (Player.facing << 5) - Girl.x) <= (Player.action == PlayerAction.ATTACK ? 10 : 60)) {
                    Girl.action = PlayerAction.STAND;
                } else if (Player.y >= Girl.y) {
                    if (Player.y > Girl.y) {
                        if (GameMap.hasFlag(Girl.x + Girl.facing * Girl.width, Girl.y, TileFlags.SOLID)) {
                            Girl.facing = Player.x > Girl.x ? 1 : -1;
                            Girl.action = PlayerAction.RUN;
                            Girl.vx = Girl.facing * Player.moveSpeed;
                            return;
                        }

                        if (GameMap.hasFlag(Girl.x + Girl.facing * 48, Girl.y + 24, TileFlags.SOLID)) {
                            Girl.action = PlayerAction.JUMP;
                            Girl.vx = Girl.facing * 7;
                            Girl.vy = -5;
                            return;
                        }

                        for(int step = 1; step <= 5 && Girl.y + step * 24 < GameMap.pixelHeight && !GameMap.hasFlag(Girl.x + Girl.facing * 24, Girl.y + step * 24, TileFlags.WATER); ++step) {
                            if (GameMap.hasFlag(Girl.x + Girl.facing * 24, Girl.y + step * 24, TileFlags.SOLID)) {
                                Girl.action = PlayerAction.JUMP;
                                Girl.vx = Girl.facing << 2;
                                Girl.vy = -4;
                                return;
                            }

                            if (GameMap.hasFlag(Girl.x + Girl.facing * 48, Girl.y + step * 24, TileFlags.SOLID)) {
                                Girl.action = PlayerAction.JUMP;
                                Girl.vx = Girl.facing * 6;
                                Girl.vy = -4;
                                return;
                            }
                        }

                        if (GameMap.hasFlag(Girl.x + Girl.facing * 24, Girl.y - 24, TileFlags.SOLID)) {
                            Girl.action = PlayerAction.JUMP;
                            Girl.vx = Girl.facing * 5;
                            Girl.vy = -7;
                            return;
                        }

                        if (GameMap.hasFlag(Girl.x + Girl.facing * 48, Girl.y - 24, TileFlags.SOLID)) {
                            Girl.action = PlayerAction.JUMP;
                            Girl.vx = Girl.facing * 6;
                            Girl.vy = -8;
                            return;
                        }

                        if (GameMap.hasFlag(Girl.x + Girl.facing * 24, Girl.y - 48, TileFlags.SOLID)) {
                            Girl.action = PlayerAction.JUMP;
                            Girl.vx = Girl.facing * 5;
                            Girl.vy = -11;
                            return;
                        }
                    } else if (GameMap.hasFlag(Girl.x + Girl.facing * Girl.width, Girl.y, TileFlags.SOLID)) {
                        Girl.facing = Player.x + (Player.facing << 5) > Girl.x ? 1 : -1;
                        Girl.action = PlayerAction.RUN;
                        Girl.vx = Girl.facing * Player.moveSpeed;
                        return;
                    }
                } else {
                    if (GameMap.hasFlag(Girl.x + Girl.facing * 48, Girl.y - 24, TileFlags.SOLID)) {
                        Girl.action = PlayerAction.JUMP;
                        Girl.vx = Girl.facing * 5;
                        Girl.vy = -8;
                        return;
                    }

                    for(int step = 1; step <= 3; ++step) {
                        if (GameMap.hasFlag(Girl.x + Girl.facing * 24, Girl.y - step * 24, TileFlags.SOLID)) {
                            Girl.action = PlayerAction.JUMP;
                            Girl.vx = Girl.facing * 6;
                            Girl.vy = -5 - step * 3;
                            return;
                        }
                    }

                    if (!GameMap.hasFlag(Girl.x + Girl.facing * 48, Girl.y - 2, TileFlags.WATER)) {
                        if (Girl.y + 24 >= GameMap.pixelHeight || GameMap.hasFlag(Girl.x + Girl.facing * 48, Girl.y + 24, TileFlags.SOLID)) {
                            Girl.action = PlayerAction.JUMP;
                            Girl.vx = Girl.facing * 7;
                            Girl.vy = -4;
                            return;
                        }

                        int step = 1;

                        while(true) {
                            if (step > 5) {
                                return;
                            }

                            if (Girl.y + step * 24 >= GameMap.pixelHeight || GameMap.hasFlag(Girl.x + Girl.facing * 24, Girl.y + step * 24, TileFlags.WATER)) {
                                break;
                            }

                            if (GameMap.hasFlag(Girl.x + Girl.facing * 48, Girl.y + step * 24, TileFlags.SOLID)) {
                                Girl.action = PlayerAction.JUMP;
                                Girl.vx = Girl.facing * 6;
                                Girl.vy = -4;
                                return;
                            }

                            if (GameMap.hasFlag(Girl.x + Girl.facing * 24, Girl.y + step * 24, TileFlags.SOLID)) {
                                Girl.action = PlayerAction.JUMP;
                                Girl.vx = Girl.facing << 2;
                                Girl.vy = -4;
                                return;
                            }

                            ++step;
                        }
                    }
                }
            } else {
                Girl.facing = Player.x + (Player.facing << 5) > Girl.x ? 1 : -1;
                tx = Player.x > Girl.x ? Camera.x - 24 : Camera.x + Viewport.viewWidth + 24;
                ty = Player.y - 20;
                if (!GameMap.hasFlag(tx, ty, TileFlags.DIGGABLE)) {
                    Girl.x = tx;
                    Girl.y = ty;
                    Girl.action = PlayerAction.FALL;
                    Girl.vx = 4;
                    Girl.vy = 3;
                    return;
                }

                if (GameMap.hasFlag(Girl.x + Girl.facing * Girl.width, Girl.y, TileFlags.SOLID)) {
                    Girl.facing = Player.x > Girl.x ? 1 : -1;
                    Girl.action = PlayerAction.RUN;
                    Girl.vx = Girl.facing * 12;
                    return;
                }
            }
        }

    }

    /** Cô bé: đi theo / đánh quái / đứng chờ. */
    public static void update() {
        if (Girl.mapId == GameMap.id) {
            try {
                switch (Girl.action) {
                    case PlayerAction.STAND:
                        if (++Girl.actionTimer > 30) {
                            Girl.actionTimer = 0;
                        }

                        if (Girl.actionTimer % 15 < 5) {
                            Girl.pose = 0;
                        } else {
                            Girl.pose = 1;
                        }

                        if (Girl.following && GameState.tick % 6 == 0) {
                            findTarget();
                        }

                        if (GameState.tick % 15 == 0) {
                            followPlayer();
                        }
                        break;
                    case PlayerAction.RUN:
                        if (++Girl.actionTimer >= 10) {
                            Girl.actionTimer = 0;
                        }

                        Girl.pose = (Girl.actionTimer >> 1) + 2;
                        if (Girl.facing == 1) {
                            if (GameMap.hasFlag(Girl.x + Girl.halfWidth, Girl.y - Girl.halfHeight, TileFlags.WALL_LEFT_SIDE)) {
                                Girl.vx = 0;
                                Girl.x = GameMap.alignToTileX(Girl.x + Girl.halfWidth) - Girl.halfWidth;
                            }
                        } else if (GameMap.hasFlag(Girl.x - Girl.halfWidth - 1, Girl.y - Girl.halfHeight, TileFlags.WALL_RIGHT_SIDE)) {
                            Girl.vx = 0;
                            Girl.x = GameMap.alignToTileX(Girl.x - Girl.halfWidth - 1) + GameMap.TILE_SIZE + Girl.halfWidth;
                        }

                        if ((Girl.x += Girl.vx) < 0) {
                            Girl.x = 0;
                        }

                        if (Girl.x >= GameMap.pixelWidth) {
                            Girl.x = GameMap.pixelWidth - 1;
                        }

                        if (Player.crate != -1) {
                            if (MathUtil.abs(Girl.x - Crates.x[Player.crate]) >= 20) {
                                Player.crate = -1;
                                if (GameMap.hasFlag(Girl.x, Girl.y, TileFlags.SOLID)) {
                                    Girl.action = PlayerAction.STAND;
                                } else {
                                    Girl.action = PlayerAction.FALL;
                                    Girl.vy = 2;
                                    Girl.vx = 3 * Girl.facing;
                                    Girl.actionTimer2 = 0;
                                }
                            }
                        } else if ((GameMap.flagsAtPixel(Girl.x + Girl.facing * Girl.width, Girl.y) & TileFlags.SOLID) != TileFlags.SOLID) {
                            Girl.action = PlayerAction.STAND;
                            Girl.pose = 0;
                            Girl.vx = 0;
                            Girl.actionTimer = 0;
                            Girl.actionTimer2 = 0;
                        }

                        Dust.spawn(Girl.facing, Girl.x - (Girl.facing << 3), Girl.y);
                        if (Girl.runTimer > 0) {
                            --Girl.runTimer;
                        } else {
                            followPlayer();
                        }
                        break;
                    case PlayerAction.JUMP:
                        if (Girl.vx == 0) {
                            Girl.vx = Girl.facing << 2;
                        }

                        if (Girl.facing == 1) {
                            if ((GameMap.flagsAtPixel(Girl.x + Girl.halfWidth, Girl.y - Girl.halfHeight) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                                Girl.vx = 0;
                            }
                        } else if ((GameMap.flagsAtPixel(Girl.x - Girl.halfWidth, Girl.y - Girl.halfHeight) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                            Girl.vx = 0;
                        }

                        if (Player.y < 0) {
                            Player.y = 0;
                            Player.vy = -1;
                        }

                        if ((Girl.x += Girl.vx) < 0) {
                            Girl.x = 0;
                        }

                        if (Girl.x >= GameMap.pixelWidth) {
                            Girl.x = GameMap.pixelWidth - 1;
                        }

                        Girl.y += Girl.vy;
                        if (Girl.actionTimer == 0) {
                            Girl.pose = 7;
                        } else {
                            Girl.pose = 8;
                        }

                        if (Girl.vx > 1) {
                            --Girl.vx;
                        }

                        if (++Girl.vy == 0) {
                            Girl.action = PlayerAction.FALL;
                            Girl.actionTimer = 0;
                            Girl.actionTimer2 = 0;
                            Girl.vy = 1;
                        }

                        if (Girl.airTimer < 0) {
                            ++Girl.airTimer;
                        }
                        break;
                    case PlayerAction.FALL:
                        if (Girl.facing == 1) {
                            if ((GameMap.flagsAtPixel(Girl.x + Girl.width, Girl.y) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                                Girl.vx = 0;
                            }
                        } else if ((GameMap.flagsAtPixel(Girl.x - Girl.width, Girl.y) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                            Girl.vx = 0;
                        }

                        Girl.slashEffect = -1;
                        if ((Girl.x += Girl.vx) < 0) {
                            Girl.x = 0;
                        }

                        if (Girl.x >= GameMap.pixelWidth) {
                            Girl.x = GameMap.pixelWidth - 1;
                        }

                        Girl.y += Girl.vy;
                        if (Girl.vy < 18) {
                            ++Girl.vy;
                        }

                        if (Girl.vy > 5) {
                            Girl.vx = 0;
                        }

                        if (Girl.vx > 1) {
                            --Girl.vx;
                        }

                        Girl.pose = 8;
                        if (Girl.y >= GameMap.pixelHeight) {
                            Girl.y = GameMap.pixelHeight - 1;
                            Girl.action = PlayerAction.JUMP;
                            Girl.vy = -20;
                        }

                        if (Girl.vy > 4 && (GameMap.flagsAtPixel(Girl.x, Girl.y) & TileFlags.SOLID) == TileFlags.SOLID) {
                            Girl.action = PlayerAction.STAND;
                            Girl.vy = 0;
                            Girl.vx = 0;
                            Girl.actionTimer2 = 0;
                            Girl.actionTimer = 0;
                            Girl.y = GameMap.alignToTileX(Girl.y);
                            Dust.spawn(-1, Girl.x - -8, Girl.y);
                            Dust.spawn(1, Girl.x - 8, Girl.y);
                        }

                        if (GameMap.hasFlag(Girl.x, Girl.y, TileFlags.WATER) && GameMap.hasFlag(Girl.x, Girl.y + 24, TileFlags.DEEP_WATER) && Girl.y % GameMap.TILE_SIZE > 8) {
                            WaterSplash.spawn(Girl.x, GameMap.alignToTileY(Girl.y) + 8);
                            Girl.action = PlayerAction.JUMP;
                            Girl.vx = (Girl.facing = Player.x > Girl.x ? 1 : -1) << 2;
                            Girl.vy = -11;
                        }

                        if (Girl.vy > 6) {
                            Girl.pose = 6;
                        }

                        if (Girl.airTimer < 0) {
                            ++Girl.airTimer;
                        }
                        break;
                    case PlayerAction.ATTACK:
                        switch (++Girl.actionTimer) {
                            case 1:
                            case 2:
                            case 3:
                                Girl.slashEffect = -1;
                                Girl.pose = 9;
                                break;
                            case 4:
                            case 5:
                                Girl.slashEffect = 0;
                                Girl.pose = 10;
                                break;
                            case 6:
                                Girl.slashEffect = 1;
                                Girl.pose = 11;
                                attackHit();
                                break;
                            case 7:
                                Girl.slashEffect = 2;
                                Girl.pose = 11;
                                break;
                            case 8:
                                Girl.slashEffect = -1;
                                Girl.pose = 12;
                                break;
                            case 9:
                                Girl.slashEffect = -1;
                                Girl.pose = 9;
                                Girl.action = PlayerAction.STAND;
                                Girl.actionTimer = 0;
                                Girl.actionTimer2 = 0;
                        }

                        if (Girl.weapon == 1) {
                            Girl.slashEffect = -1;
                        }
                    case PlayerAction.DEAD:
                    default:
                        break;
                    case PlayerAction.HURT:
                        if ((GameMap.flagsAtPixel(Girl.x + Girl.halfWidth, Girl.y - Girl.halfHeight) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                            Girl.vx = -Girl.vx;
                        }

                        if ((GameMap.flagsAtPixel(Girl.x - Girl.halfWidth, Girl.y - Girl.halfHeight) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                            Girl.vx = -Girl.vx;
                        }

                        Girl.x += Girl.vx;
                        Girl.y += Girl.vy;
                        Girl.pose = 7;
                        if (Girl.vx > 1) {
                            --Girl.vx;
                        }

                        if (Girl.vx < -1) {
                            ++Girl.vx;
                        }

                        if (++Girl.vy > 0) {
                            Girl.vy = 0;
                        }

                        if (++Girl.actionTimer >= 7) {
                            Girl.action = PlayerAction.FALL;
                            Girl.airTimer = -20;
                            Girl.actionTimer2 = 0;
                            Girl.actionTimer = 0;
                            Girl.vy = 1;
                        }
                }
            } catch (Exception e) {
                Girl.x = Player.x;
                Girl.y = Player.y;
                Girl.vx = 0;
                Girl.vy = 0;
                Girl.action = PlayerAction.FALL;
            }
        }
    }
}
