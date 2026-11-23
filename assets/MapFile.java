package assets;

import java.io.InputStream;

/**
 * File map (assets/map/map{id}.bin, gốc: m/{id}).
 *
 * Định dạng: width * height byte, mỗi byte là mã tile của một ô, xếp theo hàng
 * từ trên xuống, trái sang phải. Mã tile = chỉ số ảnh trong tileset + 1, 0 = ô trống.
 * File không lưu kích thước; width/height của từng map nằm trong code (MapConfig).
 */
public final class MapFile {
    private MapFile() {
    }

    public static byte[] load(int mapId, int tileCount) throws Exception {
        InputStream stream = MapFile.class.getResourceAsStream(Enum.PATH_MAP + "map" + mapId + Enum.EXTENSION_MAP);
        try {
            byte[] tiles = new byte[tileCount];
            for (int i = 0; i < tileCount; i++) {
                tiles[i] = (byte) stream.read();
            }

            return tiles;
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
}
