package com.abcrental.model;

import com.abcrental.shared.CarType;

public class SUV extends Car {


    public SUV(String carNumberPlate) {
        super(carNumberPlate);
        this.carType = CarType.SUV;

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
