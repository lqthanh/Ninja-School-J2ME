package core.player;

import assets.Character;
import core.app.GameState;
import core.app.Settings;
import core.combat.Combat;
import core.fx.Aura;
import core.fx.Blood;
import core.fx.Dust;
import core.fx.FloatingText;
import core.fx.WaterSplash;
import core.input.Input;
import core.map.Crates;
import core.map.GameMap;
import core.map.TileFlags;
import core.monster.BoarBoss;
import core.monster.FinalBoss;
import core.monster.Monsters;
import core.npc.Classmates;
import core.npc.Girl;
import core.npc.Npcs;
import core.npc.Toniku;
import core.quest.Quest;
import core.quest.QuestState;
import core.quest.Skills;
import core.render.Camera;
import core.render.HeroSprites;
import core.render.Viewport;
import core.ui.Hud;
import core.ui.MessageBox;
import core.ui.MessageType;
import core.ui.Notice;
import core.util.MathUtil;
import javax.microedition.lcdui.Image;

/** Cập nhật nhân vật theo hành động hiện tại: di chuyển, va chạm, nhảy, chém, biến hình... */
public final class PlayerController {
    private PlayerController() {
    }

    /** Đứng lên thùng gỗ khi rơi xuống trúng nó. */
    private static void landOnCrates() {
        for(int i = 0; i < Crates.count; ++i) {
            if (Crates.state[i] != Crates.STATE_FALLING && MathUtil.abs(Player.x - Crates.x[i]) < 20 && Crates.y[i] > Player.y && Crates.y[i] - 24 <= Player.y) {
                Player.vx = 0;
                Player.vy = 0;
                Player.y = Crates.y[i] - 24;
                Player.action = PlayerAction.STAND;
                Player.slashEffect = -1;
                Player.crate = i;
                if (Crates.state[i] == Crates.STATE_FLOATING) {
                    WaterSplash.spawn(Crates.x[i], Crates.y[i] - 10);
                    Crates.y[i] += 4;
                    Crates.vy[i] += 4;
                    return;
                }
                break;
            }
        }

    }

    /** Đẩy thùng gỗ khi đang ở trên không. */
    private static void pushCratesInAir() {
        for(int i = 0; i < Crates.count; ++i) {
            if (MathUtil.abs(Player.x - Crates.x[i]) < 20 && MathUtil.abs(Crates.y[i] - Player.y) < 4) {
                Player.vx = 0;
                if (Player.x < Crates.x[i]) {
                    Player.x = Crates.x[i] - 20;
                } else {
                    Player.x = Crates.x[i] + 20;
                }

                if (Crates.state[i] != Crates.STATE_FALLING) {
                    Crates.state[i] = Crates.STATE_SLIDING;
                    Crates.vx[i] = -Player.facing << 1;
                    return;
                }
            }
        }

    }

    /** Đẩy thùng gỗ khi đi trên mặt đất. */
    private static void pushCrates() {
        for(int i = 0; i < Crates.count; ++i) {
            if (MathUtil.abs(Player.x - Crates.x[i]) < 20 && Crates.y[i] == Player.y) {
                Player.vx = 0;
                if (Player.x < Crates.x[i]) {
                    Player.x = Crates.x[i] - 20;
                } else {
                    Player.x = Crates.x[i] + 20;
                }

                if (Crates.state[i] != Crates.STATE_FALLING) {
                    Crates.state[i] = Crates.STATE_SLIDING;
                    Crates.vx[i] = Player.facing << 2;
                    return;
                }
            }
        }

    }

