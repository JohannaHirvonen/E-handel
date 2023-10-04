import java.util.Scanner;

public class ECommerceSystem {

    public CustomerHandler loginSystem = new CustomerHandler();
    public ProductHandler productHandler = new ProductHandler();
    public OrderHandler orderHandler = new OrderHandler();
    public CustomerHandler customerHandler = new CustomerHandler();
    public void displayMainMenu() {
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("Main Menu:");
            System.out.println("\n1.Register as a customer" +
                    "\n2.Login for customer" +
                    "\n3.Login for admin" +
                    "\n4.Exit the program");
            System.out.println("Enter your choice: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    registerCustomer();
                    break;
                case "2":
                    loginCustomer();
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
    public void registerCustomer() {
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
        loginSystem.addCustomer(customer);
        System.out.println("Registration successful!");
    }

    public boolean isValidUsername(String username) {
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

    public boolean isValidPassword(String password) {
        if (password.length() < 6) {
            System.out.println("Invalid password! Please enter at least 6 characters.");
            return false;
        }
        return true;
    }
    private void loginCustomer() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String enteredUsername = scan.nextLine();
        System.out.print("Enter your password: ");
        String enteredPassword = scan.nextLine();
        if(loginSystem.authenticate(enteredUsername, enteredPassword)){
            Customer customer = new Customer(enteredUsername, enteredPassword);
            loginSystem.addCustomer(customer);
            customerMenu(customer);
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
        if (enteredUsername.equals(AdminCredentials.ADMIN_NAME.getValue())
                && enteredPassword.equals(AdminCredentials.ADMIN_PASSWORD.getValue())) {
            System.out.println("Admin login successful!");
            adminMeny();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void customerMenu(Customer customer) {
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
                    productHandler.printProductList();
//                    - välj produkt, lägg till i varukorg
//                    customer.getCart().addProduct();
//                    - gå tillbaka
                    break;
                case "2":

                   //TODO customerHandler.printCart();
//                    - Remove product from cart"
//                    - Buy order: customerHandler.getCart().makePurchase();
//                    - Cancel order
                    break;
                case "3":
                    orderHandler.printOrderList();
                case "4":
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
            System.out.println("1.Edit product list");
            System.out.println("2.Edit customer information");
            System.out.println("3. Transaction history");
            System.out.println("4.Logout");
            System.out.print("Enter your choice: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    ProductHandler.addProductToList(new Product("", "",0));
                    //Add product
                        //- new Product() + add product to list
                   ProductHandler handler = new ProductHandler().addProductToList(new Product());

                    ProductHandler.removeProductFromList();
                    ProductHandler handler = ProductHandler().removeProductFromList(Product());
                    //remove product
                    ProductHandler.removeProductYesOrNo();
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
}



//När vi startar programmet, låsa in användare, läsa in alla produkter från textfilen till produktlistan (initierad)



    /*private List<Product> productList = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();

        // Skapa en meny och implementera användarens interaktion med systemet
        // Använd klasserna ovan för att hantera produkter, kunder, beställningar och inloggning.*/
