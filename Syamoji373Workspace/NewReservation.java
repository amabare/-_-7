/*
作成者：シモジ
更新日時：2025年1月5日22時47分
動作・役割：予約情報登録機能
コメント： DB関連はまだ、入力受け取りまだ・・・
*/
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public  class NewReservation {
    //public static int NewReservation(HttpServletRequest request){
        public static int NewReservation(){
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
            // reserver = request.getParameter("");
            // date_and_time = request.getParameter("");
            // number_of_people = request.getParameter("");
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter reserver name (max 32 characters): ");
            reserver = scanner.nextLine();
            System.out.print("Enter reservation date and time (yyyymmdd): ");
            date_and_time = scanner.nextLine();
            System.out.print("Enter number of people (1 to 999): ");
            number_of_people = scanner.nextLine();

           // 入力がnullでなく、指定された長さや形式に従っているかを確認
           if (reserver == null || reserver.isEmpty() || reserver.length() > 32) {
            System.out.println("Error: Reserver name must be between 1 and 32 characters.");
        } else if (date_and_time == null || !date_and_time.matches("\\d{8}")) {
            System.out.println("Error: Reservation date must be in the format yyyymmdd (8 digits).");
        } else if (number_of_people == null || !number_of_people.matches("\\d{1,3}")) {
            System.out.println("Error: Number of people must be a number between 1 and 999.");
        } else if (Integer.parseInt(number_of_people) > 999) {
            System.out.println("Error: Number of people must not exceed 999.");
        } else {
            // 条件を満たした場合にループを抜ける
            break;
        }
        // 予約情報をMapに格納
        }

        // Map<String, Object> values = new HashMap<>();
        values.put("reserver", reserver);
        values.put("date_and_time", date_and_time);
        values.put("number_of_people", number_of_people);
        //予約情報のテーブル
        String table_name  = "RESERVATION";
        //String instruction = reserver + "," + date_and_time + "," + number_of_people ;
        // DataBaseUtilityクラスのdeleteDBメソッドを呼び出す
        //int rowsInserted = DatabaseUtility.insertDB(tableName, values);  // insertDBメソッドの呼び出し
        //return rowsInserted;  // 挿入した行数を返す
        //test
        StubExample.InsertDB(table_name, values);

        return 0;
    }
}
