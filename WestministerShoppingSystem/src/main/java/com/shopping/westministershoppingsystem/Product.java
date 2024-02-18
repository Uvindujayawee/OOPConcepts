package com.shopping.westministershoppingsystem;

import java.io.Serializable;

public class Product  implements Serializable  {
    private String productId;
    private String productName;
    private String productCategory;
    private double productPrice;
    private int productAvailableQuantity;

    // Constructor
    public Product(String productId, String productName, String productCategory, double productPrice, int productAvailableQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory; 
        this.productPrice = productPrice;
        this.productAvailableQuantity = productAvailableQuantity;

    }

    // Getters and setters for all attributes
    public String getProductId() {
        return productId;
    }

    // Setter for setting the product
    public void setProductId(String productId) {
        this.productId = productId;
    }

    // Get the product name
    public String getProductName() {
        return productName;
    }

    // Set the product name
    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Get product available quantity
    public int getProductAvailableQuantity() {
        return productAvailableQuantity;
    }

    // Set product avilable Quantity
    public void setProductAvailableQuantity(int productAvailableQuantity) {
        this.productAvailableQuantity = productAvailableQuantity;
    }

    // Get product price
    public double getProductPrice() {
        return productPrice;
    }

    // Set product price
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    // get product category
    public String getProductCategory() {
        return productCategory;
    }

    // set product category
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    // Method to get product details
    public String getProductDetails() {
        return  "Product ID: " + productId +
                "\nProduct Name: " + productName +
                "\nProduct Category: " + productCategory +
                "\nPrice: $" + productPrice +
                "\nQuantity Available: " + productAvailableQuantity;
    }

    // Method to get product id
    public String getProductID() {
        return productId;
    }



}
