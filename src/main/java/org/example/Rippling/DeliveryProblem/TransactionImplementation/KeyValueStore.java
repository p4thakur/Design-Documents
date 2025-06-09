package org.example.Rippling.DeliveryProblem.TransactionImplementation;

public interface KeyValueStore {
    String get(String key);
    void put(String key, String value);
    void remove(String key);
    boolean contains(String key);
}
