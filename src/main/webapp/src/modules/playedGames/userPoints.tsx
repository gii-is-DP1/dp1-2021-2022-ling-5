import { useEffect, useState } from 'react';
import { Col, Row } from 'react-bootstrap';
import resultAPI from '../result/resultAPI';

function UserPoints(props: any) {
  const playerId: any = 1;
  const [results, setResults] = useState<any[]>([]);

  useEffect(() => {
    resultAPI.getAllResultsByPlayer(playerId)
      .then((res: any[]) => setResults(res))
      .catch((err) => console.log(err));
  }, [])

  if (!results) return <div>Loading...</div>
  return (
    <Row>
      {
        results.map(res => (
          <Row>
            <Col><h1>{res.game.name}</h1></Col>
            {res.game.minigames.length > 1
              ? [0, 1, 2].map(i => (
                <Col>
                  <h2>{res.game.minigames[i].name}</h2>
                  <h3>{res.data.split(" ")[i]}</h3>
                </Col>
              ))
              : <Col>
                <h2>{res.game.minigames[0].name}</h2>
                <h2>{res.game.minigames[0].name}</h2>
                <h2>{res.game.minigames[0].name}</h2>
                <h3>{res.data}</h3>
              </Col>
            }
          </Row>
        ))
      }
    </Row>
  );
}


export default UserPoints;
