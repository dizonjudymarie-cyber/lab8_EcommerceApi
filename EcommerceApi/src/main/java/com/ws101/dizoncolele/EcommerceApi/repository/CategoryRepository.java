package com.ws101.dizoncolele.EcommerceApi.repository;

import com.ws101.dizoncolele.EcommerceApi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Category entity
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}