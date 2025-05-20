package org.example.Rippling.DeliveryProblem;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    int driverId;
    double hourlyRate;
    List<Delivery> deliveries;

    public Driver(int driverId, double hourlyRate) {
        this.driverId = driverId;
        this.hourlyRate = hourlyRate;
        this.deliveries = new ArrayList<>();
    }

    public void addDelivery(long start, long end) {
        deliveries.add(new Delivery(start, end));
    }

    public double getTotalPayment() {
        double total = 0.0;
        for (Delivery d : deliveries) {
            total += (d.getDurationInSeconds() / 3600.0) * hourlyRate;
        }
        return total;
    }

    public double getPaidUntil(long cutoffEpoch) {
        double total = 0.0;
        for (Delivery d : deliveries) {
            if (d.isBefore(cutoffEpoch)) {
                total += (d.getDurationInSeconds() / 3600.0) * hourlyRate;
            }
        }
        return total;
    }

    public double getUnpaidSince(long cutoffEpoch) {
        double total = 0.0;
        for (Delivery d : deliveries) {
            if (!d.isBefore(cutoffEpoch)) {
                total += (d.getDurationInSeconds() / 3600.0) * hourlyRate;
            }
        }
        return total;
    }
}

