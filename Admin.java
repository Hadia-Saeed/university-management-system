import java.io.Serializable;

public class Admin extends User implements Serializable {

    public Admin(String userID, String userName, String password) {
        super(userID, userName, password, "Admin");
    }
    
}
