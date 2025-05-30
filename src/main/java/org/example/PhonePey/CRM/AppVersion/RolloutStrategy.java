package org.example.PhonePey.CRM.AppVersion;

public interface RolloutStrategy {
    boolean shouldRolloutTo(Device device, AppVersion version);
}
