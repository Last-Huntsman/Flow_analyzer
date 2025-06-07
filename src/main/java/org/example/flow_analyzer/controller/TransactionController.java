package org.example.flow_analyzer.controller;

import lombok.RequiredArgsConstructor;
import org.example.flow_analyzer.models.Transaction;
import org.example.flow_analyzer.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        List<Transaction> transactions = transactionService.getAllTransactions(page, size);
        long total = transactionService.countTransactions();

        Map<String, Object> response = new HashMap<>();
        response.put("transactions", transactions);
        response.put("total", total);
        response.put("page", page);
        response.put("size", size);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Transaction>> searchTransactions(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal min,
            @RequestParam(required = false) BigDecimal max) {

        List<Transaction> results = transactionService.searchTransactions(category, min, max);
        return ResponseEntity.ok(results);
    }

}
