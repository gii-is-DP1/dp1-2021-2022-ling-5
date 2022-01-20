import React, { useEffect, useState } from 'react';
import figures from "../../images/figures/figures.js";
import '../../App.css'
import { Button, Col, Form, Row } from 'react-bootstrap';
import movimiento from '../game/movimiento';

function NewForum() {
    const [playerId, setPlayerId] = useState<number>();
    
    const [forum, setForum] = useState<any>({
      name: null,
      creationDate: Date.now,
    });

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
  }, [])

  function prevent(event:any){
        event.preventDefault();
        anadir();
  }
  async function anadir() {
    const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(forum)
        }

        return await fetch(`http://localhost:8080/api/forum/new`, requestOptions)
            .then((res: any) => {
              window.location.href = `/forums`;

            }).catch((err: any) => console.log(err));
  }

  return (
    <div>
      <Row>
        <Col>
          <h1>Add new forum</h1>
        </Col>
      </Row>
      <Row>
        <Col>
        <Form onSubmit={e => prevent(e)}>
        <Form.Group className="mb-3">
          <Form.Label>New Comment</Form.Label>
          <Form.Control placeholder="Enter text" onChange={(e) => setForum({ ...forum, name: e.target.value })} />
        </Form.Group>
        <Button className="Button" size="lg" variant="dark" onClick={() => anadir()}>
          Add forum
        </Button>
        </Form>
        </Col>
      </Row>
    </div>
  );
}

export default NewForum;