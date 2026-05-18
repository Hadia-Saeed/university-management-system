import java.io.*;
import java.util.ArrayList;
public class DataManager{

    // PRIVATE GENERIC HELPER SAVE METHOD (USES CAMPUS REPOSITORY GENERIC CLASS AND THE FILE NAME PASSED)
    private static <T extends Serializable> void save(CampusRepository<T> repo, String filename) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(repo);
            oos.close();
            System.out.println(filename + " saved successfully.");
        }catch (IOException e) {
            System.out.println("Error saving " + filename + ": " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error saving " + filename + ": " + e.getMessage());
        }
    }

    //PUBLIC SPECIFIC SAVE METHODS

    //to save all students
    public static void saveStudents(CampusRepository<Student> repo){
        save(repo,"students.dat");
    }
    //to save all courses 
    public static void saveCourses(CampusRepository<Course> repo){
        save(repo,"courses.dat");
    }
    //to save all facilities 
    public static void saveFacilities(CampusRepository<Facility> repo){
        save(repo,"facilities.dat");
    }
    //to save all Users (diff implementation as it uses an arraylist of users to write in the file)
    public static void saveUsers(ArrayList<User> repo){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"));
            oos.writeObject(repo);
            oos.close();
            System.out.println("users.dat saved successfully.");
        }catch (IOException e) {
            System.out.println("Error saving users.dat: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error saving users.dat: " + e.getMessage());
        }
    }

    //PRIVATE GENERIC HELPER LOAD METHOD (Uses the file name passed)
    private static <T extends Serializable> CampusRepository<T> load(String filename){
        try {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        CampusRepository<T> repo = (CampusRepository<T>) ois.readObject();
        ois.close();
        return repo;
            
        } catch (ClassNotFoundException e) {
            System.out.println(filename + " corrupted or class mismatch. Starting fresh.");
            return new CampusRepository<T>();
        }
        catch (IOException e){
            System.out.println(filename + " not found or empty. Starting fresh.");
            return new CampusRepository<T>();
        }
        catch (Exception e){
            System.out.println( " An error occured while reading the file " + filename + ". Starting fresh.");
            return new CampusRepository<T>();
        }
    }

    //PUBLIC SPECIFIC LOAD METHODS

    public static CampusRepository<Student> loadStudents() {
        return load("students.dat");
    }

    public static CampusRepository<Course> loadCourses() {
        return load("courses.dat");
    }

    public static CampusRepository<Facility> loadFacilities(){
        return load("facilities.dat");
    }

    //LOAD USERS HAS ITS OWN IMPLEMENTATION AS IT RETURNS AN ARRAY LIST AS CAMPUS REPO IS ONLY FOR STUDENTS,COURSES, AND FACILITIES
    public static ArrayList<User> loadUsers(){
        try {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"));
        ArrayList<User> users = (ArrayList<User>) ois.readObject();
        ois.close();
        return users;
            
        } catch (ClassNotFoundException e) {
            System.out.println("users.dat corrupted or class mismatch. Starting fresh.");
            return new ArrayList<User>();
        }
        catch (IOException e){
            System.out.println("users.dat not found or empty. Creating default admin.");
            ArrayList<User> users = new ArrayList<User>();
            users.add(new Admin("A001", "admin", "admin123"));  // default login
            users.add(new Student(20, 3.5, "S001", "student", "student123")); // default student login
            users.add(new Teacher("Mathematics", "T001", "teacher", "teacher123")); // default teacher login
            return users;
        }
        catch (Exception e){
            System.out.println( " An error occured while reading the file users.dat. Starting fresh.");
            return new ArrayList<User>();
        }
    }

    //SAVE ALL AT ONCE (ALSO USED AS BACKUP IN THE END OF THE PROGRAM)
    public static void saveAll(CampusRepository<Student> students,CampusRepository<Course> courses,CampusRepository<Facility> facilities,ArrayList<User> users) {
        saveStudents(students);
        saveCourses(courses);
        saveFacilities(facilities);
        saveUsers(users);
        System.out.println("All data saved.");
    }

   
}

