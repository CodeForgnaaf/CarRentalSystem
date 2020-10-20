package com.abcrental.model;

import com.abcrental.shared.CarType;

public class Sedan extends Car {


    public Sedan(String carNumberPlate) {
        super(carNumberPlate);
        this.carType = CarType.SEDAN;
    }


    //Method to show the usage of OOP
    @Override
    void carMaintenanceProcedure() {
        System.out.println("Car Maintenance Procedure for Car " + this.carType);
    }

    //Method to show the usage of OOP
    @Override
    void carCleaningProcedure() {
        System.out.println("Car Cleaning Procedure for Car " + this.carType);
    }
}
