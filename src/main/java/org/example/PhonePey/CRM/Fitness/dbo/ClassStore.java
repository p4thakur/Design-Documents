package org.example.PhonePey.CRM.Fitness.dbo;

import org.example.PhonePey.CRM.Fitness.model.FitnessClass;

import java.util.*;

public class ClassStore {
    private static final ClassStore instance = new ClassStore();
    private final Map<UUID, FitnessClass> classes = new HashMap<>();

    public static ClassStore getInstance() { return instance; }
    public void save(FitnessClass fc) { classes.put(fc.getClassId(), fc); }
    public FitnessClass getById(UUID id) { return classes.get(id); }
    public Collection<FitnessClass> all() { return classes.values(); }
}