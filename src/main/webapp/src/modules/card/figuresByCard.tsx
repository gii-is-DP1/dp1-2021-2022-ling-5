import React, { Props, useEffect, useState } from 'react';
import figures from "../../images/figures/figures.js";
import '../../App.css'
import { Col, Row } from 'react-bootstrap';
import movimiento from '../game/movimiento';
import cardAPI from './cardAPI';

//<FiguresByCard id='19'/>

const FiguresByCard = (data: any) => {
  const [card, setCard] = useState<any>()

  useEffect(() => {
    cardAPI.getCardById(data.id)
      .then((cd: any) => setCard(cd))
      .catch((err) => console.log(err));
  }, [])

  if (!card) return <div>Loading...</div>
  else console.log(movimiento(1, card))

  let ls: any[] = []
  for (let i = 0; i < card.figures.length; i++) {
    ls[i] = figures(card.figures[i].id - 1)
  }

  return (
    <Row>
      {
        ls.map(ls => (
          <Col>
            <img src={ls} width='100%' alt="logo" />
          </Col>
        ))
      }
    </Row>
  );
}

export default FiguresByCard;