package com.cs841.librarymanagementsystem.service.impl;

import com.cs841.librarymanagementsystem.entity.Book;
import com.cs841.librarymanagementsystem.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    BookRepository bookRepository;

    @Test
    void findAllBooks(){
        List<Book> books  = new ArrayList<>();
        for(int i = 0 ; i < 20; i++){
            books.add( new Book("isbn","name","serialName","description"+i));
        }

        Mockito.when(bookRepository.findAll()).thenReturn(books);
        BookServiceImpl bookService = new BookServiceImpl(bookRepository);
        List<Book> allBooks = bookService.findAllBooks();
        assertIterableEquals(books,allBooks);
    }


    @Test
    void searchBooks(){
        Book book = new Book("343fs","test object1","334566","testing searching");
        String bookid = new String(String.valueOf(50));
        Mockito.when(bookRepository.findById(Long.valueOf(bookid))).thenReturn(java.util.Optional.of(book));
        BookServiceImpl bookService = new BookServiceImpl(bookRepository);
        Book bookByID =bookService.findBookById(Long.valueOf(bookid));
        assertEquals(book,bookByID);

    }

    @Test
    void findBookById(){
        Book book = new Book("isbn","name","serialName","description");
        Long bookid = Integer.toUnsignedLong(24);
        Mockito.when(bookRepository.findById(bookid)).thenReturn(java.util.Optional.of(book));
        BookServiceImpl bookService = new BookServiceImpl(bookRepository);
        Book bookByID =bookService.findBookById(bookid);
        assertEquals(book,bookByID);


    }

    @Test
    void createBook(){
        Book book= new Book("isbn","name","serialName","description");
        BookServiceImpl bookService = new BookServiceImpl(bookRepository);
        bookService.createBook(book);
        Mockito.verify(bookRepository,Mockito.atLeastOnce()).save(book);
        Mockito.verifyNoMoreInteractions(bookRepository);


    }
    @Test
    void updateBook() {
        Book book= new Book("134","testcase","38477487","testing object");
        BookServiceImpl bookService = new BookServiceImpl(bookRepository);
        bookService.updateBook(book);
        Mockito.verify(bookRepository,Mockito.atLeastOnce()).save(book);
        Mockito.verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void deleteBook() {
        Book book= new Book("isbn","name","serialName","description");
    }


}

