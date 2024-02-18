package com.shopping.westministershoppingsystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class WestministerShoppingManager implements ShoppingManager{
    private List<Product> productList;

    // Load the daved data from the file
    public WestministerShoppingManager() {
        this.productList = new ArrayList<>();
        loadFromFile("productLists.dat");
    }

    // Logic of adding the product
    @Override
    public void addProduct(Product product) {
        productList.add(product);
        System.out.println("Product added successfully.");
    }

    // Logic of deleting the product
    @Override
    public void deleteProduct(String productIdToDelete) {
        Product productToDelete = findProductByID(productIdToDelete);
        if (productToDelete != null) {
            productList.remove(productToDelete);
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found with ID: " + productIdToDelete);
        }
    }

    // Logic for printing the product
    @Override
    public void printProducts() {
        for (Product product : productList) {
            System.out.println("------------------------------");
            System.out.println(product.getProductDetails());
            System.out.println("------------------------------");
        }
    }

    // Logic to save the added product to a file
    @Override
    public void saveToFile(String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(productList);
            System.out.println("Product list saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
    
    // Logic to load the data from file
    @Override
    public void loadFromFile(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = inputStream.readObject();

            if (obj instanceof ArrayList) {
                productList = (ArrayList<Product>) obj;
                System.out.println("Product list loaded from file: " + filename);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }

    // logic to find the product from its id
    public Product findProductByID(String productID) {
        for (Product product : productList) {
            if (product.getProductID().equals(productID)) {
                return product;
            }
        }
        return null;
    }

    // Logic to get the product list
    @Override
    public List<Product> getProductList() {
        return productList;
    }

}
