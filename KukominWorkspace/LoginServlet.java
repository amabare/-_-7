/*
ソースコード作成者: 浅島
修正日時: 2025/01/05
ソースコードの動作や役割: 入力されたログイン情報が正しいかどうかDBで確認しtrueならログインfalseならエラー
コメント: DB関係がまだ未実装　→　変数に固定の値を入れてる
*/


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/login")   //呼び出されるURLの条件
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームから送信されたデータを取得
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // ログイン認証（簡易的な例、実際にはデータベース等で認証を行う）
        if ("user@example.com".equals(email) && "password123".equals(password)) {
            // ログイン成功時、sample.htmlにリダイレクト
            response.sendRedirect("sample.html");  // リダイレクト
        } else {
            // ログイン失敗時、エラーメッセージを表示
            response.setContentType("text/html");
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h1>Invalid email or password.</h1>");
            response.getWriter().println("</body></html>");
        }
    }
}


//   <!-- LogoutServlet declaration -->
//   <servlet>
//     <servlet-name>LogoutServlet</servlet-name>
//     <servlet-class>com.example.LogoutServlet</servlet-class>
//   </servlet>
//   <servlet-mapping>
//     <servlet-name>LogoutServlet</servlet-name>
//     <url-pattern>/LogoutServlet</url-pattern>
//   </servlet-mapping>

//   <!-- LoginServlet declaration -->
//   <servlet>
//     <servlet-name>LoginServlet</servlet-name>
//     <servlet-class>com.example.LoginServlet</servlet-class>
//   </servlet>
//   <servlet-mapping>
//     <servlet-name>LoginServlet</servlet-name>
//     <url-pattern>/LoginServlet</url-pattern>

//   </servlet-mapping>

