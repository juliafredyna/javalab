package service;


import org.example.labs.model.Car;
import org.example.labs.repository.CarRepository;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CarService {
    private final CarRepository carRepository;

    public CarService() {
        this.carRepository = new CarRepository();
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> findByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    public List<Car> findByWeight(double weight) {
        return carRepository.findByWeight(weight);
    }

    public Set<Car> sort() {
        return new TreeSet<>(carRepository.findAll());
    }

}
