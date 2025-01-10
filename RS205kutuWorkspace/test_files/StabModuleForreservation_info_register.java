import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/StabModuleForreservation_info_register")
public class StabModuleForreservation_info_register extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
 
        PrintWriter out = response.getWriter();
        // HTMLからformタグにより送信された値を取得
        String name = request.getParameter("reserve_name");
        String year = request.getParameter("reserve_year");
        String month = request.getParameter("reserve_month");
        String day = request.getParameter("reserve_day");
        String hour = request.getParameter("reserve_hour");
        String minute = request.getParameter("reserve_minute");
        String time = year + month + day + hour + minute;
        String numOfPeople = request.getParameter("reserve_number_people");
        // 文字列をpタグの形で表す
        name = this.StringTopTag(name);
        time = this.StringTopTag(time);
        numOfPeople = this.StringTopTag(numOfPeople);
        // 画面に出力
        out.println("<html lang = \"ja\"><head> <meta charset = \"utf8\"> </head><body>");
        out.println(name);
        out.println(time);
        out.println(numOfPeople);
        out.println("</body></html>");
    }

    // 入力文字列をpタグ化して返却するメソッド
    private String StringTopTag(String str){ return "<p>" + str + "</p>"; }
}