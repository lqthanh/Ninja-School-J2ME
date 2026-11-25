import assets.*;
import core.app.*;
import core.combat.*;
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
import core.ui.*;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 * Canvas chính của game (tên gốc khi bị làm rối: {@code b}). Chỉ còn vòng lặp {@link #run()}
 * (gọi cập nhật của các module trong {@code core.*} theo {@link GameState}), {@link #paint(Graphics)}
 * (gọi phần vẽ tương ứng) và nhận phím / chạm. Logic của từng phần nằm trong {@code core.*}:
 * nhân vật ({@code PlayerController}, {@code PlayerInput}), quái và boss ({@code MonsterAI},
 * {@code NinjaAI}, {@code BossAI}), bạn học và cô bé ({@code ClassmateAI}, {@code GirlAI}),
 * sát thương ({@code Combat}), cảnh diễn ({@code Cutscene}), lưu / tải ({@code SaveGame})...
 */
public final class GameCanvas extends Canvas implements Runnable {
    private static int frameDurationMs = 40;
    private static GameCanvas instance;
    private static Font unusedFont;
    private static int unusedFontLineHeight;

    static {
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
        Girl.following = false;
        Girl.mapId = -1;
        FinalBoss.damage = 500;
        FinalBoss.width = 60;
        FinalBoss.height = 60;
        RiverMonster.damage = 20;
        RiverMonster.height = 60;
        RiverMonster.width = 50;
        Dialogue.visible = false;
        Monsters.groupSpriteSets = null;
        // Bảng thoại không được dùng ở đâu (còn sót lại trong bản gốc).
        String[][] unusedBusyLines = new String[][]{{"Ta đang bận."}, 
            {"Ngươi đã học được chiêu shuriken cuối cùng, ta ko còn gì dạy ngươi nữa. Đi đi."}};
        unusedFont = Font.getFont(0, 0, 0);
        unusedFontLineHeight = unusedFont.getHeight() + 6;

    }

    private static void updateTutorial() {
        if (++GameState.tick == 10000) {
            GameState.tick = 0;
        }

        MapEvent.update();
        if (!Cutscene.update()) {
            ClassmateAI.update();
            PlayerController.update();
            Dialogue.typeTick();
        }
    }

    public static void start() {
        GameCanvas canvas = new GameCanvas();
        App.canvas = canvas;
        MapEvent.update();
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
                Combat.gainExp(CheatCodes.EXP_BONUS);
            }

            Hud.dirty = true;
        }

        keyCode = Input.normalizeKeyCode(keyCode);
        if (!Cutscene.playing || keyCode == Input.CODE_FIRE) {
            Input.keyDown(keyCode);
        }
    }

    protected final void keyReleased(int keyCode) {
        keyCode = Input.normalizeKeyCode(keyCode);
        if (!Cutscene.playing || keyCode == Input.CODE_FIRE) {
            Input.keyUp(keyCode);
        }
    }

    protected final void paint(Graphics g) {
        try {
            switch (GameState.current) {
                case GameState.PLAYING:
                    WorldRenderer.draw(g);
                    return;
                case GameState.LOADING:
                    g.setColor(0);
                    g.fillRect(0, 0, Viewport.screenWidth, 21);
                    FontGraph.drawBold(g, "Loading... " + LoadingScreen.progress + "%", Viewport.screenWidth >> 1, 4, 2);
                    return;
                case GameState.MAIN_MENU:
                    MainMenu.draw(g);
                    return;
                case GameState.LOGO:
                    LogoScreen.draw(g);
                    return;
                case GameState.GAME_MENU:
                    GameMenu.draw(g, Hud.weaponIcons[0]);
                    return;
                case GameState.WORLD_MAP:
                    WorldMap.draw(g, HeroSprites.heads[0], Hud.weaponIcons[0]);
                    return;
                case GameState.MESSAGE:
                    MessageBox.draw(g);
                    return;
                case GameState.INVENTORY:
                    InventoryScreen.drawWithHud(g);
                    return;
                case GameState.SHOP:
                    ShopScreen.drawWithHud(g);
                    return;
                case GameState.SETUP_PROMPT:
                    SetupPrompt.draw(g);
                    return;
                case GameState.TUTORIAL:
                    WorldRenderer.drawTutorial(g);
                    return;
                case GameState.UNUSED_12:
                    WorldRenderer.drawTutorial(g);
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
        while(App.running) {
            long frameStart = System.currentTimeMillis();
            switch (GameState.current) {
                case GameState.PLAYING:
                    if (++GameState.tick == 10000) {
                        GameState.tick = 0;
                    }

                    if (MapExits.leaveAtEdge()) {
                        break;
                    }

                    MapEvent.update();
                    Dialogue.typeTick();
                    if (Cutscene.update()) {
                        break;
                    }

                    if (!Settings.lowGraphics) {
                        Sky.updateClouds(GameState.tick, Viewport.viewWidth);
                    }

                    if (Dialogue.visible) {
                        if (Input.pressed[Input.KEY_FIRE]) {
                            Input.clearPressed();
                            Dialogue.advance();
                            if (Dialogue.isFinished()) {
                                Dialogue.visible = false;
                                QuestAction.onDialogueFinished();
                            }
                        }

                        Hud.dirty = true;
                    } else {
                        PlayerInput.update();
                    }

                    Camera.update();
                    TileRenderer.updateVisibleRange();
                    Crates.update();
                    Motorbikes.update();

                    FloatingText.update();
                    WaterSplash.update(GameState.tick);
                    Dust.update();
                    Blood.update();

                    Combat.updateDarts();

                    if (Projectiles.update(Player.x, Player.y, Player.halfWidth, Player.halfHeight, Camera.x, Camera.y, Player.action == PlayerAction.ATTACK && Player.weapon == 0)) {
                        Combat.damagePlayer(Projectiles.hitDamage, Projectiles.hitDirection);
                    }

                    Aura.update();

                    if (ArcProjectiles.update(Player.x, Player.y, Player.halfWidth, Player.halfHeight, Camera.y)) {
                        Combat.damagePlayer(ArcProjectiles.hitDamage, ArcProjectiles.hitDirection);
                    }

                    GuideMarker.update();
                    PlayerController.update();
                    GirlAI.update();
                    ClassmateAI.update();
                    Dog.update();

                    MonsterAI.update();
                    NinjaAI.update();
                    BossAI.updateBoarBoss();
                    BossAI.updateRiverMonster();
                    BossAI.updateFinalBoss();
                    if (Toniku.active) {
                        ClassmateAI.updateTonikuFight();
                    }

                    if (MoneyDrops.update(Player.x, Player.y, Player.height)) {
                        Hud.dirty = true;
                    }

                    if (PotionDrops.update(Player.x, Player.y, Player.height)) {
                        Hud.dirty = true;
                    }

                    QuestItem.update();
                    Notice.update();
                    break;
                case GameState.LOADING:
                    if (LoadingScreen.progress < LoadingScreen.target) {
                        LoadingScreen.progress += 5;
                    }
                    break;
                case GameState.MAIN_MENU:
                    MainMenu.update();
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
                            TileRenderer.ensureMapImages();
                            GameState.current = GameState.PLAYING;
                            Hud.dirty = true;
                            break;
                        case GameMenu.ITEM_USE_ITEM:
                            InventoryScreen.open();
                            GameState.current = GameState.INVENTORY;
                            break;
                        case GameMenu.ITEM_WORLD_MAP:
                            WorldMap.open(GameMap.id);
                            GameState.current = GameState.WORLD_MAP;
                            break;
                        case GameMenu.ITEM_MUSIC:
                            Sound.setEnabled(Sound.disabled, GameMap.id);
                            break;
                        case GameMenu.ITEM_SAVE_AND_EXIT:
                            if (Player.action != PlayerAction.STAND) {
                                UiFrame.release();
                                TileRenderer.ensureMapImages();
                                MessageBox.show(MessageType.GAME_NOTICE);
                                MessageBox.setText("Bạn chỉ có thể lưu và thoát khi nhân vật đang đứng yên.");
                                MessageBox.y = Viewport.viewCenterY - 40;
                                GameState.current = GameState.MESSAGE;
                            } else {
                                App.running = false;
                            }
                            break;
                    }
                    break;
                case GameState.WORLD_MAP:
                    if (WorldMap.update() == WorldMap.RESULT_CLOSE) {
                        TileRenderer.ensureMapImages();
                        GameState.current = GameState.PLAYING;
                    }
                    break;
                case GameState.MESSAGE:
                    switch (MessageBox.update()) {
                        case MessageBox.RESULT_LEFT:
                            MessageBox.onConfirmed();
                            break;
                        case MessageBox.RESULT_RIGHT:
                            if (MessageBox.type == MessageType.TRAVEL_CONFIRM) {
                                GameState.current = GameState.WORLD_MAP;
                            } else if (MessageBox.type == MessageType.SHOP_NOTICE) {
                                GameState.current = GameState.SHOP;
                            }
                            break;
                    }

                    Hud.dirty = true;
                    break;
                case GameState.INVENTORY:
                    InventoryScreen.update();
                    break;
                case GameState.SHOP:
                    switch (ShopScreen.update()) {
                        case ShopScreen.RESULT_CLOSE:
                            TileRenderer.ensureMapImages();
                            GameState.current = GameState.PLAYING;
                            Hud.dirty = true;
                            break;
                        case ShopScreen.RESULT_SWORD_PURCHASED:
                            ShopScreen.onSwordPurchased();
                            break;
                    }
                    break;
                case GameState.SETUP_PROMPT:
                    if (SetupPrompt.done) {
                        SetupPrompt.image = null;
                        MainMenu.start();
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

        SaveGame.save();
        App.midlet.notifyDestroyed();
    }

}
