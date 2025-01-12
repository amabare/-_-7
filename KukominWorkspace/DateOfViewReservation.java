/*
ソースコード作成者: 浅島
修正日時: 2025/01/12
ソースコードの動作や役割:日時別予約情報をDBからデータを取得し出力する
コメント: データを取得はできるが何をどこからとってくるか未設定　
*/
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewSchedule")
public class DateOfViewReservation extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String date = request.getParameter("date"); // 日付 (YYYY-MM-DD形式)

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // 席情報を取得
            List<Map<String, Object>> seats = fetchSeats();

            // タイムシフト表のHTML開始
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>時間</th>");

            // 席ごとに列を作成
            for (Map<String, Object> seat : seats) {
                out.print("<th>" + seat.get("seat_name") + "</th>");
            }
            out.println("</tr>");

            // 時間帯のループ (例: 9:00～22:00)
            LocalTime startTime = LocalTime.of(9, 0);
            LocalTime endTime = LocalTime.of(22, 0);
            while (!startTime.equals(endTime)) {
                LocalTime nextTime = startTime.plusHours(1); // 1時間ごと
                out.println("<tr>");
                out.println("<td>" + startTime + "～" + nextTime + "</td>");

                // 各席に対して予約情報を取得
                for (Map<String, Object> seat : seats) {
                    int seatId = (int) seat.get("id");

                    // 予約状況を取得
                    boolean isReserved = checkReservation(seatId, date, startTime);

                    if (isReserved) {
                        out.println("<td class='reserved'>予約済み</td>");
                    } else {
                        out.println("<td class='available'>空き</td>");
                    }
                }
                out.println("</tr>");
                startTime = nextTime; // 次の時間帯へ
            }
            out.println("</table>");
        } catch (Exception e) {
            e.printStackTrace(out); // エラーを表示
            out.println("<p>エラーが発生しました。</p>");
        }
    }

    // private List<Map<String, Object>> fetchSeats() throws Exception {
    //     List<Map<String, Object>> seats = new ArrayList<>();
    //     ResultSet resultSet = DatabaseUtility.select("seats", "1=1", new Object[]{});

    //     while (resultSet.next()) {
    //         Map<String, Object> seat = new HashMap<>();
    //         seat.put("id", resultSet.getInt("id"));
    //         seat.put("seat_name", resultSet.getString("seat_name"));
    //         seats.add(seat);
    //     }
    //     return seats;
    // }

    private List<Map<String, Object>> fetchSeats() throws Exception {
        return DatabaseUtility.select("seats", "1=1", new Object[]{}); // 修正済み
    }


    // private boolean checkReservation(int seatId, String date, LocalTime startTime) throws Exception {
    //     ResultSet resultSet = DatabaseUtility.select(
    //             "reservations",
    //             "seat_id = ? AND reservation_date = ? AND start_time <= ? AND end_time > ?",
    //             new Object[]{seatId, date, startTime.toString(), startTime.toString()}
    //     );
    //     return resultSet.next();
    // }
    private boolean checkReservation(int seatId, String date, LocalTime startTime) throws Exception {
        List<Map<String, Object>> reservations = DatabaseUtility.select(
            "reservations",
            "seat_id = ? AND reservation_date = ? AND start_time <= ? AND end_time > ?",
            new Object[]{seatId, date, startTime.toString(), startTime.toString()}
        );
        return !reservations.isEmpty(); // 修正済み
    }

}
