/*
ソースコード作成者: 浅島
修正日時: 2025/01/05
ソースコードの動作や役割:日時別予約情報の表示
コメント: SelectDBを使っていないため要調整
        　サーブレットの呼び出しができていない
*/

@WebServlet("/viewSchedule")
public class ViewScheduleServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String date = request.getParameter("date"); // 日付 (YYYY-MM-DD形式)

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // 全席情報を取得
            String seatQuery = "SELECT id, seat_name FROM seats";
            try (PreparedStatement seatStmt = conn.prepareStatement(seatQuery)) {
                ResultSet seatRs = seatStmt.executeQuery();

                // タイムシフト表のHTML開始
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>時間</th>");

                // 席ごとに列を作成
                while (seatRs.next()) {
                    out.print("<th>" + seatRs.getString("seat_name") + "</th>");
                }
                out.println("</tr>");

                // 時間帯のループ (例: 9:00～22:00)
                LocalTime startTime = LocalTime.of(9, 0);
                LocalTime endTime = LocalTime.of(22, 0);
                while (!startTime.equals(endTime)) {
                    LocalTime nextTime = startTime.plusHours(1); // 1時間ごと
                    out.println("<tr>");
                    out.println("<td>" + startTime + "～" + nextTime + "</td>");

                    seatRs.beforeFirst(); // 結果セットをリセット
                    while (seatRs.next()) {
                        int seatId = seatRs.getInt("id");

                        // 予約状況を取得
                        String reservationQuery = 
                            "SELECT * FROM reservations " +
                            "WHERE seat_id = ? AND reservation_date = ? " +
                            "AND start_time <= ? AND end_time > ?";
                        try (PreparedStatement reservationStmt = conn.prepareStatement(reservationQuery)) {
                            reservationStmt.setInt(1, seatId);
                            reservationStmt.setString(2, date);
                            reservationStmt.setString(3, startTime.toString());
                            reservationStmt.setString(4, startTime.toString());
                            ResultSet reservationRs = reservationStmt.executeQuery();

                            if (reservationRs.next()) {
                                out.println("<td class='reserved'>予約済み</td>");
                            } else {
                                out.println("<td class='available'>空き</td>");
                            }
                        }
                    }
                    out.println("</tr>");
                    startTime = nextTime; // 次の時間帯へ
                }
                out.println("</table>");
            }
        } catch (Exception e) {
            e.printStackTrace(out); // エラーを表示
            out.println("<p>エラーが発生しました。</p>");
        }
    }
}
