import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import figures from "../../images/figures/figures.js";
import './App.css';

function App() {
  const [state, setState] = useState<any>()
  useEffect(() => {
    fetch("http://localhost:8080/api/cards/4")
      .then(res => {
        console.log(res.status)
        return res.json()
      })
      .then(data => setState(data))
      .catch(console.error)
  }, [])
    let ls:any[]=[]
    for (let i = 0; i < state.figures.length; i++){
      ls[i]=figures(state.figures[i].id-1)
    }
  return (
    <div>
        {
          ls.map(ls=>(
            <div>
            <img src={ls} width='100%' alt="logo" />
            </div>
          ))
        }
    </div>
  );
}

export default App;