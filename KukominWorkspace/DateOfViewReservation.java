/*
ソースコード作成者: 浅島
修正日時: 2025/01/12
ソースコードの動作や役割:日時別予約情報をDBからデータを取得し出力する
コメント: データを取得はできるが何をどこからとってくるか未設定　

INSERT INTO seats (seat_name) VALUES ('A席'), ('B席'), ('C席');

INSERT INTO reservations (seat_id, reservation_date, start_time, end_time)
VALUES (1, '2025-01-13', '12:00:00', '13:00:00'),
       (2, '2025-01-13', '13:00:00', '14:00:00');
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
        String date = request.getParameter("date");

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            if (date == null || date.isEmpty()) {
                throw new IllegalArgumentException("日付が指定されていません");
            }

            List<Map<String, Object>> seats = fetchSeats();
            List<LocalTime> timeSlots = new ArrayList<>();
            LocalTime startTime = LocalTime.of(9, 0);
            LocalTime endTime = LocalTime.of(22, 0);

            while (!startTime.equals(endTime)) {
                timeSlots.add(startTime);
                startTime = startTime.plusHours(1);
            }

            // HTMLヘッダー部分を出力
            out.println("<!DOCTYPE html>");
            out.println("<html lang='ja'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>タイムシフト管理</title>");
            out.println("<link rel='stylesheet' href='styles.css'>"); // CSSリンク
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>タイムシフト管理</h1>");

            // タイムスケジュールのテーブルを出力
            out.println("<table border='1'>");

            // ヘッダー行 (時間)
            out.println("<tr>");
            out.println("<th>席</th>");
            for (LocalTime time : timeSlots) {
                out.println("<th>" + time + "～" + time.plusHours(1) + "</th>");
            }
            out.println("</tr>");

            // 席ごとの行を出力
            for (Map<String, Object> seat : seats) {
                out.println("<tr>");
                out.println("<td>" + seat.get("seat_name") + "</td>");
                for (LocalTime time : timeSlots) {
                    int seatId = (int) seat.get("id");
                    boolean isReserved = checkReservation(seatId, date, time);

                    if (isReserved) {
                        out.println("<td class='reserved'>予約済み</td>");
                    } else {
                        out.println("<td class='available'>空き</td>");
                    }
                }
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>エラーが発生しました: " + e.getMessage() + "</p>");
        }
    }

    private List<Map<String, Object>> fetchSeats() throws Exception {
        return DatabaseUtility.select("seats", null, null);
    }

    private boolean checkReservation(int seatId, String date, LocalTime startTime) throws Exception {
        String whereClause = "seat_id = ? AND reservation_date = ? AND start_time <= ? AND end_time > ?";
        Object[] params = {seatId, date, startTime.toString(), startTime.toString()};
        List<Map<String, Object>> reservations = DatabaseUtility.select("reservations", whereClause, params);
        return !reservations.isEmpty();
    }
}
