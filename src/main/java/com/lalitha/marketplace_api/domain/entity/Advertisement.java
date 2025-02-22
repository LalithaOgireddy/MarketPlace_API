package com.lalitha.marketplace_api.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String category;

    private String brand;

    @NotNull
    private Double price;

    @Value("SEK")
    private String currency;
    private String condition;

    @NotNull
    private LocalDate createdDate;

    @NotNull
    private LocalDate expiryDate;
    private boolean sold;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User seller;

}
