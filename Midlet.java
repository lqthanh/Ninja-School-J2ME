import assets.*;
import core.app.*;
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
            b.i();
            b.f(b.mapId);
            b.setupClassmates(b.mapId);
            b.e(b.mapId);
            b.loadingTarget = 65;
            Thread.yield();
            b.r(b.mapId);
            b.loadingTarget = 70;
            Thread.yield();
            b.h(b.mapId);
            b.i(b.mapId);
            b.j(b.mapId);
            b.k(b.mapId);
            b.l(b.mapId);
            b.loadingTarget = 75;
            b.k();
            b.g();
            b.p();
            b.loadingTarget = 78;
            Thread.yield();
            b.d(b.mapId);
            b.c(b.mapId);
            b.loadingTarget = 80;
            b.l();
            b.r();
            b.loadingTarget = 82;
            Thread.yield();
            b.q();
            b.n();
            b.m();
            b.loadingTarget = 85;
            b.b(b.mapId);
            b.f();
            b.o();
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
                b.Q = 5;
                b.L = 6;
                b.M = 2;
                b.R = 8;
                b.B = 4;
                b.z = 1;
                b.A = 1;
                b.C = 0;
                b.E = 0;
                b.D = 0;
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

            switch (b.mapId) {
                case 0:
                case 1:
                case 2:
                    Sound.loadTrack(1);
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    Sound.loadTrack(2);
                    break;
                case 95:
                case 96:
                case 97:
                    Sound.loadTrack(4);
                    break;
                case 99:
                    Sound.loadTrack(0);
                    break;
                default:
                    Sound.loadTrack(3);
            }

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
