package org.example.Rippling.DeliveryProblem.RuleManager;

public class MaxRestaurantExpenseRule implements Rule {
    private final double limit;

    public MaxRestaurantExpenseRule(double limit) {
        this.limit = limit;
    }

    public String getName() {
        return "Seller type restaurant should not have expense more than " + limit;
    }

    @Override
    public boolean isViolated(Expense expense) {
        return expense.getSellerType().equalsIgnoreCase("restaurant") && expense.getAmountInUsd() > limit;
    }
}