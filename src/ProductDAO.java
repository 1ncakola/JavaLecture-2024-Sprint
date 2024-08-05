
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, price, quantity, seller_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setString(4, product.getSellerId());
            stmt.executeUpdate();
        }
    }

    public List<Product> getProductsBySeller(String sellerId) throws SQLException {
        String sql = "SELECT * FROM products WHERE seller_id = ?";
        List<Product> products = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sellerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Use the constructor that includes the 'id' field
                Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"), rs.getString("seller_id"));
                products.add(product);
            }
        }
        return products;
    }

  
}
