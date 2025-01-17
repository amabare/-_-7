import java.util.*;

public class TestDatabaseUtility {

    public static void main(String[] args) {
        try {
            // テーブル名
            String tableName = "reservations";

            // SELECTテスト
            System.out.println("=== SELECT TEST ===");
            List<Map<String, Object>> selectResults = DatabaseUtility.select(tableName, "num_of_people > ?", new Object[]{2});
            for (Map<String, Object> row : selectResults) {
                System.out.println(row);
            }

            // INSERTテスト
            System.out.println("\n=== INSERT TEST ===");
            Map<String, Object> insertValues = new HashMap<>();
            insertValues.put("customer_name", "Daisy");
            insertValues.put("reservation_date", "2025-01-19");
            insertValues.put("num_of_people", 3);
            int insertResult = DatabaseUtility.insert(tableName, insertValues);
            System.out.println("Rows inserted: " + insertResult);

            // UPDATEテスト
            System.out.println("\n=== UPDATE TEST ===");
            Map<String, Object> updateValues = new HashMap<>();
            updateValues.put("num_of_people", 5);
            int updateResult = DatabaseUtility.update(tableName, updateValues, "customer_name = 'Daisy'");
            System.out.println("Rows updated: " + updateResult);

            // DELETEテスト
            System.out.println("\n=== DELETE TEST ===");
            int deleteResult = DatabaseUtility.delete(tableName, "customer_name = 'Daisy'");
            System.out.println("Rows deleted: " + deleteResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
