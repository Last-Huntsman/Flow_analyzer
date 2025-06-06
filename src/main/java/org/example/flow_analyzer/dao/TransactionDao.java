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

    // Конструктор, EntityManager передаётся извне (например, через DI)
    public TransactionDao(EntityManager em) {
        this.em = em;
    }

    // Сохранение новой транзакции
    public void save(Transaction transaction) {
        em.getTransaction().begin();// начинаем транзакцию
        em.persist(transaction);// сохраняем сущность
        em.getTransaction().commit();// фиксируем изменения
    }

    // Поиск по ID
    public Optional<Transaction> findById(int id) {
        Transaction transaction = em.find(Transaction.class, id);
        return Optional.ofNullable(transaction);
    }

    // Получить все транзакции
    public List<Transaction> findAll() {
        TypedQuery<Transaction> query = em.createQuery("SELECT t FROM Transaction t", Transaction.class);
        return query.getResultList();
    }

}
