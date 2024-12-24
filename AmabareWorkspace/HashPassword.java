package AmabareWorkspace;

// SHA-256 などのアルゴリズムを用いてデータのハッシュ値を求めることができる
import java.security.MessageDigest;
// 指定された暗号アルゴリズムが見つからない場合にスローされる例外
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    public static String HashString(String input) {
        try {
            // MessageDigest インスタンスを取得 (アルゴリズムは SHA-256 を使用)
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // 入力文字列をバイト配列に変換し、ハッシュ値を計算
            byte[] hashBytes = md.digest(input.getBytes());

            // バイト配列を16進数の文字列に変換
            // StringBuilder クラスを使用することで文字列の連結を高速化、可変の文字列を扱える
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                // 1 バイトの値を 16 進数の文字列に変換
                // (例: 0x0A -> "0a", 0xFF -> "ff")
                sb.append(String.format("%02x", b));
            }

            // ハッシュ値の文字列を返す
            return sb.toString();
        } // NoSuchAlgorithmException がスローされた場合の例外処理
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
