package com.booleanuk.api.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private Boolean alive;

    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties(value = {"author", "publisher"}, allowSetters = true)
    @ToString.Exclude
    private List<Book> books;
}
