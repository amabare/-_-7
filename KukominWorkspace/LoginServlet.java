/*
ソースコード作成者: 浅島
修正日時: 2025/01/12
ソースコードの動作や役割: ログイン機能　→　login.htmlから入力されたメールアドレスとパスワードの組み合わせがDBにあるか確認
コメント: DBには以下が入っているので以下ならログイン成功する

INSERT INTO 利用者登録情報 (氏名, 性別, メールアドレス, 電話番号, パスワード)
VALUES 
('山田 太郎', 'M', 'taro.yamada@example.com', '08012345678', 'password1234'),
('佐藤 花子', 'F', 'hanako.sato@example.com', '09087654321', 'password5678'),
('鈴木 一郎', 'M', 'ichiro.suzuki@example.com', '07023456789', 'password91011'),
('田中 美奈子', 'F', 'minako.tanaka@example.com', '08098765432', 'password1213');
 　
*/

import java.io.IOException;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 呼び出されるURLの条件
@WebServlet("/login") 
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームから送信されたデータを取得
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // SELECTモジュールを使用して認証
        String tableName = "利用者登録情報";
        String whereCondition = "メールアドレス = ? AND パスワード = ?";
        Object[] parameters = {email, password};

        try {
            // DatabaseUtility.select()メソッドを使ってデータベースから結果を取得
            List<Map<String, Object>> resultList = DatabaseUtility.select(tableName, whereCondition, parameters);

            if (!resultList.isEmpty()) {
                // ログイン成功時、sample.htmlにリダイレクト
               response.sendRedirect("/ui/sample.html");
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


// // javac -cp /opt/tomcat/lib/servlet-api.jar:. LoginServlet.java