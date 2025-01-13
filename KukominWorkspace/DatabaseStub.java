// スタブ: データベース接続をシミュレート
public class DatabaseStub {
    public String getReservationStatus(String date, String time, int seatId) {
        // 固定の値で予約状況をシミュレート
        if ("2025-01-10".equals(date) && "9:00".equals(time) && seatId == 1) {
            return "予約済み";
        }
        return "空き";
    }
}
