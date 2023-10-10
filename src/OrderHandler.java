import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderHandler {
    private ArrayList<Order> orderList;
    private static final String FILENAME = "Order.txt";

    public OrderHandler(ProductHandler productHandler){
        Utility.createTextFile(FILENAME);
        orderList = new ArrayList<>();
        readFromFile(productHandler);
    }

    public void readFromFile(ProductHandler productHandler) {
        try {
            Scanner scan = new Scanner(new File(FILENAME));
            while (scan.hasNextLine()) {
                String order = scan.nextLine();
                String[] orderInfo = order.split(",");

                Order tempOrder = new Order(
                        orderInfo[0],
                        orderInfo[1],
                        Double.parseDouble(orderInfo[2]),
                        (orderInfo[3]),
                        (orderInfo[4]),
                        orderInfo[5].equals("true"));

                orderList.add(tempOrder);
            }
        } catch (FileNotFoundException e) {
            System.out.println(" FEL!!! " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void printOrderHistoryAdmin() {
        try {
            Scanner scan = new Scanner(new File(FILENAME));
            int receipts = 0;
            int maxUsername = WhitespaceValues.USERNAME.getValue();
            int maxProduct = WhitespaceValues.PRODUCT_STRING.getValue();
            int maxPrice = WhitespaceValues.PRICE.getValue();
            int maxTime = WhitespaceValues.DATE_TIME.getValue();

            String headline = Utility.addWhiteSpace("Username", maxUsername) + "| " +
                    Utility.addWhiteSpace("Products", maxProduct) + "| " + Utility.addWhiteSpace("Price", maxPrice)  + "   " + "| " +
                    Utility.addWhiteSpace("Order created", maxTime) + "| " + Utility.addWhiteSpace("Time of purchase", maxTime) + "| ";
            System.out.println(headline);
            System.out.println("-".repeat(headline.length()));
            while (scan.hasNextLine()) {
                String order = scan.nextLine();
                String[] orderInfo = order.split(",");
                System.out.println(Utility.addWhiteSpace(orderInfo[0], maxUsername) + "| " + Utility.addWhiteSpace(orderInfo[1], maxProduct) + "| " + Utility.addWhiteSpace(orderInfo[2], maxPrice) + " kr" + "| " +
                        Utility.addWhiteSpace(orderInfo[3], maxTime) + "| " + Utility.addWhiteSpace(orderInfo[4], maxTime) + "| ");
                    receipts ++;
                if (receipts%5 == 0){
                    System.out.println("-".repeat(headline.length()));
                }
            }
            if(receipts == 0){
                System.out.println("No order history found!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(" FEL!!! " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void printOrderHistoryCustomer(String customerID) {
        try {
            Scanner scan = new Scanner(new File(FILENAME));
            int receipts = 0;
            int maxProductString = WhitespaceValues.PRODUCT_STRING.getValue();
            int maxPrice = WhitespaceValues.PRICE.getValue();
            int maxDateTime = WhitespaceValues.DATE_TIME.getValue();

            String headline = Utility.addWhiteSpace("Products", maxProductString) +
                    "| " + Utility.addWhiteSpace("Price", maxPrice) + "   " +
                    "| " + Utility.addWhiteSpace("Order created", maxDateTime) +
                    "| " + Utility.addWhiteSpace("Time of purchase", maxDateTime) + "| ";
            System.out.println(headline);
            System.out.println("-".repeat(headline.length()));
            while (scan.hasNextLine()) {
                String order = scan.nextLine();
                String[] orderInfo = order.split(",");
                if(orderInfo[0].equals(customerID)){
                    System.out.println(Utility.addWhiteSpace(orderInfo[1], maxProductString) +
                            "| " + Utility.addWhiteSpace(orderInfo[2], maxPrice) + " kr" +
                            "| " + Utility.addWhiteSpace(orderInfo[3], maxDateTime) +
                            "| " + Utility.addWhiteSpace(orderInfo[4], maxDateTime) + "| " );
                    receipts ++;
                    if (receipts%5 == 0){
                        System.out.println("-".repeat(headline.length()));
                    }
                }
            }
            if(receipts == 0){
                System.out.println("No order history found!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(" Error " + e.getMessage());
            throw new RuntimeException(e);
        }
    }



    public void addOrderToList(Customer customer) {
        if (Utility.addItemToTextFile(customer.getCart().formatedStringForFile(), FILENAME)) {
            this.orderList.add(customer.getCart());
        }
    }
}