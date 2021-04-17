package com.cs841.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs841.librarymanagementsystem.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
