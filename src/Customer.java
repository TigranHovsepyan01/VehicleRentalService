public class Customer {
    private String name;
    private String email;
    private int licenseNumber;

    public Customer(String name, String email, int licenseNumber) {
        this.name = name;
        this.email = email;
        this.licenseNumber = licenseNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    @Override
    public String toString() {
        return "name: " + name + "email" + email + "licenseNumber" + licenseNumber;
    }
}
