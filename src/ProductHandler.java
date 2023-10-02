import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductHandler {
    private ArrayList<Product> list = new ArrayList<Product>();
    private String fileName = "Merchandise.txt";

    Product() {
        if (!createFile()) {
            try {
                Scanner scan = new Scanner(new File(fileName));
                while (scan.hasNextLine()) {
                    String product = scan.nextLine();
                    String[] productInfo = product.split(",");

                    Product tempProduct = new Product(
                            productInfo[0],
                            productInfo[1],
                            Integer.parseInt(productInfo[2])
                    );
                    list.add(tempProduct);
                }
            } catch (FileNotFoundException e) {
                System.out.println(" FEL!!! " + e.getMessage());
            }
        }
    }

    public ArrayList<Product> list() {
        return list;
    }

    boolean createFile() {
        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("Filen har skapats: " + file.getName());
            } else {
                System.out.println("Filen " + file.getName() + " finns redan!");
                System.out.println();
                return false;
            }
        } catch (IOException e) {
            System.out.println("Ett fel uppstod när vi skulle skapa filen. " + e.getMessage());
        }
        System.out.println();
        return true;
    }

    public void addProductToList(Product newProduct) {
        if (addProductToTextFile(newProduct)) {
            this.list.add(newProduct);
        }
    }

    private boolean addProductToTextFile(Product newProduct) {
        try {
            FileOutputStream fos = new FileOutputStream(this.fileName, true);
            PrintStream printStream = new PrintStream(fos);

            printStream.print("\n" + newProduct.formatedStringForFile());
            System.out.println(newProduct.formatedStringForFile());

            fos.close();
            printStream.close();
            return true;
        } catch (Exception e) {
            System.out.println("Det blev galet! " + e.getMessage());
        }

        return false;
    }

    public void removeProductToList(Product oldProduct) {
        if (removeProductToTextFile()){
            this.list.remove(oldProduct);
        }
    }

    private boolean removeProductToTextFile() {
        try {
            FileOutputStream fos = new FileOutputStream(this.fileName);
            PrintStream printStream = new PrintStream(fos);
            for (Product product: list) {
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
}
