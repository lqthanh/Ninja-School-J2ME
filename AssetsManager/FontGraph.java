package AssetsManager;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class FontGraph {
    private static final String SMALL_CHARS = "0123456789+-%$:abcdefghijklmnopqrstuvwxyz";
    private static final int[] SMALL_WIDTHS = new int[]{5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 6, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5};
    private static final int SMALL_HEIGHT = 8;

    private static final String REGULAR_CHARS = " 0123456789.,:!?()-'/ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzáàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđĐ";
    private static final int[] REGULAR_WIDTHS = new int[]{2, 6, 3, 5, 6, 6, 6, 5, 6, 6, 6, 3, 3, 3, 2, 6, 4, 4, 5, 3, 6, 8, 8, 8, 8, 8, 8, 8, 8, 3, 8, 8, 8, 10, 8, 8, 8, 8, 8, 7, 8, 8, 8, 10, 8, 8, 8, 6, 6, 6, 6, 6, 5, 6, 6, 3, 5, 6, 3, 8, 6, 6, 6, 6, 6, 6, 5, 6, 6, 8, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 3, 3, 3, 3, 3, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
    private static final int REGULAR_HEIGHT = 12;

    private static final String BOLD_CHARS = " 0123456789.,:!?()+-*/#$%abcdefghijklmnopqrstuvwxyzáàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int[] BOLD_WIDTHS = new int[]{3, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 4, 3, 3, 3, 6, 7, 7, 8, 5, 11, 11, 7, 6, 10, 6, 7, 5, 7, 6, 5, 7, 7, 3, 3, 6, 3, 9, 7, 7, 7, 7, 4, 5, 4, 8, 6, 9, 7, 6, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 8, 7, 8, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 4, 3, 3, 3, 3, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 7, 7, 8, 7, 8, 8, 8, 7, 7, 8, 7, 6, 7, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 7, 8, 7, 7, 8, 6, 6, 8, 8, 3, 6, 7, 6, 11, 9, 8, 7, 8, 7, 5, 6, 8, 7, 11, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 6, 7, 6, 6, 6, 6, 5, 5, 5, 6, 5, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 7, 7, 7, 7, 7, 9};
    private static final int BOLD_HEIGHT = 13;

    private static Image[] smallFonts;
    private static Image regularFont;
    private static Image boldFont;

    static {
        try {
            smallFonts = new Image[3];
            for (int i = 0; i < 3; i++) {
                smallFonts[i] = Image.createImage(Enum.PATH_FONT + "fs" + i + Enum.EXTENSION_PNG);
            }
        } catch (Exception ex) {
        }

        try {
            regularFont = Image.createImage(Enum.PATH_FONT + "fr" + Enum.EXTENSION_PNG);
        } catch (Exception ex) {
        }

        try {
            boldFont = Image.createImage(Enum.PATH_FONT + "fb" + Enum.EXTENSION_PNG);
        } catch (Exception ex) {
        }
    }

    private FontGraph() {
    }

    public static void init() {
    }

    public static void drawBold(Graphics g, String text, int x, int y, int align) {
        int drawX;
        if (align == 0) {
            drawX = x;
        } else if (align == 1) {
            drawX = x - getBoldWidth(text);
        } else {
            drawX = x - (getBoldWidth(text) >> 1);
        }

        int length = text.length();
        for (int i = 0; i < length; i++) {
            int index = BOLD_CHARS.indexOf(text.charAt(i));
            if (index == -1) {
                index = 0;
            }

            g.drawRegion(boldFont, 0, index * BOLD_HEIGHT + 1, BOLD_WIDTHS[index], BOLD_HEIGHT - 1, 0, drawX, y, 20);
            drawX += BOLD_WIDTHS[index];
        }
    }

    public static void drawRegular(Graphics g, String text, int x, int y, int align) {
        int drawX = x - (getRegularWidth(text) >> 1);
        // if (align == 0) {
        //     drawX = x;
        // } else if (align == 1) {
        //     drawX = x - getRegularWidth(text);
        // } else {
        //     drawX = x - (getRegularWidth(text) >> 1);
        // }

        int length = text.length();
        for (int i = 0; i < length; i++) {
            int index = REGULAR_CHARS.indexOf(text.charAt(i));
            if (index == -1) {
                index = 0;
            }

            g.drawRegion(regularFont, 0, index * REGULAR_HEIGHT + 1, REGULAR_WIDTHS[index], REGULAR_HEIGHT - 1, 0, drawX, y, 20);
            drawX += REGULAR_WIDTHS[index];
        }
    }

    public static void drawSmall(Graphics g, String text, int fontIndex, int x, int y, int align) {
        int drawX;
        if (align == 0) {
            drawX = x;
        } else if (align == 1) {
            drawX = x - getSmallWidth(text);
        } else {
            drawX = x - (getSmallWidth(text) >> 1);
        }

        int length = text.length();
        for (int i = 0; i < length; i++) {
            int index = SMALL_CHARS.indexOf(text.charAt(i));
            if (index == -1) {
                index = 0;
            }

            g.drawRegion(smallFonts[fontIndex], 0, index * SMALL_HEIGHT, SMALL_WIDTHS[index], SMALL_HEIGHT, 0, drawX, y, 20);
            drawX += SMALL_WIDTHS[index] - 1;
        }
    }

    public static int getBoldWidth(String text) {
        int width = 0;
        for (int i = 0; i < text.length(); i++) {
            int index = BOLD_CHARS.indexOf(text.charAt(i));
            if (index == -1) {
                index = 0;
            }

            width += BOLD_WIDTHS[index];
        }

        return width;
    }

    public static int getRegularWidth(String text) {
        int width = 0;
        for (int i = 0; i < text.length(); i++) {
            int index = REGULAR_CHARS.indexOf(text.charAt(i));
            if (index == -1) {
                index = 0;
            }

            width += REGULAR_WIDTHS[index];
        }

        return width;
    }

    public static int getSmallWidth(String text) {
        int width = 0;
        for (int i = 0; i < text.length(); i++) {
            int index = SMALL_CHARS.indexOf(text.charAt(i));
            if (index == -1) {
                index = 0;
            }

            width += SMALL_WIDTHS[index] + 1;
        }

        return width;
    }
}
