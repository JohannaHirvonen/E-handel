import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    public static final String FILE_NAME = "Order.txt";
    private double totalPrice;
    private LocalDateTime timeCreated;
    private LocalDateTime timeCompleted;
    private Customer customer;
    private ArrayList<Product> products;
    private boolean completed;

    public Order(){
        timeCreated = LocalDateTime.now();
        products = new ArrayList<>();
    }

    //TODO
//    public Order(LocalDateTime timeCreated, Customer customer, ArrayList<Product> products){
//
//    }

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
        timeCompleted = LocalDateTime.now();
        completed = true;
    }

    public void printOrder(){
        System.out.println("Beställning:" +
                "\n Skapad: " + timeCreated);
        printProducts();
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
                //customer.getId() + "," +
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
        return productString.toString();
    }
    public List<Product> getProducts() {
        return products;
    }
}