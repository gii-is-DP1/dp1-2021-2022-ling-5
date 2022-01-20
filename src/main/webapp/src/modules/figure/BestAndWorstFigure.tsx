import React, { useEffect, useState } from 'react';
import { Card, Col, Row } from 'react-bootstrap';
import figureImg from '../../images/figures/figures.js';
import figures from '../../images/figures/figures.js';

function BestAndWorstFigure() {

  const [state, setState] = useState<any>()
  useEffect(() => {
    var playerId = 0;
    var userData = localStorage.getItem("userData")
    if (userData !== null) playerId = JSON.parse(userData).id
    if (playerId !== 0) {
      fetch("http://localhost:8080/api/statistics/maxminfigure/" + playerId)
        .then(res => {
          console.log(res.status)
          return res.json()
        })
        .then(data => setState(data))
        .catch(console.error)
    }

  }, [])
  if (!state) return <div>Loading...</div>
  console.log(state)
  let best = state[0];
  let worst = state[1];
  return (
    <div>
      <Card className="m-1">
        <Card.Header as="h4">Figures</Card.Header>
        <Card.Body>
          <h5>Figure most found:</h5> <img src={figures(best.id - 1)} alt="Most used" width='50px' height='50px' /> <Row/>
          <h5>Figure least found:</h5> <img src={figures(worst.id - 1)} alt="Least used" width='50px' height='50px' /> <Row/>
        </Card.Body>
      </Card>
     
    </div>
  );
}

export default BestAndWorstFigure
