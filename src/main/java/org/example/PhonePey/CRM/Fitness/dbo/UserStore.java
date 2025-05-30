package org.example.PhonePey.CRM.Fitness.dbo;

import org.example.PhonePey.CRM.Fitness.model.User;

import java.util.*;

public class UserStore {
    private static final UserStore instance = new UserStore();
    private final Map<UUID, User> users = new HashMap<>();

    public static UserStore getInstance() { return instance; }
    public void save(User user) { users.put(user.getUserId(), user); }
    public User getById(UUID id) { return users.get(id); }
}
