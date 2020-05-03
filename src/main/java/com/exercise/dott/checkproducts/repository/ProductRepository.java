package com.exercise.dott.checkproducts.repository;

import com.exercise.dott.checkproducts.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
