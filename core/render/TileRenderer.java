package core.render;

import assets.Tile;
import core.app.App;
import core.app.GameState;
import core.app.Settings;
import core.map.GameMap;
import core.map.MapConfig;
import core.map.TileFlags;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/** Vẽ nền tile của map (thác nước, mặt nước, bụi cây phía trước) theo vùng đang nhìn thấy. */
public final class TileRenderer {
    /** Dải cột / hàng tile nằm trong khung nhìn (cập nhật mỗi frame). */
    public static int firstVisibleCol;
    public static int firstVisibleRow;

    /** Số cột / hàng tile vừa một khung nhìn (cộng dư 2). */
    public static int visibleCols;
    public static int visibleRows;
    public static int lastVisibleCol;
    public static int lastVisibleRow;

    /** Ảnh tileset của map, thác nước, mặt nước và bụi cây phía trước. */
    public static Image tilesetImage;
    public static Image waterfallImage;
    public static Image waterSurfaceImage;
    private static Image bushImage;

    /** Biến tạm khi tính vị trí parallax của nhà / lớp phía trước. */
    public static int parallaxScreenX;
    public static int parallaxFromCenter;
    public static int parallaxDrawX;
    public static int parallaxScaled;

    private TileRenderer() {
    }

    /** Vẽ bầu trời và mây. */
    public static void drawSky(Graphics g) {
        Sky.draw(g, Camera.x, Viewport.viewWidth, Viewport.viewHeight, Settings.lowGraphics);
    }

    /** Nạp ảnh tileset, thác, mặt nước, bụi cây nếu chưa có; lỗi thì thoát game. */
    public static void ensureMapImages() {
        try {
            tilesetImage = Tile.ensureTileset(tilesetImage);
            waterfallImage = Tile.ensureWaterfall(waterfallImage);
            waterSurfaceImage = Tile.ensureWaterSurface(waterSurfaceImage);
            if (!Settings.lowGraphics) {
                bushImage = Tile.ensureBush(bushImage);
            }

            System.gc();
        } catch (Exception e) {
            App.shutdown();
        }
    }

    /** Nạp dữ liệu tile và ảnh của map {@code mapId}. */
    public static void loadMap(int mapId) {
        try {
            MapConfig.load(mapId);
        } catch (Exception ex) {
            App.shutdown();
        }

        ensureMapImages();
    }

