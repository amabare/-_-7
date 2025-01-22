import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/seat_list")
public class SeatListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // データベース接続情報
            String url = "jdbc:mysql://database-1.c1w61uuuqanw.us-east-1.rds.amazonaws.com:3306/my_database";
            String user = "admin";
            String password = "root0000";

            // データベース接続
            conn = DriverManager.getConnection(url, user, password);

            // SEATテーブルからデータを取得
            String sql = "SELECT seat_id, seat_name, capacity FROM SEAT";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // HTMLテーブルの開始
            out.println("<!DOCTYPE html>");
            out.println("<html lang='ja'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>席一覧</title>");
            out.println("<style>");
            out.println("table { width: 100%; border-collapse: collapse; }");
            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("button { padding: 5px 10px; cursor: pointer; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>席一覧</h1>");
            out.println("<table>");
            out.println("<tr><th>席名</th><th>収容人数</th><th>操作</th></tr>");

            // データをテーブルに表示
            while (rs.next()) {
                int seatId = rs.getInt("seat_id");
                String seatName = rs.getString("seat_name");
                int capacity = rs.getInt("capacity");

                out.println("<tr>");
                out.println("<td>" + seatName + "</td>");
                out.println("<td>" + capacity + "</td>");
                out.println("<td>");
                out.println("<form action='/ui/seat_change.html' method='get' style='display:inline;'>");
                out.println("<input type='hidden' name='seat_id' value='" + seatId + "'>");
                out.println("<button type='submit'>編集</button>");
                out.println("</form>");
                out.println("<form action='/delete_seat_confirmation' method='post' style='display:inline;'>");
                out.println("<input type='hidden' name='seat_id' value='" + seatId + "'>");
                out.println("<button type='submit'>削除</button>");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            }

            // HTMLテーブルの終了
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<p>データベースエラーが発生しました: " + e.getMessage() + "</p>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

