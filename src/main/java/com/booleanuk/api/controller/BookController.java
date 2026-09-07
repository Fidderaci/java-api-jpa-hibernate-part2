package com.booleanuk.api.controller;

import com.booleanuk.api.models.Author;
import com.booleanuk.api.models.Book;
import com.booleanuk.api.models.Publisher;
import com.booleanuk.api.repo.AuthorRepo;
import com.booleanuk.api.repo.PublisherRepo;
import com.booleanuk.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorRepo authorRepository;

    @Autowired
    private PublisherRepo publisherRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        if (book.getTitle() == null || book.getGenre() == null || book.getAuthor() == null || book.getPublisher() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Author author = authorRepository.findById(book.getAuthor().getId()).orElse(null);
        Publisher publisher = publisherRepository.findById(book.getPublisher().getId()).orElse(null);

        if (author == null || publisher == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        book.setAuthor(author);
        book.setPublisher(publisher);

        Book savedBook = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book bookDetails) {
        if (bookDetails.getTitle() == null || bookDetails.getGenre() == null || bookDetails.getAuthor() == null || bookDetails.getPublisher() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Author author = authorRepository.findById(bookDetails.getAuthor().getId()).orElse(null);
        Publisher publisher = publisherRepository.findById(bookDetails.getPublisher().getId()).orElse(null);

        if (author == null || publisher == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        bookDetails.setAuthor(author);
        bookDetails.setPublisher(publisher);

        Book updatedBook = bookService.updateBook(id, bookDetails);
        if (updatedBook == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Integer id) {
        Book book = bookService.deleteBook(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(book);
    }
}