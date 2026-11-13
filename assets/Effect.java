package assets;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 * Hình ảnh hiệu ứng / vật thể bay (assets/effect/).
 *
 *  - projectile{type}_{dir}.png (gốc: ar{type}{dir}.png)
 *      Đạn của địch, 4 loại; dir 0 = bay sang phải, 1 = bay sang trái.
 *  - dart{level}_{frame}.png (gốc: pt0..pt3.png)
 *      Phi tiêu của người chơi, level 0 = cấp 1 (pt0, pt1), level 1 = cấp 2 (pt2, pt3); 2 frame xoay.
 *  - motorbike{frame}.png (gốc: v{frame}.png) : xe máy, 3 frame
 *  - slash{style}_{tier}.png (gốc: w{style}t{tier}.png)
 *      Đường kiếm của người chơi, 11 kiểu chém; tier = cấp kiếm (0..3).
 *      Kiểu nào không có ảnh cho cấp kiếm hiện tại thì dùng tier 0.
 *  - heal.png            (gốc: e/b.png)        : dấu cộng khi hồi máu
 *  - dust{dir}_{frame}   (gốc: e/d{dir}{frame}) : bụi, 2 hướng x 5 frame
 *  - aura{n}             (gốc: e/l{n})          : vòng hào quang quanh nhân vật, 2 frame
 *  - blood{n}            (gốc: e/sp{n})         : máu bắn, 3 frame
 *  - water_splash{n}     (gốc: e/w{n})          : nước bắn, 3 frame
 */
public final class Effect {
    public static final int PROJECTILE_TYPE_COUNT = 4;
    public static final int PROJECTILE_DIR_RIGHT = 0;
    public static final int PROJECTILE_DIR_LEFT = 1;
    public static final int PROJECTILE_DIR_COUNT = 2;

    public static final int DART_LEVEL_1 = 0;
    public static final int DART_LEVEL_2 = 1;
    public static final int DART_FRAME_COUNT = 2;

    public static final int MOTORBIKE_FRAME_COUNT = 3;

    public static final int SLASH_STYLE_COUNT = 11;
    public static final int SLASH_DEFAULT_TIER = 0;

    public static final int DUST_DIR_COUNT = 2;
    public static final int DUST_FRAME_COUNT = 5;
    public static final int AURA_FRAME_COUNT = 2;
    public static final int BLOOD_FRAME_COUNT = 3;
    public static final int WATER_SPLASH_FRAME_COUNT = 3;

    private Effect() {
    }

    // =====================================================================================
    // #region Projectile

    /** Đạn của địch: [type][dir]; ảnh lỗi để null (giống bản gốc). */
    public static Image[][] ensureProjectiles(Image[][] projectiles) {
        if (projectiles != null) {
            return projectiles;
        }

        Image[][] result = new Image[PROJECTILE_TYPE_COUNT][PROJECTILE_DIR_COUNT];
        for (int type = 0; type < PROJECTILE_TYPE_COUNT; type++) {
            for (int dir = 0; dir < PROJECTILE_DIR_COUNT; dir++) {
                try {
                    result[type][dir] = Image.createImage(Enum.PATH_EFFECT + "projectile" + type + "_" + dir + Enum.EXTENSION_PNG);
                } catch (Exception ex) {
                }
            }
        }

        return result;
    }

    // #endregion
    // =====================================================================================
    // #region Dart

    public static Image[] createDartFrames() {
        return new Image[DART_FRAME_COUNT];
    }

    /** Nạp các frame phi tiêu của {@code level} vào {@code frames}; frame lỗi giữ nguyên ảnh cũ. */
    public static void loadDarts(Image[] frames, int level) {
        for (int i = 0; i < DART_FRAME_COUNT; i++) {
            try {
                frames[i] = Image.createImage(Enum.PATH_EFFECT + "dart" + level + "_" + i + Enum.EXTENSION_PNG);
            } catch (Exception ex) {
            }
        }
    }

    // #endregion
    // =====================================================================================
    // #region Motorbike

    public static Image[] ensureMotorbike(Image[] motorbike) throws Exception {
        if (motorbike != null) {
            return motorbike;
        }

        Image[] result = new Image[MOTORBIKE_FRAME_COUNT];
        for (int i = 0; i < MOTORBIKE_FRAME_COUNT; i++) {
            result[i] = Image.createImage(Enum.PATH_EFFECT + "motorbike" + i + Enum.EXTENSION_PNG);
        }

        return result;
    }

    // #endregion
    // =====================================================================================
    // #region Slash

    /** Đường kiếm theo cấp kiếm {@code tier}, thiếu ảnh thì dùng tier mặc định. */
    public static Image[] loadSlashes(int tier) {
        Image[] result = new Image[SLASH_STYLE_COUNT];
        for (int style = 0; style < SLASH_STYLE_COUNT; style++) {
            try {
                result[style] = loadSlash(style, tier);
            } catch (Exception ex) {
                try {
                    result[style] = loadSlash(style, SLASH_DEFAULT_TIER);
                } catch (Exception fallbackEx) {
                    fallbackEx.printStackTrace();
                }
            }
        }

        return result;
    }

    private static Image loadSlash(int style, int tier) throws Exception {
        return Image.createImage(Enum.PATH_EFFECT + "slash" + style + "_" + tier + Enum.EXTENSION_PNG);
    }

    // #endregion
    // =====================================================================================
    // #region Particles

    public static Image loadHeal() throws IOException {
        return load("heal");
    }

    public static Image loadDust(int dir, int frame) throws IOException {
        return load("dust" + dir + "_" + frame);
    }

    public static Image loadAura(int frame) throws IOException {
        return load("aura" + frame);
    }

    public static Image loadBlood(int frame) throws IOException {
        return load("blood" + frame);
    }

    public static Image loadWaterSplash(int frame) throws IOException {
        return load("water_splash" + frame);
    }

    private static Image load(String name) throws IOException {
        return Image.createImage(Enum.PATH_EFFECT + name + Enum.EXTENSION_PNG);
    }

    // #endregion
    // =====================================================================================
}
