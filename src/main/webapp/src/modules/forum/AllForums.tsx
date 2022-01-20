import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useEffect, useState } from 'react';
import { Button, Card, Col, Row } from 'react-bootstrap';

import './AllForums.css';

function AllForums() {
    const [playerId, setPlayerId] = useState<number>();
const [state, setState] = useState<any>();
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
    if (playerId !== 0) {
      fetch("http://localhost:8080/api/forum/all")
        .then(res => {
          console.log(res.status)
          return res.json()
        })
        .then(data => setState(data))
        .catch(console.error)
    }

  }, [state])
  if (!state) return <div>Loading...</div>

  let ls = []
  for (let i = 0; i < state.length; i++) {
    ls[i] =state[i];
  }

  return (
    <div id="page">
      <p id="ptitle">FORUM</p>
      <div id="createUser" className="m-2" style={{ textAlign: "right" }}>
        <Button variant="dark" onClick={() => window.location.href = `/newforum`}>Añadir nuevo foro <FontAwesomeIcon icon={faPlus} /></Button>
      </div>
      {
        ls.map(e => (
          <Row>
            <Col>
            <Card>
            <Card.Body>
              <div className="d-flex justify-content-between">
                <Button variant="dark-link" href={"/forum/" + e.id}><strong><u>{e.name}</u></strong></Button>
                <small className="text-muted ">{e.creationDate}</small>
              </div>
            </Card.Body>
            </Card>
            </Col>
          </Row>
        ))
        }
    </div>
  );
}

export default AllForums
