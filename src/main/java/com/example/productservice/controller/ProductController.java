package com.example.productservice.controller;

import com.example.productservice.dto.ApiResponse;
import com.example.productservice.dto.ProductDTO;
import com.example.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product Controller", description = "CRUD operations for products")
public class ProductController {

    private final ProductService productService;
    
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieves a list of all products")
    public ResponseEntity<com.example.productservice.dto.ApiResponse<List<ProductDTO>>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        com.example.productservice.dto.ApiResponse<List<ProductDTO>> response = 
            new com.example.productservice.dto.ApiResponse<>(
                HttpStatus.OK.value(),
                "Products retrieved successfully",
                products
            );
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieves a product by its ID")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Product found"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<com.example.productservice.dto.ApiResponse<ProductDTO>> getProductById(
            @Parameter(description = "Product ID", required = true)
            @PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        com.example.productservice.dto.ApiResponse<ProductDTO> response = 
            new com.example.productservice.dto.ApiResponse<>(
                HttpStatus.OK.value(),
                "Product retrieved successfully",
                product
            );
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    @Operation(summary = "Create a new product", description = "Creates a new product")
    public ResponseEntity<com.example.productservice.dto.ApiResponse<ProductDTO>> createProduct(
            @Valid @RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        com.example.productservice.dto.ApiResponse<ProductDTO> response = 
            new com.example.productservice.dto.ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Product created successfully",
                createdProduct
            );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update a product", description = "Updates an existing product by ID")
    public ResponseEntity<com.example.productservice.dto.ApiResponse<ProductDTO>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        com.example.productservice.dto.ApiResponse<ProductDTO> response = 
            new com.example.productservice.dto.ApiResponse<>(
                HttpStatus.OK.value(),
                "Product updated successfully",
                updatedProduct
            );
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Deletes a product by ID")
    public ResponseEntity<com.example.productservice.dto.ApiResponse<Void>> deleteProduct(
            @PathVariable Long id) {
        productService.deleteProduct(id);
        com.example.productservice.dto.ApiResponse<Void> response = 
            new com.example.productservice.dto.ApiResponse<>(
                HttpStatus.OK.value(),
                "Product deleted successfully"
            );
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/category/{category}")
    @Operation(summary = "Get products by category", description = "Retrieves all products in a specific category")
    public ResponseEntity<com.example.productservice.dto.ApiResponse<List<ProductDTO>>> getProductsByCategory(
            @PathVariable String category) {
        List<ProductDTO> products = productService.getProductsByCategory(category);
        com.example.productservice.dto.ApiResponse<List<ProductDTO>> response = 
            new com.example.productservice.dto.ApiResponse<>(
                HttpStatus.OK.value(),
                "Products retrieved successfully",
                products
            );
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search products", description = "Searches products by name")
    public ResponseEntity<com.example.productservice.dto.ApiResponse<List<ProductDTO>>> searchProducts(
            @RequestParam String keyword) {
        List<ProductDTO> products = productService.searchProducts(keyword);
        com.example.productservice.dto.ApiResponse<List<ProductDTO>> response = 
            new com.example.productservice.dto.ApiResponse<>(
                HttpStatus.OK.value(),
                "Products retrieved successfully",
                products
            );
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/available")
    @Operation(summary = "Get available products", description = "Retrieves all available products")
    public ResponseEntity<com.example.productservice.dto.ApiResponse<List<ProductDTO>>> getAvailableProducts() {
        List<ProductDTO> products = productService.getAvailableProducts();
        com.example.productservice.dto.ApiResponse<List<ProductDTO>> response = 
            new com.example.productservice.dto.ApiResponse<>(
                HttpStatus.OK.value(),
                "Available products retrieved successfully",
                products
            );
        return ResponseEntity.ok(response);
    }
}