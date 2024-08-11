package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
        }
    }

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("role")


                    );


                }

            }
            ;

        }
        return null;
    }

    private User createUserByRole(String role, ResultSet rs) throws SQLException {
        if (role == null) {
            System.err.println("Role is null");
            throw new IllegalArgumentException("Role cannot be null");
        }

        String username = rs.getString("username");
        String password = rs.getString("password");
        String email = rs.getString("email");

        if ("buyer".equals(role)) {
            return new Buyer(username, password, email);
        } else if ("seller".equals(role)) {
            return new Seller(username, password, email);
        } else if ("admin".equals(role)) {
            return new Admin(username, password, email);
        } else {
            // Handle unexpected role - we can delete it if dont need it 
            System.err.println("Unexpected role found: " + role);
            throw new IllegalArgumentException("Unexpected role: " + role);
        }
    }


    // Public method to delete a user by username
    public void deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM users WHERE username = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role")
                );
                users.add(user);
            }
        }
        return users;
    }


}
