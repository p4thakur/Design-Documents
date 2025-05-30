package org.example.PhonePey.CRM.Fitness.model;

import org.example.PhonePey.CRM.Fitness.Enum.TierType;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {
    private final UUID userId;
    private final String name;
    private final TierType tier;
    private final Set<UUID> bookings = new HashSet<>();

    public User(String name, TierType tier) {
        this.userId = UUID.randomUUID();
        this.name = name;
        this.tier = tier;
    }

    public UUID getUserId() { return userId; }
    public String getName() { return name; }
    public TierType getTier() { return tier; }
    public Set<UUID> getBookings() { return bookings; }
}