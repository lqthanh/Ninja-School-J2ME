package core.ui;

import assets.FontGraph;
import assets.Ui;
import core.item.Inventory;
import core.player.Leveling;
import core.player.Player;
import core.player.PlayerStats;
import core.render.HeroSprites;
import core.render.Viewport;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/** Thanh trạng thái (HP/MP/EXP, tiền, vũ khí) và nhãn phím mềm. */
public final class Hud {
    /** Cần vẽ lại thanh trạng thái ở frame này. */
    public static boolean dirty;

    /** Màn hình đủ cao (>= 208) để có khung trạng thái riêng phía dưới; nếu không, thanh máu vẽ đè lên màn chơi. */
    public static boolean panelVisible;

    /** Nhãn phím mềm trái. */
    public static String leftSoftKeyLabel;

    /** Nhãn phím mềm phải ("Giao tiếp", "Trò chuyện"... hoặc " "). */
    public static String rightSoftKeyLabel;

    /** Vị trí và kích thước khung trạng thái (pixel màn hình). */
    public static int x;
    public static int y;
    public static int width;
    public static int height;

    /** Chiều rộng ảnh khung trái / phải và dải nối ở giữa. */
    private static int leftFrameWidth;
    private static int rightFrameWidth;
    private static int stripWidth;

    /** Góc trên trái của thanh HP (thanh MP, EXP vẽ ngay bên dưới). */
    private static int barX;
    private static int barY;

    /** Chiều dài tối đa của thanh HP/MP. */
    public static int barMaxWidth;

    /** Chiều dài tối đa của thanh EXP. */
    private static int expBarMaxWidth;

    /** Vị trí x của cấp và tiền khi không có khung trạng thái riêng. */
    public static int levelX;
    public static int moneyX;

    /** Chiều cao thanh MP. */
    private static int mpBarHeight;

    /** Mũi tên chỉ đường (4 khung hình). */
    public static Image[] arrowIcons;

    /** Ảnh khung trái và phải. */
    private static Image[] frames;

    /** Dấu báo còn trang thoại tiếp theo. */
    public static Image markerIcon;

    /** Biểu tượng kiếm theo cấp và phi tiêu. */
    public static Image[] weaponIcons;

    private Hud() {
    }

    /** Nạp ảnh mũi tên chỉ đường (một lần). */
    public static void loadArrowIcons() {
        if (arrowIcons == null) {
            arrowIcons = new Image[4];

            for(int i = 0; i < 4; ++i) {
                try {
                    arrowIcons[i] = Ui.loadArrow(i);
                } catch (Exception e) {
                }
            }
        }

    }

    /** Nạp ảnh và tính bố cục khung trạng thái theo kích thước màn hình. */
    public static void setup() {
        loadIcons();
        if (panelVisible) {
            try {
                if (frames == null) {
                    frames = new Image[2];

                    for(int i = 0; i < 2; ++i) {
                        frames[i] = Ui.loadHud(i);
                    }
                }

                leftFrameWidth = frames[0].getWidth();
                rightFrameWidth = frames[1].getWidth();
                stripWidth = Viewport.viewWidth - leftFrameWidth - rightFrameWidth + 1;
                barX = 78;
                barY = y + 7;
                barMaxWidth = Viewport.viewWidth - 134;
                expBarMaxWidth = Viewport.viewWidth - 84;
                mpBarHeight = 5;
            } catch (IOException e) {
            }
        }
    }

    /** Nạp biểu tượng vũ khí (theo cấp kiếm hiện tại) và dấu trang thoại. */
    public static void loadIcons() {
        try {
            if (markerIcon == null) {
                markerIcon = Ui.loadMarker();
            }

            (weaponIcons = new Image[2])[1] = Ui.loadDartIcon();
            weaponIcons[0] = Ui.loadSwordIcon(Inventory.swordLevel);
        } catch (Exception e) {
        }
    }

