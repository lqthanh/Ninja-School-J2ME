package core.app;

import javax.microedition.lcdui.Canvas;
import javax.microedition.midlet.MIDlet;

/**
 * Các đối tượng cấp ứng dụng mà {@code core.*} cần nhưng không tham chiếu trực tiếp được (lớp
 * Midlet và GameCanvas nằm ở default package). Midlet gán các giá trị này khi khởi động.
 */
public final class App {
    /** MIDlet đang chạy. */
    public static MIDlet midlet;
    /** Tác vụ nạp tài nguyên map (Midlet.run), chạy ở thread riêng trong màn hình tải. */
    public static Runnable loader;
    /** Canvas chính của game. */
    public static Canvas canvas;
    /** Vòng lặp game còn chạy (GameCanvas.run). */
    public static boolean running = true;

    private App() {
    }

    /** Bắt đầu nạp tài nguyên ở thread riêng. */
    public static void startLoader() {
        (new Thread(loader)).start();
    }

    /** Dừng vòng lặp game và thoát ứng dụng. */
    public static void shutdown() {
        running = false;
        System.gc();
        midlet.notifyDestroyed();
    }
}
