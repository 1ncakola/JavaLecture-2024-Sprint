
import java.sql.SQLException;



public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void registerUser(User user) throws SQLException {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userDAO.addUser(user);
    }

    public User authenticateUser(String username, String password) throws SQLException {
        User user = userDAO.getUserByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }

}
