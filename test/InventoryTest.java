import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abcrental.inventory.CarRentalFactory;
import com.abcrental.inventory.Inventory;
import com.abcrental.model.Car;
import com.abcrental.shared.CarType;
import com.abcrental.shared.Util;

public class InventoryTest {

    final Inventory bostonABCInventory = new Inventory();

    @BeforeEach
    void setUp() {
        final CarRentalFactory carRentalFactory = new CarRentalFactory();

        //Defining Sedans
        final Car sedan1 = carRentalFactory.createNewCar(CarType.SEDAN,"MASEDAN-1");
        sedan1.addNewBooking(Util.convertStringDateToMillis("2020/11/01 12:00:00"),Util.convertStringDateToMillis("2020/11/02 12:00:00"));
        sedan1.addNewBooking(Util.convertStringDateToMillis("2020/11/04 00:00:00"),Util.convertStringDateToMillis("2020/11/04 02:00:00"));
        sedan1.addNewBooking(Util.convertStringDateToMillis("2020/11/04 12:00:00"),Util.convertStringDateToMillis("2020/11/08 12:00:00"));
        bostonABCInventory.addCarToInventory(CarType.SEDAN,sedan1);
        final Car sedan2 = carRentalFactory.createNewCar(CarType.SEDAN,"MASEDAN-2");
        sedan2.addNewBooking(Util.convertStringDateToMillis("2020/11/01 12:00:00"),Util.convertStringDateToMillis("2020/11/01 13:00:00"));
        sedan2.addNewBooking(Util.convertStringDateToMillis("2020/11/01 14:00:00"),Util.convertStringDateToMillis("2020/11/01 15:30:00"));
        sedan2.addNewBooking(Util.convertStringDateToMillis("2020/11/01 15:45:00"),Util.convertStringDateToMillis("2020/11/01 16:45:00"));
        sedan2.addNewBooking(Util.convertStringDateToMillis("2020/11/02 11:15:00"),Util.convertStringDateToMillis("2020/11/02 12:00:00"));
        bostonABCInventory.addCarToInventory(CarType.SEDAN,sedan2);

        //Defining SUVS
        final Car SUV1 = carRentalFactory.createNewCar(CarType.SUV,"MASUV-1");
        bostonABCInventory.addCarToInventory(CarType.SUV,SUV1);
    }

    @Test
    public void reserveCarUnavailableStartTimeSameAsExisting() {
        final Boolean result = bostonABCInventory.reserveCar(CarType.SEDAN,"2020/11/01 12:00:00",1);
        assertFalse(result);
    }

    @Test
    public void reserveCarUnavailableEndTimeOverlapping() {
        final Boolean result = bostonABCInventory.reserveCar(CarType.SEDAN,"2020/11/02 12:00:00",1);
        assertFalse(result);
    }

    @Test
    public void reserveCarAvailable() {
        final Boolean result = bostonABCInventory.reserveCar(CarType.SEDAN,"2020/11/02 15:00:00",1);
        assertTrue(result);
    }

    @Test
    public void reserveCarSecondSedanAvailable() {
        final Boolean result = bostonABCInventory.reserveCar(CarType.SEDAN,"2020/11/04 13:10:00",4);
        assertTrue(result);
    }
    @Test
    public void reserveCarNotInInventory() {
        final Boolean result = bostonABCInventory.reserveCar(CarType.VAN,"2020/11/01 12:00:00",1);
        assertFalse(result);
    }

    @Test
    public void reserveCarFirstBooking() {
        final Boolean result = bostonABCInventory.reserveCar(CarType.SEDAN,"2020/10/31 11:59:00","2020/10/31 19:59:00");
        assertTrue(result);
    }

    @Test
    public void reserveCarNoBookingsYet() {
        final Boolean result = bostonABCInventory.reserveCar(CarType.SUV,"2020/11/02 12:01:00",1);
        assertTrue(result);
    }

}
