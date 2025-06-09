package org.example.Rippling.DeliveryProblem.TransactionImplementation;

public interface KeyValueStore {
    String create(String key, String value);
    String read(String key);
    String update(String key, String value);
    String delete(String key);
    void begin();
    void  commit();
    void commit(int t);
    void rollback(int t);
    void rollback();
}