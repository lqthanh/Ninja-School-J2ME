package core.ui;

import assets.FontGraph;
import assets.Ui;
import core.app.GameState;
import core.input.Input;
import core.render.Viewport;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Bản đồ thế giới (trạng thái GameState.WORLD_MAP): di chuyển con trỏ để chọn một trong
 * 19 vùng và trả tiền để chuyển đến đó. Chỉ chuyển được tới vùng đã từng đi bộ đến
 * ({@link #visited}).
 *
 * Toạ độ trên bản đồ tính theo pixel của ảnh bản đồ (280x280); view cuộn mượt về phía
 * con trỏ.
 */
public final class WorldMap {
    public static final int AREA_COUNT = 19;
    /** Kích thước ảnh bản đồ. */
    public static final int IMAGE_SIZE = 280;
    public static final int NO_SELECTION = -1;
    public static final int CURSOR_SPEED = 4;
    /** Con trỏ cách biểu tượng vùng dưới khoảng này (theo mỗi trục) thì vùng được chọn. */
    public static final int SELECT_RADIUS = 8;

    /** Kết quả của {@link #update()}. */
    public static final int RESULT_NONE = 0;
    public static final int RESULT_CLOSE = 1;

    private static String[] names = new String[]{"Trường Hirosaki",
            "Khu luyện tập",
            "Làng Kenshin",
            "Rừng đào Sakura",
            "Rừng trúc Ura",
            "Thác Kitajima",
            "Cánh đồng Mishima",
            "Sông Watamaro",
            "Nghĩa địa Izuko",
            "Miếu Kojin",
            "Miếu Kamo",
            "Miếu Yazaka",
            "Rừng gỗ Kouji",
            "Rừng chông Hakata",
            "Vách đá Ito",
            "Thung lũng Taira",
            "Thôn Sanzu",
            "Lối vào Orochi",
            "Ngôi đền Orochi"};
    /** Chi phí đi qua mỗi vùng; giá chuyển = 2/3 tổng chi phí các vùng nằm giữa (tính cả 2 đầu). */
    private static int[] travelCosts = new int[]{50, 60, 70, 80, 90, 70, 100, 120, 150, 180, 220, 250, 300, 320, 350, 370, 390, 420, 450};
    /** Vùng đã từng đi bộ đến (chỉ số = mã map). */
    public static boolean[] visited = new boolean[AREA_COUNT];
    /** Vị trí biểu tượng mỗi vùng trên ảnh bản đồ. */
    private static int[] iconX = new int[]{49, 31, 98, 112, 106, 42, 75, 84, 37, 39, 80, 117, 157, 201, 208, 170, 186, 219, 161};
    private static int[] iconY = new int[]{35, 65, 38, 75, 98, 97, 95, 128, 142, 185, 212, 189, 189, 204, 156, 142, 96, 59, 39};
    /** Ô xuất hiện của nhân vật khi chuyển đến vùng (theo ô 24px). */
    public static int[] spawnTileX = new int[]{62, 52, 17, 4, 8, 12, 14, 5, 1, 2, 3, 2, 2, 2, 2, 1, 2, 3, 1};
    public static int[] spawnTileY = new int[]{17, 9, 12, 9, 10, 5, 9, 7, 12, 11, 4, 11, 8, 9, 7, 6, 11, 16, 8};

    private static Image image;
    private static Image crossIcon;

    /** Vùng người chơi đang đứng. */
    private static int current;
    /** Vùng con trỏ đang chọn, {@link #NO_SELECTION} nếu không trỏ vào vùng nào. */
    public static int selected;
    /** Nhãn phím mềm trái: "Chuyển đến" khi đang chọn một vùng. */
    private static String actionLabel = "Chuyển đến";
    /** Giá chuyển đến vùng đang chọn, tính lúc mở hộp xác nhận. */
    public static int travelCost;

    private static int cursorX;
    private static int cursorY;
    private static int tick;

    private static int scrollX;
    private static int scrollY;
    private static int scrollTargetX;
    private static int scrollTargetY;
    private static int scrollMaxX;
    private static int scrollMaxY;
    /** Cuộn mượt: mỗi frame tiến 2/16 khoảng cách còn lại, phần lẻ được cộng dồn. */
    private static int scrollStepX;
    private static int scrollStepY;
    private static int scrollFracX;
    private static int scrollFracY;

    private WorldMap() {
    }

    /** Vùng trên bản đồ thế giới ứng với map {@code mapId} (map phụ quy về vùng chứa nó). */
    private static int areaOfMap(int mapId) {
        switch (mapId) {
            case 95:
                return 18;
            case 96:
                return 11;
            case 97:
                return 7;
            case 99:
                return 0;
            default:
                return mapId;
        }
    }

    public static void open(int mapId) {
        scrollMaxX = IMAGE_SIZE - Viewport.viewWidth;
        scrollMaxY = IMAGE_SIZE - Viewport.viewHeight;
        selected = 0;

        try {
            image = Ui.loadWorldMap();
            crossIcon = Ui.loadCross();
        } catch (Exception ex) {
        }

        current = areaOfMap(mapId);
        cursorX = iconX[current];
        cursorY = iconY[current];
        updateSelection();
        scrollTargetX = cursorX - Viewport.viewCenterX;
        scrollTargetY = cursorY - Viewport.viewCenterY;
        clampScroll();
    }

    public static void close() {
        image = null;
        crossIcon = null;
        System.gc();
    }

    /** Chọn vùng có biểu tượng gần con trỏ. */
    private static void updateSelection() {
        selected = NO_SELECTION;
        actionLabel = "";

        for (int i = 0; i < AREA_COUNT; ++i) {
            if (abs(cursorX - iconX[i]) < SELECT_RADIUS && abs(cursorY - iconY[i]) < SELECT_RADIUS) {
                selected = i;
                actionLabel = "Chuyển đến";
                return;
            }
        }
    }

    /** Giá chuyển từ vùng hiện tại đến vùng đang chọn. */
    public static int computeTravelCost() {
        int from = current < selected ? current : selected;
        int to = current > selected ? current : selected;
        int sum = 0;

        for (int i = from; i <= to; ++i) {
            sum += travelCosts[i];
        }

        return (sum << 1) / 3;
    }

    /**
     * Xử lý một frame. Nhấn chọn mở hộp thông báo (GameState.MESSAGE). Nhấn phím mềm
     * phải đóng bản đồ, trả về {@link #RESULT_CLOSE} và giả lập nhấn phím mềm trái để
     * frame sau quay lại menu trong game.
     */
    public static int update() {
        if (++tick > 10000) {
            tick = 0;
        }

        if (scrollX != scrollTargetX || scrollY != scrollTargetY) {
            scrollStepX = scrollTargetX - scrollX << 1;
            scrollStepY = scrollTargetY - scrollY << 1;
            scrollFracX += scrollStepX;
            scrollX += scrollFracX >> 4;
            scrollFracX &= 15;
            scrollFracY += scrollStepY;
            scrollY += scrollFracY >> 4;
            scrollFracY &= 15;
            clampScroll();
        }

        if (Input.pressed[Input.KEY_FIRE] || Input.pressed[Input.KEY_SOFT_LEFT]) {
            Input.clearPressed();
            if (selected != NO_SELECTION) {
                if (!visited[selected]) {
                    MessageBox.show(MessageType.TRAVEL_NOT_VISITED);
                } else if (current == selected) {
                    MessageBox.show(MessageType.TRAVEL_ALREADY_HERE);
                } else {
                    MessageBox.show(MessageType.TRAVEL_CONFIRM);
                }

                GameState.current = GameState.MESSAGE;
            }
        }

        if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
            close();
            UiFrame.release();
            Input.clearPressed();
            Input.clearHeld();
            Input.pressed[Input.KEY_SOFT_LEFT] = true;
            return RESULT_CLOSE;
        }

        boolean moved = false;
        if (Input.held[Input.KEY_UP]) {
            if ((cursorY -= CURSOR_SPEED) < -20) {
                cursorY = -20;
            }

            moved = true;
        }

        if (Input.held[Input.KEY_DOWN]) {
            if ((cursorY += CURSOR_SPEED) > IMAGE_SIZE - 1) {
                cursorY = IMAGE_SIZE - 1;
            }

            moved = true;
        }

        if (Input.held[Input.KEY_LEFT]) {
            if ((cursorX -= CURSOR_SPEED) < -20) {
                cursorX = -20;
            }

            moved = true;
        }

        if (Input.held[Input.KEY_RIGHT]) {
            if ((cursorX += CURSOR_SPEED) > IMAGE_SIZE - 1) {
                cursorX = IMAGE_SIZE - 1;
            }

            moved = true;
        }

        if (moved) {
            scrollTargetX = cursorX - Viewport.viewCenterX;
            scrollTargetY = cursorY - Viewport.viewCenterY;
            updateSelection();
        }

        return RESULT_NONE;
    }

    /**
     * @param playerIcon biểu tượng đánh dấu vùng hiện tại (đầu nhân vật)
     * @param cursorIcon biểu tượng con trỏ (kiếm)
     */
    public static void draw(Graphics g, Image playerIcon, Image cursorIcon) {
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        g.setColor(0);
        g.fillRect(0, 0, Viewport.viewWidth, Viewport.viewHeight);
        g.fillRect(0, Viewport.screenHeight - 13, Viewport.screenWidth, 13);
        FontGraph.drawBold(g, actionLabel, 3, Viewport.screenHeight - 13, 0);
        FontGraph.drawBold(g, "Quay lại", Viewport.screenWidth - 3, Viewport.screenHeight - 13, 1);
        g.setClip(0, 0, Viewport.viewWidth, Viewport.viewHeight);
        g.translate(-scrollX, -scrollY);
        g.drawImage(image, 0, 0, 0);

        for (int i = 0; i < AREA_COUNT; ++i) {
            if (i == current) {
                g.drawImage(playerIcon, iconX[i] + 10, iconY[i], 3);
            }

            // Vùng đang chọn nhấp nháy.
            if (visited[i] && (i != selected || tick % 10 > 5)) {
                g.drawImage(crossIcon, iconX[i], iconY[i], 3);
            }
        }

        g.drawImage(cursorIcon, cursorX, cursorY, 24);
        if (selected != NO_SELECTION) {
            int cx = Viewport.screenCenterX;
            int top = Viewport.viewHeight - 35;
            g.translate(-g.getTranslateX(), -g.getTranslateY());
            g.setColor(UiFrame.FILL_COLOR);
            g.fillRect(cx - 60, top, 120, 33);
            g.setColor(UiFrame.BORDER_COLOR);
            g.drawRect(cx - 59, top + 1, 117, 30);
            FontGraph.drawBold(g, names[selected], cx, top + 10, 2);
            g.drawImage(UiFrame.corners[0], cx - 60, top, 20);
            g.drawImage(UiFrame.corners[1], cx + 60, top, 24);
            g.drawImage(UiFrame.corners[2], cx - 60, top + 33, 36);
            g.drawImage(UiFrame.corners[3], cx + 60, top + 33, 40);
        }
    }

    private static void clampScroll() {
        if (scrollX < -40) {
            scrollX = -40;
        }

        if (scrollX > scrollMaxX) {
            scrollX = scrollMaxX;
        }

        if (scrollY < -40) {
            scrollY = -40;
        }

        if (scrollY > scrollMaxY) {
            scrollY = scrollMaxY;
        }
    }

    private static int abs(int value) {
        return value > 0 ? value : -value;
    }
}
