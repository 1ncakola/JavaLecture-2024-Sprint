
import java.sql.SQLException;
import java.util.Scanner;


public class MainMenu {
    private final  Scanner scanner;
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
            if (user != null) {
                System.out.println("Login successful!");
                if (user instanceof Buyer) {
                    buyerMenu();
                } else if (user instanceof Seller) {
                    sellerMenu();
                } else if (user instanceof Admin) {
                    adminMenu();
                }
            } else {
                System.out.println("Invalid credentials.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void buyerMenu() {
        while (true) {
            System.out.println("Buyer Menu");
            System.out.println("1. Browse products");
            System.out.println("2. Search for a product");
            System.out.println("3. View product details");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                }
                case 2 -> {
                }
                case 3 -> {
                }
                case 4 -> {
                    return;
                }
            }
            // Browse products
            // Search for a product
            // View product details
                    }
    }

    private void sellerMenu() {
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
                case 1 -> {
                }
                case 2 -> {
                }
                case 3 -> {
                }
                case 4 -> {
                }
                case 5 -> {
                    return;
                }
            }
            // Add product
            // Update product
            // Delete product
            // View my products
                    }
    }

    private void adminMenu() {
        while (true) {
            System.out.println("Admin Menu");
            System.out.println("1. View all users");
            System.out.println("2. Delete a user");
            System.out.println("3. View all products");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                }
                case 2 -> {
                }
                case 3 -> {
                }
                case 4 -> {
                    return;
                }
            }
           
                    }
    }
}
