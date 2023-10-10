import java.util.Scanner;

public class Login {
    public void registerCustomer(CustomerHandler customerHandler) {
        Scanner scan = new Scanner(System.in);
        String username;
        do {
            System.out.print("Enter your username (at least 3 characters long): ");
            username = scan.nextLine();
        } while (!isValidUsername(username));

        String password;
        do {
            System.out.print("Enter your password (at least 6 characters long): ");
            password = scan.nextLine();
        } while (!isValidPassword(password));

        Customer customer = new Customer(username, password);
        if(customerHandler.addCustomer(customer)) {
            System.out.println("Registration successful!");
        }
    }

    private boolean isValidUsername(String username) {
        if (!username.matches("^[A-Za-z0-9]+$")) {
            System.out.println("Invalid username! Please use letters and numbers only.");
            return false;
        }
        if (username.length() < 3) {
            System.out.println("Invalid username! Please enter at least 3 characters.");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 6) {
            System.out.println("Invalid password! Please enter at least 6 characters.");
            return false;
        }
        return true;
    }
    public Customer loginCustomer(CustomerHandler customerHandler) {
        Scanner scan = new Scanner(System.in);
        String enteredUsername;
        String enteredPassword;

        do {
            System.out.print("Enter your username: ");
            enteredUsername = scan.nextLine();

        } while (!isValidUsername(enteredUsername));

        do {
            System.out.print("Enter your password: ");
            enteredPassword = scan.nextLine();

        } while (!isValidPassword(enteredPassword));

        if (customerHandler.authenticate(enteredUsername, enteredPassword)) {
            System.out.println("Login successful. Welcome!");
            return new Customer(enteredUsername, enteredPassword);
        } else {
            System.out.println("Username or password is incorrect. Please try again or register as a new customer!");
            return null;
        }
    }

    public boolean adminLogin() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter username: ");
        String enteredUsername = scan.nextLine();
        System.out.print("Enter password: ");
        String enteredPassword = scan.nextLine();
        if (enteredUsername.equals(AdminCredentials.ADMIN_NAME.getValue())
                && enteredPassword.equals(AdminCredentials.ADMIN_PASSWORD.getValue())) {
            System.out.println("Admin login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }
}
