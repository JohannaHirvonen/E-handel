import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderHandler {
    private ArrayList<Order> orderList;
    private static final String FILENAME = "Order.txt";

    public OrderHandler(){
        Utility.createTextFile(FILENAME);
        orderList = new ArrayList<>();
        readFromFile();
    }

    public void readFromFile() {
        try {
            Scanner scan = new Scanner(new File(FILENAME));
            while (scan.hasNextLine()) {
                String order = scan.nextLine();
                String[] orderInfo = order.split(",");

                Order tempOrder = new Order(
                        orderInfo[0],
                        getOrdersByNames(orderInfo[1]),
                        Double.parseDouble(orderInfo[2]),
                        LocalDateTime.parse(orderInfo[3]),
                        LocalDateTime.parse(orderInfo[4]),
                        orderInfo[5].equals("true"));

                orderList.add(tempOrder);
            }
        } catch (FileNotFoundException e) {
            System.out.println(" FEL!!! " + e.getMessage());
        }
    }

    private ArrayList<Product> getOrdersByNames(String productString) {
        ProductHandler productHandler = new ProductHandler();
        String[] productNames = productString.split(" ");
        ArrayList<Product> productList = new ArrayList<>();
        for(String name : productNames){
            for(Product product : productHandler.getProductList()){
                if(product.getName().equals(name)){
                    productList.add(product);
                }
            }
        }
        return productList;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }


//    public void printOrderList() {
//
//        if (this.orderList.size() == 0) {
//            System.out.println("No order found!");
//        } else {
//            int maxProductName = 50;
//            int maxCatagory = 50;
//            int maxPrice = 1000;
//
//            String headline = addWhiteSpace("Product Name", maxProductName) + "| " +
//                    addWhiteSpace("Catagory", maxCatagory) + "| " +
//                    addWhiteSpace( "Price", maxPrice) ;
//            System.out.println(headline);
//
//            System.out.println("-".repeat(headline.length()));
//
//            for (int i = 0; i < this.orderList.size(); i++) {
//                System.out.println(addWhiteSpace(this.orderList.get(i).getName(), maxProductName) + "| " +
//                        addWhiteSpace(this.orderList.get(i).getCatagory() + "", maxCatagory)+ "| " +
//                        addWhiteSpace(this.orderList.get(i).getPrice() + "", maxPrice));
//                if (i%2 == 1){
//                    System.out.println("-".repeat(headline.length()));
//                }
//            }
//        }
//    }

    public void printOrderHistoryAdmin() {
        try {
            Scanner scan = new Scanner(new File(FILENAME));
            int receipts = 0;
            while (scan.hasNextLine()) {
                String order = scan.nextLine();
                String[] orderInfo = order.split(",");
                    System.out.println(orderInfo[0] + orderInfo[1] +
                            orderInfo[2] + orderInfo[3] + orderInfo[4]);
                    receipts ++;
            }
            if(receipts == 0){
                System.out.println("No order history found");
            }
        } catch (FileNotFoundException e) {
            System.out.println(" FEL!!! " + e.getMessage());
        }
    }

    public void printFromFileByCustomer(String customerID) {
        try {
            Scanner scan = new Scanner(new File(FILENAME));
                int receipts = 0;
            while (scan.hasNextLine()) {
                String order = scan.nextLine();
                String[] orderInfo = order.split(",");
                if(orderInfo[0].equals(customerID)){
                    System.out.println(orderInfo[1] + orderInfo[2] + orderInfo[3] + orderInfo[4]);
                    receipts ++;
                }
            }
            if(receipts == 0){
                System.out.println("No order history found");
            }
        } catch (FileNotFoundException e) {
            System.out.println(" FEL!!! " + e.getMessage());
        }
    }


    private String addWhiteSpace(String text, int maxAmount){
        if(text.length() > maxAmount){
            return text.substring(0, maxAmount - 3) + "...";
        }
        return text + " ".repeat(maxAmount - text.length());
    }

    public void addOrderToList(Customer customer) {
        if (Utility.addItemToTextFile(customer.getCart().formatedStringForFile(), FILENAME)) {
            this.orderList.add(customer.getCart());
        }
    }


    public void removeOrderFromList(Order oldOrder) {
        if (removeOrderFromTextFile()) {
            this.orderList.remove(oldOrder);
        }
    }
    public boolean removeOrderYesOrNo(){
        return false;
    }
    private boolean removeOrderFromTextFile() {
        try {
            FileOutputStream fos = new FileOutputStream(FILENAME);
            PrintStream printStream = new PrintStream(fos);
            for (Order order : orderList) {
                printStream.print("\n" + order.formatedStringForFile());
            }

            fos.close();
            printStream.close();
            return true;
        } catch (Exception e) {
            System.out.println("Det blev galet! " + e.getMessage());
        }

        return false;
    }
}