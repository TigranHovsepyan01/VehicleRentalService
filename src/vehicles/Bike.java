package vehicles;

public class Bike extends Vehicle {
    private final double rentalPricePerDay = 10;
    @Override
    public VehicleType getType() {
        return VehicleType.BIKE;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }
    public String toString() {
        return "ID - " + getId() + ": Vehicle - " + getType();
    }
}
