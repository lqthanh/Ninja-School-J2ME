package core.monster;

import assets.Effect;
import core.render.Viewport;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Đạn ném vòng cung (chịu trọng lực) của thủy quái và một số quái (tối đa 8). Ảnh lấy từ
 * file "heal" trong assets.
 */
public final class ArcProjectiles {
    public static final int CAPACITY = 8;
    public static final int FREE = 0;
    public static final int ACTIVE = 1;
    /**
     * Gán sau khi trúng nhân vật. LỖI GỐC (giữ nguyên): giá trị khác FREE nên đạn vẫn bay,
     * vẫn được vẽ và có thể trúng lại cho tới khi rơi khỏi màn hình.
     */
    public static final int HIT = -1;

    private static Image sprite;
    private static int count;
    private static int[] x;
    private static int[] y;
    private static int[] state;
    private static int[] vx;
    private static int[] vy;
    private static int[] damage;

    public static int hitDamage;
    public static int hitDirection;

    private ArcProjectiles() {
    }

    public static void init() {
        count = CAPACITY;
        x = new int[CAPACITY];
        y = new int[count];
        state = new int[count];
        vx = new int[count];
        vy = new int[count];
        damage = new int[count];

        try {
            sprite = Effect.loadHeal();
        } catch (Exception ex) {
        }
    }

    public static void spawn(int px, int py, int speedX, int speedY, int shotDamage) {
        int slot = -1;

        for (int i = 0; i < count; ++i) {
            if (state[i] == FREE) {
                slot = i;
                break;
            }
        }

        if (slot != -1) {
            state[slot] = ACTIVE;
            x[slot] = px;
            y[slot] = py;
            vx[slot] = speedX;
            vy[slot] = speedY;
            damage[slot] = shotDamage;
        }
    }

    /** Giống {@link Projectiles#update} nhưng không chém bật được. */
    public static boolean update(int playerX, int playerY, int halfWidth, int halfHeight, int cameraY) {
        for (int i = 0; i < count; ++i) {
            if (state[i] != FREE) {
                x[i] += vx[i];
                y[i] += vy[i];
                ++vy[i];
                if (y[i] > cameraY + Viewport.viewHeight) {
                    state[i] = FREE;
                }

                if (abs(playerX - x[i]) < halfWidth && abs(playerY - halfHeight - y[i]) < halfHeight) {
                    hitDamage = damage[i];
                    hitDirection = x[i] < playerX ? 1 : -1;
                    state[i] = HIT;
                    return true;
                }
            }
        }

        return false;
    }

    public static void draw(Graphics g) {
        for (int i = 0; i < count; ++i) {
            if (state[i] != FREE) {
                g.drawImage(sprite, x[i], y[i], 3);
            }
        }
    }

    private static int abs(int v) {
        return v > 0 ? v : -v;
    }
}
