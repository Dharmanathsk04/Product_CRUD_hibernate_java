package hb_product_crud;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;
    
    @Column(name = "product_name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "product_price", nullable = false)
    private double price;
    
    @Column(name = "product_quantity", nullable = false)
    private int quantity;
    
    @Column(name = "product_category", length = 50)
    private String category;
    
    @Column(name = "product_description", length = 255)
    private String description;
    
    @Column(name = "manufacturer", length = 100)
    private String manufacturer;
    
    // Constructors
    public Product() {
    }
    
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public Product(String name, double price, int quantity, String category, 
                   String description, String manufacturer) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.description = description;
        this.manufacturer = manufacturer;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getManufacturer() {
        return manufacturer;
    }
    
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    @Override
    public String toString() {
        return String.format("Product [ID=%d, Name=%s, Price=%.2f, Quantity=%d, Category=%s, Manufacturer=%s]",
                id, name, price, quantity, category, manufacturer);
    }
}