package core.ui;

import assets.FontGraph;
import assets.Sound;
import core.input.Input;
import core.render.Viewport;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/** Menu trong game (mở bằng phím mềm trái khi đang chơi). */
public final class GameMenu {
    public static final int ITEM_USE_ITEM = 0;
    public static final int ITEM_WORLD_MAP = 1;
    public static final int ITEM_SAVE_AND_EXIT = 2;
    /** Mục "Nhạc: Bật/Tắt": chọn để bật/tắt nhạc (b gọi Sound.setEnabled). */
    public static final int ITEM_MUSIC = 3;
    public static final int ITEM_COUNT = 4;

    /** Kết quả của {@link #update()}. */
    public static final int RESULT_NONE = -1;
    public static final int RESULT_CLOSE = -2;
    // Các giá trị >= 0 là mục vừa được chọn (ITEM_*).

    public static String[] items = new String[]{"Sử dụng item",
            "Xem bản đồ",
            "Lưu và thoát",
            "Nhạc"};
    public static int selection;
    public static int y;
    /** Đếm frame, làm con trỏ (biểu tượng kiếm) rung nhẹ. */
    public static int tick;

    private GameMenu() {
    }

    public static void open() {
        y = Viewport.viewCenterY - 40;
        UiFrame.loadCorners();
    }

    /**
     * Xử lý phím một frame. Trả về {@link #RESULT_CLOSE} khi nhấn phím mềm phải,
     * chỉ số mục (ITEM_*) khi chọn, hoặc {@link #RESULT_NONE}.
     */
    public static int update() {
        if (++tick > 10000) {
            tick = 0;
        }

        int result = RESULT_NONE;
        boolean handled = false;
        if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
            handled = true;
            result = RESULT_CLOSE;
        } else if (!Input.pressed[Input.KEY_FIRE] && !Input.pressed[Input.KEY_SOFT_LEFT]) {
            if (Input.pressed[Input.KEY_DOWN]) {
                handled = true;
                if (++selection > ITEM_COUNT - 1) {
                    selection = 0;
                }
            } else if (Input.pressed[Input.KEY_UP]) {
                handled = true;
                if (--selection < 0) {
                    selection = ITEM_COUNT - 1;
                }
            } else if (Input.pressed[Input.KEY_LEFT]) {
                handled = true;
            } else if (Input.pressed[Input.KEY_RIGHT]) {
                handled = true;
            }
        } else {
            handled = true;
            result = selection;
        }

        if (handled) {
            Input.clearPressed();
        }

        return result;
    }

    public static void draw(Graphics g, Image cursorIcon) {
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        UiFrame.drawPanel(g, y, "Chọn", "Quay lại");

        for (int i = 0; i < ITEM_COUNT; ++i) {
            String label = items[i];
            if (i == ITEM_MUSIC) {
                label = label + (Sound.disabled ? ": Tắt" : ": Bật");
            }

            FontGraph.drawBold(g, label, Viewport.screenCenterX - 30, y + i * 18, 0);
        }

        g.drawImage(cursorIcon, Viewport.screenCenterX - 55 + tick % 2, y + selection * 18 - tick % 2, 0);
        UiFrame.drawCorners(g, y);
    }
}
