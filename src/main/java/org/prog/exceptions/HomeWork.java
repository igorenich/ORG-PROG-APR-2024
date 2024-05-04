package org.prog.exceptions;
import org.prog.Car;
public class HomeWork {

    /**
     * Add field String plateNumber to class Car;
     * Make Car's equals method check for color AND plateNumber
     * Make Car's hashCode method work for color AND plateNumber
     *
     * * - if car has null color or null plate number - throw RuntimeException in equals and HashCode method
     *@param args
     */


    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.color = "Red";
        myCar.plateNumber = "123ABC";
        myCar.goTo();
        System.out.println(myCar.toString());
        Car anotherCar = new Car();
        anotherCar.color = "Red";
        anotherCar.plateNumber = "123ABC";
        if (myCar.equals(anotherCar)) {
            System.out.println("Cars are equal");
        } else {
            System.out.println("Cars are not equal");
        }
    }

}