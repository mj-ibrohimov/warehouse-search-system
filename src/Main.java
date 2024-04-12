package ui;

import DAO.ProductDAO;
import entity.Product;
import service.ProductService;

import java.util.List;
import java.io.FileNotFoundException; 

public class Main {
    public static void main(String[] args) {

        String inventoryFilePath = "inventory.csv";

        ProductDAO productDAO = new ProductDAO(inventoryFilePath);
        ProductService productService = new ProductService(productDAO);

        try {
            List<Product> products = productDAO.readProductsFromFile();

            for (Product product : products) {
                System.out.println(product); 
            }
        } catch (FileNotFoundException e) {
            System.err.println("Inventory file not found: " + e.getMessage());
        }
    }
}
