package core.ui;

import assets.FontGraph;
import core.render.Viewport;
import javax.microedition.lcdui.Graphics;

/** Dòng thông báo nhấp nháy ở đầu màn hình (tiến độ nhiệm vụ, nhận kỹ năng...). */
public final class Notice {
    /** Số frame thông báo được hiển thị. */
    public static final int DURATION = 150;

    public static boolean visible;
    private static int timer;
    private static String text;

    private Notice() {
    }

    public static void show(String message) {
        text = message;
        timer = 0;
        visible = true;
    }

    public static void update() {
        if (visible && ++timer >= DURATION) {
            visible = false;
        }
    }

    public static void draw(Graphics g, int tick) {
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        g.setClip(-1, -1, Viewport.screenWidth + 2, Viewport.screenHeight + 2);
        g.setColor(0);
        g.fillRect(0, 0, Viewport.screenWidth, 21);
        g.setColor(10275899);
        g.fillRect(0, 19, Viewport.screenWidth, 1);
        if (tick % 10 > 2) {
            FontGraph.drawBold(g, text, Viewport.viewCenterX, 4, 2);
        }
    }
}
