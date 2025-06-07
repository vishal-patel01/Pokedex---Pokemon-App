import axios from 'axios';

const POKEMON_API_BASE_URL = "http://localhost:8081/api/pokemons";

class PokemonService {
  savePokemon(pokemon) {
    return axios.post(POKEMON_API_BASE_URL, pokemon);
  }

  getPokemons() {
    return axios.get(POKEMON_API_BASE_URL);
  }

  getPokemonById(id) {
    return axios.get(`${POKEMON_API_BASE_URL}/${id}`);
  }

  deletePokemonById(id) {
    return axios.delete(`${POKEMON_API_BASE_URL}/${id}`);
  }

  updatePokemon(pokemon, id) {
    return axios.put(`${POKEMON_API_BASE_URL}/${id}`, pokemon);
  }
}

const pokemonServiceInstance = new PokemonService();
export default pokemonServiceInstance;
