package org.example.labs.repository;

import org.example.labs.model.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarRepository {
    private final DemoData demoData = new DemoData();
    private List<Car> cars;

    public CarRepository() {
        demoData.createDemoData();
        cars = demoData.getCars();
    }

    public List<Car> findAll() {
        return cars;
    }

    public List<Car> findByBrand(String brand) {//stream API
        return cars.stream()
                .filter(c -> c.getBrand().equals(brand))
                .collect(Collectors.toList());
    }

    public List<Car> findByWeight(double weight) {
        return cars.stream()
                .filter(c -> c.getWeight()>weight)
                .collect(Collectors.toList());
    }
}
