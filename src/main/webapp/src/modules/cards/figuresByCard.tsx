import React, { Props, useEffect, useState } from 'react';
import figures from "../../images/figures/figures.js";
import '../../App.css'
import { Col, Row } from 'react-bootstrap';
import movimiento from '../games/movimiento';

//<FiguresByCard id='19'/>

const FiguresByCard = (data:any) => {
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
  if (!state){ return <div>Loading...</div>}
  else{console.log(movimiento(1,state))}
    let ls:any[]=[]
    for (let i = 0; i < state.figures.length; i++){
      ls[i]=figures(state.figures[i].id-1)
    }
  return (
    <Row>
        {
          ls.map(ls=>(
            <Col>
            <img src={ls} width='100%' alt="logo" />
            </Col>
          ))
        }
    </Row>
  );
}

export default FiguresByCard;