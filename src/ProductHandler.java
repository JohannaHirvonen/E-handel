import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductHandler {
    private ArrayList<Product> customerList;
    private static final String FILENAME = "Product.txt";

    public ProductHandler(){
        Utility.createTextFile(FILENAME);
        customerList = new ArrayList<>();
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
                            Integer.parseInt(productInfo[2])
                    );
                    customerList.add(tempProduct);
                }
            } catch (FileNotFoundException e) {
                System.out.println(" FEL!!! " + e.getMessage());
            }
    }

    public ArrayList<Product> getCustomerList() {
        return customerList;
    }


    public void printProductList() {

        if (this.customerList.size() == 0) {
            System.out.println("No product in the inventory!");
        } else {
            int maxProductName = 50;
            int maxCatagory = 50;
            int maxPrice = 1000;

            String headline = "Id" + addWhiteSpace("Product Name", maxProductName) + "| " +
                    addWhiteSpace("Catagory", maxCatagory) + "| " +
                    addWhiteSpace( "Price", maxPrice) ;
            System.out.println(headline);

            System.out.println("-".repeat(headline.length()));

            for (int i = 0; i < this.customerList.size(); i++) {
                System.out.println(i + ": " +
                        addWhiteSpace(this.customerList.get(i).getName(), maxProductName) + "| " +
                        addWhiteSpace(this.customerList.get(i).getCatagory() + "", maxCatagory)+ "| " +
                        addWhiteSpace(this.customerList.get(i).getPrice() + "", maxPrice));
                if (i%2 == 1){
                    System.out.println("-".repeat(headline.length()));
                }
            }
        }
    }
    private String addWhiteSpace(String text, int maxAmount){
        if(text.length() > maxAmount){
            return text.substring(0, maxAmount - 3) + "...";
        }
        return text + " ".repeat(maxAmount - text.length());
    }

    public void addProductToList(Product newProduct) {
        if (Utility.addItemToTextFile(newProduct.formatedStringForFile(), FILENAME)) {
            this.customerList.add(newProduct);
            System.out.println("Number of products in list: " + customerList.size());
        }
    }


    public void removeProductFromList(int id) {
        this.customerList.remove(id);
        removeProductFromTextFile();
    }
    public boolean removeProductYesOrNo(){
        return false;
    }
    private boolean removeProductFromTextFile() {
        try {
            FileOutputStream fos = new FileOutputStream(FILENAME);
            PrintStream printStream = new PrintStream(fos);
            for (int i = 0; i < customerList.size(); i++) {
                if(i == 0){
                    printStream.print(customerList.get(i).formatedStringForFile());
                } else {
                    printStream.print("\n" + customerList.get(i).formatedStringForFile());
                }
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

//    public void registerProduct() {
//        Product product = new Product();
//        Scanner scan = new Scanner(System.in);
//
//        String productName = "";
//        String catagory = "";
//        int price = 0;
//
//        boolean run = true;
//        while (run) {
//            System.out.println("Registrera en produkt" +
//                    "\n1. Produkt - " + productName +
//                    "\n2. Kategori - " + catagory +
//                    "\n3. Pris - " + price +
//                    "\n4. Spara" +
//                    "\nQ. Gå tillbaka" +
//                    "\n\n Val -");
//            String choice = scan.nextLine();
//
//            switch (choice) {
//                case "1":
//                    System.out.println("Produkt: ");
//                    productName = scan.nextLine();
//                    break;
//                case "2":
//                    System.out.println("Kategori: ");
//                    catagory = scan.nextInt();
//                    break;
//                case "3":
//                    System.out.println("Pris: ");
//                    price = scan.nextLine();
//                    break;
//                case "4":
//                    Product tempProduct = new Product(productName, catagory, price);
//                    Object productHandler;
//                    productHandler.addProdctToList(tempProduct);
//                    run = false;
//                    break;
//                case "Q":
//                    run = false;
//                    break;
//                default:
//                    System.out.println("Måste välja 1 - 4  eller Q.");
//            }
//        }
//    }

