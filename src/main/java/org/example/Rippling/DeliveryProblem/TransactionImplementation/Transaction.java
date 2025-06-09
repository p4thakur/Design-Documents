package org.example.Rippling.DeliveryProblem.TransactionImplementation;

import java.util.*;
public class Transaction {
    private final Map<String, String> updates = new HashMap<>();
    private final Set<String> deletedKeys = new HashSet<>();

    public void createOrUpdate(String key, String value) {
        updates.put(key, value);
        deletedKeys.remove(key);
    }

    public void delete(String key) {
        updates.remove(key);
        deletedKeys.add(key);
    }

    public boolean hasUpdate(String key) {
        return updates.containsKey(key);
    }

    public boolean isDeleted(String key) {
        return deletedKeys.contains(key);
    }

    public String get(String key) {
        return updates.get(key);
    }

    public boolean hasChanges() {
        return !updates.isEmpty() || !deletedKeys.isEmpty();
    }

    public void apply(KeyValueStore store) {
        for (String key : deletedKeys) store.remove(key);
        for (Map.Entry<String, String> e : updates.entrySet()) {
            store.put(e.getKey(), e.getValue());
        }
    }

    public void merge(Transaction child) {
        for (String key : child.deletedKeys) this.delete(key);
        for (Map.Entry<String, String> e : child.updates.entrySet()) {
            this.createOrUpdate(e.getKey(), e.getValue());
        }
    }
}
