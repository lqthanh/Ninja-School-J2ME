import assets.*;
import core.app.*;
import core.fx.*;
import core.item.*;
import core.map.*;
import core.monster.*;
import core.input.*;
import core.player.*;
import core.quest.*;
import core.render.*;
import core.save.*;
import core.ui.*;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class Midlet extends MIDlet implements Runnable {
    static b b;
    static Midlet instance;
    private static boolean needsDefaultsInit = true;
    public static int nextGameState;

    static {
        FontGraph.init();
    }

    public Midlet() {
        instance = this;
        Display.getDisplay(this).setCurrent(b);
        b.s();
    }

    public void run() {
        try {
            Thread.sleep(200L);
            Thread.yield();
            b.tick = b.a((int) System.currentTimeMillis() % 1000);
            b.loadingTarget = 30;
            Thread.yield();
            b.m(b.mapId);
            b.loadMap(b.mapId);
            b.setupHouses(b.mapId);
            b.setupSigns(b.mapId);
            b.loadingTarget = 50;
            Thread.yield();
            b.setupNpcs(b.mapId);
            b.loadHeroSprites();
            b.f(b.mapId);
            b.setupClassmates(b.mapId);
            b.e(b.mapId);
            b.loadingTarget = 65;
            Thread.yield();
            MonsterSpawns.setup(b.mapId);
            b.loadingTarget = 70;
            Thread.yield();
            Ninjas.setup(b.mapId);
            b.i(b.mapId);
            b.j(b.mapId);
            b.k(b.mapId);
            b.l(b.mapId);
            b.loadingTarget = 75;
            MoneyDrops.init();
            b.g();
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
            b.e();
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
            b.U = false;
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
                b.b();
                Save.pendingLoad = false;
            } else if (Save.pendingSave) {
                b.a();
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

    protected void destroyApp(boolean var1) {
        Sound.stopAndRelease();
    }

    public final void shutdownApp() {
        b.b = false;
        System.gc();
        this.notifyDestroyed();
    }

    // #endregion
    // =====================================================================================

}
