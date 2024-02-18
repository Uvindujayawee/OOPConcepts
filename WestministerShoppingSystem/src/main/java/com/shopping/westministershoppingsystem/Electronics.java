package com.shopping.westministershoppingsystem;

public class Electronics extends Product {
    private String electronicBrand;
    private int electronicWarrantyPeriod; // Represented in months

    // Constructor with additional details
    public Electronics(String productId, String productName, String productCategory, double productPrice, int productAvailableQuantity, String electronicBrand, int electronicWarrantyPeriod) {
        super(productId, productName, productCategory, productPrice ,productAvailableQuantity);
        this.electronicBrand = electronicBrand;
        this.electronicWarrantyPeriod = electronicWarrantyPeriod;
    }

    // Getter and setter for brand
    public String getElectronicBrand() {
        return electronicBrand;
    }

    // Setting the electric brand
    public void setElectronicBrand(String electronicBrand) {
        this.electronicBrand = electronicBrand;
    }

    // Getter and setter for warranty period
    public int getElectronicWarrantyPeriod() {
        return electronicWarrantyPeriod;
    }

    // Set the electric warrenty period
    public void setElectronicWarrantyPeriod(int electronicWarrantyPeriod) {
        this.electronicWarrantyPeriod = electronicWarrantyPeriod;
    }

    // Override the method to include electronic-specific details
    @Override
    public String getProductDetails() {
        return super.getProductDetails() +
                "\nBrand: " + electronicBrand +
                "\nWarranty Period: " + electronicWarrantyPeriod + " months";
    }

}

