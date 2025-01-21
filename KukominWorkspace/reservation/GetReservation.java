import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/get_reservations")
public class GetReservations extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // サンプルデータの準備
        List<Map<String, Object>> reservationList = new ArrayList<>();
        Map<String, Object> reservation1 = new HashMap<>();
        reservation1.put("reservation_id", 1);
        reservation1.put("customer_name", "上子");
        reservation1.put("num_guests", 2);
        reservation1.put("reservation_time", "2025-01-30T15:00:00");
        reservation1.put("seat_id", 101);
        reservation1.put("seat_name", "A1");
        reservationList.add(reservation1);

        Map<String, Object> reservation2 = new HashMap<>();
        reservation2.put("reservation_id", 2);
        reservation2.put("customer_name", "坂本");
        reservation2.put("num_guests", 3);
        reservation2.put("reservation_time", "2025-02-03T12:30:00");
        reservation2.put("seat_id", 102);
        reservation2.put("seat_name", "B1");
        reservationList.add(reservation2);

        // 手動でJSONを生成
        StringBuilder json = new StringBuilder();
        json.append("[");

        for (int i = 0; i < reservationList.size(); i++) {
            Map<String, Object> reservation = reservationList.get(i);
            json.append("{");
            json.append("\"reservation_id\":").append(reservation.get("reservation_id")).append(",");
            json.append("\"customer_name\":\"").append(reservation.get("customer_name")).append("\",");
            json.append("\"num_guests\":").append(reservation.get("num_guests")).append(",");
            json.append("\"reservation_time\":\"").append(reservation.get("reservation_time")).append("\",");
            json.append("\"seat_id\":").append(reservation.get("seat_id")).append(",");
            json.append("\"seat_name\":\"").append(reservation.get("seat_name")).append("\"");
            json.append("}");

            // 最後の要素にはカンマを付けない
            if (i < reservationList.size() - 1) {
                json.append(",");
            }
        }

        json.append("]");

        // JSONを出力
        out.println(json.toString());
    }
}
