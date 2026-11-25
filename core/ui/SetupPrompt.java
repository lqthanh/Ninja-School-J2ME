package core.ui;

import assets.FontGraph;
import assets.Sound;
import assets.Ui;
import core.app.Settings;
import core.input.Input;
import core.render.Viewport;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Hai câu hỏi lúc khởi động (GameState.SETUP_PROMPT): bật âm thanh, rồi chất lượng hình
 * ảnh. Phím mềm trái = Bật / Cao, phím mềm phải = Tắt / Thấp.
 */
public final class SetupPrompt {
    public static final int STEP_SOUND = 0;
    public static final int STEP_GRAPHICS = 1;

    public static Image image;
    private static int step;
    /** Đã trả lời xong cả 2 câu; frame sau b chuyển sang màn hình chính. */
    public static boolean done;

    private SetupPrompt() {
    }

    public static void open() {
        try {
            image = Ui.loadSoundIcon();
        } catch (Exception ex) {
        }
    }

    public static void update() {
        boolean answered = false;
        if (Input.pressed[Input.KEY_SOFT_LEFT]) {
            if (step == STEP_SOUND) {
                Sound.disabled = false;
            } else {
                Settings.lowGraphics = false;
            }

            answered = true;
        } else if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
            if (step == STEP_SOUND) {
                Sound.disabled = true;
            } else {
                Settings.lowGraphics = true;
            }

            answered = true;
        }

        if (answered) {
            Input.clearPressed();
            Input.clearHeld();
            if (step == STEP_SOUND) {
                try {
                    image = Ui.loadSmileys();
                } catch (Exception ex) {
                }

                step = STEP_GRAPHICS;
            } else {
                done = true;
            }
        }
    }

    public static void draw(Graphics g) {
        g.setColor(0);
        g.fillRect(0, 0, Viewport.screenWidth, Viewport.screenHeight);
        if (done) {
            return;
        }

        g.drawImage(image, Viewport.screenCenterX, Viewport.screenCenterY - 30, 3);
        if (step == STEP_SOUND) {
            FontGraph.drawBold(g, "Bật âm thanh?", Viewport.screenCenterX, Viewport.screenCenterY + 15, 2);
            FontGraph.drawBold(g, "Bật", 4, Viewport.screenHeight - 14, 0);
            FontGraph.drawBold(g, "Tắt", Viewport.screenWidth - 4, Viewport.screenHeight - 14, 1);
        } else {
            FontGraph.drawBold(g, "Chất lượng hình ảnh?", Viewport.screenCenterX, Viewport.screenCenterY + 15, 2);
            FontGraph.drawBold(g, "Cao", 4, Viewport.screenHeight - 14, 0);
            FontGraph.drawBold(g, "Thấp", Viewport.screenWidth - 4, Viewport.screenHeight - 14, 1);
        }
    }
}
