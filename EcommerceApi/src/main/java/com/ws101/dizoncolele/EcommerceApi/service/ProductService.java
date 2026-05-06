package com.ws101.dizoncolele.EcommerceApi.service;

import com.ws101.dizoncolele.EcommerceApi.model.Product;
import com.ws101.dizoncolele.EcommerceApi.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Layer using JPA Repository
 * Replaces ArrayList logic
 */
@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    // GET ALL
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    // GET BY ID
    public Product getProduct(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    // CREATE
    public Product create(Product product) {
        return repo.save(product);
    }

    // UPDATE
    public Product update(Long id, Product updated) {
        Product existing = getProduct(id);
        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        return repo.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // FILTER BY CATEGORY
    public List<Product> getByCategory(String name) {
        return repo.findByCategoryName(name);
    }

    // FILTER BY PRICE RANGE
    public List<Product> getByPriceRange(double min, double max) {
        return repo.findProductsByPriceRange(min, max);
    }
}