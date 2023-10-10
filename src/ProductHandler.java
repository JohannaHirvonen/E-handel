import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductHandler {
    private ArrayList<Product> productList;
    private static final String FILENAME = "Product.txt";

    public ProductHandler(){
        Utility.createTextFile(FILENAME);
        productList = new ArrayList<>();
        readFromFile();
    }

    public void readFromFile() {
            try {
                Scanner scan = new Scanner(new File(FILENAME));
                while (scan.hasNextLine()) {
                    String product = scan.nextLine();
                    String[] productInfo = product.split(",");

                    Product tempProduct = new Product(
                            productInfo[0],
                            productInfo[1],
                            Double.parseDouble(productInfo[2])
                    );
                    productList.add(tempProduct);
                }
            } catch (FileNotFoundException e) {
                System.out.println(" An error occurred. " + e.getMessage());
            }
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void printProductList() {

        if (this.productList.isEmpty()) {
            System.out.println("No product in the inventory!");
        } else {

            int maxProductName = WhitespaceValues.PRODUCT_NAME.getValue();
            int maxCategory = WhitespaceValues.CATEGORY.getValue();
            int maxPrice = WhitespaceValues.PRICE.getValue();
            int maxID = WhitespaceValues.ID.getValue();

            String headline = Utility.addWhiteSpace("ID", maxID) + "| " + Utility.addWhiteSpace("Product", maxProductName) + "| " +
                    Utility.addWhiteSpace("Category", maxCategory) + "| " +
                    Utility.addWhiteSpace( "Price", maxPrice) + "| ";
            System.out.println(headline);

            System.out.println("-".repeat(headline.length()));

            for (int i = 0; i < this.productList.size(); i++) {
                System.out.println(Utility.addWhiteSpace(i + "", maxID) + "| " +
                        Utility.addWhiteSpace(this.productList.get(i).getName(), maxProductName) + "| " +
                        Utility.addWhiteSpace(this.productList.get(i).getCategory() + "", maxCategory)+ "| " +
                        Utility.addWhiteSpace(this.productList.get(i).getPrice() + "", maxPrice) + "| ");
                if (i%5 == 1){
                    System.out.println("-".repeat(headline.length()));
                }
            }
        }
    }

    public void addProductToList(Product newProduct) {
        if (Utility.addItemToTextFile(newProduct.formattedStringForFile(), FILENAME)) {
            this.productList.add(newProduct);
            System.out.println("Product added to the list.");
        }
    }
    public boolean isValidProduct(int productId) {
        return productId >= 0 && productId < productList.size();
    }

    public void removeProductFromList(int id) {
        this.productList.remove(id);
        removeProductFromTextFile();
    }

    private boolean removeProductFromTextFile() {
        try {
            FileOutputStream fos = new FileOutputStream(FILENAME);
            PrintStream printStream = new PrintStream(fos);
            for (int i = 0; i < productList.size(); i++) {
                if(i == 0){
                    printStream.print(productList.get(i).formattedStringForFile());
                } else {
                    printStream.print("\n" + productList.get(i).formattedStringForFile());
                }
            }
            fos.close();
            printStream.close();
            return true;
        } catch (Exception e) {
            System.out.println("An error occurred! " + e.getMessage());
        }
        return false;
    }
}

