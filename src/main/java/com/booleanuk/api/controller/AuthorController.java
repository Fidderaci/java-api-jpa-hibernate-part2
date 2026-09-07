package com.booleanuk.api.controller;

import com.booleanuk.api.models.Author;
import com.booleanuk.api.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Integer id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(author);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        if (author.getFirstName() == null || author.getLastName() == null || author.getEmail() == null || author.getAlive() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Author savedAuthor = authorService.createAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Integer id, @RequestBody Author authorDetails) {
        if (authorDetails.getFirstName() == null || authorDetails.getLastName() == null || authorDetails.getEmail() == null || authorDetails.getAlive() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Author updatedAuthor = authorService.updateAuthor(id, authorDetails);
        if (updatedAuthor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Integer id) {
        Author author = authorService.deleteAuthor(id);
        if (author == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(author);
    }
}