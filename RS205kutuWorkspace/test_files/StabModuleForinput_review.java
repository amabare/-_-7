import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/StabModuleForinput_review")
public class StabModuleForinput_review extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
 
        PrintWriter out = response.getWriter();
        // HTMLからformタグにより送信された値を取得
        String review = request.getParameter("review");
        // 文字列をpタグの形で表す
        review = "<p>" + review + "</p>";
        // 画面に出力
        out.println("<html lang = \"ja\"><head> <meta charset = \"utf8\"> </head><body>");
        out.println(review);
        out.println("</body></html>");
    }
}