import java.util.ArrayList;
import java.util.List;

public class LoginSystem {
    private static final String FILENAME = "Customer.txt";
//    TODO create file, readfromfile
    private ArrayList<Customer> customerList;

    public LoginSystem() {
        customerList = new ArrayList<>();
        //initiate list from file
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
        Utility.addItemToTextFile(customer.toFileString(), FILENAME);
    }

    public boolean authenticate(String username, String password) {
        for (Customer customer : customerList) {
            if (customer.getUserID().equals(username) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}