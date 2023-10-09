import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {

    public static final String FILE_NAME = "Order.txt";
    private double totalPrice;
    private String timeCreated;
    private String timeCompleted;
    private String customerID;
    private ArrayList<Product> products;
    private String receipt;
    private boolean completed;

    public Order(String customerID){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        timeCreated = time.format(formatter);
        products = new ArrayList<>();
        this.customerID = customerID;
    }


    public Order(String customerID, ArrayList<Product> productList, double totalPrice, String timeCreated,
                 String timeCompleted, boolean completed){
        this.timeCreated = timeCreated;
        this.customerID = customerID;
        products = productList;
        //TODO felhantering ifall en product försvunnit från textfilen/listan
    }

    public ArrayList<Product> getProducts(){
        return products;

    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    public double getTotalPrice(){
        double total = 0;
        for(Product product : products){
            total += product.getPrice();
        }
        return total;
    }

    public void makePurchase(){
        totalPrice = getTotalPrice();
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        timeCompleted = time.format(formatter);
        completed = true;
    }

    public void printCart(){

        if (this.products.isEmpty()) {
            System.out.println("No products in cart, go back to customer menu to see product list");
        } else {

            System.out.println("Your shopping cart contains:");
            printProducts();
            System.out.println("                   ");
            System.out.println("Total cost: " + getTotalPrice() + " kr");
            System.out.println("Status: " + (completed ? "Purchase complete" : "Not purchased"));
            System.out.println("Go back to customer menu too see shopping cart in order to make purchase");

            System.out.println("Order:" +
                    "\n saved: " + timeCreated);
            printProducts();
            System.out.println("..........................");
            System.out.println("Total sum: " + getTotalPrice() + " kr");
            System.out.println("Status: " + (completed ? "paid" : "unpaid"));
        }
    }

    public void printReceipt(){
        //TODO print english and one line -Joy
            System.out.println("Receipt:" +
                    "\nCreated: " + timeCreated);
            printProducts();
            System.out.println("                                        ");
            System.out.println("Total cost: " + getTotalPrice() + " kr");
            System.out.println("Status: " + (completed ? "Paid" : "Not paid"));
    }

    private void printProducts() {
        System.out.println("Products: ");
        for(Product product : products){
            System.out.println(product.getName() + "..............." + product.getPrice());
        }
    }

    public String formatedStringForFile() {
        return
                customerID + "," +
                        productsToString() + "," +
                        totalPrice + "," +
                        timeCreated + "," +
                        timeCompleted + "," +
                        completed;
    }

    private String productsToString(){
        StringBuilder productString = new StringBuilder();
        for (Product product : products) {
            productString.append(" ").append(product.getName());
        }
        receipt = productString.toString();
        return productString.toString();
    }

    public String getCustomerID() {
        return customerID;
    }
}