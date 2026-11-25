package core.render;

import core.player.Player;

/** Camera: vị trí góc trên trái của khung nhìn trên map, đích cần đi tới, rung màn hình. */
public final class Camera {
    /** Độ lệch x của 4 bước rung màn hình. */
    private static int[] shakeOffsetX = new int[]{-1, 2, 1, -2};

    /** Độ lệch y của 4 bước rung màn hình. */
    private static int[] shakeOffsetY = new int[]{-3, 2, -1, 1};

    /** Bước rung hiện tại (0..3), -1 nếu không rung. */
    public static int shakeStep = -1;

    /** Khoảng cách từ đỉnh khung nhìn tới chân nhân vật (3/4 chiều cao khung nhìn). */
    public static int offsetY;

    /** Camera nhìn trước theo hướng nhân vật quay mặt (1/6 chiều rộng khung nhìn). */
    public static int leadX;

    /** Camera tự đi theo nhân vật (cutscene có thể tắt để lia camera). */
    public static boolean followsPlayer = true;

    /** Vị trí camera cần tới (pixel trên map). */
    public static int targetX;
    public static int targetY;

    /** Vận tốc trượt về đích (pixel/16 mỗi frame). Ở menu chính: vận tốc lượn qua map nền. */
    public static int vx;
    public static int vy;

    /** Phần lẻ 1/16 pixel còn dư khi trượt. */
    public static int subpixelX;
    public static int subpixelY;

    /** Góc trên trái của khung nhìn (pixel trên map). */
    public static int x;
    public static int y;

    /** Giới hạn để khung nhìn không vượt ra ngoài map. */
    public static int maxX;
    public static int maxY;

    private Camera() {
    }

    /** Rung màn hình khi nhân vật đang biến hình (đòn đánh mạnh). */
    public static void shakeIfTransformed() {
        if (Player.transformed) {
            shakeStep = 0;
        }

    }

    /** Camera trượt dần tới đích rồi rung (nếu đang rung), giữ trong giới hạn map. */
    public static void update() {
        if (x != targetX || y != targetY) {
            vx = targetX - x << 2;
            vy = targetY - y << 2;
            subpixelX += vx;
            x += subpixelX >> 4;
            subpixelX &= 15;
            subpixelY += vy;
            y += subpixelY >> 4;
            subpixelY &= 15;
            if (x < 0) {
                x = 0;
            }

            if (x > maxX) {
                x = maxX;
            }

            if (y < 0) {
                y = 0;
            }

            if (y > maxY) {
                y = maxY;
            }
        }

        if (shakeStep >= 0) {
            x += shakeOffsetX[shakeStep];
            y += shakeOffsetY[shakeStep];
            if (++shakeStep == 4) {
                shakeStep = -1;
            }

            if (x < 0) {
                x = 0;
            }

            if (x > maxX) {
                x = maxX;
            }

            if (y < 0) {
                y = 0;
            }

            if (y > maxY) {
                y = maxY;
            }
        }
    }
}
