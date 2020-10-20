package com.abcrental.model;

import com.abcrental.shared.CarType;

public class Van extends Car {


    public Van(String carNumberPlate) {
        super(carNumberPlate);
        this.carType = CarType.VAN;
        this.maxPassengers = 7;
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
