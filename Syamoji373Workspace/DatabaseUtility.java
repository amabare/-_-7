// package your.package.name;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

public class DatabaseUtility {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root00";

    // SELECTモジュール
    public static ResultSet selectDB(String tableName, String whereClause, Object[] parameters) throws Exception {
        // SQLクエリを準備
        String query = "SELECT * FROM " + tableName + " WHERE " + whereClause;

        // データベース接続
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement statement = connection.prepareStatement(query);

        // パラメータをセット
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }

        // クエリを実行し、結果を返す
        return statement.executeQuery();
    }


    // INSERTモジュール
    public static int insertDB(String tableName, Map<String, Object> values) throws Exception {
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

    // UPDATEモジュール
    public static int updateDB(String tableName, Map<String, Object> values, String whereClause) throws Exception {
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

    // DELETEモジュール(テーブル名、where以下の文)
    public static int deleteDB(String tableName, String whereClause) throws Exception {
        String query = "DELETE FROM " + tableName + " WHERE " + whereClause;

        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement statement = connection.prepareStatement(query);

        return statement.executeUpdate();
    }
}
