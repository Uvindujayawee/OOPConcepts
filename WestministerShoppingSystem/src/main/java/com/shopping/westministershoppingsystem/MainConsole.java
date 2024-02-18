package com.shopping.westministershoppingsystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainConsole {
    private static WestministerShoppingManager shoppingManager = new WestministerShoppingManager();
    private static Scanner scanner = new Scanner(System.in);
    private static Map<Integer, ActionStrategy> actionMap = initializeActionMap();

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            choice = getUserChoice();

            if (actionMap.containsKey(choice)) {
                actionMap.get(choice).execute();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);

        System.out.println("Exiting the program. Goodbye!");
    }

    private static void displayMenu() {
        System.out.println("\n===Shopping Console ===");
        System.out.println("1. Add a new product");
        System.out.println("2. Delete a product");
        System.out.println("3. Print the list of products");
        System.out.println("4. Save products to a file");
        System.out.println("0. Exit");
    }

    private static int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private static Map<Integer, ActionStrategy> initializeActionMap() {
        Map<Integer, ActionStrategy> map = new HashMap<>();
        map.put(1, new AddProductAction());
        map.put(2, new DeleteProductAction());
        map.put(3, new PrintProductsAction());
        map.put(4, new SaveToFileAction());
        map.put(0, new ExitAction());
        return map;
    }

    interface ActionStrategy {
        void execute();
    }

    static class AddProductAction implements ActionStrategy {
        private Scanner scanner = new Scanner(System.in);

        @Override
        public void execute() {
            
            // Main console Output
            System.out.println("Adding a new product...");

            System.out.println("Select product type:");
            System.out.println("1. Electronic");
            System.out.println("2. Clothing");
            int productTypeChoice = scanner.nextInt();

            if (productTypeChoice == 1) {
                addElectronicProduct();
            } else if (productTypeChoice == 2) {
                addClothingProduct();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        // Displaying add electric product
        private void addElectronicProduct() {
            System.out.print("Enter product ID: ");
            String productID = scanner.next();

            System.out.print("Enter product name: ");
            String productName = scanner.next();

            System.out.print("Enter product price: ");
            double productPrice = scanner.nextDouble();
            
            System.out.print("Enter No of products avilable: ");
            int availableProducts = scanner.nextInt();

            System.out.print("Enter brand: ");
            String electricBrand = scanner.next();

            System.out.print("Enter warranty period: ");
            int electricWarrantyPeriod = scanner.nextInt();

            // Additional electronic product details...
            Electronics electronicItem = new Electronics(productID, productName,"Electronics", productPrice, availableProducts ,electricBrand, electricWarrantyPeriod);
            shoppingManager.addProduct(electronicItem);
        }

        // displaying add clothing product
        private void addClothingProduct() {
            System.out.print("Enter product ID: ");
            String productID = scanner.next();

            System.out.print("Enter product name: ");
            String productName = scanner.next();

            System.out.print("Enter product price: ");
            double productPrice = scanner.nextDouble();
            
            System.out.print("Enter No of prooducts avilable: ");
            int availableProducts = scanner.nextInt();

            System.out.print("Enter size: ");
            String clothSize = scanner.next();

            System.out.print("Enter color: ");
            String clothColor = scanner.next();

            // Additional clothing product details...
            Clothing clothingItem = new Clothing(productID, productName, "Clothing", productPrice, availableProducts ,clothSize, clothColor);
            shoppingManager.addProduct(clothingItem);
        }
    }

    // Method for deleting the product
    static class DeleteProductAction implements ActionStrategy {
        @Override
        public void execute() {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the product ID to delete: ");
            String productID = scanner.next();

            shoppingManager.deleteProduct(productID);
        }
    }

    // Method for print products
    static class PrintProductsAction implements ActionStrategy {
        @Override
        public void execute() {
            shoppingManager.printProducts();
        }
    }

    // Method to save the added products to a file
    static class SaveToFileAction implements ActionStrategy {
        @Override
        public void execute() {
            shoppingManager.saveToFile("productLists.dat");
        }
    }

    // Method to exit action
    static class ExitAction implements ActionStrategy {
        @Override
        public void execute() {
            System.out.println("Exiting...");
            scanner.close();
            System.exit(0);
        }
    }
}
