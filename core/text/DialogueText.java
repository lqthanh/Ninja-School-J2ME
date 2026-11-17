package core.text;

/**
 * Toàn bộ lời thoại của nhân vật trong game.
 *
 * Mỗi đoạn hội thoại là một mảng câu; bong bóng lời thoại hiện lần lượt từng câu
 * (xem core.ui.Dialogue). Bảng 2 chiều: chỉ số thứ nhất chọn đoạn hội thoại.
 */
public final class DialogueText {
    // =====================================================================================
    // #region Thầy cô (hội thoại "Giao tiếp")

    /** Thầy cô giao nhiệm vụ (hoặc hướng dẫn trước khi giao). */
    public static final String[][] questGiveLines = new String[][]{{"Con lại tìm ta có chuyện gì?",
                "Thầy chủ nhiệm Con là Takanashi, mau đến gặp thầy để nhận bài tập."},
            {"Chào cậu bé, ta là Kagome Ayumi, giảng viên cao cấp bộ môn Khí.",
                "Khí là môn đầu tiên bất kỳ học sinh nào cũng phải học khi bước vào đây.",
                "Bài học đâu tiên là kỹ năng khinh công. Sau khi học xong con sẽ phi thân cao hơn  gấp đôi bình thường.",
                "Bây giờ ta phải kiểm tra sơ bộ con. Hãy chạy thẳng về tay trái đến khu luyện tập.",
                "đến đó giết đủ 10 con ốc sên đá, sau đó quay lại đây!"},
            {"Chào con, con đã sẵn sàng luyện tập rồi chứ?",
                "Hãy đến gặp cô Ayumi để học môn đầu tiên.",
                "Cô ấy đứng đằng kia, chúc con học tốt nhé!",
                "à, nếu cô ấy có hỏi gì về ta, hãy nói tốt về ta nhé, ta sẽ thưởng."},
            {"Ta già cả rồi, tựa như chiếc lá khô..",
                "Lại đây, ta sẽ truyền hết võ công cho con...",
                "Toyotomi,...tình yêu của ta,....em ở nơi nào"},
            {"Trình độ nhà ngươi còn quá thấp. Hãy quay lại đây khi đã học xong khinh công."},
            {"Ngươi còn chưa dùng shuriken thì làm sao học phép thuật được."},
            {"Ta sẽ dạy ngươi dùng shuriken nếu ngươi có đủ khả năng.",
                "Gần cổng trường có vài cái bù nhìn rơm, hãy đánh gãy 2 cái.",
                "Nhanh lên, hãy chứng tỏ sức mạnh của mình đi."},
            {"Ngươi còn không mau đi đi!"},
            {"Ngươi muốn học thuật ư?",
                "Không như những môn khác, thuật đòi hỏi ngươi phải luyện tập hàng ngày..",
                "Hơn thế nữa, ngươi phải học theo những quyển bí kíp.",
                "Ta sẽ dạy cho ngươi môn độn thổ, nếu ngươi có thể tìm được 1 quyển bí kíp từ bọn quỷ một chân",
                "Bọn chúng thường xuất hiện ở thác nước, bên kia khu luyện tập.",
                "Đi đi, hãy kiên nhẫn với từng tên , ngươi sẽ tìm thấy thôi!"},
            {"Nhanh lên con, cô bé ấy đang đứng chờ ở đường hoa đào đấy!",
                "Nếu chậm, cô Ayumi sẽ buồn lắm đó!"},
            {"Con có thể giúp ta một chuyện được không?",
                "Cô Ayumi có nhờ ta đón giúp cô em gái. Nhưng đúng vào lúc ta phải họp cùng thầy hiệu trưởng.",
                "Ta rất tin tưởng ở con, con hãy đến đầu đường hoa đào, đón cô ấy nhé!",
                "Nhanh lên, ta sẽ thưởng cho con nếu con làm tốt."},
            {"Để trở thành 1 ninja giỏi ngươi phải có thể lực tốt.",
                "Ngươi có biết chữ Nin là Nhẫn không!",
                "Trong Nhẫn thì có Lực và Tâm..",
                "..để trở thành ninja thực thụ thì ngươi phải có Tâm và Lực",
                "....mà ta nói, ngươi có hiểu gì không.",
                "Hãy giết 20 con ong, rồi quay về gặp ta. Lúc đó ta sẽ giúp ngươi."},
            {"Ngươi đã bao giờ vượt sông Watamoro chưa? Đó là một con sông rộng và hung dữ với thuỷ quái và sóng to.",
                "Ta sẽ giúp ngươi 1 cách vượt sông rất dễ dàng, nhưng ngươi phải làm một việc...",
                "Trong khu rừng trúc, có 1 con heo rừng thành tinh rất hung hăng..",
                "Hãy giết nó, mang về đây 1 quyển bí kíp nói về thuật lặn nước, ta sẽ hướng dẫn ngươi luyện.",
                "Đi đi, và hãy cẩn thận tính mạng nhà ngươi."},
            {"Ngươi đã học được kỹ năng chạy nước rồi!",
                "Nếu ngươi có thể qua bên kia con sông,đến khu nghĩa địa, nhặt quyển bí kíp Ninja biến hình..",
                "..ta sẽ truyền thụ kỹ năng biến hình cho ngươi.",
                "Hãy nhớ ngươi phải đối đầu với một con quái vật to lớn bên kia sông đấy!",
                "Đi đi, chúc ngươi may mắn."},
            {"Con biết, ta đang nghiên cứu làm ra 1 thứ thuốc, để chữa trị vết thương...",
                "Tuy nhiên ta thiếu 1 loại nguyên liệu rất khó kiếm.",
                "Nếu con có thể tìm giúp ta 8 cánh dơi vàng, ta sẽ có đền đáp xứng đáng.",
                "Bọn dơi vàng có mặt ở những ngôi đền phía nam. Bọn chúng rất nhanh.",
                "Tuy thế, ta vẫn tin con có thể mang về cho ta.", "Chúc con may mắn!"},
            {"Cô Ayumi nói cho con nghe rồi à...",
                "Nói ra thật đáng buồn, cũng như bao trường khác, trường ta luôn có những học sinh cá biệt...",
                "Toniku là một trong số đó, nó đã bỏ trường thành lập băng nhóm...",
                "Hiện đang tụ tập quậy phá ở 3 ngôi miếu phía nam trường...",
                "Con là một học sinh tốt, hãy đi tìm bọn chúng bắt về cho ta.",
                "Làm đi, rồi báo cáo kết quả với ta nhé!"},
            {"Cô bé em gái cô Ayumi cũng xinh, y như cô ấy vậy, con có thấy thế không!",
                "Nếu con có thể vào khu rừng gỗ, đánh gục 10 tên Quỷ khố, ta sẽ mai mối cho, haha!",
                "Nhưng hãy cẩn thận, bọn quái vật trong khu rừng ấy rất đông và mạnh đấy!"},
            {"Hiện tại khả năng con đã rất khá rồi, ta quyết định dạy những kỹ năng cấp cao cho con.",
                "Đầu tiên là khinh công cấp 2.", "Tuy nhiên, con phải chứng tỏ đã thành thạo khinh công cấp 1 trước...",
                "Hãy nhảy lên không tung, đánh được 20 đòn trước khi chạm đất!",
                "Không dễ phải không..."},
            {"Shuriken , một trong những vũ khí cơ bản của Ninja, ta sẽ truyền cho ngươi bộ ám khí mới này",
                "Vẫn như các lần trước, ngươi phải cho ta thấy đã đủ sức học...",
                "Đến khu rừng chông tre. Nhổ gốc 15 con quỷ hoa đỏ rồi về đây, ta sẽ dạy cho.",
                "Cẩn thận tính mạng ngươi đấy."},
            {"Mấy hôm nay ta nhận thấy con chạy còn rất chậm..",
                "Một ninja chậm chạp như con không thể làm việc lớn được.",
                "Hãy đến vách đá Ito, mang về cho ta 18 đuôi bò cạp..",
                "Ta sẽ dùng độc của chúng, điều chế cho con 1 loại thuốc tăng lực...",
                "Cẩn thận nọc độc bọn chúng nhé! Hãy dẫn cô bé Sakura đi, nó sẽ giúp con nhiều đấy."},
            {"Ta không muốn nói nhiều. Một phần thưởng đặt biệt cho ngươi..",
                "..nếu ngươi có thể giết được 4 con cá đỏ!"},
            {"Đây là kỹ năng cuối cùng của một ninja... kỹ thuật ẩn thân",
                "Kỹ năng này muốn học cũng không khó, tại một thung lũng phía nam..",
                "có một quyển bí kíp được giấu từ ngàn năm nay.",
                "Hãy tìm về, ta sẽ giúp con có được kỹ năng lợi hại này."},
            {"Con còn nhớ tên phản đồ Toniku lần trước không!",
                "Sau khi bị con đánh, hắn đã bỏ chạy đến ngôi miếu phía đông bắc.",
                "Nó vừa bắt mất cô bé Sakura đi rồi.",
                "Ta đã sai tất cả đệ tử đi tìm nó, cứu cô bé về!",
                "Con cũng phải thế nhé! Cô bé ấy đang chờ con đấy!"}};

