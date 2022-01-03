import { useEffect, useState } from 'react';
import { Col, Row } from 'react-bootstrap';
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
    <div>
        <ChartsPage></ChartsPage>
      {/* <Row>
        <Col>Frecuencia 2: {f2}</Col>
      </Row>
      <Row>
        <Col>Frecuencia 3: {f3}</Col>
      </Row>
      <Row>
        <Col>Frecuencia 4: {f4}</Col>
      </Row>
      <Row>
        <Col>Frecuencia 5: {f5}</Col>
      </Row>
      <Row>
        <Col>Frecuencia 6: {f6}</Col>
      </Row>
      <Row>
        <Col>Frecuencia 7: {f7}</Col>
      </Row>
      <Row>
        <Col>Frecuencia 8: {f8}</Col>
      </Row> */}
    </div>
  );
}


export default UserFrequency;