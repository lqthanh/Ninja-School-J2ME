package core.map;

import assets.MapFile;

/**
 * Map đang chơi: lưới tile và cờ thuộc tính của từng ô.
 *
 * Tên field gốc trong b.java (nay là GameCanvas.java):
 *  width=iM, height=iN, pixelWidth=iO, pixelHeight=iP,
 *  tiles=iQ, flags=iR, usedTiles=iS, TILE_SIZE=je
 *
 * Kích thước map và vị trí xuất phát của từng màn được đặt trong {@link MapConfig}.
 */
public final class GameMap {
    /** Kích thước một ô (pixel). */
    public static final int TILE_SIZE = 24;

    /** Số mã tile tối đa được đánh dấu trong {@link #usedTiles}. */
    private static final int MAX_TILE_ID = 200;

    /** Số ô theo chiều ngang / dọc. */
    public static int width;
    public static int height;

    /** Kích thước map theo pixel. */
    public static int pixelWidth;
    public static int pixelHeight;

    /** Mã tile của từng ô (0 = trống), theo hàng. */
    public static byte[] tiles;
    /** Cờ {@link TileFlags} của từng ô. */
    public static int[] flags;
    /** Mã tile có xuất hiện trong map (index = mã - 1). */
    public static boolean[] usedTiles;

    private GameMap() {
    }

    public static void resetUsedTiles() {
        usedTiles = new boolean[MAX_TILE_ID];
    }

    /** Nạp file map {@code mapId} (width/height phải được đặt trước) và tính cờ. */
    public static void loadTiles(int mapId) throws Exception {
        tiles = MapFile.load(mapId, width * height);
        flags = TileFlags.compute(tiles, width, usedTiles);
    }

    // =====================================================================================
    // #region Flags

    /** Cờ của ô (cột, hàng). */
    public static int flagsAt(int col, int row) {
        return flags[row * width + col];
    }

    /** Cờ của ô chứa điểm (x, y) theo pixel. */
    public static int flagsAtPixel(int x, int y) {
        return flags[indexAtPixel(x, y)];
    }

    /** Ô chứa điểm (x, y) theo pixel có đủ các cờ {@code flag}. */
    public static boolean hasFlag(int x, int y, int flag) {
        return (flags[indexAtPixel(x, y)] & flag) == flag;
    }

    /** Đánh dấu tấm ván cầu tại điểm (x, y) đang bị giẫm. */
    public static void pressBridge(int x, int y) {
        flags[indexAtPixel(x, y)] |= TileFlags.BRIDGE_PRESSED;
    }

    /** Bỏ đánh dấu tấm ván cầu tại điểm (x, y). */
    public static void releaseBridge(int x, int y) {
        flags[indexAtPixel(x, y)] &= ~TileFlags.BRIDGE_PRESSED;
    }

    /** Xoá mọi cờ của ô (cột, hàng): dùng để mở cổng/bỏ vật cản. */
    public static void clearFlags(int col, int row) {
        flags[row * width + col] = 0;
    }

    private static int indexAtPixel(int x, int y) {
        return y / TILE_SIZE * width + x / TILE_SIZE;
    }

    // #endregion
    // =====================================================================================
}
