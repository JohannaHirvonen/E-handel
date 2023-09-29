import java.util.ArrayList;
import java.util.List;

public class LoginSystem {
    private List<User> users;

    public LoginSystem() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin(String username) {
        // Ange användarnamnet för admin
        String adminUsername = "admin";
        return username.equals(adminUsername);
    }

    public static void main(String[] args) {
        LoginSystem loginSystem = new LoginSystem();

        // Skapa användare och lägg till dem i systemet
        User user1 = new User("user1", "password1");
        User user2 = new User("user2", "password2");
        User adminUser = new User("admin", "adminpassword");

        loginSystem.addUser(user1);
        loginSystem.addUser(user2);
        loginSystem.addUser(adminUser);

        // Försök att logga in som användare eller admin
        String usernameToLogin = "user1";
        String passwordToLogin = "password1";

        if (loginSystem.authenticate(usernameToLogin, passwordToLogin)) {
            System.out.println("Inloggning lyckades!");
            if (loginSystem.isAdmin(usernameToLogin)) {
                System.out.println("Du är en admin.");
            }
        } else {
            System.out.println("Inloggning misslyckades.");
        }
    }
}

// private List<Customer> customers;
//private Customer loggedInCustomer;

// Konstruktor och metoder för inloggning och utloggning
}
