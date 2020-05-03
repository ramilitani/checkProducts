package com.exercise.dott.checkproducts.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "Order_Table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private String contact;
    @Column(nullable = false)
    private String shippingAddress;
    @Column(nullable = false)
    private Double grandTotal;
    @Column(nullable = false)
    private LocalDateTime orderDate;
    @OneToMany
    @Column(nullable = false)
    private Set<Item> items;

}
