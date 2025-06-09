package org.example.Rippling.DeliveryProblem.RuleManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleEvaluator {
    public static Map<String, List<String>> evaluateRules(List<Rule> rules, List<Expense> expenses) {
        Map<String, List<String>> violations = new HashMap<>();

        for (Expense expense : expenses) {
            List<String> violated = new ArrayList<>();
            for (Rule rule : rules) {
                if (rule.isViolated(expense)) {
                    violated.add(rule.getName());
                }
            }
            if (!violated.isEmpty()) {
                violations.put(expense.getExpenseId(), violated);
            }
        }
        return violations;
    }
}
