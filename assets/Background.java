package assets;

import javax.microedition.lcdui.Image;

public final class Background {
    private static final int LAYER_COUNT = 4;

    private Background() {
    }

    public static Image[] ensureLayers(Image[] layers, int backgroundIndex) throws Exception {
        if (layers != null) {
            return layers;
        }

        Image[] result = new Image[LAYER_COUNT];
        for (int i = 0; i < LAYER_COUNT; i++) {
            result[i] = Image.createImage(Enum.PATH_BACKGROUND + "bg" + i + backgroundIndex + Enum.EXTENSION_PNG);
        }

        return result;
    }

    public static Image ensureCloud(Image cloud, int backgroundIndex) throws Exception {
        if (cloud != null) {
            return cloud;
        }

        return Image.createImage(Enum.PATH_BACKGROUND + "cl" + backgroundIndex + Enum.EXTENSION_PNG);
    }

    public static Image ensureSun(Image sun, int backgroundIndex) throws Exception {
        if (sun != null) {
            return sun;
        }

        return Image.createImage(Enum.PATH_BACKGROUND + "sun" + backgroundIndex + Enum.EXTENSION_PNG);
    }
}
