package com.springboot.crud.apiController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud.entity.Category;
import com.springboot.crud.entity.Product;
import com.springboot.crud.repository.CategoryRepository;
import com.springboot.crud.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api")
public class ApiController {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    // CRUD endpoints for categories and products
    
    // Example for creating a new category
    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
    
    // Example for creating a new product under a specific category
    @PostMapping("/categories/{categoryId}/products")
    public Product createProduct(@PathVariable Long categoryId, @RequestBody Product product) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
        product.setCategory(category);
        return productRepository.save(product);
    }
    
    // Other CRUD operations (GET, PUT, DELETE) can be implemented similarly
    
    
    // Get all categories
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    // Get all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    // Get products by category
    @GetMapping("/categories/{categoryId}/products")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
    
    // Update a category
    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId, @RequestBody Category updatedCategory) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
        category.setName(updatedCategory.getName());
        return categoryRepository.save(category);
    }
    
    // Update a product
    @PutMapping("/products/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        return productRepository.save(product);
    }
    
    // Delete a category
    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
        categoryRepository.delete(category);
        return ResponseEntity.ok().build();
    }
    
    // Delete a product
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }
}
