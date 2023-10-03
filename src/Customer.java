import java.io.File;
import java.io.IOException;

public class Customer {
    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    private String userID;
    private String password;



    public Customer(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }
    public String toFileString() {
        return this.userID + "," + this.password + ",";
    }
}
