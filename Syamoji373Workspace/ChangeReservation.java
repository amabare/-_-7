/*
作成者：シモジ
更新日時：2025年1月5日23時47分
動作・役割：予約情報変更機能
コメント： 改善の余地あり
*/

//import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public  class ChangeReservation {
    //public static int ChangeReservation(HttpServletRequest request){
        public static int ChangeReservation(){
        // 予約者ID
        String reservation_id = "default_id"; // Initialize with a default value
        // 予約者名
        String reserver;
        // 予約日時
        String date_and_time;
        // 予約人数
        String number_of_people;
        // 予約情報をMapに格納
        Map<String, Object> values = new HashMap<>();
        // 入力を受け取る
        for (;;) {
            //テストのために標準入力に
            // reservation_id = request.getParameter("");
            // reserver = request.getParameter("");
            // date_and_time = request.getParameter("");
            // number_of_people = request.getParameter("");
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter reservation_id (max 8 characters): ");
            reservation_id = scanner.nextLine();
            System.out.print("Enter reserver name (max 32 characters): ");
            reserver = scanner.nextLine();
            System.out.print("Enter reservation date and time (yyyymmdd): ");
            date_and_time = scanner.nextLine();
            System.out.print("Enter number of people (1 to 999): ");
            number_of_people = scanner.nextLine();

            //入力チェック
            if (reservation_id == null || reservation_id.isEmpty() || reservation_id.length() > 8) {
                System.out.println("Error: Reservation ID must be between 1 and 8 characters and cannot be null.");
            } else if (reserver == null || reserver.isEmpty() || reserver.length() > 32) {
                System.out.println("Error: Reserver name must be between 1 and 32 characters and cannot be null.");
            } else if (date_and_time == null || date_and_time.isEmpty() || !date_and_time.matches("\\d{8}")) {
                System.out.println("Error: Reservation date must be in the format yyyymmdd (8 digits) and cannot be null.");
            } else if (number_of_people == null || number_of_people.isEmpty() || 
                       !number_of_people.matches("\\d{1,3}") || Integer.parseInt(number_of_people) > 999) {
                System.out.println("Error: Number of people must be a number between 1 and 999 and cannot be null.");
            } else {
                // 条件を満たした場合にループを抜ける
                break;
            }
        }

        // Map<String, Object> values = new HashMap<>();
        values.put("reserver", reservation_id);
        values.put("reserver", reserver);
        values.put("date_and_time", date_and_time);
        values.put("number_of_people", number_of_people);
        //予約情報のテーブル
        String table_name  = "RESERVATION";
        String whereClause = "reserve_id = " + reservation_id ;
        System.out.println("Where: " + whereClause);

        //UPDATEDBモジュールを呼び出す前に、更新前の情報がDBに存在するか確認する必要があるかもしれない

        //String instruction = reserver + "," + date_and_time + "," + number_of_people ;
        // DataBaseUtilityクラスのdeleteDBメソッドを呼び出す
        //int rowsInserted = DatabaseUtility.insertDB(tableName, values);  // insertDBメソッドの呼び出し
        //return rowsInserted;  // 挿入した行数を返す
        //test
        StubExample.UpdateDB(table_name, values, whereClause);

        return 0;
    }
}