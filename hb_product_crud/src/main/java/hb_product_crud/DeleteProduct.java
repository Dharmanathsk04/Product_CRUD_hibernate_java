package hb_product_crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;

public class DeleteProduct {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== DELETE PRODUCT ===");
        System.out.print("Enter Product ID to delete: ");
        int id = scanner.nextInt();
        
        // Confirmation
        System.out.print("Are you sure you want to delete product with ID " + id + "? (yes/no): ");
        scanner.nextLine(); // Consume newline
        String confirmation = scanner.nextLine();
        
        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Deletion cancelled.");
            scanner.close();
            return;
        }
        
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
        
        Session session = factory.openSession();
        Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            
            // Get the product first
            Product product = session.get(Product.class, id);
            
            if (product != null) {
                session.delete(product);
                transaction.commit();
                System.out.println("Product with ID " + id + " deleted successfully!");
            } else {
                System.out.println("Product with ID " + id + " not found.");
            }
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error deleting product: " + e.getMessage());
        } finally {
            session.close();
            factory.close();
            scanner.close();
        }
    }
}