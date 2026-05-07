package com.ai.demo.repository;

import com.ai.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    //Danh cho App/Web danh cho nguoi dung
    List<Category> findAllByIsActiveTrueOrderByDisplayOrderAsc();


    //Danh cho admin
    List<Category> findAllByOrderByDisplayOrderAsc();

    Optional<Category> findBySlug(String slug);

    boolean existsBySlug(String slug);

    Category getCategoryById(UUID id);
}
