import assets.*;
import core.app.*;
import core.fx.*;
import core.item.*;
import core.map.*;
import core.monster.*;
import core.npc.*;
import core.input.*;
import core.player.*;
import core.quest.*;
import core.render.*;
import core.save.*;
import core.ui.*;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class Midlet extends MIDlet implements Runnable {
    static GameCanvas canvas;
    static Midlet instance;
    private static boolean needsDefaultsInit = true;
    public static int nextGameState;

    static {
        FontGraph.init();
    }

    public Midlet() {
        instance = this;
        Display.getDisplay(this).setCurrent(canvas);
        GameCanvas.start();
    }

    public void run() {
        try {
            Thread.sleep(200L);
            Thread.yield();
            GameCanvas.tick = GameCanvas.abs((int) System.currentTimeMillis() % 1000);
            GameCanvas.loadingTarget = 30;
            Thread.yield();
            GameCanvas.applyMapStory(GameCanvas.mapId);
            GameCanvas.loadMap(GameCanvas.mapId);
            Houses.setup(GameCanvas.mapId);
            Signs.setup(GameCanvas.mapId);
            GameCanvas.loadingTarget = 50;
            Thread.yield();
            Npcs.setup(GameCanvas.mapId);
            GameCanvas.loadHeroSprites();
            Girl.setup(GameCanvas.mapId);
            Classmates.setup(GameCanvas.mapId);
            Dog.setup(GameCanvas.mapId);
            GameCanvas.loadingTarget = 65;
            Thread.yield();
            MonsterSpawns.setup(GameCanvas.mapId);
            GameCanvas.loadingTarget = 70;
            Thread.yield();
            Ninjas.setup(GameCanvas.mapId);
            BoarBoss.setup(GameCanvas.mapId);
            RiverMonster.setup(GameCanvas.mapId);
            FinalBoss.setup(GameCanvas.mapId);
            Toniku.setup(GameCanvas.mapId);
            GameCanvas.loadingTarget = 75;
            MoneyDrops.init();
            GameCanvas.loadScrollIcon();
            PotionDrops.init();
            GameCanvas.loadingTarget = 78;
            Thread.yield();
            Crates.setup(GameCanvas.mapId);
            Motorbikes.setup(GameCanvas.mapId);
            GameCanvas.loadingTarget = 80;
            Dust.init();
            WaterSplash.init();
            GameCanvas.loadingTarget = 82;
            Thread.yield();
            Blood.init();
            FloatingText.init();
            Darts.init();
            GameCanvas.loadingTarget = 85;
            Projectiles.init(GameCanvas.mapId);
            ArcProjectiles.init();
            Aura.init();
            GameCanvas.loadingTarget = 87;
            GameCanvas.loadArrowIcons();
            GameCanvas.setupViewport();
            GameCanvas.setupHud();
            GameCanvas.loadingTarget = 95;
            Thread.yield();
            Sky.setup(Save.backgroundIndex, GameCanvas.mapId, Viewport.viewWidth, Viewport.viewHeight, Settings.lowGraphics);
            if (GameCanvas.mapId < 19) {
                WorldMap.visited[GameCanvas.mapId] = true;
            }

            GameCanvas.hudDirty = true;
            GameCanvas.leftSoftKeyLabel = "Menu";
            GameCanvas.rightSoftKeyLabel = " ";
            GameCanvas.prologueNoRespawn = false;
            GameCanvas.onMapEntered(GameCanvas.mapId);
            GameCanvas.loadingTarget = 100;
            if (needsDefaultsInit) {
                needsDefaultsInit = false;
                PlayerStats.initDefaults();
                QuestState.current = Quest.NONE;
                Player.moveSpeed = 5;
                Player.runOnWaterSpeed = 6;
                Player.underwaterSpeed = 2;
                Player.motorbikeSpeed = 8;
                Player.action = PlayerAction.FALL;
                Player.vx = 1;
                Player.vy = 1;
                Player.pose = 0;
                Player.actionTimer2 = 0;
                Player.actionTimer = 0;
            }

            Input.clearPressed();
            Input.clearHeld();
            if (Save.pendingLoad) {
                GameCanvas.loadGame();
                Save.pendingLoad = false;
            } else if (Save.pendingSave) {
                GameCanvas.saveGame();
                Save.pendingSave = false;
            }

            Sound.loadTrack(Sound.trackForMap(GameCanvas.mapId));

            if (!Sound.isDisabled()) {
                Sound.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            instance.shutdownApp();
        }

        while (GameCanvas.loadingProgress < 90) {
            Thread.yield();
        }

        GameState.current = nextGameState;
    }

    // =====================================================================================
    // #region App

    protected void startApp() {
    }

    protected void pauseApp() {
    }

    protected void destroyApp(boolean unconditional) {
        Sound.stopAndRelease();
    }

    public final void shutdownApp() {
        GameCanvas.running = false;
        System.gc();
        this.notifyDestroyed();
    }

    // #endregion
    // =====================================================================================

}