    /** Thầy cô trao thưởng khi hoàn thành nhiệm vụ. */
    public static final String[][] questRewardLines = new String[][]{{"Thể lực con rất tốt, ta sẽ dạy khinh công cho con...",
                "Hãy tập trung khí huyết, hít thở nhẹ, người con nhẹ dần, nhẹ dần tựa chiếc lá khô...",
                "Chúc mừng con! Hãy thử nhảy lên xem kết quả thế nào.",
                "à, suýt nữa ta quên, lúc nãy thầy Hiroyaki trên tầng 1 gọi con đấy."},
            {"Tốt. Đây là cách phóng shuriken, hãy nhớ kỹ:",
                "Bấm phím 0 để chuyển sang phóng shuriken, bấm lần nữa để đánh kiếm.",
                "Nếu chăm chỉ tập luyện, phi tiêu sẽ là vũ khí lợi hại nhất của ngươi.",
                "Thầy Honda có việc cần gặp ngươi. Nhanh lại đó đi!"},
            {"Giỏi lắm, đúng là quyển bí kíp này.",
                "Cứ luyện tập theo quyển bí kíp này: hãy đến khu vực có đất..",
                "Ngồi thấp xuống và tập trung, ngươi sẽ độn thổ được.",
                "Hãy luyện tập đi, chú ý MP ngươi sẽ bị giảm khi độn thổ đó.",
                "Còn chuyện này nữa, vừa nãy ta thấy thầy chủ nhiệm ngươi có vẻ mệt. Ngươi hãy nhanh ghé thăm!"},
            {"Ngươi giỏi lắm, ta sẽ đã thông kinh mạch cho...",
                "úm ba la !! soda cacao cola!", "Ngươi có cảm nhận được luồn chân khí trong người không",
                "Đi đi, hãy dùng sức mạnh này để bảo vệ dân làng!"},
            {"Quyển bí kíp đây rồi, khá lắm cậu bé.",
                "Ngươi đã có thể lặn nước được rồi. Hãy nhớ, tuy lặn được nhưng thanh trúc dùng để thở rất ngắn...",
                "Vì thế, đừng lặn sâu quá đó.",
                "Thầy Hiroyaki tìm ngươi. Hãy mau đến gặp đi."},
            {"Ngươi có thể vượt qua con sông ấy ư?",
                "Ta sẽ giúp ngươi gọi được sức mạnh của dòng tộc mình: Xích Mao",
                "Bất kỳ khi nào, hãy ấn phím số 7, ngươi sẽ có được sức mạnh từ gốc cội của dòng tộc mình",
                "hãy thử xem, nhưng ngươi cần cẩn thận, vì thi triển thuật này,ngươi sẽ chóng mệt lắm đấy."},
            {"Cảm ơn con nhiều lắm. Đây là phần thưởng cho con, 5000$",
                "Tuy không bao nhiêu nhưng con có thể dùng nó để mua HP, MP.",
                "À, trường ta đang có việc lớn đấy!",
                "Con hãy gặp thầy chủ nhiệm để hỏi xem có giúp được gì hay không!"},
            {"Hắn bỏ trốn thoát rồi ư?",
                "Ta thật đau lòng khi có một đứa học trò như nó!",
                "Kể từ nay, ta cho phép con tấn công nó, không nương tay, bất kỳ khi nào gặp!"},
            {"Ta biết chắc chắn thế nào con cũng giết được bọn quỷ khố hung hăng đó!",
                "Lúc nãy, ta đã nói chuyện với cô Ayumi rồi.",
                "Hãy lại bắt chuyện với cô bé ấy đi!"},
            {"Tốt lắm. Con đã chứng tỏ được mình.",
                "Đây là kỹ năng khinh công cấp 2. Con đã có thể nhảy cao hơn trước rồi.",
                "Tiếp theo, hãy gặp thầy Hiroyaki, ông ta sẽ dạy con những kỹ năng mới!"},
            {"Tốt. Đây là kỹ năng shuriken cấp 2.",
                "Sức tấn công từ shuriken của con đã tăng lên rất nhiều!",
                "Con phải siêng năng tập luyện mới có thể giữ được sức mạnh này. Nhớ đấy!"},
            {"Khá lắm. Đây là phương thuốc chạy nhanh cho con!",
                "Hãy chạy 1 đoạn ngắn, sau đó tốc độ của con sẽ tăng nhanh..",
                "Hãy thử xem nào!"},
            {"Giỏi. Kể từ nay con sẽ không bao giờ bị chìm dưới nước nữa!",
                "..đây là kỹ thuật đứng nước bí truyền của ninja. Ta chỉ dạy cho mình con đấy!"},
            {"Ngươi có thể tìm được nó ư?",
                "Tốt lắm, từ nay con có thể sử dụng nó rồi đấy.",
                "Hãy nhấn phím số 9 để có thể ẩn thân. Kỹ năng này rất lợi hại..",
                "tuy nhiên nó sẽ tiêu thụ rất nhiều MP của con đấy!"},
            {"Các con đều bình an vô sự, rất tốt!",
                "Những gì đã là quá khứ thì ta cho qua, nhưng hãy nhớ và không tái phạm",
                "Nào, ta cho các con nghỉ, điện thoại cũng có lúc phải nghì ngơi chứ, hehe. Nay , ta chúc mừng các con đã hoàn thành khóa học ninja sơ cấp: các con không còn là Chunin nữa !",
                "Nay ta tuyên bố thăng cấp cho các con là Genin !"}};

