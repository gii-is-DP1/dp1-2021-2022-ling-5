import Button from 'react-bootstrap/Button';
import { Form } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import gameAPI from '../game/gameAPI';

function StartGame(props: any) {
  const gameId: any = useParams();
  const { id } = gameId;
  const [players, setPlayers] = useState<any[]>([]);

  useEffect(() => {
    gameAPI.getPlayersByGame(id)
      .then((pls: any[]) => setPlayers(pls))
      .catch((err) => console.log(err));
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
          {players.map((el, index) =>
            <p key={index}>Player {el.id} {el.nickname}</p>
          )}

        </div>

        <Button className="Button" size="lg" variant="dark">
          START
        </Button>
      </Form>
    </div>


  );
}


export default StartGame;
