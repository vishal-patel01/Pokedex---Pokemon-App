import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import PokemonService from '../service/PokemonService';

const PokemonList = () => {
  const [loading, setLoading] = useState(true);
  const [pokemons, setPokemons] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const response = await PokemonService.getPokemons();
        setPokemons(response.data);
      } catch (error) {
        console.log(error);
      }
      setLoading(false);
    };
    fetchData();
  }, []);

  const deletePokemon = (e, id) => {
    e.preventDefault();
    PokemonService.deletePokemonById(id).then(() => {
      if (pokemons) {
        setPokemons((prevElement) => {
          return prevElement.filter((pokemon) => pokemon.id !== id);
        });
      }
    });
  };

  const editPokemon = (e, id) => {
    e.preventDefault();
    navigate(`/editPokemon/${id}`);
  };

  return (
    <div className="max-w-4xl mx-auto my-8 px-4">
      <div>
        <button
          onClick={() => navigate("/addPokemon")}
          className="bg-slate-600 hover:bg-blue-700 my-12 font-semibold px-20 py-2 rounded"
        >
          Add Pokemon
        </button>
      </div>

      <div>
        <table className="shadow w-full">
          <thead className="bg-slate-600">
            <tr>
              <th className="px-6 py-3 uppercase tracking-wide">Name</th>
              <th className="px-6 py-3 uppercase tracking-wide">Breed</th>
              <th className="px-6 py-3 uppercase tracking-wide">Description</th>
              <th className="px-6 py-3 uppercase tracking-wide">Action</th>
            </tr>
          </thead>
          {!loading && (
            <tbody>
              {pokemons.map((pokemon) => (
                <tr key={pokemon.id} className="hover:bg-white hover:text-black">
                  <td className="text-left px-6 py-4 whitespace-nowrap">{pokemon.name}</td>
                  <td className="text-left px-6 py-4 whitespace-nowrap">{pokemon.breed}</td>
                  <td className="text-left px-6 py-4 whitespace-nowrap">{pokemon.description}</td>
                  <td className="text-left px-6 py-4 whitespace-nowrap space-x-2">
                    <a
                      onClick={(e) => editPokemon(e, pokemon.id)}
                      className="hover:text-green-500 hover:cursor-pointer"
                    >
                      Edit 📝
                    </a>
                    <a
                      onClick={(e) => deletePokemon(e, pokemon.id)}
                      className="hover:text-red-500 hover:cursor-pointer"
                    >
                      Delete 🗑️
                    </a>
                  </td>
                </tr>
              ))}
            </tbody>
          )}
        </table>
      </div>
    </div>
  );
};

export default PokemonList;
