import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import deck from "./images/deck/deck.js";
import figures from "./images/figures/figures.js";
import './App.css';

function App() {
  const [state, setState] = useState("")
  useEffect(() => {
    fetch("http://localhost:8080/api/figures")
      .then(res => {
        console.log(res.status)
        return res.json()
      })
      .then(data => setState(data[0].name))
      .catch(console.error)
  }, [])
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.tsx</code> and save to reloa hplaa.
          Del servidor: {state}
        </p>

        <img src={figures.ancla} width='100%' alt="logo" />
      </header>
    </div>
  );
}

export default App;
