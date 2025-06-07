import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import PokemonService from '../service/PokemonService';

const AddPokemon = () => {
  const [pokemon, setPokemon] = useState({
    id: "",
    name: "",
    breed: "",
    description: "",
  });

  const handleChange = (e) => {
    const value = e.target.value;
    setPokemon({ ...pokemon, [e.target.name]: value });
  };

  const savePokemon = (e) => {
    e.preventDefault();
    PokemonService.savePokemon(pokemon)
      .then((response) => {
        console.log("saved ", response);
        navigate("/");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const reset = (e) => {
    e.preventDefault();
    setPokemon({
      id: "",
      name: "",
      breed: "",
      description: "",
    });
  };

  const navigate = useNavigate();

  return (
    <div className="max-w-xl mx-auto bg-slate-800 my-20 rounded shadow py-6 px-8">
      <div className="text-3xl font-bold text-center text-white mb-6">
        <p>Add 🧑🏼‍ New Pokemon</p>
      </div>

      <form className="space-y-4 text-white">
        <input
          type="text"
          name="name"
          value={pokemon.name}
          onChange={handleChange}
          className="w-full py-2 px-3 rounded text-slate-800"
          placeholder="Name"
        />

        <input
          type="text"
          name="breed"
          value={pokemon.breed}
          onChange={handleChange}
          className="w-full py-2 px-3 rounded text-slate-800"
          placeholder="Breed"
        />

        <input
          type="text"
          name="description"
          value={pokemon.description}
          onChange={handleChange}
          className="w-full py-2 px-3 rounded text-slate-800"
          placeholder="Description"
        />

        <div className="flex justify-center space-x-4 pt-4">
          <button
            onClick={savePokemon}
            className="bg-green-400 hover:bg-green-700 py-2 px-6 rounded text-white"
          >
            Save
          </button>
          <button
            onClick={reset}
            className="bg-blue-400 hover:bg-blue-700 py-2 px-6 rounded text-white"
          >
            Clear
          </button>
          <button
            onClick={() => navigate("/")}
            className="bg-red-400 hover:bg-red-700 py-2 px-6 rounded text-white"
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
};

export default AddPokemon;
