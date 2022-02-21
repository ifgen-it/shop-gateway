package ru.avalon.gateway.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avalon.gateway.persistance.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
