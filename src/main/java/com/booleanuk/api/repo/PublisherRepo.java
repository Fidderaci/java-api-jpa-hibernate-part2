package com.booleanuk.api.repo;

import com.booleanuk.api.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepo extends JpaRepository<Publisher, Integer> {
}
