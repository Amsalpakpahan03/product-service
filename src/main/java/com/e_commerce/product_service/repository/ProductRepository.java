package com.e_commerce.product_service.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_commerce.product_service.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategoryIgnoreCase(String category);
}
