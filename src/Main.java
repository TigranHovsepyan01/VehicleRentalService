import vehicles.Vehicle;

public class Main {
    public static void main(String[] args) {
        RentalCars rentalCars = new RentalCars();
        VehicleInventoryManagement inventoryManagement = new VehicleInventoryManagement();
        Operator operator = new Operator(rentalCars, inventoryManagement);
        operator.startProgram();
    }
}