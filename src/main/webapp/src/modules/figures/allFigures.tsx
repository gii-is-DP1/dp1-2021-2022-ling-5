import React, { useEffect, useState } from 'react';
import figures from "../../images/figures/figures.js";
import './App.css';
import figuresService from '../../services/figures.service';

function allFigures() {

  const [figure, setFigures] = useState<any[]>([]);
    useEffect(()=>{
        figuresService.getAll()
        .then((response: any) => {
            console.log('Printing games data', response.data);
            setFigures(response.data);
          })
          .catch((error: any) => {
            console.log('Something went wrong', error);
          })    
      }, []);
    let ls=[]
    for (let i = 0; i < figure.length; i++){
      ls[i]=figures(figure[i].id)
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

export default allFigures;
