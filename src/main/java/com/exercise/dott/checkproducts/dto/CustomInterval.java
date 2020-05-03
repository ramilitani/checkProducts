package com.exercise.dott.checkproducts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomInterval {

    private Integer initMonth;
    private Integer finalMonth;
    private String description;
    private boolean isGreaterThan;

}
