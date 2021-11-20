import React from 'react';
import logo from './images/figures/mano.png';
import './App.css';
import Button from 'react-bootstrap/Button';
import FiguresByCard from './modules/cards/figuresByCard';

function App() {

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
          <FiguresByCard id='6'/>
      </header>
    </div>
  );
}

export default App;
 