    /** Thầy cô nhắc khi nhiệm vụ chưa xong. */
    public static final String[][] questReminderLines = new String[][]{{"Nhanh lên, hãy sang khu luyện tập và giết 10 con ốc sên."},
            {"Hãy đánh sập 2 bù nhìn rơm gần cổng trường. Nhanh lên!"},
            {"Bọn quỷ một chân có mặt ở thác nước, bên kia khu luyện tập, mau giết chúng để tìm quyển bí kíp!"},
            {"Hãy trở lại khi ngươi đã giết đủ 20 con ong."},
            {"Con heo rừng rất nguy hiểm. Nhưng ta tin ngươi có thể giết được nó và cầm về cho ta quyển bí kíp. Đi đi!"},
            {"Hãy đến khu nghĩa địa bên kia sông! Mang về quyển bí kíp. Nhanh lên!"},
            {"Nhanh lên, hãy giết 8 con dơi vàng ở những ngôi miếu phía nam!"},
            {"Hãy tìm tên Toniku, hắn trốn ở những ngôi miếu phía nam.",
                "Tìm gặp hắn quay lại báo cáo với ta nhé!"},
            {"Hãy giết 10 con quỷ khố ở khu rừng gỗ!",
                "Xong việc ta sẽ giúp con cưa con bé em gái cô giáo Ayumi!"},
            {"Để ta dạy kinh công cấp 2 cho con, con phải chứng tỏ được khả năng hiện tại.",
                "Hãy nhảy lên cao và chém được ít nhất 20 phát khi chưa chạm đất!",
                "Khá khó, nhưng ta tin con sẽ khẳng định được mình!"},
            {"Hãy đến rừng chông tre. Giết 15 quỷ hoa đỏ rồi về đây, ta cho ngươi shuriken cấp 2!"},
            {"Con hãy đến vách đá Ito, giết 18 con bò cạp, mang đuôi về cho ta!"},
            {"Ta không muốn nói nhiều. Một phần thưởng đặt biệt cho ngươi..",
                "..nếu ngươi có thể giết được 4 con cá đỏ!"},
            {"Để học kỹ năng ẩn thân, hãy đi tìm 1 quyển bí kíp...",
                "Cất giấu tại 1 thung lũng phía đông."},
            {"Nhanh lên, cô bé ấy đã bị bắt từ mấy hôm rồi!"}};

