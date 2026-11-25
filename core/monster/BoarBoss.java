package core.monster;

import assets.Mob;
import core.map.GameMap;
import javax.microedition.lcdui.Image;

/**
 * Boss lợn rừng ở rừng trúc (map 4 và 5). Đi qua lại, thấy nhân vật thì lao tới húc;
 * khi còn dưới nửa máu thì đi và lao nhanh hơn. Bị hạ thì văng lên rồi rơi khỏi màn hình.
 * AI: {@link BossAI#updateBoarBoss()}; trúng đòn: Combat.damageBoarBoss().
 */
public final class BoarBoss {
    /** Không có boss (map khác). */
    public static final int STATE_NONE = 0;
    /** Đứng nghỉ {@link #timer} frame rồi chọn điểm đến mới. */
    public static final int STATE_IDLE = 1;
    /** Đi tới {@link #targetX}. */
    public static final int STATE_WALK = 2;
    /** Lao tới húc nhân vật. */
    public static final int STATE_CHARGE = 5;
    /** Bị hạ: văng lên rồi rơi ({@link #timer} là vận tốc rơi). */
    public static final int STATE_KNOCKED_OUT = 6;
    /** Bị trúng đòn, lùi lại 4 frame. */
    public static final int STATE_HURT = 7;
    /** Đã rơi khỏi màn hình (sự kiện map chờ trạng thái này). */
    public static final int STATE_GONE = 22;

    public static boolean active;
    /** Sát thương mỗi cú húc. */
    public static int damage;
    /** Vị trí chân (pixel). */
    public static int x;
    public static int y;
    public static int vx;
    public static int state;
    /** Hướng: 1 phải, -1 trái. */
    public static int direction;
    /** Khung hình (0..2). */
    public static int frame;
    /** Kích thước ảnh. */
    public static int width;
    public static int height;
    /** Bộ đếm chờ khi đứng nghỉ / bị đánh; vận tốc rơi khi bị hạ. */
    public static int timer;
    /** Điểm đến khi đi hoặc lao; khi bị đánh là thời gian đứng nghỉ sau đó. */
    public static int targetX;
    public static int hp;
    public static int maxHp;
    /** Còn bao nhiêu frame vẽ hiệu ứng trúng đòn. */
    public static int hurtFlash;
    public static Image[] sprites;

    private BoarBoss() {
    }

    public static void setup(int mapId) {
        maxHp = 1200;
        hp = 1200;
        damage = 30;
        active = false;
        // Map không có boss lợn rừng: xóa trạng thái boss cũ (bản gốc giữ nguyên state, nên
        // sự kiện map khác kiểm tra state == 22 vẫn thấy boss "vừa bị hạ").
        state = STATE_NONE;
        if (mapId == 4) {
            active = true;
            sprites = Mob.ensureBoss0(sprites);

            width = sprites[0].getWidth();
            height = sprites[0].getHeight();
            x = 55 * GameMap.TILE_SIZE + 12;
            y = 13 * GameMap.TILE_SIZE;
            state = STATE_IDLE;
            direction = -1;
            frame = 0;
        }

        if (mapId == 5) {
            active = true;
            sprites = Mob.ensureBoss0(sprites);

            width = sprites[0].getWidth();
            height = sprites[0].getHeight();
            x = 8 * GameMap.TILE_SIZE + 12;
            y = 56 * GameMap.TILE_SIZE;
            state = STATE_IDLE;
            direction = -1;
            frame = 0;
        }

    }
}
