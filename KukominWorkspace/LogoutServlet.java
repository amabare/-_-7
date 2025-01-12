/*
ソースコード作成者: 浅島
修正日時: 2025/01/12
ソースコードの動作や役割: ログアウト機能　→　セッション情報を削除しトップページへ画面遷移(今は適当なhtmlに)
コメント:未テスト,遷移先未設定
*/

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; // HttpSessionをインポート

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションを取得し、無効化する
        // セッションが存在しない場合はnullを返す
        HttpSession session = request.getSession(false);
        if (session != null) {
            // セッションを無効化
            session.invalidate();
        }

        // ログアウト後、リダイレクトする
        // ログインページにリダイレクト
        response.sendRedirect("/ui/login.html");  
    }
}