    /** Thầy cô nói chuyện khi không có nhiệm vụ để giao. */
    public static final String[][] idleLines = new String[][]{{"Ta đang bận, Con hãy đi tập luyện đi."},
            {"Các ngươi phải cố gắng tập luyện để trở thành những Ninja tốt"},
            {"Hằng ngày ngươi phải siêng năng tập thể lực, đó là nền tảng cho mọi hoạt động."},
            {"Cô Ayumi xinh thật. Con có thấy thế không?"},
            {"Ngươi biết không....à..à...mà ta định nói gì với ngươi nhỉ."},
            {"Ta không còn gì để dạy con nữa...",
                "Nhưng con phải cố gắng tập luyện hàng ngày. Biết không!"},
            {"Con cần phải để mắt tới cô bé Sakura nhé..",
                "Con bé ấy quậy lắm đấy!"},
            {"Dù sau này có đi đâu, ngươi cũng phải trở thành 1 ninja tốt nhé!"},
            {"Ta không còn gì để dạy con nữa!",
                "Hãy rèn luyện sức khỏe hàng ngày, biết không!"},
            {"..à.. ừ.. ngươi muốn học phép à..",
                ".. để ta nghĩ ra thêm vài phép mới..",
                "..trước mắt hãy học những kỹ năng cũ đã nhé..."}};

