import assets.*;
import assets.Character;
import core.app.*;
import core.debug.*;
import core.fx.*;
import core.input.*;
import core.item.*;
import core.map.*;
import core.monster.*;
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

public final class b extends Canvas implements Runnable {
    private static int frameDurationMs = 40;
    private static int[][][] aq = new int[][][]{{{0, -13, 31}, {0, -7, 8}, {0, -11, 14}}, {{0, -13, 32}, {0, -7, 8}, {0, -11, 15}}, {{1, -11, 31}, {1, -9, 16}, {6, -1000, 0}}, {{2, -11, 32}, {2, -10, 16}, {6, -1000, 0}}, {{1, -11, 31}, {3, -8, 16}, {6, -1000, 0}}, {{2, -10, 32}, {7, -9, 17}, {6, -1000, 0}}, {{1, -10, 31}, {8, -8, 16}, {6, -1000, 0}}, {{0, -8, 35}, {4, -5, 15}, {2, -10, 21}}, {{0, -8, 34}, {5, -6, 13}, {3, -11, 22}}, {{0, -8, 31}, {6, -13, 9}, {4, -10, 18}}, {{1, -8, 30}, {6, -13, 9}, {5, -8, 17}}, {{2, -10, 30}, {6, -13, 9}, {6, -8, 16}}, {{2, -10, 29}, {6, -13, 9}, {7, -11, 17}}, {{3, -11, 33}, {8, -4, 13}, {8, -15, 25}}};
    private static int[][][] ar = new int[][][]{{{0, -10, 32}, {1, -7, 7}, {1, -11, 15}}, {{0, -10, 33}, {1, -7, 7}, {1, -11, 16}}, {{1, -10, 33}, {2, -10, 11}, {2, -9, 16}}, {{1, -10, 32}, {3, -11, 9}, {3, -11, 16}}, {{1, -10, 34}, {4, -9, 9}, {4, -8, 16}}, {{1, -10, 34}, {5, -11, 11}, {5, -10, 17}}, {{1, -10, 33}, {6, -9, 9}, {6, -8, 16}}, {{0, -9, 36}, {7, -5, 15}, {7, -10, 21}}, {{4, -13, 26}, new int[3], new int[3]}, {{5, -13, 25}, new int[3], new int[3]}, {{6, -12, 26}, new int[3], new int[3]}, {{7, -13, 25}, new int[3], new int[3]}, {{0, -9, 35}, {8, -4, 13}, {8, -14, 27}}, {{0, -9, 32}, {9, -11, 9}, {10, -10, 18}}, {{2, -7, 34}, {9, -11, 9}, {11, -8, 16}}, {{2, -8, 33}, {9, -11, 9}, {12, -8, 15}}, {{2, -7, 33}, {9, -11, 9}, {13, -12, 16}}, {{0, -11, 32}, {9, -11, 9}, {14, -15, 19}}, {{2, -9, 33}, {9, -11, 9}, {15, -13, 20}}, {{2, -9, 32}, {9, -11, 9}, {16, -7, 23}}, {{2, -9, 33}, {9, -11, 9}, {17, -11, 19}}, {{3, -12, 34}, {8, -4, 13}, {8, -15, 25}}, {{0, -10, 28}, new int[3], {19, -8, 12}}, {{0, -9, 35}, {8, -4, 13}, {9, -9, 22}}, {{0, -10, 33}, new int[3], new int[3]}, {{9, -4, 24}, new int[3], new int[3]}, {{10, -4, 22}, new int[3], new int[3]}, {{0, -9, 32}, {8, -4, 9}, {10, -10, 18}}, {{2, -7, 34}, {8, -4, 9}, {11, -8, 16}}, {{2, -8, 33}, {8, -4, 9}, {12, -8, 15}}, {{2, -7, 33}, {8, -4, 9}, {13, -12, 16}}, {{0, -11, 32}, {7, -5, 9}, {14, -15, 19}}, {{2, -9, 33}, {7, -5, 9}, {15, -13, 20}}, {{2, -9, 32}, {7, -5, 9}, {16, -7, 23}}, {{2, -9, 33}, {7, -5, 9}, {17, -11, 19}}, {{0, -9, 32}, {2, -10, 11}, {10, -10, 18}}, {{2, -7, 34}, {3, -11, 9}, {11, -8, 16}}, {{2, -8, 33}, {4, -9, 9}, {12, -8, 15}}, {{2, -7, 33}, {5, -11, 11}, {13, -12, 16}}, {{0, -11, 32}, {2, -10, 11}, {14, -15, 19}}, {{2, -9, 33}, {3, -11, 9}, {15, -13, 20}}, {{2, -9, 32}, {4, -9, 9}, {16, -7, 23}}, {{2, -9, 33}, {5, -11, 11}, {17, -11, 19}}, {{1, -8, 38}, new int[3], {18, -8, 21}}, {{1, -8, 39}, new int[3], {18, -8, 22}}, {{1, -10, 43}, new int[3], {18, -11, 26}}};
    private static int[] as = new int[]{-2, -6, 22, 21, 19, 22, 10, -2, -2, 5, 19};
    private static int[] at = new int[]{9, 22, 25, 17, 26, 37, 36, 49, 50, 52, 36};
    private static int[][][] aI;
    private static int[] aJ;
    private static int[] aK;
    private static int aL;
    private static b instance;
    static boolean b;
    private static int aX;
    public static boolean hudDirty;
    private static int aY;
    private static int aZ;
    private static int ba;
    private static int bb;
    private static int bc;
    private static int bd;
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
    private static int bG;
    private static int bH;
    private static int bI;
    private static int bJ;
    private static int bK;
    private static int bL;
    private static int cp;
    private static int[] cq;
    private static int[] cr;
    private static int[] cs;
    private static int[] ct;
    private static int[] cu;
    private static int[] cv;
    private static int[] cw;
    private static int[] cx;
    private static int[] cy;
    private static int[] cz;
    private static int[] cA;
    private static int[] cB;
    private static int[] cC;
    private static int[] cD;
    private static int[] cE;
    private static int[] cF;
    private static int[] cG;
    private static int[] cH;
    private static int[] cI;
    private static int[] cJ;
    private static int[] cK;
    private static int[] cL;
    private static int cV;
    private static int[] cW;
    private static int[] cX;
    private static int[] cY;
    private static int[] cZ;
    private static int[] da;
    private static int[] db;
    private static boolean[] dc;
    private static boolean dn;
    private static int doSt;
    private static int dp;
    private static int dq;
    private static int dr;
    private static int ds;
    private static boolean[] dt;
    private static int du;
    private static String[][] classmateLines;
    private static boolean dw;
    private static boolean dx;
    private static int dy;
    private static int[] dz;
    private static int[] dA;
    private static int[] dB;
    private static int[] dC;
    private static int[] dD;
    private static int[] dE;
    private static int[] dF;
    private static int[] dG;
    private static int[] dH;
    private static int[] dI;
    private static int[] dJ;
    private static int[] dK;
    private static int[] dL;
    private static int[] dM;
    private static int[] dN;
    private static Image[] arrowIcons;
    private static Image[] motorbikeFrames;
    private static Image itemScroll;
    private static Image[] heroBodies;
    private static Image itemCrate;
    private static Image[] hudFrames;
    private static Image markerIcon;
    private static Image[] npcDog;
    private static Image[] dartFrames;
    private static Image[] girlBodies;
    private static Image[] girlHeads;
    private static Image[] girlLegs;
    private static Image[] heroHeads;
    private static Image[] heroTransformHeads;
    private static Image[] heroLegs;
    private static Image[][] classmateHeads;
    private static Image[][] ninjaBodies;
    private static Image[] ninjaLegs;
    private static Image[] boss0Sprites;
    private static Image[] boss1Sprites;
    private static Image[] boss2Sprites;
    private static Image titleImage;
    private static Image[] slashEffects;
    private static Image[] weaponIcons;
    public static int mapId;
    public static int tick;
    private static int fc;
    private static int fd;
    private static int fe;
    private static int ff;
    public static int z;
    public static int A;
    public static int B;
    public static int C;
    private static int fg;
    private static int fh;
    private static int fi;
    private static int fj;
    private static int fk;
    private static int fl;
    private static int fm;
    public static int D;
    public static int E;
    private static int fn;
    public static int L;
    public static int M;
    private static int fo;
    private static int fp;
    private static int fq;
    private static boolean fr;
    private static int fs;
    private static int ft;
    private static boolean fu;
    private static boolean fv;
    private static boolean fw;
    private static boolean fx;
    private static boolean fB;
    private static int fD;
    private static int fE;
    private static int questItemX;
    private static int questItemY;
    private static int questItemState;
    private static int cameraTargetX;
    private static int cameraTargetY;
    private static int fK;
    private static int fL;
    private static int fM;
    private static int fN;
    private static int cameraX;
    private static int cameraY;
    private static int cameraMaxX;
    private static int cameraMaxY;
    private static boolean cutscenePlaying;
    private static int cutsceneId;
    private static int cutsceneStep;
    public static int Q;
    public static int R;
    private static boolean captionVisible;
    private static String[] captionLines;
    private static int captionY;
    private static int captionTargetY;
    private static int fZ;
    private static boolean ga;
    private static int guideMarkerMap;
    private static int guideMarkerX;
    private static int guideMarkerY;
    private static int ge;
    private static int gf;
    private static int gg;
    private static int gh;
    private static int gi;
    private static int guideMarkerNpc;
    private static boolean gk;
    private static int gl;
    private static int gm;
    private static int gn;
    private static int go;
    private static int gp;
    private static int gq;
    private static int gr;
    private static int gs;
    private static int gt;
    private static int gu;
    private static int gv;
    private static int gw;
    private static int gx;
    private static int gy;
    private static int gz;
    private static int gA;
    private static int gB;
    private static int gC;
    private static int gD;
    private static int gE;
    private static int gF;
    private static int gG;
    private static int gH;
    private static int gI;
    private static int gJ;
    private static int gK;
    private static boolean gN;
    private static int gO;
    private static int gP;
    private static int gQ;
    private static int gR;
    private static int gS;
    private static int gT;
    private static int gU;
    private static int gV;
    private static int gW;
    private static int gX;
    private static int gY;
    private static int gZ;
    private static int ha;
    private static int hb;
    private static boolean hc;
    private static boolean hd;
    private static boolean he;
    private static int hf;
    private static int hg;
    private static int hh;
    private static boolean hi;
    private static int hj;
    private static int hk;
    private static int hl;
    private static int hm;
    private static int hn;
    private static int ho;
    private static int hp;
    private static int hq;
    private static int hr;
    private static int hs;
    private static int ht;
    private static int hu;
    private static int hv;
    private static int hw;
    private static int[][] hx;
    private static int[][] hy;
    private static int[][] hz;
    private static int[][] hA;
    private static int[][] hB;
    private static int[][] hC;
    private static int[] hD;
    private static int[] hE;
    private static int[] hF;
    private static int[] hG;
    private static int[] hH;
    private static int[] hI;
    private static boolean hJ;
    private static int hK;
    private static int hL;
    private static int hM;
    private static int hN;
    private static int hO;
    private static int hP;
    private static int hQ;
    private static int hR;
    private static int hS;
    private static int hT;
    private static int hU;
    private static int hV;
    private static int hW;
    private static int hX;
    private static int hY;
    private static int hZ;
    private static int ia;
    private static int ib;
    private static int ic;
    private static int mapEventId;
    private static boolean mapEventActive;
    private static int mapEventStep;
    private static int ig;
    private static int ih;
    private static int ii;
    private static int ij;
    private static int[] ik;
    private static int[] il;
    private static int[] im;
    private static int[] in;
    private static int[] io;
    private static int[] ip;
    private static int iq;
    private static int ir;
    private static int[] is;
    private static int[] it;
    private static int[] iu;
    private static int[] iv;
    private static int[] iw;
    private static int[] ix;
    private static int[] iy;
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
    private static int jf;
    private static int jg;
    private static int jh;
    private static int ji;
    private static String[] signTexts;
    private static int[] signX;
    private static int[] signY;
    private static int[] signWidth;
    private static int signCount;
    static int loadingTarget;
    private static int jB;
    static int loadingProgress;
    public static boolean U;
    private static Image[][] npcSprites;
    private static int npcCount;
    private static int[] npcX;
    private static int[] npcY;
    private static int[] npcTypes;
    private static int[] lF;
    private static int pendingMarkerMap;
    private static int pendingMarkerX;
    private static int pendingMarkerY;
    private static int pendingMarkerNpc;
    private static Font lP;
    private static int lR;

    static {
        aI = new int[ar.length][3][3];

        int var0;
        for(var0 = 0; var0 < ar.length; ++var0) {
            for(int var1 = 0; var1 < 3; ++var1) {
                for(int var2 = 0; var2 < 3; ++var2) {
                    aI[var0][var1][var2] = ar[var0][var1][var2];
                    if (var1 == 0 && var2 == 0) {
                        switch (ar[var0][var1][var2]) {
                            case 1:
                            case 2:
                                aI[var0][var1][var2] = 1;
                                break;
                            default:
                                aI[var0][var1][var2] = 0;
                        }
                    }
                }
            }
        }

        aJ = new int[]{-1, 2, 1, -2};
        aK = new int[]{-3, 2, -1, 1};
        aL = -1;
        b = true;
        aX = 0;
        bG = -22;
        bH = 18;
        bI = -10;
        bJ = -19;
        bK = -12;
        bL = -26;
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
        fu = true;
        cutscenePlaying = false;
        cutsceneStep = 0;
        captionLines = new String[]{""};
        fZ = 0;
        guideMarkerMap = -1;
        gh = 1;
        gk = false;
        gm = -1;
        hj = 500;
        hk = 60;
        hl = 60;
        hK = 20;
        hL = 60;
        hO = 50;
        mapEventId = 10;
        ij = -1;
        ir = -1;
        Dialogue.visible = false;
        loadingTarget = 0;
        jB = 0;
        loadingProgress = 0;
        Monsters.groupSpriteSets = null;
        String[][] var3 = new String[][]{{"Ta đang bận."}, 
            {"Ngươi đã học được chiêu shuriken cuối cùng, ta ko còn gì dạy ngươi nữa. Đi đi."}};
        lF = new int[]{7, 6, 6, 5, 2, 4};
        pendingMarkerMap = -1;
        lP = Font.getFont(0, 0, 0);
        lR = lP.getHeight() + 6;

    }

    public static void a() {
        int[] var1 = new int[500];
        int var2 = 0;
        PlayTime.checkpoint();
        ++var2;
        var1[0] = PlayTime.savedSeconds;
        ++var2;
        var1[1] = mapId;
        ++var2;
        var1[2] = tick;
        ++var2;
        var1[3] = Story.prologueActive ? 1 : 0;
        ++var2;
        var1[4] = Inventory.money;
        ++var2;
        var1[5] = dw ? 1 : 0;
        ++var2;
        var1[6] = Story.riverMonsterDefeated ? 1 : 0;
        ++var2;
        var1[7] = Story.tonikuDefeated ? 1 : 0;
        ++var2;
        var1[8] = Story.sakuraFound ? 1 : 0;
        ++var2;
        var1[9] = gk ? 1 : 0;
        ++var2;
        var1[10] = gl;
        ++var2;
        var1[11] = gm;
        ++var2;
        var1[12] = ij;
        ++var2;
        var1[13] = Inventory.swordLevel;
        ++var2;
        var1[14] = Skills.dartsLevel2 ? 1 : 0;

        int var0;
        for(var0 = 0; var0 < WorldMap.visited.length; ++var0) {
            var1[var2++] = WorldMap.visited[var0] ? 1 : 0;
        }

        for(var0 = 0; var0 < Inventory.potionCounts.length; ++var0) {
            var1[var2++] = Inventory.potionCounts[var0];
        }

        var1[var2++] = Skills.lightness ? 1 : 0;
        var1[var2++] = Skills.doubleJump ? 1 : 0;
        var1[var2++] = Skills.darts ? 1 : 0;
        var1[var2++] = Skills.burrow ? 1 : 0;
        var1[var2++] = Skills.motorbikeLicense ? 1 : 0;
        var1[var2++] = Skills.doubleStrength ? 1 : 0;
        var1[var2++] = Skills.dive ? 1 : 0;
        var1[var2++] = Skills.runOnWater ? 1 : 0;
        var1[var2++] = Skills.transform ? 1 : 0;
        var1[var2++] = Story.goldenBatQuestDone ? 1 : 0;
        var1[var2++] = Story.tonikuQuestDone ? 1 : 0;
        var1[var2++] = Story.girlWaiting ? 1 : 0;
        var1[var2++] = Story.girlOutingDone ? 1 : 0;
        var1[var2++] = Skills.tripleJump ? 1 : 0;
        var1[var2++] = Skills.sprint ? 1 : 0;
        var1[var2++] = Skills.standOnWater ? 1 : 0;
        var1[var2++] = Skills.hide ? 1 : 0;
        var1[var2++] = Story.gameCompleted ? 1 : 0;
        var1[var2++] = Story.sakuraEscorting ? 1 : 0;
        var1[var2++] = fc;
        var1[var2++] = fd;
        var1[var2++] = fe;
        var1[var2++] = ff;
        var1[var2++] = z;
        var1[var2++] = A;
        var1[var2++] = B;
        var1[var2++] = C;
        var1[var2++] = fg;
        var1[var2++] = fh;
        var1[var2++] = fi;
        var1[var2++] = fj;
        var1[var2++] = fk;
        var1[var2++] = fl;
        var1[var2++] = fm;
        var1[var2++] = D;
        var1[var2++] = E;
        var1[var2++] = fn;
        var1[var2++] = PlayerStats.attack;
        var1[var2++] = PlayerStats.dartDamage;
        var1[var2++] = PlayerStats.hp;
        var1[var2++] = PlayerStats.maxHp;
        var1[var2++] = PlayerStats.mp;
        var1[var2++] = PlayerStats.maxMp;
        var1[var2++] = L;
        var1[var2++] = M;
        var1[var2++] = fo;
        var1[var2++] = fp;
        var1[var2++] = fq;
        var1[var2++] = fs;
        var1[var2++] = ft;
        var1[var2++] = PlayerStats.level;
        var1[var2++] = PlayerStats.exp;
        var1[var2++] = fu ? 1 : 0;
        var1[var2++] = fv ? 1 : 0;
        var1[var2++] = fw ? 1 : 0;
        var1[var2++] = fx ? 1 : 0;
        var1[var2++] = QuestState.current;
        var1[var2++] = QuestState.dropMonster;
        var1[var2++] = QuestState.dropChance;
        var1[var2++] = QuestState.itemFound ? 1 : 0;
        var1[var2++] = QuestState.progress;
        var1[var2++] = fD;
        var1[var2++] = fE;
        var1[var2++] = ig;
        var1[var2++] = ih;
        var1[var2++] = questItemX;
        var1[var2++] = questItemY;
        var1[var2++] = questItemState;
        var1[var2++] = cameraTargetX;
        var1[var2++] = cameraTargetY;
        var1[var2++] = fK;
        var1[var2++] = fL;
        var1[var2++] = fM;
        var1[var2++] = fN;
        var1[var2++] = cameraX;
        var1[var2++] = cameraY;
        var1[var2++] = cameraMaxX;
        var1[var2++] = cameraMaxY;
        var1[var2++] = mapEventActive ? 1 : 0;
        var1[var2++] = mapEventId;
        var1[var2++] = mapEventStep;
        var1[var2++] = guideMarkerMap;
        var1[var2++] = guideMarkerX;
        var1[var2++] = guideMarkerY;
        var1[var2++] = ge;
        var1[var2++] = gf;
        var1[var2++] = gg;
        var1[var2++] = gh;
        var1[var2++] = gi;
        var1[var2++] = guideMarkerNpc;
        var1[var2++] = gn;
        var1[var2++] = go;
        var1[var2++] = gp;
        var1[var2++] = gq;
        var1[var2++] = gr;
        var1[var2++] = gs;
        var1[var2++] = gt;
        var1[var2++] = gu;
        var1[var2++] = gv;
        var1[var2++] = gw;
        var1[var2++] = gx;
        var1[var2++] = gy;
        var1[var2++] = gz;
        var1[var2++] = gA;
        var1[var2++] = gB;
        var1[var2++] = gC;
        var1[var2++] = gD;
        var1[var2++] = gE;
        var1[var2++] = gF;
        var1[var2++] = gG;
        var1[var2++] = gH;
        var1[var2++] = gI;
        var1[var2++] = gJ;
        var1[var2++] = gZ;
        var1[var2++] = hg;
        var1[var2++] = hm;
        var1[var2++] = hM;
        var1[var2++] = hr;
        var1[var2++] = ii;
        if (ii > 0) {
            for(var0 = 0; var0 < io.length; ++var0) {
                var1[var2++] = io[var0];
            }

            for(var0 = 0; var0 < im.length; ++var0) {
                var1[var2++] = im[var0];
            }

            for(var0 = 0; var0 < in.length; ++var0) {
                var1[var2++] = in[var0];
            }

            for(var0 = 0; var0 < ip.length; ++var0) {
                var1[var2++] = ip[var0];
            }

            for(var0 = 0; var0 < ik.length; ++var0) {
                var1[var2++] = ik[var0];
            }

            for(var0 = 0; var0 < il.length; ++var0) {
                var1[var2++] = il[var0];
            }
        }

        var1[var2++] = iq;
        if (iq > 0) {
            var1[var2++] = ir;
            if (iu != null) {
                for(var0 = 0; var0 < iu.length; ++var0) {
                    var1[var2++] = iu[var0];
                }
            }

            if (iy != null) {
                for(var0 = 0; var0 < iy.length; ++var0) {
                    var1[var2++] = iy[var0];
                }
            }

            if (ix != null) {
                for(var0 = 0; var0 < ix.length; ++var0) {
                    var1[var2++] = ix[var0];
                }
            }

            if (iv != null) {
                for(var0 = 0; var0 < iv.length; ++var0) {
                    var1[var2++] = iv[var0];
                }
            }

            if (iw != null) {
                for(var0 = 0; var0 < iw.length; ++var0) {
                    var1[var2++] = iw[var0];
                }
            }

            if (is != null) {
                for(var0 = 0; var0 < is.length; ++var0) {
                    var1[var2++] = is[var0];
                }
            }

            if (it != null) {
                for(var0 = 0; var0 < it.length; ++var0) {
                    var1[var2++] = it[var0];
                }
            }
        }

        int[] var5 = var1;
        int var4;
        byte[] var6 = new byte[(var4 = var1.length) << 2];

        for(int var3 = 0; var3 < var4; ++var3) {
            var6[var3 << 2] = (byte)(var5[var3] >> 24);
            var6[(var3 << 2) + 1] = (byte)(var5[var3] >>> 16);
            var6[(var3 << 2) + 2] = (byte)(var5[var3] >>> 8);
            var6[(var3 << 2) + 3] = (byte)var5[var3];
        }

        Save.saveRecord("ninjasave", var6);
        Save.markSaved();
    }

    public static void b() {
        int[] var0 = a(Save.loadRecord("ninjasave"));
        int var2 = 0;
        ++var2;
        PlayTime.savedSeconds = var0[0];
        ++var2;
        mapId = var0[1];
        ++var2;
        tick = var0[2];
        ++var2;
        Story.prologueActive = var0[3] == 1;
        ++var2;
        Inventory.money = var0[4];
        ++var2;
        dw = var0[5] == 1;
        ++var2;
        Story.riverMonsterDefeated = var0[6] == 1;
        ++var2;
        Story.tonikuDefeated = var0[7] == 1;
        ++var2;
        Story.sakuraFound = var0[8] == 1;
        ++var2;
        gk = var0[9] == 1;
        ++var2;
        gl = var0[10];
        ++var2;
        gm = var0[11];
        ++var2;
        ij = var0[12];
        ++var2;
        Inventory.swordLevel = var0[13];
        ++var2;
        Skills.dartsLevel2 = var0[14] == 1;

        int var1;
        for(var1 = 0; var1 < WorldMap.visited.length; ++var1) {
            WorldMap.visited[var1] = var0[var2++] == 1;
        }

        for(var1 = 0; var1 < Inventory.potionCounts.length; ++var1) {
            Inventory.potionCounts[var1] = var0[var2++];
        }

        Skills.lightness = var0[var2++] == 1;
        Skills.doubleJump = var0[var2++] == 1;
        Skills.darts = var0[var2++] == 1;
        Skills.burrow = var0[var2++] == 1;
        Skills.motorbikeLicense = var0[var2++] == 1;
        Skills.doubleStrength = var0[var2++] == 1;
        Skills.dive = var0[var2++] == 1;
        Skills.runOnWater = var0[var2++] == 1;
        Skills.transform = var0[var2++] == 1;
        Story.goldenBatQuestDone = var0[var2++] == 1;
        Story.tonikuQuestDone = var0[var2++] == 1;
        Story.girlWaiting = var0[var2++] == 1;
        Story.girlOutingDone = var0[var2++] == 1;
        Skills.tripleJump = var0[var2++] == 1;
        Skills.sprint = var0[var2++] == 1;
        Skills.standOnWater = var0[var2++] == 1;
        Skills.hide = var0[var2++] == 1;
        Story.gameCompleted = var0[var2++] == 1;
        Story.sakuraEscorting = var0[var2++] == 1;
        fc = var0[var2++];
        fd = var0[var2++];
        fe = var0[var2++];
        ff = var0[var2++];
        z = var0[var2++];
        A = var0[var2++];
        B = var0[var2++];
        C = var0[var2++];
        fg = var0[var2++];
        fh = var0[var2++];
        fi = var0[var2++];
        fj = var0[var2++];
        fk = var0[var2++];
        fl = var0[var2++];
        fm = var0[var2++];
        D = var0[var2++];
        E = var0[var2++];
        fn = var0[var2++];
        PlayerStats.attack = var0[var2++];
        PlayerStats.dartDamage = var0[var2++];
        PlayerStats.hp = var0[var2++];
        PlayerStats.maxHp = var0[var2++];
        PlayerStats.mp = var0[var2++];
        PlayerStats.maxMp = var0[var2++];
        L = var0[var2++];
        M = var0[var2++];
        fo = var0[var2++];
        fp = var0[var2++];
        fq = var0[var2++];
        fs = var0[var2++];
        ft = var0[var2++];
        PlayerStats.level = var0[var2++];
        PlayerStats.exp = var0[var2++];
        fu = var0[var2++] == 1;
        fv = var0[var2++] == 1;
        fw = var0[var2++] == 1;
        fx = var0[var2++] == 1;
        QuestState.current = var0[var2++];
        QuestState.dropMonster = var0[var2++];
        QuestState.dropChance = var0[var2++];
        QuestState.itemFound = var0[var2++] == 1;
        QuestState.progress = var0[var2++];
        fD = var0[var2++];
        fE = var0[var2++];
        ig = var0[var2++];
        ih = var0[var2++];
        questItemX = var0[var2++];
        questItemY = var0[var2++];
        questItemState = var0[var2++];
        cameraTargetX = var0[var2++];
        cameraTargetY = var0[var2++];
        fK = var0[var2++];
        fL = var0[var2++];
        fM = var0[var2++];
        fN = var0[var2++];
        cameraX = var0[var2++];
        cameraY = var0[var2++];
        cameraMaxX = var0[var2++];
        cameraMaxY = var0[var2++];
        mapEventActive = var0[var2++] == 1;
        mapEventId = var0[var2++];
        mapEventStep = var0[var2++];
        guideMarkerMap = var0[var2++];
        guideMarkerX = var0[var2++];
        guideMarkerY = var0[var2++];
        ge = var0[var2++];
        gf = var0[var2++];
        gg = var0[var2++];
        gh = var0[var2++];
        gi = var0[var2++];
        guideMarkerNpc = var0[var2++];
        gn = var0[var2++];
        go = var0[var2++];
        gp = var0[var2++];
        gq = var0[var2++];
        gr = var0[var2++];
        gs = var0[var2++];
        gt = var0[var2++];
        gu = var0[var2++];
        gv = var0[var2++];
        gw = var0[var2++];
        gx = var0[var2++];
        gy = var0[var2++];
        gz = var0[var2++];
        gA = var0[var2++];
        gB = var0[var2++];
        gC = var0[var2++];
        gD = var0[var2++];
        gE = var0[var2++];
        gF = var0[var2++];
        gG = var0[var2++];
        gH = var0[var2++];
        gI = var0[var2++];
        gJ = var0[var2++];
        if ((gZ = var0[var2++]) <= 0) {
            gZ = ha;
        }

        hg = var0[var2++];
        hm = var0[var2++];
        hM = var0[var2++];
        hr = var0[var2++];
        int var10001 = var2++;
        int var10000 = var0[var10001];
        var10001 = var0[var10001];
        if (var10000 > 0) {
            try {
                for(var1 = 0; var1 < io.length; ++var1) {
                    ++var2;
                }

                for(var1 = 0; var1 < im.length; ++var1) {
                    ++var2;
                }

                for(var1 = 0; var1 < in.length; ++var1) {
                    ++var2;
                }

                for(var1 = 0; var1 < ip.length; ++var1) {
                    ++var2;
                }

                for(var1 = 0; var1 < ik.length; ++var1) {
                    ++var2;
                }

                for(var1 = 0; var1 < il.length; ++var1) {
                    ++var2;
                }
            } catch (Exception var5) {
            }
        }

        if ((iq = var0[var2++]) > 0) {
            ir = var0[var2++];

            try {
                for(var1 = 0; var1 < iu.length; ++var1) {
                    iu[var1] = var0[var2++];
                }

                for(var1 = 0; var1 < iy.length; ++var1) {
                    iy[var1] = var0[var2++];
                }

                for(var1 = 0; var1 < ix.length; ++var1) {
                    ix[var1] = var0[var2++];
                }

                for(var1 = 0; var1 < iv.length; ++var1) {
                    iv[var1] = var0[var2++];
                }

                for(var1 = 0; var1 < iw.length; ++var1) {
                    iw[var1] = var0[var2++];
                }

                for(var1 = 0; var1 < is.length; ++var1) {
                    is[var1] = var0[var2++];
                }

                for(var1 = 0; var1 < it.length; ++var1) {
                    it[var1] = var0[var2++];
                }
            } catch (Exception var4) {
            }
        }

        if (fx) {
            if (heroTransformHeads == null) {
                heroTransformHeads = new Image[4];

                try {
                    heroTransformHeads[0] = Character.loadHeroTransformHead(0);
                    heroTransformHeads[1] = Character.loadHeroTransformHead(1);
                } catch (Exception var3) {
                }

                heroTransformHeads[2] = heroTransformHeads[1];
                heroTransformHeads[3] = heroTransformHeads[0];
            }

            for(var1 = 0; var1 < 4; ++var1) {
                Image var6 = heroHeads[var1];
                heroHeads[var1] = heroTransformHeads[var1];
                heroTransformHeads[var1] = var6;
            }
        }

    }

    private static int[] a(byte[] var0) {
        int var1;
        int[] var3 = new int[(var1 = var0.length) >> 2];

        for(int var2 = 0; var2 < var3.length; ++var2) {
            var3[var2] = (var0[var2 << 2] << 24) + ((var0[(var2 << 2) + 1] & 255) << 16) + ((var0[(var2 << 2) + 2] & 255) << 8) + (var0[(var2 << 2) + 3] & 255);
        }

        return var3;
    }

    public static final int a(int var0) {
        return var0 > 0 ? var0 : -var0;
    }

    private static void gainExp(int amount) {
        if (Leveling.addExp(amount)) {
            FloatingText.spawn("level-up", 2, fe, ff - fh, 0, -2);
            MessageBox.show(MessageType.LEVEL_UP);
            MessageBox.setText("Chúc mừng! Bạn đạt được cấp " + PlayerStats.level + '\n' + "HP: " + PlayerStats.maxHp + '\n' + "MP: " + PlayerStats.maxMp + '\n' + "Sức đánh: " + PlayerStats.getDisplayedAttack(Inventory.swordLevel));
            GameState.current = GameState.MESSAGE;
        } else {
            FloatingText.spawn("+" + amount, 2, fe, ff - fh, 0, -2);
        }

        hudDirty = true;
    }

    private static void u() {
        if (QuestState.current == Quest.AIR_HITS && A != 0) {
            ++QuestState.progress;
            if (QuestState.current >= 20) {
                Notice.show("Thành công 20 hit!");
            } else {
                Notice.show("Hit: " + QuestState.progress);
            }
        }

        int var0 = PlayerStats.rollMeleeDamage(tick, Inventory.swordLevel);
        if (fx) {
            var0 <<= 1;
        }

        if (fd == 1) {
            a(fe + (fm << 3), ff - 12, (fm << 3) + z, A << 1, false);
        } else {
            if (A > 0 && z == 0) {
                A = -2;
            }

            int var1 = A();
            fr = false;
            if (var1 != -1) {
                fr = true;
                k(var1, var0, -fm);
                Blood.spawn(Monsters.x[var1], Monsters.y[var1] - (Monsters.height[var1] >> 1), fm);
                FloatingText.spawn("-" + var0, 0, Monsters.x[var1], Monsters.y[var1] - Monsters.height[var1], 0, -2);
                if (A > 0) {
                    A = -6;
                }

                Q();
            }

            for(var1 = 0; var1 < iq; ++var1) {
                if (a(ff - fj - it[var1]) < 20 && a(fe + (fm << 4) - is[var1]) < 24) {
                    iu[var1] = 2;
                    iv[var1] = fm << 3;
                    Blood.spawn(is[var1], it[var1] - 12, fm);
                    Q();
                    break;
                }
            }

            if (gN && a(ff - gQ) < 30 && a(fe + (fm << 4) - gP) < 40) {
                if (!c(var0, -fm)) {
                    Blood.spawn(fe + (gP - fe >> 1), ff - 12, fm);
                    FloatingText.spawn("-" + var0, 0, gP, gQ - gW, 0, -2);
                }

                Q();
            }

            for(var1 = 0; var1 < cp; ++var1) {
                if (a(ff - cv[var1]) <= 12 && a(fe + (fm << 4) - cu[var1]) < 24) {
                    a(var1, var0, -fm);
                    Blood.spawn(cu[var1], cv[var1] - 15, fm);
                    FloatingText.spawn("-" + var0, 0, cu[var1], cv[var1] - 28, 0, -2);
                    Q();
                    break;
                }
            }

            boolean var10000;
            if (hJ && a(ff - (hQ - 40)) < 30 && a(fe + (fm << 4) - hP) < 40) {
                if ((hM -= var0) <= 0) {
                    hM = 0;
                    ib = 0;
                    ic = 0;
                    hX = 1;
                    MoneyDrops.spawn(hP, hQ, 4, -10, 600);
                    MoneyDrops.spawn(hP, hQ, -4, -17, 600);
                    MoneyDrops.spawn(hP, hQ, -3, -18, 600);
                    MoneyDrops.spawn(hP, hQ, 4, -19, 600);
                    MoneyDrops.spawn(hP, hQ, 0, -10, 600);
                    var10000 = false;
                } else {
                    ic = 50;
                    var10000 = false;
                }

                Blood.spawn(fe + (hP - fe >> 1), ff - 12, fm);
                FloatingText.spawn("-" + var0, 0, hP, hQ - hL, 0, -2);
                Q();
            }

            if (hc) {
                if (a(ff - dA[0]) < 5 && a(fe + (fm << 4) - dz[0]) < 30) {
                    d(var0, fm);
                }

                Q();
            }

            if (hi) {
                if (a(ff - (hq - 30)) < 30 && a(fe + (fm << 4) - hp) < 30) {
                    if ((hm -= var0) <= 0) {
                        hm = 0;
                        hv = 0;
                        ho = 0;
                        hr = 6;
                        MoneyDrops.spawn(hp, hq, 4, -10, 500);
                        MoneyDrops.spawn(hp, hq, -4, -17, 500);
                        MoneyDrops.spawn(hp, hq, -3, -18, 500);
                        MoneyDrops.spawn(hp, hq, 4, -19, 500);
                        MoneyDrops.spawn(hp, hq, 0, -10, 500);
                        var10000 = false;
                    } else {
                        ho = 50;
                        Blood.spawn(hp, hq - 47, fm);
                        FloatingText.spawn("-" + var0, 0, hp, hq - 55, 0, -2);
                        var10000 = false;
                    }
                }

                Q();
            }

        }
    }

    private static void v() {
        int var0;
        int var1;
        if (ih == 1) {
            var0 = gz << 3;
            var1 = ff - go >> 3;
            a(gn + (gz << 3), go - 12, var0, var1 + (var0 - 8), true);
        } else {
            var0 = PlayerStats.attack << 1;
            var1 = gz > 0 ? gn - 20 : gn - 40;
            int var2 = gz > 0 ? gn + 40 : gn + 20;
            int var3 = go - gu - 15;
            int var4 = go + 5;
            int var5 = 0;

            int var10000;
            while(true) {
                if (var5 >= Monsters.count) {
                    var10000 = -1;
                    break;
                }

                if (Monsters.state[var5] != Monsters.STATE_RESPAWN_WAIT && Monsters.x[var5] >= var1 && Monsters.x[var5] <= var2 && Monsters.y[var5] >= var3 && Monsters.y[var5] <= var4) {
                    var10000 = var5;
                    break;
                }

                ++var5;
            }

            var1 = var10000;
            if (var10000 != -1) {
                k(var1, var0, -gz);
                Blood.spawn(Monsters.x[var1], Monsters.y[var1] - (Monsters.height[var1] >> 1), gz);
                FloatingText.spawn("-" + var0, 0, Monsters.x[var1], Monsters.y[var1] - Monsters.height[var1], 0, -2);
            }

            if (gN && a(go - gQ) < 30 && a(gn + (gz << 4) - gP) < 40 && !c(var0, -gz)) {
                Blood.spawn(gn + (gP - gn >> 1), go - 12, gz);
                FloatingText.spawn("-" + var0, 0, gP, gQ - gW, 0, -2);
            }

            for(var1 = 0; var1 < cp; ++var1) {
                if (go == cv[var1] && a(gn + (gz << 4) - cu[var1]) < 24) {
                    a(var1, var0, -gz);
                    Blood.spawn(cu[var1], cv[var1] - 15, gz);
                    FloatingText.spawn("-" + var0, 0, cu[var1], cv[var1] - 28, 0, -2);
                    return;
                }
            }

        }
    }

    private static void w() {
        for(int var0 = 0; var0 < iq; ++var0) {
            if (iu[var0] != 4 && a(fe - is[var0]) < 20 && it[var0] > ff && it[var0] - 24 <= ff) {
                z = 0;
                A = 0;
                ff = it[var0] - 24;
                B = 1;
                fc = -1;
                ir = var0;
                if (iu[var0] == 11) {
                    WaterSplash.spawn(is[var0], it[var0] - 10);
                    int[] var10000 = it;
                    var10000[var0] += 4;
                    var10000 = iw;
                    var10000[var0] += 4;
                    return;
                }
                break;
            }
        }

    }

