import { useEffect, useState } from 'react';
import { Row, Col, Container } from 'react-bootstrap';
import resultAPI from '../result/resultAPI';
import token from '../user/token';
import userAPI from '../user/userAPI';
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
        <Row> <h1 id="ptitle">MY GAMES</h1> </Row>
        <Col>
          <Row> <h3>Created games</h3> </Row>
          {
            created.map(e => (
              <Row>
                <strong>Game: {e.game.name} </strong><p>Points: {e.data} <br/>{e.game.winner===Number(token.getLoggedId())?<span>Juego ganado</span>:<span>Juego perdido</span>}</p>
              </Row>
            ))
          }
        </Col>
        <Col>
          <Row> <h3>Played games</h3> </Row>
          {
            played.map(e => (
              <Row>
                <strong>Game: {e.game.name} </strong><p>Points: {e.data}<br/>{e.game.winner===Number(token.getLoggedId())?<span>Juego ganado</span>:<span>Juego perdido</span>}</p>
              </Row>
            ))
          }
        </Col>
      </Row>
    </Container>
  );
}

export default PlayedGames
