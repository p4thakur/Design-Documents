package org.example.PhonePey.CRM.Fitness.Service;

import org.example.PhonePey.CRM.Fitness.model.FitnessClass;

import java.util.UUID;

public class FIFOWaitlistStrategy implements WaitlistStrategy {
    public UUID getNextUser(FitnessClass fc) {
        return fc.getWaitlist().poll();
    }
}