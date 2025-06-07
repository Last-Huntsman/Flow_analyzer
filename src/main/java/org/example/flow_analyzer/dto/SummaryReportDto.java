package org.example.flow_analyzer.dto;

import java.math.BigDecimal;

public record SummaryReportDto(BigDecimal totalExpenses, BigDecimal totalIncome, BigDecimal averagePerDay) {}

