package core.map;

/**
 * Cấu hình từng map: kích thước (ô tile) và cửa ra ({@link MapExits}).
 *
 * Map id: 0..18 là các khu vực trên bản đồ thế giới, 94..99 là các map đặc biệt,
 * 101 là map hướng dẫn (dùng chung dữ liệu với map 99).
 */
public final class MapConfig {
    /** Map hướng dẫn ("Chỉ dẫn" ở màn hình chính). */
    public static final int TUTORIAL_MAP = 101;
    /** Map có dữ liệu dùng cho map hướng dẫn. */
    public static final int TUTORIAL_MAP_DATA = 99;

    private MapConfig() {
    }

    /** Gán kích thước, cửa ra của map {@code mapId} rồi nạp tile và cờ tile. */
    public static void load(int mapId) throws Exception {
        GameMap.resetUsedTiles();
        if (mapId == TUTORIAL_MAP) {
            mapId = TUTORIAL_MAP_DATA;
        }

        if (mapId == 0) {
            GameMap.width = 80;
            GameMap.height = 19;
            MapExits.leftMap = 1;
            MapExits.leftSpawnX = 59 * GameMap.TILE_SIZE;
            MapExits.leftSpawnY = 9 * GameMap.TILE_SIZE;
            MapExits.rightMap = 2;
            MapExits.rightSpawnX = 3;
            MapExits.rightSpawnY = 12 * GameMap.TILE_SIZE;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 1) {
            GameMap.width = 60;
            GameMap.height = 14;
            MapExits.leftMap = 5;
            MapExits.leftSpawnX = 19 * GameMap.TILE_SIZE;
            MapExits.leftSpawnY = 5 * GameMap.TILE_SIZE;
            MapExits.rightMap = 0;
            MapExits.rightSpawnX = 3;
            MapExits.rightSpawnY = 16 * GameMap.TILE_SIZE;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 2) {
            GameMap.width = 60;
            GameMap.height = 16;
            MapExits.leftMap = 0;
            MapExits.leftSpawnX = 79 * GameMap.TILE_SIZE;
            MapExits.leftSpawnY = 15 * GameMap.TILE_SIZE;
            MapExits.rightMap = 3;
            MapExits.rightSpawnX = 3;
            MapExits.rightSpawnY = 9 * GameMap.TILE_SIZE;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 3) {
            GameMap.width = 120;
            GameMap.height = 12;
            MapExits.leftMap = 2;
            MapExits.leftSpawnX = 59 * GameMap.TILE_SIZE;
            MapExits.leftSpawnY = 11 * GameMap.TILE_SIZE;
            MapExits.rightMap = 4;
            MapExits.rightSpawnX = 3;
            MapExits.rightSpawnY = 144;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 4) {
            GameMap.width = 80;
            GameMap.height = 15;
            MapExits.leftMap = 3;
            MapExits.leftSpawnX = 119 * GameMap.TILE_SIZE;
            MapExits.leftSpawnY = 9 * GameMap.TILE_SIZE;
            MapExits.rightMap = MapExits.NONE;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = 6;
            MapExits.bottomSpawnX = 26 * GameMap.TILE_SIZE;
            MapExits.bottomSpawnY = 10;
        } else if (mapId == 5) {
            GameMap.width = 20;
            GameMap.height = 80;
            MapExits.leftMap = MapExits.NONE;
            MapExits.rightMap = 1;
            MapExits.rightSpawnX = 3;
            MapExits.rightSpawnY = 9 * GameMap.TILE_SIZE;
            MapExits.rightLowerMinY = 40 * GameMap.TILE_SIZE;
            MapExits.rightLowerMap = 6;
            MapExits.rightLowerSpawnX = 3;
            MapExits.rightLowerSpawnY = 5 * GameMap.TILE_SIZE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 6) {
            GameMap.width = 250;
            GameMap.height = 12;
            MapExits.leftMap = 5;
            MapExits.leftSpawnX = 19 * GameMap.TILE_SIZE;
            MapExits.leftSpawnY = 78 * GameMap.TILE_SIZE;
            MapExits.rightMap = 7;
            MapExits.rightSpawnX = 1 * GameMap.TILE_SIZE;
            MapExits.rightSpawnY = 7 * GameMap.TILE_SIZE;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 7) {
            GameMap.width = 80;
            GameMap.height = 14;
            MapExits.leftMap = 6;
            MapExits.leftSpawnX = 249 * GameMap.TILE_SIZE;
            MapExits.leftSpawnY = 10 * GameMap.TILE_SIZE;
            MapExits.rightMap = 97;
            MapExits.rightSpawnX = 1 * GameMap.TILE_SIZE;
            MapExits.rightSpawnY = 3 * GameMap.TILE_SIZE;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 97) {
            GameMap.width = 25;
            GameMap.height = 13;
            MapExits.leftMap = 7;
            MapExits.leftSpawnX = 79 * GameMap.TILE_SIZE;
            MapExits.leftSpawnY = 11 * GameMap.TILE_SIZE;
            MapExits.rightMap = 8;
            MapExits.rightSpawnX = 1 * GameMap.TILE_SIZE;
            MapExits.rightSpawnY = 12 * GameMap.TILE_SIZE;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 8) {
            GameMap.width = 80;
            GameMap.height = 14;
            MapExits.leftMap = 97;
            MapExits.leftSpawnX = 23 * GameMap.TILE_SIZE;
            MapExits.leftSpawnY = 7 * GameMap.TILE_SIZE;
            MapExits.rightMap = 9;
            MapExits.rightSpawnX = 1 * GameMap.TILE_SIZE;
            MapExits.rightSpawnY = 11 * GameMap.TILE_SIZE;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 9) {
            GameMap.width = 80;
            GameMap.height = 14;
            MapExits.leftMap = 8;
            MapExits.leftSpawnX = 79 * GameMap.TILE_SIZE;
            MapExits.leftSpawnY = 9 * GameMap.TILE_SIZE;
            MapExits.rightMap = MapExits.NONE;
            MapExits.rightSpawnX = 1 * GameMap.TILE_SIZE;
            MapExits.rightSpawnY = 11 * GameMap.TILE_SIZE;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = 10;
            MapExits.bottomSpawnX = 2 * GameMap.TILE_SIZE;
            MapExits.bottomSpawnY = 0 * GameMap.TILE_SIZE;
        } else if (mapId == 10) {
            GameMap.width = 40;
            GameMap.height = 40;
            MapExits.leftMap = MapExits.NONE;
            MapExits.rightMap = 11;
            MapExits.rightSpawnX = 24;
            MapExits.rightSpawnY = 264;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 11) {
            GameMap.width = 90;
            GameMap.height = 14;
            MapExits.leftMap = 10;
            MapExits.leftSpawnX = 936;
            MapExits.leftSpawnY = 720;
            MapExits.rightMap = 96;
            MapExits.rightSpawnX = 24;
            MapExits.rightSpawnY = 192;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 96) {
            GameMap.width = 25;
            GameMap.height = 14;
            MapExits.leftMap = 11;
            MapExits.leftSpawnX = 2112;
            MapExits.leftSpawnY = 288;
            MapExits.rightMap = 12;
            MapExits.rightSpawnX = 24;
            MapExits.rightSpawnY = 192;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 12) {
            GameMap.width = 40;
            GameMap.height = 25;
            MapExits.leftMap = 96;
            MapExits.leftSpawnX = 552;
            MapExits.leftSpawnY = 264;
            MapExits.rightMap = 13;
            MapExits.rightSpawnX = 24;
            MapExits.rightSpawnY = 216;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 13) {
            GameMap.width = 80;
            GameMap.height = 14;
            MapExits.leftMap = 12;
            MapExits.leftSpawnX = 936;
            MapExits.leftSpawnY = 456;
            MapExits.rightMap = 14;
            MapExits.rightSpawnX = 24;
            MapExits.rightSpawnY = 168;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 14) {
            GameMap.width = 60;
            GameMap.height = 16;
            MapExits.leftMap = 13;
            MapExits.leftSpawnX = 1896;
            MapExits.leftSpawnY = 264;
            MapExits.rightMap = 15;
            MapExits.rightSpawnX = 24;
            MapExits.rightSpawnY = 120;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 15) {
            GameMap.width = 40;
            GameMap.height = 30;
            MapExits.leftMap = 14;
            MapExits.leftSpawnX = 1416;
            MapExits.leftSpawnY = 144;
            MapExits.rightMap = 16;
            MapExits.rightSpawnX = 24;
            MapExits.rightSpawnY = 264;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 16) {
            GameMap.width = 70;
            GameMap.height = 15;
            MapExits.leftMap = 15;
            MapExits.leftSpawnX = 936;
            MapExits.leftSpawnY = 168;
            MapExits.rightMap = 17;
            MapExits.rightSpawnX = 24;
            MapExits.rightSpawnY = 432;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 17) {
            GameMap.width = 50;
            GameMap.height = 20;
            MapExits.leftMap = 16;
            MapExits.leftSpawnX = 1656;
            MapExits.leftSpawnY = 144;
            MapExits.rightMap = 18;
            MapExits.rightSpawnX = 24;
            MapExits.rightSpawnY = 288;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 18) {
            GameMap.width = 70;
            GameMap.height = 15;
            MapExits.leftMap = 17;
            MapExits.leftSpawnX = 1176;
            MapExits.leftSpawnY = 384;
            MapExits.rightMap = 95;
            MapExits.rightSpawnX = 24;
            MapExits.rightSpawnY = 192;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 95) {
            GameMap.width = 25;
            GameMap.height = 14;
            MapExits.leftMap = 18;
            MapExits.leftSpawnX = 1632;
            MapExits.leftSpawnY = 312;
            MapExits.rightMap = MapExits.NONE;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 94) {
            GameMap.width = 24;
            GameMap.height = 14;
            MapExits.leftMap = MapExits.NONE;
            MapExits.rightMap = MapExits.NONE;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        } else if (mapId == 99) {
            GameMap.width = 70;
            GameMap.height = 15;
            MapExits.leftMap = MapExits.NONE;
            MapExits.rightMap = 4;
            MapExits.rightSpawnX = 2 * GameMap.TILE_SIZE;
            MapExits.rightSpawnY = 5 * GameMap.TILE_SIZE;
            MapExits.rightLowerMap = MapExits.NONE;
            MapExits.bottomMap = MapExits.NONE;
        }

        GameMap.pixelWidth = GameMap.width * GameMap.TILE_SIZE;
        GameMap.pixelHeight = GameMap.height * GameMap.TILE_SIZE;

        GameMap.loadTiles(mapId);
    }
}
