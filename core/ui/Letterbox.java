package core.ui;

import assets.FontGraph;
import core.render.Viewport;
import javax.microedition.lcdui.Graphics;

/** Hai dải đen trên/dưới và dòng dẫn chuyện khi chiếu cutscene. */
public final class Letterbox {
    /** Chiều cao tối đa của mỗi dải đen. */
    public static int maxHeight;

    /** Chiều cao hiện tại (mở / đóng dần khi bắt đầu / kết thúc cutscene). */
    public static int height;

    /** Dòng dẫn chuyện đang hiện, nội dung và vị trí y (trượt tới captionTargetY). */
    public static boolean captionVisible;
    public static String[] captionLines = new String[]{""};
    public static int captionY;
    public static int captionTargetY;

    private Letterbox() {
    }

    /** Vẽ hai dải đen và dòng dẫn chuyện. */
    public static void draw(Graphics g) {
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        g.setClip(-1, -1, Viewport.screenWidth + 2, Viewport.screenHeight + 2);
        g.setColor(0);
        g.fillRect(0, 0, Viewport.viewWidth, height);
        if (Hud.panelVisible) {
            g.fillRect(0, Viewport.screenHeight - height + 1, Viewport.viewWidth, height);
        } else {
            g.fillRect(0, Viewport.screenHeight - 13, Viewport.screenWidth, 13);
        }

        if (captionVisible) {
            for(int i = 0; i < captionLines.length; ++i) {
                FontGraph.drawBold(g, captionLines[i], Viewport.viewCenterX, captionY + (i << 4), 2);
            }
        }

    }
}
