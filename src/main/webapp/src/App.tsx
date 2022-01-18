import './App.css';
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
