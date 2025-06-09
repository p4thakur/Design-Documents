package org.example.Rippling.DeliveryProblem.RuleManager;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        /**
        List<Rule> rules = List.of(
                new MaxTotalExpenseRule(175),
                new MaxRestaurantExpenseRule(45),
                new EntertainmentNotAllowedRule()
        );

        List<Expense> expenses = List.of(
                new Expense("1", "Item1", "Food", 250, "restaurant", "ABC Restaurant"),
                new Expense("2", "Item2", "Entertainment", 100, "club", "XYZ Club"),
                new Expense("3", "Item3", "Travel", 50, "taxi", "Taxi Inc")
        );

        Map<String, List<String>> result = RuleEvaluator.evaluateRules(rules, expenses);

        for (Map.Entry<String, List<String>> entry : result.entrySet()) {
            System.out.println("Expense ID: " + entry.getKey() + " violated rules:");
            for (String rule : entry.getValue()) {
                System.out.println("  - " + rule);
            }
        }
         ***/

        Rule rule1 = new MaxTotalExpenseRule(175);
        Rule rule2 = new MaxRestaurantExpenseRule(45);
        Rule rule3 = new EntertainmentNotAllowedRule();

        Rule foodRule = new Rule() {
            public String getName() {
                return "ExpenseType == Food";
            }

            public boolean isViolated(Expense e) {
                return e.getExpenseType().equalsIgnoreCase("Food");
            }
        };

        // Composite: Amount > 175 AND (Seller == restaurant OR ExpenseType == Food)
        CompositeRule orSubRule = new CompositeRule(
                "restaurant OR food",
                List.of(rule2, foodRule),
                CompositeRule.Operator.OR
        );

        CompositeRule composite = new CompositeRule(
                "Big amount + (restaurant OR food)",
                List.of(rule1, orSubRule),
                CompositeRule.Operator.AND
        );

        List<Rule> rules = List.of(rule1, rule2, rule3, composite);

        List<Expense> expenses = List.of(
                new Expense("1", "Item1", "Food", 250, "restaurant", "ABC Restaurant"),
                new Expense("2", "Item2", "Entertainment", 100, "club", "XYZ Club"),
                new Expense("3", "Item3", "Travel", 50, "taxi", "Taxi Inc")
        );

        Map<String, List<String>> result = RuleEvaluator.evaluateRules(rules, expenses);

        for (Map.Entry<String, List<String>> entry : result.entrySet()) {
            System.out.println("Expense ID: " + entry.getKey() + " violated rules:");
            for (String rule : entry.getValue()) {
                System.out.println("  - " + rule);
            }
        }
    }


}
