package com.shopping.westministershoppingsystem;

import java.util.List;

// Interface for shopping manager
public interface ShoppingManager {
    void addProduct(Product product);
    void deleteProduct(String productID);
    void printProducts();
    void saveToFile(String filename);
    void loadFromFile(String filename);
    List<Product> getProductList();
}

