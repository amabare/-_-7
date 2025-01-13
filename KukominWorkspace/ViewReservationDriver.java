public class ViewReservationDriver {
    public static void main(String[] args) {
        DatabaseStub dbStub = new DatabaseStub();
        
        // テストケース1: 予約済み
        System.out.println("Test Case 1: " + 
            dbStub.getReservationStatus("2025-01-10", "9:00", 1));

        // テストケース2: 空き
        System.out.println("Test Case 2: " + 
            dbStub.getReservationStatus("2025-01-10", "10:00", 1));
    }
}
