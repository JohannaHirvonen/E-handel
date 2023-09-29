public class Main {
    public static void main(String[] args) {
        System.out.println("Hello group!");

        System.out.println(Product.list());
        Product vara = new Product();

        vara.createFile();
    }
}