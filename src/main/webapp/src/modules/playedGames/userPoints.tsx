import { useEffect, useState } from 'react';
import { Col, Row } from 'react-bootstrap';
import resultAPI from '../result/resultAPI';

function UserPoints(props: any) {
  const playerId: any = 1;
  const [results, setResutls] = useState<any[]>([]);
  useEffect(() => {
    fetch(`http://localhost:8080/api/statistics/pointsbyminigames/${playerId}`)
      .then(res => {
        return res.json()
      })
      .then(data => setResutls(data))
      .catch(console.error)

  }, [])
  if (!results) return <div>Loading...</div>
  let total = results[0];
  let minigame1 = results[1];
  let minigame2 = results[2];
  let minigame3 = results[3];

  return (
    <div>
      <Row>
        <Col>Total: {total}</Col>
      </Row>
      <Row>
        <Col>Torre Infernal: {minigame1}</Col>
        <Col>Foso: {minigame2}</Col>
        <Col>Regalo Envenenado: {minigame3}</Col>
      </Row>
    </div>
  );
}


export default UserPoints;