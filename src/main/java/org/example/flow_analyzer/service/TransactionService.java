package org.example.flow_analyzer.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.example.flow_analyzer.models.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {


    private final EntityManager entityManager;

    public List<Transaction> getAllTransactions(int page, int size) {
        return entityManager.createQuery("SELECT t FROM Transaction t ORDER BY t.localDate DESC", Transaction.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    public long countTransactions() {
        return entityManager.createQuery("SELECT COUNT(t) FROM Transaction t", Long.class)
                .getSingleResult();
    }
    public List<Transaction> searchTransactions(
            String category,
            BigDecimal minAmount,
            BigDecimal maxAmount
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
        Root<Transaction> root = query.from(Transaction.class);
        List<Predicate> predicates = new ArrayList<>();

        // Присоединяем таблицу category для фильтрации по названию
        if (category != null && !category.isBlank()) {
            predicates.add(cb.equal(root.get("category").get("name"), category));
        }

        // Фильтрация по минимальной сумме
        if (minAmount != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("amount"), minAmount));
        }

        // Фильтрация по максимальной сумме
        if (maxAmount != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("amount"), maxAmount));
        }

        query.select(root)
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(cb.desc(root.get("localDate"))); // сортировка по дате

        return entityManager.createQuery(query).getResultList();
    }

}