    /** Đang làm nhiệm vụ khác. */
    public static final String[] questUnfinishedLines = new String[]{"Hãy hoàn thành nhiệm vụ được giao rồi quay lại gặp ta!"};

    /** Chưa đủ cấp 8 để nhận nhiệm vụ. */
    public static final String[] levelRequired8 = new String[]{"Hãy quay lại gặp ta khi con đạt cấp độ 8"};

    /** Chưa đủ cấp 10 để nhận nhiệm vụ. */
    public static final String[] levelRequired10 = new String[]{"Hãy quay lại gặp ta khi con đạt cấp độ 10"};

    /** Chưa đủ cấp 12 để nhận nhiệm vụ. */
    public static final String[] levelRequired12 = new String[]{"Hãy quay lại gặp ta khi con đạt cấp độ 12"};

    /** Chưa đủ cấp 15 để nhận nhiệm vụ. */
    public static final String[] levelRequired15 = new String[]{"Hãy quay lại gặp ta khi con đạt cấp độ 15"};

    /** Chưa đủ cấp 16 để nhận nhiệm vụ. */
    public static final String[] levelRequired16 = new String[]{"Hãy quay lại gặp ta khi con đạt cấp độ 16"};

    // #endregion
    // =====================================================================================
    // #region Lính gác, cô bé

    /** Lính gác / người bán hàng (NPC loại 5), chỉ số theo NPC trên từng map. */
    public static final String[][] guardLines = new String[][]{{"..."},
            {"Đây là khu vực luyện tập, cứ yên tâm, nếu bị thương ta sẽ giúp đỡ.",
                "Đôi khi tập luyện với cái bù nhìn rơm đằng kia cũng mang lại nhiều bất ngờ đấy!"},
            {"Thác nước này rất nguy hiểm, ta khuyên ngươi không nên xuống.",
                "Hãy quay lại đi, nếu có chuyện gì ta không cứu được ngươi đâu."},
            {"Khu rừng trước mặt ngươi là nơi sống của bọn dơi quỷ, nhện độc và những sát thủ bị truy nã.",
                "Dù ngươi tài giỏi đến đâu cũng phải mất mạng khi đi ngang qua đó.",
                "Một số người có thể qua được bằng cách chạy thật nhanh qua.",
                "Nếu ngươi tự tin hãy dùng chiếc moto kia và liều một phen xem.",
                "Chúc ngươi may mắn."}};

    /** Cô bé (nhiệm vụ dẫn đi chơi): 0 đang đi cùng, 1 chưa đủ trình, 2 rủ đi lần sau, 3 nhận nhiệm vụ. */
    public static final String[][] girlLines = new String[][]{{"Chúng ta đi thôi!"},
            {"Muốn rủ ta đi chơi à!",
                "Ta chỉ chơi với những người cùng trình độ thôi.",
                "Ngươi hãy tập luyện đi, bao giờ khá hơn thì quay lại. Đừng ở đó tán gái nữa!"},
            {"Muốn rủ ta cùng đi à?",
                "OK, nhưng chỉ một lúc thôi nhé, nếu không chị Ayumi mắng ta đó!"},
            {"Ta muốn đến miếu Koujin chơi, mi có thể dẫn đường chứ!",
                "Nếu sợ, mi có thể ở nhà, ta sẽ tự đi. Haha!"}};

    // #endregion
    // =====================================================================================
    // #region Cutscene

