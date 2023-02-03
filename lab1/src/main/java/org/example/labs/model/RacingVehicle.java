package org.example.labs.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * абстрактний клас RacingVehicle(транспортний засіб)
 * містить поля brand - марка засобу, carClass - клас автомобіля,
 * weight - маса засобу, driver - водій,
 * абстратні функції typeOfFuel - тип палива, звичайна функція fuelConsumption - розхід палива
 */
public abstract class RacingVehicle implements Serializable {
    @Size(min=1, max = 15, message = "{Size.brand}")
    private String brand; //TODO: protected

    @NotNull
    private String carClass;

    @Min(value = 100, message = "{Min.weight}")
    @Max(value = 10000, message = "{Max.weight}")
    private double weight;

    private Driver driver;

    @Max(value = 1, message = "{Max.cofForFuel}")
    private double cofForFuel;

    public RacingVehicle() {
    }

    public RacingVehicle(String brand, String carClass, double weight, Driver driver, double cofForFuel) {
        this.brand = brand;
        this.carClass = carClass;
        this.weight = weight;
        this.driver = driver;
        this.cofForFuel = cofForFuel;
    }

    public RacingVehicle(RacingVehicle ob) {
        this.brand = ob.brand;
        this.carClass = ob.carClass;
        this.weight = ob.weight;
        this.driver = ob.driver;
        this.cofForFuel = ob.cofForFuel;
    }

    public static class Builder<T extends Builder<T>> {
        private String brand;
        private String carClass;
        private double weight;
        private Driver driver;

        private double cofForFuel;

        public Builder() {
        }

        public T brand(String brand) {
            this.brand = brand;
            return (T) this;
        }

        public T carClass(String carClass) {
            this.carClass = carClass;
            return (T) this;
        }

        public T weight(double weight) {
            this.weight = weight;
            return (T) this;
        }

        public T driver(Driver driver) {
            this.driver = driver;
            return (T) this;
        }

        public T cofForFuel(double cofForFuel) {
            this.cofForFuel = cofForFuel;
            return (T) this;
        }


        public RacingVehicle validate(RacingVehicle obj) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<RacingVehicle>> violations = validator.validate(obj);
            if (violations.isEmpty()) {
                return obj;
            } else {
                StringBuilder sb = new StringBuilder();
                for (var violation : violations) {
                    sb.append(violation.getInvalidValue()).append(" : ").append(violation.getMessage());
                }
                return null;
            }
        }

    }

    protected RacingVehicle(Builder<?> builder) {
        this.brand = builder.brand;
        this.carClass = builder.carClass;
        this.weight = builder.weight;
        this.driver = builder.driver;
        this.cofForFuel = builder.cofForFuel;
    }

    public abstract String typeOfFuel();

    public double fuelConsumption() {
        return getWeight() * cofForFuel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setAClass(String aClass) {
        this.carClass = aClass;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public double getCofForFuel() {
        return cofForFuel;
    }

    public void setCofForFuel(double cofForFuel) {
        this.cofForFuel = cofForFuel;
    }

    @Override
    public String toString() {
        return "RacingVehicle{" +
                "brand='" + brand + '\'' +
                ", carClass='" + carClass + '\'' +
                ", weight=" + weight +
                ", driver=" + driver +
                ", cofForFuel=" + cofForFuel +
                '}';
    }

//    @Override
//    public String toString() {
//        return " VehicleData{" +
//                "producer='" + brand + '\'' +
//                ", aClass='" + carClass + '\'' +
//                ", weight=" + weight +
//                ", driver=" + driver +
//                ", coefficient for fuel=" + cofForFuel +
//                '}';
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RacingVehicle racingVehicle)) return false;

        if (Double.compare(racingVehicle.weight, weight) != 0) return false;
        if (Double.compare(racingVehicle.cofForFuel, cofForFuel) != 0) return false;
        if (!Objects.equals(brand, racingVehicle.brand)) return false;
        if (!Objects.equals(carClass, racingVehicle.carClass)) return false;
        return Objects.equals(driver, racingVehicle.driver);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = brand.hashCode();
        result = 31 * result + carClass.hashCode();
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + driver.hashCode();
        temp = Double.doubleToLongBits(cofForFuel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
