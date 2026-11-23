package core.map;

import assets.FontGraph;
import core.render.Viewport;
import javax.microedition.lcdui.Graphics;

/** Biển chữ vẽ trên map (tên phòng, tên địa điểm...). Vị trí là tâm-dưới của chữ (pixel). */
public final class Signs {
    public static int count;
    public static String[] texts;
    /** Mép trái của chữ (đã trừ nửa chiều rộng). */
    public static int[] x;
    public static int[] y;
    public static int[] width;

    private Signs() {
    }

    /** Đặt biển chữ cho map. */
    public static void setup(int map) {
        count = 0;
        if (map == 0) {
            count = 16;
            alloc();
            place(0, "Bộ môn Khí", 1044, 336);
            place(1, "Cô Ayumi", 1044, 347);
            place(2, "Phòng chủ nhiệm", 1212, 336);
            place(3, "Thầy Takanashi", 1212, 347);
            place(4, "Phòng", 1380, 336);
            place(5, "đào tạo", 1380, 347);
            place(6, "Phòng", 1212, 216);
            place(7, "Hiệu trưởng", 1212, 227);
            place(8, "Bộ môn Lực", 1044, 216);
            place(9, "Thầy Hiroyaki", 1044, 227);
            place(10, "Bộ môn Thuật", 1380, 216);
            place(11, "Thầy Honda", 1380, 227);
            place(12, "Kí Túc Xá", 444, 341);
            place(13, "* Khu luyện tập", 72, 341);
            place(14, "Cổng /", 1860, 317);
            place(15, "Bán HP-MP", 612, 341);
        } else if (map == 1) {
            count = 2;
            alloc();
            place(0, "Trường /", 1356, 173);
            place(1, "* Thác nước", 48, 173);
        } else if (map == 2) {
            count = 4;
            alloc();
            place(0, "* Lối vào", 60, 245);
            place(1, "Trường Hirosaki", 240, 197);
            place(2, "Lối ra /", 420, 245);
            place(2, "Mua đồ tiền thật", 552, 245);
            place(3, "đường hoa đào /", 1356, 221);
        } else if (map == 3) {
            count = 2;
            alloc();
            place(0, "* Ngôi làng", 48, 173);
            place(1, "Rừng trúc /", 2808, 173);
        } else if (map == 4) {
            count = 2;
            alloc();
            place(0, "* Vườn đào", 48, 101);
            place(1, "Rừng tối", 1704, 245);
        } else if (map == 5) {
            count = 2;
            alloc();
            place(0, "Trường /", 420, 77);
            place(1, "Rừng tối /", 408, 1829);
        } else if (map == 6) {
            count = 3;
            alloc();
            place(0, "* Thác nước", 72, 101);
            place(1, "Rừng tối /", 504, 53);
            place(2, "Sông Watamoro /", 5916, 197);
        } else {
            if (map == 99) {
                count = 1;
                alloc();
                place(0, "Trường Hirosaki", 744, 173);
            }

        }
    }

    private static void place(int i, String text, int textX, int textY) {
        texts[i] = text;
        x[i] = textX;
        y[i] = textY;
        x[i] -= FontGraph.getBoldWidth(text) >> 1;
        width[i] = FontGraph.getBoldWidth(text);
    }

    public static void draw(Graphics g, int cameraX, int cameraY) {
        for(int i = 0; i < count; ++i) {
            if (x[i] + width[i] >= cameraX && x[i] <= cameraX + Viewport.viewWidth && y[i] >= cameraY && y[i] - 14 <= cameraY + Viewport.viewHeight) {
                FontGraph.drawBold(g, texts[i], x[i], y[i], 0);
            }
        }

    }

    private static void alloc() {
        texts = new String[count];
        x = new int[count];
        y = new int[count];
        width = new int[count];
    }
}
