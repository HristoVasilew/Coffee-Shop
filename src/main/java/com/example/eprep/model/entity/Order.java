package com.example.eprep.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDateTime orderTime;
    @Column(nullable = false)
    private BigDecimal price;
    @ManyToOne
    private User employee;
    @ManyToOne
    private Category category;

    public Order() {
    }

    public String getDescription() {
        return description;
    }

    public Order setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public Order setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public Order setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Order setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public User getEmployee() {
        return employee;
    }

    public Order setEmployee(User employee) {
        this.employee = employee;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Order setCategory(Category category) {
        this.category = category;
        return this;
    }
}





