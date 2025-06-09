package org.example.Rippling.DeliveryProblem.RuleManager;

public  interface Rule {
    String getName();
    boolean isViolated(Expense expense);
}
