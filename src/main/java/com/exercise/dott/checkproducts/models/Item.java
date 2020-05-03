package com.exercise.dott.checkproducts.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private BigDecimal cost;
    @Column(nullable = false)
    private BigDecimal shippingFee;
    @Column(nullable = false)
    private BigDecimal taxAmount;
    @OneToOne
    @NotNull
    private Product product;
}