    /** Vẽ tile ở chế độ đồ họa thấp (không có lớp phía trước). */
    public static void drawTilesLowGraphics(Graphics g) {
        for(int col = firstVisibleCol; col < lastVisibleCol; ++col) {
            for(int row = firstVisibleRow; row < lastVisibleRow; ++row) {
                int tile;
                if ((tile = GameMap.tiles[row * GameMap.width + col] - 1) != -1) {
                    g.drawRegion(tilesetImage, 0, tile * GameMap.TILE_SIZE, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                }

                if ((GameMap.flagsAt(col, row) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                    g.drawRegion(waterfallImage, 0, 24 * (GameState.tick % 4), 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                } else if ((GameMap.flagsAt(col, row) & TileFlags.WATER) == TileFlags.WATER) {
                    if ((GameMap.flagsAt(col, row - 1) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                        g.drawRegion(waterfallImage, 0, 24 * (GameState.tick % 4), 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(col, row - 1) & TileFlags.DIGGABLE) == TileFlags.DIGGABLE) {
                        g.drawRegion(tilesetImage, 0, 504, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    }

                    g.drawRegion(waterSurfaceImage, 0, (GameState.tick % 8 >> 2) * 24, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                }

                if ((GameMap.flagsAt(col, row) & TileFlags.DEEP_WATER) == TileFlags.DEEP_WATER) {
                    if ((GameMap.flagsAt(col, row - 1) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                        g.drawRegion(waterfallImage, 0, 24 * (GameState.tick % 4), 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(col, row - 1) & TileFlags.DIGGABLE) == TileFlags.DIGGABLE) {
                        g.drawRegion(tilesetImage, 0, 504, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    }

                    g.drawRegion(tilesetImage, 0, (GameMap.tiles[row * GameMap.width + col] - 1) * GameMap.TILE_SIZE, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                }
            }
        }

    }

    /** Vẽ tile nền (thác nước có hoạt ảnh). */
    public static void drawTiles(Graphics g) {
        for(int col = firstVisibleCol; col < lastVisibleCol; ++col) {
            for(int row = firstVisibleRow; row < lastVisibleRow; ++row) {
                int tile = GameMap.tiles[row * GameMap.width + col] - 1;
                if ((GameMap.flagsAt(col, row) & TileFlags.PARALLAX) == TileFlags.PARALLAX) {
                    parallaxFromCenter = (parallaxScreenX = col * GameMap.TILE_SIZE - Camera.x) - Viewport.viewCenterX;
                    parallaxDrawX = (parallaxScaled = (GameMap.TILE_SIZE - 2) * parallaxFromCenter / GameMap.TILE_SIZE) + Viewport.viewCenterX;
                    g.drawRegion(tilesetImage, 0, tile * GameMap.TILE_SIZE, 24, 24, 0, parallaxDrawX + Camera.x, row * GameMap.TILE_SIZE, 0);
                } else if ((GameMap.flagsAt(col, row) & TileFlags.FOREGROUND_BUSH) != TileFlags.FOREGROUND_BUSH && (GameMap.flagsAt(col, row) & TileFlags.FOREGROUND) != TileFlags.FOREGROUND) {
                    if ((GameMap.flagsAt(col, row) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                        g.drawRegion(waterfallImage, 0, 24 * (GameState.tick % 4), 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(col, row) & TileFlags.WATER) != TileFlags.WATER && (GameMap.flagsAt(col, row) & TileFlags.DEEP_WATER) != TileFlags.DEEP_WATER) {
                        if ((GameMap.flagsAt(col, row) & TileFlags.BRIDGE_PRESSED) == TileFlags.BRIDGE_PRESSED) {
                            g.drawRegion(tilesetImage, 0, tile * GameMap.TILE_SIZE, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE + 1, 0);
                        } else if (tile != -1) {
                            g.drawRegion(tilesetImage, 0, tile * GameMap.TILE_SIZE, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                        }
                    } else if ((GameMap.flagsAt(col, row - 1) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                        g.drawRegion(waterfallImage, 0, 24 * (GameState.tick % 4), 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(col, row - 1) & TileFlags.DIGGABLE) == TileFlags.DIGGABLE) {
                        g.drawRegion(tilesetImage, 0, 504, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    }
                }
            }
        }

    }

    /** Vẽ lớp phía trước nhân vật: mặt nước, bụi cây (parallax). */
    public static void drawForegroundTiles(Graphics g) {
        if (!Settings.lowGraphics) {
            for(int col = firstVisibleCol; col < lastVisibleCol; ++col) {
                for(int row = firstVisibleRow; row < lastVisibleRow; ++row) {
                    if ((GameMap.flagsAt(col, row) & TileFlags.WATER) == TileFlags.WATER) {
                        g.drawRegion(waterSurfaceImage, 0, (GameState.tick % 8 >> 2) * 24, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(col, row) & TileFlags.FOREGROUND_BUSH) == TileFlags.FOREGROUND_BUSH) {
                        parallaxFromCenter = (parallaxScreenX = col * GameMap.TILE_SIZE - Camera.x) - Viewport.viewCenterX;
                        parallaxDrawX = (parallaxScaled = (GameMap.TILE_SIZE - 1) * parallaxFromCenter / GameMap.TILE_SIZE) + Viewport.viewCenterX;
                        g.drawImage(bushImage, parallaxDrawX + Camera.x - 2 + (col + row) % 5, row * GameMap.TILE_SIZE - 3 + row % 5, 0);
                    }

                    if ((GameMap.flagsAt(col, row) & TileFlags.FOREGROUND) == TileFlags.FOREGROUND) {
                        g.drawRegion(tilesetImage, 0, (GameMap.tiles[row * GameMap.width + col] - 1) * GameMap.TILE_SIZE, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE + 2, 0);
                    }

                    if ((GameMap.flagsAt(col, row) & TileFlags.DEEP_WATER) == TileFlags.DEEP_WATER) {
                        g.drawRegion(tilesetImage, 0, (GameMap.tiles[row * GameMap.width + col] - 1) * GameMap.TILE_SIZE, 24, 24, 0, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE, 0);
                    }
                }
            }

        }
    }

    /** Tính dải cột/hàng tile đang nằm trong khung nhìn từ vị trí camera. */
    public static void updateVisibleRange() {
        if ((firstVisibleCol = Camera.x / GameMap.TILE_SIZE - 1) < 0) {
            firstVisibleCol = 0;
        }

        firstVisibleRow = Camera.y / GameMap.TILE_SIZE;
        lastVisibleCol = firstVisibleCol + visibleCols;
        lastVisibleRow = firstVisibleRow + visibleRows;
        if (firstVisibleRow < 0) {
            firstVisibleRow = 0;
        }

        if (lastVisibleRow > GameMap.height - 1) {
            lastVisibleRow = GameMap.height - 1;
        }
    }
}
