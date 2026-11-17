package core.ui;

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
    public static String[][] pages;
    public static int pageCount;
    public static int page;
    /** Số dòng đã hiện đủ của trang hiện tại. */
    public static int shownLines;
    /** Số ký tự đã hiện của dòng đang gõ. */
    public static int shownChars;
    /** Hành động chạy khi đọc hết lời thoại (b.onDialogueFinished). */
    public static int finishAction;
    /** Vị trí người nói (pixel trên map), mũi tên của bong bóng chỉ vào đây. */
    public static int speakerX;
    public static int speakerY;
    public static int boxX;
    public static int boxWidth;

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
}
