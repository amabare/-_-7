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
import java.sql.*;
import java.util.*;  // List, Map, HashMap をインポート
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/insert_reserve")
public class Insert_Reserve extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // フォームデータを取得
        String storeIdStr = request.getParameter("store_id");
        String customerName = request.getParameter("customer_name");
        String customerPhone = request.getParameter("customer_phone");
        String reservationTimeStr = request.getParameter("reservation_time");
        String numGuestsStr = request.getParameter("num_guests");

        // 必要なパラメータが不足している場合のエラーチェック
        if (storeIdStr == null || customerName == null || reservationTimeStr == null || numGuestsStr == null) {
            throw new IllegalArgumentException("必要なパラメータが不足しています");
        }

        int storeId = Integer.parseInt(storeIdStr);
        int numGuests = Integer.parseInt(numGuestsStr);

        // データベース接続と重複チェック
        try {
            // 重複チェックのSQLクエリ
            String checkSql = "store_id = ? AND reservation_time = ?";
            Object[] checkParams = { storeId, reservationTimeStr };
            List<Map<String, Object>> existingReservations = DatabaseUtility.select("RESERVATION", checkSql, checkParams);

            if (existingReservations != null && !existingReservations.isEmpty()) {
                // すでに予約が存在する場合
                out.println("<p>この日時にはすでに予約があります。別の時間を選択してください。</p>");
            } else {
                // 重複がない場合、予約情報をINSERT
                Map<String, Object> reservationData = new HashMap<>();
                reservationData.put("store_id", storeId);
                reservationData.put("customer_name", customerName);
                reservationData.put("customer_phone", customerPhone);
                reservationData.put("reservation_time", reservationTimeStr);
                reservationData.put("num_guests", numGuests);
                reservationData.put("status", "PENDING");

                int rowsInserted = DatabaseUtility.insert("RESERVATION", reservationData);
                if (rowsInserted > 0) {
                    // 予約が正常に登録された場合
                    out.println("<p>予約が正常に登録されました！</p>");
                } else {
                    out.println("<p>予約の登録に失敗しました。</p>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>エラーが発生しました: " + e.getMessage() + "</p>");
        }
    }
}
