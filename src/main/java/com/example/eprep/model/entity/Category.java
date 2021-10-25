package com.example.eprep.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private CategoryNameEnum name;
    @Column(nullable = false)
    private Integer neededTime;

    @OneToMany(mappedBy = "category")
    private Set<Order> orders;

    public Category() {
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public Category setOrders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }

    public Integer getNeededTime() {
        return neededTime;
    }

    public Category setNeededTime(Integer neededTime) {
        this.neededTime = neededTime;
        return this;
    }

    public CategoryNameEnum getName() {
        return name;
    }

    public Category setName(CategoryNameEnum name) {
        this.name = name;
        return this;
    }
}
