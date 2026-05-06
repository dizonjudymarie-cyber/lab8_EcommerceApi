package com.ws101.dizoncolele.EcommerceApi.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Order Entity
 * One Order has many OrderItems
 */
@Entity
@Table(name = "customer_orders") // ✅ fixed
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> items;

    public Order() {}

    // GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}