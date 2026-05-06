package com.ws101.dizoncolele.EcommerceApi.repository;

import com.ws101.dizoncolele.EcommerceApi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository for Product entity
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    // ✅ Method Naming Query
    List<Product> findByCategoryName(String name);

    // ✅ JPQL Custom Query
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
    List<Product> findProductsByPriceRange(double min, double max);
}