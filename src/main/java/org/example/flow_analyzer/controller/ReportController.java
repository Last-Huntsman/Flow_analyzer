package org.example.flow_analyzer.controller;

import lombok.RequiredArgsConstructor;
import org.example.flow_analyzer.dto.CategoryReportDto;
import org.example.flow_analyzer.dto.MonthlyReportDto;
import org.example.flow_analyzer.dto.SummaryReportDto;
import org.example.flow_analyzer.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // 🔹 5.1: Категории
    @GetMapping("/by-category")
    public ResponseEntity<List<CategoryReportDto>> byCategory() {
        return ResponseEntity.ok(reportService.getReportByCategory());
    }

    // 🔹 5.2: Месяцы
    @GetMapping("/by-month")
    public ResponseEntity<List<MonthlyReportDto>> byMonth() {
        return ResponseEntity.ok(reportService.getReportByMonth());
    }

    // 🔹 5.3: Сводка
    @GetMapping("/summary")
    public ResponseEntity<SummaryReportDto> summary() {
        return ResponseEntity.ok(reportService.getSummary());
    }
}
