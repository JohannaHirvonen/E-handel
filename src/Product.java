public class Product {
    private String name;
    private String catagory;
    private int numberOfProduct;
    private int price;

    public Product(String name, String catagory, int numberOfProduct, int price) {
        this.name = name;
        this.catagory = catagory;
        this.numberOfProduct = numberOfProduct;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCatagory() {
        return catagory;
    }

    public int getNumberOfProduct(){
        return numberOfProduct;
    }

    public int getPrice() {
        return price;
    }

    public String formatedStringForFile() {
        return this.name + "," + this.catagory + "," + this.numberOfProduct + "" + this.price;
    }

}

