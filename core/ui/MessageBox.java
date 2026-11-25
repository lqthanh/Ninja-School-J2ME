package core.ui;

import assets.FontGraph;
import core.app.GameState;
import core.app.MapLoader;
import core.input.Input;
import core.item.Inventory;
import core.map.GameMap;
import core.player.Player;
import core.render.TileRenderer;
import core.render.Viewport;
import core.save.Save;
import javax.microedition.lcdui.Graphics;

/** Hộp thông báo (trạng thái GameState.MESSAGE), loại hộp xem {@link MessageType}. */
public final class MessageBox {
    /** Kết quả của {@link #update()}: phím mềm vừa được nhấn. */
    public static final int RESULT_NONE = 0;
    public static final int RESULT_LEFT = 1;
    public static final int RESULT_RIGHT = 2;

    /** Số frame đầu tiên bỏ qua phím bấm, tránh vô tình đóng hộp ngay khi vừa mở. */
    public static final int INPUT_DELAY_FRAMES = 10;

    public static int type;
    private static String[] lines;
    private static String leftLabel;
    private static String rightLabel;
    /** Toạ độ y của dòng chữ đầu tiên. */
    public static int y;
    private static int inputDelay;

    private MessageBox() {
    }

    /** Chuẩn bị mở hộp: đặt lại thời gian chờ phím và nạp ảnh khung. */
    private static void prepare() {
        inputDelay = 0;
        UiFrame.loadCorners();
    }

    /** Đặt nội dung (tự xuống dòng) và căn giữa theo chiều dọc. */
    public static void setText(String text) {
        lines = FontGraph.wrapBoldText(text, 100);
        y = Viewport.viewCenterY - lines.length * 9;
    }

    /** Mở hộp với nội dung mặc định của loại {@code messageType}. */
    public static void show(int messageType) {
        prepare();
        type = messageType;
        if (messageType == MessageType.TRAVEL_NOT_VISITED) {
            setText("Không thể chuyển. Vì bạn chưa từng đi bộ đến đây!");
            leftLabel = "OK";
            rightLabel = "";
        } else if (messageType == MessageType.TRAVEL_ALREADY_HERE) {
            setText("Không cần phải chuyển vì bạn đang ở vị trí này!");
            leftLabel = "OK";
            rightLabel = "";
        } else if (messageType == MessageType.TRAVEL_CONFIRM) {
            int cost = WorldMap.computeTravelCost();
            WorldMap.travelCost = cost;
            setText("Chuyển đến đây phải trả " + cost + "$. Bạn có " + Inventory.money + "$. Chuyển?");
            leftLabel = "Chuyển";
            rightLabel = "Không";
        } else if (messageType == MessageType.NOT_ENOUGH_MONEY) {
            setText("Bạn không có đủ tiền!");
            leftLabel = "OK";
            rightLabel = "";
        } else if (messageType == MessageType.GAME_NOTICE) {
            setText("Bạn đã bị trọng thương! Mất một số điểm kinh nghiệm!");
            leftLabel = "OK";
            rightLabel = "";
        } else if (messageType == MessageType.LEVEL_UP) {
            setText("Level up!");
            leftLabel = "OK";
            rightLabel = "";
        } else if (messageType == MessageType.SHOP_NOTICE) {
            setText("Buy OK!");
            leftLabel = "";
            rightLabel = "OK";
        }
    }

    /**
     * Xử lý phím một frame. Trong {@link #INPUT_DELAY_FRAMES} frame đầu mọi phím bị bỏ qua.
     * Phím mềm trái được ưu tiên; phím được xóa sau khi đọc.
     */
    public static int update() {
        if (++inputDelay < INPUT_DELAY_FRAMES) {
            Input.clearPressed();
        }

        if (Input.pressed[Input.KEY_SOFT_LEFT]) {
            Input.clearPressed();
            return RESULT_LEFT;
        }

        if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
            Input.clearPressed();
            return RESULT_RIGHT;
        }

        return RESULT_NONE;
    }

    public static void draw(Graphics g) {
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        UiFrame.drawPanel(g, y, leftLabel, rightLabel);

        for (int i = 0; i < lines.length; ++i) {
            FontGraph.drawBold(g, lines[i], Viewport.screenCenterX, y + i * 18, 2);
        }

        UiFrame.drawCorners(g, y);
    }

    /** Phím mềm trái trên hộp thông báo. */
    public static void onConfirmed() {
        switch (type) {
            case MessageType.TRAVEL_NOT_VISITED:
            case MessageType.TRAVEL_ALREADY_HERE:
            case MessageType.NOT_ENOUGH_MONEY:
                GameState.current = GameState.WORLD_MAP;
                break;
            case MessageType.TRAVEL_CONFIRM:
                // Có đúng bằng giá là đủ (bản gốc dùng >= nên bằng giá vẫn bị báo không đủ tiền).
                if (WorldMap.travelCost > Inventory.money) {
                    show(MessageType.NOT_ENOUGH_MONEY);
                } else {
                    Inventory.money -= WorldMap.travelCost;
                    WorldMap.close();
                    UiFrame.release();
                    TileRenderer.ensureMapImages();
                    MapLoader.releaseSprites();
                    Player.checkpointX = Player.x = WorldMap.spawnTileX[WorldMap.selected] * GameMap.TILE_SIZE;
                    Player.checkpointY = Player.y = WorldMap.spawnTileY[WorldMap.selected] * GameMap.TILE_SIZE;
                    Save.pendingSave = true;
                    MapLoader.change(WorldMap.selected);
                }
                break;
            case MessageType.GAME_NOTICE:
            case MessageType.LEVEL_UP:
                UiFrame.release();
                GameState.current = GameState.PLAYING;
                break;
        }
    }
}
