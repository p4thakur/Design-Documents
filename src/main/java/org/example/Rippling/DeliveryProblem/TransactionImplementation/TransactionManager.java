package org.example.Rippling.DeliveryProblem.TransactionImplementation;

import java.util.*;

public class TransactionManager {
    private final Deque<Transaction> transactions = new ArrayDeque<>();

    public void begin() {
        transactions.push(new Transaction());
    }

    public void commitTop(KeyValueStore store) {
        if (transactions.isEmpty()) throw new IllegalStateException("No transaction");

        Transaction txn = transactions.pop();
        if (transactions.isEmpty()) {
            txn.apply(store);
        } else {
            transactions.peek().merge(txn);
        }
    }

    public void rollbackTop() {
        if (transactions.isEmpty()) throw new IllegalStateException("No transaction");
        transactions.pop(); // discard
    }

    public Transaction getCurrentTransaction() {
        return transactions.peek();
    }

    public boolean hasTransactions() {
        return !transactions.isEmpty();
    }

    public Deque<Transaction> getTransactions() {
        return transactions;
    }

    // Commit last t transactions with changes
    public void commitLastT(int t, KeyValueStore store) {
        List<Transaction> toCommit = new ArrayList<>();
        Iterator<Transaction> it = transactions.descendingIterator();
        while (it.hasNext() && toCommit.size() < t) {
            Transaction txn = it.next();
            if (txn.hasChanges()) toCommit.add(txn);
        }

        if (toCommit.size() < t) throw new IllegalStateException("Not enough changed transactions");

        for (Transaction txn : toCommit) {
            transactions.remove(txn);
            if (transactions.isEmpty()) {
                txn.apply(store);
            } else {
                transactions.peek().merge(txn);
            }
        }
    }

    public void rollbackLastT(int t) {
        int count = 0;
        while (!transactions.isEmpty() && count < t) {
            Transaction txn = transactions.peek();
            transactions.pop();
            if (txn.hasChanges()) count++;
        }

        if (count < t) throw new IllegalStateException("Not enough changed transactions");
    }
}
