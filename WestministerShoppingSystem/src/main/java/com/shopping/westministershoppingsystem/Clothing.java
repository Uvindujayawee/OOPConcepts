package com.shopping.westministershoppingsystem;

public class Clothing extends Product {
    private String clothSize;
    private String clothColor;

    // Constructor
    public Clothing(String productId, String productName, String productCategory, double productPrice, int productAvailableQuantity, String size, String color) {
        super(productId, productName, productCategory, productPrice,  productAvailableQuantity);
        this.clothSize = size;
        this.clothColor = color;
    }
    
    // Getters and setters for additional attributes
    // Getter for get cloth size
    public String getClothSize() {
        return clothSize;
    }

    // setter for set cloth size
    public void setClothSize(String clothSize) {
        this.clothSize = clothSize;
    }

    // Getter for get cloth color
    public String getClothColor() {
        return clothColor;
    }

    // Setter for set cloth color
    public void setClothColor(String clothColor) {
        this.clothColor = clothColor;
    }

    // Override the method to include clothing-specific details
    @Override
    public String getProductDetails() {
        return super.getProductDetails() +
                "\nSize: " + clothSize +
                "\nColor: " + clothColor;
    }

}

