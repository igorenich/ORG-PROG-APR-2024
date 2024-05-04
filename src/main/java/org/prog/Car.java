package org.prog;

public class Car implements IMovable {

    public String color;
    public String plateNumber;
    public void goTo() {
        System.out.println(color + " car goes somewhere");
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Car)
        {
            Car car = (Car) obj;
            if (this.color == null || this.plateNumber == null || car.color == null || car.plateNumber == null)
            {
                throw new RuntimeException("Color and Plate Number cannot be null");
            }
            return this.color.equals(car.color) && this.plateNumber.equals(car.plateNumber);
        }
        return false;
    }


    @Override
    public int hashCode()
    {
        if (this.color == null || this.plateNumber == null) {
        throw new RuntimeException("Color and Plate Number cannot be null");
    }
        return this.color.hashCode() + this.plateNumber.hashCode();
    }

    @Override
    public String toString()
    {
        return "This car's color is " + color + " and plate number is " + plateNumber;
    }
}

