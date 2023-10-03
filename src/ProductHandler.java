import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductHandler {
    private ArrayList<Product> productList = new ArrayList<Product>();
    private static final String FILENAME = "Product.txt";

    public void readFromFile() {
        if (!Utility.createTextFile(FILENAME)) {
            try {
                Scanner scan = new Scanner(new File(FILENAME));
                while (scan.hasNextLine()) {
                    String product = scan.nextLine();
                    String[] productInfo = product.split(",");

                    Product tempProduct = new Product(
                            productInfo[0],
                            productInfo[1],
                            Integer.parseInt(productInfo[2])
                    );
                    productList.add(tempProduct);
                }
            } catch (FileNotFoundException e) {
                System.out.println(" FEL!!! " + e.getMessage());
            }
        }
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }
    
    public void addProductToList(Product newProduct) {
        if (Utility.addItemToTextFile(newProduct.formatedStringForFile(), FILENAME)) {
            this.productList.add(newProduct);
        }
    }

    public void removeProductFromList(Product oldProduct) {
        if (removeProductFromTextFile()) {
            this.productList.remove(oldProduct);
        }
    }

    private boolean removeProductFromTextFile() {
        try {
            FileOutputStream fos = new FileOutputStream(FILENAME);
            PrintStream printStream = new PrintStream(fos);
            for (Product product : productList) {
                printStream.print("\n" + product.formatedStringForFile());
            }

            fos.close();
            printStream.close();
            return true;
        } catch (Exception e) {
            System.out.println("Det blev galet! " + e.getMessage());
        }

        return false;
    }

    public void registerProduct() {
        Product product = new Product();
        Scanner scan = new Scanner(System.in);

        String productName = "";
        String catagory = "";
        int price = 0;

        boolean run = true;
        while (run) {
            System.out.println("Registrera en produkt" +
                    "\n1. Produkt - " + productName +
                    "\n2. Kategori - " + catagory +
                    "\n3. Pris - " + price +
                    "\n4. Spara" +
                    "\nQ. Gå tillbaka" +
                    "\n\n Val -");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Produkt: ");
                    productName = scan.nextLine();
                    break;
                case "2":
                    System.out.println("Kategori: ");
                    catagory = scan.nextInt();
                    break;
                case "3":
                    System.out.println("Pris: ");
                    price = scan.nextLine();
                    break;
                case "4":
                    Product tempProduct = new Product(productName, catagory, price);
                    Object productHandler;
                    productHandler.addProdctToList(tempProduct);
                    run = false;
                    break;
                case "Q":
                    run = false;
                    break;
                default:
                    System.out.println("Måste välja 1 - 4  eller Q.");
            }
        }
    }
}
