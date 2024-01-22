import vehicles.Bike;
import vehicles.Car;
import vehicles.Van;
import vehicles.Vehicle;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Operator {
    private RentalCars rentalCars;
    private List<RentalProces> bookings;
    private VehicleInventoryManagement inventoryManagement;
    private List<Customer> customers;
    private List<Vehicle> vehicles;

    public Operator(RentalCars rentalCars, VehicleInventoryManagement inventoryManagement) {
        this.rentalCars = rentalCars;
        this.inventoryManagement = inventoryManagement;
        customers = rentalCars.getCustomers();
        vehicles = rentalCars.getVehicles();

    }

    public void startProgram() {
        System.out.println("Rental Management System Menu: ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String massage = """
                    1. Add Vehicle
                    2. Delete Vehicle
                    3. Rent Vehicle
                    4. Return Vehicle
                    5. Register Customer
                    6. Save State to File
                    7. Load State from File
                    8. Generate Reports
                    9. Exit
                    """;
            System.out.println(massage);
            String operation = scanner.nextLine();
            switch (operation) {
                case "1" -> addVehicle();
                case "2" -> deleteVehicle();
                case "3" -> rentVehicle();
                case "4" -> returnVehicle();
                case "5" -> addCustomer();
                case "6" -> saveStateToFile();
                case "7" -> loadStateFromFile();
                case "8" -> generateReports();
                case "10" -> exitProgram();
                default -> System.out.println("WARNING - " + operation + "is not correct ");
            }
        }
    }

    private void generateReports() {
        System.out.println("Generating reports:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select report type:");
        System.out.println("1. Customer Rental History");
        System.out.println("2. Vehicle Booking History");
        int reportType = scanner.nextInt();
        scanner.nextLine();

        switch (reportType) {
            case 1:
                generateCustomerRentalHistoryReport();
                break;
            case 2:
                generateVehicleBookingHistoryReport();
                break;
            default:
                System.out.println("Invalid report type selected.");
        }
    }
    private void generateVehicleBookingHistoryReport() {
        System.out.println("Generating Vehicle Booking History Report:");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter vehicle ID: ");
        int vehicleId = scanner.nextInt();
        scanner.nextLine();

        Optional<Vehicle> vehicle = vehicles.stream()
                .filter(v -> v.getId() == vehicleId)
                .findAny();

        if (vehicle.isPresent()) {
            List<RentalProces> vehicleBookings = bookings.stream()
                    .filter(booking -> booking.getVehicle().equals(vehicle.get()))
                    .collect(Collectors.toList());

            saveReportToFile("VehicleBookingHistoryReport.txt", generateBookingHistoryReport(vehicleBookings));
            System.out.println("Report generated successfully.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private String generateBookingHistoryReport(List<RentalProces> vehicleBookings) {
        return "Customer Rental History Report:\n" + rentalCars.toString();
    }


    private void generateCustomerRentalHistoryReport() {
        System.out.println("Generating Customer Rental History Report:");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        Optional<Customer> customer = customers.stream()
                .filter(c -> c.getName().equalsIgnoreCase(customerName))
                .findAny();

        if (customer.isPresent()) {
            List<RentalProces> customerRentals = bookings.stream()
                    .filter(booking -> booking.getCustomer().equals(customer.get()))
                    .collect(Collectors.toList());

            saveReportToFile("CustomerRentalHistoryReport.txt", generateRentalHistoryReport(customerRentals));
            System.out.println("Report generated successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private String generateRentalHistoryReport(List<RentalProces> customerRentals) {
        return "Customer Rental History Report:\n" + rentalCars.toString();
    }

    private void saveReportToFile(String fileName, String report) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(report);
        } catch (IOException e) {
            System.out.println("Error saving report to file: " + e.getMessage());
        }
    }


    private void returnVehicle() {
        System.out.println("Renting a vehicle:");
        Scanner scanner = new Scanner(System.in);

        Vehicle vehicle;
        while (true) {
            String massage = """
                    Car - enter 1
                    Van - enter 2
                    Bike - enter 3
                    """;
            System.out.println(massage);
            String operation = scanner.nextLine();

            switch (operation) {
                case "1": {
                    vehicle = new Car();
                    break;
                }
                case "2": {
                    vehicle = new Van();
                    break;
                }
                case "3": {
                    vehicle = new Bike();
                    break;
                }
                default: {
                    System.out.println("WARNING " + operation + " is not correct ");
                    return;
                }
            }

            System.out.println(vehicle.toString());

        }
    }

    @SuppressWarnings("all")
    private void loadStateFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter filePath");
        String filePath = scanner.nextLine();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            customers = (List<Customer>) objectInputStream.readObject();
            vehicles = (List<Vehicle>) objectInputStream.readObject();
            bookings = (List<RentalProces>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveStateToFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter filePath");
        String filePath = scanner.nextLine();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(vehicles);
            outputStream.writeObject(customers);
            outputStream.writeObject(bookings);
            System.out.println("State saved successfully. ");
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void exitProgram() {
        System.out.println("Exiting Rental Vehicle. Goodbye!");
        System.exit(0);
    }

    private void addCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Here you can register a customer - ");
        System.out.println("Please enter the customer name - ");
        String name = scanner.nextLine();
        System.out.println("Please enter the customer email - ");
        String email = scanner.nextLine();
        System.out.println("Please enter customer license number - ");
        int licenseNumber = scanner.nextInt();
        Customer customer = new Customer(name, email, licenseNumber);
        customers.add(customer);
    }

    private void addVehicle() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String massage = """
                    Car - enter 1
                    Van - enter 2
                    Bike - enter 3
                    """;
            System.out.println(massage);
            String operation = scanner.nextLine();
            switch (operation) {
                case "1" -> {
                    rentalCars.addVehicle(new Car());
                    System.out.println("Car is added");
                    return;
                }
                case "2" -> {
                    rentalCars.addVehicle(new Van());
                    System.out.println("Van is added");
                    return;
                }
                case "3" -> {
                    rentalCars.addVehicle(new Bike());
                    System.out.println("Bike is added");
                }
                default -> System.out.println("WARNING " + operation + " is not correct ");
            }
        }
    }

    public void displayAvailableVehicles(Date startDate, Date endDate) {
        System.out.println("Available Vehicles for Rent:");

        for (Vehicle vehicle : inventoryManagement.getAllVehicles()) {
            if (isVehicleAvailable(vehicle, startDate, endDate)) {
                System.out.println(vehicle);
            }
        }
    }

    private boolean isVehicleAvailable(Vehicle vehicle, Date startDate, Date endDate) {
        for (RentalProces rentalProcess : rentalCars.bookings) {
            if (rentalProcess.getVehicle().equals(vehicle) && isOverlap(startDate, endDate, rentalProcess.getStartDate(), rentalProcess.getEndDate())) {
                return false;
            }
        }
        return true;
    }

    private void displayOfRentVehicle() {
        Scanner scanner = new Scanner(System.in);
        Vehicle vehicle = null;
        Customer customer = null;
        loop:
        while (true) {
            displayAllVehicle();
            System.out.println("Chose a vehicle by ID");
            String id = scanner.nextLine();
            try {
                vehicle = getVehicleById(Integer.parseInt(id));
            } catch (NumberFormatException e) {
                System.out.println("WRONG ID");
                break loop;
            }
            System.out.println("Chose a customer by license");
            String license = scanner.nextLine();
            try {
                getCustomerById(Integer.parseInt(license));

            } catch (NumberFormatException e) {
                System.out.println("WRONG ID");
                break loop;
            }

        }

        scanner.close();
    }

    private Customer getCustomerById(int license) throws NumberFormatException {
        for (Customer customer : customers) {
            if (customer.getLicenseNumber() == license) {
                return customer;
            }
        }
        return null;
    }

    private Vehicle getVehicleById(int id) throws NumberFormatException {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == id) {
                return vehicle;
            }
        }
        return null;
    }

    private void rentVehicle() {
        System.out.println("Renting a vehicle:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name customer - ");
        String name = scanner.nextLine();
        Optional<Customer> customer = customers.stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .findAny();
        if (customer.isEmpty()) {
            System.out.println("User name not found");
            return;
        }

        System.out.println("Enter startDate - ");
        String startDateStr = scanner.nextLine();
        System.out.println("Enter endDate - ");
        String endDateStr = scanner.nextLine();
        LocalDateTime end = null;
        LocalDateTime start = null;
        try {
            end = LocalDateTime.parse(endDateStr);
            start = LocalDateTime.parse(startDateStr);
        } catch (Exception e) {
            System.out.println("Problem of start or end date");
            return;
        }

        if (start.isAfter(end)) {
            System.out.println("start is after of end");
            return;
        }
        Vehicle vehicle;
        while (true) {
            String massage = """
                    Car - enter 1
                    Van - enter 2
                    Bike - enter 3
                    """;
            System.out.println(massage);
            String operation = scanner.nextLine();

            switch (operation) {
                case "1": {
                    vehicle = new Car();
                    System.out.println("Car is added");
                    break;
                }
                case "2": {
                    vehicle = new Van();
                    System.out.println("Van is added");
                    break;
                }
                case "3": {
                    vehicle = new Bike();
                    System.out.println("Bike is added");
                    break;
                }
                default: {
                    System.out.println("WARNING " + operation + " is not correct ");
                    return;
                }
            }

            RentalProces rentalProces = new RentalProces(vehicle, customer.get(), convertLocalDateTimeToDate(start), convertLocalDateTimeToDate(end));
            bookings.add(rentalProces);
        }

    }

    private static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        return date;
    }


    private void displayAvailableVehicle(Date startDate, Date endDate) {
        System.out.println("Available Vehicles for Rent:");

        for (Vehicle vehicle : inventoryManagement.getAllVehicles()) {
            if (isVehicleAvailable(vehicle, startDate, endDate)) {
                System.out.println(vehicle);
            }
        }
    }

    private boolean isOverlap(Date start1, Date end1, Date start2, Date end2) {
        return start1.before(end2) && end1.after(start2);
    }


    private void displayAllVehicle() {
        System.out.println("List of Vehicles in the Inventory:");
        for (Vehicle vehicle : inventoryManagement.getAllVehicles()) {
            System.out.println(vehicle);
        }
    }

    private void displayAllCustomers() {
        System.out.println("List of Vehicles in the Inventory:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }


    private void deleteVehicle() {
        System.out.println("Deleting a vehicle from the inventory:");

        displayAllVehicle();
        System.out.print("Enter the ID of the vehicle to delete: ");
        Scanner scanner = new Scanner(System.in);
        int vehicleId = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicleToDelete = inventoryManagement.findVehicleById(vehicleId);

        System.out.println("Are you sure you want to delete the following vehicle?");
        System.out.println(vehicleToDelete);
        System.out.print("Enter 'yes' to confirm: ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            inventoryManagement.deleteVehicle(vehicleToDelete);
            System.out.println("Vehicle deleted successfully.");
        } else {
            System.out.println("Deletion canceled.");
        }

    }
}
