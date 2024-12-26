/*
 * ソースコード作成者: 山本
 * 修正日時: 2024/12/26　11:41
 * ソースコードの動作や役割: パスワードを検査する
 * コメント: 今はまだメールサーバが構築されておらず MailSend.java が機能しない。
 * 　　　　  テストを行うときはドライバの設定を適切にして行うように。
 */

package AmabareWorkspace;

public class PasswordAuthentication {

    // パスワードを検査するメソッド
    // boolean 型
    public static boolean checkPassword(String inputPassword, String currentPasswordHash) {
        // 入力されたパスワードと現行のパスワードが一致しているかを検査
        return currentPasswordHash.equals(inputPassword);
    }

    public static int main(String[] args) {
        // 引数が足りない場合
        if (args.length < 2) {
            System.out.println("入力されていない部分があります。再入力してください。");
            return 0;
        }

        // 現在のパスワードのハッシュ値
        String currentPasswordHash = args[0];
        // 入力されたパスワード
        String inputPassword = args[1];

        if (checkPassword(inputPassword, currentPasswordHash)) {
            // パスワードが一致した場合
            System.out.println("パスワードが一致しました。メールを送信します。");
            // MailSendプログラムを呼び出す
            MailSend.main();
        } else {
            // パスワードが一致しない場合
            System.out.println("パスワードが一致しません。再入力してください。");
        }
        return 0;
    }
}
