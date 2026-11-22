package core.ui;

import assets.FontGraph;
import assets.Ui;
import core.app.GameState;
import core.input.Input;
import core.item.Inventory;
import core.item.ShopCatalog;
import core.player.PlayerStats;
import core.render.Viewport;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Cửa hàng (trạng thái GameState.SHOP). Hàng trên là 6 bình thuốc, hàng dưới là 4 cấp
 * kiếm (xem {@link ShopCatalog}).
 */
public final class ShopScreen {
    /** Cửa hàng mua bằng tiền trong game. */
    public static final int MODE_MONEY = 0;
    /** Cửa hàng tiền thật (SMS), giờ chỉ báo mua thất bại. */
    public static final int MODE_SMS = 1;

    /** Tổng số bình HP (hoặc MP) tối đa có thể mua thêm khi đang có không quá số này. */
    public static final int MAX_POTIONS_PER_KIND = 99;

    /** Kết quả của {@link #update()}. */
    public static final int RESULT_NONE = 0;
    public static final int RESULT_CLOSE = 1;
    /** Vừa mua kiếm mới ({@link Inventory#swordLevel} đã đổi); b cần nạp lại ảnh vũ khí. */
    public static final int RESULT_SWORD_PURCHASED = 2;

    public static int[] blinkColors = new int[]{12281360, 13859359, 15439166, 16693877, 16765346, 16777215, 16765346, 16693877, 15439166, 13859359, 12281360};

    public static int mode;
    public static int x;
    public static int y;
    public static int row;
    public static int cursor;
    public static int blink;
    public static Image[] swordIcons;

    private ShopScreen() {
    }

    public static void open(int shopMode) {
        y = Viewport.viewCenterY - 40;
        x = Viewport.viewCenterX - 50;
        swordIcons = new Image[Ui.SWORD_LEVEL_COUNT];

        try {
            for (int i = 0; i < Ui.SWORD_LEVEL_COUNT; ++i) {
                swordIcons[i] = Ui.loadSwordIcon(i);
            }
        } catch (Exception ex) {
        }

        UiFrame.loadCorners();
        System.gc();
        mode = shopMode;
    }

    /** Mở hộp thông báo của cửa hàng; đóng hộp sẽ quay lại cửa hàng. */
    public static void showNotice(String text) {
        MessageBox.show(MessageType.SHOP_NOTICE);
        MessageBox.setText(text);
        MessageBox.y = y;
        GameState.current = GameState.MESSAGE;
    }

    public static void showPurchaseFailed() {
        showNotice("Không thể mua. Xin hãy thử lại sau.");
    }

    public static int update() {
        if (blink > 0) {
            --blink;
        }

        if (Input.pressed[Input.KEY_FIRE] || Input.pressed[Input.KEY_SOFT_LEFT]) {
            Input.clearPressed();
            // Sau khi mua (hoặc không mua được) thì bỏ qua phần di chuyển con trỏ của frame này.
            return buy();
        }

        if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
            UiFrame.release();
            swordIcons = null;
            System.gc();
            Input.clearPressed();
            Input.clearHeld();
            return RESULT_CLOSE;
        }

        if (Input.pressed[Input.KEY_UP]) {
            Input.clearPressed();
            if (row == ShopCatalog.ROW_SWORDS) {
                row = ShopCatalog.ROW_POTIONS;
                ++cursor;
            }
        }

        if (Input.pressed[Input.KEY_DOWN]) {
            Input.clearPressed();
            if (row == ShopCatalog.ROW_POTIONS) {
                row = ShopCatalog.ROW_SWORDS;
                if (--cursor < 0) {
                    cursor = 0;
                }

                if (cursor > 3) {
                    cursor = 3;
                }
            }
        }

        if (Input.pressed[Input.KEY_LEFT]) {
            Input.clearPressed();
            if (--cursor < 0) {
                if (row == ShopCatalog.ROW_POTIONS) {
                    cursor = 5;
                } else {
                    cursor = 3;
                }
            }
        }

