package core.map;

/**
 * Cờ thuộc tính của từng ô trên map, tính từ mã tile lúc nạp map.
 * Mã tile = chỉ số ảnh trong tileset + 1 (0 = ô trống).
 */
public final class TileFlags {
    /** Đứng được / va chạm từ trên xuống (mặt đất, mái nhà, sàn gỗ...). */
    public static final int SOLID = 2;
    /** Mặt trái của ô là tường: chặn khi đi sang phải vào ô. */
    public static final int WALL_LEFT_SIDE = 4;
    /** Mặt phải của ô là tường: chặn khi đi sang trái vào ô. */
    public static final int WALL_RIGHT_SIDE = 8;
    /** Cây cảnh (tre, thân cây, bụi) vẽ trượt chậm hơn camera (parallax). */
    public static final int PARALLAX = 16;
    /** Thác nước (vẽ hoạt ảnh thác). */
    public static final int WATERFALL = 32;
    /** Mặt nước: đi chậm, có thể chạy trên nước / lặn. */
    public static final int WATER = 64;
    /** Gán cho tile 18 nhưng không có code nào đọc. */
    public static final int WATER_EDGE = 128;
    /** Bụi cây tiền cảnh (vẽ ảnh bụi đè lên nhân vật). */
    public static final int FOREGROUND_BUSH = 256;
    /** Tre tiền cảnh (vẽ đè lên nhân vật, lệch xuống 2px). */
    public static final int FOREGROUND = 512;
    /** Cờ lúc chạy: tấm ván cầu đang bị giẫm (vẽ lún xuống 1px). */
    public static final int BRIDGE_PRESSED = 1024;
    /** Tấm ván cầu. */
    public static final int BRIDGE = 2048;
    /** Nước sâu: chìm/chết đuối, vẽ đè lên nhân vật. */
    public static final int DEEP_WATER = 4096;
    /** Đất: có thể độn thổ. */
    public static final int DIGGABLE = 8192;

    private TileFlags() {
    }

    /**
     * Tính cờ cho toàn bộ map.
     *
     * @param tiles     mã tile của từng ô, theo hàng
     * @param width     số ô mỗi hàng
     * @param usedTiles đánh dấu mã tile có xuất hiện (index = mã - 1); bản gốc ghi nhưng không đọc
     */
    public static int[] compute(byte[] tiles, int width, boolean[] usedTiles) {
        int[] flags = new int[tiles.length];

        for (int i = 0; i < tiles.length; i++) {
            int tile = tiles[i];
            if (tile != 0) {
                usedTiles[tile - 1] = true;
            }

            int f = 0;
            switch (tile) {
                case 1: case 2: case 3: case 4: case 5: case 6: case 7:
                case 36: case 37: case 54: case 73: case 74:
                case 91: case 92: case 93: case 94: case 97: case 98: case 120:
                    f |= SOLID;
                    break;
                default:
                    break;
            }

            switch (tile) {
                case 2: case 3: case 4: case 5: case 6:
                case 20: case 21: case 22: case 23:
                case 36: case 37: case 38: case 39:
                    f |= DIGGABLE;
                    break;
                default:
                    break;
            }

            switch (tile) {
                case 8: case 9: case 10: case 12: case 13: case 14: case 30:
                    f |= PARALLAX;
                    break;
                case 17:
                    f |= WATERFALL;
                    break;
                case 18:
                    f |= WATER_EDGE;
                    break;
                case 19:
                    f |= WATER;
                    // Mặt nước ngay dưới đất thì vẫn tính là đất
                    if ((flags[i - width] & DIGGABLE) == DIGGABLE) {
                        f |= DIGGABLE;
                    }
                    break;
                case 35:
                    f |= DEEP_WATER;
                    break;
                case 32:
                    f |= FOREGROUND_BUSH;
                    break;
                case 33: case 34:
                    f |= FOREGROUND;
                    break;
                default:
                    break;
            }

            if (tile == 37 || tile == 38) {
                f |= WALL_LEFT_SIDE;
            }

            if (tile == 36 || tile == 39) {
                f |= WALL_RIGHT_SIDE;
            }

            if (tile == 7) {
                f |= BRIDGE;
            }

            flags[i] = f;
        }

        return flags;
    }
}
