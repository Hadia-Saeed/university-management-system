import java.io.Serializable;
import java.util.ArrayList;

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
    private ArrayList<String> routes;
    private int peakHoursStart, peakHoursEnd,currentHour; 

    public TransportService(String entityID, String name, String location, int serviceHours, int staffCount, boolean isActive, int peakHoursStart, int peakHoursEnd) {
        super(entityID, name, location, serviceHours, staffCount, isActive);
        setPeakHoursStart(peakHoursStart);
        setPeakHoursEnd(peakHoursEnd);
        this.currentHour = 12; // default to noon (normal hours)
        this.routes = new ArrayList<String>();
    }

    public void setCurrentHour(int hour) {
        if(hour < 0 || hour > 23){
            System.err.println("Invalid hour. Hour must be between 0 and 23.");
            return;
        }
        this.currentHour = hour;
    }

    public void setPeakHoursStart(int peakHoursStart) { 
        this.peakHoursStart = peakHoursStart; 
    }

    public void setPeakHoursEnd(int peakHoursEnd){ 
        this.peakHoursEnd = peakHoursEnd; 
    }

    public ArrayList<String> getRoutes(){ 
        return routes; 
    }

    public int getPeakHoursStart(){ 
        return peakHoursStart; 
    }

    public int getPeakHoursEnd(){ 
        return peakHoursEnd; 
    }

    public int getCurrentHour(){ 
        return currentHour;
    }

    public void addRoute(String route) {
        routes.add(route);
    }

    public void removeRoute(String route) {
        if(routes.contains(route)){
            routes.remove(route);
        } else {
            System.out.println("Route not found: " + route);
        }
    }

    // From Schedulable interface
    @Override
    public String generateSchedule() {
        String type = (currentHour >= peakHoursStart && currentHour <= peakHoursEnd) ? "PEAK HOURS — every 10 mins" : "Normal — every 30 mins";
        String schedule = "Transport Schedule (" + type + "):\n";
        for(int i = 0; i < routes.size(); i++) {
            schedule += "  " + routes.get(i) + "\n";
        }
        return schedule;
    }

    @Override
    public double CalculateOperationalCost() {
        return routes.size() * getServiceHours() * 15.0;
    }

    @Override
    public String toString() {
        return super.toString() + ", Routes: " + routes.size() + ", PeakHours: " + peakHoursStart + ":00 - " + peakHoursEnd + ":00" + ", CurrentHour: " + currentHour + ":00";
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