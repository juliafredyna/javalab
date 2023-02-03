package org.example.labs.model;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import java.util.Set;

/**
 * Truck(Вантажна машина) успадкований клас від абстрактного класу RacingVehicle
 * поле liftingCapacity - вантажопідйомність
 */
public class Truck extends RacingVehicle {

    @Max(value = 24000, message = "{Max.weight}")
    private double liftingCapacity;

    public Truck(String producer, String aClass, double weight, Driver driver, double cofForFuel, double liftingCapacity) {
        super(producer, aClass, weight, driver, cofForFuel);
        this.liftingCapacity = liftingCapacity;
    }

    public static class Builder extends RacingVehicle.Builder<Builder> {

        private double liftingCapacity;

        public Builder() {
        }

        public Builder liftingCapacity(double liftingCapacity) {
            this.liftingCapacity = liftingCapacity;
            return  this;
        }

        public Truck build() {
            return validate();
        }

        private Truck validate() throws IllegalArgumentException {

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Truck truck = new Truck(this);

            Set<ConstraintViolation<Truck>> violations = validator.validate(truck);

            StringBuilder mb = new StringBuilder();

            for (ConstraintViolation<Truck> violation : violations) {
                mb.append("Error for field " + violation.getPropertyPath() + ": '"+ violation.getInvalidValue() + " " + violation.getMessage()).append("\n");
            }

            if (mb.length() > 0) {
                throw new IllegalArgumentException(mb.toString());
            }
            return truck;
        }
    }

    protected  Truck(Builder builder){
        super(builder);
        this.liftingCapacity = builder.liftingCapacity;
    }

    public double getLiftingCapacity() {
        return liftingCapacity;
    }

    public void setLiftingCapacity(double liftingCapacity) {
        this.liftingCapacity = liftingCapacity;
    }

    @Override
    public String typeOfFuel() {
     return "Дизель";
    }

    @Override
    public String toString() {
        return "Truck{" +
                "liftingCapacity=" + liftingCapacity +
                 super.toString() + "} ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Truck truck)) return false;

        if(!super.equals(o)) return false;
        return Double.compare(truck.liftingCapacity, liftingCapacity) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(liftingCapacity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
