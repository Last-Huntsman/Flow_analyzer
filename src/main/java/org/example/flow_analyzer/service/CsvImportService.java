package org.example.flow_analyzer.service;

import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import org.example.flow_analyzer.dao.CategoryDao;
import org.example.flow_analyzer.dao.TransactionDao;
import org.example.flow_analyzer.dto.TransactionDto;
import org.example.flow_analyzer.models.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RequiredArgsConstructor
@Service
public class CsvImportService {
    private final Validator validator;
    private final CategoryDao categoryDao;
    private final TransactionDao transactionDao;

    public List<String> importCsv(MultipartFile file) {
        List<String> errors = new ArrayList<>();

        try (var reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            var csvList = new CsvToBeanBuilder<TransactionDto>(reader)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withType(TransactionDto.class)
                    .build()
                    .parse();

            for (int i = 0; i < csvList.size(); i++) {
                TransactionDto dto = csvList.get(i);

                Set<ConstraintViolation<TransactionDto>> violations = validator.validate(dto);
                if (!violations.isEmpty()) {
                    for (var v : violations) {
                        errors.add("Line " + (i + 1) + ": " + v.getMessage());
                    }
                    continue;
                }

                try {
                    var categoryOpt = categoryDao.findByName(dto.getCategory());

                    if (categoryOpt.isEmpty()) {
                        errors.add("Line " + (i + 1) + ": Unknown category: " + dto.getCategory());
                        continue;
                    }

                    Transaction tx = new Transaction();
                    tx.setLocalDate(dto.getLocalDate());
                    tx.setDescription(dto.getDescription());
                    tx.setOperation(dto.getOperation());
                    tx.setCategory(categoryOpt.get());

                    transactionDao.save(tx);
                } catch (Exception e) {
                    errors.add("Line " + (i + 1) + ": " + e.getMessage());
                }
            }

        } catch (Exception e) {
            errors.add("Failed to read file: " + e.getMessage());
        }

        return errors;
    }

}
