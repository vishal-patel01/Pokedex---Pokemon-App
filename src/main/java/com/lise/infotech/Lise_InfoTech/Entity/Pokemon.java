package com.lise.infotech.Lise_InfoTech.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pokemons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String breed;

    @Column(length = 1000)
    private String description;
}

