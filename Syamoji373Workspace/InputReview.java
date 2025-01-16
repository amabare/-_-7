/*
作成者：シモジ
更新日時：2025年1月13日22時47分
動作・役割：店舗の評価と口コミを登録する
コメント： 改善の余地あり(入力確認など)日本語表示できない
*/

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class InputReview {
    public static int InputReview(){
//public static int InputReview(HttpServletRequest request){
        //セッションから入力情報取得（いったん標準入力で作成）
        // 口コミと評価の取得
        // review = request.getParameter("");
        //  ratingInput = request.getParameter("");

        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in, "UTF-8")));
        System.out.println("口コミを入力してください:");
        String review = scanner.nextLine();
        System.out.println("評価（1から5の整数）を入力してください:");
        String ratingInput = scanner.nextLine();

        // 入力形式の検証
        if (!isValidReview(review)) {
            System.out.println("口コミが無効です。再入力してください。");
            return -1;
        }

        int rating;
        try {
            rating = Integer.parseInt(ratingInput);
            if (rating < 1 || rating > 5) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("評価は1〜5の整数で入力してください。");
            return -1;
        }


        // 記入日時生成
        LocalDateTime timestamp = LocalDateTime.now();

        Map<String, Object> values = new HashMap<>();
        values.put("review", review);
        values.put("rating", rating);
        values.put("timestamp", timestamp);

        String table_name  = "EVALUATION";

        // 入力情報をDBに格納（スタブ）
        StubExample.InsertDB(table_name, values);
        // DataBaseUtilityクラスのinsertDBメソッドを呼び出す
        //DatabaseUtility.insertDB(tableName, values); 

        //System.out.println("口コミと評価が正常に登録されました。");
        return 0;
    }

    // 口コミの有効性を検証するメソッド
    private static boolean isValidReview(String review) {
        // 口コミが空でないことを確認
        return review != null && !review.trim().isEmpty();
    }
}
