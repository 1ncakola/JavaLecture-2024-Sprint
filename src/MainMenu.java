package src;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class MainMenu {
    private final Scanner scanner;
    private final UserService userService;
    private final ProductService productService;

    public MainMenu() {
        this.scanner = new Scanner(System.in);
        this.userService = new UserService();
        this.productService = new ProductService();
    }

    // we might change to if else as well
    public void start() {
        while (true) {
            System.out.println("Welcome to the E-Commerce Platform");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> System.exit(0);
            }
        }
    }

    private void register() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter role (buyer, seller, admin):");
        String role = scanner.nextLine();

        User user = null;
        if (role.equalsIgnoreCase("buyer")) {
            user = new Buyer(username, password, email);
        } else if (role.equalsIgnoreCase("seller")) {
            user = new Seller(username, password, email);
        } else if (role.equalsIgnoreCase("admin")) {
            user = new Admin(username, password, email);
        }

        if (user != null) {
            try {
                userService.registerUser(user);
                System.out.println("Registration successful!");
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid role.");
        }
    }

    private void login() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();


        try {
            User user = userService.authenticateUser(username, password);
            user.getId();
            System.out.println(user.getId());
            System.out.println(user.getRole());
            System.out.println("Login successful!");
            user.getId();
            if (user.getRole().equalsIgnoreCase("Buyer")) {
                buyerMenu(user);
            } else if (user.getRole().equalsIgnoreCase("Seller")) {
                sellerMenu(user);
                System.out.println(user.getId());
            } else if (user.getRole().equalsIgnoreCase("Admin")) {
                adminMenu();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void buyerMenu(User user) throws SQLException {
        while (true) {
            System.out.println("Buyer Menu");
            System.out.println("1. Browse products");
            System.out.println("2. Search for a product");
            System.out.println("3. View product details");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> browseProducts();

                case 2 -> searchProduct(user);

                case 3 -> viewProductDetails();

                case 4 -> {
                    return;
                }
            }

        }
    }

    private void sellerMenu(User user) throws SQLException {
        while (true) {
            System.out.println("Seller Menu");
            System.out.println("1. Add product");
            System.out.println("2. Update product");
            System.out.println("3. Delete product");
            System.out.println("4. View my products");
            System.out.println("5. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addProduct(user);

                case 2 -> updateProduct();

                case 3 -> deleteProduct();

                case 4 -> viewMyProducts();

                case 5 -> {
                    return;
                }

            }

        }
    }

    private void adminMenu() {
        while (true) {
            System.out.println("Admin Menu");
            System.out.println("1. View all users");
            System.out.println("2. Delete an user");
            System.out.println("3. View all products");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewAllUsers();

                case 2 -> deleteAnUser();

                case 3 -> viewAllProducts();

                case 4 -> {
                    return;
                }
            }

        }
    }


//Buyer

    //Browser products
    private void browseProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            if (products.isEmpty()) {
                System.out.println("No products available.");
            } else {
                for (Product product : products) {
                    System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //SearchProduct
    private void searchProduct(User user) throws SQLException {
        System.out.println("Enter product name to search:");
        String user1 = scanner.nextLine();

        List<Product> products = productService.searchProductsByName(user1);
        for (Product product : products) {
            System.out.println("ID: " + product.getId() + ", Name: " + product.getName() +
                    ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity() +
                    ", Seller: " + product.getSellerId());
        }
    }

    //View product details
    private void viewProductDetails() throws SQLException {
        System.out.println("Enter product ID to view details:");
        int productId = scanner.nextInt();

        Product product = productService.getProductById(productId);
        if (product != null) {
            System.out.println("ID: " + product.getId() + ", Name: " + product.getName() +
                    ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity() +
                    ", Seller ID: " + product.getSellerId());
        } else {
            System.out.println("Product not found.");
        }
    }

    //Seller
    //AddProduct
    private void addProduct(User user) {
        System.out.println(user.getId());
        System.out.println("Enter product name:");
        String name = scanner.nextLine();
        System.out.println("Enter product price:");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter product quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Your Id");
        int id = scanner.nextInt();


        Product product = new Product(0, name, price, quantity, id);
        try {
            productService.addProduct(product);
            System.out.println("Product added successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //UpdateProduct
    private void updateProduct() {
        System.out.println("Enter product ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new product name:");
        String name = scanner.nextLine();
        System.out.println("Enter new product price:");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter new product quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter seller Id:");
        int sellerId = scanner.nextInt();
        scanner.nextLine();

        Product product = new Product(id, name, price, quantity, sellerId);
        try {
            productService.updateProduct(product);
            System.out.println("Product updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //DeleteProduct
    private void deleteProduct() {
        System.out.println("Enter product ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            productService.deleteProduct(id);
            System.out.println("Product deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //ViewProduct
    private void viewMyProducts() throws SQLException {
        System.out.println("Enter product ID to view details:");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Product product = productService.getProductById1(id);
            if (product != null) {
                System.out.println("ID: " + product.getId());
                System.out.println("Name: " + product.getName());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Quantity: " + product.getQuantity());
                System.out.println("Seller ID: " + product.getSellerId());
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());

        }
    }

    //Admin
    private void viewAllProducts() {
        try {
            List<Product> products = productService.viewAllProducts();
            if (products.isEmpty()) {
                System.out.println("No products found.");
            } else {
                for (Product product : products) {
                    System.out.println("ID: " + product.getId() + ", Name: " + product.getName() +
                            ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity() +
                            ", Seller ID: " + product.getSellerId());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
// delete user
    private void deleteAnUser() {
        System.out.print("Enter username to delete: ");
        String username = scanner.nextLine();

        try {
            User user = userService.getUserByUsername(username);
            if (user != null) {
                userService.deleteUser(username);
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    private void viewAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            if (users.isEmpty()) {
                System.out.println("No users found.");
            } else {
                for (User user : users) {
                    System.out.println("Username: " + user.getUsername() + ", Email: " + user.getEmail() + ", Role: " + user.getRole());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
    }

}
