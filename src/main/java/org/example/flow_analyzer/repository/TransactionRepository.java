package org.example.flow_analyzer.repository;

import org.example.flow_analyzer.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
