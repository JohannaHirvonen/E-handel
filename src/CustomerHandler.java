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

            int maxUserName = WhitespaceValues.USERNAME.getValue();
            int maxPassword = WhitespaceValues.PASSWORD.getValue();
            int maxID = WhitespaceValues.ID.getValue();

            String headline = Utility.addWhiteSpace("ID", maxID) + "| " + Utility.addWhiteSpace("Username", maxUserName) + "| " +
                    Utility.addWhiteSpace("Password", maxPassword) + "| ";
            System.out.println(headline);

            System.out.println("-".repeat(headline.length()));

            for (int i = 0; i < this.customerList.size(); i++) {
                System.out.println(Utility.addWhiteSpace(i + "", maxID) + "| " +
                        Utility.addWhiteSpace(this.customerList.get(i).getUserID(), maxUserName) + "| " +
                        Utility.addWhiteSpace(this.customerList.get(i).getPassword() + "", maxPassword)+ "| ");

                if (i%5 == 0){
                    System.out.println("-".repeat(headline.length()));
                }
            }
        }
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
    public void editCustomer(int userIndex, Scanner scanner) {
        System.out.println("If you wish to edit UserID - type 1 \nIf you wish to edit Password - type 2");
        int adminChoice;

        while (true) {
            try {
                adminChoice = Integer.parseInt(scanner.nextLine());

                if (adminChoice != 1 && adminChoice != 2) {
                    System.out.println("Invalid choice! Please enter 1 for UserID or 2 for Password.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter 1 or 2 only.");
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

    public boolean isValidCustomer(int customerId) {
        return customerId >= 0 && customerId < customerList.size();
    }
}