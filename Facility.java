import java.io.Serializable;
import java.util.ArrayList;

public abstract class Facility extends Campus_Entity implements Serializable{
    protected double maintenanceCost;
    protected int operatingHours, capacity;
    private static int totalFacilityUsage = 0;

    public Facility(String entityID, String location, String name, double maintenanceCost, int operatingHours, int capacity) {
        super(entityID, location, name);
        setMaintenanceCost(maintenanceCost);
        setOperatingHours(operatingHours);
        setCapacity(capacity);
        totalFacilityUsage++;
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

    public static int getTotalFacilityUsage() {
        return totalFacilityUsage;
    }

    public abstract double CalculateOperationalCost();

    @Override
    public String toString() {
        return super.toString() + ("MaintenanceCost:" + maintenanceCost + "OperatingHours:" + operatingHours + "Capacity:" + capacity);
    }

}//end of Facility class

class Library extends Facility implements Reportable, Serializable{
    private ArrayList<Book> books = new ArrayList<>(); // aggregation - books exist independently

    public Library(String entityID, String location, String name, double maintenanceCost, int operatingHours, int capacity, int memberCount) {
        super(entityID, location, name, maintenanceCost, operatingHours, capacity);
    }
    
    public ArrayList<Book> getBooks(){ 
        return books;
    }

    public void addBook(Book b) {
        if(b != null){
            books.add(b);
        } else {
            System.out.println("Book cannot be null.");
        }
    }

    public void removeBook(String bookID) {
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getBookID().equals(bookID)) {
                books.remove(i);
                System.out.println("Book removed successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    @Override
    public double CalculateOperationalCost() {
        return maintenanceCost + (operatingHours * 10) + (capacity * 5);
    }

    @Override
    public void generateReport() {
        System.out.println("=== Library Usage Report ===" +"\nName: " + getName() +"\nLocation: " + getLocation() +"\nTotal Books: " + books.size() +"\nOperational Cost: " + CalculateOperationalCost());
    }

    @Override
    public String toString() {
        return super.toString() + "\nTotal Books:" + books.size();
    }

}//end of Library class

class Cafeteria extends Facility implements Serializable{
    protected int seatCount, menuCount;

    public Cafeteria(String entityID, String location, String name, double maintenanceCost, int operatingHours, int capacity, int seatCount, int menuCount) {
        super(entityID, location, name, maintenanceCost, operatingHours, capacity);
        setSeatCount(seatCount);
        setMenuCount(menuCount);
    }

    public void setSeatCount(int seatCount) {
        if(seatCount < 0){
            System.err.println("Seat count cannot be negative.");
        }
        this.seatCount = seatCount;
    }

    public void setMenuCount(int menuCount) {
        if(menuCount < 0){
            System.err.println("Menu count cannot be negative.");
        }
        this.menuCount = menuCount;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public int getMenuCount() {
        return menuCount;
    }

    @Override
    public double CalculateOperationalCost() {
        return maintenanceCost + (operatingHours * 10) + (capacity * 5) + (seatCount * 2) + (menuCount * 3);
    }

    @Override
    public String toString() {  
        return super.toString() + ("\nSeat Count:" + seatCount + "\nMenu Count:" + menuCount);
    }

}//end of Cafeteria class

class Hostel extends Facility implements Serializable{
    protected String wardenName;
    protected int roomCount, occupiedRooms;

    public Hostel(String entityID, String location, String name, double maintenanceCost, int operatingHours, int capacity, String wardenName, int roomCount, int occupiedRooms) {
        super(entityID, location, name, maintenanceCost, operatingHours, capacity);
        setWardenName(wardenName);
        setRoomCount(roomCount);
        setOccupiedRooms(occupiedRooms);
    }

    public void setWardenName(String wardenName) {
        this.wardenName = wardenName;
    }

    public void setRoomCount(int roomCount) {
        if(roomCount < 0){
            System.err.println("Room count cannot be negative.");
        }
        this.roomCount = roomCount;
    }

    public void setOccupiedRooms(int occupiedRooms) {
        if(occupiedRooms < 0){
            System.err.println("Occupied rooms cannot be negative.");
        }
        this.occupiedRooms = occupiedRooms;
    }

    public String getWardenName() {
        return wardenName;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public int getOccupiedRooms() {
        return occupiedRooms;
    }

    @Override
    public double CalculateOperationalCost() {
        return maintenanceCost + (operatingHours * 15) + (capacity * 10) + (roomCount * 20) + (occupiedRooms * 30);
    }

    @Override
    public String toString() {
        return super.toString() + ("\nWarden Name:" + wardenName + "\nRoom Count:" + roomCount + "\nOccupied Rooms:" + occupiedRooms);
    }

}//end of Hostel class