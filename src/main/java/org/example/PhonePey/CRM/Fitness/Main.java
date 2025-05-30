package org.example.PhonePey.CRM.Fitness;

import org.example.PhonePey.CRM.Fitness.Enum.TierType;
import org.example.PhonePey.CRM.Fitness.Service.BookingService;
import org.example.PhonePey.CRM.Fitness.Service.ClassService;
import org.example.PhonePey.CRM.Fitness.Service.UserService;
import org.example.PhonePey.CRM.Fitness.model.FitnessClass;
import org.example.PhonePey.CRM.Fitness.model.User;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        ClassService classService = new ClassService();
        BookingService bookingService = new BookingService();

        User alice = userService.register("alice", TierType.GOLD);
        User bob = userService.register("bob", TierType.SILVER);

        //I can impirt this by having different class. I will do it. this doesnt look goood
        FitnessClass yoga = classService.createClass("Yoga", LocalDateTime.now().plusHours(1), 1);
        FitnessClass dance = classService.createClass("Dance", LocalDateTime.now().plusHours(2), 2);

        //I will book class if.  user have capcity. my class have capacit. and if user ddiesn't
        //book classes at same time before
        bookingService.bookClass(alice, yoga);
        bookingService.bookClass(alice, dance);
        bookingService.bookClass(bob, yoga); // Bob will go to waitlist
        //bookingService.bookClass(alice, dance);

        bookingService.userCancel(alice, yoga); // Bob gets promoted
    }
}