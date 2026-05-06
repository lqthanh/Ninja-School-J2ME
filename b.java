import assets.*;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextBox;
import javax.microedition.rms.RecordStore;

public final class b extends Canvas implements Runnable, CommandListener {
    public static boolean a = true;
    private static int aa = 40;
    private static int[] ab = new int[]{150, 180, 180, 300, 350, 380, 500};
    private static int[] ac = new int[]{20, 30, 40, 100, 80, 120, 200};
    private static int[] ad = new int[]{150, 140, 120, 240, 280, 290, 300};
    private static int[] ae = new int[]{800, 1000, 1200, 3000, 3500, 4000, 6000};
    private static int[][] af = new int[][]{{-5, -2}, {-2, -4}, {0, -5}, {-6, -6}, {-5, -2}, {-2, -4}, {0, -5}};
    private static int[][] ag = new int[][]{{5, 8}, {3, 3}, new int[2], {4, 4}, {5, 8}, {3, 3}, new int[2]};
    private static int[] ah = new int[]{1, -2, 1, 1, 1, -2, 1};
    private static int[] ai = new int[]{3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
    private static int[] aj = new int[]{50, 150, 240, 400, 600, 800, 1000, 1500, 2000, 3000, 5000, 7000, 9000, 12000, 15000, 19000, 26000, 32000, 40000, 50000, 60000, 70000, 80000, 90000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000};
    private static int[] ak = new int[]{20, 20, 20, 20, 30, 30, 30, 40, 40, 50, 60, 70, 80, 80, 90, 90, 100, 120, 130, 150, 160, 170, 180, 190, 200, 210, 220, 250, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300};
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
            "About Us", 
            "Thoát"};
    private static String[][] aF = new String[][]{{"Rừng trúc đây rồi!", 
            "Hôm nay ta phải hạ được con lon đó!", 
            "Nó chắc quanh quẩn đây thôi!"}, 
        {"Bọn quái vật nhãi nhép này làm gì được ta chứ!"}, 
        {"Con lon hung hăng kia rồi!", 
            "Ngươi phải đền tội vì phải giết ngươi mà tao ko đc đi xem concert của Rồng Tưởng Tượng!"}, 
        {"Này các con, sau bao năm tháng tập luyện...", 
            "Đây là lúc các con thể hiện tài năng!", 
            "Gần đây, trong khu rừng trúc...", 
            "..xuất hiện 1 con lon to ..", 
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
        {"Tốt! Cho ta biết ai ăn cắp game này xong phát hành lậu!"}, 
        {"Nhóm DuriuStudio!"}, 
        {"Cụ thể là ai?"}, 
        {"Kệ mẹ chúng nó, hu at hu ce!"}, 
        {"Vậy chúng ta ăn cắp lại code của chúng nó ở đâu?"}, 
        {"Trên Page FB DuriuStudio chúng nó hay đăng code với mấy cái xàm vl!"}, 
        {"Tốt, các con có thể giải tán!"}, 
        {"Chúng con chào thầy!"}, 
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
            "Ta chỉ chơi với những người có hơn 5 triệu lực chiến trong Rise of Kingdom thôi.", 
            "Ngươi hãy nạp tiền đi, bao giờ nhiều lực chiến hơn thì quay lại!"}, 
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
    private static boolean aN;
    private static int aO;
    private static int aP;
    private static boolean aQ;
    private static boolean[] aR;
    private static boolean[] aS;
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
    private static Image[][] dY;
    private static Image[] dZ;
    private static Image ea;
    public static Image[] t;
    private static Image[] eb;
    private static Image ec;
    private static Image[] ed;
    private static Image ee;
    public static Image u;
    public static Image v;
    private static Image[] ef;
    private static Image[] eg;
    private static Image eh;
    private static Image[] ei;
    private static Image[][] ej;
    private static Image[] ek;
    private static Image[] el;
    private static Image[] em;
    private static Image[] en;
    private static Image[] eo;
    private static Image[] ep;
    private static Image[] eq;
    private static Image[] er;
    private static Image[][] es;
    private static Image[][] et;
    private static Image[] eu;
    private static Image[] ev;
    private static Image[] ew;
    private static Image[] ex;
    private static Image[] ey;
    private static Image[] ez;
    private static Image eA;
    private static Image[] eB;
    private static Image[] eC;
    private static Image[] eD;
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
    public static int F;
    public static int G;
    public static int H;
    public static int I;
    public static int J;
    public static int K;
    public static int L;
    public static int M;
    private static int fo;
    private static int fp;
    private static int fq;
    private static boolean fr;
    private static int fs;
    private static int ft;
    public static int N;
    public static int O;
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
    private static String iL;
    private static int iM;
    private static int iN;
    private static int iO;
    private static int iP;
    private static byte[] iQ;
    private static int[] iR;
    private static boolean[] iS;
    private static Image iT;
    private static Image iU;
    private static Image iV;
    private static Image[] iW;
    private static int iX;
    private static int[] iY;
    private static int[] iZ;
    private static int[] ja;
    private static int[] jb;
    private static int[] jc;
    private static Image jd;
    private static int je;
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
    private static Image jC;
    private static int jD;
    private static int jE;
    public static boolean U;
    private static Image[][][] jF;
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
    private static Image[] kw;
    private static String[][] kx;
    private static String[][] ky;
    private static int kz;
    private static int kA;
    private static int kB;
    private static int kC;
    private static int[] kD;
    private static String[] kE;
    static int[] W;
    private static int[] kF;
    private static Image[] kG;
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
    private static Image kR;
    private static Image kS;
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
    private static Image lj;
    private static boolean lk;
    private static int ll;
    public static int Y;
    private static int lm;
    private static int ln;
    private static int lo;
    private static String lp;
    private static String lq;
    private static String[] lr;
    private static Image[][] ls;
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
    private static boolean lK;
    private static String[] lL;
    private static String[] lM;
    public static int Z;
    private static int lN;
    private static int lO;
    private static Font lP;
    private static Font lQ;
    private static int lR;
    private String lS = "12345678";
    private int lT;
    private static String lU;
    private static int[] lV;
    private static int lW;
    private int lX = 0;
    private String lY = "";
    private int lZ;
    private int ma;

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
        aR = new boolean[14];
        aS = new boolean[14];
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
        iL = "oplj";
        je = 24;
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
        kx = new String[][]{{"BinhHP_Nho(50)", 
                "BinhHP_Vua(50)", 
                "BinhHP_To(30)", 
                "BinhMP_Nho(50)", 
                "BinhMP_Vua(30)", 
                "BinhMP_To(50)"}, 
            {"KiemTre", 
                "ThuyLinhKiem", 
                "HoaGiacKiem", 
                "LongGiaoKiem"}};
        ky = new String[][]{{"sms://", "sms://", "sms://", "sms://", "sms://", "sms://"}, {"sms://", "sms://", "sms://", "sms://"}};
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
        lL = new String[]{"1. Tiep tuc", "2. Kich hoat", "3. Nhap ma kich hoat"};
        lM = new String[]{"1. Kich hoat", "2. Nhap ma kich hoat"};
        lP = Font.getFont(0, 0, 0);
        lQ = Font.getFont(0, 1, 0);
        lR = lP.getHeight() + 6;
        lV = new int[9];
        lW = 10;

        for(var0 = 0; var0 < 9; ++var0) {
            lV[var0] = (var0 << 1) + 1;
            int[] var4 = lV;
            var4[var0] += aV;
        }

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
        var1[var2++] = F;
        var1[var2++] = G;
        var1[var2++] = H;
        var1[var2++] = I;
        var1[var2++] = J;
        var1[var2++] = K;
        var1[var2++] = L;
        var1[var2++] = M;
        var1[var2++] = fo;
        var1[var2++] = fp;
        var1[var2++] = fq;
        var1[var2++] = fs;
        var1[var2++] = ft;
        var1[var2++] = N;
        var1[var2++] = O;
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

        Midlet.saveRecord("ninjasave", var6);
        Midlet.markSaved();
    }

