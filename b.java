import assets.*;
import assets.Character;
import core.app.*;
import core.input.*;
import core.item.*;
import core.map.*;
import core.player.*;
import core.quest.*;
import core.render.*;
import core.save.*;
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
    public static boolean lowGraphics = true;
    private static int frameDurationMs = 40;
    private static int[] ab = new int[]{150, 180, 180, 300, 350, 380, 500};
    private static int[] ac = new int[]{20, 30, 40, 100, 80, 120, 200};
    private static int[] ad = new int[]{150, 140, 120, 240, 280, 290, 300};
    private static int[] ae = new int[]{800, 1000, 1200, 3000, 3500, 4000, 6000};
    private static int[][] af = new int[][]{{-5, -2}, {-2, -4}, {0, -5}, {-6, -6}, {-5, -2}, {-2, -4}, {0, -5}};
    private static int[][] ag = new int[][]{{5, 8}, {3, 3}, new int[2], {4, 4}, {5, 8}, {3, 3}, new int[2]};
    private static int[] ah = new int[]{1, -2, 1, 1, 1, -2, 1};
    private static int[] am = new int[]{140, 70, 150, 120, 150, 150, 60, 1000, 250, 500, 4000, 500, 600, 1000, 1500, 2000, 1000, 3500, 1500, 5500};
    private static int[] an = new int[]{15, 10, 30, 25, 25, 25, 5, 10, 20, 40, 30, 40, 130, 30, 40, 100, 60, 160, 120, 200};
    private static int[] ao = new int[]{35, 20, 55, 40, 45, 50, 30, 130, 60, 65, 70, 75, 120, 85, 100, 120, 140, 160, 180, 200};
    private static int[] ap = new int[]{20, 10, 50, 25, 30, 40, 15, 50, 60, 70, 80, 100, 120, 130, 160, 180, 170, 210, 190, 220};
    private static int[][][] aq = new int[][][]{{{0, -13, 31}, {0, -7, 8}, {0, -11, 14}}, {{0, -13, 32}, {0, -7, 8}, {0, -11, 15}}, {{1, -11, 31}, {1, -9, 16}, {6, -1000, 0}}, {{2, -11, 32}, {2, -10, 16}, {6, -1000, 0}}, {{1, -11, 31}, {3, -8, 16}, {6, -1000, 0}}, {{2, -10, 32}, {7, -9, 17}, {6, -1000, 0}}, {{1, -10, 31}, {8, -8, 16}, {6, -1000, 0}}, {{0, -8, 35}, {4, -5, 15}, {2, -10, 21}}, {{0, -8, 34}, {5, -6, 13}, {3, -11, 22}}, {{0, -8, 31}, {6, -13, 9}, {4, -10, 18}}, {{1, -8, 30}, {6, -13, 9}, {5, -8, 17}}, {{2, -10, 30}, {6, -13, 9}, {6, -8, 16}}, {{2, -10, 29}, {6, -13, 9}, {7, -11, 17}}, {{3, -11, 33}, {8, -4, 13}, {8, -15, 25}}};
    private static int[][][] ar = new int[][][]{{{0, -10, 32}, {1, -7, 7}, {1, -11, 15}}, {{0, -10, 33}, {1, -7, 7}, {1, -11, 16}}, {{1, -10, 33}, {2, -10, 11}, {2, -9, 16}}, {{1, -10, 32}, {3, -11, 9}, {3, -11, 16}}, {{1, -10, 34}, {4, -9, 9}, {4, -8, 16}}, {{1, -10, 34}, {5, -11, 11}, {5, -10, 17}}, {{1, -10, 33}, {6, -9, 9}, {6, -8, 16}}, {{0, -9, 36}, {7, -5, 15}, {7, -10, 21}}, {{4, -13, 26}, new int[3], new int[3]}, {{5, -13, 25}, new int[3], new int[3]}, {{6, -12, 26}, new int[3], new int[3]}, {{7, -13, 25}, new int[3], new int[3]}, {{0, -9, 35}, {8, -4, 13}, {8, -14, 27}}, {{0, -9, 32}, {9, -11, 9}, {10, -10, 18}}, {{2, -7, 34}, {9, -11, 9}, {11, -8, 16}}, {{2, -8, 33}, {9, -11, 9}, {12, -8, 15}}, {{2, -7, 33}, {9, -11, 9}, {13, -12, 16}}, {{0, -11, 32}, {9, -11, 9}, {14, -15, 19}}, {{2, -9, 33}, {9, -11, 9}, {15, -13, 20}}, {{2, -9, 32}, {9, -11, 9}, {16, -7, 23}}, {{2, -9, 33}, {9, -11, 9}, {17, -11, 19}}, {{3, -12, 34}, {8, -4, 13}, {8, -15, 25}}, {{0, -10, 28}, new int[3], {19, -8, 12}}, {{0, -9, 35}, {8, -4, 13}, {9, -9, 22}}, {{0, -10, 33}, new int[3], new int[3]}, {{9, -4, 24}, new int[3], new int[3]}, {{10, -4, 22}, new int[3], new int[3]}, {{0, -9, 32}, {8, -4, 9}, {10, -10, 18}}, {{2, -7, 34}, {8, -4, 9}, {11, -8, 16}}, {{2, -8, 33}, {8, -4, 9}, {12, -8, 15}}, {{2, -7, 33}, {8, -4, 9}, {13, -12, 16}}, {{0, -11, 32}, {7, -5, 9}, {14, -15, 19}}, {{2, -9, 33}, {7, -5, 9}, {15, -13, 20}}, {{2, -9, 32}, {7, -5, 9}, {16, -7, 23}}, {{2, -9, 33}, {7, -5, 9}, {17, -11, 19}}, {{0, -9, 32}, {2, -10, 11}, {10, -10, 18}}, {{2, -7, 34}, {3, -11, 9}, {11, -8, 16}}, {{2, -8, 33}, {4, -9, 9}, {12, -8, 15}}, {{2, -7, 33}, {5, -11, 11}, {13, -12, 16}}, {{0, -11, 32}, {2, -10, 11}, {14, -15, 19}}, {{2, -9, 33}, {3, -11, 9}, {15, -13, 20}}, {{2, -9, 32}, {4, -9, 9}, {16, -7, 23}}, {{2, -9, 33}, {5, -11, 11}, {17, -11, 19}}, {{1, -8, 38}, new int[3], {18, -8, 21}}, {{1, -8, 39}, new int[3], {18, -8, 22}}, {{1, -10, 43}, new int[3], {18, -11, 26}}};
    private static int[] as = new int[]{-2, -6, 22, 21, 19, 22, 10, -2, -2, 5, 19};
    private static int[] at = new int[]{9, 22, 25, 17, 26, 37, 36, 49, 50, 52, 36};
    private static int[][] mapEventConditions = new int[][]{{2, 1, 1, 5, 3}, {2, 4, 3}, {2, 3}, {2, 1, 3}, {6, 3}, {2, 7, 3}, {2, 8, 3}, {2, 9, 3}, {2, 3}, {1, 3}, {2, 3}};
    private static int[][] mapEventCutscenes = new int[][]{{0, 1, 2, 5, 0}, {3, 4, 0}, {6, 0}, {7, 8, 0}, {9, 0}, {10, 11, 0}, {12, 13, 0}, {14, 15, 0}, {16, 0}, {17, 0}, {18, 0}};
    private static int[][] mapEventParams = new int[][]{{0, 360, 1128, 0, 0}, new int[3], new int[2], {0, 2784, 0}, {1248, 0}, new int[3], new int[3], new int[3], new int[2], new int[2], new int[2]};
    private static int[][] ax = new int[][]{new int[5], new int[3], new int[2], new int[3], {408, 0}, new int[3], new int[3], new int[3], new int[2], new int[2], new int[2]};
    private static int[][] ay = new int[][]{{15, 1, 1, 2, 2, 1, 2, 4, 5, 3}, {6, 15, 1, 2, 1, 2, 1, 2, 1, 2, 4, 5, 3}, {6, 1, 2, 8, 11, 4, 7, 1, 2, 1, 2, 3}, {8, 9, 15, 12, 14, 10, 4, 16, 5, 1, 2, 3}, {7, 17}, {4, 1, 2, 17}, {8, 9, 15, 12, 14, 10, 4, 18, 4, 7, 3}, {1, 2, 4, 5, 3}, {6, 4, 19, 3}, {4, 20, 21, 3}, {4, 3}, {4, 22, 3}, {23, 4, 7, 4, 3}, {23, 25, 4, 7, 24, 26, 3}, {27, 4, 7, 4, 28, 3}, {4, 31, 32, 7, 4, 31, 4, 7, 4, 8, 17, 3}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 29, 3}, {4, 30, 3}, {15, 31, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 33, 17, 3}};
    private static int[][] cutsceneScripts = new int[][]{{1, -4, -1, -1, -4, -4, -4, 0, 20000, 0}, {0, 2, -4, -4, -5, -5, -5, -5, -5, -5, 1, 20000, 0}, {0, -5, -5, 0, 0, 2, 0, 55, 55, -4, -4, 0}, {0, 1, 0, 2, 3, 4, 3, 432, 20000, -4, -4, 0}, {0, 4}, {4, 55, 55, 0}, {0, 5, 3, 6, 7, 8, 5, 2, 6, 0, 0}, {-4, -4, 7, 20000, 0}, {0, 8, 0, 0}, {9, 0, 0, 0}, {10, 0}, {11, 0, 0}, {0, 12, 0, 13, 0}, {0, 0, 14, 0, 600, 0, 0}, {0, 15, 0, 16, 0, 0}, {17, 0, 0, 0, 55, 0, 56, 0, 57, 0, 94, 0}, {18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 0, 0}, {54, 0, 0}, {4, 0, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 0, 0, 0}};
    private static int[][] aA = new int[][]{{5, 26, 30, 33, 41, 48, 53, 57, 58, 60}, {5, 6, 8, 9, 18, 20, 30, 32, 39, 40, 60, 63, 67}, {5, 6, 7, 13, 14, 19, 21, 34, 35, 60, 79, 80}, {1, 2, 3, 20, 50, 80, 85, 90, 92, 93, 95, 0}, {5, 6}, {10, 12, 13, 50}, {1, 2, 3, 4, 5, 6, 17, 18, 19, 20, 21}, {5, 10, 20, 30, 32}, {5, 10, 15, 20}, {10, 15, 20, 25}, {20, 25}, {10, 12, 14}, {15, 30, 35, 45, 55}, {10, 12, 30, 40, 45, 50, 75}, {15, 30, 35, 45, 53, 55}, {10, 12, 13, 40, 50, 60, 70, 80, 90, 95, 100, 105}, {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47}, {10, 12, 14}, {10, 11, 60, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74}};
    private static int[][] aB = new int[][]{{1344, 288}, {816, 48}, {816, 192}, {744, 192}, {144, 264}, {1440, 96}, {1368, 288}, {1128, 288}, {840, 312}};
    private static int[] aC = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 1, 0, 4, 0, 3, 0, 6, 0, 2, 0, 5, 0, 4, 0, 1, 0, 3, 0, 0, 0, 5, 0, 4, 0, 3, 0, 2, 0, 2, 0, 1, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 2, 1, 3, 2, 2};
    private static int[] aD = new int[]{0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 2, 0, 2, 3, 0, 0, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 4, 0, 4, 0, 1, 2, 1, 2, 0, 1, 2, 1, 2, 1, 2};
    private static int[][][] aI;
    private static int[] aJ;
    private static int[] aK;
    private static int aL;
    private static b instance;
    static boolean b;
    private static int screenWidth;
    private static int screenHeight;
    private static int screenCenterX;
    private static int screenCenterY;
    private static int aX;
    public static boolean hudDirty;
    private static int aY;
    private static int aZ;
    private static int ba;
    private static int bb;
    private static int bc;
    private static int bd;
    public static int state;
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
    private static String bv;
    private static int firstVisibleCol;
    private static int firstVisibleRow;
    private static int visibleCols;
    private static int visibleRows;
    private static int lastVisibleCol;
    private static int lastVisibleRow;
    public static int viewWidth;
    public static int viewHeight;
    private static int viewCenterX;
    private static int viewCenterY;
    private static int cameraOffsetY;
    private static int cameraLeadX;
    private static int bG;
    private static int bH;
    private static int bI;
    private static int bJ;
    private static int bK;
    private static int bL;
    private static int bM;
    private static int[] bN;
    private static int[] bO;
    private static int[] bP;
    private static int[] bQ;
    private static int[] bR;
    private static int[] bS;
    private static int bT;
    private static int[] bU;
    private static int[] bV;
    private static int[] bW;
    private static int[] bX;
    private static int[] bY;
    private static int[] bZ;
    private static int[] ca;
    private static int[] cb;
    private static int[] cc;
    private static int[] cd;
    private static int[] ce;
    private static int[] cf;
    private static int[] cg;
    private static int[] ch;
    private static int[] ci;
    private static int[] cj;
    private static int[] ck;
    private static int[] cl;
    private static boolean noticeVisible;
    private static int noticeTimer;
    private static String noticeText;
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
    private static int cM;
    private static int[] cN;
    private static int[] cO;
    private static int[] cP;
    private static int[] cQ;
    private static int[] cR;
    private static int[] cS;
    private static int[] cT;
    private static int[] cU;
    private static int cV;
    private static int[] cW;
    private static int[] cX;
    private static int[] cY;
    private static int[] cZ;
    private static int[] da;
    private static int[] db;
    private static boolean[] dc;
    private static int de;
    private static int[] df;
    private static int[] dg;
    private static int[] dh;
    private static int[] di;
    private static int[] dj;
    private static int[] dk;
    private static int[] dl;
    private static int[] dm;
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
    private static String[] dO;
    private static int[] dP;
    private static int[] dQ;
    private static int[] dR;
    private static int[] dS;
    private static int[] dT;
    private static int[] dU;
    private static int dV;
    private static int dW;
    private static int dX;
    private static Image[][] enemyProjectiles;
    private static Image[] arrowIcons;
    private static Image healSprite;
    private static Image[] motorbikeFrames;
    private static Image itemScroll;
    private static Image[] heroBodies;
    private static Image itemCrate;
    private static Image[] hudFrames;
    private static Image[] itemMoney;
    private static Image markerIcon;
    private static Image[] npcDog;
    private static Image[][] dustSprites;
    private static Image[] dartFrames;
    private static Image[] girlBodies;
    private static Image[] girlHeads;
    private static Image[] girlLegs;
    private static Image[] heroHeads;
    private static Image[] heroTransformHeads;
    private static Image[] heroLegs;
    private static Image[] auraSprites;
    private static Image[][] classmateHeads;
    private static Image[][] ninjaBodies;
    private static Image[] ninjaLegs;
    private static Image[] boss0Sprites;
    private static Image[] itemPotions;
    private static Image[] boss1Sprites;
    private static Image[] bloodSprites;
    private static Image[] boss2Sprites;
    private static Image titleImage;
    private static Image[] slashEffects;
    private static Image[] weaponIcons;
    private static Image[] waterSplashSprites;
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
    private static int gL;
    private static long gM;
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
    private static Image logoImage;
    private static int jD;
    private static int jE;
    public static boolean U;
    private static Image[][][] monsterSprites;
    private static int jG;
    private static int jH;
    private static int[] jI;
    private static int[] jJ;
    private static int[] jK;
    private static int[] jL;
    private static int[] jM;
    private static int[] jN;
    private static int[] jO;
    private static int[] jP;
    private static int[] jQ;
    private static int[] jR;
    private static int[] jS;
    private static int[] jT;
    private static int[] jU;
    private static int[] jV;
    private static int[] jW;
    private static int[] jX;
    private static int[] jY;
    private static int[] jZ;
    private static int[] ka;
    private static int[] kb;
    private static int[] kc;
    private static int[] kd;
    private static int[] ke;
    private static int[] kf;
    private static int[] kg;
    private static boolean[] kh;
    private static int[][] ki;
    private static int[][] kj;
    private static boolean[][] kk;
    private static int[] kl;
    static int shopY;
    private static int shopX;
    private static int shopCursor;
    private static int shopBlink;
    private static int shopRow;
    /** Cửa hàng mua bằng tiền trong game. */
    private static final int SHOP_MODE_MONEY = 0;
    /** Cửa hàng tiền thật (SMS), giờ chỉ báo mua thất bại. */
    private static final int SHOP_MODE_SMS = 1;
    private static int shopMode;
    private static int[] shopBlinkColors;
    private static Image[] shopSwordIcons;
    private static int inventoryY;
    private static int inventoryX;
    private static int inventoryCursor;
    private static int inventoryBlink;
    private static int[] inventoryBlinkColors;
    private static Image[] cornerIcons;
    private static int gameMenuY;
    private static int gameMenuSelection;
    private static int kJ;
    private static String[] gameMenuItems;
    private static String[] kL;
    private static int[] travelCosts;
    public static boolean[] X;
    private static int[] kN;
    private static int[] kO;
    private static int[] kP;
    private static int[] kQ;
    private static Image worldMapImage;
    private static Image crossIcon;
    private static int kT;
    private static int kU;
    private static int worldMapCursor;
    private static int kW;
    private static int kX;
    private static int kY;
    private static int kZ;
    private static int la;
    private static int lb;
    private static int lc;
    private static int ld;
    private static int le;
    private static int lf;
    private static int lg;
    private static int worldMapCurrent;
    private static String li;
    private static Image popupImage;
    private static boolean lk;
    private static int ll;
    public static int messageY;
    private static int messageType;
    private static int travelCost;
    private static int lo;
    private static String messageLeftLabel;
    private static String messageRightLabel;
    private static String[] messageLines;
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
        bv = "";
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
        kl = null;
        shopBlinkColors = new int[]{12281360, 13859359, 15439166, 16693877, 16765346, 16777215, 16765346, 16693877, 15439166, 13859359, 12281360};
        inventoryBlinkColors = new int[]{12281360, 13859359, 15439166, 16693877, 16765346, 16777215, 16765346, 16693877, 15439166, 13859359, 12281360};
        gameMenuItems = new String[]{"Sử dụng item", 
                "Xem bản đồ", 
                "Lưu và thoát", 
                "Nhạc: Bật"};
        kL = new String[]{"Trường Hirosaki", 
                "Khu luyện tập", 
                "Làng Kenshin", 
                "Rừng đào Sakura", 
                "Rừng trúc Ura", 
                "Thác Kitajima", 
                "Cánh đồng Mishima", 
                "Sông Watamaro", 
                "Nghĩa địa Izuko", 
                "Miếu Kojin", 
                "Miếu Kamo", 
                "Miếu Yazaka", 
                "Rừng gỗ Kouji", 
                "Rừng chông Hakata", 
                "Vách đá Ito", 
                "Thung lũng Taira", 
                "Thôn Sanzu", 
                "Lối vào Orochi", 
                "Ngôi đền Orochi"};
        travelCosts = new int[]{50, 60, 70, 80, 90, 70, 100, 120, 150, 180, 220, 250, 300, 320, 350, 370, 390, 420, 450};
        X = new boolean[19];
        kN = new int[]{49, 31, 98, 112, 106, 42, 75, 84, 37, 39, 80, 117, 157, 201, 208, 170, 186, 219, 161};
        kO = new int[]{35, 65, 38, 75, 98, 97, 95, 128, 142, 185, 212, 189, 189, 204, 156, 142, 96, 59, 39};
        kP = new int[]{62, 52, 17, 4, 8, 12, 14, 5, 1, 2, 3, 2, 2, 2, 2, 1, 2, 3, 1};
        kQ = new int[]{17, 9, 12, 9, 10, 5, 9, 7, 12, 11, 4, 11, 8, 9, 7, 6, 11, 16, 8};
        li = "Chuyển đến";
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
        if (gM > 0L) {
            gL += (int)((System.currentTimeMillis() - gM) / 1000L);
        }

        gM = System.currentTimeMillis();
        ++var2;
        var1[0] = gL;
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
        for(var0 = 0; var0 < X.length; ++var0) {
            var1[var2++] = X[var0] ? 1 : 0;
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
        gL = var0[0];
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
        for(var1 = 0; var1 < X.length; ++var1) {
            X[var1] = var0[var2++] == 1;
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

    private static void a(int var0, int var1, int var2, int var3, int var4) {
        int var5;
        for(var5 = 0; var5 < de && dh[var5] != 0; ++var5) {
        }

        if (var5 != de) {
            df[var5] = var0;
            dg[var5] = var1;
            di[var5] = var2;
            dj[var5] = var3;
            dh[var5] = 1;
            dl[var5] = var4;
            if (var4 < 160) {
                dm[var5] = Item.MONEY_GOLD;
            } else if (var4 < 600) {
                dm[var5] = Item.MONEY_BLUE_GEM;
            } else {
                dm[var5] = Item.MONEY_RED_GEM;
            }
        }
    }

    private static void a(int var0, int var1, int var2, int var3, int var4, int var5) {
        for(var3 = 0; var3 < cM && cQ[var3] != 0; ++var3) {
        }

        if (var3 != cM) {
            cO[var3] = var0;
            cP[var3] = var1;
            cR[var3] = var2;
            cS[var3] = -5;
            cQ[var3] = 1;
            cU[var3] = var4;
            cN[var3] = var5;
            if (var4 < 100) {
                cT[var3] = 0;
            } else if (var4 < 200) {
                cT[var3] = 1;
            } else {
                cT[var3] = 2;
            }
        }
    }

    private static void gainExp(int amount) {
        if (Leveling.addExp(amount)) {
            a("level-up", 2, fe, ff - fh, 0, -2);
            showMessage(MessageType.LEVEL_UP);
            setMessageText("Chúc mừng! Bạn đạt được cấp " + PlayerStats.level + '\n' + "HP: " + PlayerStats.maxHp + '\n' + "MP: " + PlayerStats.maxMp + '\n' + "Sức đánh: " + PlayerStats.getDisplayedAttack(Inventory.swordLevel));
            state = GameState.MESSAGE;
        } else {
            a("+" + amount, 2, fe, ff - fh, 0, -2);
        }

        hudDirty = true;
    }

    private static void u() {
        if (QuestState.current == Quest.AIR_HITS && A != 0) {
            ++QuestState.progress;
            if (QuestState.current >= 20) {
                showNotice("Thành công 20 hit!");
            } else {
                showNotice("Hit: " + QuestState.progress);
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
                f(jJ[var1], jK[var1] - (jL[var1] >> 1), fm);
                a("-" + var0, 0, jJ[var1], jK[var1] - jL[var1], 0, -2);
                if (A > 0) {
                    A = -6;
                }

                Q();
            }

            for(var1 = 0; var1 < iq; ++var1) {
                if (a(ff - fj - it[var1]) < 20 && a(fe + (fm << 4) - is[var1]) < 24) {
                    iu[var1] = 2;
                    iv[var1] = fm << 3;
                    f(is[var1], it[var1] - 12, fm);
                    Q();
                    break;
                }
            }

            if (gN && a(ff - gQ) < 30 && a(fe + (fm << 4) - gP) < 40) {
                if (!c(var0, -fm)) {
                    f(fe + (gP - fe >> 1), ff - 12, fm);
                    a("-" + var0, 0, gP, gQ - gW, 0, -2);
                }

                Q();
            }

            for(var1 = 0; var1 < cp; ++var1) {
                if (a(ff - cv[var1]) <= 12 && a(fe + (fm << 4) - cu[var1]) < 24) {
                    a(var1, var0, -fm);
                    f(cu[var1], cv[var1] - 15, fm);
                    a("-" + var0, 0, cu[var1], cv[var1] - 28, 0, -2);
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
                    a(hP, hQ, 4, -10, 600);
                    a(hP, hQ, -4, -17, 600);
                    a(hP, hQ, -3, -18, 600);
                    a(hP, hQ, 4, -19, 600);
                    a(hP, hQ, 0, -10, 600);
                    var10000 = false;
                } else {
                    ic = 50;
                    var10000 = false;
                }

                f(fe + (hP - fe >> 1), ff - 12, fm);
                a("-" + var0, 0, hP, hQ - hL, 0, -2);
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
                        a(hp, hq, 4, -10, 500);
                        a(hp, hq, -4, -17, 500);
                        a(hp, hq, -3, -18, 500);
                        a(hp, hq, 4, -19, 500);
                        a(hp, hq, 0, -10, 500);
                        var10000 = false;
                    } else {
                        ho = 50;
                        f(hp, hq - 47, fm);
                        a("-" + var0, 0, hp, hq - 55, 0, -2);
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
                if (var5 >= jG) {
                    var10000 = -1;
                    break;
                }

                if (jO[var5] != 5 && jJ[var5] >= var1 && jJ[var5] <= var2 && jK[var5] >= var3 && jK[var5] <= var4) {
                    var10000 = var5;
                    break;
                }

                ++var5;
            }

            var1 = var10000;
            if (var10000 != -1) {
                k(var1, var0, -gz);
                f(jJ[var1], jK[var1] - (jL[var1] >> 1), gz);
                a("-" + var0, 0, jJ[var1], jK[var1] - jL[var1], 0, -2);
            }

            if (gN && a(go - gQ) < 30 && a(gn + (gz << 4) - gP) < 40 && !c(var0, -gz)) {
                f(gn + (gP - gn >> 1), go - 12, gz);
                a("-" + var0, 0, gP, gQ - gW, 0, -2);
            }

            for(var1 = 0; var1 < cp; ++var1) {
                if (go == cv[var1] && a(gn + (gz << 4) - cu[var1]) < 24) {
                    a(var1, var0, -gz);
                    f(cu[var1], cv[var1] - 15, gz);
                    a("-" + var0, 0, cu[var1], cv[var1] - 28, 0, -2);
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
                    g(is[var0], it[var0] - 10);
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
                showNotice("NV: Giết 10 ốc sên");
                break;
            case QuestAction.REWARD_LIGHTNESS:
                Skills.lightness = true;
                Skills.doubleJump = true;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                showNotice("đã học phép khinh công");
                break;
            case QuestAction.START_KNOCK_DOWN_SCARECROWS:
                QuestState.current = Quest.KNOCK_DOWN_SCARECROWS;
                QuestState.progress = 0;
                showNotice("NV: đánh ngã 2 bù nhìn");
                break;
            case QuestAction.REWARD_DARTS:
                Skills.darts = true;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                showNotice("đã học ném phi tiêu");
            case QuestAction.UNUSED_7:
            default:
                break;
            case QuestAction.UNUSED_CLEAR_NOTICE:
                showNotice((String)null);
                break;
            case QuestAction.START_FIND_BURROW_SCROLL:
                QuestState.current = Quest.FIND_BURROW_SCROLL;
                QuestState.dropMonster = QuestState.DROP_MONSTER_BASE + 3;
                QuestState.dropChance = 2;
                QuestState.itemFound = false;
                showNotice("NV: Tìm bí kíp");
                break;
            case QuestAction.REWARD_BURROW:
                Skills.burrow = true;
                QuestState.current = Quest.NONE;
                QuestState.dropMonster = 0;
                QuestState.dropChance = 0;
                QuestState.itemFound = false;
                showNotice("đã học thuật độn thổ");
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
                showNotice("sức công nhân đôi!");
                break;
            case QuestAction.START_KILL_BEES:
                QuestState.current = Quest.KILL_BEES;
                QuestState.progress = 0;
                showNotice("NV: giết 20 con ong");
                break;
            case QuestAction.START_KILL_WILD_BOAR:
                QuestState.current = Quest.KILL_WILD_BOAR;
                QuestState.itemFound = false;
                QuestState.progress = 0;
                QuestState.dropChance = 10;
                showNotice("NV: giết heo rừng");
                break;
            case QuestAction.REWARD_WATER_SKILLS:
                QuestState.current = Quest.NONE;
                Skills.dive = true;
                Skills.runOnWater = true;
                // Thưởng gồm cả lặn nước (Skills.dive) nhưng thông báo chỉ ghi chạy nước (giữ như bản gốc).
                showNotice("đã học chạy nước.");
                break;
            case QuestAction.START_CROSS_RIVER:
                QuestState.current = Quest.CROSS_RIVER;
                QuestState.itemFound = false;
                QuestState.dropChance = 10;
                showNotice("NV: vượt sông");
                break;
            case QuestAction.REWARD_TRANSFORM:
                QuestState.current = Quest.NONE;
                Skills.transform = true;
                showNotice("đã học biến hình.");
                break;
            case QuestAction.START_KILL_GOLDEN_BATS:
                QuestState.current = Quest.KILL_GOLDEN_BATS;
                QuestState.progress = 0;
                showNotice("NV: giết 8 dơi vàng");
                break;
            case QuestAction.REWARD_MONEY:
                Story.goldenBatQuestDone = true;
                Inventory.money += 5000;
                hudDirty = true;
                QuestState.current = Quest.NONE;
                QuestState.dropMonster = 0;
                QuestState.dropChance = 0;
                QuestState.itemFound = false;
                showNotice("Nhận được tiền 5000$");
                break;
            case QuestAction.START_FIND_HIDE_SCROLL:
                QuestState.current = Quest.FIND_HIDE_SCROLL;
                QuestState.progress = 0;
                QuestState.itemFound = false;
                showNotice("NV: tìm bí kíp");
                break;
            case QuestAction.REWARD_HIDE:
                Skills.hide = true;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                QuestState.itemFound = false;
                showNotice("đã có thể ẩn thân");
                break;
            case QuestAction.START_FIGHT_TONIKU:
                QuestState.current = Quest.FIGHT_TONIKU;
                showNotice("NV: tìm đánh Toniku");
                break;
            case QuestAction.COMPLETE_FIGHT_TONIKU:
                QuestState.current = Quest.NONE;
                Story.tonikuQuestDone = true;
                showNotice("Hoàn thành nhiệm vụ.");
                break;
            case QuestAction.START_KILL_NATIVES:
                QuestState.current = Quest.KILL_NATIVES;
                QuestState.progress = 0;
                showNotice("NV: giết 10 thổ dân");
                break;
            case QuestAction.REWARD_MEET_GIRL:
                Story.girlWaiting = true;
                QuestState.current = Quest.NONE;
                QuestState.dropMonster = 0;
                QuestState.dropChance = 0;
                QuestState.itemFound = false;
                showNotice("đến gặp cô bé!");
                break;
            case QuestAction.START_GIRL_OUTING:
                QuestState.current = Quest.GIRL_OUTING;
                QuestState.progress = 0;
                gk = true;
                showNotice("NV: dẫn cô bé đi chơi");
                break;
            case QuestAction.REWARD_TRIPLE_JUMP:
                QuestState.current = Quest.NONE;
                Skills.tripleJump = true;
                QuestState.progress = 0;
                showNotice("Học khinh công cấp 2");
                break;
            case QuestAction.START_AIR_HITS:
                QuestState.current = Quest.AIR_HITS;
                QuestState.progress = 0;
                showNotice("NV: 20 hit trên không");
                break;
            case QuestAction.REWARD_DARTS_LEVEL_2:
                Skills.dartsLevel2 = true;
                PlayerStats.dartDamage <<= 2;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                showNotice("đã học phi tiêu cấp 2");
                Effect.loadDarts(dartFrames, Effect.DART_LEVEL_2);
                break label58;
            case QuestAction.START_KILL_FLOWER_DEMONS:
                QuestState.current = Quest.KILL_FLOWER_DEMONS;
                QuestState.progress = 0;
                // Mục tiêu thực tế là "Quỷ hoa" (Quest.TARGET_MONSTER = 18), thông báo ghi "quỷ hoa đỏ" (giữ như bản gốc).
                showNotice("NV: đánh 15 quỷ hoa đỏ");
                break;
            case QuestAction.REWARD_SPRINT:
                Skills.sprint = true;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                showNotice("đã có thể chạy nhanh");
                break;
            case QuestAction.START_KILL_SCORPIONS:
                QuestState.current = Quest.KILL_SCORPIONS;
                QuestState.progress = 0;
                showNotice("NV: Giết 18 bò cạp");
                break;
            case QuestAction.REWARD_STAND_ON_WATER:
                Skills.standOnWater = true;
                QuestState.current = Quest.NONE;
                QuestState.progress = 0;
                showNotice("đã có thể đứng nước");
                break;
            case QuestAction.START_KILL_RED_TIGER_FISH:
                QuestState.current = Quest.KILL_RED_TIGER_FISH;
                QuestState.progress = 0;
                showNotice("NV: Giết 4 cá đỏ");
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
                showNotice("NV: cứu Sakura");
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

        for(int var4 = 0; var4 < jG; ++var4) {
            if (jO[var4] != 5 && jJ[var4] >= var0 && jJ[var4] <= var1 && jK[var4] >= var2 && jK[var4] <= var3) {
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
        monsterSprites = null;
        System.gc();
        C();
        classmateHeads = null;
        npcDog = null;
        enemyProjectiles = null;
        System.gc();
    }

    private static void C() {
        ninjaLegs = null;
        ninjaBodies = null;
        System.gc();
    }

    private static void D() {
        int var0;
        for(var0 = 0; var0 < jG; ++var0) {
            if (jO[var0] != 5 && a(jK[var0] - go) < 20 && a(jJ[var0] - gn) < 40) {
                gz = jJ[var0] > gn ? 1 : -1;
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
            if (gn >= cameraX - 24 && gn <= cameraX + viewWidth + 24) {
                if (go < cameraY - 30 || go > cameraY + viewHeight + 30) {
                    gz = fe + (fm << 5) > gn ? 1 : -1;
                    var0 = fe;
                    var1 = ff > go ? cameraY - 20 : cameraY + viewHeight;
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
                var0 = fe > gn ? cameraX - 24 : cameraX + viewWidth + 24;
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

        f(fe, ff - 12, -fm);
        a("-" + var0, 0, fe, ff - fh, 0, -2);
    }

    private static void b(int var0, int var1) {
        ig = -1;
        gp = var1 << 2;
        gq = -2;
        gA = 0;
        gB = 0;
        gr = 7;
        f(gn, go - 12, -gz);
        a("-" + var0, 0, gn, go - gu, 0, -2);
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
                gainExp(ad[cD[var0]]);
                a(cu[var0], cv[var0], -(cH[var0] << 2), -4, ab[cD[var0]] + tick % 10);
                if (tick % 20 > 5) {
                    a(cu[var0], cv[var0], -(cH[var0] << 3), -5, ac[cD[var0]] << 2, (tick + 1) % 2);
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
                a(gP, gQ, 4, -6, 50);
                a(gP, gQ, -5, -7, 50);
                a(gP, gQ, -4, -8, 50);
                a(gP, gQ, 5, -9, 50);
                a(gP, gQ, 0, -10, 50);
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
            f(dz[0], dA[0] - 12, fm);
            a("-" + var0, 0, dz[0], dA[0] - 32, 0, -2);
        }
    }

    public static final void b(int var0) {
        bM = 0;
        if (var0 == 0) {
            bM = 0;
        } else {
            enemyProjectiles = Effect.ensureProjectiles(enemyProjectiles);

            bM = 10;
            bN = new int[10];
            bO = new int[bM];
            bP = new int[bM];
            bQ = new int[bM];
            bR = new int[bM];
            bS = new int[bM];

            for(var0 = 0; var0 < bM; ++var0) {
                bP[var0] = -1;
            }

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

    public static void f() {
        bT = 8;
        bU = new int[8];
        bV = new int[bT];
        bW = new int[bT];
        bX = new int[bT];
        bY = new int[bT];
        bZ = new int[bT];

        try {
            healSprite = Effect.loadHeal();
        } catch (Exception var0) {
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
        viewWidth = screenWidth;
        if (screenHeight >= 208) {
            hudPanelVisible = true;
            hudHeight = 50;
            viewHeight = screenHeight - hudHeight;
            hudWidth = viewWidth;
            hudX = 0;
            hudY = viewHeight + 1;
        } else {
            hudHeight = 16;
            viewHeight = screenHeight - hudHeight;
            hudPanelVisible = false;
            hudHeight = 0;
            hudWidth = viewWidth;
            hudX = 0;
            hudY = viewHeight + 1;
            hudLevelX = (hudBarMaxWidth = viewWidth / 3) + 29;
            hudMoneyX = hudBarMaxWidth + 50;
        }

        if ((letterboxMaxHeight = screenHeight / 6) < 48) {
            letterboxMaxHeight = 48;
        }

        viewCenterX = viewWidth >> 1;
        viewCenterY = viewHeight >> 1;
        cameraOffsetY = 3 * viewHeight / 4;
        cameraLeadX = viewWidth / 6;
        visibleCols = viewWidth / GameMap.TILE_SIZE + 2;
        visibleRows = viewHeight / GameMap.TILE_SIZE + 2;
        if (viewWidth % 24 != 0) {
            ++visibleCols;
        }

        cameraMaxX = (GameMap.width - 1) * GameMap.TILE_SIZE - viewWidth;
        cameraMaxY = (GameMap.height - 1) * GameMap.TILE_SIZE - viewHeight;
        cameraX = cameraTargetX = fe - viewCenterX + cameraLeadX * fm;
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
                hudStripWidth = viewWidth - hudLeftFrameWidth - hudRightFrameWidth + 1;
                hudBarX = 78;
                hudBarY = hudY + 7;
                hudBarMaxWidth = viewWidth - 134;
                hudExpBarMaxWidth = viewWidth - 84;
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

    public static void k() {
        de = 5;
        try {
            itemMoney = Item.ensureMoney(itemMoney);
        } catch (Exception var1) {
        }

        df = new int[de];
        dg = new int[de];
        dh = new int[de];
        di = new int[de];
        dj = new int[de];
        dk = new int[de];
        dm = new int[de];
        dl = new int[de];
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

    public static final void l() {
        if (!lowGraphics) {
            if (dustSprites == null) {
                dustSprites = new Image[2][5];

                for(int var0 = 0; var0 < 2; ++var0) {
                    for(int var1 = 0; var1 < 5; ++var1) {
                        try {
                            dustSprites[var0][var1] = Effect.loadDust(var0, var1);
                        } catch (IOException var2) {
                        }
                    }
                }
            }

            cj = new int[2];
            ck = new int[2];
            (cl = new int[2])[0] = cl[1] = -1;
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

    public static final void n() {
        dP = new int[5];
        dQ = new int[5];
        dR = new int[5];
        dS = new int[5];
        dT = new int[5];
        dU = new int[5];
        dO = new String[5];

        for(int var0 = 0; var0 < 5; ++var0) {
            dT[var0] = -1;
        }

    }

    private static void changeMap(int var0) {
        state = GameState.LOADING;
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

    public static void o() {
        if (auraSprites == null) {
            auraSprites = new Image[2];

            for(int var0 = 0; var0 < 2; ++var0) {
                try {
                    auraSprites[var0] = Effect.loadAura(var0);
                } catch (IOException var1) {
                }
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
        state = GameState.LOADING;
        resetLoadingProgress();
        Midlet.nextGameState = GameState.MAIN_MENU;
        mapId = 99;

        try {
            titleImage = Ui.loadTitle();
        } catch (Exception var1) {
        }

        bc = screenHeight - 30;
        bd = screenHeight;
        aY = 30;
        aZ = screenHeight - 50;
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
        cr[var0] = ae[cD[var0]];
    }

    public static void i(int var0) {
        ha = 1200;
        gZ = 1200;
        gO = 30;
        gN = false;
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

    public static void p() {
        cM = 5;
        try {
            itemPotions = Item.ensurePotions(itemPotions);
        } catch (Exception var0) {
        }

        cO = new int[cM];
        cP = new int[cM];
        cQ = new int[cM];
        cR = new int[cM];
        cS = new int[cM];
        cT = new int[cM];
        cU = new int[cM];
        cN = new int[cM];
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

    public static final void q() {
        if (bloodSprites == null) {
            bloodSprites = new Image[3];

            for(int var0 = 0; var0 < 3; ++var0) {
                try {
                    bloodSprites[var0] = Effect.loadBlood(var0);
                } catch (IOException var1) {
                }
            }
        }

        ca = new int[2];
        cb = new int[2];
        cc = new int[2];
        cd = new int[2];
        ce = new int[2];
        cc[0] = cc[1] = -1;
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
                X[var1] = false;
            }

            X[0] = true;
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
            cameraY = cameraTargetY = 336 - viewHeight;
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

    public static final void r() {
        if (!lowGraphics) {
            waterSplashSprites = new Image[3];

            for(int var0 = 0; var0 < 3; ++var0) {
                try {
                    waterSplashSprites[var0] = Effect.loadWaterSplash(var0);
                } catch (IOException var1) {
                }
            }

            cf = new int[2];
            cg = new int[2];
            ch = new int[2];
            ci = new int[2];
            ch[0] = ch[1] = -1;
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
            if (jO[var2] == 2 || jO[var2] == 3 || jO[var2] == 4) {
                int[] var5 = jY;
                var5[var2] -= var1;
                if (jY[var2] <= 0) {
                    jY[var2] = 0;
                    jT[var2] = -5;
                    jU[var2] = -(var4 << 3);
                    jV[var2] = 0;
                    jN[var2] = 0;
                    jO[var2] = 1;
                    var6 = true;
                    break label29;
                }

                jT[var2] = 0;
                jX[var2] = var4;
                jO[var2] = 6;
                jW[var2] = 3;
            }

            var6 = false;
        }

        if (!var6) {
            f(jJ[dL[var0]], jK[dL[var0]] - (jL[dL[var0]] >> 1), jX[dL[var0]]);
            a("-" + var1, 0, jJ[dL[var0]], jK[dL[var0]] - jL[dL[var0]], 0, -2);
        }

    }

    private static void B(int var0) {
        for(int var1 = 0; var1 < jG; ++var1) {
            if (jO[var1] != 5 && jK[var1] == dA[var0] && a(dz[var0] + (dJ[var0] << 3) - jJ[var1]) < 20) {
                if (jJ[var1] < dz[var0]) {
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
        Sky.draw(g, cameraX, viewWidth, viewHeight, lowGraphics);
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
        var0.setClip(-1, -1, screenWidth + 2, screenHeight + 2);
        var0.setColor(0);
        var0.fillRect(0, 0, viewWidth, letterboxHeight);
        if (hudPanelVisible) {
            var0.fillRect(0, screenHeight - letterboxHeight + 1, viewWidth, letterboxHeight);
        } else {
            var0.fillRect(0, screenHeight - 13, screenWidth, 13);
        }

        if (captionVisible) {
            for(int var1 = 0; var1 < captionLines.length; ++var1) {
                FontGraph.drawBold(var0, captionLines[var1], viewCenterX, captionY + (var1 << 4), 2);
            }
        }

    }

    private static void drawDialogue(Graphics var0) {
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        var0.setClip(-1, -1, screenWidth + 2, screenHeight + 2);
        dialogueBoxLines = Dialogue.pages[Dialogue.page].length;
        int var1 = Dialogue.speakerY - cameraY - (dialogueBoxLines << 4);
        boolean var2 = true;
        if (var1 < 10) {
            var2 = false;
            var1 = 10;
        }

        var0.setColor(16777215);
        if (lowGraphics) {
            var0.fillRect(Dialogue.boxX, var1 - 5, Dialogue.boxWidth, (dialogueBoxLines << 4) + 7);
        } else {
            var0.fillRoundRect(Dialogue.boxX, var1 - 5, Dialogue.boxWidth, (dialogueBoxLines << 4) + 7, 10, 10);
        }

        var0.setColor(17408);
        if (lowGraphics) {
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
        if (var2 && Dialogue.speakerX > cameraX + 10 && Dialogue.speakerX < cameraX + viewWidth - 10) {
            var0.drawImage(markerIcon, Dialogue.speakerX - cameraX, Dialogue.speakerY - cameraY + 2, 17);
        }

        if (tick % 10 < 8) {
            FontGraph.drawBold(var0, "Tiếp tục", viewCenterX, screenHeight - 13, 2);
        }

    }

    private static void g(Graphics var0) {
        for(int var1 = 0; var1 < du; ++var1) {
            if (dz[var1] + fi >= cameraX && dz[var1] - fi <= cameraX + viewWidth && dA[var1] >= cameraY && dA[var1] - fh <= cameraY + viewHeight) {
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
        var0.setClip(-1, -1, screenWidth + 2, screenHeight + 1);
        drawSky(var0);
        var0.translate(-cameraX, -cameraY);
        if (lowGraphics) {
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

    private static void b(int var0, int var1, int var2, int var3, int var4) {
        int var5 = -1;

        for(int var6 = 0; var6 < bM; ++var6) {
            if (bP[var6] == -1) {
                var5 = var6;
                break;
            }
        }

        if (var5 != -1) {
            bP[var5] = 1;
            bN[var5] = var0;
            bO[var5] = var1;
            bQ[var5] = var2;
            bR[var5] = var3;
            bS[var5] = var4;
        }
    }

    private static void setGuideMarker(int var0, int var1, int var2, int var3) {
        guideMarkerMap = var0;
        guideMarkerX = var1;
        guideMarkerY = var2;
        guideMarkerNpc = var3;
    }

    private static void c(int var0, int var1, int var2, int var3, int var4) {
        int var5 = -1;

        for(int var6 = 0; var6 < bT; ++var6) {
            if (bW[var6] == 0) {
                var5 = var6;
                break;
            }
        }

        if (var5 != -1) {
            bW[var5] = 1;
            bU[var5] = var0;
            bV[var5] = var1;
            bX[var5] = var2;
            bY[var5] = var3;
            bZ[var5] = var4;
        }
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
        if ((Dialogue.boxWidth = viewWidth >> 1) < 120) {
            Dialogue.boxWidth = 120;
        }

        for(var1 = 0; var1 < Dialogue.pageCount; ++var1) {
            Dialogue.pages[var1] = FontGraph.wrapRegularText(var0[var1], Dialogue.boxWidth - 20);
        }

        if ((Dialogue.boxX = Dialogue.speakerX - (Dialogue.boxWidth >> 1) - cameraX) < 2) {
            Dialogue.boxX = 2;
        }

        if (Dialogue.boxX + Dialogue.boxWidth > viewWidth - 2) {
            Dialogue.boxX = viewWidth - 2 - Dialogue.boxWidth;
        }

        Dialogue.visible = true;
        Dialogue.shownChars = 0;
        Dialogue.shownLines = 0;
    }

    private static boolean e(int var0, int var1, int var2) {
        if (lowGraphics) {
            return false;
        } else {
            var0 = var0 == 1 ? 0 : 1;
            if (cl[var0] != -1) {
                return false;
            } else {
                cl[var0] = 0;
                cj[var0] = var1;
                ck[var0] = var2;
                return true;
            }
        }
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

    private static void a(String var0, int var1, int var2, int var3, int var4, int var5) {
        var4 = -1;

        for(int var6 = 0; var6 < 5; ++var6) {
            if (dT[var6] == -1) {
                var4 = var6;
                break;
            }
        }

        if (var4 != -1) {
            dO[var4] = var0;
            dP[var4] = var2;
            dQ[var4] = var3;
            dR[var4] = 0;
            dS[var4] = var5;
            dT[var4] = 0;
            dU[var4] = var1;
        }
    }

    private static void f(int var0, int var1) {
        dX = 1;
        dV = var0;
        dW = var1;
    }

    private static void showNotice(String var0) {
        noticeText = var0;
        noticeTimer = 0;
        noticeVisible = true;
    }

    private static boolean f(int var0, int var1, int var2) {
        int var3 = cc[0] == -1 ? 0 : 1;
        if (cc[var3] != -1) {
            return false;
        } else {
            cc[var3] = 0;
            ce[var3] = var2;
            ca[var3] = var0;
            cb[var3] = var1;
            return true;
        }
    }

    private static void startMapEvent(int var0) {
        mapEventStep = 0;
        mapEventId = var0;
        mapEventActive = true;
    }

    private static boolean g(int var0, int var1) {
        if (lowGraphics) {
            return false;
        } else {
            int var2 = ch[0] == -1 ? 0 : 1;
            if (ch[var2] != -1) {
                return false;
            } else {
                ch[var2] = 0;
                cf[var2] = var0;
                cg[var2] = var1;
                return true;
            }
        }
    }

    private static void O() {
        for(int var0 = 0; var0 < bM; ++var0) {
            if (bP[var0] == 1) {
                int[] var10000 = bN;
                var10000[var0] += bQ[var0];
                if (bN[var0] >= cameraX && bN[var0] <= cameraX + viewWidth && bO[var0] >= cameraY && bO[var0] <= cameraY + viewHeight) {
                    if (a(fe - bN[var0]) < fi && a(ff - fj - bO[var0]) < fj) {
                        if (B == 5 && fd == 0 && bS[var0] < 3) {
                            bQ[var0] = -bQ[var0];
                            var10000 = bN;
                            var10000[var0] += bQ[var0];
                            return;
                        }

                        a(bR[var0], bN[var0] < fe ? 1 : -1);
                        bP[var0] = -1;
                        return;
                    }
                } else {
                    bP[var0] = -1;
                }
            }
        }

    }

    private static void P() {
        for(int var0 = 0; var0 < bT; ++var0) {
            if (bW[var0] != 0) {
                int[] var10000 = bU;
                var10000[var0] += bX[var0];
                var10000 = bV;
                var10000[var0] += bY[var0];
                int var10002 = bY[var0]++;
                if (bV[var0] > cameraY + viewHeight) {
                    bW[var0] = 0;
                }

                if (a(fe - bU[var0]) < fi && a(ff - fj - bV[var0]) < fj) {
                    a(bZ[var0], bU[var0] < fe ? 1 : -1);
                    bW[var0] = -1;
                    return;
                }
            }
        }

    }

    private static void Q() {
        if (fx) {
            aL = 0;
        }

    }

    private static void R() {
        if (fu) {
            cameraTargetX = fe - viewCenterX + cameraLeadX * fm;
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
                e(fm, fe - (fm << 3), ff);
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
                    e(-1, fe - -8, ff);
                    e(1, fe - 8, ff);
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
                        showNotice("Hit: " + QuestState.progress);
                    }
                } else {
                    if (GameMap.hasFlag(fe, ff, TileFlags.WATER) && ff % GameMap.TILE_SIZE > 8) {
                        g(fe, H(ff) + 8);
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
                            showNotice("Hit: " + QuestState.progress);
                        }
                    }

                    if (GameMap.hasFlag(fe, ff, TileFlags.WATER) && ff % GameMap.TILE_SIZE > 8) {
                        g(fe, H(ff) + 8);
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
                                    f(jJ[var0], jK[var0] - (jL[var0] >> 1), fm);
                                    jO[var0] = 9;
                                    jT[var0] = -8;
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
                if (ff >= GameMap.pixelHeight || ff > cameraY + viewHeight + fh || GameMap.hasFlag(fe, ff - fh, TileFlags.DEEP_WATER)) {
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

                    prepareDialog();
                    showMessage(MessageType.GAME_NOTICE);
                    state = GameState.MESSAGE;
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
                    e(fm, fe - (fm << 4), ff - 5);
                }

                D = 0;
                break;
            case 19:
                if (tick % 8 > 3) {
                    C = 43;
                } else {
                    C = 44;
                    e(fm, fe - (fm << 4), ff - 5);
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

                e(fm, fe - (fm << 3), ff);
                S();
                if ((var0 = A()) != -1 && (jO[var0] == 2 || jO[var0] == 3)) {
                    k(var0, 2, -fm);
                    f(jJ[var0], jK[var0] - (jL[var0] >> 1), fm);
                    a("-2", 0, jJ[var0], jK[var0] - jL[var0], 0, -2);
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
                        e(fm, fe - (fm << 4), ff - 5);
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
                        g(fe, H(ff) + 8);
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
                    e(fm, fe - (fm << 4), ff - 5);
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
        if (!lowGraphics) {
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
            f(fe, ff - 40);
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

        g(fe, ff);
        e(fm, fe - (fm << 3), ff);
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

                if (++fZ >= aA[cutsceneId][cutsceneStep]) {
                    fZ = aA[cutsceneId][cutsceneStep];
                    int var0;
                    switch (ay[cutsceneId][cutsceneStep]) {
                        case 1:
                            Input.keyDown(cutsceneScripts[cutsceneId][cutsceneStep]);
                            ++cutsceneStep;
                            break;
                        case 2:
                            Input.keyUp(cutsceneScripts[cutsceneId][cutsceneStep]);
                            ++cutsceneStep;
                            break;
                        case 3:
                            cutscenePlaying = false;
                            hudDirty = true;
                            ++cutsceneStep;
                            break;
                        case 4:
                            if (B == 1) {
                                captionVisible = false;
                                var0 = 0;
                                int var1 = 0;
                                switch (aD[cutsceneScripts[cutsceneId][cutsceneStep]]) {
                                    case 0:
                                        var0 = fe;
                                        var1 = ff;
                                        break;
                                    case 1:
                                        var0 = npcX[aC[cutsceneScripts[cutsceneId][cutsceneStep]]];
                                        var1 = npcY[aC[cutsceneScripts[cutsceneId][cutsceneStep]]];
                                        break;
                                    case 2:
                                        var0 = dz[aC[cutsceneScripts[cutsceneId][cutsceneStep]]];
                                        var1 = dA[aC[cutsceneScripts[cutsceneId][cutsceneStep]]];
                                        break;
                                    case 3:
                                    case 4:
                                        var0 = gn;
                                        var1 = go;
                                }

                                var1 -= 40;
                                startDialogue(DialogueText.cutsceneLines[cutsceneScripts[cutsceneId][cutsceneStep]], Dialogue.NO_ACTION, var0, var1);
                                ++cutsceneStep;
                                ga = true;
                            }
                            break;
                        case 5:
                            setGuideMarker(mapId, cutsceneScripts[cutsceneId][cutsceneStep], ff - 50, -1);
                            ++cutsceneStep;
                            break;
                        case 6:
                            guideMarkerMap = -1;
                            ++cutsceneStep;
                            break;
                        case 7:
                            fu = true;
                            ++cutsceneStep;
                            break;
                        case 8:
                            fu = false;
                            ++cutsceneStep;
                            break;
                        case 9:
                            cameraX = aB[cutsceneScripts[cutsceneId][cutsceneStep]][0] - viewCenterX;
                            cameraY = aB[cutsceneScripts[cutsceneId][cutsceneStep]][1] - viewCenterY;
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
                        case 10:
                            cameraTargetX = aB[cutsceneScripts[cutsceneId][cutsceneStep]][0] - viewCenterX;
                            cameraTargetY = aB[cutsceneScripts[cutsceneId][cutsceneStep]][1] - viewCenterY;
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
                        case 11:
                            cameraTargetX = gP - viewCenterX;
                            cameraTargetY = gQ - viewCenterY;
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
                        case 12:
                            cameraTargetY = cameraY += 2;
                            if (cameraY > aB[cutsceneScripts[cutsceneId][cutsceneStep]][1] - viewCenterY) {
                                ++cutsceneStep;
                            }
                        case 13:
                        default:
                            break;
                        case 14:
                            cameraTargetX = cameraX -= 2;
                            if (cameraX < aB[cutsceneScripts[cutsceneId][cutsceneStep]][0] - viewCenterX) {
                                ++cutsceneStep;
                            }
                            break;
                        case 15:
                            captionVisible = true;
                            captionLines = FontGraph.wrapBoldText(DialogueText.cutsceneCaptions[cutsceneScripts[cutsceneId][cutsceneStep]], viewWidth - 40);
                            if ((captionTargetY = (letterboxMaxHeight >> 1) - (captionLines.length << 3)) < 3) {
                                captionTargetY = 2;
                            }

                            captionY = -captionLines.length << 3;
                            ++cutsceneStep;
                            break;
                        case 16:
                            dx = true;
                            dy = cutsceneScripts[cutsceneId][cutsceneStep];
                            ++cutsceneStep;
                            break;
                        case 17:
                            fp = fe = MapExits.rightSpawnX;
                            fq = ff = MapExits.rightSpawnY;
                            releaseMapResources();
                            Save.pendingSave = true;
                            changeMap(cutsceneScripts[cutsceneId][cutsceneStep]);
                            return true;
                        case 18:
                            setNextNpcMarker(cutsceneScripts[cutsceneId][cutsceneStep]);
                            ++cutsceneStep;
                            break;
                        case 19:
                            gk = true;
                            ++cutsceneStep;
                            break;
                        case 20:
                            gk = false;
                            gm = 0;
                            gn = 1080;
                            go = 408;
                            ++cutsceneStep;
                            break;
                        case 21:
                            QuestState.current = Quest.NONE;
                            // Thưởng nhiệm vụ ESCORT_AYUMI_SISTER được trao trong cutscene, không qua onDialogueFinished.
                            Skills.motorbikeLicense = true;
                            showNotice("nhận bằng lái môtô");
                            setGuideMarker(0, npcX[4], npcY[4] - 40, 4);
                            ++cutsceneStep;
                            break;
                        case 22:
                            for(var0 = 0; var0 <= 6; ++var0) {
                                GameMap.clearFlags(24, var0);
                            }

                            Story.riverMonsterDefeated = true;
                            ++cutsceneStep;
                            break;
                        case 23:
                            fu = false;
                            cameraTargetX = dz[cutsceneScripts[cutsceneId][cutsceneStep]] - viewCenterX;
                            cameraTargetY = dA[cutsceneScripts[cutsceneId][cutsceneStep]] - viewCenterY;
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
                        case 24:
                            dx = true;
                            dy = cutsceneScripts[cutsceneId][cutsceneStep];
                            ++cutsceneStep;
                            break;
                        case 25:
                            he = true;
                            ++cutsceneStep;
                            break;
                        case 26:
                            for(var0 = 0; var0 <= 10; ++var0) {
                                GameMap.clearFlags(24, var0);
                            }

                            Story.tonikuDefeated = true;
                            ++cutsceneStep;
                            break;
                        case 27:
                            fu = false;
                            cameraTargetX = hp - viewCenterX;
                            cameraTargetY = hq - viewCenterY;
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
                        case 28:
                            hr = 1;
                            ++cutsceneStep;
                            break;
                        case 29:
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
                            itemMoney = null;
                            boss0Sprites = null;
                            System.gc();
                            boss1Sprites = null;
                            System.gc();
                            monsterSprites = null;
                            System.gc();
                            C();
                            classmateHeads = null;
                            npcDog = null;
                            enemyProjectiles = null;
                            dustSprites = null;
                            dartFrames = null;
                            auraSprites = null;
                            bloodSprites = null;
                            waterSplashSprites = null;
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
                        case 30:
                            QuestState.current = Quest.NONE;
                            Story.girlWaiting = false;
                            Story.girlOutingDone = true;
                            gm = 0;
                            gn = 648;
                            go = 288;
                            gk = false;
                            showNotice("gặp cô Ayumi");
                            setGuideMarker(0, 1032, 368, 4);
                            ++cutsceneStep;
                            break;
                        case 31:
                            fu = false;
                            cameraTargetX = gn - viewCenterX;
                            cameraTargetY = go - viewCenterY;
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
                        case 32:
                            gk = true;
                            gr = 2;
                            gp = -5;
                            gz = -1;
                            gK = 8;
                            ++cutsceneStep;
                            break;
                        case 33:
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

    private static void W() {
        for(int var0 = 0; var0 < de; ++var0) {
            if (dh[var0] != 0) {
                if (a(ff - (fh >> 1) - (dg[var0] - 7)) < 10 && a(fe - df[var0]) < 10) {
                    Inventory.money += dl[var0];
                    hudDirty = true;
                    dh[var0] = 301;
                    a("+" + dl[var0], 1, df[var0], dg[var0] - 15, 0, -1);
                }

                int[] var10000 = df;
                var10000[var0] += di[var0];
                var10000 = dg;
                var10000[var0] += dj[var0];
                int var10002;
                if (di[var0] > 1) {
                    var10002 = di[var0]--;
                }

                if (di[var0] < -1) {
                    var10002 = di[var0]++;
                }

                if (dj[var0] < 20) {
                    var10002 = dj[var0]++;
                }

                var10002 = dh[var0]++;
                if (dh[var0] > 200) {
                    dh[var0] = 0;
                }

                if (dh[var0] % 20 > 18) {
                    dk[var0] = 0;
                } else if (dh[var0] % 20 > 16) {
                    dk[var0] = 1;
                } else {
                    dk[var0] = 2;
                }

                if (dg[var0] >= 0 && dg[var0] < GameMap.pixelHeight && df[var0] < GameMap.pixelWidth && df[var0] >= 0) {
                    if (dj[var0] > 2 && (GameMap.flagsAtPixel(df[var0], dg[var0]) & TileFlags.SOLID) == TileFlags.SOLID) {
                        dg[var0] = H(dg[var0]);
                        if (dj[var0] > 2) {
                            dj[var0] = -(dj[var0] >> 1);
                        } else {
                            di[var0] = 0;
                            dj[var0] = 0;
                        }
                    }
                } else {
                    dh[var0] = 0;
                }
            }
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
                    if (cv[var0] <= cameraY + viewHeight + 32 && cv[var0] < GameMap.pixelHeight) {
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
                                b(cu[var0] + (cH[var0] << 3), cv[var0] - 15, cH[var0] * 12, ac[cD[var0]], 0);
                                break label143;
                            case 1:
                            case 5:
                                b(cu[var0] + (cH[var0] << 3), cv[var0] - 15, cH[var0] << 3, ac[cD[var0]], 1);
                                break label143;
                            case 2:
                            case 3:
                            case 6:
                                if (a(ff - cv[var0]) < 5 && a(fe - cu[var0]) < 30) {
                                    a(ac[cD[var0]], cH[var0]);
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
                        cr[var0] = ae[cD[var0]];
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
            cx[var0] = cv[var0] - 9 + cC[var0] + ag[cD[var0]][cJ[var0]];
        }

    }

    private static void ab() {
        for(int var0 = 0; var0 < cM; ++var0) {
            if (cQ[var0] != 0) {
                if (a(ff - (fh >> 1) - (cP[var0] - 7)) < 10 && a(fe - cO[var0]) < 10) {
                    if (cN[var0] == 0) {
                        PlayerStats.restoreHp(cU[var0]);

                        a("+" + cU[var0], 0, cO[var0], cP[var0] - 15, 0, -1);
                    } else {
                        PlayerStats.restoreMp(cU[var0]);

                        a("+" + cU[var0], 2, cO[var0], cP[var0] - 15, 0, -1);
                    }

                    hudDirty = true;
                    cQ[var0] = 301;
                }

                int[] var10000 = cO;
                var10000[var0] += cR[var0];
                var10000 = cP;
                var10000[var0] += cS[var0];
                int var10002;
                if (cR[var0] > 1) {
                    var10002 = cR[var0]--;
                }

                if (cR[var0] < -1) {
                    var10002 = cR[var0]++;
                }

                if (cS[var0] < 20) {
                    var10002 = cS[var0]++;
                }

                var10002 = cQ[var0]++;
                if (cQ[var0] > 200) {
                    cQ[var0] = 0;
                }

                if (cP[var0] < GameMap.pixelHeight && cP[var0] >= 0 && cO[var0] < GameMap.pixelWidth && cO[var0] >= 0) {
                    if (cS[var0] > 2 && (GameMap.flagsAtPixel(cO[var0], cP[var0]) & TileFlags.SOLID) == TileFlags.SOLID) {
                        cP[var0] = H(cP[var0]);
                        if (cS[var0] > 2) {
                            cS[var0] = -(cS[var0] >> 1);
                        } else {
                            cR[var0] = 0;
                            cS[var0] = 0;
                        }
                    }
                } else {
                    cQ[var0] = 0;
                }
            }
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
                        b(hp + (hs << 3), hq - 24, hs << 3, hj, 3);
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
            switch (mapEventConditions[mapEventId][mapEventStep]) {
                case 1:
                    if (fe > mapEventParams[mapEventId][mapEventStep]) {
                    startCutscene(mapEventCutscenes[mapEventId][mapEventStep]);
                    ++mapEventStep;
                    return;
                }
                break;
                case 2:
                    startCutscene(mapEventCutscenes[mapEventId][mapEventStep]);
                    ++mapEventStep;
                    return;
                case 3:
                default:
                    return;
                case 4:
                    if (fe > cameraX + viewWidth) {
                        startCutscene(mapEventCutscenes[mapEventId][mapEventStep]);
                        ++mapEventStep;
                        return;
                    }
                    break;
                case 6:
                    if (fe < mapEventParams[mapEventId][mapEventStep] && fe > mapEventParams[mapEventId][mapEventStep] - 96 && ff == ax[mapEventId][mapEventStep]) {
                    startCutscene(mapEventCutscenes[mapEventId][mapEventStep]);
                    ++mapEventStep;
                }
                case 5:
                    if (gS == 22) {
                        startCutscene(mapEventCutscenes[mapEventId][mapEventStep]);
                        ++mapEventStep;
                        return;
                    }
                    break;
                case 7:
                    if (hX == 5) {
                        startCutscene(mapEventCutscenes[mapEventId][mapEventStep]);
                        ++mapEventStep;
                        return;
                    }
                    break;
                case 8:
                    if (hg < 10) {
                        startCutscene(mapEventCutscenes[mapEventId][mapEventStep]);
                        ++mapEventStep;
                        return;
                    }
                    break;
                case 9:
                    if (hr == 22) {
                        startCutscene(mapEventCutscenes[mapEventId][mapEventStep]);
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
        ai();
        state = GameState.LOGO;
        (new Thread(var0)).start();
    }

    public b() {
        this.setFullScreenMode(true);
        System.gc();
        instance = this;
        Input.motorolaKeys = this.getKeyCode(Canvas.FIRE) == Input.MOTOROLA_CODE_FIRE;
        screenWidth = this.getWidth();
        screenHeight = this.getHeight();
        screenCenterX = screenWidth / 2;
        screenCenterY = screenHeight / 2;
        Input.clearHeld();
    }

    protected final void keyPressed(int var1) {
        if (var1 == Input.CODE_POUND) {
            if (bv.equals("1984")) {
                Skills.lightness = true;
                Skills.doubleJump = true;
                Skills.hide = true;
                Skills.burrow = true;
                Skills.dive = true;
                Skills.motorbikeLicense = true;
                Skills.darts = true;
                Skills.runOnWater = true;
                Skills.transform = true;
                Skills.tripleJump = true;
                showNotice("Learned All Skill");
            } else if (bv.equals("30538")) {
                PlayerStats.setMaxHpMp(500000, 500000);
                showNotice("Unlimit HP MP");
            } else if (bv.equals("38305")) {
                PlayerStats.attack = 10000;
                showNotice("Unlimit Damage");
            } else if (bv.equals("1982")) {
                gainExp(100000);
            } else if (bv.equals("1983")) {
                Inventory.money += 50000;
                showNotice("Money+=50000");
            } else {
                int var2;
                if (!bv.equals("1981")) {
                    if (bv.equals("8463")) {
                        var2 = gL + (int)((System.currentTimeMillis() - gM) / 1000L);
                        showNotice(var2 / 3600 + ":" + var2 % 3600 / 60 + ":" + var2 % 60);
                    }
                } else {
                    for(var2 = 0; var2 < 19; ++var2) {
                        X[var2] = true;
                    }

                    showNotice("Unlock all map!");
                }
            }

            hudDirty = true;
            bv = "";
        }

        if (var1 >= Input.CODE_NUM0 && var1 <= Input.CODE_NUM9 && (bv = bv + (char)var1).length() > 10) {
            bv = "";
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
            switch (state) {
                case GameState.PLAYING:
                    Graphics var6 = var1;
                    var1.setClip(-1, -1, viewWidth + 2, viewHeight + 2);
                    drawSky(var1);
                    var1.translate(-cameraX, -cameraY);
                    var1 = var1;

                    int var2;
                    for(var2 = 0; var2 < houseCount; ++var2) {
                        if (houseX[var2] + houseHalfWidth[var2] >= cameraX && houseX[var2] - houseHalfWidth[var2] <= cameraX + viewWidth && houseY[var2] >= cameraY && houseY[var2] - houseHeight[var2] <= cameraY + viewHeight) {
                            jg = (jf = houseX[var2] - cameraX) - viewCenterX;
                            jh = (ji = (GameMap.TILE_SIZE - 2) * jg / GameMap.TILE_SIZE) + viewCenterX;
                            var1.drawImage(houseImages[houseTypes[var2]], cameraX + jh, houseY[var2], 33);
                        }
                    }

                    if (lowGraphics) {
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
                        if (cameraX <= is[var2] + 12 && cameraX + viewWidth >= is[var2] - 12 && cameraY <= it[var2] && cameraY + viewHeight >= it[var2] - 24) {
                            var1.drawImage(itemCrate, is[var2], it[var2], 33);
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < ii; ++var2) {
                        if (cameraX <= ik[var2] + 12 && cameraX + viewWidth >= ik[var2] - 12 && cameraY <= il[var2] && cameraY + viewHeight >= il[var2] - 24) {
                            if (im[var2] == 1) {
                                var1.drawImage(motorbikeFrames[in[var2]], ik[var2], il[var2], 33);
                            } else {
                                var1.drawRegion(motorbikeFrames[in[var2]], 0, 0, motorbikeFrames[in[var2]].getWidth(), motorbikeFrames[in[var2]].getHeight(), 2, ik[var2], il[var2], 33);
                            }
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < de; ++var2) {
                        if (dh[var2] != 0 && df[var2] >= cameraX - 10 && df[var2] <= cameraX + viewWidth + 10 && dg[var2] >= cameraY && dg[var2] <= cameraY + viewHeight + 20) {
                            var1.drawRegion(itemMoney[dm[var2]], 0, dk[var2] * Item.FRAME_HEIGHT, Item.MONEY_WIDTH, Item.FRAME_HEIGHT, 0, df[var2], dg[var2], 33);
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < cM; ++var2) {
                        if (cQ[var2] != 0 && cO[var2] >= cameraX - 10 && cO[var2] <= cameraX + viewWidth + 10 && cP[var2] >= cameraY && cP[var2] <= cameraY + viewHeight + 20) {
                            var1.drawRegion(itemPotions[cN[var2]], 0, cT[var2] * Item.FRAME_HEIGHT, Item.POTION_WIDTH, Item.FRAME_HEIGHT, 0, cO[var2], cP[var2], 33);
                        }
                    }

                    int var3;
                    if (gN && gS != 22 && cameraX + viewWidth >= gP - (gV >> 1) && cameraX <= gP + (gV >> 1) && cameraY <= gQ && cameraY + viewHeight >= gQ - gW) {
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

                    for(var2 = 0; var2 < jG; ++var2) {
                        if (jO[var2] != 5 && jO[var2] != 8 && jJ[var2] >= cameraX - 10 && jJ[var2] <= cameraX + viewWidth + 10 && jK[var2] >= cameraY && jK[var2] <= cameraY + viewHeight + 20) {
                            var1.drawImage(monsterSprites[kg[var2]][jX[var2] >= 0 ? Mob.MONSTER_DIR_RIGHT : Mob.MONSTER_DIR_LEFT][jW[var2]], jJ[var2], jK[var2], 33);
                            if (jN[var2] != 0) {
                                var1.setColor(13073);
                                var1.drawRect(jJ[var2] - (jM[var2] >> 1) - 1, jK[var2] - jL[var2] - 7, jM[var2] + 2, 4);
                                var3 = jY[var2] * jM[var2] / jZ[var2];
                                var1.setColor(60928);
                                var1.fillRect(jJ[var2] - (jM[var2] >> 1), jK[var2] - jL[var2] - 6, var3, 3);
                                var1.setColor(17442);
                                var1.fillRect(jJ[var2] - (jM[var2] >> 1) + var3, jK[var2] - jL[var2] - 6, jM[var2] - var3 + 1, 3);
                            }
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < cp; ++var2) {
                        if (cE[var2] != 5 && cu[var2] + 15 >= cameraX && cu[var2] - 15 <= cameraX + viewWidth && cv[var2] >= cameraY && cv[var2] - 30 <= cameraY + viewHeight) {
                            if (cH[var2] == -1) {
                                var1.drawImage(ninjaLegs[cI[var2]], cu[var2] + ah[cD[var2]], cv[var2], 33);
                                var1.drawImage(ninjaBodies[cD[var2]][cJ[var2]], cw[var2] + af[cD[var2]][cJ[var2]], cx[var2], 33);
                            } else {
                                var1.drawRegion(ninjaLegs[cI[var2]], 0, 0, ninjaLegs[cI[var2]].getWidth(), ninjaLegs[cI[var2]].getHeight(), 2, cu[var2] - ah[cD[var2]], cv[var2], 33);
                                var1.drawRegion(ninjaBodies[cD[var2]][cJ[var2]], 0, 0, ninjaBodies[cD[var2]][cJ[var2]].getWidth(), ninjaBodies[cD[var2]][cJ[var2]].getHeight(), 2, cw[var2] - af[cD[var2]][cJ[var2]], cx[var2], 33);
                            }

                            if (cq[var2] != 0) {
                                var1.setColor(13073);
                                var1.drawRect(cu[var2] - 13, cv[var2] - 40, 26, 4);
                                var3 = cr[var2] * 24 / ae[cD[var2]];
                                var1.setColor(15597568);
                                var1.fillRect(cu[var2] - 12, cv[var2] - 39, var3, 3);
                                var1.setColor(4456482);
                                var1.fillRect(cu[var2] - 12 + var3, cv[var2] - 39, 24 - var3 + 1, 3);
                            }
                        }
                    }

                    g(var6);
                    if (dn && doSt + 15 >= cameraX && doSt - 15 <= cameraX + viewWidth && dp >= cameraY && dp - 10 <= cameraY + viewHeight) {
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

                    if (dz[0] + fi >= cameraX && dz[0] - fi <= cameraX + viewWidth && dA[0] >= cameraY && dA[0] - fh <= cameraY + viewHeight) {
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

                if (dX != 0 && (var2 = dX % 3) < 2) {
                    var6.drawImage(auraSprites[var2], dV, dW, 17);
                }

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

                var1 = var6;

                for(var2 = 0; var2 < 2; ++var2) {
                    if (cc[var2] != -1) {
                        if (ce[var2] == 1) {
                            var1.drawImage(bloodSprites[cd[var2]], ca[var2], cb[var2], 3);
                        } else {
                            var1.drawRegion(bloodSprites[cd[var2]], 0, 0, bloodSprites[cd[var2]].getWidth(), bloodSprites[cd[var2]].getHeight(), 2, ca[var2], cb[var2], 3);
                        }
                    }
                }

                var1 = var6;
                if (!lowGraphics) {
                    for(var2 = 0; var2 < 2; ++var2) {
                        if (cl[var2] != -1) {
                            var1.drawImage(dustSprites[var2][cl[var2]], cj[var2], ck[var2], 3);
                        }
                    }
                }

                var1 = var6;

                for(var2 = 0; var2 < bM; ++var2) {
                    if (bP[var2] != -1) {
                        var1.drawImage(enemyProjectiles[bS[var2]][bQ[var2] > 0 ? Effect.PROJECTILE_DIR_RIGHT : Effect.PROJECTILE_DIR_LEFT], bN[var2], bO[var2], 3);
                    }
                }

                var1 = var6;

                for(var2 = 0; var2 < cV; ++var2) {
                    if (cY[var2] != -1) {
                        var1.drawImage(dartFrames[cZ[var2]], cW[var2], cX[var2], 3);
                    }
                }

                var1 = var6;

                for(var2 = 0; var2 < bT; ++var2) {
                    if (bW[var2] != 0) {
                        var1.drawImage(healSprite, bU[var2], bV[var2], 3);
                    }
                }

                var1 = var6;
                if (!lowGraphics) {
                    for(var2 = 0; var2 < 2; ++var2) {
                        if (ch[var2] != -1) {
                            var1.drawImage(waterSplashSprites[ci[var2]], cf[var2], cg[var2], 3);
                        }
                    }
                }

                l(var6);
                var1 = var6;

                for(var2 = 0; var2 < 5; ++var2) {
                    if (dT[var2] != -1) {
                        FontGraph.drawSmall(var1, dO[var2], dU[var2], dP[var2], dQ[var2], 0);
                    }
                }

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

                if (noticeVisible) {
                    var6.translate(-var6.getTranslateX(), -var6.getTranslateY());
                    var6.setClip(-1, -1, screenWidth + 2, screenHeight + 2);
                    var6.setColor(0);
                    var6.fillRect(0, 0, screenWidth, 21);
                    var6.setColor(10275899);
                    var6.fillRect(0, 19, screenWidth, 1);
                    if (tick % 10 > 2) {
                        FontGraph.drawBold(var6, noticeText, viewCenterX, 4, 2);
                    }
                }

                if (Dialogue.visible) {
                    drawDialogue(var6);
                }

                return;
                case GameState.LOADING:
                    var1.setColor(0);
                    var1.fillRect(0, 0, screenWidth, 21);
                    FontGraph.drawBold(var1, "Loading... " + loadingProgress + "%", screenWidth >> 1, 4, 2);
                    return;
                case GameState.MAIN_MENU:
                    var1.setClip(-1, -1, screenWidth + 2, screenHeight + 1);
                    drawSky(var1);
                    var1.translate(-cameraX, -cameraY);
                    if (lowGraphics) {
                        j(var1);
                    } else {
                        k(var1);
                    }

                    drawSigns(var1);
                    l(var1);
                    var1.translate(-var1.getTranslateX(), -var1.getTranslateY());
                    var1.setColor(0);
                    var1.fillRect(0, 0, viewWidth, ba);
                    var1.fillRect(0, bb, viewWidth, screenHeight - bb + 1);
                    var1.setColor(10275899);
                    var1.fillRect(0, ba - 1, screenWidth, 1);
                    var1.fillRect(0, bb + 1, screenWidth, 1);
                    var1.drawImage(titleImage, screenCenterX, aY + bb >> 1, 3);
                    FontGraph.drawBold(var1, "*", screenCenterX - 50 - tick % 3, bd, 0);
                    FontGraph.drawBold(var1, "/", screenCenterX + 50 + tick % 3, bd, 1);
                    FontGraph.drawBold(var1, GameText.mainMenuItems[aX], screenCenterX, bd, 3);
                    return;
                case GameState.LOGO:
                    var1.setColor(0);
                    var1.fillRect(0, 0, screenWidth, screenHeight);
                    if (logoImage != null && jE >= 0) {
                        var1.drawRegion(logoImage, 0, jE * Ui.LOGO_FRAME_HEIGHT, 71, Ui.LOGO_FRAME_HEIGHT, 0, screenWidth >> 1, screenHeight >> 1, 3);
                    }

                    return;
                case GameState.GAME_MENU:
                    drawGameMenu(var1);
                    return;
                case GameState.WORLD_MAP:
                    drawWorldMap(var1);
                    return;
                case GameState.MESSAGE:
                    drawMessage(var1);
                    return;
                case GameState.INVENTORY:
                    drawInventory(var1);
                    return;
                case GameState.SHOP:
                    drawShop(var1);
                    return;
                case GameState.SETUP_PROMPT:
                    var1.setColor(0);
                    var1.fillRect(0, 0, screenWidth, screenHeight);
                    if (!lk) {
                        var1.drawImage(popupImage, screenCenterX, screenCenterY - 30, 3);
                        if (ll == 0) {
                            FontGraph.drawBold(var1, "Bật âm thanh?", screenCenterX, screenCenterY + 15, 2);
                            FontGraph.drawBold(var1, "Bật", 4, screenHeight - 14, 0);
                            FontGraph.drawBold(var1, "Tắt", screenWidth - 4, screenHeight - 14, 1);
                            break;
                        }

                        FontGraph.drawBold(var1, "Chất lượng hình ảnh?", screenCenterX, screenCenterY + 15, 2);
                        FontGraph.drawBold(var1, "Cao", 4, screenHeight - 14, 0);
                        FontGraph.drawBold(var1, "Thấp", screenWidth - 4, screenHeight - 14, 1);
                    }

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
            switch (state) {
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
                                showMessage(MessageType.LEVEL_UP);
                                setMessageText("Khu vực phía trước rất nguy hiểm! Chỉ những người có thể ẩn thân có thể vào!");
                                fe = GameMap.pixelWidth - 60;
                                B = 4;
                                state = GameState.MESSAGE;
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
                    if (!lowGraphics) {
                        Sky.updateClouds(tick, viewWidth);
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
                                openGameMenu();
                                state = GameState.GAME_MENU;
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
                                                        openShop();
                                                        state = GameState.SHOP;
                                                        shopMode = SHOP_MODE_MONEY;
                                                    }
                                                }

                                                if (mapId == 2 && var4 == 0) {
                                                    openShop();
                                                    state = GameState.SHOP;
                                                    shopMode = SHOP_MODE_SMS;
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
                                    e(-1, fe - -8, ff);
                                    e(1, fe - 8, ff);
                                    g(fe, ff);
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
                                        g(is[var13], it[var13] - 13);
                                        g(is[var13] + 1, it[var13] - 10);
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

                    for(var13 = 0; var13 < 5; ++var13) {
                        if (dT[var13] != -1) {
                            var10000 = dT;
                            var10000[var13] += a(dS[var13]);
                            if (dT[var13] > 30) {
                                dT[var13] = -1;
                            }

                            var10000 = dP;
                            var10000[var13] += dR[var13];
                            var10000 = dQ;
                            var10000[var13] += dS[var13];
                        }
                    }

                    if (!lowGraphics) {
                        for(var13 = 0; var13 < 2; ++var13) {
                            if (ch[var13] != -1) {
                                var10002 = cg[var13]--;
                                if (tick % 2 == 0) {
                                    var10002 = ch[var13]++;
                                    if (ch[var13] > 2) {
                                        ch[var13] = -1;
                                    } else {
                                        ci[var13] = ch[var13];
                                    }
                                }
                            }
                        }
                    }

                    if (!lowGraphics) {
                        for(var13 = 0; var13 < 2; ++var13) {
                            if (cl[var13] != -1) {
                                var10002 = cl[var13]++;
                                if (cl[var13] >= 5) {
                                    cl[var13] = -1;
                                }

                                if (var13 == 0) {
                                    var10002 = cj[var13]--;
                                } else {
                                    var10002 = cj[var13]++;
                                }

                                var10002 = ck[var13]--;
                            }
                        }
                    }

                    for(var13 = 0; var13 < 2; ++var13) {
                        if (cc[var13] != -1) {
                            var10002 = cc[var13]++;
                            var10000 = ca;
                            var10000[var13] += ce[var13] << 2;
                            var10002 = cb[var13]--;
                            if (cc[var13] >= 6) {
                                cc[var13] = -1;
                            } else {
                                cd[var13] = (cc[var13] >> 1) % 3;
                            }
                        }
                    }

                    int var5;
                    for(var13 = 0; var13 < cV; ++var13) {
                        if (cY[var13] == 1) {
                            var10000 = cW;
                            var10000[var13] += da[var13];
                            var10000 = cX;
                            var10000[var13] += db[var13];
                            if (cW[var13] >= cameraX && cW[var13] <= cameraX + viewWidth && cX[var13] >= cameraY && cX[var13] <= cameraY + viewHeight) {
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
                                    for(var4 = 0; var4 < jG; ++var4) {
                                        if (jO[var4] != 5 && jO[var4] != 1 && a(jJ[var4] - cW[var13]) < jM[var4] && a(jK[var4] - (jL[var4] >> 1) - cX[var13]) < jL[var4]) {
                                            boolean var14;
                                            label2807: {
                                                var5 = dc[var13] ? PlayerStats.dartDamage << 1 : PlayerStats.dartDamage;
                                                if (jO[var4] == 2 || jO[var4] == 3 || jO[var4] == 4) {
                                                    if (jI[var4] == 9 && da[var13] * jX[var4] < 0) {
                                                        jO[var4] = 4;
                                                        jT[var4] = 4;
                                                        da[var13] = -da[var13];
                                                        db[var13] = -db[var13];
                                                        var10000 = cW;
                                                        var10000[var13] += da[var13];
                                                        var10000 = cX;
                                                        var10000[var13] += db[var13];
                                                        var14 = true;
                                                        break label2807;
                                                    }

                                                    var10000 = jY;
                                                    var10000[var4] -= var5;
                                                    if (jY[var4] <= 0) {
                                                        jY[var4] = 0;
                                                        jT[var4] = -5;
                                                        jU[var4] = da[var13];
                                                        jV[var4] = 0;
                                                        jN[var4] = 0;
                                                        jO[var4] = 1;
                                                        L(var4);
                                                        var14 = true;
                                                        break label2807;
                                                    }

                                                    jT[var4] = 0;
                                                    jX[var4] = da[var13] < 0 ? 1 : -1;
                                                    jO[var4] = 6;
                                                    jW[var4] = 3;
                                                    cY[var13] = -1;
                                                }

                                                var14 = false;
                                            }

                                            if (!var14) {
                                                f(jJ[var4], jK[var4] - (jL[var4] >> 1), jX[var4]);
                                                a("-" + var5, 0, jJ[var4], jK[var4] - jL[var4], 0, -2);
                                            }
                                            break;
                                        }
                                    }

                                    for(var4 = 0; var4 < cp; ++var4) {
                                        if (cE[var4] != 5 && cE[var4] != 1 && a(cu[var4] - cW[var13]) < 20 && a(cv[var4] - 15 - cX[var13]) < 20) {
                                            if (dc[var13]) {
                                                a(var4, PlayerStats.dartDamage << 1, cH[var13]);
                                                f(cu[var4], cv[var4] - 15, cH[var4]);
                                                a("-" + (PlayerStats.dartDamage << 1), 0, cu[var4], cv[var4] - 32, 0, -2);
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
                                        f(cW[var13], cX[var13], gT);
                                        a("-" + PlayerStats.dartDamage, 0, gP, gQ - gW, 0, -2);
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

                    O();
                    if (dX > 0 && ++dX > 30) {
                        dX = 0;
                    }

                    P();
                    if (guideMarkerMap >= 0) {
                        if (guideMarkerX > cameraX + 10 && guideMarkerX < cameraX + viewWidth - 10 && guideMarkerY > cameraY + 10 && guideMarkerY < cameraY + viewHeight - 10) {
                            ge = 2;
                            gf = guideMarkerX;
                            gg = guideMarkerY + gi;
                        } else if (a(fe - guideMarkerX) > a(ff - guideMarkerY)) {
                            if (fe > guideMarkerX) {
                                ge = 1;
                                gf = cameraX + 20 + gi;
                            } else {
                                ge = 0;
                                gf = cameraX + viewWidth - 20 + gi;
                            }

                            if ((gg = guideMarkerY + 24) < cameraY + 10) {
                                gg = cameraY + 10;
                            }

                            if (gg > cameraY + viewHeight - 10) {
                                gg = cameraY + viewHeight - 10;
                            }
                        } else if (ff > guideMarkerY) {
                            ge = 3;
                            gf = fe;
                            gg = cameraY + 20 + gi;
                        } else {
                            ge = 2;
                            gf = fe;
                            gg = cameraY + viewHeight - 20 + gi;
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

                                    e(gz, gn - (gz << 3), go);
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
                                        e(-1, gn - -8, go);
                                        e(1, gn - 8, go);
                                    }

                                    if (GameMap.hasFlag(gn, go, TileFlags.WATER) && GameMap.hasFlag(gn, go + 24, TileFlags.DEEP_WATER) && go % GameMap.TILE_SIZE > 8) {
                                        g(gn, H(go) + 8);
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
                                e(-gT, gP + gT * 28, gQ - 5);
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

                            if (gQ > cameraY + viewHeight + gW || gQ >= GameMap.pixelHeight + gW) {
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
                                c(hP, hQ - 45, -1, -12, hK);
                                c(hP, hQ - 50, 0, -15, hK);
                                c(hP, hQ - 45, 1, -12, hK);
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
                                    c(hP, hQ - 45, -2, -10, hK);
                                } else if (var13 == 7 && hM < hN >> 1) {
                                    c(hP, hQ - 50, 0, -11, hK);
                                } else if (var13 == 9) {
                                    c(hP, hQ - 45, 2, -10, hK);
                                }
                            }

                            if (ib > 20 && ib < 50 && var13 == 0) {
                                b(hP + hY * 20, hQ - 29, hY << 3, hK, 2);
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
                                    e(-1, dz[0] - -8, dA[0]);
                                    e(1, dz[0] - 8, dA[0]);
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

                W();
                ab();
                if (questItemState != 0) {
                    if (questItemState == 1) {
                        if (a(ff - (fh >> 1) - (questItemY - 7)) < 10 && a(fe - questItemX) < 10) {
                            questItemState = 2;
                            QuestState.itemFound = true;
                            showNotice("đã lấy được bí kíp");
                        }
                    } else {
                        ++questItemState;
                        questItemY -= 3;
                        if (questItemState > 40) {
                            questItemState = 0;
                        }
                    }
                }

                if (noticeVisible && ++noticeTimer >= 150) {
                    noticeVisible = false;
                }
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
                                gM = System.currentTimeMillis();
                                changeMap(99);
                            } else if (aX == 1) {
                                if (!Save.isSaved()) {
                                    break label2172;
                                }

                                titleImage = null;
                                releaseMapResources();
                                int[] var12;
                                gL = (var12 = a(Save.loadRecord("ninjasave")))[0];
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
                                gM = System.currentTimeMillis();
                                changeMap(mapId);
                            } else if (aX == 2) {
                                titleImage = null;
                                releaseMapResources();
                                state = GameState.LOADING;
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
                    if (jD < 4) {
                        jE = jD;
                    }

                    if (jD > 51) {
                        logoImage = null;
                        at();
                        state = GameState.SETUP_PROMPT;
                    } else if (jD > 46) {
                        jE = 4 - (jD - 46);
                    }

                    screenWidth = instance.getWidth();
                    screenHeight = instance.getHeight();
                    ++jD;
                    break;
                case GameState.GAME_MENU:
                    if (++kJ > 10000) {
                        kJ = 0;
                    }

                    var3 = false;
                    if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
                        var3 = true;
                        cornerIcons = null;
                        ensureMapImages();
                        state = GameState.PLAYING;
                        hudDirty = true;
                    } else if (!Input.pressed[Input.KEY_FIRE] && !Input.pressed[Input.KEY_SOFT_LEFT]) {
                        if (Input.pressed[Input.KEY_DOWN]) {
                            var3 = true;
                            if (++gameMenuSelection > 3) {
                                gameMenuSelection = 0;
                            }
                        } else if (Input.pressed[Input.KEY_UP]) {
                            var3 = true;
                            if (--gameMenuSelection < 0) {
                                gameMenuSelection = 3;
                            }
                        } else if (Input.pressed[Input.KEY_LEFT]) {
                            var3 = true;
                        } else if (Input.pressed[Input.KEY_RIGHT]) {
                            var3 = true;
                        }
                    } else {
                        var3 = true;
                        if (gameMenuSelection == 0) {
                            openInventory();
                            state = GameState.INVENTORY;
                        }

                        if (gameMenuSelection == 1) {
                            openWorldMap();
                            state = GameState.WORLD_MAP;
                        }

                        if (gameMenuSelection == 2) {
                            if (B != 1) {
                                cornerIcons = null;
                                ensureMapImages();
                                prepareDialog();
                                showMessage(MessageType.GAME_NOTICE);
                                setMessageText("Bạn chỉ có thể lưu và thoát khi nhân vật đang đứng yên.");
                                messageY = viewCenterY - 40;
                                state = GameState.MESSAGE;
                            } else {
                                b = false;
                            }
                        }
                    }

                    if (var3) {
                        Input.clearPressed();
                    }
                    break;
                case GameState.WORLD_MAP:
                    if (++kW > 10000) {
                        kW = 0;
                    }

                    if (ld != kX || le != kY) {
                        kZ = kX - ld << 1;
                        la = kY - le << 1;
                        lb += kZ;
                        ld += lb >> 4;
                        lb &= 15;
                        lc += la;
                        le += lc >> 4;
                        lc &= 15;
                        if (ld < -40) {
                            ld = -40;
                        }

                        if (ld > lf) {
                            ld = lf;
                        }

                        if (le < -40) {
                            le = -40;
                        }

                        if (le > lg) {
                            le = lg;
                        }
                    }

                    var3 = false;
                    if (Input.pressed[Input.KEY_FIRE] || Input.pressed[Input.KEY_SOFT_LEFT]) {
                        Input.clearPressed();
                        if (worldMapCursor != -1) {
                            if (!X[worldMapCursor]) {
                                showMessage(MessageType.TRAVEL_NOT_VISITED);
                                state = GameState.MESSAGE;
                            } else if (worldMapCurrent == worldMapCursor) {
                                showMessage(MessageType.TRAVEL_ALREADY_HERE);
                                state = GameState.MESSAGE;
                            } else {
                                showMessage(MessageType.TRAVEL_CONFIRM);
                                state = GameState.MESSAGE;
                            }
                        }
                    }

                    if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
                        ar();
                        cornerIcons = null;
                        ensureMapImages();
                        state = GameState.PLAYING;
                        Input.clearPressed();
                        Input.clearHeld();
                        Input.pressed[Input.KEY_SOFT_LEFT] = true;
                    } else {
                        if (Input.held[Input.KEY_UP]) {
                            if ((kU -= 4) < -20) {
                                kU = -20;
                            }

                            var3 = true;
                        }

                        if (Input.held[Input.KEY_DOWN]) {
                            if ((kU += 4) > 279) {
                                kU = 279;
                            }

                            var3 = true;
                        }

                        if (Input.held[Input.KEY_LEFT]) {
                            if ((kT -= 4) < -20) {
                                kT = -20;
                            }

                            var3 = true;
                        }

                        if (Input.held[Input.KEY_RIGHT]) {
                            if ((kT += 4) > 279) {
                                kT = 279;
                            }

                            var3 = true;
                        }

                        if (var3) {
                            kX = kT - viewCenterX;
                            kY = kU - viewCenterY;
                            as();
                        }
                    }
                    break;
                case GameState.MESSAGE:
                    if (++lo < 10) {
                        Input.clearPressed();
                    }

                    if (Input.pressed[Input.KEY_SOFT_LEFT]) {
                        Input.clearPressed();
                        switch (messageType) {
                            case MessageType.TRAVEL_NOT_VISITED:
                            case MessageType.TRAVEL_ALREADY_HERE:
                            case MessageType.NOT_ENOUGH_MONEY:
                                state = GameState.WORLD_MAP;
                                break;
                            case MessageType.TRAVEL_CONFIRM:
                                if (travelCost >= Inventory.money) {
                                    showMessage(MessageType.NOT_ENOUGH_MONEY);
                                } else {
                                    Inventory.money -= travelCost;
                                    ar();
                                    cornerIcons = null;
                                    ensureMapImages();
                                    releaseMapResources();
                                    fp = fe = kP[worldMapCursor] * 24;
                                    fq = ff = kQ[worldMapCursor] * 24;
                                    Save.pendingSave = true;
                                    changeMap(worldMapCursor);
                                }
                                break;
                            case MessageType.GAME_NOTICE:
                            case MessageType.LEVEL_UP:
                                cornerIcons = null;
                                state = GameState.PLAYING;
                        }
                    }

                    if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
                        Input.clearPressed();
                        switch (messageType) {
                            case MessageType.TRAVEL_NOT_VISITED:
                            case MessageType.TRAVEL_ALREADY_HERE:
                            case MessageType.NOT_ENOUGH_MONEY:
                            case MessageType.GAME_NOTICE:
                            case MessageType.LEVEL_UP:
                            default:
                                break;
                            case MessageType.TRAVEL_CONFIRM:
                                state = GameState.WORLD_MAP;
                                break;
                            case MessageType.SHOP_NOTICE:
                                state = GameState.SHOP;
                        }
                    }

                    hudDirty = true;
                    break;
                case GameState.INVENTORY:
                    if (inventoryBlink > 0) {
                        --inventoryBlink;
                    }

                    if (Input.pressed[Input.KEY_FIRE] || Input.pressed[Input.KEY_SOFT_LEFT]) {
                        if (Inventory.potionCounts[inventoryCursor] > 0) {
                            var10002 = Inventory.potionCounts[inventoryCursor]--;
                            inventoryBlink = inventoryBlinkColors.length - 1;
                            if (inventoryCursor <= 2) {
                                PlayerStats.restoreHp(Inventory.potionRestoreAmounts[inventoryCursor]);
                            } else {
                                PlayerStats.restoreMp(Inventory.potionRestoreAmounts[inventoryCursor]);
                            }
                        }

                        Input.clearPressed();
                    }

                    if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
                        Input.clearPressed();
                        Input.clearHeld();
                        state = GameState.GAME_MENU;
                    } else {
                        if (Input.pressed[Input.KEY_LEFT]) {
                            Input.clearPressed();
                            if (--inventoryCursor < 0) {
                                inventoryCursor = 5;
                            }
                        }

                        if (Input.pressed[Input.KEY_RIGHT]) {
                            Input.clearPressed();
                            if (++inventoryCursor == 6) {
                                inventoryCursor = 0;
                            }
                        }
                    }
                    break;
                case GameState.SHOP:
                    if (shopBlink > 0) {
                        --shopBlink;
                    }

                    if (Input.pressed[Input.KEY_FIRE] || Input.pressed[Input.KEY_SOFT_LEFT]) {
                        Input.clearPressed();
                        if (shopRow == 0) {
                            if (shopMode == SHOP_MODE_MONEY) {
                                if (shopCursor < 3) {
                                    if (Inventory.potionCounts[0] + Inventory.potionCounts[1] + Inventory.potionCounts[2] > 99) {
                                        break;
                                    }
                                } else if (Inventory.potionCounts[3] + Inventory.potionCounts[4] + Inventory.potionCounts[5] > 99) {
                                    break;
                                }

                                if (Inventory.money >= ShopCatalog.prices[shopRow][shopCursor]) {
                                    var10002 = Inventory.potionCounts[shopCursor]++;
                                    Inventory.money -= ShopCatalog.prices[shopRow][shopCursor];
                                }
                            } else {
                                showPurchaseFailed();
                            }
                        } else {
                            if (Inventory.swordLevel >= shopCursor) {
                                showMessage(MessageType.SHOP_NOTICE);
                                setMessageText("Không cần thiết phải mua vũ khí này vì vũ khí đang sử dụng mạnh hơn!");
                                messageY = shopY;
                                state = GameState.MESSAGE;
                                break;
                            }

                            if (PlayerStats.level < ShopCatalog.swordLevelRequirements[shopCursor]) {
                                showMessage(MessageType.SHOP_NOTICE);
                                setMessageText("Bạn phải đạt cấp độ " + ShopCatalog.swordLevelRequirements[shopCursor] + " mới có thể mua vũ khí này!");
                                messageY = shopY;
                                state = GameState.MESSAGE;
                                break;
                            }

                            if (shopMode == SHOP_MODE_MONEY) {
                                if (Inventory.money >= ShopCatalog.prices[shopRow][shopCursor]) {
                                    Inventory.money -= ShopCatalog.prices[shopRow][shopCursor];
                                    Inventory.swordLevel = shopCursor;
                                    onSwordPurchased();
                                } else {
                                    showMessage(MessageType.SHOP_NOTICE);
                                    setMessageText("Không có đủ tiền: " + ShopCatalog.prices[shopRow][shopCursor] + "$. Bạn chỉ có: " + Inventory.money + "$. Hãy đi tìm thêm.");
                                    messageY = shopY;
                                    state = GameState.MESSAGE;
                                }
                                break;
                            }

                            showPurchaseFailed();
                        }
                    }

                    if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
                        cornerIcons = null;
                        shopSwordIcons = null;
                        System.gc();
                        ensureMapImages();
                        Input.clearPressed();
                        Input.clearHeld();
                        state = GameState.PLAYING;
                        hudDirty = true;
                    } else {
                        if (Input.pressed[Input.KEY_UP]) {
                            Input.clearPressed();
                            if (shopRow == 1) {
                                shopRow = 0;
                                ++shopCursor;
                            }
                        }

                        if (Input.pressed[Input.KEY_DOWN]) {
                            Input.clearPressed();
                            if (shopRow == 0) {
                                shopRow = 1;
                                if (--shopCursor < 0) {
                                    shopCursor = 0;
                                }

                                if (shopCursor > 3) {
                                    shopCursor = 3;
                                }
                            }
                        }

                        if (Input.pressed[Input.KEY_LEFT]) {
                            Input.clearPressed();
                            if (--shopCursor < 0) {
                                if (shopRow == 0) {
                                    shopCursor = 5;
                                } else {
                                    shopCursor = 3;
                                }
                            }
                        }

                        if (Input.pressed[Input.KEY_RIGHT]) {
                            Input.clearPressed();
                            ++shopCursor;
                            if (shopRow == 0 && shopCursor == 6) {
                                shopCursor = 0;
                            }

                            if (shopRow == 1 && shopCursor == 4) {
                                shopCursor = 0;
                            }
                        }
                    }
                    break;
                case GameState.SETUP_PROMPT:
                    if (lk) {
                        popupImage = null;
                        K();
                    }

                    var3 = false;
                    if (Input.pressed[Input.KEY_SOFT_LEFT]) {
                        if (ll == 0) {
                            Sound.disabled = false;
                        } else {
                            lowGraphics = false;
                        }

                        var3 = true;
                    } else if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
                        if (ll == 0) {
                            Sound.disabled = true;
                        } else {
                            lowGraphics = true;
                        }

                        var3 = true;
                    }

                    if (var3) {
                        Input.clearPressed();
                        Input.clearHeld();
                        if (ll == 0) {
                            try {
                                popupImage = Ui.loadSmileys();
                            } catch (Exception var9) {
                            }

                            ll = 1;
                        } else {
                            lk = true;
                        }
                    }
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
            if (signX[var1] + signWidth[var1] >= cameraX && signX[var1] <= cameraX + viewWidth && signY[var1] >= cameraY && signY[var1] - 14 <= cameraY + viewHeight) {
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
            if (!lowGraphics) {
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
                    jg = (jf = var1 * GameMap.TILE_SIZE - cameraX) - viewCenterX;
                    jh = (ji = (GameMap.TILE_SIZE - 2) * jg / GameMap.TILE_SIZE) + viewCenterX;
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
        if (!lowGraphics) {
            for(int var1 = firstVisibleCol; var1 < lastVisibleCol; ++var1) {
                for(int var2 = firstVisibleRow; var2 < lastVisibleRow; ++var2) {
                    if ((GameMap.flagsAt(var1, var2) & TileFlags.WATER) == TileFlags.WATER) {
                        var0.drawRegion(waterSurfaceImage, 0, (tick % 8 >> 2) * 24, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(var1, var2) & TileFlags.FOREGROUND_BUSH) == TileFlags.FOREGROUND_BUSH) {
                        jg = (jf = var1 * GameMap.TILE_SIZE - cameraX) - viewCenterX;
                        jh = (ji = (GameMap.TILE_SIZE - 1) * jg / GameMap.TILE_SIZE) + viewCenterX;
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

    private static void ai() {
        try {
            logoImage = Ui.loadLogo();
        } catch (Exception var0) {
        }
    }

    private static void resetLoadingProgress() {
        loadingProgress = 0;
        loadingTarget = 0;
        jB = 0;
    }

    private static void J(int var0) {
        if (kb[var0] > 30) {
            int var2 = jX[var0] > 0 ? jJ[var0] : jJ[var0] - kc[var0];
            int var3 = jX[var0] >= 0 ? jJ[var0] + kc[var0] : jJ[var0];
            int var4 = jK[var0] - kd[var0];
            int var1 = jK[var0] + kd[var0];
            if (B == 6 ? false : (fw ? false : (B == 14 ? false : (fe < var2 ? false : (fe > var3 ? false : (ff < var4 ? false : ff <= var1)))))) {
                kb[var0] = 0;
                jO[var0] = 4;
                jT[var0] = 0;
            }
        }

    }

    private static void K(int var0) {
        if (kb[var0] > 30) {
            int var2 = jX[var0] > 0 ? jJ[var0] : jJ[var0] - kc[var0];
            int var3 = jX[var0] >= 0 ? jJ[var0] + kc[var0] : jJ[var0];
            int var4 = jK[var0] - kd[var0];
            int var1 = jK[var0] + kd[var0];
            if (gr == 14 ? false : (gn < var2 ? false : (gn > var3 ? false : (go < var4 ? false : go <= var1)))) {
                kb[var0] = 0;
                jO[var0] = 4;
                jT[var0] = 0;
                kh[var0] = true;
            }
        }

    }

    private static boolean k(int var0, int var1, int var2) {
        if (jO[var0] == 2 || jO[var0] == 3 || jO[var0] == 4 || jO[var0] == 9) {
            int[] var10000 = jY;
            var10000[var0] -= var1;
            if (jY[var0] <= 0) {
                jY[var0] = 0;
                jT[var0] = -5;
                jU[var0] = -(var2 << 3);
                jV[var0] = 0;
                jN[var0] = 0;
                jO[var0] = 1;
                L(var0);
                return true;
            }

            if (jO[var0] == 9) {
                var10000 = jT;
                var10000[var0] -= 7;
                jX[var0] = var2;
            } else {
                jT[var0] = 0;
                jX[var0] = var2;
                jO[var0] = 6;
                jW[var0] = 3;
            }
        }

        return false;
    }

    private static void L(int var0) {
        gainExp(ap[jI[var0]]);
        int var1;
        if ((var1 = tick % 20) > 15) {
            a(jJ[var0], jK[var0], -(jX[var0] << 3), -5, ka[var0] << 2, (tick + 1) % 2);
        } else if (var1 == 15) {
            a(jJ[var0], jK[var0], -(jX[var0] << 2), -4, (kf[var0] << 3) + tick % 10);
        } else {
            var1 = ((jI[var0] << 2) / 10 + 1) * 10;
            a(jJ[var0], jK[var0], -(jX[var0] << 1), -6, var1);
            a(jJ[var0], jK[var0], -(jX[var0] << 4), -3, var1);
        }

        if (QuestState.current != Quest.NONE && Quest.TARGET_MONSTER[QuestState.current] == jI[var0]) {
            ++QuestState.progress;
            showNotice(GameText.monsterNames[Quest.TARGET_MONSTER[QuestState.current]] + ": " + QuestState.progress + "#" + Quest.TARGET_COUNT[QuestState.current]);
        }

        if (jI[var0] == QuestState.dropMonster - QuestState.DROP_MONSTER_BASE && tick % 10 < QuestState.dropChance) {
            placeQuestItem(jJ[var0], jK[var0]);
            f(jJ[var0], jK[var0] - 28);
        }

    }

    public static final void r(int var0) {
        jG = 0;
        boolean var1;
        int var2;
        boolean var4;
        switch (var0) {
            case 0:
                kl = new int[]{7};
                al();
                ki[0] = new int[]{68, 71, 74, 64};
                kj[0] = new int[]{15, 15, 15, 17};
                am();
                M(2);
                q(0, 2);
                break;
            case 1:
                kl = new int[]{1, 7, 0};
                al();
                ki[0] = new int[]{44, 40, 42, 39, 41, 39, 34};
                kj[0] = new int[]{10, 11, 9, 9, 7, 5, 9};
                ki[1] = new int[]{48, 51};
                kj[1] = new int[]{9, 9};
                ki[2] = new int[]{8, 3, 24, 25, 26};
                kj[2] = new int[]{9, 9, 10, 8, 6};
                am();
                M(7);
                u(0, 3);
                q(1, 1);
                s(2, 3);
                break;
            case 2:
                kl = new int[]{1, 6};
                al();
                ki[0] = new int[]{32, 41, 44, 48};
                kj[0] = new int[]{13, 13, 11, 11};
                ki[1] = new int[]{41, 47, 57, 36};
                kj[1] = new int[]{9, 7, 7, 8};
                am();
                M(4);
                u(0, 2);
                o(1, 2);
                break;
            case 3:
                kl = new int[]{1, 6, 0};
                al();
                ki[0] = new int[]{6, 10, 17, 26, 42, 83, 96};
                kj[0] = new int[]{9, 10, 10, 10, 10, 10, 11};
                ki[1] = new int[]{10, 19, 24, 32, 41, 49, 59, 68, 80, 87, 110};
                kj[1] = new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 7};
                ki[2] = new int[]{17, 24, 28, 35, 42, 49, 59, 69, 78, 86, 97, 107, 113};
                kj[2] = new int[]{10, 10, 8, 8, 10, 9, 9, 9, 10, 10, 8, 8, 9};
                am();
                M(20);
                u(0, 4);
                o(1, 8);
                s(2, 8);
                break;
            case 4:
                kl = new int[]{6, 0, 4, 9, 1};
                al();
                ki[0] = new int[]{29, 26, 37, 43, 58, 67, 11, 74, 57};
                kj[0] = new int[]{11, 10, 11, 10, 8, 3, 8, 7, 7};
                ki[1] = new int[]{21, 39, 37, 45, 25, 62, 58};
                kj[1] = new int[]{12, 12, 12, 8, 12, 9, 5};
                ki[2] = new int[]{29, 33, 68, 42, 57};
                kj[2] = new int[]{13, 12, 7, 12, 9};
                ki[3] = new int[]{55, 59, 53};
                kj[3] = new int[]{5, 5, 5};
                ki[4] = new int[]{19, 11};
                kj[4] = new int[]{12, 11};
                am();
                M(20);
                o(0, 6);
                s(1, 6);
                r(2, 4);
                l(3, 2);
                u(4, 2);
                break;
            case 5:
                kl = new int[]{0, 3, 4};
                al();
                ki[0] = new int[]{7, 14, 7, 14, 7, 6, 15, 9};
                kj[0] = new int[]{9, 13, 18, 28, 29, 35, 42, 70};
                ki[1] = new int[]{6, 4, 14, 4, 16, 7, 15, 11};
                kj[1] = new int[]{14, 26, 33, 44, 47, 62, 65, 78};
                ki[2] = new int[]{14, 14, 7, 12, 8, 14, 8};
                kj[2] = new int[]{23, 39, 56, 62, 66, 70, 78};
                am();
                M(20);
                s(0, 7);
                var1 = true;
                var4 = true;

                for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
                }

                if (var2 != jG) {
                    for(var0 = var2; var0 < var2 + 7; ++var0) {
                        kg[var0] = 1;
                        jI[var0] = 3;
                        kc[var0] = 30;
                        kd[var0] = 10;
                        jL[var0] = 27;
                        jM[var0] = 15;
                        jX[var0] = 1;
                        jO[var0] = 5;
                        jT[var0] = var0 << 3;
                        jY[var0] = jZ[var0] = am[jI[var0]];
                        ka[var0] = an[jI[var0]];
                        kf[var0] = ao[jI[var0]];
                        kb[var0] = 0;
                    }
                }

                r(2, 6);
                break;
            case 6:
                kl = new int[]{9, 2, 5};
                al();
                ki[0] = new int[]{42, 47, 72, 82, 85, 93, 104, 130, 139, 151, 165, 182, 189, 200, 207, 214, 225, 225, 237};
                kj[0] = new int[]{10, 8, 10, 8, 6, 6, 6, 9, 9, 7, 4, 7, 9, 10, 10, 8, 4, 10, 8};
                ki[1] = new int[]{39, 40, 41, 72, 73, 74, 132, 106, 107, 130, 131, 142, 149, 157, 168, 182, 183, 194, 214, 215, 216, 236, 39, 40, 56, 57};
                kj[1] = new int[]{5, 5, 6, 6, 4, 3, 2, 3, 4, 5, 5, 5, 2, 1, 3, 4, 5, 6, 6, 5, 4, 5, 6, 6, 6, 6};
                ki[2] = new int[]{45, 64, 83, 101, 119, 147, 173, 220};
                kj[2] = new int[]{6, 8, 4, 3, 6, 7, 5, 4};
                am();
                M(26);
                l(0, 5);
                j(1, 18);
                var1 = true;
                var4 = true;

                for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
                }

                if (var2 != jG) {
                    for(var0 = var2; var0 < var2 + 3; ++var0) {
                        kg[var0] = 2;
                        jI[var0] = 5;
                        kc[var0] = 7;
                        kd[var0] = 10;
                        jL[var0] = 16;
                        jM[var0] = 15;
                        jX[var0] = 0;
                        jO[var0] = 5;
                        jT[var0] = var0 << 3;
                        jY[var0] = jZ[var0] = am[jI[var0]];
                        ka[var0] = an[jI[var0]];
                        kf[var0] = ao[jI[var0]];
                        kb[var0] = 100;
                    }
                }
                break;
            case 7:
                kl = new int[]{9, 8, 10};
                al();
                ki[0] = new int[]{38, 39, 47, 52, 56, 61, 66, 73, 71};
                kj[0] = new int[]{5, 5, 3, 8, 4, 4, 4, 4, 8};
                ki[1] = new int[]{16, 23, 26, 29, 32, 35, 38, 41, 44, 47, 56, 59, 61, 64, 66};
                kj[1] = new int[]{11, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
                ki[2] = new int[]{36, 42, 45, 487, 56, 60, 64};
                kj[2] = new int[]{11, 11, 11, 11, 11, 11, 11};
                am();
                M(23);
                l(0, 7);
                m(1, 10);
                n(2, 6);
                break;
            case 8:
                kl = new int[]{11, 8, 10, 2};
                al();
                ki[0] = new int[]{12, 29, 6, 12, 24, 18, 37, 50, 65, 72, 47};
                kj[0] = new int[]{10, 8, 12, 12, 11, 12, 10, 8, 8, 7, 7};
                ki[1] = new int[]{29, 45, 48, 70, 73};
                kj[1] = new int[]{12, 12, 12, 12, 12};
                ki[2] = new int[]{46, 47, 71, 72};
                kj[2] = new int[]{13, 13, 13, 13};
                ki[3] = new int[]{25, 7, 69, 69, 50, 51};
                kj[3] = new int[]{25, 8, 6, 7, 5, 5};
                am();
                M(22);
                var1 = true;
                var4 = false;

                for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
                }

                if (var2 != jG) {
                    for(var0 = var2; var0 < var2 + 10; ++var0) {
                        kg[var0] = 0;
                        jI[var0] = 11;
                        kc[var0] = 32;
                        kd[var0] = 10;
                        jL[var0] = 27;
                        jM[var0] = 15;
                        jX[var0] = 1;
                        jO[var0] = 5;
                        jT[var0] = jH - var0;
                        jY[var0] = jZ[var0] = am[jI[var0]];
                        ka[var0] = an[jI[var0]];
                        kf[var0] = ao[jI[var0]];
                        kb[var0] = 0;
                    }
                }

                m(1, 4);
                n(2, 3);
                j(3, 5);
                break;
            case 9:
                kl = new int[]{13};
                al();
                ki[0] = new int[]{10, 37, 23, 65};
                kj[0] = new int[]{7, 6, 6, 4};
                am();
                M(3);
                k(0, 3);
                break;
            case 10:
                kl = new int[]{13};
                al();
                ki[0] = new int[]{31, 12, 25, 12};
                kj[0] = new int[]{19, 23, 23, 6};
                am();
                M(3);
                k(0, 3);
                break;
            case 11:
                kl = new int[]{14, 8};
                al();
                ki[0] = new int[]{15, 16, 17, 31, 32, 33, 68, 69, 72};
                kj[0] = new int[]{12, 12, 12, 12, 8, 8, 8, 8, 8};
                ki[1] = new int[]{47, 48, 50, 49};
                kj[1] = new int[]{12, 12, 12, 12};
                am();
                M(10);
                var1 = true;
                var4 = false;

                for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
                }

                if (var2 != jG) {
                    for(var0 = var2; var0 < var2 + 7; ++var0) {
                        kg[var0] = 0;
                        jI[var0] = 14;
                        kc[var0] = 7;
                        kd[var0] = 10;
                        jL[var0] = 16;
                        jM[var0] = 15;
                        jX[var0] = 1;
                        jO[var0] = 5;
                        jT[var0] = var0 << 3;
                        jY[var0] = jZ[var0] = am[jI[var0]];
                        ka[var0] = an[jI[var0]];
                        kf[var0] = ao[jI[var0]];
                        kb[var0] = 500;
                    }
                }

                m(1, 3);
                break;
            case 12:
                kl = new int[]{15, 16, 10};
                al();
                ki[0] = new int[]{11, 18, 20, 9, 9, 17, 18, 22, 31};
                kj[0] = new int[]{7, 5, 10, 14, 17, 15, 20, 19, 21};
                ki[1] = new int[]{5, 10, 5, 10, 14, 21, 16, 37};
                kj[1] = new int[]{6, 12, 15, 19, 16, 17, 9, 17};
                ki[2] = new int[]{25, 26, 27, 34};
                kj[2] = new int[]{23, 23, 23, 23};
                am();
                M(19);
                t(0, 7);
                p(1, 8);
                n(2, 4);
                break;
            case 13:
                kl = new int[]{18, 15, 13};
                al();
                ki[0] = new int[]{6, 12, 30, 34, 34, 43, 54, 53, 70, 76};
                kj[0] = new int[]{9, 11, 11, 10, 5, 5, 5, 11, 11, 11};
                ki[1] = new int[]{11, 16, 18, 29, 50, 38, 48, 58, 62, 74};
                kj[1] = new int[]{11, 9, 7, 5, 11, 5, 5, 5, 5, 11};
                ki[2] = new int[]{22, 41, 42, 61};
                kj[2] = new int[]{9, 8, 2, 8};
                am();
                M(18);
                v(0, 8);
                t(1, 6);
                k(2, 4);
                break;
            case 14:
                kl = new int[]{12, 17, 16};
                al();
                ki[0] = new int[]{7, 12, 16, 25, 28, 45, 50};
                kj[0] = new int[]{4, 7, 11, 14, 8, 14, 9};
                ki[1] = new int[]{14, 18, 29, 28, 29, 53, 47, 51};
                kj[1] = new int[]{7, 11, 12, 8, 4, 14, 9, 4};
                ki[2] = new int[]{4, 41, 26, 26, 34, 41, 49, 56};
                kj[2] = new int[]{4, 9, 11, 2, 2, 5, 7, 2};
                am();
                M(19);
                y(0, 6);
                x(1, 7);
                p(2, 6);
                break;
            case 15:
                kl = new int[]{19, 18, 17};
                al();
                ki[0] = new int[]{22, 7, 19, 32};
                kj[0] = new int[]{28, 25, 28, 14};
                ki[1] = new int[]{3, 13, 31, 35, 24};
                kj[1] = new int[]{6, 25, 25, 21, 27};
                ki[2] = new int[]{35, 29, 24, 17, 13, 2};
                kj[2] = new int[]{8, 11, 9, 7, 7, 17};
                am();
                M(15);
                w(0, 4);
                v(1, 5);
                x(2, 6);
                break;
            case 16:
                kl = new int[]{19, 13};
                al();
                ki[0] = new int[]{15, 33, 53};
                kj[0] = new int[]{12, 10, 10};
                ki[1] = new int[]{20, 39, 59};
                kj[1] = new int[]{10, 8, 8};
                am();
                M(6);
                w(0, 3);
                k(1, 3);
                break;
            case 17:
                kl = new int[]{12};
                al();
                ki[0] = new int[]{8, 14, 39, 45};
                kj[0] = new int[]{12, 12, 9, 16};
                am();
                M(4);
                y(0, 4);
                break;
            case 95:
                kl = new int[]{13};
                al();
                ki[0] = new int[]{7, 10, 14, 18};
                kj[0] = new int[]{8, 8, 8, 8};
                am();
                M(4);
                k(0, 4);
        }

        if (jG > 0) {
            monsterSprites = new Image[kl.length][][];

            for(var0 = 0; var0 < kl.length; ++var0) {
                monsterSprites[var0] = Mob.loadMonster(kl[var0]);
            }
        }

    }

    private static void ak() {
        for(int var0 = 0; var0 < jG; ++var0) {
            int var10002;
            if (kb[var0] <= 30) {
                var10002 = kb[var0]++;
            }

            if (jN[var0] > 0) {
                var10002 = jN[var0]--;
            }

            int[] var10000;
            switch (jO[var0]) {
                case 1:
                    var10002 = jT[var0]++;
                    var10000 = jK;
                    var10000[var0] += jT[var0];
                    if (tick % 2 == 0) {
                        if (jU[var0] > 1) {
                            var10002 = jU[var0]--;
                        } else if (jU[var0] < -1) {
                            var10002 = jU[var0]++;
                        }
                    }

                    var10000 = jJ;
                    var10000[var0] += jU[var0];
                    jW[var0] = 2;
                    if (jK[var0] < GameMap.pixelHeight && jJ[var0] >= 0 && jJ[var0] < GameMap.pixelWidth) {
                        if ((jV[var0] == 0 || jV[var0] < 3 && jT[var0] > 7) && (GameMap.flagsAtPixel(jJ[var0], jK[var0]) & TileFlags.SOLID) == TileFlags.SOLID) {
                            jT[var0] = jT[var0] > 4 ? -4 : -jT[var0];
                            var10002 = jV[var0]++;
                        }

                        if (GameMap.hasFlag(jJ[var0], jK[var0], TileFlags.WATER) && jK[var0] % GameMap.TILE_SIZE > 8) {
                            g(jJ[var0], H(jK[var0]) + 8);
                        }
                        break;
                    }

                    jT[var0] = -300;
                    jO[var0] = 5;
                    if (U) {
                        jO[var0] = 8;
                    }

                    kk[kg[var0]][ke[var0]] = false;
                    break;
                case 2:
                    switch (jI[var0]) {
                        case 0:
                            if (tick % 4 == 0) {
                                var10002 = jW[var0]++;
                                if (jW[var0] > 1) {
                                    jW[var0] = 0;
                                }
                            }

                            var10000 = jJ;
                            var10000[var0] += jX[var0];
                            if (jJ[var0] > jQ[var0]) {
                                jX[var0] = -1;
                            } else if (jJ[var0] < jP[var0]) {
                                jX[var0] = 1;
                            }

                            J(var0);
                            if (tick % 9 == 0) {
                                K(var0);
                            }
                            continue;
                        case 1:
                            U(var0);
                            continue;
                        case 2:
                            var10000 = jJ;
                            var10000[var0] += jR[var0];
                            var10000 = jK;
                            var10000[var0] += jS[var0];
                            if (jJ[var0] - jP[var0] > 40) {
                                jR[var0] = -(tick % 3 + 1);
                            }

                            if (jJ[var0] - jP[var0] < -40) {
                                jR[var0] = tick % 3 + 1;
                            }

                            if (jK[var0] - jQ[var0] > 40) {
                                jS[var0] = -(tick % 3 + 1);
                            }

                            if (jK[var0] - jQ[var0] < -40) {
                                jS[var0] = tick % 3 + 1;
                            }

                            if (tick % 15 == 0) {
                                jR[var0] = tick % 4 - 1;
                                jS[var0] = (tick >> 1) % 4 - 1;
                            }

                            if (tick % 2 == 0) {
                                var10002 = jW[var0]++;
                                if (jW[var0] > 1) {
                                    jW[var0] = 0;
                                }

                                if (jW[var0] == 0) {
                                    var10000 = jK;
                                    var10000[var0] -= 3;
                                } else {
                                    var10000 = jK;
                                    var10000[var0] += 2;
                                }
                            }

                            J(var0);
                            continue;
                        case 3:
                            var10000 = jJ;
                            var10000[var0] += jT[var0];
                            if (jT[var0] > 1) {
                                var10002 = jT[var0]--;
                            } else if (jT[var0] < -1) {
                                var10002 = jT[var0]++;
                            }

                            var10000 = jK;
                            var10000[var0] += jU[var0];
                            var10002 = jU[var0]++;
                            if (jU[var0] > 4) {
                                jU[var0] = -4;
                                jT[var0] = jX[var0] * 3;
                            }

                            if (a(jU[var0]) == 3) {
                                if (jJ[var0] > jQ[var0]) {
                                    jX[var0] = -1;
                                } else if (jJ[var0] < jP[var0]) {
                                    jX[var0] = 1;
                                }

                                jW[var0] = 0;
                            } else {
                                jW[var0] = 1;
                            }

                            J(var0);
                            if (tick % 9 == 0) {
                                K(var0);
                            }
                            continue;
                        case 4:
                            var10000 = jJ;
                            var10000[var0] += jV[var0];
                            if (jV[var0] > 1) {
                                var10002 = jV[var0]--;
                            } else if (jV[var0] < -1) {
                                var10002 = jV[var0]++;
                            }

                            var10000 = jK;
                            var10000[var0] += jU[var0];
                            var10002 = jU[var0]++;
                            if (jU[var0] > 3) {
                                jU[var0] = -3;
                                jV[var0] = jX[var0] << 2;
                                jT[var0] = 0;
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 3;
                            }

                            if (jU[var0] == -2) {
                                if (jJ[var0] > jQ[var0]) {
                                    jX[var0] = -1;
                                } else if (jJ[var0] < jP[var0]) {
                                    jX[var0] = 1;
                                }

                                jV[var0] = jX[var0] * 6;
                                jW[var0] = 0;
                            } else {
                                jW[var0] = 1;
                            }

                            J(var0);
                            if (tick % 9 == 0) {
                                K(var0);
                            }
                            continue;
                        case 5:
                            if (tick % 4 == 0) {
                                var10002 = jW[var0]++;
                                if (jW[var0] > 1) {
                                    jW[var0] = 0;
                                }

                                var10000 = jK;
                                var10000[var0] += jT[var0];
                            }

                            if (jK[var0] > jQ[var0]) {
                                jT[var0] = -1;
                            } else if (jK[var0] < jP[var0]) {
                                jT[var0] = 1;
                            }

                            J(var0);
                            continue;
                        case 6:
                            P(var0);
                        case 7:
                        default:
                            continue;
                        case 8:
                            var10002 = jW[var0]++;
                            if (jW[var0] > 1) {
                                jW[var0] = 0;
                            }

                            var10000 = jJ;
                            var10000[var0] += tick % 5 * jX[var0];
                            if (jJ[var0] > jQ[var0]) {
                                jX[var0] = -1;
                            } else if (jJ[var0] < jP[var0]) {
                                jX[var0] = 1;
                            }

                            J(var0);
                            continue;
                        case 9:
                            Q(var0);
                            continue;
                        case 10:
                            var10002 = jW[var0]++;
                            if (jW[var0] > 1) {
                                jW[var0] = 0;
                            }

                            var10000 = jJ;
                            var10000[var0] += 5 * jX[var0];
                            if (jJ[var0] > jQ[var0]) {
                                jX[var0] = -1;
                            } else if (jJ[var0] < jP[var0]) {
                                jX[var0] = 1;
                            }

                            J(var0);
                            continue;
                        case 11:
                            if (tick % 4 == 0) {
                                var10002 = jW[var0]++;
                                jT[var0] = 0;
                                if (jW[var0] > 1) {
                                    jW[var0] = 0;
                                    jT[var0] = 1;
                                }
                            }

                            var10000 = jJ;
                            var10000[var0] += jT[var0] * jX[var0];
                            if (jJ[var0] > jQ[var0]) {
                                jX[var0] = -1;
                            } else if (jJ[var0] < jP[var0]) {
                                jX[var0] = 1;
                            }

                            J(var0);
                            if (tick % 9 == 0) {
                                K(var0);
                            }
                            continue;
                        case 12:
                            Q(var0);
                            continue;
                        case 13:
                            var10000 = jJ;
                            var10000[var0] += jR[var0];
                            if (jJ[var0] - jP[var0] > 100) {
                                jX[var0] = -1;
                                jR[var0] = jX[var0] * (tick % 4 + 1);
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 2;
                            } else if (jJ[var0] - jP[var0] < -100) {
                                jX[var0] = 1;
                                jR[var0] = jX[var0] * (tick % 4 + 1);
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 2;
                            }

                            if (jK[var0] - jQ[var0] > 20) {
                                jS[var0] = -(tick % 4 + 1);
                            } else if (jK[var0] - jQ[var0] < -20) {
                                jS[var0] = tick % 4 + 1;
                            } else {
                                jS[var0] = 0;
                            }

                            var10000 = jK;
                            var10000[var0] += jS[var0];
                            if (tick % 2 == 0) {
                                var10002 = jW[var0]++;
                                if (jW[var0] > 1) {
                                    jW[var0] = 0;
                                    var10000 = jK;
                                    var10000[var0] -= 12;
                                } else {
                                    var10000 = jK;
                                    var10000[var0] += 12;
                                }
                            }

                            J(var0);
                            continue;
                        case 14:
                            var10002 = jW[var0]++;
                            if (jW[var0] > 1) {
                                jW[var0] = 0;
                            }

                            if (tick % 2 == 0) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 2;
                            } else {
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 1;
                            }

                            if (jJ[var0] > jQ[var0]) {
                                jX[var0] = -1;
                            } else if (jJ[var0] < jP[var0]) {
                                jX[var0] = 1;
                            }

                            J(var0);
                            if (tick % 5 == 0) {
                                K(var0);
                            }
                            continue;
                        case 15:
                            var10000 = jJ;
                            var10000[var0] += jT[var0];
                            if (jT[var0] > 1) {
                                var10002 = jT[var0]--;
                            } else if (jT[var0] < -1) {
                                var10002 = jT[var0]++;
                            }

                            var10000 = jK;
                            var10000[var0] += jU[var0];
                            var10002 = jU[var0]++;
                            if (jU[var0] > 4) {
                                jU[var0] = -4;
                                jT[var0] = jX[var0] * 3;
                            }

                            if (a(jU[var0]) == 3) {
                                if (jJ[var0] > jQ[var0]) {
                                    jX[var0] = -1;
                                } else if (jJ[var0] < jP[var0]) {
                                    jX[var0] = 1;
                                }

                                jW[var0] = 0;
                            } else {
                                jW[var0] = 1;
                            }

                            J(var0);
                            if (tick % 9 == 0) {
                                K(var0);
                            }
                            continue;
                        case 16:
                            P(var0);
                            continue;
                        case 17:
                            U(var0);
                            continue;
                        case 18:
                        case 19:
                            if (tick % 4 == 0) {
                                var10002 = jW[var0]++;
                                if (jW[var0] > 1) {
                                    jW[var0] = 0;
                                }
                            }

                            if (jJ[var0] > fe) {
                                jX[var0] = -1;
                            } else {
                                jX[var0] = 1;
                            }

                            J(var0);
                            if (tick % 9 == 0) {
                                K(var0);
                            }
                            continue;
                    }
                case 3:
                    var10002 = jT[var0]++;
                    if (jT[var0] >= 35) {
                        jT[var0] = 0;
                        jO[var0] = 2;
                    }

                    jW[var0] = 0;
                    J(var0);
                    break;
                case 4:
                    if (B == 14 || fw) {
                        jW[var0] = 0;
                        jO[var0] = 2;
                        jT[var0] = 0;
                        jU[var0] = 0;
                    }

                    switch (jI[var0]) {
                        case 0:
                            var10002 = jT[var0]++;
                            if (jT[var0] == 2) {
                                if (kh[var0]) {
                                    kh[var0] = false;
                                    b(ka[var0], jX[var0]);
                                } else {
                                    a(ka[var0], jX[var0]);
                                }
                            }

                            if ((jT[var0] >> 2) % 2 == 0) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0];
                                jW[var0] = 3;
                            } else {
                                jW[var0] = 0;
                                var10000 = jJ;
                                var10000[var0] -= jX[var0];
                            }

                            if (jT[var0] == 12) {
                                jO[var0] = 3;
                                jT[var0] = 0;
                            }
                            continue;
                        case 1:
                            a(ka[var0], -fm);
                            jO[var0] = 3;
                            jT[var0] = 0;
                            jW[var0] = 2;
                            continue;
                        case 2:
                            if (jJ[var0] < fe) {
                                jR[var0] = 3 + tick % 4;
                            } else if (jJ[var0] > fe) {
                                jR[var0] = -(3 + tick % 4);
                            }

                            if (jK[var0] < ff) {
                                jS[var0] = tick % 3;
                            } else if (jK[var0] > ff) {
                                jS[var0] = -tick % 3;
                            }

                            var10000 = jJ;
                            var10000[var0] += jR[var0];
                            var10000 = jK;
                            var10000[var0] += jS[var0];
                            if (tick % 2 == 0) {
                                var10002 = jW[var0]++;
                                if (jW[var0] > 1) {
                                    jW[var0] = 0;
                                }
                            }

                            if (a(jJ[var0] - fe) < 5 && a(jK[var0] - ff) < 5) {
                                kb[var0] = 0;
                                a(ka[var0], -fm);
                                jT[var0] = 0;
                                jO[var0] = 2;
                            }

                            if (a(jJ[var0] - fe) > 160 || a(jK[var0] - ff) > 160) {
                                jO[var0] = 2;
                                jT[var0] = 0;
                                jR[var0] = jS[var0] = 0;
                                kb[var0] = 0;
                            }
                            continue;
                        case 3:
                            var10002 = jT[var0]++;
                            if (jT[var0] == 3) {
                                if (kh[var0]) {
                                    kh[var0] = false;
                                    b(ka[var0], jX[var0]);
                                } else {
                                    a(ka[var0], jX[var0]);
                                }
                            }

                            if ((jT[var0] >> 2) % 2 == 0) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0];
                                jW[var0] = 0;
                            } else {
                                jW[var0] = 3;
                                var10000 = jJ;
                                var10000[var0] -= jX[var0];
                            }

                            if (jT[var0] == 12) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 3;
                                jU[var0] = -4;
                                jT[var0] = 0;
                            }
                            continue;
                        case 4:
                            var10002 = jT[var0]++;
                            if (jT[var0] == 3) {
                                if (kh[var0]) {
                                    kh[var0] = false;
                                    b(ka[var0], jX[var0]);
                                } else {
                                    a(ka[var0], jX[var0]);
                                }
                            }

                            if ((jT[var0] >> 2) % 2 == 0) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0];
                                jW[var0] = 0;
                            } else {
                                jW[var0] = 1;
                                var10000 = jJ;
                                var10000[var0] -= jX[var0];
                            }

                            if (jT[var0] == 12) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 3;
                                jU[var0] = -4;
                                jT[var0] = 0;
                            }
                            continue;
                        case 5:
                            a(ka[var0], -fm);
                            jO[var0] = 2;
                            jT[var0] = 1;
                            continue;
                        case 6:
                            N(var0);
                        case 7:
                        default:
                            continue;
                        case 8:
                            var10002 = jT[var0]++;
                            if (jT[var0] <= 10) {
                                if (jK[var0] > ff) {
                                    var10000 = jK;
                                    var10000[var0] -= 4;
                                }

                                if (jJ[var0] < fe) {
                                    var10000 = jJ;
                                    var10000[var0] += 2;
                                } else if (jJ[var0] > fe) {
                                    var10000 = jJ;
                                    var10000[var0] -= 2;
                                }

                                if (a(jJ[var0] - fe) < 16 && a(jK[var0] - (ff - 16)) < 20) {
                                    a(ka[var0], -fm);
                                    jT[var0] = 10;
                                }
                            }

                            if (jT[var0] == 10) {
                                jU[var0] = 1;
                            }

                            if (jT[var0] > 10) {
                                var10000 = jK;
                                var10000[var0] += jU[var0];
                                var10002 = jU[var0]++;
                                if (jK[var0] > kj[kg[var0]][ke[var0]]) {
                                    jK[var0] = kj[kg[var0]][ke[var0]];
                                    jU[var0] = 0;
                                    jT[var0] = 0;
                                    jO[var0] = 2;
                                }
                            }

                            jW[var0] = jT[var0] % 2 == 0 ? 0 : 1;
                            continue;
                        case 9:
                            var10002 = jT[var0]++;
                            if (jT[var0] < 6) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 2;
                            } else if (jT[var0] == 6) {
                                if (a(jJ[var0] + (jX[var0] << 3) - fe) < 13 + fi) {
                                    a(ka[var0], jX[var0]);
                                }
                            } else if (jT[var0] < 12) {
                                var10000 = jJ;
                                var10000[var0] -= jX[var0] << 1;
                            } else if (jT[var0] == 12) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 3;
                                jU[var0] = -4;
                                jT[var0] = 0;
                            }

                            jW[var0] = 1;
                            if (jT[var0] > 3 && jT[var0] < 9 && jT[var0] % 2 == 0) {
                                jW[var0] = 3;
                            }
                            continue;
                        case 10:
                            var10002 = jT[var0]++;
                            jW[var0] = 3;
                            if (jT[var0] <= 15) {
                                var10000 = jK;
                                var10000[var0] -= 7;
                                if (jJ[var0] < fe) {
                                    var10002 = jJ[var0]++;
                                } else if (jJ[var0] > fe) {
                                    var10002 = jJ[var0]--;
                                }

                                if (a(jJ[var0] - fe) < 16 && a(jK[var0] - (ff - 16)) < 20) {
                                    a(ka[var0], -fm);
                                    jT[var0] = 15;
                                }
                            }

                            if (jT[var0] == 15) {
                                jU[var0] = 1;
                            }

                            if (jT[var0] > 15) {
                                jW[var0] = 2;
                                var10000 = jK;
                                var10000[var0] += jU[var0];
                                var10002 = jU[var0]++;
                                if (a(jJ[var0] - fe) < 16 && a(jK[var0] - (ff - 16)) < 20) {
                                    a(ka[var0], -fm);
                                }

                                if (jK[var0] > kj[kg[var0]][ke[var0]]) {
                                    jK[var0] = kj[kg[var0]][ke[var0]];
                                    jU[var0] = 0;
                                    jT[var0] = 0;
                                    jO[var0] = 2;
                                }
                            }
                            continue;
                        case 11:
                            var10002 = jT[var0]++;
                            if (jT[var0] == 4 && jK[var0] == ff) {
                                a(ka[var0], jX[var0]);
                            }

                            if (jT[var0] < 8) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 1;
                                jW[var0] = 3;
                            } else {
                                jW[var0] = 0;
                                var10000 = jJ;
                                var10000[var0] -= jX[var0] << 1;
                            }

                            if (jT[var0] == 12) {
                                jO[var0] = 3;
                                jT[var0] = 0;
                                kb[var0] = 25;
                            }
                            continue;
                        case 12:
                            R(var0);
                            continue;
                        case 13:
                            if (jJ[var0] < fe) {
                                jR[var0] = 4 + tick % 6;
                            } else if (jJ[var0] > fe) {
                                jR[var0] = -(4 + tick % 6);
                            }

                            if (jK[var0] < ff) {
                                jS[var0] = 2 + tick % 4;
                            } else if (jK[var0] > ff) {
                                jS[var0] = -(2 + tick % 4);
                            }

                            var10000 = jJ;
                            var10000[var0] += jR[var0];
                            var10000 = jK;
                            var10000[var0] += jS[var0];
                            if (tick % 2 == 0) {
                                var10002 = jW[var0]++;
                                if (jW[var0] > 1) {
                                    jW[var0] = 0;
                                }
                            }

                            if (a(jJ[var0] - fe) < 5 && a(jK[var0] - ff) < 5) {
                                a(ka[var0], -fm);
                                jO[var0] = 2;
                                jT[var0] = 0;
                                jX[var0] = jJ[var0] > jP[var0] ? -1 : 1;
                                jR[var0] = jX[var0] * (tick % 4 + 1);
                                kb[var0] = 10;
                            }

                            if (a(jJ[var0] - fe) > 130 || a(jK[var0] - ff) > 130) {
                                jO[var0] = 2;
                                jT[var0] = 0;
                                jR[var0] = jS[var0] = 0;
                                jX[var0] = jJ[var0] > jP[var0] ? -1 : 1;
                                jR[var0] = jX[var0] * (tick % 4 + 1);
                                kb[var0] = 0;
                            }
                            continue;
                        case 14:
                            R(var0);
                            continue;
                        case 15:
                            var10002 = jT[var0]++;
                            if (jT[var0] == 3) {
                                if (kh[var0]) {
                                    kh[var0] = false;
                                    b(ka[var0], jX[var0]);
                                } else {
                                    a(ka[var0], jX[var0]);
                                }
                            }

                            if ((jT[var0] >> 2) % 2 == 0) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0];
                                jW[var0] = 0;
                            } else {
                                jW[var0] = 3;
                                var10000 = jJ;
                                var10000[var0] -= jX[var0];
                            }

                            if (jT[var0] == 12) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 3;
                                jU[var0] = -4;
                                jT[var0] = 0;
                            }
                            continue;
                        case 16:
                            N(var0);
                            continue;
                        case 17:
                        case 18:
                            var10002 = jT[var0]++;
                            jW[var0] = 3;
                            if (jT[var0] == 3) {
                                b(jJ[var0] + (jX[var0] << 3), jK[var0] - 12, jX[var0] << 3, ka[var0], 2);
                            } else if (jT[var0] == 6) {
                                jO[var0] = 3;
                                jT[var0] = 0;
                            }
                            continue;
                        case 19:
                            var10002 = jT[var0]++;
                            jW[var0] = 3;
                            if (jT[var0] == 3) {
                                var10002 = jU[var0]++;
                                if (jU[var0] == 3) {
                                    jU[var0] = 0;
                                }

                                if (jU[var0] == 2) {
                                    c(jJ[var0], jK[var0] - 22, jX[var0], -10, ka[var0]);
                                } else if (jU[var0] == 0) {
                                    c(jJ[var0], jK[var0] - 22, jX[var0] << 1, -9, ka[var0]);
                                } else {
                                    c(jJ[var0], jK[var0] - 22, 0, -11, ka[var0]);
                                }

                                g(jJ[var0], jK[var0] - 20);
                            } else if (jT[var0] == 6) {
                                jO[var0] = 3;
                                jT[var0] = 0;
                            }
                            continue;
                    }
                case 5:
                    var10002 = jT[var0]++;
                    if (jT[var0] >= jH) {
                        jT[var0] = 0;
                        int var2 = tick % ki[kg[var0]].length;
                        if (kk[kg[var0]][var2]) {
                            jT[var0] = jH;
                        } else {
                            jJ[var0] = ki[kg[var0]][var2];
                            jK[var0] = kj[kg[var0]][var2];
                            kk[kg[var0]][var2] = true;
                            ke[var0] = var2;
                            jO[var0] = 2;
                            jY[var0] = jZ[var0];
                            switch (jI[var0]) {
                                case 0:
                                    jP[var0] = jJ[var0] - 20;
                                    jQ[var0] = jJ[var0] + 20;
                                    break;
                                case 1:
                                    jP[var0] = jJ[var0] - 10;
                                    jQ[var0] = jJ[var0] + 10;
                                    break;
                                case 2:
                                    jP[var0] = jJ[var0] + 12;
                                    jQ[var0] = jK[var0] + 12;
                                    break;
                                case 3:
                                    jP[var0] = jJ[var0] - 20;
                                    jQ[var0] = jJ[var0] + 20;
                                    jU[var0] = -4;
                                case 4:
                                    jP[var0] = jJ[var0] - 20;
                                    jQ[var0] = jJ[var0] + 20;
                                    jU[var0] = -3;
                                    break;
                                case 5:
                                    jP[var0] = jK[var0] - 10;
                                    jQ[var0] = jK[var0] + 10;
                                    jT[var0] = 1;
                                    break;
                                case 6:
                                    jP[var0] = jJ[var0];
                                    jQ[var0] = jK[var0];
                                    break;
                                case 7:
                                    jO[var0] = 3;
                                    break;
                                case 8:
                                case 9:
                                case 11:
                                case 12:
                                    jP[var0] = jJ[var0] - 20;
                                    jQ[var0] = jJ[var0] + 20;
                                    break;
                                case 10:
                                    jP[var0] = jJ[var0] - 30;
                                    jQ[var0] = jJ[var0] + 30;
                                    break;
                                case 13:
                                    jP[var0] = jJ[var0] + 12;
                                    jQ[var0] = jK[var0] + 12;
                                    break;
                                case 14:
                                    jP[var0] = jJ[var0] - 40;
                                    jQ[var0] = jJ[var0] + 40;
                                    break;
                                case 15:
                                    jP[var0] = jJ[var0] - 20;
                                    jQ[var0] = jJ[var0] + 20;
                                    jU[var0] = -4;
                                    break;
                                case 16:
                                    jP[var0] = jJ[var0];
                                    jQ[var0] = jK[var0];
                                    break;
                                case 17:
                                    jP[var0] = jJ[var0] - 30;
                                    jQ[var0] = jJ[var0] + 30;
                            }
                        }
                    }
                    break;
                case 6:
                    jN[var0] = 100;
                    jW[var0] = 2;
                    switch (jI[var0]) {
                        case 0:
                            if (kb[var0] < 30) {
                                var10000 = jJ;
                                var10000[var0] -= jX[var0] << 1;
                            } else {
                                var10000 = jJ;
                                var10000[var0] -= jX[var0];
                            }

                            var10002 = jT[var0]++;
                            if (jT[var0] == 1) {
                                var10000 = jK;
                                var10000[var0] -= 2;
                            } else if (jT[var0] == 5) {
                                var10000 = jK;
                                var10000[var0] += 2;
                                jO[var0] = 2;
                            }
                            break;
                        case 1:
                            S(var0);
                            break;
                        case 2:
                            var10002 = jT[var0]++;
                            var10000 = jJ;
                            var10000[var0] -= jR[var0] >> 3;
                            var10000 = jK;
                            var10000[var0] -= jS[var0] >> 3;
                            if (jR[var0] == 0) {
                                jR[var0] = 2;
                            }

                            if (jS[var0] == 0) {
                                jS[var0] = 2;
                            }

                            if (jT[var0] == 5) {
                                jO[var0] = 2;
                                jT[var0] = 0;
                                jR[var0] = jS[var0] = 0;
                                kb[var0] = 10;
                            }
                            break;
                        case 3:
                            var10000 = jJ;
                            var10000[var0] -= jX[var0];
                            jW[var0] = 2;
                            var10002 = jT[var0]++;
                            if (jT[var0] == 7) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 2;
                                jU[var0] = -4;
                                jT[var0] = jX[var0] * 3;
                            }
                            break;
                        case 4:
                            var10000 = jJ;
                            var10000[var0] -= jX[var0];
                            jW[var0] = 2;
                            var10002 = jT[var0]++;
                            if (jT[var0] == 7) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 2;
                                jU[var0] = -4;
                                jT[var0] = jX[var0] * 3;
                            }
                            break;
                        case 5:
                            var10002 = jT[var0]++;
                            var10002 = jK[var0]--;
                            if (jT[var0] == 3) {
                                jO[var0] = 2;
                                jT[var0] = 1;
                            }

                            jW[var0] = 2;
                            break;
                        case 6:
                            O(var0);
                            break;
                        case 7:
                            var10002 = jT[var0]++;
                            jX[var0] = -fm;
                            if (jT[var0] % 4 > 1) {
                                jW[var0] = 0;
                            } else {
                                jW[var0] = 2;
                            }

                            if (jT[var0] == 8) {
                                jO[var0] = 3;
                                jX[var0] = 0;
                                jR[var0] = jS[var0] = 0;
                                kb[var0] = 0;
                            }
                            break;
                        case 8:
                            jO[var0] = 4;
                            break;
                        case 9:
                            var10000 = jJ;
                            var10000[var0] -= jX[var0];
                            jW[var0] = 2;
                            var10002 = jT[var0]++;
                            if (jT[var0] == 6) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 4;
                                jT[var0] = 4;
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 3;
                            }
                            break;
                        case 10:
                            jO[var0] = 4;
                            break;
                        case 11:
                            var10000 = jJ;
                            var10000[var0] -= jX[var0];
                            jW[var0] = 2;
                            var10002 = jT[var0]++;
                            if (jT[var0] == 6) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 4;
                                jT[var0] = 0;
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 3;
                            }
                            break;
                        case 12:
                            S(var0);
                            break;
                        case 13:
                            var10002 = jT[var0]++;
                            var10000 = jJ;
                            var10000[var0] -= jR[var0] >> 3;
                            var10000 = jK;
                            var10000[var0] -= jS[var0] >> 3;
                            if (jR[var0] == 0) {
                                jR[var0] = 2;
                            }

                            if (jS[var0] == 0) {
                                jS[var0] = 2;
                            }

                            if (jT[var0] == 5) {
                                jO[var0] = 2;
                                jT[var0] = 0;
                                jX[var0] = jJ[var0] > jP[var0] ? -1 : 1;
                                jR[var0] = jX[var0] << 3;
                                kb[var0] = 0;
                            }
                            break;
                        case 14:
                            T(var0);
                            break;
                        case 15:
                            var10000 = jJ;
                            var10000[var0] -= jX[var0];
                            jW[var0] = 2;
                            var10002 = jT[var0]++;
                            if (jT[var0] == 3) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 2;
                                jU[var0] = -4;
                                jT[var0] = jX[var0] * 3;
                            }
                            break;
                        case 16:
                            O(var0);
                            break;
                        case 17:
                            T(var0);
                            break;
                        case 18:
                            var10002 = jT[var0]++;
                            var10000 = jJ;
                            var10000[var0] -= jX[var0];
                            if (jT[var0] == 4) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 2;
                                jO[var0] = 2;
                            }
                            break;
                        case 19:
                            var10002 = jT[var0]++;
                            var10000 = jJ;
                            var10000[var0] -= jX[var0];
                            if (jT[var0] == 4) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 2;
                                jT[var0] = 0;
                                jO[var0] = 4;
                            }
                    }
                case 7:
                case 8:
                default:
                    break;
                case 9:
                    var10000 = jK;
                    var10000[var0] += jT[var0];
                    var10002 = jT[var0]++;
                    jW[var0] = 2;
                    if (jK[var0] > kj[kg[var0]][ke[var0]]) {
                        if (jI[var0] != 2 && jI[var0] != 6 && jI[var0] != 16 && jI[var0] != 13) {
                            jK[var0] = kj[kg[var0]][ke[var0]];
                        }

                        jO[var0] = 3;
                    }
            }
        }

    }

    private static void j(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 2;
                kc[var3] = 120;
                kd[var3] = 100;
                jL[var3] = 20;
                jM[var3] = 30;
                jX[var3] = 0;
                jO[var3] = 5;
                jT[var3] = var3 * 7 + var3;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 100;
                jR[var3] = 2;
                jS[var3] = 3;
            }

        }
    }

    private static void k(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 13;
                kc[var3] = 120;
                kd[var3] = 50;
                jL[var3] = 20;
                jM[var3] = 20;
                jX[var3] = -1;
                jO[var3] = 5;
                jT[var3] = var3 << 1;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 100;
                jR[var3] = -3;
                jS[var3] = 3;
            }

        }
    }

    private static void l(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 9;
                kc[var3] = 36;
                kd[var3] = 20;
                jL[var3] = 34;
                jM[var3] = 32;
                jX[var3] = 1;
                jO[var3] = 5;
                jT[var3] = var3 * 6;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 100;
            }

        }
    }

    private static void m(int var0, int var1) {
        for(var0 = 0; var0 < jG && jI[var0] != -1; ++var0) {
        }

        if (var0 != jG) {
            for(int var2 = var0; var2 < var0 + var1; ++var2) {
                kg[var2] = 1;
                jI[var2] = 8;
                kc[var2] = 40;
                kd[var2] = 30;
                jL[var2] = 12;
                jM[var2] = 16;
                int[] var10000 = jK;
                var10000[var2] += 5;
                jX[var2] = 1;
                jO[var2] = 5;
                jT[var2] = var2 * 5;
                jY[var2] = jZ[var2] = am[jI[var2]];
                ka[var2] = an[jI[var2]];
                kf[var2] = ao[jI[var2]];
                kb[var2] = 60;
            }

        }
    }

    private static void n(int var0, int var1) {
        for(var0 = 0; var0 < jG && jI[var0] != -1; ++var0) {
        }

        if (var0 != jG) {
            for(int var2 = var0; var2 < var0 + var1; ++var2) {
                kg[var2] = 2;
                jI[var2] = 10;
                kc[var2] = 80;
                kd[var2] = 100;
                jL[var2] = 12;
                jM[var2] = 16;
                jX[var2] = 1;
                jO[var2] = 5;
                jT[var2] = var2 * 5;
                jY[var2] = jZ[var2] = am[jI[var2]];
                ka[var2] = an[jI[var2]];
                kf[var2] = ao[jI[var2]];
                kb[var2] = 40;
            }

        }
    }

    private static void o(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 6;
                kc[var3] = 60;
                kd[var3] = 60;
                jL[var3] = 16;
                jM[var3] = 16;
                jX[var3] = -1;
                jO[var3] = 5;
                jT[var3] = var3 << 3;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 100;
                jR[var3] = 2;
                jS[var3] = 3;
            }

        }
    }

    private static void p(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 16;
                kc[var3] = 60;
                kd[var3] = 60;
                jL[var3] = 16;
                jM[var3] = 16;
                jX[var3] = -1;
                jO[var3] = 5;
                jT[var3] = var3 + 60;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 100;
                jR[var3] = 2;
                jS[var3] = 3;
            }

        }
    }

    private static void q(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 7;
                kc[var3] = 10;
                kd[var3] = 10;
                jL[var3] = 30;
                jM[var3] = 25;
                jX[var3] = 1;
                jO[var3] = 5;
                jT[var3] = var3 << 2;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 0;
            }

        }
    }

    private static void r(int var0, int var1) {
        for(var0 = 0; var0 < jG && jI[var0] != -1; ++var0) {
        }

        if (var0 != jG) {
            for(int var2 = var0; var2 < var0 + var1; ++var2) {
                kg[var2] = 2;
                jI[var2] = 4;
                kc[var2] = 20;
                kd[var2] = 10;
                jL[var2] = 18;
                jM[var2] = 18;
                jX[var2] = 1;
                jO[var2] = 5;
                jT[var2] = var2 << 3;
                jY[var2] = jZ[var2] = am[jI[var2]];
                ka[var2] = an[jI[var2]];
                kf[var2] = ao[jI[var2]];
                kb[var2] = 0;
            }

        }
    }

    private static void al() {
        ki = new int[kl.length][];
        kj = new int[kl.length][];
        kk = new boolean[kl.length][];
    }

    private static void M(int var0) {
        jG = var0;
        jH = 70;
        jI = new int[jG];
        kg = new int[jG];
        jJ = new int[jG];
        jK = new int[jG];
        jO = new int[jG];
        jP = new int[jG];
        jQ = new int[jG];
        jR = new int[jG];
        jS = new int[jG];
        jT = new int[jG];
        jU = new int[jG];
        jV = new int[jG];
        jW = new int[jG];
        jX = new int[jG];
        jY = new int[jG];
        kh = new boolean[jG];
        jZ = new int[jG];
        ka = new int[jG];
        kb = new int[jG];
        jL = new int[jG];
        jM = new int[jG];
        jM = new int[jG];
        jN = new int[jG];
        kc = new int[jG];
        kd = new int[jG];
        ke = new int[jG];
        kf = new int[jG];

        for(var0 = 0; var0 < jG; ++var0) {
            jI[var0] = -1;
        }

    }

    private static void s(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 0;
                kc[var3] = 25;
                kd[var3] = 10;
                jL[var3] = 27;
                jM[var3] = 15;
                jX[var3] = 1;
                jO[var3] = 5;
                jT[var3] = var3 * 7;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 0;
            }

        }
    }

    private static void t(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 15;
                kc[var3] = 36;
                kd[var3] = 10;
                jL[var3] = 27;
                jM[var3] = 15;
                jX[var3] = 1;
                jO[var3] = 5;
                jT[var3] = var3 << 1;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 0;
            }

        }
    }

    private static void u(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 1;
                kc[var3] = 7;
                kd[var3] = 10;
                jL[var3] = 16;
                jM[var3] = 15;
                jX[var3] = 1;
                jO[var3] = 5;
                jT[var3] = var3 << 3;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 100;
            }

        }
    }

    private static void v(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 18;
                kc[var3] = 120;
                kd[var3] = 10;
                jL[var3] = 24;
                jM[var3] = 20;
                jX[var3] = 1;
                jO[var3] = 5;
                jT[var3] = 60;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 100;
            }

        }
    }

    private static void w(int var0, int var1) {
        for(var0 = 0; var0 < jG && jI[var0] != -1; ++var0) {
        }

        if (var0 != jG) {
            for(int var2 = var0; var2 < var0 + var1; ++var2) {
                kg[var2] = 0;
                jI[var2] = 19;
                kc[var2] = 60;
                kd[var2] = 30;
                jL[var2] = 24;
                jM[var2] = 20;
                jX[var2] = 1;
                jO[var2] = 5;
                jT[var2] = 62;
                jY[var2] = jZ[var2] = am[jI[var2]];
                ka[var2] = an[jI[var2]];
                kf[var2] = ao[jI[var2]];
                kb[var2] = 70;
            }

        }
    }

    private static void x(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 17;
                kc[var3] = 60;
                kd[var3] = 10;
                jL[var3] = 22;
                jM[var3] = 22;
                jX[var3] = 1;
                jO[var3] = 5;
                jT[var3] = 50 + (var3 << 1);
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 500;
            }

        }
    }

    private static void y(int var0, int var1) {
        for(var0 = 0; var0 < jG && jI[var0] != -1; ++var0) {
        }

        if (var0 != jG) {
            for(int var2 = var0; var2 < var0 + var1; ++var2) {
                kg[var2] = 0;
                jI[var2] = 12;
                kc[var2] = 24;
                kd[var2] = 10;
                jL[var2] = 16;
                jM[var2] = 22;
                jX[var2] = 1;
                jO[var2] = 5;
                jT[var2] = 70;
                jY[var2] = jZ[var2] = am[jI[var2]];
                ka[var2] = an[jI[var2]];
                kf[var2] = ao[jI[var2]];
                kb[var2] = 500;
            }

        }
    }

    private static void am() {
        int var0;
        for(var0 = 0; var0 < kl.length; ++var0) {
            kk[var0] = new boolean[ki[var0].length];
        }

        for(var0 = 0; var0 < ki.length; ++var0) {
            for(int var1 = 0; var1 < ki[var0].length; ++var1) {
                ki[var0][var1] = ki[var0][var1] * GameMap.TILE_SIZE + 12;
                kj[var0][var1] *= GameMap.TILE_SIZE;
                if (var0 == 8) {
                    int[] var10000 = kj[var0];
                    var10000[var1] -= 12;
                }
            }
        }

    }

    private static void N(int var0) {
        if (jJ[var0] < fe) {
            jR[var0] = tick % 3 + 3;
            jX[var0] = 1;
        } else if (jJ[var0] > fe) {
            jR[var0] = -tick % 3 - 3;
            jX[var0] = -1;
        }

        if (jK[var0] < ff) {
            jS[var0] = tick % 3 + 3;
        } else if (jK[var0] > ff) {
            jS[var0] = -tick % 3 - 3;
        }

        int[] var10000 = jJ;
        var10000[var0] += jR[var0];
        var10000 = jK;
        var10000[var0] += jS[var0];
        int var10002 = jW[var0]++;
        if (jW[var0] > 1) {
            jW[var0] = 0;
        }

        if (a(jJ[var0] - fe) < 10 && a(jK[var0] - ff) < 10) {
            kb[var0] = 0;
            if (kh[var0]) {
                kh[var0] = false;
                b(ka[var0], jX[var0]);
            } else {
                a(ka[var0], jX[var0]);
            }

            jT[var0] = 0;
            jO[var0] = 2;
        }

        if (a(jJ[var0] - fe) > 160 || a(jK[var0] - ff) > 160) {
            jO[var0] = 2;
            jT[var0] = 0;
            jR[var0] = jS[var0] = 0;
            kb[var0] = 0;
        }

    }

    private static void O(int var0) {
        int var10002 = jT[var0]++;
        int[] var10000 = jJ;
        var10000[var0] -= jR[var0];
        var10000 = jK;
        var10000[var0] -= jS[var0];
        if (jR[var0] == 0) {
            jR[var0] = 2;
        }

        if (jS[var0] == 0) {
            jS[var0] = 2;
        }

        if (jT[var0] == 5) {
            jO[var0] = 2;
            jT[var0] = 0;
            jR[var0] = jS[var0] = 0;
            kb[var0] = 0;
        }

    }

    private static void P(int var0) {
        int[] var10000 = jJ;
        var10000[var0] += jR[var0];
        var10000 = jK;
        var10000[var0] += jS[var0];
        if (jJ[var0] - jP[var0] > 40) {
            jR[var0] = -(tick % 2 + 1);
        }

        if (jJ[var0] - jP[var0] < -40) {
            jR[var0] = tick % 2 + 1;
        }

        if (jK[var0] - jQ[var0] > 40) {
            jS[var0] = -(tick % 2 + 1);
        }

        if (jK[var0] - jQ[var0] < -40) {
            jS[var0] = tick % 2 + 1;
        }

        if (tick % 15 == 0) {
            jR[var0] = tick % 3 - 1;
            jS[var0] = (tick >> 1) % 3 - 1;
        }

        int var10002 = jW[var0]++;
        if (jW[var0] > 1) {
            jW[var0] = 0;
        }

        J(var0);
        if (tick % 5 == 0) {
            K(var0);
        }

    }

    private static void Q(int var0) {
        if (tick % 4 == 1) {
            int var10002 = jW[var0]++;
            if (jW[var0] > 1) {
                jW[var0] = 0;
            }

            int[] var10000 = jJ;
            var10000[var0] += jX[var0] << 1;
            if (jJ[var0] > jQ[var0]) {
                jX[var0] = -1;
            } else if (jJ[var0] < jP[var0]) {
                jX[var0] = 1;
            }
        }

        J(var0);
    }

    private static void R(int var0) {
        a(ka[var0], -fm);
        jO[var0] = 2;
        jT[var0] = 0;
        jW[var0] = 2;
    }

    private static void S(int var0) {
        int var10002 = jT[var0]++;
        int[] var10000 = jJ;
        var10000[var0] -= jX[var0];
        if (jT[var0] == 5) {
            jO[var0] = 2;
        }

    }

    private static void T(int var0) {
        int var10002 = jT[var0]++;
        int[] var10000 = jJ;
        var10000[var0] -= jX[var0];
        if (jT[var0] == 3) {
            jO[var0] = 2;
        }

    }

    private static void U(int var0) {
        if (tick % 4 == 0) {
            int var10002 = jW[var0]++;
            if (jW[var0] > 1) {
                jW[var0] = 0;
            }

            int[] var10000 = jJ;
            var10000[var0] += jX[var0];
        }

        if (jJ[var0] > jQ[var0]) {
            jX[var0] = -1;
        } else if (jJ[var0] < jP[var0]) {
            jX[var0] = 1;
        }

        J(var0);
        if (tick % 5 == 0) {
            K(var0);
        }

    }

    private static void openShop() {
        shopY = viewCenterY - 40;
        shopX = viewCenterX - 50;
        tilesetImage = null;
        shopSwordIcons = new Image[4];

        try {
            for(int var4 = 0; var4 < Ui.SWORD_LEVEL_COUNT; ++var4) {
                shopSwordIcons[var4] = Ui.loadSwordIcon(var4);
            }
        } catch (Exception var3) {
        }

        if (cornerIcons == null) {
            cornerIcons = new Image[4];

            for(int var0 = 0; var0 < 4; ++var0) {
                try {
                    cornerIcons[var0] = Ui.loadCorner(var0);
                } catch (IOException var2) {
                    var2.printStackTrace();
                }
            }
        }

        System.gc();
    }

    private static void drawShop(Graphics var0) {
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        drawHudPanel(var0);
        drawHudOverlay(var0);
        var0.setClip(0, 0, screenWidth, screenHeight);
        var0.setColor(0);
        var0.fillRect(viewCenterX - 64, shopY - 19, 129, 102);
        var0.fillRect(0, screenHeight - 13, screenWidth, 13);
        FontGraph.drawBold(var0, "Mua", 3, screenHeight - 13, 0);
        FontGraph.drawBold(var0, "Quay lại", screenWidth - 3, screenHeight - 13, 1);
        var0.setColor(12281361);
        var0.drawRect(viewCenterX - 63, shopY - 18, 126, 99);
        var0.setColor(6562304);
        var0.fillRect(viewCenterX - 61, shopY - 16, 123, 97);
        if (shopMode == SHOP_MODE_MONEY) {
            FontGraph.drawBold(var0, "Tiền: " + Inventory.money + "$", viewCenterX, shopY - 8, 2);
        } else {
            FontGraph.drawBold(var0, ShopCatalog.itemNames[shopRow][shopCursor], viewCenterX, shopY - 8, 2);
        }

        int var1;
        for(var1 = 0; var1 < 3; ++var1) {
            var0.setColor(0);
            var0.fillRect(shopX - 10 + var1 * 20, shopY + 10, 19, 20);
            var0.fillRect(shopX + 50 + var1 * 20, shopY + 10, 19, 20);
            var0.setColor(4856064);
            var0.drawRect(shopX - 10 + var1 * 20, shopY + 10, 20, 20);
            var0.drawRect(shopX + 50 + var1 * 20, shopY + 10, 20, 20);
            var0.drawRegion(itemPotions[Item.POTION_HP], 0, var1 * Item.FRAME_HEIGHT, Item.POTION_WIDTH, Item.FRAME_HEIGHT, 0, shopX + var1 * 20, shopY + 20, 3);
            var0.drawRegion(itemPotions[Item.POTION_MP], 0, var1 * Item.FRAME_HEIGHT, Item.POTION_WIDTH, Item.FRAME_HEIGHT, 0, shopX + 60 + var1 * 20, shopY + 20, 3);
        }

        for(var1 = 0; var1 < 4; ++var1) {
            var0.setColor(0);
            var0.fillRect(shopX + 10 + var1 * 20, shopY + 32, 19, 20);
            var0.setColor(4856064);
            var0.drawRect(shopX + 10 + var1 * 20, shopY + 32, 18, 20);
            var0.drawImage(shopSwordIcons[var1], shopX + 20 + var1 * 20, shopY + 42, 3);
        }

        var0.setColor(shopBlinkColors[shopBlink]);
        if (shopRow == 0) {
            var0.drawRect(shopX - 10 + shopCursor * 20, shopY + 10, 19, 20);
        } else {
            var0.drawRect(shopX + 10 + shopCursor * 20, shopY + 32, 19, 20);
        }

        if (shopMode == SHOP_MODE_MONEY) {
            FontGraph.drawBold(var0, ShopCatalog.itemNames[shopRow][shopCursor] + "-" + ShopCatalog.prices[shopRow][shopCursor] + "$", viewCenterX, shopY + 58, 2);
        } else {
            FontGraph.drawBold(var0, ShopCatalog.smsPrices[shopRow][shopCursor], viewCenterX, shopY + 58, 2);
        }

        var0.drawImage(cornerIcons[0], viewCenterX - 64, shopY - 19, 20);
        var0.drawImage(cornerIcons[1], viewCenterX + 65, shopY - 19, 24);
        var0.drawImage(cornerIcons[2], viewCenterX - 64, shopY + 83, 36);
        var0.drawImage(cornerIcons[3], viewCenterX + 65, shopY + 83, 40);
    }

    private static void openInventory() {
        inventoryY = viewCenterY - 40;
        inventoryX = viewCenterX - 50;
        if (cornerIcons == null) {
            cornerIcons = new Image[4];

            for(int var0 = 0; var0 < 4; ++var0) {
                try {
                    cornerIcons[var0] = Ui.loadCorner(var0);
                } catch (IOException var2) {
                    var2.printStackTrace();
                }
            }
        }

    }

    private static void drawInventory(Graphics var0) {
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        drawHudPanel(var0);
        drawHudOverlay(var0);
        var0.setClip(0, 0, screenWidth, screenHeight);
        var0.setColor(0);
        var0.fillRect(viewCenterX - 64, inventoryY - 19, 129, 102);
        var0.fillRect(0, screenHeight - 13, screenWidth, 13);
        FontGraph.drawBold(var0, "Sử dụng", 3, screenHeight - 13, 0);
        FontGraph.drawBold(var0, "Quay lại", screenWidth - 3, screenHeight - 13, 1);
        var0.setColor(12281361);
        var0.drawRect(viewCenterX - 63, inventoryY - 18, 126, 99);
        var0.setColor(6562304);
        var0.fillRect(viewCenterX - 61, inventoryY - 16, 123, 97);
        FontGraph.drawBold(var0, "Sử dụng Item", viewCenterX, inventoryY - 8, 2);

        for(int var1 = 0; var1 < 3; ++var1) {
            var0.setColor(0);
            var0.fillRect(inventoryX - 10 + var1 * 20, inventoryY + 10, 19, 20);
            var0.fillRect(inventoryX + 50 + var1 * 20, inventoryY + 10, 19, 20);
            var0.setColor(4856064);
            var0.drawRect(inventoryX - 10 + var1 * 20, inventoryY + 10, 20, 20);
            var0.drawRect(inventoryX + 50 + var1 * 20, inventoryY + 10, 20, 20);
            var0.drawRegion(itemPotions[Item.POTION_HP], 0, var1 * Item.FRAME_HEIGHT, Item.POTION_WIDTH, Item.FRAME_HEIGHT, 0, inventoryX + var1 * 20, inventoryY + 20, 3);
            var0.drawRegion(itemPotions[Item.POTION_MP], 0, var1 * Item.FRAME_HEIGHT, Item.POTION_WIDTH, Item.FRAME_HEIGHT, 0, inventoryX + 60 + var1 * 20, inventoryY + 20, 3);
        }

        var0.setColor(inventoryBlinkColors[inventoryBlink]);
        var0.drawRect(inventoryX - 10 + inventoryCursor * 20, inventoryY + 10, 20, 20);
        FontGraph.drawBold(var0, Inventory.potionNames[inventoryCursor], viewCenterX, inventoryY + 40, 2);
        FontGraph.drawBold(var0, "Số lượng: " + Inventory.potionCounts[inventoryCursor], viewCenterX, inventoryY + 55, 2);
        var0.drawImage(cornerIcons[0], viewCenterX - 64, inventoryY - 19, 20);
        var0.drawImage(cornerIcons[1], viewCenterX + 65, inventoryY - 19, 24);
        var0.drawImage(cornerIcons[2], viewCenterX - 64, inventoryY + 83, 36);
        var0.drawImage(cornerIcons[3], viewCenterX + 65, inventoryY + 83, 40);
    }

    private static void openGameMenu() {
        gameMenuY = viewCenterY - 40;
        tilesetImage = null;
        if (cornerIcons == null) {
            cornerIcons = new Image[4];

            for(int var0 = 0; var0 < 4; ++var0) {
                try {
                    cornerIcons[var0] = Ui.loadCorner(var0);
                } catch (IOException var2) {
                    var2.printStackTrace();
                }
            }
        }

    }

    private static void drawGameMenu(Graphics var0) {
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        var0.setColor(0);
        var0.fillRect(viewCenterX - 64, gameMenuY - 19, 129, 102);
        var0.fillRect(0, screenHeight - 13, screenWidth, 13);
        FontGraph.drawBold(var0, "Chọn", 3, screenHeight - 13, 0);
        FontGraph.drawBold(var0, "Quay lại", screenWidth - 3, screenHeight - 13, 1);
        var0.setColor(12281361);
        var0.drawRect(viewCenterX - 63, gameMenuY - 18, 126, 99);
        var0.setColor(6562304);
        var0.fillRect(viewCenterX - 61, gameMenuY - 16, 123, 97);

        for(int var1 = 0; var1 < 4; ++var1) {
            FontGraph.drawBold(var0, gameMenuItems[var1], screenCenterX - 30, gameMenuY + var1 * 18, 0);
        }

        var0.drawImage(weaponIcons[0], screenCenterX - 55 + kJ % 2, gameMenuY + gameMenuSelection * 18 - kJ % 2, 0);
        var0.drawImage(cornerIcons[0], viewCenterX - 64, gameMenuY - 19, 20);
        var0.drawImage(cornerIcons[1], viewCenterX + 65, gameMenuY - 19, 24);
        var0.drawImage(cornerIcons[2], viewCenterX - 64, gameMenuY + 83, 36);
        var0.drawImage(cornerIcons[3], viewCenterX + 65, gameMenuY + 83, 40);
    }

    private static void openWorldMap() {
        lf = 280 - viewWidth;
        lg = 280 - viewHeight;
        worldMapCursor = 0;

        try {
            worldMapImage = Ui.loadWorldMap();
            crossIcon = Ui.loadCross();
        } catch (Exception var0) {
        }

        worldMapCurrent = mapId;
        if (mapId == 99) {
            worldMapCurrent = 0;
        }

        if (mapId == 97) {
            worldMapCurrent = 7;
        }

        if (mapId == 96) {
            worldMapCurrent = 11;
        }

        if (mapId == 95) {
            worldMapCurrent = 18;
        }

        kT = kN[worldMapCurrent];
        kU = kO[worldMapCurrent];
        as();
        kX = kT - viewCenterX;
        kY = kU - viewCenterY;
        if (ld < -40) {
            ld = -40;
        }

        if (ld > lf) {
            ld = lf;
        }

        if (le < -40) {
            le = -40;
        }

        if (le > lg) {
            le = lg;
        }

    }

    private static void ar() {
        worldMapImage = null;
        crossIcon = null;
        System.gc();
    }

    private static void drawWorldMap(Graphics var0) {
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        var0.setColor(0);
        var0.fillRect(0, 0, viewWidth, viewHeight);
        var0.fillRect(0, screenHeight - 13, screenWidth, 13);
        FontGraph.drawBold(var0, li, 3, screenHeight - 13, 0);
        FontGraph.drawBold(var0, "Quay lại", screenWidth - 3, screenHeight - 13, 1);
        var0.setClip(0, 0, viewWidth, viewHeight);
        var0.translate(-ld, -le);
        var0.drawImage(worldMapImage, 0, 0, 0);

        for(int var1 = 0; var1 < 19; ++var1) {
            if (var1 == worldMapCurrent) {
                var0.drawImage(heroHeads[0], kN[var1] + 10, kO[var1], 3);
            }

            if (X[var1] && (var1 != worldMapCursor || kW % 10 > 5)) {
                var0.drawImage(crossIcon, kN[var1], kO[var1], 3);
            }
        }

        var0.drawImage(weaponIcons[0], kT, kU, 24);
        if (worldMapCursor != -1) {
            var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
            var0.setColor(6562304);
            var0.fillRect(screenCenterX - 60, viewHeight - 35, 120, 33);
            var0.setColor(12281361);
            var0.drawRect(screenCenterX - 59, viewHeight - 34, 117, 30);
            FontGraph.drawBold(var0, kL[worldMapCursor], screenCenterX, viewHeight - 25, 2);
            var0.drawImage(cornerIcons[0], screenCenterX - 60, viewHeight - 35, 20);
            var0.drawImage(cornerIcons[1], screenCenterX + 60, viewHeight - 35, 24);
            var0.drawImage(cornerIcons[2], screenCenterX - 60, viewHeight - 2, 36);
            var0.drawImage(cornerIcons[3], screenCenterX + 60, viewHeight - 2, 40);
        }

    }

    private static void as() {
        worldMapCursor = -1;
        li = "";

        for(int var0 = 0; var0 < 19; ++var0) {
            if (a(kT - kN[var0]) < 8 && a(kU - kO[var0]) < 8) {
                worldMapCursor = var0;
                li = "Chuyển đến";
                return;
            }
        }

    }

    private static void at() {
        try {
            popupImage = Ui.loadSoundIcon();
        } catch (Exception var0) {
        }
    }

    public static void setMessageText(String var0) {
        messageLines = FontGraph.wrapBoldText(var0, 100);
        messageY = viewCenterY - messageLines.length * 9;
    }

    public static void showMessage(int var0) {
        prepareDialog();
        messageType = var0;
        if (var0 == MessageType.TRAVEL_NOT_VISITED) {
            setMessageText("Không thể chuyển. Vì bạn chưa từng đi bộ đến đây!");
            messageLeftLabel = "OK";
            messageRightLabel = "";
        } else if (var0 == MessageType.TRAVEL_ALREADY_HERE) {
            setMessageText("Không cần phải chuyển vì bạn đang ở vị trí này!");
            messageLeftLabel = "OK";
            messageRightLabel = "";
        } else if (var0 != MessageType.TRAVEL_CONFIRM) {
            if (var0 == MessageType.NOT_ENOUGH_MONEY) {
                setMessageText("Bạn không có đủ tiền!");
                messageLeftLabel = "OK";
                messageRightLabel = "";
            } else if (var0 == MessageType.GAME_NOTICE) {
                setMessageText("Bạn đã bị trọng thương! Mất một số điểm kinh nghiệm!");
                messageLeftLabel = "OK";
                messageRightLabel = "";
            } else if (var0 == MessageType.LEVEL_UP) {
                setMessageText("Level up!");
                messageLeftLabel = "OK";
                messageRightLabel = "";
            } else {
                if (var0 == MessageType.SHOP_NOTICE) {
                    setMessageText("Buy OK!");
                    messageLeftLabel = "";
                    messageRightLabel = "OK";
                }

            }
        } else {
            var0 = worldMapCurrent < worldMapCursor ? worldMapCurrent : worldMapCursor;
            int var1 = worldMapCurrent > worldMapCursor ? worldMapCurrent : worldMapCursor;
            int var2 = 0;

            for(var0 = var0; var0 <= var1; ++var0) {
                var2 += travelCosts[var0];
            }

            travelCost = var2 = (var2 << 1) / 3;
            setMessageText("Chuyển đến đây phải trả " + var2 + "$. Bạn có " + Inventory.money + "$. Chuyển?");
            messageLeftLabel = "Chuyển";
            messageRightLabel = "Không";
        }
    }

    private static void prepareDialog() {
        lo = 0;
        if (cornerIcons == null) {
            cornerIcons = new Image[4];

            for(int var0 = 0; var0 < 4; ++var0) {
                try {
                    cornerIcons[var0] = Ui.loadCorner(var0);
                } catch (IOException var2) {
                    var2.printStackTrace();
                }
            }
        }

    }

    private static void drawMessage(Graphics var0) {
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        var0.setColor(0);
        var0.fillRect(viewCenterX - 64, messageY - 19, 129, 102);
        var0.fillRect(0, screenHeight - 13, screenWidth, 13);
        FontGraph.drawBold(var0, messageLeftLabel, 3, screenHeight - 13, 0);
        FontGraph.drawBold(var0, messageRightLabel, screenWidth - 3, screenHeight - 13, 1);
        var0.setColor(12281361);
        var0.drawRect(viewCenterX - 63, messageY - 18, 126, 99);
        var0.setColor(6562304);
        var0.fillRect(viewCenterX - 61, messageY - 16, 123, 97);

        for(int var1 = 0; var1 < messageLines.length; ++var1) {
            FontGraph.drawBold(var0, messageLines[var1], screenCenterX, messageY + var1 * 18, 2);
        }

        var0.drawImage(cornerIcons[0], viewCenterX - 64, messageY - 19, 20);
        var0.drawImage(cornerIcons[1], viewCenterX + 65, messageY - 19, 24);
        var0.drawImage(cornerIcons[2], viewCenterX - 64, messageY + 83, 36);
        var0.drawImage(cornerIcons[3], viewCenterX + 65, messageY + 83, 40);
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
            if (npcX[var1] + 10 >= cameraX && npcX[var1] - 10 <= cameraX + viewWidth && npcY[var1] >= cameraY && npcY[var1] - 32 <= cameraY + viewHeight) {
                var0.drawImage(npcSprites[npcTypes[var1]][Npc.PART_LEGS], npcX[var1], npcY[var1], 33);
                var0.drawImage(npcSprites[npcTypes[var1]][Npc.PART_BODY], npcX[var1], npcY[var1] - lF[npcTypes[var1]] + (tick % (15 + npcTypes[var1]) > 4 ? 0 : 1), 33);
            }
        }

    }

    private static void showPurchaseFailed() {
        showMessage(MessageType.SHOP_NOTICE);
        setMessageText("Không thể mua. Xin hãy thử lại sau.");
        messageY = shopY;
        state = GameState.MESSAGE;
    }

    public static void onSwordPurchased() {
        reloadSlashEffects();
        loadHudIcons();
        showMessage(MessageType.SHOP_NOTICE);
        setMessageText("Xin chúc mừng. Bạn đã có vũ khí mới: " + ShopCatalog.itemNames[shopRow][shopCursor] + "!");
        messageY = shopY;
        state = GameState.MESSAGE;
    }

}
