package com.lise.infotech.Lise_InfoTech.repository.implementation;

import com.lise.infotech.Lise_InfoTech.Entity.Pokemon;
import com.lise.infotech.Lise_InfoTech.dto.PokemonDTO;
import com.lise.infotech.Lise_InfoTech.repository.PokemonRepository;
import com.lise.infotech.Lise_InfoTech.exception.ResourceNotFoundException;
import com.lise.infotech.Lise_InfoTech.service.PokemonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PokemonDTO createPokemon(PokemonDTO pokemonDTO) {
        Pokemon pokemon = this.modelMapper.map(pokemonDTO, Pokemon.class);
        pokemon.setId(null);
        Pokemon savedPokemon = this.pokemonRepository.save(pokemon);
        return this.modelMapper.map(savedPokemon, PokemonDTO.class);
    }

    @Override
    public PokemonDTO updatePokemon(PokemonDTO pokemonDTO, Long pokemonId) {
        Pokemon oldPokemon = this.pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon", "Pokemon Id", pokemonId));

        if (pokemonDTO.getName() != null) oldPokemon.setName(pokemonDTO.getName());
        if (pokemonDTO.getBreed() != null) oldPokemon.setBreed(pokemonDTO.getBreed());
        if (pokemonDTO.getDescription() != null) oldPokemon.setDescription(pokemonDTO.getDescription());

        Pokemon updatedPokemon = this.pokemonRepository.save(oldPokemon);

        return this.modelMapper.map(updatedPokemon, PokemonDTO.class);
    }

    @Override
    public void deletePokemon(Long pokemonId) {
        Pokemon pokemon = this.pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon", "Pokemon Id", pokemonId));
        this.pokemonRepository.delete(pokemon);
    }

    @Override
    public PokemonDTO getPokemon(Long pokemonId) {
        Pokemon pokemon = this.pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon", "Pokemon Id", pokemonId));
        return this.modelMapper.map(pokemon, PokemonDTO.class);
    }

    @Override
    public List<PokemonDTO> getAllPokemon() {
        List<Pokemon> pokemons = this.pokemonRepository.findAll();
        return pokemons.stream()
                .map(pokemon -> this.modelMapper.map(pokemon, PokemonDTO.class))
                .collect(Collectors.toList());
    }

    // Removed duplicate getAllPokemons() and createPokemon(Pokemon pokemon) methods
}
