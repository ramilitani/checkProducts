package com.exercise.dott.checkproducts.repository;

import com.exercise.dott.checkproducts.dto.CustomInterval;
import com.exercise.dott.checkproducts.models.OrderPlacedInterval;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomOrderIntervalRepository {
    List<OrderPlacedInterval> findByCustomInterval(List<CustomInterval> customIntervals, String initialDate, String finalDate);
}
