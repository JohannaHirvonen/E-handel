import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Product {
    private String name;
    private String catagory;
    private int price;

    public Product(String name, String catagory, int price) {
        this.name = name;
        this.catagory = catagory;
        this.price = price;
    }

    public String name() {
        return name;
    }

    public String catagory() {
        return catagory;
    }

    public int price() {
        return price;
    }
    public int getPrice() {
        return price;
    }

    public String formatedStringForFile() {
        return this.name + "," + this.catagory + "," + this.price;
    }


    private static ArrayList<Product> list = new ArrayList<Product>();
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

    public static ArrayList<Product> list() {
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
            System.out.println("Någ gick år HELV*TE! " + e.getMessage());
        }

        return false;
    }
}

