import React, { Props, useEffect, useState } from 'react';
import figures from "../../images/figures/figures.js";
import '../../App.css'

const FiguresByCard = (data:any) => {
  console.log(data.id)
  const [state, setState] = useState<any>()
  useEffect(() => {
    fetch("http://localhost:8080/api/cards/"+data.id)
      .then(res => {
        console.log(res.status)
        return res.json()
      })
      .then(data => setState(data))
      .catch(console.error)
  }, [])
  if (!state) return <div>Loading...</div>
    let ls:any[]=[]
    for (let i = 0; i < state.figures.length; i++){
      ls[i]=figures(state.figures[i].id-1)
    }
  return (
    <div>
        {
          ls.map(ls=>(
            <div className='rowImages'>
            <img src={ls} width='100%' alt="logo" />
            </div>
          ))
        }
    </div>
  );
}

export default FiguresByCard;