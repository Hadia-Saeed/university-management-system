import java.util.ArrayList;

public class SessionManager {  //Login + Authentication  
    private static User currentUser;
    private static ArrayList<User> registeredUsers = new ArrayList<>();

    //Using this as a way to load the list of known users into SessionManager, which can then be used to check if a user is registered or not
    //As static datamemebrs do not have constructors, we can use a static method to load the list of users into the SessionManager class
    public static void loadUsers(ArrayList<User> users) { 
        registeredUsers = users;
    }

    public static boolean login(String username, String password) { //checks if the username and password match any of the registered users
        for (int i = 0; i < registeredUsers.size(); i++) {
            if (registeredUsers.get(i).getUserName().equals(username) && registeredUsers.get(i).getPassword().equals(password)) {
                currentUser = registeredUsers.get(i);
                return true;    //Login successful
            }
        }
        return false;     //Login failed
    }

    public static void logout() {
        currentUser = null;     //Clear the current user after current session is done (logout)
    }


    public static User getCurrentUser() {
        return currentUser;
    }

    public static String getRole(){
        if (currentUser != null) {
            return currentUser.getRole();
        }
        return null;    //No user is currently logged in
    }


}//end of SessionManager class
