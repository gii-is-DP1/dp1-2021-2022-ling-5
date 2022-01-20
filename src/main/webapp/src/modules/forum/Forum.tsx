import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useEffect, useState } from 'react';
import { Form, Button, Col, Row, Card, ListGroup } from 'react-bootstrap';
import { withRouter } from 'react-router-dom';
import figureImg from '../../images/figures/figures';

function Forum(props: any) {
  const forumId = props.match.params.id;
  const [playerId, setPlayerId] = useState<number>();
  const [state, setState] = useState<any>();

  const [comment, setComment] = useState<any>({
    text: null,
    forum: null,
    date: Date.now,
    user: null,
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
    if (playerId !== 0) {
      fetch("http://localhost:8080/api/forum/" + forumId)
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
  for (let i = 0; i < state.comments.length; i++) {
    ls[i] = state.comments[i];
  }

  function sortFunction(a: any, b: any) {
    var dateA = new Date(a.date).getTime();
    var dateB = new Date(b.date).getTime();
    return dateA > dateB ? -1 : 1;
  };

  ls.sort(sortFunction);
  function prevent(event: any) {
    event.preventDefault();
    event.target.reset();
    comentar();
  }
  async function comentar() {
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(comment)
    }

    return await fetch(`http://localhost:8080/api/comment/${playerId}/${forumId}`, requestOptions)
      .then((res: any) => {
        document.getElementsByTagName("input")[0].value = "";

      }).catch((err: any) => console.log(err));
  }

  async function deleteComment(commentId: any) {
    const requestOptions = {
      method: 'DELETE',
      'credentials': 'include' as RequestCredentials
    };

    return await fetch(`http://localhost:8080/api/comment/${commentId}`, requestOptions)
      .then(res => {
        console.log(res)
      })
      .catch(error => console.log(error))
  }

  return (
    <div>
      <Row>
        <Col>
          <p id="ptitle">FORUM</p>
        </Col>
      </Row>
      <Row>
        <Col className="m-3">
          <Card>
            <Card.Header as="h3">{state.name}</Card.Header >
            <Card.Body>
              <Form onSubmit={e => prevent(e)}>
                <Form.Group className="mb-3">
                  <Form.Label>New Comment</Form.Label>
                  <Form.Control as="textarea" rows={3} id="text" placeholder="Enter text" onChange={(e) => setComment({ ...comment, text: e.target.value })} />
                </Form.Group>
                <Button variant="dark" onClick={() => comentar()}>
                  Comment
                </Button>
              </Form>
            </Card.Body>
            {
              ls.map(e => (
                <>
                  <Row>
                    <Col>
                      <ListGroup variant="flush">
                        <ListGroup.Item>
                          <img src={figureImg(e.user.figure.id)} width="50" height="50" alt="" /><strong>{e.user.nickname}</strong>
                          <div>{e.text}</div>
                          <div><small className="text-muted ">{e.date}</small></div>
                        </ListGroup.Item>
                      </ListGroup>
                    </Col>
                    {e.user.id === playerId ? <Col> <Button style={{ backgroundColor: "transparent", border: "none", color: "black" }} onClick={() => deleteComment(e.id)} ><FontAwesomeIcon icon={faTrashAlt} /></Button></Col> : <></>}
                  </Row>
                </>
              ))
            }
          </Card>
        </Col>
      </Row>
      {
        ls.map(e => (
            <Row>
                <Col>
                    <strong>{e.user.nickname}:</strong> {e.text}
                </Col>
            </Row>
        ))
        }
    </div>
  );
}

export default withRouter(Forum);
function ClearFields(Controls: any) {
  throw new Error('Function not implemented.');
}

