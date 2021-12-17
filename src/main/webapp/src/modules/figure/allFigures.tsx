import React, { useEffect, useState } from 'react';
import figureImg from "../../images/figures/figures.js";

function AllFigures() {
  const [state, setState] = useState<any>()
  useEffect(() => {
    fetch("http://localhost:8080/api/figures")
      .then(res => {
        console.log(res.status)
        return res.json()
      })
      .then(data => setState(data))
      .catch(console.error)
  }, [])
  if (!state) return <div>Loading...</div>
    let ls=[]
    for (let i = 0; i < state.length; i++){
      ls[i]=figureImg(state[i].id)
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

export default AllFigures
