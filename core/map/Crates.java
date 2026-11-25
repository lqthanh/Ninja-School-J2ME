package core.map;

import assets.Item;
import core.app.GameState;
import core.fx.WaterSplash;
import core.player.Player;
import javax.microedition.lcdui.Image;

/**
 * Thùng gỗ đẩy được (map 1, 5, 11, 97). Nhân vật đẩy hoặc chém thùng để trượt đi,
 * đứng lên thùng để nhảy cao hơn ({@link core.player.Player#crate}). Thùng rơi khỏi mép
 * thì rơi xuống; rơi xuống nước thì nổi bập bềnh và trôi.
 * Cập nhật: {@link #update()}.
 */
public final class Crates {
    /** Đứng yên. */
    public static final int STATE_RESTING = 1;
    /** Đang trượt theo {@link #vx} (giảm dần về 0). */
    public static final int STATE_SLIDING = 2;
    /** Đang rơi. */
    public static final int STATE_FALLING = 4;
    /** Nổi trên mặt nước, nhấp nhô và trôi theo {@link #driftVx}. */
    public static final int STATE_FLOATING = 11;

    public static int count;
    /** Tâm đáy thùng (pixel). */
    public static int[] x;
    public static int[] y;
    public static int[] state;
    public static int[] vx;
    /** Vận tốc rơi; khi nổi thì là độ lệch nhấp nhô (-1..1). */
    public static int[] vy;
    /** Hướng nhấp nhô khi nổi. */
    public static int[] bobDirection;
    /** Vận tốc trôi ngang khi nổi. */
    public static int[] driftVx;
    public static Image sprite;

    private Crates() {
    }

    /** Đặt thùng cho map. */
    public static final void setup(int mapId) {
        count = 0;
        if (mapId == 1) {
            count = 2;
            alloc();
            place(0, 21, 9);
            place(1, 13, 8);
        }

        if (mapId == 11) {
            count = 2;
            alloc();
            place(0, 33, 7);
            place(1, 44, 10);
        }

        if (mapId == 5) {
            count = 2;
            alloc();
            place(0, 5, 46);
            place(1, 15, 46);
        }

        if (mapId == 97) {
            count = 4;
            alloc();
            place(0, 3, 6);
            place(1, 7, 4);
            place(2, 12, 3);
            place(3, 22, 6);
        }

    }

    private static void alloc() {
        try {
            sprite = Item.ensureCrate(sprite);
        } catch (Exception e) {
        }

        x = new int[count];
        y = new int[count];
        state = new int[count];
        vx = new int[count];
        vy = new int[count];
        bobDirection = new int[count];
        driftVx = new int[count];
    }

    private static void place(int i, int tileX, int tileY) {
        x[i] = tileX * GameMap.TILE_SIZE + 12;
        y[i] = tileY * GameMap.TILE_SIZE + 24;
        state[i] = STATE_RESTING;
    }

    /** Thùng gỗ: trượt, rơi, nổi trên nước. */
    public static void update() {
        int i;
        for(i = 0; i < count; ++i) {
            switch (state[i]) {
                case STATE_SLIDING:
                    x[i] += vx[i];
                    if (vx[i] > 0) {
                        vx[i]--;
                    } else if (vx[i] < 0) {
                        vx[i]++;
                    } else {
                        state[i] = STATE_RESTING;
                    }

                    if (!GameMap.hasFlag(x[i] - 12, y[i] + 1, TileFlags.SOLID) && !GameMap.hasFlag(x[i] + 12, y[i] + 1, TileFlags.SOLID)) {
                        state[i] = STATE_FALLING;
                        vy[i] = 2;
                    }

                    if (GameMap.hasFlag(x[i] + 12, y[i] - 2, TileFlags.WALL_LEFT_SIDE) || GameMap.hasFlag(x[i] - 12, y[i] - 2, TileFlags.WALL_RIGHT_SIDE)) {
                        x[i] = GameMap.alignToTileX(x[i]) + 12;
                    }
                    break;
                case STATE_FALLING:
                    y[i] += vy[i];
                    if (vy[i] < 20) {
                        vy[i]++;
                    }

                    if (!GameMap.hasFlag(x[i] + 12, y[i], TileFlags.SOLID) && !GameMap.hasFlag(x[i] - 12, y[i], TileFlags.SOLID)) {
                        if (GameMap.hasFlag(x[i], y[i] - GameMap.TILE_SIZE, TileFlags.WATER)) {
                            y[i] = GameMap.alignToTileY(y[i]) + 1;
                            WaterSplash.spawn(x[i], y[i] - 13);
                            WaterSplash.spawn(x[i] + 1, y[i] - 10);
                            vy[i] = 1;
                            bobDirection[i] = -1;
                            driftVx[i] = 1;
                            state[i] = STATE_FLOATING;
                        }
                        break;
                    }

                    y[i] = GameMap.alignToTileY(y[i]);
                    vy[i] = 0;
                    state[i] = STATE_RESTING;
                    break;
                case STATE_FLOATING:
                    if (GameState.tick % 2 == 0) {
                        y[i] += bobDirection[i];
                        vy[i] += bobDirection[i];
                        if (vy[i] >= 1) {
                            bobDirection[i] = -1;
                        }

                        if (vy[i] <= -1) {
                            bobDirection[i] = 1;
                        }

                        x[i] += driftVx[i];
                        if (i == Player.crate) {
                            Player.y = y[Player.crate] - 24;
                            Player.x += driftVx[i];
                        }

                        if (GameMap.hasFlag(x[i] + 12, y[i], TileFlags.WALL_LEFT_SIDE) || GameMap.hasFlag(x[i] - 12, y[i], TileFlags.WALL_RIGHT_SIDE)) {
                            driftVx[i] = -driftVx[i];
                        }
                    }
            }
        }
    }
}
