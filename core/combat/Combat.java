package core.combat;

import core.app.GameState;
import core.fx.Aura;
import core.fx.Blood;
import core.fx.FloatingText;
import core.item.Inventory;
import core.item.MoneyDrops;
import core.item.PotionDrops;
import core.map.Crates;
import core.map.GameMap;
import core.map.TileFlags;
import core.monster.BoarBoss;
import core.monster.FinalBoss;
import core.monster.MonsterStats;
import core.monster.Monsters;
import core.monster.NinjaStats;
import core.monster.Ninjas;
import core.monster.RiverMonster;
import core.npc.Classmates;
import core.npc.Girl;
import core.npc.Toniku;
import core.player.Darts;
import core.player.Leveling;
import core.player.Player;
import core.player.PlayerAction;
import core.player.PlayerStats;
import core.quest.Quest;
import core.quest.QuestItem;
import core.quest.QuestState;
import core.render.Camera;
import core.render.Viewport;
import core.text.GameText;
import core.ui.Hud;
import core.ui.MessageBox;
import core.ui.MessageType;
import core.ui.Notice;
import core.util.MathUtil;

/** Gây sát thương giữa nhân vật, cô bé, quái, ninja, boss; kinh nghiệm khi hạ; phi tiêu bay. */
public final class Combat {
    private Combat() {
    }

    /** Cộng kinh nghiệm; lên cấp thì hiện hộp thông báo chỉ số mới. */
    public static void gainExp(int amount) {
        if (Leveling.addExp(amount)) {
            FloatingText.spawn("level-up", 2, Player.x, Player.y - Player.height, 0, -2);
            MessageBox.show(MessageType.LEVEL_UP);
            MessageBox.setText("Chúc mừng! Bạn đạt được cấp " + PlayerStats.level + '\n' + "HP: " + PlayerStats.maxHp + '\n' + "MP: " + PlayerStats.maxMp + '\n' + "Sức đánh: " + PlayerStats.getDisplayedAttack(Inventory.swordLevel));
            GameState.current = GameState.MESSAGE;
        } else {
            FloatingText.spawn("+" + amount, 2, Player.x, Player.y - Player.height, 0, -2);
        }

        Hud.dirty = true;
    }

