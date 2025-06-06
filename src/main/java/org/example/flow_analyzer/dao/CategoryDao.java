package org.example.flow_analyzer.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.Data;
import org.example.flow_analyzer.models.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Data
@Repository
public class CategoryDao {

    private final EntityManager em;

    // Конструктор, EntityManager передаётся извне (например, через DI)
    public CategoryDao(EntityManager em) {
        this.em = em;
    }

    // Сохранение новой транзакции
    public void save(Category category) {
        em.getTransaction().begin();// начинаем транзакцию
        em.persist(category);// сохраняем сущность
        em.getTransaction().commit();// фиксируем изменения
    }

    // Поиск по ID
    public Optional<Category> findByName(String name) {
        TypedQuery<Category> query = em.createQuery("SELECT t FROM Category t WHERE category =: name ", Category.class);
        query.setParameter("name", name);
        try {
            Category category = query.getSingleResult();
            return Optional.of(category);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    // Получить все транзакции
    public List<Category> findAll() {
        TypedQuery<Category> query = em.createQuery("SELECT t FROM Category t", Category.class);
        return query.getResultList();
    }

}
