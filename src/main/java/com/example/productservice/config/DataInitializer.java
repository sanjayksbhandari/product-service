package com.example.productservice.config;

import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Autowired
    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() == 0) {
            Product laptop = new Product(null, "Laptop XPS", "High-performance laptop", new BigDecimal("1299.99"), "Electronics", true);
            Product phone = new Product(null, "Smartphone Pro", "Latest smartphone model", new BigDecimal("899.99"), "Electronics", true);
            Product headphones = new Product(null, "Wireless Headphones", "Noise-cancelling headphones", new BigDecimal("199.99"), "Audio", true);
            Product desk = new Product(null, "Office Desk", "Ergonomic office desk", new BigDecimal("249.99"), "Furniture", true);
            Product chair = new Product(null, "Gaming Chair", "Comfortable gaming chair", new BigDecimal("179.99"), "Furniture", true);

            productRepository.saveAll(Arrays.asList(laptop, phone, headphones, desk, chair));

            System.out.println("Sample data initialized successfully!");
        }
    }
}
