package src;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

//    public List<Product> getProductsBySeller(int sellerId) throws SQLException {
//        return productDAO.getProductsBySeller(sellerId);
//    }

//SELLER
    // Add Products
    public void addProduct(Product product) throws SQLException{
    productDAO.addProduct(product);
}
    //Update Product
    public void updateProduct(Product product) throws SQLException {
        productDAO.updateProduct(product);
    }

    //Delete product
    public void deleteProduct(int id) throws SQLException {
        productDAO.deleteProduct(id);
    }
    //View My Products
    public Product getProductById1(int id) throws SQLException {
        return productDAO.getProductById(id);
    }

    //BUYER
    //Browse Products
    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }
    //Search Products
    public List<Product> searchProductsByName(String user1) throws SQLException {
        return productDAO.searchProductsByName(user1);//maybe the name need to be change.
    }
    //View Products
    public Product getProductById(int id) throws SQLException {
        return productDAO.getProductById(id);
    }
    //View all Products

    public List<Product> viewAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }

}
