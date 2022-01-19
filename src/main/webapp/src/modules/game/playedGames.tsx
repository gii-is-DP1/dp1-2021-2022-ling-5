import { useEffect, useState } from 'react';
import { Row, Col, Container } from 'react-bootstrap';
import resultAPI from '../result/resultAPI';
import './playedGames.css'

function PlayedGames() {
  const [results, setResults] = useState<any[]>();
  const [playerId, setPlayerId] = useState<number>();

  useEffect(() => {
    var userData: any = localStorage.getItem("userData");
    var id = 0;
    if (userData !== null) {
      userData = JSON.parse(userData);
      if (userData !== null) {
        setPlayerId(userData.id)
        id = userData.id
      }
    }
    resultAPI.getAllResultsByPlayer(id)
      .then((res: any[]) => setResults(res))
      .catch((err) => console.log(err));
  }, [])

  if (!results && !playerId) return <div>Loading...</div>

  let created = []
  let played = []
  if (results !== undefined) {
    for (var r of results) {
      if (r.game.creator === playerId) {
        created.push(r)
      } else {
        played.push(r)
      }
    }
  }


  if (created.length === 0) {
    created.push({ game: { name: "Ninguno" }, data: "" })
  }
  if (played.length === 0) {
    played.push({ game: { name: "Ninguno" }, data: "" })
  }

  return (
    <Container id="container">
      <Row>
        <Col>
          <Row> <h1>Created games</h1> </Row>
          {
            created.map(e => (
              <Row>
                <strong>{e.game.name} </strong><p>{e.data}</p>
              </Row>
            ))
          }
        </Col>
        <Col>
          <Row> <h1>Played games</h1> </Row>
          {
            played.map(e => (
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