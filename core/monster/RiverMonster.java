package core.monster;

import assets.Mob;
import core.quest.Story;
import javax.microedition.lcdui.Image;

/**
 * Boss thủy quái ở map 97. Gồm thân (ảnh 0) và ba phần (ảnh 1, 2, 3) vẽ lệch theo
 * part*Offset; các độ lệch này thay đổi theo nhịp để tạo chuyển động. Bơi qua lại trong
 * [{@link #minX}, {@link #maxX}], nhô lên bắn đạn, lặn xuống rồi trồi lên cạnh nhân vật.
 * AI nằm trong b.run().
 */
public final class RiverMonster {
    /** Bị hạ: chìm dần trong 50 frame rồi sang {@link #STATE_SUNK}. */
    public static final int STATE_SINKING = 1;
    /** Bơi qua lại, thỉnh thoảng phun đạn vòng cung. */
    public static final int STATE_SWIM = 2;
    /** Nhô lên bắn đạn về phía nhân vật. */
    public static final int STATE_ATTACK = 4;
    /** Đã chìm hẳn (sự kiện map chờ trạng thái này). */
    public static final int STATE_SUNK = 5;
    /** Lặn xuống rồi trồi lên gần nhân vật. */
    public static final int STATE_DIVE = 7;

    public static boolean active;
    /** Sát thương của đạn. */
    public static int damage;
    /** Kích thước hộp va chạm (pixel). */
    public static int width;
    public static int height;
    public static int hp;
    public static int maxHp;
    /** Vị trí đáy thân (pixel). */
    public static int x;
    public static int y;
    /** Vị trí ba phần, tính lại mỗi frame từ {@link #x}, {@link #y} và độ lệch. */
    public static int part1X;
    public static int part1Y;
    public static int part2X;
    public static int part2Y;
    public static int part3X;
    public static int part3Y;
    /** Độ lệch của ba phần so với thân. */
    public static int part1OffsetX;
    public static int part1OffsetY;
    public static int part2OffsetX;
    public static int part2OffsetY;
    public static int part3OffsetX;
    public static int part3OffsetY;
    public static int state;
    /** Hướng: 1 phải, -1 trái. */
    public static int direction;
    /** Giới hạn bơi (pixel). */
    public static int minX;
    public static int maxX;
    public static int timer;
    /** Còn bao nhiêu frame hiện thanh máu. */
    public static int healthBarTimer;
    public static Image[] sprites;

    private RiverMonster() {
    }

    public static void setup(int mapId) {
        active = false;
        if (mapId == 97 && !Story.riverMonsterDefeated) {
            active = true;
            sprites = Mob.loadBoss1();

            x = 168;
            y = 226;
            minX = 144;
            maxX = 480;
            state = STATE_SWIM;
            direction = -1;
            maxHp = 8000;
            hp = 8000;
        }

    }
}
