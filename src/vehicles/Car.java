package vehicles;

public class Car extends Vehicle {
    private final double rentalPricePerDay = 50;

    @Override
    public VehicleType getType() {
        return VehicleType.CAR;
    }

   public Car() {

   }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }
    @Override
    public String toString() {
        return "ID - " + getId() + ": VehicleType - " + getType();
    }
}
