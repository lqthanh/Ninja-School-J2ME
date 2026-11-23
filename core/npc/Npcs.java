package core.npc;

import assets.Npc;
import core.map.GameMap;
import core.render.Viewport;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * NPC đứng yên (thầy cô, dân làng...) mà nhân vật nói chuyện để nhận nhiệm vụ.
 * Lời thoại theo NPC nằm trong GameCanvas.run() (xử lý phím nói chuyện).
 */
public final class Npcs {
    public static int count;
    /** Loại NPC (chỉ số trong {@link #sprites}). */
    public static int[] types;
    /** Vị trí chân (pixel). */
    public static int[] x;
    public static int[] y;
    /** Độ cao của thân so với chân, theo loại. */
    public static int[] bodyOffsetY = new int[]{7, 6, 6, 5, 2, 4};
    /** Ảnh theo loại và phần (Npc.PART_LEGS / PART_BODY). */
    public static Image[][] sprites;

    private Npcs() {
    }

    /** Đặt NPC cho map. */
    public static void setup(int map) {
        count = 0;
        if (map == 0) {
            sprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(sprites, 0);
                Npc.loadSprite(sprites, 1);
                Npc.loadSprite(sprites, 2);
                Npc.loadSprite(sprites, 3);
                Npc.loadSprite(sprites, 4);
                Npc.loadSprite(sprites, 5);
            } catch (Exception e) {
            }

            count = 7;
            alloc();
            place(0, 0, 34, 13);
            place(1, 1, 43, 17);
            place(2, 2, 50, 17);
            place(3, 3, 57, 12);
            place(4, 4, 43, 12);
            place(5, 5, 77, 15);
            place(6, 5, 25, 17);
        }

        if (map == 1) {
            sprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(sprites, 5);
            } catch (Exception e) {
            }

            count = 1;
            alloc();
            place(0, 5, 54, 9);
        }

        if (map == 2) {
            sprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(sprites, 5);
            } catch (Exception e) {
            }

            count = 1;
            alloc();
            place(0, 5, 23, 12);
        }

        if (map == 5) {
            sprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(sprites, 5);
            } catch (Exception e) {
            }

            count = 1;
            alloc();
            place(0, 5, 12, 5);
        }

        if (map == 6) {
            sprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(sprites, 5);
            } catch (Exception e) {
            }

            count = 1;
            alloc();
            place(0, 5, 14, 9);
        }

        if (map == 99 || map == 101) {
            sprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(sprites, 4);
            } catch (Exception e) {
            }

            count = 1;
            alloc();
            place(0, 4, 3, 12);
        }

        if (map == 94) {
            sprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(sprites, 0);
                Npc.loadSprite(sprites, 4);
                Npc.loadSprite(sprites, 2);
            } catch (Exception e) {
            }

            count = 3;
            alloc();
            place(0, 4, 2, 10);
            place(1, 0, 3, 10);
            place(2, 2, 4, 10);
        }

    }

    private static void place(int i, int type, int tileX, int tileY) {
        types[i] = type;
        x[i] = tileX * GameMap.TILE_SIZE + 12;
        y[i] = tileY * GameMap.TILE_SIZE;
    }

    private static void alloc() {
        x = new int[count];
        y = new int[count];
        types = new int[count];
    }

    public static void draw(Graphics g, int cameraX, int cameraY, int tick) {
        for(int i = 0; i < count; ++i) {
            if (x[i] + 10 >= cameraX && x[i] - 10 <= cameraX + Viewport.viewWidth && y[i] >= cameraY && y[i] - 32 <= cameraY + Viewport.viewHeight) {
                g.drawImage(sprites[types[i]][Npc.PART_LEGS], x[i], y[i], 33);
                g.drawImage(sprites[types[i]][Npc.PART_BODY], x[i], y[i] - bodyOffsetY[types[i]] + (tick % (15 + types[i]) > 4 ? 0 : 1), 33);
            }
        }

    }
}
