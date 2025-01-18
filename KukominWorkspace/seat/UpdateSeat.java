import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update_seat")
public class UpdateSeat extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // フォームデータを取得
        String seatIdStr = request.getParameter("seat_id");
        String seatName = request.getParameter("seat_name");
        String capacityStr = request.getParameter("capacity");

        // 必要なパラメータが不足している場合のエラーチェック
        if (seatIdStr == null || seatName == null || capacityStr == null) {
            out.println("<p>必要なパラメータが不足しています。</p>");
            return;
        }

        int seatId;
        int capacity;

        try {
            // 数値型のパラメータを変換
            seatId = Integer.parseInt(seatIdStr);
            capacity = Integer.parseInt(capacityStr);
        } catch (NumberFormatException e) {
            out.println("<p>座席IDまたは収容人数が無効です。</p>");
            return;
        }

        // データ更新の準備
        Map<String, Object> values = new HashMap<>();
        values.put("seat_name", seatName);
        values.put("capacity", capacity);
        values.put("updated_at", new java.sql.Timestamp(System.currentTimeMillis()));

        try {
            // DatabaseUtilityを使用して更新
            String whereClause = "seat_id = " + seatId;
            int rowsUpdated = DatabaseUtility.update("SEAT", values, whereClause);

            if (rowsUpdated > 0) {
                out.println("<p>座席情報が正常に更新されました！</p>");
            } else {
                out.println("<p>座席情報の更新に失敗しました。</p>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>エラーが発生しました: " + e.getMessage() + "</p>");
        }
        
        // 共通のボタンで遷移
        out.println("<button onclick=\"window.location.href='/seat_list'\">座席一覧に戻る</button>");
    }
}
