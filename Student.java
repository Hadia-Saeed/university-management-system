import java.util.ArrayList;
public class Student {
    private static int totalStudents = 0; // Static variable to keep track of total students
    private String studentID, name;
    private int age;
    private double cgpa;
    private ArrayList<String> enrolledCourseIDs;

    public Student(String studentID, String name, int age, double cgpa) {
        setStudentID(studentID);
        setName(name);
        setAge(age);
        setCgpa(cgpa);
        enrolledCourseIDs = new ArrayList<String>();
        totalStudents++; 
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getCgpa() {
        return cgpa;
    }

    public ArrayList<String> getEnrolledCourseIDs() {
        return enrolledCourseIDs;
    }

    public int getTotalStudents() {
        return totalStudents;
    }   

    public void addCourse(String id){
        
    }

    @Override
    public String toString() {
        return ("StudentID: " + studentID + "\nName: " + name + "\nAge: " + age + "\nCGPA: " + cgpa);
    }

}//end of Student class
