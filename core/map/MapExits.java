package core.map;

import core.app.GameState;
import core.app.MapLoader;
import core.player.Player;
import core.player.PlayerAction;
import core.quest.Skills;
import core.save.Save;
import core.ui.MessageBox;
import core.ui.MessageType;

/**
 * Cửa ra của map hiện tại: đi ra mép map thì chuyển sang map khác.
 * Mỗi cửa gồm map đích (-1 = không có cửa) và vị trí xuất hiện (pixel) ở map đích.
 * Giá trị được gán bởi {@link MapConfig#load(int)}; field nào map không gán thì giữ giá trị cũ.
 */
public final class MapExits {
    /** Không có cửa ra ở hướng này. */
    public static final int NONE = -1;

    /** Đi ra mép trái. */
    public static int leftMap;
    public static int leftSpawnX;
    public static int leftSpawnY;

    /** Đi ra mép phải. */
    public static int rightMap;
    public static int rightSpawnX;
    public static int rightSpawnY;

    /** Đi ra mép phải khi đang ở thấp hơn {@link #rightLowerMinY} (thay cho cửa phải). */
    public static int rightLowerMap;
    public static int rightLowerSpawnX;
    public static int rightLowerSpawnY;
    public static int rightLowerMinY;

    /** Rơi xuống mép dưới. */
    public static int bottomMap;
    public static int bottomSpawnX;
    public static int bottomSpawnY;

    private MapExits() {
    }

    /** Nhân vật ra khỏi mép map có lối ra: giải phóng ảnh và chuyển map. Trả về true nếu đã chuyển. */
    public static boolean leaveAtEdge() {
        if (Player.x < 0) {
            if (leftMap != NONE) {
                MapLoader.releaseSprites();
                Player.checkpointX = Player.x = leftSpawnX - 1;
                Player.checkpointY = Player.y = leftSpawnY;
                Save.pendingSave = true;
                MapLoader.change(leftMap);
                return true;
            }
        } else if (Player.x >= GameMap.pixelWidth - 24) {
            if (rightMap != NONE) {
                if (!Skills.hide && rightMap == 95) {
                    MessageBox.show(MessageType.LEVEL_UP);
                    MessageBox.setText("Khu vực phía trước rất nguy hiểm! Chỉ những người có thể ẩn thân có thể vào!");
                    Player.x = GameMap.pixelWidth - 60;
                    Player.action = PlayerAction.FALL;
                    GameState.current = GameState.MESSAGE;
                    return true;
                }

                MapLoader.releaseSprites();
                if (rightLowerMap != NONE && Player.y > rightLowerMinY) {
                    Player.checkpointX = Player.x = rightLowerSpawnX;
                    Player.checkpointY = Player.y = rightLowerSpawnY;
                    Save.pendingSave = true;
                    MapLoader.change(rightLowerMap);
                    return true;
                }

                Player.checkpointX = Player.x = rightSpawnX;
                Player.checkpointY = Player.y = rightSpawnY;
                Save.pendingSave = true;
                MapLoader.change(rightMap);
                return true;
            }
        } else if (Player.y + Player.vy >= GameMap.pixelHeight && Player.action != PlayerAction.DEAD && bottomMap != NONE) {
            MapLoader.releaseSprites();
            Player.checkpointX = Player.x = bottomSpawnX;
            Player.checkpointY = Player.y = bottomSpawnY;
            Save.pendingSave = true;
            MapLoader.change(bottomMap);
            Player.action = PlayerAction.FALL;
            return true;
        }

        return false;
    }
}
