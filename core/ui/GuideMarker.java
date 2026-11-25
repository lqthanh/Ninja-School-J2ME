package core.ui;

import core.map.GameMap;
import core.npc.Npcs;
import core.player.Player;
import core.render.Camera;
import core.render.Viewport;
import core.util.MathUtil;

/** Mũi tên chỉ đường tới NPC / điểm cần đến tiếp theo. */
public final class GuideMarker {
    /** Map có mũi tên chỉ đường, -1 nếu không có. */
    public static int map = -1;

    /** Điểm cần tới (pixel trên map). */
    public static int x;
    public static int y;

    /** Khung hình mũi tên (hướng chỉ) và vị trí vẽ trên màn hình. */
    public static int arrowFrame;
    public static int arrowX;
    public static int arrowY;

    /** Hướng và độ nhún lên xuống của mũi tên. */
    public static int bobDirection = 1;
    public static int bob;

    /** NPC cần tới (chỉ số trong Npcs), -1 nếu chỉ là một điểm. */
    public static int npc;

    /** Mũi tên chờ đặt sau khi đóng hội thoại (do cutscene / nhiệm vụ đặt trước). */
    public static int pendingMap = -1;
    public static int pendingX;
    public static int pendingY;
    public static int pendingNpc;

    private GuideMarker() {
    }

    /** Đặt mũi tên chỉ tới điểm (x, y) trên map {@code map}. */
    public static void set(int map, int x, int y, int npc) {
        GuideMarker.map = map;
        GuideMarker.x = x;
        GuideMarker.y = y;
        GuideMarker.npc = npc;
    }

    /**
     * Đặt mũi tên chỉ đường tới NPC thứ {@code npc} trên map hiện tại. Gọi ở Trường (map 0),
     * nơi NPC thứ i có loại i nên truyền thẳng {@code Npcs.TYPE_*}.
     */
    public static void setNextNpc(int npc) {
        pendingMap = GameMap.id;
        pendingNpc = npc;
        pendingX = Npcs.x[npc];
        pendingY = Npcs.y[npc] - 44;
    }

    /** Tính vị trí mũi tên: ngay trên điểm đến nếu nó trong khung nhìn, nếu không thì ở mép màn hình theo hướng điểm đến; nhún lên xuống. */
    public static void update() {
        if (map >= 0) {
            if (x > Camera.x + 10 && x < Camera.x + Viewport.viewWidth - 10 && y > Camera.y + 10 && y < Camera.y + Viewport.viewHeight - 10) {
                arrowFrame = 2;
                arrowX = x;
                arrowY = y + bob;
            } else if (MathUtil.abs(Player.x - x) > MathUtil.abs(Player.y - y)) {
                if (Player.x > x) {
                    arrowFrame = 1;
                    arrowX = Camera.x + 20 + bob;
                } else {
                    arrowFrame = 0;
                    arrowX = Camera.x + Viewport.viewWidth - 20 + bob;
                }

                if ((arrowY = y + 24) < Camera.y + 10) {
                    arrowY = Camera.y + 10;
                }

                if (arrowY > Camera.y + Viewport.viewHeight - 10) {
                    arrowY = Camera.y + Viewport.viewHeight - 10;
                }
            } else if (Player.y > y) {
                arrowFrame = 3;
                arrowX = Player.x;
                arrowY = Camera.y + 20 + bob;
            } else {
                arrowFrame = 2;
                arrowX = Player.x;
                arrowY = Camera.y + Viewport.viewHeight - 20 + bob;
            }

            if ((bob += bobDirection) == 5 || bob == -5) {
                bobDirection = -bobDirection;
            }
        }
    }
}
