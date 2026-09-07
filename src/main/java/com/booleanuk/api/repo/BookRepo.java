package com.booleanuk.api.repo;

import com.booleanuk.api.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer> {
}
