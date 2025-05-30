package org.example.PhonePey.CRM.Fitness.Service;

import org.example.PhonePey.CRM.Fitness.dbo.ClassStore;
import org.example.PhonePey.CRM.Fitness.dbo.UserStore;
import org.example.PhonePey.CRM.Fitness.model.FitnessClass;
import org.example.PhonePey.CRM.Fitness.model.User;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class BookingService {
    private final ClassStore classStore = ClassStore.getInstance();
    private final UserStore userStore = UserStore.getInstance();
    private final BookingEventManager eventManager = new BookingEventManager();
    private final WaitlistStrategy waitlistStrategy = new FIFOWaitlistStrategy();
    private final ConcurrentHashMap<UUID, Object> classMonitors = new ConcurrentHashMap<>();

    public void bookClass(User user, FitnessClass fc) {
        BookingStrategy strategy = StrategyFactory.getStrategy(user.getTier());

        if (user.getBookings().size() >= strategy.getBookingLimit()) {
            throw new RuntimeException("Booking limit exceeded for user tier");
        }

         //just check if user have already book another class at same time
        for (UUID bookedClassId : user.getBookings()) {
            FitnessClass bookedClass = classStore.getById(bookedClassId);
            if (bookedClass != null && bookedClass.getStartTime().equals(fc.getStartTime())) {
                throw new RuntimeException("User already booked another class at the same time");
            }
        }

        Object monitor = classMonitors.computeIfAbsent(fc.getClassId(), k -> new Object());
        synchronized (monitor) {
            if (fc.isFull()) {
                fc.getWaitlist().offer(user.getUserId());
            } else {
                fc.getBookedUsers().add(user.getUserId());
                user.getBookings().add(fc.getClassId());
            }
        }
    }

    public void userCancel(User user, FitnessClass fc) {
        if (fc.getStartTime().minusMinutes(30).isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Cannot cancel within 30 minutes of class start");
        }
        cancelBooking(user, fc);
    }

    public void adminCancelClass(FitnessClass fc) {
        classStore.all().removeIf(c -> c.getClassId().equals(fc.getClassId()));
    }

    private void cancelBooking(User user, FitnessClass fc) {
        Object monitor = classMonitors.computeIfAbsent(fc.getClassId(), k -> new Object());
        synchronized (monitor) {
            boolean removed = fc.getBookedUsers().remove(user.getUserId());
            if (!removed) return;

            user.getBookings().remove(fc.getClassId());

            promoteFromWaitlist(fc);
        }
    }

    private void promoteFromWaitlist(FitnessClass fc) {
        Object monitor = classMonitors.computeIfAbsent(fc.getClassId(), k -> new Object());
        synchronized (monitor) {
            UUID nextUserId = waitlistStrategy.getNextUser(fc);
            if (nextUserId == null) return;

            User nextUser = userStore.getById(nextUserId);
            for (UUID bookedClassId : nextUser.getBookings()) {
                FitnessClass bookedClass = classStore.getById(bookedClassId);
                if (bookedClass != null && bookedClass.getStartTime().equals(fc.getStartTime())) {
                    return; // skip promotion
                }
            }

            fc.getBookedUsers().add(nextUserId);
            nextUser.getBookings().add(fc.getClassId());

            eventManager.onWaitlistPromotion(nextUser, fc);
        }
    }
}

