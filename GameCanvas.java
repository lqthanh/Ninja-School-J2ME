import assets.*;
import assets.Character;
import core.app.*;
import core.debug.*;
import core.fx.*;
import core.input.*;
import core.item.*;
import core.map.*;
import core.monster.*;
import core.npc.*;
import core.player.*;
import core.quest.*;
import core.render.*;
import core.save.*;
import core.story.*;
import core.text.*;
import core.ui.*;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Canvas chính của game (tên gốc khi bị làm rối: {@code b}). Chứa vòng lặp {@link #run()},
 * phần vẽ {@link #paint(Graphics)}, cập nhật nhân vật, AI quái/boss, cảnh diễn, lưu/tải game.
 * Dữ liệu của từng loại đối tượng nằm ở các lớp trong {@code core.*}; vì {@code core.*} không
 * tham chiếu được lớp ở default package, các hàm cần GameCanvas (gây sát thương, camera...) vẫn ở đây.
 */
public final class GameCanvas extends Canvas implements Runnable {
    private static int frameDurationMs = 40;
    private static int[][][] girlPoses = new int[][][]{{{0, -13, 31}, {0, -7, 8}, {0, -11, 14}}, {{0, -13, 32}, {0, -7, 8}, {0, -11, 15}}, {{1, -11, 31}, {1, -9, 16}, {6, -1000, 0}}, {{2, -11, 32}, {2, -10, 16}, {6, -1000, 0}}, {{1, -11, 31}, {3, -8, 16}, {6, -1000, 0}}, {{2, -10, 32}, {7, -9, 17}, {6, -1000, 0}}, {{1, -10, 31}, {8, -8, 16}, {6, -1000, 0}}, {{0, -8, 35}, {4, -5, 15}, {2, -10, 21}}, {{0, -8, 34}, {5, -6, 13}, {3, -11, 22}}, {{0, -8, 31}, {6, -13, 9}, {4, -10, 18}}, {{1, -8, 30}, {6, -13, 9}, {5, -8, 17}}, {{2, -10, 30}, {6, -13, 9}, {6, -8, 16}}, {{2, -10, 29}, {6, -13, 9}, {7, -11, 17}}, {{3, -11, 33}, {8, -4, 13}, {8, -15, 25}}};
    private static int[][][] heroPoses = new int[][][]{{{0, -10, 32}, {1, -7, 7}, {1, -11, 15}}, {{0, -10, 33}, {1, -7, 7}, {1, -11, 16}}, {{1, -10, 33}, {2, -10, 11}, {2, -9, 16}}, {{1, -10, 32}, {3, -11, 9}, {3, -11, 16}}, {{1, -10, 34}, {4, -9, 9}, {4, -8, 16}}, {{1, -10, 34}, {5, -11, 11}, {5, -10, 17}}, {{1, -10, 33}, {6, -9, 9}, {6, -8, 16}}, {{0, -9, 36}, {7, -5, 15}, {7, -10, 21}}, {{4, -13, 26}, new int[3], new int[3]}, {{5, -13, 25}, new int[3], new int[3]}, {{6, -12, 26}, new int[3], new int[3]}, {{7, -13, 25}, new int[3], new int[3]}, {{0, -9, 35}, {8, -4, 13}, {8, -14, 27}}, {{0, -9, 32}, {9, -11, 9}, {10, -10, 18}}, {{2, -7, 34}, {9, -11, 9}, {11, -8, 16}}, {{2, -8, 33}, {9, -11, 9}, {12, -8, 15}}, {{2, -7, 33}, {9, -11, 9}, {13, -12, 16}}, {{0, -11, 32}, {9, -11, 9}, {14, -15, 19}}, {{2, -9, 33}, {9, -11, 9}, {15, -13, 20}}, {{2, -9, 32}, {9, -11, 9}, {16, -7, 23}}, {{2, -9, 33}, {9, -11, 9}, {17, -11, 19}}, {{3, -12, 34}, {8, -4, 13}, {8, -15, 25}}, {{0, -10, 28}, new int[3], {19, -8, 12}}, {{0, -9, 35}, {8, -4, 13}, {9, -9, 22}}, {{0, -10, 33}, new int[3], new int[3]}, {{9, -4, 24}, new int[3], new int[3]}, {{10, -4, 22}, new int[3], new int[3]}, {{0, -9, 32}, {8, -4, 9}, {10, -10, 18}}, {{2, -7, 34}, {8, -4, 9}, {11, -8, 16}}, {{2, -8, 33}, {8, -4, 9}, {12, -8, 15}}, {{2, -7, 33}, {8, -4, 9}, {13, -12, 16}}, {{0, -11, 32}, {7, -5, 9}, {14, -15, 19}}, {{2, -9, 33}, {7, -5, 9}, {15, -13, 20}}, {{2, -9, 32}, {7, -5, 9}, {16, -7, 23}}, {{2, -9, 33}, {7, -5, 9}, {17, -11, 19}}, {{0, -9, 32}, {2, -10, 11}, {10, -10, 18}}, {{2, -7, 34}, {3, -11, 9}, {11, -8, 16}}, {{2, -8, 33}, {4, -9, 9}, {12, -8, 15}}, {{2, -7, 33}, {5, -11, 11}, {13, -12, 16}}, {{0, -11, 32}, {2, -10, 11}, {14, -15, 19}}, {{2, -9, 33}, {3, -11, 9}, {15, -13, 20}}, {{2, -9, 32}, {4, -9, 9}, {16, -7, 23}}, {{2, -9, 33}, {5, -11, 11}, {17, -11, 19}}, {{1, -8, 38}, new int[3], {18, -8, 21}}, {{1, -8, 39}, new int[3], {18, -8, 22}}, {{1, -10, 43}, new int[3], {18, -11, 26}}};
    private static int[] slashOffsetX = new int[]{-2, -6, 22, 21, 19, 22, 10, -2, -2, 5, 19};
    private static int[] slashOffsetY = new int[]{9, 22, 25, 17, 26, 37, 36, 49, 50, 52, 36};
    private static int[][][] classmatePoses;
    private static int[] shakeOffsetX;
    private static int[] shakeOffsetY;
    private static int shakeStep;
    private static GameCanvas instance;
    static boolean running;
    private static int mainMenuIndex;
    public static boolean hudDirty;
    private static int menuCurtainTopLimit;
    private static int menuCurtainBottomLimit;
    private static int menuCurtainTop;
    private static int menuCurtainBottom;
    private static int menuTextTargetY;
    private static int menuTextY;
    private static boolean hudPanelVisible;
    public static String leftSoftKeyLabel;
    public static String rightSoftKeyLabel;
    private static int hudX;
    private static int hudY;
    private static int hudWidth;
    private static int hudHeight;
    private static int hudLeftFrameWidth;
    private static int hudRightFrameWidth;
    private static int hudStripWidth;
    private static int hudBarX;
    private static int hudBarY;
    private static int hudBarMaxWidth;
    private static int hudExpBarMaxWidth;
    private static int hudLevelX;
    private static int hudMoneyX;
    private static int hudMpBarHeight;
    private static int letterboxMaxHeight;
    private static int letterboxHeight;
    private static int firstVisibleCol;
    private static int firstVisibleRow;
    private static int visibleCols;
    private static int visibleRows;
    private static int lastVisibleCol;
    private static int lastVisibleRow;
    private static int cameraOffsetY;
    private static int cameraLeadX;
    private static Image[] arrowIcons;
    private static Image itemScroll;
    private static Image[] heroBodies;
    private static Image[] hudFrames;
    private static Image markerIcon;
    private static Image[] heroHeads;
    private static Image[] heroTransformHeads;
    private static Image[] heroLegs;
    private static Image titleImage;
    private static Image[] slashEffects;
    private static Image[] weaponIcons;
    public static int mapId;
    public static int tick;
    private static boolean cameraFollowsPlayer;
    private static int questItemX;
    private static int questItemY;
    private static int questItemState;
    private static int cameraTargetX;
    private static int cameraTargetY;
    private static int cameraVx;
    private static int cameraVy;
    private static int cameraSubpixelX;
    private static int cameraSubpixelY;
    private static int cameraX;
    private static int cameraY;
    private static int cameraMaxX;
    private static int cameraMaxY;
    private static boolean cutscenePlaying;
    private static int cutsceneId;
    private static int cutsceneStep;
    private static boolean captionVisible;
    private static String[] captionLines;
    private static int captionY;
    private static int captionTargetY;
    private static int cutsceneWaitTimer;
    private static boolean cutsceneDialogueOpen;
    private static int guideMarkerMap;
    private static int guideMarkerX;
    private static int guideMarkerY;
    private static int guideArrowIcon;
    private static int guideArrowX;
    private static int guideArrowY;
    private static int guideArrowBobDirection;
    private static int guideArrowBob;
    private static int guideMarkerNpc;
    private static int mapEventId;
    private static boolean mapEventActive;
    private static int mapEventStep;
    private static int dialogueBoxLines;
    private static Image tilesetImage;
    private static Image waterfallImage;
    private static Image waterSurfaceImage;
    private static Image[] houseImages;
    private static int houseCount;
    private static int[] houseTypes;
    private static int[] houseX;
    private static int[] houseY;
    private static int[] houseHalfWidth;
    private static int[] houseHeight;
    private static Image bushImage;
    private static int parallaxScreenX;
    private static int parallaxFromCenter;
    private static int parallaxDrawX;
    private static int parallaxScaled;
    private static String[] signTexts;
    private static int[] signX;
    private static int[] signY;
    private static int[] signWidth;
    private static int signCount;
    static int loadingTarget;
    private static int unusedLoadingCounter;
    static int loadingProgress;
    public static boolean prologueNoRespawn;
    private static Image[][] npcSprites;
    private static int npcCount;
    private static int[] npcX;
    private static int[] npcY;
    private static int[] npcTypes;
    private static int[] npcBodyOffsetY;
    private static int pendingMarkerMap;
    private static int pendingMarkerX;
    private static int pendingMarkerY;
    private static int pendingMarkerNpc;
    private static Font unusedFont;
    private static int unusedFontLineHeight;

    static {
        classmatePoses = new int[heroPoses.length][3][3];

        int pose;
        for(pose = 0; pose < heroPoses.length; ++pose) {
            for(int part = 0; part < 3; ++part) {
                for(int k = 0; k < 3; ++k) {
                    classmatePoses[pose][part][k] = heroPoses[pose][part][k];
                    if (part == 0 && k == 0) {
                        switch (heroPoses[pose][part][k]) {
                            case 1:
                            case 2:
                                classmatePoses[pose][part][k] = 1;
                                break;
                            default:
                                classmatePoses[pose][part][k] = 0;
                        }
                    }
                }
            }
        }

        shakeOffsetX = new int[]{-1, 2, 1, -2};
        shakeOffsetY = new int[]{-3, 2, -1, 1};
        shakeStep = -1;
        running = true;
        mainMenuIndex = 0;
        RiverMonster.part2OffsetX = -22;
        RiverMonster.part3OffsetX = 18;
        RiverMonster.part1OffsetX = -10;
        RiverMonster.part2OffsetY = -19;
        RiverMonster.part3OffsetY = -12;
        RiverMonster.part1OffsetY = -26;
        Skills.lightness = false;
        Skills.doubleJump = false;
        Skills.darts = false;
        Skills.burrow = false;
        Skills.motorbikeLicense = false;
        Skills.doubleStrength = false;
        Skills.dive = false;
        Skills.runOnWater = false;
        Skills.transform = false;
        Story.goldenBatQuestDone = false;
        Story.tonikuQuestDone = false;
        Story.girlWaiting = false;
        Story.girlOutingDone = false;
        Skills.tripleJump = false;
        Skills.dartsLevel2 = false;
        Skills.sprint = false;
        Skills.standOnWater = false;
        Skills.hide = false;
        Story.gameCompleted = false;
        Story.sakuraEscorting = false;
        cameraFollowsPlayer = true;
        cutscenePlaying = false;
        cutsceneStep = 0;
        captionLines = new String[]{""};
        cutsceneWaitTimer = 0;
        guideMarkerMap = -1;
        guideArrowBobDirection = 1;
        Girl.following = false;
        Girl.mapId = -1;
        FinalBoss.damage = 500;
        FinalBoss.width = 60;
        FinalBoss.height = 60;
        RiverMonster.damage = 20;
        RiverMonster.height = 60;
        RiverMonster.width = 50;
        mapEventId = 10;
        Dialogue.visible = false;
        loadingTarget = 0;
        unusedLoadingCounter = 0;
        loadingProgress = 0;
        Monsters.groupSpriteSets = null;
        // Bảng thoại không được dùng ở đâu (còn sót lại trong bản gốc).
        String[][] unusedBusyLines = new String[][]{{"Ta đang bận."}, 
            {"Ngươi đã học được chiêu shuriken cuối cùng, ta ko còn gì dạy ngươi nữa. Đi đi."}};
        npcBodyOffsetY = new int[]{7, 6, 6, 5, 2, 4};
        pendingMarkerMap = -1;
        unusedFont = Font.getFont(0, 0, 0);
        unusedFontLineHeight = unusedFont.getHeight() + 6;

    }

    public static void saveGame() {
        int[] data = new int[500];
        int index = 0;
        PlayTime.checkpoint();
        ++index;
        data[0] = PlayTime.savedSeconds;
        ++index;
        data[1] = mapId;
        ++index;
        data[2] = tick;
        ++index;
        data[3] = Story.prologueActive ? 1 : 0;
        ++index;
        data[4] = Inventory.money;
        ++index;
        data[5] = Classmates.stayPut ? 1 : 0;
        ++index;
        data[6] = Story.riverMonsterDefeated ? 1 : 0;
        ++index;
        data[7] = Story.tonikuDefeated ? 1 : 0;
        ++index;
        data[8] = Story.sakuraFound ? 1 : 0;
        ++index;
        data[9] = Girl.following ? 1 : 0;
        ++index;
        data[10] = Girl.unusedSaveSlot;
        ++index;
        data[11] = Girl.mapId;
        ++index;
        data[12] = Player.motorbike;
        ++index;
        data[13] = Inventory.swordLevel;
        ++index;
        data[14] = Skills.dartsLevel2 ? 1 : 0;

        int i;
        for(i = 0; i < WorldMap.visited.length; ++i) {
            data[index++] = WorldMap.visited[i] ? 1 : 0;
        }

        for(i = 0; i < Inventory.potionCounts.length; ++i) {
            data[index++] = Inventory.potionCounts[i];
        }

        data[index++] = Skills.lightness ? 1 : 0;
        data[index++] = Skills.doubleJump ? 1 : 0;
        data[index++] = Skills.darts ? 1 : 0;
        data[index++] = Skills.burrow ? 1 : 0;
        data[index++] = Skills.motorbikeLicense ? 1 : 0;
        data[index++] = Skills.doubleStrength ? 1 : 0;
        data[index++] = Skills.dive ? 1 : 0;
        data[index++] = Skills.runOnWater ? 1 : 0;
        data[index++] = Skills.transform ? 1 : 0;
        data[index++] = Story.goldenBatQuestDone ? 1 : 0;
        data[index++] = Story.tonikuQuestDone ? 1 : 0;
        data[index++] = Story.girlWaiting ? 1 : 0;
        data[index++] = Story.girlOutingDone ? 1 : 0;
        data[index++] = Skills.tripleJump ? 1 : 0;
        data[index++] = Skills.sprint ? 1 : 0;
        data[index++] = Skills.standOnWater ? 1 : 0;
        data[index++] = Skills.hide ? 1 : 0;
        data[index++] = Story.gameCompleted ? 1 : 0;
        data[index++] = Story.sakuraEscorting ? 1 : 0;
        data[index++] = Player.slashEffect;
        data[index++] = Player.weapon;
        data[index++] = Player.x;
        data[index++] = Player.y;
        data[index++] = Player.vx;
        data[index++] = Player.vy;
        data[index++] = Player.action;
        data[index++] = Player.pose;
        data[index++] = Player.width;
        data[index++] = Player.height;
        data[index++] = Player.halfWidth;
        data[index++] = Player.halfHeight;
        data[index++] = Player.quarterWidth;
        data[index++] = Player.quarterHeight;
        data[index++] = Player.facing;
        data[index++] = Player.actionTimer;
        data[index++] = Player.actionTimer2;
        data[index++] = Player.hurtRecovery;
        data[index++] = PlayerStats.attack;
        data[index++] = PlayerStats.dartDamage;
        data[index++] = PlayerStats.hp;
        data[index++] = PlayerStats.maxHp;
        data[index++] = PlayerStats.mp;
        data[index++] = PlayerStats.maxMp;
        data[index++] = Player.runOnWaterSpeed;
        data[index++] = Player.underwaterSpeed;
        data[index++] = Player.sprintBonus;
        data[index++] = Player.checkpointX;
        data[index++] = Player.checkpointY;
        data[index++] = Player.comboCount;
        data[index++] = Player.comboStep;
        data[index++] = PlayerStats.level;
        data[index++] = PlayerStats.exp;
        data[index++] = cameraFollowsPlayer ? 1 : 0;
        data[index++] = Player.comboQueued ? 1 : 0;
        data[index++] = Player.hidden ? 1 : 0;
        data[index++] = Player.transformed ? 1 : 0;
        data[index++] = QuestState.current;
        data[index++] = QuestState.dropMonster;
        data[index++] = QuestState.dropChance;
        data[index++] = QuestState.itemFound ? 1 : 0;
        data[index++] = QuestState.progress;
        data[index++] = Player.nearNpc;
        data[index++] = Player.nearClassmate;
        data[index++] = Girl.slashEffect;
        data[index++] = Girl.weapon;
        data[index++] = questItemX;
        data[index++] = questItemY;
        data[index++] = questItemState;
        data[index++] = cameraTargetX;
        data[index++] = cameraTargetY;
        data[index++] = cameraVx;
        data[index++] = cameraVy;
        data[index++] = cameraSubpixelX;
        data[index++] = cameraSubpixelY;
        data[index++] = cameraX;
        data[index++] = cameraY;
        data[index++] = cameraMaxX;
        data[index++] = cameraMaxY;
        data[index++] = mapEventActive ? 1 : 0;
        data[index++] = mapEventId;
        data[index++] = mapEventStep;
        data[index++] = guideMarkerMap;
        data[index++] = guideMarkerX;
        data[index++] = guideMarkerY;
        data[index++] = guideArrowIcon;
        data[index++] = guideArrowX;
        data[index++] = guideArrowY;
        data[index++] = guideArrowBobDirection;
        data[index++] = guideArrowBob;
        data[index++] = guideMarkerNpc;
        data[index++] = Girl.x;
        data[index++] = Girl.y;
        data[index++] = Girl.vx;
        data[index++] = Girl.vy;
        data[index++] = Girl.action;
        data[index++] = Girl.pose;
        data[index++] = Girl.width;
        data[index++] = Girl.height;
        data[index++] = Girl.halfWidth;
        data[index++] = Girl.halfHeight;
        data[index++] = Girl.quarterWidth;
        data[index++] = Girl.quarterHeight;
        data[index++] = Girl.facing;
        data[index++] = Girl.actionTimer;
        data[index++] = Girl.actionTimer2;
        data[index++] = Girl.airTimer;
        data[index++] = Girl.unused1;
        data[index++] = Girl.unused2;
        data[index++] = Girl.unused3;
        data[index++] = Girl.unused4;
        data[index++] = Girl.unused5;
        data[index++] = Girl.unused6;
        data[index++] = Girl.unused7;
        data[index++] = BoarBoss.hp;
        data[index++] = Toniku.hp;
        data[index++] = FinalBoss.hp;
        data[index++] = RiverMonster.hp;
        data[index++] = FinalBoss.state;
        data[index++] = Motorbikes.count;
        if (Motorbikes.count > 0) {
            for(i = 0; i < Motorbikes.falling.length; ++i) {
                data[index++] = Motorbikes.falling[i];
            }

            for(i = 0; i < Motorbikes.facing.length; ++i) {
                data[index++] = Motorbikes.facing[i];
            }

            for(i = 0; i < Motorbikes.frame.length; ++i) {
                data[index++] = Motorbikes.frame[i];
            }

            for(i = 0; i < Motorbikes.vy.length; ++i) {
                data[index++] = Motorbikes.vy[i];
            }

            for(i = 0; i < Motorbikes.x.length; ++i) {
                data[index++] = Motorbikes.x[i];
            }

            for(i = 0; i < Motorbikes.y.length; ++i) {
                data[index++] = Motorbikes.y[i];
            }
        }

        data[index++] = Crates.count;
        if (Crates.count > 0) {
            data[index++] = Player.crate;
            if (Crates.state != null) {
                for(i = 0; i < Crates.state.length; ++i) {
                    data[index++] = Crates.state[i];
                }
            }

            if (Crates.driftVx != null) {
                for(i = 0; i < Crates.driftVx.length; ++i) {
                    data[index++] = Crates.driftVx[i];
                }
            }

            if (Crates.bobDirection != null) {
                for(i = 0; i < Crates.bobDirection.length; ++i) {
                    data[index++] = Crates.bobDirection[i];
                }
            }

            if (Crates.vx != null) {
                for(i = 0; i < Crates.vx.length; ++i) {
                    data[index++] = Crates.vx[i];
                }
            }

            if (Crates.vy != null) {
                for(i = 0; i < Crates.vy.length; ++i) {
                    data[index++] = Crates.vy[i];
                }
            }

            if (Crates.x != null) {
                for(i = 0; i < Crates.x.length; ++i) {
                    data[index++] = Crates.x[i];
                }
            }

            if (Crates.y != null) {
                for(i = 0; i < Crates.y.length; ++i) {
                    data[index++] = Crates.y[i];
                }
            }
        }

        int[] ints = data;
        int length;
        byte[] bytes = new byte[(length = data.length) << 2];

        for(int j = 0; j < length; ++j) {
            bytes[j << 2] = (byte)(ints[j] >> 24);
            bytes[(j << 2) + 1] = (byte)(ints[j] >>> 16);
            bytes[(j << 2) + 2] = (byte)(ints[j] >>> 8);
            bytes[(j << 2) + 3] = (byte)ints[j];
        }

        Save.saveRecord("ninjasave", bytes);
        Save.markSaved();
    }

    public static void loadGame() {
        int[] data = bytesToInts(Save.loadRecord("ninjasave"));
        int index = 0;
        ++index;
        PlayTime.savedSeconds = data[0];
        ++index;
        mapId = data[1];
        ++index;
        tick = data[2];
        ++index;
        Story.prologueActive = data[3] == 1;
        ++index;
        Inventory.money = data[4];
        ++index;
        Classmates.stayPut = data[5] == 1;
        ++index;
        Story.riverMonsterDefeated = data[6] == 1;
        ++index;
        Story.tonikuDefeated = data[7] == 1;
        ++index;
        Story.sakuraFound = data[8] == 1;
        ++index;
        Girl.following = data[9] == 1;
        ++index;
        Girl.unusedSaveSlot = data[10];
        ++index;
        Girl.mapId = data[11];
        ++index;
        Player.motorbike = data[12];
        ++index;
        Inventory.swordLevel = data[13];
        ++index;
        Skills.dartsLevel2 = data[14] == 1;

        int i;
        for(i = 0; i < WorldMap.visited.length; ++i) {
            WorldMap.visited[i] = data[index++] == 1;
        }

        for(i = 0; i < Inventory.potionCounts.length; ++i) {
            Inventory.potionCounts[i] = data[index++];
        }

        Skills.lightness = data[index++] == 1;
        Skills.doubleJump = data[index++] == 1;
        Skills.darts = data[index++] == 1;
        Skills.burrow = data[index++] == 1;
        Skills.motorbikeLicense = data[index++] == 1;
        Skills.doubleStrength = data[index++] == 1;
        Skills.dive = data[index++] == 1;
        Skills.runOnWater = data[index++] == 1;
        Skills.transform = data[index++] == 1;
        Story.goldenBatQuestDone = data[index++] == 1;
        Story.tonikuQuestDone = data[index++] == 1;
        Story.girlWaiting = data[index++] == 1;
        Story.girlOutingDone = data[index++] == 1;
        Skills.tripleJump = data[index++] == 1;
        Skills.sprint = data[index++] == 1;
        Skills.standOnWater = data[index++] == 1;
        Skills.hide = data[index++] == 1;
        Story.gameCompleted = data[index++] == 1;
        Story.sakuraEscorting = data[index++] == 1;
        Player.slashEffect = data[index++];
        Player.weapon = data[index++];
        Player.x = data[index++];
        Player.y = data[index++];
        Player.vx = data[index++];
        Player.vy = data[index++];
        Player.action = data[index++];
        Player.pose = data[index++];
        Player.width = data[index++];
        Player.height = data[index++];
        Player.halfWidth = data[index++];
        Player.halfHeight = data[index++];
        Player.quarterWidth = data[index++];
        Player.quarterHeight = data[index++];
        Player.facing = data[index++];
        Player.actionTimer = data[index++];
        Player.actionTimer2 = data[index++];
        Player.hurtRecovery = data[index++];
        PlayerStats.attack = data[index++];
        PlayerStats.dartDamage = data[index++];
        PlayerStats.hp = data[index++];
        PlayerStats.maxHp = data[index++];
        PlayerStats.mp = data[index++];
        PlayerStats.maxMp = data[index++];
        Player.runOnWaterSpeed = data[index++];
        Player.underwaterSpeed = data[index++];
        Player.sprintBonus = data[index++];
        Player.checkpointX = data[index++];
        Player.checkpointY = data[index++];
        Player.comboCount = data[index++];
        Player.comboStep = data[index++];
        PlayerStats.level = data[index++];
        PlayerStats.exp = data[index++];
        cameraFollowsPlayer = data[index++] == 1;
        Player.comboQueued = data[index++] == 1;
        Player.hidden = data[index++] == 1;
        Player.transformed = data[index++] == 1;
        QuestState.current = data[index++];
        QuestState.dropMonster = data[index++];
        QuestState.dropChance = data[index++];
        QuestState.itemFound = data[index++] == 1;
        QuestState.progress = data[index++];
        Player.nearNpc = data[index++];
        Player.nearClassmate = data[index++];
        Girl.slashEffect = data[index++];
        Girl.weapon = data[index++];
        questItemX = data[index++];
        questItemY = data[index++];
        questItemState = data[index++];
        cameraTargetX = data[index++];
        cameraTargetY = data[index++];
        cameraVx = data[index++];
        cameraVy = data[index++];
        cameraSubpixelX = data[index++];
        cameraSubpixelY = data[index++];
        cameraX = data[index++];
        cameraY = data[index++];
        cameraMaxX = data[index++];
        cameraMaxY = data[index++];
        mapEventActive = data[index++] == 1;
        mapEventId = data[index++];
        mapEventStep = data[index++];
        guideMarkerMap = data[index++];
        guideMarkerX = data[index++];
        guideMarkerY = data[index++];
        guideArrowIcon = data[index++];
        guideArrowX = data[index++];
        guideArrowY = data[index++];
        guideArrowBobDirection = data[index++];
        guideArrowBob = data[index++];
        guideMarkerNpc = data[index++];
        Girl.x = data[index++];
        Girl.y = data[index++];
        Girl.vx = data[index++];
        Girl.vy = data[index++];
        Girl.action = data[index++];
        Girl.pose = data[index++];
        Girl.width = data[index++];
        Girl.height = data[index++];
        Girl.halfWidth = data[index++];
        Girl.halfHeight = data[index++];
        Girl.quarterWidth = data[index++];
        Girl.quarterHeight = data[index++];
        Girl.facing = data[index++];
        Girl.actionTimer = data[index++];
        Girl.actionTimer2 = data[index++];
        Girl.airTimer = data[index++];
        Girl.unused1 = data[index++];
        Girl.unused2 = data[index++];
        Girl.unused3 = data[index++];
        Girl.unused4 = data[index++];
        Girl.unused5 = data[index++];
        Girl.unused6 = data[index++];
        Girl.unused7 = data[index++];
        if ((BoarBoss.hp = data[index++]) <= 0) {
            BoarBoss.hp = BoarBoss.maxHp;
        }

        Toniku.hp = data[index++];
        FinalBoss.hp = data[index++];
        RiverMonster.hp = data[index++];
        FinalBoss.state = data[index++];
        int savedMotorbikeCount = data[index++];
        if (savedMotorbikeCount > 0) {
            try {
                for(i = 0; i < Motorbikes.falling.length; ++i) {
                    ++index;
                }

                for(i = 0; i < Motorbikes.facing.length; ++i) {
                    ++index;
                }

                for(i = 0; i < Motorbikes.frame.length; ++i) {
                    ++index;
                }

                for(i = 0; i < Motorbikes.vy.length; ++i) {
                    ++index;
                }

                for(i = 0; i < Motorbikes.x.length; ++i) {
                    ++index;
                }

                for(i = 0; i < Motorbikes.y.length; ++i) {
                    ++index;
                }
            } catch (Exception e) {
            }
        }

        if ((Crates.count = data[index++]) > 0) {
            Player.crate = data[index++];

            try {
                for(i = 0; i < Crates.state.length; ++i) {
                    Crates.state[i] = data[index++];
                }

                for(i = 0; i < Crates.driftVx.length; ++i) {
                    Crates.driftVx[i] = data[index++];
                }

                for(i = 0; i < Crates.bobDirection.length; ++i) {
                    Crates.bobDirection[i] = data[index++];
                }

                for(i = 0; i < Crates.vx.length; ++i) {
                    Crates.vx[i] = data[index++];
                }

                for(i = 0; i < Crates.vy.length; ++i) {
                    Crates.vy[i] = data[index++];
                }

                for(i = 0; i < Crates.x.length; ++i) {
                    Crates.x[i] = data[index++];
                }

                for(i = 0; i < Crates.y.length; ++i) {
                    Crates.y[i] = data[index++];
                }
            } catch (Exception e) {
            }
        }

        if (Player.transformed) {
            if (heroTransformHeads == null) {
                heroTransformHeads = new Image[4];

                try {
                    heroTransformHeads[0] = Character.loadHeroTransformHead(0);
                    heroTransformHeads[1] = Character.loadHeroTransformHead(1);
                } catch (Exception e) {
                }

                heroTransformHeads[2] = heroTransformHeads[1];
                heroTransformHeads[3] = heroTransformHeads[0];
            }

            for(i = 0; i < 4; ++i) {
                Image swap = heroHeads[i];
                heroHeads[i] = heroTransformHeads[i];
                heroTransformHeads[i] = swap;
            }
        }

    }

    private static int[] bytesToInts(byte[] bytes) {
        int length;
        int[] ints = new int[(length = bytes.length) >> 2];

        for(int i = 0; i < ints.length; ++i) {
            ints[i] = (bytes[i << 2] << 24) + ((bytes[(i << 2) + 1] & 255) << 16) + ((bytes[(i << 2) + 2] & 255) << 8) + (bytes[(i << 2) + 3] & 255);
        }

        return ints;
    }

    public static final int abs(int value) {
        return value > 0 ? value : -value;
    }

    private static void gainExp(int amount) {
        if (Leveling.addExp(amount)) {
            FloatingText.spawn("level-up", 2, Player.x, Player.y - Player.height, 0, -2);
            MessageBox.show(MessageType.LEVEL_UP);
            MessageBox.setText("Chúc mừng! Bạn đạt được cấp " + PlayerStats.level + '\n' + "HP: " + PlayerStats.maxHp + '\n' + "MP: " + PlayerStats.maxMp + '\n' + "Sức đánh: " + PlayerStats.getDisplayedAttack(Inventory.swordLevel));
            GameState.current = GameState.MESSAGE;
        } else {
            FloatingText.spawn("+" + amount, 2, Player.x, Player.y - Player.height, 0, -2);
        }

        hudDirty = true;
    }

    private static void playerSwordHit() {
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

        int damage = PlayerStats.rollMeleeDamage(tick, Inventory.swordLevel);
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

                shakeCameraIfTransformed();
            }

            for(i = 0; i < Crates.count; ++i) {
                if (abs(Player.y - Player.halfHeight - Crates.y[i]) < 20 && abs(Player.x + (Player.facing << 4) - Crates.x[i]) < 24) {
                    Crates.state[i] = Crates.STATE_SLIDING;
                    Crates.vx[i] = Player.facing << 3;
                    Blood.spawn(Crates.x[i], Crates.y[i] - 12, Player.facing);
                    shakeCameraIfTransformed();
                    break;
                }
            }

            if (BoarBoss.active && abs(Player.y - BoarBoss.y) < 30 && abs(Player.x + (Player.facing << 4) - BoarBoss.x) < 40) {
                if (!damageBoarBoss(damage, -Player.facing)) {
                    Blood.spawn(Player.x + (BoarBoss.x - Player.x >> 1), Player.y - 12, Player.facing);
                    FloatingText.spawn("-" + damage, 0, BoarBoss.x, BoarBoss.y - BoarBoss.height, 0, -2);
                }

                shakeCameraIfTransformed();
            }

            for(i = 0; i < Ninjas.count; ++i) {
                if (abs(Player.y - Ninjas.y[i]) <= 12 && abs(Player.x + (Player.facing << 4) - Ninjas.x[i]) < 24) {
                    damageNinja(i, damage, -Player.facing);
                    Blood.spawn(Ninjas.x[i], Ninjas.y[i] - 15, Player.facing);
                    FloatingText.spawn("-" + damage, 0, Ninjas.x[i], Ninjas.y[i] - 28, 0, -2);
                    shakeCameraIfTransformed();
                    break;
                }
            }

            if (RiverMonster.active && abs(Player.y - (RiverMonster.y - 40)) < 30 && abs(Player.x + (Player.facing << 4) - RiverMonster.x) < 40) {
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
                shakeCameraIfTransformed();
            }

            if (Toniku.active) {
                if (abs(Player.y - Classmates.y[0]) < 5 && abs(Player.x + (Player.facing << 4) - Classmates.x[0]) < 30) {
                    damageToniku(damage, Player.facing);
                }

                shakeCameraIfTransformed();
            }

            if (FinalBoss.active) {
                if (abs(Player.y - (FinalBoss.y - 30)) < 30 && abs(Player.x + (Player.facing << 4) - FinalBoss.x) < 30) {
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

                shakeCameraIfTransformed();
            }

        }
    }

    private static void girlAttackHit() {
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
                damageMonster(target, damage, -Girl.facing);
                Blood.spawn(Monsters.x[target], Monsters.y[target] - (Monsters.height[target] >> 1), Girl.facing);
                FloatingText.spawn("-" + damage, 0, Monsters.x[target], Monsters.y[target] - Monsters.height[target], 0, -2);
            }

            if (BoarBoss.active && abs(Girl.y - BoarBoss.y) < 30 && abs(Girl.x + (Girl.facing << 4) - BoarBoss.x) < 40 && !damageBoarBoss(damage, -Girl.facing)) {
                Blood.spawn(Girl.x + (BoarBoss.x - Girl.x >> 1), Girl.y - 12, Girl.facing);
                FloatingText.spawn("-" + damage, 0, BoarBoss.x, BoarBoss.y - BoarBoss.height, 0, -2);
            }

            for(int i = 0; i < Ninjas.count; ++i) {
                if (Girl.y == Ninjas.y[i] && abs(Girl.x + (Girl.facing << 4) - Ninjas.x[i]) < 24) {
                    damageNinja(i, damage, -Girl.facing);
                    Blood.spawn(Ninjas.x[i], Ninjas.y[i] - 15, Girl.facing);
                    FloatingText.spawn("-" + damage, 0, Ninjas.x[i], Ninjas.y[i] - 28, 0, -2);
                    return;
                }
            }
        }
    }

    private static void landOnCrates() {
        for(int i = 0; i < Crates.count; ++i) {
            if (Crates.state[i] != Crates.STATE_FALLING && abs(Player.x - Crates.x[i]) < 20 && Crates.y[i] > Player.y && Crates.y[i] - 24 <= Player.y) {
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

    private static void pushCratesInAir() {
        for(int i = 0; i < Crates.count; ++i) {
            if (abs(Player.x - Crates.x[i]) < 20 && abs(Crates.y[i] - Player.y) < 4) {
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

    private static void ninjaMeleeIfPlayerClose(int i) {
        if (abs(Player.y - Ninjas.y[i]) <= 24 && abs(Ninjas.x[i] + (Ninjas.direction[i] << 3) - Player.x) < 25 && (Ninjas.type[i] == 6 || Ninjas.type[i] == 2 || Ninjas.type[i] == 3)) {
            Ninjas.targetX[i] = Ninjas.timer[i] = 0;
            Ninjas.state[i] = Ninjas.STATE_ATTACK;
        }

    }

    private static void ninjaReactToPlayer(int i) {
        if (!Player.hidden) {
            if (abs(Ninjas.x[i] - Player.x) < 100 && abs(Ninjas.y[i] - Player.y) < 20) {
                switch (Ninjas.type[i]) {
                    case 0:
                    case 1:
                    case 4:
                    case 5:
                        Ninjas.direction[i] = Ninjas.x[i] < Player.x ? 1 : -1;
                        Ninjas.targetX[i] = Ninjas.timer[i] = 0;
                        Ninjas.state[i] = Ninjas.STATE_ATTACK;
                        break;
                    case 2:
                    case 3:
                    case 6:
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

    private static void pushCrates() {
        for(int i = 0; i < Crates.count; ++i) {
            if (abs(Player.x - Crates.x[i]) < 20 && Crates.y[i] == Player.y) {
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

    private static void onDialogueFinished() {
        label58:
        switch (Dialogue.finishAction) {
            case QuestAction.START_KILL_SNAILS:
                QuestState.current = Quest.KILL_SNAILS;
                QuestState.progress = 0;
                Notice.show("NV: Giết 10 ốc sên");
                break;
            case QuestAction.REWARD_LIGHTNESS:
                Skills.lightness = true;
                Skills.doubleJump = true;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                Notice.show("đã học phép khinh công");
                break;
            case QuestAction.START_KNOCK_DOWN_SCARECROWS:
                QuestState.current = Quest.KNOCK_DOWN_SCARECROWS;
                QuestState.progress = 0;
                Notice.show("NV: đánh ngã 2 bù nhìn");
                break;
            case QuestAction.REWARD_DARTS:
                Skills.darts = true;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                Notice.show("đã học ném phi tiêu");
            case QuestAction.UNUSED_7:
            default:
                break;
            case QuestAction.UNUSED_CLEAR_NOTICE:
                Notice.show((String)null);
                break;
            case QuestAction.START_FIND_BURROW_SCROLL:
                QuestState.current = Quest.FIND_BURROW_SCROLL;
                QuestState.dropMonster = QuestState.DROP_MONSTER_BASE + 3;
                QuestState.dropChance = 2;
                QuestState.itemFound = false;
                Notice.show("NV: Tìm bí kíp");
                break;
            case QuestAction.REWARD_BURROW:
                Skills.burrow = true;
                QuestState.current = Quest.NONE;
                QuestState.dropMonster = 0;
                QuestState.dropChance = 0;
                QuestState.itemFound = false;
                Notice.show("đã học thuật độn thổ");
                break;
            case QuestAction.START_ESCORT_AYUMI_SISTER:
                QuestState.current = Quest.ESCORT_AYUMI_SISTER;
                Girl.mapId = 3;
                Girl.x = 2808;
                Girl.y = 216;
                Girl.facing = -1;
                Girl.action = PlayerAction.STAND;
                Girl.vx = 5;
                break;
            case QuestAction.REWARD_DOUBLE_STRENGTH:
                Skills.doubleStrength = true;
                PlayerStats.attack <<= 1;
                QuestState.current = Quest.NONE;
                QuestState.dropMonster = 0;
                QuestState.dropChance = 0;
                QuestState.itemFound = false;
                Notice.show("sức công nhân đôi!");
                break;
            case QuestAction.START_KILL_BEES:
                QuestState.current = Quest.KILL_BEES;
                QuestState.progress = 0;
                Notice.show("NV: giết 20 con ong");
                break;
            case QuestAction.START_KILL_WILD_BOAR:
                QuestState.current = Quest.KILL_WILD_BOAR;
                QuestState.itemFound = false;
                QuestState.progress = 0;
                QuestState.dropChance = 10;
                Notice.show("NV: giết heo rừng");
                break;
            case QuestAction.REWARD_WATER_SKILLS:
                QuestState.current = Quest.NONE;
                Skills.dive = true;
                Skills.runOnWater = true;
                // Thưởng gồm cả lặn nước (Skills.dive) nhưng thông báo chỉ ghi chạy nước (giữ như bản gốc).
                Notice.show("đã học chạy nước.");
                break;
            case QuestAction.START_CROSS_RIVER:
                QuestState.current = Quest.CROSS_RIVER;
                QuestState.itemFound = false;
                QuestState.dropChance = 10;
                Notice.show("NV: vượt sông");
                break;
            case QuestAction.REWARD_TRANSFORM:
                QuestState.current = Quest.NONE;
                Skills.transform = true;
                Notice.show("đã học biến hình.");
                break;
            case QuestAction.START_KILL_GOLDEN_BATS:
                QuestState.current = Quest.KILL_GOLDEN_BATS;
                QuestState.progress = 0;
                Notice.show("NV: giết 8 dơi vàng");
                break;
            case QuestAction.REWARD_MONEY:
                Story.goldenBatQuestDone = true;
                Inventory.money += 5000;
                hudDirty = true;
                QuestState.current = Quest.NONE;
                QuestState.dropMonster = 0;
                QuestState.dropChance = 0;
                QuestState.itemFound = false;
                Notice.show("Nhận được tiền 5000$");
                break;
            case QuestAction.START_FIND_HIDE_SCROLL:
                QuestState.current = Quest.FIND_HIDE_SCROLL;
                QuestState.progress = 0;
                QuestState.itemFound = false;
                Notice.show("NV: tìm bí kíp");
                break;
            case QuestAction.REWARD_HIDE:
                Skills.hide = true;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                QuestState.itemFound = false;
                Notice.show("đã có thể ẩn thân");
                break;
            case QuestAction.START_FIGHT_TONIKU:
                QuestState.current = Quest.FIGHT_TONIKU;
                Notice.show("NV: tìm đánh Toniku");
                break;
            case QuestAction.COMPLETE_FIGHT_TONIKU:
                QuestState.current = Quest.NONE;
                Story.tonikuQuestDone = true;
                Notice.show("Hoàn thành nhiệm vụ.");
                break;
            case QuestAction.START_KILL_NATIVES:
                QuestState.current = Quest.KILL_NATIVES;
                QuestState.progress = 0;
                Notice.show("NV: giết 10 thổ dân");
                break;
            case QuestAction.REWARD_MEET_GIRL:
                Story.girlWaiting = true;
                QuestState.current = Quest.NONE;
                QuestState.dropMonster = 0;
                QuestState.dropChance = 0;
                QuestState.itemFound = false;
                Notice.show("đến gặp cô bé!");
                break;
            case QuestAction.START_GIRL_OUTING:
                QuestState.current = Quest.GIRL_OUTING;
                QuestState.progress = 0;
                Girl.following = true;
                Notice.show("NV: dẫn cô bé đi chơi");
                break;
            case QuestAction.REWARD_TRIPLE_JUMP:
                QuestState.current = Quest.NONE;
                Skills.tripleJump = true;
                QuestState.progress = 0;
                Notice.show("Học khinh công cấp 2");
                break;
            case QuestAction.START_AIR_HITS:
                QuestState.current = Quest.AIR_HITS;
                QuestState.progress = 0;
                Notice.show("NV: 20 hit trên không");
                break;
            case QuestAction.REWARD_DARTS_LEVEL_2:
                Skills.dartsLevel2 = true;
                PlayerStats.dartDamage <<= 2;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                Notice.show("đã học phi tiêu cấp 2");
                Effect.loadDarts(Darts.sprites, Effect.DART_LEVEL_2);
                break label58;
            case QuestAction.START_KILL_FLOWER_DEMONS:
                QuestState.current = Quest.KILL_FLOWER_DEMONS;
                QuestState.progress = 0;
                // Mục tiêu thực tế là "Quỷ hoa" (Quest.TARGET_MONSTER = 18), thông báo ghi "quỷ hoa đỏ" (giữ như bản gốc).
                Notice.show("NV: đánh 15 quỷ hoa đỏ");
                break;
            case QuestAction.REWARD_SPRINT:
                Skills.sprint = true;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                Notice.show("đã có thể chạy nhanh");
                break;
            case QuestAction.START_KILL_SCORPIONS:
                QuestState.current = Quest.KILL_SCORPIONS;
                QuestState.progress = 0;
                Notice.show("NV: Giết 18 bò cạp");
                break;
            case QuestAction.REWARD_STAND_ON_WATER:
                Skills.standOnWater = true;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                Notice.show("đã có thể đứng nước");
                break;
            case QuestAction.START_KILL_RED_TIGER_FISH:
                QuestState.current = Quest.KILL_RED_TIGER_FISH;
                QuestState.progress = 0;
                Notice.show("NV: Giết 4 cá đỏ");
                break;
            case QuestAction.COMPLETE_RESCUE_SAKURA:
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                QuestState.itemFound = false;
                break;
            case QuestAction.START_RESCUE_SAKURA:
                QuestState.current = Quest.RESCUE_SAKURA;
                QuestState.progress = 0;
                QuestState.itemFound = false;
                Notice.show("NV: cứu Sakura");
        }

        if (pendingMarkerMap >= 0) {
            setGuideMarker(pendingMarkerMap, pendingMarkerX, pendingMarkerY, pendingMarkerNpc);
            pendingMarkerMap = -1;
        }

    }

    private static int findLaunchTarget() {
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

    private static void releaseMapResources() {
        npcSprites = null;
        System.gc();
        Motorbikes.sprites = null;
        Crates.sprite = null;
        BoarBoss.sprites = null;
        System.gc();
        RiverMonster.sprites = null;
        System.gc();
        Monsters.sprites = null;
        System.gc();
        Ninjas.releaseSprites();
        Classmates.heads = null;
        Dog.sprites = null;
        Projectiles.sprites = null;
        System.gc();
    }

    private static void girlFindTarget() {
        int i;
        for(i = 0; i < Monsters.count; ++i) {
            if (Monsters.state[i] != Monsters.STATE_RESPAWN_WAIT && abs(Monsters.y[i] - Girl.y) < 20 && abs(Monsters.x[i] - Girl.x) < 40) {
                Girl.facing = Monsters.x[i] > Girl.x ? 1 : -1;
                Girl.action = PlayerAction.ATTACK;
                Girl.weapon = 0;
                Girl.actionTimer = 0;
                Girl.actionTimer2 = 0;
                return;
            }
        }

        for(i = 0; i < Ninjas.count; ++i) {
            if (Ninjas.state[i] != Ninjas.STATE_DEAD && Ninjas.y[i] == Girl.y && abs(Ninjas.x[i] - Girl.x) < 40) {
                Girl.facing = Ninjas.x[i] > Girl.x ? 1 : -1;
                Girl.action = PlayerAction.ATTACK;
                Girl.weapon = 0;
                Girl.actionTimer = 0;
                Girl.actionTimer2 = 0;
                return;
            }
        }

        if (abs(Player.x - Girl.x) > 25 && Player.y != Girl.y && Player.action == PlayerAction.ATTACK) {
            Girl.facing = Player.x > Girl.x ? 1 : -1;
            Girl.action = PlayerAction.ATTACK;
            Girl.weapon = 1;
            Girl.actionTimer = 0;
            Girl.actionTimer2 = 0;
        }
    }

    private static void girlFollowPlayer() {
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
            if (Girl.x >= cameraX - 24 && Girl.x <= cameraX + Viewport.viewWidth + 24) {
                if (Girl.y < cameraY - 30 || Girl.y > cameraY + Viewport.viewHeight + 30) {
                    Girl.facing = Player.x + (Player.facing << 5) > Girl.x ? 1 : -1;
                    tx = Player.x;
                    ty = Player.y > Girl.y ? cameraY - 20 : cameraY + Viewport.viewHeight;
                    Girl.x = tx;
                    Girl.y = ty;
                    Girl.action = PlayerAction.JUMP;
                    Girl.vx = Girl.facing << 2;
                    Girl.vy = -4;
                    return;
                }

                if (abs(Player.x + (Player.facing << 5) - Girl.x) <= (Player.action == PlayerAction.ATTACK ? 10 : 60)) {
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
                tx = Player.x > Girl.x ? cameraX - 24 : cameraX + Viewport.viewWidth + 24;
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

    private static void damagePlayer(int amount, int direction) {
        boolean dead = PlayerStats.takeDamage(amount);
        Player.slashEffect = -1;
        hudDirty = true;
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

    private static void damageGirl(int amount, int direction) {
        Girl.slashEffect = -1;
        Girl.vx = direction << 2;
        Girl.vy = -2;
        Girl.actionTimer = 0;
        Girl.actionTimer2 = 0;
        Girl.action = PlayerAction.HURT;
        Blood.spawn(Girl.x, Girl.y - 12, -Girl.facing);
        FloatingText.spawn("-" + amount, 0, Girl.x, Girl.y - Girl.height, 0, -2);
    }

    private static boolean damageNinja(int i, int amount, int direction) {
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
                MoneyDrops.spawn(Ninjas.x[i], Ninjas.y[i], -(Ninjas.direction[i] << 2), -4, NinjaStats.money[Ninjas.type[i]] + tick % 10);
                if (tick % 20 > 5) {
                    PotionDrops.spawn(Ninjas.x[i], Ninjas.y[i], -(Ninjas.direction[i] << 3), -5, NinjaStats.damage[Ninjas.type[i]] << 2, (tick + 1) % 2);
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

    private static boolean damageBoarBoss(int amount, int direction) {
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
                    placeQuestItem(BoarBoss.x, BoarBoss.y);
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
                BoarBoss.targetX = 2 + tick % 5;
            }
        }

        return false;
    }

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

    public static void loadArrowIcons() {
        if (arrowIcons == null) {
            arrowIcons = new Image[4];

            for(int i = 0; i < 4; ++i) {
                try {
                    arrowIcons[i] = Ui.loadArrow(i);
                } catch (Exception e) {
                }
            }
        }

    }

    public static void loadScrollIcon() {
        try {
            itemScroll = Item.loadScroll();
        } catch (Exception e) {
        }
    }

    public static final void setupViewport() {
        Viewport.viewWidth = Viewport.screenWidth;
        if (Viewport.screenHeight >= 208) {
            hudPanelVisible = true;
            hudHeight = 50;
            Viewport.viewHeight = Viewport.screenHeight - hudHeight;
            hudWidth = Viewport.viewWidth;
            hudX = 0;
            hudY = Viewport.viewHeight + 1;
        } else {
            hudHeight = 16;
            Viewport.viewHeight = Viewport.screenHeight - hudHeight;
            hudPanelVisible = false;
            hudHeight = 0;
            hudWidth = Viewport.viewWidth;
            hudX = 0;
            hudY = Viewport.viewHeight + 1;
            hudLevelX = (hudBarMaxWidth = Viewport.viewWidth / 3) + 29;
            hudMoneyX = hudBarMaxWidth + 50;
        }

        if ((letterboxMaxHeight = Viewport.screenHeight / 6) < 48) {
            letterboxMaxHeight = 48;
        }

        Viewport.viewCenterX = Viewport.viewWidth >> 1;
        Viewport.viewCenterY = Viewport.viewHeight >> 1;
        cameraOffsetY = 3 * Viewport.viewHeight / 4;
        cameraLeadX = Viewport.viewWidth / 6;
        visibleCols = Viewport.viewWidth / GameMap.TILE_SIZE + 2;
        visibleRows = Viewport.viewHeight / GameMap.TILE_SIZE + 2;
        if (Viewport.viewWidth % 24 != 0) {
            ++visibleCols;
        }

        cameraMaxX = (GameMap.width - 1) * GameMap.TILE_SIZE - Viewport.viewWidth;
        cameraMaxY = (GameMap.height - 1) * GameMap.TILE_SIZE - Viewport.viewHeight;
        cameraX = cameraTargetX = Player.x - Viewport.viewCenterX + cameraLeadX * Player.facing;
        cameraY = cameraTargetY = Player.y - cameraOffsetY;
        if (cameraX < 0) {
            cameraX = 0;
        }

        if (cameraX > cameraMaxX) {
            cameraX = cameraMaxX;
        }

        if (cameraY < 0) {
            cameraY = 0;
        }

        if (cameraY > cameraMaxY) {
            cameraY = cameraMaxY;
        }

        if ((firstVisibleCol = cameraX / GameMap.TILE_SIZE - 1) < 0) {
            firstVisibleCol = 0;
        }

        firstVisibleRow = cameraY / GameMap.TILE_SIZE;
        lastVisibleCol = firstVisibleCol + visibleCols;
        lastVisibleRow = firstVisibleRow + visibleRows;
        if (firstVisibleRow < 0) {
            firstVisibleRow = 0;
        }

        if (lastVisibleRow > GameMap.height - 1) {
            lastVisibleRow = GameMap.height - 1;
        }

    }

    public static final void loadHeroSprites() {
        Player.slashEffect = -1;
        if (heroBodies == null) {
            try {
                heroBodies = new Image[20];

                int i;
                for(i = 0; i < 20; ++i) {
                    heroBodies[i] = Character.loadHeroBody(i);
                }

                heroHeads = new Image[11];

                for(i = 0; i < 11; ++i) {
                    heroHeads[i] = Character.loadHeroHead(i);
                }

                heroLegs = new Image[10];

                for(i = 0; i < 10; ++i) {
                    heroLegs[i] = Character.loadHeroLegs(i);
                }
            } catch (Exception e) {
            }
        }

        reloadSlashEffects();
        Player.width = 22;
        Player.height = 32;
        Player.halfWidth = Player.width >> 1;
        Player.halfHeight = Player.height >> 1;
        Player.quarterWidth = Player.width >> 2;
        Player.quarterHeight = Player.height >> 2;
        Player.nearNpc = -1;
    }

    private static void reloadSlashEffects() {
        slashEffects = null;
        System.gc();
        slashEffects = Effect.loadSlashes(Inventory.swordLevel);

    }

    public static void setupHud() {
        loadHudIcons();
        if (hudPanelVisible) {
            try {
                if (hudFrames == null) {
                    hudFrames = new Image[2];

                    for(int i = 0; i < 2; ++i) {
                        hudFrames[i] = Ui.loadHud(i);
                    }
                }

                hudLeftFrameWidth = hudFrames[0].getWidth();
                hudRightFrameWidth = hudFrames[1].getWidth();
                hudStripWidth = Viewport.viewWidth - hudLeftFrameWidth - hudRightFrameWidth + 1;
                hudBarX = 78;
                hudBarY = hudY + 7;
                hudBarMaxWidth = Viewport.viewWidth - 134;
                hudExpBarMaxWidth = Viewport.viewWidth - 84;
                hudMpBarHeight = 5;
            } catch (IOException e) {
            }
        }
    }

    private static void loadHudIcons() {
        try {
            if (markerIcon == null) {
                markerIcon = Ui.loadMarker();
            }

            (weaponIcons = new Image[2])[1] = Ui.loadDartIcon();
            weaponIcons[0] = Ui.loadSwordIcon(Inventory.swordLevel);
        } catch (Exception e) {
        }
    }

    private static void changeMap(int targetMap) {
        GameState.current = GameState.LOADING;
        mapId = targetMap;
        Midlet.nextGameState = GameState.PLAYING;
        resetLoadingProgress();
        (new Thread(Midlet.instance)).start();
    }

    private static void startMainMenu() {
        GameState.current = GameState.LOADING;
        resetLoadingProgress();
        Midlet.nextGameState = GameState.MAIN_MENU;
        mapId = 99;

        try {
            titleImage = Ui.loadTitle();
        } catch (Exception e) {
        }

        menuTextTargetY = Viewport.screenHeight - 30;
        menuTextY = Viewport.screenHeight;
        menuCurtainTopLimit = 30;
        menuCurtainBottomLimit = Viewport.screenHeight - 50;
        menuCurtainTop = menuCurtainBottom = menuCurtainTopLimit + menuCurtainBottomLimit >> 1;
        if (Save.isSaved()) {
            mainMenuIndex = 1;
        }

        (new Thread(Midlet.instance)).start();
    }

    public static void applyMapStory(int map) {
        if (map == 94) {
            Classmates.stayPut = true;
            Player.x = 24;
            Player.y = 288;
            Girl.mapId = 94;
            Girl.x = 12;
            Girl.y = 288;
            Girl.facing = 1;
            Girl.following = false;
            Player.facing = 1;
        }

        if (map == 0 && QuestState.current == Quest.FIND_HIDE_SCROLL && QuestState.itemFound) {
            Girl.mapId = -1;
            Girl.following = false;
        }

        if (map == 0 && QuestState.current == Quest.RESCUE_SAKURA && Story.sakuraEscorting) {
            Story.gameCompleted = true;
            Girl.mapId = 0;
            Girl.following = true;
            cameraFollowsPlayer = true;
            QuestState.current = Quest.NONE;
            Player.x = 984;
            Player.y = 408;
        } else {
            if (map == 95 && !Story.sakuraFound) {
                Girl.following = false;
                Girl.x = 599;
                Girl.y = 264;
                Girl.facing = -1;
                Girl.mapId = 95;
            }

        }
    }

    public static void onMapEntered(int map) {
        mapEventActive = false;
        cutscenePlaying = false;
        questItemState = 0;
        if (map == 15 && QuestState.current == Quest.FIND_HIDE_SCROLL) {
            placeQuestItem(216, 432);
        }

        if (map == 8 && QuestState.current == Quest.CROSS_RIVER) {
            placeQuestItem(72, 240);
        }

        int i;
        if (map == 4 && Story.prologueActive) {
            prologueNoRespawn = true;
            Skills.lightness = true;
            Skills.transform = true;
            Skills.runOnWater = true;
            PlayerStats.setMaxHpMp(1000, 2000);
            PlayerStats.attack = 25;
            Player.action = PlayerAction.FALL;
            BoarBoss.maxHp = 500;
            BoarBoss.hp = 500;
            startMapEvent(0);
        } else if (map == 99) {
            Story.prologueActive = true;
            Classmates.stayPut = true;
            Player.x = 108;
            Player.y = 312;
            Player.facing = -1;
            startMapEvent(1);
        } else if (map == 94) {
            startMapEvent(10);
        } else if (map == 0 && Story.prologueActive) {
            prologueNoRespawn = false;
            Skills.lightness = false;
            Skills.runOnWater = false;
            Skills.transform = false;
            Player.transformed = false;
            PlayerStats.resetForNewGame();
            BoarBoss.maxHp = 1000;
            BoarBoss.hp = 1000;
            Player.x = 864;
            Player.y = 336;

            for(i = 0; i < 19; ++i) {
                WorldMap.visited[i] = false;
            }

            WorldMap.visited[0] = true;
            Player.facing = -1;
            Classmates.stayPut = false;
            Classmates.gathering = false;
            startMapEvent(2);
            Story.prologueActive = false;
        } else if (map == 3 && QuestState.current == Quest.ESCORT_AYUMI_SISTER && !Girl.following) {
            startMapEvent(3);
        } else if (map == 0 && QuestState.current == Quest.ESCORT_AYUMI_SISTER && Girl.following) {
            startMapEvent(4);
        } else if (map == 97 && !Story.riverMonsterDefeated) {
            startMapEvent(5);
        } else if (map == 96 && !Story.tonikuDefeated) {
            startMapEvent(6);
        } else if (map == 95 && !Story.sakuraFound) {
            startMapEvent(7);
        } else if (map == 101) {
            startMapEvent(8);
            cameraY = cameraTargetY = 336 - Viewport.viewHeight;
            cameraTargetX = 24;
            cameraX = 24;
        } else if (map == 9 && QuestState.current == Quest.GIRL_OUTING) {
            startMapEvent(9);
        }

        if (map == 97 && Story.riverMonsterDefeated) {
            for(i = 0; i <= 6; ++i) {
                GameMap.clearFlags(24, i);
            }
        }

        if (map == 96 && Story.tonikuDefeated) {
            for(i = 0; i <= 11; ++i) {
                GameMap.clearFlags(24, i);
            }
        }

    }

    private static void tonikuSlashPlayer(int unused) {
        if (Classmates.y[0] == Player.y && abs(Classmates.x[0] + (Classmates.facing[0] << 5) - Player.x) < 25) {
            damagePlayer(Toniku.damage, Classmates.facing[0]);
        }

    }

    private static void classmateHitMonster(int i) {
        int damage = PlayerStats.attack;
        int m = Classmates.targetMonster[i];
        int direction = -Classmates.facing[i];
        boolean killed = false;
        if (Monsters.state[m] == Monsters.STATE_ACTIVE || Monsters.state[m] == Monsters.STATE_RESTING || Monsters.state[m] == Monsters.STATE_ATTACKING) {
            Monsters.hp[m] -= damage;
            if (Monsters.hp[m] <= 0) {
                Monsters.hp[m] = 0;
                Monsters.timer[m] = -5;
                Monsters.aux1[m] = -(direction << 3);
                Monsters.aux2[m] = 0;
                Monsters.healthBarTimer[m] = 0;
                Monsters.state[m] = Monsters.STATE_KNOCKED_OUT;
                killed = true;
            } else {
                Monsters.timer[m] = 0;
                Monsters.direction[m] = direction;
                Monsters.state[m] = Monsters.STATE_HURT;
                Monsters.frame[m] = 3;
            }
        }

        if (!killed) {
            Blood.spawn(Monsters.x[m], Monsters.y[m] - (Monsters.height[m] >> 1), Monsters.direction[m]);
            FloatingText.spawn("-" + damage, 0, Monsters.x[m], Monsters.y[m] - Monsters.height[m], 0, -2);
        }
    }

    private static void classmateFindMonster(int i) {
        for(int m = 0; m < Monsters.count; ++m) {
            if (Monsters.state[m] != Monsters.STATE_RESPAWN_WAIT && Monsters.y[m] == Classmates.y[i] && abs(Classmates.x[i] + (Classmates.facing[i] << 3) - Monsters.x[m]) < 20) {
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

    private static void drawSky(Graphics g) {
        Sky.draw(g, cameraX, Viewport.viewWidth, Viewport.viewHeight, Settings.lowGraphics);
    }

    private static void drawPlayer(Graphics g) {
        if (Player.hidden) {
            if (tick % 20 > 2) {
                Player.pose = 25;
            } else {
                Player.pose = 26;
            }
        }

        if (Player.facing == 1) {
            g.drawImage(heroHeads[heroPoses[Player.pose][0][0]], Player.x + heroPoses[Player.pose][0][1], Player.y - heroPoses[Player.pose][0][2], 0);
            g.drawImage(heroLegs[heroPoses[Player.pose][1][0]], Player.x + heroPoses[Player.pose][1][1], Player.y - heroPoses[Player.pose][1][2], 0);
            g.drawImage(heroBodies[heroPoses[Player.pose][2][0]], Player.x + heroPoses[Player.pose][2][1], Player.y - heroPoses[Player.pose][2][2], 0);
            if (Player.slashEffect != -1) {
                g.drawImage(slashEffects[Player.slashEffect], Player.x - slashOffsetX[Player.slashEffect], Player.y - slashOffsetY[Player.slashEffect], 0);
                return;
            }
        } else {
            g.drawRegion(heroHeads[heroPoses[Player.pose][0][0]], 0, 0, heroHeads[heroPoses[Player.pose][0][0]].getWidth(), heroHeads[heroPoses[Player.pose][0][0]].getHeight(), 2, Player.x - heroPoses[Player.pose][0][1], Player.y - heroPoses[Player.pose][0][2], 24);
            g.drawRegion(heroLegs[heroPoses[Player.pose][1][0]], 0, 0, heroLegs[heroPoses[Player.pose][1][0]].getWidth(), heroLegs[heroPoses[Player.pose][1][0]].getHeight(), 2, Player.x - heroPoses[Player.pose][1][1], Player.y - heroPoses[Player.pose][1][2], 24);
            g.drawRegion(heroBodies[heroPoses[Player.pose][2][0]], 0, 0, heroBodies[heroPoses[Player.pose][2][0]].getWidth(), heroBodies[heroPoses[Player.pose][2][0]].getHeight(), 2, Player.x - heroPoses[Player.pose][2][1], Player.y - heroPoses[Player.pose][2][2], 24);
            if (Player.slashEffect != -1) {
                g.drawRegion(slashEffects[Player.slashEffect], 0, 0, slashEffects[Player.slashEffect].getWidth(), slashEffects[Player.slashEffect].getHeight(), 2, Player.x + slashOffsetX[Player.slashEffect], Player.y - slashOffsetY[Player.slashEffect], 24);
            }
        }

    }

    private static void drawHudOverlay(Graphics g) {
        if (!hudPanelVisible) {
            g.translate(-g.getTranslateX(), -g.getTranslateY());
            g.drawImage(heroHeads[0], 0, 2, 0);
            g.setColor(0);
            g.drawRect(23, 2, hudBarMaxWidth + 1, 4);
            g.drawRect(23, 8, hudBarMaxWidth + 1, 4);
            g.drawRect(23, 14, hudBarMaxWidth + 1, 3);
            g.setColor(16711680);
            int barWidth = PlayerStats.hpBarWidth(hudBarMaxWidth);
            g.fillRect(24, 3, barWidth, 3);
            g.setColor(5570560);
            g.fillRect(barWidth + 24, 3, hudBarMaxWidth - barWidth, 3);
            g.setColor(255);
            barWidth = PlayerStats.mpBarWidth(hudBarMaxWidth);
            g.fillRect(24, 9, barWidth, 3);
            g.setColor(5570560);
            g.fillRect(barWidth + 24, 9, hudBarMaxWidth - barWidth, 3);
            g.setColor(7274435);
            barWidth = Leveling.expBarWidth(hudBarMaxWidth);
            g.fillRect(24, 15, barWidth, 2);
            g.setColor(19506);
            g.fillRect(barWidth + 24, 15, hudBarMaxWidth - barWidth, 2);
            FontGraph.drawSmall(g, "lv:" + PlayerStats.level, 2, hudLevelX, 7, 0);
            FontGraph.drawSmall(g, Inventory.money + "$", 1, hudMoneyX, 7, 0);
        }
    }

    private static void drawHudPanel(Graphics g) {
        hudDirty = false;
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        g.setClip(-1, hudY - 1, hudWidth + 2, 100);
        g.setColor(0);
        if (!hudPanelVisible) {
            g.fillRect(0, hudY, hudWidth, 15);
            FontGraph.drawBold(g, leftSoftKeyLabel, 3, hudY + 3, 0);
            FontGraph.drawBold(g, rightSoftKeyLabel, hudWidth - 3, hudY + 3, 1);
        } else {
            g.fillRect(0, hudY + 33, hudWidth, 15);
            FontGraph.drawBold(g, leftSoftKeyLabel, 3, hudY + 36, 0);
            FontGraph.drawBold(g, rightSoftKeyLabel, hudWidth - 3, hudY + 36, 1);
            g.setColor(4352);
            g.fillRect(hudLeftFrameWidth, hudY, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 35, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 33, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 30, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 28, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 26, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 12, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 24, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 18, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 16, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 2, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 4, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 6, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 14, hudStripWidth, 1);
            g.setColor(15649843);
            g.fillRect(hudLeftFrameWidth, hudY + 1, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 5, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 17, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 34, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 29, hudStripWidth, 1);
            g.setColor(16767871);
            g.fillRect(hudLeftFrameWidth, hudY + 3, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 15, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 27, hudStripWidth, 1);
            g.setColor(3355443);
            g.fillRect(hudLeftFrameWidth, hudY + 7, hudStripWidth, 5);
            g.fillRect(hudLeftFrameWidth, hudY + 19, hudStripWidth, 5);
            g.fillRect(hudLeftFrameWidth, hudY + 31, hudStripWidth, 2);
            g.setColor(12281361);
            g.fillRect(hudLeftFrameWidth, hudY + 25, hudStripWidth, 1);
            g.fillRect(hudLeftFrameWidth, hudY + 13, hudStripWidth, 1);
            g.drawImage(hudFrames[0], 0, hudY, 0);
            g.drawImage(hudFrames[1], 0 + hudWidth, hudY, 24);
            int barWidth = PlayerStats.hpBarWidth(hudBarMaxWidth);
            g.setColor(7798784);
            g.fillRect(hudBarX, hudBarY, barWidth, 2);
            g.setColor(13369344);
            g.fillRect(hudBarX, hudBarY + 1, barWidth, 4);
            barWidth = PlayerStats.mpBarWidth(hudBarMaxWidth);
            g.setColor(119);
            g.fillRect(hudBarX, hudBarY + 12, barWidth, 2);
            g.setColor(204);
            g.fillRect(hudBarX, hudBarY + 14, barWidth, hudMpBarHeight - 2);
            FontGraph.drawSmall(g, "" + Inventory.money, 1, hudWidth - 18, hudY + 6, 2);
            barWidth = Leveling.expBarWidth(hudExpBarMaxWidth);
            g.setColor(94373);
            g.fillRect(31, hudY + 31, barWidth, 1);
            g.setColor(65535);
            g.fillRect(31, hudY + 32, barWidth, 1);
            FontGraph.drawSmall(g, "" + PlayerStats.level, 2, 26, hudY + 11, 2);
            g.drawImage(weaponIcons[Player.weapon], 43, hudY + 16, 3);
            FontGraph.drawSmall(g, "" + (Inventory.potionCounts[0] + Inventory.potionCounts[1] + Inventory.potionCounts[2]), 2, hudWidth - 29, hudY + 19, 2);
            FontGraph.drawSmall(g, "" + (Inventory.potionCounts[3] + Inventory.potionCounts[4] + Inventory.potionCounts[5]), 2, hudWidth - 9, hudY + 19, 2);
        }
    }

    private static void drawLetterbox(Graphics g) {
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        g.setClip(-1, -1, Viewport.screenWidth + 2, Viewport.screenHeight + 2);
        g.setColor(0);
        g.fillRect(0, 0, Viewport.viewWidth, letterboxHeight);
        if (hudPanelVisible) {
            g.fillRect(0, Viewport.screenHeight - letterboxHeight + 1, Viewport.viewWidth, letterboxHeight);
        } else {
            g.fillRect(0, Viewport.screenHeight - 13, Viewport.screenWidth, 13);
        }

        if (captionVisible) {
            for(int i = 0; i < captionLines.length; ++i) {
                FontGraph.drawBold(g, captionLines[i], Viewport.viewCenterX, captionY + (i << 4), 2);
            }
        }

    }

    private static void drawDialogue(Graphics g) {
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        g.setClip(-1, -1, Viewport.screenWidth + 2, Viewport.screenHeight + 2);
        dialogueBoxLines = Dialogue.pages[Dialogue.page].length;
        int boxY = Dialogue.speakerY - cameraY - (dialogueBoxLines << 4);
        boolean boxBelowSpeaker = true;
        if (boxY < 10) {
            boxBelowSpeaker = false;
            boxY = 10;
        }

        g.setColor(16777215);
        if (Settings.lowGraphics) {
            g.fillRect(Dialogue.boxX, boxY - 5, Dialogue.boxWidth, (dialogueBoxLines << 4) + 7);
        } else {
            g.fillRoundRect(Dialogue.boxX, boxY - 5, Dialogue.boxWidth, (dialogueBoxLines << 4) + 7, 10, 10);
        }

        g.setColor(17408);
        if (Settings.lowGraphics) {
            g.drawRect(Dialogue.boxX - 1, boxY - 5, Dialogue.boxWidth + 1, (dialogueBoxLines << 4) + 7);
        } else {
            g.drawRoundRect(Dialogue.boxX - 1, boxY - 5, Dialogue.boxWidth + 1, (dialogueBoxLines << 4) + 7, 10, 10);
        }

        for(int line = 0; line < Dialogue.shownLines; ++line) {
            FontGraph.drawRegular(g, Dialogue.pages[Dialogue.page][line], Dialogue.boxX + (Dialogue.boxWidth >> 1), boxY + (line << 4), 2);
        }

        if (Dialogue.shownLines < Dialogue.pages[Dialogue.page].length) {
            FontGraph.drawRegular(g, Dialogue.pages[Dialogue.page][Dialogue.shownLines].substring(0, Dialogue.shownChars), Dialogue.boxX + (Dialogue.boxWidth >> 1), boxY + (Dialogue.shownLines << 4), 2);
        }

        g.setColor(0);
        g.fillRect(0, hudY + 33, hudWidth, 15);
        if (boxBelowSpeaker && Dialogue.speakerX > cameraX + 10 && Dialogue.speakerX < cameraX + Viewport.viewWidth - 10) {
            g.drawImage(markerIcon, Dialogue.speakerX - cameraX, Dialogue.speakerY - cameraY + 2, 17);
        }

        if (tick % 10 < 8) {
            FontGraph.drawBold(g, "Tiếp tục", Viewport.viewCenterX, Viewport.screenHeight - 13, 2);
        }

    }

    private static void drawClassmates(Graphics g) {
        for(int i = 0; i < Classmates.count; ++i) {
            if (Classmates.x[i] + Player.halfWidth >= cameraX && Classmates.x[i] - Player.halfWidth <= cameraX + Viewport.viewWidth && Classmates.y[i] >= cameraY && Classmates.y[i] - Player.height <= cameraY + Viewport.viewHeight) {
                if (Classmates.facing[i] == 1) {
                    g.drawImage(Classmates.heads[Classmates.skin[i]][classmatePoses[Classmates.pose[i]][0][0]], Classmates.x[i] + classmatePoses[Classmates.pose[i]][0][1], Classmates.y[i] - classmatePoses[Classmates.pose[i]][0][2], 0);
                    g.drawImage(heroLegs[classmatePoses[Classmates.pose[i]][1][0]], Classmates.x[i] + classmatePoses[Classmates.pose[i]][1][1], Classmates.y[i] - classmatePoses[Classmates.pose[i]][1][2], 0);
                    g.drawImage(heroBodies[classmatePoses[Classmates.pose[i]][2][0]], Classmates.x[i] + classmatePoses[Classmates.pose[i]][2][1], Classmates.y[i] - classmatePoses[Classmates.pose[i]][2][2], 0);
                    if (Classmates.slashEffect[i] != -1) {
                        g.drawImage(slashEffects[Classmates.slashEffect[i]], Classmates.x[i] - slashOffsetX[Classmates.slashEffect[i]], Classmates.y[i] - slashOffsetY[Classmates.slashEffect[i]], 0);
                    }
                } else {
                    g.drawRegion(Classmates.heads[Classmates.skin[i]][classmatePoses[Classmates.pose[i]][0][0]], 0, 0, Classmates.heads[Classmates.skin[i]][classmatePoses[Classmates.pose[i]][0][0]].getWidth(), Classmates.heads[Classmates.skin[i]][classmatePoses[Classmates.pose[i]][0][0]].getHeight(), 2, Classmates.x[i] - classmatePoses[Classmates.pose[i]][0][1], Classmates.y[i] - classmatePoses[Classmates.pose[i]][0][2], 24);
                    g.drawRegion(heroLegs[classmatePoses[Classmates.pose[i]][1][0]], 0, 0, heroLegs[classmatePoses[Classmates.pose[i]][1][0]].getWidth(), heroLegs[classmatePoses[Classmates.pose[i]][1][0]].getHeight(), 2, Classmates.x[i] - classmatePoses[Classmates.pose[i]][1][1], Classmates.y[i] - classmatePoses[Classmates.pose[i]][1][2], 24);
                    g.drawRegion(heroBodies[classmatePoses[Classmates.pose[i]][2][0]], 0, 0, heroBodies[classmatePoses[Classmates.pose[i]][2][0]].getWidth(), heroBodies[classmatePoses[Classmates.pose[i]][2][0]].getHeight(), 2, Classmates.x[i] - classmatePoses[Classmates.pose[i]][2][1], Classmates.y[i] - classmatePoses[Classmates.pose[i]][2][2], 24);
                    if (Classmates.slashEffect[i] != -1) {
                        g.drawRegion(slashEffects[Classmates.slashEffect[i]], 0, 0, slashEffects[Classmates.slashEffect[i]].getWidth(), slashEffects[Classmates.slashEffect[i]].getHeight(), 2, Classmates.x[i] + slashOffsetX[Classmates.slashEffect[i]], Classmates.y[i] - slashOffsetY[Classmates.slashEffect[i]], 24);
                    }
                }
            }
        }

    }

    private static void drawTutorial(Graphics g) {
        g.setClip(-1, -1, Viewport.screenWidth + 2, Viewport.screenHeight + 1);
        drawSky(g);
        g.translate(-cameraX, -cameraY);
        if (Settings.lowGraphics) {
            drawTilesLowGraphics(g);
        } else {
            drawTiles(g);
        }

        drawSigns(g);
        drawClassmates(g);
        drawPlayer(g);
        drawNpcs(g);
        drawForegroundTiles(g);
        drawLetterbox(g);
        if (Dialogue.visible) {
            drawDialogue(g);
        }

    }

    private static void boarBossChargeIfPlayerNear() {
        if (!Player.hidden && Player.action != PlayerAction.BURROWED && abs(Player.y - BoarBoss.y) < 30 && abs(Player.x - BoarBoss.x) < 200) {
            BoarBoss.state = BoarBoss.STATE_CHARGE;
            BoarBoss.direction = BoarBoss.x < Player.x ? 1 : -1;
            BoarBoss.targetX = Player.x + (BoarBoss.direction << 5);
            BoarBoss.vx = BoarBoss.direction * 6;
            if (BoarBoss.hp < BoarBoss.maxHp >> 1) {
                BoarBoss.vx = BoarBoss.direction << 3;
            }
        }

    }

    private static void setGuideMarker(int map, int x, int y, int npc) {
        guideMarkerMap = map;
        guideMarkerX = x;
        guideMarkerY = y;
        guideMarkerNpc = npc;
    }

    private static void placeQuestItem(int x, int y) {
        questItemX = x;
        questItemY = y;
        questItemState = 1;
    }

    private static void startCutscene(int id) {
        cutsceneWaitTimer = 0;
        cutsceneId = id;
        cutsceneStep = 0;
        cutscenePlaying = true;
        cutsceneDialogueOpen = false;
        Input.clearHeld();
        Input.clearPressed();
    }

    private static void startDialogue(String[] lines, int finishAction, int speakerX, int speakerY) {
        Dialogue.finishAction = finishAction;
        Dialogue.speakerX = speakerX;
        Dialogue.speakerY = speakerY;
        Dialogue.pageCount = lines.length;
        Dialogue.page = 0;
        Dialogue.pages = new String[Dialogue.pageCount][];
        if ((Dialogue.boxWidth = Viewport.viewWidth >> 1) < 120) {
            Dialogue.boxWidth = 120;
        }

        for(int i = 0; i < Dialogue.pageCount; ++i) {
            Dialogue.pages[i] = FontGraph.wrapRegularText(lines[i], Dialogue.boxWidth - 20);
        }

        if ((Dialogue.boxX = Dialogue.speakerX - (Dialogue.boxWidth >> 1) - cameraX) < 2) {
            Dialogue.boxX = 2;
        }

        if (Dialogue.boxX + Dialogue.boxWidth > Viewport.viewWidth - 2) {
            Dialogue.boxX = Viewport.viewWidth - 2 - Dialogue.boxWidth;
        }

        Dialogue.visible = true;
        Dialogue.shownChars = 0;
        Dialogue.shownLines = 0;
    }

    private static void startMapEvent(int id) {
        mapEventStep = 0;
        mapEventId = id;
        mapEventActive = true;
    }

    private static void shakeCameraIfTransformed() {
        if (Player.transformed) {
            shakeStep = 0;
        }

    }

    private static void updatePlayer() {
        if (cameraFollowsPlayer) {
            cameraTargetX = Player.x - Viewport.viewCenterX + cameraLeadX * Player.facing;
            cameraTargetY = Player.y - cameraOffsetY;
        }

        int i;
        label788:
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
                    if (Classmates.state[i] == Classmates.STATE_IDLE && Player.y == Classmates.y[i] && abs(Player.x - Classmates.x[i]) < 30 && Player.nearClassmate == -1) {
                        rightSoftKeyLabel = "Trò chuyện";
                        hudDirty = true;
                        Player.nearClassmate = i;
                        break;
                    }
                }

                if (Girl.mapId == mapId && Girl.action == PlayerAction.STAND && Player.y == Girl.y && abs(Player.x - Girl.x) < 30) {
                    rightSoftKeyLabel = "Nói chuyện";
                    hudDirty = true;
                    Player.nearGirl = true;
                }

                i = 0;

                while(true) {
                    if (i >= npcCount) {
                        break label788;
                    }

                    if (Player.y == npcY[i] && abs(Player.x - npcX[i]) < 30 && Player.nearNpc == -1) {
                        rightSoftKeyLabel = "Giao tiếp";
                        hudDirty = true;
                        Player.nearNpc = i;
                        break label788;
                    }

                    ++i;
                }
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
                        Player.x = alignToTileX(Player.x + Player.halfWidth) - Player.halfWidth;
                    }
                } else if (GameMap.hasFlag(Player.x - Player.halfWidth - 1, Player.y - Player.halfHeight, TileFlags.WALL_RIGHT_SIDE)) {
                    Player.vx = 0;
                    Player.x = alignToTileX(Player.x - Player.halfWidth - 1) + GameMap.TILE_SIZE + Player.halfWidth;
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
                    if (abs(Player.x - Crates.x[Player.crate]) >= 20) {
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
                    hudDirty = true;
                }

                if (Player.hurtRecovery < 0) {
                    ++Player.hurtRecovery;
                }

                if (Player.facing == 1) {
                    if ((GameMap.flagsAtPixel(Player.x + Player.halfWidth, Player.y) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                        Player.x = alignToTileX(Player.x + Player.halfWidth) - Player.halfWidth;
                        Player.vx = 0;
                    }
                } else if ((GameMap.flagsAtPixel(Player.x - Player.halfWidth, Player.y) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                    Player.x = alignToTileX(Player.x + 24 - Player.halfWidth) + Player.halfWidth;
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
                    Player.y = alignToTileX(Player.y + 3);
                    Dust.spawn(-1, Player.x - -8, Player.y);
                    Dust.spawn(1, Player.x - 8, Player.y);
                    if (Skills.motorbikeLicense) {
                        for(i = 0; i < Motorbikes.count; ++i) {
                            if (Player.y == Motorbikes.y[i] && abs(Player.x - Motorbikes.x[i]) < 16) {
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
                        WaterSplash.spawn(Player.x, alignToTileY(Player.y) + 8);
                        Player.y = alignToTileY(Player.y) + 8;
                        Player.action = PlayerAction.RUN_ON_WATER;
                        Player.vx = Player.facing << 1;
                        Player.vy >>= 2;
                        Player.y = alignToTileY(Player.y) + 12;
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
                            Player.x = alignToTileX(Player.x + Player.halfWidth) - Player.halfWidth;
                            Player.vx = 0;
                        }
                    } else if ((GameMap.flagsAtPixel(Player.x - Player.halfWidth, Player.y) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                        Player.x = alignToTileX(Player.x + 24 - Player.halfWidth) + Player.halfWidth;
                        Player.vx = 0;
                    }

                    landOnCrates();
                    if (BoarBoss.active && Player.vy >= 4 && abs(Player.x - BoarBoss.x) < 30 && abs(Player.y - (BoarBoss.y - 25)) < 5) {
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

                    if (Player.crate != -1 && abs(Player.x - Crates.x[Player.crate]) >= 20) {
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
                        Player.y = alignToTileX(Player.y + 3);
                        if (QuestState.current == Quest.AIR_HITS && QuestState.progress < Quest.TARGET_COUNT[Quest.AIR_HITS]) {
                            QuestState.progress = 0;
                            Notice.show("Hit: " + QuestState.progress);
                        }
                    }

                    if (GameMap.hasFlag(Player.x, Player.y, TileFlags.WATER) && Player.y % GameMap.TILE_SIZE > 8) {
                        WaterSplash.spawn(Player.x, alignToTileY(Player.y) + 8);
                        Player.action = PlayerAction.RUN_ON_WATER;
                        Player.vx = Player.facing << 1;
                        Player.vy >>= 2;
                        Player.y = alignToTileY(Player.y) + 12;
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

                label728:
                switch (Player.comboStep) {
                    case 0:
                        switch (Player.actionTimer) {
                            case 1:
                            case 2:
                            case 3:
                                Player.slashEffect = -1;
                                Player.pose = 13;
                                break label728;
                            case 4:
                                Player.slashEffect = 0;
                                Player.pose = 14;
                                break label728;
                            case 5:
                                Player.slashEffect = 1;
                                Player.pose = 15;
                                break label728;
                            case 6:
                                Player.slashEffect = 1;
                                Player.pose = 15;
                                playerSwordHit();
                                break label728;
                            case 7:
                                Player.slashEffect = 2;
                                Player.pose = 16;
                                break label728;
                            case 8:
                                Player.slashEffect = 2;
                                Player.pose = 16;
                                if (Player.comboQueued) {
                                    Player.comboQueued = false;
                                    Player.comboStep = 2;
                                } else {
                                    Player.comboStep = 1;
                                }

                                Player.actionTimer = 1;
                            default:
                                break label728;
                        }
                    case 1:
                        switch (Player.actionTimer) {
                            case 1:
                                Player.slashEffect = 1;
                                Player.pose = 15;
                                break label728;
                            case 2:
                                Player.slashEffect = 1;
                                Player.pose = 15;
                                Player.comboStep = 10;
                            default:
                                break label728;
                        }
                    case 2:
                        switch (Player.actionTimer) {
                            case 1:
                            case 2:
                            case 3:
                                Player.slashEffect = 3;
                                Player.pose = 17;
                                break label728;
                            case 4:
                                Player.slashEffect = 4;
                                Player.pose = 18;
                                break label728;
                            case 5:
                                Player.slashEffect = 5;
                                Player.pose = 19;
                                break label728;
                            case 6:
                                Player.slashEffect = 6;
                                Player.pose = 20;
                                playerSwordHit();
                                break label728;
                            case 7:
                                Player.slashEffect = 6;
                                Player.pose = 20;
                                if (Player.comboQueued && Player.comboCount < 2) {
                                    if (Player.vy == 0 && Player.launchHit) {
                                        Player.actionTimer = 0;
                                        Player.comboQueued = false;
                                        Player.comboStep = 9;
                                    } else {
                                        Player.comboQueued = false;
                                        Player.comboStep = 3;
                                        ++Player.comboCount;
                                    }
                                } else {
                                    Player.comboStep = 10;
                                }

                                Player.actionTimer = 1;
                            default:
                                break label728;
                        }
                    case 3:
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
                                    Player.comboStep = 2;
                                } else {
                                    Player.comboStep = 1;
                                }

                                Player.actionTimer = 1;
                        }
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    default:
                        break;
                    case 9:
                        switch (Player.actionTimer) {
                            case 1:
                            case 2:
                            case 3:
                                Player.slashEffect = -1;
                                Player.pose = 13;
                                break label728;
                            case 4:
                                Player.slashEffect = 0;
                                Player.pose = 14;
                                break label728;
                            case 5:
                            case 6:
                                Player.slashEffect = 9;
                                Player.pose = 15;
                                i = findLaunchTarget();
                                Player.launchHit = false;
                                if (i != -1) {
                                    Player.launchHit = true;
                                    Blood.spawn(Monsters.x[i], Monsters.y[i] - (Monsters.height[i] >> 1), Player.facing);
                                    Monsters.state[i] = Monsters.STATE_LAUNCHED;
                                    Monsters.timer[i] = -8;
                                    shakeCameraIfTransformed();
                                }
                                break label728;
                            case 7:
                            case 8:
                                Player.slashEffect = 10;
                                Player.pose = 16;
                                break label728;
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
                                    Player.comboStep = 10;
                                }
                            default:
                                break label728;
                        }
                    case 10:
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
                if (Player.y >= GameMap.pixelHeight || Player.y > cameraY + Viewport.viewHeight + Player.height || GameMap.hasFlag(Player.x, Player.y - Player.height, TileFlags.DEEP_WATER)) {
                    Player.x = Player.checkpointX;
                    Player.y = Player.checkpointY;
                    Player.action = PlayerAction.FALL;
                    PlayerStats.revive();
                    Player.vy = 0;
                    Player.vx = 0;
                    Player.actionTimer2 = 0;
                    Player.actionTimer = 0;
                    Leveling.applyDeathPenalty();
                    hudDirty = true;
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
                if (Player.vy < 20 && tick % 2 == 0) {
                    ++Player.vy;
                }

                Player.pose = 7;
                if (Player.y >= GameMap.pixelHeight) {
                    PlayerStats.kill();
                    Player.action = PlayerAction.DEAD;
                    Player.slashEffect = -1;
                } else if (GameMap.hasFlag(Player.x, Player.y, TileFlags.SOLID)) {
                    Player.y = alignToTileY(Player.y);
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

                    Player.y = alignToTileX(Player.y);
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
                if (tick % 20 > 2) {
                    Player.pose = 25;
                } else {
                    Player.pose = 26;
                }

                if (tick % 8 == 0) {
                    if (PlayerStats.drainMp(1)) {
                        Player.action = PlayerAction.BURROW_EXIT;
                    }

                    hudDirty = true;
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
                        Player.x = alignToTileX(Player.x + Player.halfWidth) - Player.halfWidth;
                    }
                } else if (GameMap.hasFlag(Player.x - Player.halfWidth - 1, Player.y - Player.halfHeight, TileFlags.WALL_RIGHT_SIDE)) {
                    Player.vx = 0;
                    Player.x = alignToTileX(Player.x - Player.halfWidth - 1) + GameMap.TILE_SIZE + Player.halfWidth;
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
                if (tick % 8 > 3) {
                    Player.pose = 43;
                } else {
                    Player.pose = 44;
                    Dust.spawn(Player.facing, Player.x - (Player.facing << 4), Player.y - 5);
                }

                Player.actionTimer = 0;
                break;
            case PlayerAction.MOTORBIKE_RIDE:
                if (tick % 8 > 3) {
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
                        Player.x = alignToTileX(Player.x + Player.halfWidth) - Player.halfWidth;
                    }
                } else if (GameMap.hasFlag(Player.x - Player.halfWidth - 1, Player.y - Player.halfHeight, TileFlags.WALL_RIGHT_SIDE)) {
                    Player.vx = 0;
                    Player.x = alignToTileX(Player.x - Player.halfWidth - 1) + GameMap.TILE_SIZE + Player.halfWidth;
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
                    if (abs(Player.x - Crates.x[Player.crate]) >= 20) {
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
                if ((i = findLaunchTarget()) != -1 && (Monsters.state[i] == Monsters.STATE_ACTIVE || Monsters.state[i] == Monsters.STATE_RESTING)) {
                    damageMonster(i, 2, -Player.facing);
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

                    if (tick % 8 > 3) {
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
                            Player.y = alignToTileX(Player.y);
                        } else {
                            Player.action = PlayerAction.MOTORBIKE_JUMP;
                            Player.vy = -(Player.vy / 3);
                            Player.y = alignToTileX(Player.y);
                        }
                    }

                    if (GameMap.hasFlag(Player.x, Player.y, TileFlags.WATER) && Player.y % GameMap.TILE_SIZE > 8) {
                        WaterSplash.spawn(Player.x, alignToTileY(Player.y) + 8);
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
                    hudDirty = true;
                }

                if (Player.hurtRecovery < 0) {
                    ++Player.hurtRecovery;
                }

                if (tick % 8 > 3) {
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

        if (tick % 3 == 0) {
            if (Player.hidden) {
                if (PlayerStats.drainMp(1)) {
                    Player.hidden = false;
                }

                hudDirty = true;
            }

            if (Player.transformed) {
                if (PlayerStats.drainMp(1)) {
                    Player.transformed = false;

                    for(int k = 0; k < 4; ++k) {
                        Image swap = heroHeads[k];
                        heroHeads[k] = heroTransformHeads[k];
                        heroTransformHeads[k] = swap;
                    }
                }

                hudDirty = true;
            }
        }

    }

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

    private static void updateTransform() {
        if (++Player.actionTimer == 1) {
            Aura.show(Player.x, Player.y - 40);
            shakeStep = 0;
            if (heroTransformHeads == null) {
                heroTransformHeads = new Image[4];

                try {
                    heroTransformHeads[0] = Character.loadHeroTransformHead(0);
                    heroTransformHeads[1] = Character.loadHeroTransformHead(1);
                } catch (Exception e) {
                }

                heroTransformHeads[2] = heroTransformHeads[1];
                heroTransformHeads[3] = heroTransformHeads[0];
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
                    swap = heroHeads[i];
                    heroHeads[i] = heroTransformHeads[i];
                    heroTransformHeads[i] = swap;
                }

                Player.transformed = !Player.transformed;
            } else if (Player.actionTimer <= 10) {
                if (Player.actionTimer % 5 == 0) {
                    for(i = 0; i < 4; ++i) {
                        swap = heroHeads[i];
                        heroHeads[i] = heroTransformHeads[i];
                        heroTransformHeads[i] = swap;
                    }
                }
            } else if (Player.actionTimer < 20) {
                if (Player.actionTimer % 3 == 0) {
                    for(i = 0; i < 4; ++i) {
                        swap = heroHeads[i];
                        heroHeads[i] = heroTransformHeads[i];
                        heroTransformHeads[i] = swap;
                    }
                }
            } else if (Player.actionTimer < 30 && Player.actionTimer % 2 == 0) {
                for(i = 0; i < 4; ++i) {
                    swap = heroHeads[i];
                    heroHeads[i] = heroTransformHeads[i];
                    heroTransformHeads[i] = swap;
                }
            }
        }

        if (Player.actionTimer % 2 > 0) {
            Player.pose = 0;
        } else {
            Player.pose = 1;
        }
    }

    private static void updateRunOnWater() {
        if (++Player.actionTimer >= 5) {
            Player.actionTimer = 0;
            Player.sprintBonus = 0;
        }

        Player.pose = Player.actionTimer + 2;
        if (Player.facing == 1) {
            if (GameMap.hasFlag(Player.x + Player.halfWidth, Player.y - 1, TileFlags.WALL_LEFT_SIDE)) {
                Player.vx = 0;
                Player.x = alignToTileX(Player.x + Player.halfWidth) - Player.halfWidth;
            }
        } else if (GameMap.hasFlag(Player.x - Player.halfWidth - 1, Player.y - 1, TileFlags.WALL_RIGHT_SIDE)) {
            Player.vx = 0;
            Player.x = alignToTileX(Player.x - Player.halfWidth - 1) + GameMap.TILE_SIZE + Player.halfWidth;
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

        if (tick % 8 == 0) {
            if (PlayerStats.drainMp(3)) {
                Player.vx = 0;
                Player.action = PlayerAction.SINK;
            }

            hudDirty = true;
        }

        for(int i = 0; i < Crates.count; ++i) {
            if (abs(Player.x - Crates.x[i]) < 20 && Crates.y[i] > Player.y && Crates.y[i] - 24 < Player.y) {
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

    private static boolean updateCutscene() {
        if (!cutscenePlaying) {
            if (letterboxHeight > 1) {
                letterboxHeight >>= 1;
                hudDirty = true;
            }

            return false;
        } else {
            if (cutsceneDialogueOpen) {
                if (Input.pressed[Input.KEY_FIRE]) {
                    Input.clearPressed();
                    Dialogue.advance();
                    if (Dialogue.isFinished()) {
                        Dialogue.visible = false;
                        cutsceneDialogueOpen = false;
                        onDialogueFinished();
                    }
                }
            } else {
                if (letterboxHeight < letterboxMaxHeight) {
                    letterboxHeight += (letterboxMaxHeight - letterboxHeight >> 1) + 1;
                }

                if (captionVisible) {
                    if (captionY < captionTargetY) {
                        captionY += 2;
                    } else {
                        captionY = captionTargetY;
                    }
                }

                if (++cutsceneWaitTimer >= Cutscene.triggerFrames[cutsceneId][cutsceneStep]) {
                    cutsceneWaitTimer = Cutscene.triggerFrames[cutsceneId][cutsceneStep];
                    int speakerX;
                    switch (Cutscene.commands[cutsceneId][cutsceneStep]) {
                        case Cutscene.CMD_KEY_DOWN:
                            Input.keyDown(Cutscene.args[cutsceneId][cutsceneStep]);
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_KEY_UP:
                            Input.keyUp(Cutscene.args[cutsceneId][cutsceneStep]);
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_END:
                            cutscenePlaying = false;
                            hudDirty = true;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_SAY:
                            if (Player.action == PlayerAction.STAND) {
                                captionVisible = false;
                                speakerX = 0;
                                int speakerY = 0;
                                switch (Cutscene.lineSpeakerKind[Cutscene.args[cutsceneId][cutsceneStep]]) {
                                    case Cutscene.SPEAKER_PLAYER:
                                        speakerX = Player.x;
                                        speakerY = Player.y;
                                        break;
                                    case Cutscene.SPEAKER_NPC:
                                        speakerX = npcX[Cutscene.lineSpeakerIndex[Cutscene.args[cutsceneId][cutsceneStep]]];
                                        speakerY = npcY[Cutscene.lineSpeakerIndex[Cutscene.args[cutsceneId][cutsceneStep]]];
                                        break;
                                    case Cutscene.SPEAKER_CLASSMATE:
                                        speakerX = Classmates.x[Cutscene.lineSpeakerIndex[Cutscene.args[cutsceneId][cutsceneStep]]];
                                        speakerY = Classmates.y[Cutscene.lineSpeakerIndex[Cutscene.args[cutsceneId][cutsceneStep]]];
                                        break;
                                    case Cutscene.SPEAKER_GIRL:
                                    case Cutscene.SPEAKER_GIRL_2:
                                        speakerX = Girl.x;
                                        speakerY = Girl.y;
                                }

                                speakerY -= 40;
                                startDialogue(DialogueText.cutsceneLines[Cutscene.args[cutsceneId][cutsceneStep]], Dialogue.NO_ACTION, speakerX, speakerY);
                                ++cutsceneStep;
                                cutsceneDialogueOpen = true;
                            }
                            break;
                        case Cutscene.CMD_SHOW_GUIDE_MARKER:
                            setGuideMarker(mapId, Cutscene.args[cutsceneId][cutsceneStep], Player.y - 50, -1);
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_HIDE_GUIDE_MARKER:
                            guideMarkerMap = -1;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CAMERA_FOLLOW_PLAYER:
                            cameraFollowsPlayer = true;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CAMERA_FREE:
                            cameraFollowsPlayer = false;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CAMERA_JUMP:
                            cameraX = Cutscene.cameraPoints[Cutscene.args[cutsceneId][cutsceneStep]][0] - Viewport.viewCenterX;
                            cameraY = Cutscene.cameraPoints[Cutscene.args[cutsceneId][cutsceneStep]][1] - Viewport.viewCenterY;
                            if (cameraX < 0) {
                                cameraX = 0;
                            }

                            if (cameraX > cameraMaxX) {
                                cameraX = cameraMaxX;
                            }

                            if (cameraY < 0) {
                                cameraY = 0;
                            }

                            if (cameraY > cameraMaxY) {
                                cameraY = cameraMaxY;
                            }

                            cameraTargetX = cameraX;
                            cameraTargetY = cameraY;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CAMERA_PAN:
                            cameraTargetX = Cutscene.cameraPoints[Cutscene.args[cutsceneId][cutsceneStep]][0] - Viewport.viewCenterX;
                            cameraTargetY = Cutscene.cameraPoints[Cutscene.args[cutsceneId][cutsceneStep]][1] - Viewport.viewCenterY;
                            if (cameraTargetX < 0) {
                                cameraTargetX = 0;
                            }

                            if (cameraTargetX > cameraMaxX) {
                                cameraTargetX = cameraMaxX;
                            }

                            if (cameraTargetY < 0) {
                                cameraTargetY = 0;
                            }

                            if (cameraTargetY > cameraMaxY) {
                                cameraTargetY = cameraMaxY;
                            }

                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CAMERA_PAN_TO_BOAR_BOSS:
                            cameraTargetX = BoarBoss.x - Viewport.viewCenterX;
                            cameraTargetY = BoarBoss.y - Viewport.viewCenterY;
                            if (cameraTargetX < 0) {
                                cameraTargetX = 0;
                            }

                            if (cameraTargetX > cameraMaxX) {
                                cameraTargetX = cameraMaxX;
                            }

                            if (cameraTargetY < 0) {
                                cameraTargetY = 0;
                            }

                            if (cameraTargetY > cameraMaxY) {
                                cameraTargetY = cameraMaxY;
                            }

                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CAMERA_SCROLL_DOWN:
                            cameraTargetY = cameraY += 2;
                            if (cameraY > Cutscene.cameraPoints[Cutscene.args[cutsceneId][cutsceneStep]][1] - Viewport.viewCenterY) {
                                ++cutsceneStep;
                            }
                        case Cutscene.CMD_WAIT:
                        default:
                            break;
                        case Cutscene.CMD_CAMERA_SCROLL_LEFT:
                            cameraTargetX = cameraX -= 2;
                            if (cameraX < Cutscene.cameraPoints[Cutscene.args[cutsceneId][cutsceneStep]][0] - Viewport.viewCenterX) {
                                ++cutsceneStep;
                            }
                            break;
                        case Cutscene.CMD_CAPTION:
                            captionVisible = true;
                            captionLines = FontGraph.wrapBoldText(DialogueText.cutsceneCaptions[Cutscene.args[cutsceneId][cutsceneStep]], Viewport.viewWidth - 40);
                            if ((captionTargetY = (letterboxMaxHeight >> 1) - (captionLines.length << 3)) < 3) {
                                captionTargetY = 2;
                            }

                            captionY = -captionLines.length << 3;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CLASSMATE_SCENE:
                            Classmates.gathering = true;
                            Classmates.gatherX = Cutscene.args[cutsceneId][cutsceneStep];
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CHANGE_MAP:
                            Player.checkpointX = Player.x = MapExits.rightSpawnX;
                            Player.checkpointY = Player.y = MapExits.rightSpawnY;
                            releaseMapResources();
                            Save.pendingSave = true;
                            changeMap(Cutscene.args[cutsceneId][cutsceneStep]);
                            return true;
                        case Cutscene.CMD_NEXT_NPC_MARKER:
                            setNextNpcMarker(Cutscene.args[cutsceneId][cutsceneStep]);
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_GIRL_FOLLOW:
                            Girl.following = true;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_GIRL_STAY:
                            Girl.following = false;
                            Girl.mapId = 0;
                            Girl.x = 1080;
                            Girl.y = 408;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_REWARD_MOTORBIKE_LICENSE:
                            QuestState.current = Quest.NONE;
                            // Thưởng nhiệm vụ ESCORT_AYUMI_SISTER được trao trong cutscene, không qua onDialogueFinished.
                            Skills.motorbikeLicense = true;
                            Notice.show("nhận bằng lái môtô");
                            setGuideMarker(0, npcX[4], npcY[4] - 40, 4);
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_RIVER_MONSTER_DEFEATED:
                            for(int row = 0; row <= 6; ++row) {
                                GameMap.clearFlags(24, row);
                            }

                            Story.riverMonsterDefeated = true;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CAMERA_PAN_TO_CLASSMATE:
                            cameraFollowsPlayer = false;
                            cameraTargetX = Classmates.x[Cutscene.args[cutsceneId][cutsceneStep]] - Viewport.viewCenterX;
                            cameraTargetY = Classmates.y[Cutscene.args[cutsceneId][cutsceneStep]] - Viewport.viewCenterY;
                            if (cameraTargetX < 0) {
                                cameraTargetX = 0;
                            }

                            if (cameraTargetX > cameraMaxX) {
                                cameraTargetX = cameraMaxX;
                            }

                            if (cameraTargetY < 0) {
                                cameraTargetY = 0;
                            }

                            if (cameraTargetY > cameraMaxY) {
                                cameraTargetY = cameraMaxY;
                            }

                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CLASSMATE_SCENE_2:
                            Classmates.gathering = true;
                            Classmates.gatherX = Cutscene.args[cutsceneId][cutsceneStep];
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_STOP_FIRST_CLASSMATE:
                            Toniku.pacified = true;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_TONIKU_DEFEATED:
                            for(int row = 0; row <= 10; ++row) {
                                GameMap.clearFlags(24, row);
                            }

                            Story.tonikuDefeated = true;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CAMERA_PAN_TO_FINAL_BOSS:
                            cameraFollowsPlayer = false;
                            cameraTargetX = FinalBoss.x - Viewport.viewCenterX;
                            cameraTargetY = FinalBoss.y - Viewport.viewCenterY;
                            if (cameraTargetX < 0) {
                                cameraTargetX = 0;
                            }

                            if (cameraTargetX > cameraMaxX) {
                                cameraTargetX = cameraMaxX;
                            }

                            if (cameraTargetY < 0) {
                                cameraTargetY = 0;
                            }

                            if (cameraTargetY > cameraMaxY) {
                                cameraTargetY = cameraMaxY;
                            }

                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_START_FINAL_BOSS:
                            FinalBoss.state = FinalBoss.STATE_IDLE;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_END_GAME:
                            Input.clearPressed();
                            Input.clearHeld();
                            tilesetImage = null;
                            waterfallImage = null;
                            waterSurfaceImage = null;
                            houseImages = null;
                            GameMap.tiles = null;
                            GameMap.flags = null;
                            System.gc();
                            npcSprites = null;
                            System.gc();
                            Motorbikes.sprites = null;
                            Crates.sprite = null;
                            MoneyDrops.sprites = null;
                            BoarBoss.sprites = null;
                            System.gc();
                            RiverMonster.sprites = null;
                            System.gc();
                            Monsters.sprites = null;
                            System.gc();
                            Ninjas.releaseSprites();
                            Classmates.heads = null;
                            Dog.sprites = null;
                            Projectiles.sprites = null;
                            Dust.sprites = null;
                            Darts.sprites = null;
                            Aura.sprites = null;
                            Blood.sprites = null;
                            WaterSplash.sprites = null;
                            arrowIcons = null;
                            System.gc();
                            heroLegs = null;
                            heroBodies = null;
                            heroHeads = null;
                            heroTransformHeads = null;
                            slashEffects = null;
                            System.gc();
                            startMainMenu();
                            return true;
                        case Cutscene.CMD_GIRL_OUTING_DONE:
                            QuestState.current = Quest.NONE;
                            Story.girlWaiting = false;
                            Story.girlOutingDone = true;
                            Girl.mapId = 0;
                            Girl.x = 648;
                            Girl.y = 288;
                            Girl.following = false;
                            Notice.show("gặp cô Ayumi");
                            setGuideMarker(0, 1032, 368, 4);
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CAMERA_PAN_TO_GIRL:
                            cameraFollowsPlayer = false;
                            cameraTargetX = Girl.x - Viewport.viewCenterX;
                            cameraTargetY = Girl.y - Viewport.viewCenterY;
                            if (cameraTargetX < 0) {
                                cameraTargetX = 0;
                            }

                            if (cameraTargetX > cameraMaxX) {
                                cameraTargetX = cameraMaxX;
                            }

                            if (cameraTargetY < 0) {
                                cameraTargetY = 0;
                            }

                            if (cameraTargetY > cameraMaxY) {
                                cameraTargetY = cameraMaxY;
                            }

                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_GIRL_RUN:
                            Girl.following = true;
                            Girl.action = PlayerAction.RUN;
                            Girl.vx = -5;
                            Girl.facing = -1;
                            Girl.runTimer = 8;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_START_SAKURA_ESCORT:
                            Story.sakuraEscorting = true;
                            Girl.following = true;
                            ++cutsceneStep;
                    }
                }
            }

            Input.held[Input.KEY_FIRE] = false;
            Input.pressed[Input.KEY_FIRE] = false;
            return false;
        }
    }

    private static void updateClassmates() {
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
                            if ((offset = (tick + Classmates.wanderRange[i]) % 120 - 60) > 0) {
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
                                rightSoftKeyLabel = " ";
                                hudDirty = true;
                            }
                        }

                        if (tick % 10 == 1) {
                            classmateFindMonster(i);
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
                    if (Classmates.x[i] <= Classmates.maxX[i] && Classmates.x[i] >= Classmates.minX[i] && abs(Classmates.x[i] - Classmates.target[i]) >= 8) {
                        if (tick % 10 == 0) {
                            classmateFindMonster(i);
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
                        case 0:
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
                                    classmateHitMonster(i);
                                    continue;
                                case 7:
                                    Classmates.slashEffect[i] = 2;
                                    Classmates.pose[i] = 16;
                                    continue;
                                case 8:
                                    Classmates.slashEffect[i] = 2;
                                    Classmates.pose[i] = 16;
                                    Classmates.target[i] = 2;
                                    Classmates.animTimer[i] = 1;
                                default:
                                    continue;
                            }
                        case 1:
                            switch (Classmates.animTimer[i]) {
                                case 1:
                                    Classmates.slashEffect[i] = 1;
                                    Classmates.pose[i] = 15;
                                    continue;
                                case 2:
                                    Classmates.slashEffect[i] = 1;
                                    Classmates.pose[i] = 15;
                                    Classmates.target[i] = 10;
                                default:
                                    continue;
                            }
                        case 2:
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
                                    classmateHitMonster(i);
                                    continue;
                                case 7:
                                    Classmates.slashEffect[i] = 6;
                                    Classmates.pose[i] = 20;
                                    Classmates.target[i] = 3;
                                    Classmates.animTimer[i] = 1;
                                default:
                                    continue;
                            }
                        case 3:
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
                                    Classmates.target[i] = 1;
                                    Classmates.animTimer[i] = 1;
                            }
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        default:
                            break;
                        case 10:
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

    private static void updateTutorial() {
        if (++tick == 10000) {
            tick = 0;
        }

        updateMapEvent();
        if (!updateCutscene()) {
            updateClassmates();
            updatePlayer();
            Dialogue.typeTick();
        }
    }

    private static void updateNinjas() {
        for(int i = 0; i < Ninjas.count; ++i) {
            if (Ninjas.healthBarTimer[i] > 0) {
                Ninjas.healthBarTimer[i]--;
            }

            label143:
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
                    if (Ninjas.y[i] <= cameraY + Viewport.viewHeight + 32 && Ninjas.y[i] < GameMap.pixelHeight) {
                        continue;
                    }

                    Ninjas.timer[i] = 0;
                    Ninjas.targetX[i] = 0;
                    Ninjas.state[i] = Ninjas.STATE_DEAD;
                    break;
                case Ninjas.STATE_WALK:
                    if (tick % 12 > 7) {
                        Ninjas.bob[i] = 1;
                        Ninjas.legsFrame[i] = 1;
                        if (tick % 12 < 10) {
                            if (abs(Ninjas.x[i] - Ninjas.targetX[i]) < 4) {
                                Ninjas.timer[i] = Ninjas.targetX[i] = 0;
                                Ninjas.state[i] = Ninjas.STATE_IDLE;
                                break;
                            }

                            Ninjas.x[i] += Ninjas.vx[i];
                        }

                        ninjaReactToPlayer(i);
                    } else {
                        Ninjas.legsFrame[i] = 0;
                        Ninjas.bob[i] = 0;
                    }
                    break;
                case Ninjas.STATE_IDLE:
                    if ((tick + i) % 10 > 5) {
                        Ninjas.bob[i] = 1;
                    } else {
                        Ninjas.bob[i] = 0;
                    }

                    Ninjas.timer[i]++;
                    Ninjas.legsFrame[i] = 0;
                    Ninjas.bodyFrame[i] = 0;
                    if (Ninjas.timer[i] > 50) {
                        int offset;
                        if ((offset = (tick - i) % (i + 80) - 40) > 0) {
                            offset += 20;
                        } else {
                            offset -= 20;
                        }

                        Ninjas.targetX[i] = Ninjas.x[i] + offset;
                        Ninjas.direction[i] = offset > 0 ? 1 : -1;
                        if (tick % 4 > 1) {
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

                    if (tick % 30 == 28) {
                        ninjaReactToPlayer(i);
                        ninjaMeleeIfPlayerClose(i);
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
                            case 0:
                            case 4:
                                Projectiles.spawn(Ninjas.x[i] + (Ninjas.direction[i] << 3), Ninjas.y[i] - 15, Ninjas.direction[i] * 12, NinjaStats.damage[Ninjas.type[i]], 0);
                                break label143;
                            case 1:
                            case 5:
                                Projectiles.spawn(Ninjas.x[i] + (Ninjas.direction[i] << 3), Ninjas.y[i] - 15, Ninjas.direction[i] << 3, NinjaStats.damage[Ninjas.type[i]], 1);
                                break label143;
                            case 2:
                            case 3:
                            case 6:
                                if (abs(Player.y - Ninjas.y[i]) < 5 && abs(Player.x - Ninjas.x[i]) < 30) {
                                    damagePlayer(NinjaStats.damage[Ninjas.type[i]], Ninjas.direction[i]);
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
                    if ((tick + i) % 10 > 5) {
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
                    if (abs(Ninjas.x[i] - Ninjas.targetX[i]) < 12) {
                        Ninjas.timer[i] = Ninjas.targetX[i] = 0;
                        Ninjas.state[i] = Ninjas.STATE_IDLE;
                        ninjaMeleeIfPlayerClose(i);
                    } else {
                        Ninjas.x[i] += Ninjas.vx[i];
                        if (tick % 2 == 1) {
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

    private static void updateFinalBoss() {
        if (FinalBoss.active) {
            int phase = tick % 10;
            int dx = abs(Player.x - FinalBoss.x);
            int dy = abs(Player.y - FinalBoss.y);
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
                        shakeStep = 0;
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
                        damagePlayer(FinalBoss.damage, FinalBoss.direction);
                    }
                    break;
                case FinalBoss.STATE_DYING:
                    ++FinalBoss.timer;

                    for(int p = 0; p < FinalBoss.partBaseY.length; ++p) {
                        FinalBoss.partBaseY[p] += abs(p - 3) + 2;
                        FinalBoss.partBaseX[p] += p - 3 << 1;
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
                        Projectiles.spawn(FinalBoss.x + (FinalBoss.direction << 3), FinalBoss.y - 24, FinalBoss.direction << 3, FinalBoss.damage, 3);
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

            for(int p = 0; p < 6; ++p) {
                if (FinalBoss.direction == 1) {
                    FinalBoss.partX[p] = FinalBoss.x + FinalBoss.partBaseX[p] + FinalBoss.poseX[p];
                } else {
                    FinalBoss.partX[p] = FinalBoss.x - FinalBoss.partBaseX[p] - FinalBoss.poseX[p];
                }

                FinalBoss.partY[p] = FinalBoss.y + FinalBoss.partBaseY[p] + FinalBoss.poseY[p];
            }

        }
    }

    private static void updateMapEvent() {
        if (mapEventActive) {
            switch (MapEvent.conditions[mapEventId][mapEventStep]) {
                case MapEvent.WAIT_PLAYER_PAST_X:
                    if (Player.x > MapEvent.paramX[mapEventId][mapEventStep]) {
                    startCutscene(MapEvent.cutscenes[mapEventId][mapEventStep]);
                    ++mapEventStep;
                    return;
                }
                break;
                case MapEvent.IMMEDIATE:
                    startCutscene(MapEvent.cutscenes[mapEventId][mapEventStep]);
                    ++mapEventStep;
                    return;
                case MapEvent.END:
                default:
                    return;
                case MapEvent.WAIT_PLAYER_OFFSCREEN_RIGHT:
                    if (Player.x > cameraX + Viewport.viewWidth) {
                        startCutscene(MapEvent.cutscenes[mapEventId][mapEventStep]);
                        ++mapEventStep;
                        return;
                    }
                    break;
                case MapEvent.WAIT_PLAYER_AT:
                    if (Player.x < MapEvent.paramX[mapEventId][mapEventStep] && Player.x > MapEvent.paramX[mapEventId][mapEventStep] - 96 && Player.y == MapEvent.paramY[mapEventId][mapEventStep]) {
                        startCutscene(MapEvent.cutscenes[mapEventId][mapEventStep]);
                        ++mapEventStep;
                    }

                    // Bản gốc thiếu lệnh này nên rơi xuống WAIT_BOAR_BOSS_DEFEATED.
                    return;
                case MapEvent.WAIT_BOAR_BOSS_DEFEATED:
                    if (BoarBoss.state == BoarBoss.STATE_GONE) {
                        startCutscene(MapEvent.cutscenes[mapEventId][mapEventStep]);
                        ++mapEventStep;
                        return;
                    }
                    break;
                case MapEvent.WAIT_RIVER_MONSTER_SUNK:
                    if (RiverMonster.state == RiverMonster.STATE_SUNK) {
                        startCutscene(MapEvent.cutscenes[mapEventId][mapEventStep]);
                        ++mapEventStep;
                        return;
                    }
                    break;
                case MapEvent.WAIT_TONIKU_DEFEATED:
                    if (Toniku.hp < 10) {
                        startCutscene(MapEvent.cutscenes[mapEventId][mapEventStep]);
                        ++mapEventStep;
                        return;
                    }
                    break;
                case MapEvent.WAIT_FINAL_BOSS_DEFEATED:
                    if (FinalBoss.state == FinalBoss.STATE_DEFEATED) {
                        startCutscene(MapEvent.cutscenes[mapEventId][mapEventStep]);
                        Story.sakuraFound = true;
                        ++mapEventStep;
                        return;
                    }
            }
        } else if (mapEventId == 10) {
            Display.getDisplay(Midlet.instance).setCurrent(Midlet.canvas);
            mapEventId = 0;
        }

    }

    public static void start() {
        Midlet.canvas = new GameCanvas();
        updateMapEvent();
        GameCanvas canvas = Midlet.canvas;
        Save.setBackgroundIndex((Save.backgroundIndex = Save.getBackgroundIndex()) == 0 ? 1 : 0);
        LogoScreen.load();
        GameState.current = GameState.LOGO;
        (new Thread(canvas)).start();
    }

    public GameCanvas() {
        this.setFullScreenMode(true);
        System.gc();
        instance = this;
        Input.motorolaKeys = this.getKeyCode(Canvas.FIRE) == Input.MOTOROLA_CODE_FIRE;
        Viewport.screenWidth = this.getWidth();
        Viewport.screenHeight = this.getHeight();
        Viewport.screenCenterX = Viewport.screenWidth / 2;
        Viewport.screenCenterY = Viewport.screenHeight / 2;
        Input.clearHeld();
    }

    protected final void keyPressed(int keyCode) {
        int cheat = CheatCodes.onKeyPressed(keyCode);
        if (cheat != CheatCodes.RESULT_NOT_TRIGGERED) {
            if (cheat == CheatCodes.RESULT_GAIN_EXP) {
                gainExp(CheatCodes.EXP_BONUS);
            }

            hudDirty = true;
        }

        keyCode = Input.normalizeKeyCode(keyCode);
        if (!cutscenePlaying || keyCode == Input.CODE_FIRE) {
            Input.keyDown(keyCode);
        }
    }

    protected final void keyReleased(int keyCode) {
        keyCode = Input.normalizeKeyCode(keyCode);
        if (!cutscenePlaying || keyCode == Input.CODE_FIRE) {
            Input.keyUp(keyCode);
        }
    }

    protected final void paint(Graphics g) {
        try {
            switch (GameState.current) {
                case GameState.PLAYING:
                    g.setClip(-1, -1, Viewport.viewWidth + 2, Viewport.viewHeight + 2);
                    drawSky(g);
                    g.translate(-cameraX, -cameraY);

                    int i;
                    for(i = 0; i < houseCount; ++i) {
                        if (houseX[i] + houseHalfWidth[i] >= cameraX && houseX[i] - houseHalfWidth[i] <= cameraX + Viewport.viewWidth && houseY[i] >= cameraY && houseY[i] - houseHeight[i] <= cameraY + Viewport.viewHeight) {
                            parallaxFromCenter = (parallaxScreenX = houseX[i] - cameraX) - Viewport.viewCenterX;
                            parallaxDrawX = (parallaxScaled = (GameMap.TILE_SIZE - 2) * parallaxFromCenter / GameMap.TILE_SIZE) + Viewport.viewCenterX;
                            g.drawImage(houseImages[houseTypes[i]], cameraX + parallaxDrawX, houseY[i], 33);
                        }
                    }

                    if (Settings.lowGraphics) {
                        drawTilesLowGraphics(g);
                    } else {
                        drawTiles(g);
                    }

                    drawSigns(g);
                    drawNpcs(g);
                    if (RiverMonster.active) {
                        if (RiverMonster.direction < 0) {
                            g.drawImage(RiverMonster.sprites[0], RiverMonster.x, RiverMonster.y, 33);
                            g.drawImage(RiverMonster.sprites[2], RiverMonster.part2X, RiverMonster.part2Y, 33);
                            g.drawImage(RiverMonster.sprites[3], RiverMonster.part3X, RiverMonster.part3Y, 33);
                            g.drawImage(RiverMonster.sprites[1], RiverMonster.part1X, RiverMonster.part1Y, 33);
                        } else {
                            g.drawRegion(RiverMonster.sprites[0], 0, 0, RiverMonster.sprites[0].getWidth(), RiverMonster.sprites[0].getHeight(), 2, RiverMonster.x, RiverMonster.y, 33);
                            g.drawRegion(RiverMonster.sprites[2], 0, 0, RiverMonster.sprites[2].getWidth(), RiverMonster.sprites[2].getHeight(), 2, RiverMonster.part2X, RiverMonster.part2Y, 33);
                            g.drawRegion(RiverMonster.sprites[3], 0, 0, RiverMonster.sprites[3].getWidth(), RiverMonster.sprites[3].getHeight(), 2, RiverMonster.part3X, RiverMonster.part3Y, 33);
                            g.drawRegion(RiverMonster.sprites[1], 0, 0, RiverMonster.sprites[1].getWidth(), RiverMonster.sprites[1].getHeight(), 2, RiverMonster.part1X, RiverMonster.part1Y, 33);
                        }

                        if (RiverMonster.healthBarTimer != 0) {
                            g.setColor(3342353);
                            g.drawRect(RiverMonster.x - (RiverMonster.width >> 1) - 1, RiverMonster.y - RiverMonster.height - 7, RiverMonster.width + 2, 4);
                            int riverBarWidth = RiverMonster.hp * RiverMonster.width / RiverMonster.maxHp;
                            g.setColor(15597568);
                            g.fillRect(RiverMonster.x - (RiverMonster.width >> 1), RiverMonster.y - RiverMonster.height - 6, riverBarWidth, 3);
                            g.setColor(4456482);
                            g.fillRect(RiverMonster.x - (RiverMonster.width >> 1) + riverBarWidth, RiverMonster.y - RiverMonster.height - 6, RiverMonster.width - riverBarWidth + 1, 3);
                        }
                    }


                    for(i = 0; i < Crates.count; ++i) {
                        if (cameraX <= Crates.x[i] + 12 && cameraX + Viewport.viewWidth >= Crates.x[i] - 12 && cameraY <= Crates.y[i] && cameraY + Viewport.viewHeight >= Crates.y[i] - 24) {
                            g.drawImage(Crates.sprite, Crates.x[i], Crates.y[i], 33);
                        }
                    }


                    for(i = 0; i < Motorbikes.count; ++i) {
                        if (cameraX <= Motorbikes.x[i] + 12 && cameraX + Viewport.viewWidth >= Motorbikes.x[i] - 12 && cameraY <= Motorbikes.y[i] && cameraY + Viewport.viewHeight >= Motorbikes.y[i] - 24) {
                            if (Motorbikes.facing[i] == 1) {
                                g.drawImage(Motorbikes.sprites[Motorbikes.frame[i]], Motorbikes.x[i], Motorbikes.y[i], 33);
                            } else {
                                g.drawRegion(Motorbikes.sprites[Motorbikes.frame[i]], 0, 0, Motorbikes.sprites[Motorbikes.frame[i]].getWidth(), Motorbikes.sprites[Motorbikes.frame[i]].getHeight(), 2, Motorbikes.x[i], Motorbikes.y[i], 33);
                            }
                        }
                    }


                    MoneyDrops.draw(g, cameraX, cameraY);
                    PotionDrops.draw(g, cameraX, cameraY);

                    int barWidth;
                    if (BoarBoss.active && BoarBoss.state != BoarBoss.STATE_GONE && cameraX + Viewport.viewWidth >= BoarBoss.x - (BoarBoss.width >> 1) && cameraX <= BoarBoss.x + (BoarBoss.width >> 1) && cameraY <= BoarBoss.y && cameraY + Viewport.viewHeight >= BoarBoss.y - BoarBoss.height) {
                        byte bob = 0;
                        if (BoarBoss.frame == 0) {
                            bob = -2;
                            if (BoarBoss.state == BoarBoss.STATE_IDLE && tick % 10 > 5) {
                                bob = -1;
                            }

                            if (BoarBoss.direction == 1) {
                                g.drawImage(BoarBoss.sprites[4], BoarBoss.x, BoarBoss.y + 1, 33);
                            } else {
                                g.drawRegion(BoarBoss.sprites[4], 0, 0, BoarBoss.sprites[4].getWidth(), BoarBoss.sprites[4].getHeight(), 2, BoarBoss.x, BoarBoss.y + 1, 33);
                            }
                        }

                        if (BoarBoss.direction == 1) {
                            g.drawImage(BoarBoss.sprites[BoarBoss.frame], BoarBoss.x, BoarBoss.y + bob + 1, 33);
                        } else {
                            g.drawRegion(BoarBoss.sprites[BoarBoss.frame], 0, 0, BoarBoss.sprites[BoarBoss.frame].getWidth(), BoarBoss.sprites[BoarBoss.frame].getHeight(), 2, BoarBoss.x, BoarBoss.y + bob + 1, 33);
                        }

                        if (BoarBoss.state == BoarBoss.STATE_HURT) {
                            if (BoarBoss.direction == 1) {
                                g.drawImage(BoarBoss.sprites[3], BoarBoss.x + 10, BoarBoss.y - 25 + 1, 0);
                            } else {
                                g.drawRegion(BoarBoss.sprites[3], 0, 0, BoarBoss.sprites[3].getWidth(), BoarBoss.sprites[3].getHeight(), 2, BoarBoss.x - 10, BoarBoss.y - 25 + 1, 24);
                            }
                        }

                        if (BoarBoss.hurtFlash != 0) {
                            g.setColor(3342353);
                            g.drawRect(BoarBoss.x - (BoarBoss.width >> 1) - 1, BoarBoss.y - BoarBoss.height - 7, BoarBoss.width + 2, 4);
                            barWidth = BoarBoss.hp * BoarBoss.width / BoarBoss.maxHp;
                            g.setColor(15597568);
                            g.fillRect(BoarBoss.x - (BoarBoss.width >> 1), BoarBoss.y - BoarBoss.height - 6, barWidth, 3);
                            g.setColor(4456482);
                            g.fillRect(BoarBoss.x - (BoarBoss.width >> 1) + barWidth, BoarBoss.y - BoarBoss.height - 6, BoarBoss.width - barWidth + 1, 3);
                        }
                    }

                    if (FinalBoss.active) {
                        if (FinalBoss.direction > 0) {
                            for(i = 0; i < 6; ++i) {
                                g.drawImage(FinalBoss.sprites[i], FinalBoss.partX[i], FinalBoss.partY[i], 33);
                            }
                        } else {
                            for(i = 0; i < 6; ++i) {
                                g.drawRegion(FinalBoss.sprites[i], 0, 0, FinalBoss.sprites[i].getWidth(), FinalBoss.sprites[i].getHeight(), 2, FinalBoss.partX[i], FinalBoss.partY[i], 33);
                            }
                        }

                        if (FinalBoss.healthBarTimer != 0) {
                            g.setColor(3342353);
                            g.drawRect(FinalBoss.x - (FinalBoss.width >> 1) - 1, FinalBoss.y - FinalBoss.height - 7, FinalBoss.width + 2, 4);
                            int bossBarWidth = FinalBoss.hp * FinalBoss.width / FinalBoss.maxHp;
                            g.setColor(15597568);
                            g.fillRect(FinalBoss.x - (FinalBoss.width >> 1), FinalBoss.y - FinalBoss.height - 6, bossBarWidth, 3);
                            g.setColor(4456482);
                            g.fillRect(FinalBoss.x - (FinalBoss.width >> 1) + bossBarWidth, FinalBoss.y - FinalBoss.height - 6, FinalBoss.width - bossBarWidth + 1, 3);
                        }
                    }


                    for(i = 0; i < Monsters.count; ++i) {
                        if (Monsters.state[i] != Monsters.STATE_RESPAWN_WAIT && Monsters.state[i] != Monsters.STATE_GONE && Monsters.x[i] >= cameraX - 10 && Monsters.x[i] <= cameraX + Viewport.viewWidth + 10 && Monsters.y[i] >= cameraY && Monsters.y[i] <= cameraY + Viewport.viewHeight + 20) {
                            g.drawImage(Monsters.sprites[Monsters.group[i]][Monsters.direction[i] >= 0 ? Mob.MONSTER_DIR_RIGHT : Mob.MONSTER_DIR_LEFT][Monsters.frame[i]], Monsters.x[i], Monsters.y[i], 33);
                            if (Monsters.healthBarTimer[i] != 0) {
                                g.setColor(13073);
                                g.drawRect(Monsters.x[i] - (Monsters.width[i] >> 1) - 1, Monsters.y[i] - Monsters.height[i] - 7, Monsters.width[i] + 2, 4);
                                barWidth = Monsters.hp[i] * Monsters.width[i] / Monsters.maxHp[i];
                                g.setColor(60928);
                                g.fillRect(Monsters.x[i] - (Monsters.width[i] >> 1), Monsters.y[i] - Monsters.height[i] - 6, barWidth, 3);
                                g.setColor(17442);
                                g.fillRect(Monsters.x[i] - (Monsters.width[i] >> 1) + barWidth, Monsters.y[i] - Monsters.height[i] - 6, Monsters.width[i] - barWidth + 1, 3);
                            }
                        }
                    }


                    for(i = 0; i < Ninjas.count; ++i) {
                        if (Ninjas.state[i] != Ninjas.STATE_DEAD && Ninjas.x[i] + 15 >= cameraX && Ninjas.x[i] - 15 <= cameraX + Viewport.viewWidth && Ninjas.y[i] >= cameraY && Ninjas.y[i] - 30 <= cameraY + Viewport.viewHeight) {
                            if (Ninjas.direction[i] == -1) {
                                g.drawImage(Ninjas.legSprites[Ninjas.legsFrame[i]], Ninjas.x[i] + NinjaStats.legsOffsetX[Ninjas.type[i]], Ninjas.y[i], 33);
                                g.drawImage(Ninjas.bodySprites[Ninjas.type[i]][Ninjas.bodyFrame[i]], Ninjas.bodyX[i] + NinjaStats.bodyOffsetX[Ninjas.type[i]][Ninjas.bodyFrame[i]], Ninjas.bodyY[i], 33);
                            } else {
                                g.drawRegion(Ninjas.legSprites[Ninjas.legsFrame[i]], 0, 0, Ninjas.legSprites[Ninjas.legsFrame[i]].getWidth(), Ninjas.legSprites[Ninjas.legsFrame[i]].getHeight(), 2, Ninjas.x[i] - NinjaStats.legsOffsetX[Ninjas.type[i]], Ninjas.y[i], 33);
                                g.drawRegion(Ninjas.bodySprites[Ninjas.type[i]][Ninjas.bodyFrame[i]], 0, 0, Ninjas.bodySprites[Ninjas.type[i]][Ninjas.bodyFrame[i]].getWidth(), Ninjas.bodySprites[Ninjas.type[i]][Ninjas.bodyFrame[i]].getHeight(), 2, Ninjas.bodyX[i] - NinjaStats.bodyOffsetX[Ninjas.type[i]][Ninjas.bodyFrame[i]], Ninjas.bodyY[i], 33);
                            }

                            if (Ninjas.healthBarTimer[i] != 0) {
                                g.setColor(13073);
                                g.drawRect(Ninjas.x[i] - 13, Ninjas.y[i] - 40, 26, 4);
                                barWidth = Ninjas.hp[i] * 24 / NinjaStats.maxHp[Ninjas.type[i]];
                                g.setColor(15597568);
                                g.fillRect(Ninjas.x[i] - 12, Ninjas.y[i] - 39, barWidth, 3);
                                g.setColor(4456482);
                                g.fillRect(Ninjas.x[i] - 12 + barWidth, Ninjas.y[i] - 39, 24 - barWidth + 1, 3);
                            }
                        }
                    }

                    drawClassmates(g);
                    if (Dog.visible && Dog.x + 15 >= cameraX && Dog.x - 15 <= cameraX + Viewport.viewWidth && Dog.y >= cameraY && Dog.y - 10 <= cameraY + Viewport.viewHeight) {
                    int dogFrame = tick % 8 > 3 ? 0 : 1;
                    if (Dog.vx > 0) {
                        g.drawImage(Dog.sprites[dogFrame], Dog.x, Dog.y, 33);
                    } else {
                        g.drawRegion(Dog.sprites[i], 0, 0, Dog.sprites[i].getWidth(), Dog.sprites[i].getHeight(), 2, Dog.x, Dog.y, 33);
                    }
                }

                if (Toniku.active) {
                    if (Toniku.healthBarVisible) {
                        g.setColor(16768477);
                        int barX = cameraX + 50;
                        int barY = cameraY + 15;
                        if (!hudPanelVisible) {
                            barY += 20;
                        }

                        g.drawRect(barX - 35 - 1, barY - 7, 72, 4);
                        int tonikuBarWidth = Toniku.hp * 70 / Toniku.maxHp;
                        g.setColor(15597568);
                        g.fillRect(barX - 35, barY - 6, tonikuBarWidth, 3);
                        g.setColor(4456482);
                        g.fillRect(barX - 35 + tonikuBarWidth, barY - 6, 70 - tonikuBarWidth + 1, 3);
                    }

                    if (Classmates.x[0] + Player.halfWidth >= cameraX && Classmates.x[0] - Player.halfWidth <= cameraX + Viewport.viewWidth && Classmates.y[0] >= cameraY && Classmates.y[0] - Player.height <= cameraY + Viewport.viewHeight) {
                        if (Classmates.facing[0] == 1) {
                            g.drawImage(Classmates.heads[Classmates.skin[0]][classmatePoses[Classmates.pose[0]][0][0]], Classmates.x[0] + classmatePoses[Classmates.pose[0]][0][1], Classmates.y[0] - classmatePoses[Classmates.pose[0]][0][2], 0);
                            g.drawImage(heroLegs[classmatePoses[Classmates.pose[0]][1][0]], Classmates.x[0] + classmatePoses[Classmates.pose[0]][1][1], Classmates.y[0] - classmatePoses[Classmates.pose[0]][1][2], 0);
                            g.drawImage(heroBodies[classmatePoses[Classmates.pose[0]][2][0]], Classmates.x[0] + classmatePoses[Classmates.pose[0]][2][1], Classmates.y[0] - classmatePoses[Classmates.pose[0]][2][2], 0);
                            if (Classmates.slashEffect[0] != -1) {
                                g.drawImage(slashEffects[Classmates.slashEffect[0]], Classmates.x[0] - slashOffsetX[Classmates.slashEffect[0]], Classmates.y[0] - slashOffsetY[Classmates.slashEffect[0]], 0);
                            }
                        } else {
                            g.drawRegion(Classmates.heads[Classmates.skin[0]][classmatePoses[Classmates.pose[0]][0][0]], 0, 0, Classmates.heads[Classmates.skin[0]][classmatePoses[Classmates.pose[0]][0][0]].getWidth(), Classmates.heads[Classmates.skin[0]][classmatePoses[Classmates.pose[0]][0][0]].getHeight(), 2, Classmates.x[0] - classmatePoses[Classmates.pose[0]][0][1], Classmates.y[0] - classmatePoses[Classmates.pose[0]][0][2], 24);
                            g.drawRegion(heroLegs[classmatePoses[Classmates.pose[0]][1][0]], 0, 0, heroLegs[classmatePoses[Classmates.pose[0]][1][0]].getWidth(), heroLegs[classmatePoses[Classmates.pose[0]][1][0]].getHeight(), 2, Classmates.x[0] - classmatePoses[Classmates.pose[0]][1][1], Classmates.y[0] - classmatePoses[Classmates.pose[0]][1][2], 24);
                            g.drawRegion(heroBodies[classmatePoses[Classmates.pose[0]][2][0]], 0, 0, heroBodies[classmatePoses[Classmates.pose[0]][2][0]].getWidth(), heroBodies[classmatePoses[Classmates.pose[0]][2][0]].getHeight(), 2, Classmates.x[0] - classmatePoses[Classmates.pose[0]][2][1], Classmates.y[0] - classmatePoses[Classmates.pose[0]][2][2], 24);
                            if (Classmates.slashEffect[0] != -1) {
                                g.drawRegion(slashEffects[Classmates.slashEffect[0]], 0, 0, slashEffects[Classmates.slashEffect[0]].getWidth(), slashEffects[Classmates.slashEffect[0]].getHeight(), 2, Classmates.x[0] + slashOffsetX[Classmates.slashEffect[0]], Classmates.y[0] - slashOffsetY[Classmates.slashEffect[0]], 24);
                            }
                        }
                    }
                }

                Aura.draw(g);

                if (Girl.mapId == mapId) {
                    if (Girl.facing == 1) {
                        g.drawImage(Girl.heads[girlPoses[Girl.pose][0][0]], Girl.x + girlPoses[Girl.pose][0][1], Girl.y - girlPoses[Girl.pose][0][2], 0);
                        g.drawImage(Girl.legs[girlPoses[Girl.pose][1][0]], Girl.x + girlPoses[Girl.pose][1][1], Girl.y - girlPoses[Girl.pose][1][2], 0);
                        g.drawImage(Girl.bodies[girlPoses[Girl.pose][2][0]], Girl.x + girlPoses[Girl.pose][2][1], Girl.y - girlPoses[Girl.pose][2][2], 0);
                        if (Girl.slashEffect != -1) {
                            g.drawImage(slashEffects[Girl.slashEffect], Girl.x - slashOffsetX[Girl.slashEffect], Girl.y - slashOffsetY[Girl.slashEffect], 0);
                        }
                    } else {
                        g.drawRegion(Girl.heads[girlPoses[Girl.pose][0][0]], 0, 0, Girl.heads[girlPoses[Girl.pose][0][0]].getWidth(), Girl.heads[girlPoses[Girl.pose][0][0]].getHeight(), 2, Girl.x - girlPoses[Girl.pose][0][1], Girl.y - girlPoses[Girl.pose][0][2], 24);
                        g.drawRegion(Girl.legs[girlPoses[Girl.pose][1][0]], 0, 0, Girl.legs[girlPoses[Girl.pose][1][0]].getWidth(), Girl.legs[girlPoses[Girl.pose][1][0]].getHeight(), 2, Girl.x - girlPoses[Girl.pose][1][1], Girl.y - girlPoses[Girl.pose][1][2], 24);
                        g.drawRegion(Girl.bodies[girlPoses[Girl.pose][2][0]], 0, 0, Girl.bodies[girlPoses[Girl.pose][2][0]].getWidth(), Girl.bodies[girlPoses[Girl.pose][2][0]].getHeight(), 2, Girl.x - girlPoses[Girl.pose][2][1], Girl.y - girlPoses[Girl.pose][2][2], 24);
                        if (Girl.slashEffect != -1) {
                            g.drawRegion(slashEffects[Girl.slashEffect], 0, 0, slashEffects[Girl.slashEffect].getWidth(), slashEffects[Girl.slashEffect].getHeight(), 2, Girl.x + slashOffsetX[Girl.slashEffect], Girl.y - slashOffsetY[Girl.slashEffect], 24);
                        }
                    }
                }

                drawPlayer(g);
                if (questItemState != 0) {
                    g.drawImage(itemScroll, questItemX, questItemY, 33);
                }

                Blood.draw(g);
                Dust.draw(g);

                Projectiles.draw(g);

                for(i = 0; i < Darts.count; ++i) {
                    if (Darts.state[i] != Darts.STATE_FREE) {
                        g.drawImage(Darts.sprites[Darts.frame[i]], Darts.x[i], Darts.y[i], 3);
                    }
                }


                ArcProjectiles.draw(g);
                WaterSplash.draw(g);
                drawForegroundTiles(g);
                FloatingText.draw(g);
                if (guideMarkerMap == mapId) {
                    g.drawImage(arrowIcons[guideArrowIcon], guideArrowX, guideArrowY, 3);
                }

                drawHudOverlay(g);
                if (hudDirty) {
                    drawHudPanel(g);
                }

                if (letterboxHeight > 1) {
                    drawLetterbox(g);
                }

                if (Notice.visible) {
                    Notice.draw(g, tick);
                }

                if (Dialogue.visible) {
                    drawDialogue(g);
                }

                return;
                case GameState.LOADING:
                    g.setColor(0);
                    g.fillRect(0, 0, Viewport.screenWidth, 21);
                    FontGraph.drawBold(g, "Loading... " + loadingProgress + "%", Viewport.screenWidth >> 1, 4, 2);
                    return;
                case GameState.MAIN_MENU:
                    g.setClip(-1, -1, Viewport.screenWidth + 2, Viewport.screenHeight + 1);
                    drawSky(g);
                    g.translate(-cameraX, -cameraY);
                    if (Settings.lowGraphics) {
                        drawTilesLowGraphics(g);
                    } else {
                        drawTiles(g);
                    }

                    drawSigns(g);
                    drawForegroundTiles(g);
                    g.translate(-g.getTranslateX(), -g.getTranslateY());
                    g.setColor(0);
                    g.fillRect(0, 0, Viewport.viewWidth, menuCurtainTop);
                    g.fillRect(0, menuCurtainBottom, Viewport.viewWidth, Viewport.screenHeight - menuCurtainBottom + 1);
                    g.setColor(10275899);
                    g.fillRect(0, menuCurtainTop - 1, Viewport.screenWidth, 1);
                    g.fillRect(0, menuCurtainBottom + 1, Viewport.screenWidth, 1);
                    g.drawImage(titleImage, Viewport.screenCenterX, menuCurtainTopLimit + menuCurtainBottom >> 1, 3);
                    FontGraph.drawBold(g, "*", Viewport.screenCenterX - 50 - tick % 3, menuTextY, 0);
                    FontGraph.drawBold(g, "/", Viewport.screenCenterX + 50 + tick % 3, menuTextY, 1);
                    FontGraph.drawBold(g, GameText.mainMenuItems[mainMenuIndex], Viewport.screenCenterX, menuTextY, 3);
                    return;
                case GameState.LOGO:
                    LogoScreen.draw(g);
                    return;
                case GameState.GAME_MENU:
                    GameMenu.draw(g, weaponIcons[0]);
                    return;
                case GameState.WORLD_MAP:
                    WorldMap.draw(g, heroHeads[0], weaponIcons[0]);
                    return;
                case GameState.MESSAGE:
                    MessageBox.draw(g);
                    return;
                case GameState.INVENTORY:
                    drawInventory(g);
                    return;
                case GameState.SHOP:
                    drawShop(g);
                    return;
                case GameState.SETUP_PROMPT:
                    SetupPrompt.draw(g);
                    return;
                case GameState.TUTORIAL:
                    drawTutorial(g);
                    return;
                case GameState.UNUSED_12:
                    drawTutorial(g);
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected final void pointerDragged(int x, int y) {
        Input.pointerX = x;
        Input.pointerY = y;
    }

    protected final void pointerPressed(int x, int y) {
        Input.pointerX = x;
        Input.pointerY = y;
    }

    protected final void pointerReleased(int x, int y) {
        Input.pointerReleased = true;
        Input.pointerX = x;
        Input.pointerY = y;
    }

    public final void run() {
        while(running) {
            long frameStart = System.currentTimeMillis();
            switch (GameState.current) {
                case GameState.PLAYING:
                    if (++tick == 10000) {
                        tick = 0;
                    }

                    if (Player.x < 0) {
                        if (MapExits.leftMap != MapExits.NONE) {
                            releaseMapResources();
                            Player.checkpointX = Player.x = MapExits.leftSpawnX - 1;
                            Player.checkpointY = Player.y = MapExits.leftSpawnY;
                            Save.pendingSave = true;
                            changeMap(MapExits.leftMap);
                            break;
                        }
                    } else if (Player.x >= GameMap.pixelWidth - 24) {
                        if (MapExits.rightMap != MapExits.NONE) {
                            if (!Skills.hide && MapExits.rightMap == 95) {
                                MessageBox.show(MessageType.LEVEL_UP);
                                MessageBox.setText("Khu vực phía trước rất nguy hiểm! Chỉ những người có thể ẩn thân có thể vào!");
                                Player.x = GameMap.pixelWidth - 60;
                                Player.action = PlayerAction.FALL;
                                GameState.current = GameState.MESSAGE;
                                break;
                            }

                            releaseMapResources();
                            if (MapExits.rightLowerMap != MapExits.NONE && Player.y > MapExits.rightLowerMinY) {
                                Player.checkpointX = Player.x = MapExits.rightLowerSpawnX;
                                Player.checkpointY = Player.y = MapExits.rightLowerSpawnY;
                                Save.pendingSave = true;
                                changeMap(MapExits.rightLowerMap);
                                break;
                            }

                            Player.checkpointX = Player.x = MapExits.rightSpawnX;
                            Player.checkpointY = Player.y = MapExits.rightSpawnY;
                            Save.pendingSave = true;
                            changeMap(MapExits.rightMap);
                            break;
                        }
                    } else if (Player.y + Player.vy >= GameMap.pixelHeight && Player.action != PlayerAction.DEAD && MapExits.bottomMap != MapExits.NONE) {
                        releaseMapResources();
                        Player.checkpointX = Player.x = MapExits.bottomSpawnX;
                        Player.checkpointY = Player.y = MapExits.bottomSpawnY;
                        Save.pendingSave = true;
                        changeMap(MapExits.bottomMap);
                        Player.action = PlayerAction.FALL;
                        break;
                    }

                    updateMapEvent();
                    Dialogue.typeTick();
                    if (updateCutscene()) {
                        break;
                    }

                    int i;
                    if (!Settings.lowGraphics) {
                        Sky.updateClouds(tick, Viewport.viewWidth);
                    }

                    int j;
                    if (Dialogue.visible) {
                        if (Input.pressed[Input.KEY_FIRE]) {
                            Input.clearPressed();
                            Dialogue.advance();
                            if (Dialogue.isFinished()) {
                                Dialogue.visible = false;
                                onDialogueFinished();
                            }
                        }

                        hudDirty = true;
                    } else {
                        label2614: {
                            if (Input.pressed[Input.KEY_SOFT_LEFT]) {
                                tilesetImage = null;
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

                                hudDirty = true;
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
                                    break label2614;
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

                                                hudDirty = true;
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

                                                hudDirty = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }

                            if (Player.action == PlayerAction.STAND) {
                                if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
                                    Input.clearPressed();
                                    if (rightSoftKeyLabel.equals("Giao tiếp")) {
                                        j = Player.nearNpc;
                                        switch (npcTypes[j]) {
                                            case 0:
                                                if (Story.sakuraEscorting) {
                                                    startDialogue(DialogueText.idleLines[7], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                } else if (!Skills.lightness) {
                                                    setNextNpcMarker(2);
                                                    startDialogue(DialogueText.questGiveLines[0], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                } else {
                                                    if (Skills.hide && !Story.gameCompleted) {
                                                        if (QuestState.current == Quest.RESCUE_SAKURA) {
                                                            if (Story.sakuraFound) {
                                                                startDialogue(DialogueText.questRewardLines[14], QuestAction.COMPLETE_RESCUE_SAKURA, npcX[j], npcY[j] - 40);
                                                            } else {
                                                                startDialogue(DialogueText.questReminderLines[14], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                            }
                                                        } else if (QuestState.current == Quest.NONE) {
                                                            startDialogue(DialogueText.questGiveLines[22], QuestAction.START_RESCUE_SAKURA, npcX[j], npcY[j] - 40);
                                                        } else {
                                                            startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[j], npcY[j] - 40);
                                                        }
                                                        break;
                                                    }

                                                    startDialogue(DialogueText.idleLines[1], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                }
                                                break;
                                            case 1:
                                                if (Story.sakuraEscorting) {
                                                    startDialogue(DialogueText.idleLines[5], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                } else if (!Skills.lightness) {
                                                    if (QuestState.current == Quest.KILL_SNAILS) {
                                                        if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_SNAILS]) {
                                                            startDialogue(DialogueText.questReminderLines[0], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                        } else {
                                                            pendingMarkerMap = 0;
                                                            pendingMarkerNpc = 4;
                                                            pendingMarkerX = 1044;
                                                            pendingMarkerY = 240;
                                                            startDialogue(DialogueText.questRewardLines[0], QuestAction.REWARD_LIGHTNESS, npcX[j], npcY[j] - 40);
                                                        }
                                                    } else if (QuestState.current == Quest.NONE) {
                                                        startDialogue(DialogueText.questGiveLines[1], QuestAction.START_KILL_SNAILS, npcX[j], npcY[j] - 40);
                                                    } else {
                                                        startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[j], npcY[j] - 40);
                                                    }
                                                } else if (!Story.goldenBatQuestDone && Skills.transform) {
                                                    if (PlayerStats.level < 10) {
                                                        startDialogue(DialogueText.levelRequired10, Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                    } else if (QuestState.current == Quest.KILL_GOLDEN_BATS) {
                                                        if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_GOLDEN_BATS]) {
                                                            startDialogue(DialogueText.questReminderLines[6], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                        } else {
                                                            setNextNpcMarker(2);
                                                            startDialogue(DialogueText.questRewardLines[6], QuestAction.REWARD_MONEY, npcX[j], npcY[j] - 40);
                                                        }
                                                    } else if (QuestState.current == Quest.NONE) {
                                                        startDialogue(DialogueText.questGiveLines[14], QuestAction.START_KILL_GOLDEN_BATS, npcX[j], npcY[j] - 40);
                                                    } else {
                                                        startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[j], npcY[j] - 40);
                                                    }
                                                } else if (Story.girlOutingDone && !Skills.tripleJump) {
                                                    if (PlayerStats.level < 12) {
                                                        startDialogue(DialogueText.levelRequired12, Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                    } else if (QuestState.current == Quest.AIR_HITS) {
                                                        if (QuestState.progress < Quest.TARGET_COUNT[Quest.AIR_HITS]) {
                                                            startDialogue(DialogueText.questReminderLines[9], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                        } else {
                                                            setNextNpcMarker(4);
                                                            startDialogue(DialogueText.questRewardLines[9], QuestAction.REWARD_TRIPLE_JUMP, npcX[j], npcY[j] - 40);
                                                        }
                                                    } else if (QuestState.current == Quest.NONE) {
                                                        startDialogue(DialogueText.questGiveLines[17], QuestAction.START_AIR_HITS, npcX[j], npcY[j] - 40);
                                                    } else {
                                                        startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[j], npcY[j] - 40);
                                                    }
                                                } else {
                                                    if (!Skills.sprint && Skills.dartsLevel2) {
                                                        if (PlayerStats.level < 15) {
                                                            startDialogue(DialogueText.levelRequired15, Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                        } else if (QuestState.current == Quest.KILL_SCORPIONS) {
                                                            if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_SCORPIONS]) {
                                                                startDialogue(DialogueText.questReminderLines[11], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                            } else {
                                                                setNextNpcMarker(3);
                                                                startDialogue(DialogueText.questRewardLines[11], QuestAction.REWARD_SPRINT, npcX[j], npcY[j] - 40);
                                                            }
                                                        } else if (QuestState.current == Quest.NONE) {
                                                            startDialogue(DialogueText.questGiveLines[19], QuestAction.START_KILL_SCORPIONS, npcX[j], npcY[j] - 40);
                                                        } else {
                                                            startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[j], npcY[j] - 40);
                                                        }
                                                        break;
                                                    }

                                                    startDialogue(DialogueText.idleLines[0], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                }
                                                break;
                                            case 2:
                                                if (Story.sakuraEscorting) {
                                                    startDialogue(DialogueText.idleLines[9], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                } else if (!Skills.lightness) {
                                                    setNextNpcMarker(1);
                                                    startDialogue(DialogueText.questGiveLines[2], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                } else if (Skills.burrow && !Skills.motorbikeLicense) {
                                                    if (QuestState.current == Quest.ESCORT_AYUMI_SISTER) {
                                                        startDialogue(DialogueText.questGiveLines[9], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                    } else if (QuestState.current == Quest.NONE) {
                                                        startDialogue(DialogueText.questGiveLines[10], QuestAction.START_ESCORT_AYUMI_SISTER, npcX[j], npcY[j] - 40);
                                                    } else {
                                                        startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[j], npcY[j] - 40);
                                                    }
                                                } else if (Story.goldenBatQuestDone && !Story.tonikuQuestDone) {
                                                    if (QuestState.current == Quest.FIGHT_TONIKU) {
                                                        if (Story.tonikuDefeated) {
                                                            setNextNpcMarker(2);
                                                            startDialogue(DialogueText.questRewardLines[7], QuestAction.COMPLETE_FIGHT_TONIKU, npcX[j], npcY[j] - 40);
                                                        } else {
                                                            startDialogue(DialogueText.questReminderLines[7], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                        }
                                                    } else if (QuestState.current == Quest.NONE) {
                                                        startDialogue(DialogueText.questGiveLines[15], QuestAction.START_FIGHT_TONIKU, npcX[j], npcY[j] - 40);
                                                    } else {
                                                        startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[j], npcY[j] - 40);
                                                    }
                                                } else {
                                                    if (Story.tonikuQuestDone && !Story.girlWaiting) {
                                                        if (QuestState.current == Quest.KILL_NATIVES) {
                                                            if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_NATIVES]) {
                                                                startDialogue(DialogueText.questReminderLines[8], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                            } else {
                                                                setNextNpcMarker(3);
                                                                startDialogue(DialogueText.questRewardLines[8], QuestAction.REWARD_MEET_GIRL, npcX[j], npcY[j] - 40);
                                                            }
                                                        } else if (QuestState.current == Quest.NONE) {
                                                            startDialogue(DialogueText.questGiveLines[16], QuestAction.START_KILL_NATIVES, npcX[j], npcY[j] - 40);
                                                        } else {
                                                            startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[j], npcY[j] - 40);
                                                        }
                                                        break;
                                                    }

                                                    startDialogue(DialogueText.idleLines[3], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                }
                                                break;
                                            case 3:
                                                if (Story.sakuraEscorting) {
                                                    startDialogue(DialogueText.idleLines[9], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                } else {
                                                    if (Skills.lightness && Skills.darts) {
                                                        if (!Skills.burrow) {
                                                            if (QuestState.current == Quest.FIND_BURROW_SCROLL) {
                                                                if (!QuestState.itemFound) {
                                                                    startDialogue(DialogueText.questReminderLines[2], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                                } else {
                                                                    setNextNpcMarker(2);
                                                                    startDialogue(DialogueText.questRewardLines[2], QuestAction.REWARD_BURROW, npcX[j], npcY[j] - 40);
                                                                }
                                                            } else if (QuestState.current == Quest.NONE) {
                                                                startDialogue(DialogueText.questGiveLines[8], QuestAction.START_FIND_BURROW_SCROLL, npcX[j], npcY[j] - 40);
                                                            } else {
                                                                startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[j], npcY[j] - 40);
                                                            }
                                                            break;
                                                        }

                                                        if (!Skills.dive && Skills.doubleStrength) {
                                                            if (QuestState.current == Quest.KILL_WILD_BOAR) {
                                                                if (!QuestState.itemFound) {
                                                                    startDialogue(DialogueText.questReminderLines[4], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                                } else {
                                                                    setNextNpcMarker(4);
                                                                    startDialogue(DialogueText.questRewardLines[4], QuestAction.REWARD_WATER_SKILLS, npcX[j], npcY[j] - 40);
                                                                }
                                                            } else if (QuestState.current == Quest.NONE) {
                                                                startDialogue(DialogueText.questGiveLines[12], QuestAction.START_KILL_WILD_BOAR, npcX[j], npcY[j] - 40);
                                                            } else {
                                                                startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[j], npcY[j] - 40);
                                                            }
                                                            break;
                                                        }

                                                        if (!Skills.standOnWater && Skills.sprint) {
                                                            if (PlayerStats.level < 16) {
                                                                startDialogue(DialogueText.levelRequired16, Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                            } else if (QuestState.current == Quest.KILL_RED_TIGER_FISH) {
                                                                if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_RED_TIGER_FISH]) {
                                                                    startDialogue(DialogueText.questReminderLines[12], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                                } else {
                                                                    setNextNpcMarker(3);
                                                                    startDialogue(DialogueText.questRewardLines[12], QuestAction.REWARD_STAND_ON_WATER, npcX[j], npcY[j] - 40);
                                                                }
                                                            } else if (QuestState.current == Quest.NONE) {
                                                                startDialogue(DialogueText.questGiveLines[20], QuestAction.START_KILL_RED_TIGER_FISH, npcX[j], npcY[j] - 40);
                                                            } else {
                                                                startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[j], npcY[j] - 40);
                                                            }
                                                            break;
                                                        }

                                                        if (!Skills.hide && Skills.standOnWater) {
                                                            if (QuestState.current == Quest.FIND_HIDE_SCROLL) {
                                                                if (!QuestState.itemFound) {
                                                                    startDialogue(DialogueText.questReminderLines[13], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                                } else {
                                                                    setNextNpcMarker(0);
                                                                    startDialogue(DialogueText.questRewardLines[13], QuestAction.REWARD_HIDE, npcX[j], npcY[j] - 40);
                                                                }
                                                            } else if (QuestState.current == Quest.NONE) {
                                                                startDialogue(DialogueText.questGiveLines[21], QuestAction.START_FIND_HIDE_SCROLL, npcX[j], npcY[j] - 40);
                                                            } else {
                                                                startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[j], npcY[j] - 40);
                                                            }
                                                            break;
                                                        }

                                                        startDialogue(DialogueText.idleLines[4], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                        break;
                                                    }

                                                    startDialogue(DialogueText.questGiveLines[5], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                }
                                                break;
                                            case 4:
                                                if (Story.sakuraEscorting) {
                                                    startDialogue(DialogueText.idleLines[8], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                } else if (mapId == 99) {
                                                    startDialogue(DialogueText.questGiveLines[7], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                } else if (!Skills.lightness) {
                                                    startDialogue(DialogueText.questGiveLines[4], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                } else if (!Skills.darts) {
                                                    if (QuestState.current == Quest.KNOCK_DOWN_SCARECROWS) {
                                                        if (QuestState.progress < Quest.TARGET_COUNT[Quest.KNOCK_DOWN_SCARECROWS]) {
                                                            startDialogue(DialogueText.questReminderLines[3], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                        } else {
                                                            setNextNpcMarker(3);
                                                            startDialogue(DialogueText.questRewardLines[1], QuestAction.REWARD_DARTS, npcX[j], npcY[j] - 40);
                                                        }
                                                    } else if (QuestState.current == Quest.NONE) {
                                                        startDialogue(DialogueText.questGiveLines[6], QuestAction.START_KNOCK_DOWN_SCARECROWS, npcX[j], npcY[j] - 40);
                                                    } else {
                                                        startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[j], npcY[j] - 40);
                                                    }
                                                } else if (Skills.motorbikeLicense && !Skills.doubleStrength) {
                                                    if (QuestState.current == Quest.KILL_BEES) {
                                                        if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_BEES]) {
                                                            startDialogue(DialogueText.questReminderLines[3], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                        } else {
                                                            setNextNpcMarker(3);
                                                            startDialogue(DialogueText.questRewardLines[3], QuestAction.REWARD_DOUBLE_STRENGTH, npcX[j], npcY[j] - 40);
                                                        }
                                                    } else if (QuestState.current == Quest.NONE) {
                                                        startDialogue(DialogueText.questGiveLines[11], QuestAction.START_KILL_BEES, npcX[j], npcY[j] - 40);
                                                    } else {
                                                        startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[j], npcY[j] - 40);
                                                    }
                                                } else if (Skills.dive && !Skills.transform) {
                                                    if (PlayerStats.level < 8) {
                                                        startDialogue(DialogueText.levelRequired8, Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                    } else if (QuestState.current == Quest.CROSS_RIVER) {
                                                        if (!QuestState.itemFound) {
                                                            startDialogue(DialogueText.questReminderLines[5], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                        } else {
                                                            setNextNpcMarker(1);
                                                            startDialogue(DialogueText.questRewardLines[5], QuestAction.REWARD_TRANSFORM, npcX[j], npcY[j] - 40);
                                                        }
                                                    } else if (QuestState.current == Quest.NONE) {
                                                        startDialogue(DialogueText.questGiveLines[13], QuestAction.START_CROSS_RIVER, npcX[j], npcY[j] - 40);
                                                    } else {
                                                        startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[j], npcY[j] - 40);
                                                    }
                                                } else {
                                                    if (!Skills.dartsLevel2 && Skills.tripleJump) {
                                                        if (QuestState.current == Quest.KILL_FLOWER_DEMONS) {
                                                            if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_FLOWER_DEMONS]) {
                                                                startDialogue(DialogueText.questReminderLines[10], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                            } else {
                                                                setNextNpcMarker(1);
                                                                startDialogue(DialogueText.questRewardLines[10], QuestAction.REWARD_DARTS_LEVEL_2, npcX[j], npcY[j] - 40);
                                                            }
                                                        } else if (QuestState.current == Quest.NONE) {
                                                            startDialogue(DialogueText.questGiveLines[18], QuestAction.START_KILL_FLOWER_DEMONS, npcX[j], npcY[j] - 40);
                                                        } else {
                                                            startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[j], npcY[j] - 40);
                                                        }
                                                        break;
                                                    }

                                                    startDialogue(DialogueText.idleLines[2], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                }
                                                break;
                                            case 5:
                                                if (mapId == 0) {
                                                    if (j == 5) {
                                                        startDialogue(DialogueText.guardLines[0], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                    }

                                                    if (j == 6) {
                                                        openShop(ShopScreen.MODE_MONEY);
                                                    }
                                                }

                                                if (mapId == 2 && j == 0) {
                                                    openShop(ShopScreen.MODE_SMS);
                                                }

                                                if (mapId == 1 && j == 0) {
                                                    startDialogue(DialogueText.guardLines[1], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                }

                                                if (mapId == 5 && j == 0) {
                                                    startDialogue(DialogueText.guardLines[2], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                }

                                                if (mapId == 6 && j == 0) {
                                                    startDialogue(DialogueText.guardLines[3], Dialogue.NO_ACTION, npcX[j], npcY[j] - 40);
                                                }
                                        }

                                        if (mapId == guideMarkerMap && j == guideMarkerNpc) {
                                            guideMarkerMap = -1;
                                        }
                                    } else if (rightSoftKeyLabel.equals("Trò chuyện")) {
                                        Input.clearPressed();
                                        Classmates.facing[Player.nearClassmate] = -Player.facing;
                                        startDialogue(Classmates.lines[Player.nearClassmate], QuestAction.TALK, Classmates.x[Player.nearClassmate], Classmates.y[Player.nearClassmate] - 44);
                                    } else if (rightSoftKeyLabel.equals("Nói chuyện") && Player.nearGirl) {
                                        if (Girl.following) {
                                            startDialogue(DialogueText.girlLines[0], QuestAction.TALK, Girl.x, Girl.y - 44);
                                        } else if (Story.girlOutingDone) {
                                            startDialogue(DialogueText.girlLines[2], QuestAction.TALK, Girl.x, Girl.y - 44);
                                            Girl.following = true;
                                        } else if (Story.girlWaiting) {
                                            startDialogue(DialogueText.girlLines[3], QuestAction.START_GIRL_OUTING, Girl.x, Girl.y - 44);
                                            Story.girlWaiting = false;
                                        } else {
                                            startDialogue(DialogueText.girlLines[1], QuestAction.TALK, Girl.x, Girl.y - 44);
                                        }
                                    }
                                } else {
                                    label2585: {
                                        if (Input.held[Input.KEY_UP]) {
                                            if (Skills.motorbikeLicense) {
                                                for(i = 0; i < Motorbikes.count; ++i) {
                                                    if (Player.y == Motorbikes.y[i] && abs(Player.x - Motorbikes.x[i]) < 20) {
                                                        Player.x = Motorbikes.x[i];
                                                        Player.action = PlayerAction.MOTORBIKE_STAND;
                                                        Input.clearHeld();
                                                        Player.motorbike = i;
                                                        Motorbikes.facing[i] = Player.facing;
                                                        Motorbikes.frame[i] = 0;
                                                        break label2585;
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
                                            rightSoftKeyLabel = " ";
                                            hudDirty = true;
                                        } else if (Input.held[Input.KEY_LEFT]) {
                                            if (Player.facing == 1) {
                                                Player.facing = -1;
                                            } else {
                                                Player.action = PlayerAction.RUN;
                                                Player.vx = -Player.moveSpeed;
                                            }

                                            Player.nearNpc = -1;
                                            Player.nearClassmate = -1;
                                            rightSoftKeyLabel = " ";
                                            hudDirty = true;
                                        } else if (Input.held[Input.KEY_RIGHT]) {
                                            if (Player.facing == -1) {
                                                Player.facing = 1;
                                            } else {
                                                Player.action = PlayerAction.RUN;
                                                Player.vx = Player.moveSpeed;
                                            }

                                            Player.nearClassmate = -1;
                                            Player.nearNpc = -1;
                                            rightSoftKeyLabel = " ";
                                            hudDirty = true;
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
                                            rightSoftKeyLabel = " ";
                                            hudDirty = true;
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
                                            rightSoftKeyLabel = " ";
                                            hudDirty = true;
                                        }

                                        if (Input.pressed[Input.KEY_FIRE]) {
                                            Player.action = PlayerAction.ATTACK;
                                            Player.comboStep = 0;
                                            Player.actionTimer = 0;
                                            Player.vy = 0;
                                            Input.pressed[Input.KEY_FIRE] = false;
                                            Player.nearNpc = -1;
                                            Player.nearClassmate = -1;
                                            rightSoftKeyLabel = " ";
                                            hudDirty = true;
                                        }
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
                                    Player.comboStep = 0;
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
                                    Player.comboStep = 0;
                                    Player.actionTimer = 0;
                                    Input.pressed[Input.KEY_FIRE] = false;
                                }

                                if (Input.held[Input.KEY_UP] && PlayerStats.hasMp() && Player.vy > -4 && (Player.actionTimer == 0 && Skills.doubleJump || Player.actionTimer == 1 && Skills.tripleJump)) {
                                    ++Player.actionTimer;
                                    Player.vy = -7;
                                    PlayerStats.drainMp(3);

                                    hudDirty = true;
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
                                    Player.comboStep = 0;
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

                    if (cameraX != cameraTargetX || cameraY != cameraTargetY) {
                        cameraVx = cameraTargetX - cameraX << 2;
                        cameraVy = cameraTargetY - cameraY << 2;
                        cameraSubpixelX += cameraVx;
                        cameraX += cameraSubpixelX >> 4;
                        cameraSubpixelX &= 15;
                        cameraSubpixelY += cameraVy;
                        cameraY += cameraSubpixelY >> 4;
                        cameraSubpixelY &= 15;
                        if (cameraX < 0) {
                            cameraX = 0;
                        }

                        if (cameraX > cameraMaxX) {
                            cameraX = cameraMaxX;
                        }

                        if (cameraY < 0) {
                            cameraY = 0;
                        }

                        if (cameraY > cameraMaxY) {
                            cameraY = cameraMaxY;
                        }
                    }

                    if (shakeStep >= 0) {
                        cameraX += shakeOffsetX[shakeStep];
                        cameraY += shakeOffsetY[shakeStep];
                        if (++shakeStep == 4) {
                            shakeStep = -1;
                        }

                        if (cameraX < 0) {
                            cameraX = 0;
                        }

                        if (cameraX > cameraMaxX) {
                            cameraX = cameraMaxX;
                        }

                        if (cameraY < 0) {
                            cameraY = 0;
                        }

                        if (cameraY > cameraMaxY) {
                            cameraY = cameraMaxY;
                        }
                    }

                    if ((firstVisibleCol = cameraX / GameMap.TILE_SIZE - 1) < 0) {
                        firstVisibleCol = 0;
                    }

                    firstVisibleRow = cameraY / GameMap.TILE_SIZE;
                    lastVisibleCol = firstVisibleCol + visibleCols;
                    lastVisibleRow = firstVisibleRow + visibleRows;
                    if (firstVisibleRow < 0) {
                        firstVisibleRow = 0;
                    }

                    if (lastVisibleRow > GameMap.height - 1) {
                        lastVisibleRow = GameMap.height - 1;
                    }

                    for(i = 0; i < Crates.count; ++i) {
                        switch (Crates.state[i]) {
                            case Crates.STATE_SLIDING:
                                Crates.x[i] += Crates.vx[i];
                                if (Crates.vx[i] > 0) {
                                    Crates.vx[i]--;
                                } else if (Crates.vx[i] < 0) {
                                    Crates.vx[i]++;
                                } else {
                                    Crates.state[i] = Crates.STATE_RESTING;
                                }

                                if (!GameMap.hasFlag(Crates.x[i] - 12, Crates.y[i] + 1, TileFlags.SOLID) && !GameMap.hasFlag(Crates.x[i] + 12, Crates.y[i] + 1, TileFlags.SOLID)) {
                                    Crates.state[i] = Crates.STATE_FALLING;
                                    Crates.vy[i] = 2;
                                }

                                if (GameMap.hasFlag(Crates.x[i] + 12, Crates.y[i] - 2, TileFlags.WALL_LEFT_SIDE) || GameMap.hasFlag(Crates.x[i] - 12, Crates.y[i] - 2, TileFlags.WALL_RIGHT_SIDE)) {
                                    Crates.x[i] = alignToTileX(Crates.x[i]) + 12;
                                }
                                break;
                            case Crates.STATE_FALLING:
                                Crates.y[i] += Crates.vy[i];
                                if (Crates.vy[i] < 20) {
                                    Crates.vy[i]++;
                                }

                                if (!GameMap.hasFlag(Crates.x[i] + 12, Crates.y[i], TileFlags.SOLID) && !GameMap.hasFlag(Crates.x[i] - 12, Crates.y[i], TileFlags.SOLID)) {
                                    if (GameMap.hasFlag(Crates.x[i], Crates.y[i] - GameMap.TILE_SIZE, TileFlags.WATER)) {
                                        Crates.y[i] = alignToTileY(Crates.y[i]) + 1;
                                        WaterSplash.spawn(Crates.x[i], Crates.y[i] - 13);
                                        WaterSplash.spawn(Crates.x[i] + 1, Crates.y[i] - 10);
                                        Crates.vy[i] = 1;
                                        Crates.bobDirection[i] = -1;
                                        Crates.driftVx[i] = 1;
                                        Crates.state[i] = Crates.STATE_FLOATING;
                                    }
                                    break;
                                }

                                Crates.y[i] = alignToTileY(Crates.y[i]);
                                Crates.vy[i] = 0;
                                Crates.state[i] = Crates.STATE_RESTING;
                                break;
                            case Crates.STATE_FLOATING:
                                if (tick % 2 == 0) {
                                    Crates.y[i] += Crates.bobDirection[i];
                                    Crates.vy[i] += Crates.bobDirection[i];
                                    if (Crates.vy[i] >= 1) {
                                        Crates.bobDirection[i] = -1;
                                    }

                                    if (Crates.vy[i] <= -1) {
                                        Crates.bobDirection[i] = 1;
                                    }

                                    Crates.x[i] += Crates.driftVx[i];
                                    if (i == Player.crate) {
                                        Player.y = Crates.y[Player.crate] - 24;
                                        Player.x += Crates.driftVx[i];
                                    }

                                    if (GameMap.hasFlag(Crates.x[i] + 12, Crates.y[i], TileFlags.WALL_LEFT_SIDE) || GameMap.hasFlag(Crates.x[i] - 12, Crates.y[i], TileFlags.WALL_RIGHT_SIDE)) {
                                        Crates.driftVx[i] = -Crates.driftVx[i];
                                    }
                                }
                        }
                    }

                    for(i = 0; i < Motorbikes.count; ++i) {
                        if (i == Player.motorbike) {
                            if (Player.actionTimer > 1) {
                                Motorbikes.frame[i] = 2;
                            } else if (tick % 8 > 3) {
                                Motorbikes.frame[i] = 1;
                            } else {
                                Motorbikes.frame[i] = 0;
                            }
                        }

                        if (Motorbikes.falling[i] == 1) {
                            Motorbikes.y[i] += Motorbikes.vy[i];
                            if (Motorbikes.vy[i] < 20) {
                                Motorbikes.vy[i]++;
                            }

                            if (Motorbikes.y[i] >= GameMap.pixelHeight || GameMap.hasFlag(Motorbikes.x[i], Motorbikes.y[i], TileFlags.SOLID)) {
                                Motorbikes.vy[i] = 0;
                                Motorbikes.falling[i] = 0;
                                Motorbikes.y[i] = alignToTileY(Motorbikes.y[i]);
                            }
                        }
                    }

                    FloatingText.update();
                    WaterSplash.update(tick);
                    Dust.update();
                    Blood.update();

                    int damage;
                    for(i = 0; i < Darts.count; ++i) {
                        if (Darts.state[i] == Darts.STATE_FLYING) {
                            Darts.x[i] += Darts.vx[i];
                            Darts.y[i] += Darts.vy[i];
                            if (Darts.x[i] >= cameraX && Darts.x[i] <= cameraX + Viewport.viewWidth && Darts.y[i] >= cameraY && Darts.y[i] <= cameraY + Viewport.viewHeight) {
                                if (Darts.vx[i] > 0 && (GameMap.flagsAtPixel(Darts.x[i], Darts.y[i]) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                                    Darts.state[i] = Darts.STATE_STUCK;
                                    Darts.x[i] = alignToTileX(Darts.x[i]);
                                } else if (Darts.vx[i] < 0 && (GameMap.flagsAtPixel(Darts.x[i], Darts.y[i]) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                                    Darts.state[i] = Darts.STATE_STUCK;
                                    Darts.x[i] = alignToTileX(Darts.x[i]) + GameMap.TILE_SIZE;
                                }

                                Darts.frame[i]++;
                                if (Darts.frame[i] > 1) {
                                    Darts.frame[i] = 0;
                                }

                                if (Darts.vx[i] != 0) {
                                    for(j = 0; j < Monsters.count; ++j) {
                                        if (Monsters.state[j] != Monsters.STATE_RESPAWN_WAIT && Monsters.state[j] != Monsters.STATE_KNOCKED_OUT && abs(Monsters.x[j] - Darts.x[i]) < Monsters.width[j] && abs(Monsters.y[j] - (Monsters.height[j] >> 1) - Darts.y[i]) < Monsters.height[j]) {
                                            boolean killed;
                                            label2807: {
                                                damage = Darts.powered[i] ? PlayerStats.dartDamage << 1 : PlayerStats.dartDamage;
                                                if (Monsters.state[j] == Monsters.STATE_ACTIVE || Monsters.state[j] == Monsters.STATE_RESTING || Monsters.state[j] == Monsters.STATE_ATTACKING) {
                                                    if (Monsters.type[j] == Monsters.TYPE_WIND_NINJA && Darts.vx[i] * Monsters.direction[j] < 0) {
                                                        Monsters.state[j] = Monsters.STATE_ATTACKING;
                                                        Monsters.timer[j] = 4;
                                                        Darts.vx[i] = -Darts.vx[i];
                                                        Darts.vy[i] = -Darts.vy[i];
                                                        Darts.x[i] += Darts.vx[i];
                                                        Darts.y[i] += Darts.vy[i];
                                                        killed = true;
                                                        break label2807;
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
                                                        killed = true;
                                                        break label2807;
                                                    }

                                                    Monsters.timer[j] = 0;
                                                    Monsters.direction[j] = Darts.vx[i] < 0 ? 1 : -1;
                                                    Monsters.state[j] = Monsters.STATE_HURT;
                                                    Monsters.frame[j] = 3;
                                                    Darts.state[i] = Darts.STATE_FREE;
                                                }

                                                killed = false;
                                            }

                                            if (!killed) {
                                                Blood.spawn(Monsters.x[j], Monsters.y[j] - (Monsters.height[j] >> 1), Monsters.direction[j]);
                                                FloatingText.spawn("-" + damage, 0, Monsters.x[j], Monsters.y[j] - Monsters.height[j], 0, -2);
                                            }
                                            break;
                                        }
                                    }

                                    for(j = 0; j < Ninjas.count; ++j) {
                                        if (Ninjas.state[j] != Ninjas.STATE_DEAD && Ninjas.state[j] != Ninjas.STATE_KNOCKED_OUT && abs(Ninjas.x[j] - Darts.x[i]) < 20 && abs(Ninjas.y[j] - 15 - Darts.y[i]) < 20) {
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

                                    if (!Darts.powered[i] && abs(Player.x - Darts.x[i]) < Player.halfWidth && abs(Player.y - Player.halfHeight - Darts.y[i]) < Player.halfHeight) {
                                        if (Player.action == PlayerAction.ATTACK && Player.weapon == 0) {
                                            Darts.vx[i] = -Darts.vx[i];
                                            Darts.vy[i] = -3;
                                        } else {
                                            damagePlayer(PlayerStats.dartDamage, -Player.facing);
                                            Darts.state[i] = Darts.STATE_FREE;
                                        }
                                        break;
                                    }

                                    if (BoarBoss.active && abs(BoarBoss.x - Darts.x[i]) < BoarBoss.width >> 1 && abs(BoarBoss.y - (BoarBoss.height >> 1) - Darts.y[i]) < BoarBoss.height >> 1) {
                                        damageBoarBoss(PlayerStats.dartDamage, -BoarBoss.direction);
                                        Blood.spawn(Darts.x[i], Darts.y[i], BoarBoss.direction);
                                        FloatingText.spawn("-" + PlayerStats.dartDamage, 0, BoarBoss.x, BoarBoss.y - BoarBoss.height, 0, -2);
                                        Darts.state[i] = Darts.STATE_FREE;
                                    }

                                    if (RiverMonster.active && abs(RiverMonster.x - Darts.x[i]) < RiverMonster.width >> 1 && abs(RiverMonster.y - (RiverMonster.height >> 1) - Darts.y[i]) < RiverMonster.height >> 1) {
                                        Darts.vx[i] = -Darts.vx[i];
                                        Darts.vy[i] = -1;
                                    }

                                    if (Toniku.active && abs(Classmates.x[0] - Darts.x[i]) < 12 && abs(Classmates.y[0] - 16 - Darts.y[i]) < 32) {
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

                    if (Projectiles.update(Player.x, Player.y, Player.halfWidth, Player.halfHeight, cameraX, cameraY, Player.action == PlayerAction.ATTACK && Player.weapon == 0)) {
                        damagePlayer(Projectiles.hitDamage, Projectiles.hitDirection);
                    }

                    Aura.update();

                    if (ArcProjectiles.update(Player.x, Player.y, Player.halfWidth, Player.halfHeight, cameraY)) {
                        damagePlayer(ArcProjectiles.hitDamage, ArcProjectiles.hitDirection);
                    }

                    if (guideMarkerMap >= 0) {
                        if (guideMarkerX > cameraX + 10 && guideMarkerX < cameraX + Viewport.viewWidth - 10 && guideMarkerY > cameraY + 10 && guideMarkerY < cameraY + Viewport.viewHeight - 10) {
                            guideArrowIcon = 2;
                            guideArrowX = guideMarkerX;
                            guideArrowY = guideMarkerY + guideArrowBob;
                        } else if (abs(Player.x - guideMarkerX) > abs(Player.y - guideMarkerY)) {
                            if (Player.x > guideMarkerX) {
                                guideArrowIcon = 1;
                                guideArrowX = cameraX + 20 + guideArrowBob;
                            } else {
                                guideArrowIcon = 0;
                                guideArrowX = cameraX + Viewport.viewWidth - 20 + guideArrowBob;
                            }

                            if ((guideArrowY = guideMarkerY + 24) < cameraY + 10) {
                                guideArrowY = cameraY + 10;
                            }

                            if (guideArrowY > cameraY + Viewport.viewHeight - 10) {
                                guideArrowY = cameraY + Viewport.viewHeight - 10;
                            }
                        } else if (Player.y > guideMarkerY) {
                            guideArrowIcon = 3;
                            guideArrowX = Player.x;
                            guideArrowY = cameraY + 20 + guideArrowBob;
                        } else {
                            guideArrowIcon = 2;
                            guideArrowX = Player.x;
                            guideArrowY = cameraY + Viewport.viewHeight - 20 + guideArrowBob;
                        }

                        if ((guideArrowBob += guideArrowBobDirection) == 5 || guideArrowBob == -5) {
                            guideArrowBobDirection = -guideArrowBobDirection;
                        }
                    }

                    updatePlayer();
                    if (Girl.mapId == mapId) {
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

                                    if (Girl.following && tick % 6 == 0) {
                                        girlFindTarget();
                                    }

                                    if (tick % 15 == 0) {
                                        girlFollowPlayer();
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
                                            Girl.x = alignToTileX(Girl.x + Girl.halfWidth) - Girl.halfWidth;
                                        }
                                    } else if (GameMap.hasFlag(Girl.x - Girl.halfWidth - 1, Girl.y - Girl.halfHeight, TileFlags.WALL_RIGHT_SIDE)) {
                                        Girl.vx = 0;
                                        Girl.x = alignToTileX(Girl.x - Girl.halfWidth - 1) + GameMap.TILE_SIZE + Girl.halfWidth;
                                    }

                                    if ((Girl.x += Girl.vx) < 0) {
                                        Girl.x = 0;
                                    }

                                    if (Girl.x >= GameMap.pixelWidth) {
                                        Girl.x = GameMap.pixelWidth - 1;
                                    }

                                    if (Player.crate != -1) {
                                        if (abs(Girl.x - Crates.x[Player.crate]) >= 20) {
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
                                        girlFollowPlayer();
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
                                        Girl.y = alignToTileX(Girl.y);
                                        Dust.spawn(-1, Girl.x - -8, Girl.y);
                                        Dust.spawn(1, Girl.x - 8, Girl.y);
                                    }

                                    if (GameMap.hasFlag(Girl.x, Girl.y, TileFlags.WATER) && GameMap.hasFlag(Girl.x, Girl.y + 24, TileFlags.DEEP_WATER) && Girl.y % GameMap.TILE_SIZE > 8) {
                                        WaterSplash.spawn(Girl.x, alignToTileY(Girl.y) + 8);
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
                                            girlAttackHit();
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

                    updateClassmates();
                    if (tick % 2 == 0 && ((Dog.x += Dog.vx) > Dog.maxX || Dog.x < Dog.minX)) {
                    Dog.vx = -Dog.vx;
                    Dog.x += Dog.vx;
                }

                updateMonsters();
                updateNinjas();
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
                                if ((offset = tick % 80 - 40) > 0) {
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
                                if (tick % 5 == 0) {
                                    boarBossChargeIfPlayerNear();
                                }
                            }

                            BoarBoss.frame = 0;
                            if (tick % 10 == 6) {
                                Dust.spawn(-BoarBoss.direction, BoarBoss.x + BoarBoss.direction * 28, BoarBoss.y - 5);
                            }
                            break;
                        case BoarBoss.STATE_WALK:
                            if (tick % 8 == 0) {
                                boarBossChargeIfPlayerNear();
                            }

                            if (!GameMap.hasFlag(BoarBoss.x + (BoarBoss.direction << 4), BoarBoss.y + 1, TileFlags.SOLID)) {
                                BoarBoss.state = BoarBoss.STATE_IDLE;
                                BoarBoss.timer = 0;
                            } else {
                                if (abs(BoarBoss.x - BoarBoss.targetX) < 8) {
                                    BoarBoss.state = BoarBoss.STATE_IDLE;
                                    BoarBoss.timer = 20;
                                }

                                if (tick % 2 == 0) {
                                    BoarBoss.x += BoarBoss.vx;
                                    BoarBoss.frame = 0;
                                } else {
                                    BoarBoss.frame = 1;
                                }
                            }
                        case 3:
                        case 4:
                        default:
                            break;
                        case BoarBoss.STATE_CHARGE:
                            if (!GameMap.hasFlag(BoarBoss.x + (BoarBoss.direction << 4), BoarBoss.y + 1, TileFlags.SOLID)) {
                                BoarBoss.state = BoarBoss.STATE_IDLE;
                                BoarBoss.timer = 0;
                            } else {
                                if (abs(BoarBoss.x - BoarBoss.targetX) < 4) {
                                    BoarBoss.state = BoarBoss.STATE_IDLE;
                                    BoarBoss.timer = 35;
                                }

                                if (abs(Player.y - BoarBoss.y) < 18 && abs(BoarBoss.x + (BoarBoss.direction << 4) - Player.x) < 10) {
                                    damagePlayer(BoarBoss.damage, BoarBoss.direction);
                                    BoarBoss.state = BoarBoss.STATE_IDLE;
                                    BoarBoss.timer = 45;
                                    if (BoarBoss.hp < BoarBoss.maxHp >> 1) {
                                        BoarBoss.timer = 18;
                                    }
                                }

                                BoarBoss.x += BoarBoss.vx;
                                BoarBoss.frame = tick % 3;
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

                            if (BoarBoss.y > cameraY + Viewport.viewHeight + BoarBoss.height || BoarBoss.y >= GameMap.pixelHeight + BoarBoss.height) {
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

                if (RiverMonster.active) {
                    int phase = tick % 10;
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

                            if (RiverMonster.timer == 0 && abs(Player.x - (RiverMonster.x - 40)) < 30) {
                                RiverMonster.timer = 0;
                                RiverMonster.state = RiverMonster.STATE_ATTACK;
                                RiverMonster.direction = RiverMonster.x < Player.x ? 1 : -1;
                            }

                            if (tick % 122 == 0) {
                                ArcProjectiles.spawn(RiverMonster.x, RiverMonster.y - 45, -1, -12, RiverMonster.damage);
                                ArcProjectiles.spawn(RiverMonster.x, RiverMonster.y - 50, 0, -15, RiverMonster.damage);
                                ArcProjectiles.spawn(RiverMonster.x, RiverMonster.y - 45, 1, -12, RiverMonster.damage);
                            } else if (tick % 200 == 0) {
                                RiverMonster.state = RiverMonster.STATE_DIVE;
                                RiverMonster.timer = 0;
                            }
                        case 3:
                        case RiverMonster.STATE_SUNK:
                        case 6:
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
                                Projectiles.spawn(RiverMonster.x + RiverMonster.direction * 20, RiverMonster.y - 29, RiverMonster.direction << 3, RiverMonster.damage, 2);
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

                updateFinalBoss();
                if (Toniku.active) {
                    label2824: {
                        int phase = tick % 50;
                        int distance = abs(Classmates.x[0] - Player.x);
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
                                    break label2824;
                                }

                                if (distance >= 40 || Player.action != PlayerAction.ATTACK) {
                                    break label2824;
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
                                    Classmates.vx[0] = (abs(Classmates.x[0] - Player.x) >> 3) * Classmates.facing[0];
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
                                break label2824;
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
                                break label2824;
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
                                break label2824;
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
                                    Classmates.y[0] = alignToTileX(Classmates.y[0]);
                                    Dust.spawn(-1, Classmates.x[0] - -8, Classmates.y[0]);
                                    Dust.spawn(1, Classmates.x[0] - 8, Classmates.y[0]);
                                    if (abs(Classmates.x[0] - Player.x) < 40 && phase < 25) {
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
                                break label2824;
                            case Classmates.STATE_ATTACK:
                                Classmates.animTimer[0]++;
                                switch (Classmates.target[0]) {
                                    case 0:
                                        switch (Classmates.animTimer[0]) {
                                            case 1:
                                            case 2:
                                            case 3:
                                                Classmates.slashEffect[0] = -1;
                                                Classmates.pose[0] = 13;
                                                break label2824;
                                            case 4:
                                                Classmates.slashEffect[0] = 0;
                                                Classmates.pose[0] = 14;
                                                break label2824;
                                            case 5:
                                                Classmates.slashEffect[0] = 1;
                                                Classmates.pose[0] = 15;
                                                break label2824;
                                            case 6:
                                                Classmates.slashEffect[0] = 1;
                                                Classmates.pose[0] = 15;
                                                tonikuSlashPlayer(0);
                                                break label2824;
                                            case 7:
                                                Classmates.slashEffect[0] = 2;
                                                Classmates.pose[0] = 16;
                                                break label2824;
                                            case 8:
                                                Classmates.slashEffect[0] = 2;
                                                Classmates.pose[0] = 16;
                                                Classmates.target[0] = 2;
                                                Classmates.animTimer[0] = 1;
                                            default:
                                                break label2824;
                                        }
                                    case 1:
                                        switch (Classmates.animTimer[0]) {
                                            case 1:
                                                Classmates.slashEffect[0] = 1;
                                                Classmates.pose[0] = 15;
                                                break label2824;
                                            case 2:
                                                Classmates.slashEffect[0] = 1;
                                                Classmates.pose[0] = 15;
                                                Classmates.target[0] = 10;
                                            default:
                                                break label2824;
                                        }
                                    case 2:
                                        switch (Classmates.animTimer[0]) {
                                            case 1:
                                            case 2:
                                            case 3:
                                                Classmates.slashEffect[0] = 3;
                                                Classmates.pose[0] = 17;
                                                break label2824;
                                            case 4:
                                                Classmates.slashEffect[0] = 4;
                                                Classmates.pose[0] = 18;
                                                break label2824;
                                            case 5:
                                                Classmates.slashEffect[0] = 5;
                                                Classmates.pose[0] = 19;
                                                break label2824;
                                            case 6:
                                                Classmates.slashEffect[0] = 6;
                                                Classmates.pose[0] = 20;
                                                tonikuSlashPlayer(0);
                                                break label2824;
                                            case 7:
                                                Classmates.slashEffect[0] = 6;
                                                Classmates.pose[0] = 20;
                                                Classmates.target[0] = 3;
                                                Classmates.animTimer[0] = 1;
                                            default:
                                                break label2824;
                                        }
                                    case 3:
                                        switch (Classmates.animTimer[0]) {
                                            case 1:
                                                Classmates.slashEffect[0] = 1;
                                                Classmates.pose[0] = 15;
                                                break label2824;
                                            case 2:
                                                Classmates.slashEffect[0] = 1;
                                                Classmates.pose[0] = 15;
                                                break label2824;
                                            case 3:
                                                Classmates.slashEffect[0] = 2;
                                                Classmates.pose[0] = 16;
                                                break label2824;
                                            case 4:
                                                Classmates.slashEffect[0] = 2;
                                                Classmates.pose[0] = 16;
                                                Classmates.target[0] = 1;
                                                Classmates.animTimer[0] = 1;
                                        }
                                    case 4:
                                    case 5:
                                    case 6:
                                    case 7:
                                    case 8:
                                    case 9:
                                    default:
                                        break label2824;
                                    case 10:
                                        switch (Classmates.animTimer[0]) {
                                            case 1:
                                                Classmates.slashEffect[0] = 2;
                                                Classmates.pose[0] = 16;
                                                break label2824;
                                            case 2:
                                                Classmates.slashEffect[0] = 0;
                                                Classmates.pose[0] = 14;
                                                break label2824;
                                            case 3:
                                            case 4:
                                                Classmates.pose[0] = 13;
                                                Classmates.slashEffect[0] = -1;
                                                break label2824;
                                            case 5:
                                                Classmates.pose[0] = 13;
                                                Classmates.slashEffect[0] = -1;
                                                Classmates.state[0] = Classmates.STATE_IDLE;
                                                Classmates.target[0] = 120;
                                                Classmates.animTimer[0] = 0;
                                            default:
                                                break label2824;
                                        }
                                }
                            case Classmates.STATE_THROW_DART:
                                Classmates.animTimer[0]++;
                                switch (Classmates.animTimer[0]) {
                                    case 1:
                                    case 2:
                                    case 3:
                                        Classmates.pose[0] = 13;
                                        break label2824;
                                    case 4:
                                        Classmates.pose[0] = 14;
                                        break label2824;
                                    case 5:
                                        Classmates.pose[0] = 15;
                                        break label2824;
                                    case 6:
                                        Classmates.pose[0] = 15;
                                        Darts.spawn(Classmates.x[0] + (Classmates.facing[0] << 3), Classmates.y[0] - 12, Classmates.facing[0] << 3, 0, false);
                                        break label2824;
                                    case 7:
                                        Classmates.pose[0] = 16;
                                        break label2824;
                                    case 8:
                                        Classmates.pose[0] = 16;
                                        Classmates.target[0] = 2;
                                        break label2824;
                                    case 9:
                                        Classmates.animTimer[0] = 0;
                                        Classmates.state[0] = Classmates.STATE_IDLE;
                                    default:
                                        break label2824;
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
                                    if (abs(Classmates.x[0] - Player.x) < 30) {
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
                                                break label2824;
                                            case 4:
                                                Classmates.slashEffect[0] = 0;
                                                Classmates.pose[0] = 14;
                                                break label2824;
                                            case 5:
                                                Classmates.slashEffect[0] = 1;
                                                Classmates.pose[0] = 15;
                                                break label2824;
                                            case 6:
                                                Classmates.slashEffect[0] = 1;
                                                Classmates.pose[0] = 15;
                                                tonikuSlashPlayer(0);
                                                break label2824;
                                            case 7:
                                                Classmates.slashEffect[0] = 2;
                                                Classmates.pose[0] = 16;
                                                break label2824;
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
                                break label2824;
                        }

                        Classmates.animTimer[0] = Classmates.target[0] = 0;
                    }
                }

                if (MoneyDrops.update(Player.x, Player.y, Player.height)) {
                    hudDirty = true;
                }

                if (PotionDrops.update(Player.x, Player.y, Player.height)) {
                    hudDirty = true;
                }
                if (questItemState != 0) {
                    if (questItemState == 1) {
                        if (abs(Player.y - (Player.height >> 1) - (questItemY - 7)) < 10 && abs(Player.x - questItemX) < 10) {
                            questItemState = 2;
                            QuestState.itemFound = true;
                            Notice.show("đã lấy được bí kíp");
                        }
                    } else {
                        ++questItemState;
                        questItemY -= 3;
                        if (questItemState > 40) {
                            questItemState = 0;
                        }
                    }
                }

                Notice.update();
                break;
                case GameState.LOADING:
                    if (loadingProgress < loadingTarget) {
                        loadingProgress += 5;
                    }
                    break;
                case GameState.MAIN_MENU:
                    if (++tick == 10000) {
                        tick = 0;
                    }

                    if (Input.pressed[Input.KEY_LEFT]) {
                        if (--mainMenuIndex < 0) {
                            mainMenuIndex = GameText.mainMenuItems.length - 1;
                        }

                        Input.pressed[Input.KEY_LEFT] = false;
                    }

                    if (Input.pressed[Input.KEY_RIGHT]) {
                        if (++mainMenuIndex == GameText.mainMenuItems.length) {
                            mainMenuIndex = 0;
                        }

                        Input.pressed[Input.KEY_RIGHT] = false;
                    }

                    if (Input.pressed[Input.KEY_FIRE]) {
                        label2172: {
                            Input.clearPressed();
                            if (mainMenuIndex == 0) {
                                titleImage = null;
                                releaseMapResources();
                                Save.pendingSave = true;
                                PlayTime.start();
                                changeMap(99);
                            } else if (mainMenuIndex == 1) {
                                if (!Save.isSaved()) {
                                    break label2172;
                                }

                                titleImage = null;
                                releaseMapResources();
                                int[] saveData;
                                PlayTime.savedSeconds = (saveData = bytesToInts(Save.loadRecord("ninjasave")))[0];
                                mapId = saveData[1];
                                tick = saveData[2];
                                Story.prologueActive = saveData[3] == 1;
                                Inventory.money = saveData[4];
                                Classmates.stayPut = saveData[5] == 1;
                                Story.riverMonsterDefeated = saveData[6] == 1;
                                Story.tonikuDefeated = saveData[7] == 1;
                                Story.sakuraFound = saveData[8] == 1;
                                Girl.following = saveData[9] == 1;
                                Girl.unusedSaveSlot = saveData[10];
                                Girl.mapId = saveData[11];
                                Player.motorbike = saveData[12];
                                Inventory.swordLevel = saveData[13];
                                Skills.dartsLevel2 = saveData[14] == 1;
                                Save.pendingLoad = true;
                                PlayTime.start();
                                changeMap(mapId);
                            } else if (mainMenuIndex == 2) {
                                titleImage = null;
                                releaseMapResources();
                                GameState.current = GameState.LOADING;
                                resetLoadingProgress();
                                Midlet.nextGameState = GameState.TUTORIAL;
                                mapId = 101;
                                (new Thread(Midlet.instance)).start();
                            } else if (mainMenuIndex == 3) {
                                try {
                                    Midlet.instance.platformRequest("http://wap.teamobi.com/");
                                } catch (Exception e) {
                                }
                            } else if (mainMenuIndex == 4) {
                                Midlet.instance.notifyDestroyed();
                            }

                            Input.pressed[Input.KEY_FIRE] = false;
                        }
                    }

                    if (cameraX == 0) {
                        cameraX = 960;
                        cameraY = 48;
                        cameraVx = 1;
                        cameraVy = 1;
                    }

                    cameraX += cameraVx;
                    cameraY += cameraVy;
                    if (cameraX > cameraMaxX) {
                        cameraX = cameraMaxX;
                        cameraVx = -cameraVx;
                    }

                    if (cameraX < 24) {
                        cameraX = 24;
                        cameraVx = -cameraVx;
                    }

                    if (cameraY < 0) {
                        cameraY = 0;
                        cameraVy = -cameraVy;
                    }

                    if (cameraY > cameraMaxY) {
                        cameraY = cameraMaxY;
                        cameraVy = -cameraVy;
                    }

                    if ((firstVisibleCol = cameraX / GameMap.TILE_SIZE - 1) < 0) {
                        firstVisibleCol = 0;
                    }

                    firstVisibleRow = cameraY / GameMap.TILE_SIZE;
                    lastVisibleCol = firstVisibleCol + visibleCols;
                    lastVisibleRow = firstVisibleRow + visibleRows;
                    if (firstVisibleRow < 0) {
                        firstVisibleRow = 0;
                    }

                    if (lastVisibleRow > GameMap.height - 1) {
                        lastVisibleRow = GameMap.height - 1;
                    }

                    if (menuCurtainTop > menuCurtainTopLimit) {
                        menuCurtainTop -= 4;
                    }

                    if (menuCurtainBottom < menuCurtainBottomLimit) {
                        menuCurtainBottom += 4;
                    }

                    if (menuTextY > menuTextTargetY) {
                        menuTextY -= 2;
                    }
                    break;
                case GameState.LOGO:
                    if (LogoScreen.update()) {
                        SetupPrompt.open();
                        GameState.current = GameState.SETUP_PROMPT;
                    }

                    Viewport.screenWidth = instance.getWidth();
                    Viewport.screenHeight = instance.getHeight();
                    break;
                case GameState.GAME_MENU:
                    switch (GameMenu.update()) {
                        case GameMenu.RESULT_CLOSE:
                            UiFrame.release();
                            ensureMapImages();
                            GameState.current = GameState.PLAYING;
                            hudDirty = true;
                            break;
                        case GameMenu.ITEM_USE_ITEM:
                            InventoryScreen.open();
                            GameState.current = GameState.INVENTORY;
                            break;
                        case GameMenu.ITEM_WORLD_MAP:
                            WorldMap.open(mapId);
                            GameState.current = GameState.WORLD_MAP;
                            break;
                        case GameMenu.ITEM_MUSIC:
                            Sound.setEnabled(Sound.disabled, mapId);
                            break;
                        case GameMenu.ITEM_SAVE_AND_EXIT:
                            if (Player.action != PlayerAction.STAND) {
                                UiFrame.release();
                                ensureMapImages();
                                MessageBox.show(MessageType.GAME_NOTICE);
                                MessageBox.setText("Bạn chỉ có thể lưu và thoát khi nhân vật đang đứng yên.");
                                MessageBox.y = Viewport.viewCenterY - 40;
                                GameState.current = GameState.MESSAGE;
                            } else {
                                running = false;
                            }
                            break;
                    }
                    break;
                case GameState.WORLD_MAP:
                    if (WorldMap.update() == WorldMap.RESULT_CLOSE) {
                        ensureMapImages();
                        GameState.current = GameState.PLAYING;
                    }
                    break;
                case GameState.MESSAGE:
                    switch (MessageBox.update()) {
                        case MessageBox.RESULT_LEFT:
                            onMessageConfirmed();
                            break;
                        case MessageBox.RESULT_RIGHT:
                            if (MessageBox.type == MessageType.TRAVEL_CONFIRM) {
                                GameState.current = GameState.WORLD_MAP;
                            } else if (MessageBox.type == MessageType.SHOP_NOTICE) {
                                GameState.current = GameState.SHOP;
                            }
                            break;
                    }

                    hudDirty = true;
                    break;
                case GameState.INVENTORY:
                    InventoryScreen.update();
                    break;
                case GameState.SHOP:
                    switch (ShopScreen.update()) {
                        case ShopScreen.RESULT_CLOSE:
                            ensureMapImages();
                            GameState.current = GameState.PLAYING;
                            hudDirty = true;
                            break;
                        case ShopScreen.RESULT_SWORD_PURCHASED:
                            onSwordPurchased();
                            break;
                    }
                    break;
                case GameState.SETUP_PROMPT:
                    if (SetupPrompt.done) {
                        SetupPrompt.image = null;
                        startMainMenu();
                    }

                    SetupPrompt.update();
                    break;
                case GameState.TUTORIAL:
                    updateTutorial();
                    break;
                case GameState.UNUSED_12:
                    updateTutorial();
                    break;
            }

            this.repaint();
            this.serviceRepaints();

            while(System.currentTimeMillis() - frameStart < (long)frameDurationMs) {
                Thread.yield();
            }
        }

        saveGame();
        Midlet.instance.notifyDestroyed();
    }

    public static void setupSigns(int map) {
        signCount = 0;
        if (map == 0) {
            signCount = 16;
            allocSigns();
            placeSign(0, "Bộ môn Khí", 1044, 336);
            placeSign(1, "Cô Ayumi", 1044, 347);
            placeSign(2, "Phòng chủ nhiệm", 1212, 336);
            placeSign(3, "Thầy Takanashi", 1212, 347);
            placeSign(4, "Phòng", 1380, 336);
            placeSign(5, "đào tạo", 1380, 347);
            placeSign(6, "Phòng", 1212, 216);
            placeSign(7, "Hiệu trưởng", 1212, 227);
            placeSign(8, "Bộ môn Lực", 1044, 216);
            placeSign(9, "Thầy Hiroyaki", 1044, 227);
            placeSign(10, "Bộ môn Thuật", 1380, 216);
            placeSign(11, "Thầy Honda", 1380, 227);
            placeSign(12, "Kí Túc Xá", 444, 341);
            placeSign(13, "* Khu luyện tập", 72, 341);
            placeSign(14, "Cổng /", 1860, 317);
            placeSign(15, "Bán HP-MP", 612, 341);
        } else if (map == 1) {
            signCount = 2;
            allocSigns();
            placeSign(0, "Trường /", 1356, 173);
            placeSign(1, "* Thác nước", 48, 173);
        } else if (map == 2) {
            signCount = 4;
            allocSigns();
            placeSign(0, "* Lối vào", 60, 245);
            placeSign(1, "Trường Hirosaki", 240, 197);
            placeSign(2, "Lối ra /", 420, 245);
            placeSign(2, "Mua đồ tiền thật", 552, 245);
            placeSign(3, "đường hoa đào /", 1356, 221);
        } else if (map == 3) {
            signCount = 2;
            allocSigns();
            placeSign(0, "* Ngôi làng", 48, 173);
            placeSign(1, "Rừng trúc /", 2808, 173);
        } else if (map == 4) {
            signCount = 2;
            allocSigns();
            placeSign(0, "* Vườn đào", 48, 101);
            placeSign(1, "Rừng tối", 1704, 245);
        } else if (map == 5) {
            signCount = 2;
            allocSigns();
            placeSign(0, "Trường /", 420, 77);
            placeSign(1, "Rừng tối /", 408, 1829);
        } else if (map == 6) {
            signCount = 3;
            allocSigns();
            placeSign(0, "* Thác nước", 72, 101);
            placeSign(1, "Rừng tối /", 504, 53);
            placeSign(2, "Sông Watamoro /", 5916, 197);
        } else {
            if (map == 99) {
                signCount = 1;
                allocSigns();
                placeSign(0, "Trường Hirosaki", 744, 173);
            }

        }
    }

    private static void placeSign(int i, String text, int x, int y) {
        signTexts[i] = text;
        signX[i] = x;
        signY[i] = y;
        signX[i] -= FontGraph.getBoldWidth(text) >> 1;
        signWidth[i] = FontGraph.getBoldWidth(text);
    }

    private static void drawSigns(Graphics g) {
        for(int i = 0; i < signCount; ++i) {
            if (signX[i] + signWidth[i] >= cameraX && signX[i] <= cameraX + Viewport.viewWidth && signY[i] >= cameraY && signY[i] - 14 <= cameraY + Viewport.viewHeight) {
                FontGraph.drawBold(g, signTexts[i], signX[i], signY[i], 0);
            }
        }

    }

    private static void allocSigns() {
        signTexts = new String[signCount];
        signX = new int[signCount];
        signY = new int[signCount];
        signWidth = new int[signCount];
    }

    public static void setupHouses(int map) {
        houseCount = 0;
        if (map == 2) {
            try {
                houseImages = Tile.ensureHouses(houseImages);
            } catch (Exception e) {
            }

            houseCount = 5;
            allocHouses();
            placeHouse(0, 0, 29, 13);
            placeHouse(1, 1, 34, 13);
            placeHouse(2, 2, 39, 13);
            placeHouse(3, 1, 46, 11);
            placeHouse(4, 2, 51, 11);
        }

        if (map == 16) {
            try {
                houseImages = Tile.ensureHouses(houseImages);
            } catch (Exception e) {
            }

            houseCount = 5;
            allocHouses();
            placeHouse(0, 0, 5, 11);
            placeHouse(1, 1, 14, 12);
            placeHouse(2, 2, 33, 10);
            placeHouse(3, 1, 53, 10);
            placeHouse(4, 0, 59, 11);
        }

    }

    private static void placeHouse(int i, int type, int tileX, int tileY) {
        houseTypes[i] = type;
        houseX[i] = tileX * GameMap.TILE_SIZE + 12;
        houseY[i] = tileY * GameMap.TILE_SIZE;
        houseHalfWidth[i] = houseImages[type].getWidth() >> 1;
        houseHeight[i] = houseImages[type].getHeight();
    }

    private static void allocHouses() {
        houseTypes = new int[houseCount];
        houseX = new int[houseCount];
        houseY = new int[houseCount];
        houseHalfWidth = new int[houseCount];
        houseHeight = new int[houseCount];
    }

    private static void ensureMapImages() {
        try {
            tilesetImage = Tile.ensureTileset(tilesetImage);
            waterfallImage = Tile.ensureWaterfall(waterfallImage);
            waterSurfaceImage = Tile.ensureWaterSurface(waterSurfaceImage);
            if (!Settings.lowGraphics) {
                bushImage = Tile.ensureBush(bushImage);
            }

            System.gc();
        } catch (Exception e) {
            Midlet.instance.shutdownApp();
        }
    }

    public static void loadMap(int mapId) {
        try {
            MapConfig.load(mapId);
        } catch (Exception ex) {
            Midlet.instance.shutdownApp();
        }

        ensureMapImages();
    }

    private static void drawTilesLowGraphics(Graphics g) {
        for(int col = firstVisibleCol; col < lastVisibleCol; ++col) {
            for(int row = firstVisibleRow; row < lastVisibleRow; ++row) {
                int tile;
                if ((tile = GameMap.tiles[row * GameMap.width + col] - 1) != -1) {
                    g.drawRegion(tilesetImage, 0, tile * GameMap.TILE_SIZE, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                }

                if ((GameMap.flagsAt(col, row) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                    g.drawRegion(waterfallImage, 0, 24 * (tick % 4), 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                } else if ((GameMap.flagsAt(col, row) & TileFlags.WATER) == TileFlags.WATER) {
                    if ((GameMap.flagsAt(col, row - 1) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                        g.drawRegion(waterfallImage, 0, 24 * (tick % 4), 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(col, row - 1) & TileFlags.DIGGABLE) == TileFlags.DIGGABLE) {
                        g.drawRegion(tilesetImage, 0, 504, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    }

                    g.drawRegion(waterSurfaceImage, 0, (tick % 8 >> 2) * 24, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                }

                if ((GameMap.flagsAt(col, row) & TileFlags.DEEP_WATER) == TileFlags.DEEP_WATER) {
                    if ((GameMap.flagsAt(col, row - 1) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                        g.drawRegion(waterfallImage, 0, 24 * (tick % 4), 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(col, row - 1) & TileFlags.DIGGABLE) == TileFlags.DIGGABLE) {
                        g.drawRegion(tilesetImage, 0, 504, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    }

                    g.drawRegion(tilesetImage, 0, (GameMap.tiles[row * GameMap.width + col] - 1) * GameMap.TILE_SIZE, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                }
            }
        }

    }

    private static void drawTiles(Graphics g) {
        for(int col = firstVisibleCol; col < lastVisibleCol; ++col) {
            for(int row = firstVisibleRow; row < lastVisibleRow; ++row) {
                int tile = GameMap.tiles[row * GameMap.width + col] - 1;
                if ((GameMap.flagsAt(col, row) & TileFlags.PARALLAX) == TileFlags.PARALLAX) {
                    parallaxFromCenter = (parallaxScreenX = col * GameMap.TILE_SIZE - cameraX) - Viewport.viewCenterX;
                    parallaxDrawX = (parallaxScaled = (GameMap.TILE_SIZE - 2) * parallaxFromCenter / GameMap.TILE_SIZE) + Viewport.viewCenterX;
                    g.drawRegion(tilesetImage, 0, tile * GameMap.TILE_SIZE, 24, 24, 0, parallaxDrawX + cameraX, row * GameMap.TILE_SIZE, 0);
                } else if ((GameMap.flagsAt(col, row) & TileFlags.FOREGROUND_BUSH) != TileFlags.FOREGROUND_BUSH && (GameMap.flagsAt(col, row) & TileFlags.FOREGROUND) != TileFlags.FOREGROUND) {
                    if ((GameMap.flagsAt(col, row) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                        g.drawRegion(waterfallImage, 0, 24 * (tick % 4), 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(col, row) & TileFlags.WATER) != TileFlags.WATER && (GameMap.flagsAt(col, row) & TileFlags.DEEP_WATER) != TileFlags.DEEP_WATER) {
                        if ((GameMap.flagsAt(col, row) & TileFlags.BRIDGE_PRESSED) == TileFlags.BRIDGE_PRESSED) {
                            g.drawRegion(tilesetImage, 0, tile * GameMap.TILE_SIZE, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE + 1, 0);
                        } else if (tile != -1) {
                            g.drawRegion(tilesetImage, 0, tile * GameMap.TILE_SIZE, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                        }
                    } else if ((GameMap.flagsAt(col, row - 1) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                        g.drawRegion(waterfallImage, 0, 24 * (tick % 4), 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(col, row - 1) & TileFlags.DIGGABLE) == TileFlags.DIGGABLE) {
                        g.drawRegion(tilesetImage, 0, 504, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    }
                }
            }
        }

    }

    private static void drawForegroundTiles(Graphics g) {
        if (!Settings.lowGraphics) {
            for(int col = firstVisibleCol; col < lastVisibleCol; ++col) {
                for(int row = firstVisibleRow; row < lastVisibleRow; ++row) {
                    if ((GameMap.flagsAt(col, row) & TileFlags.WATER) == TileFlags.WATER) {
                        g.drawRegion(waterSurfaceImage, 0, (tick % 8 >> 2) * 24, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(col, row) & TileFlags.FOREGROUND_BUSH) == TileFlags.FOREGROUND_BUSH) {
                        parallaxFromCenter = (parallaxScreenX = col * GameMap.TILE_SIZE - cameraX) - Viewport.viewCenterX;
                        parallaxDrawX = (parallaxScaled = (GameMap.TILE_SIZE - 1) * parallaxFromCenter / GameMap.TILE_SIZE) + Viewport.viewCenterX;
                        g.drawImage(bushImage, parallaxDrawX + cameraX - 2 + (col + row) % 5, row * GameMap.TILE_SIZE - 3 + row % 5, 0);
                    }

                    if ((GameMap.flagsAt(col, row) & TileFlags.FOREGROUND) == TileFlags.FOREGROUND) {
                        g.drawRegion(tilesetImage, 0, (GameMap.tiles[row * GameMap.width + col] - 1) * GameMap.TILE_SIZE, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE + 2, 0);
                    }

                    if ((GameMap.flagsAt(col, row) & TileFlags.DEEP_WATER) == TileFlags.DEEP_WATER) {
                        g.drawRegion(tilesetImage, 0, (GameMap.tiles[row * GameMap.width + col] - 1) * GameMap.TILE_SIZE, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    }
                }
            }

        }
    }

    private static int alignToTileY(int value) {
        return value / GameMap.TILE_SIZE * GameMap.TILE_SIZE;
    }

    private static int alignToTileX(int value) {
        return value / GameMap.TILE_SIZE * GameMap.TILE_SIZE;
    }

    private static void resetLoadingProgress() {
        loadingProgress = 0;
        loadingTarget = 0;
        unusedLoadingCounter = 0;
    }

    private static void monsterTryAttackPlayer(int i) {
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

    private static void monsterTryAttackGirl(int i) {
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

    private static boolean damageMonster(int i, int amount, int direction) {
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

    private static void onMonsterKilled(int i) {
        gainExp(MonsterStats.exp[Monsters.type[i]]);
        int roll;
        if ((roll = tick % 20) > 15) {
            PotionDrops.spawn(Monsters.x[i], Monsters.y[i], -(Monsters.direction[i] << 3), -5, Monsters.damage[i] << 2, (tick + 1) % 2);
        } else if (roll == 15) {
            MoneyDrops.spawn(Monsters.x[i], Monsters.y[i], -(Monsters.direction[i] << 2), -4, (Monsters.money[i] << 3) + tick % 10);
        } else {
            int amount = ((Monsters.type[i] << 2) / 10 + 1) * 10;
            MoneyDrops.spawn(Monsters.x[i], Monsters.y[i], -(Monsters.direction[i] << 1), -6, amount);
            MoneyDrops.spawn(Monsters.x[i], Monsters.y[i], -(Monsters.direction[i] << 4), -3, amount);
        }

        if (QuestState.current != Quest.NONE && Quest.TARGET_MONSTER[QuestState.current] == Monsters.type[i]) {
            ++QuestState.progress;
            Notice.show(GameText.monsterNames[Quest.TARGET_MONSTER[QuestState.current]] + ": " + QuestState.progress + "#" + Quest.TARGET_COUNT[QuestState.current]);
        }

        if (Monsters.type[i] == QuestState.dropMonster - QuestState.DROP_MONSTER_BASE && tick % 10 < QuestState.dropChance) {
            placeQuestItem(Monsters.x[i], Monsters.y[i]);
            Aura.show(Monsters.x[i], Monsters.y[i] - 28);
        }

    }

    private static void updateMonsters() {
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
                    if (tick % 2 == 0) {
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
                            WaterSplash.spawn(Monsters.x[i], alignToTileY(Monsters.y[i]) + 8);
                        }
                        break;
                    }

                    Monsters.timer[i] = -300;
                    Monsters.state[i] = Monsters.STATE_RESPAWN_WAIT;
                    if (prologueNoRespawn) {
                        Monsters.state[i] = Monsters.STATE_GONE;
                    }

                    Monsters.spawnUsed[Monsters.group[i]][Monsters.spawnPoint[i]] = false;
                    break;
                case Monsters.STATE_ACTIVE:
                    switch (Monsters.type[i]) {
                        case Monsters.TYPE_ONE_EYED_GHOST:
                            if (tick % 4 == 0) {
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

                            monsterTryAttackPlayer(i);
                            if (tick % 9 == 0) {
                                monsterTryAttackGirl(i);
                            }
                            continue;
                        case Monsters.TYPE_SNAIL:
                            monsterPatrolSlow(i);
                            continue;
                        case Monsters.TYPE_DEMON_BAT:
                            Monsters.x[i] += Monsters.vx[i];
                            Monsters.y[i] += Monsters.vy[i];
                            if (Monsters.x[i] - Monsters.rangeA[i] > 40) {
                                Monsters.vx[i] = -(tick % 3 + 1);
                            }

                            if (Monsters.x[i] - Monsters.rangeA[i] < -40) {
                                Monsters.vx[i] = tick % 3 + 1;
                            }

                            if (Monsters.y[i] - Monsters.rangeB[i] > 40) {
                                Monsters.vy[i] = -(tick % 3 + 1);
                            }

                            if (Monsters.y[i] - Monsters.rangeB[i] < -40) {
                                Monsters.vy[i] = tick % 3 + 1;
                            }

                            if (tick % 15 == 0) {
                                Monsters.vx[i] = tick % 4 - 1;
                                Monsters.vy[i] = (tick >> 1) % 4 - 1;
                            }

                            if (tick % 2 == 0) {
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

                            monsterTryAttackPlayer(i);
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

                            if (abs(Monsters.aux1[i]) == 3) {
                                if (Monsters.x[i] > Monsters.rangeB[i]) {
                                    Monsters.direction[i] = -1;
                                } else if (Monsters.x[i] < Monsters.rangeA[i]) {
                                    Monsters.direction[i] = 1;
                                }

                                Monsters.frame[i] = 0;
                            } else {
                                Monsters.frame[i] = 1;
                            }

                            monsterTryAttackPlayer(i);
                            if (tick % 9 == 0) {
                                monsterTryAttackGirl(i);
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

                            monsterTryAttackPlayer(i);
                            if (tick % 9 == 0) {
                                monsterTryAttackGirl(i);
                            }
                            continue;
                        case Monsters.TYPE_SPIDER:
                            if (tick % 4 == 0) {
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

                            monsterTryAttackPlayer(i);
                            continue;
                        case Monsters.TYPE_BEE:
                            monsterHover(i);
                        case Monsters.TYPE_SCARECROW:
                        default:
                            continue;
                        case Monsters.TYPE_TIGER_FISH:
                            Monsters.frame[i]++;
                            if (Monsters.frame[i] > 1) {
                                Monsters.frame[i] = 0;
                            }

                            Monsters.x[i] += tick % 5 * Monsters.direction[i];
                            if (Monsters.x[i] > Monsters.rangeB[i]) {
                                Monsters.direction[i] = -1;
                            } else if (Monsters.x[i] < Monsters.rangeA[i]) {
                                Monsters.direction[i] = 1;
                            }

                            monsterTryAttackPlayer(i);
                            continue;
                        case Monsters.TYPE_WIND_NINJA:
                            monsterPatrolFast(i);
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

                            monsterTryAttackPlayer(i);
                            continue;
                        case Monsters.TYPE_ONE_EYED_GHOST_2:
                            if (tick % 4 == 0) {
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

                            monsterTryAttackPlayer(i);
                            if (tick % 9 == 0) {
                                monsterTryAttackGirl(i);
                            }
                            continue;
                        case Monsters.TYPE_DESERT_CENTIPEDE:
                            monsterPatrolFast(i);
                            continue;
                        case Monsters.TYPE_GOLDEN_BAT:
                            Monsters.x[i] += Monsters.vx[i];
                            if (Monsters.x[i] - Monsters.rangeA[i] > 100) {
                                Monsters.direction[i] = -1;
                                Monsters.vx[i] = Monsters.direction[i] * (tick % 4 + 1);
                                Monsters.x[i] += Monsters.direction[i] << 2;
                            } else if (Monsters.x[i] - Monsters.rangeA[i] < -100) {
                                Monsters.direction[i] = 1;
                                Monsters.vx[i] = Monsters.direction[i] * (tick % 4 + 1);
                                Monsters.x[i] += Monsters.direction[i] << 2;
                            }

                            if (Monsters.y[i] - Monsters.rangeB[i] > 20) {
                                Monsters.vy[i] = -(tick % 4 + 1);
                            } else if (Monsters.y[i] - Monsters.rangeB[i] < -20) {
                                Monsters.vy[i] = tick % 4 + 1;
                            } else {
                                Monsters.vy[i] = 0;
                            }

                            Monsters.y[i] += Monsters.vy[i];
                            if (tick % 2 == 0) {
                                Monsters.frame[i]++;
                                if (Monsters.frame[i] > 1) {
                                    Monsters.frame[i] = 0;
                                    Monsters.y[i] -= 12;
                                } else {
                                    Monsters.y[i] += 12;
                                }
                            }

                            monsterTryAttackPlayer(i);
                            continue;
                        case Monsters.TYPE_RAT:
                            Monsters.frame[i]++;
                            if (Monsters.frame[i] > 1) {
                                Monsters.frame[i] = 0;
                            }

                            if (tick % 2 == 0) {
                                Monsters.x[i] += Monsters.direction[i] << 2;
                            } else {
                                Monsters.x[i] += Monsters.direction[i] << 1;
                            }

                            if (Monsters.x[i] > Monsters.rangeB[i]) {
                                Monsters.direction[i] = -1;
                            } else if (Monsters.x[i] < Monsters.rangeA[i]) {
                                Monsters.direction[i] = 1;
                            }

                            monsterTryAttackPlayer(i);
                            if (tick % 5 == 0) {
                                monsterTryAttackGirl(i);
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

                            if (abs(Monsters.aux1[i]) == 3) {
                                if (Monsters.x[i] > Monsters.rangeB[i]) {
                                    Monsters.direction[i] = -1;
                                } else if (Monsters.x[i] < Monsters.rangeA[i]) {
                                    Monsters.direction[i] = 1;
                                }

                                Monsters.frame[i] = 0;
                            } else {
                                Monsters.frame[i] = 1;
                            }

                            monsterTryAttackPlayer(i);
                            if (tick % 9 == 0) {
                                monsterTryAttackGirl(i);
                            }
                            continue;
                        case Monsters.TYPE_RED_BEE:
                            monsterHover(i);
                            continue;
                        case Monsters.TYPE_SCORPION:
                            monsterPatrolSlow(i);
                            continue;
                        case Monsters.TYPE_FLOWER_DEMON:
                        case Monsters.TYPE_PURPLE_FLOWER_DEMON:
                            if (tick % 4 == 0) {
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

                            monsterTryAttackPlayer(i);
                            if (tick % 9 == 0) {
                                monsterTryAttackGirl(i);
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
                    monsterTryAttackPlayer(i);
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
                                    damageGirl(Monsters.damage[i], Monsters.direction[i]);
                                } else {
                                    damagePlayer(Monsters.damage[i], Monsters.direction[i]);
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
                            damagePlayer(Monsters.damage[i], -Player.facing);
                            Monsters.state[i] = Monsters.STATE_RESTING;
                            Monsters.timer[i] = 0;
                            Monsters.frame[i] = 2;
                            continue;
                        case Monsters.TYPE_DEMON_BAT:
                            if (Monsters.x[i] < Player.x) {
                                Monsters.vx[i] = 3 + tick % 4;
                            } else if (Monsters.x[i] > Player.x) {
                                Monsters.vx[i] = -(3 + tick % 4);
                            }

                            if (Monsters.y[i] < Player.y) {
                                Monsters.vy[i] = tick % 3;
                            } else if (Monsters.y[i] > Player.y) {
                                Monsters.vy[i] = -tick % 3;
                            }

                            Monsters.x[i] += Monsters.vx[i];
                            Monsters.y[i] += Monsters.vy[i];
                            if (tick % 2 == 0) {
                                Monsters.frame[i]++;
                                if (Monsters.frame[i] > 1) {
                                    Monsters.frame[i] = 0;
                                }
                            }

                            if (abs(Monsters.x[i] - Player.x) < 5 && abs(Monsters.y[i] - Player.y) < 5) {
                                Monsters.attackCooldown[i] = 0;
                                damagePlayer(Monsters.damage[i], -Player.facing);
                                Monsters.timer[i] = 0;
                                Monsters.state[i] = Monsters.STATE_ACTIVE;
                            }

                            if (abs(Monsters.x[i] - Player.x) > 160 || abs(Monsters.y[i] - Player.y) > 160) {
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
                                    damageGirl(Monsters.damage[i], Monsters.direction[i]);
                                } else {
                                    damagePlayer(Monsters.damage[i], Monsters.direction[i]);
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
                                    damageGirl(Monsters.damage[i], Monsters.direction[i]);
                                } else {
                                    damagePlayer(Monsters.damage[i], Monsters.direction[i]);
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
                            damagePlayer(Monsters.damage[i], -Player.facing);
                            Monsters.state[i] = Monsters.STATE_ACTIVE;
                            Monsters.timer[i] = 1;
                            continue;
                        case Monsters.TYPE_BEE:
                            monsterChasePlayer(i);
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

                                if (abs(Monsters.x[i] - Player.x) < 16 && abs(Monsters.y[i] - (Player.y - 16)) < 20) {
                                    damagePlayer(Monsters.damage[i], -Player.facing);
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
                                if (abs(Monsters.x[i] + (Monsters.direction[i] << 3) - Player.x) < 13 + Player.halfWidth) {
                                    damagePlayer(Monsters.damage[i], Monsters.direction[i]);
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

                                if (abs(Monsters.x[i] - Player.x) < 16 && abs(Monsters.y[i] - (Player.y - 16)) < 20) {
                                    damagePlayer(Monsters.damage[i], -Player.facing);
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
                                if (abs(Monsters.x[i] - Player.x) < 16 && abs(Monsters.y[i] - (Player.y - 16)) < 20) {
                                    damagePlayer(Monsters.damage[i], -Player.facing);
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
                                damagePlayer(Monsters.damage[i], Monsters.direction[i]);
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
                            monsterHitPlayer(i);
                            continue;
                        case Monsters.TYPE_GOLDEN_BAT:
                            if (Monsters.x[i] < Player.x) {
                                Monsters.vx[i] = 4 + tick % 6;
                            } else if (Monsters.x[i] > Player.x) {
                                Monsters.vx[i] = -(4 + tick % 6);
                            }

                            if (Monsters.y[i] < Player.y) {
                                Monsters.vy[i] = 2 + tick % 4;
                            } else if (Monsters.y[i] > Player.y) {
                                Monsters.vy[i] = -(2 + tick % 4);
                            }

                            Monsters.x[i] += Monsters.vx[i];
                            Monsters.y[i] += Monsters.vy[i];
                            if (tick % 2 == 0) {
                                Monsters.frame[i]++;
                                if (Monsters.frame[i] > 1) {
                                    Monsters.frame[i] = 0;
                                }
                            }

                            if (abs(Monsters.x[i] - Player.x) < 5 && abs(Monsters.y[i] - Player.y) < 5) {
                                damagePlayer(Monsters.damage[i], -Player.facing);
                                Monsters.state[i] = Monsters.STATE_ACTIVE;
                                Monsters.timer[i] = 0;
                                Monsters.direction[i] = Monsters.x[i] > Monsters.rangeA[i] ? -1 : 1;
                                Monsters.vx[i] = Monsters.direction[i] * (tick % 4 + 1);
                                Monsters.attackCooldown[i] = 10;
                            }

                            if (abs(Monsters.x[i] - Player.x) > 130 || abs(Monsters.y[i] - Player.y) > 130) {
                                Monsters.state[i] = Monsters.STATE_ACTIVE;
                                Monsters.timer[i] = 0;
                                Monsters.vx[i] = Monsters.vy[i] = 0;
                                Monsters.direction[i] = Monsters.x[i] > Monsters.rangeA[i] ? -1 : 1;
                                Monsters.vx[i] = Monsters.direction[i] * (tick % 4 + 1);
                                Monsters.attackCooldown[i] = 0;
                            }
                            continue;
                        case Monsters.TYPE_RAT:
                            monsterHitPlayer(i);
                            continue;
                        case Monsters.TYPE_NATIVE:
                            Monsters.timer[i]++;
                            if (Monsters.timer[i] == 3) {
                                if (Monsters.attackingGirl[i]) {
                                    Monsters.attackingGirl[i] = false;
                                    damageGirl(Monsters.damage[i], Monsters.direction[i]);
                                } else {
                                    damagePlayer(Monsters.damage[i], Monsters.direction[i]);
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
                            monsterChasePlayer(i);
                            continue;
                        case Monsters.TYPE_SCORPION:
                        case Monsters.TYPE_FLOWER_DEMON:
                            Monsters.timer[i]++;
                            Monsters.frame[i] = 3;
                            if (Monsters.timer[i] == 3) {
                                Projectiles.spawn(Monsters.x[i] + (Monsters.direction[i] << 3), Monsters.y[i] - 12, Monsters.direction[i] << 3, Monsters.damage[i], 2);
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
                        int spawnIndex = tick % Monsters.spawnX[Monsters.group[i]].length;
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
                            monsterRecoil5(i);
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
                            monsterFlyBack(i);
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
                            monsterRecoil5(i);
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
                            monsterRecoil3(i);
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
                            monsterFlyBack(i);
                            break;
                        case Monsters.TYPE_SCORPION:
                            monsterRecoil3(i);
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

    private static void monsterChasePlayer(int i) {
        if (Monsters.x[i] < Player.x) {
            Monsters.vx[i] = tick % 3 + 3;
            Monsters.direction[i] = 1;
        } else if (Monsters.x[i] > Player.x) {
            Monsters.vx[i] = -tick % 3 - 3;
            Monsters.direction[i] = -1;
        }

        if (Monsters.y[i] < Player.y) {
            Monsters.vy[i] = tick % 3 + 3;
        } else if (Monsters.y[i] > Player.y) {
            Monsters.vy[i] = -tick % 3 - 3;
        }

        Monsters.x[i] += Monsters.vx[i];
        Monsters.y[i] += Monsters.vy[i];
        Monsters.frame[i]++;
        if (Monsters.frame[i] > 1) {
            Monsters.frame[i] = 0;
        }

        if (abs(Monsters.x[i] - Player.x) < 10 && abs(Monsters.y[i] - Player.y) < 10) {
            Monsters.attackCooldown[i] = 0;
            if (Monsters.attackingGirl[i]) {
                Monsters.attackingGirl[i] = false;
                damageGirl(Monsters.damage[i], Monsters.direction[i]);
            } else {
                damagePlayer(Monsters.damage[i], Monsters.direction[i]);
            }

            Monsters.timer[i] = 0;
            Monsters.state[i] = Monsters.STATE_ACTIVE;
        }

        if (abs(Monsters.x[i] - Player.x) > 160 || abs(Monsters.y[i] - Player.y) > 160) {
            Monsters.state[i] = Monsters.STATE_ACTIVE;
            Monsters.timer[i] = 0;
            Monsters.vx[i] = Monsters.vy[i] = 0;
            Monsters.attackCooldown[i] = 0;
        }

    }

    private static void monsterFlyBack(int i) {
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

    private static void monsterHover(int i) {
        Monsters.x[i] += Monsters.vx[i];
        Monsters.y[i] += Monsters.vy[i];
        if (Monsters.x[i] - Monsters.rangeA[i] > 40) {
            Monsters.vx[i] = -(tick % 2 + 1);
        }

        if (Monsters.x[i] - Monsters.rangeA[i] < -40) {
            Monsters.vx[i] = tick % 2 + 1;
        }

        if (Monsters.y[i] - Monsters.rangeB[i] > 40) {
            Monsters.vy[i] = -(tick % 2 + 1);
        }

        if (Monsters.y[i] - Monsters.rangeB[i] < -40) {
            Monsters.vy[i] = tick % 2 + 1;
        }

        if (tick % 15 == 0) {
            Monsters.vx[i] = tick % 3 - 1;
            Monsters.vy[i] = (tick >> 1) % 3 - 1;
        }

        Monsters.frame[i]++;
        if (Monsters.frame[i] > 1) {
            Monsters.frame[i] = 0;
        }

        monsterTryAttackPlayer(i);
        if (tick % 5 == 0) {
            monsterTryAttackGirl(i);
        }

    }

    private static void monsterPatrolFast(int i) {
        if (tick % 4 == 1) {
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

        monsterTryAttackPlayer(i);
    }

    private static void monsterHitPlayer(int i) {
        damagePlayer(Monsters.damage[i], -Player.facing);
        Monsters.state[i] = Monsters.STATE_ACTIVE;
        Monsters.timer[i] = 0;
        Monsters.frame[i] = 2;
    }

    private static void monsterRecoil5(int i) {
        Monsters.timer[i]++;
        Monsters.x[i] -= Monsters.direction[i];
        if (Monsters.timer[i] == 5) {
            Monsters.state[i] = Monsters.STATE_ACTIVE;
        }

    }

    private static void monsterRecoil3(int i) {
        Monsters.timer[i]++;
        Monsters.x[i] -= Monsters.direction[i];
        if (Monsters.timer[i] == 3) {
            Monsters.state[i] = Monsters.STATE_ACTIVE;
        }

    }

    private static void monsterPatrolSlow(int i) {
        if (tick % 4 == 0) {
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

        monsterTryAttackPlayer(i);
        if (tick % 5 == 0) {
            monsterTryAttackGirl(i);
        }

    }

    private static void openShop(int mode) {
        tilesetImage = null;
        ShopScreen.open(mode);
        GameState.current = GameState.SHOP;
    }

    private static void drawShop(Graphics g) {
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        drawHudPanel(g);
        drawHudOverlay(g);
        ShopScreen.draw(g, PotionDrops.sprites);
    }

    private static void drawInventory(Graphics g) {
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        drawHudPanel(g);
        drawHudOverlay(g);
        InventoryScreen.draw(g, PotionDrops.sprites);
    }

    private static void setNextNpcMarker(int npc) {
        pendingMarkerMap = mapId;
        pendingMarkerNpc = npc;
        pendingMarkerX = npcX[npc];
        pendingMarkerY = npcY[npc] - 44;
    }

    public static void setupNpcs(int map) {
        npcCount = 0;
        if (map == 0) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 0);
                Npc.loadSprite(npcSprites, 1);
                Npc.loadSprite(npcSprites, 2);
                Npc.loadSprite(npcSprites, 3);
                Npc.loadSprite(npcSprites, 4);
                Npc.loadSprite(npcSprites, 5);
            } catch (Exception e) {
            }

            npcCount = 7;
            allocNpcs();
            placeNpc(0, 0, 34, 13);
            placeNpc(1, 1, 43, 17);
            placeNpc(2, 2, 50, 17);
            placeNpc(3, 3, 57, 12);
            placeNpc(4, 4, 43, 12);
            placeNpc(5, 5, 77, 15);
            placeNpc(6, 5, 25, 17);
        }

        if (map == 1) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 5);
            } catch (Exception e) {
            }

            npcCount = 1;
            allocNpcs();
            placeNpc(0, 5, 54, 9);
        }

        if (map == 2) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 5);
            } catch (Exception e) {
            }

            npcCount = 1;
            allocNpcs();
            placeNpc(0, 5, 23, 12);
        }

        if (map == 5) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 5);
            } catch (Exception e) {
            }

            npcCount = 1;
            allocNpcs();
            placeNpc(0, 5, 12, 5);
        }

        if (map == 6) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 5);
            } catch (Exception e) {
            }

            npcCount = 1;
            allocNpcs();
            placeNpc(0, 5, 14, 9);
        }

        if (map == 99 || map == 101) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 4);
            } catch (Exception e) {
            }

            npcCount = 1;
            allocNpcs();
            placeNpc(0, 4, 3, 12);
        }

        if (map == 94) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 0);
                Npc.loadSprite(npcSprites, 4);
                Npc.loadSprite(npcSprites, 2);
            } catch (Exception e) {
            }

            npcCount = 3;
            allocNpcs();
            placeNpc(0, 4, 2, 10);
            placeNpc(1, 0, 3, 10);
            placeNpc(2, 2, 4, 10);
        }

    }

    private static void placeNpc(int i, int type, int tileX, int tileY) {
        npcTypes[i] = type;
        npcX[i] = tileX * GameMap.TILE_SIZE + 12;
        npcY[i] = tileY * GameMap.TILE_SIZE;
    }

    private static void allocNpcs() {
        npcX = new int[npcCount];
        npcY = new int[npcCount];
        npcTypes = new int[npcCount];
    }

    private static void drawNpcs(Graphics g) {
        for(int i = 0; i < npcCount; ++i) {
            if (npcX[i] + 10 >= cameraX && npcX[i] - 10 <= cameraX + Viewport.viewWidth && npcY[i] >= cameraY && npcY[i] - 32 <= cameraY + Viewport.viewHeight) {
                g.drawImage(npcSprites[npcTypes[i]][Npc.PART_LEGS], npcX[i], npcY[i], 33);
                g.drawImage(npcSprites[npcTypes[i]][Npc.PART_BODY], npcX[i], npcY[i] - npcBodyOffsetY[npcTypes[i]] + (tick % (15 + npcTypes[i]) > 4 ? 0 : 1), 33);
            }
        }

    }

    /** Phím mềm trái trên hộp thông báo. */
    private static void onMessageConfirmed() {
        switch (MessageBox.type) {
            case MessageType.TRAVEL_NOT_VISITED:
            case MessageType.TRAVEL_ALREADY_HERE:
            case MessageType.NOT_ENOUGH_MONEY:
                GameState.current = GameState.WORLD_MAP;
                break;
            case MessageType.TRAVEL_CONFIRM:
                // Có đúng bằng giá là đủ (bản gốc dùng >= nên bằng giá vẫn bị báo không đủ tiền).
                if (WorldMap.travelCost > Inventory.money) {
                    MessageBox.show(MessageType.NOT_ENOUGH_MONEY);
                } else {
                    Inventory.money -= WorldMap.travelCost;
                    WorldMap.close();
                    UiFrame.release();
                    ensureMapImages();
                    releaseMapResources();
                    Player.checkpointX = Player.x = WorldMap.spawnTileX[WorldMap.selected] * GameMap.TILE_SIZE;
                    Player.checkpointY = Player.y = WorldMap.spawnTileY[WorldMap.selected] * GameMap.TILE_SIZE;
                    Save.pendingSave = true;
                    changeMap(WorldMap.selected);
                }
                break;
            case MessageType.GAME_NOTICE:
            case MessageType.LEVEL_UP:
                UiFrame.release();
                GameState.current = GameState.PLAYING;
                break;
        }
    }

    public static void onSwordPurchased() {
        reloadSlashEffects();
        loadHudIcons();
        ShopScreen.showSwordPurchased();
    }

}
