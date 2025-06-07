import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom"
import AddPokemon from './components/AddPokemon';
import UpdatePokemon from './components/UpdatePokemon';
import PokemonList from './components/PokemonList';
import NavBar from './components/NavBar';


function App() {
  return (
    <>
    <BrowserRouter>
    <NavBar/>

    <Routes>
       <Route index element={ <PokemonList/> } />
       <Route path="/" element={ <PokemonList/> } />
       <Route path="/addPokemon" element={<AddPokemon />} />
       <Route path="/editPokemon/:id" element={<UpdatePokemon />} />

    </Routes>
    </BrowserRouter>
    </>
  );
}

export default App;
