import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleInventoryManagement {
    List<Vehicle> vehiclesList = new ArrayList<>();
    public void addVehicle(Vehicle vehicle) {
        vehiclesList.add(vehicle);
    }
    public List<Vehicle> getAllVehicles() {
        return vehiclesList;
    }
    public Vehicle findVehicleById(int id) {
        for (Vehicle vehicle : vehiclesList) {
            if (vehicle.getId() == id) {
                return vehicle;
            }
        }
        return null;
    }
    public void deleteVehicle(Vehicle vehicle) {
        vehiclesList.remove(vehicle);
    }

}