    /** Nhát chém của nhân vật trúng quái, ninja, boss hoặc Toniku ở phía trước. */
    public static void playerSwordHit() {
        if (QuestState.current == Quest.AIR_HITS && Player.vy != 0) {
            ++QuestState.progress;
            // LỖI GỐC (giữ nguyên): so mã nhiệm vụ (11) thay vì QuestState.progress nên
            // thông báo "Thành công 20 hit!" không bao giờ hiện; luôn hiện "Hit: n".
            if (QuestState.current >= 20) {
                Notice.show("Thành công 20 hit!");
            } else {
                Notice.show("Hit: " + QuestState.progress);
            }
        }

        int damage = PlayerStats.rollMeleeDamage(GameState.tick, Inventory.swordLevel);
        if (Player.transformed) {
            damage <<= 1;
        }

        if (Player.weapon == 1) {
            Darts.spawn(Player.x + (Player.facing << 3), Player.y - 12, (Player.facing << 3) + Player.vx, Player.vy << 1, false);
        } else {
            if (Player.vy > 0 && Player.vx == 0) {
                Player.vy = -2;
            }

            int i = findLaunchTarget();
            Player.launchHit = false;
            if (i != -1) {
                Player.launchHit = true;
                damageMonster(i, damage, -Player.facing);
                Blood.spawn(Monsters.x[i], Monsters.y[i] - (Monsters.height[i] >> 1), Player.facing);
                FloatingText.spawn("-" + damage, 0, Monsters.x[i], Monsters.y[i] - Monsters.height[i], 0, -2);
                if (Player.vy > 0) {
                    Player.vy = -6;
                }

                Camera.shakeIfTransformed();
            }

            for(i = 0; i < Crates.count; ++i) {
                if (MathUtil.abs(Player.y - Player.halfHeight - Crates.y[i]) < 20 && MathUtil.abs(Player.x + (Player.facing << 4) - Crates.x[i]) < 24) {
                    Crates.state[i] = Crates.STATE_SLIDING;
                    Crates.vx[i] = Player.facing << 3;
                    Blood.spawn(Crates.x[i], Crates.y[i] - 12, Player.facing);
                    Camera.shakeIfTransformed();
                    break;
                }
            }

            if (BoarBoss.active && MathUtil.abs(Player.y - BoarBoss.y) < 30 && MathUtil.abs(Player.x + (Player.facing << 4) - BoarBoss.x) < 40) {
                if (!damageBoarBoss(damage, -Player.facing)) {
                    Blood.spawn(Player.x + (BoarBoss.x - Player.x >> 1), Player.y - 12, Player.facing);
                    FloatingText.spawn("-" + damage, 0, BoarBoss.x, BoarBoss.y - BoarBoss.height, 0, -2);
                }

                Camera.shakeIfTransformed();
            }

            for(i = 0; i < Ninjas.count; ++i) {
                if (MathUtil.abs(Player.y - Ninjas.y[i]) <= 12 && MathUtil.abs(Player.x + (Player.facing << 4) - Ninjas.x[i]) < 24) {
                    damageNinja(i, damage, -Player.facing);
                    Blood.spawn(Ninjas.x[i], Ninjas.y[i] - 15, Player.facing);
                    FloatingText.spawn("-" + damage, 0, Ninjas.x[i], Ninjas.y[i] - 28, 0, -2);
                    Camera.shakeIfTransformed();
                    break;
                }
            }

            if (RiverMonster.active && MathUtil.abs(Player.y - (RiverMonster.y - 40)) < 30 && MathUtil.abs(Player.x + (Player.facing << 4) - RiverMonster.x) < 40) {
                if ((RiverMonster.hp -= damage) <= 0) {
                    RiverMonster.hp = 0;
                    RiverMonster.timer = 0;
                    RiverMonster.healthBarTimer = 0;
                    RiverMonster.state = RiverMonster.STATE_SINKING;
                    MoneyDrops.spawn(RiverMonster.x, RiverMonster.y, 4, -10, 600);
                    MoneyDrops.spawn(RiverMonster.x, RiverMonster.y, -4, -17, 600);
                    MoneyDrops.spawn(RiverMonster.x, RiverMonster.y, -3, -18, 600);
                    MoneyDrops.spawn(RiverMonster.x, RiverMonster.y, 4, -19, 600);
                    MoneyDrops.spawn(RiverMonster.x, RiverMonster.y, 0, -10, 600);
                } else {
                    RiverMonster.healthBarTimer = 50;
                }

                Blood.spawn(Player.x + (RiverMonster.x - Player.x >> 1), Player.y - 12, Player.facing);
                FloatingText.spawn("-" + damage, 0, RiverMonster.x, RiverMonster.y - RiverMonster.height, 0, -2);
                Camera.shakeIfTransformed();
            }

            if (Toniku.active) {
                if (MathUtil.abs(Player.y - Classmates.y[0]) < 5 && MathUtil.abs(Player.x + (Player.facing << 4) - Classmates.x[0]) < 30) {
                    damageToniku(damage, Player.facing);
                }

                Camera.shakeIfTransformed();
            }

            if (FinalBoss.active) {
                if (MathUtil.abs(Player.y - (FinalBoss.y - 30)) < 30 && MathUtil.abs(Player.x + (Player.facing << 4) - FinalBoss.x) < 30) {
                    if ((FinalBoss.hp -= damage) <= 0) {
                        FinalBoss.hp = 0;
                        FinalBoss.timer = 0;
                        FinalBoss.healthBarTimer = 0;
                        FinalBoss.state = FinalBoss.STATE_DYING;
                        MoneyDrops.spawn(FinalBoss.x, FinalBoss.y, 4, -10, 500);
                        MoneyDrops.spawn(FinalBoss.x, FinalBoss.y, -4, -17, 500);
                        MoneyDrops.spawn(FinalBoss.x, FinalBoss.y, -3, -18, 500);
                        MoneyDrops.spawn(FinalBoss.x, FinalBoss.y, 4, -19, 500);
                        MoneyDrops.spawn(FinalBoss.x, FinalBoss.y, 0, -10, 500);
                    } else {
                        FinalBoss.healthBarTimer = 50;
                        Blood.spawn(FinalBoss.x, FinalBoss.y - 47, Player.facing);
                        FloatingText.spawn("-" + damage, 0, FinalBoss.x, FinalBoss.y - 55, 0, -2);
                    }
                }

                Camera.shakeIfTransformed();
            }

        }
    }

