/*
作成者：シモジ
更新日時：2025年1月5日22時47分
動作・役割：予約情報登録機能
コメント： DB関連はまだ、入力受け取りまだ・・・
*/
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewReservation {
    public int NewReservation(){
        // 予約者名
        String reserver;
        // 予約日時
        String date_and_time;
        // 予約人数
        String number_of_people;
        // 入力を受け取る
        for (;;) {
            reserver = request.getParameter("");
            date_and_time = request.getParameter("");
            number_of_people = request.getParameter("");
        //入力チェック
            if(reserver != null && date_and_time != null && number_of_people != null){
                break;
            }
        }
        //予約情報のテーブル
        String table_name  = "a";
        // sqlの条件文(INSERT INTO テーブル名 VALUES ('1', 'タカシ', '初ツイート！', '2017/07/05' ,'2017/07/05'))
        String instruction = reserver + "," + date_and_time + "," + number_of_people ;
        InsertDB(table_name, instruction);
        return 0;
    }
}
