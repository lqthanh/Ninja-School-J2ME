package core.render;

/**
 * Kích thước màn hình và vùng vẽ bản đồ (view).
 *
 * View là phần màn hình dành cho thế giới game; phần còn lại phía dưới là bảng HUD
 * (xem {@code GameCanvas.setupViewport()}).
 */
public final class Viewport {
    public static int screenWidth;
    public static int screenHeight;
    public static int screenCenterX;
    public static int screenCenterY;

    public static int viewWidth;
    public static int viewHeight;
    public static int viewCenterX;
    public static int viewCenterY;

    private Viewport() {
    }
}
