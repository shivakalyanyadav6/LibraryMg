package com.cs841.librarymanagementsystem.repository;

import com.cs841.librarymanagementsystem.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
