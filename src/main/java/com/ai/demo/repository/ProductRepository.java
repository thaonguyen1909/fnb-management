package com.ai.demo.repository;

import com.ai.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsBySlug(String slug);

    List<Product> findAllByCategoryIdOrderByCreatedAtDesc(UUID categoryId);
}
