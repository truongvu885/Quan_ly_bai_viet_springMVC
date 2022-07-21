package com.springmvc.repository;

import com.springmvc.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findCategoryEntityByCode(String code);
}
