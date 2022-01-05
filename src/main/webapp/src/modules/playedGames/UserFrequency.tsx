import { useEffect, useState } from 'react';
import { Badge, Col, Row } from 'react-bootstrap';
import resultAPI from '../result/resultAPI';
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
    <div className="text-center">
        
      <Row>
        
        <Col> <Badge bg="primary"> </Badge> {' '}Frecuencia 2 jugadores: {f2} %</Col>
      </Row>
      <Row>
        <Col><Badge bg="success"> </Badge> {' '}Frecuencia 3 jugadores: {f3}  %</Col>
      </Row>
      <Row>
        <Col><Badge bg="secondary"> </Badge> {' '}Frecuencia 4 jugadores: {f4} %</Col>
      </Row>
      <Row>
        <Col><Badge bg="info"> </Badge> {' '}Frecuencia 5 jugadores: {f5} %</Col>
      </Row>
      <Row>
        <Col><Badge bg="warning"> </Badge> {' '}Frecuencia 6 jugadores: {f6} %</Col>
      </Row>
      <Row>
        <Col><Badge bg="dark"> </Badge> {' '}Frecuencia 7 jugadores: {f7} %</Col>
      </Row>
      <Row>
        <Col><Badge bg="danger"> </Badge> {' '}Frecuencia 8 jugadores: {f8} %</Col>
      </Row>

      <ChartsPage frequency={frequency} />
    </div>
  );
}


export default UserFrequency;