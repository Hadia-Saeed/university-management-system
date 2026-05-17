import java.io.Serializable;

public class User implements Serializable{
    private String userID, userName, password, role;

    public User(String userID, String userName, String password, String role) {
        setUserID(userID);
        setUserName(userName);
        setPassword(password);
        setRole(role);
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) { //check for valid role
        if(role.equalsIgnoreCase("Admin") || role.equalsIgnoreCase("Student") || role.equalsIgnoreCase("Teacher")){
            this.role = role;
        }
        else{
            System.out.println("Invalid role. Role must be either Admin, Student or Teacher.");
        }
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return ("UserID: " + userID + "\nUserName: " + userName + "\nPassword: " + password + "\nRole: " + role);
    }

}//end of User class
