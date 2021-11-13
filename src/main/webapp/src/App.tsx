import React from 'react';
import logo from './logo.svg';
import deck from "./images/deck/deck.js";
import figures from "./images/figures/figures.js";
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        
        <img src={figures.trex} width='100%' alt="logo"/>
      </header>
    </div>
  );
}

export default App;
