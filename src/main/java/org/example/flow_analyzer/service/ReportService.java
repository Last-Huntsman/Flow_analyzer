package org.example.flow_analyzer.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.example.flow_analyzer.dto.CategoryReportDto;
import org.example.flow_analyzer.dto.MonthlyReportDto;
import org.example.flow_analyzer.dto.SummaryReportDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    @PersistenceContext
    private final EntityManager entityManager;

    // 🔹 5.1: Отчёт по категориям
    public List<CategoryReportDto> getReportByCategory() {
        return entityManager.createQuery("""
                    SELECT DISTINCT new org.example.flow_analyzer.dto.CategoryReportDto(c.category, SUM(t.operation))
                    FROM Transaction t
                    JOIN t.category c
                    GROUP BY c.category
                    ORDER BY SUM(t.operation) DESC
                """, CategoryReportDto.class).getResultList();
    }

    // 🔹 5.2: Отчёт по месяцам
    public List<MonthlyReportDto> getReportByMonth() {
        List<Object[]> resultList = entityManager.createQuery("""
        SELECT DISTINCT FUNCTION('TO_CHAR', t.localDate, 'YYYY-MM'), SUM(t.operation)
        FROM Transaction t
        GROUP BY FUNCTION('TO_CHAR', t.localDate, 'YYYY-MM')
        ORDER BY FUNCTION('TO_CHAR', t.localDate, 'YYYY-MM')
    """).getResultList();

        return resultList.stream()
                .map(r -> new MonthlyReportDto((String) r[0], (BigDecimal) r[1]))
                .toList();
    }


    // 🔹 5.3: Общий отчёт (summary)
    public SummaryReportDto getSummary() {

        BigDecimal expenses = entityManager.createQuery("""
                    SELECT  DISTINCT COALESCE(SUM(t.operation), 0)
                    FROM Transaction t
                    WHERE t.operation < 0
                """, BigDecimal.class).getSingleResult();

        BigDecimal income = entityManager.createQuery("""
                    SELECT DISTINCT COALESCE(SUM(t.operation), 0)
                    FROM Transaction t
                    WHERE t.operation > 0
                """, BigDecimal.class).getSingleResult();

        List<BigDecimal> dailySums = entityManager.createQuery("""
                    SELECT DISTINCT SUM(t.operation)
                    FROM Transaction t
                    GROUP BY t.localDate
                """, BigDecimal.class).getResultList();

        BigDecimal avgPerDay = BigDecimal.ZERO;
        if (!dailySums.isEmpty()) {
            BigDecimal sum = dailySums.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            avgPerDay = sum.divide(BigDecimal.valueOf(dailySums.size()), BigDecimal.ROUND_HALF_UP);
        }

        return new SummaryReportDto(expenses.abs(), income, avgPerDay);
    }
}

