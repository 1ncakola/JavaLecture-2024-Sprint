package src;

import java.sql.*;


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
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                return createUserByRole(role, rs);
            }
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
    
}
