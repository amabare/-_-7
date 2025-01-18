import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/seat_change")
public class SeatInfoChange extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // フォームからデータを取得
        String seatName = request.getParameter("seat_name");
        String capacityStr = request.getParameter("capacity");
        String storeIdStr = request.getParameter("store_id");
        String seatIdStr = request.getParameter("seat_id");

        // バリデーション用変数
        int capacity = 0;
        int storeId = 0;
        int seatId = 0;

        try {
            // 収容人数と店舗IDを数値に変換
            capacity = Integer.parseInt(capacityStr);
            storeId = Integer.parseInt(storeIdStr);
            seatId = Integer.parseInt(seatIdStr);
        } catch (NumberFormatException e) {
            // 数値変換エラー時の処理
            request.setAttribute("error_message", "収容人数または店舗IDが無効です。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("ui/seat_registration_error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // リクエスト属性にデータを設定
        request.setAttribute("seat_name", seatName);
        request.setAttribute("capacity", capacity);
        request.setAttribute("store_id", storeId);
        request.setAttribute("seat_id", seatId);

        // 必要に応じてデータベース登録処理を追加（コメントアウト例）
        /*
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password")) {
            String sql = "INSERT INTO SEAT (seat_name, capacity, store_id) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, seatName);
                stmt.setInt(2, capacity);
                stmt.setInt(3, storeId);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            request.setAttribute("error_message", "データベース登録中にエラーが発生しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("ui/seat_registration_error.jsp");
            dispatcher.forward(request, response);
            return;
        }
        */

        // JSPページにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("ui/seat_change_confirmation.jsp");
        dispatcher.forward(request, response);
    }
}