package com.lise.infotech.Lise_InfoTech.repository;

import com.lise.infotech.Lise_InfoTech.Entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
