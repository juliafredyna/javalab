package org.example.labs.repository;

import org.example.labs.model.Driver;
import org.example.labs.model.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoData {

    private List<Car> cars = new ArrayList<>();

    public List<Car> getCars() {
        return cars;
    }

    public void createDemoData() {
        Driver carDriver = new Driver.Builder()
                .fullName("Євген Карась")
                .yearOfBirth(1985)
                .insurance(false)
                .driverLicenseYear(15)
                .build();

        Car car1 = new Car.Builder()
                .brand("BMW")
                .carClass("C")
                .weight(2000)
                .driver(carDriver)
                .cofForFuel(0.01)
                .speed(190)
                .build();

        Car car2 = new Car.Builder()
                .brand("Mercedes")
                .carClass("B")
                .weight(1800)
                .driver(carDriver)
                .cofForFuel(0.012)
                .speed(210)
                .build();

        Car car3 = new Car.Builder()
                .brand("KIA")
                .carClass("A")
                .weight(1650)
                .driver(carDriver)
                .cofForFuel(0.009)
                .speed(170)
                .build();

        cars = Arrays.asList(car1, car2, car3);
    }
}
