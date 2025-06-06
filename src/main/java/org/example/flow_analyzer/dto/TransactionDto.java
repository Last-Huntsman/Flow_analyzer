package org.example.flow_analyzer.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.flow_analyzer.models.Category;

import java.time.LocalDateTime;

public class TransactionDto {
    @NotNull
    @DecimalMin(value = "0.01", message = "Amount must be positive")
    private double operation;
    @NotNull
    private LocalDateTime DataTime;
    @NotBlank
    private String description;
    @NotBlank
    Category сategory;
}
