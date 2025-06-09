package org.example.Rippling.DeliveryProblem.RuleManager;

public class EntertainmentNotAllowedRule implements Rule {
    public String getName() {
        return "Entertainment expense type should not be charged";
    }

    @Override
    public boolean isViolated(Expense expense) {
        return expense.getExpenseType().equalsIgnoreCase("Entertainment");
    }
}