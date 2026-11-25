package core.npc;

import assets.Character;
import core.player.Player;
import core.player.PlayerAction;
import javax.microedition.lcdui.Image;

/**
 * Cô gái (Ayumi/Sakura) đi theo và đánh quái cùng nhân vật. Trạng thái giống nhân vật:
 * {@link #action} dùng các giá trị STAND..HURT của {@link PlayerAction}, {@link #pose} là
 * chỉ số trong bảng tư thế của cô gái ({@link core.player.Poses#girlPoses}). Cập nhật: {@link GirlAI#update()}.
 */
public final class Girl {
    /** Đang đi theo nhân vật (được lưu trong save). */
    public static boolean following;
    /** Map cô gái đang ở, -1 nếu không có (được lưu trong save). */
    public static int mapId;
    /** Được lưu/đọc trong save nhưng không dùng ở đâu khác. */
    public static int unusedSaveSlot;
    /** Vị trí chân (pixel). */
    public static int x;
    public static int y;
    public static int vx;
    public static int vy;
    /** Hành động (giá trị của PlayerAction). */
    public static int action;
    public static int pose;
    /** Hướng nhìn: 1 phải, -1 trái. */
    public static int facing;
    public static int actionTimer;
    public static int actionTimer2;
    /** Đếm ngược tới 0 khi đang nhảy/rơi; chỉ được ghi, không được đọc để quyết định gì. */
    public static int airTimer;
    /** Số frame chạy thẳng (cảnh diễn) trước khi tự tìm đường theo nhân vật. */
    public static int runTimer;
    /** Hiệu ứng chém đang vẽ, -1 nếu không có. */
    public static int slashEffect;
    /** Vũ khí của đòn đang đánh: 0 kiếm, 1 phi tiêu. */
    public static int weapon;
    /** Hộp va chạm. */
    public static int width;
    public static int height;
    public static int halfWidth;
    public static int halfHeight;
    public static int quarterWidth;
    public static int quarterHeight;
    /** Bảy giá trị chỉ được lưu/đọc trong save, không dùng ở đâu khác. */
    public static int unused1;
    public static int unused2;
    public static int unused3;
    public static int unused4;
    public static int unused5;
    public static int unused6;
    public static int unused7;
    public static Image[] bodies;
    public static Image[] heads;
    public static Image[] legs;

    private Girl() {
    }

    /** Gọi khi vào map: nếu cô gái đang đi theo thì chuyển sang map mới, đứng sau nhân vật. */
    public static final void setup(int newMapId) {
        if (following) {
            mapId = newMapId;
        }

        if (mapId == newMapId) {
            slashEffect = -1;
            if (bodies == null) {
                try {
                    bodies = new Image[8];

                    for(int i = 0; i < 8; ++i) {
                        bodies[i] = Character.loadGirlBody(i);
                    }

                    heads = new Image[3];

                    for(int i = 0; i < 3; ++i) {
                        heads[i] = Character.loadGirlHead(i);
                    }

                    legs = new Image[9];

                    for(int i = 0; i < 9; ++i) {
                        legs[i] = Character.loadGirlLegs(i);
                    }
                } catch (Exception e) {
                }
            }

            width = 22;
            height = 32;
            halfWidth = width >> 1;
            halfHeight = height >> 1;
            quarterWidth = width >> 2;
            quarterHeight = height >> 2;
            if (following) {
                x = Player.x - Player.facing * 10;
                y = Player.y;
                facing = Player.facing;
                action = PlayerAction.FALL;
            }

        }
    }
}
