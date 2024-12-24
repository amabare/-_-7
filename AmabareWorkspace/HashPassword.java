/*
 * ソースコード作成者: 山本
 * 修正日時: 2024/12/24 16:39
 * ソースコードの動作や役割: パスワードのハッシュ化を行う
 * コメント: ハッシュ化を行うために「SHA-256」アルゴリズムを使用した
 */

package AmabareWorkspace;

// SHA-256 などのアルゴリズムを用いてデータのハッシュ値を求めることができる
import java.security.MessageDigest;
// 指定された暗号アルゴリズムが見つからない場合にスローされる例外
import java.security.NoSuchAlgorithmException;

public class HashPassword {

    // ハッシュ化された文字列を返すメソッド
    public static String HashString(String input) {
        try {
            // MessageDigest インスタンスを取得 (アルゴリズムは SHA-256 を使用)
            MessageDigest msg = MessageDigest.getInstance("SHA-256");

            // 入力文字列をバイト配列に変換し、ハッシュ値を計算
            byte[] hashbyte = msg.digest(input.getBytes());

            // バイト配列を16進数の文字列に変換
            // StringBuilder クラスを使用することで文字列の連結を高速化、可変の文字列を扱える
            StringBuilder str = new StringBuilder();
            // bstr には hashbyte の要素が順番に格納される
            for (byte bstr : hashbyte) {
                // 1 バイトの値を 16 進数の文字列に変換
                // (例: 0x0A -> "0a", 0xFF -> "ff")
                str.append(String.format("%02x", bstr));
            }

            // ハッシュ値の文字列を返す
            return str.toString();
        } // NoSuchAlgorithmException がスローされた場合の例外処理
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
