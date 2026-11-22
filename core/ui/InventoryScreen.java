package core.ui;

import assets.FontGraph;
import assets.Item;
import core.app.GameState;
import core.input.Input;
import core.item.Inventory;
import core.player.PlayerStats;
import core.render.Viewport;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/** Túi đồ (trạng thái GameState.INVENTORY): chọn bình thuốc để dùng. */
public final class InventoryScreen {
    /** Màu viền ô đang chọn, nháy sáng dần rồi tối dần sau khi dùng một bình. */
    public static int[] blinkColors = new int[]{12281360, 13859359, 15439166, 16693877, 16765346, 16777215, 16765346, 16693877, 15439166, 13859359, 12281360};

    public static int x;
    public static int y;
    public static int cursor;
    public static int blink;

    private InventoryScreen() {
    }

    public static void open() {
        y = Viewport.viewCenterY - 40;
        x = Viewport.viewCenterX - 50;
        UiFrame.loadCorners();
    }

    /** Xử lý một frame; phím mềm phải quay lại menu trong game. */
    public static void update() {
        if (blink > 0) {
            --blink;
        }

        if (Input.pressed[Input.KEY_FIRE] || Input.pressed[Input.KEY_SOFT_LEFT]) {
            if (Inventory.potionCounts[cursor] > 0) {
                --Inventory.potionCounts[cursor];
                blink = blinkColors.length - 1;
                if (cursor <= Inventory.POTION_HP_LARGE) {
                    PlayerStats.restoreHp(Inventory.potionRestoreAmounts[cursor]);
                } else {
                    PlayerStats.restoreMp(Inventory.potionRestoreAmounts[cursor]);
                }
            }

            Input.clearPressed();
        }

        if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
            Input.clearPressed();
            Input.clearHeld();
            GameState.current = GameState.GAME_MENU;
        } else {
            if (Input.pressed[Input.KEY_LEFT]) {
                Input.clearPressed();
                if (--cursor < 0) {
                    cursor = Inventory.POTION_COUNT - 1;
                }
            }

            if (Input.pressed[Input.KEY_RIGHT]) {
                Input.clearPressed();
                if (++cursor == Inventory.POTION_COUNT) {
                    cursor = 0;
                }
            }
        }
    }

    /** Vẽ hộp túi đồ (HUD phía sau do b vẽ trước). */
    public static void draw(Graphics g, Image[] potionImages) {
        g.setClip(0, 0, Viewport.screenWidth, Viewport.screenHeight);
        UiFrame.drawPanel(g, y, "Sử dụng", "Quay lại");
        FontGraph.drawBold(g, "Sử dụng Item", Viewport.viewCenterX, y - 8, 2);
        drawPotionSlots(g, potionImages, x, y);
        g.setColor(blinkColors[blink]);
        g.drawRect(x - 10 + cursor * 20, y + 10, 20, 20);
        FontGraph.drawBold(g, Inventory.potionNames[cursor], Viewport.viewCenterX, y + 40, 2);
        FontGraph.drawBold(g, "Số lượng: " + Inventory.potionCounts[cursor], Viewport.viewCenterX, y + 55, 2);
        UiFrame.drawCorners(g, y);
    }

    /** 6 ô bình thuốc: 3 bình HP bên trái, 3 bình MP bên phải. Dùng chung với cửa hàng. */
    static void drawPotionSlots(Graphics g, Image[] potionImages, int x, int y) {
        for (int i = 0; i < 3; ++i) {
            g.setColor(0);
            g.fillRect(x - 10 + i * 20, y + 10, 19, 20);
            g.fillRect(x + 50 + i * 20, y + 10, 19, 20);
            g.setColor(4856064);
            g.drawRect(x - 10 + i * 20, y + 10, 20, 20);
            g.drawRect(x + 50 + i * 20, y + 10, 20, 20);
            g.drawRegion(potionImages[Item.POTION_HP], 0, i * Item.FRAME_HEIGHT, Item.POTION_WIDTH, Item.FRAME_HEIGHT, 0, x + i * 20, y + 20, 3);
            g.drawRegion(potionImages[Item.POTION_MP], 0, i * Item.FRAME_HEIGHT, Item.POTION_WIDTH, Item.FRAME_HEIGHT, 0, x + 60 + i * 20, y + 20, 3);
        }
    }
}
