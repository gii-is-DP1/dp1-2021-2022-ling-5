import { useEffect, useState } from 'react';
import { Row, Col, Container } from 'react-bootstrap';
import resultAPI from '../result/resultAPI';


let urlParams = new URLSearchParams(window.location.search);
let playerId = urlParams.get("playerId");

function AdminPlayedGames() {

  const [results, setResults] = useState<any[]>()

  useEffect(() => {
    resultAPI.getAllResults()
      .then((res: any[]) => setResults(res))
      .catch(err => console.log(err));
  }, [])

  if (!results) return <div>Loading...</div>

  let en_progreso = []
  let jugados = []
  for (var r of results) {
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