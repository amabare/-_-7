/* 
ソースコード作成者: 大矢
修正日時: 2025/1/1
ソースコードの動作や役割: 店舗の登録情報を変更する
コメント: 
*/

package YutoOyaWorkspace;

public class PasswordChange {
    //メソッド
    public static int Passwordchange(String pass1, String pass2){
        //入力された1つ目のパスワードをpassword1に格納
        String password1 = pass1;

        //入力された2つ目のパスワード(確認用部分に入力されたもの)をpassword2に格納
        String password2 = pass2;

        //password1とpassword2が一致しているかの確認
        boolean check = Passwordcheck(password1, password2);

        //一致していない場合はモジュールの外に出す.
        if (check == false){
            return 1;
        }

        //パスワードをハッシュ値に変換

        //ハッシュ値に変換したパスワードをDBに格納

        return 0;
    }

    public static boolean Passwordcheck(String password1, String password2){
        if (password1.equals(password2) == true){
            return true;
        }
        return false;
    }
}
