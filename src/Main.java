import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome");
        displayMainMenu();

    }
    public static void displayMainMenu() {
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("Main Menu:");
            System.out.println("\n1.Register as a user" +
                    "\n2.Login for user" +
                    "\n3.Login for admin" +
                    "\n4.Exit the program");
            System.out.println("Enter your choice: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    registerUser();
                    break;
                case "2":
                    loginUser();
                    break;
                case "3":
                    adminLogin();
                    break;
                case "4":
                    System.out.println("Exiting the program.");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    public static void registerUser() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your full name: ");
        String fullName = scan.nextLine();
        System.out.print("Enter your username: ");
        String username = scan.nextLine();
        System.out.print("Enter your password: ");
        String password = scan.nextLine();
        System.out.println("Registration successful!");
    }
    private static void loginUser() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String enteredUsername = scan.nextLine();
        System.out.print("Enter your password: ");
        String enteredPassword = scan.nextLine();
        LoginSystem ls = new LoginSystem();
        if(ls.authenticate(enteredUsername, enteredPassword)){
            usermenu();
        } else {
            System.out.println("Du angav fel användarnamn eller lösenord.");
            System.out.println("1. Logga in");
            System.out.println("2. Registrera ny användare");
            System.out.println("3. Avsluta");
            String choice = scan.nextLine();
        }

    }

    private static void adminLogin() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter username: ");
        String enteredUsername = scan.nextLine();
        System.out.print("Enter password: ");
        String enteredPassword = scan.nextLine();
        if (enteredUsername.equals(Login.adminUsername) && enteredPassword.equals(Login.adminPassword)) {
            System.out.println("Admin login successful!");
            adminMeny();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }

    }
    private static void usermenu() {
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("Meny:\n");
            System.out.println("1.See product list" +
                    "\n2.Add product to cart" +
                    "\n3.Remove product from cart" +
                    "\n4.See shopping cart" +
                    "\n5.Buy order" +
                    "\n6.Cancel order" +
                    "\n7.Save shopping cart" +
                    "\n8.See purchase history" +
                    "\n9.Logout");
            System.out.println("Enter your choice: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    run = false;
                    break;
            }
        }
    }
    private static void adminMeny() {
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("Admin Meny: \n");
            System.out.println("1.Add product");
            System.out.println("2.Remove product");
            System.out.println("3.View customer information");
            System.out.println("4.Edit customer information");
            System.out.println("5.Logout");
            System.out.print("Enter your choice: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":

                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

                    break;
                case "5":
                    run  = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}