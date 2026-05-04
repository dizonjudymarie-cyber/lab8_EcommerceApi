package com.ws101.dizoncolele.EcommerceApi.service;

import com.ws101.dizoncolele.EcommerceApi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> productList = new ArrayList<>();
    private Long nextId = 1L;

    // CREATE
    public Product addProduct(Product product) {
        product.setId(nextId++);
        productList.add(product);
        return product;
    }

    // READ ALL
    public List<Product> getAllProducts() {
        return productList;
    }

    // READ BY ID
    public Optional<Product> getProductById(Long id) {
        return productList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    // UPDATE
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existing = getProductById(id);

        if (existing.isPresent()) {
            Product p = existing.get();
            p.setName(updatedProduct.getName());
            p.setDescription(updatedProduct.getDescription());
            p.setPrice(updatedProduct.getPrice());
            p.setQuantity(updatedProduct.getQuantity());
            return Optional.of(p);
        }

        return Optional.empty();
    }

    // DELETE
    public boolean deleteProduct(Long id) {
        return productList.removeIf(p -> p.getId().equals(id));
    }
}