    /** Khi không có khung trạng thái riêng: vẽ đè thanh HP/MP/EXP, cấp và tiền lên góc màn chơi. */
    public static void drawOverlay(Graphics g) {
        if (!panelVisible) {
            g.translate(-g.getTranslateX(), -g.getTranslateY());
            g.drawImage(HeroSprites.heads[0], 0, 2, 0);
            g.setColor(0);
            g.drawRect(23, 2, barMaxWidth + 1, 4);
            g.drawRect(23, 8, barMaxWidth + 1, 4);
            g.drawRect(23, 14, barMaxWidth + 1, 3);
            g.setColor(16711680);
            int barWidth = PlayerStats.hpBarWidth(barMaxWidth);
            g.fillRect(24, 3, barWidth, 3);
            g.setColor(5570560);
            g.fillRect(barWidth + 24, 3, barMaxWidth - barWidth, 3);
            g.setColor(255);
            barWidth = PlayerStats.mpBarWidth(barMaxWidth);
            g.fillRect(24, 9, barWidth, 3);
            g.setColor(5570560);
            g.fillRect(barWidth + 24, 9, barMaxWidth - barWidth, 3);
            g.setColor(7274435);
            barWidth = Leveling.expBarWidth(barMaxWidth);
            g.fillRect(24, 15, barWidth, 2);
            g.setColor(19506);
            g.fillRect(barWidth + 24, 15, barMaxWidth - barWidth, 2);
            FontGraph.drawSmall(g, "lv:" + PlayerStats.level, 2, levelX, 7, 0);
            FontGraph.drawSmall(g, Inventory.money + "$", 1, moneyX, 7, 0);
        }
    }

    /** Vẽ khung trạng thái: thanh HP/MP/EXP, vũ khí, nhãn phím mềm. */
    public static void drawPanel(Graphics g) {
        dirty = false;
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        g.setClip(-1, y - 1, width + 2, 100);
        g.setColor(0);
        if (!panelVisible) {
            g.fillRect(0, y, width, 15);
            FontGraph.drawBold(g, leftSoftKeyLabel, 3, y + 3, 0);
            FontGraph.drawBold(g, rightSoftKeyLabel, width - 3, y + 3, 1);
        } else {
            g.fillRect(0, y + 33, width, 15);
            FontGraph.drawBold(g, leftSoftKeyLabel, 3, y + 36, 0);
            FontGraph.drawBold(g, rightSoftKeyLabel, width - 3, y + 36, 1);
            g.setColor(4352);
            g.fillRect(leftFrameWidth, y, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 35, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 33, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 30, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 28, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 26, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 12, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 24, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 18, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 16, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 2, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 4, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 6, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 14, stripWidth, 1);
            g.setColor(15649843);
            g.fillRect(leftFrameWidth, y + 1, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 5, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 17, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 34, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 29, stripWidth, 1);
            g.setColor(16767871);
            g.fillRect(leftFrameWidth, y + 3, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 15, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 27, stripWidth, 1);
            g.setColor(3355443);
            g.fillRect(leftFrameWidth, y + 7, stripWidth, 5);
            g.fillRect(leftFrameWidth, y + 19, stripWidth, 5);
            g.fillRect(leftFrameWidth, y + 31, stripWidth, 2);
            g.setColor(12281361);
            g.fillRect(leftFrameWidth, y + 25, stripWidth, 1);
            g.fillRect(leftFrameWidth, y + 13, stripWidth, 1);
            g.drawImage(frames[0], 0, y, 0);
            g.drawImage(frames[1], 0 + width, y, 24);
            int barWidth = PlayerStats.hpBarWidth(barMaxWidth);
            g.setColor(7798784);
            g.fillRect(barX, barY, barWidth, 2);
            g.setColor(13369344);
            g.fillRect(barX, barY + 1, barWidth, 4);
            barWidth = PlayerStats.mpBarWidth(barMaxWidth);
            g.setColor(119);
            g.fillRect(barX, barY + 12, barWidth, 2);
            g.setColor(204);
            g.fillRect(barX, barY + 14, barWidth, mpBarHeight - 2);
            FontGraph.drawSmall(g, "" + Inventory.money, 1, width - 18, y + 6, 2);
            barWidth = Leveling.expBarWidth(expBarMaxWidth);
            g.setColor(94373);
            g.fillRect(31, y + 31, barWidth, 1);
            g.setColor(65535);
            g.fillRect(31, y + 32, barWidth, 1);
            FontGraph.drawSmall(g, "" + PlayerStats.level, 2, 26, y + 11, 2);
            g.drawImage(weaponIcons[Player.weapon], 43, y + 16, 3);
            FontGraph.drawSmall(g, "" + (Inventory.potionCounts[0] + Inventory.potionCounts[1] + Inventory.potionCounts[2]), 2, width - 29, y + 19, 2);
            FontGraph.drawSmall(g, "" + (Inventory.potionCounts[3] + Inventory.potionCounts[4] + Inventory.potionCounts[5]), 2, width - 9, y + 19, 2);
        }
    }
}
