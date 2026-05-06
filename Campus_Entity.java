public abstract class Campus_Entity{
    protected String entityID;
    protected String name,location;

    public Campus_Entity(String entityID, String location, String name) {
        this.entityID = entityID;
        this.location = location;
        this.name = name;
    }

    public void setEntityID(String entityID) {
        this.entityID = entityID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEntityID() {
        return entityID;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public abstract double CalculateOperationalCost();

    public void DisplayInfo(){
        System.out.println("EntityID:"+entityID);
        System.out.println("Name:"+name);
        System.out.println("Location:"+location);
    }
}