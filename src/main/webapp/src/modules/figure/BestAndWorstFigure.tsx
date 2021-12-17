import React, { useEffect, useState } from 'react';
import { Col, Row } from 'react-bootstrap';
import figures from '../../images/figures/figures.js';

function BestAndWorstFigure() {
  let playerId = '1';
  const [state, setState] = useState<any>()
  useEffect(() => {
    fetch("http://localhost:8080/api/players/" + playerId + "/playerfigures")
      .then(res => {
        console.log(res.status)
        return res.json()
      })
      .then(data => setState(data))
      .catch(console.error)
  }, [])
  if (!state) return <div>Loading...</div>
  let best = state[0];
  let worst = state[0];
  console.log(best.succesful)
  for (let i = 0; i < state.length; i++) {
    console.log(state[i].succesful)
    if (best.succesful < state[i].succesful) {
      best = state[i];
    }
    if (worst.succesful > state[i].succesful) {
      worst = state[i];
    }
  }
  return (
    <div>
      <Row>
        <Col>
          <h1>Most used:</h1>
          <img src={figures(best.figure.id - 1)} width='50px' height='50px' />
        </Col>
      </Row>
      <Row>
        <Col>
          <h1>Least used:</h1>
          <img src={figures(worst.figure.id - 1)} width='50px' height='50px' />
        </Col>
      </Row>
    </div>
  );
}

export default BestAndWorstFigure
