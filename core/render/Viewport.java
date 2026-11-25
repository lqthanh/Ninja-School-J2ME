package core.render;

import core.map.GameMap;
import core.player.Player;
import core.ui.Hud;
import core.ui.Letterbox;

/**
 * Kích thước màn hình và vùng vẽ bản đồ (view).
 *
 * View là phần màn hình dành cho thế giới game; phần còn lại phía dưới là bảng HUD
 * (xem {@link #setup()}).
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

    public static final void setup() {
        viewWidth = screenWidth;
        if (screenHeight >= 208) {
            Hud.panelVisible = true;
            Hud.height = 50;
            viewHeight = screenHeight - Hud.height;
            Hud.width = viewWidth;
            Hud.x = 0;
            Hud.y = viewHeight + 1;
        } else {
            Hud.height = 16;
            viewHeight = screenHeight - Hud.height;
            Hud.panelVisible = false;
            Hud.height = 0;
            Hud.width = viewWidth;
            Hud.x = 0;
            Hud.y = viewHeight + 1;
            Hud.levelX = (Hud.barMaxWidth = viewWidth / 3) + 29;
            Hud.moneyX = Hud.barMaxWidth + 50;
        }

        if ((Letterbox.maxHeight = screenHeight / 6) < 48) {
            Letterbox.maxHeight = 48;
        }

        viewCenterX = viewWidth >> 1;
        viewCenterY = viewHeight >> 1;
        Camera.offsetY = 3 * viewHeight / 4;
        Camera.leadX = viewWidth / 6;
        TileRenderer.visibleCols = viewWidth / GameMap.TILE_SIZE + 2;
        TileRenderer.visibleRows = viewHeight / GameMap.TILE_SIZE + 2;
        if (viewWidth % 24 != 0) {
            ++TileRenderer.visibleCols;
        }

        Camera.maxX = (GameMap.width - 1) * GameMap.TILE_SIZE - viewWidth;
        Camera.maxY = (GameMap.height - 1) * GameMap.TILE_SIZE - viewHeight;
        Camera.x = Camera.targetX = Player.x - viewCenterX + Camera.leadX * Player.facing;
        Camera.y = Camera.targetY = Player.y - Camera.offsetY;
        if (Camera.x < 0) {
            Camera.x = 0;
        }

        if (Camera.x > Camera.maxX) {
            Camera.x = Camera.maxX;
        }

        if (Camera.y < 0) {
            Camera.y = 0;
        }

        if (Camera.y > Camera.maxY) {
            Camera.y = Camera.maxY;
        }

        if ((TileRenderer.firstVisibleCol = Camera.x / GameMap.TILE_SIZE - 1) < 0) {
            TileRenderer.firstVisibleCol = 0;
        }

        TileRenderer.firstVisibleRow = Camera.y / GameMap.TILE_SIZE;
        TileRenderer.lastVisibleCol = TileRenderer.firstVisibleCol + TileRenderer.visibleCols;
        TileRenderer.lastVisibleRow = TileRenderer.firstVisibleRow + TileRenderer.visibleRows;
        if (TileRenderer.firstVisibleRow < 0) {
            TileRenderer.firstVisibleRow = 0;
        }

        if (TileRenderer.lastVisibleRow > GameMap.height - 1) {
            TileRenderer.lastVisibleRow = GameMap.height - 1;
        }

    }
}
