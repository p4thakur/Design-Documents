package org.example.Rippling.DeliveryProblem.TransactionImplementation;

public class StoreService {
    private final KeyValueStore store;
    private final TransactionManager txnManager;

    public StoreService(KeyValueStore store, TransactionManager txnManager) {
        this.store = store;
        this.txnManager = txnManager;
    }

    public String create(String key, String value) {
        if (txnManager.hasTransactions()) {
            Transaction txn = txnManager.getCurrentTransaction();
            txn.createOrUpdate(key, value);
        } else {
            store.put(key, value);
        }
        return value;
    }

    public String read(String key) {
        if (txnManager.hasTransactions()) {
            for (Transaction txn : txnManager.getTransactions()) {
                if (txn.isDeleted(key)) return "No key associated";
                if (txn.hasUpdate(key)) return txn.get(key);
            }
        }
        return store.contains(key) ? store.get(key) : "No key associated";
    }

    public String update(String key, String value) {
        boolean exists = store.contains(key);
        for (Transaction txn : txnManager.getTransactions()) {
            if (txn.hasUpdate(key) || txn.isDeleted(key)) exists = true;
        }

        if (!exists) return "Cannot update. No key associated";

        if (txnManager.hasTransactions()) {
            txnManager.getCurrentTransaction().createOrUpdate(key, value);
        } else {
            store.put(key, value);
        }
        return value;
    }

    public String delete(String key) {
        boolean exists = store.contains(key);
        for (Transaction txn : txnManager.getTransactions()) {
            if (txn.hasUpdate(key)) exists = true;
            if (txn.isDeleted(key)) return "Cannot delete. No key associated";
        }

        if (!exists) return "Cannot delete. No key associated";

        if (txnManager.hasTransactions()) {
            txnManager.getCurrentTransaction().delete(key);
        } else {
            store.remove(key);
        }
        return "Delete Success";
    }

    public void begin() {
        txnManager.begin();
    }

    public void commit() {
        txnManager.commitTop(store);
    }

    public void commit(int t) {
        txnManager.commitLastT(t, store);
    }

    public void rollback() {
        txnManager.rollbackTop();
    }

    public void rollback(int t) {
        txnManager.rollbackLastT(t);
    }
}

