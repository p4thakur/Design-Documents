package org.example.PhonePey.CRM.Fitness.Service;

import org.example.PhonePey.CRM.Fitness.model.FitnessClass;
import org.example.PhonePey.CRM.Fitness.model.User;

public class BookingEventManager {

    public void onWaitlistPromotion(User user, FitnessClass fc) {
        System.out.printf("[Event] User %s promoted from waitlist for class %s%n",
                user.getName(), fc.getName());
    }
}
