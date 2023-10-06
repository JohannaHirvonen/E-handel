import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    public static final String FILE_NAME = "Order.txt";
    private double totalPrice;
    private LocalDateTime timeCreated;
    private LocalDateTime timeCompleted;
    private String customerID;
    private ArrayList<Product> products;
    private String receipt;
    private boolean completed;

    public Order(String customerID){
        timeCreated = LocalDateTime.now();
        products = new ArrayList<>();
        this.customerID = customerID;
    }


    public Order(String customerID, ArrayList<Product> productList, double totalPrice, LocalDateTime timeCreated,
                 LocalDateTime timeCompleted, boolean completed){
        this.timeCreated = timeCreated;
        this.customerID = customerID;
        products = productList;
        //TODO felhantering ifall en product försvunnit från textfilen/listan
    }

    public LocalDateTime getTimeCreated (){
        return timeCreated;
    }

    public ArrayList<Product> getProducts(){
        return products;

    }

    public void addProduct(Product product){
        //Nice to have: change list type to set to be able to add numberOfProduct
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
        timeCompleted = LocalDateTime.now();
        completed = true;
    }

    public void printCart(){
        //TODO print english and one line
        if (this.products.isEmpty()) {
            System.out.println("No products in cart!");
        } else {
            System.out.println("Beställning:" +
                    "\n Skapad: " + timeCreated);
            printProducts();
            System.out.println("..........................");
            System.out.println("Totalsumma: " + getTotalPrice() + " kr");
            System.out.println("Status: " + (completed ? "betald" : "obetald"));
        }
    }

    public void printOrder(){
        //TODO print english and one line
        System.out.println("Beställning:" +
                "\n Skapad: " + timeCreated);
        System.out.println(receipt);
        System.out.println("..........................");
        System.out.println("Totalsumma: " + getTotalPrice() + " kr");
        System.out.println("Status: " + (completed ? "betald" : "obetald"));
    }

    private void printProducts() {
        System.out.println("Produkter: ");
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