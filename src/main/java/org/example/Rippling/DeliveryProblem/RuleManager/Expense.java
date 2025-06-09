package org.example.Rippling.DeliveryProblem.RuleManager;

public  class Expense {
    String expenseId;
    String itemId;
    String expenseType;
    double amountInUsd;
    String sellerType;
    String sellerName;

    // constructor, getters
    public Expense(String expenseId, String itemId, String expenseType, double amountInUsd, String sellerType, String sellerName) {
        this.expenseId = expenseId;
        this.itemId = itemId;
        this.expenseType = expenseType;
        this.amountInUsd = amountInUsd;
        this.sellerType = sellerType;
        this.sellerName = sellerName;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public double getAmountInUsd() {
        return amountInUsd;
    }

    public String getSellerType() {
        return sellerType;
    }
}

