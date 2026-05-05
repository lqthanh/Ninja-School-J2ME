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
    private static Image[] j;
    private static final int[] k = new int[]{5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 6, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5};
    private static int l = 8;
    private static Image m;
    private static final int[] n = new int[]{2, 6, 3, 5, 6, 6, 6, 5, 6, 6, 6, 3, 3, 3, 2, 6, 4, 4, 5, 3, 6, 8, 8, 8, 8, 8, 8, 8, 8, 3, 8, 8, 8, 10, 8, 8, 8, 8, 8, 7, 8, 8, 8, 10, 8, 8, 8, 6, 6, 6, 6, 6, 5, 6, 6, 3, 5, 6, 3, 8, 6, 6, 6, 6, 6, 6, 5, 6, 6, 8, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 3, 3, 3, 3, 3, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
    private static int o = 12;
    private static Image p;
    private static final int[] q = new int[]{3, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 4, 3, 3, 3, 6, 7, 7, 8, 5, 11, 11, 7, 6, 10, 6, 7, 5, 7, 6, 5, 7, 7, 3, 3, 6, 3, 9, 7, 7, 7, 7, 4, 5, 4, 8, 6, 9, 7, 6, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 8, 7, 8, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 4, 3, 3, 3, 3, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 7, 7, 8, 7, 8, 8, 8, 7, 7, 8, 7, 6, 7, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 7, 8, 7, 7, 8, 6, 6, 8, 8, 3, 6, 7, 6, 11, 9, 8, 7, 8, 7, 5, 6, 8, 7, 11, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 6, 7, 6, 6, 6, 6, 5, 5, 5, 6, 5, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 7, 7, 7, 7, 7, 9};
    private static int r = 13;
    public static int g = 0;

    static {
        try {
            j = new Image[3];

            for (int var0 = 0; var0 < 3; ++var0) {
                j[var0] = Image.createImage("/fs" + var0 + ".png");
            }
        } catch (Exception var3) {
        }

        try {
            m = Image.createImage("/fr.png");
        } catch (Exception var2) {
        }

        try {
            p = Image.createImage("/fb.png");
        } catch (Exception var1) {
        }

    }

    public static void a(Graphics var0, String var1, int var2, int var3, int var4) {
        int var5 = var1.length();
        if (var4 == 0) {
            var4 = var2;
        } else if (var4 == 1) {
            var4 = var2 - a(var1);
        } else {
            var4 = var2 - (a(var1) >> 1);
        }

        for (int var6 = 0; var6 < var5; ++var6) {
            if ((var2 = " 0123456789.,:!?()+-*/#$%abcdefghijklmnopqrstuvwxyzáàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(var1.charAt(var6))) == -1) {
                var2 = 0;
            }

            if (var2 > -1) {
                var0.drawRegion(p, 0, var2 * r + 1, q[var2], r - 1, 0, var4, var3, 20);
            }

            var4 += q[var2];
        }

    }

    public static void b(Graphics var0, String var1, int var2, int var3, int var4) {
        int var5 = var1.length();
        var4 = var2 - (c(var1) >> 1);

        for (int var6 = 0; var6 < var5; ++var6) {
            if ((var2 = " 0123456789.,:!?()-'/ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzáàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđĐ".indexOf(var1.charAt(var6))) == -1) {
                var2 = 0;
            }

            if (var2 > -1) {
                var0.drawRegion(m, 0, var2 * o + 1, n[var2], o - 1, 0, var4, var3, 20);
            }

            var4 += n[var2];
        }

    }

    public static void a(Graphics var0, String var1, int var2, int var3, int var4, int var5) {
        int var6 = var1.length();
        if (var5 == 0) {
            var5 = var3;
        } else if (var5 == 1) {
            var5 = var3 - d(var1);
        } else {
            var5 = var3 - (d(var1) >> 1);
        }

        for (int var7 = 0; var7 < var6; ++var7) {
            if ((var3 = "0123456789+-%$:abcdefghijklmnopqrstuvwxyz".indexOf(var1.charAt(var7))) == -1) {
                var3 = 0;
            }

            if (var3 > -1) {
                var0.drawRegion(j[var2], 0, var3 * l, k[var3], l, 0, var5, var4, 20);
            }

            var5 += k[var3] - 1;
        }

    }

    public static int a(String var0) {
        int var2 = 0;

        for (int var3 = 0; var3 < var0.length(); ++var3) {
            int var1;
            if ((var1 = " 0123456789.,:!?()+-*/#$%abcdefghijklmnopqrstuvwxyzáàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(var0.charAt(var3))) == -1) {
                var1 = 0;
            }

            var2 += q[var1];
        }

        return var2;
    }

    private static int c(String var0) {
        int var2 = 0;

        for (int var3 = 0; var3 < var0.length(); ++var3) {
            int var1;
            if ((var1 = " 0123456789.,:!?()-'/ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzáàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđĐ".indexOf(var0.charAt(var3))) == -1) {
                var1 = 0;
            }

            var2 += n[var1];
        }

        return var2;
    }

    private static int d(String var0) {
        int var2 = 0;

        for (int var3 = 0; var3 < var0.length(); ++var3) {
            int var1;
            if ((var1 = "0123456789+-%$:abcdefghijklmnopqrstuvwxyz".indexOf(var0.charAt(var3))) == -1) {
                var1 = 0;
            }

            var2 += k[var1] + 1;
        }

        return var2;
    }

    public static byte[] b(String var0) {
        try {
            RecordStore var3;
            byte[] var1 = (var3 = RecordStore.openRecordStore(var0, false)).getRecord(1);
            var3.closeRecordStore();
            return var1;
        } catch (Exception var2) {
            return null;
        }
    }

    public static int a() {
        byte[] var0;
        return (var0 = b("bg")) == null ? 0 : var0[0];
    }

    public static void a(String var0, byte[] var1) {
        try {
            RecordStore var3;
            if ((var3 = RecordStore.openRecordStore(var0, true)).getNumRecords() > 0) {
                var3.setRecord(1, var1, 0, var1.length);
            } else {
                var3.addRecord(var1, 0, var1.length);
            }

            var3.closeRecordStore();
        } catch (Exception var2) {
        }
    }

    public static void a(boolean var0) {
        byte[] var1;
        (var1 = new byte[1])[0] = 1;
        a("saved", var1);
    }

    public static boolean b() {
        byte[] var0;
        if ((var0 = b("saved")) == null) {
            return false;
        } else {
            return var0[0] == 1;
        }
    }

    public static void a(int var0) {
        byte[] var1;
        (var1 = new byte[1])[0] = (byte) var0;
        a("bg", var1);
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
                while (a(var4) < var1) {
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
            while (c(var4) < var1) {
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

    protected void destroyApp(boolean var1) {
        Sound.stopAndRelease();
    }

    public final void c() {
        b.b = false;
        System.gc();
        this.notifyDestroyed();
    }

    protected void pauseApp() {
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
                    if (b.t == null) {
                        b.t = new Image[4];

                        for (var4 = 0; var4 < 4; ++var4) {
                            b.t[var4] = Image.createImage("/bg" + var4 + g + ".png");
                        }
                    }

                    if (b.u == null) {
                        b.u = Image.createImage("/cl" + g + ".png");
                    }

                    if (b.v == null) {
                        b.v = Image.createImage("/sun" + g + ".png");
                    }
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

    protected void startApp() {
    }
}
