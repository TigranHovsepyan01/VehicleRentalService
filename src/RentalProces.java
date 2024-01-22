import vehicles.Vehicle;

import java.util.Date;

public class RentalProces {
    private Vehicle vehicle;
    private Customer customer;
    private Date startDate;
    private Date endDate;

    public RentalProces(Vehicle vehicle, Customer customer, Date startDate, Date endDate) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }


}
