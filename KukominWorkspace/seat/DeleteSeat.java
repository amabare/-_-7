import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete_seat")
public class DeleteSeat extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // フォームデータから seat_id を取得
        String seatId = request.getParameter("seat_id");

        // 必要なパラメータが不足している場合のエラーチェック
        if (seatId == null || seatId.isEmpty()) {
            out.println("<p>席IDが指定されていません。</p>");
            return;
        }

        try {
            int seatIdInt;
            try {
                // seat_idを数値に変換
                seatIdInt = Integer.parseInt(seatId);
            } catch (NumberFormatException e) {
                out.println("<p>席IDは数値でなければなりません。</p>");
                return;
            }

            // DatabaseUtility を使用して削除
            String tableName = "SEAT";
            String whereClause = "seat_id = ?";
            Object[] parameters = { seatIdInt };

            int rowsDeleted = DatabaseUtility.delete(tableName, whereClause, parameters);

            if (rowsDeleted > 0) {
                out.println("<p>席情報が正常に削除されました。</p>");
            } else {
                out.println("<p>指定された席IDが存在しないため、削除できませんでした。</p>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>エラーが発生しました: " + e.getMessage() + "</p>");
        }

        // 共通のボタンで遷移
        out.println("<button onclick=\"window.location.href='/seat_list'\">座席一覧に戻る</button>");
    }
}
