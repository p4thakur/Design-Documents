package org.example.PhonePey.CRM.AppVersion;

import java.util.Set;

public class BetaRolloutStrategy implements RolloutStrategy {
    private final Set<String> betaDevices;

    public BetaRolloutStrategy(Set<String> betaDevices) {
        this.betaDevices = betaDevices;
    }

    @Override
    public boolean shouldRolloutTo(Device device, AppVersion version) {
        return betaDevices.contains(device.getDeviceId());
    }
}
