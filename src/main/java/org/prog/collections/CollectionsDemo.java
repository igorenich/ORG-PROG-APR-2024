package org.prog.collections;

import org.prog.Car;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CollectionsDemo {

    public static void main(String[] args) {
        Map<String, Set<Car>> ownedCars = new HashMap<>();

        Car car1 = new Car();//bob
        car1.color = "red";
        car1.plateNumber = "A001";
        Car car2 = new Car();//bob+alice
        car2.color = "yellow";
        car2.plateNumber = "B002";
        Car car3 = new Car();//bob
        car3.color = "orange";
        car3.plateNumber = "C002";
        Car car4 = new Car();//alice
        car4.color = "green";
        car4.plateNumber = "D001";

        Set<Car> bobCars = new HashSet<>();
        bobCars.add(car1);
        bobCars.add(car2);
        bobCars.add(car3);
        Set<Car> aliceCars = new HashSet<>();
        aliceCars.add(car2);
        aliceCars.add(car4);

        ownedCars.put("Bob", bobCars);
        ownedCars.put("Alice", aliceCars);

        Set<String> keySet = ownedCars.keySet();
        for (String key : keySet) {
            Set<Car> cars = ownedCars.get(key);
            for (Car c : cars) {
                if (c.plateNumber.endsWith("001")) {
                    System.out.println("Owner: " + key + ", Color: " + c.color);
                }
            }
        }
    }
}
