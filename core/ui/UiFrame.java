package core.ui;

import assets.FontGraph;
import assets.Ui;
import core.render.Viewport;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Khung hộp dùng chung cho menu trong game, túi đồ, cửa hàng và hộp thông báo:
 * hộp 129x102 căn giữa view (nền nâu, viền vàng, 4 góc trang trí) và thanh phím mềm
 * ở đáy màn hình.
 */
public final class UiFrame {
    public static final int BORDER_COLOR = 12281361;
    public static final int FILL_COLOR = 6562304;

    /** Ảnh 4 góc khung; null khi chưa nạp hoặc đã giải phóng để tiết kiệm bộ nhớ. */
    public static Image[] corners;

    private UiFrame() {
    }

    public static void loadCorners() {
        if (corners == null) {
            corners = new Image[4];

            for (int i = 0; i < 4; ++i) {
                try {
                    corners[i] = Ui.loadCorner(i);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void release() {
        corners = null;
    }

    /**
     * Vẽ nền hộp (đỉnh nội dung tại {@code top}, khung bắt đầu từ top - 19) và thanh
     * phím mềm với nhãn trái/phải.
     */
    public static void drawPanel(Graphics g, int top, String leftLabel, String rightLabel) {
        g.setColor(0);
        g.fillRect(Viewport.viewCenterX - 64, top - 19, 129, 102);
        g.fillRect(0, Viewport.screenHeight - 13, Viewport.screenWidth, 13);
        FontGraph.drawBold(g, leftLabel, 3, Viewport.screenHeight - 13, 0);
        FontGraph.drawBold(g, rightLabel, Viewport.screenWidth - 3, Viewport.screenHeight - 13, 1);
        g.setColor(BORDER_COLOR);
        g.drawRect(Viewport.viewCenterX - 63, top - 18, 126, 99);
        g.setColor(FILL_COLOR);
        g.fillRect(Viewport.viewCenterX - 61, top - 16, 123, 97);
    }

    /** Vẽ 4 góc trang trí của hộp đã vẽ bằng {@link #drawPanel}. */
    public static void drawCorners(Graphics g, int top) {
        g.drawImage(corners[0], Viewport.viewCenterX - 64, top - 19, 20);
        g.drawImage(corners[1], Viewport.viewCenterX + 65, top - 19, 24);
        g.drawImage(corners[2], Viewport.viewCenterX - 64, top + 83, 36);
        g.drawImage(corners[3], Viewport.viewCenterX + 65, top + 83, 40);
    }
}
