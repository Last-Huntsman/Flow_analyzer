package org.example.flow_analyzer.controller;

import lombok.RequiredArgsConstructor;
import org.example.flow_analyzer.service.CsvImportService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/import")
@RequiredArgsConstructor
@Validated

public class CsvController {

    private final CsvImportService csvImportService;

    @PostMapping("/csv")
    public ResponseEntity<?> importCsv(@RequestParam("file") MultipartFile file) {
        var errors = csvImportService.importCsv(file);

        if (errors.isEmpty()) {
            return ResponseEntity.ok("CSV imported successfully");
        } else {
            return ResponseEntity.badRequest().body(errors);
        }
    }
}
