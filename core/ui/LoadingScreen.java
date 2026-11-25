package core.ui;

/** Tiến độ màn hình tải map. */
public final class LoadingScreen {
    /** Tiến độ đã nạp xong (Midlet.run tăng dần) và tiến độ đang hiển thị (đuổi theo target). */
    public static int target;

    /** Bộ đếm không dùng tới (còn sót lại trong bản gốc). */
    private static int unusedCounter;
    public static int progress;

    private LoadingScreen() {
    }

    /** Đặt lại tiến độ về 0. */
    public static void reset() {
        progress = 0;
        target = 0;
        unusedCounter = 0;
    }
}
