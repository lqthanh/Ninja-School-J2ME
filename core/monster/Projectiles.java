package core.monster;

import assets.Effect;
import core.render.Viewport;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Đạn bay thẳng của quái, ninja địch và boss (tối đa 10). Nhân vật chém trúng đạn loại
 * dưới {@link #DEFLECTABLE_TYPES} thì đạn bị bật ngược lại. Map 0 (trường) không có đạn.
 */
public final class Projectiles {
    public static final int CAPACITY = 10;
    public static final int FREE = -1;
    public static final int ACTIVE = 1;
    /** Loại đạn 0..2 chém bật lại được. */
    public static final int DEFLECTABLE_TYPES = 3;

    /** Ảnh theo loại và hướng (Effect.PROJECTILE_DIR_*). */
    public static Image[][] sprites;
    public static int count;
    public static int[] x;
    public static int[] y;
    public static int[] state;
    public static int[] vx;
    public static int[] damage;
    public static int[] type;

    /** Kết quả lần va chạm gần nhất của {@link #update}. */
    public static int hitDamage;
    public static int hitDirection;

    private Projectiles() {
    }

    public static void init(int mapId) {
        count = 0;
        if (mapId == 0) {
            count = 0;
        } else {
            sprites = Effect.ensureProjectiles(sprites);
            count = CAPACITY;
            x = new int[CAPACITY];
            y = new int[count];
            state = new int[count];
            vx = new int[count];
            damage = new int[count];
            type = new int[count];

            for (int i = 0; i < count; ++i) {
                state[i] = FREE;
            }
        }
    }

    public static void spawn(int px, int py, int speedX, int shotDamage, int shotType) {
        int slot = FREE;

        for (int i = 0; i < count; ++i) {
            if (state[i] == FREE) {
                slot = i;
                break;
            }
        }

        if (slot != FREE) {
            state[slot] = ACTIVE;
            x[slot] = px;
            y[slot] = py;
            vx[slot] = speedX;
            damage[slot] = shotDamage;
            type[slot] = shotType;
        }
    }

    /**
     * Cập nhật một frame. Xử lý tối đa một va chạm với nhân vật (hộp va chạm rộng
     * 2*halfWidth, cao 2*halfHeight, đáy tại playerY) mỗi frame.
     *
     * @param slashing nhân vật đang chém bằng kiếm (chém bật được đạn)
     * @return true nếu đạn trúng nhân vật; sát thương và hướng ở {@link #hitDamage},
     *         {@link #hitDirection}
     */
    public static boolean update(int playerX, int playerY, int halfWidth, int halfHeight, int cameraX, int cameraY, boolean slashing) {
        for (int i = 0; i < count; ++i) {
            if (state[i] == ACTIVE) {
                x[i] += vx[i];
                if (x[i] >= cameraX && x[i] <= cameraX + Viewport.viewWidth && y[i] >= cameraY && y[i] <= cameraY + Viewport.viewHeight) {
                    if (abs(playerX - x[i]) < halfWidth && abs(playerY - halfHeight - y[i]) < halfHeight) {
                        if (slashing && type[i] < DEFLECTABLE_TYPES) {
                            vx[i] = -vx[i];
                            x[i] += vx[i];
                            return false;
                        }

                        hitDamage = damage[i];
                        hitDirection = x[i] < playerX ? 1 : -1;
                        state[i] = FREE;
                        return true;
                    }
                } else {
                    // Ra khỏi màn hình thì biến mất.
                    state[i] = FREE;
                }
            }
        }

        return false;
    }

    public static void draw(Graphics g) {
        for (int i = 0; i < count; ++i) {
            if (state[i] != FREE) {
                g.drawImage(sprites[type[i]][vx[i] > 0 ? Effect.PROJECTILE_DIR_RIGHT : Effect.PROJECTILE_DIR_LEFT], x[i], y[i], 3);
            }
        }
    }

    private static int abs(int v) {
        return v > 0 ? v : -v;
    }
}
