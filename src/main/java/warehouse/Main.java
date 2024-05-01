package ui;

import dao.ProductDAO;
import entity.Product;
import service.ProductService;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inventoryFilePath = "inventory.csv";

        ProductDAO productDAO = new ProductDAO(inventoryFilePath);
        ProductService productService = new ProductService(productDAO);

        try {
            // Read products from the file
            List<Product> products = productDAO.readProductsFromFile();

            // Display products
            for (Product product : products) {
                System.out.println(product);
            }

            // Instantiate and start CommandLineInterface
            CommandLineInterface cli = new CommandLineInterface(productService);
            cli.start();

        } catch (FileNotFoundException e) {
            System.err.println("Inventory file not found: " + e.getMessage());
        }
    }
}
