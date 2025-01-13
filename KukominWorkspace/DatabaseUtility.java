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

    public static int insert(String tableName, Map<String, Object> values) throws Exception {
        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();
    
        for (String column : values.keySet()) {
            columns.append(column).append(",");
            placeholders.append("?,");
        }
    
        // 最後のカンマを削除
        columns.setLength(columns.length() - 1);
        placeholders.setLength(placeholders.length() - 1);
    
        String query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";
    
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement statement = connection.prepareStatement(query);
    
        int index = 1;
        for (Object value : values.values()) {
            statement.setObject(index++, value);
        }
    
        return statement.executeUpdate();
    }
    
    public static int update(String tableName, Map<String, Object> values, String whereClause) throws Exception {
        StringBuilder setClause = new StringBuilder();
    
        for (String column : values.keySet()) {
            setClause.append(column).append(" = ?,");
        }
    
        // 最後のカンマを削除
        setClause.setLength(setClause.length() - 1);
    
        String query = "UPDATE " + tableName + " SET " + setClause + " WHERE " + whereClause;
    
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement statement = connection.prepareStatement(query);
    
        int index = 1;
        for (Object value : values.values()) {
            statement.setObject(index++, value);
        }
    
        return statement.executeUpdate();
    }
    
    public static int delete(String tableName, String whereClause) throws Exception {
        String query = "DELETE FROM " + tableName + " WHERE " + whereClause;
    
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement statement = connection.prepareStatement(query);
    
        return statement.executeUpdate();
}

}

// javac -cp /usr/share/java/mysql-connector-j.jar DatabaseUtility.java
// java -cp .:/usr/share/java/mysql-connector-j.jar DatabaseUtility
