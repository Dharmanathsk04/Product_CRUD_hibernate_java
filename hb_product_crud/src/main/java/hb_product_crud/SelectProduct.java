package hb_product_crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Scanner;

public class SelectProduct {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== SELECT PRODUCT OPERATIONS ===");
        System.out.println("1. View All Products");
        System.out.println("2. View Product by ID");
        System.out.println("3. Search Product by Name");
        System.out.println("4. View Products by Category");
        System.out.println("5. View Out of Stock Products");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
        
        Session session = factory.openSession();
        
        try {
            switch (choice) {
                case 1:
                    viewAllProducts(session);
                    break;
                case 2:
                    viewProductById(session, scanner);
                    break;
                case 3:
                    searchProductByName(session, scanner);
                    break;
                case 4:
                    viewProductsByCategory(session, scanner);
                    break;
                case 5:
                    viewOutOfStockProducts(session);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } finally {
            session.close();
            factory.close();
            scanner.close();
        }
    }
    
    private static void viewAllProducts(Session session) {
        System.out.println("\n=== ALL PRODUCTS ===");
        
        Query<Product> query = session.createQuery("FROM Product", Product.class);
        List<Product> products = query.list();
        
        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        
        System.out.println("+-----+------------------------------+----------+----------+----------------+");
        System.out.println("| ID  | Product Name                | Price    | Quantity | Category       |");
        System.out.println("+-----+------------------------------+----------+----------+----------------+");
        
        for (Product product : products) {
            System.out.printf("| %-3d | %-28s | $%-7.2f | %-8d | %-14s |\n",
                    product.getId(),
                    product.getName().length() > 28 ? product.getName().substring(0, 25) + "..." : product.getName(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getCategory());
        }
        System.out.println("+-----+------------------------------+----------+----------+----------------+");
        System.out.println("Total Products: " + products.size());
    }
    
    private static void viewProductById(Session session, Scanner scanner) {
        System.out.print("Enter Product ID: ");
        int id = scanner.nextInt();
        
        Product product = session.get(Product.class, id);
        
        if (product != null) {
            System.out.println("\n=== PRODUCT DETAILS ===");
            System.out.println("ID: " + product.getId());
            System.out.println("Name: " + product.getName());
            System.out.println("Price: $" + product.getPrice());
            System.out.println("Quantity: " + product.getQuantity());
            System.out.println("Category: " + product.getCategory());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Manufacturer: " + product.getManufacturer());
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }
    
    private static void searchProductByName(Session session, Scanner scanner) {
        System.out.print("Enter product name or part of name: ");
        String name = scanner.nextLine();
        
        Query<Product> query = session.createQuery(
            "FROM Product WHERE name LIKE :productName", Product.class);
        query.setParameter("productName", "%" + name + "%");
        
        List<Product> products = query.list();
        
        if (products.isEmpty()) {
            System.out.println("No products found with name containing: " + name);
            return;
        }
        
        System.out.println("\n=== SEARCH RESULTS ===");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("Found " + products.size() + " product(s).");
    }
    
    private static void viewProductsByCategory(Session session, Scanner scanner) {
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        
        Query<Product> query = session.createQuery(
            "FROM Product WHERE category = :categoryName", Product.class);
        query.setParameter("categoryName", category);
        
        List<Product> products = query.list();
        
        if (products.isEmpty()) {
            System.out.println("No products found in category: " + category);
            return;
        }
        
        System.out.println("\n=== PRODUCTS IN CATEGORY: " + category.toUpperCase() + " ===");
        for (Product product : products) {
            System.out.printf("ID: %d, Name: %s, Price: $%.2f, Quantity: %d\n",
                    product.getId(), product.getName(), product.getPrice(), product.getQuantity());
        }
        System.out.println("Total in category: " + products.size());
    }
    
    private static void viewOutOfStockProducts(Session session) {
        Query<Product> query = session.createQuery(
            "FROM Product WHERE quantity = 0", Product.class);
        
        List<Product> products = query.list();
        
        if (products.isEmpty()) {
            System.out.println("No out of stock products.");
            return;
        }
        
        System.out.println("\n=== OUT OF STOCK PRODUCTS ===");
        System.out.println("ID\tName\t\t\tPrice");
        System.out.println("------------------------------------------");
        
        for (Product product : products) {
            System.out.printf("%d\t%-20s\t$%.2f\n",
                    product.getId(), product.getName(), product.getPrice());
        }
        System.out.println("Total out of stock: " + products.size());
    }
}