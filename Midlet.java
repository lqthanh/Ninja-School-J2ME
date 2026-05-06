import AssetsManager.*;

import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;

public class Midlet extends MIDlet implements Runnable {
    static boolean a;
    static b b;
    static Midlet c;
    private static boolean i = true;
    public static int d;
    public static boolean e;
    public static boolean f;
    public static int g = 0;

    static {
        FontGraph.init();
    }

    public static String[] a(String var0, int var1) {
        Vector var2 = new Vector();
        int var3;
        if ((var3 = var0.length()) <= 1) {
            return new String[]{var0};
        } else {
            String var4 = "";
            int var5 = 0;
            int var6 = 0;

            while (true) {
                while (FontGraph.getBoldWidth(var4) < var1) {
                    var4 = var4 + var0.charAt(var6);
                    ++var6;
                    if (var0.charAt(var6) == '\n') {
                        break;
                    }

                    if (var6 >= var3 - 1) {
                        var6 = var3 - 1;
                        break;
                    }
                }

                if (var6 != var3 - 1 && var0.charAt(var6 + 1) != ' ') {
                    int var8;
                    for (var8 = var6; var0.charAt(var6 + 1) != '\n' && (var0.charAt(var6 + 1) != ' ' || var0.charAt(var6) == ' ') && var6 != var5; --var6) {
                    }

                    if (var6 == var5) {
                        var6 = var8;
                    }
                }

                var2.addElement(var0.substring(var5, var6 + 1));
                if (var6 == var3 - 1) {
                    break;
                }

                for (var5 = var6 + 1; var5 != var3 - 1 && var0.charAt(var5) == ' '; ++var5) {
                }

                if (var5 == var3 - 1) {
                    break;
                }

                var6 = var5;
                var4 = "";
            }

            String[] var9 = new String[var2.size()];

            for (int var7 = 0; var7 < var2.size(); ++var7) {
                var9[var7] = (String) var2.elementAt(var7);
            }

            return var9;
        }
    }

    public static String[] b(String var0, int var1) {
        Vector var2 = new Vector();
        int var3 = var0.length();
        String var4 = "";
        int var5 = 0;
        int var6 = 0;

        while (true) {
            while (FontGraph.getRegularWidth(var4) < var1) {
                var4 = var4 + var0.charAt(var6);
                ++var6;
                if (var6 == var3 - 1) {
                    break;
                }
            }

            if (var6 != var3 - 1 && var0.charAt(var6 + 1) != ' ') {
                int var8;
                for (var8 = var6; (var0.charAt(var6 + 1) != ' ' || var0.charAt(var6) == ' ') && var6 != var5; --var6) {
                }

                if (var6 == var5) {
                    var6 = var8;
                }
            }

            var2.addElement(var0.substring(var5, var6 + 1));
            if (var6 == var3 - 1) {
                break;
            }

            for (var5 = var6 + 1; var5 != var3 - 1 && var0.charAt(var5) == ' '; ++var5) {
            }

            if (var5 == var3 - 1) {
                break;
            }

            var6 = var5;
            var4 = "";
        }

        String[] var9 = new String[var2.size()];

        for (int var7 = 0; var7 < var2.size(); ++var7) {
            var9[var7] = (String) var2.elementAt(var7);
        }

        return var9;
    }

    public Midlet() {
        c = this;
        Display.getDisplay(this).setCurrent(b);
        b.s();
    }

    public final void c() {
        b.b = false;
        System.gc();
        this.notifyDestroyed();
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
            b.j = (new int[]{5614318, 18})[g];

            int var4;
            try {
                if (!b.a) {
                    b.t = Background.ensureLayers(b.t, g);
                    b.u = Background.ensureCloud(b.u, g);
                    b.v = Background.ensureSun(b.v, g);
                }
            } catch (Exception var2) {
            }

            b.c = false;
            if (b.w != 0 && !b.a) {
                b.c = true;
                b.q = b.s - (b.t[0].getHeight() + b.t[1].getHeight() + b.t[2].getHeight()) + 11;
                b.m = (b.l = (b.k = b.s - b.t[0].getHeight()) - b.t[1].getHeight()) - b.t[2].getHeight();
                b.n = b.l - b.t[3].getHeight() - 10;
            }

            if ((var4 = 2 * b.s / 3 - b.l) < 0) {
                var4 = 0;
            }

            if (b.w < 19) {
                b.X[b.w] = true;
            }

            b.q += var4;
            b.k += var4;
            b.l += var4;
            b.m += var4;
            b.n += var4;
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
            if (i) {
                i = false;
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
            if (e) {
                b.b();
                e = false;
            } else if (f) {
                b.a();
                f = false;
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
        } catch (Exception var3) {
            var3.printStackTrace();
            c.c();
        }

        while (b.T < 90) {
            Thread.yield();
        }

        b.e = d;
    }

    // =====================================================================================
    // #region RecordStore

    public static byte[] loadRecord(String recordName) {
        try {
            RecordStore store;
            byte[] data = (store = RecordStore.openRecordStore(recordName, false)).getRecord(1);
            store.closeRecordStore();
            return data;
        } catch (Exception ex) {
            return null;
        }
    }

    public static void saveRecord(String recordName, byte[] data) {
        try {
            RecordStore store;
            if ((store = RecordStore.openRecordStore(recordName, true)).getNumRecords() > 0) {
                store.setRecord(1, data, 0, data.length);
            } else {
                store.addRecord(data, 0, data.length);
            }

            store.closeRecordStore();
        } catch (Exception ex) {
        }
    }

    public static void setBackgroundIndex(int backgroundIndex) {
        byte[] data = new byte[1];
        data[0] = (byte) backgroundIndex;
        saveRecord("bg", data);
    }

    public static int getBackgroundIndex() {
        byte[] data;
        return (data = loadRecord("bg")) == null ? 0 : data[0];
    }

    public static void markSaved() {
        byte[] data = new byte[1];
        data[0] = 1;
        saveRecord("saved", data);
    }

    public static boolean isSaved() {
        byte[] data;
        if ((data = loadRecord("saved")) == null) {
            return false;
        } else {
            return data[0] == 1;
        }
    }

    // #endregion
    // =====================================================================================

    // =====================================================================================
    // #region App

    protected void startApp() {
    }

    protected void destroyApp(boolean var1) {
        Sound.stopAndRelease();
    }

    protected void pauseApp() {
    }

    // #endregion
    // =====================================================================================

}
