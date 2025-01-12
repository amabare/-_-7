import java.util.*;

public class UserIdGenerator {
    //private static final int USER_ID_LENGTH = 8; // ユーザIDの桁数
    private final Set<Integer> existingIds; // 既存のIDを管理するセット

    public UserIdGenerator() {
        this.existingIds = new HashSet<>();
    }

    // 既存のIDを追加するメソッド
    public void addExistingId(int id) {
        existingIds.add(id);
    }

    // 新しいユーザIDを生成するメソッド
    public synchronized String generateNewUserId() {
        int newId = 0;
        while (existingIds.contains(newId)) {
            newId++;
        }

        // 新しいIDをセットに追加
        existingIds.add(newId);

        // ユーザIDを8桁の文字列にフォーマット
        return String.format("%08d", newId);
    }
/*----------------------------------------------------------------- */
    public static void main(String[] args) {
        UserIdGenerator generator = new UserIdGenerator();

        // サンプルデータ（既存のIDを追加）
        generator.addExistingId(0);
        generator.addExistingId(1);
        generator.addExistingId(3); // 空きID 2

        // 新しいユーザIDを生成
        System.out.println("新しいユーザID: " + generator.generateNewUserId()); // 期待される出力: 00000002
        System.out.println("新しいユーザID: " + generator.generateNewUserId()); // 期待される出力: 00000004
    }
}
