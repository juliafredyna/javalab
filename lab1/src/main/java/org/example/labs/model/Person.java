package org.example.labs.model;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.AssertTrue;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Person - описує людину. Поля fullName, yearOfBirth, insurance - чи застрахований
 */
public class Person implements Serializable {
    @NotNull
    private UUID id;

    @Size(min=1, max = 15, message = "{Size.fullName}")
    private String fullName;

    @Max(value = 2022, message = "{Max.yearOfBirth}")
    private int yearOfBirth;
    @AssertTrue
    private boolean insurance;

    public Person() {
    }

    public Person(String fullName, int yearOfBirth, boolean insurance) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.insurance = insurance;
    }

    public static class Builder<T extends Person.Builder<T>> {
        private UUID id;
        private String fullName;
        private int yearOfBirth;
        private boolean insurance;

        public Builder() {}

        public T id(UUID id) {
            this.id = id;
            return (T) this;
        }
        public T fullName(String fullName) {
            this.fullName = fullName;
            return (T) this;
        }

        public T yearOfBirth(int yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
            return (T) this;
        }

        public T insurance(boolean insurance) {
            this.insurance = insurance;
            return (T) this;
        }

        public Person validate(Person obj) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Person>> violations = validator.validate(obj);
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

    protected Person(Builder<?> builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.yearOfBirth = builder.yearOfBirth;
        this.insurance = builder.insurance;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    @Override
    public String toString() {
        return " Person{"
                + "id=" + id + '\n'
                + "fullName='" + fullName + '\''
                + ", yearOfBirth=" + yearOfBirth
                + ", insurance=" + insurance
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;

        if (yearOfBirth != person.yearOfBirth) return false;
        if (insurance != person.insurance) return false;
        return fullName.equals(person.fullName);
    }

    @Override
    public int hashCode() {
        int result = fullName.hashCode();
        result = 31 * result + yearOfBirth;
        result = 31 * result + (insurance ? 1 : 0);
        return result;
    }
}
