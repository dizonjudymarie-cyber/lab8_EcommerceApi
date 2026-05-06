package com.ws101.dizoncolele.EcommerceApi.controller;

import com.ws101.dizoncolele.EcommerceApi.model.Product;
import com.ws101.dizoncolele.EcommerceApi.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Product API
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // GET ALL
    @GetMapping
    public List<Product> getAll() {
        return service.getAllProducts();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getProduct(id);
    }

    // CREATE
    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return service.update(id, product);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // FILTER BY CATEGORY
    @GetMapping("/category/{name}")
    public List<Product> getByCategory(@PathVariable String name) {
        return service.getByCategory(name);
    }

    // FILTER BY PRICE RANGE
    @GetMapping("/price")
    public List<Product> getByPriceRange(
            @RequestParam double min,
            @RequestParam double max) {
        return service.getByPriceRange(min, max);
    }
}