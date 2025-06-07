import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import PokemonService from '../service/PokemonService';

const UpdatePokemon = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [pokemon, setPokemon] = useState({
    id: id,
    name: "",
    breed: "",
    description: "",
  });

  const handleChange = (e) => {
    const value = e.target.value;
    setPokemon({ ...pokemon, [e.target.name]: value });
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await PokemonService.getPokemonById(id);
        setPokemon(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, [id]);

  const updatePokemon = (e) => {
    e.preventDefault();
    PokemonService.updatePokemon(pokemon, id)
      .then((response) => {
        console.log("updated ", response);
        navigate("/");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div className="max-w-xl mx-auto bg-slate-800 my-20 rounded shadow py-6 px-8">
      <div className="text-3xl font-bold text-center text-white mb-6">
        <p>Update 🧑🏼 Pokemon</p>
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
            onClick={updatePokemon}
            className="bg-green-400 hover:bg-green-700 py-2 px-6 rounded text-white"
          >
            Update
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

export default UpdatePokemon;