    /** Quái đứng sát phía trước có thể bị chém hất lên, -1 nếu không có. */
    public static int findLaunchTarget() {
        int left = Player.facing > 0 ? Player.x - 20 : Player.x - 40;
        int right = Player.facing > 0 ? Player.x + 40 : Player.x + 20;
        int top = Player.y - Player.height - 15;
        int bottom = Player.y + 5;

        for(int i = 0; i < Monsters.count; ++i) {
            if (Monsters.state[i] != Monsters.STATE_RESPAWN_WAIT && Monsters.x[i] >= left && Monsters.x[i] <= right && Monsters.y[i] >= top && Monsters.y[i] <= bottom) {
                return i;
            }
        }

        return -1;
    }

    /** Nhân vật bị trúng đòn từ hướng {@code direction}: trừ máu, bật lùi, chết nếu hết máu. */
    public static void damagePlayer(int amount, int direction) {
        boolean dead = PlayerStats.takeDamage(amount);
        Player.slashEffect = -1;
        Hud.dirty = true;
        Player.crate = -1;
        if (dead) {
            Player.vy = -7;
            Player.vx = direction << 2;
            Player.action = PlayerAction.DEAD;
            Player.motorbike = -1;
        } else if (Player.motorbike == -1 && Player.action != PlayerAction.TRANSFORM) {
            Player.vx = direction << 2;
            Player.vy = -2;
            Player.actionTimer = 0;
            Player.actionTimer2 = 0;
            Player.action = PlayerAction.HURT;
        }

        Blood.spawn(Player.x, Player.y - 12, -Player.facing);
        FloatingText.spawn("-" + amount, 0, Player.x, Player.y - Player.height, 0, -2);
    }

    /** Cô bé bị trúng đòn. */
    public static void damageGirl(int amount, int direction) {
        Girl.slashEffect = -1;
        Girl.vx = direction << 2;
        Girl.vy = -2;
        Girl.actionTimer = 0;
        Girl.actionTimer2 = 0;
        Girl.action = PlayerAction.HURT;
        Blood.spawn(Girl.x, Girl.y - 12, -Girl.facing);
        FloatingText.spawn("-" + amount, 0, Girl.x, Girl.y - Girl.height, 0, -2);
    }

    /** Ninja thứ {@code i} bị trúng đòn; hạ thì rơi tiền, thuốc và cộng kinh nghiệm. */
    public static boolean damageNinja(int i, int amount, int direction) {
        Ninjas.hp[i] -= amount;
        if (Ninjas.state[i] != Ninjas.STATE_KNOCKED_OUT && Ninjas.state[i] != Ninjas.STATE_DEAD) {
            if (Ninjas.hp[i] <= 0) {
                Ninjas.hp[i] = 0;
                Ninjas.direction[i] = direction;
                Ninjas.vx[i] = 0;
                Ninjas.vy[i] = 0;
                Ninjas.bodyVx[i] = -(direction << 3);
                Ninjas.bodyVy[i] = -6;
                Ninjas.timer[i] = 0;
                Ninjas.healthBarTimer[i] = 0;
                Ninjas.state[i] = Ninjas.STATE_KNOCKED_OUT;
                gainExp(NinjaStats.exp[Ninjas.type[i]]);
                MoneyDrops.spawn(Ninjas.x[i], Ninjas.y[i], -(Ninjas.direction[i] << 2), -4, NinjaStats.money[Ninjas.type[i]] + GameState.tick % 10);
                if (GameState.tick % 20 > 5) {
                    PotionDrops.spawn(Ninjas.x[i], Ninjas.y[i], -(Ninjas.direction[i] << 3), -5, NinjaStats.damage[Ninjas.type[i]] << 2, (GameState.tick + 1) % 2);
                }

                return true;
            } else {
                Ninjas.timer[i] = 0;
                Ninjas.direction[i] = direction;
                Ninjas.healthBarTimer[i] = 100;
                Ninjas.state[i] = Ninjas.STATE_HURT;
                return false;
            }
        } else {
            return true;
        }
    }

