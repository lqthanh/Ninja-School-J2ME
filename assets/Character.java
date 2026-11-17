package assets;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 * Hình ảnh nhân vật ghép từ nhiều phần thân/đầu/chân (assets/character/).
 *
 *  - hero_body{n}, hero_head{n}, hero_legs{n}  (gốc: cp/cb{n}, cp/ch{n}, cp/cl{n}) : nhân vật chính
 *  - hero_head_transform{n}                     (gốc: cp/ch{n}x)                    : đầu khi biến hình
 *  - girl_body{n}, girl_head{n}, girl_legs{n}  (gốc: cp/gb{n}, cp/gh{n}, cp/gl{n}) : cô bạn đồng hành
 *  - classmate{n}_head{frame}                   (gốc: cp/f{n}h{frame})              : đầu các bạn học
 *
 * hero_body0, hero_legs0, girl_body1 là ảnh trống 1x1 (frame không vẽ phần đó).
 */
public final class Character {
    public static final int HERO_BODY_COUNT = 20;
    public static final int HERO_HEAD_COUNT = 11;
    public static final int HERO_LEGS_COUNT = 10;
    public static final int HERO_TRANSFORM_HEAD_COUNT = 2;

    public static final int GIRL_BODY_COUNT = 8;
    public static final int GIRL_HEAD_COUNT = 3;
    public static final int GIRL_LEGS_COUNT = 9;

    public static final int CLASSMATE_HEAD_FRAME_COUNT = 2;

    private Character() {
    }

    public static Image loadHeroBody(int index) throws IOException {
        return load("hero_body" + index);
    }

    public static Image loadHeroHead(int index) throws IOException {
        return load("hero_head" + index);
    }

    public static Image loadHeroLegs(int index) throws IOException {
        return load("hero_legs" + index);
    }

    public static Image loadHeroTransformHead(int index) throws IOException {
        return load("hero_head_transform" + index);
    }

    public static Image loadGirlBody(int index) throws IOException {
        return load("girl_body" + index);
    }

    public static Image loadGirlHead(int index) throws IOException {
        return load("girl_head" + index);
    }

    public static Image loadGirlLegs(int index) throws IOException {
        return load("girl_legs" + index);
    }

    public static Image loadClassmateHead(int classmate, int frame) throws IOException {
        return load("classmate" + classmate + "_head" + frame);
    }

    private static Image load(String name) throws IOException {
        return Image.createImage(Enum.PATH_CHARACTER + name + Enum.EXTENSION_PNG);
    }
}
