package com.lise.infotech.Lise_InfoTech.controller;

import com.lise.infotech.Lise_InfoTech.Entity.Pokemon;
import com.lise.infotech.Lise_InfoTech.dto.ApiResponse;
import com.lise.infotech.Lise_InfoTech.dto.PokemonDTO;
import com.lise.infotech.Lise_InfoTech.exception.ResourceNotFoundException;
import com.lise.infotech.Lise_InfoTech.service.PokemonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api/pokemons")
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;


    //Creation of Pokemon
    @PostMapping("")
    public ResponseEntity<PokemonDTO> createPokemon( @Valid  @RequestBody PokemonDTO pokemonDTO)
    {
        PokemonDTO pokemons =this.pokemonService.createPokemon(pokemonDTO);
        return new ResponseEntity<PokemonDTO>(pokemons , HttpStatus.CREATED);
    }



    //Updation of Pokemon by Id
    @PutMapping("/{pokemonId}")
    public ResponseEntity<ApiResponse> updatePokemon(@Valid @RequestBody PokemonDTO pokemonDTO, @PathVariable Long pokemonId) {
        this.pokemonService.updatePokemon(pokemonDTO, pokemonId);
        return new ResponseEntity<>(new ApiResponse("Updated successfully", true), HttpStatus.OK);
    }


    //delete
    @DeleteMapping("/{pokemonId}")
    public ResponseEntity<ApiResponse> deletePokemon(@PathVariable Long pokemonId)
    {
        this.pokemonService.deletePokemon(pokemonId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Pokemon deleted successfully" , true), HttpStatus.OK);
    }



    //get by Id
    @GetMapping("/{pokemonId}")
    public ResponseEntity<PokemonDTO> getPokemon(@PathVariable Long pokemonId)
    {
        PokemonDTO pokemon = this.pokemonService.getPokemon(pokemonId);
        return new ResponseEntity<PokemonDTO>(pokemon , HttpStatus.OK);
    }




   //get All Pokemon
    @GetMapping("")
    public ResponseEntity<List<PokemonDTO>>  getPokemons()
    {
        List<PokemonDTO> pokemon = this.pokemonService.getAllPokemon();
        return  ResponseEntity.ok( pokemon);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiResponse response = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