        if (Input.pressed[Input.KEY_RIGHT]) {
            Input.clearPressed();
            ++cursor;
            if (row == ShopCatalog.ROW_POTIONS && cursor == 6) {
                cursor = 0;
            }

            if (row == ShopCatalog.ROW_SWORDS && cursor == 4) {
                cursor = 0;
            }
        }

        return RESULT_NONE;
    }

    private static int buy() {
        int price = ShopCatalog.prices[row][cursor];
        if (row == ShopCatalog.ROW_POTIONS) {
            if (mode != MODE_MONEY) {
                showPurchaseFailed();
                return RESULT_NONE;
            }

            int[] counts = Inventory.potionCounts;
            if (cursor <= Inventory.POTION_HP_LARGE) {
                if (counts[0] + counts[1] + counts[2] > MAX_POTIONS_PER_KIND) {
                    return RESULT_NONE;
                }
            } else if (counts[3] + counts[4] + counts[5] > MAX_POTIONS_PER_KIND) {
                return RESULT_NONE;
            }

            if (Inventory.money >= price) {
                ++counts[cursor];
                Inventory.money -= price;
            }

            return RESULT_NONE;
        }

        if (Inventory.swordLevel >= cursor) {
            showNotice("Không cần thiết phải mua vũ khí này vì vũ khí đang sử dụng mạnh hơn!");
            return RESULT_NONE;
        }

        if (PlayerStats.level < ShopCatalog.swordLevelRequirements[cursor]) {
            showNotice("Bạn phải đạt cấp độ " + ShopCatalog.swordLevelRequirements[cursor] + " mới có thể mua vũ khí này!");
            return RESULT_NONE;
        }

        if (mode != MODE_MONEY) {
            showPurchaseFailed();
            return RESULT_NONE;
        }

        if (Inventory.money < price) {
            showNotice("Không có đủ tiền: " + price + "$. Bạn chỉ có: " + Inventory.money + "$. Hãy đi tìm thêm.");
            return RESULT_NONE;
        }

        Inventory.money -= price;
        Inventory.swordLevel = cursor;
        return RESULT_SWORD_PURCHASED;
    }

    /** Thông báo đã mua kiếm mới (gọi sau khi b nạp lại ảnh vũ khí). */
    public static void showSwordPurchased() {
        showNotice("Xin chúc mừng. Bạn đã có vũ khí mới: " + ShopCatalog.itemNames[row][cursor] + "!");
    }

    /** Vẽ hộp cửa hàng (HUD phía sau do b vẽ trước). */
    public static void draw(Graphics g, Image[] potionImages) {
        g.setClip(0, 0, Viewport.screenWidth, Viewport.screenHeight);
        UiFrame.drawPanel(g, y, "Mua", "Quay lại");
        if (mode == MODE_MONEY) {
            FontGraph.drawBold(g, "Tiền: " + Inventory.money + "$", Viewport.viewCenterX, y - 8, 2);
        } else {
            FontGraph.drawBold(g, ShopCatalog.itemNames[row][cursor], Viewport.viewCenterX, y - 8, 2);
        }

        InventoryScreen.drawPotionSlots(g, potionImages, x, y);

        for (int i = 0; i < Ui.SWORD_LEVEL_COUNT; ++i) {
            g.setColor(0);
            g.fillRect(x + 10 + i * 20, y + 32, 19, 20);
            g.setColor(4856064);
            g.drawRect(x + 10 + i * 20, y + 32, 18, 20);
            g.drawImage(swordIcons[i], x + 20 + i * 20, y + 42, 3);
        }

        g.setColor(blinkColors[blink]);
        if (row == ShopCatalog.ROW_POTIONS) {
            g.drawRect(x - 10 + cursor * 20, y + 10, 19, 20);
        } else {
            g.drawRect(x + 10 + cursor * 20, y + 32, 19, 20);
        }

        if (mode == MODE_MONEY) {
            FontGraph.drawBold(g, ShopCatalog.itemNames[row][cursor] + "-" + ShopCatalog.prices[row][cursor] + "$", Viewport.viewCenterX, y + 58, 2);
        } else {
            FontGraph.drawBold(g, ShopCatalog.smsPrices[row][cursor], Viewport.viewCenterX, y + 58, 2);
        }

        UiFrame.drawCorners(g, y);
    }
}
