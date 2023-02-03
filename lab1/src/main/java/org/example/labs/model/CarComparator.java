package org.example.labs.model;

import java.util.Comparator;

public class CarComparator implements Comparator<Car> {
    @Override
    public int compare(Car car1, Car car2) {
        return Double.compare(car1.getSpeed(), car2.getSpeed());
    }
}
