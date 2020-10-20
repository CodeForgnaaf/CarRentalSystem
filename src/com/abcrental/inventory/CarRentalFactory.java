package com.abcrental.inventory;

import com.abcrental.model.Car;
import com.abcrental.model.SUV;
import com.abcrental.model.Sedan;
import com.abcrental.model.Van;
import com.abcrental.shared.CarType;

public class CarRentalFactory {

        public Car createNewCar(CarType carType, String numberPlate){
            if(carType == null){
                return null;
            }
            if(carType.equals(CarType.SEDAN)){
                return new Sedan(numberPlate);

            }
            if(carType.equals(CarType.SUV)){
                return new SUV(numberPlate);

            }
            if(carType.equals(CarType.VAN)){
                return new Van(numberPlate);

            }

            return null;
        }
}