    /** Cập nhật nhân vật theo hành động hiện tại (Player.action). */
    public static void update() {
        if (Camera.followsPlayer) {
            Camera.targetX = Player.x - Viewport.viewCenterX + Camera.leadX * Player.facing;
            Camera.targetY = Player.y - Camera.offsetY;
        }

        int i;
        switch (Player.action) {
            case PlayerAction.STAND:
                if (++Player.actionTimer > 30) {
                    Player.actionTimer = 0;
                }

                if (Player.actionTimer % 15 < 5) {
                    Player.pose = 0;
                } else {
                    Player.pose = 1;
                }

                updateBridges();

                for(i = 0; i < Classmates.count; ++i) {
                    if (Classmates.state[i] == Classmates.STATE_IDLE && Player.y == Classmates.y[i] && MathUtil.abs(Player.x - Classmates.x[i]) < 30 && Player.nearClassmate == -1) {
                        Hud.rightSoftKeyLabel = "Trò chuyện";
                        Hud.dirty = true;
                        Player.nearClassmate = i;
                        break;
                    }
                }

                if (Girl.mapId == GameMap.id && Girl.action == PlayerAction.STAND && Player.y == Girl.y && MathUtil.abs(Player.x - Girl.x) < 30) {
                    Hud.rightSoftKeyLabel = "Nói chuyện";
                    Hud.dirty = true;
                    Player.nearGirl = true;
                }

                for (i = 0; i < Npcs.count; ++i) {
                    if (Player.y == Npcs.y[i] && MathUtil.abs(Player.x - Npcs.x[i]) < 30 && Player.nearNpc == -1) {
                        Hud.rightSoftKeyLabel = "Giao tiếp";
                        Hud.dirty = true;
                        Player.nearNpc = i;
                        break;
                    }
                }
                break;
            case PlayerAction.RUN:
                if (++Player.actionTimer >= 10) {
                    Player.actionTimer = 0;
                    Player.sprintBonus = 0;
                    if (Skills.sprint) {
                        Player.sprintBonus = Player.facing << 1;
                    }
                }

                Player.pose = (Player.actionTimer >> 1) + 2;
                if (Player.facing == 1) {
                    if (GameMap.hasFlag(Player.x + Player.halfWidth, Player.y - Player.halfHeight, TileFlags.WALL_LEFT_SIDE)) {
                        Player.vx = 0;
                        Player.x = GameMap.alignToTileX(Player.x + Player.halfWidth) - Player.halfWidth;
                    }
                } else if (GameMap.hasFlag(Player.x - Player.halfWidth - 1, Player.y - Player.halfHeight, TileFlags.WALL_RIGHT_SIDE)) {
                    Player.vx = 0;
                    Player.x = GameMap.alignToTileX(Player.x - Player.halfWidth - 1) + GameMap.TILE_SIZE + Player.halfWidth;
                }

                if ((GameMap.flagsAtPixel(Player.x, Player.y - 1) & TileFlags.WATER) == TileFlags.WATER) {
                    Player.x += Player.vx >> 1;
                } else {
                    Player.x += Player.vx;
                }

                if (Player.vx > 0) {
                    --Player.vx;
                } else if (Player.vx < 0) {
                    ++Player.vx;
                } else {
                    Player.action = PlayerAction.STAND;
                    Player.sprintBonus = 0;
                }

                if (Player.crate != -1) {
                    if (MathUtil.abs(Player.x - Crates.x[Player.crate]) >= 20) {
                        Player.crate = -1;
                        if (GameMap.hasFlag(Player.x, Player.y, TileFlags.SOLID)) {
                            Player.action = PlayerAction.STAND;
                        } else {
                            Player.action = PlayerAction.FALL;
                            Player.vy = 2;
                            Player.vx = 3 * Player.facing;
                            Player.actionTimer2 = 0;
                        }
                    }
                } else if ((GameMap.flagsAtPixel(Player.x, Player.y) & TileFlags.SOLID) != TileFlags.SOLID) {
                    Player.action = PlayerAction.FALL;
                    Player.vx = 3 * Player.facing;
                    Player.actionTimer2 = 0;
                }

                pushCrates();
                Dust.spawn(Player.facing, Player.x - (Player.facing << 3), Player.y);
                updateBridges();
                break;
            case PlayerAction.JUMP:
                Player.x += Player.vx;
                if ((Player.y += Player.vy) < 0) {
                    Player.y = 0;
                    Player.vy = -1;
                }

                if (Player.actionTimer == 0) {
                    Player.pose = 7;
                } else {
                    Player.pose = 23;
                }

                ++Player.vy;
                if (Skills.lightness && !Player.transformed) {
                    if (Player.vy == -3) {
                        Player.pose = 8;
                    } else if (Player.vy == -2) {
                        Player.pose = 9;
                    } else if (Player.vy == -1) {
                        Player.pose = 10;
                    } else if (Player.vy == 0) {
                        Player.pose = 11;
                    }
                }

                if (Player.vy == 0) {
                    Player.action = PlayerAction.FALL;
                    Player.actionTimer = 0;
                    Player.actionTimer2 = Skills.lightness ? 1 : 0;
                    Player.vy = 1;
                    Hud.dirty = true;
                }

                if (Player.hurtRecovery < 0) {
                    ++Player.hurtRecovery;
                }

                if (Player.facing == 1) {
                    if ((GameMap.flagsAtPixel(Player.x + Player.halfWidth, Player.y) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                        Player.x = GameMap.alignToTileX(Player.x + Player.halfWidth) - Player.halfWidth;
                        Player.vx = 0;
                    }
                } else if ((GameMap.flagsAtPixel(Player.x - Player.halfWidth, Player.y) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                    Player.x = GameMap.alignToTileX(Player.x + 24 - Player.halfWidth) + Player.halfWidth;
                    Player.vx = 0;
                }
                break;
            case PlayerAction.FALL:
                Player.slashEffect = -1;
                Player.x += Player.vx;
                Player.y += Player.vy;
                if (Player.vy < 18) {
                    ++Player.vy;
                }

                if (Player.vx > 0) {
                    --Player.vx;
                }

                Player.pose = 7;
                if (Player.y >= GameMap.pixelHeight) {
                    Player.action = PlayerAction.DEAD;
                    PlayerStats.kill();
                } else if (Player.y + 4 >= GameMap.pixelHeight) {
                    Player.action = PlayerAction.DEAD;
                    PlayerStats.kill();
                } else if (Player.vy > 4 && (GameMap.flagsAtPixel(Player.x, Player.y + 3) & TileFlags.SOLID) == TileFlags.SOLID) {
                    if (Player.vy > 12) {
                        Player.action = PlayerAction.CROUCH;
                    } else {
                        Player.action = PlayerAction.STAND;
                    }

                    Player.vy = 0;
                    Player.vx = 0;
                    Player.actionTimer2 = 0;
                    Player.actionTimer = 0;
                    Player.y = GameMap.alignToTileX(Player.y + 3);
                    Dust.spawn(-1, Player.x - -8, Player.y);
                    Dust.spawn(1, Player.x - 8, Player.y);
                    if (Skills.motorbikeLicense) {
                        for(i = 0; i < Motorbikes.count; ++i) {
                            if (Player.y == Motorbikes.y[i] && MathUtil.abs(Player.x - Motorbikes.x[i]) < 16) {
                                Player.x = Motorbikes.x[i];
                                Player.action = PlayerAction.MOTORBIKE_STAND;
                                Player.motorbike = i;
                                Motorbikes.facing[i] = Player.facing;
                                Motorbikes.frame[i] = 0;
                            }
                        }
                    }

                    if (QuestState.current == Quest.AIR_HITS && QuestState.progress < Quest.TARGET_COUNT[Quest.AIR_HITS]) {
                        QuestState.progress = 0;
                        Notice.show("Hit: " + QuestState.progress);
                    }
                } else {
                    if (GameMap.hasFlag(Player.x, Player.y, TileFlags.WATER) && Player.y % GameMap.TILE_SIZE > 8) {
                        WaterSplash.spawn(Player.x, GameMap.alignToTileY(Player.y) + 8);
                        Player.y = GameMap.alignToTileY(Player.y) + 8;
                        Player.action = PlayerAction.RUN_ON_WATER;
                        Player.vx = Player.facing << 1;
                        Player.vy >>= 2;
                        Player.y = GameMap.alignToTileY(Player.y) + 12;
                        if (!PlayerStats.hasMp() || !Skills.runOnWater) {
                            Player.action = PlayerAction.SINK;
                        }
                    }

                    if (GameMap.hasFlag(Player.x, Player.y, TileFlags.DEEP_WATER)) {
                        Player.action = PlayerAction.SINK;
                    }

                    if (Player.actionTimer2 == 1 && !Player.transformed) {
                        if (Player.vy == 3) {
                            Player.pose = 11;
                        } else if (Player.vy == 2) {
                            Player.pose = 8;
                        } else if (Player.vy == 1) {
                            Player.pose = 9;
                        } else if (Player.vy == 0) {
                            Player.pose = 10;
                        }
                    } else {
                        Player.pose = 23;
                    }

                    if (Player.vy > 6) {
                        Player.pose = 12;
                    }

                    if (Player.hurtRecovery < 0) {
                        ++Player.hurtRecovery;
                    }

                    if (Player.facing == 1) {
                        if ((GameMap.flagsAtPixel(Player.x + Player.halfWidth, Player.y) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                            Player.x = GameMap.alignToTileX(Player.x + Player.halfWidth) - Player.halfWidth;
                            Player.vx = 0;
                        }
                    } else if ((GameMap.flagsAtPixel(Player.x - Player.halfWidth, Player.y) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                        Player.x = GameMap.alignToTileX(Player.x + 24 - Player.halfWidth) + Player.halfWidth;
                        Player.vx = 0;
                    }

                    landOnCrates();
                    if (BoarBoss.active && Player.vy >= 4 && MathUtil.abs(Player.x - BoarBoss.x) < 30 && MathUtil.abs(Player.y - (BoarBoss.y - 25)) < 5) {
                        Player.vy = -(Player.vy >> 1);
                        Player.vx = Player.facing << 3;
                        Player.actionTimer = 0;
                    }
                }
                break;
            case PlayerAction.ATTACK:
                ++Player.actionTimer;
                if (Player.facing == 1) {
                    if ((GameMap.flagsAtPixel(Player.x + Player.halfWidth, Player.y - Player.halfHeight) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                        Player.vx = 0;
                    }
                } else if ((GameMap.flagsAtPixel(Player.x - Player.halfWidth, Player.y - Player.halfHeight) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                    Player.vx = 0;
                }

                Player.x += Player.vx;
                if ((Player.y += Player.vy) < 0) {
                    Player.vy = 0;
                    Player.y = 0;
                }

                if (Player.vy == 0) {
                    if (Player.crate == -1 && (GameMap.flagsAtPixel(Player.x, Player.y) & TileFlags.SOLID) != TileFlags.SOLID) {
                        Player.action = PlayerAction.FALL;
                        Player.slashEffect = -1;
                        Player.vx = (Player.moveSpeed >> 1) * Player.facing;
                        Player.actionTimer2 = 0;
                        Player.actionTimer = 0;
                    }

                    if (Player.crate != -1 && MathUtil.abs(Player.x - Crates.x[Player.crate]) >= 20) {
                        Player.crate = -1;
                        if (GameMap.hasFlag(Player.x, Player.y, TileFlags.SOLID)) {
                            Player.action = PlayerAction.STAND;
                        } else {
                            Player.action = PlayerAction.FALL;
                            Player.vy = 2;
                            Player.vx = 3 * Player.facing;
                            Player.slashEffect = -1;
                            Player.actionTimer2 = 0;
                        }
                    }
                } else if (Player.vy < 0) {
                    if (++Player.vy == 0) {
                        Player.vy = 1;
                    }
                } else {
                    if (Player.vy < 20 && Player.actionTimer % 5 == 0) {
                        ++Player.vy;
                    }

                    if (Player.vy > 3) {
                        Player.vy = 3;
                    }

                    if (Player.vy > 2 && (GameMap.flagsAtPixel(Player.x, Player.y + 3) & TileFlags.SOLID) == TileFlags.SOLID) {
                        Player.vy = 0;
                        Player.vx = 0;
                        Player.y = GameMap.alignToTileX(Player.y + 3);
                        if (QuestState.current == Quest.AIR_HITS && QuestState.progress < Quest.TARGET_COUNT[Quest.AIR_HITS]) {
                            QuestState.progress = 0;
                            Notice.show("Hit: " + QuestState.progress);
                        }
                    }

                    if (GameMap.hasFlag(Player.x, Player.y, TileFlags.WATER) && Player.y % GameMap.TILE_SIZE > 8) {
                        WaterSplash.spawn(Player.x, GameMap.alignToTileY(Player.y) + 8);
                        Player.action = PlayerAction.RUN_ON_WATER;
                        Player.vx = Player.facing << 1;
                        Player.vy >>= 2;
                        Player.y = GameMap.alignToTileY(Player.y) + 12;
                        Player.slashEffect = -1;
                        if (!PlayerStats.hasMp() || !Skills.runOnWater) {
                            Player.action = PlayerAction.SINK;
                        }
                        break;
                    }

                    if (GameMap.hasFlag(Player.x, Player.y, TileFlags.DEEP_WATER)) {
                        Player.action = PlayerAction.SINK;
                        Player.slashEffect = -1;
                        break;
                    }

                    landOnCrates();
                }

                if (Player.vx > 0) {
                    --Player.vx;
                } else if (Player.vx < 0) {
                    ++Player.vx;
                }

                if (Player.vx != 0) {
                    pushCrates();
                }

                switch (Player.comboStep) {
                    case Player.COMBO_FIRST_SLASH:
                        switch (Player.actionTimer) {
                            case 1:
                            case 2:
                            case 3:
                                Player.slashEffect = -1;
                                Player.pose = 13;
                                break;
                            case 4:
                                Player.slashEffect = 0;
                                Player.pose = 14;
                                break;
                            case 5:
                                Player.slashEffect = 1;
                                Player.pose = 15;
                                break;
                            case 6:
                                Player.slashEffect = 1;
                                Player.pose = 15;
                                Combat.playerSwordHit();
                                break;
                            case 7:
                                Player.slashEffect = 2;
                                Player.pose = 16;
                                break;
                            case 8:
                                Player.slashEffect = 2;
                                Player.pose = 16;
                                if (Player.comboQueued) {
                                    Player.comboQueued = false;
                                    Player.comboStep = Player.COMBO_SECOND_SLASH;
                                } else {
                                    Player.comboStep = Player.COMBO_FIRST_SLASH_END;
                                }

                                Player.actionTimer = 1;
                            default:
                                break;
                        }
                        break;
                    case Player.COMBO_FIRST_SLASH_END:
                        switch (Player.actionTimer) {
                            case 1:
                                Player.slashEffect = 1;
                                Player.pose = 15;
                                break;
                            case 2:
                                Player.slashEffect = 1;
                                Player.pose = 15;
                                Player.comboStep = Player.COMBO_RECOVER;
                            default:
                                break;
                        }
                        break;
                    case Player.COMBO_SECOND_SLASH:
                        switch (Player.actionTimer) {
                            case 1:
                            case 2:
                            case 3:
                                Player.slashEffect = 3;
                                Player.pose = 17;
                                break;
                            case 4:
                                Player.slashEffect = 4;
                                Player.pose = 18;
                                break;
                            case 5:
                                Player.slashEffect = 5;
                                Player.pose = 19;
                                break;
                            case 6:
                                Player.slashEffect = 6;
                                Player.pose = 20;
                                Combat.playerSwordHit();
                                break;
                            case 7:
                                Player.slashEffect = 6;
                                Player.pose = 20;
                                if (Player.comboQueued && Player.comboCount < 2) {
                                    if (Player.vy == 0 && Player.launchHit) {
                                        Player.actionTimer = 0;
                                        Player.comboQueued = false;
                                        Player.comboStep = Player.COMBO_LAUNCH_SLASH;
                                    } else {
                                        Player.comboQueued = false;
                                        Player.comboStep = Player.COMBO_THIRD_SLASH;
                                        ++Player.comboCount;
                                    }
                                } else {
                                    Player.comboStep = Player.COMBO_RECOVER;
                                }

                                Player.actionTimer = 1;
                            default:
                                break;
                        }
                        break;
                    case Player.COMBO_THIRD_SLASH:
                        switch (Player.actionTimer) {
                            case 1:
                                Player.slashEffect = 1;
                                Player.pose = 15;
                                break;
                            case 2:
                                Player.slashEffect = 1;
                                Player.pose = 15;
                                break;
                            case 3:
                                Player.slashEffect = 2;
                                Player.pose = 16;
                                break;
                            case 4:
                                Player.slashEffect = 2;
                                Player.pose = 16;
                                if (Player.comboQueued) {
                                    Player.comboQueued = false;
                                    Player.comboStep = Player.COMBO_SECOND_SLASH;
                                } else {
                                    Player.comboStep = Player.COMBO_FIRST_SLASH_END;
                                }

                                Player.actionTimer = 1;
                        }
                        break;
                    case Player.COMBO_LAUNCH_SLASH:
                        switch (Player.actionTimer) {
                            case 1:
                            case 2:
                            case 3:
                                Player.slashEffect = -1;
                                Player.pose = 13;
                                break;
                            case 4:
                                Player.slashEffect = 0;
                                Player.pose = 14;
                                break;
                            case 5:
                            case 6:
                                Player.slashEffect = 9;
                                Player.pose = 15;
                                i = Combat.findLaunchTarget();
                                Player.launchHit = false;
                                if (i != -1) {
                                    Player.launchHit = true;
                                    Blood.spawn(Monsters.x[i], Monsters.y[i] - (Monsters.height[i] >> 1), Player.facing);
                                    Monsters.state[i] = Monsters.STATE_LAUNCHED;
                                    Monsters.timer[i] = -8;
                                    Camera.shakeIfTransformed();
                                }
                                break;
                            case 7:
                            case 8:
                                Player.slashEffect = 10;
                                Player.pose = 16;
                                break;
                            case 9:
                                Player.slashEffect = -1;
                                Player.pose = 16;
                                if (Player.launchHit) {
                                    Player.action = PlayerAction.JUMP;
                                    Player.vx = 0;
                                    Player.vy = -8;
                                } else {
                                    Player.actionTimer = 0;
                                    Player.actionTimer2 = 0;
                                    Player.comboStep = Player.COMBO_RECOVER;
                                }
                            default:
                                break;
                        }
                        break;
                    case Player.COMBO_RECOVER:
                        switch (Player.actionTimer) {
                            case 1:
                                Player.slashEffect = 2;
                                Player.pose = 16;
                                break;
                            case 2:
                                Player.slashEffect = 0;
                                Player.pose = 14;
                                break;
                            case 3:
                            case 4:
                                Player.pose = 13;
                                Player.slashEffect = -1;
                                break;
                            case 5:
                                Player.pose = 13;
                                Player.slashEffect = -1;
                                if (Player.vy != 0) {
                                    Player.action = PlayerAction.FALL;
                                } else {
                                    Player.action = PlayerAction.STAND;
                                }

                                Player.comboCount = 0;
                                Player.actionTimer2 = 0;
                        }
                        break;
                }

                if (Player.weapon == 1) {
                    Player.slashEffect = -1;
                }

                if (Player.action == PlayerAction.ATTACK) {
                    if (Player.vy != 0) {
                        Player.pose += 14;
                    } else if (Player.vx != 0) {
                        Player.pose += 22;
                    }
                }
                break;
            case PlayerAction.DEAD:
                Player.x += Player.vx;
                if (Player.vx > 1) {
                    --Player.vx;
                }

                if (Player.vx < -1) {
                    ++Player.vx;
                }

                ++Player.vy;
                Player.y += Player.vy;
                Player.pose = 21;
                if (Player.y >= GameMap.pixelHeight || Player.y > Camera.y + Viewport.viewHeight + Player.height || GameMap.hasFlag(Player.x, Player.y - Player.height, TileFlags.DEEP_WATER)) {
                    Player.x = Player.checkpointX;
                    Player.y = Player.checkpointY;
                    Player.action = PlayerAction.FALL;
                    PlayerStats.revive();
                    Player.vy = 0;
                    Player.vx = 0;
                    Player.actionTimer2 = 0;
                    Player.actionTimer = 0;
                    Leveling.applyDeathPenalty();
                    Hud.dirty = true;
                    if (Toniku.active) {
                        Toniku.hp = Toniku.maxHp;
                    }

                    if (FinalBoss.active) {
                        FinalBoss.hp = FinalBoss.maxHp;
                    }

                    MessageBox.show(MessageType.GAME_NOTICE);
                    GameState.current = GameState.MESSAGE;
                }
                break;
            case PlayerAction.HURT:
                if ((GameMap.flagsAtPixel(Player.x + Player.halfWidth, Player.y - Player.halfHeight) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                    Player.vx = -Player.vx;
                }

                if ((GameMap.flagsAtPixel(Player.x - Player.halfWidth, Player.y - Player.halfHeight) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                    Player.vx = -Player.vx;
                }

                Player.x += Player.vx;
                Player.y += Player.vy;
                Player.pose = 21;
                if (Player.vx > 1) {
                    --Player.vx;
                }

                if (Player.vx < -1) {
                    ++Player.vx;
                }

                if (++Player.vy > 0) {
                    Player.vy = 0;
                }

                if (++Player.actionTimer >= 7) {
                    Player.action = PlayerAction.FALL;
                    Player.hurtRecovery = -20;
                    Player.actionTimer2 = 0;
                    Player.actionTimer = 0;
                    Player.vy = 0;
                }

                pushCratesInAir();
                break;
            case PlayerAction.CROUCH:
                Player.pose = 22;
                if (Player.actionTimer < 5) {
                    ++Player.actionTimer;
                } else if (!Input.held[Input.KEY_DOWN]) {
                    Player.action = PlayerAction.STAND;
                }

                if (++Player.actionTimer2 > 6 && GameMap.hasFlag(Player.x + Player.halfWidth, Player.y + 2, TileFlags.DIGGABLE) && GameMap.hasFlag(Player.x - Player.halfWidth, Player.y + 2, TileFlags.DIGGABLE) && Skills.burrow & PlayerStats.hasMp()) {
                    Player.action = PlayerAction.BURROW_START;
                }
            case PlayerAction.UNUSED_9:
            case PlayerAction.UNUSED_10:
            case PlayerAction.UNUSED_22:
            default:
                break;
            case PlayerAction.RUN_ON_WATER:
                updateRunOnWater();
                break;
            case PlayerAction.SINK:
                Player.y += Player.vy;
                if (Player.vy < 20 && GameState.tick % 2 == 0) {
                    ++Player.vy;
                }

                Player.pose = 7;
                if (Player.y >= GameMap.pixelHeight) {
                    PlayerStats.kill();
                    Player.action = PlayerAction.DEAD;
                    Player.slashEffect = -1;
                } else if (GameMap.hasFlag(Player.x, Player.y, TileFlags.SOLID)) {
                    Player.y = GameMap.alignToTileY(Player.y);
                    if (GameMap.hasFlag(Player.x, Player.y - 2, TileFlags.WATER)) {
                        Player.action = PlayerAction.STAND;
                        Player.vy = 0;
                        Player.vx = 0;
                        Player.actionTimer2 = 0;
                        Player.actionTimer = 0;
                    } else if (Skills.dive) {
                        Player.action = PlayerAction.UNDERWATER_STAND;
                        Player.vy = 0;
                        Player.vx = 0;
                        Player.actionTimer2 = 0;
                        Player.actionTimer = 0;
                    } else {
                        Player.action = PlayerAction.DEAD;
                    }

                    Player.y = GameMap.alignToTileX(Player.y);
                } else if (GameMap.hasFlag(Player.x, Player.y - Player.height - 5, TileFlags.DEEP_WATER)) {
                    PlayerStats.kill();
                    Player.action = PlayerAction.DEAD;
                    Player.slashEffect = -1;
                }
                break;
            case PlayerAction.BURROW_START:
                Player.pose = 24;
                Player.y += 28;
                Player.action = PlayerAction.BURROWED;
                break;
            case PlayerAction.BURROWED:
                if (GameState.tick % 20 > 2) {
                    Player.pose = 25;
                } else {
                    Player.pose = 26;
                }

                if (GameState.tick % 8 == 0) {
                    if (PlayerStats.drainMp(1)) {
                        Player.action = PlayerAction.BURROW_EXIT;
                    }

                    Hud.dirty = true;
                }
                break;
            case PlayerAction.BURROW_EXIT:
                Player.y -= 10;
                Player.vy = -10;
                Player.action = PlayerAction.JUMP;
                break;
            case PlayerAction.UNDERWATER_WALK:
                if (++Player.actionTimer >= 20) {
                    Player.actionTimer = 0;
                }

                if ((Player.pose = (Player.actionTimer >> 2) + 2) % 2 == 0) {
                    Player.slashEffect = 7;
                } else {
                    Player.slashEffect = 8;
                }

                if (Player.facing == 1) {
                    if (GameMap.hasFlag(Player.x + Player.halfWidth, Player.y - Player.halfHeight, TileFlags.WALL_LEFT_SIDE)) {
                        Player.vx = 0;
                        Player.x = GameMap.alignToTileX(Player.x + Player.halfWidth) - Player.halfWidth;
                    }
                } else if (GameMap.hasFlag(Player.x - Player.halfWidth - 1, Player.y - Player.halfHeight, TileFlags.WALL_RIGHT_SIDE)) {
                    Player.vx = 0;
                    Player.x = GameMap.alignToTileX(Player.x - Player.halfWidth - 1) + GameMap.TILE_SIZE + Player.halfWidth;
                }

                Player.x += Player.vx;
                if (Player.vx > 0) {
                    --Player.vx;
                } else if (Player.vx < 0) {
                    ++Player.vx;
                } else {
                    Player.action = PlayerAction.UNDERWATER_STAND;
                }

                if ((GameMap.flagsAtPixel(Player.x, Player.y) & TileFlags.SOLID) != TileFlags.SOLID) {
                    Player.action = PlayerAction.SINK;
                    Player.vx = Player.facing << 2;
                    Player.actionTimer2 = 0;
                }
                break;
            case PlayerAction.UNDERWATER_STAND:
                if (++Player.actionTimer > 30) {
                    Player.actionTimer = 0;
                }

                if (Player.actionTimer % 15 < 5) {
                    Player.pose = 0;
                    Player.slashEffect = 7;
                } else {
                    Player.pose = 1;
                    Player.slashEffect = 8;
                }
                break;
            case PlayerAction.MOTORBIKE_STAND:
                if (GameState.tick % 8 > 3) {
                    Player.pose = 43;
                } else {
                    Player.pose = 44;
                    Dust.spawn(Player.facing, Player.x - (Player.facing << 4), Player.y - 5);
                }

                Player.actionTimer = 0;
                break;
            case PlayerAction.MOTORBIKE_RIDE:
                if (GameState.tick % 8 > 3) {
                    Player.pose = 43;
                } else {
                    Player.pose = 44;
                    Dust.spawn(Player.facing, Player.x - (Player.facing << 4), Player.y - 5);
                }

                if (Player.actionTimer > 0) {
                    --Player.actionTimer;
                    Player.pose = 45;
                }

                if (Player.facing == 1) {
                    if (GameMap.hasFlag(Player.x + Player.halfWidth, Player.y - Player.halfHeight, TileFlags.WALL_LEFT_SIDE)) {
                        Player.vx = 0;
                        Player.x = GameMap.alignToTileX(Player.x + Player.halfWidth) - Player.halfWidth;
                    }
                } else if (GameMap.hasFlag(Player.x - Player.halfWidth - 1, Player.y - Player.halfHeight, TileFlags.WALL_RIGHT_SIDE)) {
                    Player.vx = 0;
                    Player.x = GameMap.alignToTileX(Player.x - Player.halfWidth - 1) + GameMap.TILE_SIZE + Player.halfWidth;
                }

                if ((GameMap.flagsAtPixel(Player.x, Player.y - 1) & TileFlags.WATER) == TileFlags.WATER) {
                    Player.x += Player.vx >> 1;
                } else {
                    Player.x += Player.vx;
                }

                Motorbikes.x[Player.motorbike] = Player.x;
                Motorbikes.y[Player.motorbike] = Player.y;
                if (Player.vx > 0) {
                    --Player.vx;
                } else if (Player.vx < 0) {
                    ++Player.vx;
                } else {
                    Player.action = PlayerAction.MOTORBIKE_STAND;
                    Player.sprintBonus = 0;
                }

                if (Player.crate != -1) {
                    if (MathUtil.abs(Player.x - Crates.x[Player.crate]) >= 20) {
                        Player.crate = -1;
                        if (GameMap.hasFlag(Player.x, Player.y, TileFlags.SOLID)) {
                            Player.action = PlayerAction.MOTORBIKE_STAND;
                        } else {
                            Player.action = PlayerAction.MOTORBIKE_FALL;
                            Player.vy = 2;
                            Player.vx = 3 * Player.facing;
                            Player.actionTimer2 = 0;
                        }
                    }
                } else if ((GameMap.flagsAtPixel(Player.x, Player.y) & TileFlags.SOLID) != TileFlags.SOLID) {
                    Player.action = PlayerAction.MOTORBIKE_FALL;
                    Player.vx = 3 * Player.facing;
                    Player.actionTimer2 = 0;
                }

                Dust.spawn(Player.facing, Player.x - (Player.facing << 3), Player.y);
                updateBridges();
                if ((i = Combat.findLaunchTarget()) != -1 && (Monsters.state[i] == Monsters.STATE_ACTIVE || Monsters.state[i] == Monsters.STATE_RESTING)) {
                    Combat.damageMonster(i, 2, -Player.facing);
                    Blood.spawn(Monsters.x[i], Monsters.y[i] - (Monsters.height[i] >> 1), Player.facing);
                    FloatingText.spawn("-2", 0, Monsters.x[i], Monsters.y[i] - Monsters.height[i], 0, -2);
                }
                break;
            case PlayerAction.MOTORBIKE_FALL:
                try {
                    if (Player.facing == 1) {
                        if ((GameMap.flagsAtPixel(Player.x + Player.halfWidth, Player.y - Player.halfHeight) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                            Player.vx = 0;
                        }
                    } else if ((GameMap.flagsAtPixel(Player.x - Player.halfWidth, Player.y - Player.halfHeight) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                        Player.vx = 0;
                    }

                    Player.slashEffect = -1;
                    Player.x += Player.vx;
                    Player.y += Player.vy;
                    if (Player.vy < 20) {
                        ++Player.vy;
                    }

                    if (Player.vx > 0) {
                        --Player.vx;
                    }

                    if (GameState.tick % 8 > 3) {
                        Player.pose = 43;
                    } else {
                        Player.pose = 44;
                        Dust.spawn(Player.facing, Player.x - (Player.facing << 4), Player.y - 5);
                    }

                    if (Player.vy > 4 && (GameMap.flagsAtPixel(Player.x, Player.y) & TileFlags.SOLID) == TileFlags.SOLID) {
                        if (Player.vy < 8) {
                            Player.action = PlayerAction.MOTORBIKE_STAND;
                            Player.vy = 0;
                            Player.vx = 0;
                            Player.actionTimer2 = 0;
                            Player.actionTimer = 0;
                            Player.y = GameMap.alignToTileX(Player.y);
                        } else {
                            Player.action = PlayerAction.MOTORBIKE_JUMP;
                            Player.vy = -(Player.vy / 3);
                            Player.y = GameMap.alignToTileX(Player.y);
                        }
                    }

                    if (GameMap.hasFlag(Player.x, Player.y, TileFlags.WATER) && Player.y % GameMap.TILE_SIZE > 8) {
                        WaterSplash.spawn(Player.x, GameMap.alignToTileY(Player.y) + 8);
                    }

                    Motorbikes.x[Player.motorbike] = Player.x;
                    Motorbikes.y[Player.motorbike] = Player.y;
                    if (GameMap.hasFlag(Player.x, Player.y, TileFlags.DEEP_WATER)) {
                        Motorbikes.falling[Player.motorbike] = 1;
                        Player.motorbike = -1;
                        Player.action = PlayerAction.SINK;
                    }
                } catch (Exception e) {
                }

                if (Player.hurtRecovery < 0) {
                    ++Player.hurtRecovery;
                }
                break;
            case PlayerAction.MOTORBIKE_JUMP:
                if (Player.facing == 1) {
                    if ((GameMap.flagsAtPixel(Player.x + Player.halfWidth, Player.y - Player.halfHeight) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                        Player.vx = 0;
                    }
                } else if ((GameMap.flagsAtPixel(Player.x - Player.halfWidth, Player.y - Player.halfHeight) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                    Player.vx = 0;
                }

                Player.x += Player.vx;
                Player.y += Player.vy;
                Motorbikes.x[Player.motorbike] = Player.x;
                Motorbikes.y[Player.motorbike] = Player.y;
                if (Player.vx > 0) {
                    --Player.vx;
                }

                if (++Player.vy == 0) {
                    Player.action = PlayerAction.MOTORBIKE_FALL;
                    Player.actionTimer = 0;
                    Player.actionTimer2 = Skills.lightness ? 1 : 0;
                    Player.vy = 1;
                    Hud.dirty = true;
                }

                if (Player.hurtRecovery < 0) {
                    ++Player.hurtRecovery;
                }

                if (GameState.tick % 8 > 3) {
                    Player.pose = 43;
                } else {
                    Player.pose = 44;
                    Dust.spawn(Player.facing, Player.x - (Player.facing << 4), Player.y - 5);
                }

                if (Player.actionTimer > 0) {
                    --Player.actionTimer;
                    Player.pose = 45;
                }
                break;
            case PlayerAction.TRANSFORM:
                updateTransform();
        }

        if (GameState.tick % 3 == 0) {
            if (Player.hidden) {
                if (PlayerStats.drainMp(1)) {
                    Player.hidden = false;
                }

                Hud.dirty = true;
            }

            if (Player.transformed) {
                if (PlayerStats.drainMp(1)) {
                    Player.transformed = false;

                    for(int k = 0; k < 4; ++k) {
                        Image swap = HeroSprites.heads[k];
                        HeroSprites.heads[k] = HeroSprites.transformHeads[k];
                        HeroSprites.transformHeads[k] = swap;
                    }
                }

                Hud.dirty = true;
            }
        }

    }

    /** Ván cầu dưới chân lún xuống, ván hai bên bật lên lại (chỉ ở đồ họa cao). */
    private static final void updateBridges() {
        if (!Settings.lowGraphics) {
            if (GameMap.hasFlag(Player.x, Player.y + 1, TileFlags.BRIDGE)) {
                GameMap.pressBridge(Player.x, Player.y + 1);
                GameMap.pressBridge(Player.x, Player.y - 2);
            }

            if (GameMap.hasFlag(Player.x - GameMap.TILE_SIZE, Player.y + 1, TileFlags.BRIDGE_PRESSED)) {
                GameMap.releaseBridge(Player.x - GameMap.TILE_SIZE, Player.y + 1);
                GameMap.releaseBridge(Player.x - GameMap.TILE_SIZE, Player.y - 2);
            }

            if (GameMap.hasFlag(Player.x + GameMap.TILE_SIZE, Player.y + 1, TileFlags.BRIDGE_PRESSED)) {
                GameMap.releaseBridge(Player.x + GameMap.TILE_SIZE, Player.y + 1);
                GameMap.releaseBridge(Player.x + GameMap.TILE_SIZE, Player.y - 2);
            }

        }
    }

    /** Hoạt ảnh biến hình (30 frame): hiện hào quang, rung màn hình, rồi đổi sang ảnh đầu biến hình nếu còn MP. */
    private static void updateTransform() {
        if (++Player.actionTimer == 1) {
            Aura.show(Player.x, Player.y - 40);
            Camera.shakeStep = 0;
            if (HeroSprites.transformHeads == null) {
                HeroSprites.transformHeads = new Image[4];

                try {
                    HeroSprites.transformHeads[0] = Character.loadHeroTransformHead(0);
                    HeroSprites.transformHeads[1] = Character.loadHeroTransformHead(1);
                } catch (Exception e) {
                }

                HeroSprites.transformHeads[2] = HeroSprites.transformHeads[1];
                HeroSprites.transformHeads[3] = HeroSprites.transformHeads[0];
            }
        } else {
            Image swap;
            int i;
            if (Player.actionTimer >= 30) {
                Player.action = PlayerAction.STAND;
                Player.actionTimer = 0;
                if (!PlayerStats.hasMp()) {
                    return;
                }

                for(i = 0; i < 4; ++i) {
                    swap = HeroSprites.heads[i];
                    HeroSprites.heads[i] = HeroSprites.transformHeads[i];
                    HeroSprites.transformHeads[i] = swap;
                }

                Player.transformed = !Player.transformed;
            } else if (Player.actionTimer <= 10) {
                if (Player.actionTimer % 5 == 0) {
                    for(i = 0; i < 4; ++i) {
                        swap = HeroSprites.heads[i];
                        HeroSprites.heads[i] = HeroSprites.transformHeads[i];
                        HeroSprites.transformHeads[i] = swap;
                    }
                }
            } else if (Player.actionTimer < 20) {
                if (Player.actionTimer % 3 == 0) {
                    for(i = 0; i < 4; ++i) {
                        swap = HeroSprites.heads[i];
                        HeroSprites.heads[i] = HeroSprites.transformHeads[i];
                        HeroSprites.transformHeads[i] = swap;
                    }
                }
            } else if (Player.actionTimer < 30 && Player.actionTimer % 2 == 0) {
                for(i = 0; i < 4; ++i) {
                    swap = HeroSprites.heads[i];
                    HeroSprites.heads[i] = HeroSprites.transformHeads[i];
                    HeroSprites.transformHeads[i] = swap;
                }
            }
        }

        if (Player.actionTimer % 2 > 0) {
            Player.pose = 0;
        } else {
            Player.pose = 1;
        }
    }

    /** Chạy trên mặt nước: chặn tường, giảm tốc dần; dừng lại thì chìm (trừ khi có kỹ năng đứng trên nước). */
    private static void updateRunOnWater() {
        if (++Player.actionTimer >= 5) {
            Player.actionTimer = 0;
            Player.sprintBonus = 0;
        }

        Player.pose = Player.actionTimer + 2;
        if (Player.facing == 1) {
            if (GameMap.hasFlag(Player.x + Player.halfWidth, Player.y - 1, TileFlags.WALL_LEFT_SIDE)) {
                Player.vx = 0;
                Player.x = GameMap.alignToTileX(Player.x + Player.halfWidth) - Player.halfWidth;
            }
        } else if (GameMap.hasFlag(Player.x - Player.halfWidth - 1, Player.y - 1, TileFlags.WALL_RIGHT_SIDE)) {
            Player.vx = 0;
            Player.x = GameMap.alignToTileX(Player.x - Player.halfWidth - 1) + GameMap.TILE_SIZE + Player.halfWidth;
        }

        Player.x += Player.vx;
        if (Player.vx > 0) {
            --Player.vx;
        } else if (Player.vx < 0) {
            ++Player.vx;
        } else if (!Skills.standOnWater) {
            Player.action = PlayerAction.SINK;
            Player.sprintBonus = 0;
        } else {
            Player.action = PlayerAction.JUMP;
            Player.vy = -8;
            Player.actionTimer2 = 0;
            Player.actionTimer = 0;
        }

        if (GameState.tick % 8 == 0) {
            if (PlayerStats.drainMp(3)) {
                Player.vx = 0;
                Player.action = PlayerAction.SINK;
            }

            Hud.dirty = true;
        }

        for(int i = 0; i < Crates.count; ++i) {
            if (MathUtil.abs(Player.x - Crates.x[i]) < 20 && Crates.y[i] > Player.y && Crates.y[i] - 24 < Player.y) {
                Player.vx = 0;
                if (Player.x < Crates.x[i]) {
                    Player.x = Crates.x[i] - 20;
                } else {
                    Player.x = Crates.x[i] + 20;
                }

                Player.action = PlayerAction.SINK;
                break;
            }
        }

        WaterSplash.spawn(Player.x, Player.y);
        Dust.spawn(Player.facing, Player.x - (Player.facing << 3), Player.y);
    }
}
