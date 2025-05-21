package com.example.productservice.controller;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllProducts() throws Exception {
        // Prepare test data
        ProductDTO product1 = new ProductDTO(1L, "Test Product 1", "Description 1", new BigDecimal("10.99"), "Category 1", true);
        ProductDTO product2 = new ProductDTO(2L, "Test Product 2", "Description 2", new BigDecimal("20.99"), "Category 2", true);
        List<ProductDTO> products = Arrays.asList(product1, product2);

        // Mock service method
        when(productService.getAllProducts()).thenReturn(products);

        // Perform GET request and validate
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Products retrieved successfully"))
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].name").value("Test Product 1"))
                .andExpect(jsonPath("$.data[1].id").value(2))
                .andExpect(jsonPath("$.data[1].name").value("Test Product 2"));
    }

    @Test
    public void testCreateProduct() throws Exception {
        // Prepare test data
        ProductDTO inputProduct = new ProductDTO(null, "New Product", "Description", new BigDecimal("15.99"), "Category", true);
        ProductDTO createdProduct = new ProductDTO(1L, "New Product", "Description", new BigDecimal("15.99"), "Category", true);

        // Mock service method
        when(productService.createProduct(any(ProductDTO.class))).thenReturn(createdProduct);

        // Perform POST request and validate
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputProduct)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("Product created successfully"))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("New Product"))
                .andExpect(jsonPath("$.data.price").value(15.99));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        // Prepare test data
        Long productId = 1L;
        ProductDTO updateProduct = new ProductDTO(productId, "Updated Product", "Updated Description", new BigDecimal("25.99"), "Updated Category", true);

        // Mock service method
        when(productService.updateProduct(eq(productId), any(ProductDTO.class))).thenReturn(updateProduct);

        // Perform PUT request and validate
        mockMvc.perform(put("/products/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Product updated successfully"))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("Updated Product"))
                .andExpect(jsonPath("$.data.price").value(25.99));
    }
}