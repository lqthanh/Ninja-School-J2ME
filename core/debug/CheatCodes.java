package core.debug;

import core.input.Input;
import core.item.Inventory;
import core.player.PlayerStats;
import core.quest.Skills;
import core.save.PlayTime;
import core.ui.Notice;
import core.ui.WorldMap;

/**
 * Mã cheat để thử nghiệm: gõ dãy số rồi nhấn phím #.
 *
 * <pre>
 * 1984  học tất cả kỹ năng
 * 30538 HP, MP tối đa 500000
 * 38305 sức đánh 10000
 * 1982  +100000 điểm kinh nghiệm
 * 1983  +50000 tiền
 * 1981  mở tất cả vùng trên bản đồ thế giới
 * 8463  xem tổng thời gian chơi
 * </pre>
 */
public final class CheatCodes {
    /** Kết quả của {@link #onKeyPressed}: không phải phím #. */
    public static final int RESULT_NOT_TRIGGERED = -1;
    /** Đã nhấn # (mã hợp lệ đã được áp dụng, hoặc không có mã nào khớp). */
    public static final int RESULT_DONE = 0;
    /** Mã 1982: b cần gọi gainExp({@link #EXP_BONUS}). */
    public static final int RESULT_GAIN_EXP = 1;

    public static final int EXP_BONUS = 100000;
    private static final int MAX_LENGTH = 10;

    private static String buffer = "";

    private CheatCodes() {
    }

    /** Gọi mỗi khi nhấn phím (mã phím gốc, chưa chuẩn hóa). */
    public static int onKeyPressed(int keyCode) {
        int result = RESULT_NOT_TRIGGERED;
        if (keyCode == Input.CODE_POUND) {
            result = apply(buffer);
            buffer = "";
        }

        if (keyCode >= Input.CODE_NUM0 && keyCode <= Input.CODE_NUM9 && (buffer = buffer + (char) keyCode).length() > MAX_LENGTH) {
            buffer = "";
        }

        return result;
    }

    private static int apply(String code) {
        if (code.equals("1984")) {
            Skills.lightness = true;
            Skills.doubleJump = true;
            Skills.hide = true;
            Skills.burrow = true;
            Skills.dive = true;
            Skills.motorbikeLicense = true;
            Skills.darts = true;
            Skills.runOnWater = true;
            Skills.transform = true;
            Skills.tripleJump = true;
            Notice.show("Learned All Skill");
        } else if (code.equals("30538")) {
            PlayerStats.setMaxHpMp(500000, 500000);
            Notice.show("Unlimit HP MP");
        } else if (code.equals("38305")) {
            PlayerStats.attack = 10000;
            Notice.show("Unlimit Damage");
        } else if (code.equals("1982")) {
            return RESULT_GAIN_EXP;
        } else if (code.equals("1983")) {
            Inventory.money += 50000;
            Notice.show("Money+=50000");
        } else if (code.equals("1981")) {
            for (int i = 0; i < WorldMap.AREA_COUNT; ++i) {
                WorldMap.visited[i] = true;
            }

            Notice.show("Unlock all map!");
        } else if (code.equals("8463")) {
            int seconds = PlayTime.totalSeconds();
            Notice.show(seconds / 3600 + ":" + seconds % 3600 / 60 + ":" + seconds % 60);
        }

        return RESULT_DONE;
    }
}
