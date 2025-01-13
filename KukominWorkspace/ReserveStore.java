/*
ソースコード作成者: 浅島
修正日時: 2025/01/13
ソースコードの動作や役割:予約機能 → Reserve.htmlから情報を取得し確認画面を生成
コメント: SelectDBを使っていないため要調整
　　　　　Reserve.htmlに行く際に店舗IDをURLで送ってほしい
*/

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reserve")
public class ReserveStore extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/my_database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root00";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String restaurantIdStr = request.getParameter("id");
        String customerName = request.getParameter("customer_name");
        String reservationDate = request.getParameter("reservation_date");
        String reservationTime = request.getParameter("reservation_time");
        String partySizeStr = request.getParameter("party_size");

        // 文字エンコードの設定
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 必要なパラメータが不足している場合のエラーチェック
        if (restaurantIdStr == null || customerName == null || reservationDate == null ||
            reservationTime == null || partySizeStr == null) {
            throw new IllegalArgumentException("必要なパラメータが不足しています");
        }

        int restaurantId = Integer.parseInt(restaurantIdStr);
        int partySize = Integer.parseInt(partySizeStr);

        // リクエストスコープに値を設定
        request.setAttribute("restaurantId", restaurantId);
        request.setAttribute("customer_name", customerName);
        request.setAttribute("reservation_date", reservationDate);
        request.setAttribute("reservation_time", reservationTime);
        request.setAttribute("party_size", partySize);

        // 予約登録処理（省略）

        // JSPに転送
        request.getRequestDispatcher("/reserveConfirmation.jsp").forward(request, response);
    }
}