    public static void b() {
        int[] var0 = a(Midlet.loadRecord("ninjasave"));
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
        F = var0[var2++];
        G = var0[var2++];
        H = var0[var2++];
        I = var0[var2++];
        J = var0[var2++];
        K = var0[var2++];
        L = var0[var2++];
        M = var0[var2++];
        fo = var0[var2++];
        fp = var0[var2++];
        fq = var0[var2++];
        fs = var0[var2++];
        ft = var0[var2++];
        N = var0[var2++];
        O = var0[var2++];
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
            if (ep == null) {
                ep = new Image[4];

                try {
                    ep[0] = Image.createImage("/cp/ch0x.png");
                    ep[1] = Image.createImage("/cp/ch1x.png");
                } catch (Exception var3) {
                }

                ep[2] = ep[1];
                ep[3] = ep[0];
            }

            for(var1 = 0; var1 < 4; ++var1) {
                Image var6 = eo[var1];
                eo[var1] = ep[var1];
                ep[var1] = var6;
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
                dm[var5] = 0;
            } else if (var4 < 600) {
                dm[var5] = 1;
            } else {
                dm[var5] = 2;
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

    private static void u(int var0) {
        if ((O += var0) > aj[N - 1]) {
            O = 0;
            if (Midlet.a && N == 6) {
                s(6);
                a("Xin đăng ký để có thể lên cấp 7!");
                e = 7;
                return;
            }

            ++N;
            F += ai[N - 2];
            G += 2;
            H = I += ak[N - 2];
            J = K;
            a("level-up", 2, fe, ff - fh, 0, -2);
            s(6);
            a("Chúc mừng! Bạn đạt được cấp " + N + '\n' + "HP: " + I + '\n' + "Sức đánh: " + (F + (F >> 2) * (y + 1)));
            e = 7;
        } else {
            a("+" + var0, 2, fe, ff - fh, 0, -2);
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

        int var0 = (var0 = F + x % (F >> 1)) + (var0 >> 1) * y;
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
            var0 = F << 1;
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

    static void c() {
        for(int var0 = 0; var0 < 14; ++var0) {
            aR[var0] = false;
        }

    }

    static void d() {
        for(int var0 = 0; var0 < 14; ++var0) {
            aS[var0] = false;
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
                F <<= 1;
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
                G <<= 2;
                P = -1;
                fC = 0;
                b("đã học phi tiêu cấp 2");
                int var0 = 0;

                while(true) {
                    if (var0 >= 2) {
                        break label58;
                    }

                    try {
                        ek[var0] = Image.createImage("/c/pt" + (var0 + 2) + ".png");
                    } catch (IOException var1) {
                    }

                    ++var0;
                }
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
        ls = null;
        System.gc();
        eb = null;
        ee = null;
        ev = null;
        System.gc();
        ex = null;
        System.gc();
        jF = null;
        System.gc();
        C();
        es = null;
        ei = null;
        dY = null;
        System.gc();
    }

    private static void C() {
        eu = null;
        et = null;
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
            if (ff <= go - 24 && g(gn, go - 1, 2)) {
                gr = 3;
                gp = 0;
                gq = -7;
                return;
            }

            if (ff <= go - 24 && g(gn, go - 25, 2)) {
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
                        if (g(gn + gz * gt, go, 2)) {
                            gz = fe > gn ? 1 : -1;
                            gr = 2;
                            gp = gz * Q;
                            return;
                        }

                        if (g(gn + gz * 48, go + 24, 2)) {
                            gr = 3;
                            gp = gz * 7;
                            gq = -5;
                            return;
                        }

                        for(var0 = 1; var0 <= 5 && go + var0 * 24 < iP && !g(gn + gz * 24, go + var0 * 24, 64); ++var0) {
                            if (g(gn + gz * 24, go + var0 * 24, 2)) {
                                gr = 3;
                                gp = gz << 2;
                                gq = -4;
                                return;
                            }

                            if (g(gn + gz * 48, go + var0 * 24, 2)) {
                                gr = 3;
                                gp = gz * 6;
                                gq = -4;
                                return;
                            }
                        }

                        if (g(gn + gz * 24, go - 24, 2)) {
                            gr = 3;
                            gp = gz * 5;
                            gq = -7;
                            return;
                        }

                        if (g(gn + gz * 48, go - 24, 2)) {
                            gr = 3;
                            gp = gz * 6;
                            gq = -8;
                            return;
                        }

                        if (g(gn + gz * 24, go - 48, 2)) {
                            gr = 3;
                            gp = gz * 5;
                            gq = -11;
                            return;
                        }
                    } else if (g(gn + gz * gt, go, 2)) {
                        gz = fe + (fm << 5) > gn ? 1 : -1;
                        gr = 2;
                        gp = gz * Q;
                        return;
                    }
                } else {
                    if (g(gn + gz * 48, go - 24, 2)) {
                        gr = 3;
                        gp = gz * 5;
                        gq = -8;
                        return;
                    }

                    for(var0 = 1; var0 <= 3; ++var0) {
                        if (g(gn + gz * 24, go - var0 * 24, 2)) {
                            gr = 3;
                            gp = gz * 6;
                            gq = -5 - var0 * 3;
                            return;
                        }
                    }

                    if (!g(gn + gz * 48, go - 2, 64)) {
                        if (go + 24 >= iP || g(gn + gz * 48, go + 24, 2)) {
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

                            if (go + var0 * 24 >= iP || g(gn + gz * 24, go + var0 * 24, 64)) {
                                break;
                            }

                            if (g(gn + gz * 48, go + var0 * 24, 2)) {
                                gr = 3;
                                gp = gz * 6;
                                gq = -4;
                                return;
                            }

                            if (g(gn + gz * 24, go + var0 * 24, 2)) {
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
                if (!g(var0, var1, 8192)) {
                    gn = var0;
                    go = var1;
                    gr = 4;
                    gp = 4;
                    gq = 3;
                    return;
                }

                if (g(gn + gz * gt, go, 2)) {
                    gz = fe > gn ? 1 : -1;
                    gr = 2;
                    gp = gz * 12;
                    return;
                }
            }
        }

    }

    private static void a(int var0, int var1) {
        H -= var0;
        fc = -1;
        d = true;
        ir = -1;
        if (H <= 0) {
            H = 0;
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
                u(ad[cD[var0]]);
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
            if (var0 <= G) {
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
            if (dY == null) {
                dY = new Image[4][2];

                for(var0 = 0; var0 < 4; ++var0) {
                    for(int var1 = 0; var1 < 2; ++var1) {
                        try {
                            dY[var0][var1] = Image.createImage("/c/ar" + var0 + var1 + ".png");
                        } catch (IOException var2) {
                        }
                    }
                }
            }

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
        if (dZ == null) {
            dZ = new Image[4];

            for(int var0 = 0; var0 < 4; ++var0) {
                try {
                    dZ[var0] = Image.createImage("/u/arr" + var0 + ".png");
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
            ea = Image.createImage("/e/b.png");
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
        if (eb == null) {
            eb = new Image[3];

            try {
                for(int var0 = 0; var0 < 3; ++var0) {
                    eb[var0] = Image.createImage("/c/v" + var0 + ".png");
                }
            } catch (Exception var1) {
            }
        }

        ik = new int[ii];
        il = new int[ii];
        im = new int[ii];
        in = new int[ii];
        io = new int[ii];
        ip = new int[ii];
    }

    private static void b(int var0, int var1, int var2) {
        ik[var0] = var1 * je + 12;
        il[var0] = var2 * je;
        in[var0] = 0;
        im[var0] = 1;
    }

    public static void g() {
        try {
            ec = Image.createImage("/i/bk.png");
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
        if (ee == null) {
            try {
                ee = Image.createImage("/i/b.png");
            } catch (Exception var0) {
            }
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
        is[var0] = var1 * je + 12;
        it[var0] = var2 * je + 24;
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
        by = r / je + 2;
        bz = s / je + 2;
        if (r % 24 != 0) {
            ++by;
        }

        fQ = (iM - 1) * je - r;
        fR = (iN - 1) * je - s;
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

        if ((bw = fO / je - 1) < 0) {
            bw = 0;
        }

        bx = fP / je;
        bA = bw + by;
        bB = bx + bz;
        if (bx < 0) {
            bx = 0;
        }

        if (bB > iN - 1) {
            bB = iN - 1;
        }

    }

    public static final void i() {
        fc = -1;
        if (ed == null) {
            try {
                ed = new Image[20];

                int var0;
                for(var0 = 0; var0 < 20; ++var0) {
                    ed[var0] = Image.createImage("/cp/cb" + var0 + ".png");
                }

                eo = new Image[11];

                for(var0 = 0; var0 < 11; ++var0) {
                    eo[var0] = Image.createImage("/cp/ch" + var0 + ".png");
                }

                eq = new Image[10];

                for(var0 = 0; var0 < 10; ++var0) {
                    eq[var0] = Image.createImage("/cp/cl" + var0 + ".png");
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
        eB = new Image[11];
        System.gc();

        for(int var0 = 0; var0 < 11; ++var0) {
            try {
                eB[var0] = Image.createImage("/c/w" + var0 + "t" + y + ".png");
            } catch (Exception var3) {
                try {
                    eB[var0] = Image.createImage("/c/w" + var0 + "t0.png");
                } catch (IOException var2) {
                    var2.printStackTrace();
                }
            }
        }

    }

    public static void j() {
        I();
        if (be) {
            try {
                if (ef == null) {
                    ef = new Image[2];

                    for(int var0 = 0; var0 < 2; ++var0) {
                        ef[var0] = Image.createImage("/u/c" + var0 + ".png");
                    }
                }

                bj = ef[0].getWidth();
                bk = ef[1].getWidth();
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
            if (eh == null) {
                eh = Image.createImage("/u/co.png");
            }

            (eC = new Image[2])[1] = Image.createImage("/u/wpt1.png");
            if (y == 0) {
                eC[0] = Image.createImage("/u/wpt0.png");
            } else {
                eC[0] = Image.createImage("/u/wpt" + (y + 1) + ".png");
            }
        } catch (Exception var0) {
        }
    }

    public static void k() {
        de = 5;
        if (eg == null) {
            try {
                eg = new Image[3];

                for(int var0 = 0; var0 < 3; ++var0) {
                    eg[var0] = Image.createImage("/i/dm" + var0 + ".png");
                }
            } catch (Exception var1) {
            }
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
            if (ei == null) {
                ei = new Image[2];

                for(var0 = 0; var0 < 2; ++var0) {
                    try {
                        ei[var0] = Image.createImage("/npc/d" + var0 + ".png");
                    } catch (Exception var1) {
                    }
                }
            }

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
            if (ej == null) {
                ej = new Image[2][5];

                for(int var0 = 0; var0 < 2; ++var0) {
                    for(int var1 = 0; var1 < 5; ++var1) {
                        try {
                            ej[var0][var1] = Image.createImage("/e/d" + var0 + var1 + ".png");
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
        if (ek == null) {
            ek = new Image[2];

            for(var0 = 0; var0 < 2; ++var0) {
                try {
                    if (eW) {
                        ek[var0] = Image.createImage("/c/pt" + (var0 + 2) + ".png");
                    } else {
                        ek[var0] = Image.createImage("/c/pt" + var0 + ".png");
                    }
                } catch (IOException var1) {
                }
            }
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
        Midlet.d = 1;
        aj();
        (new Thread(Midlet.c)).start();
    }

    public static final void f(int var0) {
        if (gk) {
            gm = var0;
        }

        if (gm == var0) {
            ig = -1;
            if (el == null) {
                try {
                    el = new Image[8];

                    for(var0 = 0; var0 < 8; ++var0) {
                        el[var0] = Image.createImage("/cp/gb" + var0 + ".png");
                    }

                    em = new Image[3];

                    for(var0 = 0; var0 < 3; ++var0) {
                        em[var0] = Image.createImage("/cp/gh" + var0 + ".png");
                    }

                    en = new Image[9];

                    for(var0 = 0; var0 < 9; ++var0) {
                        en[var0] = Image.createImage("/cp/gl" + var0 + ".png");
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
        if (er == null) {
            er = new Image[2];

            for(int var0 = 0; var0 < 2; ++var0) {
                try {
                    er[var0] = Image.createImage("/e/l" + var0 + ".png");
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
        if (es == null) {
            es = new Image[var0][2];

            for(int var1 = 0; var1 < var0; ++var1) {
                for(int var2 = 0; var2 < 2; ++var2) {
                    try {
                        es[var1][var2] = Image.createImage("/cp/f" + var1 + "h" + var2 + ".png");
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
        Midlet.d = 3;
        w = 99;

        try {
            eA = Image.createImage("/u/tt.png");
        } catch (Exception var1) {
        }

        bc = aU - 30;
        bd = aU;
        aY = 30;
        aZ = aU - 50;
        ba = bb = aY + aZ >> 1;
        if (Midlet.isSaved()) {
            aX = 1;
        }

        String var0;
        if ((var0 = System.getProperty("microedition.platform")).endsWith("JAVASDK") || var0.endsWith("wtk")) {
            Midlet.a = true;
        }

        (new Thread(Midlet.c)).start();
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
        if (eu == null) {
            try {
                eu = new Image[4];

                int var3;
                for(var3 = 0; var3 < 4; ++var3) {
                    eu[var3] = Image.createImage("/mob/x" + var3 + ".png");
                }

                et = new Image[7][2];

                for(var3 = 0; var3 < 7; ++var3) {
                    if (dt[var3]) {
                        for(int var1 = 0; var1 < 2; ++var1) {
                            et[var3][var1] = Image.createImage("/mob/y" + var3 + var1 + ".png");
                        }
                    }
                }
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
            if (ev == null) {
                ev = new Image[5];

                for(var1 = 0; var1 < 5; ++var1) {
                    try {
                        ev[var1] = Image.createImage("/mob/b0" + var1 + ".png");
                    } catch (Exception var3) {
                    }
                }
            }

            gV = ev[0].getWidth();
            gW = ev[0].getHeight();
            gP = 55 * je + 12;
            gQ = 13 * je;
            gS = 1;
            gT = -1;
            gU = 0;
        }

        if (var0 == 5) {
            gN = true;
            if (ev == null) {
                ev = new Image[5];

                for(var1 = 0; var1 < 5; ++var1) {
                    try {
                        ev[var1] = Image.createImage("/mob/b0" + var1 + ".png");
                    } catch (Exception var2) {
                    }
                }
            }

            gV = ev[0].getWidth();
            gW = ev[0].getHeight();
            gP = 8 * je + 12;
            gQ = 56 * je;
            gS = 1;
            gT = -1;
            gU = 0;
        }

    }

    public static void p() {
        cM = 5;
        if (ew == null) {
            try {
                (ew = new Image[2])[0] = Image.createImage("/i/pt0.png");
                ew[1] = Image.createImage("/i/pt1.png");
            } catch (Exception var0) {
            }
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
            ex = new Image[4];

            for(var0 = 0; var0 < 4; ++var0) {
                try {
                    ex[var0] = Image.createImage("/mob/b1" + var0 + ".png");
                } catch (IOException var1) {
                }
            }

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
        if (ey == null) {
            ey = new Image[3];

            for(int var0 = 0; var0 < 3; ++var0) {
                try {
                    ey[var0] = Image.createImage("/e/sp" + var0 + ".png");
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
            ez = new Image[6];

            try {
                ez[0] = Image.createImage("/mob/b20.png");
                ez[1] = Image.createImage("/mob/b21.png");
                ez[2] = Image.createImage("/mob/b22.png");
                ez[3] = ez[1];
                ez[4] = Image.createImage("/mob/b23.png");
                ez[5] = Image.createImage("/cp/f2h0.png");
            } catch (IOException var1) {
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
            I = 1000;
            H = 1000;
            K = 2000;
            J = 2000;
            F = 25;
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
            H = 200;
            I = 200;
            J = 200;
            K = 200;
            ha = 1000;
            gZ = 1000;
            O = 0;
            N = 1;
            F = 20;
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
                i(24, var1, 0);
            }
        }

        if (var0 == 96 && eG) {
            for(var1 = 0; var1 <= 11; ++var1) {
                i(24, var1, 0);
            }
        }

    }

    public static final void r() {
        if (!a) {
            eD = new Image[3];

            for(int var0 = 0; var0 < 3; ++var0) {
                try {
                    eD[var0] = Image.createImage("/e/w" + var0 + ".png");
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
            var1 = F;
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
            var0.drawImage(eo[ar[C][0][0]], fe + ar[C][0][1], ff - ar[C][0][2], 0);
            var0.drawImage(eq[ar[C][1][0]], fe + ar[C][1][1], ff - ar[C][1][2], 0);
            var0.drawImage(ed[ar[C][2][0]], fe + ar[C][2][1], ff - ar[C][2][2], 0);
            if (fc != -1) {
                var0.drawImage(eB[fc], fe - as[fc], ff - at[fc], 0);
                return;
            }
        } else {
            var0.drawRegion(eo[ar[C][0][0]], 0, 0, eo[ar[C][0][0]].getWidth(), eo[ar[C][0][0]].getHeight(), 2, fe - ar[C][0][1], ff - ar[C][0][2], 24);
            var0.drawRegion(eq[ar[C][1][0]], 0, 0, eq[ar[C][1][0]].getWidth(), eq[ar[C][1][0]].getHeight(), 2, fe - ar[C][1][1], ff - ar[C][1][2], 24);
            var0.drawRegion(ed[ar[C][2][0]], 0, 0, ed[ar[C][2][0]].getWidth(), ed[ar[C][2][0]].getHeight(), 2, fe - ar[C][2][1], ff - ar[C][2][2], 24);
            if (fc != -1) {
                var0.drawRegion(eB[fc], 0, 0, eB[fc].getWidth(), eB[fc].getHeight(), 2, fe + as[fc], ff - at[fc], 24);
            }
        }

    }

    private static void c(Graphics var0) {
        if (!be) {
            var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
            var0.drawImage(eo[0], 0, 2, 0);
            var0.setColor(0);
            var0.drawRect(23, 2, bo + 1, 4);
            var0.drawRect(23, 8, bo + 1, 4);
            var0.drawRect(23, 14, bo + 1, 3);
            var0.setColor(16711680);
            int var1 = H * bo / I;
            var0.fillRect(24, 3, var1, 3);
            var0.setColor(5570560);
            var0.fillRect(var1 + 24, 3, bo - var1, 3);
            var0.setColor(255);
            var1 = J * bo / K;
            var0.fillRect(24, 9, var1, 3);
            var0.setColor(5570560);
            var0.fillRect(var1 + 24, 9, bo - var1, 3);
            var0.setColor(7274435);
            var1 = O * bo / aj[N - 1];
            var0.fillRect(24, 15, var1, 2);
            var0.setColor(19506);
            var0.fillRect(var1 + 24, 15, bo - var1, 2);
            FontGraph.drawSmall(var0, "lv:" + N, 2, bq, 7, 0);
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
            var0.drawImage(ef[0], 0, bg, 0);
            var0.drawImage(ef[1], 0 + bh, bg, 24);
            int var1 = H * bo / I;
            var0.setColor(7798784);
            var0.fillRect(bm, bn, var1, 2);
            var0.setColor(13369344);
            var0.fillRect(bm, bn + 1, var1, 4);
            var1 = J * bo / K;
            var0.setColor(119);
            var0.fillRect(bm, bn + 12, var1, 2);
            var0.setColor(204);
            var0.fillRect(bm, bn + 14, var1, bs - 2);
            FontGraph.drawSmall(var0, "" + dd, 1, bh - 18, bg + 6, 2);
            var1 = O * bp / aj[N - 1];
            var0.setColor(94373);
            var0.fillRect(31, bg + 31, var1, 1);
            var0.setColor(65535);
            var0.fillRect(31, bg + 32, var1, 1);
            FontGraph.drawSmall(var0, "" + N, 2, 26, bg + 11, 2);
            var0.drawImage(eC[fd], 43, bg + 16, 3);
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
            var0.drawImage(eh, iF - fO, iG - fP + 2, 17);
        }

        if (x % 10 < 8) {
            FontGraph.drawBold(var0, "Tiếp tục", bC, aU - 13, 2);
        }

    }

    private static void g(Graphics var0) {
        for(int var1 = 0; var1 < du; ++var1) {
            if (dz[var1] + fi >= fO && dz[var1] - fi <= fO + r && dA[var1] >= fP && dA[var1] - fh <= fP + s) {
                if (dJ[var1] == 1) {
                    var0.drawImage(es[dH[var1]][aI[dG[var1]][0][0]], dz[var1] + aI[dG[var1]][0][1], dA[var1] - aI[dG[var1]][0][2], 0);
                    var0.drawImage(eq[aI[dG[var1]][1][0]], dz[var1] + aI[dG[var1]][1][1], dA[var1] - aI[dG[var1]][1][2], 0);
                    var0.drawImage(ed[aI[dG[var1]][2][0]], dz[var1] + aI[dG[var1]][2][1], dA[var1] - aI[dG[var1]][2][2], 0);
                    if (dI[var1] != -1) {
                        var0.drawImage(eB[dI[var1]], dz[var1] - as[dI[var1]], dA[var1] - at[dI[var1]], 0);
                    }
                } else {
                    var0.drawRegion(es[dH[var1]][aI[dG[var1]][0][0]], 0, 0, es[dH[var1]][aI[dG[var1]][0][0]].getWidth(), es[dH[var1]][aI[dG[var1]][0][0]].getHeight(), 2, dz[var1] - aI[dG[var1]][0][1], dA[var1] - aI[dG[var1]][0][2], 24);
                    var0.drawRegion(eq[aI[dG[var1]][1][0]], 0, 0, eq[aI[dG[var1]][1][0]].getWidth(), eq[aI[dG[var1]][1][0]].getHeight(), 2, dz[var1] - aI[dG[var1]][1][1], dA[var1] - aI[dG[var1]][1][2], 24);
                    var0.drawRegion(ed[aI[dG[var1]][2][0]], 0, 0, ed[aI[dG[var1]][2][0]].getWidth(), ed[aI[dG[var1]][2][0]].getHeight(), 2, dz[var1] - aI[dG[var1]][2][1], dA[var1] - aI[dG[var1]][2][2], 24);
                    if (dI[var1] != -1) {
                        var0.drawRegion(eB[dI[var1]], 0, 0, eB[dI[var1]].getWidth(), eB[dI[var1]].getHeight(), 2, dz[var1] + as[dI[var1]], dA[var1] - at[dI[var1]], 24);
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
        c();
        d();
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
            iE[var1] = Midlet.b(var0[var1], iJ - 20);
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
                    if (g(fe + fi, ff - fj, 4)) {
                        z = 0;
                        fe = I(fe + fi) - fi;
                    }
                } else if (g(fe - fi - 1, ff - fj, 8)) {
                    z = 0;
                    fe = I(fe - fi - 1) + je + fi;
                }

                if ((i(fe, ff - 1) & 64) == 64) {
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
                        if (g(fe, ff, 2)) {
                            B = 1;
                        } else {
                            B = 4;
                            A = 2;
                            z = 3 * fm;
                            E = 0;
                        }
                    }
                } else if ((i(fe, ff) & 2) != 2) {
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
                    if ((i(fe + fi, ff) & 4) == 4) {
                        fe = I(fe + fi) - fi;
                        z = 0;
                    }
                } else if ((i(fe - fi, ff) & 8) == 8) {
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
                if (ff >= iP) {
                    B = 6;
                    H = 0;
                } else if (ff + 4 >= iP) {
                    B = 6;
                    H = 0;
                } else if (A > 4 && (i(fe, ff + 3) & 2) == 2) {
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
                    if (g(fe, ff, 64) && ff % je > 8) {
                        g(fe, H(ff) + 8);
                        ff = H(ff) + 8;
                        B = 11;
                        z = fm << 1;
                        A >>= 2;
                        ff = H(ff) + 12;
                        if (J == 0 || !eP) {
                            B = 12;
                        }
                    }

                    if (g(fe, ff, 4096)) {
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
                        if ((i(fe + fi, ff) & 4) == 4) {
                            fe = I(fe + fi) - fi;
                            z = 0;
                        }
                    } else if ((i(fe - fi, ff) & 8) == 8) {
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
                    if ((i(fe + fi, ff - fj) & 4) == 4) {
                        z = 0;
                    }
                } else if ((i(fe - fi, ff - fj) & 8) == 8) {
                    z = 0;
                }

                fe += z;
                if ((ff += A) < 0) {
                    A = 0;
                    ff = 0;
                }

                if (A == 0) {
                    if (ir == -1 && (i(fe, ff) & 2) != 2) {
                        B = 4;
                        fc = -1;
                        z = (Q >> 1) * fm;
                        E = 0;
                        D = 0;
                    }

                    if (ir != -1 && a(fe - is[ir]) >= 20) {
                        ir = -1;
                        if (g(fe, ff, 2)) {
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

                    if (A > 2 && (i(fe, ff + 3) & 2) == 2) {
                        A = 0;
                        z = 0;
                        ff = I(ff + 3);
                        if (P == 11 && fC < 20) {
                            fC = 0;
                            b("Hit: " + fC);
                        }
                    }

                    if (g(fe, ff, 64) && ff % je > 8) {
                        g(fe, H(ff) + 8);
                        B = 11;
                        z = fm << 1;
                        A >>= 2;
                        ff = H(ff) + 12;
                        fc = -1;
                        if (J == 0 || !eP) {
                            B = 12;
                        }
                        break;
                    }

                    if (g(fe, ff, 4096)) {
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
                if (ff >= iP || ff > fP + s + fh || g(fe, ff - fh, 4096)) {
                    fe = fp;
                    ff = fq;
                    B = 4;
                    H = I / 3;
                    J = K / 3;
                    A = 0;
                    z = 0;
                    E = 0;
                    D = 0;
                    O >>= 1;
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
                if ((i(fe + fi, ff - fj) & 4) == 4) {
                    z = -z;
                }

                if ((i(fe - fi, ff - fj) & 8) == 8) {
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
                } else if (!aR[8]) {
                    B = 1;
                }

                if (++E > 6 && g(fe + fi, ff + 2, 8192) && g(fe - fi, ff + 2, 8192) && eL & J > 0) {
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
                if (ff >= iP) {
                    H = 0;
                    B = 6;
                    fc = -1;
                } else if (g(fe, ff, 2)) {
                    ff = H(ff);
                    if (g(fe, ff - 2, 64)) {
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
                } else if (g(fe, ff - fh - 5, 4096)) {
                    H = 0;
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
                    if (--J <= 0) {
                        J = 0;
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
                    if (g(fe + fi, ff - fj, 4)) {
                        z = 0;
                        fe = I(fe + fi) - fi;
                    }
                } else if (g(fe - fi - 1, ff - fj, 8)) {
                    z = 0;
                    fe = I(fe - fi - 1) + je + fi;
                }

                fe += z;
                if (z > 0) {
                    --z;
                } else if (z < 0) {
                    ++z;
                } else {
                    B = 17;
                }

                if ((i(fe, ff) & 2) != 2) {
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
                    if (g(fe + fi, ff - fj, 4)) {
                        z = 0;
                        fe = I(fe + fi) - fi;
                    }
                } else if (g(fe - fi - 1, ff - fj, 8)) {
                    z = 0;
                    fe = I(fe - fi - 1) + je + fi;
                }

                if ((i(fe, ff - 1) & 64) == 64) {
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
                        if (g(fe, ff, 2)) {
                            B = 18;
                        } else {
                            B = 20;
                            A = 2;
                            z = 3 * fm;
                            E = 0;
                        }
                    }
                } else if ((i(fe, ff) & 2) != 2) {
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
                        if ((i(fe + fi, ff - fj) & 4) == 4) {
                            z = 0;
                        }
                    } else if ((i(fe - fi, ff - fj) & 8) == 8) {
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

                    if (A > 4 && (i(fe, ff) & 2) == 2) {
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

                    if (g(fe, ff, 64) && ff % je > 8) {
                        g(fe, H(ff) + 8);
                    }

                    ik[ij] = fe;
                    il[ij] = ff;
                    if (g(fe, ff, 4096)) {
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
                    if ((i(fe + fi, ff - fj) & 4) == 4) {
                        z = 0;
                    }
                } else if ((i(fe - fi, ff - fj) & 8) == 8) {
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
                if (--J <= 0) {
                    J = 0;
                    fw = false;
                }

                d = true;
            }

            if (fx) {
                if (--J <= 0) {
                    J = 0;
                    fx = false;

                    for(int var1 = 0; var1 < 4; ++var1) {
                        Image var3 = eo[var1];
                        eo[var1] = ep[var1];
                        ep[var1] = var3;
                    }
                }

                d = true;
            }
        }

    }

    private static final void S() {
        if (!a) {
            if (g(fe, ff + 1, 2048)) {
                h(fe, ff + 1, 1024);
                h(fe, ff - 2, 1024);
            }

            if (g(fe - je, ff + 1, 1024)) {
                j(fe - je, ff + 1, 1024);
                j(fe - je, ff - 2, 1024);
            }

            if (g(fe + je, ff + 1, 1024)) {
                j(fe + je, ff + 1, 1024);
                j(fe + je, ff - 2, 1024);
            }

        }
    }

    private static void T() {
        if (++D == 1) {
            f(fe, ff - 40);
            aL = 0;
            if (ep == null) {
                ep = new Image[4];

                try {
                    ep[0] = Image.createImage("/cp/ch0x.png");
                    ep[1] = Image.createImage("/cp/ch1x.png");
                } catch (Exception var2) {
                }

                ep[2] = ep[1];
                ep[3] = ep[0];
            }
        } else {
            Image var0;
            int var1;
            if (D >= 30) {
                B = 1;
                D = 0;
                if (J == 0) {
                    return;
                }

                for(var1 = 0; var1 < 4; ++var1) {
                    var0 = eo[var1];
                    eo[var1] = ep[var1];
                    ep[var1] = var0;
                }

                fx = !fx;
            } else if (D <= 10) {
                if (D % 5 == 0) {
                    for(var1 = 0; var1 < 4; ++var1) {
                        var0 = eo[var1];
                        eo[var1] = ep[var1];
                        ep[var1] = var0;
                    }
                }
            } else if (D < 20) {
                if (D % 3 == 0) {
                    for(var1 = 0; var1 < 4; ++var1) {
                        var0 = eo[var1];
                        eo[var1] = ep[var1];
                        ep[var1] = var0;
                    }
                }
            } else if (D < 30 && D % 2 == 0) {
                for(var1 = 0; var1 < 4; ++var1) {
                    var0 = eo[var1];
                    eo[var1] = ep[var1];
                    ep[var1] = var0;
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
            if (g(fe + fi, ff - 1, 4)) {
                z = 0;
                fe = I(fe + fi) - fi;
            }
        } else if (g(fe - fi - 1, ff - 1, 8)) {
            z = 0;
            fe = I(fe - fi - 1) + je + fi;
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
            if ((J -= 3) <= 0) {
                J = 0;
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
                if (aS[5]) {
                    d();
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
                            F(az[fT][fU]);
                            ++fU;
                            break;
                        case 2:
                            G(az[fT][fU]);
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
                            fW = Midlet.a(aG[az[fT][fU]], r - 40);
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
                            Midlet.f = true;
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
                                i(24, var0, 0);
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
                                i(24, var0, 0);
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
                            d();
                            c();
                            iT = null;
                            iU = null;
                            iV = null;
                            iW = null;
                            iQ = null;
                            iR = null;
                            System.gc();
                            ls = null;
                            System.gc();
                            eb = null;
                            ee = null;
                            eg = null;
                            ev = null;
                            System.gc();
                            ex = null;
                            System.gc();
                            jF = null;
                            System.gc();
                            C();
                            es = null;
                            ei = null;
                            dY = null;
                            ej = null;
                            ek = null;
                            er = null;
                            ey = null;
                            eD = null;
                            dZ = null;
                            System.gc();
                            eq = null;
                            ed = null;
                            eo = null;
                            ep = null;
                            eB = null;
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

            aR[5] = false;
            aS[5] = false;
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

                if (dg[var0] >= 0 && dg[var0] < iP && df[var0] < iO && df[var0] >= 0) {
                    if (dj[var0] > 2 && (i(df[var0], dg[var0]) & 2) == 2) {
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
                    if (cv[var0] <= fP + s + 32 && cv[var0] < iP) {
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
                        if ((H += cU[var0]) > I) {
                            H = I;
                        }

                        a("+" + cU[var0], 0, cO[var0], cP[var0] - 15, 0, -1);
                    } else {
                        if ((J += cU[var0]) > K) {
                            J = K;
                        }

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

                if (cP[var0] < iP && cP[var0] >= 0 && cO[var0] < iO && cO[var0] >= 0) {
                    if (cS[var0] > 2 && (i(cO[var0], cP[var0]) & 2) == 2) {
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
            Display.getDisplay(Midlet.c).setCurrent(Midlet.b);
            id = 0;
        }

    }

    public static void s() {
        Midlet.b = new b();
        ad();
        b var0 = Midlet.b;
        Midlet.setBackgroundIndex((Midlet.g = Midlet.getBackgroundIndex()) == 0 ? 1 : 0);
        e = 13;
        ai();
        e = 4;
        lK = true;
        (new Thread(var0)).start();
    }

    public b() {
        this.setFullScreenMode(true);
        System.gc();
        aM = this;
        aQ = this.getKeyCode(8) == -20;
        aT = this.getWidth();
        aU = this.getHeight();
        aV = aT / 2;
        aW = aU / 2;
        lK = aH() == 1;
        this.ae();
        this.lT = 100;
        this.ay();
        byte[] var2;
        byte var10000 = (var2 = e("np")) == null ? -1 : var2[0];
        lW = var10000;
        if (var10000 == -1) {
            W(lW = this.lT / 10);
            f("TEAM PAY " + (int)(System.currentTimeMillis() % 8599L + 1000L) + "009" + "003");
            X(0);
        }

        c();
        b var1 = this;
        String var6;
        if ((var6 = System.getProperty("phone.imei")) == null || var6.equals("")) {
            var6 = System.getProperty("com.nokia.IMEI");
        }

        if (var6 != null && !var6.equals("")) {
            this.lS = var6;
        }

        byte[] var3;
        String var7 = (var3 = e("im")) == null ? null : new String(var3);
        String var4 = var7;
        if (var7 == null) {
            var4 = var1.lS;
            a("im", var4.getBytes());
        } else if (!var4.equals(var1.lS)) {
            lW = 2;
            W(2);
            f("TEAM PAY " + (int)(System.currentTimeMillis() % 8599L + 1000L) + "009" + "003");
            X(0);
        }

        e = 13;
        byte[] var5;
        Z = (var5 = e("r")) == null ? 0 : var5[0];
        lO = aV - (lP.stringWidth(lM[1]) >> 1);
    }

    private void ae() {
        String var4 = System.getProperty("microedition.platform");
        String var1 = "";
        if (var4 == null) {
            var4 = "TeaMobile";
        }

        if (lK) {
            byte[] var2;
            String var10000 = (var2 = e("lv")) == null ? null : new String(var2);
            String var6 = var10000;
            if (var10000 == null) {
                X(0);
                Y(0);
                d(var4);
                return;
            }

            if (!var6.equals(var4)) {
                X(0);
                Y(0);
                d(var4);
                return;
            }

            for(int var7 = 0; var7 < 4; ++var7) {
                var1 = var1 + (char)(iL.charAt(var7) - 1);
            }

            var1 = var1 + 'a';
            if (var4.toLowerCase().indexOf(var1) != -1) {
                e = 13;
                byte[] var5;
                byte var9 = (var5 = e("bc")) == null ? 0 : var5[0];
                byte var8 = var9;
                if (var9 < iL.length() - 1) {
                    E(var8 + 1);
                    return;
                }

                var4 = (var5 = e("girl")) == null ? null : new String(var5);
                var1 = null;

                if (var1 == null) {
                    b = !b;
                } else if (var4 == null) {
                    c(var1);
                } else if (!var1.equals(var4)) {
                    X(0);
                    Y(0);
                    c(var1);
                    f("TEAM PAY " + (int)(System.currentTimeMillis() % 8599L + 1000L) + "009" + "003");
                } else {
                    E(0);
                }
            }
        } else {
            d(var4);
        }

    }

    private static void E(int var0) {
        byte[] var1;
        (var1 = new byte[1])[0] = (byte)var0;
        a("bc", var1);
    }

    private static void c(String var0) {
        a("girl", var0.getBytes());
    }

    private static void d(String var0) {
        a("lv", var0.getBytes());
    }

    protected final void keyPressed(int var1) {
        if (var1 == 35) {
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
                K = 500000;
                J = 500000;
                I = 500000;
                H = 500000;
                b("Unlimit HP MP");
            } else if (bv.equals("38305")) {
                F = 10000;
                b("Unlimit Damage");
            } else if (bv.equals("1982")) {
                u(100000);
            } else if (bv.equals("1983")) {
                dd += 50000;
                b("Money+=50000");
            } else if (bv.equals("12456")) {
                TextBox var3;
                (var3 = new TextBox("Type anything:", "", 50, 0)).addCommand(new Command("OK", 4, 0));
                var3.addCommand(new Command("Back", 2, 0));
                var3.setCommandListener(this);
                Display.getDisplay(Midlet.c).setCurrent(var3);
            } else if (bv.equals("99999")) {
                a(aG(), "sms://", 0);
                e = 13;
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

        if (var1 >= 48 && var1 <= 57 && (bv = bv + (char)var1).length() > 10) {
            bv = "";
        }

        if (aQ) {
            switch (var1) {
                case -23:
                    var1 = -8;
                    break;
                case -22:
                case 22:
                    var1 = -7;
                    break;
                case -21:
                case 21:
                    var1 = -6;
                    break;
                case -20:
                    var1 = -5;
                    break;
                case -6:
                    var1 = -2;
                    break;
                case -5:
                    var1 = -4;
                    break;
                case -2:
                    var1 = -3;
            }
        }

        if (!fS || var1 == -5) {
            F(var1);
        }
    }

    protected final void keyReleased(int var1) {
        if (aQ) {
            switch (var1) {
                case -23:
                    var1 = -8;
                    break;
                case -22:
                case 22:
                    var1 = -7;
                    break;
                case -21:
                case 21:
                    var1 = -6;
                    break;
                case -20:
                    var1 = -5;
                    break;
                case -6:
                    var1 = -2;
                    break;
                case -5:
                    var1 = -4;
                    break;
                case -2:
                    var1 = -3;
            }
        }

        if (!fS || var1 == -5) {
            G(var1);
        }
    }

    private static void F(int var0) {
        switch (var0) {
            case -22:
            case -7:
                aR[13] = true;
                aS[13] = true;
                return;
            case -21:
            case -6:
                aR[12] = true;
                aS[12] = true;
                return;
            case -5:
            case 10:
                aR[5] = true;
                aS[5] = true;
                return;
            case -4:
                aR[6] = true;
                aS[6] = true;
                return;
            case -3:
                aR[4] = true;
                aS[4] = true;
                return;
            case -2:
                aR[8] = true;
                aS[8] = true;
                return;
            case -1:
                aR[2] = true;
                aS[2] = true;
                return;
            case 35:
                aR[11] = true;
                aS[11] = true;
                return;
            case 42:
                aR[10] = true;
                aS[10] = true;
                return;
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                aR[var0 - 48] = true;
                aS[var0 - 48] = true;
                return;
            default:
        }
    }

    private static void G(int var0) {
        switch (var0) {
            case -22:
            case -7:
                aR[13] = false;
                return;
            case -21:
            case -6:
                aR[12] = false;
                return;
            case -5:
            case 10:
                aR[5] = false;
                return;
            case -4:
                aR[6] = false;
                aS[6] = false;
                return;
            case -3:
                aR[4] = false;
                aS[4] = false;
                return;
            case -2:
                aR[8] = false;
                aS[8] = false;
                return;
            case -1:
                aR[2] = false;
                aS[2] = false;
                return;
            case 35:
                aR[11] = false;
                return;
            case 42:
                aR[10] = false;
                return;
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                aR[var0 - 48] = false;
                return;
            default:
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
                            jh = (ji = (je - 2) * jg / je) + bC;
                            var1.drawImage(iW[iY[var2]], fO + jh, ja[var2], 33);
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
                            var6.drawImage(ex[0], hP, hQ, 33);
                            var6.drawImage(ex[2], hR, hV, 33);
                            var6.drawImage(ex[3], hU, hW, 33);
                            var6.drawImage(ex[1], hS, hT, 33);
                        } else {
                            var6.drawRegion(ex[0], 0, 0, ex[0].getWidth(), ex[0].getHeight(), 2, hP, hQ, 33);
                            var6.drawRegion(ex[2], 0, 0, ex[2].getWidth(), ex[2].getHeight(), 2, hR, hV, 33);
                            var6.drawRegion(ex[3], 0, 0, ex[3].getWidth(), ex[3].getHeight(), 2, hU, hW, 33);
                            var6.drawRegion(ex[1], 0, 0, ex[1].getWidth(), ex[1].getHeight(), 2, hS, hT, 33);
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
                            var1.drawImage(ee, is[var2], it[var2], 33);
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < ii; ++var2) {
                        if (fO <= ik[var2] + 12 && fO + r >= ik[var2] - 12 && fP <= il[var2] && fP + s >= il[var2] - 24) {
                            if (im[var2] == 1) {
                                var1.drawImage(eb[in[var2]], ik[var2], il[var2], 33);
                            } else {
                                var1.drawRegion(eb[in[var2]], 0, 0, eb[in[var2]].getWidth(), eb[in[var2]].getHeight(), 2, ik[var2], il[var2], 33);
                            }
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < de; ++var2) {
                        if (dh[var2] != 0 && df[var2] >= fO - 10 && df[var2] <= fO + r + 10 && dg[var2] >= fP && dg[var2] <= fP + s + 20) {
                            var1.drawRegion(eg[dm[var2]], 0, dk[var2] * 15, 8, 15, 0, df[var2], dg[var2], 33);
                        }
                    }

                    var1 = var6;

                    for(var2 = 0; var2 < cM; ++var2) {
                        if (cQ[var2] != 0 && cO[var2] >= fO - 10 && cO[var2] <= fO + r + 10 && cP[var2] >= fP && cP[var2] <= fP + s + 20) {
                            var1.drawRegion(ew[cN[var2]], 0, cT[var2] * 15, 13, 15, 0, cO[var2], cP[var2], 33);
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
                                var6.drawImage(ev[4], gP, gQ + 1, 33);
                            } else {
                                var6.drawRegion(ev[4], 0, 0, ev[4].getWidth(), ev[4].getHeight(), 2, gP, gQ + 1, 33);
                            }
                        }

                        if (gT == 1) {
                            var6.drawImage(ev[gU], gP, gQ + var8 + 1, 33);
                        } else {
                            var6.drawRegion(ev[gU], 0, 0, ev[gU].getWidth(), ev[gU].getHeight(), 2, gP, gQ + var8 + 1, 33);
                        }

                        if (gS == 7) {
                            if (gT == 1) {
                                var6.drawImage(ev[3], gP + 10, gQ - 25 + 1, 0);
                            } else {
                                var6.drawRegion(ev[3], 0, 0, ev[3].getWidth(), ev[3].getHeight(), 2, gP - 10, gQ - 25 + 1, 24);
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
                                var1.drawImage(ez[var2], hD[var2], hE[var2], 33);
                            }
                        } else {
                            for(var2 = 0; var2 < 6; ++var2) {
                                var1.drawRegion(ez[var2], 0, 0, ez[var2].getWidth(), ez[var2].getHeight(), 2, hD[var2], hE[var2], 33);
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
                            var1.drawImage(jF[kg[var2]][jX[var2] >= 0 ? 0 : 1][jW[var2]], jJ[var2], jK[var2], 33);
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
                                var1.drawImage(eu[cI[var2]], cu[var2] + ah[cD[var2]], cv[var2], 33);
                                var1.drawImage(et[cD[var2]][cJ[var2]], cw[var2] + af[cD[var2]][cJ[var2]], cx[var2], 33);
                            } else {
                                var1.drawRegion(eu[cI[var2]], 0, 0, eu[cI[var2]].getWidth(), eu[cI[var2]].getHeight(), 2, cu[var2] - ah[cD[var2]], cv[var2], 33);
                                var1.drawRegion(et[cD[var2]][cJ[var2]], 0, 0, et[cD[var2]][cJ[var2]].getWidth(), et[cD[var2]][cJ[var2]].getHeight(), 2, cw[var2] - af[cD[var2]][cJ[var2]], cx[var2], 33);
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
                        var6.drawImage(ei[var2], doSt, dp, 33);
                    } else {
                        var6.drawRegion(ei[var2], 0, 0, ei[var2].getWidth(), ei[var2].getHeight(), 2, doSt, dp, 33);
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
                            var6.drawImage(es[dH[0]][aI[dG[0]][0][0]], dz[0] + aI[dG[0]][0][1], dA[0] - aI[dG[0]][0][2], 0);
                            var6.drawImage(eq[aI[dG[0]][1][0]], dz[0] + aI[dG[0]][1][1], dA[0] - aI[dG[0]][1][2], 0);
                            var6.drawImage(ed[aI[dG[0]][2][0]], dz[0] + aI[dG[0]][2][1], dA[0] - aI[dG[0]][2][2], 0);
                            if (dI[0] != -1) {
                                var6.drawImage(eB[dI[0]], dz[0] - as[dI[0]], dA[0] - at[dI[0]], 0);
                            }
                        } else {
                            var6.drawRegion(es[dH[0]][aI[dG[0]][0][0]], 0, 0, es[dH[0]][aI[dG[0]][0][0]].getWidth(), es[dH[0]][aI[dG[0]][0][0]].getHeight(), 2, dz[0] - aI[dG[0]][0][1], dA[0] - aI[dG[0]][0][2], 24);
                            var6.drawRegion(eq[aI[dG[0]][1][0]], 0, 0, eq[aI[dG[0]][1][0]].getWidth(), eq[aI[dG[0]][1][0]].getHeight(), 2, dz[0] - aI[dG[0]][1][1], dA[0] - aI[dG[0]][1][2], 24);
                            var6.drawRegion(ed[aI[dG[0]][2][0]], 0, 0, ed[aI[dG[0]][2][0]].getWidth(), ed[aI[dG[0]][2][0]].getHeight(), 2, dz[0] - aI[dG[0]][2][1], dA[0] - aI[dG[0]][2][2], 24);
                            if (dI[0] != -1) {
                                var6.drawRegion(eB[dI[0]], 0, 0, eB[dI[0]].getWidth(), eB[dI[0]].getHeight(), 2, dz[0] + as[dI[0]], dA[0] - at[dI[0]], 24);
                            }
                        }
                    }
                }

                if (dX != 0 && (var2 = dX % 3) < 2) {
                    var6.drawImage(er[var2], dV, dW, 17);
                }

                if (gm == w) {
                    if (gz == 1) {
                        var6.drawImage(em[aq[gs][0][0]], gn + aq[gs][0][1], go - aq[gs][0][2], 0);
                        var6.drawImage(en[aq[gs][1][0]], gn + aq[gs][1][1], go - aq[gs][1][2], 0);
                        var6.drawImage(el[aq[gs][2][0]], gn + aq[gs][2][1], go - aq[gs][2][2], 0);
                        if (ig != -1) {
                            var6.drawImage(eB[ig], gn - as[ig], go - at[ig], 0);
                        }
                    } else {
                        var6.drawRegion(em[aq[gs][0][0]], 0, 0, em[aq[gs][0][0]].getWidth(), em[aq[gs][0][0]].getHeight(), 2, gn - aq[gs][0][1], go - aq[gs][0][2], 24);
                        var6.drawRegion(en[aq[gs][1][0]], 0, 0, en[aq[gs][1][0]].getWidth(), en[aq[gs][1][0]].getHeight(), 2, gn - aq[gs][1][1], go - aq[gs][1][2], 24);
                        var6.drawRegion(el[aq[gs][2][0]], 0, 0, el[aq[gs][2][0]].getWidth(), el[aq[gs][2][0]].getHeight(), 2, gn - aq[gs][2][1], go - aq[gs][2][2], 24);
                        if (ig != -1) {
                            var6.drawRegion(eB[ig], 0, 0, eB[ig].getWidth(), eB[ig].getHeight(), 2, gn + as[ig], go - at[ig], 24);
                        }
                    }
                }

                b(var6);
                if (fH != 0) {
                    var6.drawImage(ec, fF, fG, 33);
                }

                var1 = var6;

                for(var2 = 0; var2 < 2; ++var2) {
                    if (cc[var2] != -1) {
                        if (ce[var2] == 1) {
                            var1.drawImage(ey[cd[var2]], ca[var2], cb[var2], 3);
                        } else {
                            var1.drawRegion(ey[cd[var2]], 0, 0, ey[cd[var2]].getWidth(), ey[cd[var2]].getHeight(), 2, ca[var2], cb[var2], 3);
                        }
                    }
                }

                var1 = var6;
                if (!a) {
                    for(var2 = 0; var2 < 2; ++var2) {
                        if (cl[var2] != -1) {
                            var1.drawImage(ej[var2][cl[var2]], cj[var2], ck[var2], 3);
                        }
                    }
                }

                var1 = var6;

                for(var2 = 0; var2 < bM; ++var2) {
                    if (bP[var2] != -1) {
                        var1.drawImage(dY[bS[var2]][bQ[var2] > 0 ? 0 : 1], bN[var2], bO[var2], 3);
                    }
                }

                var1 = var6;

                for(var2 = 0; var2 < cV; ++var2) {
                    if (cY[var2] != -1) {
                        var1.drawImage(ek[cZ[var2]], cW[var2], cX[var2], 3);
                    }
                }

                var1 = var6;

                for(var2 = 0; var2 < bT; ++var2) {
                    if (bW[var2] != 0) {
                        var1.drawImage(ea, bU[var2], bV[var2], 3);
                    }
                }

                var1 = var6;
                if (!a) {
                    for(var2 = 0; var2 < 2; ++var2) {
                        if (ch[var2] != -1) {
                            var1.drawImage(eD[ci[var2]], cf[var2], cg[var2], 3);
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
                    var6.drawImage(dZ[ge], gf, gg, 3);
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
                    var1.drawImage(eA, aV, aY + bb >> 1, 3);
                    FontGraph.drawBold(var1, "*", aV - 50 - x % 3, bd, 0);
                    FontGraph.drawBold(var1, "/", aV + 50 + x % 3, bd, 1);
                    FontGraph.drawBold(var1, aE[aX], aV, bd, 3);
                    return;
                case 4:
                    var1.setColor(0);
                    var1.fillRect(0, 0, aT, aU);
                    if (jC != null && jE >= 0) {
                        var1.drawRegion(jC, 0, jE * 51, 71, 51, 0, aT >> 1, aU >> 1, 3);
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
                        var1.drawImage(lj, aV, aW - 30, 3);
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
                case 13:
                    var1.setColor(15395562);
                    var1.fillRect(0, 0, aT, aU);
                    var1.setColor(11788624);
                    var1.fillRect(0, 0, aT, lR);
                    var1.fillRect(0, aU - lR, aT, lR);
                    var1.setColor(16777215);
                    var1.setFont(lQ);
                    var1.drawString("Ninja School", aV, 3, 17);
                    var1.setFont(lP);
                    switch (Z) {
                        case 0:
                            this.t(var1);
                            return;
                        case 1:
                            var1.setColor(lN % 10 > 5 ? 16777215 : 7171437);
                            var1.drawString("Dang gui tin", aV, aW - lR, 17);
                            var1.drawString("kich hoat...", aV, aW, 17);
                            return;
                        case 2:
                            var1.setColor(7171437);
                            var1.drawString("Gui tin kich hoat ton phi", aV, aW - (lR << 1), 17);
                            var1.drawString("15.000đ. Ban se nhan", aV, aW - lR, 17);
                            var1.drawString("duoc ma kich hoat trong", aV, aW, 17);
                            var1.drawString("tin nhan tra loi.", aV, aW + lR, 17);
                            var1.setColor(16777215);
                            var1.setFont(lQ);
                            var1.drawString("Dong y", 2, aU - lR + 3, 20);
                            var1.drawString("Khong", aT - 2, aU - lR + 3, 24);
                            return;
                        case 3:
                        default:
                            break;
                        case 4:
                            this.s(var1);
                            return;
                        case 5:
                            var1.setColor(7171437);
                            var1.drawString("Da gui tin kich hoat.", aV, aW - (lR << 1), 17);
                            var1.drawString("Xin thoat game va", aV, aW - lR, 17);
                            var1.drawString("cho giay lat. Ban se", aV, aW, 17);
                            var1.drawString("nhan duoc ma kich hoat.", aV, aW + lR, 17);
                            var1.setColor(16777215);
                            var1.setFont(lQ);
                            var1.drawString("OK", 2, aU - lR + 3, 20);
                            return;
                        case 6:
                            var1.setColor(7171437);
                            var1.drawString("Khong the gui SMS", aV, aW - (lR << 1), 17);
                            var1.drawString("Xin hay thu lai. Vao", aV, aW - lR, 17);
                            var1.drawString("www.teamobi.com", aV, aW, 17);
                            var1.drawString("de biet them thong tin.", aV, aW + lR, 17);
                            var1.setColor(16777215);
                            var1.setFont(lQ);
                            var1.drawString("OK", 2, aU - lR + 3, 20);
                            return;
                        case 7:
                            var1.setColor(0);
                            var1.fillRect(0, 0, aT, aU);
                            String var4 = this.lY;
                            if (lN % 10 > 5) {
                                var4 = var4 + "|";
                            }

                            var1.drawString(var4, aV - 20, aW - 40, 20);
                            X(7);
                            ai();
                            e = 4;
                            lK = aH() == 1;
                            return;
                        case 8:
                            var1.setColor(7171437);
                            var1.drawString("Xin vui long", aV, aW - (lR << 1), 17);
                            var1.drawString("Bat Bluetooth", aV, aW - lR, 17);
                            var1.setColor(16777215);
                            var1.setFont(lQ);
                            var1.drawString("OK", 2, aU - lR + 3, 20);
                    }
                default:
                    return;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    protected final void pointerDragged(int var1, int var2) {
        aO = var1;
        aP = var2;
    }

    protected final void pointerPressed(int var1, int var2) {
        aO = var1;
        aP = var2;
    }

    protected final void pointerReleased(int var1, int var2) {
        aN = true;
        aO = var1;
        aP = var2;
    }

    public final void run() {
        if (!b) {
            b = true;
            e = 13;
            Z = 8;
        }

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
                            Midlet.f = true;
                            x(jo);
                            break;
                        }
                    } else if (fe >= iO - 24) {
                        if (jp != -1) {
                            if (!eZ && jp == 95) {
                                s(6);
                                a("Khu vực phía trước rất nguy hiểm! Chỉ những người có thể ẩn thân có thể vào!");
                                fe = iO - 60;
                                B = 4;
                                e = 7;
                                break;
                            }

                            B();
                            if (jq != -1 && ff > jA) {
                                fp = fe = jw;
                                fq = ff = jx;
                                Midlet.f = true;
                                x(jq);
                                break;
                            }

                            fp = fe = ju;
                            fq = ff = jv;
                            Midlet.f = true;
                            x(jp);
                            break;
                        }
                    } else if (ff + A >= iP && B != 6 && jr != -1) {
                        B();
                        fp = fe = jy;
                        fq = ff = jz;
                        Midlet.f = true;
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
                        if (aS[5]) {
                            d();
                            M();
                            if (iC == iB) {
                                iz = false;
                                z();
                            }
                        }

                        d = true;
                    } else {
                        label2614: {
                            if (aS[12]) {
                                ap();
                                e = 5;
                                c();
                                d();
                            } else if (aS[0]) {
                                if (fd == 0) {
                                    if (eK) {
                                        fd = 1;
                                    }
                                } else {
                                    fd = 0;
                                }

                                d = true;
                                aS[0] = false;
                            } else if (aS[6]) {
                                fo = 2;
                                aS[6] = false;
                            } else if (aS[4]) {
                                fo = -2;
                                aS[4] = false;
                            } else {
                                if (aS[9]) {
                                    if (eZ) {
                                        fw = !fw;
                                    }

                                    aS[9] = false;
                                    break label2614;
                                }

                                if (aS[7]) {
                                    if (B == 1 && eQ) {
                                        B = 23;
                                        D = 0;
                                    }

                                    aS[7] = false;
                                } else if (aS[10]) {
                                    aS[10] = false;
                                    if (H < I) {
                                        for(var13 = 0; var13 < 3; ++var13) {
                                            if (W[var13] > 0) {
                                                var10002 = W[var13]--;
                                                if ((H += kF[var13]) > I) {
                                                    H = I;
                                                }

                                                d = true;
                                                break;
                                            }
                                        }
                                    }
                                } else if (aS[11]) {
                                    aS[11] = false;
                                    if (J < K) {
                                        for(var13 = 3; var13 < 6; ++var13) {
                                            if (W[var13] > 0) {
                                                var10002 = W[var13]--;
                                                if ((J += kF[var13]) > K) {
                                                    J = K;
                                                }

                                                d = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }

                            if (B == 1) {
                                if (aS[13]) {
                                    d();
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
                                                    if (N < 10) {
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
                                                    if (N < 12) {
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
                                                        if (N < 15) {
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
                                                            if (N < 16) {
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
                                                    if (N < 8) {
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
                                        d();
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
                                        if (aR[2]) {
                                            if (eM) {
                                                for(var13 = 0; var13 < ii; ++var13) {
                                                    if (ff == il[var13] && a(fe - ik[var13]) < 20) {
                                                        fe = ik[var13];
                                                        B = 18;
                                                        c();
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
                                        } else if (aR[4]) {
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
                                        } else if (aR[6]) {
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
                                        } else if (aR[8]) {
                                            D = 0;
                                            E = 0;
                                            B = 8;
                                        } else if (aR[1]) {
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
                                        } else if (aR[3]) {
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

                                        if (aS[5]) {
                                            B = 5;
                                            ft = 0;
                                            D = 0;
                                            A = 0;
                                            aS[5] = false;
                                            fD = -1;
                                            fE = -1;
                                            p = " ";
                                            d = true;
                                        }
                                    }
                                }
                            } else if (B == 2) {
                                if (aR[2]) {
                                    ir = -1;
                                    if (eI) {
                                        A = -10;
                                    } else {
                                        A = -8;
                                    }

                                    B = 3;
                                    D = 0;
                                } else if (aR[4]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -Q + fo;
                                    }
                                } else if (aR[6]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = Q + fo;
                                    }
                                }

                                if (aS[5]) {
                                    B = 5;
                                    ft = 0;
                                    D = 0;
                                    A = 0;
                                    aS[5] = false;
                                }
                            } else if (B == 11) {
                                if (aR[2]) {
                                    if (eI) {
                                        A = -10;
                                    } else {
                                        A = -8;
                                    }

                                    B = 3;
                                    D = 0;
                                } else if (aR[4]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -L + fo;
                                    }
                                } else if (aR[6]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = L + fo;
                                    }
                                }
                            } else if (B == 3) {
                                if (aR[4]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -Q;
                                    }
                                } else if (aR[6]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = Q;
                                    }
                                }

                                if (aS[5]) {
                                    B = 5;
                                    ft = 0;
                                    D = 0;
                                    aS[5] = false;
                                }

                                if (aR[2] && J > 0 && A > -4 && (D == 0 && eJ || D == 1 && eV)) {
                                    ++D;
                                    A = -7;
                                    if ((J -= 3) < 0) {
                                        J = 0;
                                    }

                                    d = true;
                                    e(-1, fe - -8, ff);
                                    e(1, fe - 8, ff);
                                    g(fe, ff);
                                }
                            } else if (B == 4) {
                                if (aR[4]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -Q;
                                    }
                                } else if (aR[6]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = Q;
                                    }
                                }

                                if (aS[5] && A > 4) {
                                    B = 5;
                                    D = 0;
                                    ft = 0;
                                    aS[5] = false;
                                }
                            } else if (B == 5) {
                                if (aR[4]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -Q + 2;
                                    }
                                } else if (aR[6]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = Q - 2;
                                    }
                                }

                                if (aS[5]) {
                                    fv = true;
                                    aS[5] = false;
                                }
                            } else if (B == 18) {
                                if (aR[2]) {
                                    A = -10;
                                    B = 21;
                                    D = 10;
                                    ir = -1;
                                } else if (aR[4]) {
                                    if (fm == 1) {
                                        fm = -1;
                                        im[ij] = -1;
                                    } else {
                                        D = 1;
                                        B = 19;
                                        D = 10;
                                        z = -R;
                                    }
                                } else if (aR[6]) {
                                    if (fm == -1) {
                                        fm = 1;
                                        im[ij] = 1;
                                    } else {
                                        B = 19;
                                        D = 10;
                                        z = R;
                                    }
                                } else if (aR[8]) {
                                    B = 1;
                                    ij = -1;
                                }
                            } else if (B == 19) {
                                if (aR[2]) {
                                    A = -10;
                                    B = 21;
                                    ir = -1;
                                    D = 10;
                                } else if (aR[4]) {
                                    if (fm == 1) {
                                        fm = -1;
                                        im[ij] = -1;
                                    } else {
                                        z = -R;
                                    }
                                } else if (aR[6]) {
                                    if (fm == -1) {
                                        fm = 1;
                                        im[ij] = 1;
                                    } else {
                                        z = R;
                                    }
                                }
                            } else if (B == 21) {
                                if (aR[4]) {
                                    if (fm == 1) {
                                        fm = -1;
                                        im[ij] = -1;
                                    } else {
                                        z = -R;
                                    }
                                } else if (aR[6]) {
                                    if (fm == -1) {
                                        fm = 1;
                                        im[ij] = 1;
                                    } else {
                                        z = R;
                                    }
                                }
                            } else if (B == 20) {
                                if (aR[4]) {
                                    if (fm == 1) {
                                        fm = -1;
                                        im[ij] = -1;
                                    } else {
                                        z = -R;
                                    }
                                } else if (aR[6]) {
                                    if (fm == -1) {
                                        fm = 1;
                                        im[ij] = 1;
                                    } else {
                                        z = R;
                                    }
                                }
                            } else if (B == 17) {
                                if (aR[2]) {
                                    A = -10;
                                    B = 3;
                                    fc = -1;
                                    D = 0;
                                } else if (aR[4]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        B = 16;
                                        z = -M;
                                    }
                                } else if (aR[6]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        B = 16;
                                        z = M;
                                    }
                                }
                            } else if (B == 16) {
                                if (aR[2]) {
                                    A = -10;
                                    B = 3;
                                    fc = -1;
                                    D = 0;
                                } else if (aR[4]) {
                                    if (fm == 1) {
                                        fm = -1;
                                    } else {
                                        z = -M;
                                    }
                                } else if (aR[6]) {
                                    if (fm == -1) {
                                        fm = 1;
                                    } else {
                                        z = M;
                                    }
                                }
                            } else if (B == 12) {
                                if (aR[2] && eP) {
                                    A = -10;
                                    B = 3;
                                    fc = -1;
                                    D = 0;
                                }
                            } else if (B == 14) {
                                if (aR[4]) {
                                    fm = -1;
                                } else if (aR[6]) {
                                    fm = 1;
                                } else if (aR[2]) {
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

                    if ((bw = fO / je - 1) < 0) {
                        bw = 0;
                    }

                    bx = fP / je;
                    bA = bw + by;
                    bB = bx + bz;
                    if (bx < 0) {
                        bx = 0;
                    }

                    if (bB > iN - 1) {
                        bB = iN - 1;
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

                                if (!g(is[var13] - 12, it[var13] + 1, 2) && !g(is[var13] + 12, it[var13] + 1, 2)) {
                                    iu[var13] = 4;
                                    iw[var13] = 2;
                                }

                                if (g(is[var13] + 12, it[var13] - 2, 4) || g(is[var13] - 12, it[var13] - 2, 8)) {
                                    is[var13] = I(is[var13]) + 12;
                                }
                                break;
                            case 4:
                                var10000 = it;
                                var10000[var13] += iw[var13];
                                if (iw[var13] < 20) {
                                    var10002 = iw[var13]++;
                                }

                                if (!g(is[var13] + 12, it[var13], 2) && !g(is[var13] - 12, it[var13], 2)) {
                                    if (g(is[var13], it[var13] - je, 64)) {
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

                                    if (g(is[var13] + 12, it[var13], 4) || g(is[var13] - 12, it[var13], 8)) {
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

                            if (il[var13] >= iP || g(ik[var13], il[var13], 2)) {
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
                                if (da[var13] > 0 && (i(cW[var13], cX[var13]) & 4) == 4) {
                                    cY[var13] = 2;
                                    cW[var13] = I(cW[var13]);
                                } else if (da[var13] < 0 && (i(cW[var13], cX[var13]) & 8) == 8) {
                                    cY[var13] = 2;
                                    cW[var13] = I(cW[var13]) + je;
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
                                                var5 = dc[var13] ? G << 1 : G;
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
                                                a(var4, G << 1, cH[var13]);
                                                f(cu[var4], cv[var4] - 15, cH[var4]);
                                                a("-" + (G << 1), 0, cu[var4], cv[var4] - 32, 0, -2);
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
                                            a(G, -fm);
                                            cY[var13] = -1;
                                        }
                                        break;
                                    }

                                    if (gN && a(gP - cW[var13]) < gV >> 1 && a(gQ - (gW >> 1) - cX[var13]) < gW >> 1) {
                                        c(G, -gT);
                                        f(cW[var13], cX[var13], gT);
                                        a("-" + G, 0, gP, gQ - gW, 0, -2);
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
                                            d(G, -dJ[0]);
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
                                        if (g(gn + gv, go - gw, 4)) {
                                            gp = 0;
                                            gn = I(gn + gv) - gv;
                                        }
                                    } else if (g(gn - gv - 1, go - gw, 8)) {
                                        gp = 0;
                                        gn = I(gn - gv - 1) + je + gv;
                                    }

                                    if ((gn += gp) < 0) {
                                        gn = 0;
                                    }

                                    if (gn >= iO) {
                                        gn = iO - 1;
                                    }

                                    if (ir != -1) {
                                        if (a(gn - is[ir]) >= 20) {
                                            ir = -1;
                                            if (g(gn, go, 2)) {
                                                gr = 1;
                                            } else {
                                                gr = 4;
                                                gq = 2;
                                                gp = 3 * gz;
                                                gB = 0;
                                            }
                                        }
                                    } else if ((i(gn + gz * gt, go) & 2) != 2) {
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
                                        if ((i(gn + gv, go - gw) & 4) == 4) {
                                            gp = 0;
                                        }
                                    } else if ((i(gn - gv, go - gw) & 8) == 8) {
                                        gp = 0;
                                    }

                                    if (ff < 0) {
                                        ff = 0;
                                        A = -1;
                                    }

                                    if ((gn += gp) < 0) {
                                        gn = 0;
                                    }

                                    if (gn >= iO) {
                                        gn = iO - 1;
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
                                        if ((i(gn + gt, go) & 4) == 4) {
                                            gp = 0;
                                        }
                                    } else if ((i(gn - gt, go) & 8) == 8) {
                                        gp = 0;
                                    }

                                    ig = -1;
                                    if ((gn += gp) < 0) {
                                        gn = 0;
                                    }

                                    if (gn >= iO) {
                                        gn = iO - 1;
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
                                    if (go >= iP) {
                                        go = iP - 1;
                                        gr = 3;
                                        gq = -20;
                                    }

                                    if (gq > 4 && (i(gn, go) & 2) == 2) {
                                        gr = 1;
                                        gq = 0;
                                        gp = 0;
                                        gB = 0;
                                        gA = 0;
                                        go = I(go);
                                        e(-1, gn - -8, go);
                                        e(1, gn - 8, go);
                                    }

                                    if (g(gn, go, 64) && g(gn, go + 24, 4096) && go % je > 8) {
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
                                    if ((i(gn + gv, go - gw) & 4) == 4) {
                                        gp = -gp;
                                    }

                                    if ((i(gn - gv, go - gw) & 8) == 8) {
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

                            if (!g(gP + (gT << 4), gQ + 1, 2)) {
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
                            if (!g(gP + (gT << 4), gQ + 1, 2)) {
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

                            if (gQ > fP + s + gW || gQ >= iP + gW) {
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
                                if (dA[0] >= iP) {
                                    dD[0] = 6;
                                } else if ((i(dz[0], dA[0]) & 2) == 2) {
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

                    if (aS[4]) {
                        if (--aX < 0) {
                            aX = aE.length - 1;
                        }

                        aS[4] = false;
                    }

                    if (aS[6]) {
                        if (++aX == aE.length) {
                            aX = 0;
                        }

                        aS[6] = false;
                    }

                    if (aS[5]) {
                        label2172: {
                            d();
                            if (aX == 0) {
                                eA = null;
                                B();
                                Midlet.f = true;
                                gM = System.currentTimeMillis();
                                x(99);
                            } else if (aX == 1) {
                                if (!Midlet.isSaved()) {
                                    break label2172;
                                }

                                eA = null;
                                B();
                                int[] var12;
                                gL = (var12 = a(Midlet.loadRecord("ninjasave")))[0];
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
                                Midlet.e = true;
                                gM = System.currentTimeMillis();
                                x(w);
                            } else if (aX == 2) {
                                eA = null;
                                B();
                                e = 2;
                                aj();
                                Midlet.d = 11;
                                w = 101;
                                (new Thread(Midlet.c)).start();
                            } else if (aX == 3) {
                                try {
                                    Midlet.c.platformRequest("https://www.facebook.com/duriustudio");
                                } catch (Exception var10) {
                                }
                            } else if (aX == 4) {
                                Midlet.c.notifyDestroyed();
                            }

                            aS[5] = false;
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

                    if ((bw = fO / je - 1) < 0) {
                        bw = 0;
                    }

                    bx = fP / je;
                    bA = bw + by;
                    bB = bx + bz;
                    if (bx < 0) {
                        bx = 0;
                    }

                    if (bB > iN - 1) {
                        bB = iN - 1;
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
                        jC = null;
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
                    if (aS[13]) {
                        var3 = true;
                        kG = null;
                        ah();
                        e = 1;
                        d = true;
                    } else if (!aS[5] && !aS[12]) {
                        if (aS[8]) {
                            var3 = true;
                            if (++kI > 3) {
                                kI = 0;
                            }
                        } else if (aS[2]) {
                            var3 = true;
                            if (--kI < 0) {
                                kI = 3;
                            }
                        } else if (aS[4]) {
                            var3 = true;
                        } else if (aS[6]) {
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
                                kG = null;
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
                        d();
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
                    if (aS[5] || aS[12]) {
                        d();
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

                    if (aS[13]) {
                        ar();
                        kG = null;
                        ah();
                        e = 1;
                        d();
                        c();
                        aS[12] = true;
                    } else {
                        if (aR[2]) {
                            if ((kU -= 4) < -20) {
                                kU = -20;
                            }

                            var3 = true;
                        }

                        if (aR[8]) {
                            if ((kU += 4) > 279) {
                                kU = 279;
                            }

                            var3 = true;
                        }

                        if (aR[4]) {
                            if ((kT -= 4) < -20) {
                                kT = -20;
                            }

                            var3 = true;
                        }

                        if (aR[6]) {
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
                        d();
                    }

                    if (aS[12]) {
                        d();
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
                                    kG = null;
                                    ah();
                                    B();
                                    fp = fe = kP[kV] * 24;
                                    fq = ff = kQ[kV] * 24;
                                    Midlet.f = true;
                                    x(kV);
                                }
                                break;
                            case 5:
                            case 6:
                                kG = null;
                                e = 1;
                        }
                    }

                    if (aS[13]) {
                        d();
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

                    if (aS[5] || aS[12]) {
                        if (W[kB] > 0) {
                            var10002 = W[kB]--;
                            kC = kD.length - 1;
                            if (kB <= 2) {
                                if (H < I) {
                                    H += kF[kB];
                                }

                                if (H > I) {
                                    H = I;
                                }
                            } else {
                                if (J < K) {
                                    J += kF[kB];
                                }

                                if (J > K) {
                                    J = K;
                                }
                            }
                        }

                        d();
                    }

                    if (aS[13]) {
                        d();
                        c();
                        e = 5;
                    } else {
                        if (aS[4]) {
                            d();
                            if (--kB < 0) {
                                kB = 5;
                            }
                        }

                        if (aS[6]) {
                            d();
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

                    if (aS[5] || aS[12]) {
                        d();
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
                                a("TEAM BUY 009003 " + kx[kq][ko], ky[kq][ko], ko + 1);
                            }
                        } else {
                            if (y >= ko) {
                                s(7);
                                a("Không cần thiết phải mua vũ khí này vì vũ khí đang sử dụng mạnh hơn!");
                                Y = V;
                                e = 7;
                                break;
                            }

                            if (N < km[ko]) {
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

                            a("TEAM BUY 009003 " + kx[kq][ko], ky[kq][ko], ko + 6);
                        }
                    }

                    if (aS[13]) {
                        kG = null;
                        kw = null;
                        System.gc();
                        ah();
                        d();
                        c();
                        e = 1;
                        d = true;
                    } else {
                        if (aS[2]) {
                            d();
                            if (kq == 1) {
                                kq = 0;
                                ++ko;
                            }
                        }

                        if (aS[8]) {
                            d();
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

                        if (aS[4]) {
                            d();
                            if (--ko < 0) {
                                if (kq == 0) {
                                    ko = 5;
                                } else {
                                    ko = 3;
                                }
                            }
                        }

                        if (aS[6]) {
                            d();
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
                        lj = null;
                        K();
                    }

                    var3 = false;
                    if (aS[12]) {
                        if (ll == 0) {
                            Sound.disabled = false;
                        } else {
                            a = false;
                        }

                        var3 = true;
                    } else if (aS[13]) {
                        if (ll == 0) {
                            Sound.disabled = true;
                        } else {
                            a = true;
                        }

                        var3 = true;
                    }

                    if (var3) {
                        d();
                        c();
                        if (ll == 0) {
                            try {
                                lj = Image.createImage("/u/m2.png");
                            } catch (Exception var9) {
                            }

                            ll = 1;
                        } else {
                            Midlet.a = !lK;
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
                case 13:
                    if (++lN > 10000) {
                        lN = 0;
                    }

                    aT = this.getWidth();
                    aU = this.getHeight();
                    if (aN && aP > aU - 20) {
                        if (aO < aV) {
                            aS[12] = true;
                        } else {
                            aS[13] = true;
                        }

                        aN = false;
                    }

                    switch (Z) {
                        case 0:
                            this.aE();
                        case 1:
                        case 3:
                        default:
                            break;
                        case 2:
                            this.aD();
                            break;
                        case 4:
                            this.ax();
                            break;
                        case 5:
                            if (aS[5] || aS[12]) {
                                Midlet.c.notifyDestroyed();
                            }
                            break;
                        case 6:
                            aC();
                            break;
                        case 7:
                            aw();
                            break;
                        case 8:
                            if (aS[12]) {
                                Midlet.c.notifyDestroyed();
                            }
                    }
            }

            this.repaint();
            this.serviceRepaints();

            while(System.currentTimeMillis() - var1 < (long)aa) {
                Thread.yield();
            }
        }

        a();
        Midlet.c.notifyDestroyed();
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
            if (iW == null) {
                iW = new Image[3];

                try {
                    iW[0] = Image.createImage("/t/h0.png");
                    iW[1] = Image.createImage("/t/h1.png");
                    iW[2] = Image.createImage("/t/h2.png");
                } catch (Exception var2) {
                }
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
            if (iW == null) {
                iW = new Image[3];

                try {
                    iW[0] = Image.createImage("/t/h0.png");
                    iW[1] = Image.createImage("/t/h1.png");
                    iW[2] = Image.createImage("/t/h2.png");
                } catch (Exception var1) {
                }
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
        iZ[var0] = var2 * je + 12;
        ja[var0] = var3 * je;
        jb[var0] = iW[var1].getWidth() >> 1;
        jc[var0] = iW[var1].getHeight();
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
            if (iT == null) {
                iT = Image.createImage("/t/t.png");
            }

            if (iU == null) {
                iU = Image.createImage("/t/wtf.png");
            }

            if (iV == null) {
                iV = Image.createImage("/t/wts.png");
            }

            if (jd == null && !a) {
                jd = Image.createImage("/t/tb.png");
            }

            System.gc();
        } catch (Exception var0) {
            Midlet.c.c();
        }
    }

    public static void q(int var0) {
        var0 = var0;
        iS = new boolean[200];
        if (var0 == 101) {
            var0 = 99;
        }

        if (var0 == 0) {
            iM = 80;
            iN = 19;
            jo = 1;
            js = 59 * je;
            jt = 9 * je;
            jp = 2;
            ju = 3;
            jv = 12 * je;
            jq = -1;
            jr = -1;
        } else if (var0 == 1) {
            iM = 60;
            iN = 14;
            jo = 5;
            js = 19 * je;
            jt = 5 * je;
            jp = 0;
            ju = 3;
            jv = 16 * je;
            jq = -1;
            jr = -1;
        } else if (var0 == 2) {
            iM = 60;
            iN = 16;
            jo = 0;
            js = 79 * je;
            jt = 15 * je;
            jp = 3;
            ju = 3;
            jv = 9 * je;
            jq = -1;
            jr = -1;
        } else if (var0 == 3) {
            iM = 120;
            iN = 12;
            jo = 2;
            js = 59 * je;
            jt = 11 * je;
            jp = 4;
            ju = 3;
            jv = 144;
            jq = -1;
            jr = -1;
        } else if (var0 == 4) {
            iM = 80;
            iN = 15;
            jo = 3;
            js = 119 * je;
            jt = 9 * je;
            jp = -1;
            jq = -1;
            jr = 6;
            jy = 26 * je;
            jz = 10;
        } else if (var0 == 5) {
            iM = 20;
            iN = 80;
            jo = -1;
            jp = 1;
            ju = 3;
            jv = 9 * je;
            jA = 40 * je;
            jq = 6;
            jw = 3;
            jx = 5 * je;
            jr = -1;
        } else if (var0 == 6) {
            iM = 250;
            iN = 12;
            jo = 5;
            js = 19 * je;
            jt = 78 * je;
            jp = 7;
            ju = 1 * je;
            jv = 7 * je;
            jq = -1;
            jr = -1;
        } else if (var0 == 7) {
            iM = 80;
            iN = 14;
            jo = 6;
            js = 249 * je;
            jt = 10 * je;
            jp = 97;
            ju = 1 * je;
            jv = 3 * je;
            jq = -1;
            jr = -1;
        } else if (var0 == 97) {
            iM = 25;
            iN = 13;
            jo = 7;
            js = 79 * je;
            jt = 11 * je;
            jp = 8;
            ju = 1 * je;
            jv = 12 * je;
            jq = -1;
            jr = -1;
        } else if (var0 == 8) {
            iM = 80;
            iN = 14;
            jo = 97;
            js = 23 * je;
            jt = 7 * je;
            jp = 9;
            ju = 1 * je;
            jv = 11 * je;
            jq = -1;
            jr = -1;
        } else if (var0 == 9) {
            iM = 80;
            iN = 14;
            jo = 8;
            js = 79 * je;
            jt = 9 * je;
            jp = -1;
            ju = 1 * je;
            jv = 11 * je;
            jq = -1;
            jr = 10;
            jy = 2 * je;
            jz = 0 * je;
        } else if (var0 == 10) {
            iM = 40;
            iN = 40;
            jo = -1;
            jp = 11;
            ju = 24;
            jv = 264;
            jq = -1;
            jr = -1;
        } else if (var0 == 11) {
            iM = 90;
            iN = 14;
            jo = 10;
            js = 936;
            jt = 720;
            jp = 96;
            ju = 24;
            jv = 192;
            jq = -1;
            jr = -1;
        } else if (var0 == 96) {
            iM = 25;
            iN = 14;
            jo = 11;
            js = 2112;
            jt = 288;
            jp = 12;
            ju = 24;
            jv = 192;
            jq = -1;
            jr = -1;
        } else if (var0 == 12) {
            iM = 40;
            iN = 25;
            jo = 96;
            js = 552;
            jt = 264;
            jp = 13;
            ju = 24;
            jv = 216;
            jq = -1;
            jr = -1;
        } else if (var0 == 13) {
            iM = 80;
            iN = 14;
            jo = 12;
            js = 936;
            jt = 456;
            jp = 14;
            ju = 24;
            jv = 168;
            jq = -1;
            jr = -1;
        } else if (var0 == 14) {
            iM = 60;
            iN = 16;
            jo = 13;
            js = 1896;
            jt = 264;
            jp = 15;
            ju = 24;
            jv = 120;
            jq = -1;
            jr = -1;
        } else if (var0 == 15) {
            iM = 40;
            iN = 30;
            jo = 14;
            js = 1416;
            jt = 144;
            jp = 16;
            ju = 24;
            jv = 264;
            jq = -1;
            jr = -1;
        } else if (var0 == 16) {
            iM = 70;
            iN = 15;
            jo = 15;
            js = 936;
            jt = 168;
            jp = 17;
            ju = 24;
            jv = 432;
            jq = -1;
            jr = -1;
        } else if (var0 == 17) {
            iM = 50;
            iN = 20;
            jo = 16;
            js = 1656;
            jt = 144;
            jp = 18;
            ju = 24;
            jv = 288;
            jq = -1;
            jr = -1;
        } else if (var0 == 18) {
            iM = 70;
            iN = 15;
            jo = 17;
            js = 1176;
            jt = 384;
            jp = 95;
            ju = 24;
            jv = 192;
            jq = -1;
            jr = -1;
        } else if (var0 == 95) {
            iM = 25;
            iN = 14;
            jo = 18;
            js = 1632;
            jt = 312;
            jp = -1;
            jq = -1;
            jr = -1;
        } else if (var0 == 94) {
            iM = 24;
            iN = 14;
            jo = -1;
            jp = -1;
            jq = -1;
            jr = -1;
        } else if (var0 == 99) {
            iM = 70;
            iN = 15;
            jo = -1;
            jp = 4;
            ju = 2 * je;
            jv = 5 * je;
            jq = -1;
            jr = -1;
        }

        iO = iM * je;
        iP = iN * je;

        try {
            InputStream var3 = "".getClass().getResourceAsStream("/m/" + var0);
            iQ = new byte[iM * iN];
            iR = new int[iM * iN];

            for(int var1 = 0; var1 < iM * iN; ++var1) {
                iQ[var1] = (byte)var3.read();
                if (iQ[var1] != 0) {
                    iS[iQ[var1] - 1] = true;
                }

                int[] var10000;
                if (iQ[var1] == 1 || iQ[var1] == 2 || iQ[var1] == 3 || iQ[var1] == 4 || iQ[var1] == 5 || iQ[var1] == 6 || iQ[var1] == 7 || iQ[var1] == 36 || iQ[var1] == 37 || iQ[var1] == 54 || iQ[var1] == 91 || iQ[var1] == 92 || iQ[var1] == 93 || iQ[var1] == 94 || iQ[var1] == 73 || iQ[var1] == 74 || iQ[var1] == 97 || iQ[var1] == 98 || iQ[var1] == 120) {
                    var10000 = iR;
                    var10000[var1] |= 2;
                }

                if (iQ[var1] == 2 || iQ[var1] == 3 || iQ[var1] == 4 || iQ[var1] == 5 || iQ[var1] == 6 || iQ[var1] == 20 || iQ[var1] == 21 || iQ[var1] == 22 || iQ[var1] == 23 || iQ[var1] == 36 || iQ[var1] == 37 || iQ[var1] == 38 || iQ[var1] == 39) {
                    var10000 = iR;
                    var10000[var1] |= 8192;
                }

                if (iQ[var1] == 8 || iQ[var1] == 9 || iQ[var1] == 10 || iQ[var1] == 12 || iQ[var1] == 13 || iQ[var1] == 14 || iQ[var1] == 30) {
                    var10000 = iR;
                    var10000[var1] |= 16;
                }

                if (iQ[var1] == 17) {
                    var10000 = iR;
                    var10000[var1] |= 32;
                }

                if (iQ[var1] == 18) {
                    var10000 = iR;
                    var10000[var1] |= 128;
                }

                if (iQ[var1] == 37 || iQ[var1] == 38) {
                    var10000 = iR;
                    var10000[var1] |= 4;
                }

                if (iQ[var1] == 36 || iQ[var1] == 39) {
                    var10000 = iR;
                    var10000[var1] |= 8;
                }

                if (iQ[var1] == 19) {
                    var10000 = iR;
                    var10000[var1] |= 64;
                    if ((iR[var1 - iM] & 8192) == 8192) {
                        var10000 = iR;
                        var10000[var1] |= 8192;
                    }
                }

                if (iQ[var1] == 35) {
                    var10000 = iR;
                    var10000[var1] |= 4096;
                }

                if (iQ[var1] == 32) {
                    var10000 = iR;
                    var10000[var1] |= 256;
                }

                if (iQ[var1] == 7) {
                    var10000 = iR;
                    var10000[var1] |= 2048;
                }

                if (iQ[var1] == 33 || iQ[var1] == 34) {
                    var10000 = iR;
                    var10000[var1] |= 512;
                }
            }
        } catch (Exception var2) {
            Midlet.c.c();
        }

        ah();
    }

    private static void j(Graphics var0) {
        for(int var1 = bw; var1 < bA; ++var1) {
            for(int var2 = bx; var2 < bB; ++var2) {
                int var3;
                if ((var3 = iQ[var2 * iM + var1] - 1) != -1) {
                    var0.drawRegion(iT, 0, var3 * je, 24, 24, 0, var1 * je, var2 * je, 0);
                }

                if ((h(var1, var2) & 32) == 32) {
                    var0.drawRegion(iU, 0, 24 * (x % 4), 24, 24, 0, var1 * je, var2 * je, 0);
                } else if ((h(var1, var2) & 64) == 64) {
                    if ((h(var1, var2 - 1) & 32) == 32) {
                        var0.drawRegion(iU, 0, 24 * (x % 4), 24, 24, 0, var1 * je, var2 * je, 0);
                    } else if ((h(var1, var2 - 1) & 8192) == 8192) {
                        var0.drawRegion(iT, 0, 504, 24, 24, 0, var1 * je, var2 * je, 0);
                    }

                    var0.drawRegion(iV, 0, (x % 8 >> 2) * 24, 24, 24, 0, var1 * je, var2 * je, 0);
                }

                if ((h(var1, var2) & 4096) == 4096) {
                    if ((h(var1, var2 - 1) & 32) == 32) {
                        var0.drawRegion(iU, 0, 24 * (x % 4), 24, 24, 0, var1 * je, var2 * je, 0);
                    } else if ((h(var1, var2 - 1) & 8192) == 8192) {
                        var0.drawRegion(iT, 0, 504, 24, 24, 0, var1 * je, var2 * je, 0);
                    }

                    var0.drawRegion(iT, 0, (iQ[var2 * iM + var1] - 1) * je, 24, 24, 0, var1 * je, var2 * je, 0);
                }
            }
        }

    }

    private static void k(Graphics var0) {
        for(int var1 = bw; var1 < bA; ++var1) {
            for(int var2 = bx; var2 < bB; ++var2) {
                int var3 = iQ[var2 * iM + var1] - 1;
                if ((h(var1, var2) & 16) == 16) {
                    jg = (jf = var1 * je - fO) - bC;
                    jh = (ji = (je - 2) * jg / je) + bC;
                    var0.drawRegion(iT, 0, var3 * je, 24, 24, 0, jh + fO, var2 * je, 0);
                } else if ((h(var1, var2) & 256) != 256 && (h(var1, var2) & 512) != 512) {
                    if ((h(var1, var2) & 32) == 32) {
                        var0.drawRegion(iU, 0, 24 * (x % 4), 24, 24, 0, var1 * je, var2 * je, 0);
                    } else if ((h(var1, var2) & 64) != 64 && (h(var1, var2) & 4096) != 4096) {
                        if ((h(var1, var2) & 1024) == 1024) {
                            var0.drawRegion(iT, 0, var3 * je, 24, 24, 0, var1 * je, var2 * je + 1, 0);
                        } else if (var3 != -1) {
                            var0.drawRegion(iT, 0, var3 * je, 24, 24, 0, var1 * je, var2 * je, 0);
                        }
                    } else if ((h(var1, var2 - 1) & 32) == 32) {
                        var0.drawRegion(iU, 0, 24 * (x % 4), 24, 24, 0, var1 * je, var2 * je, 0);
                    } else if ((h(var1, var2 - 1) & 8192) == 8192) {
                        var0.drawRegion(iT, 0, 504, 24, 24, 0, var1 * je, var2 * je, 0);
                    }
                }
            }
        }

    }

    private static void l(Graphics var0) {
        if (!a) {
            for(int var1 = bw; var1 < bA; ++var1) {
                for(int var2 = bx; var2 < bB; ++var2) {
                    if ((h(var1, var2) & 64) == 64) {
                        var0.drawRegion(iV, 0, (x % 8 >> 2) * 24, 24, 24, 0, var1 * je, var2 * je, 0);
                    } else if ((h(var1, var2) & 256) == 256) {
                        jg = (jf = var1 * je - fO) - bC;
                        jh = (ji = (je - 1) * jg / je) + bC;
                        var0.drawImage(jd, jh + fO - 2 + (var1 + var2) % 5, var2 * je - 3 + var2 % 5, 0);
                    }

                    if ((h(var1, var2) & 512) == 512) {
                        var0.drawRegion(iT, 0, (iQ[var2 * iM + var1] - 1) * je, 24, 24, 0, var1 * je, var2 * je + 2, 0);
                    }

                    if ((h(var1, var2) & 4096) == 4096) {
                        var0.drawRegion(iT, 0, (iQ[var2 * iM + var1] - 1) * je, 24, 24, 0, var1 * je, var2 * je, 0);
                    }
                }
            }

        }
    }

    private static int h(int var0, int var1) {
        return iR[var1 * iM + var0];
    }

    private static int i(int var0, int var1) {
        return iR[var1 / je * iM + var0 / je];
    }

    private static boolean g(int var0, int var1, int var2) {
        return (iR[var1 / je * iM + var0 / je] & var2) == var2;
    }

    private static void h(int var0, int var1, int var2) {
        int[] var10000 = iR;
        int var10001 = var1 / je * iM + var0 / je;
        var10000[var10001] |= 1024;
    }

    private static void i(int var0, int var1, int var2) {
        iR[var1 * iM + 24] = 0;
    }

    private static void j(int var0, int var1, int var2) {
        int[] var10000 = iR;
        int var10001 = var1 / je * iM + var0 / je;
        var10000[var10001] &= -1025;
    }

    private static int H(int var0) {
        return var0 / je * je;
    }

    private static int I(int var0) {
        return var0 / je * je;
    }

    private static void ai() {
        try {
            jC = Image.createImage("/u/lg.png");
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
        u(ap[jI[var0]]);
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
            jF = new Image[kl.length][2][4];

            for(var0 = 0; var0 < kl.length; ++var0) {
                for(int var5 = 0; var5 < 2; ++var5) {
                    for(var2 = 0; var2 < 4; ++var2) {
                        try {
                            jF[var0][var5][var2] = Image.createImage("/mob/m" + kl[var0] + var5 + var2 + ".png");
                        } catch (Exception var3) {
                        }

                        if (jF[var0][var5][var2] == null && var5 == 1) {
                            jF[var0][1][var2] = jF[var0][0][var2];
                        }
                    }

                    if (jF[var0][var5][2] == null) {
                        jF[var0][var5][2] = jF[var0][var5][1];
                    }
                }
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
                    if (jK[var0] < iP && jJ[var0] >= 0 && jJ[var0] < iO) {
                        if ((jV[var0] == 0 || jV[var0] < 3 && jT[var0] > 7) && (i(jJ[var0], jK[var0]) & 2) == 2) {
                            jT[var0] = jT[var0] > 4 ? -4 : -jT[var0];
                            var10002 = jV[var0]++;
                        }

                        if (g(jJ[var0], jK[var0], 64) && jK[var0] % je > 8) {
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
                ki[var0][var1] = ki[var0][var1] * je + 12;
                kj[var0][var1] *= je;
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
        iT = null;
        kw = new Image[4];

        try {
            kw[0] = Image.createImage("/u/wpt0.png");
            kw[1] = Image.createImage("/u/wpt2.png");
            kw[2] = Image.createImage("/u/wpt3.png");
            kw[3] = Image.createImage("/u/wpt4.png");
        } catch (Exception var3) {
        }

        if (kG == null) {
            kG = new Image[4];

            for(int var0 = 0; var0 < 4; ++var0) {
                try {
                    kG[var0] = Image.createImage("/u/bd" + var0 + ".png");
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
            var0.drawRegion(ew[0], 0, var1 * 15, 13, 15, 0, kn + var1 * 20, V + 20, 3);
            var0.drawRegion(ew[1], 0, var1 * 15, 13, 15, 0, kn + 60 + var1 * 20, V + 20, 3);
        }

        for(var1 = 0; var1 < 4; ++var1) {
            var0.setColor(0);
            var0.fillRect(kn + 10 + var1 * 20, V + 32, 19, 20);
            var0.setColor(4856064);
            var0.drawRect(kn + 10 + var1 * 20, V + 32, 18, 20);
            var0.drawImage(kw[var1], kn + 20 + var1 * 20, V + 42, 3);
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

        var0.drawImage(kG[0], bC - 64, V - 19, 20);
        var0.drawImage(kG[1], bC + 65, V - 19, 24);
        var0.drawImage(kG[2], bC - 64, V + 83, 36);
        var0.drawImage(kG[3], bC + 65, V + 83, 40);
    }

    private static void ao() {
        kz = bD - 40;
        kA = bC - 50;
        if (kG == null) {
            kG = new Image[4];

            for(int var0 = 0; var0 < 4; ++var0) {
                try {
                    kG[var0] = Image.createImage("/u/bd" + var0 + ".png");
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
            var0.drawRegion(ew[0], 0, var1 * 15, 13, 15, 0, kA + var1 * 20, kz + 20, 3);
            var0.drawRegion(ew[1], 0, var1 * 15, 13, 15, 0, kA + 60 + var1 * 20, kz + 20, 3);
        }

        var0.setColor(kD[kC]);
        var0.drawRect(kA - 10 + kB * 20, kz + 10, 20, 20);
        FontGraph.drawBold(var0, kE[kB], bC, kz + 40, 2);
        FontGraph.drawBold(var0, "Số lượng: " + W[kB], bC, kz + 55, 2);
        var0.drawImage(kG[0], bC - 64, kz - 19, 20);
        var0.drawImage(kG[1], bC + 65, kz - 19, 24);
        var0.drawImage(kG[2], bC - 64, kz + 83, 36);
        var0.drawImage(kG[3], bC + 65, kz + 83, 40);
    }

    private static void ap() {
        kH = bD - 40;
        iT = null;
        if (kG == null) {
            kG = new Image[4];

            for(int var0 = 0; var0 < 4; ++var0) {
                try {
                    kG[var0] = Image.createImage("/u/bd" + var0 + ".png");
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

        var0.drawImage(eC[0], aV - 55 + kJ % 2, kH + kI * 18 - kJ % 2, 0);
        var0.drawImage(kG[0], bC - 64, kH - 19, 20);
        var0.drawImage(kG[1], bC + 65, kH - 19, 24);
        var0.drawImage(kG[2], bC - 64, kH + 83, 36);
        var0.drawImage(kG[3], bC + 65, kH + 83, 40);
    }

    private static void aq() {
        lf = 280 - r;
        lg = 280 - s;
        kV = 0;

        try {
            kR = Image.createImage("/wm.png");
            kS = Image.createImage("/u/x.png");
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
        kR = null;
        kS = null;
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
        var0.drawImage(kR, 0, 0, 0);

        for(int var1 = 0; var1 < 19; ++var1) {
            if (var1 == lh) {
                var0.drawImage(eo[0], kN[var1] + 10, kO[var1], 3);
            }

            if (X[var1] && (var1 != kV || kW % 10 > 5)) {
                var0.drawImage(kS, kN[var1], kO[var1], 3);
            }
        }

        var0.drawImage(eC[0], kT, kU, 24);
        if (kV != -1) {
            var0.translate(-var0.getTranslateX(), -var0.getTranslateY());
            var0.setColor(6562304);
            var0.fillRect(aV - 60, s - 35, 120, 33);
            var0.setColor(12281361);
            var0.drawRect(aV - 59, s - 34, 117, 30);
            FontGraph.drawBold(var0, kL[kV], aV, s - 25, 2);
            var0.drawImage(kG[0], aV - 60, s - 35, 20);
            var0.drawImage(kG[1], aV + 60, s - 35, 24);
            var0.drawImage(kG[2], aV - 60, s - 2, 36);
            var0.drawImage(kG[3], aV + 60, s - 2, 40);
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
            lj = Image.createImage("/u/m.png");
        } catch (Exception var0) {
        }
    }

    public static void a(String var0) {
        lr = Midlet.a(var0, 100);
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
        if (kG == null) {
            kG = new Image[4];

            for(int var0 = 0; var0 < 4; ++var0) {
                try {
                    kG[var0] = Image.createImage("/u/bd" + var0 + ".png");
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

        var0.drawImage(kG[0], bC - 64, Y - 19, 20);
        var0.drawImage(kG[1], bC + 65, Y - 19, 24);
        var0.drawImage(kG[2], bC - 64, Y + 83, 36);
        var0.drawImage(kG[3], bC + 65, Y + 83, 40);
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
            ls = new Image[6][2];

            try {
                ls[0][0] = Image.createImage("/npc/00.png");
                ls[0][1] = Image.createImage("/npc/01.png");
                ls[1][0] = Image.createImage("/npc/10.png");
                ls[1][1] = Image.createImage("/npc/11.png");
                ls[2][0] = Image.createImage("/npc/20.png");
                ls[2][1] = Image.createImage("/npc/21.png");
                ls[3][0] = Image.createImage("/npc/30.png");
                ls[3][1] = Image.createImage("/npc/31.png");
                ls[4][0] = Image.createImage("/npc/40.png");
                ls[4][1] = Image.createImage("/npc/41.png");
                ls[5][0] = Image.createImage("/npc/50.png");
                ls[5][1] = Image.createImage("/npc/51.png");
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
            ls = new Image[6][2];

            try {
                ls[5][0] = Image.createImage("/npc/50.png");
                ls[5][1] = Image.createImage("/npc/51.png");
            } catch (Exception var6) {
            }

            lt = 1;
            av();
            c(0, 5, 54, 9);
        }

        if (var0 == 2) {
            ls = new Image[6][2];

            try {
                ls[5][0] = Image.createImage("/npc/50.png");
                ls[5][1] = Image.createImage("/npc/51.png");
            } catch (Exception var5) {
            }

            lt = 1;
            av();
            c(0, 5, 23, 12);
        }

        if (var0 == 5) {
            ls = new Image[6][2];

            try {
                ls[5][0] = Image.createImage("/npc/50.png");
                ls[5][1] = Image.createImage("/npc/51.png");
            } catch (Exception var4) {
            }

            lt = 1;
            av();
            c(0, 5, 12, 5);
        }

        if (var0 == 6) {
            ls = new Image[6][2];

            try {
                ls[5][0] = Image.createImage("/npc/50.png");
                ls[5][1] = Image.createImage("/npc/51.png");
            } catch (Exception var3) {
            }

            lt = 1;
            av();
            c(0, 5, 14, 9);
        }

        if (var0 == 99 || var0 == 101) {
            ls = new Image[6][2];

            try {
                ls[4][0] = Image.createImage("/npc/40.png");
                ls[4][1] = Image.createImage("/npc/41.png");
            } catch (Exception var2) {
            }

            lt = 1;
            av();
            c(0, 4, 3, 12);
        }

        if (var0 == 94) {
            ls = new Image[6][2];

            try {
                ls[0][0] = Image.createImage("/npc/00.png");
                ls[0][1] = Image.createImage("/npc/01.png");
                ls[4][0] = Image.createImage("/npc/40.png");
                ls[4][1] = Image.createImage("/npc/41.png");
                ls[2][0] = Image.createImage("/npc/20.png");
                ls[2][1] = Image.createImage("/npc/21.png");
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
        lu[var0] = var2 * je + 12;
        lv[var0] = var3 * je;
    }

    private static void av() {
        lu = new int[lt];
        lv = new int[lt];
        lw = new int[lt];
    }

    private static void r(Graphics var0) {
        for(int var1 = 0; var1 < lt; ++var1) {
            if (lu[var1] + 10 >= fO && lu[var1] - 10 <= fO + r && lv[var1] >= fP && lv[var1] - 32 <= fP + s) {
                var0.drawImage(ls[lw[var1]][1], lu[var1], lv[var1], 33);
                var0.drawImage(ls[lw[var1]][0], lu[var1], lv[var1] - lF[lw[var1]] + (x % (15 + lw[var1]) > 4 ? 0 : 1), 33);
            }
        }

    }

    private static void aw() {
        if (aS[12]) {
            Midlet.c.notifyDestroyed();
        }

        Y(1);
        int var10002 = lV[0]++;
    }

    private void ax() {
        if (aS[2]) {
            --this.ma;
            if (this.ma < 0) {
                this.ma = 1;
            }

            d();
        }

        if (aS[8]) {
            ++this.ma;
            if (this.ma > 1) {
                this.ma = 0;
            }

            d();
        }

        if (aS[4]) {
            --this.lZ;
            if (this.lZ < 0) {
                this.lZ = 4;
            }

            d();
        }

        if (aS[6]) {
            ++this.lZ;
            if (this.lZ > 4) {
                this.lZ = 0;
            }

            d();
        }

        if (aS[5]) {
            this.aB();
            d();
        }

        int var2;
        if (aS[12]) {
            lV[5] = Integer.parseInt(lU.substring(9, 10));
            lV[6] = Integer.parseInt(lU.substring(10, 11));
            lV[7] = Integer.parseInt(lU.substring(11, 12));
            lV[8] = Integer.parseInt(lU.substring(12, 13));
            b var1 = this;
            if (this.lY.length() < 4) {
                Z = 0;
            } else {
                lV[1] = Integer.parseInt(this.lY.substring(0, 1));
                lV[2] = Integer.parseInt(this.lY.substring(1, 2));
                lV[3] = Integer.parseInt(this.lY.substring(2, 3));
                lV[4] = Integer.parseInt(this.lY.substring(3, 4));
                lV[8] = (lV[8] + lV[7]) % 10;
                lV[7] = (lV[7] + lV[6]) % 10;
                lV[6] = (lV[6] + lV[5]) % 10;
                lV[5] = (lV[5] + lV[8]) % 10;

                for(var2 = 1; var2 < 8; ++var2) {
                    if (lV[var2 - 1] != lV[var2]) {
                        var1.az();
                    } else if (lV[var2] != lV[var2] + 1) {
                        var1.aA();
                    } else {
                        d();
                        var1.aD();
                        var1.aA();
                        aw();
                        d();
                    }
                }
            }

            d();
        }

        if (aS[13]) {
            this.ay();
            d();
        }

        if (aN) {
            for(int var4 = 0; var4 < 2; ++var4) {
                for(var2 = 0; var2 < 5; ++var2) {
                    if (aO > var2 * 20 + aV - 50 && aO < var2 * 20 + aV - 50 + 20 && aP > var4 * 25 + aW && aP < var4 * 25 + aW + 25) {
                        this.lZ = var2;
                        this.ma = var4;
                        this.aB();
                        aN = false;
                    }
                }
            }
        }

    }

    private void ay() {
        if (this.lY.length() != 0) {
            this.lY = this.lY.substring(0, this.lY.length() - 1);
        }

    }

    private void az() {
        d();
        Z = 0;
        aC();
        d();
    }

    private void aA() {
        d();
        this.aD();
        Z = 4;
        this.az();
        d();
    }

    private void aB() {
        if (this.lY.length() < 4) {
            this.lY = this.lY + (this.ma * 5 + this.lZ);
        }

    }

    private static void aC() {
        if (aS[5] || aS[12]) {
            d();
            Z = 0;
        }

    }

    private void aD() {
        if (aS[5] || aS[12]) {
            String var1;
            if ((var1 = aG()) == null || var1.equals("")) {
                f(var1 = "TEAM PAY " + (System.currentTimeMillis() % 8000L + 1000L) + "009" + "003");
            }

            Z = 1;
            a(var1, "sms://", 0);
            d();
        }

        if (aS[13]) {
            Z = 0;
            d();
        }

    }

    private void aE() {
        if (aS[2]) {
            --this.lX;
            if (this.lX < 0) {
                this.lX = lW == 0 ? 1 : 2;
            }

            d();
        }

        if (aS[8]) {
            ++this.lX;
            if (this.lX > (lW == 0 ? 1 : 2)) {
                this.lX = 0;
            }

            d();
        }

        if (aS[5] || aS[12]) {
            this.aF();
            d();
        }

        if (aS[13]) {
            Midlet.c.notifyDestroyed();
        }

        if (aN) {
            for(int var1 = 0; var1 < (lW == 0 ? 2 : 3); ++var1) {
                if (aP > aW - 5 + var1 * lR && aP < aW - 5 + var1 * lR + lR) {
                    if (this.lX != var1) {
                        this.lX = var1;
                    } else {
                        this.aF();
                    }

                    aN = false;
                    return;
                }
            }
        }

    }

    private void aF() {
        if (this.lX == 0 && lW > 0) {
            W(--lW);
            e = 4;
            ai();
            System.gc();
        }

        if (this.lX == 1 && lW > 0 || this.lX == 0 && lW == 0) {
            Z = 2;
        }

        if (this.lX == 2 && lW > 0 || this.lX == 1 && lW == 0) {
            lU = aG();
            Z = 4;
        }

    }

    private void s(Graphics var1) {
        var1.setColor(7171437);
        var1.drawString("Nhap ma kich hoat:", aV, aW - 60, 17);
        var1.setColor(11788624);
        var1.fillRect(this.lZ * 20 + aV - 50, this.ma * 25 + aW, 18, 23);
        int var2 = 0;

        for(int var3 = 0; var3 < 2; ++var3) {
            for(int var4 = 0; var4 < 5; ++var4) {
                var1.setColor(11788624);
                var1.drawRect(var4 * 20 + aV - 50, var3 * 25 + aW, 18, 23);
                var1.setColor(var4 == this.lZ && var3 == this.ma ? 16777215 : 7171437);
                var1.drawString(String.valueOf(var2), var4 * 20 + aV - 50 + 10, var3 * 25 + aW + 3, 17);
                ++var2;
            }
        }

        String var5 = this.lY;
        if (lN % 10 > 5) {
            var5 = var5 + "|";
        }

        var1.setColor(7171437);
        var1.drawString(var5, aV - 20, aW - 40, 20);
        var1.setColor(16777215);
        var1.setFont(lQ);
        var1.drawString("OK", 2, aU - lR + 3, 20);
        var1.drawString("Xoa", aT - 2, aU - lR + 3, 24);
    }

    private void t(Graphics var1) {
        var1.setColor(7171437);
        if (lV[1] == lV[5] && lV[2] == lV[6] && lV[3] == lV[7] && lV[4] == lV[8]) {
            Z = 7;
        }

        var1.setColor(11788624);
        var1.drawRect(3, aW - 8 + this.lX * lR, aT - 6, lR);
        var1.drawRect(4, aW - 8 + this.lX * lR + 1, aT - 8, lR - 2);
        var1.setColor(208472);
        int var2;
        if (lW > 0) {
            var1.setFont(lQ);
            var1.drawString("..:TEA MOBILE:..", aV, aW - (lR << 1), 17);
            var1.setFont(lP);

            for(var2 = 0; var2 < 3; ++var2) {
                var1.setColor(7171437);
                var1.drawString(lL[var2], lO, aW - 5 + var2 * lR, 20);
            }
        } else {
            var1.drawString("Da het han dung thu", aV, aW - (lR << 1), 17);
            var1.drawString("Moi ban kich hoat.", aV, aW - lR, 17);

            for(var2 = 0; var2 < 2; ++var2) {
                var1.setColor(7171437);
                var1.drawString(lM[var2], lO, aW - 5 + var2 * lR, 20);
            }
        }

        var1.setColor(16777215);
        var1.setFont(lQ);
        var1.drawString("Chon", 2, aU - lR + 3, 20);
        var1.drawString("Thoat", aT - 2, aU - lR + 3, 24);
    }

    private static void a(String var0, String var1, int var2) {
        if (var2 == 0) {
            Z = 6;
            return;
        }

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

    private static byte[] e(String var0) {
        var0 = "NINJASCHOOL" + var0;

        try {
            RecordStore var3;
            byte[] var1 = (var3 = RecordStore.openRecordStore(var0, false)).getRecord(1);
            var3.closeRecordStore();
            return var1;
        } catch (Exception var2) {
            return null;
        }
    }

    private static void a(String var0, byte[] var1) {
        var0 = "NINJASCHOOL" + var0;

        try {
            RecordStore var3;
            if ((var3 = RecordStore.openRecordStore(var0, true)).getNumRecords() > 0) {
                var3.setRecord(1, var1, 0, var1.length);
            } else {
                var3.addRecord(var1, 0, var1.length);
            }

            var3.closeRecordStore();
        } catch (Exception var2) {
        }
    }

    private static void f(String var0) {
        a("xxx", var0.getBytes());
    }

    private static String aG() {
        byte[] var0;
        return (var0 = e("xxx")) == null ? null : new String(var0);
    }

    private static void W(int var0) {
        byte[] var1;
        (var1 = new byte[1])[0] = (byte)var0;
        a("np", var1);
    }

    private static void X(int var0) {
        byte[] var1;
        (var1 = new byte[1])[0] = (byte)var0;
        a("r", var1);
    }

    private static void Y(int var0) {
        byte[] var1;
        (var1 = new byte[1])[0] = (byte)var0;
        a("g", var1);
    }

    private static int aH() {
        byte[] var0;
        return (var0 = e("g")) == null ? 0 : var0[0];
    }

    public final void commandAction(Command var1, Displayable var2) {
        if (var1.getLabel().equals("OK")) {
            a(aG() + " >> " + ((TextBox)var2).getString(), "sms://", 0);
        }

        Display.getDisplay(Midlet.c).setCurrent(this);
        this.setFullScreenMode(true);
    }
}
