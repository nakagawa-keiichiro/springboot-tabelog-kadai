package com.example.nagoyameshi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.nagoyameshi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	@Query(value ="select * from category where category_name LIKE %:keyword% ", nativeQuery = true)
	public Category findById(@Param("keyword")String keyword);
}
