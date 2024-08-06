package src;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public void addProduct(Product product) throws SQLException {
        productDAO.addProduct(product);
    }

    public List<Product> getProductsBySeller(String sellerId) throws SQLException {
        return productDAO.getProductsBySeller(sellerId);
    }

    
}
