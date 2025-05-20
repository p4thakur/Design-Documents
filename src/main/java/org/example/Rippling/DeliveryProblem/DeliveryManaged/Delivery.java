package org.example.Rippling.DeliveryProblem.DeliveryManaged;

public class Delivery {

    private long start;
    private long end;

    public Delivery(long start, long end){
                this.start=start;
                this.end=end;

            }


      public long getDuration(){
        return this.end-this.start;
      }

    public long getDurationBeforGivenTim(long time){
        return  this.end<=time ? this.end-this.start : 0;
    }

}
