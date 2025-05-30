package org.example.PhonePey.CRM.Fitness.Service;

import org.example.PhonePey.CRM.Fitness.dbo.ClassStore;
import org.example.PhonePey.CRM.Fitness.model.FitnessClass;

import java.time.LocalDateTime;

public class ClassService {
    private final ClassStore store = ClassStore.getInstance();

    public FitnessClass createClass(String name, LocalDateTime startTime, int capacity) {
        FitnessClass fc = new FitnessClass(name, startTime, capacity);
        store.save(fc);
        return fc;
    }
}