    /** Boss lợn rừng bị trúng đòn; hạ thì rơi quyển bí kíp. */
    public static boolean damageBoarBoss(int amount, int direction) {
        if (BoarBoss.state == BoarBoss.STATE_IDLE || BoarBoss.state == BoarBoss.STATE_WALK) {
            if ((BoarBoss.hp -= amount) <= 0) {
                BoarBoss.hp = 0;
                BoarBoss.timer = -5;
                BoarBoss.vx = -(direction << 3);
                BoarBoss.hurtFlash = 0;
                BoarBoss.state = BoarBoss.STATE_KNOCKED_OUT;
                MoneyDrops.spawn(BoarBoss.x, BoarBoss.y, 4, -6, 50);
                MoneyDrops.spawn(BoarBoss.x, BoarBoss.y, -5, -7, 50);
                MoneyDrops.spawn(BoarBoss.x, BoarBoss.y, -4, -8, 50);
                MoneyDrops.spawn(BoarBoss.x, BoarBoss.y, 5, -9, 50);
                MoneyDrops.spawn(BoarBoss.x, BoarBoss.y, 0, -10, 50);
                if (QuestState.current == Quest.KILL_WILD_BOAR) {
                    QuestItem.place(BoarBoss.x, BoarBoss.y);
                }

                return true;
            }

            BoarBoss.timer = 0;
            BoarBoss.direction = direction;
            BoarBoss.state = BoarBoss.STATE_HURT;
            BoarBoss.hurtFlash = 10;
            BoarBoss.frame = 0;
            if (amount <= PlayerStats.dartDamage) {
                BoarBoss.targetX = 0;
            } else {
                BoarBoss.targetX = 2 + GameState.tick % 5;
            }
        }

        return false;
    }

    /** Toniku bị trúng đòn. */
    private static void damageToniku(int amount, int direction) {
        Toniku.hp -= amount;
        Classmates.slashEffect[0] = -1;
        if (Toniku.hp <= 0) {
            Toniku.hp = 0;
        } else {
            Classmates.vx[0] = direction << 2;
            Classmates.vy[0] = -2;
            Classmates.animTimer[0] = 0;
            Classmates.target[0] = 0;
            Classmates.state[0] = Classmates.STATE_KNOCKBACK;
            Blood.spawn(Classmates.x[0], Classmates.y[0] - 12, Player.facing);
            FloatingText.spawn("-" + amount, 0, Classmates.x[0], Classmates.y[0] - 32, 0, -2);
        }
    }

