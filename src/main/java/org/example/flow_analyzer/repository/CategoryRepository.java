package org.example.flow_analyzer.repository;



import org.example.flow_analyzer.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
