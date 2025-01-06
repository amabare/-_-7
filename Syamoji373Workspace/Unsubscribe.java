/*
作成者：シモジ
更新日時：2025年1月5日22時47分
動作・役割：会員情報削除機能
コメント： 
*/

public class Unsubscribe {
    // メソッド
    public int Unsubscribe(){
        String table_name = "USER";
        //String table=name = "STORE"
        // where 以下の条件文
        String instruction = "";
        DeleteDB(table_name, instruction);
        return 0;
    }
    // cookieで画面の状態を取得
}
