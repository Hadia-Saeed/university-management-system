import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Academic_Unit extends Campus_Entity {
    protected int studentCapacity,staffCount;
    protected String semester;

    public Academic_Unit(String semester, int staffCount, int studentCapacity, String entityID, String location, String name) {
        super(entityID, location, name);
        this.semester = semester;
        setStudentCapacity(studentCapacity);
        setStaffCount(staffCount);
    }

    public void setStudentCapacity(int studentCapacity) {
        if(studentCapacity < 0){
            System.out.println("Invalid Student Capacity Entered.");
            studentCapacity = 0;
        }
        this.studentCapacity = studentCapacity;
    }

    public void setStaffCount(int staffCount) {
        if(staffCount < 0){
            System.out.println("Invalid Staff Count Entered.");
            staffCount = 0;
        }
        this.staffCount = staffCount;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getStudentCapacity() {
        return studentCapacity;
    }

    public int getStaffCount() {
        return staffCount;
    }

    public String getSemester() {
        return semester;
    }

    public abstract double CalculateOperationalCost();

    public String toString(){
        return super.toString() + "Student Capacity:" + studentCapacity + "Staff Count:" + staffCount + "Semester:" + semester;
    }
    
}
class Department extends Academic_Unit implements Reportable{
    private String hodName;
    private ArrayList <Course> coursesList = new ArrayList<>();
    private ArrayList<Teacher> teachersList = new ArrayList<>();

    public Department(String hodName, String semester, int staffCount, int studentCapacity, String entityID, String location, String name) {
        super(semester, staffCount, studentCapacity, entityID, location, name);
        this.hodName = hodName;
    }

    public void addCourse(Course c){
        if (c != null) {
        coursesList.add(c);
        System.out.println("Successfully added the course.");
        }
        else{
            System.out.println("Course cannot be null.");
        }
    }

    public void removeCourse(String courseID){
        boolean found = false;
        for (int i = 0; i < coursesList.size(); i++){
            if(coursesList.get(i) != null && coursesList.get(i).getCourseID().equals(courseID)){
                coursesList.remove(i);
                System.out.println("Successfully removed the Course.");
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Course NOT Found.");
        }
    }

    public void addTeacher(Teacher t) {
        if (t != null) {
            teachersList.add(t);
            System.out.println("Successfully added the teacher.");
        } else {
            System.out.println("Teacher cannot be null.");
        }
    }

    public void removeTeacher(String teacherID) {
        boolean found = false;
        for (int i = 0; i < teachersList.size(); i++) {
            if (teachersList.get(i) != null && teachersList.get(i).getTeacherId().equals(teacherID)) {
                teachersList.remove(i);
                System.out.println("Successfully removed the teacher.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Teacher NOT Found.");
        }
    }

    public void setHodName(String hodName) {
        this.hodName = hodName;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.coursesList = courses;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachersList = teachers;
    }

    public String getHodName() {
        return hodName;
    }

    public ArrayList<Course> getCourses() {
        return coursesList;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachersList;
    }

    public int calculateTotalStudents(){
        int total = 0;
        for(int i = 0; i < coursesList.size();i++){
            total  += coursesList.get(i).getStudentList().size();
        }
        return total;
    }

    public void generateReport(){
        System.out.println("=== Department Performance Report ===" + "\nDepartment: " + getName() + "\nHOD: " + hodName +"\nLocation: " + getLocation() +"\nTotal Courses: " + coursesList.size() +"\nTotal Students Enrolled: " + calculateTotalStudents() +"\nOperational Cost: " + CalculateOperationalCost());
    }

    @Override
    public double CalculateOperationalCost(){
        return (studentCapacity * 500) + (staffCount * 2000);
    }

    @Override
    public String toString() {
       return super.toString()+"Department [HOD Name=" + hodName + ", Courses =" + coursesList;
    }
    
}
class Classroom extends Academic_Unit{
    private boolean hasProjector;
    private String roomType;

    public Classroom(boolean hasProjector, String roomType, String semester, int staffCount, int studentCapacity, String entityID, String location, String name) {
        super(semester, staffCount, studentCapacity, entityID, location, name);
        this.hasProjector = hasProjector;
        this.roomType = roomType;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean getHasProjector() {
        return hasProjector;
    }

    public String getRoomType() {
        return roomType;
    }

    public double CalculateOperationalCost(){
        return (studentCapacity*200) + (hasProjector? 500 : 0);
    }

    @Override
    public String toString(){
        return super.toString() + "Has Projector? " + hasProjector + "Room Type: "+roomType;
    }
}
class Lab extends Academic_Unit{
    private String labType;
    private int equipmentCount;
    private  double equipmentCostPerUnit;

    public Lab(double equipmentCostPerUnit, int equipmentCount, String labType, String semester, int staffCount, int studentCapacity, String entityID, String location, String name) {
        super(semester, staffCount, studentCapacity, entityID, location, name);
        setEquipmentCostPerUnit(equipmentCostPerUnit);
        setEquipmentCount(equipmentCount);
        this.labType = labType;
    }

    public void setLabType(String labType) {
        this.labType = labType;
    }

    public void setEquipmentCount(int equipmentCount) {
        if(equipmentCount < 0){
            System.out.println("Invalid Equipment Count Entered.");
            equipmentCount = 0;
        }
        this.equipmentCount = equipmentCount;
    }

    public void setEquipmentCostPerUnit(double equipmentCostPerUnit) {
        if(equipmentCostPerUnit < 0){
            System.out.println("Invalid Equipment Cost Per Unit Entered.");
            equipmentCostPerUnit = 0;
        }
        this.equipmentCostPerUnit = equipmentCostPerUnit;
    }
    

    public String getLabType() {
        return labType;
    }

    public int getEquipmentCount() {
        return equipmentCount;
    }

    public double getEquipmentCostPerUnit() {
        return equipmentCostPerUnit;
    }

    public double CalculateOperationalCost(){
        return (studentCapacity*300) + (equipmentCount * equipmentCostPerUnit);
    }

    @Override
    public String toString(){
        return super.toString() + "Lab Type: "+labType+"Equipment Count:"+equipmentCount+"Equipment Cost per Unit: "+equipmentCostPerUnit;
    }
}
