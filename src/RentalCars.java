import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class RentalCars {
    List<Vehicle> vehicles = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();
    List<RentalProces> bookings = new ArrayList<>();

    public  List<Customer> getCustomer() {
        return customers;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
    public void addRentalProces(RentalProces rentalProces) {
        bookings.add(rentalProces);
    }

    public List<RentalProces> getBookings() {
        return bookings;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
