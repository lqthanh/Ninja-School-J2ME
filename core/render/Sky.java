package core.render;

import assets.Background;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Nền trời phía sau map: màu trời, mặt trời, 2 đám mây trôi và 4 lớp cảnh parallax
 * (assets/background, nạp bởi {@link Background}).
 *
 * Lớp cảnh chỉ hiện khi chất lượng hình ảnh cao và không phải map 0 (trường học).
 * Mỗi lớp lặp ngang theo chiều rộng ảnh và cuộn chậm hơn camera:
 *  - layers[0] ở layer0Y, cuộn 1/2 tốc độ camera, ô lặp 24px
 *  - layers[1] ở layer1Y, cuộn 1/4, ô lặp 24px
 *  - layers[2] ở layer2Y, cuộn 1/8, ô lặp 192px (vẽ sau cùng, đè lên mặt trời và mây)
 *  - layers[3] ở layer3Y, cuộn 1/16, ô lặp 64px
 */
public final class Sky {
    /** Màu trời theo bộ nền: 0 = ban ngày, 1 = ban đêm. */
    private static final int[] SKY_COLORS = new int[]{5614318, 18};
    public static final int CLOUD_COUNT = 2;

    /** Có vẽ 4 lớp cảnh parallax (false: chỉ tô màu trời, mặt trời, mây). */
    private static boolean parallaxVisible;
    private static int skyColor;
    /** Chiều cao vùng tô màu trời khi có lớp cảnh. */
    private static int skyHeight;
    private static Image[] layers;
    private static Image cloud;
    private static Image sun;
    private static int layer0Y;
    private static int layer1Y;
    private static int layer2Y;
    private static int layer3Y;
    /** Tâm mặt trời. */
    private static int sunX;
    private static int sunY;
    /** Tâm từng đám mây. */
    private static int[] cloudX;
    private static int[] cloudY;

    private Sky() {
    }

    /**
     * Nạp ảnh và tính vị trí các lớp cho map vừa vào.
     * Lưu ý (giữ như bản gốc): khi không có lớp cảnh, các vị trí cũ vẫn được cộng thêm
     * verticalOffset.
     */
    public static void setup(int backgroundIndex, int mapId, int viewWidth, int viewHeight, boolean lowGraphics) {
        skyColor = SKY_COLORS[backgroundIndex];

        int verticalOffset;
        try {
            if (!lowGraphics) {
                layers = Background.ensureLayers(layers, backgroundIndex);
                cloud = Background.ensureCloud(cloud, backgroundIndex);
                sun = Background.ensureSun(sun, backgroundIndex);
            }
        } catch (Exception ex) {
        }

        parallaxVisible = false;
        if (mapId != 0 && !lowGraphics) {
            parallaxVisible = true;
            skyHeight = viewHeight - (layers[0].getHeight() + layers[1].getHeight() + layers[2].getHeight()) + 11;
            layer2Y = (layer1Y = (layer0Y = viewHeight - layers[0].getHeight()) - layers[1].getHeight()) - layers[2].getHeight();
            layer3Y = layer1Y - layers[3].getHeight() - 10;
        }

        if ((verticalOffset = 2 * viewHeight / 3 - layer1Y) < 0) {
            verticalOffset = 0;
        }

        skyHeight += verticalOffset;
        layer0Y += verticalOffset;
        layer1Y += verticalOffset;
        layer2Y += verticalOffset;
        layer3Y += verticalOffset;
        sunX = 3 * viewWidth / 4;
        sunY = skyHeight / 3;
        cloudX = new int[CLOUD_COUNT];
        cloudY = new int[CLOUD_COUNT];
        cloudX[0] = viewWidth / 3;
        cloudY[0] = skyHeight / 2 - 8;
        cloudX[1] = 2 * viewWidth / 3;
        cloudY[1] = skyHeight / 2 + 8;
    }

    /** Mây trôi sang phải, ra khỏi màn hình thì quay lại từ mép trái. */
    public static void updateClouds(int tick, int viewWidth) {
        for (int i = 0; i < CLOUD_COUNT; ++i) {
            if (tick % (i + 2 << 3) == 0) {
                ++cloudX[i];
                if (cloudX[i] > viewWidth + (cloud.getWidth() >> 1)) {
                    cloudX[i] = -(cloud.getWidth() >> 1);
                }
            }
        }
    }

    public static void draw(Graphics g, int cameraX, int viewWidth, int viewHeight, boolean lowGraphics) {
        int x;
        if (parallaxVisible && !lowGraphics) {
            g.setColor(skyColor);
            g.fillRect(0, 0, viewWidth, skyHeight);

            for(x = -((cameraX >> 1) % 24); x < viewWidth; x += 24) {
                g.drawImage(layers[0], x, layer0Y, 0);
            }

            for(x = -((cameraX >> 2) % 24); x < viewWidth; x += 24) {
                g.drawImage(layers[1], x, layer1Y, 0);
            }

            for(x = -((cameraX >> 4) % 64); x < viewWidth; x += 64) {
                g.drawImage(layers[3], x, layer3Y, 0);
            }

            g.drawImage(sun, sunX, sunY, 3);

            for(x = 0; x < 2; ++x) {
                g.drawImage(cloud, cloudX[x], cloudY[x], 3);
            }

            for(x = -((cameraX >> 3) % 192); x < viewWidth; x += 192) {
                g.drawImage(layers[2], x, layer2Y, 0);
            }

        } else {
            g.setColor(skyColor);
            g.fillRect(0, 0, viewWidth, viewHeight);
            if (!lowGraphics) {
                g.drawImage(sun, sunX, sunY, 3);

                for(x = 0; x < 2; ++x) {
                    g.drawImage(cloud, cloudX[x], cloudY[x], 3);
                }

            }
        }
    }
}
