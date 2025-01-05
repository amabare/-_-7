/*
ソースコード作成者: 浅島
修正日時: 2025/01/05
ソースコードの動作や役割: ログアウト機能　→　セッション情報を削除しトップページへ画面遷移(今は適当なhtmlに)
コメント: DB関係がまだ未実装　→　変数に固定の値を入れてる
        　サーブレットの呼び出しができていない
*/

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 現在のセッションを取得
        HttpSession session = request.getSession(false);

        // セッションが存在する場合、無効化する
        if (session != null) {
            session.invalidate();
        }

        // ログアウト後のリダイレクト先を設定
        //response.sendRedirect("login.jsp"); // ログインページやホームページなど
        response.sendRedirect("pass_submit.html"); //ログアウト後の画面遷移先(test)
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POSTリクエストの場合もGETメソッドを呼び出す
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<h1>Servletが正常に呼び出されました</h1>");
    }

}