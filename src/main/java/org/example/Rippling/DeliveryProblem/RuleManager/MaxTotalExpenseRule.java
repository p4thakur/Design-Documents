package org.example.Rippling.DeliveryProblem.RuleManager;

public class MaxTotalExpenseRule implements Rule {
    private final double limit;

    public MaxTotalExpenseRule(double limit) {
        this.limit = limit;
    }

    public String getName() {
        return "Total expense should not be > " + limit;
    }

    @Override
    public boolean isViolated(Expense expense) {
        return expense.getAmountInUsd() > limit;
    }
}




