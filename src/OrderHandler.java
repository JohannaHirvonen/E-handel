import java.io.*;
import java.time.LocalDateTime;
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
        }
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void printOrderHistoryAdmin() {
        try {
            Scanner scan = new Scanner(new File(FILENAME));
            int receipts = 0;
            int maxSymbol = 20;
            int maxPrice = 5;
            String headline = "Username " + addWhiteSpace("Username", maxSymbol) + "| " +
                    addWhiteSpace("Products", maxSymbol) + "| " + addWhiteSpace("Price", maxPrice) + "| " + addWhiteSpace("Order created", maxSymbol) + "| " + addWhiteSpace("Time of purchase", maxSymbol) + "| ";
            while (scan.hasNextLine()) {
                String order = scan.nextLine();
                String[] orderInfo = order.split(",");
                System.out.println(headline);
                System.out.println(addWhiteSpace(orderInfo[0], maxSymbol) + addWhiteSpace(orderInfo[1], maxSymbol) + addWhiteSpace(orderInfo[2], maxPrice) +
                        addWhiteSpace(orderInfo[3], maxSymbol) + addWhiteSpace(orderInfo[4], maxSymbol));

                    receipts ++;
            }
            if(receipts == 0){
                System.out.println("No order history found!");
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
                System.out.println("No order history found!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(" Error " + e.getMessage());
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
            System.out.println("Error! " + e.getMessage());
        }

        return false;
    }
}