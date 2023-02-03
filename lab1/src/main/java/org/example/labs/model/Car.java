package org.example.labs.model;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import java.util.Set;

/**
 *
 * Car успадкований клас від абстрактного класу RacingVehicle
 * поле speed - швидкість
 */
public class Car extends RacingVehicle implements Comparable<Car> {
    @Max(value = 350, message = "{Max.speed}")
    private double speed;

    public Car() {
        super();
    }

    public Car(String producer, String aClass, double weight, Driver driver, double cofForFuel, double speed) {
        super(producer, aClass, weight, driver, cofForFuel);
        this.speed = speed;
    }

    public static class Builder extends RacingVehicle.Builder<Builder> {

        private double speed;

        public Builder() {
        }

        public Builder speed(double speed) {
            this.speed = speed;
            return this;
        }

        public Car build() {
            return validate();
        }

        private Car validate() throws IllegalArgumentException {

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

           Car car = new Car(this);

            Set<ConstraintViolation<Car>> violations = validator.validate(car);

            StringBuilder mb = new StringBuilder();

            for (ConstraintViolation<Car> violation : violations) {
                mb.append("Error for field " + violation.getPropertyPath() + ": '"+ violation.getInvalidValue() + " " + violation.getMessage()).append("\n");
            }

            if (mb.length() > 0) {
                throw new IllegalArgumentException(mb.toString());
            }
            return car;
        }
    }

    protected Car(Builder builder) {
        super(builder);
        this.speed = builder.speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String typeOfFuel() {
        return "Бензин";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        if (!super.equals(o)) return false;

        return Double.compare(car.speed, speed) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(speed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "SportCar{" +
                "speed=" + speed +
                super.toString() + "} ";
    }

    @Override
    public int compareTo(Car o) {
        int result = getBrand().compareTo(o.getBrand());
        if (result == 0) {
            result = Double.compare(getWeight(), o.getWeight());
        }
        return result;
    }
}
