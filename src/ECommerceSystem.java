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
                    
                    1.Register as a customer
                    2.Login for customer
                    3.Login for admin
                    4.Exit the program""");
            System.out.println("Enter your choice: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    login.registerCustomer(customerHandler);
                    break;
                case "2":
                    Customer customer = login.loginCustomer(customerHandler);
                    if(customer != null) {
                        customerMenu(customer);
                    }
                    break;
                case "3":
                    if(login.adminLogin(customerHandler)){
                        adminMenu();
                    }
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

    private void customerMenu(Customer customer) {
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("""
                    
                    Customer Menu
                    
                    1.See product list
                    2.See shopping cart
                    3.See purchase history
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
            }
        }
    }

    private void shoppingCartMenu(Customer customer) {
        Scanner scan = new Scanner(System.in);
        customer.getCart().printCart();
        boolean cartMenuActive = true;
        while (cartMenuActive) {
            System.out.println("""
            
            Shopping Cart Menu
            
            1. Buy order
            2. Cancel order
            3. See shopping cart
            4. Back to main menu
            Enter your choice:""");
            String cartChoice = scan.nextLine();

            switch (cartChoice) {
                case "1":
                    if (customer.getCart().getProducts().isEmpty()) {
                        System.out.println("Your cart is empty.");
                    } else {
                        customer.getCart().makePurchase();
                        orderHandler.addOrderToList(customer);
                        System.out.println("Successfull purchase! This is your receipt: \n");
                        orderHandler.getOrderList().get(orderHandler.getOrderList().size() -1).printReceipt();
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
                    System.out.println("Invalid choice. Please try again.");
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
            
            1. Add product to cart
            2. Remove product from cart
            3. Back to main meny"
            Enter your choice:""");
            String productChoice = scan.nextLine();

            switch (productChoice) {
                case "1":
                    System.out.println("Enter the ID of the product you want to add to your cart: ");
                    int productId = Integer.parseInt(scan.nextLine());
                    //Nice to have: how many would you like?

                    if (productId >= 0 && productId < productHandler.getProductList().size()) {
                        Product selectedProduct = productHandler.getProductList().get(productId);
                        customer.getCart().addProduct(selectedProduct);
                        System.out.println(selectedProduct.getName() + " has been added to your cart.");
                    } else {
                        System.out.println("Invalid product ID.");
                    }
                    customer.getCart().printCart();
                    break;
                case "2":
                    System.out.println("Enter the ID of the product you want to remove from your cart: ");
                    int removeProductId = Integer.parseInt(scan.nextLine());

                    if (removeProductId >= 0 && removeProductId < customer.getCart().getProducts().size()) {
                        Product removedProduct = customer.getCart().getProducts().get(removeProductId);
                        customer.getCart().removeProduct(removedProduct);
                        System.out.println(removedProduct.getName() + " has been removed from your cart.");
                    } else {
                        System.out.println("Invalid product ID.");
                    }
                    customer.getCart().printCart();
                    break;
                case "3":
                    productMenuActive = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
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
            
            1.Edit product list
            2.Edit customer information
            3.Transaction history
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
                    run  = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void productAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            System.out.println("""
            
            Admin Product Menu
            
            1. Add product to list
            2. Remove product from list
            3. See product list
            4. Close""");

            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Type the name of the product");
                    String name = scanner.nextLine();
                    System.out.println("Type the category of the product");
                    String category = scanner.nextLine();
                    System.out.println("Set the price");
                    //TODO handle wrong input format - Nurhan
                    int price = Integer.parseInt(scanner.nextLine());
                    productHandler.addProductToList(new Product(name, category, price));
                    break;
                case "2":
                    System.out.println("Which product do you want to remove?");
                    //TODO handle wrong input format - Nurhan
                    int id = Integer.parseInt(scanner.nextLine());
                    productHandler.removeProductFromList(id);
                    break;
                case "3":
                    productHandler.printProductList();
                    break;
                default:
                    System.out.println("Invalid input! Try again.");
            }
        } while (!choice.equals("4"));
    }
    private void customerAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            System.out.println("""
            Admin Customer Menu
            
            1. Edit customer information
            2. See Customer list
            3. Close""");

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
