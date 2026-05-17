import java.io.Serializable;

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
        return super.toString() + ("\nServiceHours:" + serviceHours + "\nStaffCount:" + staffCount + "\nIsActive:" + isActive);
    }

}//end of Service_Unit class

class TransportService extends Service_Unit implements Schedulable, Serializable{
    protected int routeCount, driverCount, busCount;

    public TransportService(String entityID, String location, String name, int serviceHours, int staffCount, boolean isActive, int routeCount, int driverCount, int busCount) {
        super(entityID, location, name, serviceHours, staffCount, isActive);
        setRouteCount(routeCount);
        setDriverCount(driverCount);
        setBusCount(busCount);
    }

    public void setRouteCount(int routeCount) {
        if(routeCount < 0){
            System.err.println("Route count cannot be negative.");
        }
        this.routeCount = routeCount;
    }

    public void setDriverCount(int driverCount) {
        if(driverCount < 0){
            System.err.println("Driver count cannot be negative.");
        }
        this.driverCount = driverCount;
    }

    public void setBusCount(int busCount) {
        if(busCount < 0){
            System.err.println("Bus count cannot be negative.");
        }
        this.busCount = busCount;
    }

    public int getRouteCount() {
        return routeCount;
    }

    public int getDriverCount() {
        return driverCount;
    }

    public int getBusCount() {
        return busCount;
    }
    
    @Override
    public double CalculateOperationalCost() {
        return (serviceHours * staffCount * 15) + (routeCount * 100) + (driverCount * 50) + (busCount * 200);
    }

    @Override
    public String toString() {
        return super.toString() + ("\nRouteCount:" + routeCount + "\nDriverCount:" + driverCount + "\nBusCount:" + busCount);
    }

    @Override
    public void generateSchedule() {
        System.out.println("Generating transport service schedule...");
    }
    
}//end of TransportService class

class SecurityService extends Service_Unit implements Notifiable, Serializable{
    protected int guardCount, shiftDuration;

    public SecurityService(String entityID, String location, String name, int serviceHours, int staffCount, boolean isActive, int guardCount, int shiftDuration) {
        super(entityID, location, name, serviceHours, staffCount, isActive);
        setGuardCount(guardCount);
        setShiftDuration(shiftDuration);
    }

    public void setGuardCount(int guardCount) {
        if(guardCount < 0){
            System.err.println("Guard count cannot be negative.");
        }
        this.guardCount = guardCount;
    }

    public void setShiftDuration(int shiftDuration) {
        if(shiftDuration < 0){
            System.err.println("Shift duration cannot be negative.");
        }
        this.shiftDuration = shiftDuration;
    }

    public int getGuardCount() {
        return guardCount;
    }

    public int getShiftDuration() {
        return shiftDuration;
    }

    @Override
    public double CalculateOperationalCost() {
        return (serviceHours * staffCount * 20) + (guardCount * 30) + (shiftDuration * 10);
    }

    @Override
    public String toString() {
        return super.toString() + ("\nGuardCount:" + guardCount + "\nShiftDuration:" + shiftDuration);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Security Notification: " + message);
    }

}//end of SecurityService class

class HealthCenter extends Service_Unit implements Notifiable, Serializable{
    protected int docCount, bedCount;
    private SecurityService securityService;

    public HealthCenter(String entityID, String location, String name, int serviceHours, int staffCount, boolean isActive, int docCount, int bedCount, SecurityService securityService) {
        super(entityID, location, name, serviceHours, staffCount, isActive);
        setDocCount(docCount);
        setBedCount(bedCount);
        this.securityService = securityService;
    }

    public void setDocCount(int docCount) {
        if(docCount < 0){
            System.err.println("Doctor count cannot be negative.");
        }
        this.docCount = docCount;
    }

    public void setBedCount(int bedCount) {
        if(bedCount < 0){
            System.err.println("Bed count cannot be negative.");
        }
        this.bedCount = bedCount;
    }

    public int getDocCount() {
        return docCount;
    }

    public int getBedCount() {
        return bedCount;
    }

    @Override
    public double CalculateOperationalCost() {
        return (serviceHours * staffCount * 25) + (docCount * 100) + (bedCount * 50);
    }

    @Override
    public String toString() {
        return super.toString() + ("\nDocCount:" + docCount + "\nBedCount:" + bedCount);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("health Center Notification: " + message);
    }

    //COMPLEXITY ADDITION : MEDICAL EMRGNCY : NOTIFY HEALTH AND SECURITY CENTER
    public void reportEmergency(String location) {
        System.out.println("=== MEDICAL EMERGENCY REPORTED ===");
        sendNotification("Medical emergency at: " + location); // notifies HealthCenter
        securityService.sendNotification("ALERT: Medical emergency at: " + location); // notifies SecurityService
    }

}//end of HealthCenter class