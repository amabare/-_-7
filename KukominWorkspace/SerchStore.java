/*
ソースコード作成者: 浅島
修正日時: 2025/01/05
ソースコードの動作や役割:店舗検索機能
コメント: SelectDBを使っていないため要調整
        　サーブレットの呼び出しができていない
*/

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// "/searchStore"エンドポイントに対応するサーブレット
@WebServlet("/searchStore")
public class SearchStoreServlet extends HttpServlet {

    // データベース接続情報
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant_db";
    private static final String DB_USER = "root"; // データベースのユーザー名
    private static final String DB_PASSWORD = "password"; // データベースのパスワード

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // クライアントから送信された検索条件を取得
        String search = request.getParameter("search");   // 検索キーワード
        String tag = request.getParameter("tag");         // タグ
        String hours = request.getParameter("hours");     // 営業時間

        // レスポンスの設定 (HTML形式、UTF-8)
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter(); // レスポンス用の出力ストリームを取得

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // SQLクエリの構築 (動的に検索条件を反映)
            String sql = "SELECT name, location, tag, hours FROM restaurants WHERE 1=1";

            // 動的条件の追加
            if (search != null && !search.isEmpty()) {
                sql += " AND (name LIKE ? OR location LIKE ?)";
            }
            if (tag != null && !tag.isEmpty()) {
                sql += " AND tag = ?";
            }
            if (hours != null && !hours.isEmpty()) {
                sql += " AND hours LIKE ?";
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                int paramIndex = 1; // プレースホルダーのインデックス

                // 条件がある場合、プレースホルダーに値を設定
                if (search != null && !search.isEmpty()) {
                    stmt.setString(paramIndex++, "%" + search + "%");
                    stmt.setString(paramIndex++, "%" + search + "%");
                }
                if (tag != null && !tag.isEmpty()) {
                    stmt.setString(paramIndex++, tag);
                }
                if (hours != null && !hours.isEmpty()) {
                    stmt.setString(paramIndex++, hours + "%");
                }

                // クエリを実行し、結果を取得
                ResultSet rs = stmt.executeQuery();

                // 結果をHTML形式で出力
                out.println("<div class='results'>");
                out.println("<ul>");
                while (rs.next()) {
                    String name = rs.getString("name");
                    String location = rs.getString("location");
                    String tagResult = rs.getString("tag");
                    String hoursResult = rs.getString("hours");
                    out.println("<li>" + name + " (" + location + ", " + tagResult + ", " + hoursResult + ")</li>");
                }
                out.println("</ul>");
                out.println("</div>");
            }
        } catch (Exception e) {
            // エラーが発生した場合の処理
            e.printStackTrace(out); // エラーメッセージを出力 (デバッグ用)
            out.println("<p>エラーが発生しました。もう一度お試しください。</p>");
        }
    }
}
