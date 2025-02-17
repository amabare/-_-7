import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import jakarta.servlet.RequestDispatcher;
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

        // フォームからデータを取得
        String storeName = request.getParameter("name");
        String storeAddress = request.getParameter("address");
        String storeMail = request.getParameter("email");
        String storePhone = request.getParameter("phone_number");
        String paymentMethod = request.getParameter("payment-method");
        String storePwd = request.getParameter("store_pwd");

        // クレジットカード情報（支払方法がクレジットの場合）
        String cardNumber = request.getParameter("card-number");
        String cardName = request.getParameter("card-name");
        String cardExpiry = request.getParameter("card-expiry");
        String cardCode = request.getParameter("card-code");

        // リクエスト属性に設定
        request.setAttribute("store_name", storeName);
        request.setAttribute("store_address", storeAddress);
        request.setAttribute("store_mail", storeMail);
        request.setAttribute("store_phone", storePhone);
        request.setAttribute("payment_method", paymentMethod);
        request.setAttribute("store_pwd", storePwd);
        // 支払い方法がクレジットカードの場合、追加情報もリクエストに設定
        if ("クレジット".equals(paymentMethod)) {
            request.setAttribute("card_number", cardNumber);
            request.setAttribute("card_name", cardName);
            request.setAttribute("card_expiry", cardExpiry);
            request.setAttribute("card_code", cardCode);
        }

        // JSPページにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("ui/store_registration_confirmation.jsp");

        dispatcher.forward(request, response);
    }
}

