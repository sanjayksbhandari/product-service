package com.example.productservice.service;

import com.example.productservice.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
    List<ProductDTO> getProductsByCategory(String category);
    List<ProductDTO> searchProducts(String keyword);
    List<ProductDTO> getAvailableProducts();
}