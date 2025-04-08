package com.example.mobile_store.services;

import com.example.mobile_store.models.Product;
import com.example.mobile_store.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    // Retrieve all products
    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    // Retrieve visible products (not hidden)
    public List<Product> getVisibleProducts() {
        return productsRepository.findByIsHiddenFalse();
    }

    // Retrieve a product by ID
    public Optional<Product> getProductById(Long id) {
        return productsRepository.findById(id);
    }

    // Save or update a product
    public Product saveProduct(Product product) {
        return productsRepository.save(product);
    }

    // Delete a product by ID with validation
    @Transactional
    public void deleteProductById(Long id) {
        if (productsRepository.existsById(id)) {
            productsRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Product with ID " + id + " does not exist.");
        }
    }

    // Mark a product as "hidden" (uncomment if needed)
    // @Transactional
    // public boolean hideProduct(Long id) {
    // Optional<Product> optionalProduct = productsRepository.findById(id);
    // if (optionalProduct.isPresent()) {
    // Product product = optionalProduct.get();
    // product.setHidden(true);
    // productsRepository.save(product);
    // return true;
    // }
    // return false;
    // }
}