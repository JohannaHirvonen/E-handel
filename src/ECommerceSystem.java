import java.util.Scanner;

public class ECommerceSystem {
    private ProductHandler productHandler;
    private OrderHandler orderHandler;
    private CustomerHandler customerHandler;
    private Customer customer;
    private static final int MIN_LENGTH = 2;

    public ECommerceSystem() {
        productHandler = new ProductHandler();
        orderHandler = new OrderHandler(productHandler);
        customerHandler = new CustomerHandler();
    }

    public void displayMainMenu() {
        Login login = new Login();
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("""
                                        
                    Main Menu
                                        
                    1.Register as a Customer
                    2.Customer Login
                    3.Admin Login
                    4.Exit the Program""");
            System.out.println("Enter your choice: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    login.registerCustomer(customerHandler);
                    break;
                case "2":
                    customer = login.loginCustomer(customerHandler);
                    if (customer != null) {
                        customerMenu();
                    }
                    break;
                case "3":
                    if (login.adminLogin(customerHandler)) {
                        adminMenu();
                    }
                    break;
                case "4":
                    System.out.println("Exiting the program.");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again!");
                    break;
            }
        }
    }

    private void customerMenu() {
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("""
                                        
                    Customer Menu
                                        
                    1.See Product List
                    2.See Shopping Cart
                    3.See Purchase History
                    4.Logout""");

            System.out.println("Enter your choice: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    productMenu();
                    break;
                case "2":
                    shoppingCartMenu();
                    //TODO shopping cart instance not updated correctly!!
                    break;
                case "3":
                    System.out.println("Purchase history:");
                    orderHandler.printOrderHistoryCustomer(customer.getUserID());
                    break;
                case "4":
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
                    break;
            }
        }
    }

    private void shoppingCartMenu() {
        Scanner scan = new Scanner(System.in);
        if (!customer.getCart().printCart()){
            return;
        }
        boolean cartMenuActive = true;
        while (cartMenuActive) {
            System.out.println("""
                                
                    Shopping Cart Menu
                                
                    1. Buy Order
                    2. Cancel Order
                    3. See Shopping Cart
                    4. Back to Customer Menu
                    Enter your choice:""");

            String cartChoice = scan.nextLine();

            switch (cartChoice) {
                case "1":
                    if (customer.getCart().getProducts().isEmpty()) {
                        System.out.println("Your cart is empty");
                    } else {
                        customer.getCart().makePurchase();
                        orderHandler.addOrderToList(customer);
                        System.out.println("Successfull purchase! This is your receipt: \n");
                        orderHandler.getOrderList().get(orderHandler.getOrderList().size() - 1).printReceipt();
                        customer.clearCart();
                    }
                    cartMenuActive = false;
                    break;
                case "2":
                    customer.clearCart();
                    System.out.println("Your cart is now cleared!");
                    cartMenuActive = false;
                    break;
                case "3":
                    customer.getCart().printCart();
                    break;
                case "4":
                    cartMenuActive = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }

    private void productMenu() {
        Scanner scan = new Scanner(System.in);
        productHandler.printProductList();
        boolean productMenuActive = true;
        while (productMenuActive) {
            System.out.println("""
                                
                    Product Menu
                                
                    1. Add Product to Cart
                    2. Remove Product from Cart
                    3. Back to Customer Menu
                    Enter your choice:""");

            String productChoice = scan.nextLine();

            switch (productChoice) {
                case "1":
                    System.out.println("Enter the ID of the product you want to add to your cart: ");
                    try {
                        int productId = Integer.parseInt(scan.nextLine());
                        //Nice to have: how many would you like?

                        if (productId >= 0 && productId < productHandler.getProductList().size()) {
                            Product selectedProduct = productHandler.getProductList().get(productId);
                            customer.getCart().addProduct(selectedProduct);
                            System.out.println(selectedProduct.getName() + " has been added to your cart.");
                            customer.getCart().printCart();
                            System.out.println("Go back to customer menu to see shopping cart in order to make purchase");

                        } else {
                            System.out.println("Invalid product ID! Please enter an ID from the list.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                    break;
                case "2":
                    customer.getCart().printCart();
                    System.out.println("Enter the ID of the product you want to remove from your cart: ");
                    try {
                        int removeProductId = Integer.parseInt(scan.nextLine());

                        if (removeProductId >= 0 && removeProductId < customer.getCart().getProducts().size()) {
                            Product removedProduct = customer.getCart().getProducts().get(removeProductId);
                            customer.getCart().removeProduct(removedProduct);
                            System.out.println(removedProduct.getName() + " has been removed from your cart.");
                            customer.getCart().printCart();
                        } else {
                            System.out.println("Invalid product ID! Please enter an ID from your cart.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid choice! Please enter a valid number.");
                    }
                    break;
                case "3":
                    productMenuActive = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again!");
                    break;
            }
        }
    }

    private void adminMenu() {
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("""
                                
                    Admin Main Menu
                                
                    1.Edit Product List
                    2.Edit Customer Information
                    3.Transaction History
                    4.Logout
                    Enter your choice:""");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    productHandler.printProductList();
                    productAdminMenu();
                    break;
                case "2":
                    customerHandler.printCustomerList();
                    System.out.println("Are you sure you want to edit customer?" + "\n Yes/No ");
                    String edit = "";
                    while (!edit.equals("no") || !edit.equals("yes")) {
                        edit = scan.nextLine().toLowerCase();
                        if (edit.equals("yes")) {
                            int id = readValidCustomerId(scan);
                            customerHandler.editCustomer(id, scan);
                        } else if (edit.equals("no")) {
                            break;
                        }
                        System.out.println("Please enter yes or no.");
                    }
                        break;
                case "3":
                    orderHandler.printOrderHistoryAdmin();
                    break;
                case "4":
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void productAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            System.out.println("""
                                
                    Admin Product Menu
                                
                    1. Add Product to List
                    2. Remove Product from List
                    3. See Product List
                    4. Close""");

            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Type the name of the product (at least 2 letters only):");
                    String name = readLettersWithMinLength(scanner, MIN_LENGTH);
                    System.out.println("Type the category of the product (at least 2 letters only):");
                    String category = readLettersWithMinLength(scanner, MIN_LENGTH);
                    System.out.println("Set the price (numbers only):");
                    double price = readNumbersOnly(scanner);
                    productHandler.addProductToList(new Product(name, category, price));
                    productHandler.printProductList();
                    break;
                case "2":
                    System.out.println("Which product do you want to remove (enter product ID):");
                    int id = readValidProductId(scanner);
                    if (id != -1) {
                        productHandler.removeProductFromList(id);
                        System.out.println("The product has been removed from the list.");
                        productHandler.printProductList();
                    }
                    break;
                case "3":
                    productHandler.printProductList();
                    break;
                default:
                    System.out.println("Invalid input! Try again.");
            }
        } while (!choice.equals("4"));
    }

    private String readLettersWithMinLength(Scanner scanner, int minLength) {
        String input;
        do {
            input = scanner.nextLine();
            if (!input.matches("^[A-Za-z]+$") || input.length() < minLength) {
                System.out.println("Invalid input! Please enter at least " + minLength + " letters.");
            }
        } while (!input.matches("^[A-Za-z]+$") || input.length() < minLength);
        return input;
    }

    private double readNumbersOnly(Scanner scanner) {
        double number;
        while (true) {
            try {
                number = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter numbers only.");
            }
        }
        return number;
    }

    private int readValidProductId(Scanner scanner) {
        int id;
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine());
                if (id >= 0 && productHandler.isValidProduct(id)) {
                    break;
                } else {
                    System.out.println("Invalid product ID! Please enter a valid product ID.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter numbers only.");
            }
        }
        return id;
    }

    private int readValidCustomerId(Scanner scanner) {
        int id;
        while (true) {
            try {
                System.out.println("Which customer (index) do you want to edit?");
                id = Integer.parseInt(scanner.nextLine());
                if (customerHandler.isValidCustomer(id)) {
                    break;
                } else {
                    System.out.println("Invalid customer ID! Please enter a valid customer ID.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 0 and " +
                        customerHandler.customerList.size() + ".");
            }
        }
        return id;
    }
}
