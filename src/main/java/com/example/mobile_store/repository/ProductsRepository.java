package com.example.mobile_store.repository;

import com.example.mobile_store.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findByIsHiddenFalse(); // Custom method
}