    private static void x() {
        for(int var0 = 0; var0 < iq; ++var0) {
            if (a(fe - is[var0]) < 20 && a(it[var0] - ff) < 4) {
                z = 0;
                if (fe < is[var0]) {
                    fe = is[var0] - 20;
                } else {
                    fe = is[var0] + 20;
                }

                if (iu[var0] != 4) {
                    iu[var0] = 2;
                    iv[var0] = -fm << 1;
                    return;
                }
            }
        }

    }

    private static void v(int var0) {
        if (a(ff - cv[var0]) <= 24 && a(cu[var0] + (cH[var0] << 3) - fe) < 25 && (cD[var0] == 6 || cD[var0] == 2 || cD[var0] == 3)) {
            cG[var0] = cF[var0] = 0;
            cE[var0] = 4;
        }

    }

    private static void w(int var0) {
        if (!fw) {
            if (a(cu[var0] - fe) < 100 && a(cv[var0] - ff) < 20) {
                switch (cD[var0]) {
                    case 0:
                    case 1:
                    case 4:
                    case 5:
                        cH[var0] = cu[var0] < fe ? 1 : -1;
                        cG[var0] = cF[var0] = 0;
                        cE[var0] = 4;
                        break;
                    case 2:
                    case 3:
                    case 6:
                        cG[var0] = fe;
                        int[] var10000;
                        if (cu[var0] < fe) {
                            var10000 = cG;
                            var10000[var0] -= 10;
                        } else {
                            var10000 = cG;
                            var10000[var0] += 10;
                        }

                        cH[var0] = cG[var0] < fe ? 1 : -1;
                        cE[var0] = 7;
                        cy[var0] = cH[var0] << 3;
                        if (cG[var0] < cK[var0]) {
                            cG[var0] = cK[var0];
                        }

                        if (cG[var0] > cL[var0]) {
                            cG[var0] = cL[var0];
                            return;
                        }
                }
            }

        }
    }

