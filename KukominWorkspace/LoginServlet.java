import java.io.IOException;
import java.sql.ResultSet;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login") // 呼び出されるURLの条件
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームから送信されたデータを取得
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // SELECTモジュールを使用して認証
        String tableName = "users";
        String whereCondition = "email = ? AND password = ?";
        Object[] parameters = {email, password};

        try {
            // DatabaseUtility.select()メソッドを使ってデータベースから結果を取得
            ResultSet rs = DatabaseUtility.select(tableName, whereCondition, parameters);

            if (rs.next()) {
                // ログイン成功時、sample.htmlにリダイレクト
                response.sendRedirect("sample.html");
            } else {
                // ログイン失敗時、エラーメッセージを表示
                response.setContentType("text/html");
                response.getWriter().println("<html><body>");
                response.getWriter().println("<h1>Invalid email or password.</h1>");
                response.getWriter().println("</body></html>");
            }
        } catch (Exception e) {
            // エラーハンドリング
            response.setContentType("text/html");
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h1>Error: " + e.getMessage() + "</h1>");
            response.getWriter().println("</body></html>");
        }
    }
}


// javac -cp /opt/tomcat/lib/servlet-api.jar:. LoginServlet.java