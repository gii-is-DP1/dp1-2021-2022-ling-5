import React from 'react';
// import logo from './images/figures/mano.png';
import './App.css';
// import Button from 'react-bootstrap/Button';
// import FiguresByCard from './modules/cards/figuresByCard';
// import AllFigures from './modules/figures/allFigures';
import { Container, Row } from 'react-bootstrap';


function App() {

  return (
    <div className="App-header">
      <Container>
        <Row>
          <p>
            Dobble
          </p>
        </Row>
        <Row>
          <button onClick={() => window.location.href = '/newGame'}>
            New Game
          </button>
          <Row>
          </Row>
          <button onClick={() => window.location.href = '/joinGame'} >
            Join Game
          </button>
        </Row>
      </Container>
    </div >

  );
}

export default App;
