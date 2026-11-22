package core.monster;

/** Chỉ số của 20 loại quái (chỉ số = mã quái, tên ở GameText.monsterNames). */
public final class MonsterStats {
    public static final int TYPE_COUNT = 20;

    public static final int[] maxHp = new int[]{140, 70, 150, 120, 150, 150, 60, 1000, 250, 500, 4000, 500, 600, 1000, 1500, 2000, 1000, 3500, 1500, 5500};
    /** Sát thương gây cho nhân vật; bình thuốc rơi ra hồi 4 lần giá trị này. */
    public static final int[] damage = new int[]{15, 10, 30, 25, 25, 25, 5, 10, 20, 40, 30, 40, 130, 30, 40, 100, 60, 160, 120, 200};
    /** Tiền rơi ra (nhân 8). */
    public static final int[] money = new int[]{35, 20, 55, 40, 45, 50, 30, 130, 60, 65, 70, 75, 120, 85, 100, 120, 140, 160, 180, 200};
    /** Điểm kinh nghiệm khi hạ. */
    public static final int[] exp = new int[]{20, 10, 50, 25, 30, 40, 15, 50, 60, 70, 80, 100, 120, 130, 160, 180, 170, 210, 190, 220};

    private MonsterStats() {
    }
}
