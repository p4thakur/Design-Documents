package org.example.PhonePey.CRM.AppVersion;

public class PercentageRolloutStrategy implements RolloutStrategy {
    private final int percentage;

    public PercentageRolloutStrategy(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean shouldRolloutTo(Device device, AppVersion version) {
        return Math.abs(device.getDeviceId().hashCode()) % 100 < percentage;
    }
}
