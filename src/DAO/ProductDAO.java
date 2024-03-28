package DAO;

import entity.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDAO {
    private File file;

    public ProductDAO(String filePath) {
        this.file = new File(filePath);
    }

    public List<Product> readProductsFromFile() throws FileNotFoundException {
        List<Product> products = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                Product product = new Product(Integer.parseInt(parts[0]), parts[1], parts[2], Double.parseDouble(parts[3]), Integer.parseInt(parts[4]));
                
                products.add(product);
            }
        }
        return products;
    }

    public void writeProductsToFile(List<Product> products) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            for (Product product : products) {
                writer.write(product.getId() + "," + product.getName() + "," + product.getCategory() + "," + product.getPrice() + "," + product.getQuantity() + "\n");
            }
        }
    }
}
