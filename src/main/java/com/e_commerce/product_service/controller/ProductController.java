package com.e_commerce.product_service.controller;

import com.e_commerce.product_service.model.Product;
import com.e_commerce.product_service.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Product create(@RequestBody Product p) {
        return service.save(p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String category) {
        if (name != null) return service.searchByName(name);
        if (category != null) return service.searchByCategory(category);
        return service.getAll();
    }
}