    /** Lời thoại trong cutscene (chỉ số lấy từ kịch bản b.cutsceneScripts). */
    public static final String[][] cutsceneLines = new String[][]{{"Rừng trúc đây rồi!",
                "Hôm nay ta phải hạ được con quái vật đó!",
                "Nó chắc quanh quẩn đây thôi!"},
            {"Bọn quái vật nhãi nhép này làm gì được ta chứ!"},
            {"Con quái vật hung hăng kia rồi!",
                "Ngươi phải đền tội vì những gì gây ra!"},
            {"Này các con, sau bao năm tháng tập luyện...",
                "Đây là lúc các con thể hiện tài năng!",
                "Gần đây, trong khu rừng trúc...",
                "..xuất hiện 1 con quái vật to lớn..",
                "Nó thường phá phách, đe dọa dân làng.",
                "Các con biết phải làm gì rồi chứ?",
                "Đây là lúc các con chứng minh khả năng của mình.. ",
                "Đi đi. Và hãy cẩn thận đấy !!"},
            {"Chết đi, con quái vật hung hăng kia !!!",
                "Hahahaha!"},
            {"Chào mừng ngươi đến với trường đào tạo ninja Hirosaki.",
                "Ta là Okamesama, hiệu trưởng trường này.",
                "Tại đây chúng ta sẽ đào tạo các ngươi, trở thành những ninja chuyên nghiệp.",
                "Giáo viên chủ nhiêm của ngươi là thầy Takanashi",
                "Hãy đến gặp thầy để nhận bài tập.",
                "Có vấn đề gì, ngươi có thể hỏi những đồng môn của mình.",
                "Chúc ngươi sớm đạt được thành công nhé."},
            {"Vâng, thưa thầy!"},
            {"Con đường hoa đào đây rồi.",
                "Con bé ấy đâu rồi nhỉ?"},
            {"Chào bạn! Bạn có phải là em gái cô Ayumi không?",
                "Thầy Takanashi bận nên nhờ mình đón bạn giúp.",
                "Đi theo mình nhé, chúng ta sẽ về trường!"},
            {"Tốt lắm, con đã đưa cô bé về an toàn!",
                "Đây ta có món quà gì giá trị để tặng con..",
                "Đây là tấm bằng lái xe có chữ ký của ta. Kể từ nay con có thể sử dụng những chiếc xe máy rồi!",
                "Nhớ phải lái cẩn thận và đội mũ bảo hiểm, nếu không là CSGT phạt đó!"},
            {"Thuỷ quái hạ nguồn sông Waramato, con quái trong truyền thuyết !",
                "Một khi nó chưa bị tiêu diệt, ta sẽ không đi tiếp!"},
            {"Chết đi, con quái vật kia!",
                "Từ nay con sông này sẽ trở lại thanh bình rồi!",
                "Ta tiếp tục cuộc hành trình thôi!"},
            {"Ngươi cũng lặn lội đến tận đây được à, khá đấy!",
                "Hãy bỏ ngôi trường ấy đi, theo ta lập căn cứ, ngươi sẽ có tất cả.",
                "Nếu không theo ta, ngươi sẽ hối hận đấy... HaHaHa!"},
            {"Không đời nào, tên phản bội kia!"},
            {"Ngươi khá lắm! Hãy đợi đấy, ta sẽ nhớ mối thù này!",
                "Hẹn gặp lại ngươi!!!"},
            {"Chúng ta lại gặp nhau rồi!",
                "Ta cho ngươi một cơ hội lần cuối. hãy theo ta, hoặc là chết!"},
            {"Haha. Tên phản bội kia, ta sẽ cho mi biết sức mạnh một ninja thực thụ!"},
            {"Cuối cùng thì mi cũng đã phải dền tội!"},
            {"Các con hãy cho biết làm cách nào để di chuyển!"},
            {"Sử dụng phím mũi tên hoặc phím 2,4,6,8!"},
            {"Làm thế nào để tấn công?, Kunio"},
            {"Sử dụng phím 5 hoặc phím chọn giữa!"},
            {"Làm thế nào để trò chuyện với bạn bè hoặc thầy cô?,Lee"},
            {"Đứng gần người đó rồi bấm Giao Tiếp!"},
            {"Làm thế nào để biết mình đang ở đâu! Katashi"},
            {"Chọn menu/Xem bản đồ!"},
            {"Làm thế nào khi hết HP hoặc MP! Toniku"},
            {"Chọn menu/Sử dụng Item, chọn một bình HP/MP để sử dụng!"},
            {"Uhm..Nếu không còn bình HP/MP nào thì sao? Minori"},
            {"Gặp ông bán hàng mua tại trường, ở gần ký túc xá!"},
            {"Tốt. Làm thế nào để di chuyển nhanh từ khu vực này đến khu vực khác?"},
            {"Mở bản đồ, chọn 1 địa điểm để di chuyển đến!"},
            {"Đúng rồi, Làm sao để sử dụng phi tiêu?,Ryuu"},
            {"Bấm phím 1 sẽ chuyển sang chế độ phi tiêu.",
                "Bấm phím 1 lần nữa để quay lại đánh kiếm!"},
            {"Tốt. Làm sao để sử dụng khinh công?"},
            {"Học khinh công từ cô Ayumi. Bấm phím mũi tên lên hoặc phím 2 nhiều lần!"},
            {"Uhm..Làm sao để độn thổ!"},
            {"Học độn thổ từ thầy Honda. Bấm và giữ luôn phím xuống hoặc phím 8!"},
            {"Khi nào mới có thể lái xe môtô?"},
            {"Học lái xe từ thầy Takanashi, được cấp bằng lái",
                "Sau đó có thể nhảy lên chạy bất kỳ chiếc mô tô nào !"},
            {"Làm sao để chạy trên mặt nước? huh ? Sadao"},
            {"Học thuật chạy nước/ lặn nước từ thầy Honda! Sau đó có thể chạy trên mặt nước!"},
            {"Làm sao để biến hình!"},
            {"Bấm phím 7. Sau khi đã học phép biến hình từ thầy Hiroyaki"},
            {"Làm sao để tàng hình!"},
            {"Bấm phím 3. Sau khi đã học phép tàng hình từ thầy Honda"},
            {"Tốt! Cho ta biết ai phát triển game này!"},
            {"Nhóm TeaMobile!"},
            {"Cụ thể là ai?"},
            {"...Con không biết..."},
            {"Hmm, có thể tìm thêm nhiều game hay khác ở đâu?"},
            {"www.teamobi.com hay www.wap.teamobi.com"},
            {"Tốt, các con có thể giải tán !"},
            {"Chúng con chào thầy !"},
            {"Cảm ơn, mi thật tốt bụng. Hẹn gặp lại!",
                "Quên mất, lúc nãy cô Ayumi dặn ta gặp cô ấy ngay.",
                "Hihi, gặp lại mi sau!"},
            {"Sakura, bạn có sao không!"},
            {"Mình không sao, bạn không bị thương chứ!"},
            {"Mình cũng không sao! Tên Toniku chắc cũng không chạy xa được đâu , hắn cũng sớm bị bắt thôi!",
                "Chúng ta về thôi!"},
            {"Toniku, ngươi đã biết tội chưa!"},
            {"..vâng..con biết...."},
            {"Theo nội quy của nhà trường. Ngươi phải rời khỏi ngôi trường này.",
                "Vĩnh viễn không được trở lại nữa.."},
            {"......"},
            {"Thưa thầy...",
                "Toniku vốn là học sinh giỏi của trường..",
                " Chỉ vì nghe lời xúi giục của bọn xấu nên mới hành động như thế...",
                "Đây là lần vi phạm đầu tiên, xin nhà trường tha thứ cho bạn ấy..."},
            {"......"},
            {"Xin các thầy hãy tha thứ cho bạn ấy ạ!"},
            {"..thôi được rồi..", "Tội Toniku rất nặng, đáng lẽ ra phải bị đuổi học.",
                "Con đường để thành một nhẫn giả thật gian lao và chông gai..",
                "..ngươi phải luyện tập bằng khả năng và sức lực của mình",
                " sẽ không có con đường nào khác",
                " ... các con đều biết chữ Nhẫn gồm chữ Đao và chữ Tâm.",
                "..nếu Tâm không tịnh thì Đao sẽ bổ xuống!",
                "Các con hãy lấy Toniku mà làm gương ...",
                "Toniku, ngươi đã quá tham lam...",
                "..nhưng ta cũng thây nơi con người ngươi có tố chất tiềm tàng!",
                "Nên ta quyết định tha cho con lần này...",
                "Sẽ không có lần sau, Toniku, ngươi nhớ đấy!!"},
            {"Vâng, con xin cảm ơn thầy!"},
            {"Sẵn đây ta muốn nhắc nhở thêm các con..",
                "Hai tháng nữa là đến kỳ thi kết khoá cho Genin. Các con phải cố gắng tập luyện hơn nữa..",
                "..Mọi thứ chỉ mới bắt đầu thôi!"},
            {"Vâng, chúng con xin nghe!"}};

