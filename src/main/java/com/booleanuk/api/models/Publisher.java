package com.booleanuk.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "publishers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String location;

    @OneToMany(mappedBy = "publisher")
    @JsonIgnoreProperties(value = {"publisher", "author"}, allowSetters = true)
    @ToString.Exclude
    private List<Book> books;
}
