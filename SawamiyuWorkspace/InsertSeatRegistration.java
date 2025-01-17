import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/insert_seat_registration")
public class InsertSeatRegistration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // フォームデータを取得
        String seatName = request.getParameter("seat_name");
        String capacityStr = request.getParameter("capacity");
        String storeIdStr = request.getParameter("store_id");

        // 必要なパラメータが不足している場合のエラーチェック
        if (seatName == null || capacityStr == null || storeIdStr == null) {
            out.println("<p>必要なパラメータが不足しています。</p>");
            return;
        }

        int capacity;
        int storeId;

        try {
            // 数値型のパラメータを変換
            capacity = Integer.parseInt(capacityStr);
            storeId = Integer.parseInt(storeIdStr);
        } catch (NumberFormatException e) {
            out.println("<p>収容人数または店舗IDが無効です。</p>");
            return;
        }

        // データベース接続とINSERT処理
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // データベース接続情報
            String url = "jdbc:mysql://localhost:3306/my_database";
            String user = "root";
            String password = "root00";

            // 接続
            conn = DriverManager.getConnection(url, user, password);

            // INSERT文の作成
            String sql = "INSERT INTO SEAT (seat_name, capacity, store_id, seat_status, created_at, updated_at) "
                       + "VALUES (?, ?, ?, '空席', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            pstmt = conn.prepareStatement(sql);

            // パラメータを設定
            pstmt.setString(1, seatName);
            pstmt.setInt(2, capacity);
            pstmt.setInt(3, storeId);

            // 実行
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                out.println("<p>座席情報が正常に登録されました！</p>");
            } else {
                out.println("<p>座席情報の登録に失敗しました。</p>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<p>エラーが発生しました: " + e.getMessage() + "</p>");
        } finally {
            // リソースを解放
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
