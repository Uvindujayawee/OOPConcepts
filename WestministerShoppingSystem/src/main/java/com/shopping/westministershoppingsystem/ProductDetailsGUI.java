package com.shopping.westministershoppingsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class ProductDetailsGUI extends JFrame {
    private JTable productTable;
    private JComboBox<String> categoryDropdown;
    private List<Product> productList;  // Store loaded data
    private JTextArea selectedProductDetailsArea;

    // Product Details GUI
    public ProductDetailsGUI() {
        setTitle("Product Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);  // Increased height
        setLayout(new BorderLayout());

        // Create a JPanel for the controls (dropdown)
        JPanel controlPanel = new JPanel();

        // Create a JComboBox for selecting product categories
        String[] categories = {"All", "Clothing", "Electronics"};
        categoryDropdown = new JComboBox<>(categories);
        categoryDropdown.addActionListener(e -> filterByCategory());

        // Add the components to the control panel
        controlPanel.add(new JLabel("Select Product Category: "));
        controlPanel.add(categoryDropdown);

        // Add the control panel above the table
        add(controlPanel, BorderLayout.NORTH);

        // Create the table model and table
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Product ID", "Name", "Category", "Price", "Additional Info", "Items Available"}, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);

        // Add the table to the frame
        add(scrollPane, BorderLayout.CENTER);

        // Create a JTextArea for displaying selected product details
        selectedProductDetailsArea = new JTextArea();
        selectedProductDetailsArea.setEditable(false);

        // Set the preferred size of the JScrollPane containing the JTextArea
        JScrollPane textAreaScrollPane = new JScrollPane(selectedProductDetailsArea);
        textAreaScrollPane.setPreferredSize(new Dimension(800, 200));  // Increased height

        // Adding the text area below the table
        add(textAreaScrollPane, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

        // Load data from file and populate the table
        loadDataFromFile();

        // Add a selection listener to the table
        productTable.getSelectionModel().addListSelectionListener(e -> displaySelectedProductDetails());

        // Create the "Sort List" button
        JButton sortListButton = new JButton("Sort List");
        sortListButton.addActionListener(e -> sortListAction());

        // Add the "Sort List" button to the control panel
        controlPanel.add(sortListButton);
        setVisible(true);
    }
    
    // main method to invoke the product details GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductDetailsGUI());
    }

    // Method to read products from the file
    private void loadDataFromFile() {
        productList = new ArrayList<>();
        String filePath = "productLists.dat";

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            productList = (List<Product>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Populate the table with loaded data
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        for (Product product : productList) {
            String additionalInfo = getAdditionalInfo(product);
            model.addRow(new Object[]{product.getProductID(),
                product.getProductName(), 
                product.getProductCategory(), 
                product.getProductPrice(), 
                additionalInfo,
                product.getProductAvailableQuantity()});
        }
    }

    // Method to filter the table by selected category
    private void filterByCategory() {
        String selectedCategory = (String) categoryDropdown.getSelectedItem();
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();

        // Remove all rows from the table
        model.setRowCount(0);

        // Filter and add rows based on the selected category
        for (Product product : productList) {
            String additionalInfo = getAdditionalInfo(product);

            if (selectedCategory.equals("All") || selectedCategory.equals(product.getProductCategory())) {
                model.addRow(new Object[]{product.getProductID(), product.getProductName(), product.getProductCategory(), product.getProductPrice(), additionalInfo});
            }
        }
    }

    // Method to get additional information based on the product category
    private String getAdditionalInfo(Product product) {
        if ("Clothing".equals(product.getProductCategory()) && product instanceof Clothing) {
            Clothing clothingProduct = (Clothing) product;
            return "Size: " + clothingProduct.getClothSize() + ", Color: " + clothingProduct.getClothColor();
        } else if ("Electronics".equals(product.getProductCategory()) && product instanceof Electronics) {
            Electronics electronicsProduct = (Electronics) product;
            return "Brand: " + electronicsProduct.getElectronicBrand() + ", Warranty: " + electronicsProduct.getElectronicWarrantyPeriod();
        } else {
            return "";
        }
    }

    // Method to display selected product details in the text area
    private void displaySelectedProductDetails() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            String selectedProductDetails = "Selected Product Details:\n";
            for (int col = 0; col < productTable.getColumnCount(); col++) {
                selectedProductDetails += productTable.getColumnName(col) + ": " + productTable.getValueAt(selectedRow, col) + "\n";
            }
            selectedProductDetailsArea.setText(selectedProductDetails);
        } else {
            selectedProductDetailsArea.setText(""); // Clear the text area if no row is selected
        }
    }

    // Sort the list in alphabatical order
    private void sortListAction() {
        // Sort the productList alphabetically based on product name (case-sensitive)
        productList.sort(Comparator.comparing(Product::getProductName, String.CASE_INSENSITIVE_ORDER));

        // Clear the table
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0);

        // Repopulate the table with the sorted productList
        for (Product product : productList) {
            String additionalInfo = getAdditionalInfo(product);
            model.addRow(new Object[]{product.getProductID(), product.getProductName(), product.getProductCategory(), product.getProductPrice(), additionalInfo, product.getProductAvailableQuantity()});
        }

        // Clear the product details text area when sorting the list
        selectedProductDetailsArea.setText("");
    }

}
