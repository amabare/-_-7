import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SelectDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/my_database"; // DBのURLを指定
    private static final String DB_USER = "root"; // データベースユーザー名
    private static final String DB_PASSWORD = "root00"; // データベースのパスワード

    /**
     * 汎用的なSELECTクエリを実行し、結果をリストとして返します。
     *
     * @param tableName テーブル名
     * @param whereCondition WHERE句の条件（例: "email = ? AND password = ?")
     * @param parameters WHERE句に渡すパラメータ
     * @return 結果セットをマップのリストとして返す
     */
    public static List<Map<String, Object>> executeSelect(String tableName, String whereCondition, Object[] parameters) {
        String query = "SELECT * FROM " + tableName + " WHERE " + whereCondition;
        List<Map<String, Object>> results = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // パラメータの設定
            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }

            // クエリの実行
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                int columnCount = resultSet.getMetaData().getColumnCount();

                // 結果セットをリストに格納
                while (resultSet.next()) {
                    Map<String, Object> row = new java.util.HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = resultSet.getMetaData().getColumnName(i);
                        Object columnValue = resultSet.getObject(i);
                        row.put(columnName, columnValue);
                    }
                    results.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}

// javac -cp .:/opt/tomcat/lib/mysql-connector-java-9.1.0.jar SelectDB.java