package org.example.PhonePey.CRM.Fitness.Service;
import org.example.PhonePey.CRM.Fitness.model.FitnessClass;

import java.util.UUID;


public interface WaitlistStrategy {
    UUID getNextUser(FitnessClass fc);
}