    /** Chữ dẫn chuyện hiện giữa màn hình khi chiếu cutscene. */
    public static final String[] cutsceneCaptions = new String[]{"Trường đạo tạo Ninja Hirosaki, Mùa thu 1937",
            "2 ngày sau, tại rừng trúc...",
            "Chỉ dẫn: Dùng phím 5 để tấn công.",
            "6 tháng trước, tại trường Hirosaki...",
            "2 ngày sau, tại trường Hirosaki..."};

    // #endregion
    // =====================================================================================
    // #region Bạn học ở Trường (map 0). *Assassin: câu thay thế khi người chơi đã học biến hình.

    public static final String[] schoolClassmate0 = new String[]{"Chào, cậu là ai thế, mình chưa từng gặp bạn thì phải?", "Nói cậu nghe nhé, thầy cô trường này khó tính lắm đó.", "Cẩn thận với ông thầy Hiroyaki nhé. Đừng chọc giận ổng."};

    public static final String[] schoolClassmate1 = new String[]{"Mình là MinhXP, lớp trưởng lớp kiếm thuật.", "Kiếm là vũ khí lợi hại nhất của một ninja đấy bạn biết không?", "Khu vực luyện tập nằm ở bên trái. Hãy sang đấy tập luyện nhiều hơn nữa."};

    public static final String[] schoolClassmate2Assassin = new String[]{"Thằng bên kia là sát thủ giả mạo.", "Ta đây mới là ninja sát thủ thứ thiệt đây!"};

