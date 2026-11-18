package assets;

import javax.microedition.lcdui.Image;

/**
 * Hình ảnh môi trường của map (assets/tile/).
 *
 *  - tileset.png       (gốc: t.png)   : 120 tile 24x24 xếp dọc, tile mã N ở vị trí N - 1
 *  - waterfall.png     (gốc: wtf.png) : thác nước, 4 frame 24x24 xếp dọc
 *  - water_surface.png (gốc: wts.png) : sóng mặt nước, 2 frame 24x24 xếp dọc
 *  - bush.png          (gốc: tb.png)  : bụi cây tiền cảnh
 *  - house{n}.png      (gốc: h{n}.png): 3 kiểu nhà
 */
public final class Tile {
    public static final int TILE_COUNT = 120;
    public static final int WATERFALL_FRAME_COUNT = 4;
    public static final int WATER_SURFACE_FRAME_COUNT = 2;
    public static final int HOUSE_COUNT = 3;

    private Tile() {
    }

    public static Image ensureTileset(Image tileset) throws Exception {
        return tileset != null ? tileset : load("tileset");
    }

    public static Image ensureWaterfall(Image waterfall) throws Exception {
        return waterfall != null ? waterfall : load("waterfall");
    }

    public static Image ensureWaterSurface(Image waterSurface) throws Exception {
        return waterSurface != null ? waterSurface : load("water_surface");
    }

    public static Image ensureBush(Image bush) throws Exception {
        return bush != null ? bush : load("bush");
    }

    public static Image[] ensureHouses(Image[] houses) throws Exception {
        if (houses != null) {
            return houses;
        }

        Image[] result = new Image[HOUSE_COUNT];
        for (int i = 0; i < HOUSE_COUNT; i++) {
            result[i] = load("house" + i);
        }

        return result;
    }

    private static Image load(String name) throws Exception {
        return Image.createImage(Enum.PATH_TILE + name + Enum.EXTENSION_PNG);
    }
}
