package assets;

import javax.microedition.lcdui.Image;

/**
 * Hình ảnh NPC (assets/npc/).
 *
 *  - npc{id}_body.png (gốc: {id}0.png) : thân trên, được vẽ nhún nhảy
 *  - npc{id}_legs.png (gốc: {id}1.png) : phần chân
 *  - dog{frame}.png   (gốc: d{frame}.png) : con chó, 2 frame
 */
public final class Npc {
    public static final int NPC_COUNT = 6;

    public static final int PART_BODY = 0;
    public static final int PART_LEGS = 1;
    public static final int PART_COUNT = 2;

    public static final int DOG_FRAME_COUNT = 2;

    private Npc() {
    }

    /** Mảng trống [NPC_COUNT][PART_COUNT] để nạp dần từng NPC bằng {@link #loadSprite}. */
    public static Image[][] createSpriteTable() {
        return new Image[NPC_COUNT][PART_COUNT];
    }

    /** Nạp thân và chân của NPC {@code id} vào {@code sprites[id]}. */
    public static void loadSprite(Image[][] sprites, int id) throws Exception {
        sprites[id][PART_BODY] = Image.createImage(Enum.PATH_NPC + "npc" + id + "_body" + Enum.EXTENSION_PNG);
        sprites[id][PART_LEGS] = Image.createImage(Enum.PATH_NPC + "npc" + id + "_legs" + Enum.EXTENSION_PNG);
    }

    /** Ảnh con chó; frame lỗi sẽ để null (giống bản gốc). */
    public static Image[] ensureDog(Image[] dog) {
        if (dog != null) {
            return dog;
        }

        Image[] result = new Image[DOG_FRAME_COUNT];
        for (int i = 0; i < DOG_FRAME_COUNT; i++) {
            try {
                result[i] = Image.createImage(Enum.PATH_NPC + "dog" + i + Enum.EXTENSION_PNG);
            } catch (Exception ex) {
            }
        }

        return result;
    }
}
