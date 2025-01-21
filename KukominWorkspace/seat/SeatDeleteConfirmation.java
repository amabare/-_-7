import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete_seat_confirmation")
public class SeatDeleteConfirmation extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // パラメータから seat_id を取得
        String seatId = request.getParameter("seat_id");

        // 必須パラメータがない場合のエラーハンドリング
        if (seatId == null || seatId.isEmpty()) {
            out.println("<html><head>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/ROOT/ui/styles.css\">");
            out.println("</head><body>");
            out.println("<p>席IDが指定されていません。</p>");
            out.println("</body></html>");
            return;
        }

        // デバッグ用のログをHTMLで表示
        out.println("<html><head>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/resources/css/styles.css\">");
        out.println("</head><body>");
        out.println("<h1>削除確認ページ</h1>");
        out.println("<p>デバッグ情報：</p>");
        out.println("<ul>");
        out.println("<li>Received seat_id: " + seatId + "</li>");
        out.println("</ul>");

        // seat_id をリクエストスコープに設定
        request.setAttribute("seat_id", seatId);

        try {
            out.println("<p>削除確認ページを表示します。</p>");
            out.println("</body></html>");
            // 削除確認ページにフォワード（ここではデバッグ情報のみ表示してフォワードは不要かも）
             request.getRequestDispatcher("ui/seat_delete_confirmation.jsp").forward(request, response);
        } catch (Exception e) {
            out.println("<p>削除確認ページの表示に失敗しました。</p>");
            out.println("<pre>" + e.getMessage() + "</pre>");
            out.println("</body></html>");
        }
    }
}

