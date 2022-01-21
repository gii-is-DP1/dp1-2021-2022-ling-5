import { useEffect, useState } from 'react';
import { Card, Col, Row } from 'react-bootstrap';

function UserPoints(props: any) {
  const [results, setResutls] = useState<any[]>([]);
  useEffect(() => {
    var userData: any = localStorage.getItem("userData");
    if (userData !== null) userData = JSON.parse(userData)
    const playerId = userData.id
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
    <div >
        <Card className="m-1">
        <Card.Header as="h4">
          Total points by minigame
        </Card.Header>
        <Card.Body>
          <strong>Torre Infernal: {minigame1}</strong><Row/>
          <strong>Foso: {minigame2}</strong><Row/>
          <strong>Regalo Envenenado: {minigame3}</strong><Row/>
        </Card.Body>
        <Card.Footer>
          <h5>Total: {total}</h5>
        </Card.Footer>
         </Card>
    </div>
  );
}


export default UserPoints;