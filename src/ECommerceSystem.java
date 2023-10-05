import java.util.Scanner;

public class ECommerceSystem {
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
        customerHandler.addCustomer(customer);
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
        if(customerHandler.authenticate(enteredUsername, enteredPassword)){
            Customer customer = new Customer(enteredUsername, enteredPassword);
            customerMenu(customer);
        } else {
            //TODO return to main menu
        }

    }

    private void adminLogin() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter username: ");
        String enteredUsername = scan.nextLine();
        System.out.print("Enter password: ");
        String enteredPassword = scan.nextLine();
        if (enteredUsername.equals(AdminCredentials.ADMIN_NAME.getValue())
                && enteredPassword.equals(AdminCredentials.ADMIN_PASSWORD.getValue())) {
            System.out.println("Admin login successful!");
            adminMenu();
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
                    boolean productMenuActive = true;
                    while (productMenuActive) {
                        System.out.println("\nProduct Meny:");
                        System.out.println("1. Add product to cart");
                        System.out.println("2. Remove product from cart");
                        //TODO see cart
                        System.out.println("3. Back to main meny");
                        System.out.println("Enter your choice: ");
                        String productChoice = scan.nextLine();

                        switch (productChoice) {
                            case "1":
                                //TODO print productList
                                System.out.println("Enter the ID of the product you want to add to your cart: ");
                                int productId = Integer.parseInt(scan.nextLine());
                                //TODO how many would you like?

                                if (productId >= 0 && productId < productHandler.getProductList().size()) {
                                    Product selectedProduct = productHandler.getProductList().get(productId);
                                    customer.getCart().addProduct(selectedProduct);
                                    System.out.println(selectedProduct.getName() + " has been added to your cart.");
                                } else {
                                    System.out.println("Invalid product ID.");
                                }
                                break;
                            case "2":
                                //TODO see cart
                                System.out.println("Enter the ID of the product you want to remove from your cart: ");
                                int removeProductId = Integer.parseInt(scan.nextLine());

                                if (removeProductId >= 0 && removeProductId < customer.getCart().getProducts().size()) {
                                    Product removedProduct = customer.getCart().getProducts().get(removeProductId);
                                    customer.getCart().removeProduct(removedProduct);
                                    System.out.println(removedProduct.getName() + " has been removed from your cart.");
                                } else {
                                    System.out.println("Invalid product ID.");
                                }
                                break;
                            case "3":
                                productMenuActive = false;
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                    }
                    break;

//                    - välj produkt, lägg till i varukorg
//                    customer.getCart().addProduct();
//                    - gå tillbaka


                case "2":
                    System.out.println("Shopping Cart:\n");
                    customer.getCart().printCart();
                    boolean cartMenuActive = true;
                    while (cartMenuActive) {
                        System.out.println("\nCart Menu:");
                        System.out.println("1. Buy order");
                        System.out.println("2. Cancel order");
                        System.out.println("3. See shopping cart");
                        System.out.println("4. Back to main menu");
                        System.out.println("Enter your choice: ");
                        String cartChoice = scan.nextLine();

                        switch (cartChoice) {
                            case "1":
                                if (customer.getCart().getProducts().isEmpty()) {
                                    System.out.println("Your cart is empty.");
                                } else {
                                    customer.getCart().makePurchase();
                                    orderHandler.addOrderToList(customer);
                                }
                                break;
                            case "2":
                                customer.clearCart();
                                System.out.println("Your cart is now empty!");
                                break;
                            case "3":
                                //TODO see cart
                            case "4":
                                cartMenuActive = false;
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                    }
                    break;
                case "3":
                    orderHandler.printFromFileByCustomer(customer.getUserID());
                    break;
                case "4":
                    run = false;
                    break;
            }
        }
    }

    private void adminMenu() {
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("Admin Menu: \n");
            System.out.println("1.Edit product list");
            System.out.println("2.Edit customer information");
            System.out.println("3.Transaction history");
            System.out.println("4.Logout");
            System.out.print("Enter your choice: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    productHandler.printProductList();
                    printProductMenu();
                    break;
                case "2":
                    printCustomersMenu();
                    break;
                case "3":
                    orderHandler.printOrderHistoryAdmin();
                    break;
                case "4":
                    run  = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printProductMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            System.out.println("1. Add product to list");
            System.out.println("2. Remove product from list");
            System.out.println("3. See product list");
            //TODO menu choice: see product list
            System.out.println("4. Close");

            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Type the name of the product");
                    String name = scanner.nextLine();
                    System.out.println("Type the category of the product");
                    String category = scanner.nextLine();
                    System.out.println("Set the price");
                    int price = Integer.parseInt(scanner.nextLine());
                    productHandler.addProductToList(new Product(name, category, price));
                    break;
                case "2":
                    System.out.println("Which product do you want to remove?");
                    int id = Integer.parseInt(scanner.nextLine());
                    productHandler.removeProductFromList(id);
                    break;
                case "3":
                    productHandler.printProductList();
                default:
                    System.out.println("Invalid input! Try again.");
            }
        } while (!choice.equals("4"));
    }
    private void printCustomersMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            System.out.println("1. Edit customer information");
            System.out.println("2. See Customer list");
            System.out.println("3. Close");

            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Which customer (index) do you want to edit?");
                    int userIndex = Integer.parseInt(scanner.nextLine());
                    System.out.println("If you wish to edit UserID - type 1 \nIf you wish to edit Password - type 2");
                    int adminChoice = Integer.parseInt(scanner.nextLine());
                    if (adminChoice == 1) {
                        System.out.println("Please type new UserID");
                        String newUserID = scanner.nextLine();
                        customerHandler.customerList.get(userIndex).setID(newUserID);
                    } else if (adminChoice == 2) {
                        System.out.println("Please type new Password");
                        String newPassword = scanner.nextLine();
                        customerHandler.customerList.get(userIndex).setPassword(newPassword);
                    }
                    break;
                case "2":
                    customerHandler.printCustomerList();
                    break;
                default:
                    System.out.println("Invalid input! Try again.");
            }
        } while (!choice.equals("3"));
    }

}



//När vi startar programmet, låsa in användare, läsa in alla produkter från textfilen till produktlistan (initierad)



    /*private List<Product> productList = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();

        // Skapa en meny och implementera användarens interaktion med systemet
        // Använd klasserna ovan för att hantera produkter, kunder, beställningar och inloggning.*/
