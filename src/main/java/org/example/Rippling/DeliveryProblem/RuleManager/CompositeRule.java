package org.example.Rippling.DeliveryProblem.RuleManager;

import java.util.List;

public class CompositeRule implements Rule {
    public enum Operator {
        AND, OR
    }

    private final String name;
    private final List<Rule> subRules;
    private final Operator operator;

    public CompositeRule(String name, List<Rule> subRules, Operator operator) {
        this.name = name;
        this.subRules = subRules;
        this.operator = operator;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isViolated(Expense expense) {
        if (operator == Operator.AND) {
            return subRules.stream().allMatch(rule -> rule.isViolated(expense));
        } else if (operator == Operator.OR) {
            return subRules.stream().anyMatch(rule -> rule.isViolated(expense));
        }
        return false;
    }
}
