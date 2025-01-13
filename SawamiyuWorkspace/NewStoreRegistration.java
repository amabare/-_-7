/*
作成者：澤村
更新日時：2025年1月8日
動作・役割：新規事業者登録機能
コメント： 改善の余地あり
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

@WebServlet("/store_registration")
public class NewStoreRegistration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // パラメータの取得
        String storeName = request.getParameter("store_name");  // HTML側のname属性に合わせる
        String storeAddress = request.getParameter("store_address");  // 同上
        String storeMail = request.getParameter("store_mail");  // 同上
        String storePhone = request.getParameter("store_phone");  // 同上
        String storePwd = request.getParameter("store_pwd");  // 同上
        String paymentMethod = request.getParameter("payment_method");  // 同上

        // クレジットカード情報を取得（支払い方法がクレジットカードの場合）
        String cardNumber = request.getParameter("card_number");  // 同上
        String cardName = request.getParameter("card_name");  // 同上
        String cardExpiry = request.getParameter("card_expiry");  // 同上
        String cardCode = request.getParameter("card_code");  // 同上

        // 文字エンコードの設定（レスポンス）
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 必要なパラメータが不足している場合のエラーチェック
        StringBuilder missingParams = new StringBuilder();

        if (storeName == null || storeName.isEmpty()) missingParams.append("店舗の名前, ");
        if (storeAddress == null || storeAddress.isEmpty()) missingParams.append("住所, ");
        if (storeMail == null || storeMail.isEmpty()) missingParams.append("メールアドレス, ");
        if (storePhone == null || storePhone.isEmpty()) missingParams.append("電話番号, ");
        if (storePwd == null || storePwd.isEmpty()) missingParams.append("パスワード, ");
        if (paymentMethod == null || paymentMethod.isEmpty()) missingParams.append("支払方法, ");

        if (paymentMethod != null && paymentMethod.equals("credit-card")) {
            if (cardNumber == null || cardNumber.isEmpty()) missingParams.append("カード番号, ");
            if (cardName == null || cardName.isEmpty()) missingParams.append("カード名義, ");
            if (cardExpiry == null || cardExpiry.isEmpty()) missingParams.append("カード期限, ");
            if (cardCode == null || cardCode.isEmpty()) missingParams.append("カードセキュリティコード, ");
        }

        if (missingParams.length() > 0) {
            // 不足しているパラメータを出力
            String missing = missingParams.substring(0, missingParams.length() - 2); // 最後のカンマを削除
            response.getWriter().println("以下のパラメータが不足しています: " + missing);
            return;
        }

        // リクエストスコープに値を設定（確認用）
        request.setAttribute("store_name", storeName);
        request.setAttribute("store_address", storeAddress);
        request.setAttribute("store_mail", storeMail);
        request.setAttribute("store_phone", storePhone);
        request.setAttribute("store_pwd", storePwd);
        request.setAttribute("payment_method", paymentMethod);
        request.setAttribute("card_number", cardNumber);
        request.setAttribute("card_name", cardName);
        request.setAttribute("card_expiry", cardExpiry);
        request.setAttribute("card_code", cardCode);

        // JSPに転送
        request.getRequestDispatcher("/store_registration_confirmation.jsp").forward(request, response);
    }
}
