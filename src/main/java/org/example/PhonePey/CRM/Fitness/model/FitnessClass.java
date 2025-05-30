package org.example.PhonePey.CRM.Fitness.model;

import java.time.LocalDateTime;
import java.util.*;

public class FitnessClass {
    private final UUID classId;
    private final String name;
    private final LocalDateTime startTime;
    private final int capacity;
    private final Set<UUID> bookedUsers = new HashSet<>();
    private final Queue<UUID> waitlist = new LinkedList<>();

    public FitnessClass(String name, LocalDateTime startTime, int capacity) {
        this.classId = UUID.randomUUID();
        this.name = name;
        this.startTime = startTime;
        this.capacity = capacity;
    }

    public UUID getClassId() { return classId; }
    public String getName() { return name; }
    public LocalDateTime getStartTime() { return startTime; }
    public int getCapacity() { return capacity; }
    public Set<UUID> getBookedUsers() { return bookedUsers; }
    public Queue<UUID> getWaitlist() { return waitlist; }

    public boolean isFull() {
        return bookedUsers.size() >= capacity;
    }
}
