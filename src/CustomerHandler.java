import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerHandler {
    private static final String FILENAME = "Customer.txt";
    public ArrayList<Customer> customerList;

    public CustomerHandler() {
        Utility.createTextFile(FILENAME);
        customerList = new ArrayList<>();
        readFromFile();
    }

    public void readFromFile() {
            try {
                Scanner scan = new Scanner(new File(FILENAME));
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] customerInfo = line.split(",");

                    Customer tempCustomer = new Customer(
                            customerInfo[0],
                            customerInfo[1]);
                    customerList.add(tempCustomer);
                }
            } catch (FileNotFoundException e) {
                System.out.println(" Error! " + e.getMessage());
            }
    }

    public boolean addCustomer(Customer customer) {
        if(!customerExists(customer.getUserID())) {
            customerList.add(customer);
            Utility.addItemToTextFile(customer.toFileString(), FILENAME);
            return true;
        } else {
            System.out.println("This username already exists. Please choose another username.");
            return false;
        }
    }

    public boolean authenticate(String username, String password) {
        for (Customer customer : customerList) {
            if (customer.getUserID().equals(username) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;

    }
    public void printCustomerList() {

        if (this.customerList.isEmpty()) {
            System.out.println("No customers registered!");
        } else {
            int maxUserID = 30;
            int maxPassword = 30;

            String headline = "ID " + addWhiteSpace("Username", maxUserID) + "| " +
                    addWhiteSpace("Password", maxPassword) + "| ";
            System.out.println(headline);

            System.out.println("-".repeat(headline.length()));

            for (int i = 0; i < this.customerList.size(); i++) {
                System.out.println(i + ": " +
                        addWhiteSpace(this.customerList.get(i).getUserID(), maxUserID) + "| " +
                        addWhiteSpace(this.customerList.get(i).getPassword() + "", maxPassword)+ "| ");

                if (i%2 == 1){
                    System.out.println("-".repeat(headline.length()));
                }
            }
        }
    }
    private String addWhiteSpace(String text, int maxAmount){
        if(text.length() > maxAmount){
            return text.substring(0, maxAmount - 3) + "...";
        }
        return text + " ".repeat(maxAmount - text.length());
    }

    public Customer getCustomerByName(String username) {
        for(Customer customer : customerList){
            if(customer.getUserID().equals(username)){
                return customer;
            }
        }
        return null;
    }
    private boolean customerExists(String customerID){
        for (Customer customer : customerList) {
            if (customer.getUserID().equals(customerID)) {
                return true;
            }
        }
        return false;
    }

    public void editCustomer(Scanner scanner) {
        System.out.println("Which customer (index) do you want to edit?");
        int userIndex;

        while (true) {
            userIndex = Integer.parseInt(scanner.nextLine());

            if (userIndex < 0 || userIndex >= customerList.size()) {
                System.out.println("Invalid user index! Please enter a valid index.");
            } else {
                break;
            }
        }
        System.out.println("If you wish to edit UserID - type 1 \nIf you wish to edit Password - type 2");
        int adminChoice;

        while (true) {
            adminChoice = Integer.parseInt(scanner.nextLine());

            if (adminChoice != 1 && adminChoice != 2) {
                System.out.println("Invalid choice! Please enter 1 for UserID or 2 for Password.");
            } else {
                break;
            }
        }

        if (adminChoice == 1) {
            System.out.println("Please type new UserID");
            String newUserID;

            while (true) {
                newUserID = scanner.nextLine();

                if (newUserID.length() < 3 || !newUserID.matches("^[A-Za-z0-9]+$")) {
                    System.out.println("Invalid UserID! At least 3 characters using letters and numbers only.");
                } else {
                    break;
                }
            }
            customerList.get(userIndex).setID(newUserID);
            System.out.println("Information has been changed.");
        } else {
            System.out.println("Please type new password: ");
            String newPassword;

            while (true) {
                newPassword = scanner.nextLine();

                if (newPassword.length() < 6) {
                    System.out.println("Invalid Password! Please enter at least 6 characters.");
                } else {
                    break;
                }
            }
            customerList.get(userIndex).setPassword(newPassword);
            System.out.println("Information has been changed.");
        }
    }
}