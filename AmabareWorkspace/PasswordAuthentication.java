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
    public static boolean checkPassword(String inputpassword, String currentpassword) {
        // 入力されたパスワードと現行のパスワードが一致しているかを検査
        return currentpassword.equals(inputpassword);
    }

    public static int main(String[] args) {
        // 引数が足りない場合
        if (args.length < 2) {
            System.out.println("入力されていない部分があります。再入力してください。");
            return 0;
        }

        // 現在のパスワード
        String currentpassword = args[0];
        // 入力されたパスワード
        String inputpassword = args[1];

        if (checkPassword(inputpassword, currentpassword)) {
            // パスワードが一致した場合
            System.out.println("パスワードが一致しました。メールを送信します。");
            // MailSendプログラムを呼び出す
            // MailSend.main();

            // テスト段階なので StaPasswordAuthentication.java を呼び出す
            StaPasswordAuthentication.main(args);
        } else {
            // パスワードが一致しない場合
            System.out.println("パスワードが一致しません。再入力してください。");
        }
        return 0;
    }
}
