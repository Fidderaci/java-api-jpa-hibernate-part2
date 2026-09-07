package com.booleanuk.api.controller;

import com.booleanuk.api.models.Publisher;
import com.booleanuk.api.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable Integer id) {
        Publisher publisher = publisherService.getPublisherById(id);
        if (publisher == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(publisher);
    }

    @PostMapping
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        if (publisher.getName() == null || publisher.getLocation() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Publisher savedPublisher = publisherService.createPublisher(publisher);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPublisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Integer id, @RequestBody Publisher publisherDetails) {
        if (publisherDetails.getName() == null || publisherDetails.getLocation() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Publisher updatedPublisher = publisherService.updatePublisher(id, publisherDetails);
        if (updatedPublisher == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedPublisher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Publisher> deletePublisher(@PathVariable Integer id) {
        Publisher publisher = publisherService.deletePublisher(id);
        if (publisher == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(publisher);
    }
}