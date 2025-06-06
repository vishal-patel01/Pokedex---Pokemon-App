package com.lise.infotech.Lise_InfoTech.service;

import com.lise.infotech.Lise_InfoTech.Entity.Pokemon;
import com.lise.infotech.Lise_InfoTech.dto.PokemonDTO;

import java.util.List;

public interface PokemonService {


    //create
    PokemonDTO createPokemon(PokemonDTO pokemonDTO);

    //update
    PokemonDTO updatePokemon(PokemonDTO pokemonDTO, Long pokemonId);

    //Delete
    void deletePokemon(Long pokemonId);

    //getSingle Pokemon
    PokemonDTO getPokemon(Long pokemonId);

    //    //getAllCategory
    List<PokemonDTO> getAllPokemon();
}
