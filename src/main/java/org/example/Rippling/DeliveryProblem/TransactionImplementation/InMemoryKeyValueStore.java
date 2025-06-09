package org.example.Rippling.DeliveryProblem.TransactionImplementation;

import java.util.*;

import java.util.HashMap;
import java.util.Map;

//rather in store this should be part of manager.
public class InMemoryKeyValueStore implements KeyValueStore {
    private final Map<String, String> store = new HashMap<>();

    public String get(String key) {
        return store.get(key);
    }

    public void put(String key, String value) {
        store.put(key, value);
    }

    public void remove(String key) {
        store.remove(key);
    }

    public boolean contains(String key) {
        return store.containsKey(key);
    }
}
