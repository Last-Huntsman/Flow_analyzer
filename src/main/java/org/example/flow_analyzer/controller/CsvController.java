package org.example.flow_analyzer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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


    @PostMapping(value = "/csv", consumes = "multipart/form-data")
    public ResponseEntity<?> importCsv(
            @Parameter(description = "CSV file to upload") @RequestParam("file") MultipartFile file) {
        var errors = csvImportService.importCsv(file);

        if (errors.isEmpty()) {
            return ResponseEntity.ok("CSV imported successfully");
        } else {
            return ResponseEntity.badRequest().body(errors);
        }
    }
}
