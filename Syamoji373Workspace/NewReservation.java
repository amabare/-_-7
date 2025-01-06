/*
作成者：シモジ
更新日時：2025年1月5日22時47分
動作・役割：予約情報登録機能
コメント： 改善の余地あり
*/

public class NewReservation {
    public int NewReservation(){
        // 予約者名
        String reserver;
        // 予約日時
        String date_and_time;
        // 予約人数
        String number_of_people;
        // 入力を受け取る？？？
        reserver = getParameter("");
        date_and_time = getParameter("");
        number_of_people = getParameter("");

        //予約情報のテーブル
        String table_name  = "a";
        // where 以下の条件文
        String instruction = "";
        InsertDB(table_name, instruction);
        return 0;
    }
}