    /** Phi tiêu đang bay: cắm vào tường, trúng quái / ninja / boss. */
    public static void updateDarts() {
        int i;
        int j;
        int damage;
        for(i = 0; i < Darts.count; ++i) {
            if (Darts.state[i] == Darts.STATE_FLYING) {
                Darts.x[i] += Darts.vx[i];
                Darts.y[i] += Darts.vy[i];
                if (Darts.x[i] >= Camera.x && Darts.x[i] <= Camera.x + Viewport.viewWidth && Darts.y[i] >= Camera.y && Darts.y[i] <= Camera.y + Viewport.viewHeight) {
                    if (Darts.vx[i] > 0 && (GameMap.flagsAtPixel(Darts.x[i], Darts.y[i]) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                        Darts.state[i] = Darts.STATE_STUCK;
                        Darts.x[i] = GameMap.alignToTileX(Darts.x[i]);
                    } else if (Darts.vx[i] < 0 && (GameMap.flagsAtPixel(Darts.x[i], Darts.y[i]) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                        Darts.state[i] = Darts.STATE_STUCK;
                        Darts.x[i] = GameMap.alignToTileX(Darts.x[i]) + GameMap.TILE_SIZE;
                    }

                    Darts.frame[i]++;
                    if (Darts.frame[i] > 1) {
                        Darts.frame[i] = 0;
                    }

                    if (Darts.vx[i] != 0) {
                        for(j = 0; j < Monsters.count; ++j) {
                            if (Monsters.state[j] != Monsters.STATE_RESPAWN_WAIT && Monsters.state[j] != Monsters.STATE_KNOCKED_OUT && MathUtil.abs(Monsters.x[j] - Darts.x[i]) < Monsters.width[j] && MathUtil.abs(Monsters.y[j] - (Monsters.height[j] >> 1) - Darts.y[i]) < Monsters.height[j]) {
                                // true: phi tiêu bị ninja gió đỡ bật lại hoặc quái chết, không hiện máu và số sát thương.
                                boolean skipHitEffect;
                                applyDartHit: {
                                    damage = Darts.powered[i] ? PlayerStats.dartDamage << 1 : PlayerStats.dartDamage;
                                    if (Monsters.state[j] == Monsters.STATE_ACTIVE || Monsters.state[j] == Monsters.STATE_RESTING || Monsters.state[j] == Monsters.STATE_ATTACKING) {
                                        if (Monsters.type[j] == Monsters.TYPE_WIND_NINJA && Darts.vx[i] * Monsters.direction[j] < 0) {
                                            Monsters.state[j] = Monsters.STATE_ATTACKING;
                                            Monsters.timer[j] = 4;
                                            Darts.vx[i] = -Darts.vx[i];
                                            Darts.vy[i] = -Darts.vy[i];
                                            Darts.x[i] += Darts.vx[i];
                                            Darts.y[i] += Darts.vy[i];
                                            skipHitEffect = true;
                                            break applyDartHit;
                                        }

                                        Monsters.hp[j] -= damage;
                                        if (Monsters.hp[j] <= 0) {
                                            Monsters.hp[j] = 0;
                                            Monsters.timer[j] = -5;
                                            Monsters.aux1[j] = Darts.vx[i];
                                            Monsters.aux2[j] = 0;
                                            Monsters.healthBarTimer[j] = 0;
                                            Monsters.state[j] = Monsters.STATE_KNOCKED_OUT;
                                            onMonsterKilled(j);
                                            skipHitEffect = true;
                                            break applyDartHit;
                                        }

                                        Monsters.timer[j] = 0;
                                        Monsters.direction[j] = Darts.vx[i] < 0 ? 1 : -1;
                                        Monsters.state[j] = Monsters.STATE_HURT;
                                        Monsters.frame[j] = 3;
                                        Darts.state[i] = Darts.STATE_FREE;
                                    }

                                    skipHitEffect = false;
                                }

                                if (!skipHitEffect) {
                                    Blood.spawn(Monsters.x[j], Monsters.y[j] - (Monsters.height[j] >> 1), Monsters.direction[j]);
                                    FloatingText.spawn("-" + damage, 0, Monsters.x[j], Monsters.y[j] - Monsters.height[j], 0, -2);
                                }
                                break;
                            }
                        }

                        for(j = 0; j < Ninjas.count; ++j) {
                            if (Ninjas.state[j] != Ninjas.STATE_DEAD && Ninjas.state[j] != Ninjas.STATE_KNOCKED_OUT && MathUtil.abs(Ninjas.x[j] - Darts.x[i]) < 20 && MathUtil.abs(Ninjas.y[j] - 15 - Darts.y[i]) < 20) {
                                if (Darts.powered[i]) {
                                    damageNinja(j, PlayerStats.dartDamage << 1, Ninjas.direction[i]);
                                    Blood.spawn(Ninjas.x[j], Ninjas.y[j] - 15, Ninjas.direction[j]);
                                    FloatingText.spawn("-" + (PlayerStats.dartDamage << 1), 0, Ninjas.x[j], Ninjas.y[j] - 32, 0, -2);
                                    Darts.state[i] = Darts.STATE_FREE;
                                } else {
                                    Ninjas.state[j] = Ninjas.STATE_ATTACK;
                                    Ninjas.timer[j] = 0;
                                    Ninjas.targetX[j] = 0;
                                    Ninjas.direction[j] = Darts.vx[i] > 0 ? -1 : 1;
                                    Darts.vx[i] = -Darts.vx[i];
                                }
                                break;
                            }
                        }

                        if (!Darts.powered[i] && MathUtil.abs(Player.x - Darts.x[i]) < Player.halfWidth && MathUtil.abs(Player.y - Player.halfHeight - Darts.y[i]) < Player.halfHeight) {
                            if (Player.action == PlayerAction.ATTACK && Player.weapon == 0) {
                                Darts.vx[i] = -Darts.vx[i];
                                Darts.vy[i] = -3;
                            } else {
                                damagePlayer(PlayerStats.dartDamage, -Player.facing);
                                Darts.state[i] = Darts.STATE_FREE;
                            }
                            break;
                        }

                        if (BoarBoss.active && MathUtil.abs(BoarBoss.x - Darts.x[i]) < BoarBoss.width >> 1 && MathUtil.abs(BoarBoss.y - (BoarBoss.height >> 1) - Darts.y[i]) < BoarBoss.height >> 1) {
                            damageBoarBoss(PlayerStats.dartDamage, -BoarBoss.direction);
                            Blood.spawn(Darts.x[i], Darts.y[i], BoarBoss.direction);
                            FloatingText.spawn("-" + PlayerStats.dartDamage, 0, BoarBoss.x, BoarBoss.y - BoarBoss.height, 0, -2);
                            Darts.state[i] = Darts.STATE_FREE;
                        }

                        if (RiverMonster.active && MathUtil.abs(RiverMonster.x - Darts.x[i]) < RiverMonster.width >> 1 && MathUtil.abs(RiverMonster.y - (RiverMonster.height >> 1) - Darts.y[i]) < RiverMonster.height >> 1) {
                            Darts.vx[i] = -Darts.vx[i];
                            Darts.vy[i] = -1;
                        }

                        if (Toniku.active && MathUtil.abs(Classmates.x[0] - Darts.x[i]) < 12 && MathUtil.abs(Classmates.y[0] - 16 - Darts.y[i]) < 32) {
                            if (Classmates.state[0] == Classmates.STATE_ATTACK && Darts.vx[i] * Classmates.facing[0] < 0) {
                                Darts.vx[i] = -Darts.vx[i];
                            } else if (Classmates.state[0] == Classmates.STATE_IDLE && Darts.vx[i] * Classmates.facing[0] < 0) {
                                Darts.vx[i] = -Darts.vx[i];
                                Classmates.animTimer[0] = Classmates.target[0] = 0;
                                Classmates.state[0] = Classmates.STATE_ATTACK;
                            } else {
                                damageToniku(PlayerStats.dartDamage, -Classmates.facing[0]);
                                Darts.state[i] = Darts.STATE_FREE;
                            }
                        }
                    }
                } else {
                    Darts.state[i] = Darts.STATE_FREE;
                }
            } else if (Darts.state[i] > Darts.STATE_FLYING) {
                Darts.state[i]++;
                if (Darts.state[i] >= 10) {
                    Darts.state[i] = Darts.STATE_FLYING;
                    Darts.vy[i] = 12;
                    Darts.vx[i] = 0;
                }
            }
        }
    }

    /** Quái thứ {@code i} bị trúng đòn; hạ thì gọi {@link #onMonsterKilled}. */
    public static boolean damageMonster(int i, int amount, int direction) {
        if (Monsters.state[i] == Monsters.STATE_ACTIVE || Monsters.state[i] == Monsters.STATE_RESTING || Monsters.state[i] == Monsters.STATE_ATTACKING || Monsters.state[i] == Monsters.STATE_LAUNCHED) {
            Monsters.hp[i] -= amount;
            if (Monsters.hp[i] <= 0) {
                Monsters.hp[i] = 0;
                Monsters.timer[i] = -5;
                Monsters.aux1[i] = -(direction << 3);
                Monsters.aux2[i] = 0;
                Monsters.healthBarTimer[i] = 0;
                Monsters.state[i] = Monsters.STATE_KNOCKED_OUT;
                onMonsterKilled(i);
                return true;
            }

            if (Monsters.state[i] == Monsters.STATE_LAUNCHED) {
                Monsters.timer[i] -= 7;
                Monsters.direction[i] = direction;
            } else {
                Monsters.timer[i] = 0;
                Monsters.direction[i] = direction;
                Monsters.state[i] = Monsters.STATE_HURT;
                Monsters.frame[i] = 3;
            }
        }

        return false;
    }

    /** Quái bị hạ: cộng kinh nghiệm, rơi tiền / thuốc, tính tiến độ nhiệm vụ, có thể rơi vật phẩm nhiệm vụ. */
    private static void onMonsterKilled(int i) {
        gainExp(MonsterStats.exp[Monsters.type[i]]);
        int roll;
        if ((roll = GameState.tick % 20) > 15) {
            PotionDrops.spawn(Monsters.x[i], Monsters.y[i], -(Monsters.direction[i] << 3), -5, Monsters.damage[i] << 2, (GameState.tick + 1) % 2);
        } else if (roll == 15) {
            MoneyDrops.spawn(Monsters.x[i], Monsters.y[i], -(Monsters.direction[i] << 2), -4, (Monsters.money[i] << 3) + GameState.tick % 10);
        } else {
            int amount = ((Monsters.type[i] << 2) / 10 + 1) * 10;
            MoneyDrops.spawn(Monsters.x[i], Monsters.y[i], -(Monsters.direction[i] << 1), -6, amount);
            MoneyDrops.spawn(Monsters.x[i], Monsters.y[i], -(Monsters.direction[i] << 4), -3, amount);
        }

        if (QuestState.current != Quest.NONE && Quest.TARGET_MONSTER[QuestState.current] == Monsters.type[i]) {
            ++QuestState.progress;
            Notice.show(GameText.monsterNames[Quest.TARGET_MONSTER[QuestState.current]] + ": " + QuestState.progress + "#" + Quest.TARGET_COUNT[QuestState.current]);
        }

        if (Monsters.type[i] == QuestState.dropMonster - QuestState.DROP_MONSTER_BASE && GameState.tick % 10 < QuestState.dropChance) {
            QuestItem.place(Monsters.x[i], Monsters.y[i]);
            Aura.show(Monsters.x[i], Monsters.y[i] - 28);
        }

    }
}
