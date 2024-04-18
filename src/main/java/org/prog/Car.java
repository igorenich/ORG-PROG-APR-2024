package org.prog;

public class Car implements IMovable {

    public String color;
    public String plateNumber;

    public void goTo() {
        System.out.println(color + " car goes somewhere");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Car) {
            if (this.color == null || this.plateNumber == null) {
                throw new RuntimeException("Car color OR plate number is empty!");
            }
            boolean match = this.color.equals(((Car) obj).color)
                    && this.plateNumber.equals(((Car) obj).plateNumber);
            return match;
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (this.color == null || this.plateNumber == null) {
            throw new RuntimeException("Car color OR plate number is empty!");
        }
        return (this.color + this.plateNumber).hashCode();
    }

    @Override
    public String toString() {
        return "This car's color is " + color;
    }
}

