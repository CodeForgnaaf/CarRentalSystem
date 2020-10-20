package com.abcrental.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.abcrental.inventory.CarRentalFactory;
import com.abcrental.inventory.Inventory;
import com.abcrental.model.Car;
import com.abcrental.shared.CarType;
import com.abcrental.shared.Util;

public class RunMe {

    public static void main(final String[] args) throws IOException {
        System.out.println("Welcome to ABC Car Rental System");

        //---------------------------- LOADING INVENTORY ------------------------------------------//
        final CarRentalFactory carRentalFactory = new CarRentalFactory();
        final Inventory bostonABCInventory = new Inventory();
        ///Filling in default Inventory and current Bookings as of today
        final Car sedan1 = carRentalFactory.createNewCar(CarType.SEDAN,"MASEDAN-1");
        sedan1.addNewBooking(Util.convertStringDateToMillis("2020/11/01 12:00:00"),Util.convertStringDateToMillis("2020/11/02 12:00:00"));
        sedan1.addNewBooking(Util.convertStringDateToMillis("2020/11/03 00:00:00"),Util.convertStringDateToMillis("2020/11/04 00:00:00"));
        sedan1.addNewBooking(Util.convertStringDateToMillis("2020/11/04 12:00:00"),Util.convertStringDateToMillis("2020/11/08 12:00:00"));
        bostonABCInventory.addCarToInventory(CarType.SEDAN,sedan1);

        final Car sedan2 = carRentalFactory.createNewCar(CarType.SEDAN,"MASEDAN-2");
        sedan2.addNewBooking(Util.convertStringDateToMillis("2020/11/01 12:00:00"),Util.convertStringDateToMillis("2020/11/01 13:00:00"));
        sedan2.addNewBooking(Util.convertStringDateToMillis("2020/11/01 14:00:00"),Util.convertStringDateToMillis("2020/11/01 15:30:00"));
        sedan2.addNewBooking(Util.convertStringDateToMillis("2020/11/01 15:45:00"),Util.convertStringDateToMillis("2020/11/01 16:45:00"));
        bostonABCInventory.addCarToInventory(CarType.SEDAN,sedan2);

        final Car SUV1 = carRentalFactory.createNewCar(CarType.SUV,"MASUV-1");
        bostonABCInventory.addCarToInventory(CarType.SUV,SUV1);

        //---------------------------- PROCESSING USER INPUT ------------------------------------------//
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your Car Type SEDAN/VAN/SUV : ");
        String carType = reader.readLine();
        System.out.print("Enter StartDateTime in format yyyy/MM/dd HH:mm:ss : ");
        String startDate = reader.readLine();
        System.out.print("Enter number of days : ");
        Integer days = Integer.valueOf(reader.readLine());

        //---------------------------- COMPUTING RESULT ------------------------------------------//
        final Boolean result = bostonABCInventory.reserveCar(CarType.valueOf(carType),startDate,days);
        if(result){
            System.out.println(carType + " has been booked successfully");
        }
        else{
            System.out.println("No cars found for your time range and Car type");
        }
    }

}


