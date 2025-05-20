package org.example.Rippling.DeliveryProblem.DeliveryManaged;

import java.util.HashMap;
import java.util.Map;

public class DeliveryManager {
    Map<String, Driver> driverMap;
    int totalCost=0;
    public DeliveryManager() {
        this.driverMap = new HashMap<>();
    }

    public void addDriver(String id, Double hourlyRate){
        if(driverMap.containsKey(id)){
            throw new IllegalArgumentException("Duplcaite id");
        }
        driverMap.put(id, new Driver(id, hourlyRate));

    }

    public void addDelivery(String driverid, long start, long end){
        if(!driverMap.containsKey(driverid)){
            throw new IllegalArgumentException("Duplcaite id");
        }

         driverMap.get(driverid).addDelivery(start, end);

    }

    //get total cost for each of the driver
    public Double getTotalCost(){
        Double cost=0.0;
        for(Driver driver :driverMap.values()){
            cost+=driver.getTotalCost();        }
        return  cost;
    }
    public Double getTotalCostByDriver(String driverId){
        Double cost=0.0;
        if(!driverMap.containsKey(driverId)){
            throw new IllegalArgumentException("No  Id preseent");
        }
        return driverMap.get(driverId).getTotalCost();
        //return  cost;
    }

    public Double getTotalCostByTime(long time){
        Double cost=0.0;
        for(Driver driver :driverMap.values()){
            cost+=driver.getTotalCostByTime(time);        }
        return  cost;
    }

}
