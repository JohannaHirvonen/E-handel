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
        LoginSystem loginSystem = new LoginSystem(); //TODO make global
        if(loginSystem.authenticate(enteredUsername, enteredPassword)){
            Customer customer = new Customer(enteredUsername, enteredPassword);
            loginSystem.addCustomer(customer);
            usermenu(customer);
        } else {
            System.out.println("Wrong username or password.");
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

    private static void usermenu(Customer customer) {
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("Meny:\n");
            System.out.println("1.See product list" +
                    "\n2.See shopping cart" +
                    "\n3.See purchase history" +
                    "\n4.Logout");
            System.out.println("Enter your choice: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
//                    Product.getAll();
//                    - välj produkt, lägg till i varukorg
//                    - gå tillbaka
                    break;
                case "2":
                    Order cart = Customer.getCart();
                    cart.printOrder();
                    cart.addProduct();
//                            "\n3.Remove product from cart" +
//                            "\n5.Buy order" +
//                            "\n6.Cancel order" +
                    customerAddProductToCart();
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
            System.out.println("1.Edit product list");
            System.out.println("2.Edit customer information");
            System.out.println("3. Transaction history");
            System.out.println("4.Logout");
            System.out.print("Enter your choice: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    //Add product
                        //- new Product() + add product to list
//                    ProductHandler handler = new ProductHandler().addProductToList(new Product());
                    //remove product
                    // - are you sure you want to remove this product?
                    break;
                case "2":
//                    see customer info (name, pasword, order history)
//                    - add customer
//                    - edit customer (remove + add)
//                        - change username
//                        - change password
                    break;
                case "3":
//                    transaction history - Transaction.getAllOrders();
                case "4":
                    run  = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
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
