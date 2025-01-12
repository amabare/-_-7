/*
作成者：澤村
更新日時：2025年1月8日
動作・役割：電話番号認証
コメント： 改善の余地あり
*/
import java.util.Random;
import java.util.Scanner;

public class SMSCertification {
    public static void main(String[] args) {
        // 6桁のランダムな数字を生成
        int generatedCode = generateRandomCode();
        System.out.println("Generated Code (for testing): " + generatedCode);

        // try-with-resources 構文で Scanner を使用
        try (Scanner scanner = new Scanner(System.in)) {
            // 電話番号の入力（模擬）
            System.out.print("Enter your phone number: ");
            String phoneNumber = scanner.nextLine();

            // SMS送信の模擬（実際の送信処理はAPIなどで実装する必要があります）
            sendSms(phoneNumber, generatedCode);

            // 入力された認証コードを取得
            System.out.print("Enter the verification code you received: ");
            int enteredCode = scanner.nextInt();

            // 認証コードの一致確認
            if (verifyCode(generatedCode, enteredCode)) {
                System.out.println("Verification successful!");
                System.exit(0); // 成功時に0を返す
            } else {
                System.out.println("Verification failed. Please try again.");
            }
        } // ここで Scanner が自動的にクローズされる
    }

    // 6桁のランダムな数字を生成するメソッド
    private static int generateRandomCode() {
        Random random = new Random();
        return 100000 + random.nextInt(900000); // 6桁のランダムな数字を生成
    }

    // SMSを送信する模擬メソッド
    private static void sendSms(String phoneNumber, int code) {
        // 実際にはSMS送信APIを利用して実装する
        System.out.println("Sending SMS to " + phoneNumber + " with code: " + code);
    }

    // 認証コードを検証するメソッド
    private static boolean verifyCode(int generatedCode, int enteredCode) {
        return generatedCode == enteredCode;
    }
}
