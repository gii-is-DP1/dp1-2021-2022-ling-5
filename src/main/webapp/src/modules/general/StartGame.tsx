import Button from 'react-bootstrap/Button';
import { Form } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router';




function StartGame(props: any) {
  const gameId: any = useParams();
  const { id } = gameId;
  const [players, SetPlayers] = useState<any[]>([]);
  useEffect(() => {

    const requestOptions = {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
    }

    fetch(`http://localhost:8080/api/players/games/${id}`, requestOptions)
      .then(res => {
        res.json().then((playersSearched: any) => {
          SetPlayers(playersSearched);
        }).catch(e => console.log(e));
        window.location.href = '/startGame'
      })
      .catch(error => console.log(error));

  }, [])

  return (

    <div className="NewGame-header">
      <p>GAME</p>
      <Form>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Waiting for players</Form.Label>

        </Form.Group>

        <Button className="Button" size="lg" variant="dark">
          START
        </Button>

        <div>
          {[...Array(players.length)].map((el, index) => {
            return <p key={index}>Player {players[index].id} {players[index].nickname}</p>
          })}

        </div>
      </Form>
    </div>


  );
}


export default StartGame;
