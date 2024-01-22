package vehicles;

public class Van extends Vehicle {

    private final double rentalPricePerDay = 80;
    @Override
    public VehicleType getType() {
        return VehicleType.VAN;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }
    public String toString() {
        return "ID -" + getId() +
                ": VehicleType - " + getType();
    }
}
