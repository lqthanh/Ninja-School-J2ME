package core.story;

import core.app.App;
import core.monster.BoarBoss;
import core.monster.FinalBoss;
import core.monster.RiverMonster;
import core.npc.Toniku;
import core.player.Player;
import core.quest.Story;
import core.render.Camera;
import core.render.Viewport;
import javax.microedition.lcdui.Display;

/**
 * Sự kiện của map: dãy bước chờ điều kiện rồi phát cutscene. Mỗi map có sự kiện dùng
 * chỉ số {@link #current}; trình chạy là {@link #update()}.
 */
public final class MapEvent {
    /** Chờ nhân vật đi qua x = {@link #paramX}. */
    public static final int WAIT_PLAYER_PAST_X = 1;
    /** Phát ngay. */
    public static final int IMMEDIATE = 2;
    /** Hết sự kiện. */
    public static final int END = 3;
    /** Chờ nhân vật ra khỏi mép phải màn hình. */
    public static final int WAIT_PLAYER_OFFSCREEN_RIGHT = 4;
    /** Chờ boss lợn rừng bị hạ. */
    public static final int WAIT_BOAR_BOSS_DEFEATED = 5;
    /**
     * Chờ nhân vật đứng trong khoảng (paramX - 96, paramX) và ở đúng y = {@link #paramY}.
     *
     * <p>Chỉ sự kiện 4 dùng điều kiện này: về map 0 cùng em gái cô Ayumi, tới chỗ thầy
     * (x 1152..1248, y 408) thì phát cutscene 9 (khen, cô bé đứng lại, nhận bằng lái mô tô).
     * Bản gốc thiếu break nên rơi xuống {@link #WAIT_BOAR_BOSS_DEFEATED}; vì trạng thái boss
     * (BoarBoss.state) không được reset khi đổi map, cảnh thưởng thường phát ngay khi vào map 0. Đã sửa:
     * thêm return và reset BoarBoss.state khi nạp map.</p>
     */
    public static final int WAIT_PLAYER_AT = 6;
    /** Chờ thủy quái chìm xuống (bị hạ). */
    public static final int WAIT_RIVER_MONSTER_SUNK = 7;
    /** Chờ Toniku còn dưới 10 HP. */
    public static final int WAIT_TONIKU_DEFEATED = 8;
    /** Chờ boss cuối bị hạ (cứu được Sakura). */
    public static final int WAIT_FINAL_BOSS_DEFEATED = 9;
    private static final int[][] conditions = new int[][]{{2, 1, 1, 5, 3}, {2, 4, 3}, {2, 3}, {2, 1, 3}, {6, 3}, {2, 7, 3}, {2, 8, 3}, {2, 9, 3}, {2, 3}, {1, 3}, {2, 3}};
    /** Cutscene phát ở mỗi bước. */
    private static final int[][] cutscenes = new int[][]{{0, 1, 2, 5, 0}, {3, 4, 0}, {6, 0}, {7, 8, 0}, {9, 0}, {10, 11, 0}, {12, 13, 0}, {14, 15, 0}, {16, 0}, {17, 0}, {18, 0}};
    private static final int[][] paramX = new int[][]{{0, 360, 1128, 0, 0}, new int[3], new int[2], {0, 2784, 0}, {1248, 0}, new int[3], new int[3], new int[3], new int[2], new int[2], new int[2]};
    private static final int[][] paramY = new int[][]{new int[5], new int[3], new int[2], new int[3], {408, 0}, new int[3], new int[3], new int[3], new int[2], new int[2], new int[2]};
    public static int current = 10;
    public static boolean active;
    public static int step;

    private MapEvent() {
    }

    public static void start(int id) {
        step = 0;
        current = id;
        active = true;
    }

    public static void update() {
        if (active) {
            switch (conditions[current][step]) {
                case WAIT_PLAYER_PAST_X:
                    if (Player.x > paramX[current][step]) {
                    Cutscene.start(cutscenes[current][step]);
                    ++step;
                    return;
                }
                break;
                case IMMEDIATE:
                    Cutscene.start(cutscenes[current][step]);
                    ++step;
                    return;
                case END:
                default:
                    return;
                case WAIT_PLAYER_OFFSCREEN_RIGHT:
                    if (Player.x > Camera.x + Viewport.viewWidth) {
                        Cutscene.start(cutscenes[current][step]);
                        ++step;
                        return;
                    }
                    break;
                case WAIT_PLAYER_AT:
                    if (Player.x < paramX[current][step] && Player.x > paramX[current][step] - 96 && Player.y == paramY[current][step]) {
                        Cutscene.start(cutscenes[current][step]);
                        ++step;
                    }

                    // Bản gốc thiếu lệnh này nên rơi xuống WAIT_BOAR_BOSS_DEFEATED.
                    return;
                case WAIT_BOAR_BOSS_DEFEATED:
                    if (BoarBoss.state == BoarBoss.STATE_GONE) {
                        Cutscene.start(cutscenes[current][step]);
                        ++step;
                        return;
                    }
                    break;
                case WAIT_RIVER_MONSTER_SUNK:
                    if (RiverMonster.state == RiverMonster.STATE_SUNK) {
                        Cutscene.start(cutscenes[current][step]);
                        ++step;
                        return;
                    }
                    break;
                case WAIT_TONIKU_DEFEATED:
                    if (Toniku.hp < 10) {
                        Cutscene.start(cutscenes[current][step]);
                        ++step;
                        return;
                    }
                    break;
                case WAIT_FINAL_BOSS_DEFEATED:
                    if (FinalBoss.state == FinalBoss.STATE_DEFEATED) {
                        Cutscene.start(cutscenes[current][step]);
                        Story.sakuraFound = true;
                        ++step;
                        return;
                    }
            }
        } else if (current == 10) {
            Display.getDisplay(App.midlet).setCurrent(App.canvas);
            current = 0;
        }

    }
}
