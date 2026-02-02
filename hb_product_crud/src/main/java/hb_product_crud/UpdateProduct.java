package hb_product_crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;

public class UpdateProduct {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== UPDATE PRODUCT ===");
        System.out.print("Enter Product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
        
        Session session = factory.openSession();
        Transaction transaction = null;
        
        try {
            // First, get the product
            Product product = session.get(Product.class, id);
            
            if (product == null) {
                System.out.println("Product with ID " + id + " not found.");
                return;
            }
            
            System.out.println("\nCurrent Product Details:");
            System.out.println("1. Name: " + product.getName());
            System.out.println("2. Price: $" + product.getPrice());
            System.out.println("3. Quantity: " + product.getQuantity());
            System.out.println("4. Category: " + product.getCategory());
            System.out.println("5. Description: " + product.getDescription());
            System.out.println("6. Manufacturer: " + product.getManufacturer());
            
            System.out.println("\nSelect field to update (1-6) or 0 to update all: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            transaction = session.beginTransaction();
            
            if (choice == 0 || choice == 1) {
                System.out.print("Enter new Product Name: ");
                product.setName(scanner.nextLine());
            }
            
            if (choice == 0 || choice == 2) {
                System.out.print("Enter new Price: ");
                product.setPrice(scanner.nextDouble());
                scanner.nextLine(); // Consume newline
            }
            
            if (choice == 0 || choice == 3) {
                System.out.print("Enter new Quantity: ");
                product.setQuantity(scanner.nextInt());
                scanner.nextLine(); // Consume newline
            }
            
            if (choice == 0 || choice == 4) {
                System.out.print("Enter new Category: ");
                product.setCategory(scanner.nextLine());
            }
            
            if (choice == 0 || choice == 5) {
                System.out.print("Enter new Description: ");
                product.setDescription(scanner.nextLine());
            }
            
            if (choice == 0 || choice == 6) {
                System.out.print("Enter new Manufacturer: ");
                product.setManufacturer(scanner.nextLine());
            }
            
            session.update(product);
            transaction.commit();
            
            System.out.println("Product updated successfully!");
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error updating product: " + e.getMessage());
        } finally {
            session.close();
            factory.close();
            scanner.close();
        }
    }
}