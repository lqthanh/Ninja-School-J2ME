import assets.*;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class Midlet extends MIDlet implements Runnable {
    static b b;
    static Midlet instance;
    private static boolean needsDefaultsInit = true;
    public static int nextGameState;
    public static boolean pendingLoadSave;
    public static boolean pendingSave;
    public static int backgroundIndex = 0;

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
            b.x = b.a((int) System.currentTimeMillis() % 1000);
            b.S = 30;
            Thread.yield();
            b.m(b.w);
            b.q(b.w);
            b.p(b.w);
            b.o(b.w);
            b.S = 50;
            Thread.yield();
            b.t(b.w);
            b.i();
            b.f(b.w);
            b.g(b.w);
            b.e(b.w);
            b.S = 65;
            Thread.yield();
            b.r(b.w);
            b.S = 70;
            Thread.yield();
            b.h(b.w);
            b.i(b.w);
            b.j(b.w);
            b.k(b.w);
            b.l(b.w);
            b.S = 75;
            b.k();
            b.g();
            b.p();
            b.S = 78;
            Thread.yield();
            b.d(b.w);
            b.c(b.w);
            b.S = 80;
            b.l();
            b.r();
            b.S = 82;
            Thread.yield();
            b.q();
            b.n();
            b.m();
            b.S = 85;
            b.b(b.w);
            b.f();
            b.o();
            b.S = 87;
            b.e();
            b.h();
            b.j();
            b.S = 95;
            Thread.yield();
            b.j = (new int[]{5614318, 18})[backgroundIndex];

            int verticalOffset;
            try {
                if (!b.a) {
                    b.t = Background.ensureLayers(b.t, backgroundIndex);
                    b.u = Background.ensureCloud(b.u, backgroundIndex);
                    b.v = Background.ensureSun(b.v, backgroundIndex);
                }
            } catch (Exception ex) {
            }

            b.c = false;
            if (b.w != 0 && !b.a) {
                b.c = true;
                b.q = b.s - (b.t[0].getHeight() + b.t[1].getHeight() + b.t[2].getHeight()) + 11;
                b.m = (b.l = (b.k = b.s - b.t[0].getHeight()) - b.t[1].getHeight()) - b.t[2].getHeight();
                b.n = b.l - b.t[3].getHeight() - 10;
            }

            if ((verticalOffset = 2 * b.s / 3 - b.l) < 0) {
                verticalOffset = 0;
            }

            if (b.w < 19) {
                b.X[b.w] = true;
            }

            b.q += verticalOffset;
            b.k += verticalOffset;
            b.l += verticalOffset;
            b.m += verticalOffset;
            b.n += verticalOffset;
            b.h = 3 * b.r / 4;
            b.i = b.q / 3;
            b.f = new int[2];
            b.g = new int[2];
            b.f[0] = b.r / 3;
            b.g[0] = b.q / 2 - 8;
            b.f[1] = 2 * b.r / 3;
            b.g[1] = b.q / 2 + 8;
            b.d = true;
            b.o = "Menu";
            b.p = " ";
            b.U = false;
            b.n(b.w);
            b.S = 100;
            if (needsDefaultsInit) {
                needsDefaultsInit = false;
                b.F = 20;
                b.G = 10;
                b.I = 200;
                b.H = 200;
                b.K = 150;
                b.J = 150;
                b.P = -1;
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
                b.N = 1;
                b.O = 0;
            }

            b.d();
            b.c();
            if (pendingLoadSave) {
                b.b();
                pendingLoadSave = false;
            } else if (pendingSave) {
                b.a();
                pendingSave = false;
            }

            switch (b.w) {
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

        while (b.T < 90) {
            Thread.yield();
        }

        b.e = nextGameState;
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
