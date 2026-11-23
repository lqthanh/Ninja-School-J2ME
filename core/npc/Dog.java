package core.npc;

import assets.Npc;
import javax.microedition.lcdui.Image;

/** Con chó chạy qua lại ở map 0 (trường). */
public final class Dog {
    public static boolean visible;
    public static int x;
    public static int y;
    /** Vận tốc ngang (1 hoặc -1), đổi chiều khi chạm {@link #minX}/{@link #maxX}. */
    public static int vx;
    public static int minX;
    public static int maxX;
    public static Image[] sprites;

    private Dog() {
    }

    public static void setup(int mapId) {
        visible = false;
        if (mapId == 0) {
            sprites = Npc.ensureDog(sprites);

            visible = true;
            x = 348;
            y = 408;
            vx = 1;
            minX = 252;
            maxX = 708;
        }

    }
}
