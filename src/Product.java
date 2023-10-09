public class Product {
    private String name;
    private String catagory;
    private double price;

    public Product(String name, String catagory, double price) {
        this.name = name;
        this.catagory = catagory;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCatagory() {
        return catagory;
    }

    public double getPrice() {
        return price;
    }

    public String formatedStringForFile() {
        return this.name + "," + this.catagory + "," + this.price;
    }

}

