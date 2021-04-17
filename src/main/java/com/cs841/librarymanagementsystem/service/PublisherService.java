package com.cs841.librarymanagementsystem.service;

import java.util.List;

import com.cs841.librarymanagementsystem.entity.Publisher;

public interface PublisherService {

	public List<Publisher> findAllPublishers();

	public Publisher findPublisherById(Long id);

	public void createPublisher(Publisher publisher);

	public void updatePublisher(Publisher publisher);

	public void deletePublisher(Long id);

}
