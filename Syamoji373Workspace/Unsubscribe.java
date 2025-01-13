/*
作成者：シモジ
更新日時：2025年1月5日22時47分
動作・役割：会員情報削除機能
コメント： DBモジュールの内容次第で変更が必要
*/
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

public class Unsubscribe {
    // メソッド
    public static int Unsubscribe(String id){
        try {
            // セッションから会員ID取得
            //HttpSession session = request.getSession();
            //String id = (String) session.getAttribute("id");
            // セッションから属性名を取得(事業者か一般ユーザか)
            //String attributeNames = session.getAttributeNames();
            //String attributeNames = "user_id";
            String attributeNames = "store_id";

            if (id == null || id.isEmpty()) {
                System.out.println("ID is not set in the session.");
                return 0; // 削除なし
            }

            // データベース削除処理
            String table_name;
            String whereClause;

            if(attributeNames == "user_id"){
                table_name = "USER";
                whereClause = "user_id = " + id ;
            }else if(attributeNames == "store_id"){
                table_name = "STORE";
                whereClause = "store_id = " + id ;
            }else{
                System.out.println("attributeNames is not set in the session.");
                return 0; // 削除なし
            }

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