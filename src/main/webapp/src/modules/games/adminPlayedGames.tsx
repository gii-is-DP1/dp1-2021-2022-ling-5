import { useEffect, useState } from 'react';
import { Row, Col, Container } from 'react-bootstrap';


let urlParams = new URLSearchParams(window.location.search);
let playerId = urlParams.get("playerId");

function AdminPlayedGames() {

  const [result, setResult] = useState<any>()
  useEffect(() => {
    fetch("http://localhost:8080/api/results")
      .then(res => {
        console.log(res.status)
        return res.json()
      })
      .then(data => setResult(data))
      .catch(console.error)
  }, [])
  if (!result) return <div>Loading...</div>
  let en_progreso = []
  let jugados = []
  for (var r of result) {
    if (r.game.state === "FINISHED") {
      jugados.push(r)
    } else if (r.game.state === "IN_PROGRESS") {
      en_progreso.push(r)
    }
  }
  if (en_progreso.length === 0) {
    en_progreso.push({ game: { name: "Ninguno" }, data: "" })
  }
  if (jugados.length === 0) {
    jugados.push({ game: { name: "Ninguno" }, data: "" })
  }
  return (
    <Container>
      <Row>
        <Col>
          <Row> <h1>Juegos en progreso</h1> </Row>
          {
            en_progreso.map(e => (
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

export default AdminPlayedGames