/*
作成者：シモジ
更新日時：2025年1月6日0時43分
動作・役割：予約情報削除機能
コメント： DB関連改善の余地あり、testのためにコメントアウトして書き直したところあり
*/

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

public class DeleteReservation {
   // public static int DeleteReservation(HttpServletRequest request) {
    public static int DeleteReservation(String reservation_id) {

        try {
            // セッションから予約ID取得
            //HttpSession session = request.getSession();
            //String reservation_id = (String) session.getAttribute("reservation_id");
            
            if (reservation_id == null || reservation_id.isEmpty()) {
                System.out.println("Reservation ID is not set in the session.");
                return 0; // 削除なし
            }

            // データベース削除処理
            String table_name = "RESERVATION";
            String whereClause = "reserve_id = " + reservation_id ;

            // DataBaseUtilityクラスのdeleteDBメソッドを呼び出す
           // int rowsDeleted = DatabaseUtility.deleteDB(table_name, whereClause);
           //test
           StubExample.DeleteDB(table_name, whereClause);

            //return rowsDeleted; // 削除した行数を返す
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // エラー
        }
    }
}
