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

    public CategoryDao(EntityManager em) {
        this.em = em;
    }

    public void save(Category category) {
        em.persist(category);
    }

    public Optional<Category> findByName(String name) {
        TypedQuery<Category> query = em.createQuery("SELECT t FROM Category t WHERE t.category = :name", Category.class);
        query.setParameter("name", name);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public List<Category> findAll() {
        TypedQuery<Category> query = em.createQuery("SELECT t FROM Category t", Category.class);
        return query.getResultList();
    }
}

