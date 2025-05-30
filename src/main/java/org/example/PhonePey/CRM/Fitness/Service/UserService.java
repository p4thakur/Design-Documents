package org.example.PhonePey.CRM.Fitness.Service;

import org.example.PhonePey.CRM.Fitness.Enum.TierType;
import org.example.PhonePey.CRM.Fitness.dbo.UserStore;
import org.example.PhonePey.CRM.Fitness.model.User;

public class UserService {
    private final UserStore store = UserStore.getInstance();

    public User register(String name, TierType tier) {
        User user = new User(name, tier);
        store.save(user);
        return user;
    }
}
