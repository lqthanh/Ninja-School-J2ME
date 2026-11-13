package assets;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 * Hình ảnh giao diện (assets/ui/).
 *
 *  - arrow{n}       (gốc: u/arr{n})     : mũi tên hướng dẫn: 0 phải, 1 trái, 2 xuống, 3 lên
 *  - corner{n}      (gốc: u/bd{n})      : 4 góc trang trí khung menu/cửa hàng
 *  - hud{n}         (gốc: u/c{n})       : khung HUD trái (Lv/HP/MP/EXP) và phải (bình HP/MP)
 *  - marker         (gốc: u/co)         : mũi tên nhỏ chỉ vị trí trên bản đồ
 *  - logo           (gốc: u/lg)         : logo nhà phát hành, 4 frame 71x51 xếp dọc
 *  - sound_icon     (gốc: u/m)          : biểu tượng nốt nhạc (hỏi bật âm thanh)
 *  - smileys        (gốc: u/m2)         : ảnh 2 mặt cười
 *  - title          (gốc: u/tt)         : chữ "Ninja School" màn hình chính
 *  - sword_icon{n}  (gốc: u/wpt0,2,3,4) : biểu tượng kiếm theo cấp kiếm 0..3
 *  - dart_icon      (gốc: u/wpt1)       : biểu tượng phi tiêu
 *  - cross          (gốc: u/x)          : dấu X đánh dấu trên bản đồ thế giới
 *  - world_map      (gốc: wm)           : bản đồ thế giới 240x240
 *  - icon           (gốc: icon)         : icon ứng dụng, khai báo trong META-INF/MANIFEST.MF (MIDlet-1)
 */
public final class Ui {
    public static final int ARROW_RIGHT = 0;
    public static final int ARROW_LEFT = 1;
    public static final int ARROW_DOWN = 2;
    public static final int ARROW_UP = 3;
    public static final int ARROW_COUNT = 4;

    public static final int CORNER_COUNT = 4;

    public static final int HUD_LEFT = 0;
    public static final int HUD_RIGHT = 1;
    public static final int HUD_COUNT = 2;

    public static final int LOGO_FRAME_HEIGHT = 51;

    public static final int SWORD_LEVEL_COUNT = 4;

    private Ui() {
    }

    public static Image loadArrow(int direction) throws IOException {
        return load("arrow" + direction);
    }

    public static Image loadCorner(int index) throws IOException {
        return load("corner" + index);
    }

    public static Image loadHud(int index) throws IOException {
        return load("hud" + index);
    }

    public static Image loadMarker() throws IOException {
        return load("marker");
    }

    public static Image loadLogo() throws IOException {
        return load("logo");
    }

    public static Image loadSoundIcon() throws IOException {
        return load("sound_icon");
    }

    public static Image loadSmileys() throws IOException {
        return load("smileys");
    }

    public static Image loadTitle() throws IOException {
        return load("title");
    }

    public static Image loadSwordIcon(int level) throws IOException {
        return load("sword_icon" + level);
    }

    public static Image loadDartIcon() throws IOException {
        return load("dart_icon");
    }

    public static Image loadCross() throws IOException {
        return load("cross");
    }

    public static Image loadWorldMap() throws IOException {
        return load("world_map");
    }

    private static Image load(String name) throws IOException {
        return Image.createImage(Enum.PATH_UI + name + Enum.EXTENSION_PNG);
    }
}
