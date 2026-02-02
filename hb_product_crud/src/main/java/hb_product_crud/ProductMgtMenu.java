package hb_product_crud;

import java.util.Scanner;

public class ProductMgtMenu {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        
        System.out.println("=========================================");
        System.out.println("    PRODUCT MANAGEMENT SYSTEM - CRUD     ");
        System.out.println("=========================================");
        
        while (!exit) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Insert New Product");
            System.out.println("2. View/Select Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("\nEnter your choice (1-5): ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        callInsertProduct();
                        break;
                    case 2:
                        callSelectProduct();
                        break;
                    case 3:
                        callUpdateProduct();
                        break;
                    case 4:
                        callDeleteProduct();
                        break;
                    case 5:
                        exit = true;
                        System.out.println("\nThank you for using Product Management System!");
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter 1-5.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        
        scanner.close();
    }
    
    private static void callInsertProduct() {
        System.out.println("\nLaunching Insert Product...");
        InsertProduct.main(new String[]{});
        System.out.println("\nPress Enter to return to main menu...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void callSelectProduct() {
        System.out.println("\nLaunching Select Product...");
        SelectProduct.main(new String[]{});
        System.out.println("\nPress Enter to return to main menu...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void callUpdateProduct() {
        System.out.println("\nLaunching Update Product...");
        UpdateProduct.main(new String[]{});
        System.out.println("\nPress Enter to return to main menu...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void callDeleteProduct() {
        System.out.println("\nLaunching Delete Product...");
        DeleteProduct.main(new String[]{});
        System.out.println("\nPress Enter to return to main menu...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}