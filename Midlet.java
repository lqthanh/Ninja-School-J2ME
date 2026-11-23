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
    static b canvas;
    static Midlet instance;
    private static boolean needsDefaultsInit = true;
    public static int nextGameState;

    static {
        FontGraph.init();
    }

    public Midlet() {
        instance = this;
        Display.getDisplay(this).setCurrent(canvas);
        b.start();
    }

    public void run() {
        try {
            Thread.sleep(200L);
            Thread.yield();
            b.tick = b.abs((int) System.currentTimeMillis() % 1000);
            b.loadingTarget = 30;
            Thread.yield();
            b.applyMapStory(b.mapId);
            b.loadMap(b.mapId);
            b.setupHouses(b.mapId);
            b.setupSigns(b.mapId);
            b.loadingTarget = 50;
            Thread.yield();
            b.setupNpcs(b.mapId);
            b.loadHeroSprites();
            Girl.setup(b.mapId);
            Classmates.setup(b.mapId);
            Dog.setup(b.mapId);
            b.loadingTarget = 65;
            Thread.yield();
            MonsterSpawns.setup(b.mapId);
            b.loadingTarget = 70;
            Thread.yield();
            Ninjas.setup(b.mapId);
            BoarBoss.setup(b.mapId);
            RiverMonster.setup(b.mapId);
            FinalBoss.setup(b.mapId);
            Toniku.setup(b.mapId);
            b.loadingTarget = 75;
            MoneyDrops.init();
            b.loadScrollIcon();
            PotionDrops.init();
            b.loadingTarget = 78;
            Thread.yield();
            Crates.setup(b.mapId);
            Motorbikes.setup(b.mapId);
            b.loadingTarget = 80;
            Dust.init();
            WaterSplash.init();
            b.loadingTarget = 82;
            Thread.yield();
            Blood.init();
            FloatingText.init();
            Darts.init();
            b.loadingTarget = 85;
            Projectiles.init(b.mapId);
            ArcProjectiles.init();
            Aura.init();
            b.loadingTarget = 87;
            b.loadArrowIcons();
            b.setupViewport();
            b.setupHud();
            b.loadingTarget = 95;
            Thread.yield();
            Sky.setup(Save.backgroundIndex, b.mapId, Viewport.viewWidth, Viewport.viewHeight, Settings.lowGraphics);
            if (b.mapId < 19) {
                WorldMap.visited[b.mapId] = true;
            }

            b.hudDirty = true;
            b.leftSoftKeyLabel = "Menu";
            b.rightSoftKeyLabel = " ";
            b.prologueNoRespawn = false;
            b.onMapEntered(b.mapId);
            b.loadingTarget = 100;
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
                b.loadGame();
                Save.pendingLoad = false;
            } else if (Save.pendingSave) {
                b.saveGame();
                Save.pendingSave = false;
            }

            Sound.loadTrack(Sound.trackForMap(b.mapId));

            if (!Sound.isDisabled()) {
                Sound.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            instance.shutdownApp();
        }

        while (b.loadingProgress < 90) {
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
        b.running = false;
        System.gc();
        this.notifyDestroyed();
    }

    // #endregion
    // =====================================================================================

}
