package com.lalitha.marketplace_api.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String city;
    @OneToOne
    @JoinColumn(name = "user_email")
    @NotNull
    private User user;
}
