package org.example.Rippling.DeliveryProblem.TransactionImplementation;

import java.util.*;

public class Transaction {
    private final Map<String, String> updates = new HashMap<>();
    private final Set<String> deletedKeys = new HashSet<>();

    public void createOrUpdate(String key, String value) {
        updates.put(key, value);
        deletedKeys.remove(key); // If previously marked deleted, undo that
    }

    public void delete(String key) {
        updates.remove(key);
        deletedKeys.add(key);
    }

    public boolean isDeleted(String key) {
        return deletedKeys.contains(key);
    }

    public boolean hasUpdate(String key) {
        return updates.containsKey(key);
    }

    public String getUpdatedValue(String key) {
        return updates.get(key);
    }

    public Map<String, String> getAllUpdates() {
        return updates;
    }

    public Set<String> getDeletedKeys() {
        return deletedKeys;
    }

    public boolean hasChanges() {
        return !updates.isEmpty() || !deletedKeys.isEmpty();
    }


}
