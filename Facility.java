public abstract class Facility extends Campus_Entity{
    protected double maintenanceCost;
    protected int operatingHours, capacity;

    public Facility(String entityID, String location, String name, double maintenanceCost, int operatingHours, int capacity) {
        super(entityID, location, name);
        setMaintenanceCost(maintenanceCost);
        setOperatingHours(operatingHours);
        setCapacity(capacity);
    }

    public void setMaintenanceCost(double maintenanceCost) {
        if(maintenanceCost < 0){
            System.err.println("Maintenance cost cannot be negative.");
        }
        this.maintenanceCost = maintenanceCost;
    }

    public void setOperatingHours(int operatingHours) {
        if(operatingHours < 0){
            System.err.println("Operating hours cannot be negative.");
        }
        this.operatingHours = operatingHours;
    }

    public void setCapacity(int capacity) {
        if(capacity < 0){
            System.err.println("Capacity cannot be negative.");
        }
        this.capacity = capacity;
    }

    public double getMaintenanceCost() {
        return maintenanceCost;
    }

    public int getOperatingHours() {
        return operatingHours;
    }

    public int getCapacity() {
        return capacity;
    }

    public abstract double CalculateOperationalCost();

    @Override
    public String toString() {
        return super.toString() + ("MaintenanceCost:" + maintenanceCost + "OperatingHours:" + operatingHours + "Capacity:" + capacity);
    }

}//end of Facility class

class Library extends Facility{
    
}