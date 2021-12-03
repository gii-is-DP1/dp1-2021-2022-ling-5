import { useEffect, useState } from 'react';
import { Col, Row } from 'react-bootstrap';

function UserPoints(props: any) {
    const playerId: any = 1;
    const [results, setResutls] = useState<any[]>([]);
    useEffect(() => {
      fetch(`http://localhost:8080/api/players/${playerId}/results`)
        .then(res => {
            return res.json()
        })
        .then(data => setResutls(data))
        .catch(console.error)
  
    }, [])
    if (!results) return <div>Loading...</div>
    return (
      <Row>
        {
          results.map(res=>(
            <Row>
                <Col><h1>{res.game.name}</h1></Col>
                {res.game.minigames.length>1
                ? [0,1,2].map(i=>(
                    <Col>
                        <h2>{res.game.minigames[i].name}</h2>
                        <h3>{res.data.split(" ")[i]}</h3>
                    </Col>
                ))
                : <Col>
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
  