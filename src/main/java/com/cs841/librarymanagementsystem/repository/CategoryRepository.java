package com.cs841.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs841.librarymanagementsystem.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
