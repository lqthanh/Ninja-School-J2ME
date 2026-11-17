package core.text;

/** Chữ hiển thị trong game không thuộc lời thoại: tên quái, menu ... */
public final class GameText {
    /** Tên các loại quái (chỉ số = loại quái); hiện trong thông báo tiến độ nhiệm vụ. */
    public static final String[] monsterNames = new String[]{"Ma một mắt",
            "ốc sên",
            "Dơi quỷ",
            "Quỷ một chân",
            "Cóc xanh",
            "Nhện",
            "Ong bò vẽ",
            "Bù nhìn rơm",
            "Cá cọp",
            "Ninja gió",
            "Cá cọp đỏ",
            "Ma một mắt",
            "Rết sa mạc",
            "Dơi vàng",
            "Chuột",
            "Thổ dân",
            "Ong đỏ",
            "Bò cạp",
            "Quỷ hoa",
            "Quỷ tử hoa"};

    /** Menu màn hình chính, theo thứ tự lựa chọn. */
    public static final String[] mainMenuItems = new String[]{"Chơi mới",
            "Chơi tiếp",
            "Chỉ dẫn",
            "Game khác",
            "Thoát"};

    private GameText() {
    }
}
