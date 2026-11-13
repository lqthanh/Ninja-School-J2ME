package assets;

import javax.microedition.lcdui.Image;

/**
 * Hình ảnh vật phẩm trong màn chơi (assets/item/).
 *
 *  - crate.png   (b.png)      : thùng gỗ phá được
 *  - scroll.png  (bk.png)     : cuộn giấy - biểu tượng nhiệm vụ
 *  - money0..2   (dm0..2.png) : tiền rơi ra: đồng vàng / ngọc xanh / ngọc đỏ
 *  - potion0..1  (pt0..1.png) : bình HP / bình MP
 *  - rope0..2    (r0..2.png)  : chưa được code nào sử dụng
 *
 * Ảnh money/potion là sprite sheet dọc gồm FRAME_COUNT frame, mỗi frame cao FRAME_HEIGHT.
 */
public final class Item {
    public static final int FRAME_HEIGHT = 15;
    public static final int FRAME_COUNT = 3;

    public static final int MONEY_WIDTH = 8;
    public static final int MONEY_GOLD = 0;
    public static final int MONEY_BLUE_GEM = 1;
    public static final int MONEY_RED_GEM = 2;
    public static final int MONEY_TYPE_COUNT = 3;

    public static final int POTION_WIDTH = 13;
    public static final int POTION_HP = 0;
    public static final int POTION_MP = 1;
    public static final int POTION_TYPE_COUNT = 2;

    private Item() {
    }

    public static Image loadScroll() throws Exception {
        return Image.createImage(Enum.PATH_ITEM + "scroll" + Enum.EXTENSION_PNG);
    }

    public static Image ensureCrate(Image crate) throws Exception {
        if (crate != null) {
            return crate;
        }

        return Image.createImage(Enum.PATH_ITEM + "crate" + Enum.EXTENSION_PNG);
    }

    public static Image[] ensureMoney(Image[] money) throws Exception {
        if (money != null) {
            return money;
        }

        return loadSheets("money", MONEY_TYPE_COUNT);
    }

    public static Image[] ensurePotions(Image[] potions) throws Exception {
        if (potions != null) {
            return potions;
        }

        return loadSheets("potion", POTION_TYPE_COUNT);
    }

    private static Image[] loadSheets(String name, int count) throws Exception {
        Image[] result = new Image[count];
        for (int i = 0; i < count; i++) {
            result[i] = Image.createImage(Enum.PATH_ITEM + name + i + Enum.EXTENSION_PNG);
        }

        return result;
    }
}
