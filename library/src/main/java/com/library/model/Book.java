package com.library.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;


    @NotBlank(message = "Title cannot be empty")
    @Column(nullable = false, unique = true)
    private String title;

    @NotBlank(message = "Author cannot be empty")
    @Column(nullable = false)
    private String author;

    @NotBlank(message = "Genre cannot be empty")
    @Column(nullable = false)
    private String genre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AvailabilityStatus status;
}
