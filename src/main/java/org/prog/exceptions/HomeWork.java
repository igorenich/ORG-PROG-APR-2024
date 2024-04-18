package org.prog.exceptions;

import org.prog.Car;

public class HomeWork {

    /**
     * Add field String plateNumber to class Car;
     * Make Car's equals method check for color AND plateNumber
     * Make Car's hashCode method work for color AND plateNumber
     * <p>
     * * - if car has null color or null plate number - throw RuntimeException in equals and HashCode method
     *
     * @param args
     */

    public static void main(String[] args) {
        Car carRed = new Car();
        Car blueCar = new Car();

        carRed.color = "red";
        carRed.plateNumber = "AA0000AA";

        blueCar.color = "blue";
        blueCar.plateNumber = "BB0000AA";

        Car bobsCar = new Car();
        bobsCar.plateNumber = "AA0000AA";
        bobsCar.color = "red";

        System.out.println(carRed.equals(blueCar));
        System.out.println(carRed.equals(bobsCar));
        System.out.println(carRed == bobsCar);

        Car emptyCar = new Car();
        System.out.println(emptyCar.equals(carRed));
        System.out.println(carRed.equals(emptyCar));
    }
}
