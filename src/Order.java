import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {

    private double totalPrice;
    private String timeCreated;
    private String timeCompleted;
    private String customerID;
    private ArrayList<Product> products;
    private String productString;
    private boolean isCompleted;

    public Order(String customerID){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        timeCreated = time.format(formatter);
        products = new ArrayList<>();
        this.customerID = customerID;
    }


    public Order(String customerID, String productString, double totalPrice, String timeCreated,
                 String timeCompleted, boolean isCompleted){
        this.customerID = customerID;
        this.productString = productString;
        this.totalPrice = totalPrice;
        this.timeCreated = timeCreated;
        this.timeCompleted = timeCompleted;
        this.isCompleted = isCompleted;

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
        isCompleted = true;
    }

    public boolean printCart(){

        if (this.products.isEmpty()) {
            System.out.println("No products in cart. Go to product list to add products.");
            return false;
        } else {
            System.out.println("Your shopping cart contains:");
            printProducts();
            System.out.println("Total price for items in cart: " + getTotalPrice() + " kr");
            System.out.println("Status: " + (isCompleted ? "Purchase complete" : "Not purchased"));
            return true;
        }
    }

    public void printReceipt(){
            System.out.println("Receipt:" +
                    "\nOrder Created: " + timeCreated);
            printProducts();
            System.out.println("Total cost: " + getTotalPrice() + " kr");
            System.out.println("Status: " + (isCompleted ? "Paid" : "Not paid"));
    }

    private void printProducts() {
        System.out.println("Products: ");
        for(Product product : products){
            System.out.println(Utility.addWhiteSpace(product.getName(), WhitespaceValues.PRODUCT_NAME.getValue()) + product.getPrice());
        }
    }

    public String formatedStringForFile() {
        return
                customerID + "," +
                        productsToString() + "," +
                        totalPrice + "," +
                        timeCreated + "," +
                        timeCompleted + "," +
                        isCompleted;
    }

    private String productsToString(){
        StringBuilder productStringBuilder = new StringBuilder();
        for (Product product : products) {
            if(productStringBuilder.isEmpty()){
                productStringBuilder.append(product.getName());
            } else {
                productStringBuilder.append(" ").append(product.getName());
            }
        }
        this.productString = productStringBuilder.toString();
        return productString;
    }
}