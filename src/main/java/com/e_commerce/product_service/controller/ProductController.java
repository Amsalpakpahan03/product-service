package com.e_commerce.product_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.product_service.model.Product;
import com.e_commerce.product_service.service.ProductService;
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // Get semua produk
    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Product product = service.getById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    // Cari nama/kategori
    @GetMapping("/search")
    public List<Product> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {
        if (name != null) return service.searchByName(name);
        if (category != null) return service.searchByCategory(category);
        return service.getAll();
    }

    // Tambah produk
    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.save(product);
    }

    // Update produk
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product updated) {
        Product existing = service.getById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setStock(updated.getStock());
        existing.setCategory(updated.getCategory());
        existing.setBrand(updated.getBrand());

        return ResponseEntity.ok(service.save(existing));
    }

    // Hapus produk
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (service.getById(id) == null)
            return ResponseEntity.notFound().build();

        service.delete(id);
        return ResponseEntity.ok("Produk berhasil dihapus");
    }

    // Kurangi stok (sudah kita bahas sebelumnya)
    @PutMapping("/{id}/reduce-stock")
    public ResponseEntity<String> reduceStock(@PathVariable Long id, @RequestParam int quantity) {
        Product product = service.getById(id);
        if (product == null) return ResponseEntity.notFound().build();
        if (product.getStock() < quantity)
            return ResponseEntity.badRequest().body("Stok tidak cukup");

        product.setStock(product.getStock() - quantity);
        service.save(product);
        return ResponseEntity.ok("Stok dikurangi");
    }
}
