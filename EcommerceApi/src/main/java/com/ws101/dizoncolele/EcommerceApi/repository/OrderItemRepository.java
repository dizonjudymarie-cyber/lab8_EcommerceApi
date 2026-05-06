package com.ws101.dizoncolele.EcommerceApi.repository;

import com.ws101.dizoncolele.EcommerceApi.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}