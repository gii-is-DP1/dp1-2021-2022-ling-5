import { useEffect, useState } from 'react';
import { Row, Col, Container } from 'react-bootstrap';

let urlParams = new URLSearchParams(window.location.search);
let playerId = urlParams.get("playerId");

function PlayedGames() {
  const player = 3
  const [state, setState] = useState<any>()
  useEffect(() => {
    fetch("http://localhost:8080/api/players/" + player + "/results")
      .then(res => {
        console.log(res.status)
        return res.json()
      })
      .then(data => setState(data))
      .catch(console.error)
  }, [])
  if (!state) return <div>Loading...</div>
  let creados = []
  let jugados = []
  for (var r of state) {
    if (r.game.creator === player) {
      creados.push(r)
    } else {
      jugados.push(r)
    }
  }
  if (creados.length === 0) {
    creados.push({ game: { name: "Ninguno" }, data: "" })
  }
  if (jugados.length === 0) {
    jugados.push({ game: { name: "Ninguno" }, data: "" })
  }
  return (
    <Container>
      <Row>
        <Col>
          <Row> <h1>Juegos creados</h1> </Row>
          {
            creados.map(e => (
              <Row>
                <strong>{e.game.name} </strong><p>{e.data}</p>
              </Row>
            ))
          }
        </Col>
        <Col>
          <Row> <h1>Juegos jugados</h1> </Row>
          {
            jugados.map(e => (
              <Row>
                <strong>{e.game.name}: </strong><p>{e.data}</p>
              </Row>
            ))
          }
        </Col>
      </Row>
    </Container>
  );
}

export default PlayedGames