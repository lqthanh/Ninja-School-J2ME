package core.ui;

import assets.FontGraph;
import assets.Ui;
import core.app.App;
import core.app.GameState;
import core.app.MapLoader;
import core.app.Settings;
import core.input.Input;
import core.item.Inventory;
import core.map.GameMap;
import core.map.Signs;
import core.npc.Classmates;
import core.npc.Girl;
import core.player.Player;
import core.quest.Skills;
import core.quest.Story;
import core.render.Camera;
import core.render.TileRenderer;
import core.render.Viewport;
import core.save.PlayTime;
import core.save.Save;
import core.save.SaveGame;
import core.text.GameText;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/** Màn hình menu chính: rèm mở, tiêu đề, mục đang chọn. */
public final class MainMenu {
    /** Mục đang chọn (chỉ số trong GameText.mainMenuItems). */
    private static int selected;

    /** Vị trí cuối của mép rèm trên / dưới khi rèm mở hết. */
    private static int curtainTopLimit;
    private static int curtainBottomLimit;

    /** Mép rèm trên / dưới hiện tại. */
    private static int curtainTop;
    private static int curtainBottom;

    /** Vị trí y của dòng mục chọn (trượt lên tới textTargetY). */
    private static int textTargetY;
    private static int textY;

    /** Ảnh tiêu đề game. */
    private static Image titleImage;

    private MainMenu() {
    }

    /** Vào menu chính: nạp map 99 làm nền ở thread riêng, khởi tạo rèm; chọn sẵn "Chơi tiếp" nếu đã có save. */
    public static void start() {
        GameState.current = GameState.LOADING;
        LoadingScreen.reset();
        GameState.next = GameState.MAIN_MENU;
        GameMap.id = 99;

        try {
            titleImage = Ui.loadTitle();
        } catch (Exception e) {
        }

        textTargetY = Viewport.screenHeight - 30;
        textY = Viewport.screenHeight;
        curtainTopLimit = 30;
        curtainBottomLimit = Viewport.screenHeight - 50;
        curtainTop = curtainBottom = curtainTopLimit + curtainBottomLimit >> 1;
        if (Save.isSaved()) {
            selected = 1;
        }

        App.startLoader();
    }

    /** Vẽ menu chính trên nền map. */
    public static void draw(Graphics g) {
        g.setClip(-1, -1, Viewport.screenWidth + 2, Viewport.screenHeight + 1);
        TileRenderer.drawSky(g);
        g.translate(-Camera.x, -Camera.y);
        if (Settings.lowGraphics) {
            TileRenderer.drawTilesLowGraphics(g);
        } else {
            TileRenderer.drawTiles(g);
        }

        Signs.draw(g, Camera.x, Camera.y);
        TileRenderer.drawForegroundTiles(g);
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        g.setColor(0);
        g.fillRect(0, 0, Viewport.viewWidth, curtainTop);
        g.fillRect(0, curtainBottom, Viewport.viewWidth, Viewport.screenHeight - curtainBottom + 1);
        g.setColor(10275899);
        g.fillRect(0, curtainTop - 1, Viewport.screenWidth, 1);
        g.fillRect(0, curtainBottom + 1, Viewport.screenWidth, 1);
        g.drawImage(titleImage, Viewport.screenCenterX, curtainTopLimit + curtainBottom >> 1, 3);
        FontGraph.drawBold(g, "*", Viewport.screenCenterX - 50 - GameState.tick % 3, textY, 0);
        FontGraph.drawBold(g, "/", Viewport.screenCenterX + 50 + GameState.tick % 3, textY, 1);
        FontGraph.drawBold(g, GameText.mainMenuItems[selected], Viewport.screenCenterX, textY, 3);
    }

    /** Menu chính: chọn mục, camera lượn qua map nền, rèm mở dần. */
    public static void update() {
        if (++GameState.tick == 10000) {
            GameState.tick = 0;
        }

        if (Input.pressed[Input.KEY_LEFT]) {
            if (--selected < 0) {
                selected = GameText.mainMenuItems.length - 1;
            }

            Input.pressed[Input.KEY_LEFT] = false;
        }

        if (Input.pressed[Input.KEY_RIGHT]) {
            if (++selected == GameText.mainMenuItems.length) {
                selected = 0;
            }

            Input.pressed[Input.KEY_RIGHT] = false;
        }

        if (Input.pressed[Input.KEY_FIRE]) {
            select();
        }

        if (Camera.x == 0) {
            Camera.x = 960;
            Camera.y = 48;
            Camera.vx = 1;
            Camera.vy = 1;
        }

        Camera.x += Camera.vx;
        Camera.y += Camera.vy;
        if (Camera.x > Camera.maxX) {
            Camera.x = Camera.maxX;
            Camera.vx = -Camera.vx;
        }

        if (Camera.x < 24) {
            Camera.x = 24;
            Camera.vx = -Camera.vx;
        }

        if (Camera.y < 0) {
            Camera.y = 0;
            Camera.vy = -Camera.vy;
        }

        if (Camera.y > Camera.maxY) {
            Camera.y = Camera.maxY;
            Camera.vy = -Camera.vy;
        }

        TileRenderer.updateVisibleRange();
        if (curtainTop > curtainTopLimit) {
            curtainTop -= 4;
        }

        if (curtainBottom < curtainBottomLimit) {
            curtainBottom += 4;
        }

        if (textY > textTargetY) {
            textY -= 2;
        }
    }

    /** Chọn mục đang trỏ ở menu chính (khi nhấn phím giữa). */
    private static void select() {
        Input.clearPressed();
        if (selected == 0) {
            titleImage = null;
            MapLoader.releaseSprites();
            Save.pendingSave = true;
            PlayTime.start();
            MapLoader.change(99);
        } else if (selected == 1) {
            if (!Save.isSaved()) {
                return;
            }

            titleImage = null;
            MapLoader.releaseSprites();
            int[] saveData;
            PlayTime.savedSeconds = (saveData = SaveGame.bytesToInts(Save.loadRecord("ninjasave")))[0];
            GameMap.id = saveData[1];
            GameState.tick = saveData[2];
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
            MapLoader.change(GameMap.id);
        } else if (selected == 2) {
            titleImage = null;
            MapLoader.releaseSprites();
            GameState.current = GameState.LOADING;
            LoadingScreen.reset();
            GameState.next = GameState.TUTORIAL;
            GameMap.id = 101;
            App.startLoader();
        } else if (selected == 3) {
            try {
                App.midlet.platformRequest("http://wap.teamobi.com/");
            } catch (Exception e) {
            }
        } else if (selected == 4) {
            App.midlet.notifyDestroyed();
        }

        Input.pressed[Input.KEY_FIRE] = false;
    }
}
