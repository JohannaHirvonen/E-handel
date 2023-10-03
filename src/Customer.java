import java.io.File;
import java.io.IOException;

public class Customer {
    private String userID;
    private String password;
    private String fileName = "customers.txt";

    public Customer(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }
    public String toFileString() {
        return this.userID + "," + this.password + ",";
    }
}
