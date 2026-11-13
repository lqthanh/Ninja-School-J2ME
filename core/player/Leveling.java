package core.player;

/**
 * Cơ chế kinh nghiệm và lên cấp của nhân vật.
 *
 * Quy tắc gốc:
 *  - Khi exp tích luỹ VƯỢT (>) mốc của cấp hiện tại -> lên 1 cấp, exp về 0
 *    (phần exp dư bị bỏ, mỗi lần nhận exp chỉ lên tối đa 1 cấp).
 *  - Lên cấp: +sức đánh theo bảng, +2 phi tiêu, +HP tối đa và +MP tối đa
 *    theo bảng, hồi đầy HP và MP.
 *    (Bản gốc không tăng MP tối đa; MAX_MP_GAIN là cơ chế mới.)
 *  - Bị trọng thương (chết): mất 1/2 exp hiện tại.
 */
public final class Leveling {
    private Leveling() {
    }

    /**
     * Cộng exp cho nhân vật.
     *
     * @return true nếu nhân vật vừa lên cấp
     */
    public static boolean addExp(int amount) {
        if (isMaxLevel()) {
            // Bản gốc không có giới hạn -> crash; ở cấp tối đa chỉ giữ thanh exp đầy.
            int cap = LevelTable.expToNextLevel(PlayerStats.level);
            PlayerStats.exp += amount;
            if (PlayerStats.exp > cap || PlayerStats.exp < 0) {
                PlayerStats.exp = cap;
            }
            return false;
        }

        PlayerStats.exp += amount;
        if (PlayerStats.exp > LevelTable.expToNextLevel(PlayerStats.level)) {
            levelUp();
            return true;
        }

        return false;
    }

    private static void levelUp() {
        PlayerStats.exp = 0;
        PlayerStats.level++;

        int newLevel = PlayerStats.level;
        PlayerStats.attack += LevelTable.attackGain(newLevel);
        PlayerStats.dartDamage += LevelTable.DART_DAMAGE_GAIN;
        PlayerStats.maxHp += LevelTable.maxHpGain(newLevel);
        PlayerStats.maxMp += LevelTable.maxMpGain(newLevel);
        PlayerStats.hp = PlayerStats.maxHp;
        PlayerStats.mp = PlayerStats.maxMp;
    }

    public static boolean isMaxLevel() {
        return PlayerStats.level >= LevelTable.MAX_LEVEL;
    }

    /** Phạt khi bị trọng thương: mất một nửa exp. */
    public static void applyDeathPenalty() {
        PlayerStats.exp >>= 1;
    }

    /** Độ dài thanh exp trên HUD. */
    public static int expBarWidth(int fullWidth) {
        return PlayerStats.exp * fullWidth / LevelTable.expToNextLevel(PlayerStats.level);
    }
}
