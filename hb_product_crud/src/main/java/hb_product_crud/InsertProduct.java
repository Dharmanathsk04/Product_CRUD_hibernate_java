package hb_product_crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;

public class InsertProduct {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== INSERT PRODUCT ===");
        
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();
        
        System.out.print("Enter Product Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter Product Category: ");
        String category = scanner.nextLine();
        
        System.out.print("Enter Product Description: ");
        String description = scanner.nextLine();
        
        System.out.print("Enter Manufacturer: ");
        String manufacturer = scanner.nextLine();
        
        // Create Product object
        Product product = new Product(name, price, quantity, category, description, manufacturer);
        
        // Hibernate operations
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
        
        Session session = factory.openSession();
        Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            System.out.println("Product inserted successfully! Product ID: " + product.getId());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error inserting product: " + e.getMessage());
        } finally {
            session.close();
            factory.close();
        }
        
        scanner.close();
    }
}