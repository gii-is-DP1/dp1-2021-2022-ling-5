import Button from 'react-bootstrap/Button';
import { Form } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router';




function StartGame(props: any) {
  const gameId: any = useParams();
  const { id } = gameId;
  const [players, setPlayers] = useState<any[]>([]);

  const getPlayers = () => {
    const requestOptions = {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
    }

    fetch(`http://localhost:8080/api/games/${id}/players`, requestOptions)
      .then(res => {
        res.json().then((playersSearched: any) => {
          setPlayers(playersSearched);
        }).catch(e => console.log(e));
      })
      .catch(error => console.log(error));
  }

  useEffect(() => {
    getPlayers();
  }, [])

  if (!players) return <p>Loading...</p>
  return (

    <div className="NewGame-header">
      <p>GAME</p>
      <Form>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Waiting for players</Form.Label>

        </Form.Group>

        <div>
          {players.map((el, index) => {
            return <p key={index}>Player {el.id} {el.nickname}</p>
          })}

        </div>

        <Button className="Button" size="lg" variant="dark">
          START
        </Button>
      </Form>
    </div>


  );
}


export default StartGame;
