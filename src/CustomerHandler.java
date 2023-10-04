import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerHandler {
    private static final String FILENAME = "Shoppingcart.txt";
    private ArrayList<CustomerHandler> cartList;

    public CustomerHandler() {
        Utility.createTextFile(FILENAME);
        CustomerHandler = new ArrayList<>();
        readFromFile();
    }

    public void readFromFile() {
        try {
            Scanner scan = new Scanner(new File(FILENAME));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] cartInfo = line.split(",");

                CustomerHandler tempCart = new CustomerHandler(
                        cartInfo[0],
                        cartInfo[1]);
                cartList.add(tempCart);
            }
        } catch (FileNotFoundException e) {
            System.out.println(" FEL!!! " + e.getMessage());
        }
    }

    public void addProductToCart(Product product) {
        //TODO check if customer alreday exists
        cartList.add(product);
        Utility.addItemToTextFile(product.toFileString(), FILENAME);
    }

}
