package core.player;

/**
 * Chỉ số của nhân vật chính: cấp độ, kinh nghiệm, HP, MP, sức đánh.
 *
 * Tên field gốc trong b.java (nay là GameCanvas.java) (trước khi deobfuscate):
 *  level=N, exp=O, hp=H, maxHp=I, mp=J, maxMp=K, attack=F, dartDamage=G
 *
 * Thứ tự ghi/đọc trong save "ninjasave" do SaveGame.save()/SaveGame.load() quản lý,
 * không được đổi thứ tự để giữ tương thích với save cũ.
 */
public final class PlayerStats {
    // Giá trị khi khởi động ứng dụng lần đầu (Midlet.run)
    public static final int DEFAULT_ATTACK = 20;
    public static final int DEFAULT_DART_DAMAGE = 10;
    public static final int DEFAULT_MAX_HP = 200;
    public static final int DEFAULT_MAX_MP = 150;

    // Giá trị khi bắt đầu "Chơi mới" (MP tối đa khác với lần khởi động đầu)
    public static final int NEW_GAME_MAX_MP = 200;

    /** Khi hồi sinh sau khi chết: hồi 1/REVIVE_DIVISOR HP/MP tối đa. */
    private static final int REVIVE_DIVISOR = 3;

    public static int level;
    public static int exp;

    public static int hp;
    public static int maxHp;
    public static int mp;
    public static int maxMp;

    /** Sức đánh cơ bản (kiếm). */
    public static int attack;
    /** Sát thương phi tiêu. */
    public static int dartDamage;

    private PlayerStats() {
    }

    // =====================================================================================
    // #region Init

    /** Khởi tạo chỉ số lần đầu chạy ứng dụng. */
    public static void initDefaults() {
        attack = DEFAULT_ATTACK;
        dartDamage = DEFAULT_DART_DAMAGE;
        maxHp = DEFAULT_MAX_HP;
        hp = DEFAULT_MAX_HP;
        maxMp = DEFAULT_MAX_MP;
        mp = DEFAULT_MAX_MP;
        level = LevelTable.MIN_LEVEL;
        exp = 0;
    }

    /**
     * Reset khi bắt đầu chơi mới.
     * Lưu ý: bản gốc KHÔNG reset dartDamage ở đây.
     */
    public static void resetForNewGame() {
        hp = DEFAULT_MAX_HP;
        maxHp = DEFAULT_MAX_HP;
        mp = NEW_GAME_MAX_MP;
        maxMp = NEW_GAME_MAX_MP;
        exp = 0;
        level = LevelTable.MIN_LEVEL;
        attack = DEFAULT_ATTACK;
    }

    /** Đặt HP/MP tối đa và hồi đầy. */
    public static void setMaxHpMp(int newMaxHp, int newMaxMp) {
        maxMp = newMaxMp;
        mp = newMaxMp;
        maxHp = newMaxHp;
        hp = newMaxHp;
    }

    // #endregion
    // =====================================================================================
    // #region HP

    /** Trừ HP. Trả về true nếu nhân vật hết HP (HP được kẹp về 0). */
    public static boolean takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            return true;
        }

        return false;
    }

    /** Chết ngay lập tức (rơi xuống vực, bị đè...). */
    public static void kill() {
        hp = 0;
    }

    /** Hồi HP, không vượt quá HP tối đa. */
    public static void restoreHp(int amount) {
        hp += amount;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    public static boolean isHpFull() {
        return hp >= maxHp;
    }

    // #endregion
    // =====================================================================================
    // #region MP

    /** Tiêu hao MP. Trả về true nếu MP cạn (MP được kẹp về 0). */
    public static boolean drainMp(int amount) {
        mp -= amount;
        if (mp <= 0) {
            mp = 0;
            return true;
        }

        return false;
    }

    /** Hồi MP, không vượt quá MP tối đa. */
    public static void restoreMp(int amount) {
        mp += amount;
        if (mp > maxMp) {
            mp = maxMp;
        }
    }

    public static boolean isMpFull() {
        return mp >= maxMp;
    }

    public static boolean hasMp() {
        return mp > 0;
    }

    // #endregion
    // =====================================================================================
    // #region Revive

    /** Hồi sinh: HP/MP về 1/3 tối đa. */
    public static void revive() {
        hp = maxHp / REVIVE_DIVISOR;
        mp = maxMp / REVIVE_DIVISOR;
    }

    // #endregion
    // =====================================================================================
    // #region Attack

    /**
     * Sát thương một nhát kiếm.
     *
     * @param seed        số ngẫu nhiên/bộ đếm frame (GameState.tick)
     * @param weaponLevel cấp kiếm đã mua (Inventory.swordLevel)
     */
    public static int rollMeleeDamage(int seed, int weaponLevel) {
        int damage = attack + seed % (attack >> 1);
        return damage + (damage >> 1) * weaponLevel;
    }

    /** Sức đánh hiển thị trên giao diện (thông báo lên cấp). */
    public static int getDisplayedAttack(int weaponLevel) {
        return attack + (attack >> 2) * (weaponLevel + 1);
    }

    // #endregion
    // =====================================================================================
    // #region HUD

    public static int hpBarWidth(int fullWidth) {
        return hp * fullWidth / maxHp;
    }

    public static int mpBarWidth(int fullWidth) {
        return mp * fullWidth / maxMp;
    }

    // #endregion
    // =====================================================================================
}
