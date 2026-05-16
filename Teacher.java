import java.util.ArrayList;
import java.io.Serializable;

public class Teacher implements Serializable{
    private String teacherId, name, department;
    private ArrayList<String> assignedCourseIDs;

    public Teacher(String teacherId, String name, String department) {
        setTeacherId(teacherId);
        setName(name);
        setDepartment(department);
        assignedCourseIDs = new ArrayList<String>();
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getName() {
        return name;
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
        return "TeacherID: " + teacherId + "\nName: " + name + "\nDepartment: " + department;
    }
    
}//end of Teacher class
