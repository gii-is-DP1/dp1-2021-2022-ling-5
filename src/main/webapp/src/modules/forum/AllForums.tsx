<<<<<<< HEAD
import { link } from 'fs';
import React, { useEffect, useState } from 'react';
import { Col, Row } from 'react-bootstrap';
import figures from '../../images/figures/figures.js';

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
    <div>
      <button onClick={() => window.location.href = `/newforum`}>Añadir nuevo foro</button>
      {
        ls.map(e => (
            <Row>
                <Col>
                    <strong><a href={"/forum/"+e.id}>{e.name}</a></strong>
                </Col>
            </Row>
        ))
        }
    </div>
  );
}

export default AllForums
=======
import { useEffect, useState } from 'react';
import { Col, Row } from 'react-bootstrap';

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
    ls[i] = state[i];
  }

  return (
    <div>
      <button onClick={() => window.location.href = `/newforum`}>Añadir nuevo foro</button>
      {
        ls.map(e => (
          <Row>
            <Col>
              <strong><a href={"/forum/" + e.id}>{e.name}</a></strong>
            </Col>
          </Row>
        ))
      }
    </div>
  );
}

export default AllForums
>>>>>>> develop
