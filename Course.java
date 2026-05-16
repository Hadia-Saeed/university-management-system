import java.io.*;
import java.util.ArrayList;

public class Course implements Schedulable,Serializable{
    private static int totalCourses = 0;
    private String courseID, courseName , creditHours;
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Assignment> assignmentList = new ArrayList<>();

    private String day,time,classroomID; //Data members for timetable

    public Course(String classroom, String courseName, String courseID, String creditHours, String day, String time) {
        this.classroomID = classroom;
        this.courseName = courseName;
        this.courseID = courseID;
        this.creditHours = creditHours;
        this.day = day;
        this.time = time;
        totalCourses++;
    }

    //add methods for student and assignment lists
    public void addStudent(Student s){
        if (s != null) {
        studentList.add(s);
        System.out.println("Successfully added the Student.");
        }
        else{
            System.out.println("Student cannot be null.");
        }
    }

    public void addAssignment(Assignment a){
        if (a != null) {
        assignmentList.add(a);
        System.out.println("Successfully added the Assignment.");
        }
        else{
            System.out.println("Assignment cannot be null.");
        }
    }

    //Setters and Getters
    public void setDay(String day) {
        this.day = day;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setClassroom(String classroom) {
        this.classroomID = classroom;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCreditHours(String creditHours) {
        this.creditHours = creditHours;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public void setAssignmentList(ArrayList<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCreditHours() {
        return creditHours;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public ArrayList<Assignment> getAssignmentList() {
        return assignmentList;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getClassroom() {
        return classroomID;
    }
    
    public static int getTotalCourses() {
        return totalCourses;
    }

    @Override
    public String toString() {
        return "Course [courseID=" + courseID + ", coursName=" + courseName + ", creditHours=" + creditHours + ", studentList=" + studentList + ", assignmentList=" + assignmentList + "]";
    }

    public void generateSchedule(){
        System.out.println("=== Class Schedule ===" +"\nCourse: " + courseName +"\nCourse ID: " + courseID +"\nDay(s): " + day +"\nTime: " + time +"\nAssigned Classroom: " + classroomID +"\nCredit Hours: " + creditHours);
        
    }


    
}