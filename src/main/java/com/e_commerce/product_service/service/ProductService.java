package com.e_commerce.product_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.e_commerce.product_service.model.Product;
import com.e_commerce.product_service.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    // Ambil semua produk
    public List<Product> getAll() {
        return repo.findAll();
    }

    // Ambil produk berdasarkan ID
    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Simpan produk baru atau update produk
    public Product save(Product p) {
        return repo.save(p);
    }

    // Hapus produk berdasarkan ID
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // Cari produk berdasarkan nama (case-insensitive, contains)
    public List<Product> searchByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    // Cari produk berdasarkan kategori (case-insensitive, exact match)
    public List<Product> searchByCategory(String category) {
        return repo.findByCategoryIgnoreCase(category);
    }

    // Kurangi stok produk (dipanggil dari order-service)
    public boolean reduceStock(Long productId, int quantity) {
        Product product = getById(productId);
        if (product == null || product.getStock() < quantity) {
            return false;
        }

        product.setStock(product.getStock() - quantity);
        save(product);
        return true;
    }

    // Update produk secara manual dengan ID dan data baru
    public Product updateProduct(Long id, Product newData) {
        Product existing = getById(id);
        if (existing == null) {
            return null;
        }

        existing.setName(newData.getName());
        existing.setDescription(newData.getDescription());
        existing.setPrice(newData.getPrice());
        existing.setStock(newData.getStock());
        existing.setCategory(newData.getCategory());
        existing.setBrand(newData.getBrand());

        return save(existing);
    }
}
