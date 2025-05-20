package org.example.Rippling.DeliveryProblem.DeliveryManaged;

import org.example.Rippling.DeliveryProblem.DriverManager;

public class Main {

      public static void main(String[]  arg){
          DeliveryManager  manager = new DeliveryManager();
          manager.addDriver("1", 100.0);
          manager.addDelivery("1",1_000_1800, 1_000_3600);//30 min
          manager.addDelivery("1",1_000_3600, 1_000_7200);//60 min
          manager.addDelivery("1",1_000_1800, 1_000_3600);//30 min
          manager.addDriver("2", 200.0);
          manager.addDelivery("2",1_000_3600, 1_000_7200);//30 minute
          Double cost =manager.getTotalCost();
          System.out.println("cost "+ cost);
          System.out.println("Driver 1 Cost " +manager.getTotalCostByDriver("1"));
          System.out.println("Driver 2 Cost " +manager.getTotalCostByDriver("2"));

          System.out.println("Cost with end time till 3600 "+ manager.getTotalCostByTime(1_000_3600)) ;
      }

}
