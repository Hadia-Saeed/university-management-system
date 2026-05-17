import java.util.ArrayList;
import java.io.Serializable;

public class Teacher extends User implements Serializable{
    private String department;
    private ArrayList<String> assignedCourseIDs;

    public Teacher(String department,String userID, String userName, String password) {
        super(userID, userName, password, "Teacher");
        setDepartment(department);
        assignedCourseIDs = new ArrayList<String>();
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public ArrayList<String> getAssignedCourseIDs() {
        return assignedCourseIDs;
    }

    public void addCourseID(String id){
        assignedCourseIDs.add(id);
    }

    public void removeCourseID(String id){
        if (assignedCourseIDs.contains(id)) {
            assignedCourseIDs.remove(id);
            System.out.println("Course ID " + id + " removed from assigned courses.");
        } else {
            System.out.println("Course ID " + id + " not found in assigned courses.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nDepartment: " + department + "\nAssigned Courses: " + assignedCourseIDs;
    }
    
}//end of Teacher class
