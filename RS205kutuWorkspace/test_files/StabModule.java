import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/StabModule")
public class StabModule extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
 
        PrintWriter out = response.getWriter();
        out.println("<html lang = \"ja\" ><head> <meta charset = \"utf8\"> </head><body>");
        out.println("<h1> モジュールが呼び出されました！ </h1>");
        out.println("</body></html>");
    }
}