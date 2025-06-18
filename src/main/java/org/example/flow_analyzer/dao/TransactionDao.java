package org.example.flow_analyzer.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.Data;
import org.example.flow_analyzer.models.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Data
@Repository
public class TransactionDao {

    private final EntityManager em;

    public TransactionDao(EntityManager em) {
        this.em = em;
    }

    public void save(Transaction transaction) {
        em.persist(transaction);
    }

    public Optional<Transaction> findById(int id) {
        return Optional.ofNullable(em.find(Transaction.class, id));
    }

    public List<Transaction> findAll() {
        TypedQuery<Transaction> query = em.createQuery("SELECT t FROM Transaction t", Transaction.class);
        return query.getResultList();
    }
}
