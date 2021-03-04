package com.chopa.wdyw.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chopa.wdyw.category.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
