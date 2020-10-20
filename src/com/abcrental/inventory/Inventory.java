package com.abcrental.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.abcrental.model.Car;
import com.abcrental.shared.CarType;
import com.abcrental.shared.Util;

public class Inventory {

    final HashMap<CarType,ArrayList<Car>> currentLocationInventory = new HashMap<>();

    /***
     * This is the heart of the car reservation system.
     * Below are the steps
     * 1. Go through the inventory of the specific car type
     * 2. For each car
     *      2.a check if there is already a booking that exists at the exact same time
     *      2.b If booking does not exist get the closest range values
     *          2.b --> Events are stored in sorted order in treemap, hence the start time will be sorted.
     *          2.b --> previous event will be the floor entry of treemap. The number lowest than the current startTime
     *          2.b --> next event will be the ceiling entry of treemap. The number higher than the current startTime
     * @param carType
     * @param date
     * @param days
     * @return
     */
    public Boolean reserveCar(final CarType carType, final String date, final Integer days){

        final Long startTimeMillis = Util.convertStringDateToMillis(date);
        //Computes End Time based on Number of Days
        final Long endTimeMillis = startTimeMillis + TimeUnit.MILLISECONDS.convert(days, TimeUnit.DAYS);
        return checkInventory(carType,startTimeMillis,endTimeMillis);

    }

    public Boolean reserveCar(final CarType carType, final String startDate, final String endDate) {
        final Long startTimeMillis = Util.convertStringDateToMillis(startDate);
        final Long endTimeMillis = Util.convertStringDateToMillis(endDate);
        return checkInventory(carType,startTimeMillis,endTimeMillis);
    }
    private Boolean checkInventory(final CarType carType, final Long startTimeMillis, final Long endTimeMillis) {
         Boolean reservationSuccess = false;
         Car carAvailable = null;
        if(currentLocationInventory.get(carType) != null) {
            for (Car currentCar : currentLocationInventory.get(carType)) {
                carAvailable = currentCar;
                //Case 1 : No bookings yet for the car
                if(currentCar.getCurrentBookings().size() == 0){
                    reservationSuccess= true;
                    break;
                }
                //Need to make sure there is no existing booking starting at the same time for this car
                if (currentCar.getCurrentBookings().get(startTimeMillis) == null) {
                    // Check for overlap
                    //start1 ---- end1 ---------------- start2 ----- end2 ------start3 ---end3
                    Map.Entry<Long, Long> previousBooking = currentCar.getCurrentBookings().floorEntry(startTimeMillis);
                    Map.Entry<Long, Long> nextBooking = currentCar.getCurrentBookings().ceilingEntry(startTimeMillis);
                    ///CASE 2 : First event to be booked insert event before start1
                    if(previousBooking != null && nextBooking == null){
                        if (previousBooking.getValue() < startTimeMillis) {
                            reservationSuccess= true;
                        }
                    }
                    ///CASE 3 : Last event to be booked insert event before start3
                    else if(previousBooking == null && nextBooking != null){
                        if (endTimeMillis < nextBooking.getKey()) {
                            reservationSuccess= true;
                        }
                    }
                    // CASE 4 : booking falling between two bookings
                    //To insert an event between end1 and start2 check if end1 < startTimeMillis and endTimeMillis < start2
                    else if (previousBooking.getValue() < startTimeMillis && endTimeMillis < nextBooking.getKey()) {
                        reservationSuccess= true;
                    }
                }
            }
        }
        if(reservationSuccess && carAvailable!=null){
            carAvailable.addNewBooking(startTimeMillis, endTimeMillis);
            return true;
        }
        return reservationSuccess;
    }

    /***
     * Method to add Cars to the inventory
     * @param carType
     * @param car
     */
    public void addCarToInventory(final CarType carType, final Car car){
        if(currentLocationInventory.get(carType) == null){
            currentLocationInventory.put(carType,new ArrayList<>());
        }
        currentLocationInventory.get(carType).add(car);
    }
}
