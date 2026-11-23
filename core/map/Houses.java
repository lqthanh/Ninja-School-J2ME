package core.map;

import assets.Tile;
import javax.microedition.lcdui.Image;

/** Nhà ở làng (vẽ kiểu parallax trong GameCanvas.paint()). */
public final class Houses {
    public static int count;
    /** Loại nhà (chỉ số trong {@link #images}). */
    public static int[] types;
    /** Tâm đáy nhà (pixel). */
    public static int[] x;
    public static int[] y;
    public static int[] halfWidth;
    public static int[] height;
    public static Image[] images;

    private Houses() {
    }

    /** Đặt nhà cho map. */
    public static void setup(int map) {
        count = 0;
        if (map == 2) {
            try {
                images = Tile.ensureHouses(images);
            } catch (Exception e) {
            }

            count = 5;
            alloc();
            place(0, 0, 29, 13);
            place(1, 1, 34, 13);
            place(2, 2, 39, 13);
            place(3, 1, 46, 11);
            place(4, 2, 51, 11);
        }

        if (map == 16) {
            try {
                images = Tile.ensureHouses(images);
            } catch (Exception e) {
            }

            count = 5;
            alloc();
            place(0, 0, 5, 11);
            place(1, 1, 14, 12);
            place(2, 2, 33, 10);
            place(3, 1, 53, 10);
            place(4, 0, 59, 11);
        }

    }

    private static void place(int i, int type, int tileX, int tileY) {
        types[i] = type;
        x[i] = tileX * GameMap.TILE_SIZE + 12;
        y[i] = tileY * GameMap.TILE_SIZE;
        halfWidth[i] = images[type].getWidth() >> 1;
        height[i] = images[type].getHeight();
    }

    private static void alloc() {
        types = new int[count];
        x = new int[count];
        y = new int[count];
        halfWidth = new int[count];
        height = new int[count];
    }
}
