package org.example.Rippling.DeliveryProblem.TransactionImplementation;

import java.util.*;

import java.util.HashMap;
import java.util.Map;

public class InMemoryKeyValueStore implements KeyValueStore {
    private final Map<String, String> mainStore = new HashMap<>();
    private final Deque<Transaction> transactions = new ArrayDeque<>();

    @Override
    public String create(String key, String value) {
        if (!transactions.isEmpty()) {
            if (existsInCurrentOrParents(key)) {
                return update(key, value);
            }
            transactions.peek().createOrUpdate(key, value);
            return value;
        } else {
            if (mainStore.containsKey(key)) {
                return update(key, value);
            }
            mainStore.put(key, value);
            return value;
        }
    }

    @Override
    public String read(String key) {
        if (!transactions.isEmpty()) {
            for (Transaction txn : transactions) {
                if (txn.isDeleted(key)) return "No key associated";
                if (txn.hasUpdate(key)) return txn.getUpdatedValue(key);
            }
            return mainStore.getOrDefault(key, "No key associated");
        } else {
            return mainStore.getOrDefault(key, "No key associated");
        }
    }

    @Override
    public String update(String key, String value) {
        if (!transactions.isEmpty()) {
            if (!existsInCurrentOrParents(key) && !mainStore.containsKey(key)) {
                return "Cannot update. No key associated";
            }
            transactions.peek().createOrUpdate(key, value);
            return value;
        } else {
            if (!mainStore.containsKey(key)) {
                return "Cannot update. No key associated";
            }
            mainStore.put(key, value);
            return value;
        }
    }

    @Override
    public String delete(String key) {
        if (!transactions.isEmpty()) {
            if (!existsInCurrentOrParents(key) && !mainStore.containsKey(key)) {
                return "Cannot delete. No key associated";
            }
            transactions.peek().delete(key);
            return "Delete Success";
        } else {
            if (!mainStore.containsKey(key)) {
                return "Cannot delete. No key associated";
            }
            mainStore.remove(key);
            return "Delete Success";
        }
    }

    public void begin() {
        transactions.push(new Transaction());
    }

    public void commit() {
        if (transactions.isEmpty()) {
            throw new IllegalStateException("No transaction in progress");
        }
        Transaction current = transactions.pop();
        if (transactions.isEmpty()) {
            // No parent transaction - commit to main store
            applyTransactionToStore(current, mainStore);
        } else {
            // Merge current changes into parent transaction
            Transaction parent = transactions.peek();
            mergeTransactions(current, parent);
        }
    }

    public void rollback() {
        if (transactions.isEmpty()) {
            throw new IllegalStateException("No transaction in progress");
        }
        transactions.pop(); // discard current transaction
    }

    // Helper methods

    private boolean existsInCurrentOrParents(String key) {
        for (Transaction txn : transactions) {
            if (txn.isDeleted(key)) return false;
            if (txn.hasUpdate(key)) return true;
        }
        return false;
    }

    private void applyTransactionToStore(Transaction txn, Map<String, String> store) {
        for (Map.Entry<String, String> e : txn.getAllUpdates().entrySet()) {
            store.put(e.getKey(), e.getValue());
        }
        for (String key : txn.getDeletedKeys()) {
            store.remove(key);
        }
    }

    private void mergeTransactions(Transaction from, Transaction to) {
        // Updates overwrite parent's updates
        for (Map.Entry<String, String> e : from.getAllUpdates().entrySet()) {
            to.createOrUpdate(e.getKey(), e.getValue());
        }
        // Deletions propagate to parent
        for (String key : from.getDeletedKeys()) {
            to.delete(key);
        }
    }

    public void commit(int t) {
        if (t <= 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (transactions.isEmpty()) {
            throw new IllegalStateException("No transactions in progress");
        }

        int committedCount = 0;
        // We'll temporarily collect committed transactions
        List<Transaction> committedTxns = new ArrayList<>();

        // Iterate from bottom to top
        Iterator<Transaction> it = transactions.descendingIterator(); // bottom -> top
        while (it.hasNext() && committedCount < t) {
            Transaction txn = it.next();
            if (txn.hasChanges()) {
                committedTxns.add(txn);
                committedCount++;
            }
        }

        if (committedCount < t) {
            throw new IllegalStateException("Not enough changed transactions to commit");
        }

        // Now actually commit these transactions from bottom to top
        for (Transaction txn : committedTxns) {
            // Remove txn from stack
            transactions.remove(txn);
            // Merge txn changes to next transaction or main store
            if (transactions.isEmpty()) {
                applyTransactionToStore(txn, mainStore);
            } else {
                Transaction parent = transactions.peek();
                mergeTransactions(txn, parent);
            }
        }
    }

    // Rollback last t transactions with changes, top to bottom
    public void rollback(int t) {
        if (t <= 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (transactions.isEmpty()) {
            throw new IllegalStateException("No transactions in progress");
        }

        int rolledBackCount = 0;
        while (!transactions.isEmpty() && rolledBackCount < t) {
            Transaction txn = transactions.peek();
            if (txn.hasChanges()) {
                transactions.pop();
                rolledBackCount++;
            } else {
                // Pop no-change transactions anyway to keep stack consistent
                transactions.pop();
            }
        }

        if (rolledBackCount < t) {
            throw new IllegalStateException("Not enough changed transactions to rollback");
        }
    }

    public Deque<Transaction> getTransactions() {
        return transactions;
    }

}
