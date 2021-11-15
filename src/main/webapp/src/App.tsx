import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import deck from "./images/deck/deck.js";
import figures from "./images/figures/figures.js";
import './App.css';
import figuresService from './services/figures.service';

function App() {

  const [games, setGames] = useState<any[]>([]);
    useEffect(()=>{
        figuresService.getAll()
        .then(response => {
            console.log('Printing games data', response.data);
            setGames(response.data);
          })
          .catch(error => {
            console.log('Something went wrong', error);
          })    
      }, []);
    let ls=[]
    for (let i = 0; i < games.length; i++){
      ls[i]=figures(games[i].id)
    }
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        
        {
          ls.map(ls=>(
            <div>
            <img src={ls} width='100%' alt="logo" />
            </div>
          ))
        }
      </header>
    </div>
  );
}

export default App;
