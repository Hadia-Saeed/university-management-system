import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Serializable{
    private static int totalStudents = 0; // Static variable to keep track of total students
    private int age;
    private double cgpa;
    private ArrayList<String> enrolledCourseIDs;

    public Student(int age, double cgpa, String userID, String userName, String password) {
        super(userID, userName, password, "Student");
        setAge(age);
        setCgpa(cgpa);
        enrolledCourseIDs = new ArrayList<String>();
        totalStudents++; 
    }

    public void setAge(int age) {
        if(age < 0){
            System.err.println("Age cannot be negative.");
        }
        this.age = age;
    }

    public void setCgpa(double cgpa) {
        if(cgpa < 0 || cgpa > 4.0) {
            System.err.println("CGPA must be between 0 and 4.0.");
        }
        this.cgpa = cgpa;
    }

    public int getAge() {
        return age;
    }

    public double getCgpa() {
        return cgpa;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public ArrayList<String> getEnrolledCourseIDs() {
        return enrolledCourseIDs;
    }

    public void addCourseID(String id){
        enrolledCourseIDs.add(id);
    }

    public void removeCourseID(String id){
        if (enrolledCourseIDs.contains(id)) {
            enrolledCourseIDs.remove(id);
            System.out.println("Course ID " + id + " removed from enrolled courses.");
        } else {
            System.out.println("Course ID " + id + " not found in enrolled courses.");
        }
    }

    @Override
    public String toString() {
        return (super.toString() + "\nAge: " + age + "\nCGPA: " + cgpa + "\nEnrolled Courses: " + enrolledCourseIDs);
    }

}//end of Student class
