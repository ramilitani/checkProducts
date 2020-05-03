package com.exercise.dott.checkproducts.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedInterval {
    String ranges;
    int count;

    public static List<OrderPlacedInterval> converter(List<Object[]> orders) {
        List<OrderPlacedInterval> orderPlacedIntervalList = orders.stream()
            .map(o -> new OrderPlacedInterval((String) o[0], ((BigInteger) o[1]).intValue()))
            .collect(Collectors.toList());
        return orderPlacedIntervalList;
    }
}
