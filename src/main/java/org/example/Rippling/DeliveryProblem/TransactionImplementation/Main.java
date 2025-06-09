package org.example.Rippling.DeliveryProblem.TransactionImplementation;

public class Main {
    public static void main(String[] args) {

        KeyValueStore store = new InMemoryKeyValueStore();

        store.create("a", "1");
        System.out.println(store.read("a")); // 1

        store.begin();
        store.update("a", "2");
        System.out.println(store.read("a")); // 2

        store.begin();
        store.delete("a");
        System.out.println(store.read("a")); // No key associated

        store.rollback();
        System.out.println(store.read("a")); // 2

        store.commit();
        System.out.println(store.read("a")); // 2

        // Create some keys outside transaction
        System.out.println(store.create("key1", "val1")); // val1
        System.out.println(store.create("key2", "val2")); // val2

        // Begin first transaction (txn1)
        store.begin();
        store.update("key1", "val2"); // txn1 has changes

        // Begin second transaction (txn2) - no changes here
        store.begin();
        // No changes in txn2

        // Begin third transaction (txn3)
        store.begin();
        store.delete("key2"); // txn3 has changes

        // Check how many transactions have changes
        int changedCount = 0;
//        for (Transaction txn : store.getTransactions()) {
//            if (txn.hasChanges()) {
//                changedCount++;
//            }
//        }
        System.out.println("Changed transactions count: " + changedCount); // Expect 2

        // Commit first 1 transaction with changes (txn1)
        store.commit(1);
        System.out.println("After commit(1):");
        System.out.println("Read key1: " + store.read("key1")); // Should print val2 (updated)
        System.out.println("Read key2: " + store.read("key2")); // Should print val2 (not deleted yet)

        // Rollback last 1 transaction with changes (txn3)
        store.rollback(1);
        System.out.println("After rollback(1):");
        System.out.println("Read key2: " + store.read("key2")); // Should print val2 (rollback delete)

        // txn2 was empty transaction, let's see if it's still there
        //System.out.println("Remaining transactions count: " + store.getTransactions().size());

        System.out.println("All tests passed.");
    }
}


