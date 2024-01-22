package vehicles;

public abstract class Vehicle {
    private static int idGeneration = 1;
    private int id = idGeneration++;
    public abstract VehicleType getType();

    public Vehicle() {

    }
    public int getId() {
        return id;
    }


}
