package core.ui;

import assets.FontGraph;
import core.app.GameState;
import core.app.Settings;
import core.render.Camera;
import core.render.Viewport;
import javax.microedition.lcdui.Graphics;

/**
 * Bong bóng lời thoại của nhân vật/NPC.
 *
 * Lời thoại gồm nhiều trang ({@link #pages}), mỗi trang là các dòng đã ngắt theo độ rộng
 * bong bóng. Chữ hiện dần từng ký tự ({@link #typeTick()}); bấm phím bắn thì hiện hết
 * trang hoặc sang trang sau ({@link #advance()}). Hết trang cuối thì game ẩn bong bóng
 * và chạy hành động {@link #finishAction}.
 */
public final class Dialogue {
    /** Không có hành động khi đóng lời thoại. */
    public static final int NO_ACTION = -1;
    public static boolean visible;
    private static String[][] pages;
    private static int pageCount;
    private static int page;
    /** Số dòng đã hiện đủ của trang hiện tại. */
    private static int shownLines;
    /** Số ký tự đã hiện của dòng đang gõ. */
    private static int shownChars;
    /** Hành động chạy khi đọc hết lời thoại ({@link core.quest.QuestAction#onDialogueFinished}). */
    public static int finishAction;
    /** Vị trí người nói (pixel trên map), mũi tên của bong bóng chỉ vào đây. */
    private static int speakerX;
    private static int speakerY;
    private static int boxX;
    private static int boxWidth;
    private static int boxLines;

    private Dialogue() {
    }

    /** Đã đọc hết mọi trang. */
    public static boolean isFinished() {
        return page == pageCount;
    }

    /** Bấm phím: đang gõ thì hiện hết trang, đã hiện hết thì sang trang sau. */
    public static void advance() {
        if (shownLines < pages[page].length) {
            shownLines = pages[page].length;
            shownChars = 0;
        } else {
            ++page;
            shownLines = 0;
            shownChars = 0;
        }
    }

    /** Mỗi frame hiện thêm một ký tự của dòng đang gõ. */
    public static void typeTick() {
        if (visible && shownLines < pages[page].length && ++shownChars >= pages[page][shownLines].length()) {
            shownChars = 0;
            ++shownLines;
        }
    }

    public static void draw(Graphics g) {
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        g.setClip(-1, -1, Viewport.screenWidth + 2, Viewport.screenHeight + 2);
        boxLines = pages[page].length;
        int boxY = Dialogue.speakerY - Camera.y - (boxLines << 4);
        boolean boxBelowSpeaker = true;
        if (boxY < 10) {
            boxBelowSpeaker = false;
            boxY = 10;
        }

        g.setColor(16777215);
        if (Settings.lowGraphics) {
            g.fillRect(boxX, boxY - 5, boxWidth, (boxLines << 4) + 7);
        } else {
            g.fillRoundRect(boxX, boxY - 5, boxWidth, (boxLines << 4) + 7, 10, 10);
        }

        g.setColor(17408);
        if (Settings.lowGraphics) {
            g.drawRect(boxX - 1, boxY - 5, boxWidth + 1, (boxLines << 4) + 7);
        } else {
            g.drawRoundRect(boxX - 1, boxY - 5, boxWidth + 1, (boxLines << 4) + 7, 10, 10);
        }

        for(int line = 0; line < shownLines; ++line) {
            FontGraph.drawRegular(g, pages[page][line], boxX + (boxWidth >> 1), boxY + (line << 4), 2);
        }

        if (shownLines < pages[page].length) {
            FontGraph.drawRegular(g, pages[page][shownLines].substring(0, shownChars), boxX + (boxWidth >> 1), boxY + (shownLines << 4), 2);
        }

        g.setColor(0);
        g.fillRect(0, Hud.y + 33, Hud.width, 15);
        if (boxBelowSpeaker && Dialogue.speakerX > Camera.x + 10 && Dialogue.speakerX < Camera.x + Viewport.viewWidth - 10) {
            g.drawImage(Hud.markerIcon, Dialogue.speakerX - Camera.x, Dialogue.speakerY - Camera.y + 2, 17);
        }

        if (GameState.tick % 10 < 8) {
            FontGraph.drawBold(g, "Tiếp tục", Viewport.viewCenterX, Viewport.screenHeight - 13, 2);
        }

    }

    public static void open(String[] lines, int finishAction, int speakerX, int speakerY) {
        Dialogue.finishAction = finishAction;
        Dialogue.speakerX = speakerX;
        Dialogue.speakerY = speakerY;
        pageCount = lines.length;
        page = 0;
        pages = new String[pageCount][];
        if ((boxWidth = Viewport.viewWidth >> 1) < 120) {
            boxWidth = 120;
        }

        for(int i = 0; i < pageCount; ++i) {
            pages[i] = FontGraph.wrapRegularText(lines[i], boxWidth - 20);
        }

        if ((boxX = Dialogue.speakerX - (boxWidth >> 1) - Camera.x) < 2) {
            boxX = 2;
        }

        if (boxX + boxWidth > Viewport.viewWidth - 2) {
            boxX = Viewport.viewWidth - 2 - boxWidth;
        }

        visible = true;
        shownChars = 0;
        shownLines = 0;
    }
}
