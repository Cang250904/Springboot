package com.example.mobile_store.api;

import com.example.mobile_store.models.Product;
import com.example.mobile_store.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {

    @Autowired
    private ProductsRepository productRepository;


    @GetMapping("/getall")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {

        return productRepository.findById((long) id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {

        if (!productRepository.existsById((long) id)) {
            return ResponseEntity.notFound().build();
        }
        product.setId(id);
        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }


    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {

        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(201).body(savedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {

        if (!productRepository.existsById((long) id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById((long) id);
        return ResponseEntity.noContent().build();
    }


}
