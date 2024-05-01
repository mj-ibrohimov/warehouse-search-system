package service;

import dao.ProductDAO;
import entity.Product;

import java.io.FileNotFoundException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() throws FileNotFoundException {
        return productDAO.readProductsFromFile();
    }
}
