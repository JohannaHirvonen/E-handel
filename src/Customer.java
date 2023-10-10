public class Customer {
    private String userID;
    private String password;

    private Order cart;

    public Customer(String userID, String password) {
        this.userID = userID;
        this.password = password;
        cart = new Order(this.userID);
    }
    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public Order getCart(){
        return cart;
    }

    public void clearCart(){
        cart = new Order(this.userID);
    }
    public String toFileString() {
        return this.userID + "," + this.password + ",";
    }

    public void setID(String newUserID) {
        userID = newUserID;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }
}
