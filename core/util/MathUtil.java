package core.util;

/** Hàm số học dùng chung. */
public final class MathUtil {
    private MathUtil() {
    }

    /** Giá trị tuyệt đối. */
    public static final int abs(int value) {
        return value > 0 ? value : -value;
    }
}
