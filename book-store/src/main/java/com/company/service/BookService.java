package com.company.service;

import com.company.enums.ErrorCodeEnum;
import com.company.exception.CustomRestException;
import com.company.model.Book;
import com.company.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> findBookById(Integer bookId) {
        return bookRepository.findById(bookId);
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new CustomRestException(ErrorCodeEnum.BOOK_NOT_FOUND));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void removeBook(Integer id) {
        bookRepository.delete(getBookById(id));
    }

    public List<Book> findBookByNameAndAuthor(String name, String surname) {
        return bookRepository.findBookByNameAndAndAuthor(name, surname);
    }

    public void update(Book book, Integer id) {
        Book book1 = bookRepository.findById(id).
                orElseThrow(() -> new CustomRestException(ErrorCodeEnum.BOOK_NOT_FOUND));
        book1.setName(book.getName());
        book1.setAuthor(book.getAuthor());
        book1.setPrice(book.getPrice());
        book1.setStock(book.getStock());
        bookRepository.save(book1);
    }
}
