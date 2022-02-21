package ru.avalon.gateway.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avalon.gateway.persistance.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
