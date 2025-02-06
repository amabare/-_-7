import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
//import AmabareWorkspace.HashPassword; // HashPasswordクラスのインポート
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
        String storePwd = request.getParameter("store_pwd");

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
        if (storePwd == null || storePwd.length() < 8 || storePwd.length() > 20) {
            missingParams.add("store_pwd (パスワードは8文字以上20文字以下で入力してください)");
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

        // パスワードをハッシュ化
        String hashedPwd = HashPassword.HashString(storePwd); // HashPasswordクラスを使用

        // データベースに保存するデータをマップにまとめる
        Map<String, Object> storeData = new HashMap<>();
        storeData.put("name", storeName);
        storeData.put("address", storeAddress);
        storeData.put("email", storeMail);
        storeData.put("phone_number", storePhone);
        storeData.put("payment_method", paymentMethod);
        storeData.put("store_pwd", hashedPwd); // ハッシュ化したパスワードを追加
        storeData.put("created_at", new java.sql.Timestamp(System.currentTimeMillis()));
        storeData.put("updated_at", new java.sql.Timestamp(System.currentTimeMillis()));

        // DatabaseUtilityを使ってデータを挿入
        try {
            int rowsInserted = DatabaseUtility.insert("STORE", storeData);

            if (rowsInserted > 0) {
                out.println("<p>店舗情報が正常に登録されました！</p>");
            } else {
                out.println("<p>店舗情報の登録に失敗しました。</p>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>エラーが発生しました: " + e.getMessage() + "</p>");
        }
    }
}
