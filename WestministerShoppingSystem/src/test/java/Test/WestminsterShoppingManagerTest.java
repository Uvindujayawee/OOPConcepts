package Test;

import com.shopping.westministershoppingsystem.Electronics;
import com.shopping.westministershoppingsystem.Product;
import com.shopping.westministershoppingsystem.WestministerShoppingManager;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WestminsterShoppingManagerTest {

    private WestministerShoppingManager manager;

    @BeforeMethod
    public void setUp() {
        manager = new WestministerShoppingManager();
    }

    // Test case for adding a test to the system
    @Test
    public void testAddProduct() {
        Electronics electronicsProduct = new Electronics("E001", "Laptop", "Electronics", 1000.0, 5 ,"Dell", 12);

        manager.addProduct(electronicsProduct);

        List<Product> productList = manager.getProductList();
        Assert.assertTrue(productList.contains(electronicsProduct));
    }
    
    // Test case for save the file and load from the file
    @Test
    public void testSaveToFileAndLoadFromFile() {
        Electronics electronicsProduct = new Electronics("E002", "Smartphone", "Electronics", 500.0,8, "Samsung", 12);

        manager.addProduct(electronicsProduct);

        String fileName = "testProductLists.dat";
        manager.saveToFile(fileName);

        WestministerShoppingManager manager = new WestministerShoppingManager();
        manager.loadFromFile(fileName);

        List<Product> loadedList = manager.getProductList();
        Assert.assertNotNull(loadedList.contains(electronicsProduct));
    }

    // Test case for printing the product details 
    @Test
    public void testPrintProductList() {
        try {
            manager.printProducts();
        } catch (Exception e) {
            Assert.fail("Exception not expected while printing product list");
        }
    }

}