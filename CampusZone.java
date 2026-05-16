import java.util.ArrayList;
import java.io.Serializable;

public class CampusZone implements Serializable{
    private String zoneName;
    ArrayList<Facility> facilityList = new ArrayList<>();
    ArrayList<Service_Unit> serviceList = new ArrayList<>();

    public CampusZone(String zoneName) {
        this.zoneName = zoneName;
    }

    public void addFacility(Facility f){
        if (f != null) {
        facilityList.add(f);
        System.out.println("Successfully added the Facility.");
        }
        else{
            System.out.println("Facility cannot be null.");
        }
    }

    public void addService(Service_Unit s){
        if (s != null) {
        serviceList.add(s);
        System.out.println("Successfully added the Service Unit.");
        }
        else{
            System.out.println("Service Unit cannot be null.");
        }
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public void setFacilityList(ArrayList<Facility> facilityList) {
        this.facilityList = facilityList;
    }

    public void setServiceList(ArrayList<Service_Unit> serviceList) {
        this.serviceList = serviceList;
    }
    public String getZoneName() {
        return zoneName;
    }

    public ArrayList<Facility> getFacilityList() {
        return facilityList;
    }

    public ArrayList<Service_Unit> getServiceList() {
        return serviceList;
    }

    public void displayZoneInfo(){
        System.out.println("Zone Name:"+zoneName);
        System.out.println("Facility List:");
        for(Facility f : facilityList){
            System.out.println(f.toString());
            System.out.println("------------------------------------------");
        }
        for(Service_Unit s : serviceList){
            System.out.println(s.toString());
            System.out.println("------------------------------------------");
        }
    }

    public String toString(){
        return "Zone name:"+zoneName;
    }

    
}
