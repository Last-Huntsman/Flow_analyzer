package org.example.flow_analyzer.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.flow_analyzer.models.Category;
import org.example.flow_analyzer.util.BigDecimalCommaConverter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class TransactionDto {


    @NotNull
    @CsvCustomBindByName(column = "Сумма платежа", converter = BigDecimalCommaConverter.class)
    private BigDecimal operation;
    @NotNull
    @CsvBindByName(column = "Дата платежа")
    @CsvDate("dd.MM.yyyy")
    private LocalDate localDate;
    @NotBlank
    @CsvBindByName(column = "Описание")
    private String description;
    @NotBlank
    @CsvBindByName(column = "Категория")
    private String category;

}
