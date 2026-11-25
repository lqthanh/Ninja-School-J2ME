package core.item;

import assets.Item;
import core.fx.FloatingText;
import core.map.GameMap;
import core.map.TileFlags;
import core.render.Viewport;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/** Tiền rơi ra khi hạ quái: nảy trên nền, nhặt khi chạm vào nhân vật (tối đa 5). */
public final class MoneyDrops {
    public static final int CAPACITY = 5;
    public static final int FREE = 0;
    /** Tự biến mất sau số frame này. */
    public static final int LIFETIME = 200;
    /** Gán khi vừa được nhặt, để frame sau vượt LIFETIME và ô được giải phóng. */
    public static final int PICKED = 301;

    /** Ảnh theo loại (Item.MONEY_*). */
    public static Image[] sprites;
    private static int count;
    private static int[] x;
    private static int[] y;
    private static int[] vx;
    private static int[] vy;
    /** Tuổi theo frame (bắt đầu từ 1), {@link #FREE} nếu ô trống. */
    private static int[] age;
    /** Khung hình xoay. */
    private static int[] frame;
    private static int[] value;
    /** Loại (Item.MONEY_*) theo giá trị: vàng < 160, ngọc xanh < 600, ngọc đỏ. */
    private static int[] type;

    private MoneyDrops() {
    }

    public static void init() {
        count = CAPACITY;

        try {
            sprites = Item.ensureMoney(sprites);
        } catch (Exception ex) {
        }

        x = new int[count];
        y = new int[count];
        age = new int[count];
        vx = new int[count];
        vy = new int[count];
        frame = new int[count];
        type = new int[count];
        value = new int[count];
    }

    public static void spawn(int px, int py, int speedX, int speedY, int amount) {
        int slot;
        for (slot = 0; slot < count && age[slot] != FREE; ++slot) {
        }

        if (slot != count) {
            x[slot] = px;
            y[slot] = py;
            vx[slot] = speedX;
            vy[slot] = speedY;
            age[slot] = 1;
            value[slot] = amount;
            if (amount < 160) {
                type[slot] = Item.MONEY_GOLD;
            } else if (amount < 600) {
                type[slot] = Item.MONEY_BLUE_GEM;
            } else {
                type[slot] = Item.MONEY_RED_GEM;
            }
        }
    }

    /**
     * Cập nhật một frame; nhân vật đứng tại (playerX, playerY) cao playerHeight nhặt tiền
     * khi chạm vào. Trả về true nếu có nhặt (cần vẽ lại HUD).
     */
    public static boolean update(int playerX, int playerY, int playerHeight) {
        boolean picked = false;

        for (int i = 0; i < count; ++i) {
            if (age[i] != FREE) {
                if (abs(playerY - (playerHeight >> 1) - (y[i] - 7)) < 10 && abs(playerX - x[i]) < 10) {
                    Inventory.money += value[i];
                    picked = true;
                    age[i] = PICKED;
                    FloatingText.spawn("+" + value[i], 1, x[i], y[i] - 15, 0, -1);
                }

                x[i] += vx[i];
                y[i] += vy[i];
                if (vx[i] > 1) {
                    --vx[i];
                }

                if (vx[i] < -1) {
                    ++vx[i];
                }

                if (vy[i] < 20) {
                    ++vy[i];
                }

                ++age[i];
                if (age[i] > LIFETIME) {
                    age[i] = FREE;
                }

                if (age[i] % 20 > 18) {
                    frame[i] = 0;
                } else if (age[i] % 20 > 16) {
                    frame[i] = 1;
                } else {
                    frame[i] = 2;
                }

                if (y[i] >= 0 && y[i] < GameMap.pixelHeight && x[i] < GameMap.pixelWidth && x[i] >= 0) {
                    bounce(i);
                } else {
                    age[i] = FREE;
                }
            }
        }

        return picked;
    }

    /** Chạm nền thì nảy lên với nửa vận tốc. */
    private static void bounce(int i) {
        if (vy[i] > 2 && (GameMap.flagsAtPixel(x[i], y[i]) & TileFlags.SOLID) == TileFlags.SOLID) {
            y[i] = y[i] / GameMap.TILE_SIZE * GameMap.TILE_SIZE;
            if (vy[i] > 2) {
                vy[i] = -(vy[i] >> 1);
            } else {
                vx[i] = 0;
                vy[i] = 0;
            }
        }
    }

    public static void draw(Graphics g, int cameraX, int cameraY) {
        for (int i = 0; i < count; ++i) {
            if (age[i] != FREE && x[i] >= cameraX - 10 && x[i] <= cameraX + Viewport.viewWidth + 10 && y[i] >= cameraY && y[i] <= cameraY + Viewport.viewHeight + 20) {
                g.drawRegion(sprites[type[i]], 0, frame[i] * Item.FRAME_HEIGHT, Item.MONEY_WIDTH, Item.FRAME_HEIGHT, 0, x[i], y[i], 33);
            }
        }
    }

    private static int abs(int v) {
        return v > 0 ? v : -v;
    }
}
