package core.input;

/**
 * Trạng thái phím bấm và cảm ứng.
 *
 * Mỗi phím logic có một chỉ số (KEY_*) trong 2 mảng:
 *  - {@link #held}    : phím đang được giữ (true từ lúc nhấn đến lúc nhả).
 *  - {@link #pressed} : phím vừa được nhấn, chưa được xử lý. Game đọc rồi tự xóa bằng
 *                       {@link #clearPressed()} hoặc gán false cho từng phím.
 *
 * Phím điều hướng dùng chung chỉ số với phím số tương ứng (lên = 2, trái = 4, bắn = 5,
 * phải = 6, xuống = 8), nên nhấn phím số hay phím mũi tên đều cho cùng kết quả.
 */
public final class Input {
    // =====================================================================================
    // #region Key index

    public static final int KEY_NUM0 = 0;
    public static final int KEY_NUM1 = 1;
    public static final int KEY_NUM2 = 2;
    public static final int KEY_NUM3 = 3;
    public static final int KEY_NUM4 = 4;
    public static final int KEY_NUM5 = 5;
    public static final int KEY_NUM6 = 6;
    public static final int KEY_NUM7 = 7;
    public static final int KEY_NUM8 = 8;
    public static final int KEY_NUM9 = 9;

    public static final int KEY_UP = KEY_NUM2;
    public static final int KEY_LEFT = KEY_NUM4;
    public static final int KEY_FIRE = KEY_NUM5;
    public static final int KEY_RIGHT = KEY_NUM6;
    public static final int KEY_DOWN = KEY_NUM8;

    public static final int KEY_STAR = 10;
    public static final int KEY_POUND = 11;
    public static final int KEY_SOFT_LEFT = 12;
    public static final int KEY_SOFT_RIGHT = 13;

    public static final int KEY_COUNT = 14;

    // #endregion
    // =====================================================================================
    // #region Key code (mã phím của máy, theo chuẩn Nokia)

    public static final int CODE_UP = -1;
    public static final int CODE_DOWN = -2;
    public static final int CODE_LEFT = -3;
    public static final int CODE_RIGHT = -4;
    public static final int CODE_FIRE = -5;
    public static final int CODE_SOFT_LEFT = -6;
    public static final int CODE_SOFT_RIGHT = -7;
    public static final int CODE_CLEAR = -8;
    public static final int CODE_ENTER = 10;
    public static final int CODE_STAR = 42;
    public static final int CODE_POUND = 35;
    public static final int CODE_NUM0 = 48;
    public static final int CODE_NUM9 = 57;

    /** Mã phím bắn của máy Motorola (Canvas.getKeyCode(FIRE) trả về giá trị này). */
    public static final int MOTOROLA_CODE_FIRE = -20;

    // #endregion
    // =====================================================================================

    public static final boolean[] held = new boolean[KEY_COUNT];
    public static final boolean[] pressed = new boolean[KEY_COUNT];

    /** Máy dùng bộ mã phím Motorola; mã phím được đổi về chuẩn Nokia trước khi xử lý. */
    public static boolean motorolaKeys;

    /** Vừa nhả tay khỏi màn hình cảm ứng, chưa được xử lý. */
    public static boolean pointerReleased;
    public static int pointerX;
    public static int pointerY;

    private Input() {
    }

    public static void clearHeld() {
        for (int i = 0; i < KEY_COUNT; ++i) {
            held[i] = false;
        }
    }

    public static void clearPressed() {
        for (int i = 0; i < KEY_COUNT; ++i) {
            pressed[i] = false;
        }
    }

    /** Đổi mã phím Motorola về mã chuẩn Nokia; máy khác giữ nguyên. */
    public static int normalizeKeyCode(int code) {
        if (!motorolaKeys) {
            return code;
        }

        switch (code) {
            case -23:
                return CODE_CLEAR;
            case -22:
            case 22:
                return CODE_SOFT_RIGHT;
            case -21:
            case 21:
                return CODE_SOFT_LEFT;
            case MOTOROLA_CODE_FIRE:
                return CODE_FIRE;
            case -6:
                return CODE_DOWN;
            case -5:
                return CODE_RIGHT;
            case -2:
                return CODE_LEFT;
            default:
                return code;
        }
    }

    /** Nhấn phím: đánh dấu phím đang giữ và vừa được nhấn. */
    public static void keyDown(int code) {
        int key = keyIndex(code);
        if (key >= 0) {
            held[key] = true;
            pressed[key] = true;
        }
    }

    /**
     * Nhả phím: bỏ trạng thái đang giữ. Riêng 4 phím mũi tên cũng xóa luôn trạng thái
     * vừa nhấn (giống bản gốc).
     */
    public static void keyUp(int code) {
        int key = keyIndex(code);
        if (key >= 0) {
            held[key] = false;
            if (code >= CODE_RIGHT && code <= CODE_UP) {
                pressed[key] = false;
            }
        }
    }

    /** Chỉ số phím logic của mã phím, -1 nếu game không dùng phím này. */
    private static int keyIndex(int code) {
        switch (code) {
            case -22:
            case CODE_SOFT_RIGHT:
                return KEY_SOFT_RIGHT;
            case -21:
            case CODE_SOFT_LEFT:
                return KEY_SOFT_LEFT;
            case CODE_FIRE:
            case CODE_ENTER:
                return KEY_FIRE;
            case CODE_RIGHT:
                return KEY_RIGHT;
            case CODE_LEFT:
                return KEY_LEFT;
            case CODE_DOWN:
                return KEY_DOWN;
            case CODE_UP:
                return KEY_UP;
            case CODE_POUND:
                return KEY_POUND;
            case CODE_STAR:
                return KEY_STAR;
            default:
                if (code >= CODE_NUM0 && code <= CODE_NUM9) {
                    return code - CODE_NUM0;
                }

                return -1;
        }
    }
}
