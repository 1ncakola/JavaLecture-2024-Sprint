package src;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String sellerId;

    public Product(int id, String name, double price, int quantity, String sellerId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sellerId = sellerId;
    }



    // Getters and Setters

    public int getId() {return id;}
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setEmail(Double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getSellerId() { return sellerId; }
    public void setSellerId(String sellerid) { this.sellerId = sellerid; }

}
