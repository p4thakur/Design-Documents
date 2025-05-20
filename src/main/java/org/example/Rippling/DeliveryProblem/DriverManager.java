package org.example.Rippling.DeliveryProblem;

import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private Map<Integer, Driver> drivers = new HashMap<>();

    public void addDriver(int driverId, double hourlyRate) {
        drivers.put(driverId, new Driver(driverId, hourlyRate));
    }

    public void addOrder(int driverId, long startEpoch, long endEpoch) {
        if (!drivers.containsKey(driverId)) {
            throw new IllegalArgumentException("Driver does not exist.");
        }
        drivers.get(driverId).addDelivery(startEpoch, endEpoch);
    }

    public double getTotalPayment() {
        double total = 0.0;
        for (Driver d : drivers.values()) {
            total += d.getTotalPayment();
        }
        return total;
    }

    public double getPaidUntil(long cutoffEpoch) {
        double total = 0.0;
        for (Driver d : drivers.values()) {
            total += d.getPaidUntil(cutoffEpoch);
        }
        return total;
    }

    public double getUnpaidSince(long cutoffEpoch) {
        double total = 0.0;
        for (Driver d : drivers.values()) {
            total += d.getUnpaidSince(cutoffEpoch);
        }
        return total;
    }
}
