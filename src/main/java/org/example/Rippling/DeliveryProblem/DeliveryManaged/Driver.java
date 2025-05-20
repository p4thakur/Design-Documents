package org.example.Rippling.DeliveryProblem.DeliveryManaged;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    String id;
    Double hourlyRate;
    List<Delivery>  deliveries;

    public Driver(String id, Double hourlyRate){
        this.id=id;
        this.hourlyRate= hourlyRate;
        deliveries= new ArrayList<>();
    }

    public void addDelivery(long start, long end){
        Delivery delivery= new Delivery(start, end);
        deliveries.add(delivery);
        //call total cast  at in global vatable. return this to manger  who can track
    }

    //public Double getCo
    // get cost of all delicery for this driver
    public Double getTotalCost(){
        Double totalCost=0.0;

        for(Delivery delivery: deliveries){
            totalCost+= ((delivery.getDuration())/3600.0)*hourlyRate;
        }

        return totalCost;
    }

    public Double getTotalCostByTime(long limitTime){
        Double totalCost=0.0;

        for(Delivery delivery: deliveries){
            totalCost+= ((delivery.getDurationBeforGivenTim(limitTime))/3600.0)*hourlyRate;
        }

        return totalCost;
    }

}
