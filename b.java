import assets.*;
import assets.Character;
import core.input.*;
import core.map.*;
import core.player.*;
import core.save.*;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class b extends Canvas implements Runnable {
    public static boolean a = true;
    private static int aa = 40;
    private static int[] ab = new int[]{150, 180, 180, 300, 350, 380, 500};
    private static int[] ac = new int[]{20, 30, 40, 100, 80, 120, 200};
    private static int[] ad = new int[]{150, 140, 120, 240, 280, 290, 300};
    private static int[] ae = new int[]{800, 1000, 1200, 3000, 3500, 4000, 6000};
    private static int[][] af = new int[][]{{-5, -2}, {-2, -4}, {0, -5}, {-6, -6}, {-5, -2}, {-2, -4}, {0, -5}};
    private static int[][] ag = new int[][]{{5, 8}, {3, 3}, new int[2], {4, 4}, {5, 8}, {3, 3}, new int[2]};
    private static int[] ah = new int[]{1, -2, 1, 1, 1, -2, 1};
    private static String[] al = new String[]{"Ma một mắt", 
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
    private static int[] am = new int[]{140, 70, 150, 120, 150, 150, 60, 1000, 250, 500, 4000, 500, 600, 1000, 1500, 2000, 1000, 3500, 1500, 5500};
    private static int[] an = new int[]{15, 10, 30, 25, 25, 25, 5, 10, 20, 40, 30, 40, 130, 30, 40, 100, 60, 160, 120, 200};
    private static int[] ao = new int[]{35, 20, 55, 40, 45, 50, 30, 130, 60, 65, 70, 75, 120, 85, 100, 120, 140, 160, 180, 200};
    private static int[] ap = new int[]{20, 10, 50, 25, 30, 40, 15, 50, 60, 70, 80, 100, 120, 130, 160, 180, 170, 210, 190, 220};
    private static int[][][] aq = new int[][][]{{{0, -13, 31}, {0, -7, 8}, {0, -11, 14}}, {{0, -13, 32}, {0, -7, 8}, {0, -11, 15}}, {{1, -11, 31}, {1, -9, 16}, {6, -1000, 0}}, {{2, -11, 32}, {2, -10, 16}, {6, -1000, 0}}, {{1, -11, 31}, {3, -8, 16}, {6, -1000, 0}}, {{2, -10, 32}, {7, -9, 17}, {6, -1000, 0}}, {{1, -10, 31}, {8, -8, 16}, {6, -1000, 0}}, {{0, -8, 35}, {4, -5, 15}, {2, -10, 21}}, {{0, -8, 34}, {5, -6, 13}, {3, -11, 22}}, {{0, -8, 31}, {6, -13, 9}, {4, -10, 18}}, {{1, -8, 30}, {6, -13, 9}, {5, -8, 17}}, {{2, -10, 30}, {6, -13, 9}, {6, -8, 16}}, {{2, -10, 29}, {6, -13, 9}, {7, -11, 17}}, {{3, -11, 33}, {8, -4, 13}, {8, -15, 25}}};
    private static int[][][] ar = new int[][][]{{{0, -10, 32}, {1, -7, 7}, {1, -11, 15}}, {{0, -10, 33}, {1, -7, 7}, {1, -11, 16}}, {{1, -10, 33}, {2, -10, 11}, {2, -9, 16}}, {{1, -10, 32}, {3, -11, 9}, {3, -11, 16}}, {{1, -10, 34}, {4, -9, 9}, {4, -8, 16}}, {{1, -10, 34}, {5, -11, 11}, {5, -10, 17}}, {{1, -10, 33}, {6, -9, 9}, {6, -8, 16}}, {{0, -9, 36}, {7, -5, 15}, {7, -10, 21}}, {{4, -13, 26}, new int[3], new int[3]}, {{5, -13, 25}, new int[3], new int[3]}, {{6, -12, 26}, new int[3], new int[3]}, {{7, -13, 25}, new int[3], new int[3]}, {{0, -9, 35}, {8, -4, 13}, {8, -14, 27}}, {{0, -9, 32}, {9, -11, 9}, {10, -10, 18}}, {{2, -7, 34}, {9, -11, 9}, {11, -8, 16}}, {{2, -8, 33}, {9, -11, 9}, {12, -8, 15}}, {{2, -7, 33}, {9, -11, 9}, {13, -12, 16}}, {{0, -11, 32}, {9, -11, 9}, {14, -15, 19}}, {{2, -9, 33}, {9, -11, 9}, {15, -13, 20}}, {{2, -9, 32}, {9, -11, 9}, {16, -7, 23}}, {{2, -9, 33}, {9, -11, 9}, {17, -11, 19}}, {{3, -12, 34}, {8, -4, 13}, {8, -15, 25}}, {{0, -10, 28}, new int[3], {19, -8, 12}}, {{0, -9, 35}, {8, -4, 13}, {9, -9, 22}}, {{0, -10, 33}, new int[3], new int[3]}, {{9, -4, 24}, new int[3], new int[3]}, {{10, -4, 22}, new int[3], new int[3]}, {{0, -9, 32}, {8, -4, 9}, {10, -10, 18}}, {{2, -7, 34}, {8, -4, 9}, {11, -8, 16}}, {{2, -8, 33}, {8, -4, 9}, {12, -8, 15}}, {{2, -7, 33}, {8, -4, 9}, {13, -12, 16}}, {{0, -11, 32}, {7, -5, 9}, {14, -15, 19}}, {{2, -9, 33}, {7, -5, 9}, {15, -13, 20}}, {{2, -9, 32}, {7, -5, 9}, {16, -7, 23}}, {{2, -9, 33}, {7, -5, 9}, {17, -11, 19}}, {{0, -9, 32}, {2, -10, 11}, {10, -10, 18}}, {{2, -7, 34}, {3, -11, 9}, {11, -8, 16}}, {{2, -8, 33}, {4, -9, 9}, {12, -8, 15}}, {{2, -7, 33}, {5, -11, 11}, {13, -12, 16}}, {{0, -11, 32}, {2, -10, 11}, {14, -15, 19}}, {{2, -9, 33}, {3, -11, 9}, {15, -13, 20}}, {{2, -9, 32}, {4, -9, 9}, {16, -7, 23}}, {{2, -9, 33}, {5, -11, 11}, {17, -11, 19}}, {{1, -8, 38}, new int[3], {18, -8, 21}}, {{1, -8, 39}, new int[3], {18, -8, 22}}, {{1, -10, 43}, new int[3], {18, -11, 26}}};
    private static int[] as = new int[]{-2, -6, 22, 21, 19, 22, 10, -2, -2, 5, 19};
    private static int[] at = new int[]{9, 22, 25, 17, 26, 37, 36, 49, 50, 52, 36};
    private static int[][] au = new int[][]{{2, 1, 1, 5, 3}, {2, 4, 3}, {2, 3}, {2, 1, 3}, {6, 3}, {2, 7, 3}, {2, 8, 3}, {2, 9, 3}, {2, 3}, {1, 3}, {2, 3}};
    private static int[][] av = new int[][]{{0, 1, 2, 5, 0}, {3, 4, 0}, {6, 0}, {7, 8, 0}, {9, 0}, {10, 11, 0}, {12, 13, 0}, {14, 15, 0}, {16, 0}, {17, 0}, {18, 0}};
    private static int[][] aw = new int[][]{{0, 360, 1128, 0, 0}, new int[3], new int[2], {0, 2784, 0}, {1248, 0}, new int[3], new int[3], new int[3], new int[2], new int[2], new int[2]};
    private static int[][] ax = new int[][]{new int[5], new int[3], new int[2], new int[3], {408, 0}, new int[3], new int[3], new int[3], new int[2], new int[2], new int[2]};
    private static int[][] ay = new int[][]{{15, 1, 1, 2, 2, 1, 2, 4, 5, 3}, {6, 15, 1, 2, 1, 2, 1, 2, 1, 2, 4, 5, 3}, {6, 1, 2, 8, 11, 4, 7, 1, 2, 1, 2, 3}, {8, 9, 15, 12, 14, 10, 4, 16, 5, 1, 2, 3}, {7, 17}, {4, 1, 2, 17}, {8, 9, 15, 12, 14, 10, 4, 18, 4, 7, 3}, {1, 2, 4, 5, 3}, {6, 4, 19, 3}, {4, 20, 21, 3}, {4, 3}, {4, 22, 3}, {23, 4, 7, 4, 3}, {23, 25, 4, 7, 24, 26, 3}, {27, 4, 7, 4, 28, 3}, {4, 31, 32, 7, 4, 31, 4, 7, 4, 8, 17, 3}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 29, 3}, {4, 30, 3}, {15, 31, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 33, 17, 3}};
    private static int[][] az = new int[][]{{1, -4, -1, -1, -4, -4, -4, 0, 20000, 0}, {0, 2, -4, -4, -5, -5, -5, -5, -5, -5, 1, 20000, 0}, {0, -5, -5, 0, 0, 2, 0, 55, 55, -4, -4, 0}, {0, 1, 0, 2, 3, 4, 3, 432, 20000, -4, -4, 0}, {0, 4}, {4, 55, 55, 0}, {0, 5, 3, 6, 7, 8, 5, 2, 6, 0, 0}, {-4, -4, 7, 20000, 0}, {0, 8, 0, 0}, {9, 0, 0, 0}, {10, 0}, {11, 0, 0}, {0, 12, 0, 13, 0}, {0, 0, 14, 0, 600, 0, 0}, {0, 15, 0, 16, 0, 0}, {17, 0, 0, 0, 55, 0, 56, 0, 57, 0, 94, 0}, {18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 0, 0}, {54, 0, 0}, {4, 0, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 0, 0, 0}};
    private static int[][] aA = new int[][]{{5, 26, 30, 33, 41, 48, 53, 57, 58, 60}, {5, 6, 8, 9, 18, 20, 30, 32, 39, 40, 60, 63, 67}, {5, 6, 7, 13, 14, 19, 21, 34, 35, 60, 79, 80}, {1, 2, 3, 20, 50, 80, 85, 90, 92, 93, 95, 0}, {5, 6}, {10, 12, 13, 50}, {1, 2, 3, 4, 5, 6, 17, 18, 19, 20, 21}, {5, 10, 20, 30, 32}, {5, 10, 15, 20}, {10, 15, 20, 25}, {20, 25}, {10, 12, 14}, {15, 30, 35, 45, 55}, {10, 12, 30, 40, 45, 50, 75}, {15, 30, 35, 45, 53, 55}, {10, 12, 13, 40, 50, 60, 70, 80, 90, 95, 100, 105}, {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47}, {10, 12, 14}, {10, 11, 60, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74}};
    private static int[][] aB = new int[][]{{1344, 288}, {816, 48}, {816, 192}, {744, 192}, {144, 264}, {1440, 96}, {1368, 288}, {1128, 288}, {840, 312}};
    private static int[] aC = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 1, 0, 4, 0, 3, 0, 6, 0, 2, 0, 5, 0, 4, 0, 1, 0, 3, 0, 0, 0, 5, 0, 4, 0, 3, 0, 2, 0, 2, 0, 1, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 2, 1, 3, 2, 2};
    private static int[] aD = new int[]{0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 2, 0, 2, 3, 0, 0, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 4, 0, 4, 0, 1, 2, 1, 2, 0, 1, 2, 1, 2, 1, 2};
    private static String[] aE = new String[]{"Chơi mới", 
            "Chơi tiếp", 
            "Chỉ dẫn", 
            "Game khác", 
            "Thoát"};
    private static String[][] aF = new String[][]{{"Rừng trúc đây rồi!", 
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
    private static String[] aG = new String[]{"Trường đạo tạo Ninja Hirosaki, Mùa thu 1937", 
            "2 ngày sau, tại rừng trúc...", 
            "Chỉ dẫn: Dùng phím 5 để tấn công.", 
            "6 tháng trước, tại trường Hirosaki...", 
            "2 ngày sau, tại trường Hirosaki..."};
    private static String[][] aH = new String[][]{{"Chúng ta đi thôi!"}, 
        {"Muốn rủ ta đi chơi à!", 
            "Ta chỉ chơi với những người cùng trình độ thôi.", 
            "Ngươi hãy tập luyện đi, bao giờ khá hơn thì quay lại. Đừng ở đó tán gái nữa!"}, 
        {"Muốn rủ ta cùng đi à?", 
            "OK, nhưng chỉ một lúc thôi nhé, nếu không chị Ayumi mắng ta đó!"}, 
        {"Ta muốn đến miếu Koujin chơi, mi có thể dẫn đường chứ!", 
            "Nếu sợ, mi có thể ở nhà, ta sẽ tự đi. Haha!"}};
    private static int[][][] aI;
    private static int[] aJ;
    private static int[] aK;
    private static int aL;
    private static b aM;
    static boolean b;
    private static int aT;
    private static int aU;
    private static int aV;
    private static int aW;
    private static int aX;
    public static boolean c;
    public static boolean d;
    private static int aY;
    private static int aZ;
    private static int ba;
    private static int bb;
    private static int bc;
    private static int bd;
    public static int e;
    private static boolean be;
    public static int[] f;
    public static int[] g;
    public static int h;
    public static int i;
    public static int j;
    public static int k;
    public static int l;
    public static int m;
    public static int n;
    public static String o;
    public static String p;
    private static int bf;
    private static int bg;
    private static int bh;
    private static int bi;
    private static int bj;
    private static int bk;
    private static int bl;
    private static int bm;
    private static int bn;
    private static int bo;
    private static int bp;
    private static int bq;
    private static int br;
    private static int bs;
    private static int bt;
    private static int bu;
    private static String bv;
    private static int bw;
    private static int bx;
    private static int by;
    private static int bz;
    private static int bA;
    private static int bB;
    public static int q;
    public static int r;
    public static int s;
    private static int bC;
    private static int bD;
    private static int bE;
    private static int bF;
    private static int bG;
    private static int bH;
    private static int bI;
    private static int bJ;
    private static int bK;
    private static int bL;
    private static int bM;
    private static int[] bN;
    private static int[] bO;
    private static int[] bP;
    private static int[] bQ;
    private static int[] bR;
    private static int[] bS;
    private static int bT;
    private static int[] bU;
    private static int[] bV;
    private static int[] bW;
    private static int[] bX;
    private static int[] bY;
    private static int[] bZ;
    private static int[] ca;
    private static int[] cb;
    private static int[] cc;
    private static int[] cd;
    private static int[] ce;
    private static int[] cf;
    private static int[] cg;
    private static int[] ch;
    private static int[] ci;
    private static int[] cj;
    private static int[] ck;
    private static int[] cl;
    private static boolean cm;
    private static int cn;
    private static String co;
    private static int cp;
    private static int[] cq;
    private static int[] cr;
    private static int[] cs;
    private static int[] ct;
    private static int[] cu;
    private static int[] cv;
    private static int[] cw;
    private static int[] cx;
    private static int[] cy;
    private static int[] cz;
    private static int[] cA;
    private static int[] cB;
    private static int[] cC;
    private static int[] cD;
    private static int[] cE;
    private static int[] cF;
    private static int[] cG;
    private static int[] cH;
    private static int[] cI;
    private static int[] cJ;
    private static int[] cK;
    private static int[] cL;
    private static int cM;
    private static int[] cN;
    private static int[] cO;
    private static int[] cP;
    private static int[] cQ;
    private static int[] cR;
    private static int[] cS;
    private static int[] cT;
    private static int[] cU;
    private static int cV;
    private static int[] cW;
    private static int[] cX;
    private static int[] cY;
    private static int[] cZ;
    private static int[] da;
    private static int[] db;
    private static boolean[] dc;
    private static int dd;
    private static int de;
    private static int[] df;
    private static int[] dg;
    private static int[] dh;
    private static int[] di;
    private static int[] dj;
    private static int[] dk;
    private static int[] dl;
    private static int[] dm;
    private static boolean dn;
    private static int doSt;
    private static int dp;
    private static int dq;
    private static int dr;
    private static int ds;
    private static boolean[] dt;
    private static int du;
    private static String[][] dv;
    private static boolean dw;
    private static boolean dx;
    private static int dy;
    private static int[] dz;
    private static int[] dA;
    private static int[] dB;
    private static int[] dC;
    private static int[] dD;
    private static int[] dE;
    private static int[] dF;
    private static int[] dG;
    private static int[] dH;
    private static int[] dI;
    private static int[] dJ;
    private static int[] dK;
    private static int[] dL;
    private static int[] dM;
    private static int[] dN;
    private static String[] dO;
    private static int[] dP;
    private static int[] dQ;
    private static int[] dR;
    private static int[] dS;
    private static int[] dT;
    private static int[] dU;
    private static int dV;
    private static int dW;
    private static int dX;
    private static Image[][] enemyProjectiles;
    private static Image[] arrowIcons;
    private static Image healSprite;
    public static Image[] t;
    private static Image[] motorbikeFrames;
    private static Image itemScroll;
    private static Image[] heroBodies;
    private static Image itemCrate;
    public static Image u;
    public static Image v;
    private static Image[] hudFrames;
    private static Image[] itemMoney;
    private static Image markerIcon;
    private static Image[] npcDog;
    private static Image[][] dustSprites;
    private static Image[] dartFrames;
    private static Image[] girlBodies;
    private static Image[] girlHeads;
    private static Image[] girlLegs;
    private static Image[] heroHeads;
    private static Image[] heroTransformHeads;
    private static Image[] heroLegs;
    private static Image[] auraSprites;
    private static Image[][] classmateHeads;
    private static Image[][] ninjaBodies;
    private static Image[] ninjaLegs;
    private static Image[] boss0Sprites;
    private static Image[] itemPotions;
    private static Image[] boss1Sprites;
    private static Image[] bloodSprites;
    private static Image[] boss2Sprites;
    private static Image titleImage;
    private static Image[] slashEffects;
    private static Image[] weaponIcons;
    private static Image[] waterSplashSprites;
    public static int w;
    public static int x;
    private static boolean eE;
    private static boolean eF;
    private static boolean eG;
    private static boolean eH;
    private static boolean eI;
    private static boolean eJ;
    private static boolean eK;
    private static boolean eL;
    private static boolean eM;
    private static boolean eN;
    private static boolean eO;
    private static boolean eP;
    private static boolean eQ;
    private static boolean eR;
    private static boolean eS;
    private static boolean eT;
    private static boolean eU;
    private static boolean eV;
    private static boolean eW;
    private static boolean eX;
    private static boolean eY;
    private static boolean eZ;
    private static boolean fa;
    private static boolean fb;
    private static int fc;
    private static int fd;
    public static int y;
    private static int fe;
    private static int ff;
    public static int z;
    public static int A;
    public static int B;
    public static int C;
    private static int fg;
    private static int fh;
    private static int fi;
    private static int fj;
    private static int fk;
    private static int fl;
    private static int fm;
    public static int D;
    public static int E;
    private static int fn;
    public static int L;
    public static int M;
    private static int fo;
    private static int fp;
    private static int fq;
    private static boolean fr;
    private static int fs;
    private static int ft;
    private static boolean fu;
    private static boolean fv;
    private static boolean fw;
    private static boolean fx;
    public static int P;
    private static int fy;
    private static int fz;
    private static boolean fA;
    private static boolean fB;
    private static int fC;
    private static int fD;
    private static int fE;
    private static int fF;
    private static int fG;
    private static int fH;
    private static int fI;
    private static int fJ;
    private static int fK;
    private static int fL;
    private static int fM;
    private static int fN;
    private static int fO;
    private static int fP;
    private static int fQ;
    private static int fR;
    private static boolean fS;
    private static int fT;
    private static int fU;
    public static int Q;
    public static int R;
    private static boolean fV;
    private static String[] fW;
    private static int fX;
    private static int fY;
    private static int fZ;
    private static boolean ga;
    private static int gb;
    private static int gc;
    private static int gd;
    private static int ge;
    private static int gf;
    private static int gg;
    private static int gh;
    private static int gi;
    private static int gj;
    private static boolean gk;
    private static int gl;
    private static int gm;
    private static int gn;
    private static int go;
    private static int gp;
    private static int gq;
    private static int gr;
    private static int gs;
    private static int gt;
    private static int gu;
    private static int gv;
    private static int gw;
    private static int gx;
    private static int gy;
    private static int gz;
    private static int gA;
    private static int gB;
    private static int gC;
    private static int gD;
    private static int gE;
    private static int gF;
    private static int gG;
    private static int gH;
    private static int gI;
    private static int gJ;
    private static int gK;
    private static int gL;
    private static long gM;
    private static boolean gN;
    private static int gO;
    private static int gP;
    private static int gQ;
    private static int gR;
    private static int gS;
    private static int gT;
    private static int gU;
    private static int gV;
    private static int gW;
    private static int gX;
    private static int gY;
    private static int gZ;
    private static int ha;
    private static int hb;
    private static boolean hc;
    private static boolean hd;
    private static boolean he;
    private static int hf;
    private static int hg;
    private static int hh;
    private static boolean hi;
    private static int hj;
    private static int hk;
    private static int hl;
    private static int hm;
    private static int hn;
    private static int ho;
    private static int hp;
    private static int hq;
    private static int hr;
    private static int hs;
    private static int ht;
    private static int hu;
    private static int hv;
    private static int hw;
    private static int[][] hx;
    private static int[][] hy;
    private static int[][] hz;
    private static int[][] hA;
    private static int[][] hB;
    private static int[][] hC;
    private static int[] hD;
    private static int[] hE;
    private static int[] hF;
    private static int[] hG;
    private static int[] hH;
    private static int[] hI;
    private static boolean hJ;
    private static int hK;
    private static int hL;
    private static int hM;
    private static int hN;
    private static int hO;
    private static int hP;
    private static int hQ;
    private static int hR;
    private static int hS;
    private static int hT;
    private static int hU;
    private static int hV;
    private static int hW;
    private static int hX;
    private static int hY;
    private static int hZ;
    private static int ia;
    private static int ib;
    private static int ic;
    private static int id;
    private static boolean ie;
    private static int ifSt;
    private static int ig;
    private static int ih;
    private static int ii;
    private static int ij;
    private static int[] ik;
    private static int[] il;
    private static int[] im;
    private static int[] in;
    private static int[] io;
    private static int[] ip;
    private static int iq;
    private static int ir;
    private static int[] is;
    private static int[] it;
    private static int[] iu;
    private static int[] iv;
    private static int[] iw;
    private static int[] ix;
    private static int[] iy;
    private static boolean iz;
    private static int iA;
    private static int iB;
    private static int iC;
    private static int iD;
    private static String[][] iE;
    private static int iF;
    private static int iG;
    private static int iH;
    private static int iI;
    private static int iJ;
    private static int iK;
    private static Image tilesetImage;
    private static Image waterfallImage;
    private static Image waterSurfaceImage;
    private static Image[] houseImages;
    private static int iX;
    private static int[] iY;
    private static int[] iZ;
    private static int[] ja;
    private static int[] jb;
    private static int[] jc;
    private static Image bushImage;
    private static int jf;
    private static int jg;
    private static int jh;
    private static int ji;
    private static String[] jj;
    private static int[] jk;
    private static int[] jl;
    private static int[] jm;
    private static int jn;
    private static int jo;
    private static int jp;
    private static int jq;
    private static int jr;
    private static int js;
    private static int jt;
    private static int ju;
    private static int jv;
    private static int jw;
    private static int jx;
    private static int jy;
    private static int jz;
    private static int jA;
    static int S;
    private static int jB;
    static int T;
    private static Image logoImage;
    private static int jD;
    private static int jE;
    public static boolean U;
    private static Image[][][] monsterSprites;
    private static int jG;
    private static int jH;
    private static int[] jI;
    private static int[] jJ;
    private static int[] jK;
    private static int[] jL;
    private static int[] jM;
    private static int[] jN;
    private static int[] jO;
    private static int[] jP;
    private static int[] jQ;
    private static int[] jR;
    private static int[] jS;
    private static int[] jT;
    private static int[] jU;
    private static int[] jV;
    private static int[] jW;
    private static int[] jX;
    private static int[] jY;
    private static int[] jZ;
    private static int[] ka;
    private static int[] kb;
    private static int[] kc;
    private static int[] kd;
    private static int[] ke;
    private static int[] kf;
    private static int[] kg;
    private static boolean[] kh;
    private static int[][] ki;
    private static int[][] kj;
    private static boolean[][] kk;
    private static int[] kl;
    private static int[] km;
    static int V;
    private static int kn;
    private static int ko;
    private static int kp;
    private static int kq;
    private static int kr;
    private static int[] ks;
    private static String[][] kt;
    private static String[][] ku;
    private static int[][] kv;
    private static Image[] shopSwordIcons;
    private static int kz;
    private static int kA;
    private static int kB;
    private static int kC;
    private static int[] kD;
    private static String[] kE;
    static int[] W;
    private static int[] kF;
    private static Image[] cornerIcons;
    private static int kH;
    private static int kI;
    private static int kJ;
    private static String[] kK;
    private static String[] kL;
    private static int[] kM;
    public static boolean[] X;
    private static int[] kN;
    private static int[] kO;
    private static int[] kP;
    private static int[] kQ;
    private static Image worldMapImage;
    private static Image crossIcon;
    private static int kT;
    private static int kU;
    private static int kV;
    private static int kW;
    private static int kX;
    private static int kY;
    private static int kZ;
    private static int la;
    private static int lb;
    private static int lc;
    private static int ld;
    private static int le;
    private static int lf;
    private static int lg;
    private static int lh;
    private static String li;
    private static Image popupImage;
    private static boolean lk;
    private static int ll;
    public static int Y;
    private static int lm;
    private static int ln;
    private static int lo;
    private static String lp;
    private static String lq;
    private static String[] lr;
    private static Image[][] npcSprites;
    private static int lt;
    private static int[] lu;
    private static int[] lv;
    private static int[] lw;
    private static String[][] lx;
    private static String[][] ly;
    private static String[][] lz;
    private static String[][] lA;
    private static String[][] lB;
    private static String[] lC;
    private static int[] lD;
    private static int[] lE;
    private static int[] lF;
    private static int lG;
    private static int lH;
    private static int lI;
    private static int lJ;
    private static Font lP;
    private static int lR;

    static {
        aI = new int[ar.length][3][3];

        int var0;
        for(var0 = 0; var0 < ar.length; ++var0) {
            for(int var1 = 0; var1 < 3; ++var1) {
                for(int var2 = 0; var2 < 3; ++var2) {
                    aI[var0][var1][var2] = ar[var0][var1][var2];
                    if (var1 == 0 && var2 == 0) {
                        switch (ar[var0][var1][var2]) {
                            case 1:
                            case 2:
                                aI[var0][var1][var2] = 1;
                                break;
                            default:
                                aI[var0][var1][var2] = 0;
                        }
                    }
                }
            }
        }

        aJ = new int[]{-1, 2, 1, -2};
        aK = new int[]{-3, 2, -1, 1};
        aL = -1;
        b = true;
        aX = 0;
        bv = "";
        bG = -22;
        bH = 18;
        bI = -10;
        bJ = -19;
        bK = -12;
        bL = -26;
        eI = false;
        eJ = false;
        eK = false;
        eL = false;
        eM = false;
        eN = false;
        eO = false;
        eP = false;
        eQ = false;
        eR = false;
        eS = false;
        eT = false;
        eU = false;
        eV = false;
        eW = false;
        eX = false;
        eY = false;
        eZ = false;
        fa = false;
        fb = false;
        fu = true;
        fS = false;
        fU = 0;
        fW = new String[]{""};
        fZ = 0;
        gb = -1;
        gh = 1;
        gk = false;
        gm = -1;
        hj = 500;
        hk = 60;
        hl = 60;
        hK = 20;
        hL = 60;
        hO = 50;
        id = 10;
        ij = -1;
        ir = -1;
        iz = false;
        S = 0;
        jB = 0;
        T = 0;
        kl = null;
        km = new int[]{0, 10, 15, 20};
        ks = new int[]{12281360, 13859359, 15439166, 16693877, 16765346, 16777215, 16765346, 16693877, 15439166, 13859359, 12281360};
        kt = new String[][]{{"Bình HP nhỏ", 
                "Bình HP vừa", 
                "Bình HP to", 
                "Bình MP nhỏ", 
                "Bình MP vừa", 
                "Bình MP to"}, 
            {"Kiếm tre", 
                "Thủy linh kiếm", 
                "Hỏa giác kiếm", 
                "Long giao kiếm"}};
        ku = new String[][]{{"2000vnđ / 50 bình", 
                "3000vnđ / 50 bình", 
                "3000vnđ / 30 bình", 
                "2000vnđ / 50 bình", 
                "2000vnđ / 30 bình", 
                "3000vnđ / 50 bình"}, 
            {"2000vnđ", 
                "3000vnđ", 
                "3000vnđ", 
                "5000vnđ"}};
        kv = new int[][]{{200, 
                600, 
                1500, 
                200, 
                600, 
                1200}, 
            {2000, 
                12000, 
                45000, 
                182000}};
        kD = new int[]{12281360, 13859359, 15439166, 16693877, 16765346, 16777215, 16765346, 16693877, 15439166, 13859359, 12281360};
        kE = new String[]{"Bình HP nhỏ", 
                "Bình HP vừa", 
                "Bình HP to", 
                "Bình MP nhỏ", 
                "Bình MP vừa", 
                "Bình MP to"};
        W = new int[]{5, 2, 1, 5, 2, 1};
        kF = new int[]{100, 400, 1000, 100, 200, 300};
        kK = new String[]{"Sử dụng item", 
                "Xem bản đồ", 
                "Lưu và thoát", 
                "Nhạc: Bật"};
        kL = new String[]{"Trường Hirosaki", 
                "Khu luyện tập", 
                "Làng Kenshin", 
                "Rừng đào Sakura", 
                "Rừng trúc Ura", 
                "Thác Kitajima", 
                "Cánh đồng Mishima", 
                "Sông Watamaro", 
                "Nghĩa địa Izuko", 
                "Miếu Kojin", 
                "Miếu Kamo", 
                "Miếu Yazaka", 
                "Rừng gỗ Kouji", 
                "Rừng chông Hakata", 
                "Vách đá Ito", 
                "Thung lũng Taira", 
                "Thôn Sanzu", 
                "Lối vào Orochi", 
                "Ngôi đền Orochi"};
        kM = new int[]{50, 60, 70, 80, 90, 70, 100, 120, 150, 180, 220, 250, 300, 320, 350, 370, 390, 420, 450};
        X = new boolean[19];
        kN = new int[]{49, 31, 98, 112, 106, 42, 75, 84, 37, 39, 80, 117, 157, 201, 208, 170, 186, 219, 161};
        kO = new int[]{35, 65, 38, 75, 98, 97, 95, 128, 142, 185, 212, 189, 189, 204, 156, 142, 96, 59, 39};
        kP = new int[]{62, 52, 17, 4, 8, 12, 14, 5, 1, 2, 3, 2, 2, 2, 2, 1, 2, 3, 1};
        kQ = new int[]{17, 9, 12, 9, 10, 5, 9, 7, 12, 11, 4, 11, 8, 9, 7, 6, 11, 16, 8};
        li = "Chuyển đến";
        String[] var10000 = new String[]{"Quy Sư Phụ", 
                "Lâm Sư Phụ"};
        lx = new String[][]{{"..."}, 
            {"Đây là khu vực luyện tập, cứ yên tâm, nếu bị thương ta sẽ giúp đỡ.", 
                "Đôi khi tập luyện với cái bù nhìn rơm đằng kia cũng mang lại nhiều bất ngờ đấy!"}, 
            {"Thác nước này rất nguy hiểm, ta khuyên ngươi không nên xuống.", 
                "Hãy quay lại đi, nếu có chuyện gì ta không cứu được ngươi đâu."}, 
            {"Khu rừng trước mặt ngươi là nơi sống của bọn dơi quỷ, nhện độc và những sát thủ bị truy nã.", 
                "Dù ngươi tài giỏi đến đâu cũng phải mất mạng khi đi ngang qua đó.", 
                "Một số người có thể qua được bằng cách chạy thật nhanh qua.", 
                "Nếu ngươi tự tin hãy dùng chiếc moto kia và liều một phen xem.", 
                "Chúc ngươi may mắn."}};
        ly = new String[][]{{"Con lại tìm ta có chuyện gì?", 
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
        lz = new String[][]{{"Thể lực con rất tốt, ta sẽ dạy khinh công cho con...", 
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
        String[][] var3 = new String[][]{{"Ta đang bận."}, 
            {"Ngươi đã học được chiêu shuriken cuối cùng, ta ko còn gì dạy ngươi nữa. Đi đi."}};
        lA = new String[][]{{"Nhanh lên, hãy sang khu luyện tập và giết 10 con ốc sên."}, 
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
        lB = new String[][]{{"Ta đang bận, Con hãy đi tập luyện đi."}, 
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
        var10000 = new String[]{"Ngươi không có đủ vàng."};
        lC = new String[]{"Hãy hoàn thành nhiệm vụ được giao rồi quay lại gặp ta!"};
        lD = new int[]{1, 7, 100, 100, 6, 100, 100, 13, 100, 15, 100, 100, 18, 17, 10, 100, 100, 100};
        lE = new int[]{10, 2, 0, 0, 20, 0, 0, 8, 0, 10, 0, 0, 15, 18, 4, 0, 0, 0};
        lF = new int[]{7, 6, 6, 5, 2, 4};
        lG = -1;
        lP = Font.getFont(0, 0, 0);
        lR = lP.getHeight() + 6;

    }

    public static void a() {
        int[] var1 = new int[500];
        int var2 = 0;
        if (gM > 0L) {
            gL += (int)((System.currentTimeMillis() - gM) / 1000L);
        }

        gM = System.currentTimeMillis();
        ++var2;
        var1[0] = gL;
        ++var2;
        var1[1] = w;
        ++var2;
        var1[2] = x;
        ++var2;
        var1[3] = eE ? 1 : 0;
        ++var2;
        var1[4] = dd;
        ++var2;
        var1[5] = dw ? 1 : 0;
        ++var2;
        var1[6] = eF ? 1 : 0;
        ++var2;
        var1[7] = eG ? 1 : 0;
        ++var2;
        var1[8] = eH ? 1 : 0;
        ++var2;
        var1[9] = gk ? 1 : 0;
        ++var2;
        var1[10] = gl;
        ++var2;
        var1[11] = gm;
        ++var2;
        var1[12] = ij;
        ++var2;
        var1[13] = y;
        ++var2;
        var1[14] = eW ? 1 : 0;

        int var0;
        for(var0 = 0; var0 < X.length; ++var0) {
            var1[var2++] = X[var0] ? 1 : 0;
        }

        for(var0 = 0; var0 < W.length; ++var0) {
            var1[var2++] = W[var0];
        }

        var1[var2++] = eI ? 1 : 0;
        var1[var2++] = eJ ? 1 : 0;
        var1[var2++] = eK ? 1 : 0;
        var1[var2++] = eL ? 1 : 0;
        var1[var2++] = eM ? 1 : 0;
        var1[var2++] = eN ? 1 : 0;
        var1[var2++] = eO ? 1 : 0;
        var1[var2++] = eP ? 1 : 0;
        var1[var2++] = eQ ? 1 : 0;
        var1[var2++] = eR ? 1 : 0;
        var1[var2++] = eS ? 1 : 0;
        var1[var2++] = eT ? 1 : 0;
        var1[var2++] = eU ? 1 : 0;
        var1[var2++] = eV ? 1 : 0;
        var1[var2++] = eX ? 1 : 0;
        var1[var2++] = eY ? 1 : 0;
        var1[var2++] = eZ ? 1 : 0;
        var1[var2++] = fa ? 1 : 0;
        var1[var2++] = fb ? 1 : 0;
        var1[var2++] = fc;
        var1[var2++] = fd;
        var1[var2++] = fe;
        var1[var2++] = ff;
        var1[var2++] = z;
        var1[var2++] = A;
        var1[var2++] = B;
        var1[var2++] = C;
        var1[var2++] = fg;
        var1[var2++] = fh;
        var1[var2++] = fi;
        var1[var2++] = fj;
        var1[var2++] = fk;
        var1[var2++] = fl;
        var1[var2++] = fm;
        var1[var2++] = D;
        var1[var2++] = E;
        var1[var2++] = fn;
        var1[var2++] = PlayerStats.attack;
        var1[var2++] = PlayerStats.dartDamage;
        var1[var2++] = PlayerStats.hp;
        var1[var2++] = PlayerStats.maxHp;
        var1[var2++] = PlayerStats.mp;
        var1[var2++] = PlayerStats.maxMp;
        var1[var2++] = L;
        var1[var2++] = M;
        var1[var2++] = fo;
        var1[var2++] = fp;
        var1[var2++] = fq;
        var1[var2++] = fs;
        var1[var2++] = ft;
        var1[var2++] = PlayerStats.level;
        var1[var2++] = PlayerStats.exp;
        var1[var2++] = fu ? 1 : 0;
        var1[var2++] = fv ? 1 : 0;
        var1[var2++] = fw ? 1 : 0;
        var1[var2++] = fx ? 1 : 0;
        var1[var2++] = P;
        var1[var2++] = fy;
        var1[var2++] = fz;
        var1[var2++] = fA ? 1 : 0;
        var1[var2++] = fC;
        var1[var2++] = fD;
        var1[var2++] = fE;
        var1[var2++] = ig;
        var1[var2++] = ih;
        var1[var2++] = fF;
        var1[var2++] = fG;
        var1[var2++] = fH;
        var1[var2++] = fI;
        var1[var2++] = fJ;
        var1[var2++] = fK;
        var1[var2++] = fL;
        var1[var2++] = fM;
        var1[var2++] = fN;
        var1[var2++] = fO;
        var1[var2++] = fP;
        var1[var2++] = fQ;
        var1[var2++] = fR;
        var1[var2++] = ie ? 1 : 0;
        var1[var2++] = id;
        var1[var2++] = ifSt;
        var1[var2++] = gb;
        var1[var2++] = gc;
        var1[var2++] = gd;
        var1[var2++] = ge;
        var1[var2++] = gf;
        var1[var2++] = gg;
        var1[var2++] = gh;
        var1[var2++] = gi;
        var1[var2++] = gj;
        var1[var2++] = gn;
        var1[var2++] = go;
        var1[var2++] = gp;
        var1[var2++] = gq;
        var1[var2++] = gr;
        var1[var2++] = gs;
        var1[var2++] = gt;
        var1[var2++] = gu;
        var1[var2++] = gv;
        var1[var2++] = gw;
        var1[var2++] = gx;
        var1[var2++] = gy;
        var1[var2++] = gz;
        var1[var2++] = gA;
        var1[var2++] = gB;
        var1[var2++] = gC;
        var1[var2++] = gD;
        var1[var2++] = gE;
        var1[var2++] = gF;
        var1[var2++] = gG;
        var1[var2++] = gH;
        var1[var2++] = gI;
        var1[var2++] = gJ;
        var1[var2++] = gZ;
        var1[var2++] = hg;
        var1[var2++] = hm;
        var1[var2++] = hM;
        var1[var2++] = hr;
        var1[var2++] = ii;
        if (ii > 0) {
            for(var0 = 0; var0 < io.length; ++var0) {
                var1[var2++] = io[var0];
            }

            for(var0 = 0; var0 < im.length; ++var0) {
                var1[var2++] = im[var0];
            }

            for(var0 = 0; var0 < in.length; ++var0) {
                var1[var2++] = in[var0];
            }

            for(var0 = 0; var0 < ip.length; ++var0) {
                var1[var2++] = ip[var0];
            }

            for(var0 = 0; var0 < ik.length; ++var0) {
                var1[var2++] = ik[var0];
            }

            for(var0 = 0; var0 < il.length; ++var0) {
                var1[var2++] = il[var0];
            }
        }

        var1[var2++] = iq;
        if (iq > 0) {
            var1[var2++] = ir;
            if (iu != null) {
                for(var0 = 0; var0 < iu.length; ++var0) {
                    var1[var2++] = iu[var0];
                }
            }

            if (iy != null) {
                for(var0 = 0; var0 < iy.length; ++var0) {
                    var1[var2++] = iy[var0];
                }
            }

            if (ix != null) {
                for(var0 = 0; var0 < ix.length; ++var0) {
                    var1[var2++] = ix[var0];
                }
            }

            if (iv != null) {
                for(var0 = 0; var0 < iv.length; ++var0) {
                    var1[var2++] = iv[var0];
                }
            }

            if (iw != null) {
                for(var0 = 0; var0 < iw.length; ++var0) {
                    var1[var2++] = iw[var0];
                }
            }

            if (is != null) {
                for(var0 = 0; var0 < is.length; ++var0) {
                    var1[var2++] = is[var0];
                }
            }

            if (it != null) {
                for(var0 = 0; var0 < it.length; ++var0) {
                    var1[var2++] = it[var0];
                }
            }
        }

        int[] var5 = var1;
        int var4;
        byte[] var6 = new byte[(var4 = var1.length) << 2];

        for(int var3 = 0; var3 < var4; ++var3) {
            var6[var3 << 2] = (byte)(var5[var3] >> 24);
            var6[(var3 << 2) + 1] = (byte)(var5[var3] >>> 16);
            var6[(var3 << 2) + 2] = (byte)(var5[var3] >>> 8);
            var6[(var3 << 2) + 3] = (byte)var5[var3];
        }

        Save.saveRecord("ninjasave", var6);
        Save.markSaved();
    }

    public static void b() {
        int[] var0 = a(Save.loadRecord("ninjasave"));
        int var2 = 0;
        ++var2;
        gL = var0[0];
        ++var2;
        w = var0[1];
        ++var2;
        x = var0[2];
        ++var2;
        eE = var0[3] == 1;
        ++var2;
        dd = var0[4];
        ++var2;
        dw = var0[5] == 1;
        ++var2;
        eF = var0[6] == 1;
        ++var2;
        eG = var0[7] == 1;
        ++var2;
        eH = var0[8] == 1;
        ++var2;
        gk = var0[9] == 1;
        ++var2;
        gl = var0[10];
        ++var2;
        gm = var0[11];
        ++var2;
        ij = var0[12];
        ++var2;
        y = var0[13];
        ++var2;
        eW = var0[14] == 1;

        int var1;
        for(var1 = 0; var1 < X.length; ++var1) {
            X[var1] = var0[var2++] == 1;
        }

        for(var1 = 0; var1 < W.length; ++var1) {
            W[var1] = var0[var2++];
        }

        eI = var0[var2++] == 1;
        eJ = var0[var2++] == 1;
        eK = var0[var2++] == 1;
        eL = var0[var2++] == 1;
        eM = var0[var2++] == 1;
        eN = var0[var2++] == 1;
        eO = var0[var2++] == 1;
        eP = var0[var2++] == 1;
        eQ = var0[var2++] == 1;
        eR = var0[var2++] == 1;
        eS = var0[var2++] == 1;
        eT = var0[var2++] == 1;
        eU = var0[var2++] == 1;
        eV = var0[var2++] == 1;
        eX = var0[var2++] == 1;
        eY = var0[var2++] == 1;
        eZ = var0[var2++] == 1;
        fa = var0[var2++] == 1;
        fb = var0[var2++] == 1;
        fc = var0[var2++];
        fd = var0[var2++];
        fe = var0[var2++];
        ff = var0[var2++];
        z = var0[var2++];
        A = var0[var2++];
        B = var0[var2++];
        C = var0[var2++];
        fg = var0[var2++];
        fh = var0[var2++];
        fi = var0[var2++];
        fj = var0[var2++];
        fk = var0[var2++];
        fl = var0[var2++];
        fm = var0[var2++];
        D = var0[var2++];
        E = var0[var2++];
        fn = var0[var2++];
        PlayerStats.attack = var0[var2++];
        PlayerStats.dartDamage = var0[var2++];
        PlayerStats.hp = var0[var2++];
        PlayerStats.maxHp = var0[var2++];
        PlayerStats.mp = var0[var2++];
        PlayerStats.maxMp = var0[var2++];
        L = var0[var2++];
        M = var0[var2++];
        fo = var0[var2++];
        fp = var0[var2++];
        fq = var0[var2++];
        fs = var0[var2++];
        ft = var0[var2++];
        PlayerStats.level = var0[var2++];
        PlayerStats.exp = var0[var2++];
        fu = var0[var2++] == 1;
        fv = var0[var2++] == 1;
        fw = var0[var2++] == 1;
        fx = var0[var2++] == 1;
        P = var0[var2++];
        fy = var0[var2++];
        fz = var0[var2++];
        fA = var0[var2++] == 1;
        fC = var0[var2++];
        fD = var0[var2++];
        fE = var0[var2++];
        ig = var0[var2++];
        ih = var0[var2++];
        fF = var0[var2++];
        fG = var0[var2++];
        fH = var0[var2++];
        fI = var0[var2++];
        fJ = var0[var2++];
        fK = var0[var2++];
        fL = var0[var2++];
        fM = var0[var2++];
        fN = var0[var2++];
        fO = var0[var2++];
        fP = var0[var2++];
        fQ = var0[var2++];
        fR = var0[var2++];
        ie = var0[var2++] == 1;
        id = var0[var2++];
        ifSt = var0[var2++];
        gb = var0[var2++];
        gc = var0[var2++];
        gd = var0[var2++];
        ge = var0[var2++];
        gf = var0[var2++];
        gg = var0[var2++];
        gh = var0[var2++];
        gi = var0[var2++];
        gj = var0[var2++];
        gn = var0[var2++];
        go = var0[var2++];
        gp = var0[var2++];
        gq = var0[var2++];
        gr = var0[var2++];
        gs = var0[var2++];
        gt = var0[var2++];
        gu = var0[var2++];
        gv = var0[var2++];
        gw = var0[var2++];
        gx = var0[var2++];
        gy = var0[var2++];
        gz = var0[var2++];
        gA = var0[var2++];
        gB = var0[var2++];
        gC = var0[var2++];
        gD = var0[var2++];
        gE = var0[var2++];
        gF = var0[var2++];
        gG = var0[var2++];
        gH = var0[var2++];
        gI = var0[var2++];
        gJ = var0[var2++];
        if ((gZ = var0[var2++]) <= 0) {
            gZ = ha;
        }

        hg = var0[var2++];
        hm = var0[var2++];
        hM = var0[var2++];
        hr = var0[var2++];
        int var10001 = var2++;
        int var10000 = var0[var10001];
        var10001 = var0[var10001];
        if (var10000 > 0) {
            try {
                for(var1 = 0; var1 < io.length; ++var1) {
                    ++var2;
                }

                for(var1 = 0; var1 < im.length; ++var1) {
                    ++var2;
                }

                for(var1 = 0; var1 < in.length; ++var1) {
                    ++var2;
                }

                for(var1 = 0; var1 < ip.length; ++var1) {
                    ++var2;
                }

                for(var1 = 0; var1 < ik.length; ++var1) {
                    ++var2;
                }

                for(var1 = 0; var1 < il.length; ++var1) {
                    ++var2;
                }
            } catch (Exception var5) {
            }
        }

        if ((iq = var0[var2++]) > 0) {
            ir = var0[var2++];

            try {
                for(var1 = 0; var1 < iu.length; ++var1) {
                    iu[var1] = var0[var2++];
                }

                for(var1 = 0; var1 < iy.length; ++var1) {
                    iy[var1] = var0[var2++];
                }

                for(var1 = 0; var1 < ix.length; ++var1) {
                    ix[var1] = var0[var2++];
                }

                for(var1 = 0; var1 < iv.length; ++var1) {
                    iv[var1] = var0[var2++];
                }

                for(var1 = 0; var1 < iw.length; ++var1) {
                    iw[var1] = var0[var2++];
                }

                for(var1 = 0; var1 < is.length; ++var1) {
                    is[var1] = var0[var2++];
                }

                for(var1 = 0; var1 < it.length; ++var1) {
                    it[var1] = var0[var2++];
                }
            } catch (Exception var4) {
            }
        }

        if (fx) {
            if (heroTransformHeads == null) {
                heroTransformHeads = new Image[4];

                try {
                    heroTransformHeads[0] = Character.loadHeroTransformHead(0);
                    heroTransformHeads[1] = Character.loadHeroTransformHead(1);
                } catch (Exception var3) {
                }

                heroTransformHeads[2] = heroTransformHeads[1];
                heroTransformHeads[3] = heroTransformHeads[0];
            }

            for(var1 = 0; var1 < 4; ++var1) {
                Image var6 = heroHeads[var1];
                heroHeads[var1] = heroTransformHeads[var1];
                heroTransformHeads[var1] = var6;
            }
        }

    }

    private static int[] a(byte[] var0) {
        int var1;
        int[] var3 = new int[(var1 = var0.length) >> 2];

        for(int var2 = 0; var2 < var3.length; ++var2) {
            var3[var2] = (var0[var2 << 2] << 24) + ((var0[(var2 << 2) + 1] & 255) << 16) + ((var0[(var2 << 2) + 2] & 255) << 8) + (var0[(var2 << 2) + 3] & 255);
        }

        return var3;
    }

    public static final int a(int var0) {
        return var0 > 0 ? var0 : -var0;
    }

    private static void a(int var0, int var1, int var2, int var3, int var4) {
        int var5;
        for(var5 = 0; var5 < de && dh[var5] != 0; ++var5) {
        }

        if (var5 != de) {
            df[var5] = var0;
            dg[var5] = var1;
            di[var5] = var2;
            dj[var5] = var3;
            dh[var5] = 1;
            dl[var5] = var4;
            if (var4 < 160) {
                dm[var5] = Item.MONEY_GOLD;
            } else if (var4 < 600) {
                dm[var5] = Item.MONEY_BLUE_GEM;
            } else {
                dm[var5] = Item.MONEY_RED_GEM;
            }
        }
    }

    private static void a(int var0, int var1, int var2, int var3, int var4, int var5) {
        for(var3 = 0; var3 < cM && cQ[var3] != 0; ++var3) {
        }

        if (var3 != cM) {
            cO[var3] = var0;
            cP[var3] = var1;
            cR[var3] = var2;
            cS[var3] = -5;
            cQ[var3] = 1;
            cU[var3] = var4;
            cN[var3] = var5;
            if (var4 < 100) {
                cT[var3] = 0;
            } else if (var4 < 200) {
                cT[var3] = 1;
            } else {
                cT[var3] = 2;
            }
        }
    }

    private static void gainExp(int amount) {
        if (Leveling.addExp(amount)) {
            a("level-up", 2, fe, ff - fh, 0, -2);
            s(6);
            a("Chúc mừng! Bạn đạt được cấp " + PlayerStats.level + '\n' + "HP: " + PlayerStats.maxHp + '\n' + "MP: " + PlayerStats.maxMp + '\n' + "Sức đánh: " + PlayerStats.getDisplayedAttack(y));
            e = 7;
        } else {
            a("+" + amount, 2, fe, ff - fh, 0, -2);
        }

        d = true;
    }

    private static void u() {
        if (P == 11 && A != 0) {
            ++fC;
            if (P >= 20) {
                b("Thành công 20 hit!");
            } else {
                b("Hit: " + fC);
            }
        }

        int var0 = PlayerStats.rollMeleeDamage(x, y);
        if (fx) {
            var0 <<= 1;
        }

        if (fd == 1) {
            a(fe + (fm << 3), ff - 12, (fm << 3) + z, A << 1, false);
        } else {
            if (A > 0 && z == 0) {
                A = -2;
            }

            int var1 = A();
            fr = false;
            if (var1 != -1) {
                fr = true;
                k(var1, var0, -fm);
                f(jJ[var1], jK[var1] - (jL[var1] >> 1), fm);
                a("-" + var0, 0, jJ[var1], jK[var1] - jL[var1], 0, -2);
                if (A > 0) {
                    A = -6;
                }

                Q();
            }

            for(var1 = 0; var1 < iq; ++var1) {
                if (a(ff - fj - it[var1]) < 20 && a(fe + (fm << 4) - is[var1]) < 24) {
                    iu[var1] = 2;
                    iv[var1] = fm << 3;
                    f(is[var1], it[var1] - 12, fm);
                    Q();
                    break;
                }
            }

            if (gN && a(ff - gQ) < 30 && a(fe + (fm << 4) - gP) < 40) {
                if (!c(var0, -fm)) {
                    f(fe + (gP - fe >> 1), ff - 12, fm);
                    a("-" + var0, 0, gP, gQ - gW, 0, -2);
                }

                Q();
            }

            for(var1 = 0; var1 < cp; ++var1) {
                if (a(ff - cv[var1]) <= 12 && a(fe + (fm << 4) - cu[var1]) < 24) {
                    a(var1, var0, -fm);
                    f(cu[var1], cv[var1] - 15, fm);
                    a("-" + var0, 0, cu[var1], cv[var1] - 28, 0, -2);
                    Q();
                    break;
                }
            }

            boolean var10000;
            if (hJ && a(ff - (hQ - 40)) < 30 && a(fe + (fm << 4) - hP) < 40) {
                if ((hM -= var0) <= 0) {
                    hM = 0;
                    ib = 0;
                    ic = 0;
                    hX = 1;
                    a(hP, hQ, 4, -10, 600);
                    a(hP, hQ, -4, -17, 600);
                    a(hP, hQ, -3, -18, 600);
                    a(hP, hQ, 4, -19, 600);
                    a(hP, hQ, 0, -10, 600);
                    var10000 = false;
                } else {
                    ic = 50;
                    var10000 = false;
                }

                f(fe + (hP - fe >> 1), ff - 12, fm);
                a("-" + var0, 0, hP, hQ - hL, 0, -2);
                Q();
            }

            if (hc) {
                if (a(ff - dA[0]) < 5 && a(fe + (fm << 4) - dz[0]) < 30) {
                    d(var0, fm);
                }

                Q();
            }

            if (hi) {
                if (a(ff - (hq - 30)) < 30 && a(fe + (fm << 4) - hp) < 30) {
                    if ((hm -= var0) <= 0) {
                        hm = 0;
                        hv = 0;
                        ho = 0;
                        hr = 6;
                        a(hp, hq, 4, -10, 500);
                        a(hp, hq, -4, -17, 500);
                        a(hp, hq, -3, -18, 500);
                        a(hp, hq, 4, -19, 500);
                        a(hp, hq, 0, -10, 500);
                        var10000 = false;
                    } else {
                        ho = 50;
                        f(hp, hq - 47, fm);
                        a("-" + var0, 0, hp, hq - 55, 0, -2);
                        var10000 = false;
                    }
                }

                Q();
            }

        }
    }

    private static void v() {
        int var0;
        int var1;
        if (ih == 1) {
            var0 = gz << 3;
            var1 = ff - go >> 3;
            a(gn + (gz << 3), go - 12, var0, var1 + (var0 - 8), true);
        } else {
            var0 = PlayerStats.attack << 1;
            var1 = gz > 0 ? gn - 20 : gn - 40;
            int var2 = gz > 0 ? gn + 40 : gn + 20;
            int var3 = go - gu - 15;
            int var4 = go + 5;
            int var5 = 0;

            int var10000;
            while(true) {
                if (var5 >= jG) {
                    var10000 = -1;
                    break;
                }

                if (jO[var5] != 5 && jJ[var5] >= var1 && jJ[var5] <= var2 && jK[var5] >= var3 && jK[var5] <= var4) {
                    var10000 = var5;
                    break;
                }

                ++var5;
            }

            var1 = var10000;
            if (var10000 != -1) {
                k(var1, var0, -gz);
                f(jJ[var1], jK[var1] - (jL[var1] >> 1), gz);
                a("-" + var0, 0, jJ[var1], jK[var1] - jL[var1], 0, -2);
            }

            if (gN && a(go - gQ) < 30 && a(gn + (gz << 4) - gP) < 40 && !c(var0, -gz)) {
                f(gn + (gP - gn >> 1), go - 12, gz);
                a("-" + var0, 0, gP, gQ - gW, 0, -2);
            }

            for(var1 = 0; var1 < cp; ++var1) {
                if (go == cv[var1] && a(gn + (gz << 4) - cu[var1]) < 24) {
                    a(var1, var0, -gz);
                    f(cu[var1], cv[var1] - 15, gz);
                    a("-" + var0, 0, cu[var1], cv[var1] - 28, 0, -2);
                    return;
                }
            }

        }
    }

    private static void w() {
        for(int var0 = 0; var0 < iq; ++var0) {
            if (iu[var0] != 4 && a(fe - is[var0]) < 20 && it[var0] > ff && it[var0] - 24 <= ff) {
                z = 0;
                A = 0;
                ff = it[var0] - 24;
                B = 1;
                fc = -1;
                ir = var0;
                if (iu[var0] == 11) {
                    g(is[var0], it[var0] - 10);
                    int[] var10000 = it;
                    var10000[var0] += 4;
                    var10000 = iw;
                    var10000[var0] += 4;
                    return;
                }
                break;
            }
        }

    }

    private static void x() {
        for(int var0 = 0; var0 < iq; ++var0) {
            if (a(fe - is[var0]) < 20 && a(it[var0] - ff) < 4) {
                z = 0;
                if (fe < is[var0]) {
                    fe = is[var0] - 20;
                } else {
                    fe = is[var0] + 20;
                }

                if (iu[var0] != 4) {
                    iu[var0] = 2;
                    iv[var0] = -fm << 1;
                    return;
                }
            }
        }

    }

    private static void v(int var0) {
        if (a(ff - cv[var0]) <= 24 && a(cu[var0] + (cH[var0] << 3) - fe) < 25 && (cD[var0] == 6 || cD[var0] == 2 || cD[var0] == 3)) {
            cG[var0] = cF[var0] = 0;
            cE[var0] = 4;
        }

    }

    private static void w(int var0) {
        if (!fw) {
            if (a(cu[var0] - fe) < 100 && a(cv[var0] - ff) < 20) {
                switch (cD[var0]) {
                    case 0:
                    case 1:
                    case 4:
                    case 5:
                        cH[var0] = cu[var0] < fe ? 1 : -1;
                        cG[var0] = cF[var0] = 0;
                        cE[var0] = 4;
                        break;
                    case 2:
                    case 3:
                    case 6:
                        cG[var0] = fe;
                        int[] var10000;
                        if (cu[var0] < fe) {
                            var10000 = cG;
                            var10000[var0] -= 10;
                        } else {
                            var10000 = cG;
                            var10000[var0] += 10;
                        }

                        cH[var0] = cG[var0] < fe ? 1 : -1;
                        cE[var0] = 7;
                        cy[var0] = cH[var0] << 3;
                        if (cG[var0] < cK[var0]) {
                            cG[var0] = cK[var0];
                        }

                        if (cG[var0] > cL[var0]) {
                            cG[var0] = cL[var0];
                            return;
                        }
                }
            }

        }
    }

    private static void y() {
        for(int var0 = 0; var0 < iq; ++var0) {
            if (a(fe - is[var0]) < 20 && it[var0] == ff) {
                z = 0;
                if (fe < is[var0]) {
                    fe = is[var0] - 20;
                } else {
                    fe = is[var0] + 20;
                }

                if (iu[var0] != 4) {
                    iu[var0] = 2;
                    iv[var0] = fm << 2;
                    return;
                }
            }
        }

    }

    private static void z() {
        label58:
        switch (iD) {
            case 3:
                P = 0;
                fC = 0;
                b("NV: Giết 10 ốc sên");
                break;
            case 4:
                eI = true;
                eJ = true;
                P = -1;
                fC = 0;
                b("đã học phép khinh công");
                break;
            case 5:
                P = 1;
                fC = 0;
                b("NV: đánh ngã 2 bù nhìn");
                break;
            case 6:
                eK = true;
                P = -1;
                fC = 0;
                b("đã học ném phi tiêu");
            case 7:
            default:
                break;
            case 8:
                b((String)null);
                break;
            case 9:
                P = 2;
                fy = 103;
                fz = 2;
                fA = false;
                b("NV: Tìm bí kíp");
                break;
            case 10:
                eL = true;
                P = -1;
                fy = 0;
                fz = 0;
                fA = false;
                b("đã học thuật độn thổ");
                break;
            case 11:
                P = 3;
                gm = 3;
                gn = 2808;
                go = 216;
                gz = -1;
                gr = 1;
                gp = 5;
                break;
            case 12:
                eN = true;
                PlayerStats.attack <<= 1;
                P = -1;
                fy = 0;
                fz = 0;
                fA = false;
                b("sức công nhân đôi!");
                break;
            case 13:
                P = 4;
                fC = 0;
                b("NV: giết 20 con ong");
                break;
            case 14:
                P = 5;
                fA = false;
                fC = 0;
                fz = 10;
                b("NV: giết heo rừng");
                break;
            case 15:
                P = -1;
                eO = true;
                eP = true;
                b("đã học chạy nước.");
                break;
            case 16:
                P = 6;
                fA = false;
                fz = 10;
                b("NV: vượt sông");
                break;
            case 17:
                P = -1;
                eQ = true;
                b("đã học biến hình.");
                break;
            case 18:
                P = 7;
                fC = 0;
                b("NV: giết 8 dơi vàng");
                break;
            case 19:
                eR = true;
                dd += 5000;
                d = true;
                P = -1;
                fy = 0;
                fz = 0;
                fA = false;
                b("Nhận được tiền 5000$");
                break;
            case 20:
                P = 15;
                fC = 0;
                fA = false;
                b("NV: tìm bí kíp");
                break;
            case 21:
                eZ = true;
                P = -1;
                fC = 0;
                fA = false;
                b("đã có thể ẩn thân");
                break;
            case 22:
                P = 8;
                b("NV: tìm đánh Toniku");
                break;
            case 23:
                P = -1;
                eS = true;
                b("Hoàn thành nhiệm vụ.");
                break;
            case 24:
                P = 9;
                fC = 0;
                b("NV: giết 10 thổ dân");
                break;
            case 25:
                eT = true;
                P = -1;
                fy = 0;
                fz = 0;
                fA = false;
                b("đến gặp cô bé!");
                break;
            case 26:
                P = 10;
                fC = 0;
                gk = true;
                b("NV: dẫn cô bé đi chơi");
                break;
            case 27:
                P = -1;
                eV = true;
                fC = 0;
                b("Học khinh công cấp 2");
                break;
            case 28:
                P = 11;
                fC = 0;
                b("NV: 20 hit trên không");
                break;
            case 29:
                eW = true;
                PlayerStats.dartDamage <<= 2;
                P = -1;
                fC = 0;
                b("đã học phi tiêu cấp 2");
                Effect.loadDarts(dartFrames, Effect.DART_LEVEL_2);
                break label58;
            case 30:
                P = 12;
                fC = 0;
                b("NV: đánh 15 quỷ hoa đỏ");
                break;
            case 31:
                eX = true;
                P = -1;
                fC = 0;
                b("đã có thể chạy nhanh");
                break;
            case 32:
                P = 13;
                fC = 0;
                b("NV: Giết 18 bò cạp");
                break;
            case 33:
                eY = true;
                P = -1;
                fC = 0;
                b("đã có thể đứng nước");
                break;
            case 34:
                P = 14;
                fC = 0;
                b("NV: Giết 4 cá đỏ");
                break;
            case 35:
                P = -1;
                fC = 0;
                fA = false;
                break;
            case 36:
                P = 16;
                fC = 0;
                fA = false;
                b("NV: cứu Sakura");
        }

        if (lG >= 0) {
            a(lG, lH, lI, lJ);
            lG = -1;
        }

    }

    private static int A() {
        int var0 = fm > 0 ? fe - 20 : fe - 40;
        int var1 = fm > 0 ? fe + 40 : fe + 20;
        int var2 = ff - fh - 15;
        int var3 = ff + 5;

        for(int var4 = 0; var4 < jG; ++var4) {
            if (jO[var4] != 5 && jJ[var4] >= var0 && jJ[var4] <= var1 && jK[var4] >= var2 && jK[var4] <= var3) {
                return var4;
            }
        }

        return -1;
    }

    private static void B() {
        npcSprites = null;
        System.gc();
        motorbikeFrames = null;
        itemCrate = null;
        boss0Sprites = null;
        System.gc();
        boss1Sprites = null;
        System.gc();
        monsterSprites = null;
        System.gc();
        C();
        classmateHeads = null;
        npcDog = null;
        enemyProjectiles = null;
        System.gc();
    }

    private static void C() {
        ninjaLegs = null;
        ninjaBodies = null;
        System.gc();
    }

    private static void D() {
        int var0;
        for(var0 = 0; var0 < jG; ++var0) {
            if (jO[var0] != 5 && a(jK[var0] - go) < 20 && a(jJ[var0] - gn) < 40) {
                gz = jJ[var0] > gn ? 1 : -1;
                gr = 5;
                ih = 0;
                gA = 0;
                gB = 0;
                return;
            }
        }

        for(var0 = 0; var0 < cp; ++var0) {
            if (cE[var0] != 5 && cv[var0] == go && a(cu[var0] - gn) < 40) {
                gz = cu[var0] > gn ? 1 : -1;
                gr = 5;
                ih = 0;
                gA = 0;
                gB = 0;
                return;
            }
        }

        if (a(fe - gn) > 25 && ff != go && B == 5) {
            gz = fe > gn ? 1 : -1;
            gr = 5;
            ih = 1;
            gA = 0;
            gB = 0;
        }
    }

    private static void E() {
        if (gk) {
            if (ff <= go - 24 && GameMap.hasFlag(gn, go - 1, TileFlags.SOLID)) {
                gr = 3;
                gp = 0;
                gq = -7;
                return;
            }

            if (ff <= go - 24 && GameMap.hasFlag(gn, go - 25, TileFlags.SOLID)) {
                gr = 3;
                gp = 0;
                gq = -10;
                return;
            }

            int var0;
            int var1;
            if (gn >= fO - 24 && gn <= fO + r + 24) {
                if (go < fP - 30 || go > fP + s + 30) {
                    gz = fe + (fm << 5) > gn ? 1 : -1;
                    var0 = fe;
                    var1 = ff > go ? fP - 20 : fP + s;
                    gn = var0;
                    go = var1;
                    gr = 3;
                    gp = gz << 2;
                    gq = -4;
                    return;
                }

                if (a(fe + (fm << 5) - gn) <= (B == 5 ? 10 : 60)) {
                    gr = 1;
                } else if (ff >= go) {
                    if (ff > go) {
                        if (GameMap.hasFlag(gn + gz * gt, go, TileFlags.SOLID)) {
                            gz = fe > gn ? 1 : -1;
                            gr = 2;
                            gp = gz * Q;
                            return;
                        }

                        if (GameMap.hasFlag(gn + gz * 48, go + 24, TileFlags.SOLID)) {
                            gr = 3;
                            gp = gz * 7;
                            gq = -5;
                            return;
                        }

                        for(var0 = 1; var0 <= 5 && go + var0 * 24 < GameMap.pixelHeight && !GameMap.hasFlag(gn + gz * 24, go + var0 * 24, TileFlags.WATER); ++var0) {
                            if (GameMap.hasFlag(gn + gz * 24, go + var0 * 24, TileFlags.SOLID)) {
                                gr = 3;
                                gp = gz << 2;
                                gq = -4;
                                return;
                            }

                            if (GameMap.hasFlag(gn + gz * 48, go + var0 * 24, TileFlags.SOLID)) {
                                gr = 3;
                                gp = gz * 6;
                                gq = -4;
                                return;
                            }
                        }

                        if (GameMap.hasFlag(gn + gz * 24, go - 24, TileFlags.SOLID)) {
                            gr = 3;
                            gp = gz * 5;
                            gq = -7;
                            return;
                        }

                        if (GameMap.hasFlag(gn + gz * 48, go - 24, TileFlags.SOLID)) {
                            gr = 3;
                            gp = gz * 6;
                            gq = -8;
                            return;
                        }

                        if (GameMap.hasFlag(gn + gz * 24, go - 48, TileFlags.SOLID)) {
                            gr = 3;
                            gp = gz * 5;
                            gq = -11;
                            return;
                        }
                    } else if (GameMap.hasFlag(gn + gz * gt, go, TileFlags.SOLID)) {
                        gz = fe + (fm << 5) > gn ? 1 : -1;
                        gr = 2;
                        gp = gz * Q;
                        return;
                    }
                } else {
                    if (GameMap.hasFlag(gn + gz * 48, go - 24, TileFlags.SOLID)) {
                        gr = 3;
                        gp = gz * 5;
                        gq = -8;
                        return;
                    }

                    for(var0 = 1; var0 <= 3; ++var0) {
                        if (GameMap.hasFlag(gn + gz * 24, go - var0 * 24, TileFlags.SOLID)) {
                            gr = 3;
                            gp = gz * 6;
                            gq = -5 - var0 * 3;
                            return;
                        }
                    }

                    if (!GameMap.hasFlag(gn + gz * 48, go - 2, TileFlags.WATER)) {
                        if (go + 24 >= GameMap.pixelHeight || GameMap.hasFlag(gn + gz * 48, go + 24, TileFlags.SOLID)) {
                            gr = 3;
                            gp = gz * 7;
                            gq = -4;
                            return;
                        }

                        var0 = 1;

                        while(true) {
                            if (var0 > 5) {
                                return;
                            }

                            if (go + var0 * 24 >= GameMap.pixelHeight || GameMap.hasFlag(gn + gz * 24, go + var0 * 24, TileFlags.WATER)) {
                                break;
                            }

                            if (GameMap.hasFlag(gn + gz * 48, go + var0 * 24, TileFlags.SOLID)) {
                                gr = 3;
                                gp = gz * 6;
                                gq = -4;
                                return;
                            }

                            if (GameMap.hasFlag(gn + gz * 24, go + var0 * 24, TileFlags.SOLID)) {
                                gr = 3;
                                gp = gz << 2;
                                gq = -4;
                                return;
                            }

                            ++var0;
                        }
                    }
                }
            } else {
                gz = fe + (fm << 5) > gn ? 1 : -1;
                var0 = fe > gn ? fO - 24 : fO + r + 24;
                var1 = ff - 20;
                if (!GameMap.hasFlag(var0, var1, TileFlags.DIGGABLE)) {
                    gn = var0;
                    go = var1;
                    gr = 4;
                    gp = 4;
                    gq = 3;
                    return;
                }

                if (GameMap.hasFlag(gn + gz * gt, go, TileFlags.SOLID)) {
                    gz = fe > gn ? 1 : -1;
                    gr = 2;
                    gp = gz * 12;
                    return;
                }
            }
        }

    }

    private static void a(int var0, int var1) {
        boolean dead = PlayerStats.takeDamage(var0);
        fc = -1;
        d = true;
        ir = -1;
        if (dead) {
            A = -7;
            z = var1 << 2;
            B = 6;
            ij = -1;
        } else if (ij == -1 && B != 23) {
            z = var1 << 2;
            A = -2;
            D = 0;
            E = 0;
            B = 7;
        }

        f(fe, ff - 12, -fm);
        a("-" + var0, 0, fe, ff - fh, 0, -2);
    }

    private static void b(int var0, int var1) {
        ig = -1;
        gp = var1 << 2;
        gq = -2;
        gA = 0;
        gB = 0;
        gr = 7;
        f(gn, go - 12, -gz);
        a("-" + var0, 0, gn, go - gu, 0, -2);
    }

    private static boolean a(int var0, int var1, int var2) {
        int[] var10000 = cr;
        var10000[var0] -= var1;
        if (cE[var0] != 1 && cE[var0] != 5) {
            if (cr[var0] <= 0) {
                cr[var0] = 0;
                cH[var0] = var2;
                cy[var0] = 0;
                cz[var0] = 0;
                cA[var0] = -(var2 << 3);
                cB[var0] = -6;
                cF[var0] = 0;
                cq[var0] = 0;
                cE[var0] = 1;
                gainExp(ad[cD[var0]]);
                a(cu[var0], cv[var0], -(cH[var0] << 2), -4, ab[cD[var0]] + x % 10);
                if (x % 20 > 5) {
                    a(cu[var0], cv[var0], -(cH[var0] << 3), -5, ac[cD[var0]] << 2, (x + 1) % 2);
                }

                return true;
            } else {
                cF[var0] = 0;
                cH[var0] = var2;
                cq[var0] = 100;
                cE[var0] = 6;
                return false;
            }
        } else {
            return true;
        }
    }

    private static boolean c(int var0, int var1) {
        if (gS == 1 || gS == 2) {
            if ((gZ -= var0) <= 0) {
                gZ = 0;
                gX = -5;
                gR = -(var1 << 3);
                hb = 0;
                gS = 6;
                a(gP, gQ, 4, -6, 50);
                a(gP, gQ, -5, -7, 50);
                a(gP, gQ, -4, -8, 50);
                a(gP, gQ, 5, -9, 50);
                a(gP, gQ, 0, -10, 50);
                if (P == 5) {
                    e(gP, gQ);
                }

                return true;
            }

            gX = 0;
            gT = var1;
            gS = 7;
            hb = 10;
            gU = 0;
            if (var0 <= PlayerStats.dartDamage) {
                gY = 0;
            } else {
                gY = 2 + x % 5;
            }
        }

        return false;
    }

    private static void d(int var0, int var1) {
        hg -= var0;
        dI[0] = -1;
        if (hg <= 0) {
            hg = 0;
        } else {
            dB[0] = var1 << 2;
            dC[0] = -2;
            dE[0] = 0;
            dF[0] = 0;
            dD[0] = 7;
            f(dz[0], dA[0] - 12, fm);
            a("-" + var0, 0, dz[0], dA[0] - 32, 0, -2);
        }
    }

    public static final void b(int var0) {
        bM = 0;
        if (var0 == 0) {
            bM = 0;
        } else {
            enemyProjectiles = Effect.ensureProjectiles(enemyProjectiles);

            bM = 10;
            bN = new int[10];
            bO = new int[bM];
            bP = new int[bM];
            bQ = new int[bM];
            bR = new int[bM];
            bS = new int[bM];

            for(var0 = 0; var0 < bM; ++var0) {
                bP[var0] = -1;
            }

        }
    }

    public static void e() {
        if (arrowIcons == null) {
            arrowIcons = new Image[4];

            for(int var0 = 0; var0 < 4; ++var0) {
                try {
                    arrowIcons[var0] = Ui.loadArrow(var0);
                } catch (Exception var1) {
                }
            }
        }

    }

    public static void f() {
        bT = 8;
        bU = new int[8];
        bV = new int[bT];
        bW = new int[bT];
        bX = new int[bT];
        bY = new int[bT];
        bZ = new int[bT];

        try {
            healSprite = Effect.loadHeal();
        } catch (Exception var0) {
        }
    }

    public static final void c(int var0) {
        ii = 0;
        int var1 = ij == -1 ? 0 : 1;
        if ((var0 == 0 || var0 == 1 || var0 == 7 || var0 == 5 || var0 == 18 || var0 >= 94) && var1 == 1) {
            ii = 1;
            F();
            b(0, fe, ff);
            ij = 0;
            im[ij] = fm;
        }

        if (var0 == 2) {
            d(var1, 18, 12);
        }

        if (var0 == 3) {
            d(var1, 2, 9);
        }

        if (var0 == 4) {
            d(var1, 3, 6);
        }

        if (var0 == 6) {
            d(var1, 12, 9);
        }

        if (var0 == 8) {
            d(var1, 3, 12);
        }

        if (var0 == 9) {
            d(var1, 2, 11);
        }

        if (var0 == 10) {
            d(var1, 3, 12);
        }

        if (var0 == 11) {
            d(var1, 5, 6);
        }

        if (var0 == 12) {
            d(var1, 4, 8);
        }

        if (var0 == 13) {
            d(var1, 16, 11);
        }

        if (var0 == 14) {
            d(var1, 18, 7);
        }

        if (var0 == 15) {
            d(var1, 1, 11);
        }

        if (var0 == 16) {
            d(var1, 7, 11);
        }

        if (var0 == 17) {
            d(var1, 14, 12);
        }

    }

    private static void F() {
        try {
            motorbikeFrames = Effect.ensureMotorbike(motorbikeFrames);
        } catch (Exception var1) {
        }

        ik = new int[ii];
        il = new int[ii];
        im = new int[ii];
        in = new int[ii];
        io = new int[ii];
        ip = new int[ii];
    }

    private static void b(int var0, int var1, int var2) {
        ik[var0] = var1 * GameMap.TILE_SIZE + 12;
        il[var0] = var2 * GameMap.TILE_SIZE;
        in[var0] = 0;
        im[var0] = 1;
    }

    public static void g() {
        try {
            itemScroll = Item.loadScroll();
        } catch (Exception var0) {
        }
    }

    public static final void d(int var0) {
        iq = 0;
        if (var0 == 1) {
            iq = 2;
            G();
            c(0, 21, 9);
            c(1, 13, 8);
        }

        if (var0 == 11) {
            iq = 2;
            G();
            c(0, 33, 7);
            c(1, 44, 10);
        }

        if (var0 == 5) {
            iq = 2;
            G();
            c(0, 5, 46);
            c(1, 15, 46);
        }

        if (var0 == 97) {
            iq = 4;
            G();
            c(0, 3, 6);
            c(1, 7, 4);
            c(2, 12, 3);
            c(3, 22, 6);
        }

    }

    private static void G() {
        try {
            itemCrate = Item.ensureCrate(itemCrate);
        } catch (Exception var0) {
        }

        is = new int[iq];
        it = new int[iq];
        iu = new int[iq];
        iv = new int[iq];
        iw = new int[iq];
        ix = new int[iq];
        iy = new int[iq];
    }

    private static void c(int var0, int var1, int var2) {
        is[var0] = var1 * GameMap.TILE_SIZE + 12;
        it[var0] = var2 * GameMap.TILE_SIZE + 24;
        iu[var0] = 1;
    }

    public static final void h() {
        r = aT;
        if (aU >= 208) {
            be = true;
            bi = 50;
            s = aU - bi;
            bh = r;
            bf = 0;
            bg = s + 1;
        } else {
            bi = 16;
            s = aU - bi;
            be = false;
            bi = 0;
            bh = r;
            bf = 0;
            bg = s + 1;
            bq = (bo = r / 3) + 29;
            br = bo + 50;
        }

        if ((bt = aU / 6) < 48) {
            bt = 48;
        }

        bC = r >> 1;
        bD = s >> 1;
        bE = 3 * s / 4;
        bF = r / 6;
        by = r / GameMap.TILE_SIZE + 2;
        bz = s / GameMap.TILE_SIZE + 2;
        if (r % 24 != 0) {
            ++by;
        }

        fQ = (GameMap.width - 1) * GameMap.TILE_SIZE - r;
        fR = (GameMap.height - 1) * GameMap.TILE_SIZE - s;
        fO = fI = fe - bC + bF * fm;
        fP = fJ = ff - bE;
        if (fO < 0) {
            fO = 0;
        }

        if (fO > fQ) {
            fO = fQ;
        }

        if (fP < 0) {
            fP = 0;
        }

        if (fP > fR) {
            fP = fR;
        }

        if ((bw = fO / GameMap.TILE_SIZE - 1) < 0) {
            bw = 0;
        }

        bx = fP / GameMap.TILE_SIZE;
        bA = bw + by;
        bB = bx + bz;
        if (bx < 0) {
            bx = 0;
        }

        if (bB > GameMap.height - 1) {
            bB = GameMap.height - 1;
        }

    }

    public static final void i() {
        fc = -1;
        if (heroBodies == null) {
            try {
                heroBodies = new Image[20];

                int var0;
                for(var0 = 0; var0 < 20; ++var0) {
                    heroBodies[var0] = Character.loadHeroBody(var0);
                }

                heroHeads = new Image[11];

                for(var0 = 0; var0 < 11; ++var0) {
                    heroHeads[var0] = Character.loadHeroHead(var0);
                }

                heroLegs = new Image[10];

                for(var0 = 0; var0 < 10; ++var0) {
                    heroLegs[var0] = Character.loadHeroLegs(var0);
                }
            } catch (Exception var1) {
            }
        }

        H();
        fg = 22;
        fh = 32;
        fi = fg >> 1;
        fj = fh >> 1;
        fk = fg >> 2;
        fl = fh >> 2;
        fD = -1;
    }

    private static void H() {
        slashEffects = null;
        System.gc();
        slashEffects = Effect.loadSlashes(y);

    }

    public static void j() {
        I();
        if (be) {
            try {
                if (hudFrames == null) {
                    hudFrames = new Image[2];

                    for(int var0 = 0; var0 < 2; ++var0) {
                        hudFrames[var0] = Ui.loadHud(var0);
                    }
                }

                bj = hudFrames[0].getWidth();
                bk = hudFrames[1].getWidth();
                bl = r - bj - bk + 1;
                bm = 78;
                bn = bg + 7;
                bo = r - 134;
                bp = r - 84;
                bs = 5;
            } catch (IOException var1) {
            }
        }
    }

    private static void I() {
        try {
            if (markerIcon == null) {
                markerIcon = Ui.loadMarker();
            }

            (weaponIcons = new Image[2])[1] = Ui.loadDartIcon();
            weaponIcons[0] = Ui.loadSwordIcon(y);
        } catch (Exception var0) {
        }
    }

    public static void k() {
        de = 5;
        try {
            itemMoney = Item.ensureMoney(itemMoney);
        } catch (Exception var1) {
        }

        df = new int[de];
        dg = new int[de];
        dh = new int[de];
        di = new int[de];
        dj = new int[de];
        dk = new int[de];
        dm = new int[de];
        dl = new int[de];
    }

    public static void e(int var0) {
        dn = false;
        if (var0 == 0) {
            npcDog = Npc.ensureDog(npcDog);

            dn = true;
            doSt = 348;
            dp = 408;
            dq = 1;
            dr = 252;
            ds = 708;
        }

    }

    public static final void l() {
        if (!a) {
            if (dustSprites == null) {
                dustSprites = new Image[2][5];

                for(int var0 = 0; var0 < 2; ++var0) {
                    for(int var1 = 0; var1 < 5; ++var1) {
                        try {
                            dustSprites[var0][var1] = Effect.loadDust(var0, var1);
                        } catch (IOException var2) {
                        }
                    }
                }
            }

            cj = new int[2];
            ck = new int[2];
            (cl = new int[2])[0] = cl[1] = -1;
        }
    }

    public static final void m() {
        int var0;
        if (dartFrames == null) {
            dartFrames = Effect.createDartFrames();
            Effect.loadDarts(dartFrames, eW ? Effect.DART_LEVEL_2 : Effect.DART_LEVEL_1);
        }

        cV = 10;
        cW = new int[10];
        cX = new int[cV];
        cY = new int[cV];
        cZ = new int[cV];
        da = new int[cV];
        dc = new boolean[cV];
        db = new int[cV];

        for(var0 = 0; var0 < cV; ++var0) {
            cY[var0] = -1;
        }

    }

    public static final void n() {
        dP = new int[5];
        dQ = new int[5];
        dR = new int[5];
        dS = new int[5];
        dT = new int[5];
        dU = new int[5];
        dO = new String[5];

        for(int var0 = 0; var0 < 5; ++var0) {
            dT[var0] = -1;
        }

    }

    private static void x(int var0) {
        e = 2;
        w = var0;
        Midlet.nextGameState = 1;
        aj();
        (new Thread(Midlet.instance)).start();
    }

    public static final void f(int var0) {
        if (gk) {
            gm = var0;
        }

        if (gm == var0) {
            ig = -1;
            if (girlBodies == null) {
                try {
                    girlBodies = new Image[8];

                    for(var0 = 0; var0 < 8; ++var0) {
                        girlBodies[var0] = Character.loadGirlBody(var0);
                    }

                    girlHeads = new Image[3];

                    for(var0 = 0; var0 < 3; ++var0) {
                        girlHeads[var0] = Character.loadGirlHead(var0);
                    }

                    girlLegs = new Image[9];

                    for(var0 = 0; var0 < 9; ++var0) {
                        girlLegs[var0] = Character.loadGirlLegs(var0);
                    }
                } catch (Exception var1) {
                }
            }

            gt = 22;
            gu = 32;
            gv = gt >> 1;
            gw = gu >> 1;
            gx = gt >> 2;
            gy = gu >> 2;
            if (gk) {
                gn = fe - fm * 10;
                go = ff;
                gz = fm;
                gr = 4;
            }

        }
    }

    public static void o() {
        if (auraSprites == null) {
            auraSprites = new Image[2];

            for(int var0 = 0; var0 < 2; ++var0) {
                try {
                    auraSprites[var0] = Effect.loadAura(var0);
                } catch (IOException var1) {
                }
            }
        }

    }

    public static void g(int var0) {
        du = 0;
        dx = false;
        if (var0 == 0) {
            du = 7;
            J();
            a(0, 0, 14, 17, 130, 11, 22, new String[]{"Chào, cậu là ai thế, mình chưa từng gặp bạn thì phải?", "Nói cậu nghe nhé, thầy cô trường này khó tính lắm đó.", "Cẩn thận với ông thầy Hiroyaki nhé. Đừng chọc giận ổng."});
            a(1, 1, 24, 17, 160, 15, 30, new String[]{"Mình là MinhXP, lớp trưởng lớp kiếm thuật.", "Kiếm là vũ khí lợi hại nhất của một ninja đấy bạn biết không?", "Khu vực luyện tập nằm ở bên trái. Hãy sang đấy tập luyện nhiều hơn nữa."});
            if (eQ) {
                a(2, 6, 17, 12, 200, 7, 29, new String[]{"Thằng bên kia là sát thủ giả mạo.", "Ta đây mới là ninja sát thủ thứ thiệt đây!"});
            } else {
                a(2, 2, 17, 12, 200, 7, 29, new String[]{"Chào, tao tên là Tonic, mày là thằng nào?", "Nhìn lão hiệu trưởng kìa, lão có một hũ rượu trông hấp dẫn quá!", "Nếu muốn rủ tao đi nhậu thì ok, còn không thì biến đi.", "À, nếu mày có thấy con chó của tao thì báo tao 1 tiếng nhé."});
            }

            a(3, 3, 48, 17, 200, 44, 63, new String[]{"Hi, mình là Craze, trông bạn có vẻ hơi buồn thì phải?", "Bạn có thấy chán khi tối ngày quanh quẩn trong trường không?", "Đi về phía tay phải bạn sẽ đến được ngôi làng, ra đó để tìm niềm vui đi!"});
            a(4, 4, 46, 12, 200, 40, 54, new String[]{"Thằng nhà quê kia, mày mau kiếm tiền đóng thuế thân cho tao.", "Tao cho mày 2 ngày, nếu không đưa tao 1000$ thì mày ăn chém đó."});
            a(5, 5, 38, 17, 200, 34, 49, new String[]{"Xin chào, bạn là học sinh mới phải không?", "Nhìn thầy hiệu trưởng kìa, thầy vốn là một ninja sát thủ đấy.", "Thầy đã bỏ nghề sát thủ lâu rồi, nhưng tài năng vẫn còn đó", "Chúng ta có tập luyện cả đời cũng không bằng thầy đâu."});
            a(6, 6, 58, 12, 200, 46, 60, new String[]{"Đừng hỏi ta là ai...", "Ta chỉ là một ninja sát thủ thôi, ngươi biết thế là được rồi.", "Biến đi cho ta luyện tập."});
            y(7);
        }

        if (var0 == 1) {
            du = 3;
            J();
            a(0, 5, 35, 9, 40, 33, 36, new String[]{"Bạn có thể nhảy lên cây cầu này nếu đã học xong khinh công từ cô Ayumi xinh đẹp."});
            if (eQ) {
                a(1, 6, 25, 10, 40, 22, 28, new String[]{"Ta đây là một ninja sát thủ!", 
                    "Ha ha ha!"});
            } else {
                a(1, 2, 25, 10, 40, 22, 28, new String[]{"Mày đã học khinh công rồi hả?", 
                    "À, mày có thấy con chó của tao chạy đâu đâu không?"});
            }

            a(2, 1, 6, 9, 40, 2, 10, new String[]{"Bên kia là một thác nước rất lớn đấy.", 
                "Nó không thuộc phạm vi trường chúng ta nữa nên có rất nhiều quái vật hung dữ.", 
                "Nghe nói có một con quái vật rất to đấy, tốt hơn ngươi đừng qua làm gì!"});
            y(7);
        }

        if (var0 == 2) {
            du = 7;
            J();
            a(0, 3, 16, 12, 60, 14, 21, new String[]{"Cậu nhìn thấy chiếc xe máy kia không?", 
                "Phải có bằng lái từ thầy chủ nhiệm bạn mới có thể chạy được."});
            a(1, 4, 8, 12, 50, 2, 15, new String[]{"Thằng nhà quê kia, mày đi đâu đấy?"});
            if (eQ) {
                a(2, 6, 29, 13, 200, 27, 40, new String[]{"Ninja sát thủ là ta - Thiên hạ vô địch!"});
            } else {
                a(2, 2, 29, 13, 200, 27, 40, new String[]{"Mày có thấy con chó của tao ở đâu không?"});
            }

            a(3, 5, 46, 13, 200, 41, 49, new String[]{"Nếu bạn muốn lên kia thì phải học khinh công từ cô Ayumi đã."});
            a(4, 1, 46, 11, 200, 43, 53, new String[]{"Cậu có thấy ở đây ai cũng tài giỏi không?", 
                "Hãy cố gắng tập luyện hơn nữa nhé"});
            a(5, 0, 54, 11, 200, 50, 58, new String[]{"Ra ngoài rừng hoa đào có rất nhiều điều thú vị đấy.", 
                "Bạn nên ra thử một lần để biết."});
            a(6, 6, 34, 13, 200, 28, 48, new String[]{"Đừng hỏi ta là ai...", 
                "Ta chỉ là một ninja sát thủ thôi, ngươi biết thế là được rồi.", "Biến đi cho ta luyện tập."});
            y(7);
        }

        String[] var1;
        if (var0 == 99 || var0 == 101) {
            du = 7;
            J();
            var1 = new String[]{"Này, cậu đi tìm con heo đó trước đi. Tớ đi theo bọc lót cho!"};
            a(0, 0, 8, 13, 0, 0, 1000, var1);
            a(1, 1, 5, 13, 0, 0, 1000, var1);
            a(2, 6, 6, 13, 0, 0, 1000, var1);
            a(3, 2, 7, 13, 0, 0, 1000, var1);
            a(4, 4, 6, 13, 0, 0, 1000, var1);
            a(5, 5, 5, 13, 0, 0, 1000, var1);
            a(6, 3, 8, 13, 0, 0, 1000, var1);
            y(7);
        }

        if (var0 == 94) {
            du = 7;
            J();
            var1 = new String[]{"Chúc mừng, sắp được ra trường rồi nhé!"};
            a(0, 0, 5, 12, 0, 0, 1000, var1);
            a(1, 1, 5, 12, 0, 0, 1000, var1);
            a(2, 6, 6, 12, 0, 0, 1000, var1);
            a(3, 7, 3, 12, 0, 0, 1000, var1);
            a(4, 4, 6, 12, 0, 0, 1000, var1);
            a(5, 5, 5, 12, 0, 0, 1000, var1);
            a(6, 3, 8, 12, 0, 0, 1000, var1);
            y(8);
        }

    }

    private static void J() {
        dz = new int[du];
        dA = new int[du];
        dB = new int[du];
        dC = new int[du];
        dD = new int[du];
        dE = new int[du];
        dJ = new int[du];
        dF = new int[du];
        dG = new int[du];
        dH = new int[du];
        dI = new int[du];
        dK = new int[du];
        dL = new int[du];
        dM = new int[du];
        dN = new int[du];
        dv = new String[du][];
    }

    private static void y(int var0) {
        if (classmateHeads == null) {
            classmateHeads = new Image[var0][2];

            for(int var1 = 0; var1 < var0; ++var1) {
                for(int var2 = 0; var2 < 2; ++var2) {
                    try {
                        classmateHeads[var1][var2] = Character.loadClassmateHead(var1, var2);
                    } catch (Exception var3) {
                    }
                }
            }
        }

    }

    private static void a(int var0, int var1, int var2, int var3, int var4, int var5, int var6, String[] var7) {
        dz[var0] = var2 * 24 + (var0 << 2);
        dA[var0] = var3 * 24;
        dB[var0] = 0;
        dC[var0] = 0;
        dD[var0] = 1;
        dJ[var0] = -1;
        dE[var0] = 0;
        dF[var0] = (var0 + 1) * 20 + var0;
        dG[var0] = 0;
        dH[var0] = var1;
        dI[var0] = -1;
        dL[var0] = -1;
        dM[var0] = var5 * 24 + 12;
        dN[var0] = var6 * 24 + 12;
        dK[var0] = var4;
        dv[var0] = var7;
    }

    private static void K() {
        e = 2;
        aj();
        Midlet.nextGameState = 3;
        w = 99;

        try {
            titleImage = Ui.loadTitle();
        } catch (Exception var1) {
        }

        bc = aU - 30;
        bd = aU;
        aY = 30;
        aZ = aU - 50;
        ba = bb = aY + aZ >> 1;
        if (Save.isSaved()) {
            aX = 1;
        }

        (new Thread(Midlet.instance)).start();
    }

    public static void h(int var0) {
        cp = 0;
        if (var0 == 9) {
            cp = 6;
            dt = new boolean[]{false, false, true, false, false, false, false};
            L();
            b(0, 10, 12, 9, 12, 2);
            b(1, 7, 8, 6, 8, 2);
            b(2, 30, 8, 27, 33, 2);
            b(3, 29, 12, 27, 33, 2);
            b(4, 45, 5, 42, 50, 2);
            b(5, 64, 9, 62, 66, 2);
        }

        if (var0 == 10) {
            cp = 12;
            dt = new boolean[]{true, false, true, false, false, false, false};
            L();
            b(0, 5, 8, 4, 6, 2);
            b(1, 6, 12, 5, 9, 0);
            b(2, 16, 12, 9, 21, 2);
            b(3, 28, 15, 26, 29, 0);
            b(4, 25, 15, 22, 27, 2);
            b(5, 16, 25, 14, 17, 0);
            b(6, 4, 29, 2, 6, 0);
            b(7, 8, 38, 2, 28, 2);
            b(8, 14, 38, 2, 28, 2);
            b(9, 20, 38, 2, 28, 2);
            b(10, 31, 30, 28, 37, 2);
            b(11, 33, 30, 28, 37, 0);
        }

        if (var0 == 11) {
            cp = 8;
            dt = new boolean[]{true, true, true, false, false, false, false};
            L();
            b(0, 6, 11, 3, 7, 1);
            b(1, 19, 12, 18, 20, 0);
            b(2, 33, 8, 28, 36, 2);
            b(3, 53, 11, 53, 54, 1);
            b(5, 54, 11, 53, 54, 0);
            b(4, 68, 12, 62, 79, 2);
            b(6, 71, 12, 70, 72, 0);
            b(7, 72, 8, 70, 73, 1);
        }

        if (var0 == 16) {
            cp = 4;
            dt = new boolean[]{false, false, false, true, false, false, false};
            L();
            b(0, 5, 11, 3, 10, 3);
            b(1, 26, 12, 24, 29, 3);
            b(2, 47, 10, 30, 55, 3);
            b(3, 60, 11, 56, 64, 3);
        }

        if (var0 == 17) {
            cp = 8;
            dt = new boolean[]{false, false, false, true, true, false, false};
            L();
            b(0, 11, 8, 9, 13, 3);
            b(1, 20, 6, 18, 21, 4);
            b(2, 20, 11, 18, 21, 3);
            b(3, 26, 3, 24, 29, 3);
            b(4, 26, 8, 24, 29, 4);
            b(5, 26, 13, 24, 29, 4);
            b(6, 33, 6, 32, 35, 3);
            b(7, 33, 10, 32, 35, 4);
        }

        if (var0 == 18) {
            cp = 8;
            dt = new boolean[]{false, false, false, false, true, true, true};
            L();
            b(0, 2, 8, 1, 4, 5);
            b(1, 2, 12, 1, 5, 6);
            b(2, 18, 12, 12, 23, 6);
            b(3, 18, 8, 15, 21, 5);
            b(4, 42, 9, 38, 45, 4);
            b(5, 42, 13, 34, 67, 6);
            b(6, 62, 13, 34, 67, 6);
            b(7, 60, 9, 57, 64, 4);
        }

    }

    private static void L() {
        boolean var0 = false;
        if (ninjaLegs == null) {
            try {
                ninjaLegs = Mob.loadNinjaLegs();
                ninjaBodies = Mob.loadNinjaBodies(dt);
            } catch (Exception var2) {
            }
        }

        cs = new int[cp];
        ct = new int[cp];
        cu = new int[cp];
        cv = new int[cp];
        cw = new int[cp];
        cx = new int[cp];
        cD = new int[cp];
        cE = new int[cp];
        cF = new int[cp];
        cG = new int[cp];
        cH = new int[cp];
        cI = new int[cp];
        cJ = new int[cp];
        cC = new int[cp];
        cK = new int[cp];
        cL = new int[cp];
        cy = new int[cp];
        cz = new int[cp];
        cA = new int[cp];
        cB = new int[cp];
        cr = new int[cp];
        cq = new int[cp];
    }

    private static void b(int var0, int var1, int var2, int var3, int var4, int var5) {
        cs[var0] = var1 * 24 + 12;
        ct[var0] = var2 * 24;
        cu[var0] = cs[var0];
        cv[var0] = ct[var0];
        cw[var0] = cu[var0];
        cx[var0] = cv[var0] - 9;
        cD[var0] = var5;
        cE[var0] = 3;
        cF[var0] = var0 << 1;
        cG[var0] = 0;
        cH[var0] = 1;
        cI[var0] = 0;
        cJ[var0] = 0;
        cC[var0] = 0;
        cK[var0] = var3 * 24 + 12;
        cL[var0] = var4 * 24 + 12;
        cr[var0] = ae[cD[var0]];
    }

    public static void i(int var0) {
        ha = 1200;
        gZ = 1200;
        gO = 30;
        gN = false;
        int var1;
        if (var0 == 4) {
            gN = true;
            boss0Sprites = Mob.ensureBoss0(boss0Sprites);

            gV = boss0Sprites[0].getWidth();
            gW = boss0Sprites[0].getHeight();
            gP = 55 * GameMap.TILE_SIZE + 12;
            gQ = 13 * GameMap.TILE_SIZE;
            gS = 1;
            gT = -1;
            gU = 0;
        }

        if (var0 == 5) {
            gN = true;
            boss0Sprites = Mob.ensureBoss0(boss0Sprites);

            gV = boss0Sprites[0].getWidth();
            gW = boss0Sprites[0].getHeight();
            gP = 8 * GameMap.TILE_SIZE + 12;
            gQ = 56 * GameMap.TILE_SIZE;
            gS = 1;
            gT = -1;
            gU = 0;
        }

    }

    public static void p() {
        cM = 5;
        try {
            itemPotions = Item.ensurePotions(itemPotions);
        } catch (Exception var0) {
        }

        cO = new int[cM];
        cP = new int[cM];
        cQ = new int[cM];
        cR = new int[cM];
        cS = new int[cM];
        cT = new int[cM];
        cU = new int[cM];
        cN = new int[cM];
    }

    public static void j(int var0) {
        hJ = false;
        if (var0 == 97 && !eF) {
            hJ = true;
            boss1Sprites = Mob.loadBoss1();

            hP = 168;
            hQ = 226;
            hZ = 144;
            ia = 480;
            hX = 2;
            hY = -1;
            hN = 8000;
            hM = 8000;
        }

    }

    public static final void q() {
        if (bloodSprites == null) {
            bloodSprites = new Image[3];

            for(int var0 = 0; var0 < 3; ++var0) {
                try {
                    bloodSprites[var0] = Effect.loadBlood(var0);
                } catch (IOException var1) {
                }
            }
        }

        ca = new int[2];
        cb = new int[2];
        cc = new int[2];
        cd = new int[2];
        ce = new int[2];
        cc[0] = cc[1] = -1;
    }

    public static void k(int var0) {
        hi = false;
        if (var0 == 95 && !eH) {
            hi = true;
            boss2Sprites = new Image[6];

            try {
                boss2Sprites[0] = Mob.loadBossPart(2, 0);
                boss2Sprites[1] = Mob.loadBossPart(2, 1);
                boss2Sprites[2] = Mob.loadBossPart(2, 2);
                boss2Sprites[3] = boss2Sprites[1];
                boss2Sprites[4] = Mob.loadBossPart(2, 3);
                boss2Sprites[5] = Character.loadClassmateHead(2, 0);
            } catch (Exception var1) {
            }

            hp = 192;
            hq = 264;
            ht = 120;
            hu = 504;
            hr = 0;
            hs = -1;
            hn = 90000;
            hm = 90000;
            hD = new int[6];
            hE = new int[6];
            hH = new int[6];
            hI = new int[6];
            hF = new int[]{20, 10, 0, -10, -16, 2};
            hG = new int[]{-16, 0, -12, 0, -18, -42};
            hx = new int[][]{new int[6], {0, 0, 1, 0, 1, 1}};
            hy = new int[][]{new int[6], {1, 0, 1, 0, 2, 1}};
            hz = new int[][]{{6, -1, 1, 1, 5, 1}, {14, -1, 2, 1, 12, 2}};
            hA = new int[][]{{1, 0, 0, 0, 1, 0}, {2, 0, 1, 0, 2, 1}};
            hB = new int[][]{{-3, 3, 0, -3, 3, 0}, new int[6], {3, -3, 0, 3, -3, 0}};
            hC = new int[][]{{-1, -2, 1, 0, 1, 1}, new int[6], {1, 0, 1, -2, -1, 1}};
        }

    }

    public static void l(int var0) {
        hc = false;
        if (var0 == 96 && !eG) {
            hc = true;
            du = 1;
            J();
            a(0, 2, 5, 11, 200, 4, 22, (String[])null);
            y(3);
            hh = 15000;
            hg = 15000;
            hf = 120;
            du = 0;
            dD[0] = 0;
            dJ[0] = 1;
        }

    }

    public static void m(int var0) {
        if (var0 == 94) {
            dw = true;
            fe = 24;
            ff = 288;
            gm = 94;
            gn = 12;
            go = 288;
            gz = 1;
            gk = false;
            fm = 1;
        }

        if (var0 == 0 && P == 15 && fA) {
            gm = -1;
            gk = false;
        }

        if (var0 == 0 && P == 16 && fb) {
            fa = true;
            gm = 0;
            gk = true;
            fu = true;
            P = -1;
            fe = 984;
            ff = 408;
        } else {
            if (var0 == 95 && !eH) {
                gk = false;
                gn = 599;
                go = 264;
                gz = -1;
                gm = 95;
            }

        }
    }

    public static void n(int var0) {
        ie = false;
        fS = false;
        fH = 0;
        if (var0 == 15 && P == 15) {
            e(216, 432);
        }

        if (var0 == 8 && P == 6) {
            e(72, 240);
        }

        int var1;
        if (var0 == 4 && eE) {
            U = true;
            eI = true;
            eQ = true;
            eP = true;
            PlayerStats.setMaxHpMp(1000, 2000);
            PlayerStats.attack = 25;
            B = 4;
            ha = 500;
            gZ = 500;
            D(0);
        } else if (var0 == 99) {
            eE = true;
            dw = true;
            fe = 108;
            ff = 312;
            fm = -1;
            D(1);
        } else if (var0 == 94) {
            D(10);
        } else if (var0 == 0 && eE) {
            U = false;
            eI = false;
            eP = false;
            eQ = false;
            fx = false;
            PlayerStats.resetForNewGame();
            ha = 1000;
            gZ = 1000;
            fe = 864;
            ff = 336;

            for(var1 = 0; var1 < 19; ++var1) {
                X[var1] = false;
            }

            X[0] = true;
            fm = -1;
            dw = false;
            dx = false;
            D(2);
            eE = false;
        } else if (var0 == 3 && P == 3 && !gk) {
            D(3);
        } else if (var0 == 0 && P == 3 && gk) {
            D(4);
        } else if (var0 == 97 && !eF) {
            D(5);
        } else if (var0 == 96 && !eG) {
            D(6);
        } else if (var0 == 95 && !eH) {
            D(7);
        } else if (var0 == 101) {
            D(8);
            fP = fJ = 336 - s;
            fI = 24;
            fO = 24;
        } else if (var0 == 9 && P == 10) {
            D(9);
        }

        if (var0 == 97 && eF) {
            for(var1 = 0; var1 <= 6; ++var1) {
                GameMap.clearFlags(24, var1);
            }
        }

        if (var0 == 96 && eG) {
            for(var1 = 0; var1 <= 11; ++var1) {
                GameMap.clearFlags(24, var1);
            }
        }

    }

    public static final void r() {
        if (!a) {
            waterSplashSprites = new Image[3];

            for(int var0 = 0; var0 < 3; ++var0) {
                try {
                    waterSplashSprites[var0] = Effect.loadWaterSplash(var0);
                } catch (IOException var1) {
                }
            }

            cf = new int[2];
            cg = new int[2];
            ch = new int[2];
            ci = new int[2];
            ch[0] = ch[1] = -1;
        }
    }

    private static void z(int var0) {
        if (dA[0] == ff && a(dz[0] + (dJ[0] << 5) - fe) < 25) {
            a(hf, dJ[0]);
        }

    }

    private static void A(int var0) {
        int var1;
        boolean var6;
        label29: {
            var1 = PlayerStats.attack;
            int var10000 = dL[var0];
            int var4 = -dJ[var0];
            int var2 = var10000;
            if (jO[var2] == 2 || jO[var2] == 3 || jO[var2] == 4) {
                int[] var5 = jY;
                var5[var2] -= var1;
                if (jY[var2] <= 0) {
                    jY[var2] = 0;
                    jT[var2] = -5;
                    jU[var2] = -(var4 << 3);
                    jV[var2] = 0;
                    jN[var2] = 0;
                    jO[var2] = 1;
                    var6 = true;
                    break label29;
                }

                jT[var2] = 0;
                jX[var2] = var4;
                jO[var2] = 6;
                jW[var2] = 3;
            }

            var6 = false;
        }

        if (!var6) {
            f(jJ[dL[var0]], jK[dL[var0]] - (jL[dL[var0]] >> 1), jX[dL[var0]]);
            a("-" + var1, 0, jJ[dL[var0]], jK[dL[var0]] - jL[dL[var0]], 0, -2);
        }

    }

    private static void B(int var0) {
        for(int var1 = 0; var1 < jG; ++var1) {
            if (jO[var1] != 5 && jK[var1] == dA[var0] && a(dz[var0] + (dJ[var0] << 3) - jJ[var1]) < 20) {
                if (jJ[var1] < dz[var0]) {
                    dJ[var0] = -1;
                } else {
                    dJ[var0] = 1;
                }

                dD[var0] = 5;
                dE[var0] = dF[var0] = 0;
                dL[var0] = var1;
            }
        }

    }

    private static void M() {
        if (iI < iE[iC].length) {
            iI = iE[iC].length;
            iH = 0;
        } else {
            ++iC;
            iI = 0;
            iH = 0;
        }
    }

    private static void a(Graphics var0) {
        int var1;
        if (c && !a) {
            var0.setColor(j);
            var0.fillRect(0, 0, r, q);

            for(var1 = -((fO >> 1) % 24); var1 < r; var1 += 24) {
                var0.drawImage(t[0], var1, k, 0);
            }

            for(var1 = -((fO >> 2) % 24); var1 < r; var1 += 24) {
                var0.drawImage(t[1], var1, l, 0);
            }

            for(var1 = -((fO >> 4) % 64); var1 < r; var1 += 64) {
                var0.drawImage(t[3], var1, n, 0);
            }

            var0.drawImage(v, h, i, 3);

            for(var1 = 0; var1 < 2; ++var1) {
                var0.drawImage(u, f[var1], g[var1], 3);
            }

            for(var1 = -((fO >> 3) % 192); var1 < r; var1 += 192) {
                var0.drawImage(t[2], var1, m, 0);
            }

        } else {
            var0.setColor(j);
            var0.fillRect(0, 0, r, s);
            if (!a) {
                var0.drawImage(v, h, i, 3);

                for(var1 = 0; var1 < 2; ++var1) {
                    var0.drawImage(u, f[var1], g[var1], 3);
                }

            }
        }
    }

    private static void b(Graphics var0) {
        if (fw) {
            if (x % 20 > 2) {
                C = 25;
            } else {
                C = 26;
            }
        }

        if (fm == 1) {
            var0.drawImage(heroHeads[ar[C][0][0]], fe + ar[C][0][1], ff - ar[C][0][2], 0);
            var0.drawImage(heroLegs[ar[C][1][0]], fe + ar[C][1][1], ff - ar[C][1][2], 0);
            var0.drawImage(heroBodies[ar[C][2][0]], fe + ar[C][2][1], ff - ar[C][2][2], 0);
            if (fc != -1) {
                var0.drawImage(slashEffects[fc], fe - as[fc], ff - at[fc], 0);
                return;
            }
        } else {
            var0.drawRegion(heroHeads[ar[C][0][0]], 0, 0, heroHeads[ar[C][0][0]].getWidth(), heroHeads[ar[C][0][0]].getHeight(), 2, fe - ar[C][0][1], ff - ar[C][0][2], 24);
            var0.drawRegion(heroLegs[ar[C][1][0]], 0, 0, heroLegs[ar[C][1][0]].getWidth(), heroLegs[ar[C][1][0]].getHeight(), 2, fe - ar[C][1][1], ff - ar[C][1][2], 24);
            var0.drawRegion(heroBodies[ar[C][2][0]], 0, 0, heroBodies[ar[C][2][0]].getWidth(), heroBodies[ar[C][2][0]].getHeight(), 2, fe - ar[C][2][1], ff - ar[C][2][2], 24);
            if (fc != -1) {
                var0.drawRegion(slashEffects[fc], 0, 0, slashEffects[fc].getWidth(), slashEffects[fc].getHeight(), 2, fe + as[fc], ff - at[fc], 24);
            }
        }

    }

    private static void c(Graphics var0) {
        if (!be) {
            var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
            var0.drawImage(heroHeads[0], 0, 2, 0);
            var0.setColor(0);
            var0.drawRect(23, 2, bo + 1, 4);
            var0.drawRect(23, 8, bo + 1, 4);
            var0.drawRect(23, 14, bo + 1, 3);
            var0.setColor(16711680);
            int var1 = PlayerStats.hpBarWidth(bo);
            var0.fillRect(24, 3, var1, 3);
            var0.setColor(5570560);
            var0.fillRect(var1 + 24, 3, bo - var1, 3);
            var0.setColor(255);
            var1 = PlayerStats.mpBarWidth(bo);
            var0.fillRect(24, 9, var1, 3);
            var0.setColor(5570560);
            var0.fillRect(var1 + 24, 9, bo - var1, 3);
            var0.setColor(7274435);
            var1 = Leveling.expBarWidth(bo);
            var0.fillRect(24, 15, var1, 2);
            var0.setColor(19506);
            var0.fillRect(var1 + 24, 15, bo - var1, 2);
            FontGraph.drawSmall(var0, "lv:" + PlayerStats.level, 2, bq, 7, 0);
            FontGraph.drawSmall(var0, dd + "$", 1, br, 7, 0);
        }
    }

    private static void d(Graphics var0) {
        d = false;
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        var0.setClip(-1, bg - 1, bh + 2, 100);
        var0.setColor(0);
        if (!be) {
            var0.fillRect(0, bg, bh, 15);
            FontGraph.drawBold(var0, o, 3, bg + 3, 0);
            FontGraph.drawBold(var0, p, bh - 3, bg + 3, 1);
        } else {
            var0.fillRect(0, bg + 33, bh, 15);
            FontGraph.drawBold(var0, o, 3, bg + 36, 0);
            FontGraph.drawBold(var0, p, bh - 3, bg + 36, 1);
            var0.setColor(4352);
            var0.fillRect(bj, bg, bl, 1);
            var0.fillRect(bj, bg + 35, bl, 1);
            var0.fillRect(bj, bg + 33, bl, 1);
            var0.fillRect(bj, bg + 30, bl, 1);
            var0.fillRect(bj, bg + 28, bl, 1);
            var0.fillRect(bj, bg + 26, bl, 1);
            var0.fillRect(bj, bg + 12, bl, 1);
            var0.fillRect(bj, bg + 24, bl, 1);
            var0.fillRect(bj, bg + 18, bl, 1);
            var0.fillRect(bj, bg + 16, bl, 1);
            var0.fillRect(bj, bg + 2, bl, 1);
            var0.fillRect(bj, bg + 4, bl, 1);
            var0.fillRect(bj, bg + 6, bl, 1);
            var0.fillRect(bj, bg + 14, bl, 1);
            var0.setColor(15649843);
            var0.fillRect(bj, bg + 1, bl, 1);
            var0.fillRect(bj, bg + 5, bl, 1);
            var0.fillRect(bj, bg + 17, bl, 1);
            var0.fillRect(bj, bg + 34, bl, 1);
            var0.fillRect(bj, bg + 29, bl, 1);
            var0.setColor(16767871);
            var0.fillRect(bj, bg + 3, bl, 1);
            var0.fillRect(bj, bg + 15, bl, 1);
            var0.fillRect(bj, bg + 27, bl, 1);
            var0.setColor(3355443);
            var0.fillRect(bj, bg + 7, bl, 5);
            var0.fillRect(bj, bg + 19, bl, 5);
            var0.fillRect(bj, bg + 31, bl, 2);
            var0.setColor(12281361);
            var0.fillRect(bj, bg + 25, bl, 1);
            var0.fillRect(bj, bg + 13, bl, 1);
            var0.drawImage(hudFrames[0], 0, bg, 0);
            var0.drawImage(hudFrames[1], 0 + bh, bg, 24);
            int var1 = PlayerStats.hpBarWidth(bo);
            var0.setColor(7798784);
            var0.fillRect(bm, bn, var1, 2);
            var0.setColor(13369344);
            var0.fillRect(bm, bn + 1, var1, 4);
            var1 = PlayerStats.mpBarWidth(bo);
            var0.setColor(119);
            var0.fillRect(bm, bn + 12, var1, 2);
            var0.setColor(204);
            var0.fillRect(bm, bn + 14, var1, bs - 2);
            FontGraph.drawSmall(var0, "" + dd, 1, bh - 18, bg + 6, 2);
            var1 = Leveling.expBarWidth(bp);
            var0.setColor(94373);
            var0.fillRect(31, bg + 31, var1, 1);
            var0.setColor(65535);
            var0.fillRect(31, bg + 32, var1, 1);
            FontGraph.drawSmall(var0, "" + PlayerStats.level, 2, 26, bg + 11, 2);
            var0.drawImage(weaponIcons[fd], 43, bg + 16, 3);
            FontGraph.drawSmall(var0, "" + (W[0] + W[1] + W[2]), 2, bh - 29, bg + 19, 2);
            FontGraph.drawSmall(var0, "" + (W[3] + W[4] + W[5]), 2, bh - 9, bg + 19, 2);
        }
    }

    private static void e(Graphics var0) {
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        var0.setClip(-1, -1, aT + 2, aU + 2);
        var0.setColor(0);
        var0.fillRect(0, 0, r, bu);
        if (be) {
            var0.fillRect(0, aU - bu + 1, r, bu);
        } else {
            var0.fillRect(0, aU - 13, aT, 13);
        }

        if (fV) {
            for(int var1 = 0; var1 < fW.length; ++var1) {
                FontGraph.drawBold(var0, fW[var1], bC, fX + (var1 << 4), 2);
            }
        }

    }

    private static void f(Graphics var0) {
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        var0.setClip(-1, -1, aT + 2, aU + 2);
        iA = iE[iC].length;
        int var1 = iG - fP - (iA << 4);
        boolean var2 = true;
        if (var1 < 10) {
            var2 = false;
            var1 = 10;
        }

        var0.setColor(16777215);
        if (a) {
            var0.fillRect(iK, var1 - 5, iJ, (iA << 4) + 7);
        } else {
            var0.fillRoundRect(iK, var1 - 5, iJ, (iA << 4) + 7, 10, 10);
        }

        var0.setColor(17408);
        if (a) {
            var0.drawRect(iK - 1, var1 - 5, iJ + 1, (iA << 4) + 7);
        } else {
            var0.drawRoundRect(iK - 1, var1 - 5, iJ + 1, (iA << 4) + 7, 10, 10);
        }

        for(int var3 = 0; var3 < iI; ++var3) {
            FontGraph.drawRegular(var0, iE[iC][var3], iK + (iJ >> 1), var1 + (var3 << 4), 2);
        }

        if (iI < iE[iC].length) {
            FontGraph.drawRegular(var0, iE[iC][iI].substring(0, iH), iK + (iJ >> 1), var1 + (iI << 4), 2);
        }

        var0.setColor(0);
        var0.fillRect(0, bg + 33, bh, 15);
        if (var2 && iF > fO + 10 && iF < fO + r - 10) {
            var0.drawImage(markerIcon, iF - fO, iG - fP + 2, 17);
        }

        if (x % 10 < 8) {
            FontGraph.drawBold(var0, "Tiếp tục", bC, aU - 13, 2);
        }

    }

    private static void g(Graphics var0) {
        for(int var1 = 0; var1 < du; ++var1) {
            if (dz[var1] + fi >= fO && dz[var1] - fi <= fO + r && dA[var1] >= fP && dA[var1] - fh <= fP + s) {
                if (dJ[var1] == 1) {
                    var0.drawImage(classmateHeads[dH[var1]][aI[dG[var1]][0][0]], dz[var1] + aI[dG[var1]][0][1], dA[var1] - aI[dG[var1]][0][2], 0);
                    var0.drawImage(heroLegs[aI[dG[var1]][1][0]], dz[var1] + aI[dG[var1]][1][1], dA[var1] - aI[dG[var1]][1][2], 0);
                    var0.drawImage(heroBodies[aI[dG[var1]][2][0]], dz[var1] + aI[dG[var1]][2][1], dA[var1] - aI[dG[var1]][2][2], 0);
                    if (dI[var1] != -1) {
                        var0.drawImage(slashEffects[dI[var1]], dz[var1] - as[dI[var1]], dA[var1] - at[dI[var1]], 0);
                    }
                } else {
                    var0.drawRegion(classmateHeads[dH[var1]][aI[dG[var1]][0][0]], 0, 0, classmateHeads[dH[var1]][aI[dG[var1]][0][0]].getWidth(), classmateHeads[dH[var1]][aI[dG[var1]][0][0]].getHeight(), 2, dz[var1] - aI[dG[var1]][0][1], dA[var1] - aI[dG[var1]][0][2], 24);
                    var0.drawRegion(heroLegs[aI[dG[var1]][1][0]], 0, 0, heroLegs[aI[dG[var1]][1][0]].getWidth(), heroLegs[aI[dG[var1]][1][0]].getHeight(), 2, dz[var1] - aI[dG[var1]][1][1], dA[var1] - aI[dG[var1]][1][2], 24);
                    var0.drawRegion(heroBodies[aI[dG[var1]][2][0]], 0, 0, heroBodies[aI[dG[var1]][2][0]].getWidth(), heroBodies[aI[dG[var1]][2][0]].getHeight(), 2, dz[var1] - aI[dG[var1]][2][1], dA[var1] - aI[dG[var1]][2][2], 24);
                    if (dI[var1] != -1) {
                        var0.drawRegion(slashEffects[dI[var1]], 0, 0, slashEffects[dI[var1]].getWidth(), slashEffects[dI[var1]].getHeight(), 2, dz[var1] + as[dI[var1]], dA[var1] - at[dI[var1]], 24);
                    }
                }
            }
        }

    }

    private static void h(Graphics var0) {
        var0.setClip(-1, -1, aT + 2, aU + 1);
        a(var0);
        var0.translate(-fO, -fP);
        if (a) {
            j(var0);
        } else {
            k(var0);
        }

        i(var0);
        g(var0);
        b(var0);
        r(var0);
        l(var0);
        e(var0);
        if (iz) {
            f(var0);
        }

    }

    private static void N() {
        if (!fw && B != 14 && a(ff - gQ) < 30 && a(fe - gP) < 200) {
            gS = 5;
            gT = gP < fe ? 1 : -1;
            gY = fe + (gT << 5);
            gR = gT * 6;
            if (gZ < ha >> 1) {
                gR = gT << 3;
            }
        }

    }

    private static void d(int var0, int var1, int var2) {
        ii = var0 + 1;
        F();
        b(0, var1, var2);
        if (var0 == 1) {
            b(1, fe, ff);
            ij = 1;
            im[ij] = fm;
        }

    }

    private static void b(int var0, int var1, int var2, int var3, int var4) {
        int var5 = -1;

        for(int var6 = 0; var6 < bM; ++var6) {
            if (bP[var6] == -1) {
                var5 = var6;
                break;
            }
        }

        if (var5 != -1) {
            bP[var5] = 1;
            bN[var5] = var0;
            bO[var5] = var1;
            bQ[var5] = var2;
            bR[var5] = var3;
            bS[var5] = var4;
        }
    }

    private static void a(int var0, int var1, int var2, int var3) {
        gb = var0;
        gc = var1;
        gd = var2;
        gj = var3;
    }

    private static void c(int var0, int var1, int var2, int var3, int var4) {
        int var5 = -1;

        for(int var6 = 0; var6 < bT; ++var6) {
            if (bW[var6] == 0) {
                var5 = var6;
                break;
            }
        }

        if (var5 != -1) {
            bW[var5] = 1;
            bU[var5] = var0;
            bV[var5] = var1;
            bX[var5] = var2;
            bY[var5] = var3;
            bZ[var5] = var4;
        }
    }

    private static void e(int var0, int var1) {
        fF = var0;
        fG = var1;
        fH = 1;
    }

    private static void C(int var0) {
        fZ = 0;
        fT = var0;
        fU = 0;
        fS = true;
        ga = false;
        Input.clearHeld();
        Input.clearPressed();
    }

    private static void a(String[] var0, int var1, int var2, int var3) {
        iD = var1;
        iF = var2;
        iG = var3;
        iB = var0.length;
        iC = 0;
        iE = new String[iB][];
        if ((iJ = r >> 1) < 120) {
            iJ = 120;
        }

        for(var1 = 0; var1 < iB; ++var1) {
            iE[var1] = FontGraph.wrapRegularText(var0[var1], iJ - 20);
        }

        if ((iK = iF - (iJ >> 1) - fO) < 2) {
            iK = 2;
        }

        if (iK + iJ > r - 2) {
            iK = r - 2 - iJ;
        }

        iz = true;
        iH = 0;
        iI = 0;
    }

    private static boolean e(int var0, int var1, int var2) {
        if (a) {
            return false;
        } else {
            var0 = var0 == 1 ? 0 : 1;
            if (cl[var0] != -1) {
                return false;
            } else {
                cl[var0] = 0;
                cj[var0] = var1;
                ck[var0] = var2;
                return true;
            }
        }
    }

    private static void a(int var0, int var1, int var2, int var3, boolean var4) {
        int var5 = -1;

        for(int var6 = 0; var6 < cV; ++var6) {
            if (cY[var6] == -1) {
                var5 = var6;
                break;
            }
        }

        if (var5 != -1) {
            cY[var5] = 1;
            cW[var5] = var0;
            cX[var5] = var1;
            da[var5] = var2;
            db[var5] = var3;
            dc[var5] = var4;
        }
    }

    private static void a(String var0, int var1, int var2, int var3, int var4, int var5) {
        var4 = -1;

        for(int var6 = 0; var6 < 5; ++var6) {
            if (dT[var6] == -1) {
                var4 = var6;
                break;
            }
        }

        if (var4 != -1) {
            dO[var4] = var0;
            dP[var4] = var2;
            dQ[var4] = var3;
            dR[var4] = 0;
            dS[var4] = var5;
            dT[var4] = 0;
            dU[var4] = var1;
        }
    }

    private static void f(int var0, int var1) {
        dX = 1;
        dV = var0;
        dW = var1;
    }

    private static void b(String var0) {
        co = var0;
        cn = 0;
        cm = true;
    }

    private static boolean f(int var0, int var1, int var2) {
        int var3 = cc[0] == -1 ? 0 : 1;
        if (cc[var3] != -1) {
            return false;
        } else {
            cc[var3] = 0;
            ce[var3] = var2;
            ca[var3] = var0;
            cb[var3] = var1;
            return true;
        }
    }

    private static void D(int var0) {
        ifSt = 0;
        id = var0;
        ie = true;
    }

    private static boolean g(int var0, int var1) {
        if (a) {
            return false;
        } else {
            int var2 = ch[0] == -1 ? 0 : 1;
            if (ch[var2] != -1) {
                return false;
            } else {
                ch[var2] = 0;
                cf[var2] = var0;
                cg[var2] = var1;
                return true;
            }
        }
    }

    private static void O() {
        for(int var0 = 0; var0 < bM; ++var0) {
            if (bP[var0] == 1) {
                int[] var10000 = bN;
                var10000[var0] += bQ[var0];
                if (bN[var0] >= fO && bN[var0] <= fO + r && bO[var0] >= fP && bO[var0] <= fP + s) {
                    if (a(fe - bN[var0]) < fi && a(ff - fj - bO[var0]) < fj) {
                        if (B == 5 && fd == 0 && bS[var0] < 3) {
                            bQ[var0] = -bQ[var0];
                            var10000 = bN;
                            var10000[var0] += bQ[var0];
                            return;
                        }

                        a(bR[var0], bN[var0] < fe ? 1 : -1);
                        bP[var0] = -1;
                        return;
                    }
                } else {
                    bP[var0] = -1;
                }
            }
        }

    }

    private static void P() {
        for(int var0 = 0; var0 < bT; ++var0) {
            if (bW[var0] != 0) {
                int[] var10000 = bU;
                var10000[var0] += bX[var0];
                var10000 = bV;
                var10000[var0] += bY[var0];
                int var10002 = bY[var0]++;
                if (bV[var0] > fP + s) {
                    bW[var0] = 0;
                }

                if (a(fe - bU[var0]) < fi && a(ff - fj - bV[var0]) < fj) {
                    a(bZ[var0], bU[var0] < fe ? 1 : -1);
                    bW[var0] = -1;
                    return;
                }
            }
        }

    }

    private static void Q() {
        if (fx) {
            aL = 0;
        }

    }

    private static void R() {
        if (fu) {
            fI = fe - bC + bF * fm;
            fJ = ff - bE;
        }

        int var0;
        label788:
        switch (B) {
            case 1:
                if (++D > 30) {
                    D = 0;
                }

                if (D % 15 < 5) {
                    C = 0;
                } else {
                    C = 1;
                }

                S();

                for(var0 = 0; var0 < du; ++var0) {
                    if (dD[var0] == 1 && ff == dA[var0] && a(fe - dz[var0]) < 30 && fE == -1) {
                        p = "Trò chuyện";
                        d = true;
                        fE = var0;
                        break;
                    }
                }

                if (gm == w && gr == 1 && ff == go && a(fe - gn) < 30) {
                    p = "Nói chuyện";
                    d = true;
                    fB = true;
                }

                var0 = 0;

                while(true) {
                    if (var0 >= lt) {
                        break label788;
                    }

                    if (ff == lv[var0] && a(fe - lu[var0]) < 30 && fD == -1) {
                        p = "Giao tiếp";
                        d = true;
                        fD = var0;
                        break label788;
                    }

                    ++var0;
                }
            case 2:
                if (++D >= 10) {
                    D = 0;
                    fo = 0;
                    if (eX) {
                        fo = fm << 1;
                    }
                }

                C = (D >> 1) + 2;
                if (fm == 1) {
                    if (GameMap.hasFlag(fe + fi, ff - fj, TileFlags.WALL_LEFT_SIDE)) {
                        z = 0;
                        fe = I(fe + fi) - fi;
                    }
                } else if (GameMap.hasFlag(fe - fi - 1, ff - fj, TileFlags.WALL_RIGHT_SIDE)) {
                    z = 0;
                    fe = I(fe - fi - 1) + GameMap.TILE_SIZE + fi;
                }

                if ((GameMap.flagsAtPixel(fe, ff - 1) & TileFlags.WATER) == TileFlags.WATER) {
                    fe += z >> 1;
                } else {
                    fe += z;
                }

                if (z > 0) {
                    --z;
                } else if (z < 0) {
                    ++z;
                } else {
                    B = 1;
                    fo = 0;
                }

                if (ir != -1) {
                    if (a(fe - is[ir]) >= 20) {
                        ir = -1;
                        if (GameMap.hasFlag(fe, ff, TileFlags.SOLID)) {
                            B = 1;
                        } else {
                            B = 4;
                            A = 2;
                            z = 3 * fm;
                            E = 0;
                        }
                    }
                } else if ((GameMap.flagsAtPixel(fe, ff) & TileFlags.SOLID) != TileFlags.SOLID) {
                    B = 4;
                    z = 3 * fm;
                    E = 0;
                }

                y();
                e(fm, fe - (fm << 3), ff);
                S();
                break;
            case 3:
                fe += z;
                if ((ff += A) < 0) {
                    ff = 0;
                    A = -1;
                }

                if (D == 0) {
                    C = 7;
                } else {
                    C = 23;
                }

                ++A;
                if (eI && !fx) {
                    if (A == -3) {
                        C = 8;
                    } else if (A == -2) {
                        C = 9;
                    } else if (A == -1) {
                        C = 10;
                    } else if (A == 0) {
                        C = 11;
                    }
                }

                if (A == 0) {
                    B = 4;
                    D = 0;
                    E = eI ? 1 : 0;
                    A = 1;
                    d = true;
                }

                if (fn < 0) {
                    ++fn;
                }

                if (fm == 1) {
                    if ((GameMap.flagsAtPixel(fe + fi, ff) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                        fe = I(fe + fi) - fi;
                        z = 0;
                    }
                } else if ((GameMap.flagsAtPixel(fe - fi, ff) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                    fe = I(fe + 24 - fi) + fi;
                    z = 0;
                }
                break;
            case 4:
                fc = -1;
                fe += z;
                ff += A;
                if (A < 18) {
                    ++A;
                }

                if (z > 0) {
                    --z;
                }

                C = 7;
                if (ff >= GameMap.pixelHeight) {
                    B = 6;
                    PlayerStats.kill();
                } else if (ff + 4 >= GameMap.pixelHeight) {
                    B = 6;
                    PlayerStats.kill();
                } else if (A > 4 && (GameMap.flagsAtPixel(fe, ff + 3) & TileFlags.SOLID) == TileFlags.SOLID) {
                    if (A > 12) {
                        B = 8;
                    } else {
                        B = 1;
                    }

                    A = 0;
                    z = 0;
                    E = 0;
                    D = 0;
                    ff = I(ff + 3);
                    e(-1, fe - -8, ff);
                    e(1, fe - 8, ff);
                    if (eM) {
                        for(var0 = 0; var0 < ii; ++var0) {
                            if (ff == il[var0] && a(fe - ik[var0]) < 16) {
                                fe = ik[var0];
                                B = 18;
                                ij = var0;
                                im[var0] = fm;
                                in[var0] = 0;
                            }
                        }
                    }

                    if (P == 11 && fC < 20) {
                        fC = 0;
                        b("Hit: " + fC);
                    }
                } else {
                    if (GameMap.hasFlag(fe, ff, TileFlags.WATER) && ff % GameMap.TILE_SIZE > 8) {
                        g(fe, H(ff) + 8);
                        ff = H(ff) + 8;
                        B = 11;
                        z = fm << 1;
                        A >>= 2;
                        ff = H(ff) + 12;
                        if (!PlayerStats.hasMp() || !eP) {
                            B = 12;
                        }
                    }

                    if (GameMap.hasFlag(fe, ff, TileFlags.DEEP_WATER)) {
                        B = 12;
                    }

                    if (E == 1 && !fx) {
                        if (A == 3) {
                            C = 11;
                        } else if (A == 2) {
                            C = 8;
                        } else if (A == 1) {
                            C = 9;
                        } else if (A == 0) {
                            C = 10;
                        }
                    } else {
                        C = 23;
                    }

                    if (A > 6) {
                        C = 12;
                    }

                    if (fn < 0) {
                        ++fn;
                    }

                    if (fm == 1) {
                        if ((GameMap.flagsAtPixel(fe + fi, ff) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                            fe = I(fe + fi) - fi;
                            z = 0;
                        }
                    } else if ((GameMap.flagsAtPixel(fe - fi, ff) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                        fe = I(fe + 24 - fi) + fi;
                        z = 0;
                    }

                    w();
                    if (gN && A >= 4 && a(fe - gP) < 30 && a(ff - (gQ - 25)) < 5) {
                        A = -(A >> 1);
                        z = fm << 3;
                        D = 0;
                    }
                }
                break;
            case 5:
                ++D;
                if (fm == 1) {
                    if ((GameMap.flagsAtPixel(fe + fi, ff - fj) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                        z = 0;
                    }
                } else if ((GameMap.flagsAtPixel(fe - fi, ff - fj) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                    z = 0;
                }

                fe += z;
                if ((ff += A) < 0) {
                    A = 0;
                    ff = 0;
                }

                if (A == 0) {
                    if (ir == -1 && (GameMap.flagsAtPixel(fe, ff) & TileFlags.SOLID) != TileFlags.SOLID) {
                        B = 4;
                        fc = -1;
                        z = (Q >> 1) * fm;
                        E = 0;
                        D = 0;
                    }

                    if (ir != -1 && a(fe - is[ir]) >= 20) {
                        ir = -1;
                        if (GameMap.hasFlag(fe, ff, TileFlags.SOLID)) {
                            B = 1;
                        } else {
                            B = 4;
                            A = 2;
                            z = 3 * fm;
                            fc = -1;
                            E = 0;
                        }
                    }
                } else if (A < 0) {
                    if (++A == 0) {
                        A = 1;
                    }
                } else {
                    if (A < 20 && D % 5 == 0) {
                        ++A;
                    }

                    if (A > 3) {
                        A = 3;
                    }

                    if (A > 2 && (GameMap.flagsAtPixel(fe, ff + 3) & TileFlags.SOLID) == TileFlags.SOLID) {
                        A = 0;
                        z = 0;
                        ff = I(ff + 3);
                        if (P == 11 && fC < 20) {
                            fC = 0;
                            b("Hit: " + fC);
                        }
                    }

                    if (GameMap.hasFlag(fe, ff, TileFlags.WATER) && ff % GameMap.TILE_SIZE > 8) {
                        g(fe, H(ff) + 8);
                        B = 11;
                        z = fm << 1;
                        A >>= 2;
                        ff = H(ff) + 12;
                        fc = -1;
                        if (!PlayerStats.hasMp() || !eP) {
                            B = 12;
                        }
                        break;
                    }

                    if (GameMap.hasFlag(fe, ff, TileFlags.DEEP_WATER)) {
                        B = 12;
                        fc = -1;
                        break;
                    }

                    w();
                }

                if (z > 0) {
                    --z;
                } else if (z < 0) {
                    ++z;
                }

                if (z != 0) {
                    y();
                }

                label728:
                switch (ft) {
                    case 0:
                        switch (D) {
                            case 1:
                            case 2:
                            case 3:
                                fc = -1;
                                C = 13;
                                break label728;
                            case 4:
                                fc = 0;
                                C = 14;
                                break label728;
                            case 5:
                                fc = 1;
                                C = 15;
                                break label728;
                            case 6:
                                fc = 1;
                                C = 15;
                                u();
                                break label728;
                            case 7:
                                fc = 2;
                                C = 16;
                                break label728;
                            case 8:
                                fc = 2;
                                C = 16;
                                if (fv) {
                                    fv = false;
                                    ft = 2;
                                } else {
                                    ft = 1;
                                }

                                D = 1;
                            default:
                                break label728;
                        }
                    case 1:
                        switch (D) {
                            case 1:
                                fc = 1;
                                C = 15;
                                break label728;
                            case 2:
                                fc = 1;
                                C = 15;
                                ft = 10;
                            default:
                                break label728;
                        }
                    case 2:
                        switch (D) {
                            case 1:
                            case 2:
                            case 3:
                                fc = 3;
                                C = 17;
                                break label728;
                            case 4:
                                fc = 4;
                                C = 18;
                                break label728;
                            case 5:
                                fc = 5;
                                C = 19;
                                break label728;
                            case 6:
                                fc = 6;
                                C = 20;
                                u();
                                break label728;
                            case 7:
                                fc = 6;
                                C = 20;
                                if (fv && fs < 2) {
                                    if (A == 0 && fr) {
                                        D = 0;
                                        fv = false;
                                        ft = 9;
                                    } else {
                                        fv = false;
                                        ft = 3;
                                        ++fs;
                                    }
                                } else {
                                    ft = 10;
                                }

                                D = 1;
                            default:
                                break label728;
                        }
                    case 3:
                        switch (D) {
                            case 1:
                                fc = 1;
                                C = 15;
                                break;
                            case 2:
                                fc = 1;
                                C = 15;
                                break;
                            case 3:
                                fc = 2;
                                C = 16;
                                break;
                            case 4:
                                fc = 2;
                                C = 16;
                                if (fv) {
                                    fv = false;
                                    ft = 2;
                                } else {
                                    ft = 1;
                                }

                                D = 1;
                        }
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    default:
                        break;
                    case 9:
                        switch (D) {
                            case 1:
                            case 2:
                            case 3:
                                fc = -1;
                                C = 13;
                                break label728;
                            case 4:
                                fc = 0;
                                C = 14;
                                break label728;
                            case 5:
                            case 6:
                                fc = 9;
                                C = 15;
                                var0 = A();
                                fr = false;
                                if (var0 != -1) {
                                    fr = true;
                                    f(jJ[var0], jK[var0] - (jL[var0] >> 1), fm);
                                    jO[var0] = 9;
                                    jT[var0] = -8;
                                    Q();
                                }
                                break label728;
                            case 7:
                            case 8:
                                fc = 10;
                                C = 16;
                                break label728;
                            case 9:
                                fc = -1;
                                C = 16;
                                if (fr) {
                                    B = 3;
                                    z = 0;
                                    A = -8;
                                } else {
                                    D = 0;
                                    E = 0;
                                    ft = 10;
                                }
                            default:
                                break label728;
                        }
                    case 10:
                        switch (D) {
                            case 1:
                                fc = 2;
                                C = 16;
                                break;
                            case 2:
                                fc = 0;
                                C = 14;
                                break;
                            case 3:
                            case 4:
                                C = 13;
                                fc = -1;
                                break;
                            case 5:
                                C = 13;
                                fc = -1;
                                if (A != 0) {
                                    B = 4;
                                } else {
                                    B = 1;
                                }

                                fs = 0;
                                E = 0;
                        }
                }

                if (fd == 1) {
                    fc = -1;
                }

                if (B == 5) {
                    if (A != 0) {
                        C += 14;
                    } else if (z != 0) {
                        C += 22;
                    }
                }
                break;
            case 6:
                fe += z;
                if (z > 1) {
                    --z;
                }

                if (z < -1) {
                    ++z;
                }

                ++A;
                ff += A;
                C = 21;
                if (ff >= GameMap.pixelHeight || ff > fP + s + fh || GameMap.hasFlag(fe, ff - fh, TileFlags.DEEP_WATER)) {
                    fe = fp;
                    ff = fq;
                    B = 4;
                    PlayerStats.revive();
                    A = 0;
                    z = 0;
                    E = 0;
                    D = 0;
                    Leveling.applyDeathPenalty();
                    d = true;
                    if (hc) {
                        hg = hh;
                    }

                    if (hi) {
                        hm = hn;
                    }

                    au();
                    s(5);
                    e = 7;
                }
                break;
            case 7:
                if ((GameMap.flagsAtPixel(fe + fi, ff - fj) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                    z = -z;
                }

                if ((GameMap.flagsAtPixel(fe - fi, ff - fj) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                    z = -z;
                }

                fe += z;
                ff += A;
                C = 21;
                if (z > 1) {
                    --z;
                }

                if (z < -1) {
                    ++z;
                }

                if (++A > 0) {
                    A = 0;
                }

                if (++D >= 7) {
                    B = 4;
                    fn = -20;
                    E = 0;
                    D = 0;
                    A = 0;
                }

                x();
                break;
            case 8:
                C = 22;
                if (D < 5) {
                    ++D;
                } else if (!Input.held[Input.KEY_DOWN]) {
                    B = 1;
                }

                if (++E > 6 && GameMap.hasFlag(fe + fi, ff + 2, TileFlags.DIGGABLE) && GameMap.hasFlag(fe - fi, ff + 2, TileFlags.DIGGABLE) && eL & PlayerStats.hasMp()) {
                    B = 13;
                }
            case 9:
            case 10:
            case 22:
            default:
                break;
            case 11:
                U();
                break;
            case 12:
                ff += A;
                if (A < 20 && x % 2 == 0) {
                    ++A;
                }

                C = 7;
                if (ff >= GameMap.pixelHeight) {
                    PlayerStats.kill();
                    B = 6;
                    fc = -1;
                } else if (GameMap.hasFlag(fe, ff, TileFlags.SOLID)) {
                    ff = H(ff);
                    if (GameMap.hasFlag(fe, ff - 2, TileFlags.WATER)) {
                        B = 1;
                        A = 0;
                        z = 0;
                        E = 0;
                        D = 0;
                    } else if (eO) {
                        B = 17;
                        A = 0;
                        z = 0;
                        E = 0;
                        D = 0;
                    } else {
                        B = 6;
                    }

                    ff = I(ff);
                } else if (GameMap.hasFlag(fe, ff - fh - 5, TileFlags.DEEP_WATER)) {
                    PlayerStats.kill();
                    B = 6;
                    fc = -1;
                }
                break;
            case 13:
                C = 24;
                ff += 28;
                B = 14;
                break;
            case 14:
                if (x % 20 > 2) {
                    C = 25;
                } else {
                    C = 26;
                }

                if (x % 8 == 0) {
                    if (PlayerStats.drainMp(1)) {
                        B = 15;
                    }

                    d = true;
                }
                break;
            case 15:
                ff -= 10;
                A = -10;
                B = 3;
                break;
            case 16:
                if (++D >= 20) {
                    D = 0;
                }

                if ((C = (D >> 2) + 2) % 2 == 0) {
                    fc = 7;
                } else {
                    fc = 8;
                }

                if (fm == 1) {
                    if (GameMap.hasFlag(fe + fi, ff - fj, TileFlags.WALL_LEFT_SIDE)) {
                        z = 0;
                        fe = I(fe + fi) - fi;
                    }
                } else if (GameMap.hasFlag(fe - fi - 1, ff - fj, TileFlags.WALL_RIGHT_SIDE)) {
                    z = 0;
                    fe = I(fe - fi - 1) + GameMap.TILE_SIZE + fi;
                }

                fe += z;
                if (z > 0) {
                    --z;
                } else if (z < 0) {
                    ++z;
                } else {
                    B = 17;
                }

                if ((GameMap.flagsAtPixel(fe, ff) & TileFlags.SOLID) != TileFlags.SOLID) {
                    B = 12;
                    z = fm << 2;
                    E = 0;
                }
                break;
            case 17:
                if (++D > 30) {
                    D = 0;
                }

                if (D % 15 < 5) {
                    C = 0;
                    fc = 7;
                } else {
                    C = 1;
                    fc = 8;
                }
                break;
            case 18:
                if (x % 8 > 3) {
                    C = 43;
                } else {
                    C = 44;
                    e(fm, fe - (fm << 4), ff - 5);
                }

                D = 0;
                break;
            case 19:
                if (x % 8 > 3) {
                    C = 43;
                } else {
                    C = 44;
                    e(fm, fe - (fm << 4), ff - 5);
                }

                if (D > 0) {
                    --D;
                    C = 45;
                }

                if (fm == 1) {
                    if (GameMap.hasFlag(fe + fi, ff - fj, TileFlags.WALL_LEFT_SIDE)) {
                        z = 0;
                        fe = I(fe + fi) - fi;
                    }
                } else if (GameMap.hasFlag(fe - fi - 1, ff - fj, TileFlags.WALL_RIGHT_SIDE)) {
                    z = 0;
                    fe = I(fe - fi - 1) + GameMap.TILE_SIZE + fi;
                }

                if ((GameMap.flagsAtPixel(fe, ff - 1) & TileFlags.WATER) == TileFlags.WATER) {
                    fe += z >> 1;
                } else {
                    fe += z;
                }

                ik[ij] = fe;
                il[ij] = ff;
                if (z > 0) {
                    --z;
                } else if (z < 0) {
                    ++z;
                } else {
                    B = 18;
                    fo = 0;
                }

                if (ir != -1) {
                    if (a(fe - is[ir]) >= 20) {
                        ir = -1;
                        if (GameMap.hasFlag(fe, ff, TileFlags.SOLID)) {
                            B = 18;
                        } else {
                            B = 20;
                            A = 2;
                            z = 3 * fm;
                            E = 0;
                        }
                    }
                } else if ((GameMap.flagsAtPixel(fe, ff) & TileFlags.SOLID) != TileFlags.SOLID) {
                    B = 20;
                    z = 3 * fm;
                    E = 0;
                }

                e(fm, fe - (fm << 3), ff);
                S();
                if ((var0 = A()) != -1 && (jO[var0] == 2 || jO[var0] == 3)) {
                    k(var0, 2, -fm);
                    f(jJ[var0], jK[var0] - (jL[var0] >> 1), fm);
                    a("-2", 0, jJ[var0], jK[var0] - jL[var0], 0, -2);
                }
                break;
            case 20:
                try {
                    if (fm == 1) {
                        if ((GameMap.flagsAtPixel(fe + fi, ff - fj) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                            z = 0;
                        }
                    } else if ((GameMap.flagsAtPixel(fe - fi, ff - fj) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                        z = 0;
                    }

                    fc = -1;
                    fe += z;
                    ff += A;
                    if (A < 20) {
                        ++A;
                    }

                    if (z > 0) {
                        --z;
                    }

                    if (x % 8 > 3) {
                        C = 43;
                    } else {
                        C = 44;
                        e(fm, fe - (fm << 4), ff - 5);
                    }

                    if (A > 4 && (GameMap.flagsAtPixel(fe, ff) & TileFlags.SOLID) == TileFlags.SOLID) {
                        if (A < 8) {
                            B = 18;
                            A = 0;
                            z = 0;
                            E = 0;
                            D = 0;
                            ff = I(ff);
                        } else {
                            B = 21;
                            A = -(A / 3);
                            ff = I(ff);
                        }
                    }

                    if (GameMap.hasFlag(fe, ff, TileFlags.WATER) && ff % GameMap.TILE_SIZE > 8) {
                        g(fe, H(ff) + 8);
                    }

                    ik[ij] = fe;
                    il[ij] = ff;
                    if (GameMap.hasFlag(fe, ff, TileFlags.DEEP_WATER)) {
                        io[ij] = 1;
                        ij = -1;
                        B = 12;
                    }
                } catch (Exception var2) {
                }

                if (fn < 0) {
                    ++fn;
                }
                break;
            case 21:
                if (fm == 1) {
                    if ((GameMap.flagsAtPixel(fe + fi, ff - fj) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                        z = 0;
                    }
                } else if ((GameMap.flagsAtPixel(fe - fi, ff - fj) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                    z = 0;
                }

                fe += z;
                ff += A;
                ik[ij] = fe;
                il[ij] = ff;
                if (z > 0) {
                    --z;
                }

                if (++A == 0) {
                    B = 20;
                    D = 0;
                    E = eI ? 1 : 0;
                    A = 1;
                    d = true;
                }

                if (fn < 0) {
                    ++fn;
                }

                if (x % 8 > 3) {
                    C = 43;
                } else {
                    C = 44;
                    e(fm, fe - (fm << 4), ff - 5);
                }

                if (D > 0) {
                    --D;
                    C = 45;
                }
                break;
            case 23:
                T();
        }

        if (x % 3 == 0) {
            if (fw) {
                if (PlayerStats.drainMp(1)) {
                    fw = false;
                }

                d = true;
            }

            if (fx) {
                if (PlayerStats.drainMp(1)) {
                    fx = false;

                    for(int var1 = 0; var1 < 4; ++var1) {
                        Image var3 = heroHeads[var1];
                        heroHeads[var1] = heroTransformHeads[var1];
                        heroTransformHeads[var1] = var3;
                    }
                }

                d = true;
            }
        }

    }

    private static final void S() {
        if (!a) {
            if (GameMap.hasFlag(fe, ff + 1, TileFlags.BRIDGE)) {
                GameMap.pressBridge(fe, ff + 1);
                GameMap.pressBridge(fe, ff - 2);
            }

            if (GameMap.hasFlag(fe - GameMap.TILE_SIZE, ff + 1, TileFlags.BRIDGE_PRESSED)) {
                GameMap.releaseBridge(fe - GameMap.TILE_SIZE, ff + 1);
                GameMap.releaseBridge(fe - GameMap.TILE_SIZE, ff - 2);
            }

            if (GameMap.hasFlag(fe + GameMap.TILE_SIZE, ff + 1, TileFlags.BRIDGE_PRESSED)) {
                GameMap.releaseBridge(fe + GameMap.TILE_SIZE, ff + 1);
                GameMap.releaseBridge(fe + GameMap.TILE_SIZE, ff - 2);
            }

        }
    }

    private static void T() {
        if (++D == 1) {
            f(fe, ff - 40);
            aL = 0;
            if (heroTransformHeads == null) {
                heroTransformHeads = new Image[4];

                try {
                    heroTransformHeads[0] = Character.loadHeroTransformHead(0);
                    heroTransformHeads[1] = Character.loadHeroTransformHead(1);
                } catch (Exception var2) {
                }

                heroTransformHeads[2] = heroTransformHeads[1];
                heroTransformHeads[3] = heroTransformHeads[0];
            }
        } else {
            Image var0;
            int var1;
            if (D >= 30) {
                B = 1;
                D = 0;
                if (!PlayerStats.hasMp()) {
                    return;
                }

                for(var1 = 0; var1 < 4; ++var1) {
                    var0 = heroHeads[var1];
                    heroHeads[var1] = heroTransformHeads[var1];
                    heroTransformHeads[var1] = var0;
                }

                fx = !fx;
            } else if (D <= 10) {
                if (D % 5 == 0) {
                    for(var1 = 0; var1 < 4; ++var1) {
                        var0 = heroHeads[var1];
                        heroHeads[var1] = heroTransformHeads[var1];
                        heroTransformHeads[var1] = var0;
                    }
                }
            } else if (D < 20) {
                if (D % 3 == 0) {
                    for(var1 = 0; var1 < 4; ++var1) {
                        var0 = heroHeads[var1];
                        heroHeads[var1] = heroTransformHeads[var1];
                        heroTransformHeads[var1] = var0;
                    }
                }
            } else if (D < 30 && D % 2 == 0) {
                for(var1 = 0; var1 < 4; ++var1) {
                    var0 = heroHeads[var1];
                    heroHeads[var1] = heroTransformHeads[var1];
                    heroTransformHeads[var1] = var0;
                }
            }
        }

        if (D % 2 > 0) {
            C = 0;
        } else {
            C = 1;
        }
    }

    private static void U() {
        if (++D >= 5) {
            D = 0;
            fo = 0;
        }

        C = D + 2;
        if (fm == 1) {
            if (GameMap.hasFlag(fe + fi, ff - 1, TileFlags.WALL_LEFT_SIDE)) {
                z = 0;
                fe = I(fe + fi) - fi;
            }
        } else if (GameMap.hasFlag(fe - fi - 1, ff - 1, TileFlags.WALL_RIGHT_SIDE)) {
            z = 0;
            fe = I(fe - fi - 1) + GameMap.TILE_SIZE + fi;
        }

        fe += z;
        if (z > 0) {
            --z;
        } else if (z < 0) {
            ++z;
        } else if (!eY) {
            B = 12;
            fo = 0;
        } else {
            B = 3;
            A = -8;
            E = 0;
            D = 0;
        }

        if (x % 8 == 0) {
            if (PlayerStats.drainMp(3)) {
                z = 0;
                B = 12;
            }

            d = true;
        }

        for(int var0 = 0; var0 < iq; ++var0) {
            if (a(fe - is[var0]) < 20 && it[var0] > ff && it[var0] - 24 < ff) {
                z = 0;
                if (fe < is[var0]) {
                    fe = is[var0] - 20;
                } else {
                    fe = is[var0] + 20;
                }

                B = 12;
                break;
            }
        }

        g(fe, ff);
        e(fm, fe - (fm << 3), ff);
    }

    private static boolean V() {
        if (!fS) {
            if (bu > 1) {
                bu >>= 1;
                d = true;
            }

            return false;
        } else {
            if (ga) {
                if (Input.pressed[Input.KEY_FIRE]) {
                    Input.clearPressed();
                    M();
                    if (iC == iB) {
                        iz = false;
                        ga = false;
                        z();
                    }
                }
            } else {
                if (bu < bt) {
                    bu += (bt - bu >> 1) + 1;
                }

                if (fV) {
                    if (fX < fY) {
                        fX += 2;
                    } else {
                        fX = fY;
                    }
                }

                if (++fZ >= aA[fT][fU]) {
                    fZ = aA[fT][fU];
                    int var0;
                    switch (ay[fT][fU]) {
                        case 1:
                            Input.keyDown(az[fT][fU]);
                            ++fU;
                            break;
                        case 2:
                            Input.keyUp(az[fT][fU]);
                            ++fU;
                            break;
                        case 3:
                            fS = false;
                            d = true;
                            ++fU;
                            break;
                        case 4:
                            if (B == 1) {
                                fV = false;
                                var0 = 0;
                                int var1 = 0;
                                switch (aD[az[fT][fU]]) {
                                    case 0:
                                        var0 = fe;
                                        var1 = ff;
                                        break;
                                    case 1:
                                        var0 = lu[aC[az[fT][fU]]];
                                        var1 = lv[aC[az[fT][fU]]];
                                        break;
                                    case 2:
                                        var0 = dz[aC[az[fT][fU]]];
                                        var1 = dA[aC[az[fT][fU]]];
                                        break;
                                    case 3:
                                    case 4:
                                        var0 = gn;
                                        var1 = go;
                                }

                                var1 -= 40;
                                a(aF[az[fT][fU]], -1, var0, var1);
                                ++fU;
                                ga = true;
                            }
                            break;
                        case 5:
                            a(w, az[fT][fU], ff - 50, -1);
                            ++fU;
                            break;
                        case 6:
                            gb = -1;
                            ++fU;
                            break;
                        case 7:
                            fu = true;
                            ++fU;
                            break;
                        case 8:
                            fu = false;
                            ++fU;
                            break;
                        case 9:
                            fO = aB[az[fT][fU]][0] - bC;
                            fP = aB[az[fT][fU]][1] - bD;
                            if (fO < 0) {
                                fO = 0;
                            }

                            if (fO > fQ) {
                                fO = fQ;
                            }

                            if (fP < 0) {
                                fP = 0;
                            }

                            if (fP > fR) {
                                fP = fR;
                            }

                            fI = fO;
                            fJ = fP;
                            ++fU;
                            break;
                        case 10:
                            fI = aB[az[fT][fU]][0] - bC;
                            fJ = aB[az[fT][fU]][1] - bD;
                            if (fI < 0) {
                                fI = 0;
                            }

                            if (fI > fQ) {
                                fI = fQ;
                            }

                            if (fJ < 0) {
                                fJ = 0;
                            }

                            if (fJ > fR) {
                                fJ = fR;
                            }

                            ++fU;
                            break;
                        case 11:
                            fI = gP - bC;
                            fJ = gQ - bD;
                            if (fI < 0) {
                                fI = 0;
                            }

                            if (fI > fQ) {
                                fI = fQ;
                            }

                            if (fJ < 0) {
                                fJ = 0;
                            }

                            if (fJ > fR) {
                                fJ = fR;
                            }

                            ++fU;
                            break;
                        case 12:
                            fJ = fP += 2;
                            if (fP > aB[az[fT][fU]][1] - bD) {
                                ++fU;
                            }
                        case 13:
                        default:
                            break;
                        case 14:
                            fI = fO -= 2;
                            if (fO < aB[az[fT][fU]][0] - bC) {
                                ++fU;
                            }
                            break;
                        case 15:
                            fV = true;
                            fW = FontGraph.wrapBoldText(aG[az[fT][fU]], r - 40);
                            if ((fY = (bt >> 1) - (fW.length << 3)) < 3) {
                                fY = 2;
                            }

                            fX = -fW.length << 3;
                            ++fU;
                            break;
                        case 16:
                            dx = true;
                            dy = az[fT][fU];
                            ++fU;
                            break;
                        case 17:
                            fp = fe = ju;
                            fq = ff = jv;
                            B();
                            Save.pendingSave = true;
                            x(az[fT][fU]);
                            return true;
                        case 18:
                            V(az[fT][fU]);
                            ++fU;
                            break;
                        case 19:
                            gk = true;
                            ++fU;
                            break;
                        case 20:
                            gk = false;
                            gm = 0;
                            gn = 1080;
                            go = 408;
                            ++fU;
                            break;
                        case 21:
                            P = -1;
                            eM = true;
                            b("nhận bằng lái môtô");
                            a(0, lu[4], lv[4] - 40, 4);
                            ++fU;
                            break;
                        case 22:
                            for(var0 = 0; var0 <= 6; ++var0) {
                                GameMap.clearFlags(24, var0);
                            }

                            eF = true;
                            ++fU;
                            break;
                        case 23:
                            fu = false;
                            fI = dz[az[fT][fU]] - bC;
                            fJ = dA[az[fT][fU]] - bD;
                            if (fI < 0) {
                                fI = 0;
                            }

                            if (fI > fQ) {
                                fI = fQ;
                            }

                            if (fJ < 0) {
                                fJ = 0;
                            }

                            if (fJ > fR) {
                                fJ = fR;
                            }

                            ++fU;
                            break;
                        case 24:
                            dx = true;
                            dy = az[fT][fU];
                            ++fU;
                            break;
                        case 25:
                            he = true;
                            ++fU;
                            break;
                        case 26:
                            for(var0 = 0; var0 <= 10; ++var0) {
                                GameMap.clearFlags(24, var0);
                            }

                            eG = true;
                            ++fU;
                            break;
                        case 27:
                            fu = false;
                            fI = hp - bC;
                            fJ = hq - bD;
                            if (fI < 0) {
                                fI = 0;
                            }

                            if (fI > fQ) {
                                fI = fQ;
                            }

                            if (fJ < 0) {
                                fJ = 0;
                            }

                            if (fJ > fR) {
                                fJ = fR;
                            }

                            ++fU;
                            break;
                        case 28:
                            hr = 1;
                            ++fU;
                            break;
                        case 29:
                            Input.clearPressed();
                            Input.clearHeld();
                            tilesetImage = null;
                            waterfallImage = null;
                            waterSurfaceImage = null;
                            houseImages = null;
                            GameMap.tiles = null;
                            GameMap.flags = null;
                            System.gc();
                            npcSprites = null;
                            System.gc();
                            motorbikeFrames = null;
                            itemCrate = null;
                            itemMoney = null;
                            boss0Sprites = null;
                            System.gc();
                            boss1Sprites = null;
                            System.gc();
                            monsterSprites = null;
                            System.gc();
                            C();
                            classmateHeads = null;
                            npcDog = null;
                            enemyProjectiles = null;
                            dustSprites = null;
                            dartFrames = null;
                            auraSprites = null;
                            bloodSprites = null;
                            waterSplashSprites = null;
                            arrowIcons = null;
                            System.gc();
                            heroLegs = null;
                            heroBodies = null;
                            heroHeads = null;
                            heroTransformHeads = null;
                            slashEffects = null;
                            System.gc();
                            K();
                            return true;
                        case 30:
                            P = -1;
                            eT = false;
                            eU = true;
                            gm = 0;
                            gn = 648;
                            go = 288;
                            gk = false;
                            b("gặp cô Ayumi");
                            a(0, 1032, 368, 4);
                            ++fU;
                            break;
                        case 31:
                            fu = false;
                            fI = gn - bC;
                            fJ = go - bD;
                            if (fI < 0) {
                                fI = 0;
                            }

                            if (fI > fQ) {
                                fI = fQ;
                            }

                            if (fJ < 0) {
                                fJ = 0;
                            }

                            if (fJ > fR) {
                                fJ = fR;
                            }

                            ++fU;
                            break;
                        case 32:
                            gk = true;
                            gr = 2;
                            gp = -5;
                            gz = -1;
                            gK = 8;
                            ++fU;
                            break;
                        case 33:
                            fb = true;
                            gk = true;
                            ++fU;
                    }
                }
            }

            Input.held[Input.KEY_FIRE] = false;
            Input.pressed[Input.KEY_FIRE] = false;
            return false;
        }
    }

    private static void W() {
        for(int var0 = 0; var0 < de; ++var0) {
            if (dh[var0] != 0) {
                if (a(ff - (fh >> 1) - (dg[var0] - 7)) < 10 && a(fe - df[var0]) < 10) {
                    dd += dl[var0];
                    d = true;
                    dh[var0] = 301;
                    a("+" + dl[var0], 1, df[var0], dg[var0] - 15, 0, -1);
                }

                int[] var10000 = df;
                var10000[var0] += di[var0];
                var10000 = dg;
                var10000[var0] += dj[var0];
                int var10002;
                if (di[var0] > 1) {
                    var10002 = di[var0]--;
                }

                if (di[var0] < -1) {
                    var10002 = di[var0]++;
                }

                if (dj[var0] < 20) {
                    var10002 = dj[var0]++;
                }

                var10002 = dh[var0]++;
                if (dh[var0] > 200) {
                    dh[var0] = 0;
                }

                if (dh[var0] % 20 > 18) {
                    dk[var0] = 0;
                } else if (dh[var0] % 20 > 16) {
                    dk[var0] = 1;
                } else {
                    dk[var0] = 2;
                }

                if (dg[var0] >= 0 && dg[var0] < GameMap.pixelHeight && df[var0] < GameMap.pixelWidth && df[var0] >= 0) {
                    if (dj[var0] > 2 && (GameMap.flagsAtPixel(df[var0], dg[var0]) & TileFlags.SOLID) == TileFlags.SOLID) {
                        dg[var0] = H(dg[var0]);
                        if (dj[var0] > 2) {
                            dj[var0] = -(dj[var0] >> 1);
                        } else {
                            di[var0] = 0;
                            dj[var0] = 0;
                        }
                    }
                } else {
                    dh[var0] = 0;
                }
            }
        }

    }

    private static void X() {
        if (iz && iI < iE[iC].length && ++iH >= iE[iC][iI].length()) {
            iH = 0;
            ++iI;
        }

    }

    private static void Y() {
        for(int var0 = 0; var0 < du; ++var0) {
            int var10002;
            switch (dD[var0]) {
                case 1:
                    var10002 = dE[var0]++;
                    if (dE[var0] > 30) {
                        dE[var0] = 0;
                    }

                    if (dE[var0] % (var0 + 18) < var0 + 6) {
                        dG[var0] = 0;
                    } else {
                        dG[var0] = 1;
                    }

                    if (dx) {
                        dF[var0] = dy;
                        dJ[var0] = dy > dz[var0] ? 1 : -1;
                        dB[var0] = dJ[var0] << 2;
                        dB[var0] = dJ[var0] > 0 ? dB[var0] + var0 % 2 : dB[var0] - var0 % 2;
                        dD[var0] = 2;
                    } else {
                        if (dw) {
                            break;
                        }

                        var10002 = dF[var0]++;
                        if (dF[var0] > (dK[var0] + dz[var0]) % 20 + (dK[var0] >> 1)) {
                            if (iz && fE == var0) {
                                break;
                            }

                            dF[var0] = 0;
                            int var2;
                            if ((var2 = (x + dK[var0]) % 120 - 60) > 0) {
                                var2 += dK[var0];
                            } else {
                                var2 -= dK[var0];
                            }

                            dF[var0] = dz[var0] + var2;
                            dJ[var0] = var2 > 0 ? 1 : -1;
                            dB[var0] = dJ[var0] << 2;
                            dD[var0] = 2;
                            if (fE == var0) {
                                fE = -1;
                                p = " ";
                                d = true;
                            }
                        }

                        if (x % 10 == 1) {
                            B(var0);
                        }
                    }
                    break;
                case 2:
                    var10002 = dE[var0]++;
                    if (dE[var0] >= 10) {
                        dE[var0] = 0;
                    }

                    dG[var0] = (dE[var0] >> 1) + 2;
                    int[] var10000 = dz;
                    var10000[var0] += dB[var0];
                    if (dz[var0] <= dN[var0] && dz[var0] >= dM[var0] && a(dz[var0] - dF[var0]) >= 8) {
                        if (x % 10 == 0) {
                            B(var0);
                        }
                    } else {
                        dE[var0] = dF[var0] = 0;
                        var10000 = dz;
                        var10000[var0] -= dB[var0];
                        dB[var0] = 0;
                        dD[var0] = 1;
                    }
                case 3:
                case 4:
                default:
                    break;
                case 5:
                    var10002 = dE[var0]++;
                    switch (dF[var0]) {
                        case 0:
                            switch (dE[var0]) {
                                case 1:
                                case 2:
                                case 3:
                                    dI[var0] = -1;
                                    dG[var0] = 13;
                                    continue;
                                case 4:
                                    dI[var0] = 0;
                                    dG[var0] = 14;
                                    continue;
                                case 5:
                                    dI[var0] = 1;
                                    dG[var0] = 15;
                                    continue;
                                case 6:
                                    dI[var0] = 1;
                                    dG[var0] = 15;
                                    A(var0);
                                    continue;
                                case 7:
                                    dI[var0] = 2;
                                    dG[var0] = 16;
                                    continue;
                                case 8:
                                    dI[var0] = 2;
                                    dG[var0] = 16;
                                    dF[var0] = 2;
                                    dE[var0] = 1;
                                default:
                                    continue;
                            }
                        case 1:
                            switch (dE[var0]) {
                                case 1:
                                    dI[var0] = 1;
                                    dG[var0] = 15;
                                    continue;
                                case 2:
                                    dI[var0] = 1;
                                    dG[var0] = 15;
                                    dF[var0] = 10;
                                default:
                                    continue;
                            }
                        case 2:
                            switch (dE[var0]) {
                                case 1:
                                case 2:
                                case 3:
                                    dI[var0] = 3;
                                    dG[var0] = 17;
                                    continue;
                                case 4:
                                    dI[var0] = 4;
                                    dG[var0] = 18;
                                    continue;
                                case 5:
                                    dI[var0] = 5;
                                    dG[var0] = 19;
                                    continue;
                                case 6:
                                    dI[var0] = 6;
                                    dG[var0] = 20;
                                    A(var0);
                                    continue;
                                case 7:
                                    dI[var0] = 6;
                                    dG[var0] = 20;
                                    dF[var0] = 3;
                                    dE[var0] = 1;
                                default:
                                    continue;
                            }
                        case 3:
                            switch (dE[var0]) {
                                case 1:
                                    dI[var0] = 1;
                                    dG[var0] = 15;
                                    break;
                                case 2:
                                    dI[var0] = 1;
                                    dG[var0] = 15;
                                    break;
                                case 3:
                                    dI[var0] = 2;
                                    dG[var0] = 16;
                                    break;
                                case 4:
                                    dI[var0] = 2;
                                    dG[var0] = 16;
                                    dF[var0] = 1;
                                    dE[var0] = 1;
                            }
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        default:
                            break;
                        case 10:
                            switch (dE[var0]) {
                                case 1:
                                    dI[var0] = 2;
                                    dG[var0] = 16;
                                    break;
                                case 2:
                                    dI[var0] = 0;
                                    dG[var0] = 14;
                                    break;
                                case 3:
                                case 4:
                                    dG[var0] = 13;
                                    dI[var0] = -1;
                                    break;
                                case 5:
                                    dG[var0] = 13;
                                    dI[var0] = -1;
                                    dD[var0] = 1;
                                    dF[var0] = 120;
                                    dE[var0] = 0;
                            }
                    }
            }
        }

    }

    private static void Z() {
        if (++x == 10000) {
            x = 0;
        }

        ad();
        if (!V()) {
            Y();
            R();
            X();
        }
    }

    private static void aa() {
        for(int var0 = 0; var0 < cp; ++var0) {
            int var10002;
            if (cq[var0] > 0) {
                var10002 = cq[var0]--;
            }

            int[] var10000;
            label143:
            switch (cE[var0]) {
                case 1:
                    cJ[var0] = 1;
                    var10002 = cF[var0]++;
                    var10000 = cu;
                    var10000[var0] += cy[var0];
                    var10000 = cv;
                    var10000[var0] += cz[var0];
                    var10000 = cw;
                    var10000[var0] += cA[var0];
                    var10000 = cx;
                    var10000[var0] += cB[var0];
                    if (cF[var0] > 14) {
                        if (cy[var0] > 1) {
                            var10002 = cy[var0]--;
                        } else if (cy[var0] < -1) {
                            var10002 = cy[var0]++;
                        }

                        var10002 = cz[var0]++;
                    }

                    if (cA[var0] > 1) {
                        var10002 = cA[var0]--;
                    } else if (cA[var0] < -1) {
                        var10002 = cA[var0]++;
                    }

                    var10002 = cB[var0]++;
                    if (cv[var0] <= fP + s + 32 && cv[var0] < GameMap.pixelHeight) {
                        continue;
                    }

                    cF[var0] = 0;
                    cG[var0] = 0;
                    cE[var0] = 5;
                    break;
                case 2:
                    if (x % 12 > 7) {
                        cC[var0] = 1;
                        cI[var0] = 1;
                        if (x % 12 < 10) {
                            if (a(cu[var0] - cG[var0]) < 4) {
                                cF[var0] = cG[var0] = 0;
                                cE[var0] = 3;
                                break;
                            }

                            var10000 = cu;
                            var10000[var0] += cy[var0];
                        }

                        w(var0);
                    } else {
                        cI[var0] = 0;
                        cC[var0] = 0;
                    }
                    break;
                case 3:
                    if ((x + var0) % 10 > 5) {
                        cC[var0] = 1;
                    } else {
                        cC[var0] = 0;
                    }

                    var10002 = cF[var0]++;
                    cI[var0] = 0;
                    cJ[var0] = 0;
                    if (cF[var0] > 50) {
                        int var1;
                        if ((var1 = (x - var0) % (var0 + 80) - 40) > 0) {
                            var1 += 20;
                        } else {
                            var1 -= 20;
                        }

                        cG[var0] = cu[var0] + var1;
                        cH[var0] = var1 > 0 ? 1 : -1;
                        if (x % 4 > 1) {
                            cE[var0] = 2;
                            cy[var0] = cH[var0] << 1;
                        } else {
                            cE[var0] = 7;
                            cy[var0] = cH[var0] << 2;
                        }

                        if (cG[var0] < cK[var0]) {
                            cG[var0] = cK[var0];
                        }

                        if (cG[var0] > cL[var0]) {
                            cG[var0] = cL[var0];
                        }
                    }

                    if (x % 30 == 28) {
                        w(var0);
                        v(var0);
                    }
                    break;
                case 4:
                    var10002 = cF[var0]++;
                    if (cF[var0] < 3) {
                        cC[var0] = 0;
                        cI[var0] = 0;
                        cJ[var0] = 0;
                    } else if (cF[var0] < 14) {
                        cC[var0] = 1;
                        cI[var0] = 1;
                        cJ[var0] = 1;
                    } else {
                        cE[var0] = 3;
                        cF[var0] = 1;
                    }

                    if (cF[var0] == 3) {
                        switch (cD[var0]) {
                            case 0:
                            case 4:
                                b(cu[var0] + (cH[var0] << 3), cv[var0] - 15, cH[var0] * 12, ac[cD[var0]], 0);
                                break label143;
                            case 1:
                            case 5:
                                b(cu[var0] + (cH[var0] << 3), cv[var0] - 15, cH[var0] << 3, ac[cD[var0]], 1);
                                break label143;
                            case 2:
                            case 3:
                            case 6:
                                if (a(ff - cv[var0]) < 5 && a(fe - cu[var0]) < 30) {
                                    a(ac[cD[var0]], cH[var0]);
                                }
                        }
                    }
                    break;
                case 5:
                    var10002 = cF[var0]++;
                    if (cF[var0] > 550 + var0 * 5) {
                        cu[var0] = cs[var0];
                        cv[var0] = ct[var0];
                        cF[var0] = 0;
                        cE[var0] = 3;
                        cr[var0] = ae[cD[var0]];
                    }
                    break;
                case 6:
                    if ((x + var0) % 10 > 5) {
                        cC[var0] = 1;
                    } else {
                        cC[var0] = 0;
                    }

                    cI[var0] = 0;
                    cJ[var0] = 0;
                    var10002 = cF[var0]++;
                    if (cF[var0] >= 5) {
                        cG[var0] = fe;
                        if (cu[var0] < fe) {
                            var10000 = cG;
                            var10000[var0] -= 80;
                        } else {
                            var10000 = cG;
                            var10000[var0] += 80;
                        }

                        cF[var0] = 0;
                        cH[var0] = cG[var0] < fe ? -1 : 1;
                        cE[var0] = 7;
                        cy[var0] = cH[var0] << 3;
                        if (cG[var0] < cK[var0]) {
                            cG[var0] = cK[var0];
                        }

                        if (cG[var0] > cL[var0]) {
                            cG[var0] = cL[var0];
                        }
                    }
                    break;
                case 7:
                    if (a(cu[var0] - cG[var0]) < 12) {
                        cF[var0] = cG[var0] = 0;
                        cE[var0] = 3;
                        v(var0);
                    } else {
                        var10000 = cu;
                        var10000[var0] += cy[var0];
                        if (x % 2 == 1) {
                            cC[var0] = 1;
                            cI[var0] = 3;
                        } else {
                            cI[var0] = 2;
                            cC[var0] = 0;
                        }
                    }
            }

            cw[var0] = cu[var0];
            cx[var0] = cv[var0] - 9 + cC[var0] + ag[cD[var0]][cJ[var0]];
        }

    }

    private static void ab() {
        for(int var0 = 0; var0 < cM; ++var0) {
            if (cQ[var0] != 0) {
                if (a(ff - (fh >> 1) - (cP[var0] - 7)) < 10 && a(fe - cO[var0]) < 10) {
                    if (cN[var0] == 0) {
                        PlayerStats.restoreHp(cU[var0]);

                        a("+" + cU[var0], 0, cO[var0], cP[var0] - 15, 0, -1);
                    } else {
                        PlayerStats.restoreMp(cU[var0]);

                        a("+" + cU[var0], 2, cO[var0], cP[var0] - 15, 0, -1);
                    }

                    d = true;
                    cQ[var0] = 301;
                }

                int[] var10000 = cO;
                var10000[var0] += cR[var0];
                var10000 = cP;
                var10000[var0] += cS[var0];
                int var10002;
                if (cR[var0] > 1) {
                    var10002 = cR[var0]--;
                }

                if (cR[var0] < -1) {
                    var10002 = cR[var0]++;
                }

                if (cS[var0] < 20) {
                    var10002 = cS[var0]++;
                }

                var10002 = cQ[var0]++;
                if (cQ[var0] > 200) {
                    cQ[var0] = 0;
                }

                if (cP[var0] < GameMap.pixelHeight && cP[var0] >= 0 && cO[var0] < GameMap.pixelWidth && cO[var0] >= 0) {
                    if (cS[var0] > 2 && (GameMap.flagsAtPixel(cO[var0], cP[var0]) & TileFlags.SOLID) == TileFlags.SOLID) {
                        cP[var0] = H(cP[var0]);
                        if (cS[var0] > 2) {
                            cS[var0] = -(cS[var0] >> 1);
                        } else {
                            cR[var0] = 0;
                            cS[var0] = 0;
                        }
                    }
                } else {
                    cQ[var0] = 0;
                }
            }
        }

    }

    private static void ac() {
        if (hi) {
            int var0 = x % 10;
            int var1 = a(fe - hp);
            int var2 = a(ff - hq);
            switch (hr) {
                case 0:
                    if (var0 < 7) {
                        hH = hx[0];
                        hI = hy[0];
                    } else {
                        hH = hx[1];
                        hI = hy[1];
                    }
                    break;
                case 1:
                    if (var0 < 7) {
                        hH = hx[0];
                        hI = hy[0];
                    } else {
                        hH = hx[1];
                        hI = hy[1];
                    }

                    if (var1 > 90 && var1 < 100 && var0 < 2) {
                        hs = hp < fe ? 1 : -1;
                        hr = 23;
                        hv = 0;
                    } else if ((var1 > 120 && var1 < 150 || ff < hq && var1 > 40) && var0 < 2) {
                        hs = hp < fe ? 1 : -1;
                        hv = 0;
                        hr = 8;
                    } else if (var1 < 40 && var0 == 1) {
                        hs = hp < fe ? 1 : -1;
                        hv = 0;
                        hr = 5;
                    } else if (var1 > 40 && var1 < 100 && var0 < 3) {
                        hs = hp < fe ? 1 : -1;
                        hv = 0;
                        if ((hw = fe) > hu) {
                            hw = hu;
                        } else if (hw < ht) {
                            hw = ht;
                        }

                        hr = 24;
                    } else if (var1 > 80 && var0 == 2) {
                        hv = 0;
                        hr = 2;
                        if ((hw = fe) > hu) {
                            hw = hu;
                        } else if (hw < ht) {
                            hw = ht;
                        }
                    }
                    break;
                case 2:
                    if ((var2 = ++hv % 6 >> 1) == 3) {
                        aL = 0;
                    }

                    hH = hB[var2];
                    hI = hC[var2];
                    if ((hp += hs << 2) > hw && hs > 0 || hp < hw && hs < 0 || hp > hu || hp < ht || hv > 100) {
                        hv = 0;
                        hr = 1;
                    }

                    if (var1 > 50 && var1 < 100 && var0 == 1) {
                        hs = hp < fe ? 1 : -1;
                        hr = 23;
                        hv = 0;
                    }
                    break;
                case 5:
                    if (++hv < 2) {
                        hH = hx[0];
                        hI = hy[0];
                    } else if (hv < 2) {
                        hH = hz[0];
                        hI = hA[0];
                    } else if (hv < 12) {
                        hH = hz[1];
                        hI = hA[1];
                    } else if (hv < 14) {
                        hH = hz[0];
                        hI = hA[0];
                    } else if (hv == 14) {
                        hv = 0;
                        hr = 1;
                    }

                    if (hv == 3 && var1 < 40 && var2 < 10) {
                        a(hj, hs);
                    }
                    break;
                case 6:
                    ++hv;

                    for(var0 = 0; var0 < hG.length; ++var0) {
                        int[] var10000 = hG;
                        var10000[var0] += a(var0 - 3) + 2;
                        var10000 = hF;
                        var10000[var0] += var0 - 3 << 1;
                    }

                    if (hv >= 50) {
                        hr = 22;
                    }
                    break;
                case 8:
                    if (++hv < 2) {
                        hH = hx[0];
                        hI = hy[0];
                    } else if (hv < 2) {
                        hH = hz[0];
                        hI = hA[0];
                    } else if (hv < 12) {
                        hH = hz[1];
                        hI = hA[1];
                    } else if (hv < 14) {
                        hH = hz[0];
                        hI = hA[0];
                    } else if (hv == 14) {
                        hv = 0;
                        hr = 1;
                    }

                    if (hv % 5 == 0) {
                        a(hp + (hs << 3), hq - 20, hs << 3, -var0 + 2, false);
                    }
                    break;
                case 23:
                    var0 = ++hv % 4 >> 1;
                    hH = hz[var0];
                    hI = hA[var0];
                    if (hv > 20 && hv % 10 == 0) {
                        b(hp + (hs << 3), hq - 24, hs << 3, hj, 3);
                    }

                    if (hv == 100) {
                        hs = hp < fe ? 1 : -1;
                        hv = 0;
                        if ((hw = fe) > hu) {
                            hw = hu;
                        } else if (hw < ht) {
                            hw = ht;
                        }

                        hr = 24;
                    }
                    break;
                case 24:
                    var0 = ++hv % 6 >> 1;
                    hH = hB[var0];
                    hI = hC[var0];
                    if ((hp += hs << 3) > hw && hs > 0 || hp < hw && hs < 0 || hp > hu || hp < ht || hv > 100) {
                        hv = 0;
                        hr = 5;
                    }
            }

            for(var2 = 0; var2 < 6; ++var2) {
                if (hs == 1) {
                    hD[var2] = hp + hF[var2] + hH[var2];
                } else {
                    hD[var2] = hp - hF[var2] - hH[var2];
                }

                hE[var2] = hq + hG[var2] + hI[var2];
            }

        }
    }

    private static void ad() {
        if (ie) {
            switch (au[id][ifSt]) {
                case 1:
                    if (fe > aw[id][ifSt]) {
                    C(av[id][ifSt]);
                    ++ifSt;
                    return;
                }
                break;
                case 2:
                    C(av[id][ifSt]);
                    ++ifSt;
                    return;
                case 3:
                default:
                    return;
                case 4:
                    if (fe > fO + r) {
                        C(av[id][ifSt]);
                        ++ifSt;
                        return;
                    }
                    break;
                case 6:
                    if (fe < aw[id][ifSt] && fe > aw[id][ifSt] - 96 && ff == ax[id][ifSt]) {
                    C(av[id][ifSt]);
                    ++ifSt;
                }
                case 5:
                    if (gS == 22) {
                        C(av[id][ifSt]);
                        ++ifSt;
                        return;
                    }
                    break;
                case 7:
                    if (hX == 5) {
                        C(av[id][ifSt]);
                        ++ifSt;
                        return;
                    }
                    break;
                case 8:
                    if (hg < 10) {
                        C(av[id][ifSt]);
                        ++ifSt;
                        return;
                    }
                    break;
                case 9:
                    if (hr == 22) {
                        C(av[id][ifSt]);
                        eH = true;
                        ++ifSt;
                        return;
                    }
            }
        } else if (id == 10) {
            Display.getDisplay(Midlet.instance).setCurrent(Midlet.b);
            id = 0;
        }

    }

    public static void s() {
        Midlet.b = new b();
        ad();
        b var0 = Midlet.b;
        Save.setBackgroundIndex((Save.backgroundIndex = Save.getBackgroundIndex()) == 0 ? 1 : 0);
        ai();
        e = 4;
        (new Thread(var0)).start();
    }

    public b() {
        this.setFullScreenMode(true);
        System.gc();
        aM = this;
        Input.motorolaKeys = this.getKeyCode(Canvas.FIRE) == Input.MOTOROLA_CODE_FIRE;
        aT = this.getWidth();
        aU = this.getHeight();
        aV = aT / 2;
        aW = aU / 2;
        Input.clearHeld();
    }

    protected final void keyPressed(int var1) {
        if (var1 == Input.CODE_POUND) {
            if (bv.equals("1984")) {
                eI = true;
                eJ = true;
                eZ = true;
                eL = true;
                eO = true;
                eM = true;
                eK = true;
                eP = true;
                eQ = true;
                eV = true;
                b("Learned All Skill");
            } else if (bv.equals("30538")) {
                PlayerStats.setMaxHpMp(500000, 500000);
                b("Unlimit HP MP");
            } else if (bv.equals("38305")) {
                PlayerStats.attack = 10000;
                b("Unlimit Damage");
            } else if (bv.equals("1982")) {
                gainExp(100000);
            } else if (bv.equals("1983")) {
                dd += 50000;
                b("Money+=50000");
            } else {
                int var2;
                if (!bv.equals("1981")) {
                    if (bv.equals("8463")) {
                        var2 = gL + (int)((System.currentTimeMillis() - gM) / 1000L);
                        b(var2 / 3600 + ":" + var2 % 3600 / 60 + ":" + var2 % 60);
                    }
                } else {
                    for(var2 = 0; var2 < 19; ++var2) {
                        X[var2] = true;
                    }

                    b("Unlock all map!");
                }
            }

            d = true;
            bv = "";
        }

        if (var1 >= Input.CODE_NUM0 && var1 <= Input.CODE_NUM9 && (bv = bv + (char)var1).length() > 10) {
            bv = "";
        }

        var1 = Input.normalizeKeyCode(var1);
        if (!fS || var1 == Input.CODE_FIRE) {
            Input.keyDown(var1);
        }
    }

    protected final void keyReleased(int var1) {
        var1 = Input.normalizeKeyCode(var1);
        if (!fS || var1 == Input.CODE_FIRE) {
            Input.keyUp(var1);
        }
    }

    protected final void paint(Graphics var1) {
        try {
            switch (e) {
                case 1:
                    Graphics var6 = var1;
                    var1.setClip(-1, -1, r + 2, s + 2);
                    a(var1);
                    var1.translate(-fO, -fP);
                    var1 = var1;

                    int var2;
                    for(var2 = 0; var2 < iX; ++var2) {
                        if (iZ[var2] + jb[var2] >= fO && iZ[var2] - jb[var2] <= fO + r && ja[var2] >= fP && ja[var2] - jc[var2] <= fP + s) {
                            jg = (jf = iZ[var2] - fO) - bC;
                            jh = (ji = (GameMap.TILE_SIZE - 2) * jg / GameMap.TILE_SIZE) + bC;
                            var1.drawImage(houseImages[iY[var2]], fO + jh, ja[var2], 33);
                        }
                    }

                    if (a) {
                        j(var6);
                    } else {
                        k(var6);
                    }

                    i(var6);
                    r(var6);
                    if (hJ) {
                        if (hY < 0) {
                            var6.drawImage(boss1Sprites[0], hP, hQ, 33);
                            var6.drawImage(boss1Sprites[2], hR, hV, 33);
                            var6.drawImage(boss1Sprites[3], hU, hW, 33);
                            var6.drawImage(boss1Sprites[1], hS, hT, 33);
                        } else {
                            var6.drawRegion(boss1Sprites[0], 0, 0, boss1Sprites[0].getWidth(), boss1Sprites[0].getHeight(), 2, hP, hQ, 33);
                            var6.drawRegion(boss1Sprites[2], 0, 0, boss1Sprites[2].getWidth(), boss1Sprites[2].getHeight(), 2, hR, hV, 33);
                            var6.drawRegion(boss1Sprites[3], 0, 0, boss1Sprites[3].getWidth(), boss1Sprites[3].getHeight(), 2, hU, hW, 33);
                            var6.drawRegion(boss1Sprites[1], 0, 0, boss1Sprites[1].getWidth(), boss1Sprites[1].getHeight(), 2, hS, hT, 33);
                        }

                        if (ic != 0) {
                            var6.setColor(3342353);
                            var6.drawRect(hP - (hO >> 1) - 1, hQ - hL - 7, hO + 2, 4);
                            var2 = hM * hO / hN;
                            var6.setColor(15597568);
                            var6.fillRect(hP - (hO >> 1), hQ - hL - 6, var2, 3);
                            var6.setColor(4456482);
                            var6.fillRect(hP - (hO >> 1) + var2, hQ - hL - 6, hO - var2 + 1, 3);
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < iq; ++var2) {
                        if (fO <= is[var2] + 12 && fO + r >= is[var2] - 12 && fP <= it[var2] && fP + s >= it[var2] - 24) {
                            var1.drawImage(itemCrate, is[var2], it[var2], 33);
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < ii; ++var2) {
                        if (fO <= ik[var2] + 12 && fO + r >= ik[var2] - 12 && fP <= il[var2] && fP + s >= il[var2] - 24) {
                            if (im[var2] == 1) {
                                var1.drawImage(motorbikeFrames[in[var2]], ik[var2], il[var2], 33);
                            } else {
                                var1.drawRegion(motorbikeFrames[in[var2]], 0, 0, motorbikeFrames[in[var2]].getWidth(), motorbikeFrames[in[var2]].getHeight(), 2, ik[var2], il[var2], 33);
                            }
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < de; ++var2) {
                        if (dh[var2] != 0 && df[var2] >= fO - 10 && df[var2] <= fO + r + 10 && dg[var2] >= fP && dg[var2] <= fP + s + 20) {
                            var1.drawRegion(itemMoney[dm[var2]], 0, dk[var2] * Item.FRAME_HEIGHT, Item.MONEY_WIDTH, Item.FRAME_HEIGHT, 0, df[var2], dg[var2], 33);
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < cM; ++var2) {
                        if (cQ[var2] != 0 && cO[var2] >= fO - 10 && cO[var2] <= fO + r + 10 && cP[var2] >= fP && cP[var2] <= fP + s + 20) {
                            var1.drawRegion(itemPotions[cN[var2]], 0, cT[var2] * Item.FRAME_HEIGHT, Item.POTION_WIDTH, Item.FRAME_HEIGHT, 0, cO[var2], cP[var2], 33);
                        }
                    }

                    int var3;
                    if (gN && gS != 22 && fO + r >= gP - (gV >> 1) && fO <= gP + (gV >> 1) && fP <= gQ && fP + s >= gQ - gW) {
                        byte var8 = 0;
                        if (gU == 0) {
                            var8 = -2;
                            if (gS == 1 && x % 10 > 5) {
                                var8 = -1;
                            }

                            if (gT == 1) {
                                var6.drawImage(boss0Sprites[4], gP, gQ + 1, 33);
                            } else {
                                var6.drawRegion(boss0Sprites[4], 0, 0, boss0Sprites[4].getWidth(), boss0Sprites[4].getHeight(), 2, gP, gQ + 1, 33);
                            }
                        }

                        if (gT == 1) {
                            var6.drawImage(boss0Sprites[gU], gP, gQ + var8 + 1, 33);
                        } else {
                            var6.drawRegion(boss0Sprites[gU], 0, 0, boss0Sprites[gU].getWidth(), boss0Sprites[gU].getHeight(), 2, gP, gQ + var8 + 1, 33);
                        }

                        if (gS == 7) {
                            if (gT == 1) {
                                var6.drawImage(boss0Sprites[3], gP + 10, gQ - 25 + 1, 0);
                            } else {
                                var6.drawRegion(boss0Sprites[3], 0, 0, boss0Sprites[3].getWidth(), boss0Sprites[3].getHeight(), 2, gP - 10, gQ - 25 + 1, 24);
                            }
                        }

                        if (hb != 0) {
                            var6.setColor(3342353);
                            var6.drawRect(gP - (gV >> 1) - 1, gQ - gW - 7, gV + 2, 4);
                            var3 = gZ * gV / ha;
                            var6.setColor(15597568);
                            var6.fillRect(gP - (gV >> 1), gQ - gW - 6, var3, 3);
                            var6.setColor(4456482);
                            var6.fillRect(gP - (gV >> 1) + var3, gQ - gW - 6, gV - var3 + 1, 3);
                        }
                    }

                    var1 = var6;
                    if (hi) {
                        if (hs > 0) {
                            for(var2 = 0; var2 < 6; ++var2) {
                                var1.drawImage(boss2Sprites[var2], hD[var2], hE[var2], 33);
                            }
                        } else {
                            for(var2 = 0; var2 < 6; ++var2) {
                                var1.drawRegion(boss2Sprites[var2], 0, 0, boss2Sprites[var2].getWidth(), boss2Sprites[var2].getHeight(), 2, hD[var2], hE[var2], 33);
                            }
                        }

                        if (ho != 0) {
                            var1.setColor(3342353);
                            var1.drawRect(hp - (hk >> 1) - 1, hq - hl - 7, hk + 2, 4);
                            var2 = hm * hk / hn;
                            var1.setColor(15597568);
                            var1.fillRect(hp - (hk >> 1), hq - hl - 6, var2, 3);
                            var1.setColor(4456482);
                            var1.fillRect(hp - (hk >> 1) + var2, hq - hl - 6, hk - var2 + 1, 3);
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < jG; ++var2) {
                        if (jO[var2] != 5 && jO[var2] != 8 && jJ[var2] >= fO - 10 && jJ[var2] <= fO + r + 10 && jK[var2] >= fP && jK[var2] <= fP + s + 20) {
                            var1.drawImage(monsterSprites[kg[var2]][jX[var2] >= 0 ? Mob.MONSTER_DIR_RIGHT : Mob.MONSTER_DIR_LEFT][jW[var2]], jJ[var2], jK[var2], 33);
                            if (jN[var2] != 0) {
                                var1.setColor(13073);
                                var1.drawRect(jJ[var2] - (jM[var2] >> 1) - 1, jK[var2] - jL[var2] - 7, jM[var2] + 2, 4);
                                var3 = jY[var2] * jM[var2] / jZ[var2];
                                var1.setColor(60928);
                                var1.fillRect(jJ[var2] - (jM[var2] >> 1), jK[var2] - jL[var2] - 6, var3, 3);
                                var1.setColor(17442);
                                var1.fillRect(jJ[var2] - (jM[var2] >> 1) + var3, jK[var2] - jL[var2] - 6, jM[var2] - var3 + 1, 3);
                            }
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < cp; ++var2) {
                        if (cE[var2] != 5 && cu[var2] + 15 >= fO && cu[var2] - 15 <= fO + r && cv[var2] >= fP && cv[var2] - 30 <= fP + s) {
                            if (cH[var2] == -1) {
                                var1.drawImage(ninjaLegs[cI[var2]], cu[var2] + ah[cD[var2]], cv[var2], 33);
                                var1.drawImage(ninjaBodies[cD[var2]][cJ[var2]], cw[var2] + af[cD[var2]][cJ[var2]], cx[var2], 33);
                            } else {
                                var1.drawRegion(ninjaLegs[cI[var2]], 0, 0, ninjaLegs[cI[var2]].getWidth(), ninjaLegs[cI[var2]].getHeight(), 2, cu[var2] - ah[cD[var2]], cv[var2], 33);
                                var1.drawRegion(ninjaBodies[cD[var2]][cJ[var2]], 0, 0, ninjaBodies[cD[var2]][cJ[var2]].getWidth(), ninjaBodies[cD[var2]][cJ[var2]].getHeight(), 2, cw[var2] - af[cD[var2]][cJ[var2]], cx[var2], 33);
                            }

                            if (cq[var2] != 0) {
                                var1.setColor(13073);
                                var1.drawRect(cu[var2] - 13, cv[var2] - 40, 26, 4);
                                var3 = cr[var2] * 24 / ae[cD[var2]];
                                var1.setColor(15597568);
                                var1.fillRect(cu[var2] - 12, cv[var2] - 39, var3, 3);
                                var1.setColor(4456482);
                                var1.fillRect(cu[var2] - 12 + var3, cv[var2] - 39, 24 - var3 + 1, 3);
                            }
                        }
                    }

                    g(var6);
                    if (dn && doSt + 15 >= fO && doSt - 15 <= fO + r && dp >= fP && dp - 10 <= fP + s) {
                    var2 = x % 8 > 3 ? 0 : 1;
                    if (dq > 0) {
                        var6.drawImage(npcDog[var2], doSt, dp, 33);
                    } else {
                        var6.drawRegion(npcDog[var2], 0, 0, npcDog[var2].getWidth(), npcDog[var2].getHeight(), 2, doSt, dp, 33);
                    }
                }

                if (hc) {
                    if (hd) {
                        var6.setColor(16768477);
                        var2 = fO + 50;
                        var3 = fP + 15;
                        if (!be) {
                            var3 += 20;
                        }

                        var6.drawRect(var2 - 35 - 1, var3 - 7, 72, 4);
                        int var7 = hg * 70 / hh;
                        var6.setColor(15597568);
                        var6.fillRect(var2 - 35, var3 - 6, var7, 3);
                        var6.setColor(4456482);
                        var6.fillRect(var2 - 35 + var7, var3 - 6, 70 - var7 + 1, 3);
                    }

                    if (dz[0] + fi >= fO && dz[0] - fi <= fO + r && dA[0] >= fP && dA[0] - fh <= fP + s) {
                        if (dJ[0] == 1) {
                            var6.drawImage(classmateHeads[dH[0]][aI[dG[0]][0][0]], dz[0] + aI[dG[0]][0][1], dA[0] - aI[dG[0]][0][2], 0);
                            var6.drawImage(heroLegs[aI[dG[0]][1][0]], dz[0] + aI[dG[0]][1][1], dA[0] - aI[dG[0]][1][2], 0);
                            var6.drawImage(heroBodies[aI[dG[0]][2][0]], dz[0] + aI[dG[0]][2][1], dA[0] - aI[dG[0]][2][2], 0);
                            if (dI[0] != -1) {
                                var6.drawImage(slashEffects[dI[0]], dz[0] - as[dI[0]], dA[0] - at[dI[0]], 0);
                            }
                        } else {
                            var6.drawRegion(classmateHeads[dH[0]][aI[dG[0]][0][0]], 0, 0, classmateHeads[dH[0]][aI[dG[0]][0][0]].getWidth(), classmateHeads[dH[0]][aI[dG[0]][0][0]].getHeight(), 2, dz[0] - aI[dG[0]][0][1], dA[0] - aI[dG[0]][0][2], 24);
                            var6.drawRegion(heroLegs[aI[dG[0]][1][0]], 0, 0, heroLegs[aI[dG[0]][1][0]].getWidth(), heroLegs[aI[dG[0]][1][0]].getHeight(), 2, dz[0] - aI[dG[0]][1][1], dA[0] - aI[dG[0]][1][2], 24);
                            var6.drawRegion(heroBodies[aI[dG[0]][2][0]], 0, 0, heroBodies[aI[dG[0]][2][0]].getWidth(), heroBodies[aI[dG[0]][2][0]].getHeight(), 2, dz[0] - aI[dG[0]][2][1], dA[0] - aI[dG[0]][2][2], 24);
                            if (dI[0] != -1) {
                                var6.drawRegion(slashEffects[dI[0]], 0, 0, slashEffects[dI[0]].getWidth(), slashEffects[dI[0]].getHeight(), 2, dz[0] + as[dI[0]], dA[0] - at[dI[0]], 24);
                            }
                        }
                    }
                }

                if (dX != 0 && (var2 = dX % 3) < 2) {
                    var6.drawImage(auraSprites[var2], dV, dW, 17);
                }

                if (gm == w) {
                    if (gz == 1) {
                        var6.drawImage(girlHeads[aq[gs][0][0]], gn + aq[gs][0][1], go - aq[gs][0][2], 0);
                        var6.drawImage(girlLegs[aq[gs][1][0]], gn + aq[gs][1][1], go - aq[gs][1][2], 0);
                        var6.drawImage(girlBodies[aq[gs][2][0]], gn + aq[gs][2][1], go - aq[gs][2][2], 0);
                        if (ig != -1) {
                            var6.drawImage(slashEffects[ig], gn - as[ig], go - at[ig], 0);
                        }
                    } else {
                        var6.drawRegion(girlHeads[aq[gs][0][0]], 0, 0, girlHeads[aq[gs][0][0]].getWidth(), girlHeads[aq[gs][0][0]].getHeight(), 2, gn - aq[gs][0][1], go - aq[gs][0][2], 24);
                        var6.drawRegion(girlLegs[aq[gs][1][0]], 0, 0, girlLegs[aq[gs][1][0]].getWidth(), girlLegs[aq[gs][1][0]].getHeight(), 2, gn - aq[gs][1][1], go - aq[gs][1][2], 24);
                        var6.drawRegion(girlBodies[aq[gs][2][0]], 0, 0, girlBodies[aq[gs][2][0]].getWidth(), girlBodies[aq[gs][2][0]].getHeight(), 2, gn - aq[gs][2][1], go - aq[gs][2][2], 24);
                        if (ig != -1) {
                            var6.drawRegion(slashEffects[ig], 0, 0, slashEffects[ig].getWidth(), slashEffects[ig].getHeight(), 2, gn + as[ig], go - at[ig], 24);
                        }
                    }
                }

                b(var6);
                if (fH != 0) {
                    var6.drawImage(itemScroll, fF, fG, 33);
                }

                var1 = var6;

                for(var2 = 0; var2 < 2; ++var2) {
                    if (cc[var2] != -1) {
                        if (ce[var2] == 1) {
                            var1.drawImage(bloodSprites[cd[var2]], ca[var2], cb[var2], 3);
                        } else {
                            var1.drawRegion(bloodSprites[cd[var2]], 0, 0, bloodSprites[cd[var2]].getWidth(), bloodSprites[cd[var2]].getHeight(), 2, ca[var2], cb[var2], 3);
                        }
                    }
                }

                var1 = var6;
                if (!a) {
                    for(var2 = 0; var2 < 2; ++var2) {
                        if (cl[var2] != -1) {
                            var1.drawImage(dustSprites[var2][cl[var2]], cj[var2], ck[var2], 3);
                        }
                    }
                }

                var1 = var6;

                for(var2 = 0; var2 < bM; ++var2) {
                    if (bP[var2] != -1) {
                        var1.drawImage(enemyProjectiles[bS[var2]][bQ[var2] > 0 ? Effect.PROJECTILE_DIR_RIGHT : Effect.PROJECTILE_DIR_LEFT], bN[var2], bO[var2], 3);
                    }
                }

                var1 = var6;

                for(var2 = 0; var2 < cV; ++var2) {
                    if (cY[var2] != -1) {
                        var1.drawImage(dartFrames[cZ[var2]], cW[var2], cX[var2], 3);
                    }
                }

                var1 = var6;

                for(var2 = 0; var2 < bT; ++var2) {
                    if (bW[var2] != 0) {
                        var1.drawImage(healSprite, bU[var2], bV[var2], 3);
                    }
                }

                var1 = var6;
                if (!a) {
                    for(var2 = 0; var2 < 2; ++var2) {
                        if (ch[var2] != -1) {
                            var1.drawImage(waterSplashSprites[ci[var2]], cf[var2], cg[var2], 3);
                        }
                    }
                }

                l(var6);
                var1 = var6;

                for(var2 = 0; var2 < 5; ++var2) {
                    if (dT[var2] != -1) {
                        FontGraph.drawSmall(var1, dO[var2], dU[var2], dP[var2], dQ[var2], 0);
                    }
                }

                if (gb == w) {
                    var6.drawImage(arrowIcons[ge], gf, gg, 3);
                }

                c(var6);
                if (d) {
                    d(var6);
                }

                if (bu > 1) {
                    e(var6);
                }

                if (cm) {
                    var6.translate(-var6.getTranslateX(), -var6.getTranslateY());
                    var6.setClip(-1, -1, aT + 2, aU + 2);
                    var6.setColor(0);
                    var6.fillRect(0, 0, aT, 21);
                    var6.setColor(10275899);
                    var6.fillRect(0, 19, aT, 1);
                    if (x % 10 > 2) {
                        FontGraph.drawBold(var6, co, bC, 4, 2);
                    }
                }

                if (iz) {
                    f(var6);
                }

                return;
                case 2:
                    var1.setColor(0);
                    var1.fillRect(0, 0, aT, 21);
                    FontGraph.drawBold(var1, "Loading... " + T + "%", aT >> 1, 4, 2);
                    return;
                case 3:
                    var1.setClip(-1, -1, aT + 2, aU + 1);
                    a(var1);
                    var1.translate(-fO, -fP);
                    if (a) {
                        j(var1);
                    } else {
                        k(var1);
                    }

                    i(var1);
                    l(var1);
                    var1.translate(-var1.getTranslateX(), -var1.getTranslateY());
                    var1.setColor(0);
                    var1.fillRect(0, 0, r, ba);
                    var1.fillRect(0, bb, r, aU - bb + 1);
                    var1.setColor(10275899);
                    var1.fillRect(0, ba - 1, aT, 1);
                    var1.fillRect(0, bb + 1, aT, 1);
                    var1.drawImage(titleImage, aV, aY + bb >> 1, 3);
                    FontGraph.drawBold(var1, "*", aV - 50 - x % 3, bd, 0);
                    FontGraph.drawBold(var1, "/", aV + 50 + x % 3, bd, 1);
                    FontGraph.drawBold(var1, aE[aX], aV, bd, 3);
                    return;
                case 4:
                    var1.setColor(0);
                    var1.fillRect(0, 0, aT, aU);
                    if (logoImage != null && jE >= 0) {
                        var1.drawRegion(logoImage, 0, jE * Ui.LOGO_FRAME_HEIGHT, 71, Ui.LOGO_FRAME_HEIGHT, 0, aT >> 1, aU >> 1, 3);
                    }

                    return;
                case 5:
                    o(var1);
                    return;
                case 6:
                    p(var1);
                    return;
                case 7:
                    q(var1);
                    return;
                case 8:
                    n(var1);
                    return;
                case 9:
                    m(var1);
                    return;
                case 10:
                    var1.setColor(0);
                    var1.fillRect(0, 0, aT, aU);
                    if (!lk) {
                        var1.drawImage(popupImage, aV, aW - 30, 3);
                        if (ll == 0) {
                            FontGraph.drawBold(var1, "Bật âm thanh?", aV, aW + 15, 2);
                            FontGraph.drawBold(var1, "Bật", 4, aU - 14, 0);
                            FontGraph.drawBold(var1, "Tắt", aT - 4, aU - 14, 1);
                            break;
                        }

                        FontGraph.drawBold(var1, "Chất lượng hình ảnh?", aV, aW + 15, 2);
                        FontGraph.drawBold(var1, "Cao", 4, aU - 14, 0);
                        FontGraph.drawBold(var1, "Thấp", aT - 4, aU - 14, 1);
                    }

                    return;
                case 11:
                    h(var1);
                    return;
                case 12:
                    h(var1);
                    return;
                default:
                    return;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    protected final void pointerDragged(int var1, int var2) {
        Input.pointerX = var1;
        Input.pointerY = var2;
    }

    protected final void pointerPressed(int var1, int var2) {
        Input.pointerX = var1;
        Input.pointerY = var2;
    }

    protected final void pointerReleased(int var1, int var2) {
        Input.pointerReleased = true;
        Input.pointerX = var1;
        Input.pointerY = var2;
    }

    public final void run() {
        while(b) {
            long var1 = System.currentTimeMillis();
            boolean var3;
            int var10002;
            switch (e) {
                case 1:
                    if (++x == 10000) {
                        x = 0;
                    }

                    if (fe < 0) {
                        if (jo != -1) {
                            B();
                            fp = fe = js - 1;
                            fq = ff = jt;
                            Save.pendingSave = true;
                            x(jo);
                            break;
                        }
                    } else if (fe >= GameMap.pixelWidth - 24) {
                        if (jp != -1) {
                            if (!eZ && jp == 95) {
                                s(6);
                                a("Khu vực phía trước rất nguy hiểm! Chỉ những người có thể ẩn thân có thể vào!");
                                fe = GameMap.pixelWidth - 60;
                                B = 4;
                                e = 7;
                                break;
                            }

                            B();
                            if (jq != -1 && ff > jA) {
                                fp = fe = jw;
                                fq = ff = jx;
                                Save.pendingSave = true;
                                x(jq);
                                break;
                            }

                            fp = fe = ju;
                            fq = ff = jv;
                            Save.pendingSave = true;
                            x(jp);
                            break;
                        }
                    } else if (ff + A >= GameMap.pixelHeight && B != 6 && jr != -1) {
                        B();
                        fp = fe = jy;
                        fq = ff = jz;
                        Save.pendingSave = true;
                        x(jr);
                        B = 4;
                        break;
                    }

                    ad();
                    X();
                    if (V()) {
                        break;
                    }

                    int var13;
                    if (!a) {
                        for(var13 = 0; var13 < 2; ++var13) {
                            if (x % (var13 + 2 << 3) == 0) {
                                var10002 = f[var13]++;
                                if (f[var13] > r + (u.getWidth() >> 1)) {
                                    f[var13] = -(u.getWidth() >> 1);
                                }
                            }
                        }
                    }

                    int var4;
                    if (iz) {
                        if (Input.pressed[Input.KEY_FIRE]) {
                            Input.clearPressed();
                            M();
                            if (iC == iB) {
                                iz = false;
                                z();
                            }
                        }

                        d = true;
                    } else {
                        label2614: {
                            if (Input.pressed[Input.KEY_SOFT_LEFT]) {
                                ap();
                                e = 5;
                                Input.clearHeld();
                                Input.clearPressed();
                            } else if (Input.pressed[Input.KEY_NUM0]) {
                                if (fd == 0) {
                                    if (eK) {
                                        fd = 1;
                                    }
                                } else {
                                    fd = 0;
                                }

                                d = true;
                                Input.pressed[Input.KEY_NUM0] = false;
                            } else if (Input.pressed[Input.KEY_RIGHT]) {
                                fo = 2;
                                Input.pressed[Input.KEY_RIGHT] = false;
                            } else if (Input.pressed[Input.KEY_LEFT]) {
                                fo = -2;
                                Input.pressed[Input.KEY_LEFT] = false;
                            } else {
                                if (Input.pressed[Input.KEY_NUM9]) {
                                    if (eZ) {
                                        fw = !fw;
                                    }

                                    Input.pressed[Input.KEY_NUM9] = false;
                                    break label2614;
                                }

                                if (Input.pressed[Input.KEY_NUM7]) {
                                    if (B == 1 && eQ) {
                                        B = 23;
                                        D = 0;
                                    }

                                    Input.pressed[Input.KEY_NUM7] = false;
                                } else if (Input.pressed[Input.KEY_STAR]) {
                                    Input.pressed[Input.KEY_STAR] = false;
                                    if (!PlayerStats.isHpFull()) {
                                        for(var13 = 0; var13 < 3; ++var13) {
                                            if (W[var13] > 0) {
                                                var10002 = W[var13]--;
                                                PlayerStats.restoreHp(kF[var13]);

                                                d = true;
                                                break;
                                            }
                                        }
                                    }
                                } else if (Input.pressed[Input.KEY_POUND]) {
                                    Input.pressed[Input.KEY_POUND] = false;
                                    if (!PlayerStats.isMpFull()) {
                                        for(var13 = 3; var13 < 6; ++var13) {
                                            if (W[var13] > 0) {
                                                var10002 = W[var13]--;
                                                PlayerStats.restoreMp(kF[var13]);

                                                d = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }

                            if (B == 1) {
                                if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
                                    Input.clearPressed();
                                    if (p.equals("Giao tiếp")) {
                                        var4 = fD;
                                        switch (lw[var4]) {
                                            case 0:
                                                if (fb) {
                                                    a(lB[7], -1, lu[var4], lv[var4] - 40);
                                                } else if (!eI) {
                                                    V(2);
                                                    a(ly[0], -1, lu[var4], lv[var4] - 40);
                                                } else {
                                                    if (eZ && !fa) {
                                                        if (P == 16) {
                                                            if (eH) {
                                                                a(lz[14], 35, lu[var4], lv[var4] - 40);
                                                            } else {
                                                                a(lA[14], -1, lu[var4], lv[var4] - 40);
                                                            }
                                                        } else if (P == -1) {
                                                            a(ly[22], 36, lu[var4], lv[var4] - 40);
                                                        } else {
                                                            a(lC, 1, lu[var4], lv[var4] - 40);
                                                        }
                                                        break;
                                                    }

                                                    a(lB[1], -1, lu[var4], lv[var4] - 40);
                                                }
                                                break;
                                            case 1:
                                                if (fb) {
                                                    a(lB[5], -1, lu[var4], lv[var4] - 40);
                                                } else if (!eI) {
                                                    if (P == 0) {
                                                        if (fC < lE[0]) {
                                                            a(lA[0], -1, lu[var4], lv[var4] - 40);
                                                        } else {
                                                            var3 = true;
                                                            boolean var8 = true;
                                                            boolean var7 = true;
                                                            boolean var6 = false;
                                                            lG = 0;
                                                            lJ = 4;
                                                            lH = 1044;
                                                            lI = 240;
                                                            a(lz[0], 4, lu[var4], lv[var4] - 40);
                                                        }
                                                    } else if (P == -1) {
                                                        a(ly[1], 3, lu[var4], lv[var4] - 40);
                                                    } else {
                                                        a(lC, 1, lu[var4], lv[var4] - 40);
                                                    }
                                                } else if (!eR && eQ) {
                                                    if (PlayerStats.level < 10) {
                                                        a(new String[]{"Hãy quay lại gặp ta khi con đạt cấp độ 10"}, -1, lu[var4], lv[var4] - 40);
                                                    } else if (P == 7) {
                                                        if (fC < lE[7]) {
                                                            a(lA[6], -1, lu[var4], lv[var4] - 40);
                                                        } else {
                                                            V(2);
                                                            a(lz[6], 19, lu[var4], lv[var4] - 40);
                                                        }
                                                    } else if (P == -1) {
                                                        a(ly[14], 18, lu[var4], lv[var4] - 40);
                                                    } else {
                                                        a(lC, 1, lu[var4], lv[var4] - 40);
                                                    }
                                                } else if (eU && !eV) {
                                                    if (PlayerStats.level < 12) {
                                                        a(new String[]{"Hãy quay lại gặp ta khi con đạt cấp độ 12"}, -1, lu[var4], lv[var4] - 40);
                                                    } else if (P == 11) {
                                                        if (fC < 20) {
                                                            a(lA[9], -1, lu[var4], lv[var4] - 40);
                                                        } else {
                                                            V(4);
                                                            a(lz[9], 27, lu[var4], lv[var4] - 40);
                                                        }
                                                    } else if (P == -1) {
                                                        a(ly[17], 28, lu[var4], lv[var4] - 40);
                                                    } else {
                                                        a(lC, 1, lu[var4], lv[var4] - 40);
                                                    }
                                                } else {
                                                    if (!eX && eW) {
                                                        if (PlayerStats.level < 15) {
                                                            a(new String[]{"Hãy quay lại gặp ta khi con đạt cấp độ 15"}, -1, lu[var4], lv[var4] - 40);
                                                        } else if (P == 13) {
                                                            if (fC < lE[13]) {
                                                                a(lA[11], -1, lu[var4], lv[var4] - 40);
                                                            } else {
                                                                V(3);
                                                                a(lz[11], 31, lu[var4], lv[var4] - 40);
                                                            }
                                                        } else if (P == -1) {
                                                            a(ly[19], 32, lu[var4], lv[var4] - 40);
                                                        } else {
                                                            a(lC, 1, lu[var4], lv[var4] - 40);
                                                        }
                                                        break;
                                                    }

                                                    a(lB[0], -1, lu[var4], lv[var4] - 40);
                                                }
                                                break;
                                            case 2:
                                                if (fb) {
                                                    a(lB[9], -1, lu[var4], lv[var4] - 40);
                                                } else if (!eI) {
                                                    V(1);
                                                    a(ly[2], -1, lu[var4], lv[var4] - 40);
                                                } else if (eL && !eM) {
                                                    if (P == 3) {
                                                        a(ly[9], -1, lu[var4], lv[var4] - 40);
                                                    } else if (P == -1) {
                                                        a(ly[10], 11, lu[var4], lv[var4] - 40);
                                                    } else {
                                                        a(lC, 1, lu[var4], lv[var4] - 40);
                                                    }
                                                } else if (eR && !eS) {
                                                    if (P == 8) {
                                                        if (eG) {
                                                            V(2);
                                                            a(lz[7], 23, lu[var4], lv[var4] - 40);
                                                        } else {
                                                            a(lA[7], -1, lu[var4], lv[var4] - 40);
                                                        }
                                                    } else if (P == -1) {
                                                        a(ly[15], 22, lu[var4], lv[var4] - 40);
                                                    } else {
                                                        a(lC, 1, lu[var4], lv[var4] - 40);
                                                    }
                                                } else {
                                                    if (eS && !eT) {
                                                        if (P == 9) {
                                                            if (fC < lE[9]) {
                                                                a(lA[8], -1, lu[var4], lv[var4] - 40);
                                                            } else {
                                                                V(3);
                                                                a(lz[8], 25, lu[var4], lv[var4] - 40);
                                                            }
                                                        } else if (P == -1) {
                                                            a(ly[16], 24, lu[var4], lv[var4] - 40);
                                                        } else {
                                                            a(lC, 1, lu[var4], lv[var4] - 40);
                                                        }
                                                        break;
                                                    }

                                                    a(lB[3], -1, lu[var4], lv[var4] - 40);
                                                }
                                                break;
                                            case 3:
                                                if (fb) {
                                                    a(lB[9], -1, lu[var4], lv[var4] - 40);
                                                } else {
                                                    if (eI && eK) {
                                                        if (!eL) {
                                                            if (P == 2) {
                                                                if (!fA) {
                                                                    a(lA[2], -1, lu[var4], lv[var4] - 40);
                                                                } else {
                                                                    V(2);
                                                                    a(lz[2], 10, lu[var4], lv[var4] - 40);
                                                                }
                                                            } else if (P == -1) {
                                                                a(ly[8], 9, lu[var4], lv[var4] - 40);
                                                            } else {
                                                                a(lC, 1, lu[var4], lv[var4] - 40);
                                                            }
                                                            break;
                                                        }

                                                        if (!eO && eN) {
                                                            if (P == 5) {
                                                                if (!fA) {
                                                                    a(lA[4], -1, lu[var4], lv[var4] - 40);
                                                                } else {
                                                                    V(4);
                                                                    a(lz[4], 15, lu[var4], lv[var4] - 40);
                                                                }
                                                            } else if (P == -1) {
                                                                a(ly[12], 14, lu[var4], lv[var4] - 40);
                                                            } else {
                                                                a(lC, 1, lu[var4], lv[var4] - 40);
                                                            }
                                                            break;
                                                        }

                                                        if (!eY && eX) {
                                                            if (PlayerStats.level < 16) {
                                                                a(new String[]{"Hãy quay lại gặp ta khi con đạt cấp độ 16"}, -1, lu[var4], lv[var4] - 40);
                                                            } else if (P == 14) {
                                                                if (fC < lE[14]) {
                                                                    a(lA[12], -1, lu[var4], lv[var4] - 40);
                                                                } else {
                                                                    V(3);
                                                                    a(lz[12], 33, lu[var4], lv[var4] - 40);
                                                                }
                                                            } else if (P == -1) {
                                                                a(ly[20], 34, lu[var4], lv[var4] - 40);
                                                            } else {
                                                                a(lC, 1, lu[var4], lv[var4] - 40);
                                                            }
                                                            break;
                                                        }

                                                        if (!eZ && eY) {
                                                            if (P == 15) {
                                                                if (!fA) {
                                                                    a(lA[13], -1, lu[var4], lv[var4] - 40);
                                                                } else {
                                                                    V(0);
                                                                    a(lz[13], 21, lu[var4], lv[var4] - 40);
                                                                }
                                                            } else if (P == -1) {
                                                                a(ly[21], 20, lu[var4], lv[var4] - 40);
                                                            } else {
                                                                a(lC, 1, lu[var4], lv[var4] - 40);
                                                            }
                                                            break;
                                                        }

                                                        a(lB[4], -1, lu[var4], lv[var4] - 40);
                                                        break;
                                                    }

                                                    a(ly[5], -1, lu[var4], lv[var4] - 40);
                                                }
                                                break;
                                            case 4:
                                                if (fb) {
                                                    a(lB[8], -1, lu[var4], lv[var4] - 40);
                                                } else if (w == 99) {
                                                    a(ly[7], -1, lu[var4], lv[var4] - 40);
                                                } else if (!eI) {
                                                    a(ly[4], -1, lu[var4], lv[var4] - 40);
                                                } else if (!eK) {
                                                    if (P == 1) {
                                                        if (fC < lE[1]) {
                                                            a(lA[3], -1, lu[var4], lv[var4] - 40);
                                                        } else {
                                                            V(3);
                                                            a(lz[1], 6, lu[var4], lv[var4] - 40);
                                                        }
                                                    } else if (P == -1) {
                                                        a(ly[6], 5, lu[var4], lv[var4] - 40);
                                                    } else {
                                                        a(lC, 1, lu[var4], lv[var4] - 40);
                                                    }
                                                } else if (eM && !eN) {
                                                    if (P == 4) {
                                                        if (fC < lE[4]) {
                                                            a(lA[3], -1, lu[var4], lv[var4] - 40);
                                                        } else {
                                                            V(3);
                                                            a(lz[3], 12, lu[var4], lv[var4] - 40);
                                                        }
                                                    } else if (P == -1) {
                                                        a(ly[11], 13, lu[var4], lv[var4] - 40);
                                                    } else {
                                                        a(lC, 1, lu[var4], lv[var4] - 40);
                                                    }
                                                } else if (eO && !eQ) {
                                                    if (PlayerStats.level < 8) {
                                                        a(new String[]{"Hãy quay lại gặp ta khi con đạt cấp độ 8"}, -1, lu[var4], lv[var4] - 40);
                                                    } else if (P == 6) {
                                                        if (!fA) {
                                                            a(lA[5], -1, lu[var4], lv[var4] - 40);
                                                        } else {
                                                            V(1);
                                                            a(lz[5], 17, lu[var4], lv[var4] - 40);
                                                        }
                                                    } else if (P == -1) {
                                                        a(ly[13], 16, lu[var4], lv[var4] - 40);
                                                    } else {
                                                        a(lC, 1, lu[var4], lv[var4] - 40);
                                                    }
                                                } else {
                                                    if (!eW && eV) {
                                                        if (P == 12) {
                                                            if (fC < lE[12]) {
                                                                a(lA[10], -1, lu[var4], lv[var4] - 40);
                                                            } else {
                                                                V(1);
                                                                a(lz[10], 29, lu[var4], lv[var4] - 40);
                                                            }
                                                        } else if (P == -1) {
                                                            a(ly[18], 30, lu[var4], lv[var4] - 40);
                                                        } else {
                                                            a(lC, 1, lu[var4], lv[var4] - 40);
                                                        }
                                                        break;
                                                    }

                                                    a(lB[2], -1, lu[var4], lv[var4] - 40);
                                                }
                                                break;
                                            case 5:
                                                if (w == 0) {
                                                    if (var4 == 5) {
                                                        a(lx[0], -1, lu[var4], lv[var4] - 40);
                                                    }

                                                    if (var4 == 6) {
                                                        an();
                                                        e = 9;
                                                        kr = 0;
                                                    }
                                                }

                                                if (w == 2 && var4 == 0) {
                                                    an();
                                                    e = 9;
                                                    kr = 1;
                                                }

                                                if (w == 1 && var4 == 0) {
                                                    a(lx[1], -1, lu[var4], lv[var4] - 40);
                                                }

                                                if (w == 5 && var4 == 0) {
                                                    a(lx[2], -1, lu[var4], lv[var4] - 40);
                                                }

                                                if (w == 6 && var4 == 0) {
                                                    a(lx[3], -1, lu[var4], lv[var4] - 40);
                                                }
                                        }

                                        if (w == gb && var4 == gj) {
                                            gb = -1;
                                        }
                                    } else if (p.equals("Trò chuyện")) {
                                        Input.clearPressed();
                                        dJ[fE] = -fm;
                                        a(dv[fE], 0, dz[fE], dA[fE] - 44);
                                    } else if (p.equals("Nói chuyện") && fB) {
                                        if (gk) {
                                            a(aH[0], 0, gn, go - 44);
                                        } else if (eU) {
                                            a(aH[2], 0, gn, go - 44);
                                            gk = true;
                                        } else if (eT) {
                                            a(aH[3], 26, gn, go - 44);
                                            eT = false;
                                        } else {
                                            a(aH[1], 0, gn, go - 44);
                                        }
                                    }
                                } else {
                                    label2585: {
                                        if (Input.held[Input.KEY_UP]) {
                                            if (eM) {
                                                for(var13 = 0; var13 < ii; ++var13) {
                                                    if (ff == il[var13] && a(fe - ik[var13]) < 20) {
                                                        fe = ik[var13];
                                                        B = 18;
                                                        Input.clearHeld();
                                                        ij = var13;
                                                        im[var13] = fm;
                                                        in[var13] = 0;
                                                        break label2585;
                                                    }
                                                }
                                            }

                                            if (eI) {
                                                A = -10;
                                            } else {
                                                A = -8;
                                            }

                                            B = 3;
                                            ir = -1;
                                            D = 0;
                                            fD = -1;
                                            fE = -1;
                                            p = " ";
                                            d = true;
                                        } else if (Input.held[Input.KEY_LEFT]) {
                                            if (fm == 1) {
                                                fm = -1;
                                            } else {
                                                B = 2;
                                                z = -Q;
                                            }

                                            fD = -1;
                                            fE = -1;
                                            p = " ";
                                            d = true;
                                        } else if (Input.held[Input.KEY_RIGHT]) {
                                            if (fm == -1) {
                                                fm = 1;
                                            } else {
                                                B = 2;
                                                z = Q;
                                            }

                                            fE = -1;
                                            fD = -1;
                                            p = " ";
                                            d = true;
                                        } else if (Input.held[Input.KEY_DOWN]) {
                                            D = 0;
                                            E = 0;
                                            B = 8;
                                        } else if (Input.held[Input.KEY_NUM1]) {
                                            if (eI) {
                                                A = -10;
                                            } else {
                                                A = -8;
                                            }

                                            fm = -1;
                                            z = -4;
                                            B = 3;
                                            ir = -1;
                                            D = 0;
                                            fD = -1;
                                            fE = -1;
                                            p = " ";
                                            d = true;
                                        } else if (Input.held[Input.KEY_NUM3]) {
                                            if (eI) {
                                                A = -10;
                                            } else {
                                                A = -8;
                                            }

                                            fm = 1;
                                            z = 4;
                                            B = 3;
                                            ir = -1;
                                            D = 0;
                                            fD = -1;
                                            fE = -1;
                                            p = " ";
                                            d = true;
                                        }

                                        if (Input.pressed[Input.KEY_FIRE]) {
                                            B = 5;
                                            ft = 0;
                                            D = 0;
                                            A = 0;
                                            Input.pressed[Input.KEY_FIRE] = false;
                                            fD = -1;
                                            fE = -1;
                                            p = " ";
                                            d = true;
                                        }
                                    }
                                }
                            } else if (B == 2) {
                                if (Input.held[Input.KEY_UP]) {
                                    ir = -1;
                                    if (eI) {
                                        A = -10;
                                    } else {
                                        A = -8;
                                    }

                                    B = 3;
                                    D = 0;
                                } else if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -Q + fo;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = Q + fo;
                                    }
                                }

                                if (Input.pressed[Input.KEY_FIRE]) {
                                    B = 5;
                                    ft = 0;
                                    D = 0;
                                    A = 0;
                                    Input.pressed[Input.KEY_FIRE] = false;
                                }
                            } else if (B == 11) {
                                if (Input.held[Input.KEY_UP]) {
                                    if (eI) {
                                        A = -10;
                                    } else {
                                        A = -8;
                                    }

                                    B = 3;
                                    D = 0;
                                } else if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -L + fo;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = L + fo;
                                    }
                                }
                            } else if (B == 3) {
                                if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -Q;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = Q;
                                    }
                                }

                                if (Input.pressed[Input.KEY_FIRE]) {
                                    B = 5;
                                    ft = 0;
                                    D = 0;
                                    Input.pressed[Input.KEY_FIRE] = false;
                                }

                                if (Input.held[Input.KEY_UP] && PlayerStats.hasMp() && A > -4 && (D == 0 && eJ || D == 1 && eV)) {
                                    ++D;
                                    A = -7;
                                    PlayerStats.drainMp(3);

                                    d = true;
                                    e(-1, fe - -8, ff);
                                    e(1, fe - 8, ff);
                                    g(fe, ff);
                                }
                            } else if (B == 4) {
                                if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -Q;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = Q;
                                    }
                                }

                                if (Input.pressed[Input.KEY_FIRE] && A > 4) {
                                    B = 5;
                                    D = 0;
                                    ft = 0;
                                    Input.pressed[Input.KEY_FIRE] = false;
                                }
                            } else if (B == 5) {
                                if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -Q + 2;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = Q - 2;
                                    }
                                }

                                if (Input.pressed[Input.KEY_FIRE]) {
                                    fv = true;
                                    Input.pressed[Input.KEY_FIRE] = false;
                                }
                            } else if (B == 18) {
                                if (Input.held[Input.KEY_UP]) {
                                    A = -10;
                                    B = 21;
                                    D = 10;
                                    ir = -1;
                                } else if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                        im[ij] = -1;
                                    } else {
                                        D = 1;
                                        B = 19;
                                        D = 10;
                                        z = -R;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                        im[ij] = 1;
                                    } else {
                                        B = 19;
                                        D = 10;
                                        z = R;
                                    }
                                } else if (Input.held[Input.KEY_DOWN]) {
                                    B = 1;
                                    ij = -1;
                                }
                            } else if (B == 19) {
                                if (Input.held[Input.KEY_UP]) {
                                    A = -10;
                                    B = 21;
                                    ir = -1;
                                    D = 10;
                                } else if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                        im[ij] = -1;
                                    } else {
                                        z = -R;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                        im[ij] = 1;
                                    } else {
                                        z = R;
                                    }
                                }
                            } else if (B == 21) {
                                if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                        im[ij] = -1;
                                    } else {
                                        z = -R;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                        im[ij] = 1;
                                    } else {
                                        z = R;
                                    }
                                }
                            } else if (B == 20) {
                                if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                        im[ij] = -1;
                                    } else {
                                        z = -R;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                        im[ij] = 1;
                                    } else {
                                        z = R;
                                    }
                                }
                            } else if (B == 17) {
                                if (Input.held[Input.KEY_UP]) {
                                    A = -10;
                                    B = 3;
                                    fc = -1;
                                    D = 0;
                                } else if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        B = 16;
                                        z = -M;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        B = 16;
                                        z = M;
                                    }
                                }
                            } else if (B == 16) {
                                if (Input.held[Input.KEY_UP]) {
                                    A = -10;
                                    B = 3;
                                    fc = -1;
                                    D = 0;
                                } else if (Input.held[Input.KEY_LEFT]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -M;
                                    }
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = M;
                                    }
                                }
                            } else if (B == 12) {
                                if (Input.held[Input.KEY_UP] && eP) {
                                    A = -10;
                                    B = 3;
                                    fc = -1;
                                    D = 0;
                                }
                            } else if (B == 14) {
                                if (Input.held[Input.KEY_LEFT]) {
                                    fm = -1;
                                } else if (Input.held[Input.KEY_RIGHT]) {
                                    fm = 1;
                                } else if (Input.held[Input.KEY_UP]) {
                                    B = 15;
                                }
                            }
                        }
                    }

                    if (fO != fI || fP != fJ) {
                        fK = fI - fO << 2;
                        fL = fJ - fP << 2;
                        fM += fK;
                        fO += fM >> 4;
                        fM &= 15;
                        fN += fL;
                        fP += fN >> 4;
                        fN &= 15;
                        if (fO < 0) {
                            fO = 0;
                        }

                        if (fO > fQ) {
                            fO = fQ;
                        }

                        if (fP < 0) {
                            fP = 0;
                        }

                        if (fP > fR) {
                            fP = fR;
                        }
                    }

                    if (aL >= 0) {
                        fO += aJ[aL];
                        fP += aK[aL];
                        if (++aL == 4) {
                            aL = -1;
                        }

                        if (fO < 0) {
                            fO = 0;
                        }

                        if (fO > fQ) {
                            fO = fQ;
                        }

                        if (fP < 0) {
                            fP = 0;
                        }

                        if (fP > fR) {
                            fP = fR;
                        }
                    }

                    if ((bw = fO / GameMap.TILE_SIZE - 1) < 0) {
                        bw = 0;
                    }

                    bx = fP / GameMap.TILE_SIZE;
                    bA = bw + by;
                    bB = bx + bz;
                    if (bx < 0) {
                        bx = 0;
                    }

                    if (bB > GameMap.height - 1) {
                        bB = GameMap.height - 1;
                    }

                    int[] var10000;
                    for(var13 = 0; var13 < iq; ++var13) {
                        switch (iu[var13]) {
                            case 2:
                                var10000 = is;
                                var10000[var13] += iv[var13];
                                if (iv[var13] > 0) {
                                    var10002 = iv[var13]--;
                                } else if (iv[var13] < 0) {
                                    var10002 = iv[var13]++;
                                } else {
                                    iu[var13] = 1;
                                }

                                if (!GameMap.hasFlag(is[var13] - 12, it[var13] + 1, TileFlags.SOLID) && !GameMap.hasFlag(is[var13] + 12, it[var13] + 1, TileFlags.SOLID)) {
                                    iu[var13] = 4;
                                    iw[var13] = 2;
                                }

                                if (GameMap.hasFlag(is[var13] + 12, it[var13] - 2, TileFlags.WALL_LEFT_SIDE) || GameMap.hasFlag(is[var13] - 12, it[var13] - 2, TileFlags.WALL_RIGHT_SIDE)) {
                                    is[var13] = I(is[var13]) + 12;
                                }
                                break;
                            case 4:
                                var10000 = it;
                                var10000[var13] += iw[var13];
                                if (iw[var13] < 20) {
                                    var10002 = iw[var13]++;
                                }

                                if (!GameMap.hasFlag(is[var13] + 12, it[var13], TileFlags.SOLID) && !GameMap.hasFlag(is[var13] - 12, it[var13], TileFlags.SOLID)) {
                                    if (GameMap.hasFlag(is[var13], it[var13] - GameMap.TILE_SIZE, TileFlags.WATER)) {
                                        it[var13] = H(it[var13]) + 1;
                                        g(is[var13], it[var13] - 13);
                                        g(is[var13] + 1, it[var13] - 10);
                                        iw[var13] = 1;
                                        ix[var13] = -1;
                                        iy[var13] = 1;
                                        iu[var13] = 11;
                                    }
                                    break;
                                }

                                it[var13] = H(it[var13]);
                                iw[var13] = 0;
                                iu[var13] = 1;
                                break;
                            case 11:
                                if (x % 2 == 0) {
                                    var10000 = it;
                                    var10000[var13] += ix[var13];
                                    var10000 = iw;
                                    var10000[var13] += ix[var13];
                                    if (iw[var13] >= 1) {
                                        ix[var13] = -1;
                                    }

                                    if (iw[var13] <= -1) {
                                        ix[var13] = 1;
                                    }

                                    var10000 = is;
                                    var10000[var13] += iy[var13];
                                    if (var13 == ir) {
                                        ff = it[ir] - 24;
                                        fe += iy[var13];
                                    }

                                    if (GameMap.hasFlag(is[var13] + 12, it[var13], TileFlags.WALL_LEFT_SIDE) || GameMap.hasFlag(is[var13] - 12, it[var13], TileFlags.WALL_RIGHT_SIDE)) {
                                        iy[var13] = -iy[var13];
                                    }
                                }
                        }
                    }

                    for(var13 = 0; var13 < ii; ++var13) {
                        if (var13 == ij) {
                            if (D > 1) {
                                in[var13] = 2;
                            } else if (x % 8 > 3) {
                                in[var13] = 1;
                            } else {
                                in[var13] = 0;
                            }
                        }

                        if (io[var13] == 1) {
                            var10000 = il;
                            var10000[var13] += ip[var13];
                            if (ip[var13] < 20) {
                                var10002 = ip[var13]++;
                            }

                            if (il[var13] >= GameMap.pixelHeight || GameMap.hasFlag(ik[var13], il[var13], TileFlags.SOLID)) {
                                ip[var13] = 0;
                                io[var13] = 0;
                                il[var13] = H(il[var13]);
                            }
                        }
                    }

                    for(var13 = 0; var13 < 5; ++var13) {
                        if (dT[var13] != -1) {
                            var10000 = dT;
                            var10000[var13] += a(dS[var13]);
                            if (dT[var13] > 30) {
                                dT[var13] = -1;
                            }

                            var10000 = dP;
                            var10000[var13] += dR[var13];
                            var10000 = dQ;
                            var10000[var13] += dS[var13];
                        }
                    }

                    if (!a) {
                        for(var13 = 0; var13 < 2; ++var13) {
                            if (ch[var13] != -1) {
                                var10002 = cg[var13]--;
                                if (x % 2 == 0) {
                                    var10002 = ch[var13]++;
                                    if (ch[var13] > 2) {
                                        ch[var13] = -1;
                                    } else {
                                        ci[var13] = ch[var13];
                                    }
                                }
                            }
                        }
                    }

                    if (!a) {
                        for(var13 = 0; var13 < 2; ++var13) {
                            if (cl[var13] != -1) {
                                var10002 = cl[var13]++;
                                if (cl[var13] >= 5) {
                                    cl[var13] = -1;
                                }

                                if (var13 == 0) {
                                    var10002 = cj[var13]--;
                                } else {
                                    var10002 = cj[var13]++;
                                }

                                var10002 = ck[var13]--;
                            }
                        }
                    }

                    for(var13 = 0; var13 < 2; ++var13) {
                        if (cc[var13] != -1) {
                            var10002 = cc[var13]++;
                            var10000 = ca;
                            var10000[var13] += ce[var13] << 2;
                            var10002 = cb[var13]--;
                            if (cc[var13] >= 6) {
                                cc[var13] = -1;
                            } else {
                                cd[var13] = (cc[var13] >> 1) % 3;
                            }
                        }
                    }

                    int var5;
                    for(var13 = 0; var13 < cV; ++var13) {
                        if (cY[var13] == 1) {
                            var10000 = cW;
                            var10000[var13] += da[var13];
                            var10000 = cX;
                            var10000[var13] += db[var13];
                            if (cW[var13] >= fO && cW[var13] <= fO + r && cX[var13] >= fP && cX[var13] <= fP + s) {
                                if (da[var13] > 0 && (GameMap.flagsAtPixel(cW[var13], cX[var13]) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                                    cY[var13] = 2;
                                    cW[var13] = I(cW[var13]);
                                } else if (da[var13] < 0 && (GameMap.flagsAtPixel(cW[var13], cX[var13]) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                                    cY[var13] = 2;
                                    cW[var13] = I(cW[var13]) + GameMap.TILE_SIZE;
                                }

                                var10002 = cZ[var13]++;
                                if (cZ[var13] > 1) {
                                    cZ[var13] = 0;
                                }

                                if (da[var13] != 0) {
                                    for(var4 = 0; var4 < jG; ++var4) {
                                        if (jO[var4] != 5 && jO[var4] != 1 && a(jJ[var4] - cW[var13]) < jM[var4] && a(jK[var4] - (jL[var4] >> 1) - cX[var13]) < jL[var4]) {
                                            boolean var14;
                                            label2807: {
                                                var5 = dc[var13] ? PlayerStats.dartDamage << 1 : PlayerStats.dartDamage;
                                                if (jO[var4] == 2 || jO[var4] == 3 || jO[var4] == 4) {
                                                    if (jI[var4] == 9 && da[var13] * jX[var4] < 0) {
                                                        jO[var4] = 4;
                                                        jT[var4] = 4;
                                                        da[var13] = -da[var13];
                                                        db[var13] = -db[var13];
                                                        var10000 = cW;
                                                        var10000[var13] += da[var13];
                                                        var10000 = cX;
                                                        var10000[var13] += db[var13];
                                                        var14 = true;
                                                        break label2807;
                                                    }

                                                    var10000 = jY;
                                                    var10000[var4] -= var5;
                                                    if (jY[var4] <= 0) {
                                                        jY[var4] = 0;
                                                        jT[var4] = -5;
                                                        jU[var4] = da[var13];
                                                        jV[var4] = 0;
                                                        jN[var4] = 0;
                                                        jO[var4] = 1;
                                                        L(var4);
                                                        var14 = true;
                                                        break label2807;
                                                    }

                                                    jT[var4] = 0;
                                                    jX[var4] = da[var13] < 0 ? 1 : -1;
                                                    jO[var4] = 6;
                                                    jW[var4] = 3;
                                                    cY[var13] = -1;
                                                }

                                                var14 = false;
                                            }

                                            if (!var14) {
                                                f(jJ[var4], jK[var4] - (jL[var4] >> 1), jX[var4]);
                                                a("-" + var5, 0, jJ[var4], jK[var4] - jL[var4], 0, -2);
                                            }
                                            break;
                                        }
                                    }

                                    for(var4 = 0; var4 < cp; ++var4) {
                                        if (cE[var4] != 5 && cE[var4] != 1 && a(cu[var4] - cW[var13]) < 20 && a(cv[var4] - 15 - cX[var13]) < 20) {
                                            if (dc[var13]) {
                                                a(var4, PlayerStats.dartDamage << 1, cH[var13]);
                                                f(cu[var4], cv[var4] - 15, cH[var4]);
                                                a("-" + (PlayerStats.dartDamage << 1), 0, cu[var4], cv[var4] - 32, 0, -2);
                                                cY[var13] = -1;
                                            } else {
                                                cE[var4] = 4;
                                                cF[var4] = 0;
                                                cG[var4] = 0;
                                                cH[var4] = da[var13] > 0 ? -1 : 1;
                                                da[var13] = -da[var13];
                                            }
                                            break;
                                        }
                                    }

                                    if (!dc[var13] && a(fe - cW[var13]) < fi && a(ff - fj - cX[var13]) < fj) {
                                        if (B == 5 && fd == 0) {
                                            da[var13] = -da[var13];
                                            db[var13] = -3;
                                        } else {
                                            a(PlayerStats.dartDamage, -fm);
                                            cY[var13] = -1;
                                        }
                                        break;
                                    }

                                    if (gN && a(gP - cW[var13]) < gV >> 1 && a(gQ - (gW >> 1) - cX[var13]) < gW >> 1) {
                                        c(PlayerStats.dartDamage, -gT);
                                        f(cW[var13], cX[var13], gT);
                                        a("-" + PlayerStats.dartDamage, 0, gP, gQ - gW, 0, -2);
                                        cY[var13] = -1;
                                    }

                                    if (hJ && a(hP - cW[var13]) < hO >> 1 && a(hQ - (hL >> 1) - cX[var13]) < hL >> 1) {
                                        da[var13] = -da[var13];
                                        db[var13] = -1;
                                    }

                                    if (hc && a(dz[0] - cW[var13]) < 12 && a(dA[0] - 16 - cX[var13]) < 32) {
                                        if (dD[0] == 5 && da[var13] * dJ[0] < 0) {
                                            da[var13] = -da[var13];
                                        } else if (dD[0] == 1 && da[var13] * dJ[0] < 0) {
                                            da[var13] = -da[var13];
                                            dE[0] = dF[0] = 0;
                                            dD[0] = 5;
                                        } else {
                                            d(PlayerStats.dartDamage, -dJ[0]);
                                            cY[var13] = -1;
                                        }
                                    }
                                }
                            } else {
                                cY[var13] = -1;
                            }
                        } else if (cY[var13] > 1) {
                            var10002 = cY[var13]++;
                            if (cY[var13] >= 10) {
                                cY[var13] = 1;
                                db[var13] = 12;
                                da[var13] = 0;
                            }
                        }
                    }

                    O();
                    if (dX > 0 && ++dX > 30) {
                        dX = 0;
                    }

                    P();
                    if (gb >= 0) {
                        if (gc > fO + 10 && gc < fO + r - 10 && gd > fP + 10 && gd < fP + s - 10) {
                            ge = 2;
                            gf = gc;
                            gg = gd + gi;
                        } else if (a(fe - gc) > a(ff - gd)) {
                            if (fe > gc) {
                                ge = 1;
                                gf = fO + 20 + gi;
                            } else {
                                ge = 0;
                                gf = fO + r - 20 + gi;
                            }

                            if ((gg = gd + 24) < fP + 10) {
                                gg = fP + 10;
                            }

                            if (gg > fP + s - 10) {
                                gg = fP + s - 10;
                            }
                        } else if (ff > gd) {
                            ge = 3;
                            gf = fe;
                            gg = fP + 20 + gi;
                        } else {
                            ge = 2;
                            gf = fe;
                            gg = fP + s - 20 + gi;
                        }

                        if ((gi += gh) == 5 || gi == -5) {
                            gh = -gh;
                        }
                    }

                    R();
                    if (gm == w) {
                        try {
                            switch (gr) {
                                case 1:
                                    if (++gA > 30) {
                                        gA = 0;
                                    }

                                    if (gA % 15 < 5) {
                                        gs = 0;
                                    } else {
                                        gs = 1;
                                    }

                                    if (gk && x % 6 == 0) {
                                        D();
                                    }

                                    if (x % 15 == 0) {
                                        E();
                                    }
                                    break;
                                case 2:
                                    if (++gA >= 10) {
                                        gA = 0;
                                    }

                                    gs = (gA >> 1) + 2;
                                    if (gz == 1) {
                                        if (GameMap.hasFlag(gn + gv, go - gw, TileFlags.WALL_LEFT_SIDE)) {
                                            gp = 0;
                                            gn = I(gn + gv) - gv;
                                        }
                                    } else if (GameMap.hasFlag(gn - gv - 1, go - gw, TileFlags.WALL_RIGHT_SIDE)) {
                                        gp = 0;
                                        gn = I(gn - gv - 1) + GameMap.TILE_SIZE + gv;
                                    }

                                    if ((gn += gp) < 0) {
                                        gn = 0;
                                    }

                                    if (gn >= GameMap.pixelWidth) {
                                        gn = GameMap.pixelWidth - 1;
                                    }

                                    if (ir != -1) {
                                        if (a(gn - is[ir]) >= 20) {
                                            ir = -1;
                                            if (GameMap.hasFlag(gn, go, TileFlags.SOLID)) {
                                                gr = 1;
                                            } else {
                                                gr = 4;
                                                gq = 2;
                                                gp = 3 * gz;
                                                gB = 0;
                                            }
                                        }
                                    } else if ((GameMap.flagsAtPixel(gn + gz * gt, go) & TileFlags.SOLID) != TileFlags.SOLID) {
                                        gr = 1;
                                        gs = 0;
                                        gp = 0;
                                        gA = 0;
                                        gB = 0;
                                    }

                                    e(gz, gn - (gz << 3), go);
                                    if (gK > 0) {
                                        --gK;
                                    } else {
                                        E();
                                    }
                                    break;
                                case 3:
                                    if (gp == 0) {
                                        gp = gz << 2;
                                    }

                                    if (gz == 1) {
                                        if ((GameMap.flagsAtPixel(gn + gv, go - gw) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                                            gp = 0;
                                        }
                                    } else if ((GameMap.flagsAtPixel(gn - gv, go - gw) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                                        gp = 0;
                                    }

                                    if (ff < 0) {
                                        ff = 0;
                                        A = -1;
                                    }

                                    if ((gn += gp) < 0) {
                                        gn = 0;
                                    }

                                    if (gn >= GameMap.pixelWidth) {
                                        gn = GameMap.pixelWidth - 1;
                                    }

                                    go += gq;
                                    if (gA == 0) {
                                        gs = 7;
                                    } else {
                                        gs = 8;
                                    }

                                    if (gp > 1) {
                                        --gp;
                                    }

                                    if (++gq == 0) {
                                        gr = 4;
                                        gA = 0;
                                        gB = 0;
                                        gq = 1;
                                    }

                                    if (gC < 0) {
                                        ++gC;
                                    }
                                    break;
                                case 4:
                                    if (gz == 1) {
                                        if ((GameMap.flagsAtPixel(gn + gt, go) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                                            gp = 0;
                                        }
                                    } else if ((GameMap.flagsAtPixel(gn - gt, go) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                                        gp = 0;
                                    }

                                    ig = -1;
                                    if ((gn += gp) < 0) {
                                        gn = 0;
                                    }

                                    if (gn >= GameMap.pixelWidth) {
                                        gn = GameMap.pixelWidth - 1;
                                    }

                                    go += gq;
                                    if (gq < 18) {
                                        ++gq;
                                    }

                                    if (gq > 5) {
                                        gp = 0;
                                    }

                                    if (gp > 1) {
                                        --gp;
                                    }

                                    gs = 8;
                                    if (go >= GameMap.pixelHeight) {
                                        go = GameMap.pixelHeight - 1;
                                        gr = 3;
                                        gq = -20;
                                    }

                                    if (gq > 4 && (GameMap.flagsAtPixel(gn, go) & TileFlags.SOLID) == TileFlags.SOLID) {
                                        gr = 1;
                                        gq = 0;
                                        gp = 0;
                                        gB = 0;
                                        gA = 0;
                                        go = I(go);
                                        e(-1, gn - -8, go);
                                        e(1, gn - 8, go);
                                    }

                                    if (GameMap.hasFlag(gn, go, TileFlags.WATER) && GameMap.hasFlag(gn, go + 24, TileFlags.DEEP_WATER) && go % GameMap.TILE_SIZE > 8) {
                                        g(gn, H(go) + 8);
                                        gr = 3;
                                        gp = (gz = fe > gn ? 1 : -1) << 2;
                                        gq = -11;
                                    }

                                    if (gq > 6) {
                                        gs = 6;
                                    }

                                    if (gC < 0) {
                                        ++gC;
                                    }
                                    break;
                                case 5:
                                    switch (++gA) {
                                        case 1:
                                        case 2:
                                        case 3:
                                            ig = -1;
                                            gs = 9;
                                            break;
                                        case 4:
                                        case 5:
                                            ig = 0;
                                            gs = 10;
                                            break;
                                        case 6:
                                            ig = 1;
                                            gs = 11;
                                            v();
                                            break;
                                        case 7:
                                            ig = 2;
                                            gs = 11;
                                            break;
                                        case 8:
                                            ig = -1;
                                            gs = 12;
                                            break;
                                        case 9:
                                            ig = -1;
                                            gs = 9;
                                            gr = 1;
                                            gA = 0;
                                            gB = 0;
                                    }

                                    if (ih == 1) {
                                        ig = -1;
                                    }
                                case 6:
                                default:
                                    break;
                                case 7:
                                    if ((GameMap.flagsAtPixel(gn + gv, go - gw) & TileFlags.WALL_LEFT_SIDE) == TileFlags.WALL_LEFT_SIDE) {
                                        gp = -gp;
                                    }

                                    if ((GameMap.flagsAtPixel(gn - gv, go - gw) & TileFlags.WALL_RIGHT_SIDE) == TileFlags.WALL_RIGHT_SIDE) {
                                        gp = -gp;
                                    }

                                    gn += gp;
                                    go += gq;
                                    gs = 7;
                                    if (gp > 1) {
                                        --gp;
                                    }

                                    if (gp < -1) {
                                        ++gp;
                                    }

                                    if (++gq > 0) {
                                        gq = 0;
                                    }

                                    if (++gA >= 7) {
                                        gr = 4;
                                        gC = -20;
                                        gB = 0;
                                        gA = 0;
                                        gq = 1;
                                    }
                            }
                        } catch (Exception var11) {
                            gn = fe;
                            go = ff;
                            gp = 0;
                            gq = 0;
                            gr = 4;
                        }
                    }

                    Y();
                    if (x % 2 == 0 && ((doSt += dq) > ds || doSt < dr)) {
                    dq = -dq;
                    doSt += dq;
                }

                ak();
                aa();
                if (gN) {
                    if (hb > 0) {
                        --hb;
                    }

                    switch (gS) {
                        case 1:
                            if (gX > 0) {
                                --gX;
                            } else {
                                if ((var13 = x % 80 - 40) > 0) {
                                    var13 += 20;
                                } else {
                                    var13 -= 20;
                                }

                                gY = gP + var13;
                                gR = (gT = var13 > 0 ? 1 : -1) << 2;
                                if (gZ < ha >> 1) {
                                    gR = gT << 3;
                                }

                                gS = 2;
                                if (x % 5 == 0) {
                                    N();
                                }
                            }

                            gU = 0;
                            if (x % 10 == 6) {
                                e(-gT, gP + gT * 28, gQ - 5);
                            }
                            break;
                        case 2:
                            if (x % 8 == 0) {
                                N();
                            }

                            if (!GameMap.hasFlag(gP + (gT << 4), gQ + 1, TileFlags.SOLID)) {
                                gS = 1;
                                gX = 0;
                            } else {
                                if (a(gP - gY) < 8) {
                                    gS = 1;
                                    gX = 20;
                                }

                                if (x % 2 == 0) {
                                    gP += gR;
                                    gU = 0;
                                } else {
                                    gU = 1;
                                }
                            }
                        case 3:
                        case 4:
                        default:
                            break;
                        case 5:
                            if (!GameMap.hasFlag(gP + (gT << 4), gQ + 1, TileFlags.SOLID)) {
                                gS = 1;
                                gX = 0;
                            } else {
                                if (a(gP - gY) < 4) {
                                    gS = 1;
                                    gX = 35;
                                }

                                if (a(ff - gQ) < 18 && a(gP + (gT << 4) - fe) < 10) {
                                    a(gO, gT);
                                    gS = 1;
                                    gX = 45;
                                    if (gZ < ha >> 1) {
                                        gX = 18;
                                    }
                                }

                                gP += gR;
                                gU = x % 3;
                            }
                            break;
                        case 6:
                            gQ += gX;
                            ++gX;
                            gP += gR;
                            if (gR > 1) {
                                --gR;
                            }

                            if (gR < -1) {
                                ++gR;
                            }

                            if (gQ > fP + s + gW || gQ >= GameMap.pixelHeight + gW) {
                                gX = 0;
                                gS = 22;
                            }
                            break;
                        case 7:
                            gP -= gT;
                            gU = 2;
                            if (++gX == 4) {
                                gS = 1;
                                if ((gX = gY) > 0 && gZ < ha >> 1) {
                                    gX = 5;
                                }
                            }
                    }
                }

                if (hJ) {
                    var13 = x % 10;
                    if (ic > 0) {
                        --ic;
                    }

                    switch (hX) {
                        case 1:
                            ++ib;
                            hQ += 2;
                            ++bJ;
                            ++bK;
                            ++bL;
                            if (ib > 50) {
                                hX = 5;
                            }
                            break;
                        case 2:
                            if (hM < hN >> 1) {
                                hP += hY << 2;
                            } else {
                                hP += hY << 1;
                            }

                            if (hP < hZ) {
                                hY = 1;
                            }

                            if (hP > ia) {
                                hY = -1;
                            }

                            if (ib > 0) {
                                --ib;
                            }

                            if (ib == 0 && a(fe - (hP - 40)) < 30) {
                                ib = 0;
                                hX = 4;
                                hY = hP < fe ? 1 : -1;
                            }

                            if (x % 122 == 0) {
                                c(hP, hQ - 45, -1, -12, hK);
                                c(hP, hQ - 50, 0, -15, hK);
                                c(hP, hQ - 45, 1, -12, hK);
                            } else if (x % 200 == 0) {
                                hX = 7;
                                ib = 0;
                            }
                        case 3:
                        case 5:
                        case 6:
                        default:
                            break;
                        case 4:
                            if (ib == 110) {
                                hX = 2;
                                ib = 30;
                            } else if (ib < 35) {
                                --hQ;
                            } else if (ib >= 75) {
                                ++hQ;
                                if (var13 == 5) {
                                    c(hP, hQ - 45, -2, -10, hK);
                                } else if (var13 == 7 && hM < hN >> 1) {
                                    c(hP, hQ - 50, 0, -11, hK);
                                } else if (var13 == 9) {
                                    c(hP, hQ - 45, 2, -10, hK);
                                }
                            }

                            if (ib > 20 && ib < 50 && var13 == 0) {
                                b(hP + hY * 20, hQ - 29, hY << 3, hK, 2);
                            }

                            if (ib > 45 && ib < 50) {
                                bI += hY;
                            } else if (ib > 50 && ib < 55) {
                                bI -= hY;
                            }

                            ++ib;
                            break;
                        case 7:
                            if (++ib == 50) {
                                hX = 2;
                                ib = 1;
                            } else if (ib == 30) {
                                if ((hP = fe + (fm << 6)) > ia) {
                                    hP = ia - 30;
                                }

                                if (hP < hZ) {
                                    hP = hZ + 30;
                                }

                                hY = hP < fe ? 1 : -1;
                            } else if (ib < 10) {
                                hQ += 10;
                            } else if (ib > 40) {
                                hQ -= 10;
                            }
                    }

                    if (var13 < 4) {
                        --hQ;
                    } else if (var13 < 8) {
                        ++hQ;
                    }

                    if (var13 > 4) {
                        --bJ;
                        ++bK;
                    } else {
                        ++bJ;
                        --bK;
                    }

                    if (hY == -1) {
                        hR = hP + bG;
                        hV = hQ + bJ;
                        hU = hP + bH;
                        hW = hQ + bK;
                        hS = hP + bI;
                        hT = hQ + bL;
                    } else {
                        hR = hP - bG;
                        hV = hQ + bJ;
                        hU = hP - bH;
                        hW = hQ + bK;
                        hS = hP - bI;
                        hT = hQ + bL;
                    }
                }

                ac();
                if (hc) {
                    label2824: {
                        var13 = x % 50;
                        var5 = a(dz[0] - fe);
                        switch (dD[0]) {
                            case 0:
                                var10002 = dE[0]++;
                                if (dE[0] > 30) {
                                    dE[0] = 0;
                                }

                                if (dE[0] % 18 < 6) {
                                    dG[0] = 0;
                                } else {
                                    dG[0] = 1;
                                }

                                if (dx) {
                                    hd = false;
                                    dF[0] = dy;
                                    dJ[0] = dy > dz[0] ? 1 : -1;
                                    dB[0] = dJ[0] << 2;
                                    dB[0] = dJ[0] > 0 ? dB[0] : dB[0];
                                    dD[0] = 2;
                                    break label2824;
                                }

                                if (var5 >= 40 || B != 5) {
                                    break label2824;
                                }

                                hd = true;
                                dD[0] = 3;
                                dJ[0] = -fm;
                                if (var13 > 35) {
                                    dB[0] = fm * 5;
                                    dC[0] = -9;
                                } else {
                                    dB[0] = -fm * 6;
                                    dC[0] = -9;
                                }

                                if (dz[0] < dM[0] + 40) {
                                    dB[0] = 10;
                                    dJ[0] = -1;
                                } else if (dz[0] > dN[0] - 40) {
                                    dB[0] = -10;
                                    dJ[0] = 1;
                                }
                                break;
                            case 1:
                                var10002 = dE[0]++;
                                if (dE[0] > 30) {
                                    dE[0] = 0;
                                }

                                if (dE[0] % 18 < 6) {
                                    dG[0] = 0;
                                } else {
                                    dG[0] = 1;
                                }

                                if (he) {
                                    dD[0] = 0;
                                    dE[0] = dF[0] = 0;
                                }

                                if (var5 < 40 && B == 5 && fm != dJ[0]) {
                                    dD[0] = 3;
                                    dJ[0] = -fm;
                                    if (var13 > 35) {
                                        dB[0] = fm * 5;
                                        dC[0] = -9;
                                    } else {
                                        dB[0] = -fm * 6;
                                        dC[0] = -9;
                                    }

                                    if (dz[0] < dM[0] + 40) {
                                        dB[0] = 10;
                                        dJ[0] = -1;
                                    } else if (dz[0] > dN[0] - 40) {
                                        dB[0] = -10;
                                        dJ[0] = 1;
                                    }

                                    dE[0] = dF[0] = 0;
                                } else if (var5 < 40 && var13 < 10) {
                                    dJ[0] = fe > dz[0] ? 1 : -1;
                                    dD[0] = 5;
                                    dE[0] = dF[0] = dL[0] = 0;
                                } else if (var5 < 100 && var5 > 40 && var13 < 1) {
                                    dJ[0] = fe > dz[0] ? 1 : -1;
                                    dD[0] = 24;
                                    dE[0] = dF[0] = dL[0] = 0;
                                    dB[0] = (a(dz[0] - fe) >> 3) * dJ[0];
                                } else if (var5 > 100 && var5 < 160 && var13 < 30) {
                                    dJ[0] = fe > dz[0] ? 1 : -1;
                                    dD[0] = 8;
                                    dE[0] = dF[0] = dL[0] = 0;
                                } else if (var5 >= 20 && var13 != 42) {
                                    if (var5 > 160) {
                                        dJ[0] = fe > dz[0] ? 1 : -1;
                                        dF[0] = dz[0] + (dJ[0] << 7);
                                        if (dF[0] < dM[0]) {
                                            dF[0] = dM[0];
                                        } else if (dF[0] > dN[0]) {
                                            dF[0] = dN[0];
                                        }

                                        dE[0] = 0;
                                        dD[0] = 2;
                                        dB[0] = dJ[0] << 2;
                                    }
                                } else {
                                    dJ[0] = -fm;
                                    dF[0] = dz[0] + (dJ[0] << 6);
                                    if (dF[0] < dM[0] || dF[0] > dN[0]) {
                                        dJ[0] = -dJ[0];
                                        dF[0] = dz[0] + (dJ[0] << 6);
                                    }

                                    dE[0] = 0;
                                    dD[0] = 2;
                                    dB[0] = dJ[0] << 3;
                                }
                                break label2824;
                            case 2:
                                var10002 = dE[0]++;
                                if (dE[0] >= 10) {
                                    dE[0] = 0;
                                }

                                dG[0] = (dE[0] >> 1) + 2;
                                var10000 = dz;
                                var10000[0] += dB[0];
                                if (!dx && (dJ[0] > 0 && dz[0] > dF[0] || dJ[0] < 0 && dz[0] < dF[0])) {
                                    dJ[0] = fe > dz[0] ? 1 : -1;
                                    dF[0] = dE[0] = 0;
                                    dD[0] = 1;
                                }
                                break label2824;
                            case 3:
                            case 7:
                                var10000 = dz;
                                var10000[0] += dB[0];
                                if (dz[0] < dM[0]) {
                                    dz[0] = dM[0];
                                }

                                if (dz[0] > dN[0]) {
                                    dz[0] = dN[0];
                                }

                                var10000 = dA;
                                var10000[0] += dC[0];
                                dG[0] = 23;
                                if (dB[0] > 0) {
                                    var10002 = dB[0]--;
                                }

                                var10002 = dC[0]++;
                                if (dC[0] == 0) {
                                    dD[0] = 4;
                                    dE[0] = 0;
                                    dC[0] = 1;
                                }
                                break label2824;
                            case 4:
                                var10000 = dz;
                                var10000[0] += dB[0];
                                if (dz[0] < dM[0]) {
                                    dz[0] = dM[0];
                                }

                                if (dz[0] > dN[0]) {
                                    dz[0] = dN[0];
                                }

                                var10000 = dA;
                                var10000[0] += dC[0];
                                dG[0] = 7;
                                if (dC[0] < 18) {
                                    var10002 = dC[0]++;
                                }

                                if (dB[0] > 0) {
                                    var10002 = dB[0]--;
                                }

                                dG[0] = 7;
                                if (dA[0] >= GameMap.pixelHeight) {
                                    dD[0] = 6;
                                } else if ((GameMap.flagsAtPixel(dz[0], dA[0]) & TileFlags.SOLID) == TileFlags.SOLID) {
                                    dD[0] = 1;
                                    dE[0] = dF[0] = 0;
                                    dB[0] = dC[0] = 0;
                                    dA[0] = I(dA[0]);
                                    e(-1, dz[0] - -8, dA[0]);
                                    e(1, dz[0] - 8, dA[0]);
                                    if (a(dz[0] - fe) < 40 && var13 < 25) {
                                        dJ[0] = fe > dz[0] ? 1 : -1;
                                        dD[0] = 5;
                                        dE[0] = dF[0] = dL[0] = 0;
                                    }

                                    if (var5 < 40) {
                                        dJ[0] = fm;
                                        dF[0] = dz[0] + dJ[0] * 110;
                                        if (dF[0] < dM[0] || dF[0] > dN[0]) {
                                            dJ[0] = -dJ[0];
                                            dF[0] = dz[0] + (dJ[0] << 6);
                                        }

                                        dE[0] = 0;
                                        dD[0] = 2;
                                        dB[0] = dJ[0] * 10;
                                    }
                                }
                                break label2824;
                            case 5:
                                var10002 = dE[0]++;
                                switch (dF[0]) {
                                    case 0:
                                        switch (dE[0]) {
                                            case 1:
                                            case 2:
                                            case 3:
                                                dI[0] = -1;
                                                dG[0] = 13;
                                                break label2824;
                                            case 4:
                                                dI[0] = 0;
                                                dG[0] = 14;
                                                break label2824;
                                            case 5:
                                                dI[0] = 1;
                                                dG[0] = 15;
                                                break label2824;
                                            case 6:
                                                dI[0] = 1;
                                                dG[0] = 15;
                                                z(0);
                                                break label2824;
                                            case 7:
                                                dI[0] = 2;
                                                dG[0] = 16;
                                                break label2824;
                                            case 8:
                                                dI[0] = 2;
                                                dG[0] = 16;
                                                dF[0] = 2;
                                                dE[0] = 1;
                                            default:
                                                break label2824;
                                        }
                                    case 1:
                                        switch (dE[0]) {
                                            case 1:
                                                dI[0] = 1;
                                                dG[0] = 15;
                                                break label2824;
                                            case 2:
                                                dI[0] = 1;
                                                dG[0] = 15;
                                                dF[0] = 10;
                                            default:
                                                break label2824;
                                        }
                                    case 2:
                                        switch (dE[0]) {
                                            case 1:
                                            case 2:
                                            case 3:
                                                dI[0] = 3;
                                                dG[0] = 17;
                                                break label2824;
                                            case 4:
                                                dI[0] = 4;
                                                dG[0] = 18;
                                                break label2824;
                                            case 5:
                                                dI[0] = 5;
                                                dG[0] = 19;
                                                break label2824;
                                            case 6:
                                                dI[0] = 6;
                                                dG[0] = 20;
                                                z(0);
                                                break label2824;
                                            case 7:
                                                dI[0] = 6;
                                                dG[0] = 20;
                                                dF[0] = 3;
                                                dE[0] = 1;
                                            default:
                                                break label2824;
                                        }
                                    case 3:
                                        switch (dE[0]) {
                                            case 1:
                                                dI[0] = 1;
                                                dG[0] = 15;
                                                break label2824;
                                            case 2:
                                                dI[0] = 1;
                                                dG[0] = 15;
                                                break label2824;
                                            case 3:
                                                dI[0] = 2;
                                                dG[0] = 16;
                                                break label2824;
                                            case 4:
                                                dI[0] = 2;
                                                dG[0] = 16;
                                                dF[0] = 1;
                                                dE[0] = 1;
                                        }
                                    case 4:
                                    case 5:
                                    case 6:
                                    case 7:
                                    case 8:
                                    case 9:
                                    default:
                                        break label2824;
                                    case 10:
                                        switch (dE[0]) {
                                            case 1:
                                                dI[0] = 2;
                                                dG[0] = 16;
                                                break label2824;
                                            case 2:
                                                dI[0] = 0;
                                                dG[0] = 14;
                                                break label2824;
                                            case 3:
                                            case 4:
                                                dG[0] = 13;
                                                dI[0] = -1;
                                                break label2824;
                                            case 5:
                                                dG[0] = 13;
                                                dI[0] = -1;
                                                dD[0] = 1;
                                                dF[0] = 120;
                                                dE[0] = 0;
                                            default:
                                                break label2824;
                                        }
                                }
                            case 8:
                                var10002 = dE[0]++;
                                switch (dE[0]) {
                                    case 1:
                                    case 2:
                                    case 3:
                                        dG[0] = 13;
                                        break label2824;
                                    case 4:
                                        dG[0] = 14;
                                        break label2824;
                                    case 5:
                                        dG[0] = 15;
                                        break label2824;
                                    case 6:
                                        dG[0] = 15;
                                        a(dz[0] + (dJ[0] << 3), dA[0] - 12, dJ[0] << 3, 0, false);
                                        break label2824;
                                    case 7:
                                        dG[0] = 16;
                                        break label2824;
                                    case 8:
                                        dG[0] = 16;
                                        dF[0] = 2;
                                        break label2824;
                                    case 9:
                                        dE[0] = 0;
                                        dD[0] = 1;
                                    default:
                                        break label2824;
                                }
                            case 24:
                                var10002 = dF[0]++;
                                if (dF[0] < 7) {
                                    var10002 = dE[0]++;
                                    if (dE[0] >= 10) {
                                        dE[0] = 0;
                                    }

                                    dG[0] = (dE[0] >> 1) + 2;
                                    var10000 = dz;
                                    var10000[0] += dB[0];
                                    if (a(dz[0] - fe) < 30) {
                                        if (B == 5 && fm != dJ[0]) {
                                            dD[0] = 3;
                                            dJ[0] = -fm;
                                            dB[0] = fm << 1;
                                            if (dz[0] < dM[0] + 30) {
                                                dB[0] = 6;
                                                dJ[0] = -1;
                                            } else if (dz[0] > dN[0] - 30) {
                                                dB[0] = -6;
                                                dJ[0] = 1;
                                            }

                                            dC[0] = -9;
                                            break;
                                        } else {
                                            dF[0] = 6;
                                        }
                                    }
                                } else if (dF[0] != 7) {
                                    if (dF[0] >= 15) {
                                        dI[0] = -1;
                                        dD[0] = 1;
                                        break;
                                    } else {
                                        var10002 = dE[0]++;
                                        switch (dE[0]) {
                                            case 1:
                                            case 2:
                                            case 3:
                                                dI[0] = -1;
                                                dG[0] = 13;
                                                break label2824;
                                            case 4:
                                                dI[0] = 0;
                                                dG[0] = 14;
                                                break label2824;
                                            case 5:
                                                dI[0] = 1;
                                                dG[0] = 15;
                                                break label2824;
                                            case 6:
                                                dI[0] = 1;
                                                dG[0] = 15;
                                                z(0);
                                                break label2824;
                                            case 7:
                                                dI[0] = 2;
                                                dG[0] = 16;
                                                break label2824;
                                            case 8:
                                                dI[0] = 2;
                                                dG[0] = 16;
                                                dF[0] = 2;
                                                dE[0] = 1;
                                        }
                                    }
                                } else {
                                    dE[0] = 0;
                                }
                            default:
                                break label2824;
                        }

                        dE[0] = dF[0] = 0;
                    }
                }

                W();
                ab();
                if (fH != 0) {
                    if (fH == 1) {
                        if (a(ff - (fh >> 1) - (fG - 7)) < 10 && a(fe - fF) < 10) {
                            fH = 2;
                            fA = true;
                            b("đã lấy được bí kíp");
                        }
                    } else {
                        ++fH;
                        fG -= 3;
                        if (fH > 40) {
                            fH = 0;
                        }
                    }
                }

                if (cm && ++cn >= 150) {
                    cm = false;
                }
                break;
                case 2:
                    if (T < S) {
                        T += 5;
                    }
                    break;
                case 3:
                    if (++x == 10000) {
                        x = 0;
                    }

                    if (Input.pressed[Input.KEY_LEFT]) {
                        if (--aX < 0) {
                            aX = aE.length - 1;
                        }

                        Input.pressed[Input.KEY_LEFT] = false;
                    }

                    if (Input.pressed[Input.KEY_RIGHT]) {
                        if (++aX == aE.length) {
                            aX = 0;
                        }

                        Input.pressed[Input.KEY_RIGHT] = false;
                    }

                    if (Input.pressed[Input.KEY_FIRE]) {
                        label2172: {
                            Input.clearPressed();
                            if (aX == 0) {
                                titleImage = null;
                                B();
                                Save.pendingSave = true;
                                gM = System.currentTimeMillis();
                                x(99);
                            } else if (aX == 1) {
                                if (!Save.isSaved()) {
                                    break label2172;
                                }

                                titleImage = null;
                                B();
                                int[] var12;
                                gL = (var12 = a(Save.loadRecord("ninjasave")))[0];
                                w = var12[1];
                                x = var12[2];
                                eE = var12[3] == 1;
                                dd = var12[4];
                                dw = var12[5] == 1;
                                eF = var12[6] == 1;
                                eG = var12[7] == 1;
                                eH = var12[8] == 1;
                                gk = var12[9] == 1;
                                gl = var12[10];
                                gm = var12[11];
                                ij = var12[12];
                                y = var12[13];
                                eW = var12[14] == 1;
                                Save.pendingLoad = true;
                                gM = System.currentTimeMillis();
                                x(w);
                            } else if (aX == 2) {
                                titleImage = null;
                                B();
                                e = 2;
                                aj();
                                Midlet.nextGameState = 11;
                                w = 101;
                                (new Thread(Midlet.instance)).start();
                            } else if (aX == 3) {
                                try {
                                    Midlet.instance.platformRequest("http://wap.teamobi.com/");
                                } catch (Exception var10) {
                                }
                            } else if (aX == 4) {
                                Midlet.instance.notifyDestroyed();
                            }

                            Input.pressed[Input.KEY_FIRE] = false;
                        }
                    }

                    if (fO == 0) {
                        fO = 960;
                        fP = 48;
                        fK = 1;
                        fL = 1;
                    }

                    fO += fK;
                    fP += fL;
                    if (fO > fQ) {
                        fO = fQ;
                        fK = -fK;
                    }

                    if (fO < 24) {
                        fO = 24;
                        fK = -fK;
                    }

                    if (fP < 0) {
                        fP = 0;
                        fL = -fL;
                    }

                    if (fP > fR) {
                        fP = fR;
                        fL = -fL;
                    }

                    if ((bw = fO / GameMap.TILE_SIZE - 1) < 0) {
                        bw = 0;
                    }

                    bx = fP / GameMap.TILE_SIZE;
                    bA = bw + by;
                    bB = bx + bz;
                    if (bx < 0) {
                        bx = 0;
                    }

                    if (bB > GameMap.height - 1) {
                        bB = GameMap.height - 1;
                    }

                    if (ba > aY) {
                        ba -= 4;
                    }

                    if (bb < aZ) {
                        bb += 4;
                    }

                    if (bd > bc) {
                        bd -= 2;
                    }
                    break;
                case 4:
                    if (jD < 4) {
                        jE = jD;
                    }

                    if (jD > 51) {
                        logoImage = null;
                        at();
                        e = 10;
                    } else if (jD > 46) {
                        jE = 4 - (jD - 46);
                    }

                    aT = aM.getWidth();
                    aU = aM.getHeight();
                    ++jD;
                    break;
                case 5:
                    if (++kJ > 10000) {
                        kJ = 0;
                    }

                    var3 = false;
                    if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
                        var3 = true;
                        cornerIcons = null;
                        ah();
                        e = 1;
                        d = true;
                    } else if (!Input.pressed[Input.KEY_FIRE] && !Input.pressed[Input.KEY_SOFT_LEFT]) {
                        if (Input.pressed[Input.KEY_DOWN]) {
                            var3 = true;
                            if (++kI > 3) {
                                kI = 0;
                            }
                        } else if (Input.pressed[Input.KEY_UP]) {
                            var3 = true;
                            if (--kI < 0) {
                                kI = 3;
                            }
                        } else if (Input.pressed[Input.KEY_LEFT]) {
                            var3 = true;
                        } else if (Input.pressed[Input.KEY_RIGHT]) {
                            var3 = true;
                        }
                    } else {
                        var3 = true;
                        if (kI == 0) {
                            ao();
                            e = 8;
                        }

                        if (kI == 1) {
                            aq();
                            e = 6;
                        }

                        if (kI == 2) {
                            if (B != 1) {
                                cornerIcons = null;
                                ah();
                                au();
                                s(5);
                                a("Bạn chỉ có thể lưu và thoát khi nhân vật đang đứng yên.");
                                Y = bD - 40;
                                e = 7;
                            } else {
                                b = false;
                            }
                        }
                    }

                    if (var3) {
                        Input.clearPressed();
                    }
                    break;
                case 6:
                    if (++kW > 10000) {
                        kW = 0;
                    }

                    if (ld != kX || le != kY) {
                        kZ = kX - ld << 1;
                        la = kY - le << 1;
                        lb += kZ;
                        ld += lb >> 4;
                        lb &= 15;
                        lc += la;
                        le += lc >> 4;
                        lc &= 15;
                        if (ld < -40) {
                            ld = -40;
                        }

                        if (ld > lf) {
                            ld = lf;
                        }

                        if (le < -40) {
                            le = -40;
                        }

                        if (le > lg) {
                            le = lg;
                        }
                    }

                    var3 = false;
                    if (Input.pressed[Input.KEY_FIRE] || Input.pressed[Input.KEY_SOFT_LEFT]) {
                        Input.clearPressed();
                        if (kV != -1) {
                            if (!X[kV]) {
                                s(1);
                                e = 7;
                            } else if (lh == kV) {
                                s(3);
                                e = 7;
                            } else {
                                s(2);
                                e = 7;
                            }
                        }
                    }

                    if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
                        ar();
                        cornerIcons = null;
                        ah();
                        e = 1;
                        Input.clearPressed();
                        Input.clearHeld();
                        Input.pressed[Input.KEY_SOFT_LEFT] = true;
                    } else {
                        if (Input.held[Input.KEY_UP]) {
                            if ((kU -= 4) < -20) {
                                kU = -20;
                            }

                            var3 = true;
                        }

                        if (Input.held[Input.KEY_DOWN]) {
                            if ((kU += 4) > 279) {
                                kU = 279;
                            }

                            var3 = true;
                        }

                        if (Input.held[Input.KEY_LEFT]) {
                            if ((kT -= 4) < -20) {
                                kT = -20;
                            }

                            var3 = true;
                        }

                        if (Input.held[Input.KEY_RIGHT]) {
                            if ((kT += 4) > 279) {
                                kT = 279;
                            }

                            var3 = true;
                        }

                        if (var3) {
                            kX = kT - bC;
                            kY = kU - bD;
                            as();
                        }
                    }
                    break;
                case 7:
                    if (++lo < 10) {
                        Input.clearPressed();
                    }

                    if (Input.pressed[Input.KEY_SOFT_LEFT]) {
                        Input.clearPressed();
                        switch (lm) {
                            case 1:
                            case 3:
                            case 4:
                                e = 6;
                                break;
                            case 2:
                                if (ln >= dd) {
                                    s(4);
                                } else {
                                    dd -= ln;
                                    ar();
                                    cornerIcons = null;
                                    ah();
                                    B();
                                    fp = fe = kP[kV] * 24;
                                    fq = ff = kQ[kV] * 24;
                                    Save.pendingSave = true;
                                    x(kV);
                                }
                                break;
                            case 5:
                            case 6:
                                cornerIcons = null;
                                e = 1;
                        }
                    }

                    if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
                        Input.clearPressed();
                        switch (lm) {
                            case 1:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            default:
                                break;
                            case 2:
                                e = 6;
                                break;
                            case 7:
                                e = 9;
                        }
                    }

                    d = true;
                    break;
                case 8:
                    if (kC > 0) {
                        --kC;
                    }

                    if (Input.pressed[Input.KEY_FIRE] || Input.pressed[Input.KEY_SOFT_LEFT]) {
                        if (W[kB] > 0) {
                            var10002 = W[kB]--;
                            kC = kD.length - 1;
                            if (kB <= 2) {
                                PlayerStats.restoreHp(kF[kB]);
                            } else {
                                PlayerStats.restoreMp(kF[kB]);
                            }
                        }

                        Input.clearPressed();
                    }

                    if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
                        Input.clearPressed();
                        Input.clearHeld();
                        e = 5;
                    } else {
                        if (Input.pressed[Input.KEY_LEFT]) {
                            Input.clearPressed();
                            if (--kB < 0) {
                                kB = 5;
                            }
                        }

                        if (Input.pressed[Input.KEY_RIGHT]) {
                            Input.clearPressed();
                            if (++kB == 6) {
                                kB = 0;
                            }
                        }
                    }
                    break;
                case 9:
                    if (kp > 0) {
                        --kp;
                    }

                    if (Input.pressed[Input.KEY_FIRE] || Input.pressed[Input.KEY_SOFT_LEFT]) {
                        Input.clearPressed();
                        if (kq == 0) {
                            if (kr == 0) {
                                if (ko < 3) {
                                    if (W[0] + W[1] + W[2] > 99) {
                                        break;
                                    }
                                } else if (W[3] + W[4] + W[5] > 99) {
                                    break;
                                }

                                if (dd >= kv[kq][ko]) {
                                    var10002 = W[ko]++;
                                    dd -= kv[kq][ko];
                                }
                            } else {
                                showPurchaseFailed();
                            }
                        } else {
                            if (y >= ko) {
                                s(7);
                                a("Không cần thiết phải mua vũ khí này vì vũ khí đang sử dụng mạnh hơn!");
                                Y = V;
                                e = 7;
                                break;
                            }

                            if (PlayerStats.level < km[ko]) {
                                s(7);
                                a("Bạn phải đạt cấp độ " + km[ko] + " mới có thể mua vũ khí này!");
                                Y = V;
                                e = 7;
                                break;
                            }

                            if (kr == 0) {
                                if (dd >= kv[kq][ko]) {
                                    dd -= kv[kq][ko];
                                    y = ko;
                                    t();
                                } else {
                                    s(7);
                                    a("Không có đủ tiền: " + kv[kq][ko] + "$. Bạn chỉ có: " + dd + "$. Hãy đi tìm thêm.");
                                    Y = V;
                                    e = 7;
                                }
                                break;
                            }

                            showPurchaseFailed();
                        }
                    }

                    if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
                        cornerIcons = null;
                        shopSwordIcons = null;
                        System.gc();
                        ah();
                        Input.clearPressed();
                        Input.clearHeld();
                        e = 1;
                        d = true;
                    } else {
                        if (Input.pressed[Input.KEY_UP]) {
                            Input.clearPressed();
                            if (kq == 1) {
                                kq = 0;
                                ++ko;
                            }
                        }

                        if (Input.pressed[Input.KEY_DOWN]) {
                            Input.clearPressed();
                            if (kq == 0) {
                                kq = 1;
                                if (--ko < 0) {
                                    ko = 0;
                                }

                                if (ko > 3) {
                                    ko = 3;
                                }
                            }
                        }

                        if (Input.pressed[Input.KEY_LEFT]) {
                            Input.clearPressed();
                            if (--ko < 0) {
                                if (kq == 0) {
                                    ko = 5;
                                } else {
                                    ko = 3;
                                }
                            }
                        }

                        if (Input.pressed[Input.KEY_RIGHT]) {
                            Input.clearPressed();
                            ++ko;
                            if (kq == 0 && ko == 6) {
                                ko = 0;
                            }

                            if (kq == 1 && ko == 4) {
                                ko = 0;
                            }
                        }
                    }
                    break;
                case 10:
                    if (lk) {
                        popupImage = null;
                        K();
                    }

                    var3 = false;
                    if (Input.pressed[Input.KEY_SOFT_LEFT]) {
                        if (ll == 0) {
                            Sound.disabled = false;
                        } else {
                            a = false;
                        }

                        var3 = true;
                    } else if (Input.pressed[Input.KEY_SOFT_RIGHT]) {
                        if (ll == 0) {
                            Sound.disabled = true;
                        } else {
                            a = true;
                        }

                        var3 = true;
                    }

                    if (var3) {
                        Input.clearPressed();
                        Input.clearHeld();
                        if (ll == 0) {
                            try {
                                popupImage = Ui.loadSmileys();
                            } catch (Exception var9) {
                            }

                            ll = 1;
                        } else {
                            lk = true;
                        }
                    }
                    break;
                case 11:
                    Z();
                    break;
                case 12:
                    Z();
                    break;
            }

            this.repaint();
            this.serviceRepaints();

            while(System.currentTimeMillis() - var1 < (long)aa) {
                Thread.yield();
            }
        }

        a();
        Midlet.instance.notifyDestroyed();
    }

    public static void o(int var0) {
        jn = 0;
        if (var0 == 0) {
            jn = 16;
            af();
            a(0, "Bộ môn Khí", 1044, 336);
            a(1, "Cô Ayumi", 1044, 347);
            a(2, "Phòng chủ nhiệm", 1212, 336);
            a(3, "Thầy Takanashi", 1212, 347);
            a(4, "Phòng", 1380, 336);
            a(5, "đào tạo", 1380, 347);
            a(6, "Phòng", 1212, 216);
            a(7, "Hiệu trưởng", 1212, 227);
            a(8, "Bộ môn Lực", 1044, 216);
            a(9, "Thầy Hiroyaki", 1044, 227);
            a(10, "Bộ môn Thuật", 1380, 216);
            a(11, "Thầy Honda", 1380, 227);
            a(12, "Kí Túc Xá", 444, 341);
            a(13, "* Khu luyện tập", 72, 341);
            a(14, "Cổng /", 1860, 317);
            a(15, "Bán HP-MP", 612, 341);
        } else if (var0 == 1) {
            jn = 2;
            af();
            a(0, "Trường /", 1356, 173);
            a(1, "* Thác nước", 48, 173);
        } else if (var0 == 2) {
            jn = 4;
            af();
            a(0, "* Lối vào", 60, 245);
            a(1, "Trường Hirosaki", 240, 197);
            a(2, "Lối ra /", 420, 245);
            a(2, "Mua đồ tiền thật", 552, 245);
            a(3, "đường hoa đào /", 1356, 221);
        } else if (var0 == 3) {
            jn = 2;
            af();
            a(0, "* Ngôi làng", 48, 173);
            a(1, "Rừng trúc /", 2808, 173);
        } else if (var0 == 4) {
            jn = 2;
            af();
            a(0, "* Vườn đào", 48, 101);
            a(1, "Rừng tối", 1704, 245);
        } else if (var0 == 5) {
            jn = 2;
            af();
            a(0, "Trường /", 420, 77);
            a(1, "Rừng tối /", 408, 1829);
        } else if (var0 == 6) {
            jn = 3;
            af();
            a(0, "* Thác nước", 72, 101);
            a(1, "Rừng tối /", 504, 53);
            a(2, "Sông Watamoro /", 5916, 197);
        } else {
            if (var0 == 99) {
                jn = 1;
                af();
                a(0, "Trường Hirosaki", 744, 173);
            }

        }
    }

    private static void a(int var0, String var1, int var2, int var3) {
        jj[var0] = var1;
        jk[var0] = var2;
        jl[var0] = var3;
        int[] var10000 = jk;
        var10000[var0] -= FontGraph.getBoldWidth(var1) >> 1;
        jm[var0] = FontGraph.getBoldWidth(var1);
    }

    private static void i(Graphics var0) {
        for(int var1 = 0; var1 < jn; ++var1) {
            if (jk[var1] + jm[var1] >= fO && jk[var1] <= fO + r && jl[var1] >= fP && jl[var1] - 14 <= fP + s) {
                FontGraph.drawBold(var0, jj[var1], jk[var1], jl[var1], 0);
            }
        }

    }

    private static void af() {
        jj = new String[jn];
        jk = new int[jn];
        jl = new int[jn];
        jm = new int[jn];
    }

    public static void p(int var0) {
        iX = 0;
        if (var0 == 2) {
            try {
                houseImages = Tile.ensureHouses(houseImages);
            } catch (Exception var2) {
            }

            iX = 5;
            ag();
            b(0, 0, 29, 13);
            b(1, 1, 34, 13);
            b(2, 2, 39, 13);
            b(3, 1, 46, 11);
            b(4, 2, 51, 11);
        }

        if (var0 == 16) {
            try {
                houseImages = Tile.ensureHouses(houseImages);
            } catch (Exception var1) {
            }

            iX = 5;
            ag();
            b(0, 0, 5, 11);
            b(1, 1, 14, 12);
            b(2, 2, 33, 10);
            b(3, 1, 53, 10);
            b(4, 0, 59, 11);
        }

    }

    private static void b(int var0, int var1, int var2, int var3) {
        iY[var0] = var1;
        iZ[var0] = var2 * GameMap.TILE_SIZE + 12;
        ja[var0] = var3 * GameMap.TILE_SIZE;
        jb[var0] = houseImages[var1].getWidth() >> 1;
        jc[var0] = houseImages[var1].getHeight();
    }

    private static void ag() {
        iY = new int[iX];
        iZ = new int[iX];
        ja = new int[iX];
        jb = new int[iX];
        jc = new int[iX];
    }

    private static void ah() {
        try {
            tilesetImage = Tile.ensureTileset(tilesetImage);
            waterfallImage = Tile.ensureWaterfall(waterfallImage);
            waterSurfaceImage = Tile.ensureWaterSurface(waterSurfaceImage);
            if (!a) {
                bushImage = Tile.ensureBush(bushImage);
            }

            System.gc();
        } catch (Exception var0) {
            Midlet.instance.shutdownApp();
        }
    }

    public static void q(int var0) {
        var0 = var0;
        GameMap.resetUsedTiles();
        if (var0 == 101) {
            var0 = 99;
        }

        if (var0 == 0) {
            GameMap.width = 80;
            GameMap.height = 19;
            jo = 1;
            js = 59 * GameMap.TILE_SIZE;
            jt = 9 * GameMap.TILE_SIZE;
            jp = 2;
            ju = 3;
            jv = 12 * GameMap.TILE_SIZE;
            jq = -1;
            jr = -1;
        } else if (var0 == 1) {
            GameMap.width = 60;
            GameMap.height = 14;
            jo = 5;
            js = 19 * GameMap.TILE_SIZE;
            jt = 5 * GameMap.TILE_SIZE;
            jp = 0;
            ju = 3;
            jv = 16 * GameMap.TILE_SIZE;
            jq = -1;
            jr = -1;
        } else if (var0 == 2) {
            GameMap.width = 60;
            GameMap.height = 16;
            jo = 0;
            js = 79 * GameMap.TILE_SIZE;
            jt = 15 * GameMap.TILE_SIZE;
            jp = 3;
            ju = 3;
            jv = 9 * GameMap.TILE_SIZE;
            jq = -1;
            jr = -1;
        } else if (var0 == 3) {
            GameMap.width = 120;
            GameMap.height = 12;
            jo = 2;
            js = 59 * GameMap.TILE_SIZE;
            jt = 11 * GameMap.TILE_SIZE;
            jp = 4;
            ju = 3;
            jv = 144;
            jq = -1;
            jr = -1;
        } else if (var0 == 4) {
            GameMap.width = 80;
            GameMap.height = 15;
            jo = 3;
            js = 119 * GameMap.TILE_SIZE;
            jt = 9 * GameMap.TILE_SIZE;
            jp = -1;
            jq = -1;
            jr = 6;
            jy = 26 * GameMap.TILE_SIZE;
            jz = 10;
        } else if (var0 == 5) {
            GameMap.width = 20;
            GameMap.height = 80;
            jo = -1;
            jp = 1;
            ju = 3;
            jv = 9 * GameMap.TILE_SIZE;
            jA = 40 * GameMap.TILE_SIZE;
            jq = 6;
            jw = 3;
            jx = 5 * GameMap.TILE_SIZE;
            jr = -1;
        } else if (var0 == 6) {
            GameMap.width = 250;
            GameMap.height = 12;
            jo = 5;
            js = 19 * GameMap.TILE_SIZE;
            jt = 78 * GameMap.TILE_SIZE;
            jp = 7;
            ju = 1 * GameMap.TILE_SIZE;
            jv = 7 * GameMap.TILE_SIZE;
            jq = -1;
            jr = -1;
        } else if (var0 == 7) {
            GameMap.width = 80;
            GameMap.height = 14;
            jo = 6;
            js = 249 * GameMap.TILE_SIZE;
            jt = 10 * GameMap.TILE_SIZE;
            jp = 97;
            ju = 1 * GameMap.TILE_SIZE;
            jv = 3 * GameMap.TILE_SIZE;
            jq = -1;
            jr = -1;
        } else if (var0 == 97) {
            GameMap.width = 25;
            GameMap.height = 13;
            jo = 7;
            js = 79 * GameMap.TILE_SIZE;
            jt = 11 * GameMap.TILE_SIZE;
            jp = 8;
            ju = 1 * GameMap.TILE_SIZE;
            jv = 12 * GameMap.TILE_SIZE;
            jq = -1;
            jr = -1;
        } else if (var0 == 8) {
            GameMap.width = 80;
            GameMap.height = 14;
            jo = 97;
            js = 23 * GameMap.TILE_SIZE;
            jt = 7 * GameMap.TILE_SIZE;
            jp = 9;
            ju = 1 * GameMap.TILE_SIZE;
            jv = 11 * GameMap.TILE_SIZE;
            jq = -1;
            jr = -1;
        } else if (var0 == 9) {
            GameMap.width = 80;
            GameMap.height = 14;
            jo = 8;
            js = 79 * GameMap.TILE_SIZE;
            jt = 9 * GameMap.TILE_SIZE;
            jp = -1;
            ju = 1 * GameMap.TILE_SIZE;
            jv = 11 * GameMap.TILE_SIZE;
            jq = -1;
            jr = 10;
            jy = 2 * GameMap.TILE_SIZE;
            jz = 0 * GameMap.TILE_SIZE;
        } else if (var0 == 10) {
            GameMap.width = 40;
            GameMap.height = 40;
            jo = -1;
            jp = 11;
            ju = 24;
            jv = 264;
            jq = -1;
            jr = -1;
        } else if (var0 == 11) {
            GameMap.width = 90;
            GameMap.height = 14;
            jo = 10;
            js = 936;
            jt = 720;
            jp = 96;
            ju = 24;
            jv = 192;
            jq = -1;
            jr = -1;
        } else if (var0 == 96) {
            GameMap.width = 25;
            GameMap.height = 14;
            jo = 11;
            js = 2112;
            jt = 288;
            jp = 12;
            ju = 24;
            jv = 192;
            jq = -1;
            jr = -1;
        } else if (var0 == 12) {
            GameMap.width = 40;
            GameMap.height = 25;
            jo = 96;
            js = 552;
            jt = 264;
            jp = 13;
            ju = 24;
            jv = 216;
            jq = -1;
            jr = -1;
        } else if (var0 == 13) {
            GameMap.width = 80;
            GameMap.height = 14;
            jo = 12;
            js = 936;
            jt = 456;
            jp = 14;
            ju = 24;
            jv = 168;
            jq = -1;
            jr = -1;
        } else if (var0 == 14) {
            GameMap.width = 60;
            GameMap.height = 16;
            jo = 13;
            js = 1896;
            jt = 264;
            jp = 15;
            ju = 24;
            jv = 120;
            jq = -1;
            jr = -1;
        } else if (var0 == 15) {
            GameMap.width = 40;
            GameMap.height = 30;
            jo = 14;
            js = 1416;
            jt = 144;
            jp = 16;
            ju = 24;
            jv = 264;
            jq = -1;
            jr = -1;
        } else if (var0 == 16) {
            GameMap.width = 70;
            GameMap.height = 15;
            jo = 15;
            js = 936;
            jt = 168;
            jp = 17;
            ju = 24;
            jv = 432;
            jq = -1;
            jr = -1;
        } else if (var0 == 17) {
            GameMap.width = 50;
            GameMap.height = 20;
            jo = 16;
            js = 1656;
            jt = 144;
            jp = 18;
            ju = 24;
            jv = 288;
            jq = -1;
            jr = -1;
        } else if (var0 == 18) {
            GameMap.width = 70;
            GameMap.height = 15;
            jo = 17;
            js = 1176;
            jt = 384;
            jp = 95;
            ju = 24;
            jv = 192;
            jq = -1;
            jr = -1;
        } else if (var0 == 95) {
            GameMap.width = 25;
            GameMap.height = 14;
            jo = 18;
            js = 1632;
            jt = 312;
            jp = -1;
            jq = -1;
            jr = -1;
        } else if (var0 == 94) {
            GameMap.width = 24;
            GameMap.height = 14;
            jo = -1;
            jp = -1;
            jq = -1;
            jr = -1;
        } else if (var0 == 99) {
            GameMap.width = 70;
            GameMap.height = 15;
            jo = -1;
            jp = 4;
            ju = 2 * GameMap.TILE_SIZE;
            jv = 5 * GameMap.TILE_SIZE;
            jq = -1;
            jr = -1;
        }

        GameMap.pixelWidth = GameMap.width * GameMap.TILE_SIZE;
        GameMap.pixelHeight = GameMap.height * GameMap.TILE_SIZE;

        try {
            GameMap.loadTiles(var0);
        } catch (Exception var2) {
            Midlet.instance.shutdownApp();
        }

        ah();
    }

    private static void j(Graphics var0) {
        for(int var1 = bw; var1 < bA; ++var1) {
            for(int var2 = bx; var2 < bB; ++var2) {
                int var3;
                if ((var3 = GameMap.tiles[var2 * GameMap.width + var1] - 1) != -1) {
                    var0.drawRegion(tilesetImage, 0, var3 * GameMap.TILE_SIZE, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                }

                if ((GameMap.flagsAt(var1, var2) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                    var0.drawRegion(waterfallImage, 0, 24 * (x % 4), 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                } else if ((GameMap.flagsAt(var1, var2) & TileFlags.WATER) == TileFlags.WATER) {
                    if ((GameMap.flagsAt(var1, var2 - 1) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                        var0.drawRegion(waterfallImage, 0, 24 * (x % 4), 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(var1, var2 - 1) & TileFlags.DIGGABLE) == TileFlags.DIGGABLE) {
                        var0.drawRegion(tilesetImage, 0, 504, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    }

                    var0.drawRegion(waterSurfaceImage, 0, (x % 8 >> 2) * 24, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                }

                if ((GameMap.flagsAt(var1, var2) & TileFlags.DEEP_WATER) == TileFlags.DEEP_WATER) {
                    if ((GameMap.flagsAt(var1, var2 - 1) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                        var0.drawRegion(waterfallImage, 0, 24 * (x % 4), 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(var1, var2 - 1) & TileFlags.DIGGABLE) == TileFlags.DIGGABLE) {
                        var0.drawRegion(tilesetImage, 0, 504, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    }

                    var0.drawRegion(tilesetImage, 0, (GameMap.tiles[var2 * GameMap.width + var1] - 1) * GameMap.TILE_SIZE, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                }
            }
        }

    }

    private static void k(Graphics var0) {
        for(int var1 = bw; var1 < bA; ++var1) {
            for(int var2 = bx; var2 < bB; ++var2) {
                int var3 = GameMap.tiles[var2 * GameMap.width + var1] - 1;
                if ((GameMap.flagsAt(var1, var2) & TileFlags.PARALLAX) == TileFlags.PARALLAX) {
                    jg = (jf = var1 * GameMap.TILE_SIZE - fO) - bC;
                    jh = (ji = (GameMap.TILE_SIZE - 2) * jg / GameMap.TILE_SIZE) + bC;
                    var0.drawRegion(tilesetImage, 0, var3 * GameMap.TILE_SIZE, 24, 24, 0, jh + fO, var2 * GameMap.TILE_SIZE, 0);
                } else if ((GameMap.flagsAt(var1, var2) & TileFlags.FOREGROUND_BUSH) != TileFlags.FOREGROUND_BUSH && (GameMap.flagsAt(var1, var2) & TileFlags.FOREGROUND) != TileFlags.FOREGROUND) {
                    if ((GameMap.flagsAt(var1, var2) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                        var0.drawRegion(waterfallImage, 0, 24 * (x % 4), 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(var1, var2) & TileFlags.WATER) != TileFlags.WATER && (GameMap.flagsAt(var1, var2) & TileFlags.DEEP_WATER) != TileFlags.DEEP_WATER) {
                        if ((GameMap.flagsAt(var1, var2) & TileFlags.BRIDGE_PRESSED) == TileFlags.BRIDGE_PRESSED) {
                            var0.drawRegion(tilesetImage, 0, var3 * GameMap.TILE_SIZE, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE + 1, 0);
                        } else if (var3 != -1) {
                            var0.drawRegion(tilesetImage, 0, var3 * GameMap.TILE_SIZE, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                        }
                    } else if ((GameMap.flagsAt(var1, var2 - 1) & TileFlags.WATERFALL) == TileFlags.WATERFALL) {
                        var0.drawRegion(waterfallImage, 0, 24 * (x % 4), 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(var1, var2 - 1) & TileFlags.DIGGABLE) == TileFlags.DIGGABLE) {
                        var0.drawRegion(tilesetImage, 0, 504, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    }
                }
            }
        }

    }

    private static void l(Graphics var0) {
        if (!a) {
            for(int var1 = bw; var1 < bA; ++var1) {
                for(int var2 = bx; var2 < bB; ++var2) {
                    if ((GameMap.flagsAt(var1, var2) & TileFlags.WATER) == TileFlags.WATER) {
                        var0.drawRegion(waterSurfaceImage, 0, (x % 8 >> 2) * 24, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    } else if ((GameMap.flagsAt(var1, var2) & TileFlags.FOREGROUND_BUSH) == TileFlags.FOREGROUND_BUSH) {
                        jg = (jf = var1 * GameMap.TILE_SIZE - fO) - bC;
                        jh = (ji = (GameMap.TILE_SIZE - 1) * jg / GameMap.TILE_SIZE) + bC;
                        var0.drawImage(bushImage, jh + fO - 2 + (var1 + var2) % 5, var2 * GameMap.TILE_SIZE - 3 + var2 % 5, 0);
                    }

                    if ((GameMap.flagsAt(var1, var2) & TileFlags.FOREGROUND) == TileFlags.FOREGROUND) {
                        var0.drawRegion(tilesetImage, 0, (GameMap.tiles[var2 * GameMap.width + var1] - 1) * GameMap.TILE_SIZE, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE + 2, 0);
                    }

                    if ((GameMap.flagsAt(var1, var2) & TileFlags.DEEP_WATER) == TileFlags.DEEP_WATER) {
                        var0.drawRegion(tilesetImage, 0, (GameMap.tiles[var2 * GameMap.width + var1] - 1) * GameMap.TILE_SIZE, 24, 24, 0, var1 * GameMap.TILE_SIZE, var2 * GameMap.TILE_SIZE, 0);
                    }
                }
            }

        }
    }

    private static int H(int var0) {
        return var0 / GameMap.TILE_SIZE * GameMap.TILE_SIZE;
    }

    private static int I(int var0) {
        return var0 / GameMap.TILE_SIZE * GameMap.TILE_SIZE;
    }

    private static void ai() {
        try {
            logoImage = Ui.loadLogo();
        } catch (Exception var0) {
        }
    }

    private static void aj() {
        T = 0;
        S = 0;
        jB = 0;
    }

    private static void J(int var0) {
        if (kb[var0] > 30) {
            int var2 = jX[var0] > 0 ? jJ[var0] : jJ[var0] - kc[var0];
            int var3 = jX[var0] >= 0 ? jJ[var0] + kc[var0] : jJ[var0];
            int var4 = jK[var0] - kd[var0];
            int var1 = jK[var0] + kd[var0];
            if (B == 6 ? false : (fw ? false : (B == 14 ? false : (fe < var2 ? false : (fe > var3 ? false : (ff < var4 ? false : ff <= var1)))))) {
                kb[var0] = 0;
                jO[var0] = 4;
                jT[var0] = 0;
            }
        }

    }

    private static void K(int var0) {
        if (kb[var0] > 30) {
            int var2 = jX[var0] > 0 ? jJ[var0] : jJ[var0] - kc[var0];
            int var3 = jX[var0] >= 0 ? jJ[var0] + kc[var0] : jJ[var0];
            int var4 = jK[var0] - kd[var0];
            int var1 = jK[var0] + kd[var0];
            if (gr == 14 ? false : (gn < var2 ? false : (gn > var3 ? false : (go < var4 ? false : go <= var1)))) {
                kb[var0] = 0;
                jO[var0] = 4;
                jT[var0] = 0;
                kh[var0] = true;
            }
        }

    }

    private static boolean k(int var0, int var1, int var2) {
        if (jO[var0] == 2 || jO[var0] == 3 || jO[var0] == 4 || jO[var0] == 9) {
            int[] var10000 = jY;
            var10000[var0] -= var1;
            if (jY[var0] <= 0) {
                jY[var0] = 0;
                jT[var0] = -5;
                jU[var0] = -(var2 << 3);
                jV[var0] = 0;
                jN[var0] = 0;
                jO[var0] = 1;
                L(var0);
                return true;
            }

            if (jO[var0] == 9) {
                var10000 = jT;
                var10000[var0] -= 7;
                jX[var0] = var2;
            } else {
                jT[var0] = 0;
                jX[var0] = var2;
                jO[var0] = 6;
                jW[var0] = 3;
            }
        }

        return false;
    }

    private static void L(int var0) {
        gainExp(ap[jI[var0]]);
        int var1;
        if ((var1 = x % 20) > 15) {
            a(jJ[var0], jK[var0], -(jX[var0] << 3), -5, ka[var0] << 2, (x + 1) % 2);
        } else if (var1 == 15) {
            a(jJ[var0], jK[var0], -(jX[var0] << 2), -4, (kf[var0] << 3) + x % 10);
        } else {
            var1 = ((jI[var0] << 2) / 10 + 1) * 10;
            a(jJ[var0], jK[var0], -(jX[var0] << 1), -6, var1);
            a(jJ[var0], jK[var0], -(jX[var0] << 4), -3, var1);
        }

        if (P != -1 && lD[P] == jI[var0]) {
            ++fC;
            b(al[lD[P]] + ": " + fC + "#" + lE[P]);
        }

        if (jI[var0] == fy - 100 && x % 10 < fz) {
            e(jJ[var0], jK[var0]);
            f(jJ[var0], jK[var0] - 28);
        }

    }

    public static final void r(int var0) {
        jG = 0;
        boolean var1;
        int var2;
        boolean var4;
        switch (var0) {
            case 0:
                kl = new int[]{7};
                al();
                ki[0] = new int[]{68, 71, 74, 64};
                kj[0] = new int[]{15, 15, 15, 17};
                am();
                M(2);
                q(0, 2);
                break;
            case 1:
                kl = new int[]{1, 7, 0};
                al();
                ki[0] = new int[]{44, 40, 42, 39, 41, 39, 34};
                kj[0] = new int[]{10, 11, 9, 9, 7, 5, 9};
                ki[1] = new int[]{48, 51};
                kj[1] = new int[]{9, 9};
                ki[2] = new int[]{8, 3, 24, 25, 26};
                kj[2] = new int[]{9, 9, 10, 8, 6};
                am();
                M(7);
                u(0, 3);
                q(1, 1);
                s(2, 3);
                break;
            case 2:
                kl = new int[]{1, 6};
                al();
                ki[0] = new int[]{32, 41, 44, 48};
                kj[0] = new int[]{13, 13, 11, 11};
                ki[1] = new int[]{41, 47, 57, 36};
                kj[1] = new int[]{9, 7, 7, 8};
                am();
                M(4);
                u(0, 2);
                o(1, 2);
                break;
            case 3:
                kl = new int[]{1, 6, 0};
                al();
                ki[0] = new int[]{6, 10, 17, 26, 42, 83, 96};
                kj[0] = new int[]{9, 10, 10, 10, 10, 10, 11};
                ki[1] = new int[]{10, 19, 24, 32, 41, 49, 59, 68, 80, 87, 110};
                kj[1] = new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 7};
                ki[2] = new int[]{17, 24, 28, 35, 42, 49, 59, 69, 78, 86, 97, 107, 113};
                kj[2] = new int[]{10, 10, 8, 8, 10, 9, 9, 9, 10, 10, 8, 8, 9};
                am();
                M(20);
                u(0, 4);
                o(1, 8);
                s(2, 8);
                break;
            case 4:
                kl = new int[]{6, 0, 4, 9, 1};
                al();
                ki[0] = new int[]{29, 26, 37, 43, 58, 67, 11, 74, 57};
                kj[0] = new int[]{11, 10, 11, 10, 8, 3, 8, 7, 7};
                ki[1] = new int[]{21, 39, 37, 45, 25, 62, 58};
                kj[1] = new int[]{12, 12, 12, 8, 12, 9, 5};
                ki[2] = new int[]{29, 33, 68, 42, 57};
                kj[2] = new int[]{13, 12, 7, 12, 9};
                ki[3] = new int[]{55, 59, 53};
                kj[3] = new int[]{5, 5, 5};
                ki[4] = new int[]{19, 11};
                kj[4] = new int[]{12, 11};
                am();
                M(20);
                o(0, 6);
                s(1, 6);
                r(2, 4);
                l(3, 2);
                u(4, 2);
                break;
            case 5:
                kl = new int[]{0, 3, 4};
                al();
                ki[0] = new int[]{7, 14, 7, 14, 7, 6, 15, 9};
                kj[0] = new int[]{9, 13, 18, 28, 29, 35, 42, 70};
                ki[1] = new int[]{6, 4, 14, 4, 16, 7, 15, 11};
                kj[1] = new int[]{14, 26, 33, 44, 47, 62, 65, 78};
                ki[2] = new int[]{14, 14, 7, 12, 8, 14, 8};
                kj[2] = new int[]{23, 39, 56, 62, 66, 70, 78};
                am();
                M(20);
                s(0, 7);
                var1 = true;
                var4 = true;

                for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
                }

                if (var2 != jG) {
                    for(var0 = var2; var0 < var2 + 7; ++var0) {
                        kg[var0] = 1;
                        jI[var0] = 3;
                        kc[var0] = 30;
                        kd[var0] = 10;
                        jL[var0] = 27;
                        jM[var0] = 15;
                        jX[var0] = 1;
                        jO[var0] = 5;
                        jT[var0] = var0 << 3;
                        jY[var0] = jZ[var0] = am[jI[var0]];
                        ka[var0] = an[jI[var0]];
                        kf[var0] = ao[jI[var0]];
                        kb[var0] = 0;
                    }
                }

                r(2, 6);
                break;
            case 6:
                kl = new int[]{9, 2, 5};
                al();
                ki[0] = new int[]{42, 47, 72, 82, 85, 93, 104, 130, 139, 151, 165, 182, 189, 200, 207, 214, 225, 225, 237};
                kj[0] = new int[]{10, 8, 10, 8, 6, 6, 6, 9, 9, 7, 4, 7, 9, 10, 10, 8, 4, 10, 8};
                ki[1] = new int[]{39, 40, 41, 72, 73, 74, 132, 106, 107, 130, 131, 142, 149, 157, 168, 182, 183, 194, 214, 215, 216, 236, 39, 40, 56, 57};
                kj[1] = new int[]{5, 5, 6, 6, 4, 3, 2, 3, 4, 5, 5, 5, 2, 1, 3, 4, 5, 6, 6, 5, 4, 5, 6, 6, 6, 6};
                ki[2] = new int[]{45, 64, 83, 101, 119, 147, 173, 220};
                kj[2] = new int[]{6, 8, 4, 3, 6, 7, 5, 4};
                am();
                M(26);
                l(0, 5);
                j(1, 18);
                var1 = true;
                var4 = true;

                for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
                }

                if (var2 != jG) {
                    for(var0 = var2; var0 < var2 + 3; ++var0) {
                        kg[var0] = 2;
                        jI[var0] = 5;
                        kc[var0] = 7;
                        kd[var0] = 10;
                        jL[var0] = 16;
                        jM[var0] = 15;
                        jX[var0] = 0;
                        jO[var0] = 5;
                        jT[var0] = var0 << 3;
                        jY[var0] = jZ[var0] = am[jI[var0]];
                        ka[var0] = an[jI[var0]];
                        kf[var0] = ao[jI[var0]];
                        kb[var0] = 100;
                    }
                }
                break;
            case 7:
                kl = new int[]{9, 8, 10};
                al();
                ki[0] = new int[]{38, 39, 47, 52, 56, 61, 66, 73, 71};
                kj[0] = new int[]{5, 5, 3, 8, 4, 4, 4, 4, 8};
                ki[1] = new int[]{16, 23, 26, 29, 32, 35, 38, 41, 44, 47, 56, 59, 61, 64, 66};
                kj[1] = new int[]{11, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
                ki[2] = new int[]{36, 42, 45, 487, 56, 60, 64};
                kj[2] = new int[]{11, 11, 11, 11, 11, 11, 11};
                am();
                M(23);
                l(0, 7);
                m(1, 10);
                n(2, 6);
                break;
            case 8:
                kl = new int[]{11, 8, 10, 2};
                al();
                ki[0] = new int[]{12, 29, 6, 12, 24, 18, 37, 50, 65, 72, 47};
                kj[0] = new int[]{10, 8, 12, 12, 11, 12, 10, 8, 8, 7, 7};
                ki[1] = new int[]{29, 45, 48, 70, 73};
                kj[1] = new int[]{12, 12, 12, 12, 12};
                ki[2] = new int[]{46, 47, 71, 72};
                kj[2] = new int[]{13, 13, 13, 13};
                ki[3] = new int[]{25, 7, 69, 69, 50, 51};
                kj[3] = new int[]{25, 8, 6, 7, 5, 5};
                am();
                M(22);
                var1 = true;
                var4 = false;

                for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
                }

                if (var2 != jG) {
                    for(var0 = var2; var0 < var2 + 10; ++var0) {
                        kg[var0] = 0;
                        jI[var0] = 11;
                        kc[var0] = 32;
                        kd[var0] = 10;
                        jL[var0] = 27;
                        jM[var0] = 15;
                        jX[var0] = 1;
                        jO[var0] = 5;
                        jT[var0] = jH - var0;
                        jY[var0] = jZ[var0] = am[jI[var0]];
                        ka[var0] = an[jI[var0]];
                        kf[var0] = ao[jI[var0]];
                        kb[var0] = 0;
                    }
                }

                m(1, 4);
                n(2, 3);
                j(3, 5);
                break;
            case 9:
                kl = new int[]{13};
                al();
                ki[0] = new int[]{10, 37, 23, 65};
                kj[0] = new int[]{7, 6, 6, 4};
                am();
                M(3);
                k(0, 3);
                break;
            case 10:
                kl = new int[]{13};
                al();
                ki[0] = new int[]{31, 12, 25, 12};
                kj[0] = new int[]{19, 23, 23, 6};
                am();
                M(3);
                k(0, 3);
                break;
            case 11:
                kl = new int[]{14, 8};
                al();
                ki[0] = new int[]{15, 16, 17, 31, 32, 33, 68, 69, 72};
                kj[0] = new int[]{12, 12, 12, 12, 8, 8, 8, 8, 8};
                ki[1] = new int[]{47, 48, 50, 49};
                kj[1] = new int[]{12, 12, 12, 12};
                am();
                M(10);
                var1 = true;
                var4 = false;

                for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
                }

                if (var2 != jG) {
                    for(var0 = var2; var0 < var2 + 7; ++var0) {
                        kg[var0] = 0;
                        jI[var0] = 14;
                        kc[var0] = 7;
                        kd[var0] = 10;
                        jL[var0] = 16;
                        jM[var0] = 15;
                        jX[var0] = 1;
                        jO[var0] = 5;
                        jT[var0] = var0 << 3;
                        jY[var0] = jZ[var0] = am[jI[var0]];
                        ka[var0] = an[jI[var0]];
                        kf[var0] = ao[jI[var0]];
                        kb[var0] = 500;
                    }
                }

                m(1, 3);
                break;
            case 12:
                kl = new int[]{15, 16, 10};
                al();
                ki[0] = new int[]{11, 18, 20, 9, 9, 17, 18, 22, 31};
                kj[0] = new int[]{7, 5, 10, 14, 17, 15, 20, 19, 21};
                ki[1] = new int[]{5, 10, 5, 10, 14, 21, 16, 37};
                kj[1] = new int[]{6, 12, 15, 19, 16, 17, 9, 17};
                ki[2] = new int[]{25, 26, 27, 34};
                kj[2] = new int[]{23, 23, 23, 23};
                am();
                M(19);
                t(0, 7);
                p(1, 8);
                n(2, 4);
                break;
            case 13:
                kl = new int[]{18, 15, 13};
                al();
                ki[0] = new int[]{6, 12, 30, 34, 34, 43, 54, 53, 70, 76};
                kj[0] = new int[]{9, 11, 11, 10, 5, 5, 5, 11, 11, 11};
                ki[1] = new int[]{11, 16, 18, 29, 50, 38, 48, 58, 62, 74};
                kj[1] = new int[]{11, 9, 7, 5, 11, 5, 5, 5, 5, 11};
                ki[2] = new int[]{22, 41, 42, 61};
                kj[2] = new int[]{9, 8, 2, 8};
                am();
                M(18);
                v(0, 8);
                t(1, 6);
                k(2, 4);
                break;
            case 14:
                kl = new int[]{12, 17, 16};
                al();
                ki[0] = new int[]{7, 12, 16, 25, 28, 45, 50};
                kj[0] = new int[]{4, 7, 11, 14, 8, 14, 9};
                ki[1] = new int[]{14, 18, 29, 28, 29, 53, 47, 51};
                kj[1] = new int[]{7, 11, 12, 8, 4, 14, 9, 4};
                ki[2] = new int[]{4, 41, 26, 26, 34, 41, 49, 56};
                kj[2] = new int[]{4, 9, 11, 2, 2, 5, 7, 2};
                am();
                M(19);
                y(0, 6);
                x(1, 7);
                p(2, 6);
                break;
            case 15:
                kl = new int[]{19, 18, 17};
                al();
                ki[0] = new int[]{22, 7, 19, 32};
                kj[0] = new int[]{28, 25, 28, 14};
                ki[1] = new int[]{3, 13, 31, 35, 24};
                kj[1] = new int[]{6, 25, 25, 21, 27};
                ki[2] = new int[]{35, 29, 24, 17, 13, 2};
                kj[2] = new int[]{8, 11, 9, 7, 7, 17};
                am();
                M(15);
                w(0, 4);
                v(1, 5);
                x(2, 6);
                break;
            case 16:
                kl = new int[]{19, 13};
                al();
                ki[0] = new int[]{15, 33, 53};
                kj[0] = new int[]{12, 10, 10};
                ki[1] = new int[]{20, 39, 59};
                kj[1] = new int[]{10, 8, 8};
                am();
                M(6);
                w(0, 3);
                k(1, 3);
                break;
            case 17:
                kl = new int[]{12};
                al();
                ki[0] = new int[]{8, 14, 39, 45};
                kj[0] = new int[]{12, 12, 9, 16};
                am();
                M(4);
                y(0, 4);
                break;
            case 95:
                kl = new int[]{13};
                al();
                ki[0] = new int[]{7, 10, 14, 18};
                kj[0] = new int[]{8, 8, 8, 8};
                am();
                M(4);
                k(0, 4);
        }

        if (jG > 0) {
            monsterSprites = new Image[kl.length][][];

            for(var0 = 0; var0 < kl.length; ++var0) {
                monsterSprites[var0] = Mob.loadMonster(kl[var0]);
            }
        }

    }

    private static void ak() {
        for(int var0 = 0; var0 < jG; ++var0) {
            int var10002;
            if (kb[var0] <= 30) {
                var10002 = kb[var0]++;
            }

            if (jN[var0] > 0) {
                var10002 = jN[var0]--;
            }

            int[] var10000;
            switch (jO[var0]) {
                case 1:
                    var10002 = jT[var0]++;
                    var10000 = jK;
                    var10000[var0] += jT[var0];
                    if (x % 2 == 0) {
                        if (jU[var0] > 1) {
                            var10002 = jU[var0]--;
                        } else if (jU[var0] < -1) {
                            var10002 = jU[var0]++;
                        }
                    }

                    var10000 = jJ;
                    var10000[var0] += jU[var0];
                    jW[var0] = 2;
                    if (jK[var0] < GameMap.pixelHeight && jJ[var0] >= 0 && jJ[var0] < GameMap.pixelWidth) {
                        if ((jV[var0] == 0 || jV[var0] < 3 && jT[var0] > 7) && (GameMap.flagsAtPixel(jJ[var0], jK[var0]) & TileFlags.SOLID) == TileFlags.SOLID) {
                            jT[var0] = jT[var0] > 4 ? -4 : -jT[var0];
                            var10002 = jV[var0]++;
                        }

                        if (GameMap.hasFlag(jJ[var0], jK[var0], TileFlags.WATER) && jK[var0] % GameMap.TILE_SIZE > 8) {
                            g(jJ[var0], H(jK[var0]) + 8);
                        }
                        break;
                    }

                    jT[var0] = -300;
                    jO[var0] = 5;
                    if (U) {
                        jO[var0] = 8;
                    }

                    kk[kg[var0]][ke[var0]] = false;
                    break;
                case 2:
                    switch (jI[var0]) {
                        case 0:
                            if (x % 4 == 0) {
                                var10002 = jW[var0]++;
                                if (jW[var0] > 1) {
                                    jW[var0] = 0;
                                }
                            }

                            var10000 = jJ;
                            var10000[var0] += jX[var0];
                            if (jJ[var0] > jQ[var0]) {
                                jX[var0] = -1;
                            } else if (jJ[var0] < jP[var0]) {
                                jX[var0] = 1;
                            }

                            J(var0);
                            if (x % 9 == 0) {
                                K(var0);
                            }
                            continue;
                        case 1:
                            U(var0);
                            continue;
                        case 2:
                            var10000 = jJ;
                            var10000[var0] += jR[var0];
                            var10000 = jK;
                            var10000[var0] += jS[var0];
                            if (jJ[var0] - jP[var0] > 40) {
                                jR[var0] = -(x % 3 + 1);
                            }

                            if (jJ[var0] - jP[var0] < -40) {
                                jR[var0] = x % 3 + 1;
                            }

                            if (jK[var0] - jQ[var0] > 40) {
                                jS[var0] = -(x % 3 + 1);
                            }

                            if (jK[var0] - jQ[var0] < -40) {
                                jS[var0] = x % 3 + 1;
                            }

                            if (x % 15 == 0) {
                                jR[var0] = x % 4 - 1;
                                jS[var0] = (x >> 1) % 4 - 1;
                            }

                            if (x % 2 == 0) {
                                var10002 = jW[var0]++;
                                if (jW[var0] > 1) {
                                    jW[var0] = 0;
                                }

                                if (jW[var0] == 0) {
                                    var10000 = jK;
                                    var10000[var0] -= 3;
                                } else {
                                    var10000 = jK;
                                    var10000[var0] += 2;
                                }
                            }

                            J(var0);
                            continue;
                        case 3:
                            var10000 = jJ;
                            var10000[var0] += jT[var0];
                            if (jT[var0] > 1) {
                                var10002 = jT[var0]--;
                            } else if (jT[var0] < -1) {
                                var10002 = jT[var0]++;
                            }

                            var10000 = jK;
                            var10000[var0] += jU[var0];
                            var10002 = jU[var0]++;
                            if (jU[var0] > 4) {
                                jU[var0] = -4;
                                jT[var0] = jX[var0] * 3;
                            }

                            if (a(jU[var0]) == 3) {
                                if (jJ[var0] > jQ[var0]) {
                                    jX[var0] = -1;
                                } else if (jJ[var0] < jP[var0]) {
                                    jX[var0] = 1;
                                }

                                jW[var0] = 0;
                            } else {
                                jW[var0] = 1;
                            }

                            J(var0);
                            if (x % 9 == 0) {
                                K(var0);
                            }
                            continue;
                        case 4:
                            var10000 = jJ;
                            var10000[var0] += jV[var0];
                            if (jV[var0] > 1) {
                                var10002 = jV[var0]--;
                            } else if (jV[var0] < -1) {
                                var10002 = jV[var0]++;
                            }

                            var10000 = jK;
                            var10000[var0] += jU[var0];
                            var10002 = jU[var0]++;
                            if (jU[var0] > 3) {
                                jU[var0] = -3;
                                jV[var0] = jX[var0] << 2;
                                jT[var0] = 0;
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 3;
                            }

                            if (jU[var0] == -2) {
                                if (jJ[var0] > jQ[var0]) {
                                    jX[var0] = -1;
                                } else if (jJ[var0] < jP[var0]) {
                                    jX[var0] = 1;
                                }

                                jV[var0] = jX[var0] * 6;
                                jW[var0] = 0;
                            } else {
                                jW[var0] = 1;
                            }

                            J(var0);
                            if (x % 9 == 0) {
                                K(var0);
                            }
                            continue;
                        case 5:
                            if (x % 4 == 0) {
                                var10002 = jW[var0]++;
                                if (jW[var0] > 1) {
                                    jW[var0] = 0;
                                }

                                var10000 = jK;
                                var10000[var0] += jT[var0];
                            }

                            if (jK[var0] > jQ[var0]) {
                                jT[var0] = -1;
                            } else if (jK[var0] < jP[var0]) {
                                jT[var0] = 1;
                            }

                            J(var0);
                            continue;
                        case 6:
                            P(var0);
                        case 7:
                        default:
                            continue;
                        case 8:
                            var10002 = jW[var0]++;
                            if (jW[var0] > 1) {
                                jW[var0] = 0;
                            }

                            var10000 = jJ;
                            var10000[var0] += x % 5 * jX[var0];
                            if (jJ[var0] > jQ[var0]) {
                                jX[var0] = -1;
                            } else if (jJ[var0] < jP[var0]) {
                                jX[var0] = 1;
                            }

                            J(var0);
                            continue;
                        case 9:
                            Q(var0);
                            continue;
                        case 10:
                            var10002 = jW[var0]++;
                            if (jW[var0] > 1) {
                                jW[var0] = 0;
                            }

                            var10000 = jJ;
                            var10000[var0] += 5 * jX[var0];
                            if (jJ[var0] > jQ[var0]) {
                                jX[var0] = -1;
                            } else if (jJ[var0] < jP[var0]) {
                                jX[var0] = 1;
                            }

                            J(var0);
                            continue;
                        case 11:
                            if (x % 4 == 0) {
                                var10002 = jW[var0]++;
                                jT[var0] = 0;
                                if (jW[var0] > 1) {
                                    jW[var0] = 0;
                                    jT[var0] = 1;
                                }
                            }

                            var10000 = jJ;
                            var10000[var0] += jT[var0] * jX[var0];
                            if (jJ[var0] > jQ[var0]) {
                                jX[var0] = -1;
                            } else if (jJ[var0] < jP[var0]) {
                                jX[var0] = 1;
                            }

                            J(var0);
                            if (x % 9 == 0) {
                                K(var0);
                            }
                            continue;
                        case 12:
                            Q(var0);
                            continue;
                        case 13:
                            var10000 = jJ;
                            var10000[var0] += jR[var0];
                            if (jJ[var0] - jP[var0] > 100) {
                                jX[var0] = -1;
                                jR[var0] = jX[var0] * (x % 4 + 1);
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 2;
                            } else if (jJ[var0] - jP[var0] < -100) {
                                jX[var0] = 1;
                                jR[var0] = jX[var0] * (x % 4 + 1);
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 2;
                            }

                            if (jK[var0] - jQ[var0] > 20) {
                                jS[var0] = -(x % 4 + 1);
                            } else if (jK[var0] - jQ[var0] < -20) {
                                jS[var0] = x % 4 + 1;
                            } else {
                                jS[var0] = 0;
                            }

                            var10000 = jK;
                            var10000[var0] += jS[var0];
                            if (x % 2 == 0) {
                                var10002 = jW[var0]++;
                                if (jW[var0] > 1) {
                                    jW[var0] = 0;
                                    var10000 = jK;
                                    var10000[var0] -= 12;
                                } else {
                                    var10000 = jK;
                                    var10000[var0] += 12;
                                }
                            }

                            J(var0);
                            continue;
                        case 14:
                            var10002 = jW[var0]++;
                            if (jW[var0] > 1) {
                                jW[var0] = 0;
                            }

                            if (x % 2 == 0) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 2;
                            } else {
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 1;
                            }

                            if (jJ[var0] > jQ[var0]) {
                                jX[var0] = -1;
                            } else if (jJ[var0] < jP[var0]) {
                                jX[var0] = 1;
                            }

                            J(var0);
                            if (x % 5 == 0) {
                                K(var0);
                            }
                            continue;
                        case 15:
                            var10000 = jJ;
                            var10000[var0] += jT[var0];
                            if (jT[var0] > 1) {
                                var10002 = jT[var0]--;
                            } else if (jT[var0] < -1) {
                                var10002 = jT[var0]++;
                            }

                            var10000 = jK;
                            var10000[var0] += jU[var0];
                            var10002 = jU[var0]++;
                            if (jU[var0] > 4) {
                                jU[var0] = -4;
                                jT[var0] = jX[var0] * 3;
                            }

                            if (a(jU[var0]) == 3) {
                                if (jJ[var0] > jQ[var0]) {
                                    jX[var0] = -1;
                                } else if (jJ[var0] < jP[var0]) {
                                    jX[var0] = 1;
                                }

                                jW[var0] = 0;
                            } else {
                                jW[var0] = 1;
                            }

                            J(var0);
                            if (x % 9 == 0) {
                                K(var0);
                            }
                            continue;
                        case 16:
                            P(var0);
                            continue;
                        case 17:
                            U(var0);
                            continue;
                        case 18:
                        case 19:
                            if (x % 4 == 0) {
                                var10002 = jW[var0]++;
                                if (jW[var0] > 1) {
                                    jW[var0] = 0;
                                }
                            }

                            if (jJ[var0] > fe) {
                                jX[var0] = -1;
                            } else {
                                jX[var0] = 1;
                            }

                            J(var0);
                            if (x % 9 == 0) {
                                K(var0);
                            }
                            continue;
                    }
                case 3:
                    var10002 = jT[var0]++;
                    if (jT[var0] >= 35) {
                        jT[var0] = 0;
                        jO[var0] = 2;
                    }

                    jW[var0] = 0;
                    J(var0);
                    break;
                case 4:
                    if (B == 14 || fw) {
                        jW[var0] = 0;
                        jO[var0] = 2;
                        jT[var0] = 0;
                        jU[var0] = 0;
                    }

                    switch (jI[var0]) {
                        case 0:
                            var10002 = jT[var0]++;
                            if (jT[var0] == 2) {
                                if (kh[var0]) {
                                    kh[var0] = false;
                                    b(ka[var0], jX[var0]);
                                } else {
                                    a(ka[var0], jX[var0]);
                                }
                            }

                            if ((jT[var0] >> 2) % 2 == 0) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0];
                                jW[var0] = 3;
                            } else {
                                jW[var0] = 0;
                                var10000 = jJ;
                                var10000[var0] -= jX[var0];
                            }

                            if (jT[var0] == 12) {
                                jO[var0] = 3;
                                jT[var0] = 0;
                            }
                            continue;
                        case 1:
                            a(ka[var0], -fm);
                            jO[var0] = 3;
                            jT[var0] = 0;
                            jW[var0] = 2;
                            continue;
                        case 2:
                            if (jJ[var0] < fe) {
                                jR[var0] = 3 + x % 4;
                            } else if (jJ[var0] > fe) {
                                jR[var0] = -(3 + x % 4);
                            }

                            if (jK[var0] < ff) {
                                jS[var0] = x % 3;
                            } else if (jK[var0] > ff) {
                                jS[var0] = -x % 3;
                            }

                            var10000 = jJ;
                            var10000[var0] += jR[var0];
                            var10000 = jK;
                            var10000[var0] += jS[var0];
                            if (x % 2 == 0) {
                                var10002 = jW[var0]++;
                                if (jW[var0] > 1) {
                                    jW[var0] = 0;
                                }
                            }

                            if (a(jJ[var0] - fe) < 5 && a(jK[var0] - ff) < 5) {
                                kb[var0] = 0;
                                a(ka[var0], -fm);
                                jT[var0] = 0;
                                jO[var0] = 2;
                            }

                            if (a(jJ[var0] - fe) > 160 || a(jK[var0] - ff) > 160) {
                                jO[var0] = 2;
                                jT[var0] = 0;
                                jR[var0] = jS[var0] = 0;
                                kb[var0] = 0;
                            }
                            continue;
                        case 3:
                            var10002 = jT[var0]++;
                            if (jT[var0] == 3) {
                                if (kh[var0]) {
                                    kh[var0] = false;
                                    b(ka[var0], jX[var0]);
                                } else {
                                    a(ka[var0], jX[var0]);
                                }
                            }

                            if ((jT[var0] >> 2) % 2 == 0) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0];
                                jW[var0] = 0;
                            } else {
                                jW[var0] = 3;
                                var10000 = jJ;
                                var10000[var0] -= jX[var0];
                            }

                            if (jT[var0] == 12) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 3;
                                jU[var0] = -4;
                                jT[var0] = 0;
                            }
                            continue;
                        case 4:
                            var10002 = jT[var0]++;
                            if (jT[var0] == 3) {
                                if (kh[var0]) {
                                    kh[var0] = false;
                                    b(ka[var0], jX[var0]);
                                } else {
                                    a(ka[var0], jX[var0]);
                                }
                            }

                            if ((jT[var0] >> 2) % 2 == 0) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0];
                                jW[var0] = 0;
                            } else {
                                jW[var0] = 1;
                                var10000 = jJ;
                                var10000[var0] -= jX[var0];
                            }

                            if (jT[var0] == 12) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 3;
                                jU[var0] = -4;
                                jT[var0] = 0;
                            }
                            continue;
                        case 5:
                            a(ka[var0], -fm);
                            jO[var0] = 2;
                            jT[var0] = 1;
                            continue;
                        case 6:
                            N(var0);
                        case 7:
                        default:
                            continue;
                        case 8:
                            var10002 = jT[var0]++;
                            if (jT[var0] <= 10) {
                                if (jK[var0] > ff) {
                                    var10000 = jK;
                                    var10000[var0] -= 4;
                                }

                                if (jJ[var0] < fe) {
                                    var10000 = jJ;
                                    var10000[var0] += 2;
                                } else if (jJ[var0] > fe) {
                                    var10000 = jJ;
                                    var10000[var0] -= 2;
                                }

                                if (a(jJ[var0] - fe) < 16 && a(jK[var0] - (ff - 16)) < 20) {
                                    a(ka[var0], -fm);
                                    jT[var0] = 10;
                                }
                            }

                            if (jT[var0] == 10) {
                                jU[var0] = 1;
                            }

                            if (jT[var0] > 10) {
                                var10000 = jK;
                                var10000[var0] += jU[var0];
                                var10002 = jU[var0]++;
                                if (jK[var0] > kj[kg[var0]][ke[var0]]) {
                                    jK[var0] = kj[kg[var0]][ke[var0]];
                                    jU[var0] = 0;
                                    jT[var0] = 0;
                                    jO[var0] = 2;
                                }
                            }

                            jW[var0] = jT[var0] % 2 == 0 ? 0 : 1;
                            continue;
                        case 9:
                            var10002 = jT[var0]++;
                            if (jT[var0] < 6) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 2;
                            } else if (jT[var0] == 6) {
                                if (a(jJ[var0] + (jX[var0] << 3) - fe) < 13 + fi) {
                                    a(ka[var0], jX[var0]);
                                }
                            } else if (jT[var0] < 12) {
                                var10000 = jJ;
                                var10000[var0] -= jX[var0] << 1;
                            } else if (jT[var0] == 12) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 3;
                                jU[var0] = -4;
                                jT[var0] = 0;
                            }

                            jW[var0] = 1;
                            if (jT[var0] > 3 && jT[var0] < 9 && jT[var0] % 2 == 0) {
                                jW[var0] = 3;
                            }
                            continue;
                        case 10:
                            var10002 = jT[var0]++;
                            jW[var0] = 3;
                            if (jT[var0] <= 15) {
                                var10000 = jK;
                                var10000[var0] -= 7;
                                if (jJ[var0] < fe) {
                                    var10002 = jJ[var0]++;
                                } else if (jJ[var0] > fe) {
                                    var10002 = jJ[var0]--;
                                }

                                if (a(jJ[var0] - fe) < 16 && a(jK[var0] - (ff - 16)) < 20) {
                                    a(ka[var0], -fm);
                                    jT[var0] = 15;
                                }
                            }

                            if (jT[var0] == 15) {
                                jU[var0] = 1;
                            }

                            if (jT[var0] > 15) {
                                jW[var0] = 2;
                                var10000 = jK;
                                var10000[var0] += jU[var0];
                                var10002 = jU[var0]++;
                                if (a(jJ[var0] - fe) < 16 && a(jK[var0] - (ff - 16)) < 20) {
                                    a(ka[var0], -fm);
                                }

                                if (jK[var0] > kj[kg[var0]][ke[var0]]) {
                                    jK[var0] = kj[kg[var0]][ke[var0]];
                                    jU[var0] = 0;
                                    jT[var0] = 0;
                                    jO[var0] = 2;
                                }
                            }
                            continue;
                        case 11:
                            var10002 = jT[var0]++;
                            if (jT[var0] == 4 && jK[var0] == ff) {
                                a(ka[var0], jX[var0]);
                            }

                            if (jT[var0] < 8) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 1;
                                jW[var0] = 3;
                            } else {
                                jW[var0] = 0;
                                var10000 = jJ;
                                var10000[var0] -= jX[var0] << 1;
                            }

                            if (jT[var0] == 12) {
                                jO[var0] = 3;
                                jT[var0] = 0;
                                kb[var0] = 25;
                            }
                            continue;
                        case 12:
                            R(var0);
                            continue;
                        case 13:
                            if (jJ[var0] < fe) {
                                jR[var0] = 4 + x % 6;
                            } else if (jJ[var0] > fe) {
                                jR[var0] = -(4 + x % 6);
                            }

                            if (jK[var0] < ff) {
                                jS[var0] = 2 + x % 4;
                            } else if (jK[var0] > ff) {
                                jS[var0] = -(2 + x % 4);
                            }

                            var10000 = jJ;
                            var10000[var0] += jR[var0];
                            var10000 = jK;
                            var10000[var0] += jS[var0];
                            if (x % 2 == 0) {
                                var10002 = jW[var0]++;
                                if (jW[var0] > 1) {
                                    jW[var0] = 0;
                                }
                            }

                            if (a(jJ[var0] - fe) < 5 && a(jK[var0] - ff) < 5) {
                                a(ka[var0], -fm);
                                jO[var0] = 2;
                                jT[var0] = 0;
                                jX[var0] = jJ[var0] > jP[var0] ? -1 : 1;
                                jR[var0] = jX[var0] * (x % 4 + 1);
                                kb[var0] = 10;
                            }

                            if (a(jJ[var0] - fe) > 130 || a(jK[var0] - ff) > 130) {
                                jO[var0] = 2;
                                jT[var0] = 0;
                                jR[var0] = jS[var0] = 0;
                                jX[var0] = jJ[var0] > jP[var0] ? -1 : 1;
                                jR[var0] = jX[var0] * (x % 4 + 1);
                                kb[var0] = 0;
                            }
                            continue;
                        case 14:
                            R(var0);
                            continue;
                        case 15:
                            var10002 = jT[var0]++;
                            if (jT[var0] == 3) {
                                if (kh[var0]) {
                                    kh[var0] = false;
                                    b(ka[var0], jX[var0]);
                                } else {
                                    a(ka[var0], jX[var0]);
                                }
                            }

                            if ((jT[var0] >> 2) % 2 == 0) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0];
                                jW[var0] = 0;
                            } else {
                                jW[var0] = 3;
                                var10000 = jJ;
                                var10000[var0] -= jX[var0];
                            }

                            if (jT[var0] == 12) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 3;
                                jU[var0] = -4;
                                jT[var0] = 0;
                            }
                            continue;
                        case 16:
                            N(var0);
                            continue;
                        case 17:
                        case 18:
                            var10002 = jT[var0]++;
                            jW[var0] = 3;
                            if (jT[var0] == 3) {
                                b(jJ[var0] + (jX[var0] << 3), jK[var0] - 12, jX[var0] << 3, ka[var0], 2);
                            } else if (jT[var0] == 6) {
                                jO[var0] = 3;
                                jT[var0] = 0;
                            }
                            continue;
                        case 19:
                            var10002 = jT[var0]++;
                            jW[var0] = 3;
                            if (jT[var0] == 3) {
                                var10002 = jU[var0]++;
                                if (jU[var0] == 3) {
                                    jU[var0] = 0;
                                }

                                if (jU[var0] == 2) {
                                    c(jJ[var0], jK[var0] - 22, jX[var0], -10, ka[var0]);
                                } else if (jU[var0] == 0) {
                                    c(jJ[var0], jK[var0] - 22, jX[var0] << 1, -9, ka[var0]);
                                } else {
                                    c(jJ[var0], jK[var0] - 22, 0, -11, ka[var0]);
                                }

                                g(jJ[var0], jK[var0] - 20);
                            } else if (jT[var0] == 6) {
                                jO[var0] = 3;
                                jT[var0] = 0;
                            }
                            continue;
                    }
                case 5:
                    var10002 = jT[var0]++;
                    if (jT[var0] >= jH) {
                        jT[var0] = 0;
                        int var2 = x % ki[kg[var0]].length;
                        if (kk[kg[var0]][var2]) {
                            jT[var0] = jH;
                        } else {
                            jJ[var0] = ki[kg[var0]][var2];
                            jK[var0] = kj[kg[var0]][var2];
                            kk[kg[var0]][var2] = true;
                            ke[var0] = var2;
                            jO[var0] = 2;
                            jY[var0] = jZ[var0];
                            switch (jI[var0]) {
                                case 0:
                                    jP[var0] = jJ[var0] - 20;
                                    jQ[var0] = jJ[var0] + 20;
                                    break;
                                case 1:
                                    jP[var0] = jJ[var0] - 10;
                                    jQ[var0] = jJ[var0] + 10;
                                    break;
                                case 2:
                                    jP[var0] = jJ[var0] + 12;
                                    jQ[var0] = jK[var0] + 12;
                                    break;
                                case 3:
                                    jP[var0] = jJ[var0] - 20;
                                    jQ[var0] = jJ[var0] + 20;
                                    jU[var0] = -4;
                                case 4:
                                    jP[var0] = jJ[var0] - 20;
                                    jQ[var0] = jJ[var0] + 20;
                                    jU[var0] = -3;
                                    break;
                                case 5:
                                    jP[var0] = jK[var0] - 10;
                                    jQ[var0] = jK[var0] + 10;
                                    jT[var0] = 1;
                                    break;
                                case 6:
                                    jP[var0] = jJ[var0];
                                    jQ[var0] = jK[var0];
                                    break;
                                case 7:
                                    jO[var0] = 3;
                                    break;
                                case 8:
                                case 9:
                                case 11:
                                case 12:
                                    jP[var0] = jJ[var0] - 20;
                                    jQ[var0] = jJ[var0] + 20;
                                    break;
                                case 10:
                                    jP[var0] = jJ[var0] - 30;
                                    jQ[var0] = jJ[var0] + 30;
                                    break;
                                case 13:
                                    jP[var0] = jJ[var0] + 12;
                                    jQ[var0] = jK[var0] + 12;
                                    break;
                                case 14:
                                    jP[var0] = jJ[var0] - 40;
                                    jQ[var0] = jJ[var0] + 40;
                                    break;
                                case 15:
                                    jP[var0] = jJ[var0] - 20;
                                    jQ[var0] = jJ[var0] + 20;
                                    jU[var0] = -4;
                                    break;
                                case 16:
                                    jP[var0] = jJ[var0];
                                    jQ[var0] = jK[var0];
                                    break;
                                case 17:
                                    jP[var0] = jJ[var0] - 30;
                                    jQ[var0] = jJ[var0] + 30;
                            }
                        }
                    }
                    break;
                case 6:
                    jN[var0] = 100;
                    jW[var0] = 2;
                    switch (jI[var0]) {
                        case 0:
                            if (kb[var0] < 30) {
                                var10000 = jJ;
                                var10000[var0] -= jX[var0] << 1;
                            } else {
                                var10000 = jJ;
                                var10000[var0] -= jX[var0];
                            }

                            var10002 = jT[var0]++;
                            if (jT[var0] == 1) {
                                var10000 = jK;
                                var10000[var0] -= 2;
                            } else if (jT[var0] == 5) {
                                var10000 = jK;
                                var10000[var0] += 2;
                                jO[var0] = 2;
                            }
                            break;
                        case 1:
                            S(var0);
                            break;
                        case 2:
                            var10002 = jT[var0]++;
                            var10000 = jJ;
                            var10000[var0] -= jR[var0] >> 3;
                            var10000 = jK;
                            var10000[var0] -= jS[var0] >> 3;
                            if (jR[var0] == 0) {
                                jR[var0] = 2;
                            }

                            if (jS[var0] == 0) {
                                jS[var0] = 2;
                            }

                            if (jT[var0] == 5) {
                                jO[var0] = 2;
                                jT[var0] = 0;
                                jR[var0] = jS[var0] = 0;
                                kb[var0] = 10;
                            }
                            break;
                        case 3:
                            var10000 = jJ;
                            var10000[var0] -= jX[var0];
                            jW[var0] = 2;
                            var10002 = jT[var0]++;
                            if (jT[var0] == 7) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 2;
                                jU[var0] = -4;
                                jT[var0] = jX[var0] * 3;
                            }
                            break;
                        case 4:
                            var10000 = jJ;
                            var10000[var0] -= jX[var0];
                            jW[var0] = 2;
                            var10002 = jT[var0]++;
                            if (jT[var0] == 7) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 2;
                                jU[var0] = -4;
                                jT[var0] = jX[var0] * 3;
                            }
                            break;
                        case 5:
                            var10002 = jT[var0]++;
                            var10002 = jK[var0]--;
                            if (jT[var0] == 3) {
                                jO[var0] = 2;
                                jT[var0] = 1;
                            }

                            jW[var0] = 2;
                            break;
                        case 6:
                            O(var0);
                            break;
                        case 7:
                            var10002 = jT[var0]++;
                            jX[var0] = -fm;
                            if (jT[var0] % 4 > 1) {
                                jW[var0] = 0;
                            } else {
                                jW[var0] = 2;
                            }

                            if (jT[var0] == 8) {
                                jO[var0] = 3;
                                jX[var0] = 0;
                                jR[var0] = jS[var0] = 0;
                                kb[var0] = 0;
                            }
                            break;
                        case 8:
                            jO[var0] = 4;
                            break;
                        case 9:
                            var10000 = jJ;
                            var10000[var0] -= jX[var0];
                            jW[var0] = 2;
                            var10002 = jT[var0]++;
                            if (jT[var0] == 6) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 4;
                                jT[var0] = 4;
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 3;
                            }
                            break;
                        case 10:
                            jO[var0] = 4;
                            break;
                        case 11:
                            var10000 = jJ;
                            var10000[var0] -= jX[var0];
                            jW[var0] = 2;
                            var10002 = jT[var0]++;
                            if (jT[var0] == 6) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 4;
                                jT[var0] = 0;
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 3;
                            }
                            break;
                        case 12:
                            S(var0);
                            break;
                        case 13:
                            var10002 = jT[var0]++;
                            var10000 = jJ;
                            var10000[var0] -= jR[var0] >> 3;
                            var10000 = jK;
                            var10000[var0] -= jS[var0] >> 3;
                            if (jR[var0] == 0) {
                                jR[var0] = 2;
                            }

                            if (jS[var0] == 0) {
                                jS[var0] = 2;
                            }

                            if (jT[var0] == 5) {
                                jO[var0] = 2;
                                jT[var0] = 0;
                                jX[var0] = jJ[var0] > jP[var0] ? -1 : 1;
                                jR[var0] = jX[var0] << 3;
                                kb[var0] = 0;
                            }
                            break;
                        case 14:
                            T(var0);
                            break;
                        case 15:
                            var10000 = jJ;
                            var10000[var0] -= jX[var0];
                            jW[var0] = 2;
                            var10002 = jT[var0]++;
                            if (jT[var0] == 3) {
                                jK[var0] = kj[kg[var0]][ke[var0]];
                                jO[var0] = 2;
                                jU[var0] = -4;
                                jT[var0] = jX[var0] * 3;
                            }
                            break;
                        case 16:
                            O(var0);
                            break;
                        case 17:
                            T(var0);
                            break;
                        case 18:
                            var10002 = jT[var0]++;
                            var10000 = jJ;
                            var10000[var0] -= jX[var0];
                            if (jT[var0] == 4) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 2;
                                jO[var0] = 2;
                            }
                            break;
                        case 19:
                            var10002 = jT[var0]++;
                            var10000 = jJ;
                            var10000[var0] -= jX[var0];
                            if (jT[var0] == 4) {
                                var10000 = jJ;
                                var10000[var0] += jX[var0] << 2;
                                jT[var0] = 0;
                                jO[var0] = 4;
                            }
                    }
                case 7:
                case 8:
                default:
                    break;
                case 9:
                    var10000 = jK;
                    var10000[var0] += jT[var0];
                    var10002 = jT[var0]++;
                    jW[var0] = 2;
                    if (jK[var0] > kj[kg[var0]][ke[var0]]) {
                        if (jI[var0] != 2 && jI[var0] != 6 && jI[var0] != 16 && jI[var0] != 13) {
                            jK[var0] = kj[kg[var0]][ke[var0]];
                        }

                        jO[var0] = 3;
                    }
            }
        }

    }

    private static void j(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 2;
                kc[var3] = 120;
                kd[var3] = 100;
                jL[var3] = 20;
                jM[var3] = 30;
                jX[var3] = 0;
                jO[var3] = 5;
                jT[var3] = var3 * 7 + var3;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 100;
                jR[var3] = 2;
                jS[var3] = 3;
            }

        }
    }

    private static void k(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 13;
                kc[var3] = 120;
                kd[var3] = 50;
                jL[var3] = 20;
                jM[var3] = 20;
                jX[var3] = -1;
                jO[var3] = 5;
                jT[var3] = var3 << 1;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 100;
                jR[var3] = -3;
                jS[var3] = 3;
            }

        }
    }

    private static void l(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 9;
                kc[var3] = 36;
                kd[var3] = 20;
                jL[var3] = 34;
                jM[var3] = 32;
                jX[var3] = 1;
                jO[var3] = 5;
                jT[var3] = var3 * 6;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 100;
            }

        }
    }

    private static void m(int var0, int var1) {
        for(var0 = 0; var0 < jG && jI[var0] != -1; ++var0) {
        }

        if (var0 != jG) {
            for(int var2 = var0; var2 < var0 + var1; ++var2) {
                kg[var2] = 1;
                jI[var2] = 8;
                kc[var2] = 40;
                kd[var2] = 30;
                jL[var2] = 12;
                jM[var2] = 16;
                int[] var10000 = jK;
                var10000[var2] += 5;
                jX[var2] = 1;
                jO[var2] = 5;
                jT[var2] = var2 * 5;
                jY[var2] = jZ[var2] = am[jI[var2]];
                ka[var2] = an[jI[var2]];
                kf[var2] = ao[jI[var2]];
                kb[var2] = 60;
            }

        }
    }

    private static void n(int var0, int var1) {
        for(var0 = 0; var0 < jG && jI[var0] != -1; ++var0) {
        }

        if (var0 != jG) {
            for(int var2 = var0; var2 < var0 + var1; ++var2) {
                kg[var2] = 2;
                jI[var2] = 10;
                kc[var2] = 80;
                kd[var2] = 100;
                jL[var2] = 12;
                jM[var2] = 16;
                jX[var2] = 1;
                jO[var2] = 5;
                jT[var2] = var2 * 5;
                jY[var2] = jZ[var2] = am[jI[var2]];
                ka[var2] = an[jI[var2]];
                kf[var2] = ao[jI[var2]];
                kb[var2] = 40;
            }

        }
    }

    private static void o(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 6;
                kc[var3] = 60;
                kd[var3] = 60;
                jL[var3] = 16;
                jM[var3] = 16;
                jX[var3] = -1;
                jO[var3] = 5;
                jT[var3] = var3 << 3;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 100;
                jR[var3] = 2;
                jS[var3] = 3;
            }

        }
    }

    private static void p(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 16;
                kc[var3] = 60;
                kd[var3] = 60;
                jL[var3] = 16;
                jM[var3] = 16;
                jX[var3] = -1;
                jO[var3] = 5;
                jT[var3] = var3 + 60;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 100;
                jR[var3] = 2;
                jS[var3] = 3;
            }

        }
    }

    private static void q(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 7;
                kc[var3] = 10;
                kd[var3] = 10;
                jL[var3] = 30;
                jM[var3] = 25;
                jX[var3] = 1;
                jO[var3] = 5;
                jT[var3] = var3 << 2;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 0;
            }

        }
    }

    private static void r(int var0, int var1) {
        for(var0 = 0; var0 < jG && jI[var0] != -1; ++var0) {
        }

        if (var0 != jG) {
            for(int var2 = var0; var2 < var0 + var1; ++var2) {
                kg[var2] = 2;
                jI[var2] = 4;
                kc[var2] = 20;
                kd[var2] = 10;
                jL[var2] = 18;
                jM[var2] = 18;
                jX[var2] = 1;
                jO[var2] = 5;
                jT[var2] = var2 << 3;
                jY[var2] = jZ[var2] = am[jI[var2]];
                ka[var2] = an[jI[var2]];
                kf[var2] = ao[jI[var2]];
                kb[var2] = 0;
            }

        }
    }

    private static void al() {
        ki = new int[kl.length][];
        kj = new int[kl.length][];
        kk = new boolean[kl.length][];
    }

    private static void M(int var0) {
        jG = var0;
        jH = 70;
        jI = new int[jG];
        kg = new int[jG];
        jJ = new int[jG];
        jK = new int[jG];
        jO = new int[jG];
        jP = new int[jG];
        jQ = new int[jG];
        jR = new int[jG];
        jS = new int[jG];
        jT = new int[jG];
        jU = new int[jG];
        jV = new int[jG];
        jW = new int[jG];
        jX = new int[jG];
        jY = new int[jG];
        kh = new boolean[jG];
        jZ = new int[jG];
        ka = new int[jG];
        kb = new int[jG];
        jL = new int[jG];
        jM = new int[jG];
        jM = new int[jG];
        jN = new int[jG];
        kc = new int[jG];
        kd = new int[jG];
        ke = new int[jG];
        kf = new int[jG];

        for(var0 = 0; var0 < jG; ++var0) {
            jI[var0] = -1;
        }

    }

    private static void s(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 0;
                kc[var3] = 25;
                kd[var3] = 10;
                jL[var3] = 27;
                jM[var3] = 15;
                jX[var3] = 1;
                jO[var3] = 5;
                jT[var3] = var3 * 7;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 0;
            }

        }
    }

    private static void t(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 15;
                kc[var3] = 36;
                kd[var3] = 10;
                jL[var3] = 27;
                jM[var3] = 15;
                jX[var3] = 1;
                jO[var3] = 5;
                jT[var3] = var3 << 1;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 0;
            }

        }
    }

    private static void u(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 1;
                kc[var3] = 7;
                kd[var3] = 10;
                jL[var3] = 16;
                jM[var3] = 15;
                jX[var3] = 1;
                jO[var3] = 5;
                jT[var3] = var3 << 3;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 100;
            }

        }
    }

    private static void v(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 18;
                kc[var3] = 120;
                kd[var3] = 10;
                jL[var3] = 24;
                jM[var3] = 20;
                jX[var3] = 1;
                jO[var3] = 5;
                jT[var3] = 60;
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 100;
            }

        }
    }

    private static void w(int var0, int var1) {
        for(var0 = 0; var0 < jG && jI[var0] != -1; ++var0) {
        }

        if (var0 != jG) {
            for(int var2 = var0; var2 < var0 + var1; ++var2) {
                kg[var2] = 0;
                jI[var2] = 19;
                kc[var2] = 60;
                kd[var2] = 30;
                jL[var2] = 24;
                jM[var2] = 20;
                jX[var2] = 1;
                jO[var2] = 5;
                jT[var2] = 62;
                jY[var2] = jZ[var2] = am[jI[var2]];
                ka[var2] = an[jI[var2]];
                kf[var2] = ao[jI[var2]];
                kb[var2] = 70;
            }

        }
    }

    private static void x(int var0, int var1) {
        int var2;
        for(var2 = 0; var2 < jG && jI[var2] != -1; ++var2) {
        }

        if (var2 != jG) {
            for(int var3 = var2; var3 < var2 + var1; ++var3) {
                kg[var3] = var0;
                jI[var3] = 17;
                kc[var3] = 60;
                kd[var3] = 10;
                jL[var3] = 22;
                jM[var3] = 22;
                jX[var3] = 1;
                jO[var3] = 5;
                jT[var3] = 50 + (var3 << 1);
                jY[var3] = jZ[var3] = am[jI[var3]];
                ka[var3] = an[jI[var3]];
                kf[var3] = ao[jI[var3]];
                kb[var3] = 500;
            }

        }
    }

    private static void y(int var0, int var1) {
        for(var0 = 0; var0 < jG && jI[var0] != -1; ++var0) {
        }

        if (var0 != jG) {
            for(int var2 = var0; var2 < var0 + var1; ++var2) {
                kg[var2] = 0;
                jI[var2] = 12;
                kc[var2] = 24;
                kd[var2] = 10;
                jL[var2] = 16;
                jM[var2] = 22;
                jX[var2] = 1;
                jO[var2] = 5;
                jT[var2] = 70;
                jY[var2] = jZ[var2] = am[jI[var2]];
                ka[var2] = an[jI[var2]];
                kf[var2] = ao[jI[var2]];
                kb[var2] = 500;
            }

        }
    }

    private static void am() {
        int var0;
        for(var0 = 0; var0 < kl.length; ++var0) {
            kk[var0] = new boolean[ki[var0].length];
        }

        for(var0 = 0; var0 < ki.length; ++var0) {
            for(int var1 = 0; var1 < ki[var0].length; ++var1) {
                ki[var0][var1] = ki[var0][var1] * GameMap.TILE_SIZE + 12;
                kj[var0][var1] *= GameMap.TILE_SIZE;
                if (var0 == 8) {
                    int[] var10000 = kj[var0];
                    var10000[var1] -= 12;
                }
            }
        }

    }

    private static void N(int var0) {
        if (jJ[var0] < fe) {
            jR[var0] = x % 3 + 3;
            jX[var0] = 1;
        } else if (jJ[var0] > fe) {
            jR[var0] = -x % 3 - 3;
            jX[var0] = -1;
        }

        if (jK[var0] < ff) {
            jS[var0] = x % 3 + 3;
        } else if (jK[var0] > ff) {
            jS[var0] = -x % 3 - 3;
        }

        int[] var10000 = jJ;
        var10000[var0] += jR[var0];
        var10000 = jK;
        var10000[var0] += jS[var0];
        int var10002 = jW[var0]++;
        if (jW[var0] > 1) {
            jW[var0] = 0;
        }

        if (a(jJ[var0] - fe) < 10 && a(jK[var0] - ff) < 10) {
            kb[var0] = 0;
            if (kh[var0]) {
                kh[var0] = false;
                b(ka[var0], jX[var0]);
            } else {
                a(ka[var0], jX[var0]);
            }

            jT[var0] = 0;
            jO[var0] = 2;
        }

        if (a(jJ[var0] - fe) > 160 || a(jK[var0] - ff) > 160) {
            jO[var0] = 2;
            jT[var0] = 0;
            jR[var0] = jS[var0] = 0;
            kb[var0] = 0;
        }

    }

    private static void O(int var0) {
        int var10002 = jT[var0]++;
        int[] var10000 = jJ;
        var10000[var0] -= jR[var0];
        var10000 = jK;
        var10000[var0] -= jS[var0];
        if (jR[var0] == 0) {
            jR[var0] = 2;
        }

        if (jS[var0] == 0) {
            jS[var0] = 2;
        }

        if (jT[var0] == 5) {
            jO[var0] = 2;
            jT[var0] = 0;
            jR[var0] = jS[var0] = 0;
            kb[var0] = 0;
        }

    }

    private static void P(int var0) {
        int[] var10000 = jJ;
        var10000[var0] += jR[var0];
        var10000 = jK;
        var10000[var0] += jS[var0];
        if (jJ[var0] - jP[var0] > 40) {
            jR[var0] = -(x % 2 + 1);
        }

        if (jJ[var0] - jP[var0] < -40) {
            jR[var0] = x % 2 + 1;
        }

        if (jK[var0] - jQ[var0] > 40) {
            jS[var0] = -(x % 2 + 1);
        }

        if (jK[var0] - jQ[var0] < -40) {
            jS[var0] = x % 2 + 1;
        }

        if (x % 15 == 0) {
            jR[var0] = x % 3 - 1;
            jS[var0] = (x >> 1) % 3 - 1;
        }

        int var10002 = jW[var0]++;
        if (jW[var0] > 1) {
            jW[var0] = 0;
        }

        J(var0);
        if (x % 5 == 0) {
            K(var0);
        }

    }

    private static void Q(int var0) {
        if (x % 4 == 1) {
            int var10002 = jW[var0]++;
            if (jW[var0] > 1) {
                jW[var0] = 0;
            }

            int[] var10000 = jJ;
            var10000[var0] += jX[var0] << 1;
            if (jJ[var0] > jQ[var0]) {
                jX[var0] = -1;
            } else if (jJ[var0] < jP[var0]) {
                jX[var0] = 1;
            }
        }

        J(var0);
    }

    private static void R(int var0) {
        a(ka[var0], -fm);
        jO[var0] = 2;
        jT[var0] = 0;
        jW[var0] = 2;
    }

    private static void S(int var0) {
        int var10002 = jT[var0]++;
        int[] var10000 = jJ;
        var10000[var0] -= jX[var0];
        if (jT[var0] == 5) {
            jO[var0] = 2;
        }

    }

    private static void T(int var0) {
        int var10002 = jT[var0]++;
        int[] var10000 = jJ;
        var10000[var0] -= jX[var0];
        if (jT[var0] == 3) {
            jO[var0] = 2;
        }

    }

    private static void U(int var0) {
        if (x % 4 == 0) {
            int var10002 = jW[var0]++;
            if (jW[var0] > 1) {
                jW[var0] = 0;
            }

            int[] var10000 = jJ;
            var10000[var0] += jX[var0];
        }

        if (jJ[var0] > jQ[var0]) {
            jX[var0] = -1;
        } else if (jJ[var0] < jP[var0]) {
            jX[var0] = 1;
        }

        J(var0);
        if (x % 5 == 0) {
            K(var0);
        }

    }

    private static void an() {
        V = bD - 40;
        kn = bC - 50;
        tilesetImage = null;
        shopSwordIcons = new Image[4];

        try {
            for(int var4 = 0; var4 < Ui.SWORD_LEVEL_COUNT; ++var4) {
                shopSwordIcons[var4] = Ui.loadSwordIcon(var4);
            }
        } catch (Exception var3) {
        }

        if (cornerIcons == null) {
            cornerIcons = new Image[4];

            for(int var0 = 0; var0 < 4; ++var0) {
                try {
                    cornerIcons[var0] = Ui.loadCorner(var0);
                } catch (IOException var2) {
                    var2.printStackTrace();
                }
            }
        }

        System.gc();
    }

    private static void m(Graphics var0) {
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        d(var0);
        c(var0);
        var0.setClip(0, 0, aT, aU);
        var0.setColor(0);
        var0.fillRect(bC - 64, V - 19, 129, 102);
        var0.fillRect(0, aU - 13, aT, 13);
        FontGraph.drawBold(var0, "Mua", 3, aU - 13, 0);
        FontGraph.drawBold(var0, "Quay lại", aT - 3, aU - 13, 1);
        var0.setColor(12281361);
        var0.drawRect(bC - 63, V - 18, 126, 99);
        var0.setColor(6562304);
        var0.fillRect(bC - 61, V - 16, 123, 97);
        if (kr == 0) {
            FontGraph.drawBold(var0, "Tiền: " + dd + "$", bC, V - 8, 2);
        } else {
            FontGraph.drawBold(var0, kt[kq][ko], bC, V - 8, 2);
        }

        int var1;
        for(var1 = 0; var1 < 3; ++var1) {
            var0.setColor(0);
            var0.fillRect(kn - 10 + var1 * 20, V + 10, 19, 20);
            var0.fillRect(kn + 50 + var1 * 20, V + 10, 19, 20);
            var0.setColor(4856064);
            var0.drawRect(kn - 10 + var1 * 20, V + 10, 20, 20);
            var0.drawRect(kn + 50 + var1 * 20, V + 10, 20, 20);
            var0.drawRegion(itemPotions[Item.POTION_HP], 0, var1 * Item.FRAME_HEIGHT, Item.POTION_WIDTH, Item.FRAME_HEIGHT, 0, kn + var1 * 20, V + 20, 3);
            var0.drawRegion(itemPotions[Item.POTION_MP], 0, var1 * Item.FRAME_HEIGHT, Item.POTION_WIDTH, Item.FRAME_HEIGHT, 0, kn + 60 + var1 * 20, V + 20, 3);
        }

        for(var1 = 0; var1 < 4; ++var1) {
            var0.setColor(0);
            var0.fillRect(kn + 10 + var1 * 20, V + 32, 19, 20);
            var0.setColor(4856064);
            var0.drawRect(kn + 10 + var1 * 20, V + 32, 18, 20);
            var0.drawImage(shopSwordIcons[var1], kn + 20 + var1 * 20, V + 42, 3);
        }

        var0.setColor(ks[kp]);
        if (kq == 0) {
            var0.drawRect(kn - 10 + ko * 20, V + 10, 19, 20);
        } else {
            var0.drawRect(kn + 10 + ko * 20, V + 32, 19, 20);
        }

        if (kr == 0) {
            FontGraph.drawBold(var0, kt[kq][ko] + "-" + kv[kq][ko] + "$", bC, V + 58, 2);
        } else {
            FontGraph.drawBold(var0, ku[kq][ko], bC, V + 58, 2);
        }

        var0.drawImage(cornerIcons[0], bC - 64, V - 19, 20);
        var0.drawImage(cornerIcons[1], bC + 65, V - 19, 24);
        var0.drawImage(cornerIcons[2], bC - 64, V + 83, 36);
        var0.drawImage(cornerIcons[3], bC + 65, V + 83, 40);
    }

    private static void ao() {
        kz = bD - 40;
        kA = bC - 50;
        if (cornerIcons == null) {
            cornerIcons = new Image[4];

            for(int var0 = 0; var0 < 4; ++var0) {
                try {
                    cornerIcons[var0] = Ui.loadCorner(var0);
                } catch (IOException var2) {
                    var2.printStackTrace();
                }
            }
        }

    }

    private static void n(Graphics var0) {
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        d(var0);
        c(var0);
        var0.setClip(0, 0, aT, aU);
        var0.setColor(0);
        var0.fillRect(bC - 64, kz - 19, 129, 102);
        var0.fillRect(0, aU - 13, aT, 13);
        FontGraph.drawBold(var0, "Sử dụng", 3, aU - 13, 0);
        FontGraph.drawBold(var0, "Quay lại", aT - 3, aU - 13, 1);
        var0.setColor(12281361);
        var0.drawRect(bC - 63, kz - 18, 126, 99);
        var0.setColor(6562304);
        var0.fillRect(bC - 61, kz - 16, 123, 97);
        FontGraph.drawBold(var0, "Sử dụng Item", bC, kz - 8, 2);

        for(int var1 = 0; var1 < 3; ++var1) {
            var0.setColor(0);
            var0.fillRect(kA - 10 + var1 * 20, kz + 10, 19, 20);
            var0.fillRect(kA + 50 + var1 * 20, kz + 10, 19, 20);
            var0.setColor(4856064);
            var0.drawRect(kA - 10 + var1 * 20, kz + 10, 20, 20);
            var0.drawRect(kA + 50 + var1 * 20, kz + 10, 20, 20);
            var0.drawRegion(itemPotions[Item.POTION_HP], 0, var1 * Item.FRAME_HEIGHT, Item.POTION_WIDTH, Item.FRAME_HEIGHT, 0, kA + var1 * 20, kz + 20, 3);
            var0.drawRegion(itemPotions[Item.POTION_MP], 0, var1 * Item.FRAME_HEIGHT, Item.POTION_WIDTH, Item.FRAME_HEIGHT, 0, kA + 60 + var1 * 20, kz + 20, 3);
        }

        var0.setColor(kD[kC]);
        var0.drawRect(kA - 10 + kB * 20, kz + 10, 20, 20);
        FontGraph.drawBold(var0, kE[kB], bC, kz + 40, 2);
        FontGraph.drawBold(var0, "Số lượng: " + W[kB], bC, kz + 55, 2);
        var0.drawImage(cornerIcons[0], bC - 64, kz - 19, 20);
        var0.drawImage(cornerIcons[1], bC + 65, kz - 19, 24);
        var0.drawImage(cornerIcons[2], bC - 64, kz + 83, 36);
        var0.drawImage(cornerIcons[3], bC + 65, kz + 83, 40);
    }

    private static void ap() {
        kH = bD - 40;
        tilesetImage = null;
        if (cornerIcons == null) {
            cornerIcons = new Image[4];

            for(int var0 = 0; var0 < 4; ++var0) {
                try {
                    cornerIcons[var0] = Ui.loadCorner(var0);
                } catch (IOException var2) {
                    var2.printStackTrace();
                }
            }
        }

    }

    private static void o(Graphics var0) {
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        var0.setColor(0);
        var0.fillRect(bC - 64, kH - 19, 129, 102);
        var0.fillRect(0, aU - 13, aT, 13);
        FontGraph.drawBold(var0, "Chọn", 3, aU - 13, 0);
        FontGraph.drawBold(var0, "Quay lại", aT - 3, aU - 13, 1);
        var0.setColor(12281361);
        var0.drawRect(bC - 63, kH - 18, 126, 99);
        var0.setColor(6562304);
        var0.fillRect(bC - 61, kH - 16, 123, 97);

        for(int var1 = 0; var1 < 4; ++var1) {
            FontGraph.drawBold(var0, kK[var1], aV - 30, kH + var1 * 18, 0);
        }

        var0.drawImage(weaponIcons[0], aV - 55 + kJ % 2, kH + kI * 18 - kJ % 2, 0);
        var0.drawImage(cornerIcons[0], bC - 64, kH - 19, 20);
        var0.drawImage(cornerIcons[1], bC + 65, kH - 19, 24);
        var0.drawImage(cornerIcons[2], bC - 64, kH + 83, 36);
        var0.drawImage(cornerIcons[3], bC + 65, kH + 83, 40);
    }

    private static void aq() {
        lf = 280 - r;
        lg = 280 - s;
        kV = 0;

        try {
            worldMapImage = Ui.loadWorldMap();
            crossIcon = Ui.loadCross();
        } catch (Exception var0) {
        }

        lh = w;
        if (w == 99) {
            lh = 0;
        }

        if (w == 97) {
            lh = 7;
        }

        if (w == 96) {
            lh = 11;
        }

        if (w == 95) {
            lh = 18;
        }

        kT = kN[lh];
        kU = kO[lh];
        as();
        kX = kT - bC;
        kY = kU - bD;
        if (ld < -40) {
            ld = -40;
        }

        if (ld > lf) {
            ld = lf;
        }

        if (le < -40) {
            le = -40;
        }

        if (le > lg) {
            le = lg;
        }

    }

    private static void ar() {
        worldMapImage = null;
        crossIcon = null;
        System.gc();
    }

    private static void p(Graphics var0) {
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        var0.setColor(0);
        var0.fillRect(0, 0, r, s);
        var0.fillRect(0, aU - 13, aT, 13);
        FontGraph.drawBold(var0, li, 3, aU - 13, 0);
        FontGraph.drawBold(var0, "Quay lại", aT - 3, aU - 13, 1);
        var0.setClip(0, 0, r, s);
        var0.translate(-ld, -le);
        var0.drawImage(worldMapImage, 0, 0, 0);

        for(int var1 = 0; var1 < 19; ++var1) {
            if (var1 == lh) {
                var0.drawImage(heroHeads[0], kN[var1] + 10, kO[var1], 3);
            }

            if (X[var1] && (var1 != kV || kW % 10 > 5)) {
                var0.drawImage(crossIcon, kN[var1], kO[var1], 3);
            }
        }

        var0.drawImage(weaponIcons[0], kT, kU, 24);
        if (kV != -1) {
            var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
            var0.setColor(6562304);
            var0.fillRect(aV - 60, s - 35, 120, 33);
            var0.setColor(12281361);
            var0.drawRect(aV - 59, s - 34, 117, 30);
            FontGraph.drawBold(var0, kL[kV], aV, s - 25, 2);
            var0.drawImage(cornerIcons[0], aV - 60, s - 35, 20);
            var0.drawImage(cornerIcons[1], aV + 60, s - 35, 24);
            var0.drawImage(cornerIcons[2], aV - 60, s - 2, 36);
            var0.drawImage(cornerIcons[3], aV + 60, s - 2, 40);
        }

    }

    private static void as() {
        kV = -1;
        li = "";

        for(int var0 = 0; var0 < 19; ++var0) {
            if (a(kT - kN[var0]) < 8 && a(kU - kO[var0]) < 8) {
                kV = var0;
                li = "Chuyển đến";
                return;
            }
        }

    }

    private static void at() {
        try {
            popupImage = Ui.loadSoundIcon();
        } catch (Exception var0) {
        }
    }

    public static void a(String var0) {
        lr = FontGraph.wrapBoldText(var0, 100);
        Y = bD - lr.length * 9;
    }

    public static void s(int var0) {
        au();
        lm = var0;
        if (var0 == 1) {
            a("Không thể chuyển. Vì bạn chưa từng đi bộ đến đây!");
            lp = "OK";
            lq = "";
        } else if (var0 == 3) {
            a("Không cần phải chuyển vì bạn đang ở vị trí này!");
            lp = "OK";
            lq = "";
        } else if (var0 != 2) {
            if (var0 == 4) {
                a("Bạn không có đủ tiền!");
                lp = "OK";
                lq = "";
            } else if (var0 == 5) {
                a("Bạn đã bị trọng thương! Mất một số điểm kinh nghiệm!");
                lp = "OK";
                lq = "";
            } else if (var0 == 6) {
                a("Level up!");
                lp = "OK";
                lq = "";
            } else {
                if (var0 == 7) {
                    a("Buy OK!");
                    lp = "";
                    lq = "OK";
                }

            }
        } else {
            var0 = lh < kV ? lh : kV;
            int var1 = lh > kV ? lh : kV;
            int var2 = 0;

            for(var0 = var0; var0 <= var1; ++var0) {
                var2 += kM[var0];
            }

            ln = var2 = (var2 << 1) / 3;
            a("Chuyển đến đây phải trả " + var2 + "$. Bạn có " + dd + "$. Chuyển?");
            lp = "Chuyển";
            lq = "Không";
        }
    }

    private static void au() {
        lo = 0;
        if (cornerIcons == null) {
            cornerIcons = new Image[4];

            for(int var0 = 0; var0 < 4; ++var0) {
                try {
                    cornerIcons[var0] = Ui.loadCorner(var0);
                } catch (IOException var2) {
                    var2.printStackTrace();
                }
            }
        }

    }

    private static void q(Graphics var0) {
        var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
        var0.setColor(0);
        var0.fillRect(bC - 64, Y - 19, 129, 102);
        var0.fillRect(0, aU - 13, aT, 13);
        FontGraph.drawBold(var0, lp, 3, aU - 13, 0);
        FontGraph.drawBold(var0, lq, aT - 3, aU - 13, 1);
        var0.setColor(12281361);
        var0.drawRect(bC - 63, Y - 18, 126, 99);
        var0.setColor(6562304);
        var0.fillRect(bC - 61, Y - 16, 123, 97);

        for(int var1 = 0; var1 < lr.length; ++var1) {
            FontGraph.drawBold(var0, lr[var1], aV, Y + var1 * 18, 2);
        }

        var0.drawImage(cornerIcons[0], bC - 64, Y - 19, 20);
        var0.drawImage(cornerIcons[1], bC + 65, Y - 19, 24);
        var0.drawImage(cornerIcons[2], bC - 64, Y + 83, 36);
        var0.drawImage(cornerIcons[3], bC + 65, Y + 83, 40);
    }

    private static void V(int var0) {
        lG = w;
        lJ = var0;
        lH = lu[var0];
        lI = lv[var0] - 44;
    }

    public static void t(int var0) {
        lt = 0;
        if (var0 == 0) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 0);
                Npc.loadSprite(npcSprites, 1);
                Npc.loadSprite(npcSprites, 2);
                Npc.loadSprite(npcSprites, 3);
                Npc.loadSprite(npcSprites, 4);
                Npc.loadSprite(npcSprites, 5);
            } catch (Exception var7) {
            }

            lt = 7;
            av();
            c(0, 0, 34, 13);
            c(1, 1, 43, 17);
            c(2, 2, 50, 17);
            c(3, 3, 57, 12);
            c(4, 4, 43, 12);
            c(5, 5, 77, 15);
            c(6, 5, 25, 17);
        }

        if (var0 == 1) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 5);
            } catch (Exception var6) {
            }

            lt = 1;
            av();
            c(0, 5, 54, 9);
        }

        if (var0 == 2) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 5);
            } catch (Exception var5) {
            }

            lt = 1;
            av();
            c(0, 5, 23, 12);
        }

        if (var0 == 5) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 5);
            } catch (Exception var4) {
            }

            lt = 1;
            av();
            c(0, 5, 12, 5);
        }

        if (var0 == 6) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 5);
            } catch (Exception var3) {
            }

            lt = 1;
            av();
            c(0, 5, 14, 9);
        }

        if (var0 == 99 || var0 == 101) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 4);
            } catch (Exception var2) {
            }

            lt = 1;
            av();
            c(0, 4, 3, 12);
        }

        if (var0 == 94) {
            npcSprites = Npc.createSpriteTable();

            try {
                Npc.loadSprite(npcSprites, 0);
                Npc.loadSprite(npcSprites, 4);
                Npc.loadSprite(npcSprites, 2);
            } catch (Exception var1) {
            }

            lt = 3;
            av();
            c(0, 4, 2, 10);
            c(1, 0, 3, 10);
            c(2, 2, 4, 10);
        }

    }

    private static void c(int var0, int var1, int var2, int var3) {
        lw[var0] = var1;
        lu[var0] = var2 * GameMap.TILE_SIZE + 12;
        lv[var0] = var3 * GameMap.TILE_SIZE;
    }

    private static void av() {
        lu = new int[lt];
        lv = new int[lt];
        lw = new int[lt];
    }

    private static void r(Graphics var0) {
        for(int var1 = 0; var1 < lt; ++var1) {
            if (lu[var1] + 10 >= fO && lu[var1] - 10 <= fO + r && lv[var1] >= fP && lv[var1] - 32 <= fP + s) {
                var0.drawImage(npcSprites[lw[var1]][Npc.PART_LEGS], lu[var1], lv[var1], 33);
                var0.drawImage(npcSprites[lw[var1]][Npc.PART_BODY], lu[var1], lv[var1] - lF[lw[var1]] + (x % (15 + lw[var1]) > 4 ? 0 : 1), 33);
            }
        }

    }

    private static void showPurchaseFailed() {
        s(7);
        a("Không thể mua. Xin hãy thử lại sau.");
        Y = V;
        e = 7;
    }

    public static void t() {
        H();
        I();
        s(7);
        a("Xin chúc mừng. Bạn đã có vũ khí mới: " + kt[kq][ko] + "!");
        Y = V;
        e = 7;
    }

}
