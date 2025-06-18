package org.example.flow_analyzer.dto;

import lombok.Data;

import java.math.BigDecimal;

public record MonthlyReportDto(String month, BigDecimal total) {}

