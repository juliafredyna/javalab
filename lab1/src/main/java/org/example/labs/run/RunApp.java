package org.example.labs.run;

import org.example.labs.model.*;
import org.example.labs.serialize.JsonMapper;
import org.example.labs.serialize.TxtMapper;
import org.example.labs.serialize.XmlMapper;
import service.CarService;

import java.util.ArrayList;
import java.util.List;

public class RunApp {
    private CarService carService;

    public RunApp() {
        carService = new CarService();
    }

    public static void main(String[] args) {
        testRun();
        //new RunApp().demoServices();
        //new RunApp().demoValidation();
    }



    private void demoValidation() {
        try {
            Driver bmwDriver = new Driver.Builder()
                    .fullName("Гак Антон")
                    .yearOfBirth(1985)
                    .insurance(true)
                    .driverLicenseYear(15)
                    .build();

            Car car = new Car.Builder()
                    .brand("BMW")
                    .carClass("C")
                    .weight(900)
                    .driver(bmwDriver)
                    .cofForFuel(0.01)
                    .speed(220)
                    .build();

            System.out.println(car);

            Driver TruckDriver = new Driver.Builder()
                    .fullName("Перхоть Іван")
                    .yearOfBirth(1966)
                    .insurance(true)
                    .driverLicenseYear(30)
                    .build();

            Truck truck = new Truck.Builder()
                    .brand("ScaniaScaniaScaniaScaniaScania")
                    .carClass("D")
                    .weight(8000)
                    .driver(TruckDriver)
                    .cofForFuel(0.1)
                    .liftingCapacity(10000)
                    .build();

            System.out.println(truck);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void demoServices() {
        System.out.println("Demo carService.getAll() =====================");
        List<Car> cars = carService.findAll();
        cars.forEach(System.out::println);

        System.out.println("\nDemo carService.findByBrand('BMW') =====================");
        carService.findByBrand("BMW").forEach(System.out::println);

        System.out.println("\nDemo carService.sort(all cars) sort by brand and after weight =====================");
        carService.sort().forEach(System.out::println);

        System.out.println("\nDemo sort by Comparator(all cars) sort by speed and after weight =====================");
        CarComparator carComparator = new CarComparator();
        cars.sort(carComparator);
        cars.forEach(System.out::println);
    }


    private static void testRun() {
        Driver bmwDriver = new Driver.Builder()
                .fullName("Гак Антон")
                .yearOfBirth(1985)
                .insurance(true)
                .driverLicenseYear(15)
                .build();

        Car car = new Car.Builder()
                .brand("BMW")
                .carClass("C")
                .weight(2000)
                .driver(bmwDriver)
                .cofForFuel(0.007)
                .speed(220)
                .build();

        Driver TruckDriver = new Driver.Builder()
                .fullName("Перхоть Іван")
                .yearOfBirth(1966)
                .insurance(false)
                .driverLicenseYear(30)
                .build();

        Truck truck = new Truck.Builder()
                .brand("ScaniaScaniaScaniaScaniaScaniaScaniaScania")
                .carClass("B")
                .weight(4000)
                .driver(TruckDriver)
                .cofForFuel(0.025)
                .liftingCapacity(7000)
                .build();

        List<RacingVehicle> racingVehicles = new ArrayList() {
            {
                add(car);
                add(truck);
            }
        };

        Driver MotoDriver = new Driver.Builder()
                .fullName("Шевченко Валодя")
                .yearOfBirth(1977)
                .insurance(false)
                .driverLicenseYear(25)
                .build();

        Motorcycle motorcycle = new Motorcycle.Builder()
                .brand("Yamaha")
                .carClass("A")
                .weight(200)
                .driver(MotoDriver)
                .cofForFuel(0.02)
                .seats(2)
                .build();

        racingVehicles.add(motorcycle);

        for (RacingVehicle racingVehicle : racingVehicles) {
            System.out.println(racingVehicle);
            System.out.println(racingVehicle.typeOfFuel());
            System.out.println("Розхід палива: " + racingVehicle.fuelConsumption() + "\n");
        }

        Car car2 = car;
        System.out.println("Equals = " + car.equals(car2));

        boolean hash = car.hashCode() == car2.hashCode();
        System.out.println("HashCodes are equal = " + hash);

        new JsonMapper<Motorcycle>().writeObject("motorcycle.json", motorcycle);
        new XmlMapper<Motorcycle>().writeObject("motorcycle.xml", motorcycle);
        new TxtMapper<Motorcycle>().writeObject("motorcycle.txt", motorcycle);
    }
}