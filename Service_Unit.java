public abstract class Service_Unit extends Campus_Entity{
    protected int serviceHours,staffCount;
    protected boolean isActive;

    public Service_Unit(String entityID, String location, String name, int serviceHours, int staffCount, boolean isActive) {
        super(entityID, location, name);
        setServiceHours(serviceHours);
        setStaffCount(staffCount);
        setActive(isActive);
    }

    public void setServiceHours(int serviceHours) {
        if(serviceHours < 0){
            System.err.println("Service hours cannot be negative.");
        }
        this.serviceHours = serviceHours;
    }

    public void setStaffCount(int staffCount) {
        if(staffCount < 0){
            System.err.println("Staff count cannot be negative.");
        }
        this.staffCount = staffCount;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getServiceHours() {
        return serviceHours;
    }

    public int getStaffCount() {
        return staffCount;
    }

    public abstract double CalculateOperationalCost();

    @Override
    public String toString() {
        return super.toString() + ("ServiceHours:" + serviceHours + "StaffCount:" + staffCount + "IsActive:" + isActive);
    }

}//end of Service_Unit class
class TransportService extends Service_Unit{
    protected int routeCount, driverCount, busCount;

    public TransportService(String entityID, String location, String name, int serviceHours, int staffCount, boolean isActive, int routeCount, int driverCount, int busCount) {
        super(entityID, location, name, serviceHours, staffCount, isActive);
        this.routeCount = routeCount;
        this.driverCount = driverCount;
        this.busCount = busCount;
    }

    public void setRouteCount(int routeCount) {
        this.routeCount = routeCount;
    }

    public void setDriverCount(int driverCount) {
        this.driverCount = driverCount;
    }

    public void setBusCount(int busCount) {
        this.busCount = busCount;
    }

    

    
    
    
    
}