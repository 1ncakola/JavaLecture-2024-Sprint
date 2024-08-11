package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO productss (name, price, quantity, seller_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getSellerId());
            stmt.executeUpdate();
        }
    }

    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE productss SET name = ?, price = ?, quantity = ? WHERE id = ? AND seller_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getId());
            stmt.setInt(5, product.getSellerId());
            stmt.executeUpdate();
        }

    }
        
    public List<Product> getProductsBySeller(int sellerId) throws SQLException {
        String sql = "SELECT * FROM productss WHERE seller_id = ?";
        List<Product> products = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sellerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Use the constructor that includes the 'id' field
                Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"), rs.getInt("seller_id"));
                products.add(product);

            }
        }
        return products;
    }
  

    public List<Product> getAllProducts() throws SQLException {
        String sql = "SELECT * FROM productss";
        List<Product> products = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int sellerId = rs.getInt("seller_id");
                products.add(new Product(id, name, price, quantity, sellerId));
            }
        }
        return products;
    }
    
    public List<Product> searchProductsByName(String name) throws SQLException {
        String sql = "SELECT * FROM productss WHERE name LIKE ?";
        List<Product> products = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"), rs.getInt("seller_id"));
                    products.add(product);
                }
            }
        }
        return products;
    }
    
    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM productss WHERE id = ?";
        Product product = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"), rs.getInt("seller_id"));
                }
            }
        }
        return product;
    }

    void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM productss WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}


    //Admin 





