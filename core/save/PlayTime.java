package core.save;

/** Tổng thời gian chơi, lưu cùng file save (tính bằng giây). */
public final class PlayTime {
    /** Số giây đã chơi tính đến lần cập nhật gần nhất. */
    public static int savedSeconds;
    /** Thời điểm (ms) của lần cập nhật gần nhất; 0 nếu chưa bắt đầu chơi. */
    private static long sessionStartMs;

    private PlayTime() {
    }

    /** Bắt đầu đếm từ lúc này (vào game mới hoặc tải game). */
    public static void start() {
        sessionStartMs = System.currentTimeMillis();
    }

    /** Cộng thời gian từ lần cập nhật trước vào {@link #savedSeconds} (gọi trước khi lưu). */
    public static void checkpoint() {
        if (sessionStartMs > 0L) {
            savedSeconds += (int) ((System.currentTimeMillis() - sessionStartMs) / 1000L);
        }

        sessionStartMs = System.currentTimeMillis();
    }

    public static int totalSeconds() {
        return savedSeconds + (int) ((System.currentTimeMillis() - sessionStartMs) / 1000L);
    }
}
