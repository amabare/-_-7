/*
ソースコード作成者: 浅島
修正日時: 2025/01/12
ソースコードの動作や役割: DBモジュールが全て入る予定
コメント:SELECTはできる
*/

import java.sql.*;
import java.util.*;
// reservation_system

public class DatabaseUtility {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/reservation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root00";

    // SELECTメソッドを修正して、List<Map<String, Object>> を返すように変更
    public static List<Map<String, Object>> select(String tableName, String whereClause, Object[] parameters) throws Exception {
        List<Map<String, Object>> resultList = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;
        if (whereClause != null && !whereClause.isEmpty()) {
            query += " WHERE " + whereClause;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    statement.setObject(i + 1, parameters[i]);
                }
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Map<String, Object> row = new HashMap<>();
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        Object columnValue = resultSet.getObject(i);
                        row.put(columnName, columnValue);
                    }
                    resultList.add(row);
                }
            }
        }
        return resultList;
    }
}

// javac -cp /usr/share/java/mysql-connector-j.jar DatabaseUtility.java
// java -cp .:/usr/share/java/mysql-connector-j.jar DatabaseUtility
