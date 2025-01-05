/*
ソースコード作成者: 浅島
修正日時: 2025/01/05
ソースコードの動作や役割:予約機能 → Reserve.htmlから情報を取得しDBに保存
コメント: SelectDBを使っていないため要調整
         サーブレットの呼び出しができていない
         重複チェックが入ってない
*/

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// "/reserve"エンドポイントに対応するサーブレット
@WebServlet("/reserve")
public class ReservationServlet extends HttpServlet {

    // データベース接続情報
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // フォームから送信されたデータを取得
        int restaurantId = Integer.parseInt(request.getParameter("restaurant_id")); // 店舗ID
        String customerName = request.getParameter("customer_name");                 // 顧客名
        String reservationDate = request.getParameter("reservation_date");           // 予約日
        String reservationTime = request.getParameter("reservation_time");           // 予約時間
        int partySize = Integer.parseInt(request.getParameter("party_size"));        // 人数
        String phone = request.getParameter("phone");                                // 電話番号 (オプション)

        // レスポンス設定
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // データベースに接続して予約データを保存
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO reservations (restaurant_id, customer_name, reservation_date, reservation_time, party_size, phone) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, restaurantId);
                stmt.setString(2, customerName);
                stmt.setString(3, reservationDate);
                stmt.setString(4, reservationTime);
                stmt.setInt(5, partySize);
                stmt.setString(6, phone);

                // クエリ実行
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    out.println("<p>予約が正常に登録されました！</p>");
                } else {
                    out.println("<p>予約の登録に失敗しました。</p>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(out); // デバッグ用にエラーを出力
            out.println("<p>エラーが発生しました。もう一度お試しください。</p>");
        }
    }
}
