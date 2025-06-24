package com.e_commerce.product_service.service;


import com.e_commerce.product_service.model.Product;
import com.e_commerce.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Product save(Product p) {
        return repo.save(p);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Product> searchByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    public List<Product> searchByCategory(String category) {
        return repo.findByCategoryIgnoreCase(category);
    }
}
