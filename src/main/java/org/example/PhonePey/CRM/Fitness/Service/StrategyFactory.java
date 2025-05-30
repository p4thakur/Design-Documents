package org.example.PhonePey.CRM.Fitness.Service;

import org.example.PhonePey.CRM.Fitness.Enum.TierType;

public class StrategyFactory {
    public static BookingStrategy getStrategy(TierType tier) {
         switch (tier) {
            case PLATINUM :
                return  new PlatinumBookingStrategy();
            case GOLD :
                  return new GoldBookingStrategy();
            case SILVER :
                   return new SilverBookingStrategy();
             default:
                 return  new PlatinumBookingStrategy();
              // throw  new RuntimeException("");

        }
        //return  new PlatinumBookingStrategy();
    }
}