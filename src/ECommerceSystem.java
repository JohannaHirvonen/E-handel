import java.util.Scanner;

public class ECommerceSystem {
    public ProductHandler productHandler;
    public OrderHandler orderHandler;
    public CustomerHandler customerHandler;

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
                    Customer customer = login.loginCustomer(customerHandler);
                    if (customer != null) {
                        customerMenu(customer);
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

    private void customerMenu(Customer customer) {
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
                    productMenu(customer);
                    break;
                case "2":
                    shoppingCartMenu(customer);
                    break;
                case "3":
                    //Nice to have: prettier output
                    orderHandler.printFromFileByCustomer(customer.getUserID());
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

    private void shoppingCartMenu(Customer customer) {
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
                    break;
                case "2":
                    customer.clearCart();
                    System.out.println("Your cart is now empty!");
                    break;
                case "3":
                    customer.getCart().printCart();
                case "4":
                    cartMenuActive = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }

    private void productMenu(Customer customer) {
        Scanner scan = new Scanner(System.in);
        productHandler.printProductList();
        boolean productMenuActive = true;
        while (productMenuActive) {
            System.out.println("""
                                
                    Product Menu
                                
                    1. Add Product to Cart
                    2. Remove Product from Cart
                    3. Back to Customer Menu"
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
                        } else {
                            System.out.println("Invalid product ID!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                    customer.getCart().printCart();
                    break;
                case "2":
                    System.out.println("Enter the ID of the product you want to remove from your cart: ");
                    try {
                        int removeProductId = Integer.parseInt(scan.nextLine());

                        if (removeProductId >= 0 && removeProductId < customer.getCart().getProducts().size()) {
                            Product removedProduct = customer.getCart().getProducts().get(removeProductId);
                            customer.getCart().removeProduct(removedProduct);
                            System.out.println(removedProduct.getName() + " has been removed from your cart.");
                        } else {
                            System.out.println("Invalid product ID!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid choice! Please enter a valid number.");
                    }
                    customer.getCart().printCart();
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
                                
                    Admin Menu
                                
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
                    customerAdminMenu();
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
                    String name = readLettersWithMinLength(scanner, 2);
                    System.out.println("Type the category of the product (at least 2 letters only):");
                    String category = readLettersWithMinLength(scanner, 2);
                    System.out.println("Set the price (numbers only):");
                    int price = readNumbersOnly(scanner);
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

    private int readNumbersOnly(Scanner scanner) {
        int number;
        while (true) {
            try {
                number = Integer.parseInt(scanner.nextLine());
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

    private void customerAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            System.out.println("""
                    Admin Customer Menu
                                
                    1. Edit Customer Information
                    2. See Customer List
                    3. Close""");

            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Which customer (index) do you want to edit?");
                    int userIndex;

                    while (true) {
                        userIndex = Integer.parseInt(scanner.nextLine());

                        if (userIndex < 0 || userIndex >= customerHandler.customerList.size()) {
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
                        customerHandler.customerList.get(userIndex).setID(newUserID);
                        System.out.println("Information has been changed.");
                    } else if (adminChoice == 2) {
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

                        customerHandler.customerList.get(userIndex).setPassword(newPassword);
                        System.out.println("Information has been changed.");
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
