import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/insert_store_registration")
public class InsertStoreRegistration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // フォームデータを取得
        String storeName = request.getParameter("store_name");
        String storeAddress = request.getParameter("store_address");
        String storeMail = request.getParameter("store_mail");
        String storePhone = request.getParameter("store_phone");
        String paymentMethod = request.getParameter("payment_method");

        // 必要なパラメータが不足している場合のエラーチェック
        List<String> missingParams = new ArrayList<>();
        if (storeName == null || storeName.isEmpty()) {
            missingParams.add("store_name");
        }
        if (storeAddress == null || storeAddress.isEmpty()) {
            missingParams.add("store_address");
        }
        if (storeMail == null || storeMail.isEmpty()) {
            missingParams.add("store_mail");
        }
        if (storePhone == null || storePhone.isEmpty()) {
            missingParams.add("store_phone");
        }
        if (paymentMethod == null || paymentMethod.isEmpty()) {
            missingParams.add("payment_method");
        }

        if (!missingParams.isEmpty()) {
            // 不足しているパラメータを出力
            out.println("<p>以下のパラメータが不足しています:</p>");
            out.println("<ul>");
            for (String param : missingParams) {
                out.println("<li>" + param + "</li>");
            }
            out.println("</ul>");
            return;
        }

        // データベース接続と登録処理
        try {
            // 重複チェックのSQLクエリ
            String checkSql = "name = ? AND address = ?";
            Object[] checkParams = { storeName, storeAddress };
            List<Map<String, Object>> existingStores = DatabaseUtility.select("STORE", checkSql, checkParams);

            if (existingStores != null && !existingStores.isEmpty()) {
                // すでに店舗が登録されている場合
                out.println("<p>この店舗はすでに登録されています。</p>");
            } else {
                // 重複がない場合、店舗情報をINSERT
                Map<String, Object> storeData = new HashMap<>();
                storeData.put("name", storeName); // カラム名を修正
                storeData.put("address", storeAddress);
                storeData.put("store_mail", storeMail);
                storeData.put("store_phone", storePhone);
                storeData.put("payment_method", paymentMethod);

                int rowsInserted = DatabaseUtility.insert("STORE", storeData);
                if (rowsInserted > 0) {
                    // 店舗が正常に登録された場合
                    out.println("<p>店舗が正常に登録されました！</p>");
                } else {
                    out.println("<p>店舗の登録に失敗しました。</p>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>エラーが発生しました: " + e.getMessage() + "</p>");
        }
    }
}
