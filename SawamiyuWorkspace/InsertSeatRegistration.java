import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
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
            out.println("<button onclick=\"window.location.href='/seat_list'\">座席一覧に戻る</button>");
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
            out.println("<button onclick=\"window.location.href='/seat_list'\">座席一覧に戻る</button>");
            return;
        }

        // データベースに挿入するための値をマップにまとめる
        Map<String, Object> seatData = new HashMap<>();
        seatData.put("seat_name", seatName);
        seatData.put("capacity", capacity);
        seatData.put("store_id", storeId);
        seatData.put("seat_status", "空席");  // 初期状態は「空席」
        seatData.put("created_at", new java.sql.Timestamp(System.currentTimeMillis()));
        seatData.put("updated_at", new java.sql.Timestamp(System.currentTimeMillis()));

        // DatabaseUtilityのinsertメソッドを使用してデータを挿入
        try {
            int rowsInserted = DatabaseUtility.insert("SEAT", seatData);

            if (rowsInserted > 0) {
                out.println("<p>座席情報が正常に登録されました！</p>");
            } else {
                out.println("<p>座席情報の登録に失敗しました。</p>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>エラーが発生しました: " + e.getMessage() + "</p>");
        }

        // 共通のボタンで遷移
        out.println("<button onclick=\"window.location.href='/seat_list'\">座席一覧に戻る</button>");
    }
}