    private static void y() {
        for(int var0 = 0; var0 < iq; ++var0) {
            if (a(fe - is[var0]) < 20 && it[var0] == ff) {
                z = 0;
                if (fe < is[var0]) {
                    fe = is[var0] - 20;
                } else {
                    fe = is[var0] + 20;
                }

                if (iu[var0] != 4) {
                    iu[var0] = 2;
                    iv[var0] = fm << 2;
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
                gm = 3;
                gn = 2808;
                go = 216;
                gz = -1;
                gr = 1;
                gp = 5;
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
                gk = true;
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
                Effect.loadDarts(dartFrames, Effect.DART_LEVEL_2);
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

    private static int A() {
        int var0 = fm > 0 ? fe - 20 : fe - 40;
        int var1 = fm > 0 ? fe + 40 : fe + 20;
        int var2 = ff - fh - 15;
        int var3 = ff + 5;

        for(int var4 = 0; var4 < Monsters.count; ++var4) {
            if (Monsters.state[var4] != Monsters.STATE_RESPAWN_WAIT && Monsters.x[var4] >= var0 && Monsters.x[var4] <= var1 && Monsters.y[var4] >= var2 && Monsters.y[var4] <= var3) {
                return var4;
            }
        }

        return -1;
    }

    private static void releaseMapResources() {
        npcSprites = null;
        System.gc();
        motorbikeFrames = null;
        itemCrate = null;
        boss0Sprites = null;
        System.gc();
        boss1Sprites = null;
        System.gc();
        Monsters.sprites = null;
        System.gc();
        C();
        classmateHeads = null;
        npcDog = null;
        Projectiles.sprites = null;
        System.gc();
    }

    private static void C() {
        ninjaLegs = null;
        ninjaBodies = null;
        System.gc();
    }

    private static void D() {
        int var0;
        for(var0 = 0; var0 < Monsters.count; ++var0) {
            if (Monsters.state[var0] != Monsters.STATE_RESPAWN_WAIT && a(Monsters.y[var0] - go) < 20 && a(Monsters.x[var0] - gn) < 40) {
                gz = Monsters.x[var0] > gn ? 1 : -1;
                gr = 5;
                ih = 0;
                gA = 0;
                gB = 0;
                return;
            }
        }

        for(var0 = 0; var0 < cp; ++var0) {
            if (cE[var0] != 5 && cv[var0] == go && a(cu[var0] - gn) < 40) {
                gz = cu[var0] > gn ? 1 : -1;
                gr = 5;
                ih = 0;
                gA = 0;
                gB = 0;
                return;
            }
        }

        if (a(fe - gn) > 25 && ff != go && B == 5) {
            gz = fe > gn ? 1 : -1;
            gr = 5;
            ih = 1;
            gA = 0;
            gB = 0;
        }
    }

    private static void E() {
        if (gk) {
            if (ff <= go - 24 && GameMap.hasFlag(gn, go - 1, TileFlags.SOLID)) {
                gr = 3;
                gp = 0;
                gq = -7;
                return;
            }

            if (ff <= go - 24 && GameMap.hasFlag(gn, go - 25, TileFlags.SOLID)) {
                gr = 3;
                gp = 0;
                gq = -10;
                return;
            }

            int var0;
            int var1;
            if (gn >= cameraX - 24 && gn <= cameraX + Viewport.viewWidth + 24) {
                if (go < cameraY - 30 || go > cameraY + Viewport.viewHeight + 30) {
                    gz = fe + (fm << 5) > gn ? 1 : -1;
                    var0 = fe;
                    var1 = ff > go ? cameraY - 20 : cameraY + Viewport.viewHeight;
                    gn = var0;
                    go = var1;
                    gr = 3;
                    gp = gz << 2;
                    gq = -4;
                    return;
                }

                if (a(fe + (fm << 5) - gn) <= (B == 5 ? 10 : 60)) {
                    gr = 1;
                } else if (ff >= go) {
                    if (ff > go) {
                        if (GameMap.hasFlag(gn + gz * gt, go, TileFlags.SOLID)) {
                            gz = fe > gn ? 1 : -1;
                            gr = 2;
                            gp = gz * Q;
                            return;
                        }

                        if (GameMap.hasFlag(gn + gz * 48, go + 24, TileFlags.SOLID)) {
                            gr = 3;
                            gp = gz * 7;
                            gq = -5;
                            return;
                        }

                        for(var0 = 1; var0 <= 5 && go + var0 * 24 < GameMap.pixelHeight && !GameMap.hasFlag(gn + gz * 24, go + var0 * 24, TileFlags.WATER); ++var0) {
                            if (GameMap.hasFlag(gn + gz * 24, go + var0 * 24, TileFlags.SOLID)) {
                                gr = 3;
                                gp = gz << 2;
                                gq = -4;
                                return;
                            }

                            if (GameMap.hasFlag(gn + gz * 48, go + var0 * 24, TileFlags.SOLID)) {
                                gr = 3;
                                gp = gz * 6;
                                gq = -4;
                                return;
                            }
                        }

                        if (GameMap.hasFlag(gn + gz * 24, go - 24, TileFlags.SOLID)) {
                            gr = 3;
                            gp = gz * 5;
                            gq = -7;
                            return;
                        }

                        if (GameMap.hasFlag(gn + gz * 48, go - 24, TileFlags.SOLID)) {
                            gr = 3;
                            gp = gz * 6;
                            gq = -8;
                            return;
                        }

                        if (GameMap.hasFlag(gn + gz * 24, go - 48, TileFlags.SOLID)) {
                            gr = 3;
                            gp = gz * 5;
                            gq = -11;
                            return;
                        }
                    } else if (GameMap.hasFlag(gn + gz * gt, go, TileFlags.SOLID)) {
                        gz = fe + (fm << 5) > gn ? 1 : -1;
                        gr = 2;
                        gp = gz * Q;
                        return;
                    }
                } else {
                    if (GameMap.hasFlag(gn + gz * 48, go - 24, TileFlags.SOLID)) {
                        gr = 3;
                        gp = gz * 5;
                        gq = -8;
                        return;
                    }

                    for(var0 = 1; var0 <= 3; ++var0) {
                        if (GameMap.hasFlag(gn + gz * 24, go - var0 * 24, TileFlags.SOLID)) {
                            gr = 3;
                            gp = gz * 6;
                            gq = -5 - var0 * 3;
                            return;
                        }
                    }

                    if (!GameMap.hasFlag(gn + gz * 48, go - 2, TileFlags.WATER)) {
                        if (go + 24 >= GameMap.pixelHeight || GameMap.hasFlag(gn + gz * 48, go + 24, TileFlags.SOLID)) {
                            gr = 3;
                            gp = gz * 7;
                            gq = -4;
                            return;
                        }

                        var0 = 1;

                        while(true) {
                            if (var0 > 5) {
                                return;
                            }

                            if (go + var0 * 24 >= GameMap.pixelHeight || GameMap.hasFlag(gn + gz * 24, go + var0 * 24, TileFlags.WATER)) {
                                break;
                            }

                            if (GameMap.hasFlag(gn + gz * 48, go + var0 * 24, TileFlags.SOLID)) {
                                gr = 3;
                                gp = gz * 6;
                                gq = -4;
                                return;
                            }

                            if (GameMap.hasFlag(gn + gz * 24, go + var0 * 24, TileFlags.SOLID)) {
                                gr = 3;
                                gp = gz << 2;
                                gq = -4;
                                return;
                            }

                            ++var0;
                        }
                    }
                }
            } else {
                gz = fe + (fm << 5) > gn ? 1 : -1;
                var0 = fe > gn ? cameraX - 24 : cameraX + Viewport.viewWidth + 24;
                var1 = ff - 20;
                if (!GameMap.hasFlag(var0, var1, TileFlags.DIGGABLE)) {
                    gn = var0;
                    go = var1;
                    gr = 4;
                    gp = 4;
                    gq = 3;
                    return;
                }

                if (GameMap.hasFlag(gn + gz * gt, go, TileFlags.SOLID)) {
                    gz = fe > gn ? 1 : -1;
                    gr = 2;
                    gp = gz * 12;
                    return;
                }
            }
        }

    }

    private static void a(int var0, int var1) {
        boolean dead = PlayerStats.takeDamage(var0);
        fc = -1;
        hudDirty = true;
        ir = -1;
        if (dead) {
            A = -7;
            z = var1 << 2;
            B = 6;
            ij = -1;
        } else if (ij == -1 && B != 23) {
            z = var1 << 2;
            A = -2;
            D = 0;
            E = 0;
            B = 7;
        }

        Blood.spawn(fe, ff - 12, -fm);
        FloatingText.spawn("-" + var0, 0, fe, ff - fh, 0, -2);
    }

    private static void b(int var0, int var1) {
        ig = -1;
        gp = var1 << 2;
        gq = -2;
        gA = 0;
        gB = 0;
        gr = 7;
        Blood.spawn(gn, go - 12, -gz);
        FloatingText.spawn("-" + var0, 0, gn, go - gu, 0, -2);
    }

    private static boolean a(int var0, int var1, int var2) {
        int[] var10000 = cr;
        var10000[var0] -= var1;
        if (cE[var0] != 1 && cE[var0] != 5) {
            if (cr[var0] <= 0) {
                cr[var0] = 0;
                cH[var0] = var2;
                cy[var0] = 0;
                cz[var0] = 0;
                cA[var0] = -(var2 << 3);
                cB[var0] = -6;
                cF[var0] = 0;
                cq[var0] = 0;
                cE[var0] = 1;
                gainExp(NinjaStats.exp[cD[var0]]);
                MoneyDrops.spawn(cu[var0], cv[var0], -(cH[var0] << 2), -4, NinjaStats.money[cD[var0]] + tick % 10);
                if (tick % 20 > 5) {
                    PotionDrops.spawn(cu[var0], cv[var0], -(cH[var0] << 3), -5, NinjaStats.damage[cD[var0]] << 2, (tick + 1) % 2);
                }

                return true;
            } else {
                cF[var0] = 0;
                cH[var0] = var2;
                cq[var0] = 100;
                cE[var0] = 6;
                return false;
            }
        } else {
            return true;
        }
    }

    private static boolean c(int var0, int var1) {
        if (gS == 1 || gS == 2) {
            if ((gZ -= var0) <= 0) {
                gZ = 0;
                gX = -5;
                gR = -(var1 << 3);
                hb = 0;
                gS = 6;
                MoneyDrops.spawn(gP, gQ, 4, -6, 50);
                MoneyDrops.spawn(gP, gQ, -5, -7, 50);
                MoneyDrops.spawn(gP, gQ, -4, -8, 50);
                MoneyDrops.spawn(gP, gQ, 5, -9, 50);
                MoneyDrops.spawn(gP, gQ, 0, -10, 50);
                if (QuestState.current == Quest.KILL_WILD_BOAR) {
                    placeQuestItem(gP, gQ);
                }

                return true;
            }

            gX = 0;
            gT = var1;
            gS = 7;
            hb = 10;
            gU = 0;
            if (var0 <= PlayerStats.dartDamage) {
                gY = 0;
            } else {
                gY = 2 + tick % 5;
            }
        }

        return false;
    }

    private static void d(int var0, int var1) {
        hg -= var0;
        dI[0] = -1;
        if (hg <= 0) {
            hg = 0;
        } else {
            dB[0] = var1 << 2;
            dC[0] = -2;
            dE[0] = 0;
            dF[0] = 0;
            dD[0] = 7;
            Blood.spawn(dz[0], dA[0] - 12, fm);
            FloatingText.spawn("-" + var0, 0, dz[0], dA[0] - 32, 0, -2);
        }
    }

    public static void e() {
        if (arrowIcons == null) {
            arrowIcons = new Image[4];

            for(int var0 = 0; var0 < 4; ++var0) {
                try {
                    arrowIcons[var0] = Ui.loadArrow(var0);
                } catch (Exception var1) {
                }
            }
        }

    }

    public static final void c(int var0) {
        ii = 0;
        int var1 = ij == -1 ? 0 : 1;
        if ((var0 == 0 || var0 == 1 || var0 == 7 || var0 == 5 || var0 == 18 || var0 >= 94) && var1 == 1) {
            ii = 1;
            F();
            b(0, fe, ff);
            ij = 0;
            im[ij] = fm;
        }

        if (var0 == 2) {
            d(var1, 18, 12);
        }

        if (var0 == 3) {
            d(var1, 2, 9);
        }

        if (var0 == 4) {
            d(var1, 3, 6);
        }

        if (var0 == 6) {
            d(var1, 12, 9);
        }

        if (var0 == 8) {
            d(var1, 3, 12);
        }

        if (var0 == 9) {
            d(var1, 2, 11);
        }

        if (var0 == 10) {
            d(var1, 3, 12);
        }

        if (var0 == 11) {
            d(var1, 5, 6);
        }

        if (var0 == 12) {
            d(var1, 4, 8);
        }

        if (var0 == 13) {
            d(var1, 16, 11);
        }

        if (var0 == 14) {
            d(var1, 18, 7);
        }

        if (var0 == 15) {
            d(var1, 1, 11);
        }

        if (var0 == 16) {
            d(var1, 7, 11);
        }

        if (var0 == 17) {
            d(var1, 14, 12);
        }

    }

    private static void F() {
        try {
            motorbikeFrames = Effect.ensureMotorbike(motorbikeFrames);
        } catch (Exception var1) {
        }

        ik = new int[ii];
        il = new int[ii];
        im = new int[ii];
        in = new int[ii];
        io = new int[ii];
        ip = new int[ii];
    }

    private static void b(int var0, int var1, int var2) {
        ik[var0] = var1 * GameMap.TILE_SIZE + 12;
        il[var0] = var2 * GameMap.TILE_SIZE;
        in[var0] = 0;
        im[var0] = 1;
    }

    public static void g() {
        try {
            itemScroll = Item.loadScroll();
        } catch (Exception var0) {
        }
    }

    public static final void d(int var0) {
        iq = 0;
        if (var0 == 1) {
            iq = 2;
            G();
            c(0, 21, 9);
            c(1, 13, 8);
        }

        if (var0 == 11) {
            iq = 2;
            G();
            c(0, 33, 7);
            c(1, 44, 10);
        }

        if (var0 == 5) {
            iq = 2;
            G();
            c(0, 5, 46);
            c(1, 15, 46);
        }

        if (var0 == 97) {
            iq = 4;
            G();
            c(0, 3, 6);
            c(1, 7, 4);
            c(2, 12, 3);
            c(3, 22, 6);
        }

    }

    private static void G() {
        try {
            itemCrate = Item.ensureCrate(itemCrate);
        } catch (Exception var0) {
        }

        is = new int[iq];
        it = new int[iq];
        iu = new int[iq];
        iv = new int[iq];
        iw = new int[iq];
        ix = new int[iq];
        iy = new int[iq];
    }

    private static void c(int var0, int var1, int var2) {
        is[var0] = var1 * GameMap.TILE_SIZE + 12;
        it[var0] = var2 * GameMap.TILE_SIZE + 24;
        iu[var0] = 1;
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
        cameraX = cameraTargetX = fe - Viewport.viewCenterX + cameraLeadX * fm;
        cameraY = cameraTargetY = ff - cameraOffsetY;
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

    public static final void i() {
        fc = -1;
        if (heroBodies == null) {
            try {
                heroBodies = new Image[20];

                int var0;
                for(var0 = 0; var0 < 20; ++var0) {
                    heroBodies[var0] = Character.loadHeroBody(var0);
                }

                heroHeads = new Image[11];

                for(var0 = 0; var0 < 11; ++var0) {
                    heroHeads[var0] = Character.loadHeroHead(var0);
                }

                heroLegs = new Image[10];

                for(var0 = 0; var0 < 10; ++var0) {
                    heroLegs[var0] = Character.loadHeroLegs(var0);
                }
            } catch (Exception var1) {
            }
        }

        reloadSlashEffects();
        fg = 22;
        fh = 32;
        fi = fg >> 1;
        fj = fh >> 1;
        fk = fg >> 2;
        fl = fh >> 2;
        fD = -1;
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

                    for(int var0 = 0; var0 < 2; ++var0) {
                        hudFrames[var0] = Ui.loadHud(var0);
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
            } catch (IOException var1) {
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
        } catch (Exception var0) {
        }
    }

    public static void e(int var0) {
        dn = false;
        if (var0 == 0) {
            npcDog = Npc.ensureDog(npcDog);

            dn = true;
            doSt = 348;
            dp = 408;
            dq = 1;
            dr = 252;
            ds = 708;
        }

    }

    public static final void m() {
        int var0;
        if (dartFrames == null) {
            dartFrames = Effect.createDartFrames();
            Effect.loadDarts(dartFrames, Skills.dartsLevel2 ? Effect.DART_LEVEL_2 : Effect.DART_LEVEL_1);
        }

        cV = 10;
        cW = new int[10];
        cX = new int[cV];
        cY = new int[cV];
        cZ = new int[cV];
        da = new int[cV];
        dc = new boolean[cV];
        db = new int[cV];

        for(var0 = 0; var0 < cV; ++var0) {
            cY[var0] = -1;
        }

    }

    private static void changeMap(int var0) {
        GameState.current = GameState.LOADING;
        mapId = var0;
        Midlet.nextGameState = GameState.PLAYING;
        resetLoadingProgress();
        (new Thread(Midlet.instance)).start();
    }

    public static final void f(int var0) {
        if (gk) {
            gm = var0;
        }

        if (gm == var0) {
            ig = -1;
            if (girlBodies == null) {
                try {
                    girlBodies = new Image[8];

                    for(var0 = 0; var0 < 8; ++var0) {
                        girlBodies[var0] = Character.loadGirlBody(var0);
                    }

                    girlHeads = new Image[3];

                    for(var0 = 0; var0 < 3; ++var0) {
                        girlHeads[var0] = Character.loadGirlHead(var0);
                    }

                    girlLegs = new Image[9];

                    for(var0 = 0; var0 < 9; ++var0) {
                        girlLegs[var0] = Character.loadGirlLegs(var0);
                    }
                } catch (Exception var1) {
                }
            }

            gt = 22;
            gu = 32;
            gv = gt >> 1;
            gw = gu >> 1;
            gx = gt >> 2;
            gy = gu >> 2;
            if (gk) {
                gn = fe - fm * 10;
                go = ff;
                gz = fm;
                gr = 4;
            }

        }
    }

    public static void setupClassmates(int var0) {
        du = 0;
        dx = false;
        if (var0 == 0) {
            du = 7;
            J();
            placeClassmate(0, 0, 14, 17, 130, 11, 22, DialogueText.schoolClassmate0);
            placeClassmate(1, 1, 24, 17, 160, 15, 30, DialogueText.schoolClassmate1);
            if (Skills.transform) {
                placeClassmate(2, 6, 17, 12, 200, 7, 29, DialogueText.schoolClassmate2Assassin);
            } else {
                placeClassmate(2, 2, 17, 12, 200, 7, 29, DialogueText.schoolClassmate2);
            }

            placeClassmate(3, 3, 48, 17, 200, 44, 63, DialogueText.schoolClassmate3);
            placeClassmate(4, 4, 46, 12, 200, 40, 54, DialogueText.schoolClassmate4);
            placeClassmate(5, 5, 38, 17, 200, 34, 49, DialogueText.schoolClassmate5);
            placeClassmate(6, 6, 58, 12, 200, 46, 60, DialogueText.schoolClassmate6);
            y(7);
        }

        if (var0 == 1) {
            du = 3;
            J();
            placeClassmate(0, 5, 35, 9, 40, 33, 36, DialogueText.trainingClassmate0);
            if (Skills.transform) {
                placeClassmate(1, 6, 25, 10, 40, 22, 28, DialogueText.trainingClassmate1Assassin);
            } else {
                placeClassmate(1, 2, 25, 10, 40, 22, 28, DialogueText.trainingClassmate1);
            }

            placeClassmate(2, 1, 6, 9, 40, 2, 10, DialogueText.trainingClassmate2);
            y(7);
        }

        if (var0 == 2) {
            du = 7;
            J();
            placeClassmate(0, 3, 16, 12, 60, 14, 21, DialogueText.villageClassmate0);
            placeClassmate(1, 4, 8, 12, 50, 2, 15, DialogueText.villageClassmate1);
            if (Skills.transform) {
                placeClassmate(2, 6, 29, 13, 200, 27, 40, DialogueText.villageClassmate2Assassin);
            } else {
                placeClassmate(2, 2, 29, 13, 200, 27, 40, DialogueText.villageClassmate2);
            }

            placeClassmate(3, 5, 46, 13, 200, 41, 49, DialogueText.villageClassmate3);
            placeClassmate(4, 1, 46, 11, 200, 43, 53, DialogueText.villageClassmate4);
            placeClassmate(5, 0, 54, 11, 200, 50, 58, DialogueText.villageClassmate5);
            placeClassmate(6, 6, 34, 13, 200, 28, 48, DialogueText.villageClassmate6);
            y(7);
        }

        String[] var1;
        if (var0 == 99 || var0 == 101) {
            du = 7;
            J();
            var1 = DialogueText.tutorialClassmates;
            placeClassmate(0, 0, 8, 13, 0, 0, 1000, var1);
            placeClassmate(1, 1, 5, 13, 0, 0, 1000, var1);
            placeClassmate(2, 6, 6, 13, 0, 0, 1000, var1);
            placeClassmate(3, 2, 7, 13, 0, 0, 1000, var1);
            placeClassmate(4, 4, 6, 13, 0, 0, 1000, var1);
            placeClassmate(5, 5, 5, 13, 0, 0, 1000, var1);
            placeClassmate(6, 3, 8, 13, 0, 0, 1000, var1);
            y(7);
        }

        if (var0 == 94) {
            du = 7;
            J();
            var1 = DialogueText.graduationClassmates;
            placeClassmate(0, 0, 5, 12, 0, 0, 1000, var1);
            placeClassmate(1, 1, 5, 12, 0, 0, 1000, var1);
            placeClassmate(2, 6, 6, 12, 0, 0, 1000, var1);
            placeClassmate(3, 7, 3, 12, 0, 0, 1000, var1);
            placeClassmate(4, 4, 6, 12, 0, 0, 1000, var1);
            placeClassmate(5, 5, 5, 12, 0, 0, 1000, var1);
            placeClassmate(6, 3, 8, 12, 0, 0, 1000, var1);
            y(8);
        }

    }

    private static void J() {
        dz = new int[du];
        dA = new int[du];
        dB = new int[du];
        dC = new int[du];
        dD = new int[du];
        dE = new int[du];
        dJ = new int[du];
        dF = new int[du];
        dG = new int[du];
        dH = new int[du];
        dI = new int[du];
        dK = new int[du];
        dL = new int[du];
        dM = new int[du];
        dN = new int[du];
        classmateLines = new String[du][];
    }

    private static void y(int var0) {
        if (classmateHeads == null) {
            classmateHeads = new Image[var0][2];

            for(int var1 = 0; var1 < var0; ++var1) {
                for(int var2 = 0; var2 < 2; ++var2) {
                    try {
                        classmateHeads[var1][var2] = Character.loadClassmateHead(var1, var2);
                    } catch (Exception var3) {
                    }
                }
            }
        }

    }

    private static void placeClassmate(int var0, int var1, int var2, int var3, int var4, int var5, int var6, String[] var7) {
        dz[var0] = var2 * 24 + (var0 << 2);
        dA[var0] = var3 * 24;
        dB[var0] = 0;
        dC[var0] = 0;
        dD[var0] = 1;
        dJ[var0] = -1;
        dE[var0] = 0;
        dF[var0] = (var0 + 1) * 20 + var0;
        dG[var0] = 0;
        dH[var0] = var1;
        dI[var0] = -1;
        dL[var0] = -1;
        dM[var0] = var5 * 24 + 12;
        dN[var0] = var6 * 24 + 12;
        dK[var0] = var4;
        classmateLines[var0] = var7;
    }

    private static void K() {
        GameState.current = GameState.LOADING;
        resetLoadingProgress();
        Midlet.nextGameState = GameState.MAIN_MENU;
        mapId = 99;

        try {
            titleImage = Ui.loadTitle();
        } catch (Exception var1) {
        }

        bc = Viewport.screenHeight - 30;
        bd = Viewport.screenHeight;
        aY = 30;
        aZ = Viewport.screenHeight - 50;
        ba = bb = aY + aZ >> 1;
        if (Save.isSaved()) {
            aX = 1;
        }

        (new Thread(Midlet.instance)).start();
    }

    public static void h(int var0) {
        cp = 0;
        if (var0 == 9) {
            cp = 6;
            dt = new boolean[]{false, false, true, false, false, false, false};
            L();
            b(0, 10, 12, 9, 12, 2);
            b(1, 7, 8, 6, 8, 2);
            b(2, 30, 8, 27, 33, 2);
            b(3, 29, 12, 27, 33, 2);
            b(4, 45, 5, 42, 50, 2);
            b(5, 64, 9, 62, 66, 2);
        }

        if (var0 == 10) {
            cp = 12;
            dt = new boolean[]{true, false, true, false, false, false, false};
            L();
            b(0, 5, 8, 4, 6, 2);
            b(1, 6, 12, 5, 9, 0);
            b(2, 16, 12, 9, 21, 2);
            b(3, 28, 15, 26, 29, 0);
            b(4, 25, 15, 22, 27, 2);
            b(5, 16, 25, 14, 17, 0);
            b(6, 4, 29, 2, 6, 0);
            b(7, 8, 38, 2, 28, 2);
            b(8, 14, 38, 2, 28, 2);
            b(9, 20, 38, 2, 28, 2);
            b(10, 31, 30, 28, 37, 2);
            b(11, 33, 30, 28, 37, 0);
        }

        if (var0 == 11) {
            cp = 8;
            dt = new boolean[]{true, true, true, false, false, false, false};
            L();
            b(0, 6, 11, 3, 7, 1);
            b(1, 19, 12, 18, 20, 0);
            b(2, 33, 8, 28, 36, 2);
            b(3, 53, 11, 53, 54, 1);
            b(5, 54, 11, 53, 54, 0);
            b(4, 68, 12, 62, 79, 2);
            b(6, 71, 12, 70, 72, 0);
            b(7, 72, 8, 70, 73, 1);
        }

        if (var0 == 16) {
            cp = 4;
            dt = new boolean[]{false, false, false, true, false, false, false};
            L();
            b(0, 5, 11, 3, 10, 3);
            b(1, 26, 12, 24, 29, 3);
            b(2, 47, 10, 30, 55, 3);
            b(3, 60, 11, 56, 64, 3);
        }

        if (var0 == 17) {
            cp = 8;
            dt = new boolean[]{false, false, false, true, true, false, false};
            L();
            b(0, 11, 8, 9, 13, 3);
            b(1, 20, 6, 18, 21, 4);
            b(2, 20, 11, 18, 21, 3);
            b(3, 26, 3, 24, 29, 3);
            b(4, 26, 8, 24, 29, 4);
            b(5, 26, 13, 24, 29, 4);
            b(6, 33, 6, 32, 35, 3);
            b(7, 33, 10, 32, 35, 4);
        }

        if (var0 == 18) {
            cp = 8;
            dt = new boolean[]{false, false, false, false, true, true, true};
            L();
            b(0, 2, 8, 1, 4, 5);
            b(1, 2, 12, 1, 5, 6);
            b(2, 18, 12, 12, 23, 6);
            b(3, 18, 8, 15, 21, 5);
            b(4, 42, 9, 38, 45, 4);
            b(5, 42, 13, 34, 67, 6);
            b(6, 62, 13, 34, 67, 6);
            b(7, 60, 9, 57, 64, 4);
        }

    }

    private static void L() {
        boolean var0 = false;
        if (ninjaLegs == null) {
            try {
                ninjaLegs = Mob.loadNinjaLegs();
                ninjaBodies = Mob.loadNinjaBodies(dt);
            } catch (Exception var2) {
            }
        }

        cs = new int[cp];
        ct = new int[cp];
        cu = new int[cp];
        cv = new int[cp];
        cw = new int[cp];
        cx = new int[cp];
        cD = new int[cp];
        cE = new int[cp];
        cF = new int[cp];
        cG = new int[cp];
        cH = new int[cp];
        cI = new int[cp];
        cJ = new int[cp];
        cC = new int[cp];
        cK = new int[cp];
        cL = new int[cp];
        cy = new int[cp];
        cz = new int[cp];
        cA = new int[cp];
        cB = new int[cp];
        cr = new int[cp];
        cq = new int[cp];
    }

    private static void b(int var0, int var1, int var2, int var3, int var4, int var5) {
        cs[var0] = var1 * 24 + 12;
        ct[var0] = var2 * 24;
        cu[var0] = cs[var0];
        cv[var0] = ct[var0];
        cw[var0] = cu[var0];
        cx[var0] = cv[var0] - 9;
        cD[var0] = var5;
        cE[var0] = 3;
        cF[var0] = var0 << 1;
        cG[var0] = 0;
        cH[var0] = 1;
        cI[var0] = 0;
        cJ[var0] = 0;
        cC[var0] = 0;
        cK[var0] = var3 * 24 + 12;
        cL[var0] = var4 * 24 + 12;
        cr[var0] = NinjaStats.maxHp[cD[var0]];
    }

    public static void i(int var0) {
        ha = 1200;
        gZ = 1200;
        gO = 30;
        gN = false;
        // Map không có boss rừng trúc: xóa trạng thái boss cũ (bản gốc giữ nguyên gS, nên
        // sự kiện map khác kiểm tra gS == 22 vẫn thấy boss "vừa bị hạ").
        gS = 0;
        int var1;
        if (var0 == 4) {
            gN = true;
            boss0Sprites = Mob.ensureBoss0(boss0Sprites);

            gV = boss0Sprites[0].getWidth();
            gW = boss0Sprites[0].getHeight();
            gP = 55 * GameMap.TILE_SIZE + 12;
            gQ = 13 * GameMap.TILE_SIZE;
            gS = 1;
            gT = -1;
            gU = 0;
        }

        if (var0 == 5) {
            gN = true;
            boss0Sprites = Mob.ensureBoss0(boss0Sprites);

            gV = boss0Sprites[0].getWidth();
            gW = boss0Sprites[0].getHeight();
            gP = 8 * GameMap.TILE_SIZE + 12;
            gQ = 56 * GameMap.TILE_SIZE;
            gS = 1;
            gT = -1;
            gU = 0;
        }

    }

    public static void j(int var0) {
        hJ = false;
        if (var0 == 97 && !Story.riverMonsterDefeated) {
            hJ = true;
            boss1Sprites = Mob.loadBoss1();

            hP = 168;
            hQ = 226;
            hZ = 144;
            ia = 480;
            hX = 2;
            hY = -1;
            hN = 8000;
            hM = 8000;
        }

    }

    public static void k(int var0) {
        hi = false;
        if (var0 == 95 && !Story.sakuraFound) {
            hi = true;
            boss2Sprites = new Image[6];

            try {
                boss2Sprites[0] = Mob.loadBossPart(2, 0);
                boss2Sprites[1] = Mob.loadBossPart(2, 1);
                boss2Sprites[2] = Mob.loadBossPart(2, 2);
                boss2Sprites[3] = boss2Sprites[1];
                boss2Sprites[4] = Mob.loadBossPart(2, 3);
                boss2Sprites[5] = Character.loadClassmateHead(2, 0);
            } catch (Exception var1) {
            }

            hp = 192;
            hq = 264;
            ht = 120;
            hu = 504;
            hr = 0;
            hs = -1;
            hn = 90000;
            hm = 90000;
            hD = new int[6];
            hE = new int[6];
            hH = new int[6];
            hI = new int[6];
            hF = new int[]{20, 10, 0, -10, -16, 2};
            hG = new int[]{-16, 0, -12, 0, -18, -42};
            hx = new int[][]{new int[6], {0, 0, 1, 0, 1, 1}};
            hy = new int[][]{new int[6], {1, 0, 1, 0, 2, 1}};
            hz = new int[][]{{6, -1, 1, 1, 5, 1}, {14, -1, 2, 1, 12, 2}};
            hA = new int[][]{{1, 0, 0, 0, 1, 0}, {2, 0, 1, 0, 2, 1}};
            hB = new int[][]{{-3, 3, 0, -3, 3, 0}, new int[6], {3, -3, 0, 3, -3, 0}};
            hC = new int[][]{{-1, -2, 1, 0, 1, 1}, new int[6], {1, 0, 1, -2, -1, 1}};
        }

    }

    public static void l(int var0) {
        hc = false;
        if (var0 == 96 && !Story.tonikuDefeated) {
            hc = true;
            du = 1;
            J();
            placeClassmate(0, 2, 5, 11, 200, 4, 22, (String[])null);
            y(3);
            hh = 15000;
            hg = 15000;
            hf = 120;
            du = 0;
            dD[0] = 0;
            dJ[0] = 1;
        }

    }

    public static void m(int var0) {
        if (var0 == 94) {
            dw = true;
            fe = 24;
            ff = 288;
            gm = 94;
            gn = 12;
            go = 288;
            gz = 1;
            gk = false;
            fm = 1;
        }

        if (var0 == 0 && QuestState.current == Quest.FIND_HIDE_SCROLL && QuestState.itemFound) {
            gm = -1;
            gk = false;
        }

        if (var0 == 0 && QuestState.current == Quest.RESCUE_SAKURA && Story.sakuraEscorting) {
            Story.gameCompleted = true;
            gm = 0;
            gk = true;
            fu = true;
            QuestState.current = Quest.NONE;
            fe = 984;
            ff = 408;
        } else {
            if (var0 == 95 && !Story.sakuraFound) {
                gk = false;
                gn = 599;
                go = 264;
                gz = -1;
                gm = 95;
            }

        }
    }

    public static void onMapEntered(int var0) {
        mapEventActive = false;
        cutscenePlaying = false;
        questItemState = 0;
        if (var0 == 15 && QuestState.current == Quest.FIND_HIDE_SCROLL) {
            placeQuestItem(216, 432);
        }

        if (var0 == 8 && QuestState.current == Quest.CROSS_RIVER) {
            placeQuestItem(72, 240);
        }

        int var1;
        if (var0 == 4 && Story.prologueActive) {
            U = true;
            Skills.lightness = true;
            Skills.transform = true;
            Skills.runOnWater = true;
            PlayerStats.setMaxHpMp(1000, 2000);
            PlayerStats.attack = 25;
            B = 4;
            ha = 500;
            gZ = 500;
            startMapEvent(0);
        } else if (var0 == 99) {
            Story.prologueActive = true;
            dw = true;
            fe = 108;
            ff = 312;
            fm = -1;
            startMapEvent(1);
        } else if (var0 == 94) {
            startMapEvent(10);
        } else if (var0 == 0 && Story.prologueActive) {
            U = false;
            Skills.lightness = false;
            Skills.runOnWater = false;
            Skills.transform = false;
            fx = false;
            PlayerStats.resetForNewGame();
            ha = 1000;
            gZ = 1000;
            fe = 864;
            ff = 336;

            for(var1 = 0; var1 < 19; ++var1) {
                WorldMap.visited[var1] = false;
            }

            WorldMap.visited[0] = true;
            fm = -1;
            dw = false;
            dx = false;
            startMapEvent(2);
            Story.prologueActive = false;
        } else if (var0 == 3 && QuestState.current == Quest.ESCORT_AYUMI_SISTER && !gk) {
            startMapEvent(3);
        } else if (var0 == 0 && QuestState.current == Quest.ESCORT_AYUMI_SISTER && gk) {
            startMapEvent(4);
        } else if (var0 == 97 && !Story.riverMonsterDefeated) {
            startMapEvent(5);
        } else if (var0 == 96 && !Story.tonikuDefeated) {
            startMapEvent(6);
        } else if (var0 == 95 && !Story.sakuraFound) {
            startMapEvent(7);
        } else if (var0 == 101) {
            startMapEvent(8);
            cameraY = cameraTargetY = 336 - Viewport.viewHeight;
            cameraTargetX = 24;
            cameraX = 24;
        } else if (var0 == 9 && QuestState.current == Quest.GIRL_OUTING) {
            startMapEvent(9);
        }

        if (var0 == 97 && Story.riverMonsterDefeated) {
            for(var1 = 0; var1 <= 6; ++var1) {
                GameMap.clearFlags(24, var1);
            }
        }

        if (var0 == 96 && Story.tonikuDefeated) {
            for(var1 = 0; var1 <= 11; ++var1) {
                GameMap.clearFlags(24, var1);
            }
        }

    }

    private static void z(int var0) {
        if (dA[0] == ff && a(dz[0] + (dJ[0] << 5) - fe) < 25) {
            a(hf, dJ[0]);
        }

    }

    private static void A(int var0) {
        int var1;
        boolean var6;
        label29: {
            var1 = PlayerStats.attack;
            int var10000 = dL[var0];
            int var4 = -dJ[var0];
            int var2 = var10000;
            if (Monsters.state[var2] == Monsters.STATE_ACTIVE || Monsters.state[var2] == Monsters.STATE_RESTING || Monsters.state[var2] == Monsters.STATE_ATTACKING) {
                int[] var5 = Monsters.hp;
                var5[var2] -= var1;
                if (Monsters.hp[var2] <= 0) {
                    Monsters.hp[var2] = 0;
                    Monsters.timer[var2] = -5;
                    Monsters.aux1[var2] = -(var4 << 3);
                    Monsters.aux2[var2] = 0;
                    Monsters.healthBarTimer[var2] = 0;
                    Monsters.state[var2] = Monsters.STATE_KNOCKED_OUT;
                    var6 = true;
                    break label29;
                }

                Monsters.timer[var2] = 0;
                Monsters.direction[var2] = var4;
                Monsters.state[var2] = Monsters.STATE_HURT;
                Monsters.frame[var2] = 3;
            }

            var6 = false;
        }

        if (!var6) {
            Blood.spawn(Monsters.x[dL[var0]], Monsters.y[dL[var0]] - (Monsters.height[dL[var0]] >> 1), Monsters.direction[dL[var0]]);
            FloatingText.spawn("-" + var1, 0, Monsters.x[dL[var0]], Monsters.y[dL[var0]] - Monsters.height[dL[var0]], 0, -2);
        }

    }

    private static void B(int var0) {
        for(int var1 = 0; var1 < Monsters.count; ++var1) {
            if (Monsters.state[var1] != Monsters.STATE_RESPAWN_WAIT && Monsters.y[var1] == dA[var0] && a(dz[var0] + (dJ[var0] << 3) - Monsters.x[var1]) < 20) {
                if (Monsters.x[var1] < dz[var0]) {
                    dJ[var0] = -1;
                } else {
                    dJ[var0] = 1;
                }

                dD[var0] = 5;
                dE[var0] = dF[var0] = 0;
                dL[var0] = var1;
            }
        }

    }

    private static void drawSky(Graphics g) {
        Sky.draw(g, cameraX, Viewport.viewWidth, Viewport.viewHeight, Settings.lowGraphics);
    }

    private static void b(Graphics var0) {
        if (fw) {
            if (tick % 20 > 2) {
                C = 25;
            } else {
                C = 26;
            }
        }

        if (fm == 1) {
            var0.drawImage(heroHeads[ar[C][0][0]], fe + ar[C][0][1], ff - ar[C][0][2], 0);
            var0.drawImage(heroLegs[ar[C][1][0]], fe + ar[C][1][1], ff - ar[C][1][2], 0);
            var0.drawImage(heroBodies[ar[C][2][0]], fe + ar[C][2][1], ff - ar[C][2][2], 0);
            if (fc != -1) {
                var0.drawImage(slashEffects[fc], fe - as[fc], ff - at[fc], 0);
                return;
            }
        } else {
            var0.drawRegion(heroHeads[ar[C][0][0]], 0, 0, heroHeads[ar[C][0][0]].getWidth(), heroHeads[ar[C][0][0]].getHeight(), 2, fe - ar[C][0][1], ff - ar[C][0][2], 24);
            var0.drawRegion(heroLegs[ar[C][1][0]], 0, 0, heroLegs[ar[C][1][0]].getWidth(), heroLegs[ar[C][1][0]].getHeight(), 2, fe - ar[C][1][1], ff - ar[C][1][2], 24);
            var0.drawRegion(heroBodies[ar[C][2][0]], 0, 0, heroBodies[ar[C][2][0]].getWidth(), heroBodies[ar[C][2][0]].getHeight(), 2, fe - ar[C][2][1], ff - ar[C][2][2], 24);
            if (fc != -1) {
                var0.drawRegion(slashEffects[fc], 0, 0, slashEffects[fc].getWidth(), slashEffects[fc].getHeight(), 2, fe + as[fc], ff - at[fc], 24);
            }
        }

    }

    private static void drawHudOverlay(Graphics var0) {
        if (!hudPanelVisible) {
            var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
            var0.drawImage(heroHeads[0], 0, 2, 0);
            var0.setColor(0);
            var0.drawRect(23, 2, hudBarMaxWidth + 1, 4);
            var0.drawRect(23, 8, hudBarMaxWidth + 1, 4);
            var0.drawRect(23, 14, hudBarMaxWidth + 1, 3);
            var0.setColor(16711680);
            int var1 = PlayerStats.hpBarWidth(hudBarMaxWidth);
            var0.fillRect(24, 3, var1, 3);
            var0.setColor(5570560);
            var0.fillRect(var1 + 24, 3, hudBarMaxWidth - var1, 3);
            var0.setColor(255);
            var1 = PlayerStats.mpBarWidth(hudBarMaxWidth);
            var0.fillRect(24, 9, var1, 3);
            var0.setColor(5570560);
            var0.fillRect(var1 + 24, 9, hudBarMaxWidth - var1, 3);
            var0.setColor(7274435);
            var1 = Leveling.expBarWidth(hudBarMaxWidth);
            var0.fillRect(24, 15, var1, 2);
            var0.setColor(19506);
            var0.fillRect(var1 + 24, 15, hudBarMaxWidth - var1, 2);
            FontGraph.drawSmall(var0, "lv:" + PlayerStats.level, 2, hudLevelX, 7, 0);
            FontGraph.drawSmall(var0, Inventory.money + "$", 1, hudMoneyX, 7, 0);
        }
    }

    private static void drawHudPanel(Graphics var0) {
        hudDirty = false;
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        var0.setClip(-1, hudY - 1, hudWidth + 2, 100);
        var0.setColor(0);
        if (!hudPanelVisible) {
            var0.fillRect(0, hudY, hudWidth, 15);
            FontGraph.drawBold(var0, leftSoftKeyLabel, 3, hudY + 3, 0);
            FontGraph.drawBold(var0, rightSoftKeyLabel, hudWidth - 3, hudY + 3, 1);
        } else {
            var0.fillRect(0, hudY + 33, hudWidth, 15);
            FontGraph.drawBold(var0, leftSoftKeyLabel, 3, hudY + 36, 0);
            FontGraph.drawBold(var0, rightSoftKeyLabel, hudWidth - 3, hudY + 36, 1);
            var0.setColor(4352);
            var0.fillRect(hudLeftFrameWidth, hudY, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 35, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 33, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 30, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 28, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 26, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 12, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 24, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 18, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 16, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 2, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 4, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 6, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 14, hudStripWidth, 1);
            var0.setColor(15649843);
            var0.fillRect(hudLeftFrameWidth, hudY + 1, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 5, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 17, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 34, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 29, hudStripWidth, 1);
            var0.setColor(16767871);
            var0.fillRect(hudLeftFrameWidth, hudY + 3, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 15, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 27, hudStripWidth, 1);
            var0.setColor(3355443);
            var0.fillRect(hudLeftFrameWidth, hudY + 7, hudStripWidth, 5);
            var0.fillRect(hudLeftFrameWidth, hudY + 19, hudStripWidth, 5);
            var0.fillRect(hudLeftFrameWidth, hudY + 31, hudStripWidth, 2);
            var0.setColor(12281361);
            var0.fillRect(hudLeftFrameWidth, hudY + 25, hudStripWidth, 1);
            var0.fillRect(hudLeftFrameWidth, hudY + 13, hudStripWidth, 1);
            var0.drawImage(hudFrames[0], 0, hudY, 0);
            var0.drawImage(hudFrames[1], 0 + hudWidth, hudY, 24);
            int var1 = PlayerStats.hpBarWidth(hudBarMaxWidth);
            var0.setColor(7798784);
            var0.fillRect(hudBarX, hudBarY, var1, 2);
            var0.setColor(13369344);
            var0.fillRect(hudBarX, hudBarY + 1, var1, 4);
            var1 = PlayerStats.mpBarWidth(hudBarMaxWidth);
            var0.setColor(119);
            var0.fillRect(hudBarX, hudBarY + 12, var1, 2);
            var0.setColor(204);
            var0.fillRect(hudBarX, hudBarY + 14, var1, hudMpBarHeight - 2);
            FontGraph.drawSmall(var0, "" + Inventory.money, 1, hudWidth - 18, hudY + 6, 2);
            var1 = Leveling.expBarWidth(hudExpBarMaxWidth);
            var0.setColor(94373);
            var0.fillRect(31, hudY + 31, var1, 1);
            var0.setColor(65535);
            var0.fillRect(31, hudY + 32, var1, 1);
            FontGraph.drawSmall(var0, "" + PlayerStats.level, 2, 26, hudY + 11, 2);
            var0.drawImage(weaponIcons[fd], 43, hudY + 16, 3);
            FontGraph.drawSmall(var0, "" + (Inventory.potionCounts[0] + Inventory.potionCounts[1] + Inventory.potionCounts[2]), 2, hudWidth - 29, hudY + 19, 2);
            FontGraph.drawSmall(var0, "" + (Inventory.potionCounts[3] + Inventory.potionCounts[4] + Inventory.potionCounts[5]), 2, hudWidth - 9, hudY + 19, 2);
        }
    }

    private static void drawLetterbox(Graphics var0) {
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        var0.setClip(-1, -1, Viewport.screenWidth + 2, Viewport.screenHeight + 2);
        var0.setColor(0);
        var0.fillRect(0, 0, Viewport.viewWidth, letterboxHeight);
        if (hudPanelVisible) {
            var0.fillRect(0, Viewport.screenHeight - letterboxHeight + 1, Viewport.viewWidth, letterboxHeight);
        } else {
            var0.fillRect(0, Viewport.screenHeight - 13, Viewport.screenWidth, 13);
        }

        if (captionVisible) {
            for(int var1 = 0; var1 < captionLines.length; ++var1) {
                FontGraph.drawBold(var0, captionLines[var1], Viewport.viewCenterX, captionY + (var1 << 4), 2);
            }
        }

    }

    private static void drawDialogue(Graphics var0) {
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        var0.setClip(-1, -1, Viewport.screenWidth + 2, Viewport.screenHeight + 2);
        dialogueBoxLines = Dialogue.pages[Dialogue.page].length;
        int var1 = Dialogue.speakerY - cameraY - (dialogueBoxLines << 4);
        boolean var2 = true;
        if (var1 < 10) {
            var2 = false;
            var1 = 10;
        }

        var0.setColor(16777215);
        if (Settings.lowGraphics) {
            var0.fillRect(Dialogue.boxX, var1 - 5, Dialogue.boxWidth, (dialogueBoxLines << 4) + 7);
        } else {
            var0.fillRoundRect(Dialogue.boxX, var1 - 5, Dialogue.boxWidth, (dialogueBoxLines << 4) + 7, 10, 10);
        }

        var0.setColor(17408);
        if (Settings.lowGraphics) {
            var0.drawRect(Dialogue.boxX - 1, var1 - 5, Dialogue.boxWidth + 1, (dialogueBoxLines << 4) + 7);
        } else {
            var0.drawRoundRect(Dialogue.boxX - 1, var1 - 5, Dialogue.boxWidth + 1, (dialogueBoxLines << 4) + 7, 10, 10);
        }

        for(int var3 = 0; var3 < Dialogue.shownLines; ++var3) {
            FontGraph.drawRegular(var0, Dialogue.pages[Dialogue.page][var3], Dialogue.boxX + (Dialogue.boxWidth >> 1), var1 + (var3 << 4), 2);
        }

        if (Dialogue.shownLines < Dialogue.pages[Dialogue.page].length) {
            FontGraph.drawRegular(var0, Dialogue.pages[Dialogue.page][Dialogue.shownLines].substring(0, Dialogue.shownChars), Dialogue.boxX + (Dialogue.boxWidth >> 1), var1 + (Dialogue.shownLines << 4), 2);
        }

        var0.setColor(0);
        var0.fillRect(0, hudY + 33, hudWidth, 15);
        if (var2 && Dialogue.speakerX > cameraX + 10 && Dialogue.speakerX < cameraX + Viewport.viewWidth - 10) {
            var0.drawImage(markerIcon, Dialogue.speakerX - cameraX, Dialogue.speakerY - cameraY + 2, 17);
        }

        if (tick % 10 < 8) {
            FontGraph.drawBold(var0, "Tiếp tục", Viewport.viewCenterX, Viewport.screenHeight - 13, 2);
        }

    }

    private static void g(Graphics var0) {
        for(int var1 = 0; var1 < du; ++var1) {
            if (dz[var1] + fi >= cameraX && dz[var1] - fi <= cameraX + Viewport.viewWidth && dA[var1] >= cameraY && dA[var1] - fh <= cameraY + Viewport.viewHeight) {
                if (dJ[var1] == 1) {
                    var0.drawImage(classmateHeads[dH[var1]][aI[dG[var1]][0][0]], dz[var1] + aI[dG[var1]][0][1], dA[var1] - aI[dG[var1]][0][2], 0);
                    var0.drawImage(heroLegs[aI[dG[var1]][1][0]], dz[var1] + aI[dG[var1]][1][1], dA[var1] - aI[dG[var1]][1][2], 0);
                    var0.drawImage(heroBodies[aI[dG[var1]][2][0]], dz[var1] + aI[dG[var1]][2][1], dA[var1] - aI[dG[var1]][2][2], 0);
                    if (dI[var1] != -1) {
                        var0.drawImage(slashEffects[dI[var1]], dz[var1] - as[dI[var1]], dA[var1] - at[dI[var1]], 0);
                    }
                } else {
                    var0.drawRegion(classmateHeads[dH[var1]][aI[dG[var1]][0][0]], 0, 0, classmateHeads[dH[var1]][aI[dG[var1]][0][0]].getWidth(), classmateHeads[dH[var1]][aI[dG[var1]][0][0]].getHeight(), 2, dz[var1] - aI[dG[var1]][0][1], dA[var1] - aI[dG[var1]][0][2], 24);
                    var0.drawRegion(heroLegs[aI[dG[var1]][1][0]], 0, 0, heroLegs[aI[dG[var1]][1][0]].getWidth(), heroLegs[aI[dG[var1]][1][0]].getHeight(), 2, dz[var1] - aI[dG[var1]][1][1], dA[var1] - aI[dG[var1]][1][2], 24);
                    var0.drawRegion(heroBodies[aI[dG[var1]][2][0]], 0, 0, heroBodies[aI[dG[var1]][2][0]].getWidth(), heroBodies[aI[dG[var1]][2][0]].getHeight(), 2, dz[var1] - aI[dG[var1]][2][1], dA[var1] - aI[dG[var1]][2][2], 24);
                    if (dI[var1] != -1) {
                        var0.drawRegion(slashEffects[dI[var1]], 0, 0, slashEffects[dI[var1]].getWidth(), slashEffects[dI[var1]].getHeight(), 2, dz[var1] + as[dI[var1]], dA[var1] - at[dI[var1]], 24);
                    }
                }
            }
        }

    }

    private static void drawTutorial(Graphics var0) {
        var0.setClip(-1, -1, Viewport.screenWidth + 2, Viewport.screenHeight + 1);
        drawSky(var0);
        var0.translate(-cameraX, -cameraY);
        if (Settings.lowGraphics) {
            j(var0);
        } else {
            k(var0);
        }

        drawSigns(var0);
        g(var0);
        b(var0);
        drawNpcs(var0);
        l(var0);
        drawLetterbox(var0);
        if (Dialogue.visible) {
            drawDialogue(var0);
        }

    }

    private static void N() {
        if (!fw && B != 14 && a(ff - gQ) < 30 && a(fe - gP) < 200) {
            gS = 5;
            gT = gP < fe ? 1 : -1;
            gY = fe + (gT << 5);
            gR = gT * 6;
            if (gZ < ha >> 1) {
                gR = gT << 3;
            }
        }

    }

    private static void d(int var0, int var1, int var2) {
        ii = var0 + 1;
        F();
        b(0, var1, var2);
        if (var0 == 1) {
            b(1, fe, ff);
            ij = 1;
            im[ij] = fm;
        }

    }

    private static void setGuideMarker(int var0, int var1, int var2, int var3) {
        guideMarkerMap = var0;
        guideMarkerX = var1;
        guideMarkerY = var2;
        guideMarkerNpc = var3;
    }

    private static void placeQuestItem(int var0, int var1) {
        questItemX = var0;
        questItemY = var1;
        questItemState = 1;
    }

    private static void startCutscene(int var0) {
        fZ = 0;
        cutsceneId = var0;
        cutsceneStep = 0;
        cutscenePlaying = true;
        ga = false;
        Input.clearHeld();
        Input.clearPressed();
    }

    private static void startDialogue(String[] var0, int var1, int var2, int var3) {
        Dialogue.finishAction = var1;
        Dialogue.speakerX = var2;
        Dialogue.speakerY = var3;
        Dialogue.pageCount = var0.length;
        Dialogue.page = 0;
        Dialogue.pages = new String[Dialogue.pageCount][];
        if ((Dialogue.boxWidth = Viewport.viewWidth >> 1) < 120) {
            Dialogue.boxWidth = 120;
        }

        for(var1 = 0; var1 < Dialogue.pageCount; ++var1) {
            Dialogue.pages[var1] = FontGraph.wrapRegularText(var0[var1], Dialogue.boxWidth - 20);
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

    private static void a(int var0, int var1, int var2, int var3, boolean var4) {
        int var5 = -1;

        for(int var6 = 0; var6 < cV; ++var6) {
            if (cY[var6] == -1) {
                var5 = var6;
                break;
            }
        }

        if (var5 != -1) {
            cY[var5] = 1;
            cW[var5] = var0;
            cX[var5] = var1;
            da[var5] = var2;
            db[var5] = var3;
            dc[var5] = var4;
        }
    }

    private static void startMapEvent(int var0) {
        mapEventStep = 0;
        mapEventId = var0;
        mapEventActive = true;
    }

    private static void Q() {
        if (fx) {
            aL = 0;
        }

    }

    private static void R() {
        if (fu) {
            cameraTargetX = fe - Viewport.viewCenterX + cameraLeadX * fm;
            cameraTargetY = ff - cameraOffsetY;
        }

        int var0;
        label788:
        switch (B) {
            case 1:
                if (++D > 30) {
                    D = 0;
                }

                if (D % 15 < 5) {
                    C = 0;
                } else {
                    C = 1;
                }

                S();

                for(var0 = 0; var0 < du; ++var0) {
                    if (dD[var0] == 1 && ff == dA[var0] && a(fe - dz[var0]) < 30 && fE == -1) {
                        rightSoftKeyLabel = "Trò chuyện";
                        hudDirty = true;
                        fE = var0;
                        break;
                    }
                }

                if (gm == mapId && gr == 1 && ff == go && a(fe - gn) < 30) {
                    rightSoftKeyLabel = "Nói chuyện";
                    hudDirty = true;
                    fB = true;
                }

                var0 = 0;

                while(true) {
                    if (var0 >= npcCount) {
                        break label788;
                    }

                    if (ff == npcY[var0] && a(fe - npcX[var0]) < 30 && fD == -1) {
                        rightSoftKeyLabel = "Giao tiếp";
                        hudDirty = true;
                        fD = var0;
                        break label788;
                    }

                    ++var0;
                }
            case 2:
                if (++D >= 10) {
                    D = 0;
                    fo = 0;
                    if (Skills.sprint) {
                        fo = fm << 1;
                    }
                }

                C = (D >> 1) + 2;
                if (fm == 1) {
                    if (GameMap.hasFlag(fe + fi, ff - fj, TileFlags.WALL_LEFT_SIDE)) {
                        z = 0;
                        fe = I(fe + fi) - fi;
                    }
                } else if (GameMap.hasFlag(fe - fi - 1, ff - fj, TileFlags.WALL_RIGHT_SIDE)) {
                    z = 0;
                    fe = I(fe - fi - 1) + GameMap.TILE_SIZE + fi;
                }

                if ((GameMap.flagsAtPixel(fe, ff - 1) & TileFlags.WATER) == TileFlags.WATER) {
                    fe += z >> 1;
                } else {
                    fe += z;
                }

                if (z > 0) {
                    --z;
                } else if (z < 0) {
                    ++z;
                } else {
                    B = 1;
                    fo = 0;
                }

                if (ir != -1) {
                    if (a(fe - is[ir]) >= 20) {
                        ir = -1;
                        if (GameMap.hasFlag(fe, ff, TileFlags.SOLID)) {
                            B = 1;
                        } else {
                            B = 4;
                            A = 2;
                            z = 3 * fm;
                            E = 0;
                        }
                    }
                } else if ((GameMap.flagsAtPixel(fe, ff) & TileFlags.SOLID) != TileFlags.SOLID) {
                    B = 4;
                    z = 3 * fm;
                    E = 0;
                }

                y();
                Dust.spawn(fm, fe - (fm << 3), ff);
                S();
                break;
            case 3:
                fe += z;
                if ((ff += A) < 0) {
                    ff = 0;
                    A = -1;
                }

                if (D == 0) {
                    C = 7;
                } else {
                    C = 23;
                }

                ++A;
                if (Skills.lightness && !fx) {
                    if (A == -3) {
                        C = 8;
                    } else if (A == -2) {
                        C = 9;
                    } else if (A == -1) {
                        C = 10;
                    } else if (A == 0) {
                        C = 11;
                    }
                }

                if (A == 0) {
                    B = 4;
                    D = 0;
                    E = Skills.lightness ? 1 : 0;
                    A = 1;
                    hudDirty = true;
                }

                if (fn < 0) {
                    ++fn;
                }

                if (fm == 1) {
                    if ((GameMap.flagsAtPixel(fe + fi, ff) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                        fe = I(fe + fi) - fi;
                        z = 0;
                    }
                } else if ((GameMap.flagsAtPixel(fe - fi, ff) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                    fe = I(fe + 24 - fi) + fi;
                    z = 0;
                }
                break;
            case 4:
                fc = -1;
                fe += z;
                ff += A;
                if (A < 18) {
                    ++A;
                }

                if (z > 0) {
                    --z;
                }

                C = 7;
                if (ff >= GameMap.pixelHeight) {
                    B = 6;
                    PlayerStats.kill();
                } else if (ff + 4 >= GameMap.pixelHeight) {
                    B = 6;
                    PlayerStats.kill();
                } else if (A > 4 && (GameMap.flagsAtPixel(fe, ff + 3) & TileFlags.SOLID) == TileFlags.SOLID) {
                    if (A > 12) {
                        B = 8;
                    } else {
                        B = 1;
                    }

                    A = 0;
                    z = 0;
                    E = 0;
                    D = 0;
                    ff = I(ff + 3);
                    Dust.spawn(-1, fe - -8, ff);
                    Dust.spawn(1, fe - 8, ff);
                    if (Skills.motorbikeLicense) {
                        for(var0 = 0; var0 < ii; ++var0) {
                            if (ff == il[var0] && a(fe - ik[var0]) < 16) {
                                fe = ik[var0];
                                B = 18;
                                ij = var0;
                                im[var0] = fm;
                                in[var0] = 0;
                            }
                        }
                    }

                    if (QuestState.current == Quest.AIR_HITS && QuestState.progress < Quest.TARGET_COUNT[Quest.AIR_HITS]) {
                        QuestState.progress = 0;
                        Notice.show("Hit: " + QuestState.progress);
                    }
                } else {
                    if (GameMap.hasFlag(fe, ff, TileFlags.WATER) && ff % GameMap.TILE_SIZE > 8) {
                        WaterSplash.spawn(fe, H(ff) + 8);
                        ff = H(ff) + 8;
                        B = 11;
                        z = fm << 1;
                        A >>= 2;
                        ff = H(ff) + 12;
                        if (!PlayerStats.hasMp() || !Skills.runOnWater) {
                            B = 12;
                        }
                    }

                    if (GameMap.hasFlag(fe, ff, TileFlags.DEEP_WATER)) {
                        B = 12;
                    }

                    if (E == 1 && !fx) {
                        if (A == 3) {
                            C = 11;
                        } else if (A == 2) {
                            C = 8;
                        } else if (A == 1) {
                            C = 9;
                        } else if (A == 0) {
                            C = 10;
                        }
                    } else {
                        C = 23;
                    }

                    if (A > 6) {
                        C = 12;
                    }

                    if (fn < 0) {
                        ++fn;
                    }

                    if (fm == 1) {
                        if ((GameMap.flagsAtPixel(fe + fi, ff) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                            fe = I(fe + fi) - fi;
                            z = 0;
                        }
                    } else if ((GameMap.flagsAtPixel(fe - fi, ff) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                        fe = I(fe + 24 - fi) + fi;
                        z = 0;
                    }

                    w();
                    if (gN && A >= 4 && a(fe - gP) < 30 && a(ff - (gQ - 25)) < 5) {
                        A = -(A >> 1);
                        z = fm << 3;
                        D = 0;
                    }
                }
                break;
            case 5:
                ++D;
                if (fm == 1) {
                    if ((GameMap.flagsAtPixel(fe + fi, ff - fj) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                        z = 0;
                    }
                } else if ((GameMap.flagsAtPixel(fe - fi, ff - fj) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                    z = 0;
                }

                fe += z;
                if ((ff += A) < 0) {
                    A = 0;
                    ff = 0;
                }

                if (A == 0) {
                    if (ir == -1 && (GameMap.flagsAtPixel(fe, ff) & TileFlags.SOLID) != TileFlags.SOLID) {
                        B = 4;
                        fc = -1;
                        z = (Q >> 1) * fm;
                        E = 0;
                        D = 0;
                    }

                    if (ir != -1 && a(fe - is[ir]) >= 20) {
                        ir = -1;
                        if (GameMap.hasFlag(fe, ff, TileFlags.SOLID)) {
                            B = 1;
                        } else {
                            B = 4;
                            A = 2;
                            z = 3 * fm;
                            fc = -1;
                            E = 0;
                        }
                    }
                } else if (A < 0) {
                    if (++A == 0) {
                        A = 1;
                    }
                } else {
                    if (A < 20 && D % 5 == 0) {
                        ++A;
                    }

                    if (A > 3) {
                        A = 3;
                    }

                    if (A > 2 && (GameMap.flagsAtPixel(fe, ff + 3) & TileFlags.SOLID) == TileFlags.SOLID) {
                        A = 0;
                        z = 0;
                        ff = I(ff + 3);
                        if (QuestState.current == Quest.AIR_HITS && QuestState.progress < Quest.TARGET_COUNT[Quest.AIR_HITS]) {
                            QuestState.progress = 0;
                            Notice.show("Hit: " + QuestState.progress);
                        }
                    }

                    if (GameMap.hasFlag(fe, ff, TileFlags.WATER) && ff % GameMap.TILE_SIZE > 8) {
                        WaterSplash.spawn(fe, H(ff) + 8);
                        B = 11;
                        z = fm << 1;
                        A >>= 2;
                        ff = H(ff) + 12;
                        fc = -1;
                        if (!PlayerStats.hasMp() || !Skills.runOnWater) {
                            B = 12;
                        }
                        break;
                    }

                    if (GameMap.hasFlag(fe, ff, TileFlags.DEEP_WATER)) {
                        B = 12;
                        fc = -1;
                        break;
                    }

                    w();
                }

                if (z > 0) {
                    --z;
                } else if (z < 0) {
                    ++z;
                }

                if (z != 0) {
                    y();
                }

                label728:
                switch (ft) {
                    case 0:
                        switch (D) {
                            case 1:
                            case 2:
                            case 3:
                                fc = -1;
                                C = 13;
                                break label728;
                            case 4:
                                fc = 0;
                                C = 14;
                                break label728;
                            case 5:
                                fc = 1;
                                C = 15;
                                break label728;
                            case 6:
                                fc = 1;
                                C = 15;
                                u();
                                break label728;
                            case 7:
                                fc = 2;
                                C = 16;
                                break label728;
                            case 8:
                                fc = 2;
                                C = 16;
                                if (fv) {
                                    fv = false;
                                    ft = 2;
                                } else {
                                    ft = 1;
                                }

                                D = 1;
                            default:
                                break label728;
                        }
                    case 1:
                        switch (D) {
                            case 1:
                                fc = 1;
                                C = 15;
                                break label728;
                            case 2:
                                fc = 1;
                                C = 15;
                                ft = 10;
                            default:
                                break label728;
                        }
                    case 2:
                        switch (D) {
                            case 1:
                            case 2:
                            case 3:
                                fc = 3;
                                C = 17;
                                break label728;
                            case 4:
                                fc = 4;
                                C = 18;
                                break label728;
                            case 5:
                                fc = 5;
                                C = 19;
                                break label728;
                            case 6:
                                fc = 6;
                                C = 20;
                                u();
                                break label728;
                            case 7:
                                fc = 6;
                                C = 20;
                                if (fv && fs < 2) {
                                    if (A == 0 && fr) {
                                        D = 0;
                                        fv = false;
                                        ft = 9;
                                    } else {
                                        fv = false;
                                        ft = 3;
                                        ++fs;
                                    }
                                } else {
                                    ft = 10;
                                }

                                D = 1;
                            default:
                                break label728;
                        }
                    case 3:
                        switch (D) {
                            case 1:
                                fc = 1;
                                C = 15;
                                break;
                            case 2:
                                fc = 1;
                                C = 15;
                                break;
                            case 3:
                                fc = 2;
                                C = 16;
                                break;
                            case 4:
                                fc = 2;
                                C = 16;
                                if (fv) {
                                    fv = false;
                                    ft = 2;
                                } else {
                                    ft = 1;
                                }

                                D = 1;
                        }
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    default:
                        break;
                    case 9:
                        switch (D) {
                            case 1:
                            case 2:
                            case 3:
                                fc = -1;
                                C = 13;
                                break label728;
                            case 4:
                                fc = 0;
                                C = 14;
                                break label728;
                            case 5:
                            case 6:
                                fc = 9;
                                C = 15;
                                var0 = A();
                                fr = false;
                                if (var0 != -1) {
                                    fr = true;
                                    Blood.spawn(Monsters.x[var0], Monsters.y[var0] - (Monsters.height[var0] >> 1), fm);
                                    Monsters.state[var0] = Monsters.STATE_LAUNCHED;
                                    Monsters.timer[var0] = -8;
                                    Q();
                                }
                                break label728;
                            case 7:
                            case 8:
                                fc = 10;
                                C = 16;
                                break label728;
                            case 9:
                                fc = -1;
                                C = 16;
                                if (fr) {
                                    B = 3;
                                    z = 0;
                                    A = -8;
                                } else {
                                    D = 0;
                                    E = 0;
                                    ft = 10;
                                }
                            default:
                                break label728;
                        }
                    case 10:
                        switch (D) {
                            case 1:
                                fc = 2;
                                C = 16;
                                break;
                            case 2:
                                fc = 0;
                                C = 14;
                                break;
                            case 3:
                            case 4:
                                C = 13;
                                fc = -1;
                                break;
                            case 5:
                                C = 13;
                                fc = -1;
                                if (A != 0) {
                                    B = 4;
                                } else {
                                    B = 1;
                                }

                                fs = 0;
                                E = 0;
                        }
                }

                if (fd == 1) {
                    fc = -1;
                }

                if (B == 5) {
                    if (A != 0) {
                        C += 14;
                    } else if (z != 0) {
                        C += 22;
                    }
                }
                break;
            case 6:
                fe += z;
                if (z > 1) {
                    --z;
                }

                if (z < -1) {
                    ++z;
                }

                ++A;
                ff += A;
                C = 21;
                if (ff >= GameMap.pixelHeight || ff > cameraY + Viewport.viewHeight + fh || GameMap.hasFlag(fe, ff - fh, TileFlags.DEEP_WATER)) {
                    fe = fp;
                    ff = fq;
                    B = 4;
                    PlayerStats.revive();
                    A = 0;
                    z = 0;
                    E = 0;
                    D = 0;
                    Leveling.applyDeathPenalty();
                    hudDirty = true;
                    if (hc) {
                        hg = hh;
                    }

                    if (hi) {
                        hm = hn;
                    }

                    MessageBox.show(MessageType.GAME_NOTICE);
                    GameState.current = GameState.MESSAGE;
                }
                break;
            case 7:
                if ((GameMap.flagsAtPixel(fe + fi, ff - fj) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                    z = -z;
                }

                if ((GameMap.flagsAtPixel(fe - fi, ff - fj) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                    z = -z;
                }

                fe += z;
                ff += A;
                C = 21;
                if (z > 1) {
                    --z;
                }

                if (z < -1) {
                    ++z;
                }

                if (++A > 0) {
                    A = 0;
                }

                if (++D >= 7) {
                    B = 4;
                    fn = -20;
                    E = 0;
                    D = 0;
                    A = 0;
                }

                x();
                break;
            case 8:
                C = 22;
                if (D < 5) {
                    ++D;
                } else if (!Input.held[Input.KEY_DOWN]) {
                    B = 1;
                }

                if (++E > 6 && GameMap.hasFlag(fe + fi, ff + 2, TileFlags.DIGGABLE) && GameMap.hasFlag(fe - fi, ff + 2, TileFlags.DIGGABLE) && Skills.burrow & PlayerStats.hasMp()) {
                    B = 13;
                }
            case 9:
            case 10:
            case 22:
            default:
                break;
            case 11:
                U();
                break;
            case 12:
                ff += A;
                if (A < 20 && tick % 2 == 0) {
                    ++A;
                }

                C = 7;
                if (ff >= GameMap.pixelHeight) {
                    PlayerStats.kill();
                    B = 6;
                    fc = -1;
                } else if (GameMap.hasFlag(fe, ff, TileFlags.SOLID)) {
                    ff = H(ff);
                    if (GameMap.hasFlag(fe, ff - 2, TileFlags.WATER)) {
                        B = 1;
                        A = 0;
                        z = 0;
                        E = 0;
                        D = 0;
                    } else if (Skills.dive) {
                        B = 17;
                        A = 0;
                        z = 0;
                        E = 0;
                        D = 0;
                    } else {
                        B = 6;
                    }

                    ff = I(ff);
                } else if (GameMap.hasFlag(fe, ff - fh - 5, TileFlags.DEEP_WATER)) {
                    PlayerStats.kill();
                    B = 6;
                    fc = -1;
                }
                break;
            case 13:
                C = 24;
                ff += 28;
                B = 14;
                break;
            case 14:
                if (tick % 20 > 2) {
                    C = 25;
                } else {
                    C = 26;
                }

                if (tick % 8 == 0) {
                    if (PlayerStats.drainMp(1)) {
                        B = 15;
                    }

                    hudDirty = true;
                }
                break;
            case 15:
                ff -= 10;
                A = -10;
                B = 3;
                break;
            case 16:
                if (++D >= 20) {
                    D = 0;
                }

                if ((C = (D >> 2) + 2) % 2 == 0) {
                    fc = 7;
                } else {
                    fc = 8;
                }

                if (fm == 1) {
                    if (GameMap.hasFlag(fe + fi, ff - fj, TileFlags.WALL_LEFT_SIDE)) {
                        z = 0;
                        fe = I(fe + fi) - fi;
                    }
                } else if (GameMap.hasFlag(fe - fi - 1, ff - fj, TileFlags.WALL_RIGHT_SIDE)) {
                    z = 0;
                    fe = I(fe - fi - 1) + GameMap.TILE_SIZE + fi;
                }

                fe += z;
                if (z > 0) {
                    --z;
                } else if (z < 0) {
                    ++z;
                } else {
                    B = 17;
                }

                if ((GameMap.flagsAtPixel(fe, ff) & TileFlags.SOLID) != TileFlags.SOLID) {
                    B = 12;
                    z = fm << 2;
                    E = 0;
                }
                break;
            case 17:
                if (++D > 30) {
                    D = 0;
                }

                if (D % 15 < 5) {
                    C = 0;
                    fc = 7;
                } else {
                    C = 1;
                    fc = 8;
                }
                break;
            case 18:
                if (tick % 8 > 3) {
                    C = 43;
                } else {
                    C = 44;
                    Dust.spawn(fm, fe - (fm << 4), ff - 5);
                }

                D = 0;
                break;
            case 19:
                if (tick % 8 > 3) {
                    C = 43;
                } else {
                    C = 44;
                    Dust.spawn(fm, fe - (fm << 4), ff - 5);
                }

                if (D > 0) {
                    --D;
                    C = 45;
                }

                if (fm == 1) {
                    if (GameMap.hasFlag(fe + fi, ff - fj, TileFlags.WALL_LEFT_SIDE)) {
                        z = 0;
                        fe = I(fe + fi) - fi;
                    }
                } else if (GameMap.hasFlag(fe - fi - 1, ff - fj, TileFlags.WALL_RIGHT_SIDE)) {
                    z = 0;
                    fe = I(fe - fi - 1) + GameMap.TILE_SIZE + fi;
                }

                if ((GameMap.flagsAtPixel(fe, ff - 1) & TileFlags.WATER) == TileFlags.WATER) {
                    fe += z >> 1;
                } else {
                    fe += z;
                }

                ik[ij] = fe;
                il[ij] = ff;
                if (z > 0) {
                    --z;
                } else if (z < 0) {
                    ++z;
                } else {
                    B = 18;
                    fo = 0;
                }

                if (ir != -1) {
                    if (a(fe - is[ir]) >= 20) {
                        ir = -1;
                        if (GameMap.hasFlag(fe, ff, TileFlags.SOLID)) {
                            B = 18;
                        } else {
                            B = 20;
                            A = 2;
                            z = 3 * fm;
                            E = 0;
                        }
                    }
                } else if ((GameMap.flagsAtPixel(fe, ff) & TileFlags.SOLID) != TileFlags.SOLID) {
                    B = 20;
                    z = 3 * fm;
                    E = 0;
                }

                Dust.spawn(fm, fe - (fm << 3), ff);
                S();
                if ((var0 = A()) != -1 && (Monsters.state[var0] == Monsters.STATE_ACTIVE || Monsters.state[var0] == Monsters.STATE_RESTING)) {
                    k(var0, 2, -fm);
                    Blood.spawn(Monsters.x[var0], Monsters.y[var0] - (Monsters.height[var0] >> 1), fm);
                    FloatingText.spawn("-2", 0, Monsters.x[var0], Monsters.y[var0] - Monsters.height[var0], 0, -2);
                }
                break;
            case 20:
                try {
                    if (fm == 1) {
                        if ((GameMap.flagsAtPixel(fe + fi, ff - fj) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                            z = 0;
                        }
                    } else if ((GameMap.flagsAtPixel(fe - fi, ff - fj) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                        z = 0;
                    }

                    fc = -1;
                    fe += z;
                    ff += A;
                    if (A < 20) {
                        ++A;
                    }

                    if (z > 0) {
                        --z;
                    }

                    if (tick % 8 > 3) {
                        C = 43;
                    } else {
                        C = 44;
                        Dust.spawn(fm, fe - (fm << 4), ff - 5);
                    }

                    if (A > 4 && (GameMap.flagsAtPixel(fe, ff) & TileFlags.SOLID) == TileFlags.SOLID) {
                        if (A < 8) {
                            B = 18;
                            A = 0;
                            z = 0;
                            E = 0;
                            D = 0;
                            ff = I(ff);
                        } else {
                            B = 21;
                            A = -(A / 3);
                            ff = I(ff);
                        }
                    }

                    if (GameMap.hasFlag(fe, ff, TileFlags.WATER) && ff % GameMap.TILE_SIZE > 8) {
                        WaterSplash.spawn(fe, H(ff) + 8);
                    }

                    ik[ij] = fe;
                    il[ij] = ff;
                    if (GameMap.hasFlag(fe, ff, TileFlags.DEEP_WATER)) {
                        io[ij] = 1;
                        ij = -1;
                        B = 12;
                    }
                } catch (Exception var2) {
                }

                if (fn < 0) {
                    ++fn;
                }
                break;
            case 21:
                if (fm == 1) {
                    if ((GameMap.flagsAtPixel(fe + fi, ff - fj) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                        z = 0;
                    }
                } else if ((GameMap.flagsAtPixel(fe - fi, ff - fj) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                    z = 0;
                }

                fe += z;
                ff += A;
                ik[ij] = fe;
                il[ij] = ff;
                if (z > 0) {
                    --z;
                }

                if (++A == 0) {
                    B = 20;
                    D = 0;
                    E = Skills.lightness ? 1 : 0;
                    A = 1;
                    hudDirty = true;
                }

                if (fn < 0) {
                    ++fn;
                }

                if (tick % 8 > 3) {
                    C = 43;
                } else {
                    C = 44;
                    Dust.spawn(fm, fe - (fm << 4), ff - 5);
                }

                if (D > 0) {
                    --D;
                    C = 45;
                }
                break;
            case 23:
                T();
        }

        if (tick % 3 == 0) {
            if (fw) {
                if (PlayerStats.drainMp(1)) {
                    fw = false;
                }

                hudDirty = true;
            }

            if (fx) {
                if (PlayerStats.drainMp(1)) {
                    fx = false;

                    for(int var1 = 0; var1 < 4; ++var1) {
                        Image var3 = heroHeads[var1];
                        heroHeads[var1] = heroTransformHeads[var1];
                        heroTransformHeads[var1] = var3;
                    }
                }

                hudDirty = true;
            }
        }

    }

    private static final void S() {
        if (!Settings.lowGraphics) {
            if (GameMap.hasFlag(fe, ff + 1, TileFlags.BRIDGE)) {
                GameMap.pressBridge(fe, ff + 1);
                GameMap.pressBridge(fe, ff - 2);
            }

            if (GameMap.hasFlag(fe - GameMap.TILE_SIZE, ff + 1, TileFlags.BRIDGE_PRESSED)) {
                GameMap.releaseBridge(fe - GameMap.TILE_SIZE, ff + 1);
                GameMap.releaseBridge(fe - GameMap.TILE_SIZE, ff - 2);
            }

            if (GameMap.hasFlag(fe + GameMap.TILE_SIZE, ff + 1, TileFlags.BRIDGE_PRESSED)) {
                GameMap.releaseBridge(fe + GameMap.TILE_SIZE, ff + 1);
                GameMap.releaseBridge(fe + GameMap.TILE_SIZE, ff - 2);
            }

        }
    }

    private static void T() {
        if (++D == 1) {
            Aura.show(fe, ff - 40);
            aL = 0;
            if (heroTransformHeads == null) {
                heroTransformHeads = new Image[4];

                try {
                    heroTransformHeads[0] = Character.loadHeroTransformHead(0);
                    heroTransformHeads[1] = Character.loadHeroTransformHead(1);
                } catch (Exception var2) {
                }

                heroTransformHeads[2] = heroTransformHeads[1];
                heroTransformHeads[3] = heroTransformHeads[0];
            }
        } else {
            Image var0;
            int var1;
            if (D >= 30) {
                B = 1;
                D = 0;
                if (!PlayerStats.hasMp()) {
                    return;
                }

                for(var1 = 0; var1 < 4; ++var1) {
                    var0 = heroHeads[var1];
                    heroHeads[var1] = heroTransformHeads[var1];
                    heroTransformHeads[var1] = var0;
                }

                fx = !fx;
            } else if (D <= 10) {
                if (D % 5 == 0) {
                    for(var1 = 0; var1 < 4; ++var1) {
                        var0 = heroHeads[var1];
                        heroHeads[var1] = heroTransformHeads[var1];
                        heroTransformHeads[var1] = var0;
                    }
                }
            } else if (D < 20) {
                if (D % 3 == 0) {
                    for(var1 = 0; var1 < 4; ++var1) {
                        var0 = heroHeads[var1];
                        heroHeads[var1] = heroTransformHeads[var1];
                        heroTransformHeads[var1] = var0;
                    }
                }
            } else if (D < 30 && D % 2 == 0) {
                for(var1 = 0; var1 < 4; ++var1) {
                    var0 = heroHeads[var1];
                    heroHeads[var1] = heroTransformHeads[var1];
                    heroTransformHeads[var1] = var0;
                }
            }
        }

        if (D % 2 > 0) {
            C = 0;
        } else {
            C = 1;
        }
    }

    private static void U() {
        if (++D >= 5) {
            D = 0;
            fo = 0;
        }

        C = D + 2;
        if (fm == 1) {
            if (GameMap.hasFlag(fe + fi, ff - 1, TileFlags.WALL_LEFT_SIDE)) {
                z = 0;
                fe = I(fe + fi) - fi;
            }
        } else if (GameMap.hasFlag(fe - fi - 1, ff - 1, TileFlags.WALL_RIGHT_SIDE)) {
            z = 0;
            fe = I(fe - fi - 1) + GameMap.TILE_SIZE + fi;
        }

        fe += z;
        if (z > 0) {
            --z;
        } else if (z < 0) {
            ++z;
        } else if (!Skills.standOnWater) {
            B = 12;
            fo = 0;
        } else {
            B = 3;
            A = -8;
            E = 0;
            D = 0;
        }

        if (tick % 8 == 0) {
            if (PlayerStats.drainMp(3)) {
                z = 0;
                B = 12;
            }

            hudDirty = true;
        }

        for(int var0 = 0; var0 < iq; ++var0) {
            if (a(fe - is[var0]) < 20 && it[var0] > ff && it[var0] - 24 < ff) {
                z = 0;
                if (fe < is[var0]) {
                    fe = is[var0] - 20;
                } else {
                    fe = is[var0] + 20;
                }

                B = 12;
                break;
            }
        }

        WaterSplash.spawn(fe, ff);
        Dust.spawn(fm, fe - (fm << 3), ff);
    }

    private static boolean updateCutscene() {
        if (!cutscenePlaying) {
            if (letterboxHeight > 1) {
                letterboxHeight >>= 1;
                hudDirty = true;
            }

            return false;
        } else {
            if (ga) {
                if (Input.pressed[Input.KEY_FIRE]) {
                    Input.clearPressed();
                    Dialogue.advance();
                    if (Dialogue.isFinished()) {
                        Dialogue.visible = false;
                        ga = false;
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

                if (++fZ >= Cutscene.triggerFrames[cutsceneId][cutsceneStep]) {
                    fZ = Cutscene.triggerFrames[cutsceneId][cutsceneStep];
                    int var0;
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
                            if (B == 1) {
                                captionVisible = false;
                                var0 = 0;
                                int var1 = 0;
                                switch (Cutscene.lineSpeakerKind[Cutscene.args[cutsceneId][cutsceneStep]]) {
                                    case Cutscene.SPEAKER_PLAYER:
                                        var0 = fe;
                                        var1 = ff;
                                        break;
                                    case Cutscene.SPEAKER_NPC:
                                        var0 = npcX[Cutscene.lineSpeakerIndex[Cutscene.args[cutsceneId][cutsceneStep]]];
                                        var1 = npcY[Cutscene.lineSpeakerIndex[Cutscene.args[cutsceneId][cutsceneStep]]];
                                        break;
                                    case Cutscene.SPEAKER_CLASSMATE:
                                        var0 = dz[Cutscene.lineSpeakerIndex[Cutscene.args[cutsceneId][cutsceneStep]]];
                                        var1 = dA[Cutscene.lineSpeakerIndex[Cutscene.args[cutsceneId][cutsceneStep]]];
                                        break;
                                    case Cutscene.SPEAKER_GIRL:
                                    case Cutscene.SPEAKER_GIRL_2:
                                        var0 = gn;
                                        var1 = go;
                                }

                                var1 -= 40;
                                startDialogue(DialogueText.cutsceneLines[Cutscene.args[cutsceneId][cutsceneStep]], Dialogue.NO_ACTION, var0, var1);
                                ++cutsceneStep;
                                ga = true;
                            }
                            break;
                        case Cutscene.CMD_SHOW_GUIDE_MARKER:
                            setGuideMarker(mapId, Cutscene.args[cutsceneId][cutsceneStep], ff - 50, -1);
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_HIDE_GUIDE_MARKER:
                            guideMarkerMap = -1;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CAMERA_FOLLOW_PLAYER:
                            fu = true;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CAMERA_FREE:
                            fu = false;
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
                        case Cutscene.CMD_CAMERA_PAN_TO_BAMBOO_BOSS:
                            cameraTargetX = gP - Viewport.viewCenterX;
                            cameraTargetY = gQ - Viewport.viewCenterY;
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
                            dx = true;
                            dy = Cutscene.args[cutsceneId][cutsceneStep];
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CHANGE_MAP:
                            fp = fe = MapExits.rightSpawnX;
                            fq = ff = MapExits.rightSpawnY;
                            releaseMapResources();
                            Save.pendingSave = true;
                            changeMap(Cutscene.args[cutsceneId][cutsceneStep]);
                            return true;
                        case Cutscene.CMD_NEXT_NPC_MARKER:
                            setNextNpcMarker(Cutscene.args[cutsceneId][cutsceneStep]);
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_GIRL_FOLLOW:
                            gk = true;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_GIRL_STAY:
                            gk = false;
                            gm = 0;
                            gn = 1080;
                            go = 408;
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
                            for(var0 = 0; var0 <= 6; ++var0) {
                                GameMap.clearFlags(24, var0);
                            }

                            Story.riverMonsterDefeated = true;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CAMERA_PAN_TO_CLASSMATE:
                            fu = false;
                            cameraTargetX = dz[Cutscene.args[cutsceneId][cutsceneStep]] - Viewport.viewCenterX;
                            cameraTargetY = dA[Cutscene.args[cutsceneId][cutsceneStep]] - Viewport.viewCenterY;
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
                            dx = true;
                            dy = Cutscene.args[cutsceneId][cutsceneStep];
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_STOP_FIRST_CLASSMATE:
                            he = true;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_TONIKU_DEFEATED:
                            for(var0 = 0; var0 <= 10; ++var0) {
                                GameMap.clearFlags(24, var0);
                            }

                            Story.tonikuDefeated = true;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CAMERA_PAN_TO_FINAL_BOSS:
                            fu = false;
                            cameraTargetX = hp - Viewport.viewCenterX;
                            cameraTargetY = hq - Viewport.viewCenterY;
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
                            hr = 1;
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
                            motorbikeFrames = null;
                            itemCrate = null;
                            MoneyDrops.sprites = null;
                            boss0Sprites = null;
                            System.gc();
                            boss1Sprites = null;
                            System.gc();
                            Monsters.sprites = null;
                            System.gc();
                            C();
                            classmateHeads = null;
                            npcDog = null;
                            Projectiles.sprites = null;
                            Dust.sprites = null;
                            dartFrames = null;
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
                            K();
                            return true;
                        case Cutscene.CMD_GIRL_OUTING_DONE:
                            QuestState.current = Quest.NONE;
                            Story.girlWaiting = false;
                            Story.girlOutingDone = true;
                            gm = 0;
                            gn = 648;
                            go = 288;
                            gk = false;
                            Notice.show("gặp cô Ayumi");
                            setGuideMarker(0, 1032, 368, 4);
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_CAMERA_PAN_TO_GIRL:
                            fu = false;
                            cameraTargetX = gn - Viewport.viewCenterX;
                            cameraTargetY = go - Viewport.viewCenterY;
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
                            gk = true;
                            gr = 2;
                            gp = -5;
                            gz = -1;
                            gK = 8;
                            ++cutsceneStep;
                            break;
                        case Cutscene.CMD_START_SAKURA_ESCORT:
                            Story.sakuraEscorting = true;
                            gk = true;
                            ++cutsceneStep;
                    }
                }
            }

            Input.held[Input.KEY_FIRE] = false;
            Input.pressed[Input.KEY_FIRE] = false;
            return false;
        }
    }

    private static void Y() {
        for(int var0 = 0; var0 < du; ++var0) {
            int var10002;
            switch (dD[var0]) {
                case 1:
                    var10002 = dE[var0]++;
                    if (dE[var0] > 30) {
                        dE[var0] = 0;
                    }

                    if (dE[var0] % (var0 + 18) < var0 + 6) {
                        dG[var0] = 0;
                    } else {
                        dG[var0] = 1;
                    }

                    if (dx) {
                        dF[var0] = dy;
                        dJ[var0] = dy > dz[var0] ? 1 : -1;
                        dB[var0] = dJ[var0] << 2;
                        dB[var0] = dJ[var0] > 0 ? dB[var0] + var0 % 2 : dB[var0] - var0 % 2;
                        dD[var0] = 2;
                    } else {
                        if (dw) {
                            break;
                        }

                        var10002 = dF[var0]++;
                        if (dF[var0] > (dK[var0] + dz[var0]) % 20 + (dK[var0] >> 1)) {
                            if (Dialogue.visible && fE == var0) {
                                break;
                            }

                            dF[var0] = 0;
                            int var2;
                            if ((var2 = (tick + dK[var0]) % 120 - 60) > 0) {
                                var2 += dK[var0];
                            } else {
                                var2 -= dK[var0];
                            }

                            dF[var0] = dz[var0] + var2;
                            dJ[var0] = var2 > 0 ? 1 : -1;
                            dB[var0] = dJ[var0] << 2;
                            dD[var0] = 2;
                            if (fE == var0) {
                                fE = -1;
                                rightSoftKeyLabel = " ";
                                hudDirty = true;
                            }
                        }

                        if (tick % 10 == 1) {
                            B(var0);
                        }
                    }
                    break;
                case 2:
                    var10002 = dE[var0]++;
                    if (dE[var0] >= 10) {
                        dE[var0] = 0;
                    }

                    dG[var0] = (dE[var0] >> 1) + 2;
                    int[] var10000 = dz;
                    var10000[var0] += dB[var0];
                    if (dz[var0] <= dN[var0] && dz[var0] >= dM[var0] && a(dz[var0] - dF[var0]) >= 8) {
                        if (tick % 10 == 0) {
                            B(var0);
                        }
                    } else {
                        dE[var0] = dF[var0] = 0;
                        var10000 = dz;
                        var10000[var0] -= dB[var0];
                        dB[var0] = 0;
                        dD[var0] = 1;
                    }
                case 3:
                case 4:
                default:
                    break;
                case 5:
                    var10002 = dE[var0]++;
                    switch (dF[var0]) {
                        case 0:
                            switch (dE[var0]) {
                                case 1:
                                case 2:
                                case 3:
                                    dI[var0] = -1;
                                    dG[var0] = 13;
                                    continue;
                                case 4:
                                    dI[var0] = 0;
                                    dG[var0] = 14;
                                    continue;
                                case 5:
                                    dI[var0] = 1;
                                    dG[var0] = 15;
                                    continue;
                                case 6:
                                    dI[var0] = 1;
                                    dG[var0] = 15;
                                    A(var0);
                                    continue;
                                case 7:
                                    dI[var0] = 2;
                                    dG[var0] = 16;
                                    continue;
                                case 8:
                                    dI[var0] = 2;
                                    dG[var0] = 16;
                                    dF[var0] = 2;
                                    dE[var0] = 1;
                                default:
                                    continue;
                            }
                        case 1:
                            switch (dE[var0]) {
                                case 1:
                                    dI[var0] = 1;
                                    dG[var0] = 15;
                                    continue;
                                case 2:
                                    dI[var0] = 1;
                                    dG[var0] = 15;
                                    dF[var0] = 10;
                                default:
                                    continue;
                            }
                        case 2:
                            switch (dE[var0]) {
                                case 1:
                                case 2:
                                case 3:
                                    dI[var0] = 3;
                                    dG[var0] = 17;
                                    continue;
                                case 4:
                                    dI[var0] = 4;
                                    dG[var0] = 18;
                                    continue;
                                case 5:
                                    dI[var0] = 5;
                                    dG[var0] = 19;
                                    continue;
                                case 6:
                                    dI[var0] = 6;
                                    dG[var0] = 20;
                                    A(var0);
                                    continue;
                                case 7:
                                    dI[var0] = 6;
                                    dG[var0] = 20;
                                    dF[var0] = 3;
                                    dE[var0] = 1;
                                default:
                                    continue;
                            }
                        case 3:
                            switch (dE[var0]) {
                                case 1:
                                    dI[var0] = 1;
                                    dG[var0] = 15;
                                    break;
                                case 2:
                                    dI[var0] = 1;
                                    dG[var0] = 15;
                                    break;
                                case 3:
                                    dI[var0] = 2;
                                    dG[var0] = 16;
                                    break;
                                case 4:
                                    dI[var0] = 2;
                                    dG[var0] = 16;
                                    dF[var0] = 1;
                                    dE[var0] = 1;
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
                            switch (dE[var0]) {
                                case 1:
                                    dI[var0] = 2;
                                    dG[var0] = 16;
                                    break;
                                case 2:
                                    dI[var0] = 0;
                                    dG[var0] = 14;
                                    break;
                                case 3:
                                case 4:
                                    dG[var0] = 13;
                                    dI[var0] = -1;
                                    break;
                                case 5:
                                    dG[var0] = 13;
                                    dI[var0] = -1;
                                    dD[var0] = 1;
                                    dF[var0] = 120;
                                    dE[var0] = 0;
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
            Y();
            R();
            Dialogue.typeTick();
        }
    }

    private static void aa() {
        for(int var0 = 0; var0 < cp; ++var0) {
            int var10002;
            if (cq[var0] > 0) {
                var10002 = cq[var0]--;
            }

            int[] var10000;
            label143:
            switch (cE[var0]) {
                case 1:
                    cJ[var0] = 1;
                    var10002 = cF[var0]++;
                    var10000 = cu;
                    var10000[var0] += cy[var0];
                    var10000 = cv;
                    var10000[var0] += cz[var0];
                    var10000 = cw;
                    var10000[var0] += cA[var0];
                    var10000 = cx;
                    var10000[var0] += cB[var0];
                    if (cF[var0] > 14) {
                        if (cy[var0] > 1) {
                            var10002 = cy[var0]--;
                        } else if (cy[var0] < -1) {
                            var10002 = cy[var0]++;
                        }

                        var10002 = cz[var0]++;
                    }

                    if (cA[var0] > 1) {
                        var10002 = cA[var0]--;
                    } else if (cA[var0] < -1) {
                        var10002 = cA[var0]++;
                    }

                    var10002 = cB[var0]++;
                    if (cv[var0] <= cameraY + Viewport.viewHeight + 32 && cv[var0] < GameMap.pixelHeight) {
                        continue;
                    }

                    cF[var0] = 0;
                    cG[var0] = 0;
                    cE[var0] = 5;
                    break;
                case 2:
                    if (tick % 12 > 7) {
                        cC[var0] = 1;
                        cI[var0] = 1;
                        if (tick % 12 < 10) {
                            if (a(cu[var0] - cG[var0]) < 4) {
                                cF[var0] = cG[var0] = 0;
                                cE[var0] = 3;
                                break;
                            }

                            var10000 = cu;
                            var10000[var0] += cy[var0];
                        }

                        w(var0);
                    } else {
                        cI[var0] = 0;
                        cC[var0] = 0;
                    }
                    break;
                case 3:
                    if ((tick + var0) % 10 > 5) {
                        cC[var0] = 1;
                    } else {
                        cC[var0] = 0;
                    }

                    var10002 = cF[var0]++;
                    cI[var0] = 0;
                    cJ[var0] = 0;
                    if (cF[var0] > 50) {
                        int var1;
                        if ((var1 = (tick - var0) % (var0 + 80) - 40) > 0) {
                            var1 += 20;
                        } else {
                            var1 -= 20;
                        }

                        cG[var0] = cu[var0] + var1;
                        cH[var0] = var1 > 0 ? 1 : -1;
                        if (tick % 4 > 1) {
                            cE[var0] = 2;
                            cy[var0] = cH[var0] << 1;
                        } else {
                            cE[var0] = 7;
                            cy[var0] = cH[var0] << 2;
                        }

                        if (cG[var0] < cK[var0]) {
                            cG[var0] = cK[var0];
                        }

                        if (cG[var0] > cL[var0]) {
                            cG[var0] = cL[var0];
                        }
                    }

                    if (tick % 30 == 28) {
                        w(var0);
                        v(var0);
                    }
                    break;
                case 4:
                    var10002 = cF[var0]++;
                    if (cF[var0] < 3) {
                        cC[var0] = 0;
                        cI[var0] = 0;
                        cJ[var0] = 0;
                    } else if (cF[var0] < 14) {
                        cC[var0] = 1;
                        cI[var0] = 1;
                        cJ[var0] = 1;
                    } else {
                        cE[var0] = 3;
                        cF[var0] = 1;
                    }

                    if (cF[var0] == 3) {
                        switch (cD[var0]) {
                            case 0:
                            case 4:
                                Projectiles.spawn(cu[var0] + (cH[var0] << 3), cv[var0] - 15, cH[var0] * 12, NinjaStats.damage[cD[var0]], 0);
                                break label143;
                            case 1:
                            case 5:
                                Projectiles.spawn(cu[var0] + (cH[var0] << 3), cv[var0] - 15, cH[var0] << 3, NinjaStats.damage[cD[var0]], 1);
                                break label143;
                            case 2:
                            case 3:
                            case 6:
                                if (a(ff - cv[var0]) < 5 && a(fe - cu[var0]) < 30) {
                                    a(NinjaStats.damage[cD[var0]], cH[var0]);
                                }
                        }
                    }
                    break;
                case 5:
                    var10002 = cF[var0]++;
                    if (cF[var0] > 550 + var0 * 5) {
                        cu[var0] = cs[var0];
                        cv[var0] = ct[var0];
                        cF[var0] = 0;
                        cE[var0] = 3;
                        cr[var0] = NinjaStats.maxHp[cD[var0]];
                    }
                    break;
                case 6:
                    if ((tick + var0) % 10 > 5) {
                        cC[var0] = 1;
                    } else {
                        cC[var0] = 0;
                    }

                    cI[var0] = 0;
                    cJ[var0] = 0;
                    var10002 = cF[var0]++;
                    if (cF[var0] >= 5) {
                        cG[var0] = fe;
                        if (cu[var0] < fe) {
                            var10000 = cG;
                            var10000[var0] -= 80;
                        } else {
                            var10000 = cG;
                            var10000[var0] += 80;
                        }

                        cF[var0] = 0;
                        cH[var0] = cG[var0] < fe ? -1 : 1;
                        cE[var0] = 7;
                        cy[var0] = cH[var0] << 3;
                        if (cG[var0] < cK[var0]) {
                            cG[var0] = cK[var0];
                        }

                        if (cG[var0] > cL[var0]) {
                            cG[var0] = cL[var0];
                        }
                    }
                    break;
                case 7:
                    if (a(cu[var0] - cG[var0]) < 12) {
                        cF[var0] = cG[var0] = 0;
                        cE[var0] = 3;
                        v(var0);
                    } else {
                        var10000 = cu;
                        var10000[var0] += cy[var0];
                        if (tick % 2 == 1) {
                            cC[var0] = 1;
                            cI[var0] = 3;
                        } else {
                            cI[var0] = 2;
                            cC[var0] = 0;
                        }
                    }
            }

            cw[var0] = cu[var0];
            cx[var0] = cv[var0] - 9 + cC[var0] + NinjaStats.bodyOffsetY[cD[var0]][cJ[var0]];
        }

    }

    private static void ac() {
        if (hi) {
            int var0 = tick % 10;
            int var1 = a(fe - hp);
            int var2 = a(ff - hq);
            switch (hr) {
                case 0:
                    if (var0 < 7) {
                        hH = hx[0];
                        hI = hy[0];
                    } else {
                        hH = hx[1];
                        hI = hy[1];
                    }
                    break;
                case 1:
                    if (var0 < 7) {
                        hH = hx[0];
                        hI = hy[0];
                    } else {
                        hH = hx[1];
                        hI = hy[1];
                    }

                    if (var1 > 90 && var1 < 100 && var0 < 2) {
                        hs = hp < fe ? 1 : -1;
                        hr = 23;
                        hv = 0;
                    } else if ((var1 > 120 && var1 < 150 || ff < hq && var1 > 40) && var0 < 2) {
                        hs = hp < fe ? 1 : -1;
                        hv = 0;
                        hr = 8;
                    } else if (var1 < 40 && var0 == 1) {
                        hs = hp < fe ? 1 : -1;
                        hv = 0;
                        hr = 5;
                    } else if (var1 > 40 && var1 < 100 && var0 < 3) {
                        hs = hp < fe ? 1 : -1;
                        hv = 0;
                        if ((hw = fe) > hu) {
                            hw = hu;
                        } else if (hw < ht) {
                            hw = ht;
                        }

                        hr = 24;
                    } else if (var1 > 80 && var0 == 2) {
                        hv = 0;
                        hr = 2;
                        if ((hw = fe) > hu) {
                            hw = hu;
                        } else if (hw < ht) {
                            hw = ht;
                        }
                    }
                    break;
                case 2:
                    if ((var2 = ++hv % 6 >> 1) == 3) {
                        aL = 0;
                    }

                    hH = hB[var2];
                    hI = hC[var2];
                    if ((hp += hs << 2) > hw && hs > 0 || hp < hw && hs < 0 || hp > hu || hp < ht || hv > 100) {
                        hv = 0;
                        hr = 1;
                    }

                    if (var1 > 50 && var1 < 100 && var0 == 1) {
                        hs = hp < fe ? 1 : -1;
                        hr = 23;
                        hv = 0;
                    }
                    break;
                case 5:
                    if (++hv < 2) {
                        hH = hx[0];
                        hI = hy[0];
                    } else if (hv < 2) {
                        hH = hz[0];
                        hI = hA[0];
                    } else if (hv < 12) {
                        hH = hz[1];
                        hI = hA[1];
                    } else if (hv < 14) {
                        hH = hz[0];
                        hI = hA[0];
                    } else if (hv == 14) {
                        hv = 0;
                        hr = 1;
                    }

                    if (hv == 3 && var1 < 40 && var2 < 10) {
                        a(hj, hs);
                    }
                    break;
                case 6:
                    ++hv;

                    for(var0 = 0; var0 < hG.length; ++var0) {
                        int[] var10000 = hG;
                        var10000[var0] += a(var0 - 3) + 2;
                        var10000 = hF;
                        var10000[var0] += var0 - 3 << 1;
                    }

                    if (hv >= 50) {
                        hr = 22;
                    }
                    break;
                case 8:
                    if (++hv < 2) {
                        hH = hx[0];
                        hI = hy[0];
                    } else if (hv < 2) {
                        hH = hz[0];
                        hI = hA[0];
                    } else if (hv < 12) {
                        hH = hz[1];
                        hI = hA[1];
                    } else if (hv < 14) {
                        hH = hz[0];
                        hI = hA[0];
                    } else if (hv == 14) {
                        hv = 0;
                        hr = 1;
                    }

                    if (hv % 5 == 0) {
                        a(hp + (hs << 3), hq - 20, hs << 3, -var0 + 2, false);
                    }
                    break;
                case 23:
                    var0 = ++hv % 4 >> 1;
                    hH = hz[var0];
                    hI = hA[var0];
                    if (hv > 20 && hv % 10 == 0) {
                        Projectiles.spawn(hp + (hs << 3), hq - 24, hs << 3, hj, 3);
                    }

                    if (hv == 100) {
                        hs = hp < fe ? 1 : -1;
                        hv = 0;
                        if ((hw = fe) > hu) {
                            hw = hu;
                        } else if (hw < ht) {
                            hw = ht;
                        }

                        hr = 24;
                    }
                    break;
                case 24:
                    var0 = ++hv % 6 >> 1;
                    hH = hB[var0];
                    hI = hC[var0];
                    if ((hp += hs << 3) > hw && hs > 0 || hp < hw && hs < 0 || hp > hu || hp < ht || hv > 100) {
                        hv = 0;
                        hr = 5;
                    }
            }

            for(var2 = 0; var2 < 6; ++var2) {
                if (hs == 1) {
                    hD[var2] = hp + hF[var2] + hH[var2];
                } else {
                    hD[var2] = hp - hF[var2] - hH[var2];
                }

                hE[var2] = hq + hG[var2] + hI[var2];
            }

        }
    }

    private static void updateMapEvent() {
        if (mapEventActive) {
            switch (MapEvent.conditions[mapEventId][mapEventStep]) {
                case MapEvent.WAIT_PLAYER_PAST_X:
                    if (fe > MapEvent.paramX[mapEventId][mapEventStep]) {
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
                    if (fe > cameraX + Viewport.viewWidth) {
                        startCutscene(MapEvent.cutscenes[mapEventId][mapEventStep]);
                        ++mapEventStep;
                        return;
                    }
                    break;
                case MapEvent.WAIT_PLAYER_AT:
                    if (fe < MapEvent.paramX[mapEventId][mapEventStep] && fe > MapEvent.paramX[mapEventId][mapEventStep] - 96 && ff == MapEvent.paramY[mapEventId][mapEventStep]) {
                        startCutscene(MapEvent.cutscenes[mapEventId][mapEventStep]);
                        ++mapEventStep;
                    }

                    // Bản gốc thiếu lệnh này nên rơi xuống WAIT_BAMBOO_BOSS_DEFEATED.
                    return;
                case MapEvent.WAIT_BAMBOO_BOSS_DEFEATED:
                    if (gS == 22) {
                        startCutscene(MapEvent.cutscenes[mapEventId][mapEventStep]);
                        ++mapEventStep;
                        return;
                    }
                    break;
                case MapEvent.WAIT_RIVER_MONSTER_SUNK:
                    if (hX == 5) {
                        startCutscene(MapEvent.cutscenes[mapEventId][mapEventStep]);
                        ++mapEventStep;
                        return;
                    }
                    break;
                case MapEvent.WAIT_TONIKU_DEFEATED:
                    if (hg < 10) {
                        startCutscene(MapEvent.cutscenes[mapEventId][mapEventStep]);
                        ++mapEventStep;
                        return;
                    }
                    break;
                case MapEvent.WAIT_FINAL_BOSS_DEFEATED:
                    if (hr == 22) {
                        startCutscene(MapEvent.cutscenes[mapEventId][mapEventStep]);
                        Story.sakuraFound = true;
                        ++mapEventStep;
                        return;
                    }
            }
        } else if (mapEventId == 10) {
            Display.getDisplay(Midlet.instance).setCurrent(Midlet.b);
            mapEventId = 0;
        }

    }

    public static void s() {
        Midlet.b = new b();
        updateMapEvent();
        b var0 = Midlet.b;
        Save.setBackgroundIndex((Save.backgroundIndex = Save.getBackgroundIndex()) == 0 ? 1 : 0);
        LogoScreen.load();
        GameState.current = GameState.LOGO;
        (new Thread(var0)).start();
    }

    public b() {
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

    protected final void keyPressed(int var1) {
        int cheat = CheatCodes.onKeyPressed(var1);
        if (cheat != CheatCodes.RESULT_NOT_TRIGGERED) {
            if (cheat == CheatCodes.RESULT_GAIN_EXP) {
                gainExp(CheatCodes.EXP_BONUS);
            }

            hudDirty = true;
        }

        var1 = Input.normalizeKeyCode(var1);
        if (!cutscenePlaying || var1 == Input.CODE_FIRE) {
            Input.keyDown(var1);
        }
    }

    protected final void keyReleased(int var1) {
        var1 = Input.normalizeKeyCode(var1);
        if (!cutscenePlaying || var1 == Input.CODE_FIRE) {
            Input.keyUp(var1);
        }
    }

    protected final void paint(Graphics var1) {
        try {
            switch (GameState.current) {
                case GameState.PLAYING:
                    Graphics var6 = var1;
                    var1.setClip(-1, -1, Viewport.viewWidth + 2, Viewport.viewHeight + 2);
                    drawSky(var1);
                    var1.translate(-cameraX, -cameraY);
                    var1 = var1;

                    int var2;
                    for(var2 = 0; var2 < houseCount; ++var2) {
                        if (houseX[var2] + houseHalfWidth[var2] >= cameraX && houseX[var2] - houseHalfWidth[var2] <= cameraX + Viewport.viewWidth && houseY[var2] >= cameraY && houseY[var2] - houseHeight[var2] <= cameraY + Viewport.viewHeight) {
                            jg = (jf = houseX[var2] - cameraX) - Viewport.viewCenterX;
                            jh = (ji = (GameMap.TILE_SIZE - 2) * jg / GameMap.TILE_SIZE) + Viewport.viewCenterX;
                            var1.drawImage(houseImages[houseTypes[var2]], cameraX + jh, houseY[var2], 33);
                        }
                    }

                    if (Settings.lowGraphics) {
                        j(var6);
                    } else {
                        k(var6);
                    }

                    drawSigns(var6);
                    drawNpcs(var6);
                    if (hJ) {
                        if (hY < 0) {
                            var6.drawImage(boss1Sprites[0], hP, hQ, 33);
                            var6.drawImage(boss1Sprites[2], hR, hV, 33);
                            var6.drawImage(boss1Sprites[3], hU, hW, 33);
                            var6.drawImage(boss1Sprites[1], hS, hT, 33);
                        } else {
                            var6.drawRegion(boss1Sprites[0], 0, 0, boss1Sprites[0].getWidth(), boss1Sprites[0].getHeight(), 2, hP, hQ, 33);
                            var6.drawRegion(boss1Sprites[2], 0, 0, boss1Sprites[2].getWidth(), boss1Sprites[2].getHeight(), 2, hR, hV, 33);
                            var6.drawRegion(boss1Sprites[3], 0, 0, boss1Sprites[3].getWidth(), boss1Sprites[3].getHeight(), 2, hU, hW, 33);
                            var6.drawRegion(boss1Sprites[1], 0, 0, boss1Sprites[1].getWidth(), boss1Sprites[1].getHeight(), 2, hS, hT, 33);
                        }

                        if (ic != 0) {
                            var6.setColor(3342353);
                            var6.drawRect(hP - (hO >> 1) - 1, hQ - hL - 7, hO + 2, 4);
                            var2 = hM * hO / hN;
                            var6.setColor(15597568);
                            var6.fillRect(hP - (hO >> 1), hQ - hL - 6, var2, 3);
                            var6.setColor(4456482);
                            var6.fillRect(hP - (hO >> 1) + var2, hQ - hL - 6, hO - var2 + 1, 3);
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < iq; ++var2) {
                        if (cameraX <= is[var2] + 12 && cameraX + Viewport.viewWidth >= is[var2] - 12 && cameraY <= it[var2] && cameraY + Viewport.viewHeight >= it[var2] - 24) {
                            var1.drawImage(itemCrate, is[var2], it[var2], 33);
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < ii; ++var2) {
                        if (cameraX <= ik[var2] + 12 && cameraX + Viewport.viewWidth >= ik[var2] - 12 && cameraY <= il[var2] && cameraY + Viewport.viewHeight >= il[var2] - 24) {
                            if (im[var2] == 1) {
                                var1.drawImage(motorbikeFrames[in[var2]], ik[var2], il[var2], 33);
                            } else {
                                var1.drawRegion(motorbikeFrames[in[var2]], 0, 0, motorbikeFrames[in[var2]].getWidth(), motorbikeFrames[in[var2]].getHeight(), 2, ik[var2], il[var2], 33);
                            }
                        }
                    }

                    var1 = var6;

                    MoneyDrops.draw(var1, cameraX, cameraY);
                    PotionDrops.draw(var1, cameraX, cameraY);

                    int var3;
                    if (gN && gS != 22 && cameraX + Viewport.viewWidth >= gP - (gV >> 1) && cameraX <= gP + (gV >> 1) && cameraY <= gQ && cameraY + Viewport.viewHeight >= gQ - gW) {
                        byte var8 = 0;
                        if (gU == 0) {
                            var8 = -2;
                            if (gS == 1 && tick % 10 > 5) {
                                var8 = -1;
                            }

                            if (gT == 1) {
                                var6.drawImage(boss0Sprites[4], gP, gQ + 1, 33);
                            } else {
                                var6.drawRegion(boss0Sprites[4], 0, 0, boss0Sprites[4].getWidth(), boss0Sprites[4].getHeight(), 2, gP, gQ + 1, 33);
                            }
                        }

                        if (gT == 1) {
                            var6.drawImage(boss0Sprites[gU], gP, gQ + var8 + 1, 33);
                        } else {
                            var6.drawRegion(boss0Sprites[gU], 0, 0, boss0Sprites[gU].getWidth(), boss0Sprites[gU].getHeight(), 2, gP, gQ + var8 + 1, 33);
                        }

                        if (gS == 7) {
                            if (gT == 1) {
                                var6.drawImage(boss0Sprites[3], gP + 10, gQ - 25 + 1, 0);
                            } else {
                                var6.drawRegion(boss0Sprites[3], 0, 0, boss0Sprites[3].getWidth(), boss0Sprites[3].getHeight(), 2, gP - 10, gQ - 25 + 1, 24);
                            }
                        }

                        if (hb != 0) {
                            var6.setColor(3342353);
                            var6.drawRect(gP - (gV >> 1) - 1, gQ - gW - 7, gV + 2, 4);
                            var3 = gZ * gV / ha;
                            var6.setColor(15597568);
                            var6.fillRect(gP - (gV >> 1), gQ - gW - 6, var3, 3);
                            var6.setColor(4456482);
                            var6.fillRect(gP - (gV >> 1) + var3, gQ - gW - 6, gV - var3 + 1, 3);
                        }
                    }

                    var1 = var6;
                    if (hi) {
                        if (hs > 0) {
                            for(var2 = 0; var2 < 6; ++var2) {
                                var1.drawImage(boss2Sprites[var2], hD[var2], hE[var2], 33);
                            }
                        } else {
                            for(var2 = 0; var2 < 6; ++var2) {
                                var1.drawRegion(boss2Sprites[var2], 0, 0, boss2Sprites[var2].getWidth(), boss2Sprites[var2].getHeight(), 2, hD[var2], hE[var2], 33);
                            }
                        }

                        if (ho != 0) {
                            var1.setColor(3342353);
                            var1.drawRect(hp - (hk >> 1) - 1, hq - hl - 7, hk + 2, 4);
                            var2 = hm * hk / hn;
                            var1.setColor(15597568);
                            var1.fillRect(hp - (hk >> 1), hq - hl - 6, var2, 3);
                            var1.setColor(4456482);
                            var1.fillRect(hp - (hk >> 1) + var2, hq - hl - 6, hk - var2 + 1, 3);
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < Monsters.count; ++var2) {
                        if (Monsters.state[var2] != Monsters.STATE_RESPAWN_WAIT && Monsters.state[var2] != Monsters.STATE_GONE && Monsters.x[var2] >= cameraX - 10 && Monsters.x[var2] <= cameraX + Viewport.viewWidth + 10 && Monsters.y[var2] >= cameraY && Monsters.y[var2] <= cameraY + Viewport.viewHeight + 20) {
                            var1.drawImage(Monsters.sprites[Monsters.group[var2]][Monsters.direction[var2] >= 0 ? Mob.MONSTER_DIR_RIGHT : Mob.MONSTER_DIR_LEFT][Monsters.frame[var2]], Monsters.x[var2], Monsters.y[var2], 33);
                            if (Monsters.healthBarTimer[var2] != 0) {
                                var1.setColor(13073);
                                var1.drawRect(Monsters.x[var2] - (Monsters.width[var2] >> 1) - 1, Monsters.y[var2] - Monsters.height[var2] - 7, Monsters.width[var2] + 2, 4);
                                var3 = Monsters.hp[var2] * Monsters.width[var2] / Monsters.maxHp[var2];
                                var1.setColor(60928);
                                var1.fillRect(Monsters.x[var2] - (Monsters.width[var2] >> 1), Monsters.y[var2] - Monsters.height[var2] - 6, var3, 3);
                                var1.setColor(17442);
                                var1.fillRect(Monsters.x[var2] - (Monsters.width[var2] >> 1) + var3, Monsters.y[var2] - Monsters.height[var2] - 6, Monsters.width[var2] - var3 + 1, 3);
                            }
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < cp; ++var2) {
                        if (cE[var2] != 5 && cu[var2] + 15 >= cameraX && cu[var2] - 15 <= cameraX + Viewport.viewWidth && cv[var2] >= cameraY && cv[var2] - 30 <= cameraY + Viewport.viewHeight) {
                            if (cH[var2] == -1) {
                                var1.drawImage(ninjaLegs[cI[var2]], cu[var2] + NinjaStats.legsOffsetX[cD[var2]], cv[var2], 33);
                                var1.drawImage(ninjaBodies[cD[var2]][cJ[var2]], cw[var2] + NinjaStats.bodyOffsetX[cD[var2]][cJ[var2]], cx[var2], 33);
                            } else {
                                var1.drawRegion(ninjaLegs[cI[var2]], 0, 0, ninjaLegs[cI[var2]].getWidth(), ninjaLegs[cI[var2]].getHeight(), 2, cu[var2] - NinjaStats.legsOffsetX[cD[var2]], cv[var2], 33);
                                var1.drawRegion(ninjaBodies[cD[var2]][cJ[var2]], 0, 0, ninjaBodies[cD[var2]][cJ[var2]].getWidth(), ninjaBodies[cD[var2]][cJ[var2]].getHeight(), 2, cw[var2] - NinjaStats.bodyOffsetX[cD[var2]][cJ[var2]], cx[var2], 33);
                            }

                            if (cq[var2] != 0) {
                                var1.setColor(13073);
                                var1.drawRect(cu[var2] - 13, cv[var2] - 40, 26, 4);
                                var3 = cr[var2] * 24 / NinjaStats.maxHp[cD[var2]];
                                var1.setColor(15597568);
                                var1.fillRect(cu[var2] - 12, cv[var2] - 39, var3, 3);
                                var1.setColor(4456482);
                                var1.fillRect(cu[var2] - 12 + var3, cv[var2] - 39, 24 - var3 + 1, 3);
                            }
                        }
                    }

                    g(var6);
                    if (dn && doSt + 15 >= cameraX && doSt - 15 <= cameraX + Viewport.viewWidth && dp >= cameraY && dp - 10 <= cameraY + Viewport.viewHeight) {
                    var2 = tick % 8 > 3 ? 0 : 1;
                    if (dq > 0) {
                        var6.drawImage(npcDog[var2], doSt, dp, 33);
                    } else {
                        var6.drawRegion(npcDog[var2], 0, 0, npcDog[var2].getWidth(), npcDog[var2].getHeight(), 2, doSt, dp, 33);
                    }
                }

                if (hc) {
                    if (hd) {
                        var6.setColor(16768477);
                        var2 = cameraX + 50;
                        var3 = cameraY + 15;
                        if (!hudPanelVisible) {
                            var3 += 20;
                        }

                        var6.drawRect(var2 - 35 - 1, var3 - 7, 72, 4);
                        int var7 = hg * 70 / hh;
                        var6.setColor(15597568);
                        var6.fillRect(var2 - 35, var3 - 6, var7, 3);
                        var6.setColor(4456482);
                        var6.fillRect(var2 - 35 + var7, var3 - 6, 70 - var7 + 1, 3);
                    }

                    if (dz[0] + fi >= cameraX && dz[0] - fi <= cameraX + Viewport.viewWidth && dA[0] >= cameraY && dA[0] - fh <= cameraY + Viewport.viewHeight) {
                        if (dJ[0] == 1) {
                            var6.drawImage(classmateHeads[dH[0]][aI[dG[0]][0][0]], dz[0] + aI[dG[0]][0][1], dA[0] - aI[dG[0]][0][2], 0);
                            var6.drawImage(heroLegs[aI[dG[0]][1][0]], dz[0] + aI[dG[0]][1][1], dA[0] - aI[dG[0]][1][2], 0);
                            var6.drawImage(heroBodies[aI[dG[0]][2][0]], dz[0] + aI[dG[0]][2][1], dA[0] - aI[dG[0]][2][2], 0);
                            if (dI[0] != -1) {
                                var6.drawImage(slashEffects[dI[0]], dz[0] - as[dI[0]], dA[0] - at[dI[0]], 0);
                            }
                        } else {
                            var6.drawRegion(classmateHeads[dH[0]][aI[dG[0]][0][0]], 0, 0, classmateHeads[dH[0]][aI[dG[0]][0][0]].getWidth(), classmateHeads[dH[0]][aI[dG[0]][0][0]].getHeight(), 2, dz[0] - aI[dG[0]][0][1], dA[0] - aI[dG[0]][0][2], 24);
                            var6.drawRegion(heroLegs[aI[dG[0]][1][0]], 0, 0, heroLegs[aI[dG[0]][1][0]].getWidth(), heroLegs[aI[dG[0]][1][0]].getHeight(), 2, dz[0] - aI[dG[0]][1][1], dA[0] - aI[dG[0]][1][2], 24);
                            var6.drawRegion(heroBodies[aI[dG[0]][2][0]], 0, 0, heroBodies[aI[dG[0]][2][0]].getWidth(), heroBodies[aI[dG[0]][2][0]].getHeight(), 2, dz[0] - aI[dG[0]][2][1], dA[0] - aI[dG[0]][2][2], 24);
                            if (dI[0] != -1) {
                                var6.drawRegion(slashEffects[dI[0]], 0, 0, slashEffects[dI[0]].getWidth(), slashEffects[dI[0]].getHeight(), 2, dz[0] + as[dI[0]], dA[0] - at[dI[0]], 24);
                            }
                        }
                    }
                }

                Aura.draw(var6);

                if (gm == mapId) {
                    if (gz == 1) {
                        var6.drawImage(girlHeads[aq[gs][0][0]], gn + aq[gs][0][1], go - aq[gs][0][2], 0);
                        var6.drawImage(girlLegs[aq[gs][1][0]], gn + aq[gs][1][1], go - aq[gs][1][2], 0);
                        var6.drawImage(girlBodies[aq[gs][2][0]], gn + aq[gs][2][1], go - aq[gs][2][2], 0);
                        if (ig != -1) {
                            var6.drawImage(slashEffects[ig], gn - as[ig], go - at[ig], 0);
                        }
                    } else {
                        var6.drawRegion(girlHeads[aq[gs][0][0]], 0, 0, girlHeads[aq[gs][0][0]].getWidth(), girlHeads[aq[gs][0][0]].getHeight(), 2, gn - aq[gs][0][1], go - aq[gs][0][2], 24);
                        var6.drawRegion(girlLegs[aq[gs][1][0]], 0, 0, girlLegs[aq[gs][1][0]].getWidth(), girlLegs[aq[gs][1][0]].getHeight(), 2, gn - aq[gs][1][1], go - aq[gs][1][2], 24);
                        var6.drawRegion(girlBodies[aq[gs][2][0]], 0, 0, girlBodies[aq[gs][2][0]].getWidth(), girlBodies[aq[gs][2][0]].getHeight(), 2, gn - aq[gs][2][1], go - aq[gs][2][2], 24);
                        if (ig != -1) {
                            var6.drawRegion(slashEffects[ig], 0, 0, slashEffects[ig].getWidth(), slashEffects[ig].getHeight(), 2, gn + as[ig], go - at[ig], 24);
                        }
                    }
                }

                b(var6);
                if (questItemState != 0) {
                    var6.drawImage(itemScroll, questItemX, questItemY, 33);
                }

                Blood.draw(var6);
                Dust.draw(var6);
                var1 = var6;

                Projectiles.draw(var6);
                var1 = var6;

                for(var2 = 0; var2 < cV; ++var2) {
                    if (cY[var2] != -1) {
                        var1.drawImage(dartFrames[cZ[var2]], cW[var2], cX[var2], 3);
                    }
                }

                var1 = var6;

                ArcProjectiles.draw(var6);
                WaterSplash.draw(var6);
                l(var6);
                FloatingText.draw(var6);
                if (guideMarkerMap == mapId) {
                    var6.drawImage(arrowIcons[ge], gf, gg, 3);
                }

                drawHudOverlay(var6);
                if (hudDirty) {
                    drawHudPanel(var6);
                }

                if (letterboxHeight > 1) {
                    drawLetterbox(var6);
                }

                if (Notice.visible) {
                    Notice.draw(var6, tick);
                }

                if (Dialogue.visible) {
                    drawDialogue(var6);
                }

                return;
                case GameState.LOADING:
                    var1.setColor(0);
                    var1.fillRect(0, 0, Viewport.screenWidth, 21);
                    FontGraph.drawBold(var1, "Loading... " + loadingProgress + "%", Viewport.screenWidth >> 1, 4, 2);
                    return;
                case GameState.MAIN_MENU:
                    var1.setClip(-1, -1, Viewport.screenWidth + 2, Viewport.screenHeight + 1);
                    drawSky(var1);
                    var1.translate(-cameraX, -cameraY);
                    if (Settings.lowGraphics) {
                        j(var1);
                    } else {
                        k(var1);
                    }

                    drawSigns(var1);
                    l(var1);
                    var1.translate(-var1.getTranslateX(), -var1.getTranslateY());
                    var1.setColor(0);
                    var1.fillRect(0, 0, Viewport.viewWidth, ba);
                    var1.fillRect(0, bb, Viewport.viewWidth, Viewport.screenHeight - bb + 1);
                    var1.setColor(10275899);
                    var1.fillRect(0, ba - 1, Viewport.screenWidth, 1);
                    var1.fillRect(0, bb + 1, Viewport.screenWidth, 1);
                    var1.drawImage(titleImage, Viewport.screenCenterX, aY + bb >> 1, 3);
                    FontGraph.drawBold(var1, "*", Viewport.screenCenterX - 50 - tick % 3, bd, 0);
                    FontGraph.drawBold(var1, "/", Viewport.screenCenterX + 50 + tick % 3, bd, 1);
                    FontGraph.drawBold(var1, GameText.mainMenuItems[aX], Viewport.screenCenterX, bd, 3);
                    return;
                case GameState.LOGO:
                    LogoScreen.draw(var1);
                    return;
                case GameState.GAME_MENU:
                    GameMenu.draw(var1, weaponIcons[0]);
                    return;
                case GameState.WORLD_MAP:
                    WorldMap.draw(var1, heroHeads[0], weaponIcons[0]);
                    return;
                case GameState.MESSAGE:
                    MessageBox.draw(var1);
                    return;
                case GameState.INVENTORY:
                    drawInventory(var1);
                    return;
                case GameState.SHOP:
                    drawShop(var1);
                    return;
                case GameState.SETUP_PROMPT:
                    SetupPrompt.draw(var1);
                    return;
                case GameState.TUTORIAL:
                    drawTutorial(var1);
                    return;
                case GameState.UNUSED_12:
                    drawTutorial(var1);
                    return;
                default:
                    return;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    protected final void pointerDragged(int var1, int var2) {
        Input.pointerX = var1;
        Input.pointerY = var2;
    }

    protected final void pointerPressed(int var1, int var2) {
        Input.pointerX = var1;
        Input.pointerY = var2;
    }

    protected final void pointerReleased(int var1, int var2) {
        Input.pointerReleased = true;
        Input.pointerX = var1;
        Input.pointerY = var2;
    }

    public final void run() {
        while(b) {
            long var1 = System.currentTimeMillis();
            boolean var3;
            int var10002;
            switch (GameState.current) {
                case GameState.PLAYING:
                    if (++tick == 10000) {
                        tick = 0;
                    }

                    if (fe < 0) {
                        if (MapExits.leftMap != MapExits.NONE) {
                            releaseMapResources();
                            fp = fe = MapExits.leftSpawnX - 1;
                            fq = ff = MapExits.leftSpawnY;
                            Save.pendingSave = true;
                            changeMap(MapExits.leftMap);
                            break;
                        }
                    } else if (fe >= GameMap.pixelWidth - 24) {
                        if (MapExits.rightMap != MapExits.NONE) {
                            if (!Skills.hide && MapExits.rightMap == 95) {
                                MessageBox.show(MessageType.LEVEL_UP);
                                MessageBox.setText("Khu vực phía trước rất nguy hiểm! Chỉ những người có thể ẩn thân có thể vào!");
                                fe = GameMap.pixelWidth - 60;
                                B = 4;
                                GameState.current = GameState.MESSAGE;
                                break;
                            }

                            releaseMapResources();
                            if (MapExits.rightLowerMap != MapExits.NONE && ff > MapExits.rightLowerMinY) {
                                fp = fe = MapExits.rightLowerSpawnX;
                                fq = ff = MapExits.rightLowerSpawnY;
                                Save.pendingSave = true;
                                changeMap(MapExits.rightLowerMap);
                                break;
                            }

                            fp = fe = MapExits.rightSpawnX;
                            fq = ff = MapExits.rightSpawnY;
                            Save.pendingSave = true;
                            changeMap(MapExits.rightMap);
                            break;
                        }
                    } else if (ff + A >= GameMap.pixelHeight && B != 6 && MapExits.bottomMap != MapExits.NONE) {
                        releaseMapResources();
                        fp = fe = MapExits.bottomSpawnX;
                        fq = ff = MapExits.bottomSpawnY;
                        Save.pendingSave = true;
                        changeMap(MapExits.bottomMap);
                        B = 4;
                        break;
                    }

                    updateMapEvent();
                    Dialogue.typeTick();
                    if (updateCutscene()) {
                        break;
                    }

                    int var13;
                    if (!Settings.lowGraphics) {
                        Sky.updateClouds(tick, Viewport.viewWidth);
                    }

                    int var4;
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
                                if (fd == 0) {
                                    if (Skills.darts) {
                                        fd = 1;
                                    }
                                } else {
                                    fd = 0;
                                }

                                hudDirty = true;
                                Input.pressed[Input.KEY_NUM0] = false;
                            } else if (Input.pressed[Input.KEY_RIGHT]) {
                                fo = 2;
                                Input.pressed[Input.KEY_RIGHT] = false;
                            } else if (Input.pressed[Input.KEY_LEFT]) {
                                fo = -2;
                                Input.pressed[Input.KEY_LEFT] = false;
                            } else {
                                if (Input.pressed[Input.KEY_NUM9]) {
                                    if (Skills.hide) {
                                        fw = !fw;
                                    }

                                    Input.pressed[Input.KEY_NUM9] = false;
                                    break label2614;
                                }

                                if (Input.pressed[Input.KEY_NUM7]) {
                                    if (B == 1 && Skills.transform) {
                                        B = 23;
                                        D = 0;
                                    }

                                    Input.pressed[Input.KEY_NUM7] = false;
                                } else if (Input.pressed[Input.KEY_STAR]) {
                                    Input.pressed[Input.KEY_STAR] = false;
                                    if (!PlayerStats.isHpFull()) {
                                        for(var13 = 0; var13 < 3; ++var13) {
                                            if (Inventory.potionCounts[var13] > 0) {
                                                var10002 = Inventory.potionCounts[var13]--;
                                                PlayerStats.restoreHp(Inventory.potionRestoreAmounts[var13]);

                                                hudDirty = true;
                                                break;
                                            }
                                        }
                                    }
                                } else if (Input.pressed[Input.KEY_POUND]) {
                                    Input.pressed[Input.KEY_POUND] = false;
                                    if (!PlayerStats.isMpFull()) {
                                        for(var13 = 3; var13 < 6; ++var13) {
                                            if (Inventory.potionCounts[var13] > 0) {
                                                var10002 = Inventory.potionCounts[var13]--;
                                                PlayerStats.restoreMp(Inventory.potionRestoreAmounts[var13]);

                                                hudDirty = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }

                            if (B == 1) {
                                if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
                                    Input.clearPressed();
                                    if (rightSoftKeyLabel.equals("Giao tiếp")) {
                                        var4 = fD;
                                        switch (npcTypes[var4]) {
                                            case 0:
                                                if (Story.sakuraEscorting) {
                                                    startDialogue(DialogueText.idleLines[7], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                } else if (!Skills.lightness) {
                                                    setNextNpcMarker(2);
                                                    startDialogue(DialogueText.questGiveLines[0], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                } else {
                                                    if (Skills.hide && !Story.gameCompleted) {
                                                        if (QuestState.current == Quest.RESCUE_SAKURA) {
                                                            if (Story.sakuraFound) {
                                                                startDialogue(DialogueText.questRewardLines[14], QuestAction.COMPLETE_RESCUE_SAKURA, npcX[var4], npcY[var4] - 40);
                                                            } else {
                                                                startDialogue(DialogueText.questReminderLines[14], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                            }
                                                        } else if (QuestState.current == Quest.NONE) {
                                                            startDialogue(DialogueText.questGiveLines[22], QuestAction.START_RESCUE_SAKURA, npcX[var4], npcY[var4] - 40);
                                                        } else {
                                                            startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[var4], npcY[var4] - 40);
                                                        }
                                                        break;
                                                    }

                                                    startDialogue(DialogueText.idleLines[1], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                }
                                                break;
                                            case 1:
                                                if (Story.sakuraEscorting) {
                                                    startDialogue(DialogueText.idleLines[5], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                } else if (!Skills.lightness) {
                                                    if (QuestState.current == Quest.KILL_SNAILS) {
                                                        if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_SNAILS]) {
                                                            startDialogue(DialogueText.questReminderLines[0], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                        } else {
                                                            var3 = true;
                                                            boolean var8 = true;
                                                            boolean var7 = true;
                                                            boolean var6 = false;
                                                            pendingMarkerMap = 0;
                                                            pendingMarkerNpc = 4;
                                                            pendingMarkerX = 1044;
                                                            pendingMarkerY = 240;
                                                            startDialogue(DialogueText.questRewardLines[0], QuestAction.REWARD_LIGHTNESS, npcX[var4], npcY[var4] - 40);
                                                        }
                                                    } else if (QuestState.current == Quest.NONE) {
                                                        startDialogue(DialogueText.questGiveLines[1], QuestAction.START_KILL_SNAILS, npcX[var4], npcY[var4] - 40);
                                                    } else {
                                                        startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[var4], npcY[var4] - 40);
                                                    }
                                                } else if (!Story.goldenBatQuestDone && Skills.transform) {
                                                    if (PlayerStats.level < 10) {
                                                        startDialogue(DialogueText.levelRequired10, Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                    } else if (QuestState.current == Quest.KILL_GOLDEN_BATS) {
                                                        if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_GOLDEN_BATS]) {
                                                            startDialogue(DialogueText.questReminderLines[6], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                        } else {
                                                            setNextNpcMarker(2);
                                                            startDialogue(DialogueText.questRewardLines[6], QuestAction.REWARD_MONEY, npcX[var4], npcY[var4] - 40);
                                                        }
                                                    } else if (QuestState.current == Quest.NONE) {
                                                        startDialogue(DialogueText.questGiveLines[14], QuestAction.START_KILL_GOLDEN_BATS, npcX[var4], npcY[var4] - 40);
                                                    } else {
                                                        startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[var4], npcY[var4] - 40);
                                                    }
                                                } else if (Story.girlOutingDone && !Skills.tripleJump) {
                                                    if (PlayerStats.level < 12) {
                                                        startDialogue(DialogueText.levelRequired12, Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                    } else if (QuestState.current == Quest.AIR_HITS) {
                                                        if (QuestState.progress < Quest.TARGET_COUNT[Quest.AIR_HITS]) {
                                                            startDialogue(DialogueText.questReminderLines[9], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                        } else {
                                                            setNextNpcMarker(4);
                                                            startDialogue(DialogueText.questRewardLines[9], QuestAction.REWARD_TRIPLE_JUMP, npcX[var4], npcY[var4] - 40);
                                                        }
                                                    } else if (QuestState.current == Quest.NONE) {
                                                        startDialogue(DialogueText.questGiveLines[17], QuestAction.START_AIR_HITS, npcX[var4], npcY[var4] - 40);
                                                    } else {
                                                        startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[var4], npcY[var4] - 40);
                                                    }
                                                } else {
                                                    if (!Skills.sprint && Skills.dartsLevel2) {
                                                        if (PlayerStats.level < 15) {
                                                            startDialogue(DialogueText.levelRequired15, Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                        } else if (QuestState.current == Quest.KILL_SCORPIONS) {
                                                            if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_SCORPIONS]) {
                                                                startDialogue(DialogueText.questReminderLines[11], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                            } else {
                                                                setNextNpcMarker(3);
                                                                startDialogue(DialogueText.questRewardLines[11], QuestAction.REWARD_SPRINT, npcX[var4], npcY[var4] - 40);
                                                            }
                                                        } else if (QuestState.current == Quest.NONE) {
                                                            startDialogue(DialogueText.questGiveLines[19], QuestAction.START_KILL_SCORPIONS, npcX[var4], npcY[var4] - 40);
                                                        } else {
                                                            startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[var4], npcY[var4] - 40);
                                                        }
                                                        break;
                                                    }

                                                    startDialogue(DialogueText.idleLines[0], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                }
                                                break;
                                            case 2:
                                                if (Story.sakuraEscorting) {
                                                    startDialogue(DialogueText.idleLines[9], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                } else if (!Skills.lightness) {
                                                    setNextNpcMarker(1);
                                                    startDialogue(DialogueText.questGiveLines[2], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                } else if (Skills.burrow && !Skills.motorbikeLicense) {
                                                    if (QuestState.current == Quest.ESCORT_AYUMI_SISTER) {
                                                        startDialogue(DialogueText.questGiveLines[9], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                    } else if (QuestState.current == Quest.NONE) {
                                                        startDialogue(DialogueText.questGiveLines[10], QuestAction.START_ESCORT_AYUMI_SISTER, npcX[var4], npcY[var4] - 40);
                                                    } else {
                                                        startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[var4], npcY[var4] - 40);
                                                    }
                                                } else if (Story.goldenBatQuestDone && !Story.tonikuQuestDone) {
                                                    if (QuestState.current == Quest.FIGHT_TONIKU) {
                                                        if (Story.tonikuDefeated) {
                                                            setNextNpcMarker(2);
                                                            startDialogue(DialogueText.questRewardLines[7], QuestAction.COMPLETE_FIGHT_TONIKU, npcX[var4], npcY[var4] - 40);
                                                        } else {
                                                            startDialogue(DialogueText.questReminderLines[7], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                        }
                                                    } else if (QuestState.current == Quest.NONE) {
                                                        startDialogue(DialogueText.questGiveLines[15], QuestAction.START_FIGHT_TONIKU, npcX[var4], npcY[var4] - 40);
                                                    } else {
                                                        startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[var4], npcY[var4] - 40);
                                                    }
                                                } else {
                                                    if (Story.tonikuQuestDone && !Story.girlWaiting) {
                                                        if (QuestState.current == Quest.KILL_NATIVES) {
                                                            if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_NATIVES]) {
                                                                startDialogue(DialogueText.questReminderLines[8], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                            } else {
                                                                setNextNpcMarker(3);
                                                                startDialogue(DialogueText.questRewardLines[8], QuestAction.REWARD_MEET_GIRL, npcX[var4], npcY[var4] - 40);
                                                            }
                                                        } else if (QuestState.current == Quest.NONE) {
                                                            startDialogue(DialogueText.questGiveLines[16], QuestAction.START_KILL_NATIVES, npcX[var4], npcY[var4] - 40);
                                                        } else {
                                                            startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[var4], npcY[var4] - 40);
                                                        }
                                                        break;
                                                    }

                                                    startDialogue(DialogueText.idleLines[3], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                }
                                                break;
                                            case 3:
                                                if (Story.sakuraEscorting) {
                                                    startDialogue(DialogueText.idleLines[9], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                } else {
                                                    if (Skills.lightness && Skills.darts) {
                                                        if (!Skills.burrow) {
                                                            if (QuestState.current == Quest.FIND_BURROW_SCROLL) {
                                                                if (!QuestState.itemFound) {
                                                                    startDialogue(DialogueText.questReminderLines[2], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                                } else {
                                                                    setNextNpcMarker(2);
                                                                    startDialogue(DialogueText.questRewardLines[2], QuestAction.REWARD_BURROW, npcX[var4], npcY[var4] - 40);
                                                                }
                                                            } else if (QuestState.current == Quest.NONE) {
                                                                startDialogue(DialogueText.questGiveLines[8], QuestAction.START_FIND_BURROW_SCROLL, npcX[var4], npcY[var4] - 40);
                                                            } else {
                                                                startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[var4], npcY[var4] - 40);
                                                            }
                                                            break;
                                                        }

                                                        if (!Skills.dive && Skills.doubleStrength) {
                                                            if (QuestState.current == Quest.KILL_WILD_BOAR) {
                                                                if (!QuestState.itemFound) {
                                                                    startDialogue(DialogueText.questReminderLines[4], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                                } else {
                                                                    setNextNpcMarker(4);
                                                                    startDialogue(DialogueText.questRewardLines[4], QuestAction.REWARD_WATER_SKILLS, npcX[var4], npcY[var4] - 40);
                                                                }
                                                            } else if (QuestState.current == Quest.NONE) {
                                                                startDialogue(DialogueText.questGiveLines[12], QuestAction.START_KILL_WILD_BOAR, npcX[var4], npcY[var4] - 40);
                                                            } else {
                                                                startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[var4], npcY[var4] - 40);
                                                            }
                                                            break;
                                                        }

                                                        if (!Skills.standOnWater && Skills.sprint) {
                                                            if (PlayerStats.level < 16) {
                                                                startDialogue(DialogueText.levelRequired16, Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                            } else if (QuestState.current == Quest.KILL_RED_TIGER_FISH) {
                                                                if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_RED_TIGER_FISH]) {
                                                                    startDialogue(DialogueText.questReminderLines[12], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                                } else {
                                                                    setNextNpcMarker(3);
                                                                    startDialogue(DialogueText.questRewardLines[12], QuestAction.REWARD_STAND_ON_WATER, npcX[var4], npcY[var4] - 40);
                                                                }
                                                            } else if (QuestState.current == Quest.NONE) {
                                                                startDialogue(DialogueText.questGiveLines[20], QuestAction.START_KILL_RED_TIGER_FISH, npcX[var4], npcY[var4] - 40);
                                                            } else {
                                                                startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[var4], npcY[var4] - 40);
                                                            }
                                                            break;
                                                        }

                                                        if (!Skills.hide && Skills.standOnWater) {
                                                            if (QuestState.current == Quest.FIND_HIDE_SCROLL) {
                                                                if (!QuestState.itemFound) {
                                                                    startDialogue(DialogueText.questReminderLines[13], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                                } else {
                                                                    setNextNpcMarker(0);
                                                                    startDialogue(DialogueText.questRewardLines[13], QuestAction.REWARD_HIDE, npcX[var4], npcY[var4] - 40);
                                                                }
                                                            } else if (QuestState.current == Quest.NONE) {
                                                                startDialogue(DialogueText.questGiveLines[21], QuestAction.START_FIND_HIDE_SCROLL, npcX[var4], npcY[var4] - 40);
                                                            } else {
                                                                startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[var4], npcY[var4] - 40);
                                                            }
                                                            break;
                                                        }

                                                        startDialogue(DialogueText.idleLines[4], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                        break;
                                                    }

                                                    startDialogue(DialogueText.questGiveLines[5], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                }
                                                break;
                                            case 4:
                                                if (Story.sakuraEscorting) {
                                                    startDialogue(DialogueText.idleLines[8], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                } else if (mapId == 99) {
                                                    startDialogue(DialogueText.questGiveLines[7], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                } else if (!Skills.lightness) {
                                                    startDialogue(DialogueText.questGiveLines[4], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                } else if (!Skills.darts) {
                                                    if (QuestState.current == Quest.KNOCK_DOWN_SCARECROWS) {
                                                        if (QuestState.progress < Quest.TARGET_COUNT[Quest.KNOCK_DOWN_SCARECROWS]) {
                                                            startDialogue(DialogueText.questReminderLines[3], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                        } else {
                                                            setNextNpcMarker(3);
                                                            startDialogue(DialogueText.questRewardLines[1], QuestAction.REWARD_DARTS, npcX[var4], npcY[var4] - 40);
                                                        }
                                                    } else if (QuestState.current == Quest.NONE) {
                                                        startDialogue(DialogueText.questGiveLines[6], QuestAction.START_KNOCK_DOWN_SCARECROWS, npcX[var4], npcY[var4] - 40);
                                                    } else {
                                                        startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[var4], npcY[var4] - 40);
                                                    }
                                                } else if (Skills.motorbikeLicense && !Skills.doubleStrength) {
                                                    if (QuestState.current == Quest.KILL_BEES) {
                                                        if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_BEES]) {
                                                            startDialogue(DialogueText.questReminderLines[3], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                        } else {
                                                            setNextNpcMarker(3);
                                                            startDialogue(DialogueText.questRewardLines[3], QuestAction.REWARD_DOUBLE_STRENGTH, npcX[var4], npcY[var4] - 40);
                                                        }
                                                    } else if (QuestState.current == Quest.NONE) {
                                                        startDialogue(DialogueText.questGiveLines[11], QuestAction.START_KILL_BEES, npcX[var4], npcY[var4] - 40);
                                                    } else {
                                                        startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[var4], npcY[var4] - 40);
                                                    }
                                                } else if (Skills.dive && !Skills.transform) {
                                                    if (PlayerStats.level < 8) {
                                                        startDialogue(DialogueText.levelRequired8, Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                    } else if (QuestState.current == Quest.CROSS_RIVER) {
                                                        if (!QuestState.itemFound) {
                                                            startDialogue(DialogueText.questReminderLines[5], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                        } else {
                                                            setNextNpcMarker(1);
                                                            startDialogue(DialogueText.questRewardLines[5], QuestAction.REWARD_TRANSFORM, npcX[var4], npcY[var4] - 40);
                                                        }
                                                    } else if (QuestState.current == Quest.NONE) {
                                                        startDialogue(DialogueText.questGiveLines[13], QuestAction.START_CROSS_RIVER, npcX[var4], npcY[var4] - 40);
                                                    } else {
                                                        startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[var4], npcY[var4] - 40);
                                                    }
                                                } else {
                                                    if (!Skills.dartsLevel2 && Skills.tripleJump) {
                                                        if (QuestState.current == Quest.KILL_FLOWER_DEMONS) {
                                                            if (QuestState.progress < Quest.TARGET_COUNT[Quest.KILL_FLOWER_DEMONS]) {
                                                                startDialogue(DialogueText.questReminderLines[10], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                            } else {
                                                                setNextNpcMarker(1);
                                                                startDialogue(DialogueText.questRewardLines[10], QuestAction.REWARD_DARTS_LEVEL_2, npcX[var4], npcY[var4] - 40);
                                                            }
                                                        } else if (QuestState.current == Quest.NONE) {
                                                            startDialogue(DialogueText.questGiveLines[18], QuestAction.START_KILL_FLOWER_DEMONS, npcX[var4], npcY[var4] - 40);
                                                        } else {
                                                            startDialogue(DialogueText.questUnfinishedLines, QuestAction.QUEST_BUSY, npcX[var4], npcY[var4] - 40);
                                                        }
                                                        break;
                                                    }

                                                    startDialogue(DialogueText.idleLines[2], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                }
                                                break;
                                            case 5:
                                                if (mapId == 0) {
                                                    if (var4 == 5) {
                                                        startDialogue(DialogueText.guardLines[0], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                    }

                                                    if (var4 == 6) {
                                                        openShop(ShopScreen.MODE_MONEY);
                                                    }
                                                }

                                                if (mapId == 2 && var4 == 0) {
                                                    openShop(ShopScreen.MODE_SMS);
                                                }

                                                if (mapId == 1 && var4 == 0) {
                                                    startDialogue(DialogueText.guardLines[1], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                }

                                                if (mapId == 5 && var4 == 0) {
                                                    startDialogue(DialogueText.guardLines[2], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                }

                                                if (mapId == 6 && var4 == 0) {
                                                    startDialogue(DialogueText.guardLines[3], Dialogue.NO_ACTION, npcX[var4], npcY[var4] - 40);
                                                }
                                        }

                                        if (mapId == guideMarkerMap && var4 == guideMarkerNpc) {
                                            guideMarkerMap = -1;
                                        }
                                    } else if (rightSoftKeyLabel.equals("Trò chuyện")) {
                                        Input.clearPressed();
                                        dJ[fE] = -fm;
                                        startDialogue(classmateLines[fE], QuestAction.TALK, dz[fE], dA[fE] - 44);
                                    } else if (rightSoftKeyLabel.equals("Nói chuyện") && fB) {
                                        if (gk) {
                                            startDialogue(DialogueText.girlLines[0], QuestAction.TALK, gn, go - 44);
                                        } else if (Story.girlOutingDone) {
                                            startDialogue(DialogueText.girlLines[2], QuestAction.TALK, gn, go - 44);
                                            gk = true;
                                        } else if (Story.girlWaiting) {
                                            startDialogue(DialogueText.girlLines[3], QuestAction.START_GIRL_OUTING, gn, go - 44);
                                            Story.girlWaiting = false;
                                        } else {
                                            startDialogue(DialogueText.girlLines[1], QuestAction.TALK, gn, go - 44);
                                        }
                                    }
                                } else {
                                    label2585: {
                                        if (Input.held[Input.KEY_UP]) {
                                            if (Skills.motorbikeLicense) {
                                                for(var13 = 0; var13 < ii; ++var13) {
                                                    if (ff == il[var13] && a(fe - ik[var13]) < 20) {
                                                        fe = ik[var13];
                                                        B = 18;
                                                        Input.clearHeld();
                                                        ij = var13;
                                                        im[var13] = fm;
                                                        in[var13] = 0;
                                                        break label2585;
                                                    }
                                                }
                                            }

                                            if (Skills.lightness) {
                                                A = -10;
                                            } else {
                                                A = -8;
                                            }

                                            B = 3;
                                            ir = -1;
                                            D = 0;
                                            fD = -1;
                                            fE = -1;
                                            rightSoftKeyLabel = " ";
                                            hudDirty = true;
                                        } else if (Input.held[Input.KEY_LEFT]) {
                                            if (fm == 1) {
                                                fm = -1;
                                            } else {
                                                B = 2;
                                                z = -Q;
                                            }

                                            fD = -1;
                                            fE = -1;
                                            rightSoftKeyLabel = " ";
                                            hudDirty = true;
                                        } else if (Input.held[Input.KEY_RIGHT]) {
                                            if (fm == -1) {
                                                fm = 1;
                                            } else {
                                                B = 2;
                                                z = Q;
                                            }

                                            fE = -1;
                                            fD = -1;
                                            rightSoftKeyLabel = " ";
                                            hudDirty = true;
                                        } else if (Input.held[Input.KEY_DOWN]) {
                                            D = 0;
                                            E = 0;
                                            B = 8;
                                        } else if (Input.held[Input.KEY_NUM1]) {
                                            if (Skills.lightness) {
                                                A = -10;
                                            } else {
                                                A = -8;
                                            }

                                            fm = -1;
                                            z = -4;
                                            B = 3;
                                            ir = -1;
                                            D = 0;
                                            fD = -1;
                                            fE = -1;
                                            rightSoftKeyLabel = " ";
                                            hudDirty = true;
                                        } else if (Input.held[Input.KEY_NUM3]) {
                                            if (Skills.lightness) {
                                                A = -10;
                                            } else {
                                                A = -8;
                                            }

                                            fm = 1;
                                            z = 4;
                                            B = 3;
                                            ir = -1;
                                            D = 0;
                                            fD = -1;
                                            fE = -1;
                                            rightSoftKeyLabel = " ";
                                            hudDirty = true;
                                        }

                                        if (Input.pressed[Input.KEY_FIRE]) {
                                            B = 5;
                                            ft = 0;
                                            D = 0;
                                            A = 0;
                                            Input.pressed[Input.KEY_FIRE] = false;
                                            fD = -1;
                                            fE = -1;
                                            rightSoftKeyLabel = " ";
                                            hudDirty = true;
                                        }
                                    }
                                }
                            } else if (B == 2) {
                                if (Input.held[Input.KEY_UP]) {
                                    ir = -1;
                                    if (Skills.lightness) {
                                        A = -10;
                                    } else {
                                        A = -8;
                                    }

                                    B = 3;
                                    D = 0;
                                } else if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -Q + fo;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = Q + fo;
                                    }
                                }

                                if (Input.pressed[Input.KEY_FIRE]) {
                                    B = 5;
                                    ft = 0;
                                    D = 0;
                                    A = 0;
                                    Input.pressed[Input.KEY_FIRE] = false;
                                }
                            } else if (B == 11) {
                                if (Input.held[Input.KEY_UP]) {
                                    if (Skills.lightness) {
                                        A = -10;
                                    } else {
                                        A = -8;
                                    }

                                    B = 3;
                                    D = 0;
                                } else if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -L + fo;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = L + fo;
                                    }
                                }
                            } else if (B == 3) {
                                if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -Q;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = Q;
                                    }
                                }

                                if (Input.pressed[Input.KEY_FIRE]) {
                                    B = 5;
                                    ft = 0;
                                    D = 0;
                                    Input.pressed[Input.KEY_FIRE] = false;
                                }

                                if (Input.held[Input.KEY_UP] && PlayerStats.hasMp() && A > -4 && (D == 0 && Skills.doubleJump || D == 1 && Skills.tripleJump)) {
                                    ++D;
                                    A = -7;
                                    PlayerStats.drainMp(3);

                                    hudDirty = true;
                                    Dust.spawn(-1, fe - -8, ff);
                                    Dust.spawn(1, fe - 8, ff);
                                    WaterSplash.spawn(fe, ff);
                                }
                            } else if (B == 4) {
                                if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -Q;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = Q;
                                    }
                                }

                                if (Input.pressed[Input.KEY_FIRE] && A > 4) {
                                    B = 5;
                                    D = 0;
                                    ft = 0;
                                    Input.pressed[Input.KEY_FIRE] = false;
                                }
                            } else if (B == 5) {
                                if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -Q + 2;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = Q - 2;
                                    }
                                }

                                if (Input.pressed[Input.KEY_FIRE]) {
                                    fv = true;
                                    Input.pressed[Input.KEY_FIRE] = false;
                                }
                            } else if (B == 18) {
                                if (Input.held[Input.KEY_UP]) {
                                    A = -10;
                                    B = 21;
                                    D = 10;
                                    ir = -1;
                                } else if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                        im[ij] = -1;
                                    } else {
                                        D = 1;
                                        B = 19;
                                        D = 10;
                                        z = -R;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                        im[ij] = 1;
                                    } else {
                                        B = 19;
                                        D = 10;
                                        z = R;
                                    }
                                } else if (Input.held[Input.KEY_DOWN]) {
                                    B = 1;
                                    ij = -1;
                                }
                            } else if (B == 19) {
                                if (Input.held[Input.KEY_UP]) {
                                    A = -10;
                                    B = 21;
                                    ir = -1;
                                    D = 10;
                                } else if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                        im[ij] = -1;
                                    } else {
                                        z = -R;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                        im[ij] = 1;
                                    } else {
                                        z = R;
                                    }
                                }
                            } else if (B == 21) {
                                if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                        im[ij] = -1;
                                    } else {
                                        z = -R;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                        im[ij] = 1;
                                    } else {
                                        z = R;
                                    }
                                }
                            } else if (B == 20) {
                                if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                        im[ij] = -1;
                                    } else {
                                        z = -R;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                        im[ij] = 1;
                                    } else {
                                        z = R;
                                    }
                                }
                            } else if (B == 17) {
                                if (Input.held[Input.KEY_UP]) {
                                    A = -10;
                                    B = 3;
                                    fc = -1;
                                    D = 0;
                                } else if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        B = 16;
                                        z = -M;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        B = 16;
                                        z = M;
                                    }
                                }
                            } else if (B == 16) {
                                if (Input.held[Input.KEY_UP]) {
                                    A = -10;
                                    B = 3;
                                    fc = -1;
                                    D = 0;
                                } else if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -M;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = M;
                                    }
                                }
                            } else if (B == 12) {
                                if (Input.held[Input.KEY_UP] && Skills.runOnWater) {
                                    A = -10;
                                    B = 3;
                                    fc = -1;
                                    D = 0;
                                }
                            } else if (B == 14) {
                                if (Input.held[Input.KEY_LEFT]) {
                                    fm = -1;
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    fm = 1;
                                } else if (Input.held[Input.KEY_UP]) {
                                    B = 15;
                                }
                            }
                        }
                    }

                    if (cameraX != cameraTargetX || cameraY != cameraTargetY) {
                        fK = cameraTargetX - cameraX << 2;
                        fL = cameraTargetY - cameraY << 2;
                        fM += fK;
                        cameraX += fM >> 4;
                        fM &= 15;
                        fN += fL;
                        cameraY += fN >> 4;
                        fN &= 15;
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

                    if (aL >= 0) {
                        cameraX += aJ[aL];
                        cameraY += aK[aL];
                        if (++aL == 4) {
                            aL = -1;
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

                    int[] var10000;
                    for(var13 = 0; var13 < iq; ++var13) {
                        switch (iu[var13]) {
                            case 2:
                                var10000 = is;
                                var10000[var13] += iv[var13];
                                if (iv[var13] > 0) {
                                    var10002 = iv[var13]--;
                                } else if (iv[var13] < 0) {
                                    var10002 = iv[var13]++;
                                } else {
                                    iu[var13] = 1;
                                }

                                if (!GameMap.hasFlag(is[var13] - 12, it[var13] + 1, TileFlags.SOLID) && !GameMap.hasFlag(is[var13] + 12, it[var13] + 1, TileFlags.SOLID)) {
                                    iu[var13] = 4;
                                    iw[var13] = 2;
                                }

                                if (GameMap.hasFlag(is[var13] + 12, it[var13] - 2, TileFlags.WALL_LEFT_SIDE) || GameMap.hasFlag(is[var13] - 12, it[var13] - 2, TileFlags.WALL_RIGHT_SIDE)) {
                                    is[var13] = I(is[var13]) + 12;
                                }
                                break;
                            case 4:
                                var10000 = it;
                                var10000[var13] += iw[var13];
                                if (iw[var13] < 20) {
                                    var10002 = iw[var13]++;
                                }

                                if (!GameMap.hasFlag(is[var13] + 12, it[var13], TileFlags.SOLID) && !GameMap.hasFlag(is[var13] - 12, it[var13], TileFlags.SOLID)) {
                                    if (GameMap.hasFlag(is[var13], it[var13] - GameMap.TILE_SIZE, TileFlags.WATER)) {
                                        it[var13] = H(it[var13]) + 1;
                                        WaterSplash.spawn(is[var13], it[var13] - 13);
                                        WaterSplash.spawn(is[var13] + 1, it[var13] - 10);
                                        iw[var13] = 1;
                                        ix[var13] = -1;
                                        iy[var13] = 1;
                                        iu[var13] = 11;
                                    }
                                    break;
                                }

                                it[var13] = H(it[var13]);
                                iw[var13] = 0;
                                iu[var13] = 1;
                                break;
                            case 11:
                                if (tick % 2 == 0) {
                                    var10000 = it;
                                    var10000[var13] += ix[var13];
                                    var10000 = iw;
                                    var10000[var13] += ix[var13];
                                    if (iw[var13] >= 1) {
                                        ix[var13] = -1;
                                    }

                                    if (iw[var13] <= -1) {
                                        ix[var13] = 1;
                                    }

                                    var10000 = is;
                                    var10000[var13] += iy[var13];
                                    if (var13 == ir) {
                                        ff = it[ir] - 24;
                                        fe += iy[var13];
                                    }

                                    if (GameMap.hasFlag(is[var13] + 12, it[var13], TileFlags.WALL_LEFT_SIDE) || GameMap.hasFlag(is[var13] - 12, it[var13], TileFlags.WALL_RIGHT_SIDE)) {
                                        iy[var13] = -iy[var13];
                                    }
                                }
                        }
                    }

                    for(var13 = 0; var13 < ii; ++var13) {
                        if (var13 == ij) {
                            if (D > 1) {
                                in[var13] = 2;
                            } else if (tick % 8 > 3) {
                                in[var13] = 1;
                            } else {
                                in[var13] = 0;
                            }
                        }

                        if (io[var13] == 1) {
                            var10000 = il;
                            var10000[var13] += ip[var13];
                            if (ip[var13] < 20) {
                                var10002 = ip[var13]++;
                            }

                            if (il[var13] >= GameMap.pixelHeight || GameMap.hasFlag(ik[var13], il[var13], TileFlags.SOLID)) {
                                ip[var13] = 0;
                                io[var13] = 0;
                                il[var13] = H(il[var13]);
                            }
                        }
                    }

                    FloatingText.update();
                    WaterSplash.update(tick);
                    Dust.update();
                    Blood.update();

                    int var5;
                    for(var13 = 0; var13 < cV; ++var13) {
                        if (cY[var13] == 1) {
                            var10000 = cW;
                            var10000[var13] += da[var13];
                            var10000 = cX;
                            var10000[var13] += db[var13];
                            if (cW[var13] >= cameraX && cW[var13] <= cameraX + Viewport.viewWidth && cX[var13] >= cameraY && cX[var13] <= cameraY + Viewport.viewHeight) {
                                if (da[var13] > 0 && (GameMap.flagsAtPixel(cW[var13], cX[var13]) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                                    cY[var13] = 2;
                                    cW[var13] = I(cW[var13]);
                                } else if (da[var13] < 0 && (GameMap.flagsAtPixel(cW[var13], cX[var13]) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                                    cY[var13] = 2;
                                    cW[var13] = I(cW[var13]) + GameMap.TILE_SIZE;
                                }

                                var10002 = cZ[var13]++;
                                if (cZ[var13] > 1) {
                                    cZ[var13] = 0;
                                }

                                if (da[var13] != 0) {
                                    for(var4 = 0; var4 < Monsters.count; ++var4) {
                                        if (Monsters.state[var4] != Monsters.STATE_RESPAWN_WAIT && Monsters.state[var4] != Monsters.STATE_KNOCKED_OUT && a(Monsters.x[var4] - cW[var13]) < Monsters.width[var4] && a(Monsters.y[var4] - (Monsters.height[var4] >> 1) - cX[var13]) < Monsters.height[var4]) {
                                            boolean var14;
                                            label2807: {
                                                var5 = dc[var13] ? PlayerStats.dartDamage << 1 : PlayerStats.dartDamage;
                                                if (Monsters.state[var4] == Monsters.STATE_ACTIVE || Monsters.state[var4] == Monsters.STATE_RESTING || Monsters.state[var4] == Monsters.STATE_ATTACKING) {
                                                    if (Monsters.type[var4] == Monsters.TYPE_WIND_NINJA && da[var13] * Monsters.direction[var4] < 0) {
                                                        Monsters.state[var4] = Monsters.STATE_ATTACKING;
                                                        Monsters.timer[var4] = 4;
                                                        da[var13] = -da[var13];
                                                        db[var13] = -db[var13];
                                                        var10000 = cW;
                                                        var10000[var13] += da[var13];
                                                        var10000 = cX;
                                                        var10000[var13] += db[var13];
                                                        var14 = true;
                                                        break label2807;
                                                    }

                                                    var10000 = Monsters.hp;
                                                    var10000[var4] -= var5;
                                                    if (Monsters.hp[var4] <= 0) {
                                                        Monsters.hp[var4] = 0;
                                                        Monsters.timer[var4] = -5;
                                                        Monsters.aux1[var4] = da[var13];
                                                        Monsters.aux2[var4] = 0;
                                                        Monsters.healthBarTimer[var4] = 0;
                                                        Monsters.state[var4] = Monsters.STATE_KNOCKED_OUT;
                                                        L(var4);
                                                        var14 = true;
                                                        break label2807;
                                                    }

                                                    Monsters.timer[var4] = 0;
                                                    Monsters.direction[var4] = da[var13] < 0 ? 1 : -1;
                                                    Monsters.state[var4] = Monsters.STATE_HURT;
                                                    Monsters.frame[var4] = 3;
                                                    cY[var13] = -1;
                                                }

                                                var14 = false;
                                            }

                                            if (!var14) {
                                                Blood.spawn(Monsters.x[var4], Monsters.y[var4] - (Monsters.height[var4] >> 1), Monsters.direction[var4]);
                                                FloatingText.spawn("-" + var5, 0, Monsters.x[var4], Monsters.y[var4] - Monsters.height[var4], 0, -2);
                                            }
                                            break;
                                        }
                                    }

                                    for(var4 = 0; var4 < cp; ++var4) {
                                        if (cE[var4] != 5 && cE[var4] != 1 && a(cu[var4] - cW[var13]) < 20 && a(cv[var4] - 15 - cX[var13]) < 20) {
                                            if (dc[var13]) {
                                                a(var4, PlayerStats.dartDamage << 1, cH[var13]);
                                                Blood.spawn(cu[var4], cv[var4] - 15, cH[var4]);
                                                FloatingText.spawn("-" + (PlayerStats.dartDamage << 1), 0, cu[var4], cv[var4] - 32, 0, -2);
                                                cY[var13] = -1;
                                            } else {
                                                cE[var4] = 4;
                                                cF[var4] = 0;
                                                cG[var4] = 0;
                                                cH[var4] = da[var13] > 0 ? -1 : 1;
                                                da[var13] = -da[var13];
                                            }
                                            break;
                                        }
                                    }

                                    if (!dc[var13] && a(fe - cW[var13]) < fi && a(ff - fj - cX[var13]) < fj) {
                                        if (B == 5 && fd == 0) {
                                            da[var13] = -da[var13];
                                            db[var13] = -3;
                                        } else {
                                            a(PlayerStats.dartDamage, -fm);
                                            cY[var13] = -1;
                                        }
                                        break;
                                    }

                                    if (gN && a(gP - cW[var13]) < gV >> 1 && a(gQ - (gW >> 1) - cX[var13]) < gW >> 1) {
                                        c(PlayerStats.dartDamage, -gT);
                                        Blood.spawn(cW[var13], cX[var13], gT);
                                        FloatingText.spawn("-" + PlayerStats.dartDamage, 0, gP, gQ - gW, 0, -2);
                                        cY[var13] = -1;
                                    }

                                    if (hJ && a(hP - cW[var13]) < hO >> 1 && a(hQ - (hL >> 1) - cX[var13]) < hL >> 1) {
                                        da[var13] = -da[var13];
                                        db[var13] = -1;
                                    }

                                    if (hc && a(dz[0] - cW[var13]) < 12 && a(dA[0] - 16 - cX[var13]) < 32) {
                                        if (dD[0] == 5 && da[var13] * dJ[0] < 0) {
                                            da[var13] = -da[var13];
                                        } else if (dD[0] == 1 && da[var13] * dJ[0] < 0) {
                                            da[var13] = -da[var13];
                                            dE[0] = dF[0] = 0;
                                            dD[0] = 5;
                                        } else {
                                            d(PlayerStats.dartDamage, -dJ[0]);
                                            cY[var13] = -1;
                                        }
                                    }
                                }
                            } else {
                                cY[var13] = -1;
                            }
                        } else if (cY[var13] > 1) {
                            var10002 = cY[var13]++;
                            if (cY[var13] >= 10) {
                                cY[var13] = 1;
                                db[var13] = 12;
                                da[var13] = 0;
                            }
                        }
                    }

                    if (Projectiles.update(fe, ff, fi, fj, cameraX, cameraY, B == 5 && fd == 0)) {
                        a(Projectiles.hitDamage, Projectiles.hitDirection);
                    }

                    Aura.update();

                    if (ArcProjectiles.update(fe, ff, fi, fj, cameraY)) {
                        a(ArcProjectiles.hitDamage, ArcProjectiles.hitDirection);
                    }

                    if (guideMarkerMap >= 0) {
                        if (guideMarkerX > cameraX + 10 && guideMarkerX < cameraX + Viewport.viewWidth - 10 && guideMarkerY > cameraY + 10 && guideMarkerY < cameraY + Viewport.viewHeight - 10) {
                            ge = 2;
                            gf = guideMarkerX;
                            gg = guideMarkerY + gi;
                        } else if (a(fe - guideMarkerX) > a(ff - guideMarkerY)) {
                            if (fe > guideMarkerX) {
                                ge = 1;
                                gf = cameraX + 20 + gi;
                            } else {
                                ge = 0;
                                gf = cameraX + Viewport.viewWidth - 20 + gi;
                            }

                            if ((gg = guideMarkerY + 24) < cameraY + 10) {
                                gg = cameraY + 10;
                            }

                            if (gg > cameraY + Viewport.viewHeight - 10) {
                                gg = cameraY + Viewport.viewHeight - 10;
                            }
                        } else if (ff > guideMarkerY) {
                            ge = 3;
                            gf = fe;
                            gg = cameraY + 20 + gi;
                        } else {
                            ge = 2;
                            gf = fe;
                            gg = cameraY + Viewport.viewHeight - 20 + gi;
                        }

                        if ((gi += gh) == 5 || gi == -5) {
                            gh = -gh;
                        }
                    }

                    R();
                    if (gm == mapId) {
                        try {
                            switch (gr) {
                                case 1:
                                    if (++gA > 30) {
                                        gA = 0;
                                    }

                                    if (gA % 15 < 5) {
                                        gs = 0;
                                    } else {
                                        gs = 1;
                                    }

                                    if (gk && tick % 6 == 0) {
                                        D();
                                    }

                                    if (tick % 15 == 0) {
                                        E();
                                    }
                                    break;
                                case 2:
                                    if (++gA >= 10) {
                                        gA = 0;
                                    }

                                    gs = (gA >> 1) + 2;
                                    if (gz == 1) {
                                        if (GameMap.hasFlag(gn + gv, go - gw, TileFlags.WALL_LEFT_SIDE)) {
                                            gp = 0;
                                            gn = I(gn + gv) - gv;
                                        }
                                    } else if (GameMap.hasFlag(gn - gv - 1, go - gw, TileFlags.WALL_RIGHT_SIDE)) {
                                        gp = 0;
                                        gn = I(gn - gv - 1) + GameMap.TILE_SIZE + gv;
                                    }

                                    if ((gn += gp) < 0) {
                                        gn = 0;
                                    }

                                    if (gn >= GameMap.pixelWidth) {
                                        gn = GameMap.pixelWidth - 1;
                                    }

                                    if (ir != -1) {
                                        if (a(gn - is[ir]) >= 20) {
                                            ir = -1;
                                            if (GameMap.hasFlag(gn, go, TileFlags.SOLID)) {
                                                gr = 1;
                                            } else {
                                                gr = 4;
                                                gq = 2;
                                                gp = 3 * gz;
                                                gB = 0;
                                            }
                                        }
                                    } else if ((GameMap.flagsAtPixel(gn + gz * gt, go) & TileFlags.SOLID) != TileFlags.SOLID) {
                                        gr = 1;
                                        gs = 0;
                                        gp = 0;
                                        gA = 0;
                                        gB = 0;
                                    }

                                    Dust.spawn(gz, gn - (gz << 3), go);
                                    if (gK > 0) {
                                        --gK;
                                    } else {
                                        E();
                                    }
                                    break;
                                case 3:
                                    if (gp == 0) {
                                        gp = gz << 2;
                                    }

                                    if (gz == 1) {
                                        if ((GameMap.flagsAtPixel(gn + gv, go - gw) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                                            gp = 0;
                                        }
                                    } else if ((GameMap.flagsAtPixel(gn - gv, go - gw) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                                        gp = 0;
                                    }

                                    if (ff < 0) {
                                        ff = 0;
                                        A = -1;
                                    }

                                    if ((gn += gp) < 0) {
                                        gn = 0;
                                    }

                                    if (gn >= GameMap.pixelWidth) {
                                        gn = GameMap.pixelWidth - 1;
                                    }

                                    go += gq;
                                    if (gA == 0) {
                                        gs = 7;
                                    } else {
                                        gs = 8;
                                    }

                                    if (gp > 1) {
                                        --gp;
                                    }

                                    if (++gq == 0) {
                                        gr = 4;
                                        gA = 0;
                                        gB = 0;
                                        gq = 1;
                                    }

                                    if (gC < 0) {
                                        ++gC;
                                    }
                                    break;
                                case 4:
                                    if (gz == 1) {
                                        if ((GameMap.flagsAtPixel(gn + gt, go) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                                            gp = 0;
                                        }
                                    } else if ((GameMap.flagsAtPixel(gn - gt, go) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                                        gp = 0;
                                    }

                                    ig = -1;
                                    if ((gn += gp) < 0) {
                                        gn = 0;
                                    }

                                    if (gn >= GameMap.pixelWidth) {
                                        gn = GameMap.pixelWidth - 1;
                                    }

                                    go += gq;
                                    if (gq < 18) {
                                        ++gq;
                                    }

                                    if (gq > 5) {
                                        gp = 0;
                                    }

                                    if (gp > 1) {
                                        --gp;
                                    }

                                    gs = 8;
                                    if (go >= GameMap.pixelHeight) {
                                        go = GameMap.pixelHeight - 1;
                                        gr = 3;
                                        gq = -20;
                                    }

                                    if (gq > 4 && (GameMap.flagsAtPixel(gn, go) & TileFlags.SOLID) == TileFlags.SOLID) {
                                        gr = 1;
                                        gq = 0;
                                        gp = 0;
                                        gB = 0;
                                        gA = 0;
                                        go = I(go);
                                        Dust.spawn(-1, gn - -8, go);
                                        Dust.spawn(1, gn - 8, go);
                                    }

                                    if (GameMap.hasFlag(gn, go, TileFlags.WATER) && GameMap.hasFlag(gn, go + 24, TileFlags.DEEP_WATER) && go % GameMap.TILE_SIZE > 8) {
                                        WaterSplash.spawn(gn, H(go) + 8);
                                        gr = 3;
                                        gp = (gz = fe > gn ? 1 : -1) << 2;
                                        gq = -11;
                                    }

                                    if (gq > 6) {
                                        gs = 6;
                                    }

                                    if (gC < 0) {
                                        ++gC;
                                    }
                                    break;
                                case 5:
                                    switch (++gA) {
                                        case 1:
                                        case 2:
                                        case 3:
                                            ig = -1;
                                            gs = 9;
                                            break;
                                        case 4:
                                        case 5:
                                            ig = 0;
                                            gs = 10;
                                            break;
                                        case 6:
                                            ig = 1;
                                            gs = 11;
                                            v();
                                            break;
                                        case 7:
                                            ig = 2;
                                            gs = 11;
                                            break;
                                        case 8:
                                            ig = -1;
                                            gs = 12;
                                            break;
                                        case 9:
                                            ig = -1;
                                            gs = 9;
                                            gr = 1;
                                            gA = 0;
                                            gB = 0;
                                    }

                                    if (ih == 1) {
                                        ig = -1;
                                    }
                                case 6:
                                default:
                                    break;
                                case 7:
                                    if ((GameMap.flagsAtPixel(gn + gv, go - gw) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                                        gp = -gp;
                                    }

                                    if ((GameMap.flagsAtPixel(gn - gv, go - gw) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                                        gp = -gp;
                                    }

                                    gn += gp;
                                    go += gq;
                                    gs = 7;
                                    if (gp > 1) {
                                        --gp;
                                    }

                                    if (gp < -1) {
                                        ++gp;
                                    }

                                    if (++gq > 0) {
                                        gq = 0;
                                    }

                                    if (++gA >= 7) {
                                        gr = 4;
                                        gC = -20;
                                        gB = 0;
                                        gA = 0;
                                        gq = 1;
                                    }
                            }
                        } catch (Exception var11) {
                            gn = fe;
                            go = ff;
                            gp = 0;
                            gq = 0;
                            gr = 4;
                        }
                    }

                    Y();
                    if (tick % 2 == 0 && ((doSt += dq) > ds || doSt < dr)) {
                    dq = -dq;
                    doSt += dq;
                }

                ak();
                aa();
                if (gN) {
                    if (hb > 0) {
                        --hb;
                    }

                    switch (gS) {
                        case 1:
                            if (gX > 0) {
                                --gX;
                            } else {
                                if ((var13 = tick % 80 - 40) > 0) {
                                    var13 += 20;
                                } else {
                                    var13 -= 20;
                                }

                                gY = gP + var13;
                                gR = (gT = var13 > 0 ? 1 : -1) << 2;
                                if (gZ < ha >> 1) {
                                    gR = gT << 3;
                                }

                                gS = 2;
                                if (tick % 5 == 0) {
                                    N();
                                }
                            }

                            gU = 0;
                            if (tick % 10 == 6) {
                                Dust.spawn(-gT, gP + gT * 28, gQ - 5);
                            }
                            break;
                        case 2:
                            if (tick % 8 == 0) {
                                N();
                            }

                            if (!GameMap.hasFlag(gP + (gT << 4), gQ + 1, TileFlags.SOLID)) {
                                gS = 1;
                                gX = 0;
                            } else {
                                if (a(gP - gY) < 8) {
                                    gS = 1;
                                    gX = 20;
                                }

                                if (tick % 2 == 0) {
                                    gP += gR;
                                    gU = 0;
                                } else {
                                    gU = 1;
                                }
                            }
                        case 3:
                        case 4:
                        default:
                            break;
                        case 5:
                            if (!GameMap.hasFlag(gP + (gT << 4), gQ + 1, TileFlags.SOLID)) {
                                gS = 1;
                                gX = 0;
                            } else {
                                if (a(gP - gY) < 4) {
                                    gS = 1;
                                    gX = 35;
                                }

                                if (a(ff - gQ) < 18 && a(gP + (gT << 4) - fe) < 10) {
                                    a(gO, gT);
                                    gS = 1;
                                    gX = 45;
                                    if (gZ < ha >> 1) {
                                        gX = 18;
                                    }
                                }

                                gP += gR;
                                gU = tick % 3;
                            }
                            break;
                        case 6:
                            gQ += gX;
                            ++gX;
                            gP += gR;
                            if (gR > 1) {
                                --gR;
                            }

                            if (gR < -1) {
                                ++gR;
                            }

                            if (gQ > cameraY + Viewport.viewHeight + gW || gQ >= GameMap.pixelHeight + gW) {
                                gX = 0;
                                gS = 22;
                            }
                            break;
                        case 7:
                            gP -= gT;
                            gU = 2;
                            if (++gX == 4) {
                                gS = 1;
                                if ((gX = gY) > 0 && gZ < ha >> 1) {
                                    gX = 5;
                                }
                            }
                    }
                }

                if (hJ) {
                    var13 = tick % 10;
                    if (ic > 0) {
                        --ic;
                    }

                    switch (hX) {
                        case 1:
                            ++ib;
                            hQ += 2;
                            ++bJ;
                            ++bK;
                            ++bL;
                            if (ib > 50) {
                                hX = 5;
                            }
                            break;
                        case 2:
                            if (hM < hN >> 1) {
                                hP += hY << 2;
                            } else {
                                hP += hY << 1;
                            }

                            if (hP < hZ) {
                                hY = 1;
                            }

                            if (hP > ia) {
                                hY = -1;
                            }

                            if (ib > 0) {
                                --ib;
                            }

                            if (ib == 0 && a(fe - (hP - 40)) < 30) {
                                ib = 0;
                                hX = 4;
                                hY = hP < fe ? 1 : -1;
                            }

                            if (tick % 122 == 0) {
                                ArcProjectiles.spawn(hP, hQ - 45, -1, -12, hK);
                                ArcProjectiles.spawn(hP, hQ - 50, 0, -15, hK);
                                ArcProjectiles.spawn(hP, hQ - 45, 1, -12, hK);
                            } else if (tick % 200 == 0) {
                                hX = 7;
                                ib = 0;
                            }
                        case 3:
                        case 5:
                        case 6:
                        default:
                            break;
                        case 4:
                            if (ib == 110) {
                                hX = 2;
                                ib = 30;
                            } else if (ib < 35) {
                                --hQ;
                            } else if (ib >= 75) {
                                ++hQ;
                                if (var13 == 5) {
                                    ArcProjectiles.spawn(hP, hQ - 45, -2, -10, hK);
                                } else if (var13 == 7 && hM < hN >> 1) {
                                    ArcProjectiles.spawn(hP, hQ - 50, 0, -11, hK);
                                } else if (var13 == 9) {
                                    ArcProjectiles.spawn(hP, hQ - 45, 2, -10, hK);
                                }
                            }

                            if (ib > 20 && ib < 50 && var13 == 0) {
                                Projectiles.spawn(hP + hY * 20, hQ - 29, hY << 3, hK, 2);
                            }

                            if (ib > 45 && ib < 50) {
                                bI += hY;
                            } else if (ib > 50 && ib < 55) {
                                bI -= hY;
                            }

                            ++ib;
                            break;
                        case 7:
                            if (++ib == 50) {
                                hX = 2;
                                ib = 1;
                            } else if (ib == 30) {
                                if ((hP = fe + (fm << 6)) > ia) {
                                    hP = ia - 30;
                                }

                                if (hP < hZ) {
                                    hP = hZ + 30;
                                }

                                hY = hP < fe ? 1 : -1;
                            } else if (ib < 10) {
                                hQ += 10;
                            } else if (ib > 40) {
                                hQ -= 10;
                            }
                    }

                    if (var13 < 4) {
                        --hQ;
                    } else if (var13 < 8) {
                        ++hQ;
                    }

                    if (var13 > 4) {
                        --bJ;
                        ++bK;
                    } else {
                        ++bJ;
                        --bK;
                    }

                    if (hY == -1) {
                        hR = hP + bG;
                        hV = hQ + bJ;
                        hU = hP + bH;
                        hW = hQ + bK;
                        hS = hP + bI;
                        hT = hQ + bL;
                    } else {
                        hR = hP - bG;
                        hV = hQ + bJ;
                        hU = hP - bH;
                        hW = hQ + bK;
                        hS = hP - bI;
                        hT = hQ + bL;
                    }
                }

                ac();
                if (hc) {
                    label2824: {
                        var13 = tick % 50;
                        var5 = a(dz[0] - fe);
                        switch (dD[0]) {
                            case 0:
                                var10002 = dE[0]++;
                                if (dE[0] > 30) {
                                    dE[0] = 0;
                                }

                                if (dE[0] % 18 < 6) {
                                    dG[0] = 0;
                                } else {
                                    dG[0] = 1;
                                }

                                if (dx) {
                                    hd = false;
                                    dF[0] = dy;
                                    dJ[0] = dy > dz[0] ? 1 : -1;
                                    dB[0] = dJ[0] << 2;
                                    dB[0] = dJ[0] > 0 ? dB[0] : dB[0];
                                    dD[0] = 2;
                                    break label2824;
                                }

                                if (var5 >= 40 || B != 5) {
                                    break label2824;
                                }

                                hd = true;
                                dD[0] = 3;
                                dJ[0] = -fm;
                                if (var13 > 35) {
                                    dB[0] = fm * 5;
                                    dC[0] = -9;
                                } else {
                                    dB[0] = -fm * 6;
                                    dC[0] = -9;
                                }

                                if (dz[0] < dM[0] + 40) {
                                    dB[0] = 10;
                                    dJ[0] = -1;
                                } else if (dz[0] > dN[0] - 40) {
                                    dB[0] = -10;
                                    dJ[0] = 1;
                                }
                                break;
                            case 1:
                                var10002 = dE[0]++;
                                if (dE[0] > 30) {
                                    dE[0] = 0;
                                }

                                if (dE[0] % 18 < 6) {
                                    dG[0] = 0;
                                } else {
                                    dG[0] = 1;
                                }

                                if (he) {
                                    dD[0] = 0;
                                    dE[0] = dF[0] = 0;
                                }

                                if (var5 < 40 && B == 5 && fm != dJ[0]) {
                                    dD[0] = 3;
                                    dJ[0] = -fm;
                                    if (var13 > 35) {
                                        dB[0] = fm * 5;
                                        dC[0] = -9;
                                    } else {
                                        dB[0] = -fm * 6;
                                        dC[0] = -9;
                                    }

                                    if (dz[0] < dM[0] + 40) {
                                        dB[0] = 10;
                                        dJ[0] = -1;
                                    } else if (dz[0] > dN[0] - 40) {
                                        dB[0] = -10;
                                        dJ[0] = 1;
                                    }

                                    dE[0] = dF[0] = 0;
                                } else if (var5 < 40 && var13 < 10) {
                                    dJ[0] = fe > dz[0] ? 1 : -1;
                                    dD[0] = 5;
                                    dE[0] = dF[0] = dL[0] = 0;
                                } else if (var5 < 100 && var5 > 40 && var13 < 1) {
                                    dJ[0] = fe > dz[0] ? 1 : -1;
                                    dD[0] = 24;
                                    dE[0] = dF[0] = dL[0] = 0;
                                    dB[0] = (a(dz[0] - fe) >> 3) * dJ[0];
                                } else if (var5 > 100 && var5 < 160 && var13 < 30) {
                                    dJ[0] = fe > dz[0] ? 1 : -1;
                                    dD[0] = 8;
                                    dE[0] = dF[0] = dL[0] = 0;
                                } else if (var5 >= 20 && var13 != 42) {
                                    if (var5 > 160) {
                                        dJ[0] = fe > dz[0] ? 1 : -1;
                                        dF[0] = dz[0] + (dJ[0] << 7);
                                        if (dF[0] < dM[0]) {
                                            dF[0] = dM[0];
                                        } else if (dF[0] > dN[0]) {
                                            dF[0] = dN[0];
                                        }

                                        dE[0] = 0;
                                        dD[0] = 2;
                                        dB[0] = dJ[0] << 2;
                                    }
                                } else {
                                    dJ[0] = -fm;
                                    dF[0] = dz[0] + (dJ[0] << 6);
                                    if (dF[0] < dM[0] || dF[0] > dN[0]) {
                                        dJ[0] = -dJ[0];
                                        dF[0] = dz[0] + (dJ[0] << 6);
                                    }

                                    dE[0] = 0;
                                    dD[0] = 2;
                                    dB[0] = dJ[0] << 3;
                                }
                                break label2824;
                            case 2:
                                var10002 = dE[0]++;
                                if (dE[0] >= 10) {
                                    dE[0] = 0;
                                }

                                dG[0] = (dE[0] >> 1) + 2;
                                var10000 = dz;
                                var10000[0] += dB[0];
                                if (!dx && (dJ[0] > 0 && dz[0] > dF[0] || dJ[0] < 0 && dz[0] < dF[0])) {
                                    dJ[0] = fe > dz[0] ? 1 : -1;
                                    dF[0] = dE[0] = 0;
                                    dD[0] = 1;
                                }
                                break label2824;
                            case 3:
                            case 7:
                                var10000 = dz;
                                var10000[0] += dB[0];
                                if (dz[0] < dM[0]) {
                                    dz[0] = dM[0];
                                }

                                if (dz[0] > dN[0]) {
                                    dz[0] = dN[0];
                                }

                                var10000 = dA;
                                var10000[0] += dC[0];
                                dG[0] = 23;
                                if (dB[0] > 0) {
                                    var10002 = dB[0]--;
                                }

                                var10002 = dC[0]++;
                                if (dC[0] == 0) {
                                    dD[0] = 4;
                                    dE[0] = 0;
                                    dC[0] = 1;
                                }
                                break label2824;
                            case 4:
                                var10000 = dz;
                                var10000[0] += dB[0];
                                if (dz[0] < dM[0]) {
                                    dz[0] = dM[0];
                                }

                                if (dz[0] > dN[0]) {
                                    dz[0] = dN[0];
                                }

                                var10000 = dA;
                                var10000[0] += dC[0];
                                dG[0] = 7;
                                if (dC[0] < 18) {
                                    var10002 = dC[0]++;
                                }

                                if (dB[0] > 0) {
                                    var10002 = dB[0]--;
                                }

                                dG[0] = 7;
                                if (dA[0] >= GameMap.pixelHeight) {
                                    dD[0] = 6;
                                } else if ((GameMap.flagsAtPixel(dz[0], dA[0]) & TileFlags.SOLID) == TileFlags.SOLID) {
                                    dD[0] = 1;
                                    dE[0] = dF[0] = 0;
                                    dB[0] = dC[0] = 0;
                                    dA[0] = I(dA[0]);
                                    Dust.spawn(-1, dz[0] - -8, dA[0]);
                                    Dust.spawn(1, dz[0] - 8, dA[0]);
                                    if (a(dz[0] - fe) < 40 && var13 < 25) {
                                        dJ[0] = fe > dz[0] ? 1 : -1;
                                        dD[0] = 5;
                                        dE[0] = dF[0] = dL[0] = 0;
                                    }

                                    if (var5 < 40) {
                                        dJ[0] = fm;
                                        dF[0] = dz[0] + dJ[0] * 110;
                                        if (dF[0] < dM[0] || dF[0] > dN[0]) {
                                            dJ[0] = -dJ[0];
                                            dF[0] = dz[0] + (dJ[0] << 6);
                                        }

                                        dE[0] = 0;
                                        dD[0] = 2;
                                        dB[0] = dJ[0] * 10;
                                    }
                                }
                                break label2824;
                            case 5:
                                var10002 = dE[0]++;
                                switch (dF[0]) {
                                    case 0:
                                        switch (dE[0]) {
                                            case 1:
                                            case 2:
                                            case 3:
                                                dI[0] = -1;
                                                dG[0] = 13;
                                                break label2824;
                                            case 4:
                                                dI[0] = 0;
                                                dG[0] = 14;
                                                break label2824;
                                            case 5:
                                                dI[0] = 1;
                                                dG[0] = 15;
                                                break label2824;
                                            case 6:
                                                dI[0] = 1;
                                                dG[0] = 15;
                                                z(0);
                                                break label2824;
                                            case 7:
                                                dI[0] = 2;
                                                dG[0] = 16;
                                                break label2824;
                                            case 8:
                                                dI[0] = 2;
                                                dG[0] = 16;
                                                dF[0] = 2;
                                                dE[0] = 1;
                                            default:
                                                break label2824;
                                        }
                                    case 1:
                                        switch (dE[0]) {
                                            case 1:
                                                dI[0] = 1;
                                                dG[0] = 15;
                                                break label2824;
                                            case 2:
                                                dI[0] = 1;
                                                dG[0] = 15;
                                                dF[0] = 10;
                                            default:
                                                break label2824;
                                        }
                                    case 2:
                                        switch (dE[0]) {
                                            case 1:
                                            case 2:
                                            case 3:
                                                dI[0] = 3;
                                                dG[0] = 17;
                                                break label2824;
                                            case 4:
                                                dI[0] = 4;
                                                dG[0] = 18;
                                                break label2824;
                                            case 5:
                                                dI[0] = 5;
                                                dG[0] = 19;
                                                break label2824;
                                            case 6:
                                                dI[0] = 6;
                                                dG[0] = 20;
                                                z(0);
                                                break label2824;
                                            case 7:
                                                dI[0] = 6;
                                                dG[0] = 20;
                                                dF[0] = 3;
                                                dE[0] = 1;
                                            default:
                                                break label2824;
                                        }
                                    case 3:
                                        switch (dE[0]) {
                                            case 1:
                                                dI[0] = 1;
                                                dG[0] = 15;
                                                break label2824;
                                            case 2:
                                                dI[0] = 1;
                                                dG[0] = 15;
                                                break label2824;
                                            case 3:
                                                dI[0] = 2;
                                                dG[0] = 16;
                                                break label2824;
                                            case 4:
                                                dI[0] = 2;
                                                dG[0] = 16;
                                                dF[0] = 1;
                                                dE[0] = 1;
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
                                        switch (dE[0]) {
                                            case 1:
                                                dI[0] = 2;
                                                dG[0] = 16;
                                                break label2824;
                                            case 2:
                                                dI[0] = 0;
                                                dG[0] = 14;
                                                break label2824;
                                            case 3:
                                            case 4:
                                                dG[0] = 13;
                                                dI[0] = -1;
                                                break label2824;
                                            case 5:
                                                dG[0] = 13;
                                                dI[0] = -1;
                                                dD[0] = 1;
                                                dF[0] = 120;
                                                dE[0] = 0;
                                            default:
                                                break label2824;
                                        }
                                }
                            case 8:
                                var10002 = dE[0]++;
                                switch (dE[0]) {
                                    case 1:
                                    case 2:
                                    case 3:
                                        dG[0] = 13;
                                        break label2824;
                                    case 4:
                                        dG[0] = 14;
                                        break label2824;
                                    case 5:
                                        dG[0] = 15;
                                        break label2824;
                                    case 6:
                                        dG[0] = 15;
                                        a(dz[0] + (dJ[0] << 3), dA[0] - 12, dJ[0] << 3, 0, false);
                                        break label2824;
                                    case 7:
                                        dG[0] = 16;
                                        break label2824;
                                    case 8:
                                        dG[0] = 16;
                                        dF[0] = 2;
                                        break label2824;
                                    case 9:
                                        dE[0] = 0;
                                        dD[0] = 1;
                                    default:
                                        break label2824;
                                }
                            case 24:
                                var10002 = dF[0]++;
                                if (dF[0] < 7) {
                                    var10002 = dE[0]++;
                                    if (dE[0] >= 10) {
                                        dE[0] = 0;
                                    }

                                    dG[0] = (dE[0] >> 1) + 2;
                                    var10000 = dz;
                                    var10000[0] += dB[0];
                                    if (a(dz[0] - fe) < 30) {
                                        if (B == 5 && fm != dJ[0]) {
                                            dD[0] = 3;
                                            dJ[0] = -fm;
                                            dB[0] = fm << 1;
                                            if (dz[0] < dM[0] + 30) {
                                                dB[0] = 6;
                                                dJ[0] = -1;
                                            } else if (dz[0] > dN[0] - 30) {
                                                dB[0] = -6;
                                                dJ[0] = 1;
                                            }

                                            dC[0] = -9;
                                            break;
                                        } else {
                                            dF[0] = 6;
                                        }
                                    }
                                } else if (dF[0] != 7) {
                                    if (dF[0] >= 15) {
                                        dI[0] = -1;
                                        dD[0] = 1;
                                        break;
                                    } else {
                                        var10002 = dE[0]++;
                                        switch (dE[0]) {
                                            case 1:
                                            case 2:
                                            case 3:
                                                dI[0] = -1;
                                                dG[0] = 13;
                                                break label2824;
                                            case 4:
                                                dI[0] = 0;
                                                dG[0] = 14;
                                                break label2824;
                                            case 5:
                                                dI[0] = 1;
                                                dG[0] = 15;
                                                break label2824;
                                            case 6:
                                                dI[0] = 1;
                                                dG[0] = 15;
                                                z(0);
                                                break label2824;
                                            case 7:
                                                dI[0] = 2;
                                                dG[0] = 16;
                                                break label2824;
                                            case 8:
                                                dI[0] = 2;
                                                dG[0] = 16;
                                                dF[0] = 2;
                                                dE[0] = 1;
                                        }
                                    }
                                } else {
                                    dE[0] = 0;
                                }
                            default:
                                break label2824;
                        }

                        dE[0] = dF[0] = 0;
                    }
                }

                if (MoneyDrops.update(fe, ff, fh)) {
                    hudDirty = true;
                }

                if (PotionDrops.update(fe, ff, fh)) {
                    hudDirty = true;
                }
                if (questItemState != 0) {
                    if (questItemState == 1) {
                        if (a(ff - (fh >> 1) - (questItemY - 7)) < 10 && a(fe - questItemX) < 10) {
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
                        if (--aX < 0) {
                            aX = GameText.mainMenuItems.length - 1;
                        }

                        Input.pressed[Input.KEY_LEFT] = false;
                    }

                    if (Input.pressed[Input.KEY_RIGHT]) {
                        if (++aX == GameText.mainMenuItems.length) {
                            aX = 0;
                        }

                        Input.pressed[Input.KEY_RIGHT] = false;
                    }

                    if (Input.pressed[Input.KEY_FIRE]) {
                        label2172: {
                            Input.clearPressed();
                            if (aX == 0) {
                                titleImage = null;
                                releaseMapResources();
                                Save.pendingSave = true;
                                PlayTime.start();
                                changeMap(99);
                            } else if (aX == 1) {
                                if (!Save.isSaved()) {
                                    break label2172;
                                }

                                titleImage = null;
                                releaseMapResources();
                                int[] var12;
                                PlayTime.savedSeconds = (var12 = a(Save.loadRecord("ninjasave")))[0];
                                mapId = var12[1];
                                tick = var12[2];
                                Story.prologueActive = var12[3] == 1;
                                Inventory.money = var12[4];
                                dw = var12[5] == 1;
                                Story.riverMonsterDefeated = var12[6] == 1;
                                Story.tonikuDefeated = var12[7] == 1;
                                Story.sakuraFound = var12[8] == 1;
                                gk = var12[9] == 1;
                                gl = var12[10];
                                gm = var12[11];
                                ij = var12[12];
                                Inventory.swordLevel = var12[13];
                                Skills.dartsLevel2 = var12[14] == 1;
                                Save.pendingLoad = true;
                                PlayTime.start();
                                changeMap(mapId);
                            } else if (aX == 2) {
                                titleImage = null;
                                releaseMapResources();
                                GameState.current = GameState.LOADING;
                                resetLoadingProgress();
                                Midlet.nextGameState = GameState.TUTORIAL;
                                mapId = 101;
                                (new Thread(Midlet.instance)).start();
                            } else if (aX == 3) {
                                try {
                                    Midlet.instance.platformRequest("http://wap.teamobi.com/");
                                } catch (Exception var10) {
                                }
                            } else if (aX == 4) {
                                Midlet.instance.notifyDestroyed();
                            }

                            Input.pressed[Input.KEY_FIRE] = false;
                        }
                    }

                    if (cameraX == 0) {
                        cameraX = 960;
                        cameraY = 48;
                        fK = 1;
                        fL = 1;
                    }

                    cameraX += fK;
                    cameraY += fL;
                    if (cameraX > cameraMaxX) {
                        cameraX = cameraMaxX;
                        fK = -fK;
                    }

                    if (cameraX < 24) {
                        cameraX = 24;
                        fK = -fK;
                    }

                    if (cameraY < 0) {
                        cameraY = 0;
                        fL = -fL;
                    }

                    if (cameraY > cameraMaxY) {
                        cameraY = cameraMaxY;
                        fL = -fL;
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

                    if (ba > aY) {
                        ba -= 4;
                    }

                    if (bb < aZ) {
                        bb += 4;
                    }

                    if (bd > bc) {
                        bd -= 2;
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
                            if (B != 1) {
                                UiFrame.release();
                                ensureMapImages();
                                MessageBox.show(MessageType.GAME_NOTICE);
                                MessageBox.setText("Bạn chỉ có thể lưu và thoát khi nhân vật đang đứng yên.");
                                MessageBox.y = Viewport.viewCenterY - 40;
                                GameState.current = GameState.MESSAGE;
                            } else {
                                b = false;
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
                        K();
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

            while(System.currentTimeMillis() - var1 < (long)frameDurationMs) {
                Thread.yield();
            }
        }

        a();
        Midlet.instance.notifyDestroyed();
    }

    public static void setupSigns(int var0) {
        signCount = 0;
        if (var0 == 0) {
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
        } else if (var0 == 1) {
            signCount = 2;
            allocSigns();
            placeSign(0, "Trường /", 1356, 173);
            placeSign(1, "* Thác nước", 48, 173);
        } else if (var0 == 2) {
            signCount = 4;
            allocSigns();
            placeSign(0, "* Lối vào", 60, 245);
            placeSign(1, "Trường Hirosaki", 240, 197);
            placeSign(2, "Lối ra /", 420, 245);
            placeSign(2, "Mua đồ tiền thật", 552, 245);
            placeSign(3, "đường hoa đào /", 1356, 221);
        } else if (var0 == 3) {
            signCount = 2;
            allocSigns();
            placeSign(0, "* Ngôi làng", 48, 173);
            placeSign(1, "Rừng trúc /", 2808, 173);
        } else if (var0 == 4) {
            signCount = 2;
            allocSigns();
            placeSign(0, "* Vườn đào", 48, 101);
            placeSign(1, "Rừng tối", 1704, 245);
        } else if (var0 == 5) {
            signCount = 2;
            allocSigns();
            placeSign(0, "Trường /", 420, 77);
            placeSign(1, "Rừng tối /", 408, 1829);
        } else if (var0 == 6) {
            signCount = 3;
            allocSigns();
            placeSign(0, "* Thác nước", 72, 101);
            placeSign(1, "Rừng tối /", 504, 53);
            placeSign(2, "Sông Watamoro /", 5916, 197);
        } else {
            if (var0 == 99) {
                signCount = 1;
                allocSigns();
                placeSign(0, "Trường Hirosaki", 744, 173);
            }

        }
    }

    private static void placeSign(int var0, String var1, int var2, int var3) {
        signTexts[var0] = var1;
        signX[var0] = var2;
        signY[var0] = var3;
        int[] var10000 = signX;
        var10000[var0] -= FontGraph.getBoldWidth(var1) >> 1;
        signWidth[var0] = FontGraph.getBoldWidth(var1);
    }

    private static void drawSigns(Graphics var0) {
        for(int var1 = 0; var1 < signCount; ++var1) {
            if (signX[var1] + signWidth[var1] >= cameraX && signX[var1] <= cameraX + Viewport.viewWidth && signY[var1] >= cameraY && signY[var1] - 14 <= cameraY + Viewport.viewHeight) {
                FontGraph.drawBold(var0, signTexts[var1], signX[var1], signY[var1], 0);
            }
        }

    }

    private static void allocSigns() {
        signTexts = new String[signCount];
        signX = new int[signCount];
        signY = new int[signCount];
        signWidth = new int[signCount];
    }

    public static void setupHouses(int var0) {
        houseCount = 0;
        if (var0 == 2) {
            try {
                houseImages = Tile.ensureHouses(houseImages);
            } catch (Exception var2) {
            }

            houseCount = 5;
            allocHouses();
            placeHouse(0, 0, 29, 13);
            placeHouse(1, 1, 34, 13);
            placeHouse(2, 2, 39, 13);
            placeHouse(3, 1, 46, 11);
            placeHouse(4, 2, 51, 11);
        }

        if (var0 == 16) {
            try {
                houseImages = Tile.ensureHouses(houseImages);
            } catch (Exception var1) {
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

    private static void placeHouse(int var0, int var1, int var2, int var3) {
        houseTypes[var0] = var1;
        houseX[var0] = var2 * GameMap.TILE_SIZE + 12;
        houseY[var0] = var3 * GameMap.TILE_SIZE;
        houseHalfWidth[var0] = houseImages[var1].getWidth() >> 1;
        houseHeight[var0] = houseImages[var1].getHeight();
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
        } catch (Exception var0) {
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

    private static void j(Graphics var0) {
        for(int var1 = firstVisibleCol; var1 < lastVisibleCol; ++var1) {
            for(int var2 = firstVisibleRow; var2 < lastVisibleRow; ++var2) {
                int var3;
                if ((var3 = GameMap.tiles[var2 * GameMap.width + var1] - 1) != -1) {
                    var0.drawRegion(tilesetImage, 0, var3 * GameMap.TILE_SIZE, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                }

                if ((GameMap.flagsAt(var1, var2) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                    var0.drawRegion(waterfallImage, 0, 24 * (tick % 4), 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                } else if ((GameMap.flagsAt(var1, var2) & TileFlags.WATER) == TileFlags.WATER) {
                    if ((GameMap.flagsAt(var1, var2 - 1) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                        var0.drawRegion(waterfallImage, 0, 24 * (tick % 4), 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(var1, var2 - 1) & TileFlags.DIGGABLE) == TileFlags.DIGGABLE) {
                        var0.drawRegion(tilesetImage, 0, 504, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    }

                    var0.drawRegion(waterSurfaceImage, 0, (tick % 8 >> 2) * 24, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                }

                if ((GameMap.flagsAt(var1, var2) & TileFlags.DEEP_WATER) == TileFlags.DEEP_WATER) {
                    if ((GameMap.flagsAt(var1, var2 - 1) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                        var0.drawRegion(waterfallImage, 0, 24 * (tick % 4), 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(var1, var2 - 1) & TileFlags.DIGGABLE) == TileFlags.DIGGABLE) {
                        var0.drawRegion(tilesetImage, 0, 504, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    }

                    var0.drawRegion(tilesetImage, 0, (GameMap.tiles[var2 * GameMap.width + var1] - 1) * GameMap.TILE_SIZE, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                }
            }
        }

    }

    private static void k(Graphics var0) {
        for(int var1 = firstVisibleCol; var1 < lastVisibleCol; ++var1) {
            for(int var2 = firstVisibleRow; var2 < lastVisibleRow; ++var2) {
                int var3 = GameMap.tiles[var2 * GameMap.width + var1] - 1;
                if ((GameMap.flagsAt(var1, var2) & TileFlags.PARALLAX) == TileFlags.PARALLAX) {
                    jg = (jf = var1 * GameMap.TILE_SIZE - cameraX) - Viewport.viewCenterX;
                    jh = (ji = (GameMap.TILE_SIZE - 2) * jg / GameMap.TILE_SIZE) + Viewport.viewCenterX;
                    var0.drawRegion(tilesetImage, 0, var3 * GameMap.TILE_SIZE, 24, 24, 0, jh + cameraX, var2 * GameMap.TILE_SIZE, 0);
                } else if ((GameMap.flagsAt(var1, var2) & TileFlags.FOREGROUND_BUSH) != TileFlags.FOREGROUND_BUSH && (GameMap.flagsAt(var1, var2) & TileFlags.FOREGROUND) != TileFlags.FOREGROUND) {
                    if ((GameMap.flagsAt(var1, var2) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                        var0.drawRegion(waterfallImage, 0, 24 * (tick % 4), 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(var1, var2) & TileFlags.WATER) != TileFlags.WATER && (GameMap.flagsAt(var1, var2) & TileFlags.DEEP_WATER) != TileFlags.DEEP_WATER) {
                        if ((GameMap.flagsAt(var1, var2) & TileFlags.BRIDGE_PRESSED) == TileFlags.BRIDGE_PRESSED) {
                            var0.drawRegion(tilesetImage, 0, var3 * GameMap.TILE_SIZE, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE + 1, 0);
                        } else if (var3 != -1) {
                            var0.drawRegion(tilesetImage, 0, var3 * GameMap.TILE_SIZE, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                        }
                    } else if ((GameMap.flagsAt(var1, var2 - 1) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                        var0.drawRegion(waterfallImage, 0, 24 * (tick % 4), 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(var1, var2 - 1) & TileFlags.DIGGABLE) == TileFlags.DIGGABLE) {
                        var0.drawRegion(tilesetImage, 0, 504, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    }
                }
            }
        }

    }

    private static void l(Graphics var0) {
        if (!Settings.lowGraphics) {
            for(int var1 = firstVisibleCol; var1 < lastVisibleCol; ++var1) {
                for(int var2 = firstVisibleRow; var2 < lastVisibleRow; ++var2) {
                    if ((GameMap.flagsAt(var1, var2) & TileFlags.WATER) == TileFlags.WATER) {
                        var0.drawRegion(waterSurfaceImage, 0, (tick % 8 >> 2) * 24, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(var1, var2) & TileFlags.FOREGROUND_BUSH) == TileFlags.FOREGROUND_BUSH) {
                        jg = (jf = var1 * GameMap.TILE_SIZE - cameraX) - Viewport.viewCenterX;
                        jh = (ji = (GameMap.TILE_SIZE - 1) * jg / GameMap.TILE_SIZE) + Viewport.viewCenterX;
                        var0.drawImage(bushImage, jh + cameraX - 2 + (var1 + var2) % 5, var2 * GameMap.TILE_SIZE - 3 + var2 % 5, 0);
                    }

                    if ((GameMap.flagsAt(var1, var2) & TileFlags.FOREGROUND) == TileFlags.FOREGROUND) {
                        var0.drawRegion(tilesetImage, 0, (GameMap.tiles[var2 * GameMap.width + var1] - 1) * GameMap.TILE_SIZE, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE + 2, 0);
                    }

                    if ((GameMap.flagsAt(var1, var2) & TileFlags.DEEP_WATER) == TileFlags.DEEP_WATER) {
                        var0.drawRegion(tilesetImage, 0, (GameMap.tiles[var2 * GameMap.width + var1] - 1) * GameMap.TILE_SIZE, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    }
                }
            }

        }
    }

    private static int H(int var0) {
        return var0 / GameMap.TILE_SIZE * GameMap.TILE_SIZE;
    }

    private static int I(int var0) {
        return var0 / GameMap.TILE_SIZE * GameMap.TILE_SIZE;
    }

    private static void resetLoadingProgress() {
        loadingProgress = 0;
        loadingTarget = 0;
        jB = 0;
    }

    private static void J(int var0) {
        if (Monsters.attackCooldown[var0] > 30) {
            int var2 = Monsters.direction[var0] > 0 ? Monsters.x[var0] : Monsters.x[var0] - Monsters.attackRangeX[var0];
            int var3 = Monsters.direction[var0] >= 0 ? Monsters.x[var0] + Monsters.attackRangeX[var0] : Monsters.x[var0];
            int var4 = Monsters.y[var0] - Monsters.attackRangeY[var0];
            int var1 = Monsters.y[var0] + Monsters.attackRangeY[var0];
            if (B == 6 ? false : (fw ? false : (B == 14 ? false : (fe < var2 ? false : (fe > var3 ? false : (ff < var4 ? false : ff <= var1)))))) {
                Monsters.attackCooldown[var0] = 0;
                Monsters.state[var0] = Monsters.STATE_ATTACKING;
                Monsters.timer[var0] = 0;
            }
        }

    }

    private static void K(int var0) {
        if (Monsters.attackCooldown[var0] > 30) {
            int var2 = Monsters.direction[var0] > 0 ? Monsters.x[var0] : Monsters.x[var0] - Monsters.attackRangeX[var0];
            int var3 = Monsters.direction[var0] >= 0 ? Monsters.x[var0] + Monsters.attackRangeX[var0] : Monsters.x[var0];
            int var4 = Monsters.y[var0] - Monsters.attackRangeY[var0];
            int var1 = Monsters.y[var0] + Monsters.attackRangeY[var0];
            if (gr == 14 ? false : (gn < var2 ? false : (gn > var3 ? false : (go < var4 ? false : go <= var1)))) {
                Monsters.attackCooldown[var0] = 0;
                Monsters.state[var0] = Monsters.STATE_ATTACKING;
                Monsters.timer[var0] = 0;
                Monsters.attackingGirl[var0] = true;
            }
        }

    }

    private static boolean k(int var0, int var1, int var2) {
        if (Monsters.state[var0] == Monsters.STATE_ACTIVE || Monsters.state[var0] == Monsters.STATE_RESTING || Monsters.state[var0] == Monsters.STATE_ATTACKING || Monsters.state[var0] == Monsters.STATE_LAUNCHED) {
            int[] var10000 = Monsters.hp;
            var10000[var0] -= var1;
            if (Monsters.hp[var0] <= 0) {
                Monsters.hp[var0] = 0;
                Monsters.timer[var0] = -5;
                Monsters.aux1[var0] = -(var2 << 3);
                Monsters.aux2[var0] = 0;
                Monsters.healthBarTimer[var0] = 0;
                Monsters.state[var0] = Monsters.STATE_KNOCKED_OUT;
                L(var0);
                return true;
            }

            if (Monsters.state[var0] == Monsters.STATE_LAUNCHED) {
                var10000 = Monsters.timer;
                var10000[var0] -= 7;
                Monsters.direction[var0] = var2;
            } else {
                Monsters.timer[var0] = 0;
                Monsters.direction[var0] = var2;
                Monsters.state[var0] = Monsters.STATE_HURT;
                Monsters.frame[var0] = 3;
            }
        }

        return false;
    }

    private static void L(int var0) {
        gainExp(MonsterStats.exp[Monsters.type[var0]]);
        int var1;
        if ((var1 = tick % 20) > 15) {
            PotionDrops.spawn(Monsters.x[var0], Monsters.y[var0], -(Monsters.direction[var0] << 3), -5, Monsters.damage[var0] << 2, (tick + 1) % 2);
        } else if (var1 == 15) {
            MoneyDrops.spawn(Monsters.x[var0], Monsters.y[var0], -(Monsters.direction[var0] << 2), -4, (Monsters.money[var0] << 3) + tick % 10);
        } else {
            var1 = ((Monsters.type[var0] << 2) / 10 + 1) * 10;
            MoneyDrops.spawn(Monsters.x[var0], Monsters.y[var0], -(Monsters.direction[var0] << 1), -6, var1);
            MoneyDrops.spawn(Monsters.x[var0], Monsters.y[var0], -(Monsters.direction[var0] << 4), -3, var1);
        }

        if (QuestState.current != Quest.NONE && Quest.TARGET_MONSTER[QuestState.current] == Monsters.type[var0]) {
            ++QuestState.progress;
            Notice.show(GameText.monsterNames[Quest.TARGET_MONSTER[QuestState.current]] + ": " + QuestState.progress + "#" + Quest.TARGET_COUNT[QuestState.current]);
        }

        if (Monsters.type[var0] == QuestState.dropMonster - QuestState.DROP_MONSTER_BASE && tick % 10 < QuestState.dropChance) {
            placeQuestItem(Monsters.x[var0], Monsters.y[var0]);
            Aura.show(Monsters.x[var0], Monsters.y[var0] - 28);
        }

    }

    private static void ak() {
        for(int var0 = 0; var0 < Monsters.count; ++var0) {
            int var10002;
            if (Monsters.attackCooldown[var0] <= 30) {
                var10002 = Monsters.attackCooldown[var0]++;
            }

            if (Monsters.healthBarTimer[var0] > 0) {
                var10002 = Monsters.healthBarTimer[var0]--;
            }

            int[] var10000;
            switch (Monsters.state[var0]) {
                case Monsters.STATE_KNOCKED_OUT:
                    var10002 = Monsters.timer[var0]++;
                    var10000 = Monsters.y;
                    var10000[var0] += Monsters.timer[var0];
                    if (tick % 2 == 0) {
                        if (Monsters.aux1[var0] > 1) {
                            var10002 = Monsters.aux1[var0]--;
                        } else if (Monsters.aux1[var0] < -1) {
                            var10002 = Monsters.aux1[var0]++;
                        }
                    }

                    var10000 = Monsters.x;
                    var10000[var0] += Monsters.aux1[var0];
                    Monsters.frame[var0] = 2;
                    if (Monsters.y[var0] < GameMap.pixelHeight && Monsters.x[var0] >= 0 && Monsters.x[var0] < GameMap.pixelWidth) {
                        if ((Monsters.aux2[var0] == 0 || Monsters.aux2[var0] < 3 && Monsters.timer[var0] > 7) && (GameMap.flagsAtPixel(Monsters.x[var0], Monsters.y[var0]) & TileFlags.SOLID) == TileFlags.SOLID) {
                            Monsters.timer[var0] = Monsters.timer[var0] > 4 ? -4 : -Monsters.timer[var0];
                            var10002 = Monsters.aux2[var0]++;
                        }

                        if (GameMap.hasFlag(Monsters.x[var0], Monsters.y[var0], TileFlags.WATER) && Monsters.y[var0] % GameMap.TILE_SIZE > 8) {
                            WaterSplash.spawn(Monsters.x[var0], H(Monsters.y[var0]) + 8);
                        }
                        break;
                    }

                    Monsters.timer[var0] = -300;
                    Monsters.state[var0] = Monsters.STATE_RESPAWN_WAIT;
                    if (U) {
                        Monsters.state[var0] = Monsters.STATE_GONE;
                    }

                    Monsters.spawnUsed[Monsters.group[var0]][Monsters.spawnPoint[var0]] = false;
                    break;
                case Monsters.STATE_ACTIVE:
                    switch (Monsters.type[var0]) {
                        case Monsters.TYPE_ONE_EYED_GHOST:
                            if (tick % 4 == 0) {
                                var10002 = Monsters.frame[var0]++;
                                if (Monsters.frame[var0] > 1) {
                                    Monsters.frame[var0] = 0;
                                }
                            }

                            var10000 = Monsters.x;
                            var10000[var0] += Monsters.direction[var0];
                            if (Monsters.x[var0] > Monsters.rangeB[var0]) {
                                Monsters.direction[var0] = -1;
                            } else if (Monsters.x[var0] < Monsters.rangeA[var0]) {
                                Monsters.direction[var0] = 1;
                            }

                            J(var0);
                            if (tick % 9 == 0) {
                                K(var0);
                            }
                            continue;
                        case Monsters.TYPE_SNAIL:
                            U(var0);
                            continue;
                        case Monsters.TYPE_DEMON_BAT:
                            var10000 = Monsters.x;
                            var10000[var0] += Monsters.vx[var0];
                            var10000 = Monsters.y;
                            var10000[var0] += Monsters.vy[var0];
                            if (Monsters.x[var0] - Monsters.rangeA[var0] > 40) {
                                Monsters.vx[var0] = -(tick % 3 + 1);
                            }

                            if (Monsters.x[var0] - Monsters.rangeA[var0] < -40) {
                                Monsters.vx[var0] = tick % 3 + 1;
                            }

                            if (Monsters.y[var0] - Monsters.rangeB[var0] > 40) {
                                Monsters.vy[var0] = -(tick % 3 + 1);
                            }

                            if (Monsters.y[var0] - Monsters.rangeB[var0] < -40) {
                                Monsters.vy[var0] = tick % 3 + 1;
                            }

                            if (tick % 15 == 0) {
                                Monsters.vx[var0] = tick % 4 - 1;
                                Monsters.vy[var0] = (tick >> 1) % 4 - 1;
                            }

                            if (tick % 2 == 0) {
                                var10002 = Monsters.frame[var0]++;
                                if (Monsters.frame[var0] > 1) {
                                    Monsters.frame[var0] = 0;
                                }

                                if (Monsters.frame[var0] == 0) {
                                    var10000 = Monsters.y;
                                    var10000[var0] -= 3;
                                } else {
                                    var10000 = Monsters.y;
                                    var10000[var0] += 2;
                                }
                            }

                            J(var0);
                            continue;
                        case Monsters.TYPE_ONE_LEGGED_DEMON:
                            var10000 = Monsters.x;
                            var10000[var0] += Monsters.timer[var0];
                            if (Monsters.timer[var0] > 1) {
                                var10002 = Monsters.timer[var0]--;
                            } else if (Monsters.timer[var0] < -1) {
                                var10002 = Monsters.timer[var0]++;
                            }

                            var10000 = Monsters.y;
                            var10000[var0] += Monsters.aux1[var0];
                            var10002 = Monsters.aux1[var0]++;
                            if (Monsters.aux1[var0] > 4) {
                                Monsters.aux1[var0] = -4;
                                Monsters.timer[var0] = Monsters.direction[var0] * 3;
                            }

                            if (a(Monsters.aux1[var0]) == 3) {
                                if (Monsters.x[var0] > Monsters.rangeB[var0]) {
                                    Monsters.direction[var0] = -1;
                                } else if (Monsters.x[var0] < Monsters.rangeA[var0]) {
                                    Monsters.direction[var0] = 1;
                                }

                                Monsters.frame[var0] = 0;
                            } else {
                                Monsters.frame[var0] = 1;
                            }

                            J(var0);
                            if (tick % 9 == 0) {
                                K(var0);
                            }
                            continue;
                        case Monsters.TYPE_GREEN_TOAD:
                            var10000 = Monsters.x;
                            var10000[var0] += Monsters.aux2[var0];
                            if (Monsters.aux2[var0] > 1) {
                                var10002 = Monsters.aux2[var0]--;
                            } else if (Monsters.aux2[var0] < -1) {
                                var10002 = Monsters.aux2[var0]++;
                            }

                            var10000 = Monsters.y;
                            var10000[var0] += Monsters.aux1[var0];
                            var10002 = Monsters.aux1[var0]++;
                            if (Monsters.aux1[var0] > 3) {
                                Monsters.aux1[var0] = -3;
                                Monsters.aux2[var0] = Monsters.direction[var0] << 2;
                                Monsters.timer[var0] = 0;
                                Monsters.y[var0] = Monsters.spawnY[Monsters.group[var0]][Monsters.spawnPoint[var0]];
                                Monsters.state[var0] = Monsters.STATE_RESTING;
                            }

                            if (Monsters.aux1[var0] == -2) {
                                if (Monsters.x[var0] > Monsters.rangeB[var0]) {
                                    Monsters.direction[var0] = -1;
                                } else if (Monsters.x[var0] < Monsters.rangeA[var0]) {
                                    Monsters.direction[var0] = 1;
                                }

                                Monsters.aux2[var0] = Monsters.direction[var0] * 6;
                                Monsters.frame[var0] = 0;
                            } else {
                                Monsters.frame[var0] = 1;
                            }

                            J(var0);
                            if (tick % 9 == 0) {
                                K(var0);
                            }
                            continue;
                        case Monsters.TYPE_SPIDER:
                            if (tick % 4 == 0) {
                                var10002 = Monsters.frame[var0]++;
                                if (Monsters.frame[var0] > 1) {
                                    Monsters.frame[var0] = 0;
                                }

                                var10000 = Monsters.y;
                                var10000[var0] += Monsters.timer[var0];
                            }

                            if (Monsters.y[var0] > Monsters.rangeB[var0]) {
                                Monsters.timer[var0] = -1;
                            } else if (Monsters.y[var0] < Monsters.rangeA[var0]) {
                                Monsters.timer[var0] = 1;
                            }

                            J(var0);
                            continue;
                        case Monsters.TYPE_BEE:
                            P(var0);
                        case Monsters.TYPE_SCARECROW:
                        default:
                            continue;
                        case Monsters.TYPE_TIGER_FISH:
                            var10002 = Monsters.frame[var0]++;
                            if (Monsters.frame[var0] > 1) {
                                Monsters.frame[var0] = 0;
                            }

                            var10000 = Monsters.x;
                            var10000[var0] += tick % 5 * Monsters.direction[var0];
                            if (Monsters.x[var0] > Monsters.rangeB[var0]) {
                                Monsters.direction[var0] = -1;
                            } else if (Monsters.x[var0] < Monsters.rangeA[var0]) {
                                Monsters.direction[var0] = 1;
                            }

                            J(var0);
                            continue;
                        case Monsters.TYPE_WIND_NINJA:
                            Q(var0);
                            continue;
                        case Monsters.TYPE_RED_TIGER_FISH:
                            var10002 = Monsters.frame[var0]++;
                            if (Monsters.frame[var0] > 1) {
                                Monsters.frame[var0] = 0;
                            }

                            var10000 = Monsters.x;
                            var10000[var0] += 5 * Monsters.direction[var0];
                            if (Monsters.x[var0] > Monsters.rangeB[var0]) {
                                Monsters.direction[var0] = -1;
                            } else if (Monsters.x[var0] < Monsters.rangeA[var0]) {
                                Monsters.direction[var0] = 1;
                            }

                            J(var0);
                            continue;
                        case Monsters.TYPE_ONE_EYED_GHOST_2:
                            if (tick % 4 == 0) {
                                var10002 = Monsters.frame[var0]++;
                                Monsters.timer[var0] = 0;
                                if (Monsters.frame[var0] > 1) {
                                    Monsters.frame[var0] = 0;
                                    Monsters.timer[var0] = 1;
                                }
                            }

                            var10000 = Monsters.x;
                            var10000[var0] += Monsters.timer[var0] * Monsters.direction[var0];
                            if (Monsters.x[var0] > Monsters.rangeB[var0]) {
                                Monsters.direction[var0] = -1;
                            } else if (Monsters.x[var0] < Monsters.rangeA[var0]) {
                                Monsters.direction[var0] = 1;
                            }

                            J(var0);
                            if (tick % 9 == 0) {
                                K(var0);
                            }
                            continue;
                        case Monsters.TYPE_DESERT_CENTIPEDE:
                            Q(var0);
                            continue;
                        case Monsters.TYPE_GOLDEN_BAT:
                            var10000 = Monsters.x;
                            var10000[var0] += Monsters.vx[var0];
                            if (Monsters.x[var0] - Monsters.rangeA[var0] > 100) {
                                Monsters.direction[var0] = -1;
                                Monsters.vx[var0] = Monsters.direction[var0] * (tick % 4 + 1);
                                var10000 = Monsters.x;
                                var10000[var0] += Monsters.direction[var0] << 2;
                            } else if (Monsters.x[var0] - Monsters.rangeA[var0] < -100) {
                                Monsters.direction[var0] = 1;
                                Monsters.vx[var0] = Monsters.direction[var0] * (tick % 4 + 1);
                                var10000 = Monsters.x;
                                var10000[var0] += Monsters.direction[var0] << 2;
                            }

                            if (Monsters.y[var0] - Monsters.rangeB[var0] > 20) {
                                Monsters.vy[var0] = -(tick % 4 + 1);
                            } else if (Monsters.y[var0] - Monsters.rangeB[var0] < -20) {
                                Monsters.vy[var0] = tick % 4 + 1;
                            } else {
                                Monsters.vy[var0] = 0;
                            }

                            var10000 = Monsters.y;
                            var10000[var0] += Monsters.vy[var0];
                            if (tick % 2 == 0) {
                                var10002 = Monsters.frame[var0]++;
                                if (Monsters.frame[var0] > 1) {
                                    Monsters.frame[var0] = 0;
                                    var10000 = Monsters.y;
                                    var10000[var0] -= 12;
                                } else {
                                    var10000 = Monsters.y;
                                    var10000[var0] += 12;
                                }
                            }

                            J(var0);
                            continue;
                        case Monsters.TYPE_RAT:
                            var10002 = Monsters.frame[var0]++;
                            if (Monsters.frame[var0] > 1) {
                                Monsters.frame[var0] = 0;
                            }

                            if (tick % 2 == 0) {
                                var10000 = Monsters.x;
                                var10000[var0] += Monsters.direction[var0] << 2;
                            } else {
                                var10000 = Monsters.x;
                                var10000[var0] += Monsters.direction[var0] << 1;
                            }

                            if (Monsters.x[var0] > Monsters.rangeB[var0]) {
                                Monsters.direction[var0] = -1;
                            } else if (Monsters.x[var0] < Monsters.rangeA[var0]) {
                                Monsters.direction[var0] = 1;
                            }

                            J(var0);
                            if (tick % 5 == 0) {
                                K(var0);
                            }
                            continue;
                        case Monsters.TYPE_NATIVE:
                            var10000 = Monsters.x;
                            var10000[var0] += Monsters.timer[var0];
                            if (Monsters.timer[var0] > 1) {
                                var10002 = Monsters.timer[var0]--;
                            } else if (Monsters.timer[var0] < -1) {
                                var10002 = Monsters.timer[var0]++;
                            }

                            var10000 = Monsters.y;
                            var10000[var0] += Monsters.aux1[var0];
                            var10002 = Monsters.aux1[var0]++;
                            if (Monsters.aux1[var0] > 4) {
                                Monsters.aux1[var0] = -4;
                                Monsters.timer[var0] = Monsters.direction[var0] * 3;
                            }

                            if (a(Monsters.aux1[var0]) == 3) {
                                if (Monsters.x[var0] > Monsters.rangeB[var0]) {
                                    Monsters.direction[var0] = -1;
                                } else if (Monsters.x[var0] < Monsters.rangeA[var0]) {
                                    Monsters.direction[var0] = 1;
                                }

                                Monsters.frame[var0] = 0;
                            } else {
                                Monsters.frame[var0] = 1;
                            }

                            J(var0);
                            if (tick % 9 == 0) {
                                K(var0);
                            }
                            continue;
                        case Monsters.TYPE_RED_BEE:
                            P(var0);
                            continue;
                        case Monsters.TYPE_SCORPION:
                            U(var0);
                            continue;
                        case Monsters.TYPE_FLOWER_DEMON:
                        case Monsters.TYPE_PURPLE_FLOWER_DEMON:
                            if (tick % 4 == 0) {
                                var10002 = Monsters.frame[var0]++;
                                if (Monsters.frame[var0] > 1) {
                                    Monsters.frame[var0] = 0;
                                }
                            }

                            if (Monsters.x[var0] > fe) {
                                Monsters.direction[var0] = -1;
                            } else {
                                Monsters.direction[var0] = 1;
                            }

                            J(var0);
                            if (tick % 9 == 0) {
                                K(var0);
                            }
                            continue;
                    }
                case Monsters.STATE_RESTING:
                    var10002 = Monsters.timer[var0]++;
                    if (Monsters.timer[var0] >= 35) {
                        Monsters.timer[var0] = 0;
                        Monsters.state[var0] = Monsters.STATE_ACTIVE;
                    }

                    Monsters.frame[var0] = 0;
                    J(var0);
                    break;
                case Monsters.STATE_ATTACKING:
                    if (B == 14 || fw) {
                        Monsters.frame[var0] = 0;
                        Monsters.state[var0] = Monsters.STATE_ACTIVE;
                        Monsters.timer[var0] = 0;
                        Monsters.aux1[var0] = 0;
                    }

                    switch (Monsters.type[var0]) {
                        case Monsters.TYPE_ONE_EYED_GHOST:
                            var10002 = Monsters.timer[var0]++;
                            if (Monsters.timer[var0] == 2) {
                                if (Monsters.attackingGirl[var0]) {
                                    Monsters.attackingGirl[var0] = false;
                                    b(Monsters.damage[var0], Monsters.direction[var0]);
                                } else {
                                    a(Monsters.damage[var0], Monsters.direction[var0]);
                                }
                            }

                            if ((Monsters.timer[var0] >> 2) % 2 == 0) {
                                var10000 = Monsters.x;
                                var10000[var0] += Monsters.direction[var0];
                                Monsters.frame[var0] = 3;
                            } else {
                                Monsters.frame[var0] = 0;
                                var10000 = Monsters.x;
                                var10000[var0] -= Monsters.direction[var0];
                            }

                            if (Monsters.timer[var0] == 12) {
                                Monsters.state[var0] = Monsters.STATE_RESTING;
                                Monsters.timer[var0] = 0;
                            }
                            continue;
                        case Monsters.TYPE_SNAIL:
                            a(Monsters.damage[var0], -fm);
                            Monsters.state[var0] = Monsters.STATE_RESTING;
                            Monsters.timer[var0] = 0;
                            Monsters.frame[var0] = 2;
                            continue;
                        case Monsters.TYPE_DEMON_BAT:
                            if (Monsters.x[var0] < fe) {
                                Monsters.vx[var0] = 3 + tick % 4;
                            } else if (Monsters.x[var0] > fe) {
                                Monsters.vx[var0] = -(3 + tick % 4);
                            }

                            if (Monsters.y[var0] < ff) {
                                Monsters.vy[var0] = tick % 3;
                            } else if (Monsters.y[var0] > ff) {
                                Monsters.vy[var0] = -tick % 3;
                            }

                            var10000 = Monsters.x;
                            var10000[var0] += Monsters.vx[var0];
                            var10000 = Monsters.y;
                            var10000[var0] += Monsters.vy[var0];
                            if (tick % 2 == 0) {
                                var10002 = Monsters.frame[var0]++;
                                if (Monsters.frame[var0] > 1) {
                                    Monsters.frame[var0] = 0;
                                }
                            }

                            if (a(Monsters.x[var0] - fe) < 5 && a(Monsters.y[var0] - ff) < 5) {
                                Monsters.attackCooldown[var0] = 0;
                                a(Monsters.damage[var0], -fm);
                                Monsters.timer[var0] = 0;
                                Monsters.state[var0] = Monsters.STATE_ACTIVE;
                            }

                            if (a(Monsters.x[var0] - fe) > 160 || a(Monsters.y[var0] - ff) > 160) {
                                Monsters.state[var0] = Monsters.STATE_ACTIVE;
                                Monsters.timer[var0] = 0;
                                Monsters.vx[var0] = Monsters.vy[var0] = 0;
                                Monsters.attackCooldown[var0] = 0;
                            }
                            continue;
                        case Monsters.TYPE_ONE_LEGGED_DEMON:
                            var10002 = Monsters.timer[var0]++;
                            if (Monsters.timer[var0] == 3) {
                                if (Monsters.attackingGirl[var0]) {
                                    Monsters.attackingGirl[var0] = false;
                                    b(Monsters.damage[var0], Monsters.direction[var0]);
                                } else {
                                    a(Monsters.damage[var0], Monsters.direction[var0]);
                                }
                            }

                            if ((Monsters.timer[var0] >> 2) % 2 == 0) {
                                var10000 = Monsters.x;
                                var10000[var0] += Monsters.direction[var0];
                                Monsters.frame[var0] = 0;
                            } else {
                                Monsters.frame[var0] = 3;
                                var10000 = Monsters.x;
                                var10000[var0] -= Monsters.direction[var0];
                            }

                            if (Monsters.timer[var0] == 12) {
                                Monsters.y[var0] = Monsters.spawnY[Monsters.group[var0]][Monsters.spawnPoint[var0]];
                                Monsters.state[var0] = Monsters.STATE_RESTING;
                                Monsters.aux1[var0] = -4;
                                Monsters.timer[var0] = 0;
                            }
                            continue;
                        case Monsters.TYPE_GREEN_TOAD:
                            var10002 = Monsters.timer[var0]++;
                            if (Monsters.timer[var0] == 3) {
                                if (Monsters.attackingGirl[var0]) {
                                    Monsters.attackingGirl[var0] = false;
                                    b(Monsters.damage[var0], Monsters.direction[var0]);
                                } else {
                                    a(Monsters.damage[var0], Monsters.direction[var0]);
                                }
                            }

                            if ((Monsters.timer[var0] >> 2) % 2 == 0) {
                                var10000 = Monsters.x;
                                var10000[var0] += Monsters.direction[var0];
                                Monsters.frame[var0] = 0;
                            } else {
                                Monsters.frame[var0] = 1;
                                var10000 = Monsters.x;
                                var10000[var0] -= Monsters.direction[var0];
                            }

                            if (Monsters.timer[var0] == 12) {
                                Monsters.y[var0] = Monsters.spawnY[Monsters.group[var0]][Monsters.spawnPoint[var0]];
                                Monsters.state[var0] = Monsters.STATE_RESTING;
                                Monsters.aux1[var0] = -4;
                                Monsters.timer[var0] = 0;
                            }
                            continue;
                        case Monsters.TYPE_SPIDER:
                            a(Monsters.damage[var0], -fm);
                            Monsters.state[var0] = Monsters.STATE_ACTIVE;
                            Monsters.timer[var0] = 1;
                            continue;
                        case Monsters.TYPE_BEE:
                            N(var0);
                        case Monsters.TYPE_SCARECROW:
                        default:
                            continue;
                        case Monsters.TYPE_TIGER_FISH:
                            var10002 = Monsters.timer[var0]++;
                            if (Monsters.timer[var0] <= 10) {
                                if (Monsters.y[var0] > ff) {
                                    var10000 = Monsters.y;
                                    var10000[var0] -= 4;
                                }

                                if (Monsters.x[var0] < fe) {
                                    var10000 = Monsters.x;
                                    var10000[var0] += 2;
                                } else if (Monsters.x[var0] > fe) {
                                    var10000 = Monsters.x;
                                    var10000[var0] -= 2;
                                }

                                if (a(Monsters.x[var0] - fe) < 16 && a(Monsters.y[var0] - (ff - 16)) < 20) {
                                    a(Monsters.damage[var0], -fm);
                                    Monsters.timer[var0] = 10;
                                }
                            }

                            if (Monsters.timer[var0] == 10) {
                                Monsters.aux1[var0] = 1;
                            }

                            if (Monsters.timer[var0] > 10) {
                                var10000 = Monsters.y;
                                var10000[var0] += Monsters.aux1[var0];
                                var10002 = Monsters.aux1[var0]++;
                                if (Monsters.y[var0] > Monsters.spawnY[Monsters.group[var0]][Monsters.spawnPoint[var0]]) {
                                    Monsters.y[var0] = Monsters.spawnY[Monsters.group[var0]][Monsters.spawnPoint[var0]];
                                    Monsters.aux1[var0] = 0;
                                    Monsters.timer[var0] = 0;
                                    Monsters.state[var0] = Monsters.STATE_ACTIVE;
                                }
                            }

                            Monsters.frame[var0] = Monsters.timer[var0] % 2 == 0 ? 0 : 1;
                            continue;
                        case Monsters.TYPE_WIND_NINJA:
                            var10002 = Monsters.timer[var0]++;
                            if (Monsters.timer[var0] < 6) {
                                var10000 = Monsters.x;
                                var10000[var0] += Monsters.direction[var0] << 2;
                            } else if (Monsters.timer[var0] == 6) {
                                if (a(Monsters.x[var0] + (Monsters.direction[var0] << 3) - fe) < 13 + fi) {
                                    a(Monsters.damage[var0], Monsters.direction[var0]);
                                }
                            } else if (Monsters.timer[var0] < 12) {
                                var10000 = Monsters.x;
                                var10000[var0] -= Monsters.direction[var0] << 1;
                            } else if (Monsters.timer[var0] == 12) {
                                Monsters.y[var0] = Monsters.spawnY[Monsters.group[var0]][Monsters.spawnPoint[var0]];
                                Monsters.state[var0] = Monsters.STATE_RESTING;
                                Monsters.aux1[var0] = -4;
                                Monsters.timer[var0] = 0;
                            }

                            Monsters.frame[var0] = 1;
                            if (Monsters.timer[var0] > 3 && Monsters.timer[var0] < 9 && Monsters.timer[var0] % 2 == 0) {
                                Monsters.frame[var0] = 3;
                            }
                            continue;
                        case Monsters.TYPE_RED_TIGER_FISH:
                            var10002 = Monsters.timer[var0]++;
                            Monsters.frame[var0] = 3;
                            if (Monsters.timer[var0] <= 15) {
                                var10000 = Monsters.y;
                                var10000[var0] -= 7;
                                if (Monsters.x[var0] < fe) {
                                    var10002 = Monsters.x[var0]++;
                                } else if (Monsters.x[var0] > fe) {
                                    var10002 = Monsters.x[var0]--;
                                }

                                if (a(Monsters.x[var0] - fe) < 16 && a(Monsters.y[var0] - (ff - 16)) < 20) {
                                    a(Monsters.damage[var0], -fm);
                                    Monsters.timer[var0] = 15;
                                }
                            }

                            if (Monsters.timer[var0] == 15) {
                                Monsters.aux1[var0] = 1;
                            }

                            if (Monsters.timer[var0] > 15) {
                                Monsters.frame[var0] = 2;
                                var10000 = Monsters.y;
                                var10000[var0] += Monsters.aux1[var0];
                                var10002 = Monsters.aux1[var0]++;
                                if (a(Monsters.x[var0] - fe) < 16 && a(Monsters.y[var0] - (ff - 16)) < 20) {
                                    a(Monsters.damage[var0], -fm);
                                }

                                if (Monsters.y[var0] > Monsters.spawnY[Monsters.group[var0]][Monsters.spawnPoint[var0]]) {
                                    Monsters.y[var0] = Monsters.spawnY[Monsters.group[var0]][Monsters.spawnPoint[var0]];
                                    Monsters.aux1[var0] = 0;
                                    Monsters.timer[var0] = 0;
                                    Monsters.state[var0] = Monsters.STATE_ACTIVE;
                                }
                            }
                            continue;
                        case Monsters.TYPE_ONE_EYED_GHOST_2:
                            var10002 = Monsters.timer[var0]++;
                            if (Monsters.timer[var0] == 4 && Monsters.y[var0] == ff) {
                                a(Monsters.damage[var0], Monsters.direction[var0]);
                            }

                            if (Monsters.timer[var0] < 8) {
                                var10000 = Monsters.x;
                                var10000[var0] += Monsters.direction[var0] << 1;
                                Monsters.frame[var0] = 3;
                            } else {
                                Monsters.frame[var0] = 0;
                                var10000 = Monsters.x;
                                var10000[var0] -= Monsters.direction[var0] << 1;
                            }

                            if (Monsters.timer[var0] == 12) {
                                Monsters.state[var0] = Monsters.STATE_RESTING;
                                Monsters.timer[var0] = 0;
                                Monsters.attackCooldown[var0] = 25;
                            }
                            continue;
                        case Monsters.TYPE_DESERT_CENTIPEDE:
                            R(var0);
                            continue;
                        case Monsters.TYPE_GOLDEN_BAT:
                            if (Monsters.x[var0] < fe) {
                                Monsters.vx[var0] = 4 + tick % 6;
                            } else if (Monsters.x[var0] > fe) {
                                Monsters.vx[var0] = -(4 + tick % 6);
                            }

                            if (Monsters.y[var0] < ff) {
                                Monsters.vy[var0] = 2 + tick % 4;
                            } else if (Monsters.y[var0] > ff) {
                                Monsters.vy[var0] = -(2 + tick % 4);
                            }

                            var10000 = Monsters.x;
                            var10000[var0] += Monsters.vx[var0];
                            var10000 = Monsters.y;
                            var10000[var0] += Monsters.vy[var0];
                            if (tick % 2 == 0) {
                                var10002 = Monsters.frame[var0]++;
                                if (Monsters.frame[var0] > 1) {
                                    Monsters.frame[var0] = 0;
                                }
                            }

                            if (a(Monsters.x[var0] - fe) < 5 && a(Monsters.y[var0] - ff) < 5) {
                                a(Monsters.damage[var0], -fm);
                                Monsters.state[var0] = Monsters.STATE_ACTIVE;
                                Monsters.timer[var0] = 0;
                                Monsters.direction[var0] = Monsters.x[var0] > Monsters.rangeA[var0] ? -1 : 1;
                                Monsters.vx[var0] = Monsters.direction[var0] * (tick % 4 + 1);
                                Monsters.attackCooldown[var0] = 10;
                            }

                            if (a(Monsters.x[var0] - fe) > 130 || a(Monsters.y[var0] - ff) > 130) {
                                Monsters.state[var0] = Monsters.STATE_ACTIVE;
                                Monsters.timer[var0] = 0;
                                Monsters.vx[var0] = Monsters.vy[var0] = 0;
                                Monsters.direction[var0] = Monsters.x[var0] > Monsters.rangeA[var0] ? -1 : 1;
                                Monsters.vx[var0] = Monsters.direction[var0] * (tick % 4 + 1);
                                Monsters.attackCooldown[var0] = 0;
                            }
                            continue;
                        case Monsters.TYPE_RAT:
                            R(var0);
                            continue;
                        case Monsters.TYPE_NATIVE:
                            var10002 = Monsters.timer[var0]++;
                            if (Monsters.timer[var0] == 3) {
                                if (Monsters.attackingGirl[var0]) {
                                    Monsters.attackingGirl[var0] = false;
                                    b(Monsters.damage[var0], Monsters.direction[var0]);
                                } else {
                                    a(Monsters.damage[var0], Monsters.direction[var0]);
                                }
                            }

                            if ((Monsters.timer[var0] >> 2) % 2 == 0) {
                                var10000 = Monsters.x;
                                var10000[var0] += Monsters.direction[var0];
                                Monsters.frame[var0] = 0;
                            } else {
                                Monsters.frame[var0] = 3;
                                var10000 = Monsters.x;
                                var10000[var0] -= Monsters.direction[var0];
                            }

                            if (Monsters.timer[var0] == 12) {
                                Monsters.y[var0] = Monsters.spawnY[Monsters.group[var0]][Monsters.spawnPoint[var0]];
                                Monsters.state[var0] = Monsters.STATE_RESTING;
                                Monsters.aux1[var0] = -4;
                                Monsters.timer[var0] = 0;
                            }
                            continue;
                        case Monsters.TYPE_RED_BEE:
                            N(var0);
                            continue;
                        case Monsters.TYPE_SCORPION:
                        case Monsters.TYPE_FLOWER_DEMON:
                            var10002 = Monsters.timer[var0]++;
                            Monsters.frame[var0] = 3;
                            if (Monsters.timer[var0] == 3) {
                                Projectiles.spawn(Monsters.x[var0] + (Monsters.direction[var0] << 3), Monsters.y[var0] - 12, Monsters.direction[var0] << 3, Monsters.damage[var0], 2);
                            } else if (Monsters.timer[var0] == 6) {
                                Monsters.state[var0] = Monsters.STATE_RESTING;
                                Monsters.timer[var0] = 0;
                            }
                            continue;
                        case Monsters.TYPE_PURPLE_FLOWER_DEMON:
                            var10002 = Monsters.timer[var0]++;
                            Monsters.frame[var0] = 3;
                            if (Monsters.timer[var0] == 3) {
                                var10002 = Monsters.aux1[var0]++;
                                if (Monsters.aux1[var0] == 3) {
                                    Monsters.aux1[var0] = 0;
                                }

                                if (Monsters.aux1[var0] == 2) {
                                    ArcProjectiles.spawn(Monsters.x[var0], Monsters.y[var0] - 22, Monsters.direction[var0], -10, Monsters.damage[var0]);
                                } else if (Monsters.aux1[var0] == 0) {
                                    ArcProjectiles.spawn(Monsters.x[var0], Monsters.y[var0] - 22, Monsters.direction[var0] << 1, -9, Monsters.damage[var0]);
                                } else {
                                    ArcProjectiles.spawn(Monsters.x[var0], Monsters.y[var0] - 22, 0, -11, Monsters.damage[var0]);
                                }

                                WaterSplash.spawn(Monsters.x[var0], Monsters.y[var0] - 20);
                            } else if (Monsters.timer[var0] == 6) {
                                Monsters.state[var0] = Monsters.STATE_RESTING;
                                Monsters.timer[var0] = 0;
                            }
                            continue;
                    }
                case Monsters.STATE_RESPAWN_WAIT:
                    var10002 = Monsters.timer[var0]++;
                    if (Monsters.timer[var0] >= Monsters.respawnDelay) {
                        Monsters.timer[var0] = 0;
                        int var2 = tick % Monsters.spawnX[Monsters.group[var0]].length;
                        if (Monsters.spawnUsed[Monsters.group[var0]][var2]) {
                            Monsters.timer[var0] = Monsters.respawnDelay;
                        } else {
                            Monsters.x[var0] = Monsters.spawnX[Monsters.group[var0]][var2];
                            Monsters.y[var0] = Monsters.spawnY[Monsters.group[var0]][var2];
                            Monsters.spawnUsed[Monsters.group[var0]][var2] = true;
                            Monsters.spawnPoint[var0] = var2;
                            Monsters.state[var0] = Monsters.STATE_ACTIVE;
                            Monsters.hp[var0] = Monsters.maxHp[var0];
                            switch (Monsters.type[var0]) {
                                case Monsters.TYPE_ONE_EYED_GHOST:
                                    Monsters.rangeA[var0] = Monsters.x[var0] - 20;
                                    Monsters.rangeB[var0] = Monsters.x[var0] + 20;
                                    break;
                                case Monsters.TYPE_SNAIL:
                                    Monsters.rangeA[var0] = Monsters.x[var0] - 10;
                                    Monsters.rangeB[var0] = Monsters.x[var0] + 10;
                                    break;
                                case Monsters.TYPE_DEMON_BAT:
                                    Monsters.rangeA[var0] = Monsters.x[var0] + 12;
                                    Monsters.rangeB[var0] = Monsters.y[var0] + 12;
                                    break;
                                case Monsters.TYPE_ONE_LEGGED_DEMON:
                                    Monsters.rangeA[var0] = Monsters.x[var0] - 20;
                                    Monsters.rangeB[var0] = Monsters.x[var0] + 20;
                                    Monsters.aux1[var0] = -4;
                                case Monsters.TYPE_GREEN_TOAD:
                                    Monsters.rangeA[var0] = Monsters.x[var0] - 20;
                                    Monsters.rangeB[var0] = Monsters.x[var0] + 20;
                                    Monsters.aux1[var0] = -3;
                                    break;
                                case Monsters.TYPE_SPIDER:
                                    Monsters.rangeA[var0] = Monsters.y[var0] - 10;
                                    Monsters.rangeB[var0] = Monsters.y[var0] + 10;
                                    Monsters.timer[var0] = 1;
                                    break;
                                case Monsters.TYPE_BEE:
                                    Monsters.rangeA[var0] = Monsters.x[var0];
                                    Monsters.rangeB[var0] = Monsters.y[var0];
                                    break;
                                case Monsters.TYPE_SCARECROW:
                                    Monsters.state[var0] = Monsters.STATE_RESTING;
                                    break;
                                case Monsters.TYPE_TIGER_FISH:
                                case Monsters.TYPE_WIND_NINJA:
                                case Monsters.TYPE_ONE_EYED_GHOST_2:
                                case Monsters.TYPE_DESERT_CENTIPEDE:
                                    Monsters.rangeA[var0] = Monsters.x[var0] - 20;
                                    Monsters.rangeB[var0] = Monsters.x[var0] + 20;
                                    break;
                                case Monsters.TYPE_RED_TIGER_FISH:
                                    Monsters.rangeA[var0] = Monsters.x[var0] - 30;
                                    Monsters.rangeB[var0] = Monsters.x[var0] + 30;
                                    break;
                                case Monsters.TYPE_GOLDEN_BAT:
                                    Monsters.rangeA[var0] = Monsters.x[var0] + 12;
                                    Monsters.rangeB[var0] = Monsters.y[var0] + 12;
                                    break;
                                case Monsters.TYPE_RAT:
                                    Monsters.rangeA[var0] = Monsters.x[var0] - 40;
                                    Monsters.rangeB[var0] = Monsters.x[var0] + 40;
                                    break;
                                case Monsters.TYPE_NATIVE:
                                    Monsters.rangeA[var0] = Monsters.x[var0] - 20;
                                    Monsters.rangeB[var0] = Monsters.x[var0] + 20;
                                    Monsters.aux1[var0] = -4;
                                    break;
                                case Monsters.TYPE_RED_BEE:
                                    Monsters.rangeA[var0] = Monsters.x[var0];
                                    Monsters.rangeB[var0] = Monsters.y[var0];
                                    break;
                                case Monsters.TYPE_SCORPION:
                                    Monsters.rangeA[var0] = Monsters.x[var0] - 30;
                                    Monsters.rangeB[var0] = Monsters.x[var0] + 30;
                            }
                        }
                    }
                    break;
                case Monsters.STATE_HURT:
                    Monsters.healthBarTimer[var0] = 100;
                    Monsters.frame[var0] = 2;
                    switch (Monsters.type[var0]) {
                        case Monsters.TYPE_ONE_EYED_GHOST:
                            if (Monsters.attackCooldown[var0] < 30) {
                                var10000 = Monsters.x;
                                var10000[var0] -= Monsters.direction[var0] << 1;
                            } else {
                                var10000 = Monsters.x;
                                var10000[var0] -= Monsters.direction[var0];
                            }

                            var10002 = Monsters.timer[var0]++;
                            if (Monsters.timer[var0] == 1) {
                                var10000 = Monsters.y;
                                var10000[var0] -= 2;
                            } else if (Monsters.timer[var0] == 5) {
                                var10000 = Monsters.y;
                                var10000[var0] += 2;
                                Monsters.state[var0] = Monsters.STATE_ACTIVE;
                            }
                            break;
                        case Monsters.TYPE_SNAIL:
                            S(var0);
                            break;
                        case Monsters.TYPE_DEMON_BAT:
                            var10002 = Monsters.timer[var0]++;
                            var10000 = Monsters.x;
                            var10000[var0] -= Monsters.vx[var0] >> 3;
                            var10000 = Monsters.y;
                            var10000[var0] -= Monsters.vy[var0] >> 3;
                            if (Monsters.vx[var0] == 0) {
                                Monsters.vx[var0] = 2;
                            }

                            if (Monsters.vy[var0] == 0) {
                                Monsters.vy[var0] = 2;
                            }

                            if (Monsters.timer[var0] == 5) {
                                Monsters.state[var0] = Monsters.STATE_ACTIVE;
                                Monsters.timer[var0] = 0;
                                Monsters.vx[var0] = Monsters.vy[var0] = 0;
                                Monsters.attackCooldown[var0] = 10;
                            }
                            break;
                        case Monsters.TYPE_ONE_LEGGED_DEMON:
                            var10000 = Monsters.x;
                            var10000[var0] -= Monsters.direction[var0];
                            Monsters.frame[var0] = 2;
                            var10002 = Monsters.timer[var0]++;
                            if (Monsters.timer[var0] == 7) {
                                Monsters.y[var0] = Monsters.spawnY[Monsters.group[var0]][Monsters.spawnPoint[var0]];
                                Monsters.state[var0] = Monsters.STATE_ACTIVE;
                                Monsters.aux1[var0] = -4;
                                Monsters.timer[var0] = Monsters.direction[var0] * 3;
                            }
                            break;
                        case Monsters.TYPE_GREEN_TOAD:
                            var10000 = Monsters.x;
                            var10000[var0] -= Monsters.direction[var0];
                            Monsters.frame[var0] = 2;
                            var10002 = Monsters.timer[var0]++;
                            if (Monsters.timer[var0] == 7) {
                                Monsters.y[var0] = Monsters.spawnY[Monsters.group[var0]][Monsters.spawnPoint[var0]];
                                Monsters.state[var0] = Monsters.STATE_ACTIVE;
                                Monsters.aux1[var0] = -4;
                                Monsters.timer[var0] = Monsters.direction[var0] * 3;
                            }
                            break;
                        case Monsters.TYPE_SPIDER:
                            var10002 = Monsters.timer[var0]++;
                            var10002 = Monsters.y[var0]--;
                            if (Monsters.timer[var0] == 3) {
                                Monsters.state[var0] = Monsters.STATE_ACTIVE;
                                Monsters.timer[var0] = 1;
                            }

                            Monsters.frame[var0] = 2;
                            break;
                        case Monsters.TYPE_BEE:
                            O(var0);
                            break;
                        case Monsters.TYPE_SCARECROW:
                            var10002 = Monsters.timer[var0]++;
                            Monsters.direction[var0] = -fm;
                            if (Monsters.timer[var0] % 4 > 1) {
                                Monsters.frame[var0] = 0;
                            } else {
                                Monsters.frame[var0] = 2;
                            }

                            if (Monsters.timer[var0] == 8) {
                                Monsters.state[var0] = Monsters.STATE_RESTING;
                                Monsters.direction[var0] = 0;
                                Monsters.vx[var0] = Monsters.vy[var0] = 0;
                                Monsters.attackCooldown[var0] = 0;
                            }
                            break;
                        case Monsters.TYPE_TIGER_FISH:
                            Monsters.state[var0] = Monsters.STATE_ATTACKING;
                            break;
                        case Monsters.TYPE_WIND_NINJA:
                            var10000 = Monsters.x;
                            var10000[var0] -= Monsters.direction[var0];
                            Monsters.frame[var0] = 2;
                            var10002 = Monsters.timer[var0]++;
                            if (Monsters.timer[var0] == 6) {
                                Monsters.y[var0] = Monsters.spawnY[Monsters.group[var0]][Monsters.spawnPoint[var0]];
                                Monsters.state[var0] = Monsters.STATE_ATTACKING;
                                Monsters.timer[var0] = 4;
                                var10000 = Monsters.x;
                                var10000[var0] += Monsters.direction[var0] << 3;
                            }
                            break;
                        case Monsters.TYPE_RED_TIGER_FISH:
                            Monsters.state[var0] = Monsters.STATE_ATTACKING;
                            break;
                        case Monsters.TYPE_ONE_EYED_GHOST_2:
                            var10000 = Monsters.x;
                            var10000[var0] -= Monsters.direction[var0];
                            Monsters.frame[var0] = 2;
                            var10002 = Monsters.timer[var0]++;
                            if (Monsters.timer[var0] == 6) {
                                Monsters.y[var0] = Monsters.spawnY[Monsters.group[var0]][Monsters.spawnPoint[var0]];
                                Monsters.state[var0] = Monsters.STATE_ATTACKING;
                                Monsters.timer[var0] = 0;
                                var10000 = Monsters.x;
                                var10000[var0] += Monsters.direction[var0] << 3;
                            }
                            break;
                        case Monsters.TYPE_DESERT_CENTIPEDE:
                            S(var0);
                            break;
                        case Monsters.TYPE_GOLDEN_BAT:
                            var10002 = Monsters.timer[var0]++;
                            var10000 = Monsters.x;
                            var10000[var0] -= Monsters.vx[var0] >> 3;
                            var10000 = Monsters.y;
                            var10000[var0] -= Monsters.vy[var0] >> 3;
                            if (Monsters.vx[var0] == 0) {
                                Monsters.vx[var0] = 2;
                            }

                            if (Monsters.vy[var0] == 0) {
                                Monsters.vy[var0] = 2;
                            }

                            if (Monsters.timer[var0] == 5) {
                                Monsters.state[var0] = Monsters.STATE_ACTIVE;
                                Monsters.timer[var0] = 0;
                                Monsters.direction[var0] = Monsters.x[var0] > Monsters.rangeA[var0] ? -1 : 1;
                                Monsters.vx[var0] = Monsters.direction[var0] << 3;
                                Monsters.attackCooldown[var0] = 0;
                            }
                            break;
                        case Monsters.TYPE_RAT:
                            T(var0);
                            break;
                        case Monsters.TYPE_NATIVE:
                            var10000 = Monsters.x;
                            var10000[var0] -= Monsters.direction[var0];
                            Monsters.frame[var0] = 2;
                            var10002 = Monsters.timer[var0]++;
                            if (Monsters.timer[var0] == 3) {
                                Monsters.y[var0] = Monsters.spawnY[Monsters.group[var0]][Monsters.spawnPoint[var0]];
                                Monsters.state[var0] = Monsters.STATE_ACTIVE;
                                Monsters.aux1[var0] = -4;
                                Monsters.timer[var0] = Monsters.direction[var0] * 3;
                            }
                            break;
                        case Monsters.TYPE_RED_BEE:
                            O(var0);
                            break;
                        case Monsters.TYPE_SCORPION:
                            T(var0);
                            break;
                        case Monsters.TYPE_FLOWER_DEMON:
                            var10002 = Monsters.timer[var0]++;
                            var10000 = Monsters.x;
                            var10000[var0] -= Monsters.direction[var0];
                            if (Monsters.timer[var0] == 4) {
                                var10000 = Monsters.x;
                                var10000[var0] += Monsters.direction[var0] << 2;
                                Monsters.state[var0] = Monsters.STATE_ACTIVE;
                            }
                            break;
                        case Monsters.TYPE_PURPLE_FLOWER_DEMON:
                            var10002 = Monsters.timer[var0]++;
                            var10000 = Monsters.x;
                            var10000[var0] -= Monsters.direction[var0];
                            if (Monsters.timer[var0] == 4) {
                                var10000 = Monsters.x;
                                var10000[var0] += Monsters.direction[var0] << 2;
                                Monsters.timer[var0] = 0;
                                Monsters.state[var0] = Monsters.STATE_ATTACKING;
                            }
                    }
                case Monsters.STATE_UNUSED_7:
                case Monsters.STATE_GONE:
                default:
                    break;
                case Monsters.STATE_LAUNCHED:
                    var10000 = Monsters.y;
                    var10000[var0] += Monsters.timer[var0];
                    var10002 = Monsters.timer[var0]++;
                    Monsters.frame[var0] = 2;
                    if (Monsters.y[var0] > Monsters.spawnY[Monsters.group[var0]][Monsters.spawnPoint[var0]]) {
                        if (Monsters.type[var0] != Monsters.TYPE_DEMON_BAT && Monsters.type[var0] != Monsters.TYPE_BEE && Monsters.type[var0] != Monsters.TYPE_RED_BEE && Monsters.type[var0] != Monsters.TYPE_GOLDEN_BAT) {
                            Monsters.y[var0] = Monsters.spawnY[Monsters.group[var0]][Monsters.spawnPoint[var0]];
                        }

                        Monsters.state[var0] = Monsters.STATE_RESTING;
                    }
            }
        }

    }

    private static void N(int var0) {
        if (Monsters.x[var0] < fe) {
            Monsters.vx[var0] = tick % 3 + 3;
            Monsters.direction[var0] = 1;
        } else if (Monsters.x[var0] > fe) {
            Monsters.vx[var0] = -tick % 3 - 3;
            Monsters.direction[var0] = -1;
        }

        if (Monsters.y[var0] < ff) {
            Monsters.vy[var0] = tick % 3 + 3;
        } else if (Monsters.y[var0] > ff) {
            Monsters.vy[var0] = -tick % 3 - 3;
        }

        int[] var10000 = Monsters.x;
        var10000[var0] += Monsters.vx[var0];
        var10000 = Monsters.y;
        var10000[var0] += Monsters.vy[var0];
        int var10002 = Monsters.frame[var0]++;
        if (Monsters.frame[var0] > 1) {
            Monsters.frame[var0] = 0;
        }

        if (a(Monsters.x[var0] - fe) < 10 && a(Monsters.y[var0] - ff) < 10) {
            Monsters.attackCooldown[var0] = 0;
            if (Monsters.attackingGirl[var0]) {
                Monsters.attackingGirl[var0] = false;
                b(Monsters.damage[var0], Monsters.direction[var0]);
            } else {
                a(Monsters.damage[var0], Monsters.direction[var0]);
            }

            Monsters.timer[var0] = 0;
            Monsters.state[var0] = Monsters.STATE_ACTIVE;
        }

        if (a(Monsters.x[var0] - fe) > 160 || a(Monsters.y[var0] - ff) > 160) {
            Monsters.state[var0] = Monsters.STATE_ACTIVE;
            Monsters.timer[var0] = 0;
            Monsters.vx[var0] = Monsters.vy[var0] = 0;
            Monsters.attackCooldown[var0] = 0;
        }

    }

    private static void O(int var0) {
        int var10002 = Monsters.timer[var0]++;
        int[] var10000 = Monsters.x;
        var10000[var0] -= Monsters.vx[var0];
        var10000 = Monsters.y;
        var10000[var0] -= Monsters.vy[var0];
        if (Monsters.vx[var0] == 0) {
            Monsters.vx[var0] = 2;
        }

        if (Monsters.vy[var0] == 0) {
            Monsters.vy[var0] = 2;
        }

        if (Monsters.timer[var0] == 5) {
            Monsters.state[var0] = Monsters.STATE_ACTIVE;
            Monsters.timer[var0] = 0;
            Monsters.vx[var0] = Monsters.vy[var0] = 0;
            Monsters.attackCooldown[var0] = 0;
        }

    }

    private static void P(int var0) {
        int[] var10000 = Monsters.x;
        var10000[var0] += Monsters.vx[var0];
        var10000 = Monsters.y;
        var10000[var0] += Monsters.vy[var0];
        if (Monsters.x[var0] - Monsters.rangeA[var0] > 40) {
            Monsters.vx[var0] = -(tick % 2 + 1);
        }

        if (Monsters.x[var0] - Monsters.rangeA[var0] < -40) {
            Monsters.vx[var0] = tick % 2 + 1;
        }

        if (Monsters.y[var0] - Monsters.rangeB[var0] > 40) {
            Monsters.vy[var0] = -(tick % 2 + 1);
        }

        if (Monsters.y[var0] - Monsters.rangeB[var0] < -40) {
            Monsters.vy[var0] = tick % 2 + 1;
        }

        if (tick % 15 == 0) {
            Monsters.vx[var0] = tick % 3 - 1;
            Monsters.vy[var0] = (tick >> 1) % 3 - 1;
        }

        int var10002 = Monsters.frame[var0]++;
        if (Monsters.frame[var0] > 1) {
            Monsters.frame[var0] = 0;
        }

        J(var0);
        if (tick % 5 == 0) {
            K(var0);
        }

    }

    private static void Q(int var0) {
        if (tick % 4 == 1) {
            int var10002 = Monsters.frame[var0]++;
            if (Monsters.frame[var0] > 1) {
                Monsters.frame[var0] = 0;
            }

            int[] var10000 = Monsters.x;
            var10000[var0] += Monsters.direction[var0] << 1;
            if (Monsters.x[var0] > Monsters.rangeB[var0]) {
                Monsters.direction[var0] = -1;
            } else if (Monsters.x[var0] < Monsters.rangeA[var0]) {
                Monsters.direction[var0] = 1;
            }
        }

        J(var0);
    }

    private static void R(int var0) {
        a(Monsters.damage[var0], -fm);
        Monsters.state[var0] = Monsters.STATE_ACTIVE;
        Monsters.timer[var0] = 0;
        Monsters.frame[var0] = 2;
    }

    private static void S(int var0) {
        int var10002 = Monsters.timer[var0]++;
        int[] var10000 = Monsters.x;
        var10000[var0] -= Monsters.direction[var0];
        if (Monsters.timer[var0] == 5) {
            Monsters.state[var0] = Monsters.STATE_ACTIVE;
        }

    }

    private static void T(int var0) {
        int var10002 = Monsters.timer[var0]++;
        int[] var10000 = Monsters.x;
        var10000[var0] -= Monsters.direction[var0];
        if (Monsters.timer[var0] == 3) {
            Monsters.state[var0] = Monsters.STATE_ACTIVE;
        }

    }

    private static void U(int var0) {
        if (tick % 4 == 0) {
            int var10002 = Monsters.frame[var0]++;
            if (Monsters.frame[var0] > 1) {
                Monsters.frame[var0] = 0;
            }

            int[] var10000 = Monsters.x;
            var10000[var0] += Monsters.direction[var0];
        }

        if (Monsters.x[var0] > Monsters.rangeB[var0]) {
            Monsters.direction[var0] = -1;
        } else if (Monsters.x[var0] < Monsters.rangeA[var0]) {
            Monsters.direction[var0] = 1;
        }

        J(var0);
        if (tick % 5 == 0) {
            K(var0);
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

    private static void setNextNpcMarker(int var0) {
        pendingMarkerMap = mapId;
        pendingMarkerNpc = var0;
        pendingMarkerX = npcX[var0];
        pendingMarkerY = npcY[var0] - 44;
    }

    public static void setupNpcs(int var0) {
        npcCount = 0;
        if (var0 == 0) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 0);
                Npc.loadSprite(npcSprites, 1);
                Npc.loadSprite(npcSprites, 2);
                Npc.loadSprite(npcSprites, 3);
                Npc.loadSprite(npcSprites, 4);
                Npc.loadSprite(npcSprites, 5);
            } catch (Exception var7) {
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

        if (var0 == 1) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 5);
            } catch (Exception var6) {
            }

            npcCount = 1;
            allocNpcs();
            placeNpc(0, 5, 54, 9);
        }

        if (var0 == 2) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 5);
            } catch (Exception var5) {
            }

            npcCount = 1;
            allocNpcs();
            placeNpc(0, 5, 23, 12);
        }

        if (var0 == 5) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 5);
            } catch (Exception var4) {
            }

            npcCount = 1;
            allocNpcs();
            placeNpc(0, 5, 12, 5);
        }

        if (var0 == 6) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 5);
            } catch (Exception var3) {
            }

            npcCount = 1;
            allocNpcs();
            placeNpc(0, 5, 14, 9);
        }

        if (var0 == 99 || var0 == 101) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 4);
            } catch (Exception var2) {
            }

            npcCount = 1;
            allocNpcs();
            placeNpc(0, 4, 3, 12);
        }

        if (var0 == 94) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 0);
                Npc.loadSprite(npcSprites, 4);
                Npc.loadSprite(npcSprites, 2);
            } catch (Exception var1) {
            }

            npcCount = 3;
            allocNpcs();
            placeNpc(0, 4, 2, 10);
            placeNpc(1, 0, 3, 10);
            placeNpc(2, 2, 4, 10);
        }

    }

    private static void placeNpc(int var0, int var1, int var2, int var3) {
        npcTypes[var0] = var1;
        npcX[var0] = var2 * GameMap.TILE_SIZE + 12;
        npcY[var0] = var3 * GameMap.TILE_SIZE;
    }

    private static void allocNpcs() {
        npcX = new int[npcCount];
        npcY = new int[npcCount];
        npcTypes = new int[npcCount];
    }

    private static void drawNpcs(Graphics var0) {
        for(int var1 = 0; var1 < npcCount; ++var1) {
            if (npcX[var1] + 10 >= cameraX && npcX[var1] - 10 <= cameraX + Viewport.viewWidth && npcY[var1] >= cameraY && npcY[var1] - 32 <= cameraY + Viewport.viewHeight) {
                var0.drawImage(npcSprites[npcTypes[var1]][Npc.PART_LEGS], npcX[var1], npcY[var1], 33);
                var0.drawImage(npcSprites[npcTypes[var1]][Npc.PART_BODY], npcX[var1], npcY[var1] - lF[npcTypes[var1]] + (tick % (15 + npcTypes[var1]) > 4 ? 0 : 1), 33);
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
                    fp = fe = WorldMap.spawnTileX[WorldMap.selected] * GameMap.TILE_SIZE;
                    fq = ff = WorldMap.spawnTileY[WorldMap.selected] * GameMap.TILE_SIZE;
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
