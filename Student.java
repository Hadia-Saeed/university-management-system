public class Student {
    private String studentID, name;
    private int age;

    public Student(String studentID, String name, int age) {
        setStudentID(studentID);
        setName(name);
        setAge(age);
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

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return "StudentID: " + studentID + "\nName: " + name + "\nAge: " + age;
    }

}//end of Student class
