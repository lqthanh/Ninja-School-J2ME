package core.item;

import assets.Item;
import core.fx.FloatingText;
import core.map.GameMap;
import core.map.TileFlags;
import core.player.PlayerStats;
import core.render.Viewport;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/** Bình thuốc rơi ra khi hạ quái: nảy trên nền, chạm vào là hồi HP/MP ngay (tối đa 5). */
public final class PotionDrops {
    public static final int CAPACITY = 5;
    public static final int FREE = 0;
    public static final int LIFETIME = 200;
    public static final int PICKED = 301;

    /** Ảnh bình theo loại (Item.POTION_HP / POTION_MP), mỗi ảnh 3 cỡ; dùng chung với cửa hàng. */
    public static Image[] sprites;
    private static int count;
    private static int[] x;
    private static int[] y;
    private static int[] vx;
    private static int[] vy;
    /** Tuổi theo frame (bắt đầu từ 1), {@link #FREE} nếu ô trống. */
    private static int[] age;
    /** Loại: Item.POTION_HP hoặc Item.POTION_MP. */
    private static int[] kind;
    /** Cỡ bình (0 nhỏ, 1 vừa, 2 to) theo lượng hồi: < 100, < 200, còn lại. */
    private static int[] size;
    /** Lượng HP/MP hồi. */
    private static int[] amount;

    private PotionDrops() {
    }

    public static void init() {
        count = CAPACITY;

        try {
            sprites = Item.ensurePotions(sprites);
        } catch (Exception ex) {
        }

        x = new int[count];
        y = new int[count];
        age = new int[count];
        vx = new int[count];
        vy = new int[count];
        size = new int[count];
        amount = new int[count];
        kind = new int[count];
    }

    /**
     * @param unused tham số không dùng (bản gốc luôn cho vận tốc y ban đầu là -5)
     */
    public static void spawn(int px, int py, int speedX, int unused, int restoreAmount, int potionKind) {
        int slot;
        for (slot = 0; slot < count && age[slot] != FREE; ++slot) {
        }

        if (slot != count) {
            x[slot] = px;
            y[slot] = py;
            vx[slot] = speedX;
            vy[slot] = -5;
            age[slot] = 1;
            amount[slot] = restoreAmount;
            kind[slot] = potionKind;
            if (restoreAmount < 100) {
                size[slot] = 0;
            } else if (restoreAmount < 200) {
                size[slot] = 1;
            } else {
                size[slot] = 2;
            }
        }
    }

    /** Giống {@link MoneyDrops#update}; trả về true nếu có nhặt (cần vẽ lại HUD). */
    public static boolean update(int playerX, int playerY, int playerHeight) {
        boolean picked = false;

        for (int i = 0; i < count; ++i) {
            if (age[i] != FREE) {
                if (abs(playerY - (playerHeight >> 1) - (y[i] - 7)) < 10 && abs(playerX - x[i]) < 10) {
                    if (kind[i] == Item.POTION_HP) {
                        PlayerStats.restoreHp(amount[i]);
                        FloatingText.spawn("+" + amount[i], 0, x[i], y[i] - 15, 0, -1);
                    } else {
                        PlayerStats.restoreMp(amount[i]);
                        FloatingText.spawn("+" + amount[i], 2, x[i], y[i] - 15, 0, -1);
                    }

                    picked = true;
                    age[i] = PICKED;
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

                if (y[i] < GameMap.pixelHeight && y[i] >= 0 && x[i] < GameMap.pixelWidth && x[i] >= 0) {
                    if (vy[i] > 2 && (GameMap.flagsAtPixel(x[i], y[i]) & TileFlags.SOLID) == TileFlags.SOLID) {
                        y[i] = y[i] / GameMap.TILE_SIZE * GameMap.TILE_SIZE;
                        if (vy[i] > 2) {
                            vy[i] = -(vy[i] >> 1);
                        } else {
                            vx[i] = 0;
                            vy[i] = 0;
                        }
                    }
                } else {
                    age[i] = FREE;
                }
            }
        }

        return picked;
    }

    public static void draw(Graphics g, int cameraX, int cameraY) {
        for (int i = 0; i < count; ++i) {
            if (age[i] != FREE && x[i] >= cameraX - 10 && x[i] <= cameraX + Viewport.viewWidth + 10 && y[i] >= cameraY && y[i] <= cameraY + Viewport.viewHeight + 20) {
                g.drawRegion(sprites[kind[i]], 0, size[i] * Item.FRAME_HEIGHT, Item.POTION_WIDTH, Item.FRAME_HEIGHT, 0, x[i], y[i], 33);
            }
        }
    }

    private static int abs(int v) {
        return v > 0 ? v : -v;
    }
}
