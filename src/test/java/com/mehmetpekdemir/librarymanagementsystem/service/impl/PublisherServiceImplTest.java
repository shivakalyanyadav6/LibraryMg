package com.mehmetpekdemir.librarymanagementsystem.service.impl;

import com.mehmetpekdemir.librarymanagementsystem.entity.Publisher;
import com.mehmetpekdemir.librarymanagementsystem.repository.PublisherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PublisherServiceImplTest {

    @Mock
    PublisherRepository  publisherRepository;

    @Test
    void findAllPublishers() {
        List<Publisher>  publishers  = new ArrayList<>();
        for(int i = 0 ; i < 20; i++){
           publishers.add( new Publisher("user "+i ));
        }
        Mockito.when(publisherRepository.findAll()).thenReturn(publishers);
        PublisherServiceImpl   publisherService = new PublisherServiceImpl(publisherRepository);
        List<Publisher> allPublishers = publisherService.findAllPublishers();
        assertIterableEquals(publishers, allPublishers);
    }

    @Test
    void findPublisherById(){
        Publisher publisher = new Publisher("Robert John");
        Long publisherId = Integer.toUnsignedLong(24);
        Mockito.when(publisherRepository.findById(publisherId)).thenReturn(java.util.Optional.of(publisher));
        PublisherServiceImpl   publisherService = new PublisherServiceImpl(publisherRepository);
        Publisher publisherById = publisherService.findPublisherById(publisherId);
        assertEquals(publisher,publisherById);
    }

    @Test
    void createPublisher() {
        Publisher publisher = new Publisher("Dave Greck");
        PublisherServiceImpl publisherService = new PublisherServiceImpl(publisherRepository);
        publisherService.createPublisher(publisher);
        Mockito.verify(publisherRepository,Mockito.atLeastOnce()).save(publisher);
        Mockito.verifyNoMoreInteractions(publisherRepository);

    }

    @Test
    void updatePublisher() {
    }

    @Test
    void deletePublisher() {
    }
}