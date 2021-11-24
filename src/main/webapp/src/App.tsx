import React from 'react';
import logo from './logo.svg';
import './App.css';
import Button from 'react-bootstrap/Button';
import FiguresByCard from './modules/cards/figuresByCard';
import { Link } from 'react-router-dom';


function App() {

  return (
  
    <div className="App-header"> 
      <p>
        Dobble
      </p>
      <div className="Button">
      <Link to="/newgame">
        <button>
          New Game
        </button>
      </Link>
      <Link to="/joingame">
        <button>
          Join Game
        </button>
      </Link>
      </div>
      
        
    </div>

    
  );
}


export default App ;
