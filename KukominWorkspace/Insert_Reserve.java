/*
ソースコード作成者: 浅島
修正日時: 2025/01/13
ソースコードの動作や役割:予約機能 → reserveConfirmationではいを押されたらDBにINSERTする
コメント: SelectDBを使っていないため要調整
         重複チェックが入ってない
*/
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/insert_reserve")
public class Insert_Reserve extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/my_database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root00";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // フォームデータを取得
        String restaurantIdStr = request.getParameter("id");
        String customerName = request.getParameter("customer_name");
        String reservationDate = request.getParameter("reservation_date");
        String reservationTime = request.getParameter("reservation_time");
        String partySizeStr = request.getParameter("party_size");

        // 必要なパラメータが不足している場合のエラーチェック
        if (restaurantIdStr == null || customerName == null || reservationDate == null ||
            reservationTime == null || partySizeStr == null) {
            throw new IllegalArgumentException("必要なパラメータが不足しています");
        }

        int restaurantId = Integer.parseInt(restaurantIdStr);
        int partySize = Integer.parseInt(partySizeStr);

        // データベース接続と重複チェック
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // 重複チェックのSQLクエリ
            String checkSql = "SELECT COUNT(*) FROM reservations WHERE id = ? AND reservation_date = ? AND reservation_time = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, restaurantId);
                checkStmt.setString(2, reservationDate);
                checkStmt.setString(3, reservationTime);

                // 予約がすでに存在するかチェック
                int count = 0;
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        count = rs.getInt(1);
                    }
                }

                if (count > 0) {
                    // すでに予約が存在する場合
                    out.println("<p>この日時にはすでに予約があります。別の時間を選択してください。</p>");
                } else {
                    // 重複がない場合、予約情報をINSERT
                    String insertSql = "INSERT INTO reservations (id, customer_name, reservation_date, reservation_time, party_size) "
                                     + "VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                        stmt.setInt(1, restaurantId);
                        stmt.setString(2, customerName);
                        stmt.setString(3, reservationDate);
                        stmt.setString(4, reservationTime);
                        stmt.setInt(5, partySize);

                        int rowsInserted = stmt.executeUpdate();
                        if (rowsInserted > 0) {
                            // 予約が正常に登録された場合
                            out.println("<p>予約が正常に登録されました！</p>");
                        } else {
                            out.println("<p>予約の登録に失敗しました。</p>");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>エラーが発生しました: " + e.getMessage() + "</p>");
        }
    }
}