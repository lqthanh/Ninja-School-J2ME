package core.ui;

import assets.Ui;
import core.render.Viewport;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/** Logo nhà phát hành lúc khởi động (GameState.LOGO): hiện dần, giữ, rồi mờ dần. */
public final class LogoScreen {
    /** Logo có 5 khung hình (0 = mờ nhất, 4 = rõ nhất). */
    public static final int LAST_FRAME = 4;
    /** Bắt đầu mờ dần sau frame này. */
    public static final int FADE_OUT_START = 46;
    /** Kết thúc logo sau frame này. */
    public static final int END_TICK = 51;

    public static Image image;
    public static int tick;
    /** Khung hình logo đang hiện. */
    public static int frame;

    private LogoScreen() {
    }

    public static void load() {
        try {
            image = Ui.loadLogo();
        } catch (Exception ex) {
        }
    }

    /** Cập nhật một frame; trả về true khi logo đã chạy xong (ảnh đã được giải phóng). */
    public static boolean update() {
        boolean finished = false;
        if (tick < LAST_FRAME) {
            frame = tick;
        }

        if (tick > END_TICK) {
            image = null;
            finished = true;
        } else if (tick > FADE_OUT_START) {
            frame = LAST_FRAME - (tick - FADE_OUT_START);
        }

        ++tick;
        return finished;
    }

    public static void draw(Graphics g) {
        g.setColor(0);
        g.fillRect(0, 0, Viewport.screenWidth, Viewport.screenHeight);
        if (image != null && frame >= 0) {
            g.drawRegion(image, 0, frame * Ui.LOGO_FRAME_HEIGHT, 71, Ui.LOGO_FRAME_HEIGHT, 0, Viewport.screenWidth >> 1, Viewport.screenHeight >> 1, 3);
        }
    }
}
