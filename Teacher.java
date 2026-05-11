public class Teacher {
    private String teacherId, name, department;

    public Teacher(String teacherId, String name, String department) {
        setTeacherId(teacherId);
        setName(name);
        setDepartment(department);
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

    @Override
    public String toString() {
        return "TeacherID: " + teacherId + "\nName: " + name + "\nDepartment: " + department;
    }
    
}
