import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/seat_manage")
public class SeatManage extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        int storeId = (int) session.getAttribute("store_id");
        
        try (Connection connection = DatabaseUtility.getConnection();
             PrintWriter out = response.getWriter()) {

            List<Map<String, Object>> seats = getSeatData(connection, storeId);

            out.println("<html lang='ja'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>座席管理</title>");
            out.println("<style>");
            out.println("table { width: 100%; border-collapse: collapse; }");
            out.println("th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }");
            out.println("th { background-color: #f4f4f4; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>座席管理</h1>");
            
            out.println("<form action='/seat_manage' method='post'>");
            out.println("<table>");
            out.println("<tr><th>席名</th><th>予約</th><th>使用中</th><th>時間</th><th>人数</th></tr>");
            
            for (Map<String, Object> seat : seats) {
                int seatId = (int) seat.get("seat_id");
                String seatName = (String) seat.get("seat_name");
                boolean isReserved = (boolean) seat.get("is_reserved");
                boolean isInUse = (boolean) seat.get("is_in_use");
                Timestamp startTime = (Timestamp) seat.get("start_time");
                int numPeople = (int) seat.get("num_people");
                
                out.println("<tr>");
                out.println("<td>" + seatName + "</td>");
                out.println("<td><input type='checkbox' name='reserved_" + seatId + "' " + (isReserved ? "checked" : "") + " disabled></td>");
                out.println("<td><input type='checkbox' name='in_use_" + seatId + "' " + (isInUse ? "checked" : "") + "></td>");
                out.println("<td>" + (startTime != null ? startTime.toString() : "未設定") + "</td>");
                out.println("<td><input type='number' name='num_people_" + seatId + "' min='1' value='" + numPeople + "'></td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
            out.println("<button type='submit'>更新</button>");
            out.println("</form>");
            out.println("</body></html>");
        }catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("データベースエラーが発生しました: " + e.getMessage());
        }
        
    }

    private List<Map<String, Object>> getSeatData(Connection connection, int storeId) throws SQLException {
        String query = "SELECT seat_id, seat_name, status = '予約' AS is_reserved, status = '使用中' AS is_in_use, start_time, num_people FROM SEAT_STATUS_HISTORY WHERE store_id = ?";
        List<Map<String, Object>> seats = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, storeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Map<String, Object> seat = new HashMap<>();
                    seat.put("seat_id", resultSet.getInt("seat_id"));
                    seat.put("seat_name", resultSet.getString("seat_name"));
                    seat.put("is_reserved", resultSet.getBoolean("is_reserved"));
                    seat.put("is_in_use", resultSet.getBoolean("is_in_use"));
                    seat.put("start_time", resultSet.getTimestamp("start_time"));
                    seat.put("num_people", resultSet.getInt("num_people"));
                    seats.add(seat);
                }
            }
        }
        return seats;
    }
}