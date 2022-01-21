import { useEffect, useState } from 'react';
import { Badge, Card, Col, Row } from 'react-bootstrap';
import ChartsPage from './ChartsPage';

function UserFrequency() {

  const [frequency, setFrequency] = useState<any[]>([]);
  useEffect(() => {
    var userData: any = localStorage.getItem("userData");
    if (userData !== null) userData = JSON.parse(userData)
    const playerId = userData.id
    fetch(`http://localhost:8080/api/statistics/playerpergame/${playerId}`)
      .then(res => {
        return res.json()
      })
      .then(data => setFrequency(data))
      .catch(console.error)

  }, [])
  if (!frequency) return <div>Loading...</div>
  let f2 = frequency[2];
  let f3 = frequency[3];
  let f4 = frequency[4];
  let f5 = frequency[5];
  let f6 = frequency[6];
  let f7 = frequency[7];
  let f8 = frequency[8];

  console.log(frequency);
  return (
    <div><Card className="m-1">

    <Card.Body>
        <h4>Ratio of games played to total number of players played </h4>
        <Row className="text-center d-flex">
        <Col>
          <Row>
            <Col> <Badge bg="primary"> </Badge> {' '}Frec with 2 players: {f2} %</Col>
          </Row>
          <Row>
            <Col><Badge bg="success"> </Badge> {' '}Frec with 3 players: {f3}  %</Col>
          </Row>
          <Row>
            <Col><Badge bg="secondary"> </Badge> {' '}Frec with 4 players: {f4} %</Col>
          </Row>
          <Row>
            <Col><Badge bg="info"> </Badge> {' '}Frec with 5 players: {f5} %</Col>
          </Row>
          <Row>
            <Col><Badge bg="warning"> </Badge> {' '}Frec 6 players: {f6} %</Col>
          </Row>
          <Row>
            <Col><Badge bg="dark"> </Badge> {' '}Frecuencia 7 jugadores: {f7} %</Col>
          </Row>
          <Row>
            <Col><Badge bg="danger"> </Badge> {' '}Frecuencia 8 jugadores: {f8} %</Col>
          </Row>
      </Col>
        <Col><ChartsPage frequency={frequency}  width="10%" /></Col>
      </Row>
      </Card.Body>
      </Card>
    </div>
  );
}


export default UserFrequency;