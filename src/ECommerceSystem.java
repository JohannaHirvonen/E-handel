import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ECommerceSystem {
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
        String fullName;
        do {
            System.out.print("Enter your full name: ");
            fullName = scan.nextLine();
        } while (!isValidFullName(fullName));

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

        System.out.println("Registration successful!");
    }

    public static boolean isValidFullName(String fullName) {
        String[] nameParts = fullName.split(" ");

        if (nameParts.length < 2) {
            System.out.println("Invalid full name! Please enter your name and surname.");
            return false;
        }

        for (String part : nameParts) {
            if (part.length() < 2 || !part.matches("^[A-Za-z]+$")) {
                System.out.println("Invalid full name! Each word must have at least 2 letters. ");
                return false;
            }
        }
        return true;
    }
    public static boolean isValidUsername(String username) {
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

    public static boolean isValidPassword(String password) {
        if (password.length() < 6) {
            System.out.println("Invalid password! Please enter at least 6 characters.");
            return false;
        }
        return true;
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
                    customerSeeProductList();
                    break;
                case "2":
                    customerAddProductToCart();
                    break;
                case "3":
                    customerRemoveProcutFromCart();
                    break;
                case "4":
                    customerSeeShoppingCart();
                    break;
                case "5":
                    customerPurchase();
                    break;
                case "6":
                    customerCancelOrder();
                    break;
                case "7":
                    customerSaveShoppingCart();
                    break;
                case "8":
                    customerSeePurchaseHistory();
                    break;
                case "9":
                    run = false;
                    break;
            }
        }
    }
    public void customerSeeProductList(){

    }
    public void customerAddProductToCart(){

    }

    public void customerRemoveProcutFromCart(){

    }

    public void customerSeeShoppingCart(){

    }

    public void customerPurchase(){

    }

    public void customerCancelOrder(){

    }

    public void customerSaveShoppingCart(){

    }

    public void customerSeePurchaseHistory(){

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
                    registerProduct();
                    break;
                case "2":
                    adminDeleteProduct();
                    break;
                case "3":
                    adminViewCustomerInfo();
                    break;
                case "4":
                    adminEditCustomerInfo();
                    break;
                case "5":
                    run  = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public void registerProduct() {
        Product product = new Product();
        Scanner scan = new Scanner(System.in);

        String productName = "";
        String catagory = "";
        int price = 0;

        boolean run = true;
        while (run) {
            System.out.println("Registrera en produkt" +
                    "\n1. Produkt - " + productName +
                    "\n2. Kategori - " + catagory +
                    "\n3. Pris - " + price +
                    "\n4. Spara" +
                    "\nQ. Gå tillbaka" +
                    "\n\n Val -");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Produkt: ");
                    productName = scan.nextLine();
                    break;
                case "2":
                    System.out.println("Kategori: ");
                    catagory = scan.nextInt();
                    break;
                case "3":
                    System.out.println("Pris: ");
                    price = scan.nextLine();
                    break;
                case "4":
                    Product tempProduct = new Product(productName, catagory, price);
                    Object productHandler;
                    productHandler.addProdctToList(tempProduct);
                    run = false;
                    break;
                case "Q":
                    run = false;
                    break;
                default:
                    System.out.println("Måste välja 1 - 4  eller Q.");
            }
        }
    }
    public void printProductList(){

    }
    public void adminDeleteProduct(){

    }

    public void adminViewCustomerInfo(){

    }

    public void adminEditCustomerInfo(){

    }
}



//När vi startar programmet, låsa in användare, läsa in alla produkter från textfilen till produktlistan (initierad)



    /*private List<Product> productList = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();

        // Skapa en meny och implementera användarens interaktion med systemet
        // Använd klasserna ovan för att hantera produkter, kunder, beställningar och inloggning.*/
