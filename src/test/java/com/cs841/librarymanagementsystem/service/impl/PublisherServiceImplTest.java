package com.cs841.librarymanagementsystem.service.impl;

import com.cs841.librarymanagementsystem.exception.NotFoundException;
import com.cs841.librarymanagementsystem.repository.PublisherRepository;
import com.cs841.librarymanagementsystem.entity.Publisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PublisherServiceImplTest {

    @Mock
    PublisherRepository publisherRepository;

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
        Publisher publisher = new Publisher("Dave Greck");
        PublisherServiceImpl publisherService = new PublisherServiceImpl(publisherRepository);
        publisherService.updatePublisher(publisher);
        Mockito.verify(publisherRepository,Mockito.atLeastOnce()).save(publisher);
        Mockito.verifyNoMoreInteractions(publisherRepository);
    }

    @Test
    void deletePublisher() {
        Publisher publisher = new Publisher(" Almond milk ");
        Long publisherId = Integer.toUnsignedLong(24);
        publisher.setId(publisherId);
        Mockito.when(publisherRepository.findById(publisherId)).thenReturn(java.util.Optional.of(publisher));
        PublisherServiceImpl publisherService = new PublisherServiceImpl(publisherRepository);
        publisherService.deletePublisher(publisher.getId());

        Mockito.verify(publisherRepository,Mockito.atLeastOnce()).deleteById(publisher.getId());
        Mockito.verifyNoMoreInteractions(publisherRepository);
    }

    //@Test(expected = NotFoundException.class)
    void deleteNotExistingPublisher(){
        Publisher publisher = new Publisher(" Am not existing  ");
        Long publisherId = Integer.toUnsignedLong(1000000);
        String expectedMessage = String.format("Publisher not found  with ID %d", publisherId);
        publisher.setId(publisherId);
        Mockito.when(publisherRepository.findById(publisherId)).thenThrow(new NotFoundException(expectedMessage));
        PublisherServiceImpl publisherService = new PublisherServiceImpl(publisherRepository);
        Exception actual = assertThrows(NotFoundException.class, () -> publisherService.deletePublisher(publisher.getId()));

        Mockito.verifyNoMoreInteractions(publisherRepository);
    }


    void  findNotExistingPublisher(){
        Publisher publisher = new Publisher(" Am not existing  ");
        Long publisherId = Integer.toUnsignedLong(1000000);
        String expectedMessage = String.format("Publisher not found  with ID %d", publisherId);
        publisher.setId(publisherId);
        Mockito.when(publisherRepository.findById(publisherId)).thenThrow(new NotFoundException(expectedMessage));
        PublisherServiceImpl publisherService = new PublisherServiceImpl(publisherRepository);
        Exception actual =  assertThrows(NotFoundException.class,()-> publisherService.findPublisherById(publisher.getId()));
        assertEquals(expectedMessage,actual.getMessage());
    }


}