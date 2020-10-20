package com.abcrental.model;

import java.util.TreeMap;

import com.abcrental.shared.CarType;

public abstract class Car {
    protected CarType carType;
    //Many other fields carMake, color, class (economy/premium), Mileage etc can be added. Keeping it to a minimum
    protected Integer maxPassengers = 4;
    private String carNumberPlate;

    //TreeMap gives easy access to Ceiling and Floor values to find range between Start and End times. Hence storing as TreeMap
    //private TreeMap<startTimeEpoch,EndTimeEpoch> currentBookings
    private TreeMap<Long, Long> currentBookings = new TreeMap<>();

    protected Car(String carNumberPlate) {
        this.carNumberPlate = carNumberPlate;
    }


    //many Abstract Functions can be added
    abstract void carMaintenanceProcedure();

    abstract void carCleaningProcedure();


    public String getCarNumberPlate() {
        return carNumberPlate;
    }

    public TreeMap<Long, Long> getCurrentBookings() {
        return currentBookings;
    }

   public void addNewBooking(final Long startTime, final Long endTime) {
     if(endTime < startTime)
     {
      throw new IllegalArgumentException("Start Time has to be less than End Time");
     }
     this.currentBookings.put(startTime,endTime);
   }
}
