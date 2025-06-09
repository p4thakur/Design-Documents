package org.example.Rippling.DeliveryProblem.TransactionImplementation;

public class Main {
    public static void main(String[] args) {
        KeyValueStore baseStore = new InMemoryKeyValueStore();
        TransactionManager txnManager = new TransactionManager();
        StoreService storeService = new StoreService(baseStore, txnManager);

        System.out.println("Create a -> apple: " + storeService.create("a", "apple"));
        System.out.println("Read a: " + storeService.read("a")); // apple

        storeService.begin();
        System.out.println("\nBegin transaction 1");
        storeService.update("a", "apricot");
        storeService.create("b", "banana");
        System.out.println("Read a: " + storeService.read("a")); // apricot
        System.out.println("Read b: " + storeService.read("b")); // banana

        storeService.begin();
        System.out.println("\nBegin transaction 2");
        storeService.update("b", "blueberry");
        storeService.delete("a");
        System.out.println("Read a: " + storeService.read("a")); // No key associated
        System.out.println("Read b: " + storeService.read("b")); // blueberry

        System.out.println("\nRolling back transaction 2...");
        storeService.rollback();

        System.out.println("Read a after rollback: " + storeService.read("a")); // apricot
        System.out.println("Read b after rollback: " + storeService.read("b")); // banana

        System.out.println("\nCommitting transaction 1...");
        storeService.commit();

        System.out.println("Read a after commit: " + storeService.read("a")); // apricot
        System.out.println("Read b after commit: " + storeService.read("b")); // banana

        System.out.println("\nDeleting a...");
        System.out.println("Delete result: " + storeService.delete("a")); // Delete Success
        System.out.println("Read a after delete: " + storeService.read("a")); // No key associated

        System.out.println("\nTrying rollback with no active transaction...");
        try {
            storeService.rollback();
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }
    }
}


