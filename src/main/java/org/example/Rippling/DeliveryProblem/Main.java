package org.example.Rippling.DeliveryProblem;

public class Main {
    public static void main(String[] args) {
        DriverManager dm = new DriverManager();

        dm.addDriver(1, 100.0);
        dm.addDriver(2, 200.0);

        dm.addOrder(1, 1_000_000, 1_008_000); // 8000 sec = ~2.22 hr
        dm.addOrder(1, 1_009_000, 1_014_000); // 5000 sec = ~1.38 hr
        dm.addOrder(2, 1_005_000, 1_012_000); // 7000 sec = ~1.94 hr

        System.out.println("Total payment: " + dm.getTotalPayment());
        System.out.println("Paid until 1_010_000: " + dm.getPaidUntil(1_010_000));
        System.out.println("Unpaid since 1_010_000: " + dm.getUnpaidSince(1_010_000));
    }
}

