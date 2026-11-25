package core.player;

import core.app.GameState;
import core.fx.Dust;
import core.fx.WaterSplash;
import core.input.Input;
import core.item.Inventory;
import core.map.GameMap;
import core.npc.Classmates;
import core.npc.Girl;
import core.npc.Npcs;
import core.quest.Quest;
import core.quest.QuestAction;
import core.quest.QuestState;
import core.quest.Skills;
import core.quest.Story;
import core.render.TileRenderer;
import core.text.DialogueText;
import core.ui.Dialogue;
import core.ui.GameMenu;
import core.ui.GuideMarker;
import core.ui.Hud;
import core.ui.ShopScreen;
import core.util.MathUtil;

/** Xử lý phím khi đang chơi: di chuyển, tấn công, nói chuyện, dùng kỹ năng. */
public final class PlayerInput {
    private PlayerInput() {
    }

    /** Xử lý phím khi đang chơi và không có hộp thoại. */
    public static void update() {
        int i;
        int j;
        if (Input.pressed[Input.KEY_SOFT_LEFT]) {
            TileRenderer.tilesetImage = null;
            GameMenu.open();
            GameState.current = GameState.GAME_MENU;
            Input.clearHeld();
            Input.clearPressed();
        } else if (Input.pressed[Input.KEY_NUM0]) {
            if (Player.weapon == 0) {
                if (Skills.darts) {
                    Player.weapon = 1;
                }
            } else {
                Player.weapon = 0;
            }

            Hud.dirty = true;
            Input.pressed[Input.KEY_NUM0] = false;
        } else if (Input.pressed[Input.KEY_RIGHT]) {
            Player.sprintBonus = 2;
            Input.pressed[Input.KEY_RIGHT] = false;
        } else if (Input.pressed[Input.KEY_LEFT]) {
            Player.sprintBonus = -2;
            Input.pressed[Input.KEY_LEFT] = false;
        } else {
            if (Input.pressed[Input.KEY_NUM9]) {
                if (Skills.hide) {
                    Player.hidden = !Player.hidden;
                }

                Input.pressed[Input.KEY_NUM9] = false;
                return;
            }

            if (Input.pressed[Input.KEY_NUM7]) {
                if (Player.action == PlayerAction.STAND && Skills.transform) {
                    Player.action = PlayerAction.TRANSFORM;
                    Player.actionTimer = 0;
                }

                Input.pressed[Input.KEY_NUM7] = false;
            } else if (Input.pressed[Input.KEY_STAR]) {
                Input.pressed[Input.KEY_STAR] = false;
                if (!PlayerStats.isHpFull()) {
                    for(i = 0; i < 3; ++i) {
                        if (Inventory.potionCounts[i] > 0) {
                            Inventory.potionCounts[i]--;
                            PlayerStats.restoreHp(Inventory.potionRestoreAmounts[i]);

                            Hud.dirty = true;
                            break;
                        }
                    }
                }
            } else if (Input.pressed[Input.KEY_POUND]) {
                Input.pressed[Input.KEY_POUND] = false;
                if (!PlayerStats.isMpFull()) {
                    for(i = 3; i < 6; ++i) {
                        if (Inventory.potionCounts[i] > 0) {
                            Inventory.potionCounts[i]--;
                            PlayerStats.restoreMp(Inventory.potionRestoreAmounts[i]);

                            Hud.dirty = true;
                            break;
                        }
                    }
                }
            }
        }

        if (Player.action == PlayerAction.STAND) {
            if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
                Input.clearPressed();
                if (Hud.rightSoftKeyLabel.equals("Giao tiếp")) {
                    j = Player.nearNpc;
                    switch (Npcs.types[j]) {
                        case Npcs.TYPE_PRINCIPAL:
                            if (Story.sakuraEscorting) {
                                Dialogue.open(DialogueText.idleLines[7], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            } else if (!Skills.lightness) {
                                GuideMarker.setNextNpc(Npcs.TYPE_TAKANASHI);
                                Dialogue.open(DialogueText.questGiveLines[0], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            } else {
                                if (Skills.hide && !Story.gameCompleted) {
                                    if (QuestState.current == Quest.RESCUE_SAKURA) {
                                        if (Story.sakuraFound) {
                                            Dialogue.open(DialogueText.questRewardLines[14], QuestAction.COMPLETE_RESCUE_SAKURA, Npcs.x[j], Npcs.y[j] - 40);
                                        } else {
                                            Dialogue.open(DialogueText.questReminderLines[14], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                        }
                                    } else if (QuestState.current == Quest.NONE) {
                                        Dialogue.open(DialogueText.questGiveLines[22], QuestAction.START_RESCUE_SAKURA, Npcs.x[j], Npcs.y[j] - 40);
                                    } else {
                                        Dialogue.open(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, Npcs.x[j], Npcs.y[j] - 40);
                                    }
                                    break;
                                }

                                Dialogue.open(DialogueText.idleLines[1], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            }
                            break;
                        case Npcs.TYPE_AYUMI:
                            if (Story.sakuraEscorting) {
                                Dialogue.open(DialogueText.idleLines[5], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            } else if (!Skills.lightness) {
                                if (QuestState.current == Quest.KILL_SNAILS) {
                                    if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_SNAILS]) {
                                        Dialogue.open(DialogueText.questReminderLines[0], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                    } else {
                                        GuideMarker.pendingMap = 0;
                                        GuideMarker.pendingNpc = 4;
                                        GuideMarker.pendingX = 1044;
                                        GuideMarker.pendingY = 240;
                                        Dialogue.open(DialogueText.questRewardLines[0], QuestAction.REWARD_LIGHTNESS, Npcs.x[j], Npcs.y[j] - 40);
                                    }
                                } else if (QuestState.current == Quest.NONE) {
                                    Dialogue.open(DialogueText.questGiveLines[1], QuestAction.START_KILL_SNAILS, Npcs.x[j], Npcs.y[j] - 40);
                                } else {
                                    Dialogue.open(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, Npcs.x[j], Npcs.y[j] - 40);
                                }
                            } else if (!Story.goldenBatQuestDone && Skills.transform) {
                                if (PlayerStats.level < 10) {
                                    Dialogue.open(DialogueText.levelRequired10, Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                } else if (QuestState.current == Quest.KILL_GOLDEN_BATS) {
                                    if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_GOLDEN_BATS]) {
                                        Dialogue.open(DialogueText.questReminderLines[6], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                    } else {
                                        GuideMarker.setNextNpc(Npcs.TYPE_TAKANASHI);
                                        Dialogue.open(DialogueText.questRewardLines[6], QuestAction.REWARD_MONEY, Npcs.x[j], Npcs.y[j] - 40);
                                    }
                                } else if (QuestState.current == Quest.NONE) {
                                    Dialogue.open(DialogueText.questGiveLines[14], QuestAction.START_KILL_GOLDEN_BATS, Npcs.x[j], Npcs.y[j] - 40);
                                } else {
                                    Dialogue.open(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, Npcs.x[j], Npcs.y[j] - 40);
                                }
                            } else if (Story.girlOutingDone && !Skills.tripleJump) {
                                if (PlayerStats.level < 12) {
                                    Dialogue.open(DialogueText.levelRequired12, Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                } else if (QuestState.current == Quest.AIR_HITS) {
                                    if (QuestState.progress < Quest.TARGET_COUNT[Quest.AIR_HITS]) {
                                        Dialogue.open(DialogueText.questReminderLines[9], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                    } else {
                                        GuideMarker.setNextNpc(Npcs.TYPE_SHURIKEN_TEACHER);
                                        Dialogue.open(DialogueText.questRewardLines[9], QuestAction.REWARD_TRIPLE_JUMP, Npcs.x[j], Npcs.y[j] - 40);
                                    }
                                } else if (QuestState.current == Quest.NONE) {
                                    Dialogue.open(DialogueText.questGiveLines[17], QuestAction.START_AIR_HITS, Npcs.x[j], Npcs.y[j] - 40);
                                } else {
                                    Dialogue.open(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, Npcs.x[j], Npcs.y[j] - 40);
                                }
                            } else {
                                if (!Skills.sprint && Skills.dartsLevel2) {
                                    if (PlayerStats.level < 15) {
                                        Dialogue.open(DialogueText.levelRequired15, Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                    } else if (QuestState.current == Quest.KILL_SCORPIONS) {
                                        if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_SCORPIONS]) {
                                            Dialogue.open(DialogueText.questReminderLines[11], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                        } else {
                                            GuideMarker.setNextNpc(Npcs.TYPE_JUTSU_TEACHER);
                                            Dialogue.open(DialogueText.questRewardLines[11], QuestAction.REWARD_SPRINT, Npcs.x[j], Npcs.y[j] - 40);
                                        }
                                    } else if (QuestState.current == Quest.NONE) {
                                        Dialogue.open(DialogueText.questGiveLines[19], QuestAction.START_KILL_SCORPIONS, Npcs.x[j], Npcs.y[j] - 40);
                                    } else {
                                        Dialogue.open(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, Npcs.x[j], Npcs.y[j] - 40);
                                    }
                                    break;
                                }

                                Dialogue.open(DialogueText.idleLines[0], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            }
                            break;
                        case Npcs.TYPE_TAKANASHI:
                            if (Story.sakuraEscorting) {
                                Dialogue.open(DialogueText.idleLines[9], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            } else if (!Skills.lightness) {
                                GuideMarker.setNextNpc(Npcs.TYPE_AYUMI);
                                Dialogue.open(DialogueText.questGiveLines[2], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            } else if (Skills.burrow && !Skills.motorbikeLicense) {
                                if (QuestState.current == Quest.ESCORT_AYUMI_SISTER) {
                                    Dialogue.open(DialogueText.questGiveLines[9], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                } else if (QuestState.current == Quest.NONE) {
                                    Dialogue.open(DialogueText.questGiveLines[10], QuestAction.START_ESCORT_AYUMI_SISTER, Npcs.x[j], Npcs.y[j] - 40);
                                } else {
                                    Dialogue.open(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, Npcs.x[j], Npcs.y[j] - 40);
                                }
                            } else if (Story.goldenBatQuestDone && !Story.tonikuQuestDone) {
                                if (QuestState.current == Quest.FIGHT_TONIKU) {
                                    if (Story.tonikuDefeated) {
                                        GuideMarker.setNextNpc(Npcs.TYPE_TAKANASHI);
                                        Dialogue.open(DialogueText.questRewardLines[7], QuestAction.COMPLETE_FIGHT_TONIKU, Npcs.x[j], Npcs.y[j] - 40);
                                    } else {
                                        Dialogue.open(DialogueText.questReminderLines[7], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                    }
                                } else if (QuestState.current == Quest.NONE) {
                                    Dialogue.open(DialogueText.questGiveLines[15], QuestAction.START_FIGHT_TONIKU, Npcs.x[j], Npcs.y[j] - 40);
                                } else {
                                    Dialogue.open(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, Npcs.x[j], Npcs.y[j] - 40);
                                }
                            } else {
                                if (Story.tonikuQuestDone && !Story.girlWaiting) {
                                    if (QuestState.current == Quest.KILL_NATIVES) {
                                        if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_NATIVES]) {
                                            Dialogue.open(DialogueText.questReminderLines[8], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                        } else {
                                            GuideMarker.setNextNpc(Npcs.TYPE_JUTSU_TEACHER);
                                            Dialogue.open(DialogueText.questRewardLines[8], QuestAction.REWARD_MEET_GIRL, Npcs.x[j], Npcs.y[j] - 40);
                                        }
                                    } else if (QuestState.current == Quest.NONE) {
                                        Dialogue.open(DialogueText.questGiveLines[16], QuestAction.START_KILL_NATIVES, Npcs.x[j], Npcs.y[j] - 40);
                                    } else {
                                        Dialogue.open(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, Npcs.x[j], Npcs.y[j] - 40);
                                    }
                                    break;
                                }

                                Dialogue.open(DialogueText.idleLines[3], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            }
                            break;
                        case Npcs.TYPE_JUTSU_TEACHER:
                            if (Story.sakuraEscorting) {
                                Dialogue.open(DialogueText.idleLines[9], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            } else {
                                if (Skills.lightness && Skills.darts) {
                                    if (!Skills.burrow) {
                                        if (QuestState.current == Quest.FIND_BURROW_SCROLL) {
                                            if (!QuestState.itemFound) {
                                                Dialogue.open(DialogueText.questReminderLines[2], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                            } else {
                                                GuideMarker.setNextNpc(Npcs.TYPE_TAKANASHI);
                                                Dialogue.open(DialogueText.questRewardLines[2], QuestAction.REWARD_BURROW, Npcs.x[j], Npcs.y[j] - 40);
                                            }
                                        } else if (QuestState.current == Quest.NONE) {
                                            Dialogue.open(DialogueText.questGiveLines[8], QuestAction.START_FIND_BURROW_SCROLL, Npcs.x[j], Npcs.y[j] - 40);
                                        } else {
                                            Dialogue.open(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, Npcs.x[j], Npcs.y[j] - 40);
                                        }
                                        break;
                                    }

                                    if (!Skills.dive && Skills.doubleStrength) {
                                        if (QuestState.current == Quest.KILL_WILD_BOAR) {
                                            if (!QuestState.itemFound) {
                                                Dialogue.open(DialogueText.questReminderLines[4], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                            } else {
                                                GuideMarker.setNextNpc(Npcs.TYPE_SHURIKEN_TEACHER);
                                                Dialogue.open(DialogueText.questRewardLines[4], QuestAction.REWARD_WATER_SKILLS, Npcs.x[j], Npcs.y[j] - 40);
                                            }
                                        } else if (QuestState.current == Quest.NONE) {
                                            Dialogue.open(DialogueText.questGiveLines[12], QuestAction.START_KILL_WILD_BOAR, Npcs.x[j], Npcs.y[j] - 40);
                                        } else {
                                            Dialogue.open(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, Npcs.x[j], Npcs.y[j] - 40);
                                        }
                                        break;
                                    }

                                    if (!Skills.standOnWater && Skills.sprint) {
                                        if (PlayerStats.level < 16) {
                                            Dialogue.open(DialogueText.levelRequired16, Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                        } else if (QuestState.current == Quest.KILL_RED_TIGER_FISH) {
                                            if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_RED_TIGER_FISH]) {
                                                Dialogue.open(DialogueText.questReminderLines[12], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                            } else {
                                                GuideMarker.setNextNpc(Npcs.TYPE_JUTSU_TEACHER);
                                                Dialogue.open(DialogueText.questRewardLines[12], QuestAction.REWARD_STAND_ON_WATER, Npcs.x[j], Npcs.y[j] - 40);
                                            }
                                        } else if (QuestState.current == Quest.NONE) {
                                            Dialogue.open(DialogueText.questGiveLines[20], QuestAction.START_KILL_RED_TIGER_FISH, Npcs.x[j], Npcs.y[j] - 40);
                                        } else {
                                            Dialogue.open(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, Npcs.x[j], Npcs.y[j] - 40);
                                        }
                                        break;
                                    }

                                    if (!Skills.hide && Skills.standOnWater) {
                                        if (QuestState.current == Quest.FIND_HIDE_SCROLL) {
                                            if (!QuestState.itemFound) {
                                                Dialogue.open(DialogueText.questReminderLines[13], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                            } else {
                                                GuideMarker.setNextNpc(Npcs.TYPE_PRINCIPAL);
                                                Dialogue.open(DialogueText.questRewardLines[13], QuestAction.REWARD_HIDE, Npcs.x[j], Npcs.y[j] - 40);
                                            }
                                        } else if (QuestState.current == Quest.NONE) {
                                            Dialogue.open(DialogueText.questGiveLines[21], QuestAction.START_FIND_HIDE_SCROLL, Npcs.x[j], Npcs.y[j] - 40);
                                        } else {
                                            Dialogue.open(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, Npcs.x[j], Npcs.y[j] - 40);
                                        }
                                        break;
                                    }

                                    Dialogue.open(DialogueText.idleLines[4], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                    break;
                                }

                                Dialogue.open(DialogueText.questGiveLines[5], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            }
                            break;
                        case Npcs.TYPE_SHURIKEN_TEACHER:
                            if (Story.sakuraEscorting) {
                                Dialogue.open(DialogueText.idleLines[8], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            } else if (GameMap.id == 99) {
                                Dialogue.open(DialogueText.questGiveLines[7], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            } else if (!Skills.lightness) {
                                Dialogue.open(DialogueText.questGiveLines[4], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            } else if (!Skills.darts) {
                                if (QuestState.current == Quest.KNOCK_DOWN_SCARECROWS) {
                                    if (QuestState.progress < Quest.TARGET_COUNT[Quest.KNOCK_DOWN_SCARECROWS]) {
                                        Dialogue.open(DialogueText.questReminderLines[3], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                    } else {
                                        GuideMarker.setNextNpc(Npcs.TYPE_JUTSU_TEACHER);
                                        Dialogue.open(DialogueText.questRewardLines[1], QuestAction.REWARD_DARTS, Npcs.x[j], Npcs.y[j] - 40);
                                    }
                                } else if (QuestState.current == Quest.NONE) {
                                    Dialogue.open(DialogueText.questGiveLines[6], QuestAction.START_KNOCK_DOWN_SCARECROWS, Npcs.x[j], Npcs.y[j] - 40);
                                } else {
                                    Dialogue.open(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, Npcs.x[j], Npcs.y[j] - 40);
                                }
                            } else if (Skills.motorbikeLicense && !Skills.doubleStrength) {
                                if (QuestState.current == Quest.KILL_BEES) {
                                    if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_BEES]) {
                                        Dialogue.open(DialogueText.questReminderLines[3], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                    } else {
                                        GuideMarker.setNextNpc(Npcs.TYPE_JUTSU_TEACHER);
                                        Dialogue.open(DialogueText.questRewardLines[3], QuestAction.REWARD_DOUBLE_STRENGTH, Npcs.x[j], Npcs.y[j] - 40);
                                    }
                                } else if (QuestState.current == Quest.NONE) {
                                    Dialogue.open(DialogueText.questGiveLines[11], QuestAction.START_KILL_BEES, Npcs.x[j], Npcs.y[j] - 40);
                                } else {
                                    Dialogue.open(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, Npcs.x[j], Npcs.y[j] - 40);
                                }
                            } else if (Skills.dive && !Skills.transform) {
                                if (PlayerStats.level < 8) {
                                    Dialogue.open(DialogueText.levelRequired8, Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                } else if (QuestState.current == Quest.CROSS_RIVER) {
                                    if (!QuestState.itemFound) {
                                        Dialogue.open(DialogueText.questReminderLines[5], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                    } else {
                                        GuideMarker.setNextNpc(Npcs.TYPE_AYUMI);
                                        Dialogue.open(DialogueText.questRewardLines[5], QuestAction.REWARD_TRANSFORM, Npcs.x[j], Npcs.y[j] - 40);
                                    }
                                } else if (QuestState.current == Quest.NONE) {
                                    Dialogue.open(DialogueText.questGiveLines[13], QuestAction.START_CROSS_RIVER, Npcs.x[j], Npcs.y[j] - 40);
                                } else {
                                    Dialogue.open(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, Npcs.x[j], Npcs.y[j] - 40);
                                }
                            } else {
                                if (!Skills.dartsLevel2 && Skills.tripleJump) {
                                    if (QuestState.current == Quest.KILL_FLOWER_DEMONS) {
                                        if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_FLOWER_DEMONS]) {
                                            Dialogue.open(DialogueText.questReminderLines[10], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                        } else {
                                            GuideMarker.setNextNpc(Npcs.TYPE_AYUMI);
                                            Dialogue.open(DialogueText.questRewardLines[10], QuestAction.REWARD_DARTS_LEVEL_2, Npcs.x[j], Npcs.y[j] - 40);
                                        }
                                    } else if (QuestState.current == Quest.NONE) {
                                        Dialogue.open(DialogueText.questGiveLines[18], QuestAction.START_KILL_FLOWER_DEMONS, Npcs.x[j], Npcs.y[j] - 40);
                                    } else {
                                        Dialogue.open(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, Npcs.x[j], Npcs.y[j] - 40);
                                    }
                                    break;
                                }

                                Dialogue.open(DialogueText.idleLines[2], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            }
                            break;
                        case Npcs.TYPE_GUARD:
                            if (GameMap.id == 0) {
                                if (j == 5) {
                                    Dialogue.open(DialogueText.guardLines[0], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                                }

                                if (j == 6) {
                                    ShopScreen.enter(ShopScreen.MODE_MONEY);
                                }
                            }

                            if (GameMap.id == 2 && j == 0) {
                                ShopScreen.enter(ShopScreen.MODE_SMS);
                            }

                            if (GameMap.id == 1 && j == 0) {
                                Dialogue.open(DialogueText.guardLines[1], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            }

                            if (GameMap.id == 5 && j == 0) {
                                Dialogue.open(DialogueText.guardLines[2], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            }

                            if (GameMap.id == 6 && j == 0) {
                                Dialogue.open(DialogueText.guardLines[3], Dialogue.NO_ACTION, Npcs.x[j], Npcs.y[j] - 40);
                            }
                    }

                    if (GameMap.id == GuideMarker.map && j == GuideMarker.npc) {
                        GuideMarker.map = -1;
                    }
                } else if (Hud.rightSoftKeyLabel.equals("Trò chuyện")) {
                    Input.clearPressed();
                    Classmates.facing[Player.nearClassmate] = -Player.facing;
                    Dialogue.open(Classmates.lines[Player.nearClassmate], QuestAction.TALK, Classmates.x[Player.nearClassmate], Classmates.y[Player.nearClassmate] - 44);
                } else if (Hud.rightSoftKeyLabel.equals("Nói chuyện") && Player.nearGirl) {
                    if (Girl.following) {
                        Dialogue.open(DialogueText.girlLines[0], QuestAction.TALK, Girl.x, Girl.y - 44);
                    } else if (Story.girlOutingDone) {
                        Dialogue.open(DialogueText.girlLines[2], QuestAction.TALK, Girl.x, Girl.y - 44);
                        Girl.following = true;
                    } else if (Story.girlWaiting) {
                        Dialogue.open(DialogueText.girlLines[3], QuestAction.START_GIRL_OUTING, Girl.x, Girl.y - 44);
                        Story.girlWaiting = false;
                    } else {
                        Dialogue.open(DialogueText.girlLines[1], QuestAction.TALK, Girl.x, Girl.y - 44);
                    }
                }
            } else {
                if (Input.held[Input.KEY_UP]) {
                    if (Skills.motorbikeLicense) {
                        for(i = 0; i < Motorbikes.count; ++i) {
                            if (Player.y == Motorbikes.y[i] && MathUtil.abs(Player.x - Motorbikes.x[i]) < 20) {
                                Player.x = Motorbikes.x[i];
                                Player.action = PlayerAction.MOTORBIKE_STAND;
                                Input.clearHeld();
                                Player.motorbike = i;
                                Motorbikes.facing[i] = Player.facing;
                                Motorbikes.frame[i] = 0;
                                return;
                            }
                        }
                    }

                    if (Skills.lightness) {
                        Player.vy = -10;
                    } else {
                        Player.vy = -8;
                    }

                    Player.action = PlayerAction.JUMP;
                    Player.crate = -1;
                    Player.actionTimer = 0;
                    Player.nearNpc = -1;
                    Player.nearClassmate = -1;
                    Hud.rightSoftKeyLabel = " ";
                    Hud.dirty = true;
                } else if (Input.held[Input.KEY_LEFT]) {
                    if (Player.facing == 1) {
                        Player.facing = -1;
                    } else {
                        Player.action = PlayerAction.RUN;
                        Player.vx = -Player.moveSpeed;
                    }

                    Player.nearNpc = -1;
                    Player.nearClassmate = -1;
                    Hud.rightSoftKeyLabel = " ";
                    Hud.dirty = true;
                } else if (Input.held[Input.KEY_RIGHT]) {
                    if (Player.facing == -1) {
                        Player.facing = 1;
                    } else {
                        Player.action = PlayerAction.RUN;
                        Player.vx = Player.moveSpeed;
                    }

                    Player.nearClassmate = -1;
                    Player.nearNpc = -1;
                    Hud.rightSoftKeyLabel = " ";
                    Hud.dirty = true;
                } else if (Input.held[Input.KEY_DOWN]) {
                    Player.actionTimer = 0;
                    Player.actionTimer2 = 0;
                    Player.action = PlayerAction.CROUCH;
                } else if (Input.held[Input.KEY_NUM1]) {
                    if (Skills.lightness) {
                        Player.vy = -10;
                    } else {
                        Player.vy = -8;
                    }

                    Player.facing = -1;
                    Player.vx = -4;
                    Player.action = PlayerAction.JUMP;
                    Player.crate = -1;
                    Player.actionTimer = 0;
                    Player.nearNpc = -1;
                    Player.nearClassmate = -1;
                    Hud.rightSoftKeyLabel = " ";
                    Hud.dirty = true;
                } else if (Input.held[Input.KEY_NUM3]) {
                    if (Skills.lightness) {
                        Player.vy = -10;
                    } else {
                        Player.vy = -8;
                    }

                    Player.facing = 1;
                    Player.vx = 4;
                    Player.action = PlayerAction.JUMP;
                    Player.crate = -1;
                    Player.actionTimer = 0;
                    Player.nearNpc = -1;
                    Player.nearClassmate = -1;
                    Hud.rightSoftKeyLabel = " ";
                    Hud.dirty = true;
                }

                if (Input.pressed[Input.KEY_FIRE]) {
                    Player.action = PlayerAction.ATTACK;
                    Player.comboStep = Player.COMBO_FIRST_SLASH;
                    Player.actionTimer = 0;
                    Player.vy = 0;
                    Input.pressed[Input.KEY_FIRE] = false;
                    Player.nearNpc = -1;
                    Player.nearClassmate = -1;
                    Hud.rightSoftKeyLabel = " ";
                    Hud.dirty = true;
                }
            }
        } else if (Player.action == PlayerAction.RUN) {
            if (Input.held[Input.KEY_UP]) {
                Player.crate = -1;
                if (Skills.lightness) {
                    Player.vy = -10;
                } else {
                    Player.vy = -8;
                }

                Player.action = PlayerAction.JUMP;
                Player.actionTimer = 0;
            } else if (Input.held[Input.KEY_LEFT]) {
                if (Player.facing == 1) {
                    Player.facing = -1;
                } else {
                    Player.vx = -Player.moveSpeed + Player.sprintBonus;
                }
            } else if (Input.held[Input.KEY_RIGHT]) {
                if (Player.facing == -1) {
                    Player.facing = 1;
                } else {
                    Player.vx = Player.moveSpeed + Player.sprintBonus;
                }
            }

            if (Input.pressed[Input.KEY_FIRE]) {
                Player.action = PlayerAction.ATTACK;
                Player.comboStep = Player.COMBO_FIRST_SLASH;
                Player.actionTimer = 0;
                Player.vy = 0;
                Input.pressed[Input.KEY_FIRE] = false;
            }
        } else if (Player.action == PlayerAction.RUN_ON_WATER) {
            if (Input.held[Input.KEY_UP]) {
                if (Skills.lightness) {
                    Player.vy = -10;
                } else {
                    Player.vy = -8;
                }

                Player.action = PlayerAction.JUMP;
                Player.actionTimer = 0;
            } else if (Input.held[Input.KEY_LEFT]) {
                if (Player.facing == 1) {
                    Player.facing = -1;
                } else {
                    Player.vx = -Player.runOnWaterSpeed + Player.sprintBonus;
                }
            } else if (Input.held[Input.KEY_RIGHT]) {
                if (Player.facing == -1) {
                    Player.facing = 1;
                } else {
                    Player.vx = Player.runOnWaterSpeed + Player.sprintBonus;
                }
            }
        } else if (Player.action == PlayerAction.JUMP) {
            if (Input.held[Input.KEY_LEFT]) {
                if (Player.facing == 1) {
                    Player.facing = -1;
                } else {
                    Player.vx = -Player.moveSpeed;
                }
            } else if (Input.held[Input.KEY_RIGHT]) {
                if (Player.facing == -1) {
                    Player.facing = 1;
                } else {
                    Player.vx = Player.moveSpeed;
                }
            }

            if (Input.pressed[Input.KEY_FIRE]) {
                Player.action = PlayerAction.ATTACK;
                Player.comboStep = Player.COMBO_FIRST_SLASH;
                Player.actionTimer = 0;
                Input.pressed[Input.KEY_FIRE] = false;
            }

            if (Input.held[Input.KEY_UP] && PlayerStats.hasMp() && Player.vy > -4 && (Player.actionTimer == 0 && Skills.doubleJump || Player.actionTimer == 1 && Skills.tripleJump)) {
                ++Player.actionTimer;
                Player.vy = -7;
                PlayerStats.drainMp(3);

                Hud.dirty = true;
                Dust.spawn(-1, Player.x - -8, Player.y);
                Dust.spawn(1, Player.x - 8, Player.y);
                WaterSplash.spawn(Player.x, Player.y);
            }
        } else if (Player.action == PlayerAction.FALL) {
            if (Input.held[Input.KEY_LEFT]) {
                if (Player.facing == 1) {
                    Player.facing = -1;
                } else {
                    Player.vx = -Player.moveSpeed;
                }
            } else if (Input.held[Input.KEY_RIGHT]) {
                if (Player.facing == -1) {
                    Player.facing = 1;
                } else {
                    Player.vx = Player.moveSpeed;
                }
            }

            if (Input.pressed[Input.KEY_FIRE] && Player.vy > 4) {
                Player.action = PlayerAction.ATTACK;
                Player.actionTimer = 0;
                Player.comboStep = Player.COMBO_FIRST_SLASH;
                Input.pressed[Input.KEY_FIRE] = false;
            }
        } else if (Player.action == PlayerAction.ATTACK) {
            if (Input.held[Input.KEY_LEFT]) {
                if (Player.facing == 1) {
                    Player.facing = -1;
                } else {
                    Player.vx = -Player.moveSpeed + 2;
                }
            } else if (Input.held[Input.KEY_RIGHT]) {
                if (Player.facing == -1) {
                    Player.facing = 1;
                } else {
                    Player.vx = Player.moveSpeed - 2;
                }
            }

            if (Input.pressed[Input.KEY_FIRE]) {
                Player.comboQueued = true;
                Input.pressed[Input.KEY_FIRE] = false;
            }
        } else if (Player.action == PlayerAction.MOTORBIKE_STAND) {
            if (Input.held[Input.KEY_UP]) {
                Player.vy = -10;
                Player.action = PlayerAction.MOTORBIKE_JUMP;
                Player.actionTimer = 10;
                Player.crate = -1;
            } else if (Input.held[Input.KEY_LEFT]) {
                if (Player.facing == 1) {
                    Player.facing = -1;
                    Motorbikes.facing[Player.motorbike] = -1;
                } else {
                    Player.actionTimer = 1;
                    Player.action = PlayerAction.MOTORBIKE_RIDE;
                    Player.actionTimer = 10;
                    Player.vx = -Player.motorbikeSpeed;
                }
            } else if (Input.held[Input.KEY_RIGHT]) {
                if (Player.facing == -1) {
                    Player.facing = 1;
                    Motorbikes.facing[Player.motorbike] = 1;
                } else {
                    Player.action = PlayerAction.MOTORBIKE_RIDE;
                    Player.actionTimer = 10;
                    Player.vx = Player.motorbikeSpeed;
                }
            } else if (Input.held[Input.KEY_DOWN]) {
                Player.action = PlayerAction.STAND;
                Player.motorbike = -1;
            }
        } else if (Player.action == PlayerAction.MOTORBIKE_RIDE) {
            if (Input.held[Input.KEY_UP]) {
                Player.vy = -10;
                Player.action = PlayerAction.MOTORBIKE_JUMP;
                Player.crate = -1;
                Player.actionTimer = 10;
            } else if (Input.held[Input.KEY_LEFT]) {
                if (Player.facing == 1) {
                    Player.facing = -1;
                    Motorbikes.facing[Player.motorbike] = -1;
                } else {
                    Player.vx = -Player.motorbikeSpeed;
                }
            } else if (Input.held[Input.KEY_RIGHT]) {
                if (Player.facing == -1) {
                    Player.facing = 1;
                    Motorbikes.facing[Player.motorbike] = 1;
                } else {
                    Player.vx = Player.motorbikeSpeed;
                }
            }
        } else if (Player.action == PlayerAction.MOTORBIKE_JUMP) {
            if (Input.held[Input.KEY_LEFT]) {
                if (Player.facing == 1) {
                    Player.facing = -1;
                    Motorbikes.facing[Player.motorbike] = -1;
                } else {
                    Player.vx = -Player.motorbikeSpeed;
                }
            } else if (Input.held[Input.KEY_RIGHT]) {
                if (Player.facing == -1) {
                    Player.facing = 1;
                    Motorbikes.facing[Player.motorbike] = 1;
                } else {
                    Player.vx = Player.motorbikeSpeed;
                }
            }
        } else if (Player.action == PlayerAction.MOTORBIKE_FALL) {
            if (Input.held[Input.KEY_LEFT]) {
                if (Player.facing == 1) {
                    Player.facing = -1;
                    Motorbikes.facing[Player.motorbike] = -1;
                } else {
                    Player.vx = -Player.motorbikeSpeed;
                }
            } else if (Input.held[Input.KEY_RIGHT]) {
                if (Player.facing == -1) {
                    Player.facing = 1;
                    Motorbikes.facing[Player.motorbike] = 1;
                } else {
                    Player.vx = Player.motorbikeSpeed;
                }
            }
        } else if (Player.action == PlayerAction.UNDERWATER_STAND) {
            if (Input.held[Input.KEY_UP]) {
                Player.vy = -10;
                Player.action = PlayerAction.JUMP;
                Player.slashEffect = -1;
                Player.actionTimer = 0;
            } else if (Input.held[Input.KEY_LEFT]) {
                if (Player.facing == 1) {
                    Player.facing = -1;
                } else {
                    Player.action = PlayerAction.UNDERWATER_WALK;
                    Player.vx = -Player.underwaterSpeed;
                }
            } else if (Input.held[Input.KEY_RIGHT]) {
                if (Player.facing == -1) {
                    Player.facing = 1;
                } else {
                    Player.action = PlayerAction.UNDERWATER_WALK;
                    Player.vx = Player.underwaterSpeed;
                }
            }
        } else if (Player.action == PlayerAction.UNDERWATER_WALK) {
            if (Input.held[Input.KEY_UP]) {
                Player.vy = -10;
                Player.action = PlayerAction.JUMP;
                Player.slashEffect = -1;
                Player.actionTimer = 0;
            } else if (Input.held[Input.KEY_LEFT]) {
                if (Player.facing == 1) {
                    Player.facing = -1;
                } else {
                    Player.vx = -Player.underwaterSpeed;
                }
            } else if (Input.held[Input.KEY_RIGHT]) {
                if (Player.facing == -1) {
                    Player.facing = 1;
                } else {
                    Player.vx = Player.underwaterSpeed;
                }
            }
        } else if (Player.action == PlayerAction.SINK) {
            if (Input.held[Input.KEY_UP] && Skills.runOnWater) {
                Player.vy = -10;
                Player.action = PlayerAction.JUMP;
                Player.slashEffect = -1;
                Player.actionTimer = 0;
            }
        } else if (Player.action == PlayerAction.BURROWED) {
            if (Input.held[Input.KEY_LEFT]) {
                Player.facing = -1;
            } else if (Input.held[Input.KEY_RIGHT]) {
                Player.facing = 1;
            } else if (Input.held[Input.KEY_UP]) {
                Player.action = PlayerAction.BURROW_EXIT;
            }
        }
    }
}
