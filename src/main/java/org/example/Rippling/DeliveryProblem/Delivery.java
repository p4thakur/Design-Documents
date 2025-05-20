package org.example.Rippling.DeliveryProblem;

public class Delivery {

    long startEpoch;
    long endEpoch;

    public Delivery(long startEpoch, long endEpoch) {
        this.startEpoch = startEpoch;
        this.endEpoch = endEpoch;
    }

    public long getDurationInSeconds() {
        return endEpoch - startEpoch;
    }

    public boolean isBefore(long cutoff) {
        return endEpoch <= cutoff;
    }
}
