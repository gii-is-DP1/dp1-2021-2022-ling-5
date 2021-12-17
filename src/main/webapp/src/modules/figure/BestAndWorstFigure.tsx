import React, { useEffect, useState } from 'react';
import { Col, Row } from 'react-bootstrap';
import figures from '../../images/figures/figures.js';
import figureImg from "../../images/figures/figures.js";

function BestAndWorstFigure() {
  let playerId='1';
  const [state, setState] = useState<any>()
  useEffect(() => {
    fetch("http://localhost:8080/api/statistics/maxminfigure/"+playerId)
      .then(res => {
        console.log(res.status)
        return res.json()
      })
      .then(data => setState(data))
      .catch(console.error)
  }, [])
  if (!state) return <div>Loading...</div>
  console.log(state)
    let best=state[0];
    let worst=state[1];
  return (
    <div>
        <Row>
            <Col>
                <h1>Most used:</h1>
                <img src={figures(best.id-1)} width='50px' height='50px' />
            </Col>
        </Row>
        <Row>
            <Col>
                <h1>Least used:</h1>
                <img src={figures(worst.id-1)} width='50px' height='50px' />
            </Col>
        </Row>
    </div>
  );
}

export default BestAndWorstFigure
