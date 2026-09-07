package com.booleanuk.api.service;

import com.booleanuk.api.models.Publisher;
import com.booleanuk.api.repo.PublisherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepo publisherRepository;

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher getPublisherById(Integer id) {
        return publisherRepository.findById(id).orElse(null);
    }

    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public Publisher updatePublisher(Integer id, Publisher publisherDetails) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        if (publisher != null) {
            publisher.setName(publisherDetails.getName());
            publisher.setLocation(publisherDetails.getLocation());
            return publisherRepository.save(publisher);
        }
        return null;
    }

    public Publisher deletePublisher(Integer id) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        if (publisher != null) {
            publisherRepository.delete(publisher);
            return publisher;
        }
        return null;
    }
}