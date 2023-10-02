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
}

