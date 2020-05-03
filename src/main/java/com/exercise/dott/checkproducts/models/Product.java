package com.exercise.dott.checkproducts.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private Double weight;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private LocalDateTime creation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId()) &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getCategory(), product.getCategory()) &&
                Objects.equals(getWeight(), product.getWeight()) &&
                Objects.equals(getPrice(), product.getPrice()) &&
                Objects.equals(getCreation(), product.getCreation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCategory(), getWeight(), getPrice(), getCreation());
    }
}
