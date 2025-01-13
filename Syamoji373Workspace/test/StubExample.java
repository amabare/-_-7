// （delete,update,insertDB等）
// スタブクラス

import java.util.Map;

public class StubExample extends DeleteReservation{
    public static void DeleteDB(String a, String b){
        System.out.println("delete;" + a);
        System.out.println(b);
    }

    public static void InsertDB(String tableName, Map<String, Object> values){
        System.out.println("insert; " + tableName);
        System.out.println();
        System.out.println("Data to be inserted into the database (but will not be inserted):");
        System.out.println("Table: " + tableName);
        System.out.println("Data: " + values);
    }

    public static void UpdateDB(String tableName, Map<String, Object> values, String whereClause){
        System.out.println("change; ");
        System.out.println();
        System.out.println("Data to be inserted into the database (but will not be inserted):");
        System.out.println("Table: " + tableName);
        System.out.println("Data: " + values);
        System.out.println("Where: " + whereClause);
    }
}