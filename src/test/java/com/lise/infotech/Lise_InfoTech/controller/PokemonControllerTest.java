package com.lise.infotech.Lise_InfoTech.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lise.infotech.Lise_InfoTech.dto.PokemonDTO;
import com.lise.infotech.Lise_InfoTech.service.PokemonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(PokemonController.class)
public class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokemonService pokemonService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreatePokemon() throws Exception {
        PokemonDTO input =  new PokemonDTO(null, "Pikachu", "Electric", "This is Pikachu");
        PokemonDTO output = new PokemonDTO(1L, "Pikachu", "Electric", "This is Pikachu");

        Mockito.when(pokemonService.createPokemon(any(PokemonDTO.class))).thenReturn(output);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/pokemons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Pikachu"));
    }

    @Test
    public void testGetPokemonById() throws Exception {
        PokemonDTO output = new PokemonDTO(1L, "Pikachu", "Electric", "This is Pikachu");

        Mockito.when(pokemonService.getPokemon(1L)).thenReturn(output);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pokemons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pikachu"));
    }

    @Test
    public void testGetAllPokemons() throws Exception {
        Mockito.when(pokemonService.getAllPokemon()).thenReturn(java.util.List.of(
                new PokemonDTO(1L, "Pikachu", "Electric", "Desc1"),
                new PokemonDTO(2L, "Charmander", "Fire", "Desc2")
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pokemons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void testDeletePokemon() throws Exception {
        Mockito.doNothing().when(pokemonService).deletePokemon(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/pokemons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Pokemon deleted successfully"));
    }

    @Test
    public void testUpdatePokemon() throws Exception {
        PokemonDTO input = new PokemonDTO(null, "UpdatedName", "Electric", "Updated Desc");
        PokemonDTO updated = new PokemonDTO(1L, "UpdatedName", "Electric", "Updated Desc");

        Mockito.when(pokemonService.updatePokemon(any(PokemonDTO.class), any(Long.class))).thenReturn(updated);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/pokemons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("UpdatedName"));
    }
}