    public static final String[] schoolClassmate2 = new String[]{"Chào, tao tên là Tonic, mày là thằng nào?", "Nhìn lão hiệu trưởng kìa, lão có một hũ rượu trông hấp dẫn quá!", "Nếu muốn rủ tao đi nhậu thì ok, còn không thì biến đi.", "À, nếu mày có thấy con chó của tao thì báo tao 1 tiếng nhé."};

    public static final String[] schoolClassmate3 = new String[]{"Hi, mình là Craze, trông bạn có vẻ hơi buồn thì phải?", "Bạn có thấy chán khi tối ngày quanh quẩn trong trường không?", "Đi về phía tay phải bạn sẽ đến được ngôi làng, ra đó để tìm niềm vui đi!"};

    public static final String[] schoolClassmate4 = new String[]{"Thằng nhà quê kia, mày mau kiếm tiền đóng thuế thân cho tao.", "Tao cho mày 2 ngày, nếu không đưa tao 1000$ thì mày ăn chém đó."};

    public static final String[] schoolClassmate5 = new String[]{"Xin chào, bạn là học sinh mới phải không?", "Nhìn thầy hiệu trưởng kìa, thầy vốn là một ninja sát thủ đấy.", "Thầy đã bỏ nghề sát thủ lâu rồi, nhưng tài năng vẫn còn đó", "Chúng ta có tập luyện cả đời cũng không bằng thầy đâu."};

    public static final String[] schoolClassmate6 = new String[]{"Đừng hỏi ta là ai...", "Ta chỉ là một ninja sát thủ thôi, ngươi biết thế là được rồi.", "Biến đi cho ta luyện tập."};

    // #endregion
    // =====================================================================================
    // #region Bạn học ở Khu luyện tập (map 1)

    public static final String[] trainingClassmate0 = new String[]{"Bạn có thể nhảy lên cây cầu này nếu đã học xong khinh công từ cô Ayumi xinh đẹp."};

    public static final String[] trainingClassmate1Assassin = new String[]{"Ta đây là một ninja sát thủ!",
            "Ha ha ha!"};

    public static final String[] trainingClassmate1 = new String[]{"Mày đã học khinh công rồi hả?",
            "À, mày có thấy con chó của tao chạy đâu đâu không?"};

    public static final String[] trainingClassmate2 = new String[]{"Bên kia là một thác nước rất lớn đấy.",
            "Nó không thuộc phạm vi trường chúng ta nữa nên có rất nhiều quái vật hung dữ.",
            "Nghe nói có một con quái vật rất to đấy, tốt hơn ngươi đừng qua làm gì!"};

    // #endregion
    // =====================================================================================
    // #region Bạn học ở Làng Kenshin (map 2)

    public static final String[] villageClassmate0 = new String[]{"Cậu nhìn thấy chiếc xe máy kia không?",
            "Phải có bằng lái từ thầy chủ nhiệm bạn mới có thể chạy được."};

    public static final String[] villageClassmate1 = new String[]{"Thằng nhà quê kia, mày đi đâu đấy?"};

    public static final String[] villageClassmate2Assassin = new String[]{"Ninja sát thủ là ta - Thiên hạ vô địch!"};

    public static final String[] villageClassmate2 = new String[]{"Mày có thấy con chó của tao ở đâu không?"};

    public static final String[] villageClassmate3 = new String[]{"Nếu bạn muốn lên kia thì phải học khinh công từ cô Ayumi đã."};

    public static final String[] villageClassmate4 = new String[]{"Cậu có thấy ở đây ai cũng tài giỏi không?",
            "Hãy cố gắng tập luyện hơn nữa nhé"};

    public static final String[] villageClassmate5 = new String[]{"Ra ngoài rừng hoa đào có rất nhiều điều thú vị đấy.",
            "Bạn nên ra thử một lần để biết."};

    public static final String[] villageClassmate6 = new String[]{"Đừng hỏi ta là ai...",
            "Ta chỉ là một ninja sát thủ thôi, ngươi biết thế là được rồi.", "Biến đi cho ta luyện tập."};

    // #endregion
    // =====================================================================================
    // #region Bạn học ở màn hướng dẫn (map 99/101) và lễ ra trường (map 94): mọi bạn nói cùng một câu

    public static final String[] tutorialClassmates = new String[]{"Này, cậu đi tìm con heo đó trước đi. Tớ đi theo bọc lót cho!"};

    public static final String[] graduationClassmates = new String[]{"Chúc mừng, sắp được ra trường rồi nhé!"};

    // #endregion
    // =====================================================================================

    private DialogueText() {
    }
}
