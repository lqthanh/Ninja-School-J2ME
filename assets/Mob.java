package assets;

import javax.microedition.lcdui.Image;

/**
 * Hình ảnh quái vật (assets/mob/).
 *
 *  - monster{type}_{dir}_{frame}.png (gốc: m{type}{dir}{frame}.png)
 *      Quái thường, type 0..19 (theo danh sách tên quái trong GameText.monsterNames),
 *      dir 0 = hướng phải, 1 = hướng trái; frame 0..3.
 *      Một số quái thiếu file: hướng trái dùng lại hướng phải, frame 2 dùng lại frame 1.
 *  - ninja{type}_{frame}.png (gốc: y{type}{frame}.png) : thân ninja địch, 7 loại x 2 frame
 *  - ninja_legs{frame}.png   (gốc: x{frame}.png)       : chân ninja địch, 4 frame
 *  - boss0_{part}.png (gốc: b0{part}.png) : boss lợn rừng (map 4, 5)
 *  - boss1_{part}.png (gốc: b1{part}.png) : boss thuỷ quái (map 97)
 *  - boss2_{part}.png (gốc: b2{part}.png) : boss map 95
 */
public final class Mob {
    public static final int MONSTER_DIR_RIGHT = 0;
    public static final int MONSTER_DIR_LEFT = 1;
    public static final int MONSTER_DIR_COUNT = 2;
    public static final int MONSTER_FRAME_COUNT = 4;

    public static final int NINJA_TYPE_COUNT = 7;
    public static final int NINJA_FRAME_COUNT = 2;
    public static final int NINJA_LEGS_FRAME_COUNT = 4;

    public static final int BOSS0_PART_COUNT = 5;
    public static final int BOSS1_PART_COUNT = 4;

    private Mob() {
    }

    // =====================================================================================
    // #region Monster

    /**
     * Nạp toàn bộ frame của một loại quái thường: [dir][frame].
     * File thiếu được thay thế giống bản gốc, frame không có thay thế sẽ là null.
     */
    public static Image[][] loadMonster(int type) {
        Image[][] frames = new Image[MONSTER_DIR_COUNT][MONSTER_FRAME_COUNT];

        for (int dir = 0; dir < MONSTER_DIR_COUNT; dir++) {
            for (int frame = 0; frame < MONSTER_FRAME_COUNT; frame++) {
                try {
                    frames[dir][frame] = Image.createImage(Enum.PATH_MOB + "monster" + type + "_" + dir + "_" + frame + Enum.EXTENSION_PNG);
                } catch (Exception ex) {
                }

                if (frames[dir][frame] == null && dir == MONSTER_DIR_LEFT) {
                    frames[MONSTER_DIR_LEFT][frame] = frames[MONSTER_DIR_RIGHT][frame];
                }
            }

            if (frames[dir][2] == null) {
                frames[dir][2] = frames[dir][1];
            }
        }

        return frames;
    }

    // #endregion
    // =====================================================================================
    // #region Ninja

    public static Image[] loadNinjaLegs() throws Exception {
        return loadParts("ninja_legs", NINJA_LEGS_FRAME_COUNT);
    }

    /**
     * Thân ninja địch: [type][frame], chỉ nạp các type có {@code used[type] == true}.
     */
    public static Image[][] loadNinjaBodies(boolean[] used) throws Exception {
        Image[][] result = new Image[NINJA_TYPE_COUNT][NINJA_FRAME_COUNT];

        for (int type = 0; type < NINJA_TYPE_COUNT; type++) {
            if (used[type]) {
                for (int frame = 0; frame < NINJA_FRAME_COUNT; frame++) {
                    result[type][frame] = Image.createImage(Enum.PATH_MOB + "ninja" + type + "_" + frame + Enum.EXTENSION_PNG);
                }
            }
        }

        return result;
    }

    // #endregion
    // =====================================================================================
    // #region Boss

    /** Boss lợn rừng; part lỗi để null (giống bản gốc). */
    public static Image[] ensureBoss0(Image[] parts) {
        if (parts != null) {
            return parts;
        }

        Image[] result = new Image[BOSS0_PART_COUNT];
        for (int i = 0; i < BOSS0_PART_COUNT; i++) {
            try {
                result[i] = loadBossPart(0, i);
            } catch (Exception ex) {
            }
        }

        return result;
    }

    /** Boss thuỷ quái; part lỗi để null (giống bản gốc). */
    public static Image[] loadBoss1() {
        Image[] result = new Image[BOSS1_PART_COUNT];
        for (int i = 0; i < BOSS1_PART_COUNT; i++) {
            try {
                result[i] = loadBossPart(1, i);
            } catch (Exception ex) {
            }
        }

        return result;
    }

    public static Image loadBossPart(int boss, int part) throws Exception {
        return Image.createImage(Enum.PATH_MOB + "boss" + boss + "_" + part + Enum.EXTENSION_PNG);
    }

    // #endregion
    // =====================================================================================

    private static Image[] loadParts(String name, int count) throws Exception {
        Image[] result = new Image[count];
        for (int i = 0; i < count; i++) {
            result[i] = Image.createImage(Enum.PATH_MOB + name + i + Enum.EXTENSION_PNG);
        }

        return result;
    }
}
